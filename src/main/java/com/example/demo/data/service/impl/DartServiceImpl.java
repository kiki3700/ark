package com.example.demo.data.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.constants.ReprtCode;
import com.example.demo.data.mapper.DartMapper;
import com.example.demo.data.mapper.ItemMapper;
import com.example.demo.data.service.DartService;
import com.example.demo.util.DartUtil;
import com.example.demo.util.FormatConverter;
import com.example.demo.vo.BalanceSheetDto;
import com.example.demo.vo.ItemDto;

@Service
public class DartServiceImpl implements DartService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${dartkey}")
	String crtfcKey;
	@Value("${darturl}")
	String dartUrl;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient webClient;
	
	
	@Autowired
	private DartUtil dartUtil;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private DartMapper dartMapper;
	
	@Autowired
	private FormatConverter formatConverter;
	
	@Override
	public void insBalaceSheet(Map<String, Object> inParam) {
//		String targetUrl = dartUrl+"/api/fnlttSinglAcnt.json?crtfc_key={crtfcKey}&corp_code={corpCode}&bsns_year={year}&reprt_code={reprtCode}";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("crtfcKey", crtfcKey);
//		System.out.print(crtfcKey);
		String corpCode = (String) inParam.getOrDefault("corpCode", null);
		int year = (int) inParam.getOrDefault("year", 0);
		String reprtCode = (String) inParam.getOrDefault("reprtCode", null);
		map.put("corpCode", corpCode);
		map.put("year", year);
		map.put("reprtCode", reprtCode);
		HashMap<String, Object> resultMap = webClient.mutate()
				.baseUrl(dartUrl)
				.build()
				.get()
				.uri("/api/fnlttSinglAcnt.json?crtfc_key={crtfcKey}&corp_code={corpCode}&bsns_year={year}&reprt_code={reprtCode}",map)
				.retrieve()
				.bodyToMono(HashMap.class)
				.block();
				
				//restTemplate.getForObject(targetUrl, HashMap.class, map);
		
//		System.out.println(resultMap.get("status"));
//		System.out.println(resultMap.get("message"));
		BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
		if(((String) resultMap.get("status")).equals("000")) {
			List<Map<String, Object>> bs =(List<Map<String, Object>>) resultMap.get("list");
			String itemId ="A"+bs.get(0).get("stock_code");
			
//			System.out.println(bs.get(0).get("bsns_year"));
			int	reportingYear = Integer.parseInt((String) bs.get(0).get("bsns_year"));
							
			String reportCode =(String) map.get("reprtCode");
			String fsNm = "연결재무제표";
			try {
			double revenue= formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("매출액", bs)) ;					//매출
			double operatingIncome= formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("영업이익", bs));			//영업이익
			double netIncome= formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("당기순이익", bs));				//당기순이익
			double asset=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("자산총계", bs));					//자산
			double debt=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("부채총계", bs));					//부채
			double equity= formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("자본총계", bs));					//자본
			double currentAsset= formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("유동자산", bs));			//단기자산(현금성자산)
			double totalNonCurrentAsset=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("비유동자산", bs));	//장기자산(설비, 부동산 등)
			double shortTermDebt=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("유동부채", bs));			//단기 부채
			double longTermDebt=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("비유동부채", bs));			//장기 부채
			balanceSheetDto.setItemId(itemId); 
			balanceSheetDto.setAsset(asset);
			balanceSheetDto.setCurrentAsset(currentAsset);
			balanceSheetDto.setDebt(debt);
			balanceSheetDto.setEquity(equity);
			balanceSheetDto.setFsNm(fsNm);
			balanceSheetDto.setLongTermDebt(longTermDebt);
			balanceSheetDto.setNetIncome(netIncome);
			balanceSheetDto.setOperatingIncome(operatingIncome);
			balanceSheetDto.setReportCode(reportCode);
			balanceSheetDto.setReportingYear(reportingYear);
			balanceSheetDto.setRevenue(revenue);
			balanceSheetDto.setShortTermDebt(shortTermDebt);
			balanceSheetDto.setTotalNonCurrentAsset(totalNonCurrentAsset);
			balanceSheetDto.setTotalNonCurrentAsset(totalNonCurrentAsset);
			dartMapper.insertBalanceSheet(balanceSheetDto);
			}catch(NumberFormatException e2) {
				e2.printStackTrace();
			}
			//에러 잡기
			try{
			    Thread.sleep(300);
			}catch(InterruptedException e){
			    e.printStackTrace();
			}
		}
	}
	
	@Override
	public void insAllBalaceSheet() {
		List<ItemDto> itemDtoList = itemMapper.selectItemList(null);
		List<BalanceSheetDto> balanceSheetDtoList = new ArrayList<>();
		HashMap<String, Object> inParam = new HashMap<>();
		int idx=0;
		for(ItemDto itemDto : itemDtoList) {
			if(itemDto.getCorpCode()==null) continue;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("corpCode", itemDto.getCorpCode());
				for(int i = 0; i<6; i++) {
					map.put("crtfcKey", crtfcKey);
					Date d = new Date();
					int year = d.getYear()+1900 - i;
					
					map.put("year", year);
					for(int j = 1; j <=4;j++) {
						map.put("reprtCode", ReprtCode.getReprtCode(j));
						HashMap<String, Object> resultMap = webClient.mutate()
								.baseUrl(dartUrl)
								.build()
								.get()
								.uri("/api/fnlttSinglAcnt.json?crtfc_key={crtfcKey}&corp_code={corpCode}&bsns_year={year}&reprt_code={reprtCode}",map)
								.retrieve()
								.bodyToMono(HashMap.class)
								.block();
						BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
						if(((String) resultMap.get("status")).equals("000")) {
							List<Map<String, Object>> bs =(List<Map<String, Object>>) resultMap.get("list");
							String itemId ="A"+bs.get(0).get("stock_code");
							
//							System.out.println(bs.get(0).get("bsns_year"));
							int	reportingYear = Integer.parseInt((String) bs.get(0).get("bsns_year"));
											
							String reportCode =(String) map.get("reprtCode");
							String fsNm = "연결재무제표";
							try {
							double revenue= formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("매출액", bs)) ;					//매출
							double operatingIncome= formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("영업이익", bs));			//영업이익
							double netIncome= formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("당기순이익", bs));				//당기순이익
							double asset=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("자산총계", bs));					//자산
							double debt=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("부채총계", bs));					//부채
							double equity= formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("자본총계", bs));					//자본
							double currentAsset= formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("유동자산", bs));			//단기자산(현금성자산)
							double totalNonCurrentAsset=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("비유동자산", bs));	//장기자산(설비, 부동산 등)
							double shortTermDebt=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("유동부채", bs));			//단기 부채
							double longTermDebt=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("비유동부채", bs));			//장기 부채
							balanceSheetDto.setItemId(itemId); 
							balanceSheetDto.setAsset(asset);
							balanceSheetDto.setCurrentAsset(currentAsset);
							balanceSheetDto.setDebt(debt);
							balanceSheetDto.setEquity(equity);
							balanceSheetDto.setFsNm(fsNm);
							balanceSheetDto.setLongTermDebt(longTermDebt);
							balanceSheetDto.setNetIncome(netIncome);
							balanceSheetDto.setOperatingIncome(operatingIncome);
							balanceSheetDto.setReportCode(reportCode);
							balanceSheetDto.setReportingYear(reportingYear);
							balanceSheetDto.setRevenue(revenue);
							balanceSheetDto.setShortTermDebt(shortTermDebt);
							balanceSheetDto.setTotalNonCurrentAsset(totalNonCurrentAsset);
							balanceSheetDto.setTotalNonCurrentAsset(totalNonCurrentAsset);
							balanceSheetDtoList.add(balanceSheetDto);
							}catch(NumberFormatException e2) {
								e2.printStackTrace();
							}
						//에러 잡기
						try{
						    Thread.sleep(200);
						}catch(InterruptedException e){
						    e.printStackTrace();
						}
						idx++;
						if(idx%1000==0) {
							dartMapper.insertAllBalanceSheet(balanceSheetDtoList);
							balanceSheetDtoList.clear();
						}
					}
					
				}
			}
		}
		if(!balanceSheetDtoList.isEmpty()) dartMapper.insertAllBalanceSheet(balanceSheetDtoList);
	}
	
	@Override
	public void updateCorpCode() throws IOException{
		String targetUrl = dartUrl+"/api/corpCode.xml?crtfc_key="+crtfcKey;
	    		
		byte[] arr =webClient.mutate()
	    		.baseUrl(dartUrl)
	    		.build()
	    		.get()
	    		.uri("/api/corpCode.xml?crtfc_key={crtfcKey}",crtfcKey)
	    		.retrieve()
	    		.bodyToMono(byte[].class)
	    		.block();
	    		
	    InputStream inputStream = new ByteArrayInputStream(arr);
	    ZipInputStream zip = new ZipInputStream(inputStream);
	    System.out.println(zip.getNextEntry());
	    Scanner sc = new Scanner(zip);
	    
	    String a = new String();
	    
	    String b = new String();
	    System.out.println(sc.hasNext());
	    ArrayList<HashMap<String,String>> inParam = new ArrayList<>();
	    while(sc.hasNext()){
	    	String str = sc.next();
	    	HashMap<String, String> map = new HashMap<>();
	    	if(str.equals("<list>")) {
	    		a= new String();
	    		b= new String();
	    		continue;
	    	}
	    	if(str.contains("<corp_code>")&str.contains("</corp_code>")) {
	    		a = str.replace("<corp_code>", "").replace("</corp_code>", "");
	    	}else if(str.contains("<stock_code>")&str.contains("</stock_code>")) {
	    		b = "A" + str.replace("<stock_code>", "").replace("</stock_code>", "");
	    	}else if(str.equals("</list>")&!b.isEmpty()) {
	    		map.put("id", b);
	    		map.put("corpCode", a);
	    		inParam.add(map);
	    	}
	    }
	    for(HashMap<String,String> map : inParam) {
	    	itemMapper.updateCorpCode(map);
	    }
	}
	
	
}

package com.example.demo.data.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

import com.example.demo.data.service.DartService;
import com.example.demo.util.DartUtil;
import com.example.demo.util.FormatConverter;
import com.example.demo.vo.BalanceSheetDto;

@Service
public class DartServiceImpl implements DartService{
	@Value("${dartkey}")
	String crtfcKey;
	@Value("${darturl}")
	String dartUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DartUtil dartUtil;
	
	@Autowired
	private FormatConverter formatConverter;
	
	@Override
	public BalanceSheetDto getBalaceSheet(Map inParam) throws ParseException {
		String targetUrl = dartUrl+"/api/fnlttSinglAcnt.json?crtfc_key={crtfcKey}&corp_code={corpCode}&bsns_year={year}&reprt_code={reprtCode}";
		Map<String, String> map = new HashMap<String, String>();
		map.put("crtfcKey", crtfcKey);
		System.out.print(crtfcKey);
		map.put("corpCode", (String) inParam.getOrDefault("corpCode", null));
		map.put("year", (String) inParam.getOrDefault("year", null));
		map.put("reprtCode", (String) inParam.getOrDefault("reprtCode", null));
		HashMap<String, Object> resultMap = restTemplate.getForObject(targetUrl, HashMap.class, map);
		
		System.out.println(resultMap.get("status"));
		System.out.println(resultMap.get("message"));
		BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
		if(((String) resultMap.get("status")).equals("000")) {
			List<Map<String, Object>> bs =(List<Map<String, Object>>) resultMap.get("list");
			int itemId = 0;
			Date reportingYear = new Date();
			try {
				reportingYear = new SimpleDateFormat("yyyy").parse((String) bs.get(0).get("bsns_year"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 					
			String reportCode =(String) map.get("reprtCode");
			String fsNm = "연결재무제표";
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
//			double OCF=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("영업현금흐름", bs));					//영업현금흐름
//			double ICF=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("투자현금흐름", bs));					//투자현금흐름
//			double FCF=formatConverter.separatorStringToDouble((String) dartUtil.getAccountValue("재무현금흐름", bs));					//재무현금흐름
			
			balanceSheetDto.setAsset(asset);
			balanceSheetDto.setCurrentAsset(currentAsset);
			balanceSheetDto.setDebt(debt);
			balanceSheetDto.setEquity(equity);
			balanceSheetDto.setFsNm(fsNm);
			balanceSheetDto.setLongTermDebt(longTermDebt);
			balanceSheetDto.setNetIncome(netIncome);
			balanceSheetDto.setOperatinIncome(operatingIncome);
			balanceSheetDto.setReportCode(reportCode);
			balanceSheetDto.setReportingYear(reportingYear);
			balanceSheetDto.setRevenue(revenue);
			balanceSheetDto.setShortTermDebt(shortTermDebt);
			balanceSheetDto.setTotalNonCurrentAsset(totalNonCurrentAsset);
			balanceSheetDto.setTotalNonCurrentAsset(totalNonCurrentAsset);
//			balanceSheetDto.setOCF(OCF);
//			balanceSheetDto.setICF(ICF);
//			balanceSheetDto.setItemId((int) inParam.get("itemId"));
//			balanceSheetDto.setFCF(FCF);
		}
		return balanceSheetDto;
	}
	@Override
	public int insertBalanceSheeat(BalanceSheetDto balaceSheetDto) {
		return 1;
		
	}
	
	@Override
	public HashMap<String,String> getCorpCodeMap() throws IOException{
		String targetUrl = dartUrl+"/api/corpCode.xml?crtfc_key={crtfcKey}";
	    byte[] arr = restTemplate.getForObject(targetUrl, byte[].class);
	    InputStream inputStream = new ByteArrayInputStream(arr);
	    ZipInputStream zip = new ZipInputStream(inputStream);
	    Scanner sc = new Scanner(zip);
	    HashMap<String, String> map = new HashMap<>();
	    String a = new String();
	    String b = new String();
	    while(sc.hasNext()){
	    	String str = sc.next();
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
	    		map.put(b, a);
	    	}
	    }
		return map;
	}
}

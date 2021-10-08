package com.example.demo.data.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.util.BalanceSheetUtil;
import com.example.demo.vo.BalanceSheetDto;

@Service
public class dartServiceImpl {
	@Value("${dart.crtfckey}")
	String certfcKey;
	@Value("${dart.url}")
	String dartUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	BalanceSheetUtil balanceSheetUtil;
	
	public BalanceSheetDto getBalaceSheet(Map inParam) {
		String targetUrl = dartUrl+"/fnlttSingAcnt.json?crtfc_key={crtfcKey}&corp_code={corpCode}&bsns_year={year}&reprt_code={reprtCode}";
		Map<String, String> map = new HashMap<String, String>();
		map.put("crtcKey", certfcKey);
		map.put("corpCode", (String) inParam.getOrDefault("corpCode", 0));
		map.put("year", (String) inParam.getOrDefault("year", 0));
		map.put("year", (String) inParam.getOrDefault("reprtCode", 0));
		
		
		HashMap<String, Object> resultMap = restTemplate.getForObject(targetUrl, HashMap.class, map);
		BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
		
		if(((String) resultMap.get("status")).equals("000")) {
			List<Map<String, Object>> bs =(List<Map<String, Object>>) resultMap.get("list");
			int itemId = 0;
			Date reportingYear = new Date();
			String reportCode = (String) balanceSheetUtil.getAccountValue("reprt_code", bs);
			String fsNm = (String) balanceSheetUtil.getAccountValue("fs_nm", bs);
			int revenue=(int) balanceSheetUtil.getAccountValue("매출", bs);					//매출
			int operatingIncome= (int) balanceSheetUtil.getAccountValue("영업이익", bs);			//영업이익
			int netIncome=(int) balanceSheetUtil.getAccountValue("당기순이익", bs);				//당기순이익
			int asset=(int) balanceSheetUtil.getAccountValue("자산총계", bs);					//자산
			int debt=(int) balanceSheetUtil.getAccountValue("부채총계", bs);					//부채
			int equity=(int) balanceSheetUtil.getAccountValue("자본총계", bs);					//자본
			int currentAsset=(int) balanceSheetUtil.getAccountValue("유동자산", bs);			//단기자산(현금성자산)
			int totalNonCurrentAsset=(int) balanceSheetUtil.getAccountValue("비유동자산", bs);	//장기자산(설비, 부동산 등)
			int shortTermDebt=(int) balanceSheetUtil.getAccountValue("유동부채", bs);			//단기 부채
			int longTermDebt=(int) balanceSheetUtil.getAccountValue("비유동부채", bs);			//장기 부채
			int OCF=(int) balanceSheetUtil.getAccountValue("영업이익", bs);					//영업현금흐름
			int ICF=(int) balanceSheetUtil.getAccountValue("영업이익", bs);					//투자현금흐름
			int FCF=(int) balanceSheetUtil.getAccountValue("영업이익", bs);					//재무현금흐름
			
			balanceSheetDto.setAsset(asset);
			balanceSheetDto.setCurrentAsset(currentAsset);
			balanceSheetDto.setDebt(debt);
			balanceSheetDto.setEquity(equity);
			balanceSheetDto.setFCF(FCF);
			balanceSheetDto.setFsNm(fsNm);
			balanceSheetDto.setICF(ICF);
			balanceSheetDto.setItemId((int) inParam.get("itemId"));
			balanceSheetDto.setLongTermDebt(longTermDebt);
			balanceSheetDto.setNetIncome(netIncome);
			balanceSheetDto.setOCF(OCF);
			balanceSheetDto.setOperatinIncome(operatingIncome);
			balanceSheetDto.setReportCode(reportCode);
			balanceSheetDto.setReportingYear(reportingYear);
			balanceSheetDto.setRevenue(revenue);
			balanceSheetDto.setShortTermDebt(shortTermDebt);
			balanceSheetDto.setTotalNonCurrentAsset(totalNonCurrentAsset);
			balanceSheetDto.setTotalNonCurrentAsset(totalNonCurrentAsset);
		}
		return balanceSheetDto;
	}
}

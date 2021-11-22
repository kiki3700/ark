package com.example.demo.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.demo.vo.BalanceSheetDto;
import com.example.demo.vo.ItemDto;
@Component
public class DartUtil {
	//연결 재무제표만 취득한다.
	public static String getAccountValue(String accountNm, List<Map<String,Object>> balanceSheet){
		int len = balanceSheet.size();
		String value = new String();
		for(int i = 0; i < len; i++) {
			Map<String, Object> map = balanceSheet.get(i);
			if(map.get("account_nm").equals(accountNm) & map.get("fs_nm").equals("연결재무제표")) {
				value =(String) map.get("thstrm_amount");
				break;
			}
		}
		return value;
	}
	public void toBalanceSheetDtoMap(HashMap<String,Object> dataMap, HashMap<String, BalanceSheetDto> connectBalanceMap, HashMap<String, BalanceSheetDto> balanceMAP) {
		String balanceSheetCategory = (String) dataMap.get("fs_nm");
		String id ="A"+ dataMap.get("stock_code");
		BalanceSheetDto balanceSheetDto;
		if(balanceSheetCategory.equals("연결재무제표")) balanceSheetDto = connectBalanceMap.getOrDefault(id, new BalanceSheetDto());
		else balanceSheetDto  = balanceMAP.getOrDefault(id, new BalanceSheetDto()); 
		
		if(balanceSheetDto==null) {
			balanceSheetDto = new BalanceSheetDto();
		}
		
		if(dataMap!=null) {
			String reportCode = (String) dataMap.get("reprt_code");
			int reportingYear = Integer.parseInt((String) dataMap.get("bsns_year")); 
			balanceSheetDto.setReportCode(reportCode);
			balanceSheetDto.setReportingYear(reportingYear);
			balanceSheetDto.setItemId(id);
			balanceSheetDto.setFsNm(balanceSheetCategory);
		}
		String formatAmount =(String) dataMap.get("thstrm_amount");		
		double amount = FormatConverter.separatorStringToDouble(formatAmount);
		
			switch((String) dataMap.get("account_nm")) {
			case "법인세차감전 순이익":
				balanceSheetDto.setEarningBeforeTax(amount);
				break;
			case "유동부채":
				balanceSheetDto.setCurrentAsset(amount);
				break;
			case "자산총계":
				balanceSheetDto.setAsset(amount);
				break;
			case "영업이익":
				balanceSheetDto.setOperatingIncome(amount);
				break;
			case "당기순이익":
				balanceSheetDto.setNetIncome(amount);
				break;
			case "비유동자산":
				balanceSheetDto.setTotalNonCurrentAsset(amount);
				break;
			case "비유동부채":
				balanceSheetDto.setLongTermDebt(amount);
				break;
			case "부채총계":
				balanceSheetDto.setDebt(amount);
				break;
			case "유동자산":
				balanceSheetDto.setCurrentAsset(amount);
				break;
			case "자본총계":
				balanceSheetDto.setEquity(amount);
				break;
			case "매출액":
				balanceSheetDto.setRevenue(amount);
				break;
			case "이익잉여금":
				balanceSheetDto.setRetainedEaring(amount);
				break;
			}
			if(balanceSheetCategory.equals("연결재무제표")) connectBalanceMap.put(id, balanceSheetDto);
			else balanceMAP.put(id, balanceSheetDto);
	}

}

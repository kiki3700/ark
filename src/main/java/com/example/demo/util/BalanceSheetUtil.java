package com.example.demo.util;


import java.util.List;
import java.util.Map;

public class BalanceSheetUtil {
	//연결 재무제표만 취득한다.
	public static Object getAccountValue(String accountNm, List<Map<String,Object>> balanceSheet){
		int len = balanceSheet.size();
		Object value = new Object();
		for(int i = 0; i < len; i++) {
			Map<String, Object> map = balanceSheet.get(i);
			if(map.containsKey(accountNm) & map.get("fs_nm").equals("연결재무제표")) {
				value = map.get(accountNm);
				break;
			}
		}
		return value;
	}
}

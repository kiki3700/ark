package com.example.demo.util;


import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Component;
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

}

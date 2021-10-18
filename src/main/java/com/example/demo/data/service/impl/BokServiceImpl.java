package com.example.demo.data.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.constants.BokConst;
import com.example.demo.data.service.BokService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class BokServiceImpl implements BokService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${bokkey}")
	String bok_key;
	@Value("${bokbaseurl}")
	String base_url;
	
	
	@Override
	public HashMap<Object, Object> getBokIndex(Map<String, String> inParam) throws Exception {
		
		HashMap<Object, Object> resultMap = new HashMap<Object,Object>();
		// 		PARAMETER																												MANDATORY
		String serviceName = ""; //서비스명																	   							 Y
		String reqType = "";	// 요청타입 (xml, json) 																					 Y
		String reqlang = ""; //언어 (kr , en )																								 Y
		String startNum = ""; //요청시작건수																								 Y
		String endNum = ""; //요청종료건수																								 Y
		String reqCode = "";	//통계표 코드 ex) 027Y215																				 Y
		String ymd = ""; // 주기   년:YY, 분기:QQ , 월:MM, 일:DD																 Y
		String startDate= ""; //검색시작일자 주기에맞게 ex) 2011 ,20111 , 201101 , 20110101						 Y
		String endDate = ""; // 검색종료일자 																								 Y
		String atcl_Code1 = ""; // 통계항목코드 1																						 N
		String atcl_Code2 = ""; // 통계항목코드 2																						 N
		String atcl_Code3 = ""; // 통계항목코드 3	
		
		
		serviceName = "/StatisticSearch"; //서비스명																	   							 Y
		reqType = inParam.get("REQUEST_TYPE");
		reqlang = inParam.get("REQUEST_LANG"); 
		startNum =  inParam.get("START_NUM");
		endNum =  inParam.get("END_NUM"); 
		reqCode =  inParam.get("REQUEST_CODE");	
		ymd =  inParam.get("YMD"); 
		startDate=  inParam.get("START_DATE");
		endDate =  inParam.get("END_DATE");
		atcl_Code1 =  inParam.get("ATCL_CODE2");
		
		String surl = base_url + serviceName + bok_key + reqType + reqlang + startNum + endNum + reqCode + ymd + startDate + endDate + atcl_Code1;
		HttpURLConnection con = null;
		Gson gson = new Gson();
		
		URL url = new URL(surl);

        con = (HttpURLConnection)url.openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setRequestMethod("GET");
		
        int responseCode = con.getResponseCode();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());
        resultMap = gson.fromJson(sb.toString(), resultMap.getClass() );
        
		System.out.println(resultMap.get("UNIT_NAME"));
		System.out.println(resultMap.get("DATA_VALUE"));
		return resultMap;
	}


	@Override
	public HashMap<Object, Object> getBokIndex() throws Exception {
		return null;
	}

}

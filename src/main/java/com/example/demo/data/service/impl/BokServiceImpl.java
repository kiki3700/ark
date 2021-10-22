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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.constants.BokConst;
import com.example.demo.data.service.BokService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import reactor.core.publisher.Mono;

@Service
public class BokServiceImpl implements BokService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${bokkey}")
	String bok_key;
	@Value("${bokbaseurl}")
	String base_url;
	
	@Autowired
	WebClient webClient;
	
	
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
		
		String surl =  serviceName + "/" + bok_key + reqType + reqlang + startNum + endNum + reqCode + ymd + startDate + endDate + atcl_Code1;
		System.out.println(surl);
        
        Mono<HashMap>  monoMap = webClient.mutate()
                .baseUrl(base_url)
                .build()
                .get()
                .uri(surl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(HashMap.class)
       ;
        resultMap = monoMap.block(); 
        
        
        System.out.println(resultMap.get("StatisticSearch"));
        //resultMap = gson.fromJson(sb.toString(), resultMap.getClass() );
		return resultMap;
	}


	@Override
	public HashMap<Object, Object> getBokIndex() throws Exception {
		return null;
	}

}

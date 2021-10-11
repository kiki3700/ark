package com.example.demo.data.dart;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonCode {

	@Value("${dartkey}")
	String crtfcKey;
	@Value("${darturl}")
	String dartUrl;
	
	String targetUrl;
	@Before
	public void setting() {
		targetUrl = dartUrl+"/apicorpCode.xml?crtfc_key="+crtfcKey;
	}
	
	@Test
	@DisplayName("집파일 받기")
	public void getZip() throws IOException {
		System.out.println(targetUrl);
	    // Given
	    RestTemplate restTemplate = new RestTemplate();
	    List<ZipEntry> entries = new ArrayList<>();
	    String url = "https://opendart.fss.or.kr/api/corpCode.xml?crtfc_key=044ec3f15e4539438354808f49cd3879982c4201";
	    // When
	    byte[] result = restTemplate.getForObject(url,  byte[].class);
	    ZipInputStream zi = null;
	    try {
	    	zi = new ZipInputStream(new ByteArrayInputStream(result));
	    	ZipEntry zipEntry = null;
	    	while((zipEntry = zi.getNextEntry())!=null) {
	    		entries.add(zipEntry);
	    		
	    	}
	    }finally {
	    	if(zi!= null) zi.close();
	    }
	    
	}
}

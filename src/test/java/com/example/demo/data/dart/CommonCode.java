package com.example.demo.data.dart;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonCode {

	@Value("${dartkey}")
	String crtfcKey;
	@Value("${darturl}")
	String dartUrl;
	
	@Before
	public void setting() {
		String targetUrl = dartUrl+"apicorpCode.xml?crtfc_key="+crtfcKey;
	}
	
	@Test
	@DisplayName("집파일 받기")
	public void getZip() {
	    // Given
	    RestTemplate restTemplate = new RestTemplate();
		UriComponents uriComponents = UriComponentsBuilder
	            .fromHttpUrl(dartUrl)
	            .pathSegment("corpCode.xml")
	            .queryParam("crtfc_key", crtfcKey)
	            .build();

	    // When
	    Path file = restTemplate.execute(uriComponents.toUriString(), HttpMethod.GET, null, (ResponseExtractor<T>) response -> {

	        Path zipFile = Files.createTempFile("opendart-", ".zip");
	        Files.write(zipFile, response.getBody().readAllBytes());

	        
	    }, null);

	  

	    // 테스트 후 삭제
	    Files.delete(file);
	}
}

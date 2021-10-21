package com.example.demo.data.dart;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.example.demo.data.service.impl.DartServiceImpl;
import com.example.demo.util.wrapper.UncloseableInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.zip.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonCode {

	@Value("${dartkey}")
	String crtfcKey;
	@Value("${darturl}")
	String dartUrl;
	
	String targetUrl;
	@Autowired
	DartServiceImpl ser;
	
	@Before
	public void setting() {
		targetUrl = dartUrl+"/apicorpCode.xml?crtfc_key="+crtfcKey;
	}
	
	@Test
	@DisplayName("집파일 받기")
	public void parseTest() throws IOException, ParserConfigurationException, SAXException {
		
	    
	    
//	    try (ZipInputStream zipIn = new ZipInputStream(inputStream))
//	    {
//	      DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//	      ZipEntry entry;  
//	      Document doc = db.parse(new UncloseableInputStream(zipIn));
//	    }
	   
	}

	@Test
	public void testMethod() throws IOException {
		HashMap<String, String> map = ser.getCorpCodeMap();
		
		for(String key : map.keySet()) {
			System.out.println("key : "+key+" , value : "+map.get(key));
		}
	}
}

package com.example.demo.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemEnvTest {
	@Test
	public void printTest() {
		Map<String, String> envMap = System.getenv();
		for(String key : envMap.keySet()) {
			System.out.println(key+" "+envMap.get(key));
		}
	}
	@Test
	public void idTest() {
		assertEquals(System.getenv("CybosId"),"kiki3700");
	}
}

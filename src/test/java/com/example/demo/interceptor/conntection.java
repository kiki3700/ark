package com.example.demo.interceptor;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.util.CybosConnection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class conntection {

	@Autowired
	CybosConnection con;
	
	@Test
	public void test() throws Exception {
		boolean is = con.runCybos();
		System.out.println(is);
	}
}

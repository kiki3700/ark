package com.example.demo.data.dart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.util.CybosConnection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Connection {

	@Autowired
	CybosConnection connection;
	
	@Test
	public void test() {

	}
}

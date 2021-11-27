package com.example.demo.data.setup;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.controller.SetupController;
import com.example.demo.data.mapper.IndexMapper;
import com.example.demo.data.mapper.SetupMapper;
import com.example.demo.data.service.IndexService;
import com.example.demo.data.service.impl.BithumbServiceImpl;
import com.example.demo.data.service.impl.BokServiceImpl;
import com.example.demo.data.service.impl.DartServiceImpl;
import com.example.demo.data.service.impl.ItemServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SetupMethodtest {
	
	@Autowired
	SetupMapper setupMapper;
	
	@Autowired
	IndexMapper indexMapper;
	@Autowired
	ItemServiceImpl itemService;
	
	@Autowired
	BithumbServiceImpl bithumbService;
	
	@Autowired
	IndexService indexService;
	
	@Autowired
	BokServiceImpl bokService;
	
	@Autowired
	DartServiceImpl dartService;
	@Autowired
	SetupController setController;
	@Test
	public void test() throws IOException, ParseException {
		setController.fillDB();
	}
	
}

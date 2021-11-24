package com.example.demo.data.item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.service.impl.ItemServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RenewItemTest {
	
	@Autowired
	ItemServiceImpl itemSerivce;
	
	@Test
	public void renewItemTest() {
		itemSerivce.insertItem();
	}
}

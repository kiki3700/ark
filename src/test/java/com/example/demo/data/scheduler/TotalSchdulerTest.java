package com.example.demo.data.scheduler;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TotalSchdulerTest {
	@Autowired
	ItemScheduler itemScheduler;
	
	@Autowired
	KorIndexScheduler korIndexScheduler;
	
	@Autowired
	IndexScheduler indexScheduler;
	
	@Test
	public void itemTest() throws IOException, ParseException {
		itemScheduler.itemListScheduler();
		itemScheduler.corpNumScheduler();
		itemScheduler.historyDataScheduler();
		itemScheduler.renewMarketCap();
		itemScheduler.BalanceSheetScheduler();
	}
	@Test
	public void korIndTest() {
		korIndexScheduler.dsKorIndexScheduler();
	}
	@Test
	public void indTest() {
		indexScheduler.dsIndexScheduler();
		indexScheduler.bokIndexScheduler();
		indexScheduler.bitScheduler();
	}
//	@Test
	public void historyTest() {
		itemScheduler.historyDataScheduler();
	}
}

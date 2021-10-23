package com.example.demo.trading;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.trade.eventHandler.TradeEventHandler;

import com4j.EventCookie;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventHandlerTest {
	@Test
	public void ttest() {
		dashin.cpdib.IDib  dib = dashin.cpdib.ClassFactory.createStockCur();
		EventCookie cookie = dib.advise(dashin.cpdib.events._IDibEvents.class, new TradeEventHandler(dib, "conclude"));
		dib.setInputValue(0, "A005930");
		dib.subscribe();
		cookie.close();
		System.out.println("end");
	}
}

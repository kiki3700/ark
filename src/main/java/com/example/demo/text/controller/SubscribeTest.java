package com.example.demo.text.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.trade.eventHandler.TradeEventHandler;

import com4j.EventCookie;

@RestController
public class SubscribeTest {
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public void test() {
		dashin.cpdib.IDib  dib = dashin.cpdib.ClassFactory.createStockCur();

		EventCookie cookie = dib.advise(dashin.cpdib.events._IDibEvents.class,new TradeEventHandler(dib, "curPrice"));
		dib.setInputValue(0, "A005930");
		dib.subscribe();
//		dib.setInputValue(0, "A000660");
//		dib.subscribe();
		System.out.println("end");
	}
}

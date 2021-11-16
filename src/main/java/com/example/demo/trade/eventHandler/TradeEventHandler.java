package com.example.demo.trade.eventHandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.trade.mapper.TradeMapper;
import com.example.demo.vo.TradingDto;

public class TradeEventHandler extends dashin.cpdib.events._IDibEvents{
	dashin.cpdib.IDib client;
	
	@Autowired
	TradeMapper tradeDao;
	
	String name;
	public TradeEventHandler(dashin.cpdib.IDib client, String name) {
		this.client = client;
		this.name = name;
		System.out.println("construct");
	}
	
	@Override
	public void received() {
		
		if(this.name.equals("conclude")) {
			String id =(String) client.getHeaderValue(9);
			long amount =(long) client.getHeaderValue(3);//체결 수량3
			long price =(long) client.getHeaderValue(4);			//체결 가격4
			String position = (String) client.getHeaderValue(12);
			
			TradingDto tradingDto = new TradingDto(); 
			tradingDto.setItemId(id);
			tradingDto.setAmount(amount);
			tradingDto.setPrice(price);
			tradingDto.setPosition(position);
			tradingDto.setComplete("complete");
			tradeDao.updateTradingStatus(tradingDto);
			client.unsubscribe();
			
		}else if(this.name.equals("curPrice")) {
			
			System.out.println((String) client.getHeaderValue(0)+(String) client.getHeaderValue(1));	
			System.out.println("전일비"+client.getHeaderValue(2));
			System.out.println("시간"+client.getHeaderValue(3));
			System.out.println("시가"+client.getHeaderValue(4));
			System.out.println("고가"+client.getHeaderValue(5));
			System.out.println("저가"+client.getHeaderValue(6));
			System.out.println("매도호가"+client.getHeaderValue(7));
			
			client.unsubscribe();
		
		}
		
	}
}

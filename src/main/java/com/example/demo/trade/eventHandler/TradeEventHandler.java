package com.example.demo.trade.eventHandler;

public class TradeEventHandler extends dashin.cpdib.events._IDibEvents{
	dashin.cpdib.IDib client;
	String name;
	public TradeEventHandler(dashin.cpdib.IDib client, String name) {
		this.client = client;
		this.name = name;
		System.out.println("construct");
	}
	
	@Override
	public void received() {
		System.out.print("listen");
		if(this.name.equals("conclude")) {
			String ticker =(String) client.getHeaderValue(9);
			long amount =(long) client.getHeaderValue(3);//체결 수량3
			long price =(long) client.getHeaderValue(4);			//체결 가격4
			String position = (String) client.getHeaderValue(12);			
		}else if(this.name.equals("curPrice")) {
			String ticker = (String) client.getHeaderValue(0);
			String name = (String) client.getHeaderValue(1);
			long marketPrice = (long) client.getHeaderValue(10);
			System.out.print(marketPrice);
		}
		
	}
}

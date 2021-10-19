package com.example.demo.constants;

public enum CashFlow {
	DIVIDEND(0), //배당
	FEE(1),		//수수료
	BENEFIT(2),	//이익
	LOSS(3);	//손실
	
	private final int value;
	CashFlow(int value){this.value = value;}
	public int getValue() {return value;}
}

package com.example.demo.constants;


public enum DARTREPTCODE{
	Q1(11013),
	Q2(11012),
	Q3(11014),
	Q4(11011);
	
	private final int code;
	DARTREPTCODE(int code){this.code = code;}
	public int getValue() {return code;}
}
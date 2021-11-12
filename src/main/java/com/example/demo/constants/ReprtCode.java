package com.example.demo.constants;

public class ReprtCode {
	public final static String Q1 = "11013";
	public final static String Q2 = "11012";
	public final static String Q3 = "11014";
	public final static String Q4 = "11011";
	
	public String getReprtCode(int q) {
		switch(q) {
		case 1 : return ReprtCode.Q1;
		case 2 : return  ReprtCode.Q2;
		case 3 : return  ReprtCode.Q4;
		default : return  ReprtCode.Q2;
		}
	}
}

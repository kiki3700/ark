package com.example.demo.constants;

public class ReprtCode {
	public final static int Q1 = 11013;
	public final static int Q2 = 11012;
	public final static int Q3 = 11014;
	public final static int Q4 = 11011;
	
	public int getReprtCode(int q) {
		switch(q) {
		case 1 : return ReprtCode.Q1;
		case 2 : return  ReprtCode.Q2;
		case 3 : return  ReprtCode.Q4;
		default : return  ReprtCode.Q2;
		}
	}
}

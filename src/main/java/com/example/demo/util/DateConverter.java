package com.example.demo.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	public Date StringToDate(String dateString) throws ParseException {
	SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
	Date date = format.parse(dateString);
	return date;
	}
}

package com.example.demo.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatConverter {
	static public Date stringToDate(String dateString) throws ParseException {
	SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
	Date date = format.parse(dateString);
	return date;
	}
	static public String dateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
		return format.format(date);
	}
}

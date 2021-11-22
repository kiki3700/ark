package com.example.demo.data.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.vo.BalanceSheetDto;

public interface DartService {

	void insBalaceSheet(Map<String, Object> inParam) throws ParseException;
	
	void insAllBalaceSheet();
	
	void updateCorpCode() throws IOException;

	void initMultiBalanceSheet();

	void insertMultiBalanceSheet(HashMap<String, Object> inParam);
}

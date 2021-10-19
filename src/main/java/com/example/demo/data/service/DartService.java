package com.example.demo.data.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.vo.BalanceSheetDto;

public interface DartService {

	BalanceSheetDto getBalaceSheet(Map inParam) throws ParseException;

	public int insertBalanceSheeat(BalanceSheetDto balaceSheetDto);

	HashMap<String, String> getCorpCodeMap() throws IOException;
}

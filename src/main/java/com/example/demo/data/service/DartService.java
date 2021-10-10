package com.example.demo.data.service;

import java.text.ParseException;
import java.util.Map;

import com.example.demo.vo.BalanceSheetDto;

public interface DartService {

	BalanceSheetDto getBalaceSheet(Map inParam) throws ParseException;

	int insertBalanceSheeat(BalanceSheetDto balaceSheetDto);
}

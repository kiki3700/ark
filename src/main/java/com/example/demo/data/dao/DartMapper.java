package com.example.demo.data.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.BalanceSheetDto;

@Mapper
public interface DartMapper {
	int insertBalanceSheet(BalanceSheetDto balanceSheetDto);
}

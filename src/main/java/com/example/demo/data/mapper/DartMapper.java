package com.example.demo.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.BalanceSheetDto;

@Mapper
public interface DartMapper {
	int insertBalanceSheet(BalanceSheetDto balanceSheetDto);
	int insertAllBalanceSheet(List<BalanceSheetDto> balanceSheetDtoList);
}

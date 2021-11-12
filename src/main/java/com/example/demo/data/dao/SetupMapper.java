package com.example.demo.data.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

@Mapper
public interface SetupMapper {
	int insertItem(ItemDto item);
	int insertHistoryData(HistoryDataDto historyData);
}

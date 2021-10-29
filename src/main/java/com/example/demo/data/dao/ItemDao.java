package com.example.demo.data.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

@Mapper
public interface ItemDao {
	
	void updateCopCode(HashMap<String, String>map);
	
	int updateMarketCap(ItemDto itemDto);
	
	List<ItemDto> selectItemList();
	
	int insertHistoryDataDtoList(List<HistoryDataDto> historyDataDtoList);
}

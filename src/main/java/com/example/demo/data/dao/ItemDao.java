package com.example.demo.data.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

@Mapper
public interface ItemDao {
	int insertItem(ItemDto itemDto);
	
	int updateCorpCode(HashMap map);
	
	List<ItemDto> selectItemList();
	
	int insertHistoryDataDtoList(List<HistoryDataDto> historyDataDtoList);

	
}

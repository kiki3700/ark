package com.example.demo.data.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

@Mapper
public interface ItemMapper {
	
	void mergeItem(ItemDto itemDto);
	void updateCorpCode(HashMap<String, String>map);
	int updateMarketCap(ItemDto itemDto);
	List<ItemDto> selectItemList(Map<String, Object> inParams);
	
	
	int mergeHistoryDataDto(HistoryDataDto historyDataDto);
	int insertHistoryDataDtoBatch(HistoryDataDto historyDataDto);
	int deleteHistoryData(HashMap<String, Object> inParams);
}

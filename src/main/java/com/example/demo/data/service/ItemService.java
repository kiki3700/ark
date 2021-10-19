package com.example.demo.data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.vo.ItemDto;

public interface ItemService {
	int insertKoreaItem(ItemDto item);
	ItemDto getItemDto(Map<String, Object> inParam);
	List<ItemDto> getItemList();
	int updateCorpCode(HashMap map);
	int insertKoreaItem();
}

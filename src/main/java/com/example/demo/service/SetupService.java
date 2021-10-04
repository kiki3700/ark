package com.example.demo.service;

import com.example.demo.vo.ItemDto;

public interface SetupService {
	int insertKoreaItem();
	int insertKoreaIndex(ItemDto dto);
}

package com.example.demo.data.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.ItemDto;

@Mapper
public interface ItemDao {
	int insertItem(ItemDto itemDto);
}

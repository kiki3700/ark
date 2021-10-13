package com.example.demo.data.service;

import java.util.List;
import java.util.Map;

import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.IndexHistoryDataDto;

public interface BithumbService {
	List<IndexHistoryDataDto> getCrytoCurrencyHistory(Map<String, Object>inParams);

}

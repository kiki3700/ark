package com.example.demo.data.service;

import java.util.List;
import java.util.Map;

import com.example.demo.vo.HistoryDataDto;

public interface BithumbService {
	List<HistoryDataDto> getCrytoCurrencyHistory(Map<String, Object>inParams);

}

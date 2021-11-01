package com.example.demo.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.vo.HistoryDataDto;

@Component
public class Calculator {
	public float getMovingAverage(List<HistoryDataDto> historyDataDtoList, int duration) {
		if(duration > historyDataDtoList.size()) return 0;
		float avg = 0;
		for(int i = 0 ; i< duration; i++) {
			avg += historyDataDtoList.get(i).getClose()/duration; 
		}
		return avg;
	}
}


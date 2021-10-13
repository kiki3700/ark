package com.example.demo.data.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.data.dao.SetupDao;
import com.example.demo.data.service.SetupService;
import com.example.demo.util.FormatConverter;
import com.example.demo.util.ItemUtil;
import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

import dashin.cputil.ClassFactory;

public class SetupServiceImpl implements SetupService {
	
	@Autowired
	SetupDao setupDao;
	
	@Autowired
	ItemUtil itemUtil;


	
	
	@Override
	public int insertKoreaItem() {
		// TODO Auto-generated method stub
		dashin.cputil.ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
		Object[] tickers = (Object[]) codeMgr.getStockListByMarket(dashin.cputil.CPE_MARKET_KIND.CPC_MARKET_KOSPI);
		for(int i = 0; i < tickers.length; i++) {
			String ticker = (String) tickers[i];
			//종목명
			ItemDto item = itemUtil.getKoreanStockItemDto(ticker);
			setupDao.insertItem(item);
			}
		return 0;
	}

	@Override
	public int insertKoreaIndex(ItemDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}

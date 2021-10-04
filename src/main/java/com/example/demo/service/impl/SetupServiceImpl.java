package com.example.demo.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.SetupDao;
import com.example.demo.service.SetupService;
import com.example.demo.util.FormatConverter;
import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

import dashin.cputil.ClassFactory;

public class SetupServiceImpl implements SetupService {
	
	@Autowired
	SetupDao setupDao;
	
	static ItemDto getKoreanStockItemDto(String ticker) {
		dashin.cputil.ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
		String name = codeMgr.codeToName(ticker);
		//거래 구분
		dashin.cputil.CPE_STOCK_STATUS_KIND isActivate = codeMgr.getStockStatusKind(ticker);
		//자본금 규모
		dashin.cputil.CPE_CAPITAL_SIZE size = codeMgr.getStockCapital(ticker);
		dashin.cputil.CPE_MARKET_KIND market = codeMgr.getStockMarketKind(ticker);
		String industry = codeMgr.getStockIndustryCode(ticker);
		dashin.cputil.CPE_KSE_SECTION_KIND category = codeMgr.getStockSectionKind(ticker);
		ItemDto item = new ItemDto();
		item.setTicker(ticker);
		item.setName(name);
		item.setActive(isActivate.name());
		item.setMarket(market.name());
		item.setCurrentId(0);
		item.setCategory(category.name());
		item.setIndustry(industry);
		return item;
	}
	static List<HistoryDataDto> getKoreanStockItemDto(ItemDto item) throws ParseException {
		List<HistoryDataDto> dataList = new LinkedList<HistoryDataDto>();
		dashin.cpsysdib.ISysDib chart =dashin.cpsysdib.ClassFactory.createStockChart();
		chart.setInputValue(0, item.getTicker());
		chart.setInputValue(1, (int) '1');
		//chart.setInputValue(3,"20000101");
		chart.setInputValue(5, new int[] {0,2,3,4, 5, 8});
		chart.setInputValue(6,(int) 'D');
		chart.setInputValue(9,(int) '1');
		do {
		chart.blockRequest();
		Object data = chart.getHeaderValue(3);
		System.out.println(chart.getHeaderValue(5));
		for(int i =0 ; i < Integer.parseInt(data.toString()); i++) {
			String time = Long.toString((long) chart.getDataValue(0, i));
			Date tradingDate = FormatConverter.stringToDate(time);
			Number open = (Number) chart.getDataValue(1, i);
			Number high = (Number) chart.getDataValue(2, i);
			Number low = (Number) chart.getDataValue(3, i);
			Number close = (Number) chart.getDataValue(4, i);
			Number volume = (Number) chart.getDataValue(5, i);
			HistoryDataDto vo = new HistoryDataDto();
			vo.setTradingDate(tradingDate);
			vo.setOpen(open);
			vo.setHigh(high);
			vo.setLow(low);
			vo.setClose(close);
			vo.setVolume(volume);
			dataList.add(vo);
			}
		}while(1==((int) chart._continue()));
		return dataList;
	}
	
	
	@Override
	public int insertKoreaItem() {
		// TODO Auto-generated method stub
		dashin.cputil.ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
		Object[] tickers = (Object[]) codeMgr.getStockListByMarket(dashin.cputil.CPE_MARKET_KIND.CPC_MARKET_KOSPI);
		for(int i = 0; i < 10; i++) {
			String ticker = (String) tickers[i];
			//종목명
			ItemDto item = getKoreanStockItemDto(ticker);
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

package com.example.demo.data.cybosApi;

import java.util.Date;

import com.example.demo.util.FormatConverter;
import com.example.demo.vo.BalanceSheetDto;
import com.example.demo.vo.ItemDto;

import dashin.cputil.ClassFactory;

public class SetupApi {
	
	
	public ItemDto getKoreanStockItemDto(String ticker) {
		dashin.cputil.ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
		String name = codeMgr.codeToName(ticker);
		//거래 구분
		dashin.cputil.CPE_STOCK_STATUS_KIND isActive = codeMgr.getStockStatusKind(ticker);
		//자본금 규모
		dashin.cputil.CPE_CAPITAL_SIZE size = codeMgr.getStockCapital(ticker);
		dashin.cputil.CPE_MARKET_KIND market = codeMgr.getStockMarketKind(ticker);
		String industry = codeMgr.getStockIndustryCode(ticker);
		dashin.cputil.CPE_KSE_SECTION_KIND category = codeMgr.getStockSectionKind(ticker);
		long date = codeMgr.getStockListedDate(ticker);
		Date listingDate = FormatConverter.longToDate(date);
		ItemDto item = new ItemDto();
		item.setTicker(ticker);
		item.setName(name);
		item.setIsActive(isActive.name());
		item.setMarket(market.name());
		item.setCurrencyId(0);
		item.setCategory(category.name());
		item.setIndustry(industry);
		return item;
	}
}

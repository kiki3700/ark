package com.example.demo.data.service.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.ItemDao;
import com.example.demo.data.service.ItemService;
import com.example.demo.util.FormatConverter;
import com.example.demo.util.ItemUtil;
import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

import dashin.cpsysdib.ISysDib;
import dashin.cputil.CPE_CAPITAL_SIZE;
import dashin.cputil.CPE_KSE_SECTION_KIND;
import dashin.cputil.CPE_MARKET_KIND;
import dashin.cputil.CPE_STOCK_STATUS_KIND;
import dashin.cputil.ClassFactory;
import dashin.cputil.ICpCodeMgr;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	ItemDao itemDao;

	
	@Autowired
	ItemUtil itemUtil;

	@Override
	public int insertKoreaItem(ItemDto itemDto) {
		// TODO Auto-generated method stub
		return itemDao.insertItem(itemDto);
	}

	@Override
	public ItemDto getItemDto(Map<String, Object> inParam) {
		// TODO Auto-generated method stub
		String ticker = (String) inParam.get("ticker");
		
		ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();

		ISysDib marketEye = dashin.cpsysdib.ClassFactory.createMarketEye();
		int[] reqArr = new int[] {1,20};
		marketEye.setInputValue(0, reqArr);
		marketEye.setInputValue(1, ticker);
		marketEye.blockRequest();
		
		String name = codeMgr.codeToName(ticker);
		//거래 구분
		CPE_STOCK_STATUS_KIND isActive = codeMgr.getStockStatusKind(ticker);
		//자본금 규모
		CPE_CAPITAL_SIZE corpSize = codeMgr.getStockCapital(ticker);
		CPE_MARKET_KIND market = codeMgr.getStockMarketKind(ticker);
		String industry = codeMgr.getStockIndustryCode(ticker);
		CPE_KSE_SECTION_KIND category = codeMgr.getStockSectionKind(ticker);
		long date = codeMgr.getStockListedDate(ticker);
		Date listingDate = FormatConverter.longToDate(date);
		
		int close = (int) marketEye.getDataValue(0, 0);
		BigInteger listedShare = (BigInteger) marketEye.getDataValue(1, 0);
		if(codeMgr.isBigListingStock(ticker)==1) {
			listedShare = listedShare.multiply(new BigInteger("1000"));
		}
		
		ItemDto item = new ItemDto();
		
		
		
		
		item.setId(ticker);
		item.setName(name);
		item.setIsActive(isActive.name());
		item.setCurrencyId(0);
		item.setMarket(market.name());
		item.setCategory(category.name());
		item.setIndustry(industry);
		item.setCorpSize(corpSize.name());
		item.setListingDate(listingDate);
		item.setListedShare(listedShare);
		item.setMarketCap(listedShare.multiply(new BigInteger(Integer.toString(close))));
		return item;
	}
	
	@Override
	public int insertKoreaItem() {
		// TODO Auto-generated method stub
		ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
		Object[] tickers = (Object[]) codeMgr.getStockListByMarket(CPE_MARKET_KIND.CPC_MARKET_KOSPI);
		for(int j = 0; j<2; j++) {
			
			for(int i = 0; i < tickers.length; i++) {
				String ticker = (String) tickers[i];
				//종목명
				ItemDto item = itemUtil.getKoreanStockItemDto(ticker);
				itemDao.insertItem(item);
				}
			tickers = (Object[]) codeMgr.getStockListByMarket(CPE_MARKET_KIND.CPC_MARKET_KOSDAQ);
		}
		return 0;		
	}
	
	@Override
	public int insertHistoryData(ItemDto itemDto) throws ParseException{
		List<HistoryDataDto> dataList = new LinkedList<HistoryDataDto>();
		ISysDib sysDib = dashin.cpsysdib.ClassFactory.createStockChart();
		sysDib.setInputValue(0, itemDto.getId());
		sysDib.setInputValue(1, (int) '1');
		sysDib.setInputValue(3, FormatConverter.dateToLong(itemDto.getListingDate()));
		sysDib.setInputValue(5, new int[] {0,1,2,3,4,5,8});
		sysDib.setInputValue(6, (int) 'D');
		sysDib.setInputValue(9,(int) '1');		
		do {
			sysDib.blockRequest();
			Object data = sysDib.getHeaderValue(3);
			System.out.println(sysDib.getHeaderValue(5));
			for(int i =0 ; i < Integer.parseInt(data.toString()); i++) {
				String time = Long.toString((long) sysDib.getDataValue(0, i));
				Date tradingDate = FormatConverter.stringToDate(time);
				Number open = (Number) sysDib.getDataValue(1, i);
				Number high = (Number) sysDib.getDataValue(2, i);
				Number low = (Number) sysDib.getDataValue(3, i);
				Number close = (Number) sysDib.getDataValue(4, i);
				Number volume = (Number) sysDib.getDataValue(5, i);
				HistoryDataDto historyDataDto = new HistoryDataDto();
				historyDataDto.setTradingDate(tradingDate);
				historyDataDto.setOpen(open);
				historyDataDto.setHigh(high);
				historyDataDto.setLow(low);
				historyDataDto.setClose(close);
				historyDataDto.setVolume(volume);
				dataList.add(historyDataDto);
				}
			}while(1==((int) sysDib._continue()));
			return itemDao.insertHistoryDataDtoList(dataList);
	}
	

	@Override
	public List<ItemDto> getItemList(){
		return itemDao.selectItemList();
	}
	
	@Override
	public int updateCorpCode(HashMap map) {
		return itemDao.updateCorpCode(map);
	}
}

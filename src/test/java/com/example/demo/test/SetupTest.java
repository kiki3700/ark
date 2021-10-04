package com.example.demo.test;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.service.impl.SetupServiceImpl;
import com.example.demo.util.FormatConverter;
import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.PriceVo;

import dashin.cputil.ClassFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SetupTest {
	
	@Test
	public void check(){
	dashin.cputil.ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
	//코스피에 있는 모든 종목 
	Object[] tickers = (Object[]) codeMgr.getStockListByMarket(dashin.cputil.CPE_MARKET_KIND.CPC_MARKET_KOSPI);
	for(int i = 0; i < 10; i++) {
		String ticker = (String) tickers[i];
		//종목명
		String name = codeMgr.codeToName(ticker);
		//거래 구분
		dashin.cputil.CPE_STOCK_STATUS_KIND isActivate = codeMgr.getStockStatusKind(ticker);
		//자본금 규모
		dashin.cputil.CPE_CAPITAL_SIZE size = codeMgr.getStockCapital(ticker);
		dashin.cputil.CPE_MARKET_KIND market = codeMgr.getStockMarketKind(ticker);
		String industry = codeMgr.getStockIndustryCode(ticker);
		dashin.cputil.CPE_KSE_SECTION_KIND category = codeMgr.getStockSectionKind(ticker);
		System.out.println("ticker : "+ticker+", name : "+name+", is_active :"+isActivate.name()+", size : "+size+", market : "+ market+ ",ind : "+industry+", category : "+category);
		}
	
	}
//	@Test
//	public void foreinInd() {
//		dashin.cputil.ICpUsCode usCode = ClassFactory.createCpUsCode();
//		Object[] tickers = (Object[]) usCode.getUsCodeList(dashin.cputil.USTYPE.USTYPE_COUNTRY);
//		for(int i = 0; i < tickers.length; i++) {
//			System.out.println(tickers[i]);
//			System.out.println(usCode.getNameByUsCode((String) tickers[i]));
//		}
//		dashin.cpdib.IDib chart  = dashin.cpdib.ClassFactory.createCpFore8312();
//		chart.setInputValue(0, "SPX");
//		chart.setInputValue(1,(int) '1');
//		chart.setInputValue(3, 30);
//		chart.request();
//		Object header = chart.getHeaderValue(2);
//		System.out.println(Integer.parseInt(header.toString()));
//		for(int i =0; i<Integer.parseInt(header.toString());i++) {
//			String time = Long.toString((long) chart.getDataValue(0, i));
//			float open = (float) chart.getDataValue(1, i);
//			float high = (float) chart.getDataValue(2, i);
//			float low = (float) chart.getDataValue(3, i);
//			float close = (float) chart.getDataValue(4, i);
//			System.out.println("date : "+time+", open : "+open);
//		}
// 	}
//	@Test
//	public void indexPrice() {
//		dashin.cputil.ICpCybos cybos = dashin.cputil.ClassFactory.createCpCybos();
//		System.out.println(cybos.isConnect());
//		dashin.cpsysdib.ISysDib chart =dashin.cpsysdib.ClassFactory.createStockChart();
//		chart.setInputValue(0, "U001");
//		chart.setInputValue(1, (int) '1');
////		chart.setInputValue(3,"20000101");
//		chart.setInputValue(5, new int[] {0,2,3,4, 5, 8});
//		chart.setInputValue(6,(int) 'D');
//		chart.setInputValue(9,(int) '1');
//		do {
//		chart.blockRequest();
//		Object data = chart.getHeaderValue(3);
//		System.out.println(chart.getHeaderValue(5));
//		for(int i =0 ; i < Integer.parseInt(data.toString()); i++) {
//			String time = Long.toString((long) chart.getDataValue(0, i));
//			float open = (float) chart.getDataValue(1, i);
//			float high = (float) chart.getDataValue(2, i);
//			float low = (float) chart.getDataValue(3, i);
//			System.out.print(chart.getDataValue(4, i).getClass());
//			Number close = (Number) chart.getDataValue(4, i);
//			System.out.print(chart.getDataValue(5, i).getClass());
//			Number volume = (Number) chart.getDataValue(5, i);
//			HistoryDataDto vo = new HistoryDataDto();
//			vo.setOpen(open);
//			vo.setHigh(high);
//			System.out.println((float) close);
//			}
//		}while(1==((int) chart._continue()));	
//	}
	@Test
	public void priceGetter() throws ParseException {
		List<HistoryDataDto> dataList = new LinkedList<HistoryDataDto>();
		dashin.cpsysdib.ISysDib chart =dashin.cpsysdib.ClassFactory.createStockChart();
		chart.setInputValue(0, "A000060");
		chart.setInputValue(1, (int) '1');
		chart.setInputValue(3,"20000101");
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
            System.out.println(vo.toString());
			}
		}while(1==((int) chart._continue()));
	}
}
package com.example.demo.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dashin.cputil.ClassFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SetupTest {
	
	@Test
	public void check(){
	dashin.cputil.ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
	//코스피에 있는 모든 종목 
	Object[] tickers = (Object[]) codeMgr.getStockListByMarket(dashin.cputil.CPE_MARKET_KIND.CPC_MARKET_KOSPI);
	for(int i = 0; i < tickers.length; i++) {
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
		System.out.println("ticker : "+ticker+", name : "+name+", size : "+size+", market : "+ market+ ",ind : "+industry+", setcor : "+category);
		}
	
	}
}
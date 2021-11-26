package com.example.demo.data.dart;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.data.service.impl.ItemServiceImpl;
import com.example.demo.vo.ItemDto;

import dashin.cputil.CPE_MARKET_KIND;
import dashin.cputil.ClassFactory;
import dashin.cputil.ICpCodeMgr;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {
	@Autowired
	ItemServiceImpl itemService;
	
	Map<String, Object> inParam;
	
	@Before
	public void Initialize() {
		ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
	}
//	@Test
//	public void makeDTo() {
//		ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
//		
//		Object[] kospiTickers= (Object[]) codeMgr.getStockListByMarket(CPE_MARKET_KIND.CPC_MARKET_KOSPI);
//		Object[] kosdaqTickers = (Object[]) codeMgr.getStockListByMarket(CPE_MARKET_KIND.CPC_MARKET_KOSDAQ);
//		
//		for(int i = 0; i < kospiTickers.length; i++) {
//			//dto 생성
//			HashMap<String, Object> inParam = new HashMap<>();
//			inParam.put("ticker",(String) kospiTickers[i]);
//			ItemDto itemDto = itemService.getItemDto(inParam);
//			if(itemDto.getName().contains("삼성")) {
//				System.out.println(itemDto);
//			}
//		}
//		for(int i = 0; i < kosdaqTickers.length; i++) {
//			//dto 생성
//			HashMap<String, Object> inParam = new HashMap<>();
//			inParam.put("ticker",(String) kosdaqTickers[i]);
//			ItemDto itemDto = itemService.getItemDto(inParam);
////			System.out.println(itemDto);
//		}
//	}
	@Test
	public void insTest() {
		itemService.mergeItem();
	}
	@Test
	public void updateCapTest() {
		itemService.updateMarketCap();
	}
//	@Test
//	public void updateCorpNum() {
//		itemService.updateCorpCode();
//	}
	
}

package com.example.demo.data.scheduler;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.constants.ReprtCode;
import com.example.demo.data.service.DartService;
import com.example.demo.data.service.ItemService;

/*작성장 : 이성현 
 *아이템과 관련된 스케쥴러
 *아이템 리스트 갱신
 *corpNum 갱신
 *시가총액
 *회계정보갱신
 *historydata 갱신
 *
 *
*/
@Component
public class ItemScheduler {
	@Autowired
	ItemService itemService;
	
	@Autowired
	DartService dartService;
	
	//종목들 인서트
	@Scheduled(cron = "0 0 08 * 1-5 *")
	public void itemListScheduler(){
		itemService.mergeItem();
	}
	
	//기업 번호 업데이트 => 다트 (밸런스 시트 쿼리용) 
	@Scheduled(cron = "0 5 08 * 6 *")
	public void corpNumScheduler() throws IOException {
		dartService.updateCorpCode();
	}
	
	//종목들의 가격을 한꺼번에 merge
	@Scheduled(cron = "0 35 15 * 1-5 *")
	public void historyDataScheduler() {
		HashMap<String, Object> inParam = new HashMap<>();
		inParam.put("quant", 3);
		try {
			itemService.insertHistoryData(inParam);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	
	//시가총액 업데이트
	@Scheduled(cron="0 30 08 * 6 *")
	public void renewMarketCap() {
		itemService.updateMarketCap();
	}
	
	//밸런스 시트 인서트
	@Scheduled(cron = "0 0 17 *  7 *")
	public void BalanceSheetScheduler() throws ParseException {
		HashMap<String, Object> inParam = new HashMap<>();
		inParam = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		inParam.put("year", cal.get(Calendar.YEAR));
		ReprtCode reportCode = new ReprtCode();
		int quart= cal.get(Calendar.MONDAY)/3+1;
		inParam.put("reprtCode", reportCode.getReprtCode(quart));
		dartService.insertMultiBalanceSheet(inParam);
	}
}

package com.example.demo.data.scheduler;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.constants.ReprtCode;
import com.example.demo.data.service.DartService;
import com.example.demo.data.service.ItemService;
import com.example.demo.vo.ItemDto;

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
public class ItemScheduler {
	@Autowired
	ItemService itemService;
	
	@Autowired
	DartService dartService;
	
	@Scheduled(cron = "0 0 08 * * *")
	public void itemListScheduler(){
		itemService.insertItem();
	}
	
	@Scheduled(cron = "0 5 08 * * *")
	public void corpNumScheduler() throws IOException {
		dartService.updateCorpCode();
	}
	
	@Scheduled(cron = "0 35 15 * * *")
	public void historyDataScheduler() {
		HashMap<String, Object> inParam = new HashMap<>();
		inParam.put("isActive", "CPC_STOCK_STATUS_NORMAL");
		List<ItemDto> itemDtoList = itemService.getItemList(inParam);
		for(int i = 0 ; i < itemDtoList.size(); i++) {
			inParam = new HashMap<>();
			inParam.put("quant", 3);
			try {
				itemService.insertHistoryData(itemDtoList.get(i), inParam);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Scheduled(cron="0 30 08 * * *")
	public void renewMarketCap() {
		itemService.updateMarketCap();
	}
	
	@Scheduled(cron = "0 0 17 15 * *")
	public void BalanceSheetScheduler() throws ParseException {
		HashMap<String, Object> inParam = new HashMap<>();
		inParam.put("isActive", "CPC_STOCK_STATUS_NORMAL");
		inParam.put("isCoprCode", "true");
		List<ItemDto> itemDtoList = itemService.getItemList(inParam);
		inParam = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		inParam.put("year", cal.get(Calendar.YEAR));
		ReprtCode reportCode = new ReprtCode();
		int quart= cal.get(Calendar.MONDAY)/3+1;
		inParam.put("reprtCode", reportCode.getReprtCode(quart));
		for(int i = 0; i<itemDtoList.size();i++) {
			inParam.put("corpCode", itemDtoList.get(i).getCorpCode());
			dartService.insBalaceSheet(inParam);
		}
	}
	
}

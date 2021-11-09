package com.example.demo.data.itemScheduler;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.ReprtCode;
import com.example.demo.data.service.impl.DartServiceImpl;
import com.example.demo.data.service.impl.ItemServiceImpl;
import com.example.demo.vo.ItemDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemScheduler {
	@Autowired
	ItemServiceImpl itemService;
	
	@Autowired
	DartServiceImpl dartService;
	
	@Test
	public void ins() {
		itemService.insertItem();
	}
	
//	@Test
//	public void mar() {
//		itemService.updateMarketCap();
//	}
//	
//	@Test
//	public void corpNum() throws IOException {
//		dartService.updaeCopCode();
//	}
	
	@Test
	public void historyData() {
		HashMap<String, Object> inParam = new HashMap<>();
		inParam.put("isActive", "CPC_STOCK_STATUS_NORMAL");
		List<ItemDto> itemDtoList = itemService.getItemList(inParam);
		for(int i = 0 ; i < itemDtoList.size(); i++) {
			inParam = new HashMap<>();
			inParam.put("quant", 700);
			try {
				itemService.insertHistoryData(itemDtoList.get(i), inParam);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
//	
//	@Test
//	public void balance() throws ParseException {
//		HashMap<String, Object> inParam = new HashMap<>();
//		inParam.put("isActive", "CPC_STOCK_STATUS_NORMAL");
//		List<ItemDto> itemDtoList = itemService.getItemList(inParam);
//		inParam = new HashMap<>();
//		Calendar cal = Calendar.getInstance();
//		inParam.put("year",cal.get(Calendar.YEAR));
//		ReprtCode reportCode = new ReprtCode();
//		int quart = cal.get(Calendar.MONDAY)/3+1;
//		inParam.put("reprtCode", reportCode.getReprtCode(quart));
//		for(int i = 0; i<itemDtoList.size();i++) {
//			inParam.put("corpCode", itemDtoList.get(i).getCorpCode());
//			dartService.insBalaceSheet(inParam);
//		}
//	}
	
}

package com.example.demo.data.dart;

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
public class BalanceSheetTest {
	//782.231s
	@Autowired
	ItemServiceImpl itemService;
	@Autowired
	DartServiceImpl dartService;
	@Test
	public void et() throws ParseException {
		System.out.println();
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

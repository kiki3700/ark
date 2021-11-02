package com.example.demo.data.scheduler;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.data.dao.ItemMapper;
import com.example.demo.data.service.DartService;
import com.example.demo.vo.ItemDto;

public class KorIndexScheduler {
	
	@Autowired
	private DartService dartService;
	
	@Autowired
	private ItemMapper itemDao;
	
	@Scheduled(cron ="0 0 0 * * 7 *")
	public void balanceSheet() throws ParseException {
		HashMap<String, Object> inParams = new HashMap<>();
		List<ItemDto> itemDtoList = itemDao.selectItemList(inParams);
		for(int i = 0 ; i < itemDtoList.size(); i++) {
			HashMap<String, Object> inParam = new HashMap<>();
			ItemDto item = itemDtoList.get(i);
			inParam.put("corpCode", item.getCorpCode());
			int year = Calendar.getInstance().get(Calendar.YEAR);
			inParam.put("year",Integer.toString(year));
			int quarter = (int) Math.ceil( (Calendar.getInstance().get(Calendar.MONTH)+1) / 3.0 );
			String[] reprtCode = new String[] {"11013", "11012", "11014", "11011"};
			inParam.put("reprtCode",reprtCode[quarter-1]);
			dartService.insBalaceSheet(inParam);
		}
		
	}
}

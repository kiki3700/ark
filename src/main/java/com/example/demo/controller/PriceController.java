package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PriceService;
import com.example.demo.service.impl.PriceServiceImpl;
import com.example.demo.vo.PriceVo;

import dashin.cpsysdib.ClassFactory;

@CrossOrigin
@RestController
public class PriceController {

	@Autowired
	PriceService priceService;
	
	@RequestMapping("/all")
	public List<PriceVo> getall() {
		return priceService.getPrice(null);
	}
	
	@RequestMapping(value = "/make", method =RequestMethod.POST)
	public List<PriceVo> makeChar(Model model, PriceVo vo) {
		HashMap<String, String> param = new HashMap<>();
		param.put("name", vo.getName());
		return priceService.getPrice(param);
	}
	
	@RequestMapping("/set")
	public void setKospi() {
		dashin.cputil.ICpCybos cybos = dashin.cputil.ClassFactory.createCpCybos();
		System.out.println(cybos.isConnect());
		dashin.cpsysdib.ISysDib chart =ClassFactory.createStockChart();
		chart.setInputValue(0, "A219480");
		chart.setInputValue(1, (int) '1');
		chart.setInputValue(3,"20000101");
		chart.setInputValue(5, new int[] {0,2,3,4, 5});
		chart.setInputValue(6,(int) 'D');
		chart.setInputValue(9,(int) '1');
		do {
		chart.blockRequest();
		Object data = chart.getHeaderValue(3);
		System.out.println(chart.getHeaderValue(5));
		for(int i =0 ; i < Integer.parseInt(data.toString()); i++) {
			String time = Long.toString((long) chart.getDataValue(0, i));
			float open = (int) chart.getDataValue(1, i);
			float high = (int) chart.getDataValue(2, i);
			float low = (int) chart.getDataValue(3, i);
			float close = (int) chart.getDataValue(4, i);
			PriceVo vo = new PriceVo("s&p500",
					open, close, high, low, time);
			System.out.println(vo);
			priceService.insertPrice(vo);
			}
		}while(1==((int) chart._continue()));
	}
}

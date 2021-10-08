package com.example.demo.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.service.PriceService;
import com.example.demo.data.service.impl.PriceServiceImpl;
import com.example.demo.vo.PriceVo;

import dashin.cpsysdib.ClassFactory;
@RestController
public class Setup {
	static PriceService priceService;
	
	public static void main(String[] args) {
		priceService = new PriceServiceImpl();
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
			System.out.println(chart.getDataValue(1, i).getClass());
			float open = (int) chart.getDataValue(1, i);
			float high = (int) chart.getDataValue(2, i);
			float low = (int) chart.getDataValue(3, i);
			float close = (int) chart.getDataValue(4, i);
			PriceVo vo = new PriceVo("s&p500",
					open, close, high, low, time);
			System.out.println(vo);
			
			}
		}while(1==((int) chart._continue()));
	}
}

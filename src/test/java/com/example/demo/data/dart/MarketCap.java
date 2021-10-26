package com.example.demo.data.dart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketCap {
	@Test
	public void marketCapTest() {
		dashin.cpsysdib.ISysDib marketEye = dashin.cpsysdib.ClassFactory.createMarketEye();
		dashin.cputil.ICpCodeMgr manager = dashin.cputil.ClassFactory.createCpCodeMgr();
		int[] arr = new int[3];
		arr[0] =0;
		arr[1] =4;
		arr[2] =20;
		marketEye.setInputValue(0,arr);
		marketEye.setInputValue(1, "A005930");
		marketEye.blockRequest();
		System.out.println(marketEye.getDataValue(0,0));
		System.out.println(marketEye.getDataValue(1,0).getClass());
		System.out.println(marketEye.getDataValue(2,0).getClass());
		System.out.println(manager.isBigListingStock("A005930")==1);
		
		
	}
}

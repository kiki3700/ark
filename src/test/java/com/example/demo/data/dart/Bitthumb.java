package com.example.demo.data.dart;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.data.service.impl.BithumbServiceImpl;
import com.example.demo.vo.IndexHistoryDataDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Bitthumb {

	@Autowired
	WebClient webClient;
	
	@Autowired
	BithumbServiceImpl ser;
	
	HashMap<String, Object> map = new HashMap<>();
	
	@Before
	public void init() {
		
		map.put("name", "BTC");
	}
	
	
	@Test
	public void test() {
//		HashMap<String, Object> first= webClient.mutate()
//		.baseUrl("https://api.bithumb.com/public/candlestick/BTC_KRW/24h")
//		.build().get().retrieve().bodyToFlux(HashMap.class).blockFirst();
//		System.out.println(first);
//		HashMap<String, Object> last = webClient.mutate()
//				.baseUrl("https://api.bithumb.com/public/candlestick/BTC_KRW/24h")
//				.build().get().retrieve().bodyToFlux(HashMap.class).blockLast();
//		System.out.println(last);
		List<IndexHistoryDataDto> list = ser.getCrytoCurrencyHistory(map);
		for(IndexHistoryDataDto bit : list) {
			System.out.println(bit);
		}
	}
}

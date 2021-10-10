package com.example.demo.data.dart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.dao.PriceDao;
import com.example.demo.data.service.impl.PriceServiceImpl;
import com.example.demo.vo.IndexHistoryDataDto;

import dashin.cputil.ClassFactory;
import dashin.cputil.ICpUsCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexTest {

	@Autowired
	PriceServiceImpl priceService;
	
	@Autowired
	PriceDao priceDao;
	
	Map<Object, Object> inParam;
	
	@Before
	public void Initialize() {
		
		
	}
	
	@Test
	public void getIndex() throws Exception {
		//UsCode 나라별지수 가져오기 추후 테이블 대체
		ICpUsCode usCode =  ClassFactory.createCpUsCode();
		String[] tickers =  (String[]) usCode.getUsCodeList(dashin.cputil.USTYPE.USTYPE_COUNTRY);
		List<String> usCodeList = new ArrayList<String>();
		for(int i = 0; i < tickers.length; i++) {
			if(tickers[i].equals("SPX") || tickers[i].equals("SHANG")) {
				usCodeList.add(tickers[i]);
			} 
		}
		
		for(int i=0;i < usCodeList.size();i++ ) {
			inParam = new HashMap<>();
			inParam.put("indexCode",usCodeList.get(i));
			inParam.put("ydm",'D');
			inParam.put("quant","5");
			IndexHistoryDataDto dto = priceService.getIndexHistory(inParam);
			System.out.print(usCodeList.get(i) + "===============");
			System.out.print(dto);
			System.out.print("================");
		}
	}
	
	/*// 원하는 지수과거 일자별 데이터 가져오기 (1년간) 테이블 완성 후 적용
	public void getIndex(String indexCode) throws Exception {
		
		//controller에 getIndex 호출하는 부분에서 indexCode 공통코드에서 꺼내오기
		 
			Map<String,Object> codeMap = new HashMap<String,Object>();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("id", CommonCodeConst.ICPUSCODE_COUNTRY);
			paramMap.put("code_name", "S&P500");
			codeMap = priceDao.selectUsCodeCont(paramMap);
			String indexCodet = (String) codeMap.get("code_value");
			this.getIndex(indexCodet); 
		
		
		inParam = new HashMap<>();
		inParam.put("indexCode",indexCode);
		inParam.put("ydm","D");
		inParam.put("quant","365");
		IndexHistoryDataDto dto = priceService.getIndexHistory(inParam);
		
		priceDao.insertIndex(dto); // 추후 controller로 옮기면 getIndexHistroy 안으로 이동
			
	}*/
}

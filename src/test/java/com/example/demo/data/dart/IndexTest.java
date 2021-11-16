package com.example.demo.data.dart;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.CommonCodeConst;
import com.example.demo.data.mapper.IndexMapper;
import com.example.demo.data.service.IndexService;

import dashin.cputil.ClassFactory;
import dashin.cputil.ICpUsCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexTest {

	@Autowired	
	@Qualifier("indexServiceImpl")
	IndexService priceService;
	
	@Autowired
	IndexMapper indexMapper;
	
	Map<Object, Object> inParam;
	
	@Before
	public void Initialize() {
		
		
	}
	
	@Test
	public void getIndex() throws Exception {
		//UsCode 나라별지수 인서트
		ICpUsCode usCode =  ClassFactory.createCpUsCode();
		Map<String, Object> paramMap =new HashMap<String,Object>();
		paramMap.put("id", CommonCodeConst.ICPUSCODE_COUNTRY);
		paramMap.put("api", "DAISHIN");
		try {			
			String[] tickers =  (String[]) usCode.getUsCodeList(dashin.cputil.USTYPE.USTYPE_COUNTRY);
			for(int i = 0; i < tickers.length; i++) {
				paramMap.put("code_value", tickers[i]);
				paramMap.put("code_name", usCode.getNameByUsCode((String) tickers[i]));
				int order_num = i+1;
				paramMap.put("code_order_num", order_num);
				indexMapper.insertUsCoded(paramMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//UsCode 업종별지수 인서트
		paramMap =new HashMap<String,Object>();
		paramMap.put("id", CommonCodeConst.ICPUSCODE_UPJONG);
		paramMap.put("api", "DAISHIN");
		try {			
			String[] tickers =  (String[]) usCode.getUsCodeList(dashin.cputil.USTYPE.USTYPE_UPJONG);
			for(int i = 0; i < tickers.length; i++) {
				paramMap.put("code_value", tickers[i]);
				paramMap.put("code_name", usCode.getNameByUsCode((String) tickers[i]));
				int order_num = i+1;
				paramMap.put("code_order_num", order_num);
				indexMapper.insertUsCoded(paramMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//UsCode 업종별지수 인서트
				paramMap =new HashMap<String,Object>();
				paramMap.put("id", CommonCodeConst.ICPUSCODE_RAW);
				paramMap.put("api", "DAISHIN");
				try {			
					String[] tickers =  (String[]) usCode.getUsCodeList(dashin.cputil.USTYPE.USTYPE_RAW);
					for(int i = 0; i < tickers.length; i++) {
						paramMap.put("code_value", tickers[i]);
						paramMap.put("code_name", usCode.getNameByUsCode((String) tickers[i]));
						int order_num = i+1;
						paramMap.put("code_order_num", order_num);
						indexMapper.insertUsCoded(paramMap);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		

		
		/*for(int i=0;i < usCodeList.size();i++ ) {
			short quant = 5;
			inParam = new HashMap<>();
			inParam.put("indexCode",usCodeList.get(i));
			inParam.put("ydm",'D');
			inParam.put("quant",quant);
			IndexHistoryDataDto dto = priceService.getIndexHistory(inParam);
			System.out.println("123====================================================");
			System.out.println(usCodeList.get(i) + "===============");
			System.out.println(dto);
			System.out.println("================");
		}*/
		
	}
	
	/*// 원하는 지수과거 일자별 데이터 가져오기 (1년간) 테이블 완성 후 적용
	public void getIndex(String indexCode) throws Exception {
		
		//controller에 getIndex 호출하는 부분에서 indexCode 공통코드에서 꺼내오기
		 
			Map<String,Object> codeMap = new HashMap<String,Object>();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("id", CommonCodeConst.ICPUSCODE_COUNTRY);
			paramMap.put("code_name", "S&P500");
			codeMap = indexMapper.selectUsCodeCont(paramMap);
			this.getIndex(indexCodet); 
		
		
		inParam = new HashMap<>();
		inParam.put("indexCode",indexCode);
		inParam.put("ydm","D");
		inParam.put("quant","365");
		IndexHistoryDataDto dto = priceService.getIndexHistory(inParam);
		
		indexMapper.insertIndex(dto); // 추후 controller로 옮기면 getIndexHistroy 안으로 이동
			
	}*/
}

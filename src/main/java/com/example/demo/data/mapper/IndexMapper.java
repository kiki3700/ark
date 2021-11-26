package com.example.demo.data.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.IndexHistoryDataDto;
import com.example.demo.vo.PriceVo;
@Mapper
public interface IndexMapper {
	List<PriceVo> getPrice(HashMap<String,Object> param);
	void insertPrice(PriceVo vo);
	
	List<HashMap<String,Object>> selectUsCodeCont (Map<String, Object> paramMap);
	//인덱스 인서트
	int insertIndex(IndexHistoryDataDto indexHistoryDataDto);
	
	int insertIntHistoryDataDtoBatch(IndexHistoryDataDto indexHistroyDataDto);
	
	int insertUsCoded(Map<String, Object> paramMap);
	
	List<HashMap<String, Object>> selectUsCodes(Map<String, Object> paramMap);
	
	List<HashMap<String, Object>> selectKorCodes(Map<String, Object> paramMap);

	int insIndexDaishin(IndexHistoryDataDto historyDataDto);
	
	int insertAllIndex(List<IndexHistoryDataDto> indexHistoryDataDto);
}

package com.example.demo.data.dao;

import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BalanceSheetDto;
import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.IndexHistoryDataDto;

@Repository
public class BatchDao {
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
//	

	
	public void initHistoryDataDtoList(List<HistoryDataDto> historyDtoList) {
//		batchSqlSession.insert("ItemMapper.initHistoryDataDtoList", historyDtoList);
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			for( HistoryDataDto historyDataDto : historyDtoList) {
				sqlSession.insert("com.example.demo.data.mapper.ItemMapper.insertHistoryDataDtoBatch",historyDataDto);
			}
		}finally {
			sqlSession.flushStatements();
			sqlSession.close();
			sqlSession.clearCache();
		}
	}

	public void initIndexHistoryDataDtoList(HashSet<IndexHistoryDataDto> indexHistoryDataDtoSet) {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			for( IndexHistoryDataDto indexHistoryDataDto : indexHistoryDataDtoSet) {
				System.out.println(indexHistoryDataDto);
				sqlSession.insert("com.example.demo.data.mapper.IndexMapper.insertIndex",indexHistoryDataDto);
			}
		}finally {
			sqlSession.flushStatements();
			sqlSession.close();
			sqlSession.clearCache();
		}
	}
	
	public void initIndexHistoryDataDtoList(List<IndexHistoryDataDto> indexHistoryDataDtoList) {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			for( IndexHistoryDataDto indexHistoryDataDto : indexHistoryDataDtoList) {
				System.out.println(indexHistoryDataDto);
				sqlSession.insert("com.example.demo.data.mapper.IndexMapper.insertIndex",indexHistoryDataDto);
			}
		}finally {
			sqlSession.flushStatements();
			sqlSession.close();
			sqlSession.clearCache();
		}
	}

    public void insertBalanceSheetBatch(List<BalanceSheetDto> balanceSheetDtoList) {
    	SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
    	try {
    		for(BalanceSheetDto balanceSheetDto : balanceSheetDtoList) {
    			sqlSession.insert("com.example.demo.data.mapper.DartMapper.insertBalanceSheetBatch",balanceSheetDto);
    		}
    	}finally {
			sqlSession.flushStatements();
			sqlSession.close();
			sqlSession.clearCache();
		}
    }
}

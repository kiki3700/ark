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
import com.example.demo.vo.ItemDto;

@Repository
public class BatchDao {
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
//	

	
	public void initHistoryDataDtoList(List<HistoryDataDto> historyDtoList) {
//		batchSqlSession.insert("ItemMapper.initHistoryDataDtoList", historyDtoList);
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			int idx=0;
			for( HistoryDataDto historyDataDto : historyDtoList) {
				sqlSession.insert("com.example.demo.data.mapper.ItemMapper.mergeHistoryDataDto",historyDataDto);
				idx++;
				if(idx%1000==0) {
    				sqlSession.flushStatements();
    				sqlSession.close();
    				sqlSession.clearCache();
    				sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
    			}
			}
		}finally {
			sqlSession.flushStatements();
			sqlSession.close();
			sqlSession.clearCache();
		}
	}
	public void mergeHistoryDataDtoList(List<HistoryDataDto> historyDataDtoList) {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			int idx=0;
			for( HistoryDataDto historyDataDto : historyDataDtoList) {
				sqlSession.insert("com.example.demo.data.mapper.ItemMapper.mergeHistoryDataDto",historyDataDto);
				idx++;
				if(idx%1000==0) {
    				sqlSession.flushStatements();
    				sqlSession.close();
    				sqlSession.clearCache();
    				sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
    			}
			}
		}finally {
			sqlSession.flushStatements();
			sqlSession.close();
			sqlSession.clearCache();
		}
	}
	public void updateMarketCap(List<ItemDto> itemDtoList) {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			int idx=0;
			for( ItemDto itemDto: itemDtoList) {
				sqlSession.insert("com.example.demo.data.mapper.ItemMapper.updateMarketCap",itemDto);
				idx++;
				if(idx%1000==0) {
    				sqlSession.flushStatements();
    				sqlSession.close();
    				sqlSession.clearCache();
    				sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
    			}
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
				int idx=0;
				sqlSession.insert("com.example.demo.data.mapper.IndexMapper.insertIndex",indexHistoryDataDto);
				idx++;
				if(idx%1000==0) {
    				sqlSession.flushStatements();
    			}
			}
		}finally {
			sqlSession.flushStatements();
			sqlSession.close();
			sqlSession.clearCache();
		}
	}
	
	public void mergeIndexHistoryDataDtoList(List<IndexHistoryDataDto> indexHistoryDataDtoList) {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		
		try {
			int idx =0;
			for( IndexHistoryDataDto indexHistoryDataDto : indexHistoryDataDtoList) {
				sqlSession.insert("com.example.demo.data.mapper.IndexMapper.insIndexDaishin",indexHistoryDataDto);
				idx++;
    			if(idx%1000==0) {
    				sqlSession.flushStatements();
    			}
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
			int idx =0;
			for( IndexHistoryDataDto indexHistoryDataDto : indexHistoryDataDtoList) {
				System.out.println(indexHistoryDataDto);
				sqlSession.insert("com.example.demo.data.mapper.IndexMapper.insertIndex",indexHistoryDataDto);
				idx++;
    			if(idx%1000==0) {
    				sqlSession.flushStatements();
    			}
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
			int idx =0;
    		for(BalanceSheetDto balanceSheetDto : balanceSheetDtoList) {
    			sqlSession.insert("com.example.demo.data.mapper.DartMapper.insertBalanceSheetBatch",balanceSheetDto);
				idx++;
    			if(idx%1000==0) {
    				sqlSession.flushStatements();
    			}
    		}
    	}finally {
			sqlSession.flushStatements();
			sqlSession.close();
			sqlSession.clearCache();
		}
    }
    
    public void mergeItemDtoList(List<ItemDto> itemDtoList) {
    	SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
    	try {
    		int idx=0;
    		for(ItemDto itemDto : itemDtoList) {
    			sqlSession.insert("com.example.demo.data.mapper.ItemMapper.mergeItem",itemDto);
    			idx++;
    			if(idx%1000==0) {
    				sqlSession.flushStatements();
    			}
    		}
    	}finally {
			sqlSession.flushStatements();
			sqlSession.close();
			sqlSession.clearCache();
		}
    }
}

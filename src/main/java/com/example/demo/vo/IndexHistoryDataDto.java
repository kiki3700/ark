package com.example.demo.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IndexHistoryDataDto {
	private int id;
	private String indexName;
	private Date indexDate;
	private float open;
	private float close;
	private float low;
	private float high;
	private BigDecimal volume;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public Date getIndexDate() {
		return indexDate;
	}
	public void setIndexDate(Date indexDate) {
		this.indexDate = indexDate;
	}

	public float getOpen() {
		return open;
	}
	public void setOpen(float open) {
		this.open = open;
	}
	public float getClose() {
		return close;
	}
	public void setClose(float close) {
		this.close = close;
	}
	public float getLow() {
		return low;
	}
	public void setLow(float low) {
		this.low = low;
	}
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
	@Override
	public String toString() {
		return "HistoryDataDto [id=" + id + ", indexName=" + indexName + ", indexDate=" + indexDate + ", open=" + open
				+ ", close=" + close + ", low=" + low + ", high=" + high + ", volume=" + volume + "]";
	}
}

package com.example.demo.vo;

import java.util.Date;

public class ItemDto {
	private int id;
	private Date listingDate;
	private String ticker;
	private String name;
	private String isActive;
	private int currentId;
	private String market;
	private String size;
	private String category;
	private String industry;
	public ItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getListingDate() {
		return listingDate;
	}
	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public int getCurrentId() {
		return currentId;
	}
	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "ItemDto [id=" + id + ", listingDate=" + listingDate + ", ticker=" + ticker + ", name=" + name
				+ ", isActive=" + isActive + ", currentId=" + currentId + ", market=" + market + ", size=" + size
				+ ", category=" + category + ", industry=" + industry + "]";
	}

}

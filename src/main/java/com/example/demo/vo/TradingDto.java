package com.example.demo.vo;

import java.util.Date;

public class TradingDto {
	private String id;
	private String ItemId;
	
	private String portfolioTargetId;
	private long price;
	private long amount;
	private String position;
	private Date offerDate;
	private String complete;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemId() {
		return ItemId;
	}
	public void setItemId(String itemId) {
		ItemId = itemId;
	}
	public String getPortfolioTargetId() {
		return portfolioTargetId;
	}
	public void setPortfolioTargetId(String portfolioTargetId) {
		this.portfolioTargetId = portfolioTargetId;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	@Override
	public String toString() {
		return "TradingDto [id=" + id + ", ItemId=" + ItemId + ", portfolioTargetId=" + portfolioTargetId + ", price="
				+ price + ", amount=" + amount + ", position=" + position + ", offerDate=" + offerDate + ", complete="
				+ complete + "]";
	}
}

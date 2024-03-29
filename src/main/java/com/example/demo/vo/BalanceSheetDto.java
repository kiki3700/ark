package com.example.demo.vo;

import java.util.Date;

public class BalanceSheetDto {
	private String id;
	private String itemId;					//아이템아이디
	private int reportingYear;		 	//회계연도
	private String reportCode;			//리포트 코드 (1q, 2q, 3,q 4q)
	private String fsNm;				//연결재무제표 or 재무제표
	private double revenue;				//매출
	private double operatingIncome;			//영업이익
	private double netIncome;				//당기순이익
	private double asset;					//자산
	private double debt;					//부채
	private double equity;					//자본
	private double currentAsset;			//단기자산(현금성자산)
	private double totalNonCurrentAsset;	//장기자산(설비, 부동산 등)
	private double shortTermDebt;			//단기 부채
	private double longTermDebt;			//장기 부채
	
	public BalanceSheetDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public int getReportingYear() {
		return reportingYear;
	}
	public String getFsNm() {
		return fsNm;
	}

	public void setFsNm(String fsNm) {
		this.fsNm = fsNm;
	}

	public void setReportingYear(int reportingYear) {
		this.reportingYear = reportingYear;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	public double getOperatingIncome() {
		return operatingIncome;
	}
	public void setOperatingIncome(double operatingIncome) {
		this.operatingIncome = operatingIncome;
	}
	public double getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}
	public double getAsset() {
		return asset;
	}
	public void setAsset(double asset) {
		this.asset = asset;
	}
	public double getDebt() {
		return debt;
	}
	public void setDebt(double debt) {
		this.debt = debt;
	}
	public double getEquity() {
		return equity;
	}
	public void setEquity(double equity) {
		this.equity = equity;
	}
	public double getCurrentAsset() {
		return currentAsset;
	}
	public void setCurrentAsset(double currentAsset) {
		this.currentAsset = currentAsset;
	}
	public double getTotalNonCurrentAsset() {
		return totalNonCurrentAsset;
	}
	public void setTotalNonCurrentAsset(double totalNonCurrentAsset) {
		this.totalNonCurrentAsset = totalNonCurrentAsset;
	}
	public double getShortTermDebt() {
		return shortTermDebt;
	}
	public void setShortTermDebt(double shortTermDebt) {
		this.shortTermDebt = shortTermDebt;
	}
	public double getLongTermDebt() {
		return longTermDebt;
	}
	public void setLongTermDebt(double longTermDebt) {
		this.longTermDebt = longTermDebt;
	}

	@Override
	public String toString() {
		return "BalanceSheetDto [id=" + id + ", itemId=" + itemId + ", reportingYear=" + reportingYear + ", reportCode="
				+ reportCode + ", fsNm=" + fsNm + ", revenue=" + revenue + ", operatinIncome=" + operatingIncome
				+ ", netIncome=" + netIncome + ", asset=" + asset + ", debt=" + debt + ", equity=" + equity
				+ ", currentAsset=" + currentAsset + ", totalNonCurrentAsset=" + totalNonCurrentAsset
				+ ", shortTermDebt=" + shortTermDebt + ", longTermDebt=" + longTermDebt + "]";
	}
}

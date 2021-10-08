package com.example.demo.vo;

import java.util.Date;

public class BalanceSheetDto {
	private int id;
	private int itemId;					//아이템아이디
	private Date reportingYear;		 	//회계연도
	private String reportCode;			//리포트 코드 (1q, 2q, 3,q 4q)
	private String fsNm;				//연결재무제표 or 재무제표
	private int revenue;				//매출
	private int operatinIncome;			//영업이익
	private int netIncome;				//당기순이익
	private int asset;					//자산
	private int debt;					//부채
	private int equity;					//자본
	private int currentAsset;			//단기자산(현금성자산)
	private int totalNonCurrentAsset;	//장기자산(설비, 부동산 등)
	private int shortTermDebt;			//단기 부채
	private int longTermDebt;			//장기 부채
	private int OCF;					//영업현금흐름
	private int ICF;					//투자현금흐름
	private int FCF;					//재무현금흐름
	
	public BalanceSheetDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public Date getReportingYear() {
		return reportingYear;
	}
	public String getFsNm() {
		return fsNm;
	}

	public void setFsNm(String fsNm) {
		this.fsNm = fsNm;
	}

	public void setReportingYear(Date reportingYear) {
		this.reportingYear = reportingYear;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public int getOperatinIncome() {
		return operatinIncome;
	}
	public void setOperatinIncome(int operatinIncome) {
		this.operatinIncome = operatinIncome;
	}
	public int getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(int netIncome) {
		this.netIncome = netIncome;
	}
	public int getAsset() {
		return asset;
	}
	public void setAsset(int asset) {
		this.asset = asset;
	}
	public int getDebt() {
		return debt;
	}
	public void setDebt(int debt) {
		this.debt = debt;
	}
	public int getEquity() {
		return equity;
	}
	public void setEquity(int equity) {
		this.equity = equity;
	}
	public int getCurrentAsset() {
		return currentAsset;
	}
	public void setCurrentAsset(int currentAsset) {
		this.currentAsset = currentAsset;
	}
	public int getTotalNonCurrentAsset() {
		return totalNonCurrentAsset;
	}
	public void setTotalNonCurrentAsset(int totalNonCurrentAsset) {
		this.totalNonCurrentAsset = totalNonCurrentAsset;
	}
	public int getShortTermDebt() {
		return shortTermDebt;
	}
	public void setShortTermDebt(int shortTermDebt) {
		this.shortTermDebt = shortTermDebt;
	}
	public int getLongTermDebt() {
		return longTermDebt;
	}
	public void setLongTermDebt(int longTermDebt) {
		this.longTermDebt = longTermDebt;
	}
	public int getOCF() {
		return OCF;
	}
	public void setOCF(int oCF) {
		OCF = oCF;
	}
	public int getICF() {
		return ICF;
	}
	public void setICF(int iCF) {
		ICF = iCF;
	}
	public int getFCF() {
		return FCF;
	}
	public void setFCF(int fCF) {
		FCF = fCF;
	}

	@Override
	public String toString() {
		return "BalanceSheetDto [id=" + id + ", itemId=" + itemId + ", reportingYear=" + reportingYear + ", reportCode="
				+ reportCode + ", fsNm=" + fsNm + ", revenue=" + revenue + ", operatinIncome=" + operatinIncome
				+ ", netIncome=" + netIncome + ", asset=" + asset + ", debt=" + debt + ", equity=" + equity
				+ ", currentAsset=" + currentAsset + ", totalNonCurrentAsset=" + totalNonCurrentAsset
				+ ", shortTermDebt=" + shortTermDebt + ", longTermDebt=" + longTermDebt + ", OCF=" + OCF + ", ICF="
				+ ICF + ", FCF=" + FCF + "]";
	}
}

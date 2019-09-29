package com.softline.entity.fundPosition;

/**
 * DataFundPositionRmb entity. @author MyEclipse Persistence Tools
 */

public class DataFundPositionRmb implements java.io.Serializable {

	// Fields

	private Integer id;
	private DataFundPosition dataFundPosition;
	private String fcAvailableCashR;
	private String fcNavailableCashR;
	private String BAvailableCashR;
	private String BNavailableCashOneR;
	private String BNavailableCashTwoR;
	private String fcCashR;
	private String BNavailableCashR;
	private String availableCashR;
	private String navailableCashR;
	private String cashR;
	private String currencyR;

	// Constructors

	/** default constructor */
	public DataFundPositionRmb() {
	}

	/** full constructor */
	public DataFundPositionRmb(DataFundPosition dataFundPosition,
			String fcAvailableCashR, String fcNavailableCashR,
			String BAvailableCashR, String BNavailableCashOneR,
			String BNavailableCashTwoR, String fcCashR,
			String BNavailableCashR, String availableCashR,
			String navailableCashR, String cashR, String currencyR) {
		this.dataFundPosition = dataFundPosition;
		this.fcAvailableCashR = fcAvailableCashR;
		this.fcNavailableCashR = fcNavailableCashR;
		this.BAvailableCashR = BAvailableCashR;
		this.BNavailableCashOneR = BNavailableCashOneR;
		this.BNavailableCashTwoR = BNavailableCashTwoR;
		this.fcCashR = fcCashR;
		this.BNavailableCashR = BNavailableCashR;
		this.availableCashR = availableCashR;
		this.navailableCashR = navailableCashR;
		this.cashR = cashR;
		this.currencyR = currencyR;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DataFundPosition getDataFundPosition() {
		return this.dataFundPosition;
	}

	public void setDataFundPosition(DataFundPosition dataFundPosition) {
		this.dataFundPosition = dataFundPosition;
	}

	public String getFcAvailableCashR() {
		return this.fcAvailableCashR;
	}

	public void setFcAvailableCashR(String fcAvailableCashR) {
		this.fcAvailableCashR = fcAvailableCashR;
	}

	public String getFcNavailableCashR() {
		return this.fcNavailableCashR;
	}

	public void setFcNavailableCashR(String fcNavailableCashR) {
		this.fcNavailableCashR = fcNavailableCashR;
	}

	public String getBAvailableCashR() {
		return this.BAvailableCashR;
	}

	public void setBAvailableCashR(String BAvailableCashR) {
		this.BAvailableCashR = BAvailableCashR;
	}

	public String getBNavailableCashOneR() {
		return this.BNavailableCashOneR;
	}

	public void setBNavailableCashOneR(String BNavailableCashOneR) {
		this.BNavailableCashOneR = BNavailableCashOneR;
	}

	public String getBNavailableCashTwoR() {
		return this.BNavailableCashTwoR;
	}

	public void setBNavailableCashTwoR(String BNavailableCashTwoR) {
		this.BNavailableCashTwoR = BNavailableCashTwoR;
	}

	public String getFcCashR() {
		return this.fcCashR;
	}

	public void setFcCashR(String fcCashR) {
		this.fcCashR = fcCashR;
	}

	public String getBNavailableCashR() {
		return this.BNavailableCashR;
	}

	public void setBNavailableCashR(String BNavailableCashR) {
		this.BNavailableCashR = BNavailableCashR;
	}

	public String getAvailableCashR() {
		return this.availableCashR;
	}

	public void setAvailableCashR(String availableCashR) {
		this.availableCashR = availableCashR;
	}

	public String getNavailableCashR() {
		return this.navailableCashR;
	}

	public void setNavailableCashR(String navailableCashR) {
		this.navailableCashR = navailableCashR;
	}

	public String getCashR() {
		return this.cashR;
	}

	public void setCashR(String cashR) {
		this.cashR = cashR;
	}

	public String getCurrencyR() {
		return this.currencyR;
	}

	public void setCurrencyR(String currencyR) {
		this.currencyR = currencyR;
	}

}
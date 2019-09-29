package com.softline.entity.fundPosition;

/**
 * DataFundPositionOther entity. @author MyEclipse Persistence Tools
 */

public class DataFundPositionOther implements java.io.Serializable {

	// Fields

	private Integer id;
	//private DataFundPosition dataFundPosition;
	private String fcAvailableCashO;
	private String fcNavailableCashO;
	private String BAvailableCashO;
	private String BNavailableCashOneO;
	private String BNavailableCashTwoO;
	private String fcCashO;
	private String BNavailableCashO;
	private String availableCashO;
	private String navailableCashO;
	private String cashO;
	private String currencyO;

	// Constructors

	/** default constructor */
	public DataFundPositionOther() {
	}

	/** full constructor */
	public DataFundPositionOther(
			String fcAvailableCashO, String fcNavailableCashO,
			String BAvailableCashO, String BNavailableCashOneO,
			String BNavailableCashTwoO, String fcCashO,
			String BNavailableCashO, String availableCashO,
			String navailableCashO, String cashO, String currencyO) {
		this.fcAvailableCashO = fcAvailableCashO;
		this.fcNavailableCashO = fcNavailableCashO;
		this.BAvailableCashO = BAvailableCashO;
		this.BNavailableCashOneO = BNavailableCashOneO;
		this.BNavailableCashTwoO = BNavailableCashTwoO;
		this.fcCashO = fcCashO;
		this.BNavailableCashO = BNavailableCashO;
		this.availableCashO = availableCashO;
		this.navailableCashO = navailableCashO;
		this.cashO = cashO;
		this.currencyO = currencyO;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFcAvailableCashO() {
		return this.fcAvailableCashO;
	}

	public void setFcAvailableCashO(String fcAvailableCashO) {
		this.fcAvailableCashO = fcAvailableCashO;
	}

	public String getFcNavailableCashO() {
		return this.fcNavailableCashO;
	}

	public void setFcNavailableCashO(String fcNavailableCashO) {
		this.fcNavailableCashO = fcNavailableCashO;
	}

	public String getBAvailableCashO() {
		return this.BAvailableCashO;
	}

	public void setBAvailableCashO(String BAvailableCashO) {
		this.BAvailableCashO = BAvailableCashO;
	}

	public String getBNavailableCashOneO() {
		return this.BNavailableCashOneO;
	}

	public void setBNavailableCashOneO(String BNavailableCashOneO) {
		this.BNavailableCashOneO = BNavailableCashOneO;
	}

	public String getBNavailableCashTwoO() {
		return this.BNavailableCashTwoO;
	}

	public void setBNavailableCashTwoO(String BNavailableCashTwoO) {
		this.BNavailableCashTwoO = BNavailableCashTwoO;
	}

	public String getFcCashO() {
		return this.fcCashO;
	}

	public void setFcCashO(String fcCashO) {
		this.fcCashO = fcCashO;
	}

	public String getBNavailableCashO() {
		return this.BNavailableCashO;
	}

	public void setBNavailableCashO(String BNavailableCashO) {
		this.BNavailableCashO = BNavailableCashO;
	}

	public String getAvailableCashO() {
		return this.availableCashO;
	}

	public void setAvailableCashO(String availableCashO) {
		this.availableCashO = availableCashO;
	}

	public String getNavailableCashO() {
		return this.navailableCashO;
	}

	public void setNavailableCashO(String navailableCashO) {
		this.navailableCashO = navailableCashO;
	}

	public String getCashO() {
		return this.cashO;
	}

	public void setCashO(String cashO) {
		this.cashO = cashO;
	}

	public String getCurrencyO() {
		return this.currencyO;
	}

	public void setCurrencyO(String currencyO) {
		this.currencyO = currencyO;
	}
	
	public int compareTo(DataFundPositionOther o) {
		if(o.getId()==null||this.getId()==null){
			return 1;
		}else{
			return (o.getId() - this.getId());
		}
	}
}
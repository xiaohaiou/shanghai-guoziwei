package com.softline.entityTemp;

/**
 * VContractChangeId entity. @author MyEclipse Persistence Tools
 */

public class VContractChange implements java.io.Serializable {

	// Fields
	private Integer id;
	private Integer pjId;
	private String pcName;
	private String pcBodyA;
	private String pcBodyB;
	private Double pcBdMoney;
	private Double oldPaidMoney;
	private Double newPaidMoney;

	// Constructors

	/** default constructor */
	public VContractChange() {
	}


	/** full constructor */
	public VContractChange(Integer id,Integer pjId,String pcName, String pcBodyA,
			String pcBodyB, Double pcBdMoney, Double oldPaidMoney,
			Double newPaidMoney) {
		this.id = id;
		this.pjId = pjId;
		this.pcName = pcName;
		this.pcBodyA = pcBodyA;
		this.pcBodyB = pcBodyB;
		this.pcBdMoney = pcBdMoney;
		this.oldPaidMoney = oldPaidMoney;
		this.newPaidMoney = newPaidMoney;
	}

	// Property accessors

	public Integer getId() {
		return id;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPcName() {
		return this.pcName;
	}



	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getPcBodyA() {
		return this.pcBodyA;
	}

	public void setPcBodyA(String pcBodyA) {
		this.pcBodyA = pcBodyA;
	}

	public String getPcBodyB() {
		return this.pcBodyB;
	}

	public void setPcBodyB(String pcBodyB) {
		this.pcBodyB = pcBodyB;
	}

	public Double getPcBdMoney() {
		return this.pcBdMoney;
	}

	public void setPcBdMoney(Double pcBdMoney) {
		this.pcBdMoney = pcBdMoney;
	}

	public Double getOldPaidMoney() {
		return this.oldPaidMoney;
	}

	public void setOldPaidMoney(Double oldPaidMoney) {
		this.oldPaidMoney = oldPaidMoney;
	}

	public Double getNewPaidMoney() {
		return this.newPaidMoney;
	}

	public void setNewPaidMoney(Double newPaidMoney) {
		this.newPaidMoney = newPaidMoney;
	}


	public Integer getPjId() {
		return pjId;
	}


	public void setPjId(Integer pjId) {
		this.pjId = pjId;
	}
	
	
}
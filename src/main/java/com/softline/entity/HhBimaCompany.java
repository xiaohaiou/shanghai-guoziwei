package com.softline.entity;

/**
 * HhBimaCompany entity. @author MyEclipse Persistence Tools
 */

public class HhBimaCompany implements java.io.Serializable {

	// Fields

	private Integer bimaId;
	private String registeredCapital;
	private String unionid;
	private String legalPersonName;
	private String registryState;
	private String setTime;
	private String isExpire;
	private String expire;
	private String registrationNumber;
	private String address;
	private String area;
	private String type1;
	private String type2;
	private String type3;
	private String businessFormat;
	private String englishName;
	private String operational;
	private String operationalPurpose;
	private String name;
	
	private String lastModifyDate;
	// Constructors

	/** default constructor */
	public HhBimaCompany() {
	}

	/** minimal constructor */
	public HhBimaCompany(Integer bimaId) {
		this.bimaId = bimaId;
	}

	/** full constructor */
	

	// Property accessors

	public Integer getBimaId() {
		return this.bimaId;
	}

	public HhBimaCompany(Integer bimaId, String registeredCapital,
			String unionid, String legalPersonName, String registryState,
			String setTime, String isExpire, String expire,
			String registrationNumber, String address, String area,
			String type1, String type2, String type3, String businessFormat,
			String englishName, String operational, String operationalPurpose,
			String name) {
		super();
		this.bimaId = bimaId;
		this.registeredCapital = registeredCapital;
		this.unionid = unionid;
		this.legalPersonName = legalPersonName;
		this.registryState = registryState;
		this.setTime = setTime;
		this.isExpire = isExpire;
		this.expire = expire;
		this.registrationNumber = registrationNumber;
		this.address = address;
		this.area = area;
		this.type1 = type1;
		this.type2 = type2;
		this.type3 = type3;
		this.businessFormat = businessFormat;
		this.englishName = englishName;
		this.operational = operational;
		this.operationalPurpose = operationalPurpose;
		this.name = name;
	}

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBimaId(Integer bimaId) {
		this.bimaId = bimaId;
	}

	public String getRegisteredCapital() {
		return this.registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getLegalPersonName() {
		return this.legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getRegistryState() {
		return this.registryState;
	}

	public void setRegistryState(String registryState) {
		this.registryState = registryState;
	}

	public String getSetTime() {
		return this.setTime;
	}

	public void setSetTime(String setTime) {
		this.setTime = setTime;
	}

	public String getIsExpire() {
		return this.isExpire;
	}

	public void setIsExpire(String isExpire) {
		this.isExpire = isExpire;
	}

	public String getExpire() {
		return this.expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getType1() {
		return this.type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return this.type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getType3() {
		return this.type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}

	public String getBusinessFormat() {
		return this.businessFormat;
	}

	public void setBusinessFormat(String businessFormat) {
		this.businessFormat = businessFormat;
	}

	public String getEnglishName() {
		return this.englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getOperational() {
		return this.operational;
	}

	public void setOperational(String operational) {
		this.operational = operational;
	}

	public String getOperationalPurpose() {
		return this.operationalPurpose;
	}

	public void setOperationalPurpose(String operationalPurpose) {
		this.operationalPurpose = operationalPurpose;
	}

}
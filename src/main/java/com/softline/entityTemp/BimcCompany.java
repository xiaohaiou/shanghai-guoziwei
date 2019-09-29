package com.softline.entityTemp;


public class BimcCompany {

	private Integer bimaID;//bima的公司主键
	
	private String name;//公司名称
	
	private Integer parentID;//父节点主键
	
	private String registeredCapital;//注册资本
	
	private String unionid;//注册资本币种
	
	private String legalPersonName;//法人
	
	private String registryState;//登记状态 
	
	private String setTime;//成立时间
	
	private String isExpire;//经营期限 无限期or“”
	
	private String Expire;//经营期限 null or  数值
	
	private String registrationNumber;//工商注册号

	private String address;//地址
	
	private String area;//省市
	
	private String type1;//上市非上市
	
	private String type2;//企业类型  有限责任公司...等等
	
	private String type3;//企业经营类型  运营及其他类..等等
	
	private String businessFormat;//业态
	
	private String englishName;//英文名
	
	private String operational;// 运营性质  实体、非实体
	
	private String operationalPurpose;//企业成立目的
	private String companySimple;
//	公司简称
	
	public String getcompanySimple() {
		return companySimple;
	}
	public void setcompanySimple(String companySimple) {
		this.companySimple = companySimple;
	}
	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getBimaID() {
		return bimaID;
	}

	public void setBimaID(Integer bimaID) {
		this.bimaID = bimaID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getRegistryState() {
		return registryState;
	}

	public void setRegistryState(String registryState) {
		this.registryState = registryState;
	}

	public String getSetTime() {
		return setTime;
	}

	public void setSetTime(String setTime) {
		this.setTime = setTime;
	}

	public String getIsExpire() {
		return isExpire;
	}

	public void setIsExpire(String isExpire) {
		this.isExpire = isExpire;
	}

	public String getExpire() {
		return Expire;
	}

	public void setExpire(String expire) {
		Expire = expire;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}

	public String getBusinessFormat() {
		return businessFormat;
	}

	public void setBusinessFormat(String businessFormat) {
		this.businessFormat = businessFormat;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getOperational() {
		return operational;
	}

	public void setOperational(String operational) {
		this.operational = operational;
	}

	public String getOperationalPurpose() {
		return operationalPurpose;
	}

	public void setOperationalPurpose(String operationalPurpose) {
		this.operationalPurpose = operationalPurpose;
	}
	
	
}
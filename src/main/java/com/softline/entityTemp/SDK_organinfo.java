package com.softline.entityTemp;


public class SDK_organinfo {

	private String nNodeID;					//机构编号
	
	private String vcOrganID;					//节点编号
	
	private String vcParentID;					//父节点编号
	
	private String cFlag;							//有效标示

	private Integer iShowOrder;				//机构排序号

	private String vcFullName;				//机构全称

	private String vcShortName;				//机构简称
	
	private String vcshoworder;				//排序节点号
	
	private String dOperatorDate;			//操作时间
	
	private String vcCompany;
	private String dStartDate;
	private String vcCreatorID;
	private String dCreatorDate;
	private String cLevel;
	
	
	public String getVcCompany() {
		return vcCompany;
	}

	public void setVcCompany(String vcCompany) {
		this.vcCompany = vcCompany;
	}

	public String getdStartDate() {
		return dStartDate;
	}

	public void setdStartDate(String dStartDate) {
		this.dStartDate = dStartDate;
	}

	public String getVcCreatorID() {
		return vcCreatorID;
	}

	public void setVcCreatorID(String vcCreatorID) {
		this.vcCreatorID = vcCreatorID;
	}

	public String getdCreatorDate() {
		return dCreatorDate;
	}

	public void setdCreatorDate(String dCreatorDate) {
		this.dCreatorDate = dCreatorDate;
	}

	public String getcLevel() {
		return cLevel;
	}

	public void setcLevel(String cLevel) {
		this.cLevel = cLevel;
	}

	public String getnNodeID() {
		return nNodeID;
	}

	public void setnNodeID(String nNodeID) {
		this.nNodeID = nNodeID;
	}

	public String getVcOrganID() {
		return vcOrganID;
	}

	public void setVcOrganID(String vcOrganID) {
		this.vcOrganID = vcOrganID;
	}

	public String getVcParentID() {
		return vcParentID;
	}

	public void setVcParentID(String vcParentID) {
		this.vcParentID = vcParentID;
	}

	public String getcFlag() {
		return cFlag;
	}

	public void setcFlag(String cFlag) {
		this.cFlag = cFlag;
	}

	public Integer getiShowOrder() {
		return iShowOrder;
	}

	public void setiShowOrder(Integer iShowOrder) {
		this.iShowOrder = iShowOrder;
	}

	public String getVcFullName() {
		return vcFullName;
	}

	public void setVcFullName(String vcFullName) {
		this.vcFullName = vcFullName;
	}

	public String getVcShortName() {
		return vcShortName;
	}

	public void setVcShortName(String vcShortName) {
		this.vcShortName = vcShortName;
	}

	public String getVcshoworder() {
		return vcshoworder;
	}

	public void setVcshoworder(String vcshoworder) {
		this.vcshoworder = vcshoworder;
	}

	public String getdOperatorDate() {
		return dOperatorDate;
	}

	public void setdOperatorDate(String dOperatorDate) {
		this.dOperatorDate = dOperatorDate;
	}

}
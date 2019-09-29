package com.softline.entityTemp;

public class HhOrganInfoTreeRelationFinance {
	 private String nNodeID;
	 private String vcVullName;
	 private Integer isMistake;
	public HhOrganInfoTreeRelationFinance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HhOrganInfoTreeRelationFinance(String nNodeID, String vcVullName, Integer isMistake) {
		super();
		this.nNodeID = nNodeID;
		this.vcVullName = vcVullName;
		this.isMistake = isMistake;
	}
	public String getnNodeID() {
		return nNodeID;
	}
	public void setnNodeID(String nNodeID) {
		this.nNodeID = nNodeID;
	}
	public String getVcVullName() {
		return vcVullName;
	}
	public void setVcVullName(String vcVullName) {
		this.vcVullName = vcVullName;
	}
	public Integer getIsMistake() {
		return isMistake;
	}
	public void setIsMistake(Integer isMistake) {
		this.isMistake = isMistake;
	}
	 

}

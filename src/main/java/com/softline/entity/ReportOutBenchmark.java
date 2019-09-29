package com.softline.entity;

public class ReportOutBenchmark implements java.io.Serializable{
	
	private Integer id;
	private String orgname;
	private String org;
	private Integer orgIsHongkong;
	private String orgcode;
	private String financeorgname;
	private String financeorg;
	private String financecode;
	private Integer financeorgIsHongkong;
	private String parentorg;
	
	//formula
	private String authOrg; // 权限控制字段
	
	public ReportOutBenchmark() {
	}

	public ReportOutBenchmark(Integer id, String orgname, String org,
			Integer orgIsHongkong, String orgcode, String financeorgname,
			String financeorg, String financecode,
			Integer financeorgIsHongkong, String parentorg) {
		super();
		this.id = id;
		this.orgname = orgname;
		this.org = org;
		this.orgIsHongkong = orgIsHongkong;
		this.orgcode = orgcode;
		this.financeorgname = financeorgname;
		this.financeorg = financeorg;
		this.financecode = financecode;
		this.financeorgIsHongkong = financeorgIsHongkong;
		this.parentorg = parentorg;
	}

	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public Integer getOrgIsHongkong() {
		return orgIsHongkong;
	}
	public void setOrgIsHongkong(Integer orgIsHongkong) {
		this.orgIsHongkong = orgIsHongkong;
	}
	public String getFinanceorgname() {
		return financeorgname;
	}
	public void setFinanceorgname(String financeorgname) {
		this.financeorgname = financeorgname;
	}
	public String getFinanceorg() {
		return financeorg;
	}
	public void setFinanceorg(String financeorg) {
		this.financeorg = financeorg;
	}
	public Integer getFinanceorgIsHongkong() {
		return financeorgIsHongkong;
	}
	public void setFinanceorgIsHongkong(Integer financeorgIsHongkong) {
		this.financeorgIsHongkong = financeorgIsHongkong;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getFinancecode() {
		return financecode;
	}

	public void setFinancecode(String financecode) {
		this.financecode = financecode;
	}
	
	
	public String getParentorg() {
		return parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}
	

}

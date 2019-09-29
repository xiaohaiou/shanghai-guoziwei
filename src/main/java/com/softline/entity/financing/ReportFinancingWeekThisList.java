package com.softline.entity.financing;


/**
 * ReportFinancingWeekThisList entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingWeekThisList implements java.io.Serializable,Comparable<ReportFinancingWeekThisList>  {

	// Fields

	private Integer id;
	private Integer pid;
	private Integer fid;
	private String financialInstitution;
	private String operateOrg;
	private String operateOrgname;
	private String alreadyAccountAmounts;
	private String accountDate;
	private Integer projectProgress;
	
	private String lastModifyDate;
	private String projectProgressName;
	// Constructors

	/** default constructor */
	public ReportFinancingWeekThisList() {
	}

	/** full constructor */
	public ReportFinancingWeekThisList(
			String financialInstitution, String operateOrg,
			String operateOrgname, String alreadyAccountAmounts,
			String accountDate, Integer projectProgress) {
		this.financialInstitution = financialInstitution;
		this.operateOrg = operateOrg;
		this.operateOrgname = operateOrgname;
		this.alreadyAccountAmounts = alreadyAccountAmounts;
		this.accountDate = accountDate;
		this.projectProgress = projectProgress;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectProgressName() {
		return projectProgressName;
	}

	public void setProjectProgressName(String projectProgressName) {
		this.projectProgressName = projectProgressName;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getFinancialInstitution() {
		return this.financialInstitution;
	}

	public void setFinancialInstitution(String financialInstitution) {
		this.financialInstitution = financialInstitution;
	}

	public String getOperateOrg() {
		return this.operateOrg;
	}

	public void setOperateOrg(String operateOrg) {
		this.operateOrg = operateOrg;
	}

	public String getOperateOrgname() {
		return this.operateOrgname;
	}

	public void setOperateOrgname(String operateOrgname) {
		this.operateOrgname = operateOrgname;
	}

	public String getAlreadyAccountAmounts() {
		return this.alreadyAccountAmounts;
	}

	public void setAlreadyAccountAmounts(String alreadyAccountAmounts) {
		this.alreadyAccountAmounts = alreadyAccountAmounts;
	}

	public String getAccountDate() {
		return this.accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public Integer getProjectProgress() {
		return this.projectProgress;
	}

	public void setProjectProgress(Integer projectProgress) {
		this.projectProgress = projectProgress;
	}
	
	public int compareTo(ReportFinancingWeekThisList o) {
		if(o.getId()==null||this.getId()==null){
			return 1;
		}else{
			return (o.getId() - this.getId());
		}
	}

}
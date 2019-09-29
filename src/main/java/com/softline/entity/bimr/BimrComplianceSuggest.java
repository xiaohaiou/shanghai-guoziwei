package com.softline.entity.bimr;



/**
 * BimrComplianceSuggest entity. @author MyEclipse Persistence Tools
 */

public class BimrComplianceSuggest  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer complianceId;
     private String suggest;
     private Integer isChange;
     private String followDep;
     private String followPerson;
     private String followPersonId;
     private String abutmentPerson;
     private String accountabilityPerson;
     private String accountabilityDep;
     private String accountabilityDepId;
     private String changeLastTime;
     private Integer changeStatus;
     private String changeProgress;
     private String createPersonName;
     private String createPersonId;
     private String createDate;
     private String lastModifyPersonId;
     private String lastModifyPersonName;
     private String lastModifyDate;
     private Integer isDel;


    // Constructors

    /** default constructor */
    public BimrComplianceSuggest() {
    }

	/** minimal constructor */
    public BimrComplianceSuggest(Integer isDel) {
        this.isDel = isDel;
    }
    
    /** full constructor */
    public BimrComplianceSuggest(String suggest, Integer isChange, String followDep, String followPerson,String followPersonId, String abutmentPerson, 
    		String accountabilityPerson, String accountabilityDep, String accountabilityDepId, String changeLastTime, 
    		Integer changeStatus, String changeProgress, String createPersonName, String createPersonId, 
    		String createDate, String lastModifyPersonId, String lastModifyPersonName, String lastModifyDate, Integer isDel) {
        this.suggest = suggest;
        this.isChange = isChange;
        this.followDep = followDep;
        this.followPerson = followPerson;
        this.followPersonId = followPersonId;
        this.abutmentPerson = abutmentPerson;
        this.accountabilityPerson = accountabilityPerson;
        this.accountabilityDep = accountabilityDep;
        this.accountabilityDepId = accountabilityDepId;
        this.changeLastTime = changeLastTime;
        this.changeStatus = changeStatus;
        this.changeProgress = changeProgress;
        this.createPersonName = createPersonName;
        this.createPersonId = createPersonId;
        this.createDate = createDate;
        this.lastModifyPersonId = lastModifyPersonId;
        this.lastModifyPersonName = lastModifyPersonName;
        this.lastModifyDate = lastModifyDate;
        this.isDel = isDel;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSuggest() {
        return this.suggest;
    }
    
    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public Integer getIsChange() {
        return this.isChange;
    }
    
    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }

    public String getFollowDep() {
        return this.followDep;
    }
    
    public void setFollowDep(String followDep) {
        this.followDep = followDep;
    }

    public String getFollowPerson() {
        return this.followPerson;
    }
    
    public void setFollowPerson(String followPerson) {
        this.followPerson = followPerson;
    }

    public String getAbutmentPerson() {
        return this.abutmentPerson;
    }
    
    public void setAbutmentPerson(String abutmentPerson) {
        this.abutmentPerson = abutmentPerson;
    }

    public String getAccountabilityPerson() {
        return this.accountabilityPerson;
    }
    
    public void setAccountabilityPerson(String accountabilityPerson) {
        this.accountabilityPerson = accountabilityPerson;
    }

    public String getAccountabilityDep() {
        return this.accountabilityDep;
    }
    
    public void setAccountabilityDep(String accountabilityDep) {
        this.accountabilityDep = accountabilityDep;
    }

    public String getChangeLastTime() {
        return this.changeLastTime;
    }
    
    public void setChangeLastTime(String changeLastTime) {
        this.changeLastTime = changeLastTime;
    }

    public Integer getChangeStatus() {
        return this.changeStatus;
    }
    
    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }

    public String getChangeProgress() {
        return this.changeProgress;
    }
    
    public void setChangeProgress(String changeProgress) {
        this.changeProgress = changeProgress;
    }

    public String getCreatePersonName() {
        return this.createPersonName;
    }
    
    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    public String getCreatePersonId() {
        return this.createPersonId;
    }
    
    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    public String getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastModifyPersonId() {
        return this.lastModifyPersonId;
    }
    
    public void setLastModifyPersonId(String lastModifyPersonId) {
        this.lastModifyPersonId = lastModifyPersonId;
    }

    public String getLastModifyPersonName() {
        return this.lastModifyPersonName;
    }
    
    public void setLastModifyPersonName(String lastModifyPersonName) {
        this.lastModifyPersonName = lastModifyPersonName;
    }

    public String getLastModifyDate() {
        return this.lastModifyDate;
    }
    
    public void setLastModifyDate(String lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Integer getIsDel() {
        return this.isDel;
    }
    
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

	public Integer getComplianceId() {
		return complianceId;
	}

	public void setComplianceId(Integer complianceId) {
		this.complianceId = complianceId;
	}

	public String getFollowPersonId() {
		return followPersonId;
	}

	public void setFollowPersonId(String followPersonId) {
		this.followPersonId = followPersonId;
	}

	public String getAccountabilityDepId() {
		return accountabilityDepId;
	}

	public void setAccountabilityDepId(String accountabilityDepId) {
		this.accountabilityDepId = accountabilityDepId;
	}
   








}
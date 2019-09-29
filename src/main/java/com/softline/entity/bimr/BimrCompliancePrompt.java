package com.softline.entity.bimr;



/**
 * BimrCompliancePrompt entity. @author MyEclipse Persistence Tools
 */

public class BimrCompliancePrompt  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer complianceId;
     private String promptDescribe;
     private Integer risk1;
     private Integer risk2;
     private Integer risk3;
     private String createPersonName;
     private String createPersonId;
     private String createDate;
     private String lastModifyPersonId;
     private String lastModifyPersonName;
     private String lastModifyDate;
     private Integer isDel;

     private String riskName1;
     private String riskName2;
     private String riskName3;

    // Constructors

    /** default constructor */
    public BimrCompliancePrompt() {
    }

	/** minimal constructor */
    public BimrCompliancePrompt(Integer isDel) {
        this.isDel = isDel;
    }
    
    /** full constructor */
    public BimrCompliancePrompt(String promptDescribe, Integer risk1, Integer risk2, Integer risk3, String createPersonName, String createPersonId, String createDate, String lastModifyPersonId, String lastModifyPersonName, String lastModifyDate, Integer isDel) {
        this.promptDescribe = promptDescribe;
        this.risk1 = risk1;
        this.risk2 = risk2;
        this.risk3 = risk3;
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

    public String getPromptDescribe() {
        return this.promptDescribe;
    }
    
    public void setPromptDescribe(String promptDescribe) {
        this.promptDescribe = promptDescribe;
    }

    public Integer getRisk1() {
        return this.risk1;
    }
    
    public void setRisk1(Integer risk1) {
        this.risk1 = risk1;
    }

    public Integer getRisk2() {
        return this.risk2;
    }
    
    public void setRisk2(Integer risk2) {
        this.risk2 = risk2;
    }

    public Integer getRisk3() {
        return this.risk3;
    }
    
    public void setRisk3(Integer risk3) {
        this.risk3 = risk3;
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

	public String getRiskName1() {
		return riskName1;
	}

	public void setRiskName1(String riskName1) {
		this.riskName1 = riskName1;
	}

	public String getRiskName2() {
		return riskName2;
	}

	public void setRiskName2(String riskName2) {
		this.riskName2 = riskName2;
	}

	public String getRiskName3() {
		return riskName3;
	}

	public void setRiskName3(String riskName3) {
		this.riskName3 = riskName3;
	}
   








}
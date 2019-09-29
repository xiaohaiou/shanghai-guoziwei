package com.softline.entity.settingCenter;



/**
 * HhRolepagecode entity. @author MyEclipse Persistence Tools
 */

public class PortalHhRolepagecode  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer sysId;
     private Integer roleId;
     private Integer roleNum;
     private Integer pageId;
     private Integer codeId;
     private Integer modelId;
     private String code;
     private String codeName;

    // Constructors

    /** default constructor */
    public PortalHhRolepagecode() {
    }

    
    /** full constructor */
    public PortalHhRolepagecode(Integer sysId, Integer roleId, Integer roleNum, Integer pageId, Integer codeId, Integer modelId) {
        this.sysId = sysId;
        this.roleId = roleId;
        this.roleNum = roleNum;
        this.pageId = pageId;
        this.codeId = codeId;
        this.modelId = modelId;
    }

   
    // Property accessors

    public Integer getModelId() {
		return modelId;
	}


	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}


	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysId() {
        return this.sysId;
    }
    
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleNum() {
        return this.roleNum;
    }
    
    public void setRoleNum(Integer roleNum) {
        this.roleNum = roleNum;
    }

    public Integer getPageId() {
        return this.pageId;
    }
    
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getCodeId() {
        return this.codeId;
    }
    
    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
    
}
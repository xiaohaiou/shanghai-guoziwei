package com.softline.entity.settingCenter;

import java.util.List;



/**
 * HhRolepage entity. @author MyEclipse Persistence Tools
 */

public class PortalHhRolepage  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer sysId;
     private Integer roleId;
     private Integer roleNum;
     private Integer pageId;
     
     private String pageNum;
     private String pageName;
     private Integer modelId;
    // Constructors

    /** default constructor */
    public PortalHhRolepage() {
    }

    
    /** full constructor */
    public PortalHhRolepage(Integer sysId, Integer roleId, Integer roleNum, Integer pageId, Integer modelId) {
        this.sysId = sysId;
        this.roleId = roleId;
        this.roleNum = roleNum;
        this.pageId = pageId;
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


	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}


	public String getPageName() {
		return pageName;
	}


	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
    
}
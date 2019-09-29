package com.softline.entity;



/**
 * HhSdkLog entity. @author MyEclipse Persistence Tools
 */

public class HhSdkLog  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer totalrow;
     private Integer rightrow;
     private Integer type;
     private String errorinfomation;
     private Integer errorrow;
     private String createDate;
     private String createPersonId;
     private String createPersonName;
     private String lastModifyPersonId;
     private String lastModifyPersonName;
     private String lastModifyDate;
     private String typeName;

    // Constructors

    /** default constructor */
    public HhSdkLog() {
    }

    
    /** full constructor */
    public HhSdkLog(Integer totalrow, Integer rightrow, Integer type, String errorinfomation, Integer errorrow, String createDate, String createPersonId, String createPersonName, String lastModifyPersonId, String lastModifyPersonName, String lastModifyDate,String typeName) {
        this.totalrow = totalrow;
        this.rightrow = rightrow;
        this.type = type;
        this.errorinfomation = errorinfomation;
        this.errorrow = errorrow;
        this.createDate = createDate;
        this.createPersonId = createPersonId;
        this.createPersonName = createPersonName;
        this.lastModifyPersonId = lastModifyPersonId;
        this.lastModifyPersonName = lastModifyPersonName;
        this.lastModifyDate = lastModifyDate;
        this.typeName=typeName;
    }

   
    // Property accessors

    public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalrow() {
        return this.totalrow;
    }
    
    public void setTotalrow(Integer totalrow) {
        this.totalrow = totalrow;
    }

    public Integer getRightrow() {
        return this.rightrow;
    }
    
    public void setRightrow(Integer rightrow) {
        this.rightrow = rightrow;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public String getErrorinfomation() {
        return this.errorinfomation;
    }
    
    public void setErrorinfomation(String errorinfomation) {
        this.errorinfomation = errorinfomation;
    }

    public Integer getErrorrow() {
        return this.errorrow;
    }
    
    public void setErrorrow(Integer errorrow) {
        this.errorrow = errorrow;
    }

    public String getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatePersonId() {
        return this.createPersonId;
    }
    
    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    public String getCreatePersonName() {
        return this.createPersonName;
    }
    
    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
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
   








}
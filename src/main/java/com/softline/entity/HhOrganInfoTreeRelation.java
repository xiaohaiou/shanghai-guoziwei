package com.softline.entity;

/**
 * HhOrganInfoTreeRelation entity. @author MyEclipse Persistence Tools
 */

public class HhOrganInfoTreeRelation implements java.io.Serializable {

	// Fields
	private HhOrganInfoTreeRelationId id;
	private String vcOrganId;
	private String vcParentId;
	private String cflag;
	private Integer ishowOrder;
	private String vcFullName;
	private String vcShortName;
	private String vcshoworder;
	private String createDate;
	private String createPersonId;
	private String createPersonName;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer bimaId;
	private String hrID;
	private Integer status;
	private String responsiblePersonName;
	private String vcEmployeeID;	
	private String responsiblePersonEmail;	
	
	// Constructors
	/** default constructor */
	public HhOrganInfoTreeRelation() {
		id=new HhOrganInfoTreeRelationId();
	}

	/** minimal constructor */
	public HhOrganInfoTreeRelation(HhOrganInfoTreeRelationId id) {
		this.id = id;
	}

	/** full constructor */
	public HhOrganInfoTreeRelation(HhOrganInfoTreeRelationId id,
			String vcOrganId, String vcParentId, String cflag,
			Integer ishowOrder, String vcFullName, String vcShortName,
			String vcshoworder, String createDate, String createPersonId,
			String createPersonName, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate,
			String responsiblePersonName,String vcEmployeeID,
			String responsiblePersonEmail) {
		this.id = id;
		this.vcOrganId = vcOrganId;
		this.vcParentId = vcParentId;
		this.cflag = cflag;
		this.ishowOrder = ishowOrder;
		this.vcFullName = vcFullName;
		this.vcShortName = vcShortName;
		this.vcshoworder = vcshoworder;
		this.createDate = createDate;
		this.createPersonId = createPersonId;
		this.createPersonName = createPersonName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.responsiblePersonName = responsiblePersonName;
		this.vcEmployeeID = vcEmployeeID;
		this.responsiblePersonEmail = responsiblePersonEmail;
	}

	// Property accessors

	public HhOrganInfoTreeRelationId getId() {
		return this.id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setId(HhOrganInfoTreeRelationId id) {
		this.id = id;
	}

	public String getVcOrganId() {
		return this.vcOrganId;
	}

	public void setVcOrganId(String vcOrganId) {
		this.vcOrganId = vcOrganId;
	}

	public String getVcParentId() {
		return this.vcParentId;
	}

	public void setVcParentId(String vcParentId) {
		this.vcParentId = vcParentId;
	}

	public String getCflag() {
		return this.cflag;
	}

	public void setCflag(String cflag) {
		this.cflag = cflag;
	}

	public Integer getIshowOrder() {
		return this.ishowOrder;
	}

	public void setIshowOrder(Integer ishowOrder) {
		this.ishowOrder = ishowOrder;
	}

	public String getVcFullName() {
		return this.vcFullName;
	}

	public void setVcFullName(String vcFullName) {
		this.vcFullName = vcFullName;
	}

	public String getVcShortName() {
		return this.vcShortName;
	}

	public void setVcShortName(String vcShortName) {
		this.vcShortName = vcShortName;
	}

	public String getVcshoworder() {
		return this.vcshoworder;
	}

	public void setVcshoworder(String vcshoworder) {
		this.vcshoworder = vcshoworder;
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

	 public Integer getBimaId() {
		return bimaId;
	}

	public void setBimaId(Integer bimaId) {
		this.bimaId = bimaId;
	}

	public String getHrID() {
		return hrID;
	}

	public void setHrID(String hrID) {
		this.hrID = hrID;
	}

	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	public String getVcEmployeeID() {
		return vcEmployeeID;
	}

	public void setVcEmployeeID(String vcEmployeeID) {
		this.vcEmployeeID = vcEmployeeID;
	}

	public String getResponsiblePersonEmail() {
		return responsiblePersonEmail;
	}

	public void setResponsiblePersonEmail(String responsiblePersonEmail) {
		this.responsiblePersonEmail = responsiblePersonEmail;
	}

	// 覆写equals方法
    public boolean equals (Object obj){
        // 地址相等，则肯定是同一个对象
        if(this==obj){
            return true;
        }
        // 类型不同，则肯定不是同一类对象
        if(!(obj instanceof HhOrganInfoTreeRelation)){
            return false;
        } 
        // 类型相同，向下转型
        HhOrganInfoTreeRelation per=(HhOrganInfoTreeRelation) obj;
        // 如果两个对象的姓名和性别相同，则是同一个人
        if(this.getId().getNnodeId().equals(per.getId().getNnodeId())&& this.getId().getType().equals(per.getId().getType()))
            return true;
        return false;
    }
}
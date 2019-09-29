package com.softline.entity.bimr;

/**
 * BimrRiskEventRelevance entity. @author MyEclipse Persistence Tools
 */

public class BimrRiskEventRelevance implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer eventid;
	private Integer leveloneid;
	private String levelonename;
	private Integer leveltwoid;
	private String leveltwoname;
	private Integer levelthreeid;
	private String levelthreename;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	private Integer status;
	 /**
     * 是否删除.
     */
    private Integer isDel;
    
    private String dataauth;
    private String companyId;
	// Constructors

	/** default constructor */
	public BimrRiskEventRelevance() {
	}

	/** full constructor */
	public BimrRiskEventRelevance(Integer eventid, Integer leveloneid,
			String levelonename, Integer leveltwoid, String leveltwoname,
			Integer levelthreeid, String levelthreename,
			String createPersonName, String createPersonId, String createDate,
			String auditorPersonName, String auditorPersonId,
			String auditorDate, Integer status,Integer isDel) {
		this.eventid = eventid;
		this.leveloneid = leveloneid;
		this.levelonename = levelonename;
		this.leveltwoid = leveltwoid;
		this.leveltwoname = leveltwoname;
		this.levelthreeid = levelthreeid;
		this.levelthreename = levelthreename;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
		this.status = status;
		this.isDel = isDel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEventid() {
		return this.eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public Integer getLeveloneid() {
		return this.leveloneid;
	}

	public void setLeveloneid(Integer leveloneid) {
		this.leveloneid = leveloneid;
	}

	public String getLevelonename() {
		return this.levelonename;
	}

	public void setLevelonename(String levelonename) {
		this.levelonename = levelonename;
	}

	public Integer getLeveltwoid() {
		return this.leveltwoid;
	}

	public void setLeveltwoid(Integer leveltwoid) {
		this.leveltwoid = leveltwoid;
	}

	public String getLeveltwoname() {
		return this.leveltwoname;
	}

	public void setLeveltwoname(String leveltwoname) {
		this.leveltwoname = leveltwoname;
	}

	public Integer getLevelthreeid() {
		return this.levelthreeid;
	}

	public void setLevelthreeid(Integer levelthreeid) {
		this.levelthreeid = levelthreeid;
	}

	public String getLevelthreename() {
		return this.levelthreename;
	}

	public void setLevelthreename(String levelthreename) {
		this.levelthreename = levelthreename;
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

	public String getAuditorPersonName() {
		return this.auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorPersonId() {
		return this.auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorDate() {
		return this.auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getDataauth() {
		return dataauth;
	}

	public void setDataauth(String dataauth) {
		this.dataauth = dataauth;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	

}
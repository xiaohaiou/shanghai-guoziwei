package com.softline.entity.bimr;

/**
 * BimrRiskEventAdoptstrategy entity. @author MyEclipse Persistence Tools
 */

public class BimrRiskEventAdoptstrategy implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer eventId;
	private Integer adoptStrategy;
	private String adoptStrategyname;
	private String planfinishtime;
	private String responsibleCompName;
	private String nowfinishsituation;
	private String responsibleCompId;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer isDel;

	// Constructors

	/** default constructor */
	public BimrRiskEventAdoptstrategy() {
	}

	/** minimal constructor */
	public BimrRiskEventAdoptstrategy(Integer isDel) {
		this.isDel = isDel;
	}

	/** full constructor */
	public BimrRiskEventAdoptstrategy(Integer eventId, Integer adoptStrategy,
			String adoptStrategyname, String planfinishtime,
			String responsibleCompName, String nowfinishsituation,
			String responsibleCompId, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, Integer isDel) {
		this.eventId = eventId;
		this.adoptStrategy = adoptStrategy;
		this.adoptStrategyname = adoptStrategyname;
		this.planfinishtime = planfinishtime;
		this.responsibleCompName = responsibleCompName;
		this.nowfinishsituation = nowfinishsituation;
		this.responsibleCompId = responsibleCompId;
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

	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getAdoptStrategy() {
		return this.adoptStrategy;
	}

	public void setAdoptStrategy(Integer adoptStrategy) {
		this.adoptStrategy = adoptStrategy;
	}

	public String getAdoptStrategyname() {
		return this.adoptStrategyname;
	}

	public void setAdoptStrategyname(String adoptStrategyname) {
		this.adoptStrategyname = adoptStrategyname;
	}

	public String getPlanfinishtime() {
		return this.planfinishtime;
	}

	public void setPlanfinishtime(String planfinishtime) {
		this.planfinishtime = planfinishtime;
	}

	public String getResponsibleCompName() {
		return this.responsibleCompName;
	}

	public void setResponsibleCompName(String responsibleCompName) {
		this.responsibleCompName = responsibleCompName;
	}

	public String getNowfinishsituation() {
		return this.nowfinishsituation;
	}

	public void setNowfinishsituation(String nowfinishsituation) {
		this.nowfinishsituation = nowfinishsituation;
	}

	public String getResponsibleCompId() {
		return this.responsibleCompId;
	}

	public void setResponsibleCompId(String responsibleCompId) {
		this.responsibleCompId = responsibleCompId;
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

}
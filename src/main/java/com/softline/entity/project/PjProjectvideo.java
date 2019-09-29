package com.softline.entity.project;

/**
 * PjProjectvideo entity. @author MyEclipse Persistence Tools
 */

public class PjProjectvideo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pjId;
	private String pjVideoName;
	private String pjVideoPath;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String createDate;
	
	private String pjVideoUuid; // 视频文件UUID
	private Integer videoStatus;// 视频文件审核状态

	// Constructors

	/** default constructor */
	public PjProjectvideo() {
	}

	/** minimal constructor */
	public PjProjectvideo(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public PjProjectvideo(Integer pjId, String pjVideoName, String pjVideoPath,
			Integer isdel, String createPersonName, String createPersonId,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String createDate) {
		this.pjId = pjId;
		this.pjVideoName = pjVideoName;
		this.pjVideoPath = pjVideoPath;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.createDate = createDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPjId() {
		return this.pjId;
	}

	public void setPjId(Integer pjId) {
		this.pjId = pjId;
	}

	public String getPjVideoName() {
		return this.pjVideoName;
	}

	public void setPjVideoName(String pjVideoName) {
		this.pjVideoName = pjVideoName;
	}

	public String getPjVideoPath() {
		return this.pjVideoPath;
	}

	public void setPjVideoPath(String pjVideoPath) {
		this.pjVideoPath = pjVideoPath;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPjVideoUuid() {
		return pjVideoUuid;
	}

	public void setPjVideoUuid(String pjVideoUuid) {
		this.pjVideoUuid = pjVideoUuid;
	}

	public Integer getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(Integer videoStatus) {
		this.videoStatus = videoStatus;
	}
	
	

}
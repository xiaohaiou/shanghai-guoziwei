package com.softline.entity;

/**
 * SysExamine entity. @author MyEclipse Persistence Tools
 */

public class SysExamine implements java.io.Serializable {

	// Fields

	private Integer id;
	private String examinestr;
	private Integer examresult;
	private Integer examentityid;
	private Integer examKind;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;

	// Constructors

	private String examresultName;
	
	/** default constructor */
	public SysExamine() {
	}

	/** minimal constructor */
	public SysExamine(Integer examresult, Integer examentityid,
			Integer examKind, Integer isdel) {
		this.examresult = examresult;
		this.examentityid = examentityid;
		this.examKind = examKind;
		this.isdel = isdel;
	}

	/** full constructor */
	public SysExamine(String examinestr, Integer examresult,
			Integer examentityid, Integer examKind, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		this.examinestr = examinestr;
		this.examresult = examresult;
		this.examentityid = examentityid;
		this.examKind = examKind;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getExamresultName() {
		return examresultName;
	}

	public void setExamresultName(String examresultName) {
		this.examresultName = examresultName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExaminestr() {
		return this.examinestr;
	}

	public void setExaminestr(String examinestr) {
		this.examinestr = examinestr;
	}

	public Integer getExamresult() {
		return this.examresult;
	}

	public void setExamresult(Integer examresult) {
		this.examresult = examresult;
	}

	public Integer getExamentityid() {
		return this.examentityid;
	}

	public void setExamentityid(Integer examentityid) {
		this.examentityid = examentityid;
	}

	public Integer getExamKind() {
		return this.examKind;
	}

	public void setExamKind(Integer examKind) {
		this.examKind = examKind;
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

}
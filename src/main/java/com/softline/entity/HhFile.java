package com.softline.entity;

/**
 * HhFile entity. @author MyEclipse Persistence Tools
 */

public class HhFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer typeId;
	private Integer releaseId;
	private String fileName;
	private String filePath;
	private String fileRemark;
	private String fileUuid;

	// Constructors

	/** default constructor */
	public HhFile() {
	}

	/** full constructor */
	public HhFile(Integer typeId, Integer releaseId, String fileName,
			String filePath, String fileRemark, String fileUuid) {
		this.typeId = typeId;
		this.releaseId = releaseId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileRemark = fileRemark;
		this.fileUuid = fileUuid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getReleaseId() {
		return this.releaseId;
	}

	public void setReleaseId(Integer releaseId) {
		this.releaseId = releaseId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileRemark() {
		return this.fileRemark;
	}

	public void setFileRemark(String fileRemark) {
		this.fileRemark = fileRemark;
	}

	public String getFileUuid() {
		return this.fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

}
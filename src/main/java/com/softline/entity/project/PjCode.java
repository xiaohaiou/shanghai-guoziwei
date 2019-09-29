package com.softline.entity.project;

/**
 * PjCode entity. @author MyEclipse Persistence Tools
 */

public class PjCode implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer codeType;
	private String codeNo;
	private String codeName;

	// Constructors

	/** default constructor */
	public PjCode() {
	}

	/** full constructor */
	public PjCode(Integer codeType, String codeNo, String codeName) {
		this.codeType = codeType;
		this.codeNo = codeNo;
		this.codeName = codeName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodeType() {
		return this.codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}

	public String getCodeNo() {
		return this.codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

}
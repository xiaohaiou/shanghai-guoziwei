package com.softline.entity.settingCenter;

/**
 * HhPagecode entity. @author MyEclipse Persistence Tools
 */

public class PortalHhPagecode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer sysId;
	private Integer pageId;
	private Integer pageNum;
	private String code;
	private String codeName;
	private Integer seqNo;
	private Integer isDel;
	
	//功能注册对应模板名称（用于列表页面显示）
	private Integer modelId;
	private String modelName;
	
	private String sysName;
	//功能注册对应页面名称（用于列表页面显示）
	private String pageName;
	
	// Constructors

	/** default constructor */
	public PortalHhPagecode() {
	}

	/** full constructor */
	public PortalHhPagecode(Integer sysId, Integer pageId, Integer pageNum,
			String code, String codeName, Integer seqNo, Integer isDel, String sysName, String pageName, Integer modelId, String modelName) {
		this.sysId = sysId;
		this.pageId = pageId;
		this.pageNum = pageNum;
		this.code = code;
		this.codeName = codeName;
		this.seqNo = seqNo;
		this.isDel = isDel;
		this.sysName = sysName;
		this.pageName = pageName;
		this.modelId = modelId;
		this.modelName = modelName;
	}

	// Property accessors
	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
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

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getPageNum() {
		return this.pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Integer getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
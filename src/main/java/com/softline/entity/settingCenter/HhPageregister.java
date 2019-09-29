package com.softline.entity.settingCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * HhPageregister entity. @author MyEclipse Persistence Tools
 */

public class HhPageregister implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer sysId;
	private String pageNum;
	private String pageName;
	private String pageDescription;
	private Integer isDel;
	private String creator;
	private String createTime;
	private String modifier;
	private String modifyTime;
	//所属模板Id
	private Integer modelId;
	//页面注册对应模板名称（用于列表页面显示）
	private String modelName;
	
	//页面注册对应系统名称（用于列表页面显示）
	private String sysName;
	
	private Integer pageType;
	
	private List<PortalHhPagecode> codeList = new ArrayList<PortalHhPagecode>();

	// Constructors

	/** default constructor */
	public HhPageregister() {
	}

	/** full constructor */
	public HhPageregister(Integer sysId, String pageNum, String pageName,
			String pageDescription, Integer isDel, String creator,
			String createTime, String modifier, String modifyTime, String sysName, Integer modelId, String modelName) {
		this.sysId = sysId;
		this.pageNum = pageNum;
		this.pageName = pageName;
		this.pageDescription = pageDescription;
		this.isDel = isDel;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.sysName = sysName;
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

	public String getPageNum() {
		return this.pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageDescription() {
		return this.pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<PortalHhPagecode> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<PortalHhPagecode> codeList) {
		this.codeList = codeList;
	}

	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}
	
	

}
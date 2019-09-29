package com.softline.entity.settingCenter;

import java.util.ArrayList;
import java.util.List;

public class HhModelRegister {
	private Integer id;
	private String modelNum;
	private String modelName;
	private String modelDescription;
	private Integer isDel;
	private String creator;
	private String createTime;
	private String modifier;
	private String modifyTime;
	private String modelUrlPage;
	private Integer sysId;
	private String sysName;
	
	private List<HhPageregister> pageList = new ArrayList<HhPageregister>();
	
	public HhModelRegister() {
		super();
	}
	
	public HhModelRegister(Integer id, String modelNum, String modelName,
			String modelDescription, Integer isDel, String creator,
			String createTime, String modifier, String modifyTime,
			String modelUrlPage, Integer sysId, String sysName, List<HhPageregister> pageList) {
		super();
		this.id = id;
		this.modelNum = modelNum;
		this.modelName = modelName;
		this.modelDescription = modelDescription;
		this.isDel = isDel;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.modelUrlPage = modelUrlPage;
		this.sysId = sysId;
		this.sysName = sysName;
		this.pageList = pageList;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModelUrlPage() {
		return modelUrlPage;
	}

	public void setModelUrlPage(String modelUrlPage) {
		this.modelUrlPage = modelUrlPage;
	}

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public List<HhPageregister> getPageList() {
		return pageList;
	}

	public void setPageList(List<HhPageregister> pageList) {
		this.pageList = pageList;
	}

}

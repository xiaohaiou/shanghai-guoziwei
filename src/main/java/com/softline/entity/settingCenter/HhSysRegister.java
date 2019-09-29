package com.softline.entity.settingCenter;

import java.util.ArrayList;
import java.util.List;

public class HhSysRegister {
	private Integer id;
	private String sysNum;
	private String sysName;
	private String sysDescription;
	private Integer isDel;
	private String creator;
	private String createTime;
	private String modifier;
	private String modifyTime;
	private String sysUrlPage;
	
	private List<HhModelRegister> modelList = new ArrayList<HhModelRegister>();
	
	public HhSysRegister() {
		super();
	}
	public HhSysRegister(Integer id, String sysNum, String sysName,
			String sysDescription, Integer isDel, String creator,
			String createTime, String modifier, String modifyTime,String sysUrlPage) {
		super();
		this.id = id;
		this.sysNum = sysNum;
		this.sysName = sysName;
		this.sysDescription = sysDescription;
		this.isDel = isDel;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.sysUrlPage = sysUrlPage;
	}
	
	public String getSysUrlPage() {
		return sysUrlPage;
	}
	public void setSysUrlPage(String sysUrlPage) {
		this.sysUrlPage = sysUrlPage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSysNum() {
		return sysNum;
	}
	public void setSysNum(String sysNum) {
		this.sysNum = sysNum;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getSysDescription() {
		return sysDescription;
	}
	public void setSysDescription(String sysDescription) {
		this.sysDescription = sysDescription;
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
	public List<HhModelRegister> getModelList() {
		return modelList;
	}
	public void setModelList(List<HhModelRegister> modelList) {
		this.modelList = modelList;
	}
}

package com.softline.entity.settingCenter;

public class HhModelUser {
	private Integer id;
	private Integer modelId;
	private String vcEmployeeId;
	
	public HhModelUser() {
		super();
	}
	public HhModelUser(Integer id, Integer modelId, String vcEmployeeId) {
		super();
		this.id = id;
		this.modelId = modelId;
		this.vcEmployeeId = vcEmployeeId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public String getVcEmployeeId() {
		return vcEmployeeId;
	}
	public void setVcEmployeeId(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}
	
	
}

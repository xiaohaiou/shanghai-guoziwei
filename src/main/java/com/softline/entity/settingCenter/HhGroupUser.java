package com.softline.entity.settingCenter;

public class HhGroupUser {
	private Integer id;
	private Integer groupId;
	private String vcEmployeeId;
	
	public HhGroupUser() {
		super();
	}
	public HhGroupUser(Integer id, Integer groupId, String vcEmployeeId) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.vcEmployeeId = vcEmployeeId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getVcEmployeeId() {
		return vcEmployeeId;
	}
	public void setVcEmployeeId(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}
	
	
}

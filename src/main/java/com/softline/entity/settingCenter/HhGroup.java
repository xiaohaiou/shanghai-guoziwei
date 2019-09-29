package com.softline.entity.settingCenter;


/**
 * HhPageregister entity. @author MyEclipse Persistence Tools
 */

public class HhGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String groupName;
	private String groupNum;
	private String creator;
	private Integer isDel;
	private String createTime;
	private String modifier;
	private String modifyTime;
	private String groupDescription;
	private Integer groupStatus;
	
	// Constructors

	/** default constructor */
	public HhGroup() {
	}
	
	/** full constructor */
	public HhGroup(Integer id, String groupName, String groupNum,
			String creator, Integer isDel, String createTime, String modifier,
			String modifyTime, String groupDescription, Integer groupStatus) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.groupNum = groupNum;
		this.creator = creator;
		this.isDel = isDel;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.groupDescription = groupDescription;
		this.groupStatus = groupStatus;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public Integer getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(Integer groupStatus) {
		this.groupStatus = groupStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
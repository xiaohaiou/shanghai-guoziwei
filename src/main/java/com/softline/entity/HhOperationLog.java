package com.softline.entity;

/**
 * HhOperationLog entity. @author MyEclipse Persistence Tools
 */

public class HhOperationLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String operationTime;
	private String description;
	private String model;

	// Constructors
	//用户姓名
	private String userName;
	// 登录次数
	private Integer totalcount;
	private String starttime;
	private String endtime;
	private String hrfullname;
	private String loginname;

	/** default constructor */
	public HhOperationLog() {
	}

	/** full constructor */
	public HhOperationLog(String name, String operationTime,
			String description, String model, String userName) {
		this.name = name;
		this.operationTime = operationTime;
		this.description = description;
		this.model = model;
		this.userName = userName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getHrfullname() {
		return hrfullname;
	}

	public void setHrfullname(String hrfullname) {
		this.hrfullname = hrfullname;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

}
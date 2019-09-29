package com.softline.entityTemp;


public class BimrRiskEventSecondTop {

	private Integer id;
	
	private String relevanceTime;//关联时间
	
	private String oneName;//一级风险目录名称
	
	private Integer oneid;
	
	private String secondName;//二级风险目录名称
	
	private Integer secondid;
	
	private String secondDefinition;//风险描述
	
	private String secondAnalyse;//风险成因分析
	
	private Integer count;//关联风险事件(条)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRelevanceTime() {
		return relevanceTime;
	}

	public void setRelevanceTime(String relevanceTime) {
		this.relevanceTime = relevanceTime;
	}

	public String getOneName() {
		return oneName;
	}

	public void setOneName(String oneName) {
		this.oneName = oneName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getSecondDefinition() {
		return secondDefinition;
	}

	public void setSecondDefinition(String secondDefinition) {
		this.secondDefinition = secondDefinition;
	}

	public String getSecondAnalyse() {
		return secondAnalyse;
	}

	public void setSecondAnalyse(String secondAnalyse) {
		this.secondAnalyse = secondAnalyse;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getOneid() {
		return oneid;
	}

	public void setOneid(Integer oneid) {
		this.oneid = oneid;
	}

	public Integer getSecondid() {
		return secondid;
	}

	public void setSecondid(Integer secondid) {
		this.secondid = secondid;
	}
	
	
	
}
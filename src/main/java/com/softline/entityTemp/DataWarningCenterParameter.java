package com.softline.entityTemp;

public class DataWarningCenterParameter {
	
	private int id;
	private String sql_label;
	private String page_author;
	private String model_id;
	private String model_name;
	private String url;
	private String warnTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSql_label() {
		return sql_label;
	}
	public void setSql_label(String sql_label) {
		this.sql_label = sql_label;
	}
	public String getPage_author() {
		return page_author;
	}
	public void setPage_author(String page_author) {
		this.page_author = page_author;
	}
	public String getModel_id() {
		return model_id;
	}
	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWarnTime() {
		return warnTime;
	}
	public void setWarnTime(String warnTime) {
		this.warnTime = warnTime;
	}
	@Override
	public String toString() {
		return "DataWarningCenterParameter [id=" + id + ", sql_label="
				+ sql_label + ", page_author=" + page_author + ", model_id="
				+ model_id + ", model_name=" + model_name + ", url=" + url
				+ ", warnTime=" + warnTime + "]";
	}
		
	
}

package com.softline.entityTemp;

public class ReportIndexAndData {
	private String indexRowID;
	private String indexColID;
	private String value;
	public String getIndexRowID() {
		return indexRowID;
	}
	public void setIndexRowID(String indexRowID) {
		this.indexRowID = indexRowID;
	}
	public String getIndexColID() {
		return indexColID;
	}
	public void setIndexColID(String indexColID) {
		this.indexColID = indexColID;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ReportIndexAndData(String indexRowID, String indexColID, String value) {
		super();
		this.indexRowID = indexRowID;
		this.indexColID = indexColID;
		this.value = value;
	}
	public ReportIndexAndData() {
		super();
	}
	
}

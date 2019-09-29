package com.softline.entity;


public class ReportFormsUnFilledCompany implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nNodeID;
	private int isdel;
	private int isRemind;
	private String lastRemindTime;
	private String updatetime;
	private int formKind;
	
	public String getnNodeID() {
		return nNodeID;
	}
	public void setnNodeID(String nNodeID) {
		this.nNodeID = nNodeID;
	}
	public Integer getIsdel() {
		return isdel;
	}
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	public Integer getIsRemind() {
		return isRemind;
	}
	public void setIsRemind(Integer isRemind) {
		this.isRemind = isRemind;
	}
	public String getLastRemindTime() {
		return lastRemindTime;
	}
	public void setLastRemindTime(String lastRemindTime) {
		this.lastRemindTime = lastRemindTime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getFormKind() {
		return formKind;
	}
	public void setFormKind(Integer formKind) {
		this.formKind = formKind;
	}
}

package com.softline.common;

public class CompanyTreeState {

	public Boolean opened;
	public Boolean selected;
	
	public CompanyTreeState() {
		super();
	}
	public CompanyTreeState(Boolean opened, Boolean selected) {
		super();
		this.opened = opened;
		this.selected = selected;
	}
	public Boolean getOpened() {
		return opened;
	}
	public void setOpened(Boolean opened) {
		this.opened = opened;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
}

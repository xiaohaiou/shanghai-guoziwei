package com.softline.common;

import java.util.ArrayList;
import java.util.List;

public class BylawInfoTree {
	private String id;
	private String text;
	private String openStatus;//展示
	private String type;//企业0 规章制度1
	private List<BylawInfoTree> children =  new ArrayList<BylawInfoTree>();
	
	public BylawInfoTree(){}
	
	public BylawInfoTree(String id,String text,String openStatus,String type,List<BylawInfoTree> children){
		this.id = id;
		this.text = text;
		this.openStatus = openStatus;
		this.type = type;
		this.children = children;
				
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getOpenStatus() {
		return openStatus;
	}
	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<BylawInfoTree> getChildren() {
		return children;
	}
	public void setChildren(List<BylawInfoTree> children) {
		this.children = children;
	}
	
	
}

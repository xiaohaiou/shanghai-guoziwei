package com.softline.common;

import java.util.ArrayList;
import java.util.List;

public class CompanyTree {

	public String name;
	public String text;
	public String id;
	public String pId;
	public String icon;
	public String vcOrganID;
	public List<CompanyTree> children =  new ArrayList<CompanyTree>();
	public CompanyTreeState state = new CompanyTreeState();
	public Integer businessFormat;
	public CompanyTree() {
		super();
	}
	public CompanyTree(String text, String id, String icon,
			List<CompanyTree> children, CompanyTreeState state,Integer businessFormat) {
		super();
		this.text = text;
		this.id = id;
		this.icon = icon;
		this.children = children;
		this.state = state;
		this.businessFormat=businessFormat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.text=name;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getVcOrganID() {
		return vcOrganID;
	}
	public void setVcOrganID(String vcOrganID) {
		this.vcOrganID = vcOrganID;
	}
	public List<CompanyTree> getChildren() {
		return children;
	}
	public void setChildren(List<CompanyTree> children) {
		this.children = children;
	}
	public CompanyTree(String name, String id, String pId, String vcOrganID,
			List<CompanyTree> children) {
		super();
		this.text=name;
		this.name = name;
		this.id = id;
		this.pId = pId;
		this.vcOrganID = vcOrganID;
		this.children = children;
	}
	
	public CompanyTree(String name, String id, String pId,List<CompanyTree> children)
	{
		this.text=name;
		this.name = name;
		this.id = id;
		this.pId = pId;
		this.children = children;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}

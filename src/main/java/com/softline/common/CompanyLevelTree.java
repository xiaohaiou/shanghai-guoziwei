package com.softline.common;

import java.util.ArrayList;
import java.util.List;

public class CompanyLevelTree {

	public String name;
	public String text;
	public String id;
	public String icon;
	public String vcOrganID;
	public List<CompanyLevelTree> children =  new ArrayList<CompanyLevelTree>();
	public CompanyTreeState state = new CompanyTreeState();
	public Integer bimaID;
	public CompanyLevelTree() {
		super();
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
		return icon;
	}
	public void setpId(String pId) {
		this.icon = pId;
	}
	public String getVcOrganID() {
		return vcOrganID;
	}
	public void setVcOrganID(String vcOrganID) {
		this.vcOrganID = vcOrganID;
	}
	public List<CompanyLevelTree> getChildren() {
		return children;
	}
	public void setChildren(List<CompanyLevelTree> children) {
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
	public CompanyTreeState getState() {
		return state;
	}
	public void setState(CompanyTreeState state) {
		this.state = state;
	}
	
	public Integer getBimaID() {
		return bimaID;
	}
	public void setBimaID(Integer bimaID) {
		this.bimaID = bimaID;
	}
	public CompanyLevelTree(String name, String id, String pId, String vcOrganID,
			List<CompanyLevelTree> children,Integer bimaID) {
		super();
		this.text=name;
		this.name = name;
		this.id = id;
		this.icon = pId;
		this.vcOrganID = vcOrganID;
		this.children = children;
		this.bimaID=bimaID;
	}
	
	public CompanyLevelTree(String name, String id, String pId,List<CompanyLevelTree> children,Integer bimaID)
	{
		this.text=name;
		this.name = name;
		this.id = id;
		this.icon = pId;
		this.children = children;
		this.bimaID=bimaID;
	}
}

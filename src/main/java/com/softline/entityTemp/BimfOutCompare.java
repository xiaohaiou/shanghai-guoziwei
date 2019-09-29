package com.softline.entityTemp;//

import java.util.ArrayList;
import java.util.List;

public class BimfOutCompare {
	
	
	
	private List<BimfOutCompareData> outdata;
	
	private List<BimfOutCompareData> publicdata;

	
	

	public List<BimfOutCompareData> getOutdata() {
		return outdata;
	}

	public void setOutdata(List<BimfOutCompareData> outdata) {
		this.outdata = outdata;
	}

	public List<BimfOutCompareData> getPublicdata() {
		return publicdata;
	}

	public void setPublicdata(List<BimfOutCompareData> publicdata) {
		this.publicdata = publicdata;
	}

	public BimfOutCompare() {
		super();
		outdata=new ArrayList<BimfOutCompareData>();
		publicdata=new ArrayList<BimfOutCompareData>();
	}



}

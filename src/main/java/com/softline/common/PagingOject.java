package com.softline.common;

import java.util.List;

public class PagingOject<T> {

	private int startPage;
	private int pageSize;
	private long recordCount;
	private List<T> reasult;
	
	public PagingOject(int s,int p,long r ,List<T> d)
	{
		startPage=s;
		pageSize=p;
		recordCount=r;
		reasult=d;
	}
	
	public PagingOject()
	{
	
	}

	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public List<T> getReasult() {
		return reasult;
	}
	public void setReasult(List<T> data) {
		this.reasult = data;
	}
	
	
}

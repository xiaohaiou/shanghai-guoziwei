package com.softline.util;

import java.util.List;

public class MsgPage {
	// 结果集
	@SuppressWarnings("rawtypes")
    private List list;
    
	private int totalRecords; // 总记录数
	private int totalPage; // 总页数
	private int pageNum; // 当前页
	private int pageSize;// 每页记录数

    /**
     * @return 总页数
     * */
    public int countTotalPage(int totalRecords, int pageSize){
    	int totalPages = totalRecords%pageSize==0 ? totalRecords/pageSize : totalRecords/pageSize+1;
        return totalPages;
    }

    /**
     * 计算当前页开始记录
     * @param pageSize 每页记录数
     * @param currentPage 当前第几页
     * @return 当前页开始记录号
     */
    public int countOffset(int currentPage,int pageSize){
        int offset = pageSize*(currentPage-1);
        return offset;
    }
    @SuppressWarnings("rawtypes")
    public List getList() {
        return list;
    }
    @SuppressWarnings("rawtypes")
    public void setList(List list) {
        this.list = list;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}

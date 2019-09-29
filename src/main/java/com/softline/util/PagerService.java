package com.softline.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PagerService {
	
	public static Pager getPager(String currentPage, String pagerMethod,
			int totalRows) {
		// 定义pager对象，用于传到页面
		Pager pager = new Pager(totalRows);
		// 如果当前页号为空，表示为首次查询该页
		// 如果不为空，则刷新pager对象，输入当前页号等信息
		if (currentPage != null) {
			pager.refresh(Integer.parseInt(currentPage));
		}
		// 获取当前执行的方法，首页，前一页，后一页，尾页。
		if (pagerMethod != null) {
			if (pagerMethod.equals("first")) {
				pager.first();
			} else if (pagerMethod.equals("previous")) {
				pager.previous();
			} else if (pagerMethod.equals("next")) {
				pager.next();
			} else if (pagerMethod.equals("last")) {
				pager.last();
			}
		}
		return pager;
	}
	
	public static Pager getPager(int currentPage, int totalRows) {
		// 定义pager对象，用于传到页面
				Pager pager = new Pager(totalRows);
				pager.setCurrentPage(currentPage);
				
				return pager;
	}
	
	
	
	public static int getPageSize()
	{
		return	Pager.GetPageSize();
	}
	
	public static int getCurrentPage(HttpServletRequest request)
	{
		return getCurrentPage( request.getParameter("currentPage"), request.getParameter("pagerMethod"),  request.getParameter("totalPages"));
	}
	public static int getCurrentPage(String currentPage, String pagerMethod ,String  totalPages)
	{
		int currpage=1;
		if( org.apache.commons.lang.StringUtils.isEmpty(currentPage))
			return 1;
		 currpage = Integer.parseInt(currentPage);
		if (!org.apache.commons.lang.StringUtils.isEmpty(pagerMethod)) {
			if (pagerMethod.equals("first")) {
				return 1;
			} else if (pagerMethod.equals("previous")) {
				if (currpage <= 1) {
					return 1;
				}
				currpage--;
				return currpage;
			} else if (pagerMethod.equals("next")) {
				currpage++;
				return currpage;
			} 
			else if (pagerMethod.equals("last")) {
				//pager.last();
				if(org.apache.commons.lang.StringUtils.isEmpty(totalPages))
					return 0;
				return Integer.parseInt(totalPages);
			}
		}
		return 1;
	}

	public static List<?> getPageContent(List<?> list, Pager pager) {
		if (list != null && list.size() > 0) {
			return list.subList(
					pager.getStartRow(),
					pager.getCurrentPage() < pager.getTotalPages() ? pager
							.getCurrentPage() * pager.getPageSize() : list
							.size());
		} else {
			return list;
		}
	}
}

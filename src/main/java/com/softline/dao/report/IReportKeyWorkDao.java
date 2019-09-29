package com.softline.dao.report;

import java.util.List;

import com.softline.entity.ReportKeywork;


public interface IReportKeyWorkDao {

	/**
	 * 获取年度重点工作数量
	 * @param entity
	 * @return
	 */
	public int getKeyWorkCount(ReportKeywork entity);
	
	/**
	 * 获取年度重点工作
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	public List<ReportKeywork> getKeyWork(ReportKeywork entity ,Integer offsize,Integer pagesize);
	
	public ReportKeywork getKeyworkbyID(Integer id);
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveKeyworkCheck(ReportKeywork entity);

	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean reportKeyworkCheck(ReportKeywork entity);
	
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportKeywork entity);
}

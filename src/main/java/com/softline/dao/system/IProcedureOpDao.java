package com.softline.dao.system;

import com.softline.dao.system.impl.ProcedureOpDao;
import com.softline.entity.HhProcedureOpRecord;

public interface IProcedureOpDao {
	/**
	 * 运行固化财务树存储过程
	 * @param time
	 * @return
	 */
	public HhProcedureOpRecord runGuahuaFinanceTreee(String time);
	
	/**
	 * 运行固化财务树存储过程
	 * @param time
	 * @return
	 */
	public HhProcedureOpRecord runGuahuaFinanceHistoryTreee(String time);
	
	/**
	 * 重点基建工程基本信息同步
	 * @return
	 */
	public HhProcedureOpRecord runProjectAdminSyn();
	
	/**
	 * 重点工基建工程同步
	 * @return
	 */
	public HhProcedureOpRecord runProjectVideoSyn();
}

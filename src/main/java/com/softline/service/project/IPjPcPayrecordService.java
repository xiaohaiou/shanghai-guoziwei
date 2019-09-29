package com.softline.service.project;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.project.PjPcPayrecord;
import com.softline.entity.project.PjPcPayrecordTemp;

public interface IPjPcPayrecordService {
	/**
	 * 根据临时的合同表得到临时的付款信息
	 * @param contractTempId
	 * @return
	 */
	List<PjPcPayrecordTemp> getPayrecordTemps(Integer contractTempId);
	
	/**
	 * 根据合同表得到付款信息
	 * @param contractId
	 * @return
	 */
	List<PjPcPayrecord> getPayrecords(Integer contractId);
	
	/**
	 * 保存临时合同付款记录
	 * @param user 创建人
	 * @param wkReportTemp 要保存的实体
	 * @param oldEntity 老的实体
	 * @return
	 */
	String savePayRecordTemp(HhUsers user,PjPcPayrecordTemp payRecord,PjPcPayrecordTemp oldEntity);
	
	
	/**
	 * 保存合同付款记录
	 * @param user 创建人
	 * @param payRecord要保存的实体
	 * @param oldEntity老的实体
	 * @return
	 */
	void savePayRecord(HhUsers user,PjPcPayrecord payRecord,PjPcPayrecord oldEntity);
}

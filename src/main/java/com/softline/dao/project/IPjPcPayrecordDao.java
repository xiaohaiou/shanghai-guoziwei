package com.softline.dao.project;

import java.util.List;

import com.softline.entity.project.PjPcPayrecord;
import com.softline.entity.project.PjPcPayrecordTemp;

public interface IPjPcPayrecordDao {
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
}

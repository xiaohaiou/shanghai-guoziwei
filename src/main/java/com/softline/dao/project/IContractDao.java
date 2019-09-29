package com.softline.dao.project;

import java.util.List;

import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjContractHistory;

public interface IContractDao {
	/**
	 * 查询项目对应的合同
	 * @param pjId 项目ID
	 * @param offset 分页开始位置
	 * @param pageSize 分页最大返回条数
	 * @return
	 */
	List<PjContract> getContracts(Integer pjId,Integer offset,Integer pageSize); 
	
	/**
	 * 
	 * @param pjId
	 * @return
	 */
	List<PjContract> getContracts(Integer pjId,Integer reportStatus);
	
	/**
	 * 根据合同ID查询历史
	 * @param contractId
	 * @return
	 */
	List<PjContractHistory> getContractHistories(Integer contractId);
}

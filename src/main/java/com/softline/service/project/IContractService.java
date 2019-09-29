package com.softline.service.project;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjContractHistory;
import com.softline.entity.project.PjContractTemp;

import com.softline.util.MsgPage;

public interface IContractService {
	
	/**
	 * 查询项目对应的合同
	 * @param pjId 项目ID
	 * @param offset 分页开始位置
	 * @param pageSize 分页最大返回条数
	 * @return
	 */
	MsgPage getContracts(Integer pjId,Integer curPageNum,Integer pageSize); 
	
	/**
	 * 保存临时合同记录
	 * @param user 创建人or修改人
	 * @param contractTemp 新的合同实体
	 * @param oldEntity 老的合同实体
	 * @return
	 */
	String saveContractTemp(HhUsers user,String payRecordIds,PjContractTemp contractTemp,PjContractTemp oldEntity);
	
	/**
	 * 删除临时合同，同是删除临时合同上的付款信息
	 * @param contractTempId临时合同ID
	 */
	void deleteContractTemp(Integer contractTempId);
	
	
	/**
	 * 保存合同信息
	 * @param user
	 * @param payRecordIds
	 * @param contract
	 * @param oldEntity
	 * @return
	 */
	void saveContract(HhUsers user,String payRecordIds,PjContract contract,PjContract oldEntity);
	

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
	
	/**
	 * 将重点基建工程的合同删除，删除时保存到历史记录中并将version+1,审核状态改为审核通过。
	 * 解决删除数据不能进行同步的问题。
	 * @param id 合同ID
	 * @param users 操作人
	 */
	void deleteContract(Integer id,HhUsers users);
}

package com.softline.dao.report;

import java.util.List;

import com.softline.entity.ReportReceivabledebt;
import com.softline.entity.ReportReceivabledebtinfo;

public interface IReceivableDebtDao {

	/**
	 * 通过ID查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportReceivabledebt getReceivabledebtbyID(Integer id);
	
	
	/**
	 * 获取总记录数
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReceivabledebtListCount(ReportReceivabledebt entity);
	
	
	/**
	 * 获取记录
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportReceivabledebt> getReceivabledebtList(ReportReceivabledebt entity ,Integer offsize,Integer pagesize); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReceivabledebtRepeatCheck(ReportReceivabledebt entity);
	
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportReceivabledebt entity);
	
	
	/**
	 * 删除子信息
	 * @param groupID
	 * @return
	 */
	public boolean deleteReceivabledebtinfo(String groupID);
	
	
	/**
	 * 保存子信息
	 * @param groupID
	 * @return
	 */
	public void saveReceivabledebtinfoitem(List<ReportReceivabledebtinfo> a);
	
	/**
	 * 查询子信息数量
	 * @param id 查询ID
	 * @return
	 */
	public Integer getReceivabledebtinfoCount(Integer id);
	
	
	/**
	 * 获取人事数据子信息
	 * @param groupID
	 * @return
	 */
	public List<ReportReceivabledebtinfo> getReceivabledebtinfoList(Integer groupID ,Integer offsize,Integer pagesize);
	
	
	/**
	 *   --------------------------------应收债权(内部)明细查询---------------------------------------
	 */
	
	/**
	 * 查询子信息数量
	 * @param id 查询ID
	 * @return
	 */
	public Integer getReceivabledebtinfoDetailCount(ReportReceivabledebtinfo entity);
	
	
	/**
	 * 获取人事数据子信息
	 * @param groupID
	 * @return
	 */
	public List<ReportReceivabledebtinfo> getReceivabledebtinfoDetailList(ReportReceivabledebtinfo entity,Integer offsize,Integer pagesize);
	
	/**
	 * 通过ID查询明细
	 * @param id 查询ID
	 * @return
	 */
	public ReportReceivabledebtinfo getReceivabledebtinfobyID(Integer id);
	
	
	/**
	 *   --------------------------------应收债权(内部)汇总查询---------------------------------------
	 */
	
	/**
	 * 查询子信息数量
	 * @param id 查询ID
	 * @return
	 */
	public Integer getReceivabledebtinfoCollectCount(ReportReceivabledebtinfo entity);
	
	
	/**
	 * 获取人事数据子信息
	 * @param groupID
	 * @return
	 */
	public List<Object> getReceivabledebtinfoCollectList(ReportReceivabledebtinfo entity,Integer offsize,Integer pagesize);
	
	
	/**
	 *   --------------------------------公司大额应收债权(内部)查询---------------------------------------
	 */
	
	/**
	 * 查询子信息数量
	 * @param id 查询ID
	 * @return
	 */
	public Integer getReceivabledebtinfoOrgCount(ReportReceivabledebtinfo entity);
	
	
	/**
	 * 获取人事数据子信息
	 * @param groupID
	 * @return
	 */
	public List<Object> getReceivabledebtinfoOrgList(ReportReceivabledebtinfo entity,Integer offsize,Integer pagesize);
	
	
	/**
	 *   --------------------------------超期外部应收账款无催收进展一览---------------------------------------
	 */
	
	
	/**
	 * 查询子信息数量
	 * @param id 查询ID
	 * @return
	 */
	public Integer getOverOutCount(ReportReceivabledebtinfo entity);
	
	
	/**
	 * 获取子信息
	 * @param groupID
	 * @return
	 */
	public List<Object> getOverOutList(ReportReceivabledebtinfo entity,Integer offsize,Integer pagesize);
	
}

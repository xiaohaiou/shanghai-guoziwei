package com.softline.dao.riskmanagement;

import java.util.List;

import com.softline.entity.HhBase;
import com.softline.entity.Purchase;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
/**
 * 
 * @author tch
 *
 */
public interface IPurchaseDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public Purchase getPurchase(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<Purchase> getPurchaseList(Purchase entity ,Integer offsize,Integer pagesize); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getPurchaseListCount(Purchase entity); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean savePurchaseRepeatCheck(Purchase entity);
}

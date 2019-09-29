package com.softline.dao.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.softline.common.Common;
import com.softline.entity.HrPersonOrganization;
import com.softline.entity.Purchase;
import com.softline.entity.HrPersongroup;
import com.softline.entity.HrPersonitem;
/**
 * 
 * @author tch
 *
 */
public interface IHrPersongroupDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public HrPersongroup getHrPersongroup(Integer id);
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public HrPersongroup getHrPersongroup(String org,int year,int month);
	
	
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<HrPersongroup> getHrPersongroupList(HrPersongroup entity ,Integer offsize,Integer pagesize); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getHrPersongroupListCount(HrPersongroup entity); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveHrPersongroupRepeatCheck(HrPersongroup entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(HrPersongroup entity);
	
	
	/**
	 * 获取人事数据子信息
	 * @param groupID
	 * @return
	 */
	public List<HrPersonitem> getHrPersonitem(Integer groupID ,Integer offsize,Integer pagesize);
	
	/**
	 * 获取人事数据子信息数目
	 * @param groupID
	 * @return
	 */
	public Integer getHrPersonitemCount(Integer groupID);
	
	
	/**
	 * 保存人事数据子信息数目
	 * @param groupID
	 * @return
	 */
	public void saveHrPersonitem(List<HrPersonitem> a);
	
	
	/**
	 * 删除人事数据子信息
	 * @param groupID
	 * @return
	 */
	public boolean deleteHrPersonitem(String groupID);
	
	
	
	public List<HrPersongroup>  getpersonGroup(HrPersongroup personGroup1);
	
	/**
	 * 财务人员资质数据导出
	 */
public List<HrPersonitem>  getpersonItem(HrPersonitem personItem1);
	
	/**
	 * 财务人员资质数据导出
	 */
public List<HrPersonitem>  getpersonItemSh(HrPersonitem personItemSh2);

	/**
	 * 删除财务编制数据子信息
	 * @param groupID
	 * @return
	 */
	public boolean deleteHrPersonorganization(String groupID);

	/**
	 * 保存财务编制数据子信息数目
	 * @param groupID
	 * @return
	 */
	public void saveHrPersonOrganization(List<HrPersonOrganization> a);

	/**
	 * 获取财务编制表数据子信息数目
	 * @param groupID
	 * @return
	 */
	public Integer getHrPersonorganizationCount(Integer groupID);
	
	/**
	 * 获取人事数据子信息
	 * @param groupID
	 * @return
	 */
	public List<HrPersonOrganization> getHrPersonorganization(Integer groupID ,Integer offsize,Integer pagesize);


	public List<HrPersonitem> getHrPersonitem(HrPersongroup entity);


	public List<HrPersongroup> getHrPersongroup(HrPersongroup entity);


	public List<HrPersonitem>  getHrPersonitem(Integer groupID);


	public List<HrPersonOrganization> getHrPersonorganization(Integer groupID);
}

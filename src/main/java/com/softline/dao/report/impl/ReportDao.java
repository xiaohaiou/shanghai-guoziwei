package com.softline.dao.report.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.report.IReportDao;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportExportout;
import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsCell;
import com.softline.entity.ReportFormsGroup;
import com.softline.entity.ReportFormsOrganal;
import com.softline.entity.ReportFormsPattend;
import com.softline.entity.ReportFormsUnFilledCompany;
import com.softline.entity.ReportIndex;
import com.softline.entityTemp.ReportIndexAndData;
import com.softline.entityTemp.ReportQueryView;
import com.softline.entityTemp.Report_formsView;

@Repository(value = "reportDao")
public class ReportDao implements IReportDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//针对ReportFormsGroup 
	/**
	 * 校验ReportFormsGroup的保存
	 * @param entity
	 * @return
	 */
	public String checkReportFormsGroups(ReportFormsGroup entity){
		if(entity==null)
			return "";
		
		StringBuilder hql=new StringBuilder();
		if(entity.getNameID() != 52002){ //不为核算报表时
			hql.append(" select count(0) from report_forms_group where isdel=0 and id !="+(entity.getId()==null? "0":entity.getId())+" and type="+entity.getType()+" and nameID="+entity.getNameID()+" and time= '"+entity.getTime()+"' ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			Object a=query.uniqueResult();
			if(a!=null)
			{
				int count=Integer.parseInt(a.toString());
				if(count>0)
					return "在"+entity.getTime()+"年内已有为该种类的报表组,不能再添加";
			}
		}else{//核算报表
			hql.append(" select count(0) from report_forms_group where isdel=0 and id !="+(entity.getId()==null? "0":entity.getId())+" and type="+entity.getType()+" and nameID="+entity.getNameID()+" and time= '"+entity.getTime()+"' and month = '"+entity.getMonth()+"'");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			Object a=query.uniqueResult();
			if(a!=null)
			{
				int count=Integer.parseInt(a.toString());
				if(count>0)
					return "在"+entity.getTime()+"年"+entity.getMonth()+"月内已有为该种类的报表组,不能再添加";
			}
		}
		return "";
	}
	
	
	/**
	 * 删除报表组
	 * @param groupID 报表组ID
	 * @param user 操作人
	 */
	public void deleteReportFormsGroups(int groupID,HhUsers user)
	{
		ReportForms ReportFormsentity=new ReportForms();
		ReportFormsentity.setGroupId(groupID);
		List<ReportForms> formList= getReportFormsList(ReportFormsentity,null,null);
		StringBuilder formIDs=new StringBuilder();
		for (int i = 0; i < formList.size(); i++) {
			if(i==0)
				formIDs.append(formList.get(i).getId());
			else
			{
				formIDs.append(",");
				formIDs.append(formList.get(i).getId());
			}
		}		
		//删除报表
		deleteReportForms(formIDs.toString(),user);
		//删除报表组
		StringBuilder  hql = new StringBuilder();
		hql.append("update report_forms_group set isdel=1 ,"+createLastModifyString(user)+"  where id ="+groupID+" ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		query.executeUpdate();
	}
	/**
	 * 根据查询实体，获取一个报表组 (通过ID，或者属性、类型和时间的组合键能唯一确定)
	 * @param entity 查询实体
	 * @return
	 */
	public ReportFormsGroup getReportFormsGroup(ReportFormsGroup entity)
	{
		StringBuilder  hql = new StringBuilder();
		ReportFormsGroup returnData=new ReportFormsGroup();
		if(entity==null )
			return null;
		hql.append("from ReportFormsGroup s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getId()!=null)
			{
				hql.append(" and s.id="+entity.getId()+ " ");
			}
			if(entity.getNameID()!=null && Common.notEmpty(entity.getTime()) && entity.getType()!=null)
			{
				hql.append(" and s.nameID="+entity.getNameID()+ " ");
				hql.append(" and s.time='"+entity.getTime()+ "' ");
				hql.append(" and s.type='"+entity.getType()+ "' ");
			}
			if(Common.notEmpty(entity.getMonth())){
				if(entity.getNameID() == Base.reportgroup_adjust){//核算到月，预算到年
					hql.append(" and s.month='"+entity.getMonth()+ "' ");
				}
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		returnData= (ReportFormsGroup) query.uniqueResult();
		return returnData;
	}
	
	public Integer getLastGroup(Integer grouptype,Integer type)
	{
		if(grouptype==null)
			return null;
		StringBuilder  hql = new StringBuilder();
//      sql写法有误，无法查询到想要的结果 sht 2018-04-13发现		
//		hql.append("select id,max(time) from report_forms_group s where 1=1 and isdel=0 and nameID="+grouptype+ " and type="+type);	
//		修改如下：
		if(grouptype == Base.reportgroup_adjust){//核算
			hql.append("select s.id,s.time from report_forms_group s where s.isdel=0 and s.nameID=" + grouptype + " and s.type=" + type + " order by s.time desc,s.month desc  limit 1");
		}else{
			hql.append("select s.id,s.time from report_forms_group s where s.isdel=0 and s.nameID=" + grouptype + " and s.type=" + type + " and s.time =( ");
			hql.append("select max(t.time) from report_forms_group t where t.isdel=0 and t.nameID=" + grouptype + " and t.type=" + type + ")");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Object a= query.uniqueResult();
		if(a==null)
			return null;
		Object[] aa=(Object[]) a;
		return aa[0]==null ? null:Integer.parseInt(aa[0].toString());
	}
	
	public Integer getAdjustGroup(Integer year,Integer month){
		StringBuilder  hql = new StringBuilder();
		hql.append("select s.id,s.time from report_forms_group s where s.isdel=0 and s.nameID=" + Base.reportgroup_adjust + " and s.type=" + Base.reportgroup_type_simple + " and s.time = '"+year+"' and s.month = '"+month+"'");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Object a= query.uniqueResult();
		if(a==null)
			return null;
		Object[] aa=(Object[]) a;
		return aa[0]==null ? null:Integer.parseInt(aa[0].toString());
	}
	
	/**
	 * 根据查询实体，获取报表组列表
	 * @param entity 查询实体
	 * @return
	 */
	public  Integer getReportFormsGroupsListCount(ReportFormsGroup entity)
	{
		if(entity==null || !isValidDate(entity.getTime()) )
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFormsGroup s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getNameID()!=null)
			{
				hql.append(" and s.nameID = "+entity.getNameID()+ " ");
			}
			if(Common.notEmpty(entity.getTime()))
			{
				hql.append(" and s.time = '"+entity.getTime()+ "' ");
			}
			if(entity.getType()!=null)
			{
				hql.append(" and s.type = '"+entity.getType()+ "' ");
			}
			if(Common.notEmpty(entity.getMonth())){
				hql.append(" and s.month = '"+entity.getMonth()+ "' ");
			}
			
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	/**
	 * 根据查询实体，获取报表组列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportFormsGroup> getReportFormsGroupsList(ReportFormsGroup entity, Integer offset,Integer pageSize)
	{
		if(entity==null || !isValidDate(entity.getTime()) )
			return new ArrayList<ReportFormsGroup>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportFormsGroup s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getNameID()!=null)
			{
				hql.append(" and s.nameID = "+entity.getNameID()+ " ");
			}
			if(Common.notEmpty(entity.getTime()))
			{
				hql.append(" and s.time = '"+entity.getTime()+ "' ");
			}
			if(entity.getType()!=null)
			{
				hql.append(" and s.type = '"+entity.getType()+ "' ");
			}
			if(Common.notEmpty(entity.getMonth())){
				hql.append(" and s.month = '"+entity.getMonth()+ "' ");
			}
			
		}
		hql.append(" order by time desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}

	//针对ReportForms 
	
	/**
	 * 查询报表
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public List<ReportForms> getReportformsList(ReportForms entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportForms s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getFormkind()!=null)
			{
				hql.append(" and s.formkind = "+entity.getFormkind()+ " ");
			}
			if(entity.getGroupId()!=null)
			{
				hql.append(" and s.groupId = "+entity.getGroupId()+ "  ");
			}
			if(entity.getFre()!=null)
			{
				hql.append(" and s.fre = "+entity.getFre()+ " ");
			}
		}
		hql.append(" order by sort ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	
	/**
	 * 根据报表ID删除报表相关数据
	 * @param entity 查询实体
	 * @return
	 */
	public void deleteReportForms(String formsID,HhUsers user)
	{
		if(Common.notEmpty(formsID))
		{	//删除对应的样式
			deleteReportPattend(formsID,user);
			//删除对应的机构关联
			deleteReportOrganal(formsID,user);
			//删除对应的指标关联
			//deleteReportIndex(formsID,user);
			//删除对应的报表
			StringBuilder hql = new StringBuilder();
			hql.append("update report_forms set isdel=1 , "+createLastModifyString(user)+" where id in ("+formsID+") ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			query.executeUpdate();
		}
	}
	/**
	 * 根据查询实体，获取一个报表
	 * @param entity 查询实体
	 * @return
	 */
	public ReportForms getReportForms(ReportForms entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportForms s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getId()!=null)
			{
				hql.append(" and  s.id="+entity.getId()+ " ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportForms) query.uniqueResult();
	}
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportForms> getReportFormsList(ReportForms entity , Integer offset,Integer pageSize)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportForms s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getFormkind()!=null)
			{
				hql.append(" and s.formkind = "+entity.getFormkind()+ " ");
			}
			if(entity.getGroupId()!=null)
			{
				hql.append(" and s.groupId = "+entity.getGroupId()+ "  ");
			}
			if(entity.getFre()!=null)
			{
				hql.append(" and s.fre = "+entity.getFre()+ " ");
			}
		}
		hql.append(" order by sort ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}
	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getReportFormsListCount(ReportForms entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportForms s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getFormkind()!=null)
			{
				hql.append(" and s.formkind = "+entity.getFormkind()+ " ");
			}
			if(entity.getGroupId()!=null)
			{
				hql.append(" and s.groupId = "+entity.getGroupId()+ " ");
			}
			if(entity.getFre()!=null);
			{
				hql.append(" and s.fre = "+entity.getFre()+ " ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<Report_formsView> getReportFormsListView(Report_formsView entity , Integer offset,Integer pageSize)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.formkind,(select description from hh_base where id=a.formkind) ");
		hql.append(" ,a.fre,(select description from hh_base where id=a.fre),a.fileID,(select description from hh_base where id=b.nameID),b.nameID ,b.time ,(select description from hh_base where id=b.type),b.month ");
		hql.append(" from report_forms a,report_forms_group b where a.groupID=b.id and a.isdel=0 and b.isdel=0 ");
		if(entity!=null)
		{
			if(entity.getType()!=null)
			{
				hql.append(" and b.type = "+entity.getType()+ " ");
			}
			if(entity.getGroupkindId()!=null)
			{
				hql.append(" and b.nameID = "+entity.getGroupkindId()+ " ");
			}
			if(entity.getFormkind()!=null)
			{
				hql.append(" and a.formkind = "+entity.getFormkind()+ " ");
			}
			if(Common.notEmpty(entity.getGroupTime()))
			{
				hql.append(" and b.time = '"+entity.getGroupTime()+ "' ");
			}
			if(entity.getFre()!=null)
			{
				hql.append(" and a.fre = "+entity.getFre()+ " ");
			}
			if(Common.notEmpty(entity.getMonth())){
				hql.append(" and b.month = '"+entity.getMonth()+ "' ");
			}
		}
		hql.append(" order by b.time desc,b.nameID,b.type,a.sort ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		List a= query.list();
		List<Report_formsView> returnData=new ArrayList<Report_formsView>();
		
		for (Object object : a) {
			int i=0;
			Report_formsView rowobj=new Report_formsView();
			Object[] row= (Object[]) object;
			rowobj.setId( row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			rowobj.setFormkind(row[i]==null ? 0:Integer.parseInt(row[i].toString()));i++;
			rowobj.setFormkindName(row[i]==null ? "":row[i].toString());i++;
			rowobj.setFre(  Integer.parseInt(row[i]==null ? null:row[i].toString()));i++;
			rowobj.setFreName( row[i]==null ? "":row[i].toString());i++;
			rowobj.setFileId( row[i]==null ? null:Integer.parseInt(row[i].toString()));i++;
			rowobj.setGroupkindName( row[i]==null ? "":row[i].toString());i++;
			rowobj.setGroupkindId( Integer.parseInt(row[i]==null ? null:row[i].toString()));i++;
			rowobj.setGroupTime( row[i]==null ? "":row[i].toString());i++;
			rowobj.setTypeName( row[i]==null ? "":row[i].toString());i++;
			rowobj.setMonth( row[i]==null ? "":row[i].toString());i++;
			returnData.add(rowobj);
		}
		return returnData;
	}
	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getReportFormsListViewCount(Report_formsView entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from report_forms a,report_forms_group b where a.groupID=b.id  and a.isdel=0 and b.isdel=0 ");
		if(entity!=null)
		{
			if(entity.getType()!=null)
			{
				hql.append(" and b.type = "+entity.getType()+ " ");
			}
			if(entity.getGroupkindId()!=null)
			{
				hql.append(" and b.nameID = "+entity.getGroupkindId()+ " ");
			}
			if(entity.getFormkind()!=null)
			{
				hql.append(" and a.formkind = "+entity.getFormkind()+ " ");
			}
			if(Common.notEmpty(entity.getGroupTime()))
			{
				hql.append(" and b.time = '"+entity.getGroupTime()+ "' ");
			}
			if(entity.getFre()!=null)
			{
				hql.append(" and a.fre = "+entity.getFre()+ " ");
			}
			if(Common.notEmpty(entity.getMonth())){
				hql.append(" and b.month = '"+entity.getMonth()+ "' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	
	//针对Reportpattend
	
	
	/**
	 * 添加样式
	 * @param formsID 报表ID
	 * @param user 用户
	 */
	public void  saveReportPattend(List<ReportFormsPattend> pattend)
	{
		if(pattend!=null && pattend.size()>0)
		{
			StringBuilder pattendhql = new StringBuilder();
			pattendhql.append("INSERT INTO `report_forms_pattend` ( `isdel`, `formsID`, `startrow`, `startcol`, `endrow`, `endcol`, `cellcss`, " +
					"`indexIDcol`, `indexIDrow`, `isdata`, `createPersonName`, `createPersonID`, `createDate`, `lastModifyPersonID`, `lastModifyPersonName`, " +
					"`lastModifyDate`, `unit`, `isunit`, `value`, `colspan`, `rowspan`, `formula`,isformula) VALUES ");
			int i=1;
			for (ReportFormsPattend item : pattend) {
				pattendhql.append("("+item.getIsdel()+","+item.getFormsId()+","+item.getStartrow()+","+item.getStartcol()+","+item.getEndrow()+","+item.getEndcol()+",'"+item.getCellcss()+"',");
				pattendhql.append(item.getIndexIdcol()+","+item.getIndexIdrow()+","+item.getIsdata()+",'"+item.getCreatePersonName()+"','"+item.getCreatePersonId()+"','"+item.getCreateDate()+"','"+item.getLastModifyPersonId()+"','"+item.getLastModifyPersonName()+"','");
				pattendhql.append(item.getLastModifyDate()+"',"+item.getUnit()+","+item.getIsunit()+",'"+item.getValue()+"',"+item.getColspan()+","+item.getRowspan()+",");
				if(Common.notEmpty(item.getFormula()))
					pattendhql.append("'"+item.getFormula()+"'");
				else
					pattendhql.append("''");
				pattendhql.append(","+item.getIsformula()+")");
				if(i<pattend.size())
					pattendhql.append(",");
				i++;
			}
			Query query = sessionFactory.getCurrentSession().createSQLQuery(pattendhql.toString());
			query.executeUpdate();
		}
	}
	
	/**
	 * 删除对应的样式
	 * @param formsID 报表ID
	 * @param user 用户
	 */
	public void  deleteReportPattend(String formsID,HhUsers user)
	{
		if(Common.notEmpty(formsID))
		{
			StringBuilder pattendhql = new StringBuilder();
			pattendhql.append("update report_forms_pattend set isdel=1 , "+createLastModifyString(user)+" where formsID in("+formsID+") ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(pattendhql.toString());
			query.executeUpdate();
		}
	}
	/**
	 * 根据查询实体，获取一个报表的样式
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportFormsPattend> getReportFormsPattend(ReportFormsPattend entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportFormsPattend s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getFormsId()!=null)
			{
				hql.append(" and s.formsId = "+entity.getFormsId()+ " ");
			}
			if(entity.getIsdata()!=null)
			{
				hql.append(" and s.isdata = "+entity.getIsdata()+" ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	//针对ReportIndex 
	/**
	 * 删除报表与指标的关联
	 * @param formsID 报表ID
	 * @param user 用户
	 */
	public void  deleteReportIndex(String formsID,HhUsers user)
	{
		StringBuilder pattendhql = new StringBuilder();
		pattendhql.append("update report_forms_index set isdel=1 , "+createLastModifyString(user)+" where formsID='"+formsID+"' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(pattendhql.toString());
		query.executeUpdate();
	}
	/**
	 * 根据查询实体，获取报表指标
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportIndex> getReportFormsIndexList(ReportIndex entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportFormsIndex s where 1=1 and isdel=0  ");
		if(entity!=null)
		{
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(Common.notEmpty(entity.getName()))
			{
				hql.append(" and s.name like '%"+entity.getName()+ "%' ");
			}
			if(entity.getParentId()!=null)
			{
				hql.append(" and s.parentId = "+entity.getParentId()+ " ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	//针对ReportFormsOrganal
	/**
	 * 删除报表与机构的关联
	 * @param formsID 报表ID
	 * @param user 用户
	 */
	public void  deleteReportOrganal(String formsID,HhUsers user)
	{
		List<ReportFormsOrganal> releation= getReportFormsOrganalByFormsID(formsID);
		StringBuilder formOrganalID=new StringBuilder();
		for (ReportFormsOrganal item : releation) {
			if(formOrganalID.toString().equals(""))
				formOrganalID.append(item.getId());
			else
				formOrganalID.append(","+item.getId());
		}
		//删除报表格子
		deleteReportCell(formOrganalID.toString(),user);
		StringBuilder ReportOrganal = new StringBuilder();
		ReportOrganal.append("update report_forms_organal set isdel=1 , "+createLastModifyString(user)+" where formsID in ("+formsID+") ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(ReportOrganal.toString());
		query.executeUpdate();
	}
	
	/**
	 * 删除报表与机构的关联
	 * @param ID 主键ID
	 * @param user 用户
	 */
	public void  deleteReportOrganalByID(String ID,HhUsers user)
	{
		if(!Common.notEmpty(ID))
			return ;
		//删除报表格子
		deleteReportCell(ID,user);
		StringBuilder ReportOrganal=new StringBuilder();
		ReportOrganal.append("update report_forms_organal set isdel=1 , "+createLastModifyString(user)+" where id in ("+ID+") ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(ReportOrganal.toString());
		query.executeUpdate();
	}
	
	
	/**
	 * 获取机构和报表的关联
	 * @param entity
	 * @return
	 */
	public List<ReportFormsOrganal> getReportFormsOrganal(ReportFormsOrganal entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportFormsOrganal s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getOrganalId()!=null)
			{
				hql.append(" and s.organalId in ("+Common.dealinStr(entity.getOrganalId()) + ") ");
			}
			if(entity.getFormsId()!=null)
			{
				hql.append(" and s.formsId = "+entity.getFormsId()+ " ");
			}
			if(entity.getGroupID()!=null)
			{
				hql.append(" and s.groupID = "+entity.getGroupID()+ " ");
			}
			if(Common.notEmpty(entity.getReportTime()))
			{
				hql.append(" and s.reportTime = '"+entity.getReportTime()+ "' ");
			}
			
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * 获取机构和报表的关联
	 * @param entity
	 * @return
	 */
	public ReportFormsOrganal getReportFormsOrganal(String OrganalId,Integer formsId,String reporttime)
	{
		if(Common.notEmpty(OrganalId) && formsId!=null && Common.notEmpty(reporttime))
		{
			StringBuilder  hql = new StringBuilder();
			hql.append("from ReportFormsOrganal s where 1=1 and isdel=0 ");
			hql.append(" and s.organalId = '"+OrganalId+ "' ");
			hql.append(" and s.formsId = "+formsId+ " ");
			hql.append(" and s.reportTime = '"+reporttime+ "' ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return (ReportFormsOrganal) query.uniqueResult();
		}
		else
			return new ReportFormsOrganal();
	}
	
	/**
	 * 上报报表
	 * @param OrganalId 机构ID
	 * @param groupID 报表类型
	 * @param reporttime 报表时间
	 * @return
	 */
	public void saveReportFormReport(String OrganalId,Integer groupID,String reporttime,Integer reportstatus,HhUsers use,String parentorg)
	{
		if(Common.notEmpty(OrganalId) && groupID!=null && Common.notEmpty(reporttime))
		{
			StringBuilder  hql = new StringBuilder();
			hql.append("update report_forms_organal set isexamine="+reportstatus+","+"parentorg='" + parentorg +"',"+createLastModifyString(use)+","+createReportModifyString(use)+" where 1=1 and isdel=0 ");
			hql.append(" and organalId = '"+OrganalId+ "' ");
			hql.append(" and groupID = "+groupID+ " ");
			hql.append(" and reportTime = '"+reporttime+ "' ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			query.executeUpdate();
		}
	}
	
	/**
	 * 审核报表
	 * @param OrganalId 机构ID
	 * @param groupID 报表类型
	 * @param reporttime 报表时间
	 * @return
	 */
	public void saveReportFormExamine(String OrganalId,Integer groupID,String reporttime,Integer reportstatus,HhUsers use)
	{
		if(Common.notEmpty(OrganalId) && groupID!=null && Common.notEmpty(reporttime))
		{
			StringBuilder  hql = new StringBuilder();
			hql.append("update report_forms_organal set isexamine="+reportstatus+","+createExamineModifyString(use)+" where 1=1 and isdel=0 ");
			hql.append(" and organalId = '"+OrganalId+ "' ");
			hql.append(" and groupID = "+groupID+ " ");
			hql.append(" and reportTime = '"+reporttime+ "' ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			query.executeUpdate();
		}
	}
	
	/*
	 * 删除审核状态
	 * 
	 */
	public void deleteReportFormExamine(String OrganalId,Integer groupID,String reporttime){
		if(Common.notEmpty(OrganalId) && groupID!=null && Common.notEmpty(reporttime)){
			StringBuilder  hql = new StringBuilder();
			hql.append("update report_forms_organal set isdel = 1 where ");
			hql.append(" organalId = '"+OrganalId+ "' ");
			hql.append(" and groupID = "+groupID+ " ");
			hql.append(" and reportTime = '"+reporttime+ "' ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			query.executeUpdate();
		}
	}

	/**
	 * 根据报表formID获取机构和报表的关联
	 * @param entity
	 * @return
	 */
	public List<ReportFormsOrganal> getReportFormsOrganalByFormsID(String formsId)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportFormsOrganal s where 1=1 and isdel=0 ");
		if(Common.notEmpty(formsId))
		{
			hql.append(" and s.formsId in ("+formsId+ ") ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * 根据报表formID和机构ID或者reporttime获取机构和报表的关联
	 * @param formsId in匹配
	 * @param OrganID in匹配
	 * @param date  等号匹配
	 * @return
	 */
	public String getReportFormsOrganalByFormIDAndOrganIDOrDate(String formsId,String OrganID,String date)
	{
		
		String returnData="";
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportFormsOrganal s where 1=1 and isdel=0 ");
		if(Common.notEmpty(formsId))
		{
			hql.append(" and s.formsId in ("+formsId+ ") ");
		}
		if(Common.notEmpty(OrganID))
		{
			hql.append(" and s.organalId in ("+Common.dealinStr(OrganID)+ ") ");
		}
		if(Common.notEmpty(date))
		{
			hql.append(" and s.reportTime = '"+date+ "' ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<ReportFormsOrganal> obj = query.list();

		for (int i = 0; i < obj.size(); i++) {
			if(i==0)
				returnData=obj.get(i).getId().toString();
			else
				returnData+=","+obj.get(i).getId().toString();
		}
		return returnData;
	}
	
	
	/**
	 * 根据ID获取报表实例 reportformOrganal
	 * @param id
	 * @return
	 */
	public ReportFormsOrganal getFormOrganal(Integer id)
	{
		if(id==null )
			return new ReportFormsOrganal();
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportFormsOrganal s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportFormsOrganal) query.uniqueResult();
	}
	
	/**
	 * 根据报表ID和公司ID获取报表实例 reportformOrganal
	 * @param id
	 * @return
	 */
	public ReportFormsOrganal getFormOrganal(Integer formid,String organalID,String date)
	{
		if(formid==null || !Common.notEmpty(organalID) || !Common.notEmpty(date))
			return new ReportFormsOrganal();
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportFormsOrganal s where 1=1 and isdel=0 and formsId="+formid + "  and organalId='"+organalID+"' and reportTime='"+date+"'");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportFormsOrganal) query.uniqueResult();
	}
	
	/**
	 * 根据查询公司和报表的关联
	 * @param organID 公司ID
	 * @param formsID	报表ID
	 * @param startTime 起始时间
	 * @param endTime	终止时间
	 * @return
	 */
	public List<ReportQueryView> getReportFormsOrganalList(Integer pagenum,Integer pagesize,ReportQueryView entity,String status)
	{
		List<ReportQueryView> returnData=new ArrayList<ReportQueryView>();
		if(entity==null )
			return returnData;
		StringBuilder  hql = new StringBuilder();
		hql.append("  select a.groupID,a.organalID,(select description from hh_base where id=c.nameID),a.organName,a.reportPersonName,a.createPersonName,ort.status, ");
		hql.append("  a.auditorPersonName,a.reportTime,a.reportDate,(SELECT description from hh_base where id=a.isexamine),a.isexamine from (report_forms_organal a left join report_forms_group c on c.id=a.groupID) left join hh_organInfo_tree_relation ort on a.organalID = ort.nNodeID");		 
		hql.append(" 	 where  a.isdel=0 and c.isdel=0  ");
		if(Common.notEmpty(status)){
			hql.append(" and ort.status in ("+status+")");
		}
		if(entity.getType()!=null)
		{
			hql.append(" and c.type = '"+entity.getType()+"' ");
		}
		if( Common.notEmpty(entity.getOrganalID()))
		{
			hql.append(" and a.organalID in("+Common.dealinStr(entity.getOrganalID())+") ");
		}
		if(entity.getGroupType()!=null)
		{
			hql.append(" and c.nameID = '"+entity.getGroupType()+"' ");
		}
		if(Common.notEmpty(entity.getStarttime()))
		{
			hql.append(" and a.reportTime >= '"+entity.getStarttime()+"' ");
		}
		if(Common.notEmpty(entity.getEndtime()))
		{
			hql.append(" and a.reportTime <= '"+entity.getEndtime()+"' ");
		}
		if(Common.notEmpty(entity.getTime()))
		{
			hql.append(" and a.reportTime = '"+entity.getTime()+"' ");
		}
		if(entity.getExaminestatus()!=null)
		{
			hql.append(" and a.isexamine = "+entity.getExaminestatus()+" ");
		}
		if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
		{
			hql.append(" and a.isexamine != "+Base.examstatus_1+" ");
		}
		if(Common.notEmpty(entity.getParentorg())){
			hql.append(" and (");
			String [] dd = entity.getParentorg().split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					hql.append(" ort.vcOrganID like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" ort.vcOrganID like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and ort.vcOrganID like '%--%' ");
		}
		
		hql.append(" group by a.groupID,organalID,reportTime ORDER BY a.reportTime desc,a.organalID ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(pagenum!=null)
			query.setFirstResult(pagenum);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		List a= query.list();
		
		for (Object object : a) {
			int i=0;
			Object[] row= (Object[]) object;
			ReportQueryView rowobj=new ReportQueryView();
			rowobj.setGroupID( row[i]==null ? null:Integer.parseInt(row[i].toString()));i++;
			rowobj.setOrganalID(row[i]==null ? "":row[i].toString());i++;
			rowobj.setTitle(row[i]==null ? "":row[i].toString());i++;
			rowobj.setOrganalname( row[i]==null ? "":row[i].toString());i++;
			rowobj.setReportpersonName( row[i]==null ? "":row[i].toString());i++;
			rowobj.setCreatepersonName( row[i]==null ? "":row[i].toString());i++;
			rowobj.setStatus( row[i]==null ? "":row[i].toString());i++;
			rowobj.setAuditorpersonName( row[i]==null ? "":row[i].toString());i++;
			rowobj.setTime( row[i]==null ? "": row[i].toString());i++;
			rowobj.setReporttime( row[i]==null ? "": row[i].toString());i++;
			rowobj.setExaminestatusName( row[i]==null ? "":row[i].toString());i++;
			rowobj.setExaminestatus( row[i]==null ? null:Integer.parseInt(row[i].toString()));i++;
			returnData.add(rowobj);
		}
		return returnData;
	}
	
	/**
	 * 
	 * 根据查询实体，获取企业与报表关联列表的数目
	 * @param entity 查询实体
	 * status 公司种类 实体、虚拟
	 * @return
	 */
	public Integer getReportFormsOrganalListCount(ReportQueryView entity,String status)
	{
		List<ReportQueryView> returnData=new ArrayList<ReportQueryView>();
		if(entity==null )
			return 0;
		StringBuilder  hql = new StringBuilder();
		
		hql.append("select count(1) from(  select count(0) from (report_forms_organal a left join report_forms_group c on c.id=a.groupID) left join hh_organInfo_tree_relation otr on a.organalID = otr.nNodeID");		 
		hql.append(" where a.isdel=0  and c.isdel=0 ");
		if(Common.notEmpty(status)){
			hql.append(" and otr.status in ("+status+")");
		}
		if(entity.getType()!=null)
		{
			hql.append(" and c.type = '"+entity.getType()+"' ");
		}
		if( Common.notEmpty(entity.getOrganalID()))
		{
			hql.append(" and a.organalID in("+Common.dealinStr(entity.getOrganalID())+") ");
		}
		if(entity.getGroupType()!=null)
		{
			hql.append(" and c.nameID = '"+entity.getGroupType()+"' ");
		}
		if(Common.notEmpty(entity.getStarttime()))
		{
			hql.append(" and a.reportTime >= '"+entity.getStarttime()+"' ");
		}
		if(Common.notEmpty(entity.getEndtime()))
		{
			hql.append(" and a.reportTime <= '"+entity.getEndtime()+"' ");
		}
		if(Common.notEmpty(entity.getTime()))
		{
			hql.append(" and a.reportTime = '"+entity.getTime()+"' ");
		}
		if(entity.getExaminestatus()!=null)
		{
			hql.append(" and a.isexamine = "+entity.getExaminestatus()+" ");
		}
		if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
		{
			hql.append(" and a.isexamine != "+Base.examstatus_1+" ");
		}
		
		if(Common.notEmpty(entity.getParentorg())){
			hql.append(" and (");
			String [] dd = entity.getParentorg().split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					hql.append(" otr.vcOrganID like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" otr.vcOrganID like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and otr.vcOrganID like '%--%' ");
		}
		
		hql.append(" group by a.groupID,organalID,reportTime ORDER BY a.reportTime desc,a.organalID ) as a");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
    
	////针对ReportCell
	/**
	 * 保存某个报表组之前进行校验,返回true说明已经审核通过,或者待审核不能再修改，返回false说明还可以修改
	 * @param reportTime 报表时间
	 * @param organalID 机构ID
	 * @param grouptype 报表组类型
	 * status 公司种类 实体、虚拟
	 * @return
	 */
	public boolean saveReportValueCheck(String reportTime,String organalID,Integer groupID)
	{
		if(!Common.notEmpty(reportTime) || !Common.notEmpty(organalID) || groupID==null)
			return true;
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) from ReportFormsOrganal where isdel=0 and reportTime='"+reportTime+"' and organalId='"+organalID+"' and groupID ="+groupID+" and (isexamine=50115 or isexamine=50113) ");		 
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString())>0;
	}
	/**
	 * 根据报表和公司的关联ID删除该公司该报表的内容
	 * @param formOrganalID
	 * @param user
	 */
	public void deleteReportCell(String formOrganalID,HhUsers user)
	{
		if(Common.notEmpty(formOrganalID))
		{
			StringBuilder ReportCell = new StringBuilder();
			ReportCell.append("update report_forms_cell set isdel=1 , "+createLastModifyString(user)+" where releatiionID in ("+formOrganalID+") ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(ReportCell.toString());
			query.executeUpdate();
		}
	}
    
	/**
	 * 保存一张表的值
	 * @param formsID 报表ID
	 * @param user 用户
	 */
	public void  saveReportCell(List<ReportFormsCell> cell)
	{
		if(cell!=null && cell.size()>0)
		{
			StringBuilder pattendhql = new StringBuilder();
			pattendhql.append("INSERT INTO `report_forms_cell` (`isformula`, `formulaStr`, `cellvalue`, `isdel`, `releatiionID`, " +
			"`createPersonName`, `createPersonID`, `createDate`, `lastModifyPersonID`, `lastModifyPersonName`, `lastModifyDate`, `indexIDcol`," +
			" `indexIDrow`) VALUES ");
	
			int i=1;
			for (ReportFormsCell item : cell) {
				pattendhql.append("("+item.getIsformula()+",'"+item.getFormulaStr()+"','"+item.getCellvalue()+"',"+0+","+item.getReleatiionId()+",'");
				pattendhql.append(item.getCreatePersonName()+"','"+item.getCreatePersonId()+"','"+item.getCreateDate()+"','"+(item.getLastModifyPersonId()==null? "":item.getLastModifyPersonId())+"','"+(item.getLastModifyPersonName()==null ? "" :item.getLastModifyPersonName())+"','"+(item.getLastModifyDate()==null ? "" :item.getLastModifyDate())+"',"+item.getIndexIdcol()+",");
				pattendhql.append(item.getIndexIdrow()+")");
				if(i<cell.size())
					pattendhql.append(",");
				i++;
			}
			Query query = sessionFactory.getCurrentSession().createSQLQuery(pattendhql.toString());
			query.executeUpdate();
		}
	}
	
	public void  saveChangeReportCell(Integer oldrelation,Integer newrelation)
	{
		if(oldrelation!=null && newrelation!=null)
		{
			StringBuilder ReportCell = new StringBuilder();
			
			ReportCell.append("INSERT INTO `report_forms_cell` ( `isformula`, `formulaStr`, `cellvalue`, `isdel`, `releatiionID`, `createPersonName`, `createPersonID`, `createDate`, `lastModifyPersonID`, `lastModifyPersonName`, `lastModifyDate`, `indexIDcol`, `indexIDrow`) ");
			ReportCell.append("SELECT  `isformula`, `formulaStr`, `cellvalue`, `isdel`, '"+newrelation+"', `createPersonName`, `createPersonID`, `createDate`, `lastModifyPersonID`, `lastModifyPersonName`, `lastModifyDate`, `indexIDcol`, `indexIDrow` FROM `report_forms_cell` where releatiionID="+oldrelation+";");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(ReportCell.toString());
			query.executeUpdate();
		}
	}
		
	
	public Integer getChangeNewformsID(String oldformsID,Integer groupid)
	{
		StringBuilder ReportCell = new StringBuilder();
		ReportCell.append("select b.id from report_forms a,report_forms b where a.formkind=b.formkind and a.fre=b.fre and a.groupID!=b.groupID and a.isdel=0 and b.isdel=0 and a.id='"+oldformsID+"' and b.groupID= "+groupid);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(ReportCell.toString());
		return (Integer) query.uniqueResult();
	}
	
	
	/**
	 * 汇总一张表的数据 提取子公司和自己
	 * @param organID
	 * @param reporttime
	 * @param formID
	 * @return 
	 */
	public  HashMap<String,String> getCollectReportCell(String childrelactionID)
	{
		HashMap<String, String> map=new HashMap<String, String>();
		if(Common.notEmpty(childrelactionID) )
		{
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuilder hql = new StringBuilder();
			hql.append(" select CAST(SUM(cellvalue) AS DECIMAL(30,4)) as cellvalue,indexIDcol,indexIDrow from report_forms_cell where releatiionID in ("+childrelactionID+") and isdel = 0 group by indexIDcol,indexIDrow");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List a= query.list();
			List<ReportFormsCell> cells=new ArrayList<ReportFormsCell>();
			for (int i = 0; i < a.size(); i++) {
				ReportFormsCell cell=new ReportFormsCell();
				Object[] obj=(Object[]) a.get(i);
				cell.setCellvalue(String.valueOf(obj[0]) );
				cell.setIndexIdcol(Integer.parseInt(obj[1]==null ? null: obj[1].toString()));
				cell.setIndexIdrow(Integer.parseInt(obj[2]==null ? null: obj[2].toString()));
				map.put("["+cell.getIndexIdrow()+","+cell.getIndexIdcol()+"]", cell.getCellvalue());
			}		
		}
		return map;
	}
	

	/**
	 * 汇总报表的检查，子公司和自己的相应报表组是否都上传且审核通过
	 * @param organalID
	 * @param groupID
	 * @return
	 */
	public boolean saveCollectReportCellCheck(String organalID,int groupID,String reporttime)
	{
		if(Common.notEmpty(organalID))
		{
			
			int companynum=organalID.split(",").length;
			//所有公司都有上报
			StringBuilder hql2 = new StringBuilder();
			hql2.append(" SELECT count(DISTINCT organalID) FROM `report_forms_organal` where  organalID in ("+Common.dealinStr(organalID)+") and isdel=0 and reportTime='"+reporttime+"' and groupID= "+groupID+" " );
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql2.toString());
			int a= Integer.parseInt(query.uniqueResult().toString())-companynum;
			
			Boolean flaga=(a==0);
			//上报的都已审核了
			StringBuilder hql = new StringBuilder();
			hql.append(" SELECT count(0) FROM `report_forms_organal` where isexamine!="+Base.examstatus_4+" and organalID in ("+ Common.dealinStr(organalID)+") and isdel=0 and  reportTime='"+reporttime+"' and groupID= "+groupID+"");
			query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			int b = Integer.parseInt(query.uniqueResult().toString()) ;
			Boolean flagb=(b==0);
			return flaga && flagb;
		}
		else
			return false;
	}
	
	
	/**
	 * 汇总时，需要子的汇总数据和自己的单体，这个方法就是获取自己单体用的
	 * @param formID
	 * @param oldgroup
	 * @param organID
	 * @param reportTime
	 * @return
	 */
	public String getCollectMyRelationID(Integer formID,Integer oldgroup,String organID,String reportTime)
	{

		StringBuilder ReportCell = new StringBuilder();
		ReportCell.append("select id from report_forms_organal where isdel=0 and reportTime='"+reportTime+"' and organalID='"+organID+"'  and formsID=");
		ReportCell.append("(select a.id from report_forms a ,report_forms b where a.fre=b.fre and a.formkind=b.formkind and b.id="+formID+" and a.groupID="+oldgroup+")");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(ReportCell.toString());
		return String.valueOf(query.uniqueResult());
	}
	
	
	/**
	 * 根据查询实体获取，相关报表格子
	 * @param entity
	 * @return
	 */
	public List<ReportFormsCell> getReportFormsCell(ReportFormsCell entity)
	{
		if(entity!=null)
		{
			StringBuilder ReportCell = new StringBuilder();
			ReportCell.append("from  ReportFormsCell where isdel=0 ");
			if(entity.getReleatiionId()!=null)
			{
				ReportCell.append(" and  releatiionId="+entity.getReleatiionId() +" ");
			}
			if(entity.getIndexIdcol()!=null)
			{
				ReportCell.append(" and  indexIdcol="+entity.getIndexIdcol() +" ");
			}
			if(entity.getIndexIdrow()!=null)
			{
				ReportCell.append(" and  indexIdrow="+entity.getIndexIdrow() +" ");
			}
			Query query = sessionFactory.getCurrentSession().createQuery(ReportCell.toString());
			return query.list();
		}
		else
			return new ArrayList<ReportFormsCell>();
	}
	
    /**
     * 生成最后修改人sql
     * @param user
     * @return
     */
    private String createLastModifyString (HhUsers user)
    {
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastModifyDate=formatter.format(new Date());
    	StringBuilder  hql = new StringBuilder();
    	hql.append(" lastModifyDate='"+lastModifyDate+"' , lastModifyPersonID='"+user.getVcEmployeeId()+"',lastModifyPersonName='"+user.getVcName()+"' ");
    	return hql.toString();
    }
    
    /**
     * 生成上报人sql
     * @param user
     * @return
     */
    private String createReportModifyString (HhUsers user)
    {
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastModifyDate=formatter.format(new Date());
    	StringBuilder  hql = new StringBuilder();
    	hql.append(" reportDate='"+lastModifyDate+"' , reportPersonId='"+user.getVcEmployeeId()+"',reportPersonName='"+user.getVcName()+"' ");
    	return hql.toString();
    }
    
    /**
     * 生成审核人sql
     * @param user
     * @return
     */
    private String createExamineModifyString (HhUsers user)
    {
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastModifyDate=formatter.format(new Date());
    	StringBuilder  hql = new StringBuilder();
    	hql.append(" auditorDate='"+lastModifyDate+"' , auditorPersonId='"+user.getVcEmployeeId()+"',auditorPersonName='"+user.getVcName()+"' ");
    	return hql.toString();
    }
    
    
    
	/** 
     * 判断时间格式 格式必须为“yyyy-MM-dd HH:mm:ss” 
     * 2004-2-30 是无效的 
     * 2003-2-29 是无效的 
     * @param sDate 
     * @return 
     */  
    private boolean isValidDate(String str) { 
    	if(!Common.notEmpty(str))
    		return true;
        DateFormat formatter = new SimpleDateFormat("yyyy");  
        try{  
            Date date = (Date)formatter.parse(str);  
            return str.equals(formatter.format(date));  
        }catch(Exception e){  
            return false;  
        }  
    } 
    
    /** 
     * 比较时间大小 DATE1<DATE2 return true else false
     * 2004-2-30 是无效的 
     * 2003-2-29 是无效的 
     * @param sDate 
     * @return 
     */  
    private boolean compareTime(String DATE1,String DATE2) { 
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    	try {
	    		Date dt1 = df.parse(DATE1);
	    		Date dt2 = df.parse(DATE2);
	    		if (dt1.getTime() >= dt2.getTime()) {
		    		return false;
	    		} 
	    		else if (dt1.getTime() < dt2.getTime()) {
	    			return true;
	    		} 
    		} 
    	catch (Exception exception) {
    			exception.printStackTrace();
    		}
    		return false;
    } 
    
    
	/**
	 * 导出模板
	 * @param Date 时间
	 * @param grouptype 模板种类
	 * @return
	 */
	public ReportExportout getReportExportTemplet(String Date,Integer grouptype)
	{
		if(Common.notEmpty(Date) && grouptype!=null)
		{
			StringBuilder ReportCell = new StringBuilder();
			ReportCell.append("from ReportExportout  where grouptype="+grouptype+" and  groupstarttime <= '"+Date+"'  and groupendtime>='"+Date+"' ");
			Query query = sessionFactory.getCurrentSession().createQuery(ReportCell.toString());
			return (ReportExportout) query.uniqueResult();
		}
		else
			return new ReportExportout();
	}
	
	/**
	 * 根据报表种类、机构id、报表时间获取数据
	 * @param reportTime
	 * @param organl
	 * @param formKind
	 * @return
	 */
	public List<ReportIndexAndData> getReportValueBy(String reportTime,String organl,int[] formKind)
	{
		String kind="";
		for (int i = 0; i < formKind.length; i++) {
			if(Common.notEmpty(kind))
				kind=kind+",";
			
				kind=kind+formKind[i];
		}
		
		List<ReportIndexAndData>result=new ArrayList<ReportIndexAndData>();
		StringBuffer ReportCell = new StringBuffer();
		ReportCell.append("SELECT m.indexIDrow, m.indexIDcol, CASE WHEN a.isunit = 1 THEN a.unit * m.cellvalue ELSE m.cellvalue END,m.formsKind FROM (");
		ReportCell.append("SELECT c.indexIDrow, c.indexIDcol,c.cellvalue,b.formsKind FROM report_forms_cell c LEFT JOIN report_forms_organal b ON c.releatiionID = b.id");
		ReportCell.append(" WHERE c.isdel = 0 AND b.isdel = 0");
		if(Common.notEmpty(kind))
			ReportCell.append(" and b.formsKind in ("+kind+") ");
		
		if(Common.notEmpty(reportTime))
			ReportCell.append(" and b.reportTime ='"+reportTime+"' ");
		if(Common.notEmpty(organl))
			ReportCell.append(" and b.organalID ='"+organl+"' ");
		ReportCell.append(") m LEFT JOIN report_forms_pattend a ON m.indexIDcol = a.indexIDcol AND m.indexIDrow = a.indexIDrow WHERE a.isdel = 0");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(ReportCell.toString());
		List a=query.list();
		for (Object object : a) {
			Object[] obj=(Object[])object;
			ReportIndexAndData item=new ReportIndexAndData();
			item.setIndexRowID(obj[0]==null ? "":obj[0].toString());
			item.setIndexColID(obj[1]==null ? "":obj[1].toString());
			item.setValue(obj[2]==null ? "":obj[2].toString());
			result.add(item);
		}
		return result;
	}

	public int getReportId(int time,int type,int nameId){
		StringBuilder  hql = new StringBuilder();
		hql.append("select id from ReportFormsGroup s where 1=1 and isdel=0 ");
		
			if(time!=0)
			{
				hql.append(" and s.time = "+time+ " ");
			}
			if(type!=0)
			{
				hql.append(" and s.type = "+type+ " ");
			}
			if(type!=0);
			{
				hql.append(" and s.nameID = "+nameId+ " ");
			}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		int i=Integer.parseInt(query.uniqueResult().toString());
		if(i==0){
			return 0;
		}
		return i;
		
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getHsNoCreateCompanyList(String reportTime,String authdata,String org,Integer offset,Integer pageSize) {
		//rfg.type = 52004   单体
		//rfg.nameID = 52002    核算
		String year1 = "";
		if(Common.notEmpty(reportTime)){
			year1 = reportTime.substring(0,4);
		}
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT hotr.vcFullName,hotr.vcShortName,hotr.nNodeID,hotr.responsiblePersonName,hotr.vcEmployeeID,hotr.responsiblePersonEmail,ruc.lastRemindTime,isRemind ").
			append(" FROM (hh_organInfo_tree_relation_history hotr LEFT JOIN ( SELECT t.organalID FROM report_forms_organal t WHERE t.groupID = (select rfg.id from report_forms_group rfg where rfg.time = ").
			append(year1).append(" and rfg.type = 52004 and rfg.nameID = 52002) ");
		hql.append(" AND t.isdel = 0 AND t.reportTime = '").append(reportTime).append("'");
		hql.append(" GROUP BY t.organalID ) temp1 ON temp1.organalID = hotr.nNodeID ").
			append("left join (select * from report_forms_unFilled_company ruc_temp where ruc_temp.formKind=51112 and ruc_temp.updatetime = '"+reportTime+"' and ruc_temp.isdel=0) ruc on hotr.nNodeID = ruc.nNodeID)");
		hql.append("WHERE hotr.updatetime = '"+reportTime+"' AND temp1.organalID IS NULL");
		
		if(Common.notEmpty(org)){
			hql.append(" and  hotr.vcOrganID like '%-"+org+ "-%'");
		}else{
			if(Common.notEmpty(authdata)){
				hql.append(" and (");
				String [] dd = authdata.split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and hotr.vcOrganID like '%--%' ");
			}
		}
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset != null && pageSize != null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	@Override
	public List<Object[]> getYsNoCreateCompanyList(String reportTime,
			String authdata, String org, Integer formKind, Integer CompanyKind,
			Integer offset, Integer pageSize) {
		// 预算执行
		// public static final int reportgroup_BudgetEnforcement=52001;
		// 报表组属性单户
		// public static final int reportgroup_type_simple=52004;
		// 报表组属性汇总
		// public static final int reportgroup_type_collect=52005;
		String year1 = "";
		if (Common.notEmpty(reportTime)) {
			year1 = reportTime.substring(0, 4);
		}
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT hotr.vcFullName,hotr.vcShortName,(select description from hh_base where id = hotr.status),").
			append("hotr.responsiblePersonName,hotr.vcEmployeeID,hotr.responsiblePersonEmail,temp_t.lastRemindTime,hotr.nNodeID,temp_t.isRemind").
			append(" FROM hh_organInfo_tree_relation_history hotr LEFT JOIN ( SELECT t.organalID,t.formsKind FROM report_forms_organal t WHERE t.groupID in (select rfg.id from report_forms_group rfg where rfg.time = ").
			append(year1);
		if(formKind != null){
			hql.append(" and rfg.type = ").append(formKind);
		}
		hql.append(" and rfg.nameID = ")
				.append(Base.reportgroup_BudgetEnforcement).append(") ");
		hql.append(" AND t.isdel = 0 AND t.reportTime = '").append(reportTime)
				.append("'");
		hql.append(" GROUP BY t.organalID,t.formsKind ) temp1 ON temp1.organalID = hotr.nNodeID ").
		append(" LEFT JOIN (select nNodeID,lastRemindTime,isRemind from report_forms_unFilled_company h where h.updatetime = '").append(reportTime).append("' and formKind = 51111 and isdel=0").append(")temp_t ").
		append(" on temp_t.nNodeID =hotr.nNodeID").
		append(" WHERE hotr.updatetime = '").append(reportTime).append("' AND temp1.organalID IS NULL");

		if(CompanyKind != null){
			hql.append(" and hotr.status = ").append(CompanyKind);
		}
		if (Common.notEmpty(org)) {
			hql.append(" and  hotr.vcOrganID like '%-" + org + "-%'");
		} else {
			if (Common.notEmpty(authdata)) {
				hql.append(" and (");
				String[] dd = authdata.split(",");
				for (int i = 0; i < dd.length; i++) {
					if (i == (dd.length - 1)) {
						hql.append(" hotr.vcOrganID like '%-" + dd[i] + "-%' )");
					} else {
						hql.append(" hotr.vcOrganID like '%-" + dd[i]
								+ "-%' or ");
					}
				}
			} else {
				hql.append(" and hotr.vcOrganID like '%--%' ");
			}
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				hql.toString());
		if (offset != null && pageSize != null) {
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}
	
	@Override
	public List<Object[]> getRemindCompanyList(String reportTime,
			String authdata, String org, Integer formKind, Integer CompanyKind,
			Integer offset, Integer pageSize) {
		String year1 = "";
		if (Common.notEmpty(reportTime)) {
			year1 = reportTime.substring(0, 4);
		}
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT hotr.vcFullName,hotr.vcShortName,(select description from hh_base where id = hotr.status),").
			append("hotr.responsiblePersonName,hotr.vcEmployeeID,hotr.responsiblePersonEmail,temp_t.lastRemindTime,hotr.nNodeID,temp_t.isRemind").
			append(" FROM hh_organInfo_tree_relation_history hotr LEFT JOIN ( SELECT t.organalID,t.formsKind FROM report_forms_organal t WHERE t.groupID in (select rfg.id from report_forms_group rfg where rfg.time = ").
			append(year1);
		if(formKind != null){
			hql.append(" and rfg.type = ").append(formKind);
		}
		hql.append(" and rfg.nameID = ")
				.append(Base.reportgroup_BudgetEnforcement).append(") ");
		hql.append(" AND t.isdel = 0 AND t.reportTime = '").append(reportTime).append("'");
		hql.append(" GROUP BY t.organalID,t.formsKind ) temp1 ON temp1.organalID = hotr.nNodeID ").
		append(" LEFT JOIN (select nNodeID,lastRemindTime,isRemind from report_forms_unFilled_company h where h.updatetime = '").append(reportTime).append("' and formKind = 51111 and isdel=0").append(")temp_t ").
		append(" on temp_t.nNodeID =hotr.nNodeID").
		append(" WHERE hotr.updatetime = '").append(reportTime).append("' AND temp1.organalID IS NULL and temp_t.isRemind=0");

		if(CompanyKind != null){
			hql.append(" and hotr.status = ").append(CompanyKind);
		}
		if (Common.notEmpty(org)) {
			hql.append(" and  hotr.vcOrganID like '%-" + org + "-%'");
		} else {
			if (Common.notEmpty(authdata)) {
				hql.append(" and (");
				String[] dd = authdata.split(",");
				for (int i = 0; i < dd.length; i++) {
					if (i == (dd.length - 1)) {
						hql.append(" hotr.vcOrganID like '%-" + dd[i] + "-%' )");
					} else {
						hql.append(" hotr.vcOrganID like '%-" + dd[i]
								+ "-%' or ");
					}
				}
			} else {
				hql.append(" and hotr.vcOrganID like '%--%' ");
			}
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				hql.toString());
		if (offset != null && pageSize != null) {
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}
	
	@Override
	public List<ReportFormsOrganal> getUpCompanyInfoByHistoryTree(String date,String nNodeIds,Integer groupId){
		if(null == date || 
				null == nNodeIds ||
				null == groupId)
			return new ArrayList<ReportFormsOrganal>();
		StringBuilder sb = new StringBuilder();
		sb.append("select a from ReportFormsOrganal a,HhOrganInfoTreeRelationHistory h where a.organalId = h.id.nnodeId and h.id.updatetime = '").append(date).append("'");
		sb.append(" and a.isdel = 0 and a.isexamine = 50115 and a.groupID = ").append(groupId);
		sb.append(" and a.reportTime = '").append(date).append("'");
		sb.append(" and a.organalId in (").append(nNodeIds).append(") ");
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		return query.list();		
	}

	@Override
	public void setReportFormsOrganalStatus(ReportFormsOrganal reportFormsOrganal){
		if(null == reportFormsOrganal || null == reportFormsOrganal.getId())
			return;
		StringBuilder sb = new StringBuilder();
		sb.append("update ReportFormsOrganal h set h.isexamine = 50114 where h.id = ").append(reportFormsOrganal.getId());		
		sb.append(" and h.isexamine = 50115");
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		query.executeUpdate();	
	}
	
	@Override
	public HhOrganInfoTreeRelationHistory getUpHistoryCompanyInfo(HhOrganInfoTreeRelationHistory beanIn){
		
		if(null == beanIn || 
				null == beanIn.getVcParentId() ||
				null == beanIn.getId().getUpdatetime())
			return new HhOrganInfoTreeRelationHistory();
		
		StringBuilder sb = new StringBuilder();	
		sb.append(" from HhOrganInfoTreeRelationHistory h where h.vcOrganId='").append(beanIn.getVcParentId()).append("'");
		sb.append(" and h.id.updatetime='").append(beanIn.getId().getUpdatetime()).append("'");
		
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		return (HhOrganInfoTreeRelationHistory)query.uniqueResult();		
	}
	
	@Override
	public int isVirtualCompany(String organalId){
		
		if(null == organalId || "".equals(organalId))
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select h.status from HhOrganInfoTreeRelation h where h.id.nnodeId = '").append(organalId).append("'");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());	
		
		return Integer.valueOf(query.uniqueResult().toString());			
	}
	
	/**
	 * @param reportTime   报告时间
	 * @param authdata     数据权限
	 * @param org          公司编号
	 * @param formKind     
	 * 			//报表组属性单户
				public static final int reportgroup_type_simple=52004;
				//报表组属性汇总
				public static final int reportgroup_type_collect=52005;
	 * @param CompanyKind
	 * 			public static final Integer report_organal_entity=50500;
				//虚拟
				public static final Integer report_organal_invent=50501;
				//壳公司
				public static final Integer report_organal_ke=50502; ）
	 * 
	 */
	@Override
	public void addAllDataToRemindPlan(String reportTime,String authdata, 
												String org, Integer formKind, Integer CompanyKind){
		// 预算执行
		// public static final int reportgroup_BudgetEnforcement=52001;
		// 报表组属性单户
		// public static final int reportgroup_type_simple=52004;
		// 报表组属性汇总
		// public static final int reportgroup_type_collect=52005;
		if(null==reportTime || 
				null ==org ||
				null == formKind)
			return;
		
		String year1 = "";	
		if (Common.notEmpty(reportTime)) {
			year1 = reportTime.substring(0, 4);
		}
		
		StringBuffer hql = new StringBuffer();
		hql.append("insert into report_forms_unFilled_company(nNodeID,isdel,isRemind,updatetime,formKind)").
			append("select hotr.nNodeID,0,0,'").append(reportTime).append("',").append(51111).
			append(" FROM hh_organInfo_tree_relation_history hotr LEFT JOIN");
		hql.append(" (SELECT t.organalID,t.formsKind FROM report_forms_organal t WHERE t.groupID in (select rfg.id from report_forms_group rfg where rfg.time = ").append(year1).
			append(" and rfg.type = ").append(formKind).append(" and rfg.nameID = ").append(Base.reportgroup_BudgetEnforcement).append(") ").
			append(" AND t.isdel = 0 AND t.reportTime = '").append(reportTime).append("'").
			append(" GROUP BY t.organalID,t.formsKind ) temp1 ON temp1.organalID = hotr.nNodeID ");
		hql.append(" LEFT JOIN (select nNodeID,lastRemindTime,isRemind from report_forms_unFilled_company h where h.updatetime = '").append(reportTime).append("' and formKind = 51111 and isdel=0").append(")temp_t ").
		append(" on temp_t.nNodeID =hotr.nNodeID").
		append(" WHERE hotr.updatetime = '").append(reportTime).append("' AND temp1.organalID IS NULL and temp_t.nNodeID is null");
		if(CompanyKind != null){
			hql.append(" and hotr.status = ").append(CompanyKind);
		}
		if (Common.notEmpty(org)) {
			hql.append(" and  hotr.vcOrganID like '%-" + org + "-%'");
		} else {
			if (Common.notEmpty(authdata)) {
				hql.append(" and (");
				String[] dd = authdata.split(",");
				for (int i = 0; i < dd.length; i++) {
					if (i == (dd.length - 1)) {
						hql.append(" hotr.vcOrganID like '%-" + dd[i] + "-%' )");
					} else {
						hql.append(" hotr.vcOrganID like '%-" + dd[i]
								+ "-%' or ");
					}
				}
			} else {
				return;
			}
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		query.executeUpdate();
		return;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAdjustAccountRemindCompanyList(String reportTime,String authdata,String org,Integer offset,Integer pageSize) {
		//rfg.type = 52004   单体
		//rfg.nameID = 52002    核算
		String year1 = "";
		if(Common.notEmpty(reportTime)){
			year1 = reportTime.substring(0,4);
		}
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT hotr.vcFullName,hotr.vcShortName,hotr.nNodeID,hotr.responsiblePersonName,hotr.vcEmployeeID,hotr.responsiblePersonEmail,ruc.lastRemindTime,isRemind ").
			append(" FROM (hh_organInfo_tree_relation_history hotr LEFT JOIN ( SELECT t.organalID FROM report_forms_organal t WHERE t.groupID = (select rfg.id from report_forms_group rfg where rfg.time = ").
			append(year1).append(" and rfg.type = 52004 and rfg.nameID = 52002) ");
		hql.append(" AND t.isdel = 0 AND t.reportTime = '").append(reportTime).append("'");
		hql.append(" GROUP BY t.organalID ) temp1 ON temp1.organalID = hotr.nNodeID ").
			append("left join (select * from report_forms_unFilled_company ruc_temp where ruc_temp.formKind=51112 and ruc_temp.updatetime = '"+reportTime+"' and ruc_temp.isdel=0) ruc on hotr.nNodeID = ruc.nNodeID)");
		hql.append("WHERE hotr.updatetime = '"+reportTime+"' AND temp1.organalID IS NULL and ruc.isRemind = 0");
		
		if(Common.notEmpty(org)){
			hql.append(" and  hotr.vcOrganID like '%-"+org+ "-%'");
		}else{
			if(Common.notEmpty(authdata)){
				hql.append(" and (");
				String [] dd = authdata.split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and hotr.vcOrganID like '%--%' ");
			}
		}
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset != null && pageSize != null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}
	
	
	
}

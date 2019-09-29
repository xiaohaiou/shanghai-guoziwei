package com.softline.dao.report.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.report.IHrPersongroupDao;
import com.softline.entity.HhUsers;
import com.softline.entity.HrPersonOrganization;
import com.softline.entity.HrPersongroup;
import com.softline.entity.HrPersonitem;

@Repository(value = "hrPersongroupDao")
/**
 * 
 * @author tch
 *
 */
public class HrPersongroupDao implements IHrPersongroupDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public HrPersongroup getHrPersongroup(Integer id)
	{
		if(id==null)
			return new HrPersongroup();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from HrPersongroup s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		HrPersongroup a= (HrPersongroup) query.uniqueResult();
		
		hql = new StringBuilder();
		hql.append(" from HrPersonitem s where 1=1 and isdel=0 and groupId="+id);
		query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		List<HrPersonitem> b=query.list();
		
		a.setItem(b);
		
		return a;
	}
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public HrPersongroup getHrPersongroup(String org,int year,int month)
	{
		if(!Common.notEmpty(org))
			return new HrPersongroup();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from HrPersongroup s where 1=1 and isdel=0 and month="+month +" and year="+year +" and org="+org);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		HrPersongroup a= (HrPersongroup) query.uniqueResult();
	
		return a;
	}
	
	
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<HrPersongroup> getHrPersongroupList(HrPersongroup entity ,Integer offsize,Integer pagesize)
	{
		if(entity==null)
			return new ArrayList<HrPersongroup>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from HrPersongroup s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
//				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
				
				hql.append(" and s.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
						entity.getOrg()+"'),h.vcOrganId)>0)");
			}
			
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.authOrg like '%--%' ");
			}
		}
		hql.append(" order by year desc,month desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getHrPersongroupListCount(HrPersongroup entity)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from HrPersongroup s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
//				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
				hql.append(" and s.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
						entity.getOrg()+"'),h.vcOrganId)>0)");
			}
			
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.authOrg like '%--%' ");
			}

		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveHrPersongroupRepeatCheck(HrPersongroup entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from HrPersongroup s where 1=1 and isdel=0  ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and s.org = '"+entity.getOrg()+ "' ");
			}
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			if(entity.getMonth()!=null)
			{
				hql.append(" and s.month = "+entity.getMonth()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id != "+entity.getId()+ " ");
			}
			
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return true;
		else
			return false;
		
	}
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(HrPersongroup entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from HrPersongroup s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
		if(entity!=null)
		{
			
			if(entity.getId()!=null)
			{
				hql.append(" and id ="+entity.getId()  +"");
			}
			else
				return true;
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return false;
		else
			return true;
	}
	
	/**
	 * 获取纳税子信息
	 * @param groupID
	 * @return
	 */
	public List<HrPersonitem> getHrPersonitem(Integer groupID ,Integer offsize,Integer pagesize)
	{
		
		StringBuilder  hql = new StringBuilder();
		hql.append("from HrPersonitem s where 1=1 and isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupId = "+groupID+ " order by id ");
		}
		else
			return new ArrayList<HrPersonitem>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * 获取纳税子信息数目
	 * @param groupID
	 * @return
	 */
	public Integer getHrPersonitemCount(Integer groupID)
	{
		
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from HrPersonitem s where 1=1 and isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupId = "+groupID+ "   ");
		}
		else
			return 0;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	/**
	 * 保存纳税子信息数目
	 * @param groupID
	 * @return
	 */
	public void saveHrPersonitem(List<HrPersonitem> a)
	{
		if(a==null || a.size()==0)
			return;
		StringBuilder  hql = new StringBuilder();
		hql.append(" INSERT INTO `hr_personitem` (`isdel`, `post`, `postkind`, `other_work_remark`, `post_status`, `manage_level`, `name`, `sex`, `birthday`, `age`, `workage`, " +
				"`setup_time`, `education`, `school`, `schoollevel`,  `learnfun`,`schoolqualified`, `major`, `isfinance`, `financialtitle`, `titlequalified`, `titlename`, `englishlevel`, " +
				"`englishqualified`, `englishlevelremark`, `workplace`, `overseasexperience`, `businesscompany`, `groupid`,companyname) VALUES");
		for (HrPersonitem hrPersonitem : a) {
			hql.append(" ( "+hrPersonitem.getIsdel()+", '"+hrPersonitem.getPost()+"', "+hrPersonitem.getPostkind()+", '"+hrPersonitem.getOtherWorkRemark()+"', "+hrPersonitem.getPostStatus()+", "
							+hrPersonitem.getManageLevel()+",'"+hrPersonitem.getName()+"',"+hrPersonitem.getSex()+",'"+hrPersonitem.getBirthday()+"',"+hrPersonitem.getAge()+","+hrPersonitem.getWorkage()+", '"
							+hrPersonitem.getSetupTime()+"',"+hrPersonitem.getEducation()+",'"+hrPersonitem.getSchool()+"',"+hrPersonitem.getSchoollevel()+","+hrPersonitem.getLearnfun()+","+hrPersonitem.getSchoolqualified()+",'"
							+hrPersonitem.getMajor()+"',"+hrPersonitem.getIsfinance()+","+hrPersonitem.getFinancialtitle()+","+hrPersonitem.getTitlequalified()+",'"+hrPersonitem.getTitlename()+"',"
							+hrPersonitem.getEnglishlevel()+","+hrPersonitem.getEnglishqualified()+",'"+hrPersonitem.getEnglishlevelremark()+"','"+hrPersonitem.getWorkplace()+"',"
							+hrPersonitem.getOverseasexperience()+","+hrPersonitem.getBusinesscompany()+","+hrPersonitem.getGroupId()+",'"+hrPersonitem.getCompanyname()+"'),");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString().substring(0, hql.toString().length()-1));
		query.executeUpdate();
	}
	
	/**
	 * 删除纳税子信息
	 * @param groupID
	 * @return
	 */
	public boolean deleteHrPersonitem(String groupID)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder  hql = new StringBuilder();
		hql.append("update hr_personitem set isdel=1  ");	
		if(Common.notEmpty(groupID))
		{
			hql.append(" where groupid = "+groupID+ " ");
		}
		else
			return true;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.executeUpdate()>0;
	}

	@Override
	public List<HrPersongroup>  getpersonGroup(HrPersongroup personGroup1){
		StringBuilder hql=new StringBuilder();
		hql.append("  from HrPersongroup  ");
		Query query=sessionFactory.getCurrentSession().createQuery(hql.toString());
	
		return query.list();
	}

	@Override
	public List<HrPersonitem> getpersonItem(HrPersonitem personItem1) {
		
			StringBuilder hql=new StringBuilder();
			hql.append("  from HrPersonitem  ");
			Query query=sessionFactory.getCurrentSession().createQuery(hql.toString());
		
			return query.list();
		
	}
//	财务人员资质数据审核
	@Override
	public List<HrPersonitem> getpersonItemSh(HrPersonitem personItemSh2) {
		StringBuilder  hql=new StringBuilder();
		hql.append("  from HrPersonitem  ");
		Query  query=sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	/**
	 * 删除财务子信息
	 * @param groupID
	 * @return
	 */
	public boolean deleteHrPersonorganization(String groupID) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder  hql = new StringBuilder();
		hql.append("update hr_peosonorganization set isdel=1  ");	
		if(Common.notEmpty(groupID))
		{
			hql.append(" where groupid = "+groupID+ " ");
		}
		else
			return true;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.executeUpdate()>0;
	}

	/**
	 * 保存纳税子信息数目
	 * @param groupID
	 * @return
	 */
	public void saveHrPersonOrganization(List<HrPersonOrganization> a) {
		if(a==null || a.size()==0)
			return;
		StringBuilder  hql = new StringBuilder();
		hql.append(" INSERT INTO `hr_peosonorganization` (`isdel`,`groupid`,`businesscompany`,`companyname`, `post`, `postkind`, `other_work_remark`, `post_status`, `manage_level`, `number_people_A`, `actual_number_people_B`," +
				"`name_actual_people`, `actual_level`, `super_lack`, `remark`) VALUES");
		for (HrPersonOrganization hrPersonOrganization : a) {
			hql.append(" ( "+hrPersonOrganization.getIsdel()+", '"+hrPersonOrganization.getGroupId()+"', "+hrPersonOrganization.getBusinesscompany()+",'"+hrPersonOrganization.getCompanyname()+"', "+"'"+hrPersonOrganization.getPost()+"', "
		                    +hrPersonOrganization.getPostkind()+", '"+hrPersonOrganization.getOtherWorkRemark()+"', "+hrPersonOrganization.getPostStatus()+", "
							+hrPersonOrganization.getManageLevel()+","+hrPersonOrganization.getNumberPeopleA()+","+hrPersonOrganization.getActualNumberPeopleB()+",'"+hrPersonOrganization.getNameActualPeople()+"',"
						    +hrPersonOrganization.getActualLevel()+","+hrPersonOrganization.getSuperLack()+",'"+hrPersonOrganization.getRemark()+"'),");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString().substring(0, hql.toString().length()-1));
		query.executeUpdate();
		
	}

	/**
	 * 获取纳税子信息数目
	 * @param groupID
	 * @return
	 */
	public Integer getHrPersonorganizationCount(Integer groupID) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from HrPersonOrganization s where 1=1 and isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupId = "+groupID+ "   ");
		}
		else
			return 0;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}

	/**
	 * 获取纳税子信息
	 * @param groupID
	 * @return
	 */
	public List<HrPersonOrganization> getHrPersonorganization(Integer groupID,Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from HrPersonOrganization s where 1=1 and isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupId = "+groupID+ " order by id ");
		}
		else
			return new ArrayList<HrPersonOrganization>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	@Override
	public List<HrPersonitem> getHrPersonitem(HrPersongroup entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from HrPersonitem s where 1=1 and isdel=0  ");	
		if(Common.notEmpty(entity.getOrg()))
		{
			hql.append(" and s.org in ("+Common.dealinStr( entity.getOrg())+ ") ");
		}
		else
			return new ArrayList<HrPersonitem>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<HrPersongroup> getHrPersongroup(HrPersongroup entity) {
		if(entity==null)
			return new ArrayList<HrPersongroup>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from HrPersongroup s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{	
				hql.append(" and s.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
						entity.getOrg()+"'),h.vcOrganId)>0)");
			}
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.parentorg like '%--%' ");
			}
		}
		hql.append(" order by year desc,month desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<HrPersonitem> getHrPersonitem(Integer groupID) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from HrPersonitem s where 1=1 and isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupId = "+groupID+ " order by id ");
		}
		else
			return new ArrayList<HrPersonitem>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<HrPersonOrganization> getHrPersonorganization(Integer groupID) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from HrPersonOrganization s where 1=1 and isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupId = "+groupID+ " order by id ");
		}
		else
			return new ArrayList<HrPersonOrganization>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
}

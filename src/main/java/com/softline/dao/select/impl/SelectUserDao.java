package com.softline.dao.select.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.select.ISelectUserDao;
import com.softline.entity.HhBimaCompany;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.HhUsersCopy;

@Repository(value = "selectUserDao")
public class SelectUserDao implements ISelectUserDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public HhOrganInfo getTop(String id,Integer type) {
		if(type==null)
		{	// Base.HRTop顶层企业
			String hql = "from  HhOrganInfo s where cflag = 1 and  s.nnodeId ='" + id+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return (HhOrganInfo) query.uniqueResult();
		}
		else
		{
			String hql = "select new HhOrganInfo(s.id.nnodeId,s.vcOrganId,s.vcParentId,s.vcFullName) from  HhOrganInfoTreeRelation s where cflag = 1 and  type="+type+" and s.id.nnodeId ='" + id+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			return (HhOrganInfo) query.uniqueResult();
			
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HhOrganInfo> getNode(String parent,Integer type) {
		if(type==null)
		{
			String hql = " select nNodeID,vcOrganID,vcParentID,vcFullName FROM hh_organInfo where cFlag = 1 and vcParentID like '"+parent+"%' ORDER BY vcParentID,iShowOrder";
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
			List<Object> a = query.list();
			List<HhOrganInfo> organs = new ArrayList<HhOrganInfo>();
			for(Object obj : a) {
				Object[] data = (Object[])obj;
				int i = 0;
				HhOrganInfo organ = new HhOrganInfo();
				organ.setNnodeId(data[i].toString());i++;
				organ.setVcOrganId(data[i].toString());i++;
				organ.setVcParentId(data[i].toString());i++;
				organ.setVcFullName(data[i].toString());
				organs.add(organ);
			}
			return organs;
		}
		else
		{
			String hql = "select new HhOrganInfo(s.id.nnodeId,s.vcOrganId,s.vcParentId,s.vcFullName) from HhOrganInfoTreeRelation s where cflag = 1 and id.type="+type+"  and vcParentId like '"+parent+"%' ORDER BY vcParentId,ishowOrder";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HhUsers> getAllPerson02(String vcOrganId, String vcName) {
		StringBuffer hql = new StringBuffer();
		hql.append(" FROM HhUsers where cflag=1 ");
		if(Common.notEmpty(vcOrganId)){
			hql.append(" and vcOrganId like '%"+vcOrganId+"%'  ");
		}
		if(Common.notEmpty(vcName)){
			hql.append(" and vcName like '%"+vcName+"%'  ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	@Override
	public List<HhUsersCopy> getAllPerson01(String vcOrganId, String vcName) {
		StringBuffer hql = new StringBuffer();
		hql.append(" FROM HhUsersCopy where cflag=1 ");
//		if(Common.notEmpty(vcOrganId)){
//			hql.append(" and vcOrganId like '%"+vcOrganId+"%'  ");
//		}
//		if(Common.notEmpty(vcName)){
//			hql.append(" and vcName like '%"+vcName+"%'  ");
//		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	
	/**
	 * 更新bima的公司数据
	 */
	public void updateBimaCompany(List<HhBimaCompany> data)
	{
		
			if(data==null || data.size()==0)
			{
				return ;
			}
			String changeid="";
			StringBuffer inserthql = new StringBuffer();
			StringBuffer delhql = new StringBuffer();
			StringBuffer updatehql = new StringBuffer();
			inserthql.append(" INSERT INTO `hh_bima_company` (`bimaID`, `name`, `registeredCapital`, `unionid`, `legalPersonName`, `registryState`, `setTime`, `isExpire`, `Expire`, `registrationNumber`, `address`, `area`, `type1`, `type2`, `type3`, `businessFormat`, `englishName`, `operational`, `operationalPurpose`,lastModifyDate) VALUES");
			delhql.append(" delete from hh_bima_company where 1=1  ");
			updatehql.append(" update hh_organInfo_tree_relation c set vcFullName=(select a.`name` from hh_bima_company a where a.bimaID=c.bimaID) where c.bimaID in (select bimaID from hh_bima_company) ");
			for (HhBimaCompany item : data) {
				String id=(item.getBimaId()==null ? "":item.getBimaId().toString());
				if(changeid.equals(""))
				{
					changeid=id;
				}
				else
					changeid+=","+id;
				inserthql.append("("+item.getBimaId()+", '"+item.getName()+"', '"+item.getRegisteredCapital()+"', '"+item.getUnionid()+"'" +
						",'"+item.getLegalPersonName()+"', '"+item.getRegistryState()+"', '"+item.getSetTime()+"', '"+item.getIsExpire()+"', '"+
						item.getExpire()+"', '"+item.getRegistrationNumber()+"', '"+item.getAddress()+"', '"+item.getArea()+"', '"+item.getType1()+"', '"
						+item.getType2()+"', '"+item.getType3()+"', '"+item.getBusinessFormat()+"', '"+item.getEnglishName()+"', '"+item.getOperational()+"', '"+item.getOperationalPurpose()+"','"+item.getLastModifyDate()+"'), ");
				
			}
			delhql.append(" and bimaID in ("+changeid+")  ");
			
			synchronized (this) {
				System.out.println("bima同步开始");
				//针对变更的内容先删再插，update慢所以采用范围的删插操作
				if(Common.notEmpty(changeid)){
					Query query= sessionFactory.getCurrentSession().createSQLQuery(delhql.toString());
					query.executeUpdate();
				}
				//插入
				String sql=inserthql.toString();
				sql=sql.substring(0,sql.length()-2);
				Query addquery = sessionFactory.getCurrentSession().createSQLQuery(sql);
				addquery.executeUpdate();
				//更新节点树表
				Query delquery = sessionFactory.getCurrentSession().createSQLQuery(updatehql.toString());
				delquery.executeUpdate();
				System.out.println("bima同步结束");
			}
			return;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HhOrganInfo> getNode(String parent) {
		//只获取公司，cLevel<=3
		String hql = "FROM HhOrganInfo where cflag = 1 and vcParentId like '"+parent+"%' and cLevel <= '3' ORDER BY vcParentId,ishowOrder";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<HhUsers> getUsersByName(String name) {
		String hql = " from HhUsers where cflag=1 and vcName like '%"+name+"%'  ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HhOrganInfo> getHistoryNode(String parent, Integer type,String time) {
		if(type==null)
		{
			String hql = " select nNodeID,vcOrganID,vcParentID,vcFullName FROM hh_organInfo where cFlag = 1 and vcParentID like '"+parent+"%' ORDER BY vcParentID,iShowOrder";
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
			List<Object> a = query.list();
			List<HhOrganInfo> organs = new ArrayList<HhOrganInfo>();
			for(Object obj : a) {
				Object[] data = (Object[])obj;
				int i = 0;
				HhOrganInfo organ = new HhOrganInfo();
				organ.setNnodeId(data[i].toString());i++;
				organ.setVcOrganId(data[i].toString());i++;
				organ.setVcParentId(data[i].toString());i++;
				organ.setVcFullName(data[i].toString());
				organs.add(organ);
			}
			return organs;
		}
		else
		{
			String hql = "select new HhOrganInfo(s.id.nnodeId,s.vcOrganId,s.vcParentId,s.vcFullName) from HhOrganInfoTreeRelationHistory s where 1 =1 and id.type="+type+" and updatetime='"+time+"' and vcParentId like '"+parent+"%' ORDER BY vcParentId,ishowOrder";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();
		}
	}

	@Override
	public HhOrganInfo getHistoryTop(String id, Integer type, String time) {
		if(type==null)
		{	// Base.HRTop顶层企业
			String hql = "from  HhOrganInfo s where cflag = 1 and  s.nnodeId ='" + id+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return (HhOrganInfo) query.uniqueResult();
		}
		else
		{
			String hql = "select new HhOrganInfo(s.id.nnodeId,s.vcOrganId,s.vcParentId,s.vcFullName) from  HhOrganInfoTreeRelationHistory s where 1=1 and updatetime='"+time+"' and type="+type+" and s.id.nnodeId ='" + id+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			return (HhOrganInfo) query.uniqueResult();
			
			
		}
	}

	@Override
	public List<HhOrganInfo> getDepNode(String parent) {
		String hql = " select vcOrganID,vcParentID,vcFullName FROM hh_organInfo where cFlag = 1 and vcParentID like '"+parent+"%' ORDER BY vcParentID,iShowOrder";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		List<Object> a = query.list();
		List<HhOrganInfo> organs = new ArrayList<HhOrganInfo>();
		for(Object obj : a) {
			Object[] data = (Object[])obj;
			int i = 0;
			HhOrganInfo organ = new HhOrganInfo();
			organ.setVcOrganId(data[i].toString());i++;
			organ.setVcParentId(data[i].toString());i++;
			organ.setVcFullName(data[i].toString());
			organs.add(organ);
		}
		return organs;
	}

	@Override
	public List<HhUsers> getAllPerson04(String id) {
		String hql = "from HhUsers where cflag=1 and vcOrganId = '" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<HhUsers> getAllPerson03(String ids) {
		// TODO Auto-generated method stub
		String hql = "from HhUsers h where h.cflag=1  and h.vcEmployeeId in(select u.vcEmployeeId from HhGroupUser u where u.groupId in (" + ids + "))";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<HhUsers> getUsersByVcEmployeeId(String userName,String vcAccount) {
		// TODO Auto-generated method stub
		String hql = " from HhUsers where 1=1  ";
		if(null!=userName && !"".equals(userName))
			hql+=" and vcName in ('"+userName+"') ";
		if(null!=vcAccount && !"".equals(vcAccount))
			hql+=" and vcAccount in ('"+vcAccount+"') ";
			
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
}

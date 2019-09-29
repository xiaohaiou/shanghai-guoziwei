package com.softline.dao.bylaw.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bylaw.IBylawInfoDao;
import com.softline.entity.bylaw.BylawInfo;
import com.softline.entity.bylaw.BylawInfoSynRecord;
@Repository("bylawInfoDao")
public class BylawInfoDao implements IBylawInfoDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<BylawInfo> getBylawInfos(String dataauth,BylawInfo condition, Integer offset,
			Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BylawInfo t where t.orgNm is not null");//fileIsAbolish(0 正常1废止）
		//数据权限
		//hql.append(" and t.org in(").append(dataauth).append(")");
		
		if(Common.notEmpty(condition.getOrg())){//发文单位组织机构ID
			hql.append(" and t.org in ( ").append(condition.getOrg()).append(" ) ");
		}
		
		/*if(Common.notEmpty(condition.getOrgNm())){//发文单位名称
			hql.append(" and t.orgNm like '%").append(condition.getOrgNm()).append("%'");
		}*/
		if(Common.notEmpty(condition.getFileName())){//规章制度标题名
			hql.append(" and t.fileName like '%").append(condition.getFileName()).append("%'");
		}
		if(Common.notEmpty(condition.getFileIsAbolish())){//规章制度有效性(true：已作废 false：执行中)
			hql.append(" and t.fileIsAbolish = '").append(condition.getFileIsAbolish()).append("'");
		}
		if(condition.getIsLinked() != null){//是否关联 （0 没有关联 1关联）
			if(condition.getIsLinked() == 0)
				hql.append(" and (t.isLinked = ").append(condition.getIsLinked()).append(" or t.isLinked is null)");
			else if(condition.getIsLinked() == 1)
				hql.append(" and t.isLinked = ").append(condition.getIsLinked());
		}
		if(condition.getFileLevel() != null){
			hql.append(" and t.fileLevel = ").append(condition.getFileLevel());
		}
		if(condition.getDeptId() != null){
			hql.append(" and t.deptId = ").append(condition.getDeptId());
		}
		//hql.append(" and t.isCoreOrg = 1");
		hql.append(" order by t.deptId desc,t.fileEffectiveTime desc");//体系降序，生效日期降序
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	@Override
	public List<BylawInfoSynRecord> getBylawInfoSynRecords(
			BylawInfoSynRecord condition, Integer offset, Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BylawInfoSynRecord t where 1 =1");
		if(Common.notEmpty(condition.getOpStartTime())){
			hql.append(" and t.optTime >= '").append(condition.getOpStartTime()).append(" 00:00:00'");
		}
		if(Common.notEmpty(condition.getOpEndTime())){
			hql.append(" and t.optTime <= '").append(condition.getOpEndTime()).append(" 23:59:59'");
		}
		hql.append("order by t.optTime desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	@Override
	public BylawInfo getBylawInfoByFileId(String fileId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BylawInfo t where t.fileId = '").append(fileId).append("'");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (BylawInfo) query.uniqueResult();
	}

	@Override
	public List<BylawInfo> getSonBylawInfos(Integer parentId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BylawInfo t where t.isCoreOrg = 1 and t.isLinked = 1 and t.parentId = ").append(parentId);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<BylawInfo> getZongzAndNoLevel(Integer deptId, String org) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BylawInfo t where t.isCoreOrg = 1 and t.isLinked = 1 ");
		if(deptId != null){
			hql.append(" and t.deptId = ").append(deptId);
		}
		hql.append(" and t.org = '").append(org).append("'");
		hql.append(" and (t.haveLevel = 0 or t.fileLevel = 1)");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<BylawInfo> getCanChangedBylawInfos(String orgId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BylawInfo t where t.fileIsAbolish = 0 and t.orgNm is not null");
		hql.append(" and t.org = '").append(orgId).append("'");
		hql.append(" and (t.isLinked = 0 or t.isLinked is null)");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	
}

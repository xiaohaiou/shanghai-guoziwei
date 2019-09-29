package com.softline.dao.system.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.system.IFinanceHistroyTreeDao;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhOrganInfoTreeRelationLog;

@Repository(value = "financeHistroyTreeDao")
public class FinanceHistroyTreeDao implements IFinanceHistroyTreeDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public HhOrganInfo getTop(String id, Integer type, String time) {
			String hql = "select new HhOrganInfo(s.id.nnodeId,s.vcOrganId,s.vcParentId,s.vcFullName) from  HhOrganInfoTreeRelationHistory s where  s.id.type="+type+" and s.id.nnodeId ='" + id+"' and s.id.updatetime='" + time + "'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return (HhOrganInfo) query.uniqueResult();
	}

	@Override
	public List<HhOrganInfo> getNode(String parent, Integer type, String time) {
			String hql = "select new HhOrganInfo(s.id.nnodeId,s.vcOrganId,s.vcParentId,s.vcFullName) from HhOrganInfoTreeRelationHistory s where s.id.type="+type+" and s.id.updatetime='" + time + "' and s.vcParentId like '"+parent+"%' ORDER BY vcParentId,ishowOrder";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();
	}

	@Override
	public HhOrganInfoTreeRelationHistory getTreeOrganInfoNode(int type,
			String nnodelD,String time) {
		if(!Common.notEmpty(nnodelD))
			return null;
		StringBuilder  hql = new StringBuilder();
		
		hql.append("from HhOrganInfoTreeRelationHistory where  id.type="+type+" and id.nnodeId='"+nnodelD+"' and id.updatetime='" + time +"'");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (HhOrganInfoTreeRelationHistory) query.uniqueResult();
	}

	@Override
	public List<HhOrganInfoTreeRelationHistory> getChildrenTreeOrganInfos(
			int type, String parentID,String time) {
		List<HhOrganInfoTreeRelationHistory> result=new ArrayList<HhOrganInfoTreeRelationHistory>();
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" from HhOrganInfoTreeRelationHistory where id.type="+type+" and id.updatetime='"+time+"' and vcParentId='"+parentID+"'  ORDER BY iShowOrder ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<HhOrganInfoTreeRelationHistory> a= query.list();
		return a;
	}

	
	@Override
	public List<HhOrganInfoTreeRelationHistory> getChildrenAllTreeOrganInfos(
			int type, String parentID,String time) {
//		List<HhOrganInfoTreeRelationHistory> result=new ArrayList<HhOrganInfoTreeRelationHistory>();
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" from HhOrganInfoTreeRelationHistory where id.type="+type+" and id.updatetime='"+time+"' and vcParentId like '%"+parentID+"%'  ORDER BY iShowOrder ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<HhOrganInfoTreeRelationHistory> a= query.list();
		return a;
	}
	
	/**
	 * 根据类型获取所有公司信息
	 * @param type
	 * @param parentID 
	 * @return
	 */
	@Override
	public List<HhOrganInfoTreeRelationHistory> getChildrenTreeOrganInfos(int type,String time){
		List<HhOrganInfoTreeRelationHistory> result=new ArrayList<HhOrganInfoTreeRelationHistory>();
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" from HhOrganInfoTreeRelationHistory where id.type="+type+" and id.updatetime='"+time+"'  ORDER BY iShowOrder ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<HhOrganInfoTreeRelationHistory> a= query.list();
		return a;	
	}
	
	/**
	 * 获取当前公司上一层及公司的历史财务树信息
	 * @param type			财务种类
	 * @param nnodelD		nNodeID
	 * @param time			财务树固化时间
	 * @return
	 */
	@Override
	public HhOrganInfoTreeRelationHistory getUpTreeOrganInfoByNnodeID(int type,
			String nnodelD,String time) {
		if(!Common.notEmpty(nnodelD))
			return null;
		String sql = " from HhOrganInfoTreeRelationHistory where vcOrganId in (select vcParentId from HhOrganInfoTreeRelationHistory where id.nnodeId= '"+
				nnodelD +"' and id.updatetime='"+time+"' and id.type = '"+type+"') and id.type= '"+type+"' and id.updatetime='" + time +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		return (HhOrganInfoTreeRelationHistory) query.uniqueResult();
	}
	
	@Override
	public void saveTreeEmailInfoEmail(Map info){
		
		if(!info.containsKey("send_Time") ||
				!info.containsKey("send_People") ||
				!info.containsKey("receive_People") ||
				!info.containsKey("status") ||
				!info.containsKey("type") ||
				!info.containsKey("email_title") ||
				!info.containsKey("email_result"))
			return;
		
		String sql = "insert into sendEmailInfo values(0,?,?,?,?,?,?,?)";
		
		Query query=sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, info.get("send_Time"));
		query.setParameter(1, info.get("send_People"));
		query.setParameter(2, info.get("receive_People"));
		query.setParameter(3, info.get("status"));
		query.setParameter(4, info.get("type"));
		query.setParameter(5, info.get("email_title"));
		query.setParameter(6, info.get("email_result"));
		query.executeUpdate();		
	}
	
	@Override
	public void saveTreeEmailInterface(Map info){		
		String sql = "insert into hh_interfase_log values(0,?,?,?,?,?,?,?,?)";
		Query query=sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, info.get("inteface_name"));
		query.setParameter(1, info.get("inteface_alias"));
		query.setParameter(2, info.get("back_result"));
		query.setParameter(3, info.get("back_resultInfo"));
		query.setParameter(4, info.get("remark"));
		query.setParameter(5, info.get("call_time"));
		query.setParameter(6, info.get("call_person_name"));
		query.setParameter(7, info.get("call_vcEmployeeId"));
		query.executeUpdate();
	}
	
	/**
	 * 查询财务树历史信息
	 * @param searchBean
	 * @return
	 */
	@Override
	public List<HhOrganInfoTreeRelationLog> getTreeOperationLog(HhOrganInfoTreeRelationLog searchBean,Integer offsize,Integer pagesize,String date2){
		
		if(null == searchBean)
			return new ArrayList<HhOrganInfoTreeRelationLog>();
		
		String date2Temp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(Common.notEmpty(date2)){
			date2Temp = date2;
		}
		String yearTemp = date2Temp.substring(0, 4);
		String monthTemp = date2Temp.substring(5, 7);
		String dayTemp = date2Temp.substring(8, 10);
			
		StringBuffer hql = new StringBuffer();
		hql.append("from HhOrganInfoTreeRelationLog where 1=1 and isdel = 0");		
		hql.append(" and year <= "+yearTemp);
		hql.append(" and month <= "+monthTemp);
		hql.append(" and day <= "+dayTemp);
		
		if(Common.notEmpty(searchBean.getYear()))
			hql.append(" and year >= "+searchBean.getYear());
		if(Common.notEmpty(searchBean.getMonth()))
			hql.append(" and month >= "+searchBean.getMonth());
		if(Common.notEmpty(searchBean.getDay()))
			hql.append(" and day >= "+searchBean.getDay());

		if(Common.notEmpty(searchBean.getVcFullName()))
			hql.append(" and vcFullName like '%"+searchBean.getVcFullName()+"%'");
		if(Common.notEmpty(searchBean.getOperate_type()))
			hql.append(" and operate_type = '"+searchBean.getOperate_type()+"'");
		
		hql.append(" order by operate_desc desc");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * 查询财务树历史信息
	 * @param searchBean
	 * @return
	 */
	@Override
	public int getTreeOperationLog(HhOrganInfoTreeRelationLog searchBean,String date2){
		
		if(null == searchBean)
			return 0;
		
		String date2Temp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(Common.notEmpty(date2)){
			date2Temp = date2;
		}
		String yearTemp = date2Temp.substring(0, 4);
		String monthTemp = date2Temp.substring(5, 7);
		String dayTemp = date2Temp.substring(8, 10);
			
		StringBuffer hql = new StringBuffer();
		hql.append("select count(0) from HhOrganInfoTreeRelationLog where 1=1 and isdel = 0");		
		hql.append(" and year <= "+yearTemp);
		hql.append(" and month <= "+monthTemp);
		hql.append(" and day <= "+dayTemp);
		
		if(Common.notEmpty(searchBean.getYear()))
			hql.append(" and year >= "+searchBean.getYear());
		if(Common.notEmpty(searchBean.getMonth()))
			hql.append(" and month >= "+searchBean.getMonth());
		if(Common.notEmpty(searchBean.getDay()))
			hql.append(" and day >= "+searchBean.getDay());

		if(Common.notEmpty(searchBean.getVcFullName()))
			hql.append(" and vcFullName like '%"+searchBean.getVcFullName()+"%'");
		if(Common.notEmpty(searchBean.getOperate_type()))
			hql.append(" and operate_type = '"+searchBean.getOperate_type()+"'");
		
		hql.append(" order by operate_time desc");		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	
	
	
}

package com.softline.dao.report.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.report.IReportRemindCompanyDao;
import com.softline.entity.ReportFormsUnFilledCompany;

@Repository(value = "reportRemindCompanyDao")
public class ReportRemindCompanyDao implements IReportRemindCompanyDao{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public boolean isExitsReportFormsUnFilledCompany(ReportFormsUnFilledCompany bean){
		if(null == bean || 
				null == bean.getnNodeID() ||
				null == bean.getUpdatetime() ||
				null == bean.getFormKind())
			return false;
		
		String hql = "select count(*) from ReportFormsUnFilledCompany h where 1=1 and h.nNodeID = '"+bean.getnNodeID()+
					"' and h.updatetime = '"+bean.getUpdatetime()+
					"' and h.formKind = "+bean.getFormKind();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult().toString()) >= 1;		
	}

	@Override
	public ReportFormsUnFilledCompany getReportFormsUnFilledCompany(ReportFormsUnFilledCompany bean){
		
		if(null == bean || 
				null == bean.getnNodeID() ||
				null == bean.getUpdatetime() ||
				null == bean.getFormKind())
			return new ReportFormsUnFilledCompany();
		
		String hql = " from ReportFormsUnFilledCompany h where h.nNodeID = '"+bean.getnNodeID()+
				"' and h.updatetime = '"+bean.getUpdatetime()+
				"' and h.formKind = "+bean.getFormKind()+
				" and h.isdel = 0";		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);		
		return (ReportFormsUnFilledCompany) query.uniqueResult();			
	}

	@Override
	public void deleteReportFormsUnFilledCompany(ReportFormsUnFilledCompany bean){
		if(null == bean || 
				null == bean.getnNodeID() ||
				null == bean.getUpdatetime() ||
				null == bean.getFormKind())
			return;
		
		String hql = "update ReportFormsUnFilledCompany h set h.isRemind = 1 where h.nNodeID = '"+bean.getnNodeID()+
				"' and h.updatetime = '"+bean.getUpdatetime()+
				"' and h.formKind = "+bean.getFormKind();			
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	
		query.executeUpdate();
	}

	@Override
	public void setReportFormsUnFilledCompanyUnRemind(ReportFormsUnFilledCompany bean){
		if(null == bean || 
				null == bean.getnNodeID() ||
				null == bean.getUpdatetime() ||
				null == bean.getFormKind())
			return;
		
		String hql;	
		
		if(bean.getLastRemindTime() != null){
			hql = "update ReportFormsUnFilledCompany h set h.isRemind = 1,h.lastRemindTime=NOW() where h.nNodeID = '"+bean.getnNodeID()+
					"' and h.updatetime = '"+bean.getUpdatetime()+
					"' and h.formKind = "+bean.getFormKind();			
		}else{
			hql = "update ReportFormsUnFilledCompany h set h.isRemind = 1 where h.nNodeID = '"+bean.getnNodeID()+
					"' and h.updatetime = '"+bean.getUpdatetime()+
					"' and h.formKind = "+bean.getFormKind();	
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	
		query.executeUpdate();	
	}	

	@Override
	public void setReportFormsUnFilledCompanyRemind(ReportFormsUnFilledCompany bean){
		
		if(null == bean || 
				null == bean.getnNodeID() ||
				null == bean.getUpdatetime() ||
				null == bean.getFormKind())
			return;
		
		String hql = "update ReportFormsUnFilledCompany h set h.isRemind = 0,h.isdel=0 where h.nNodeID = '"+
					  bean.getnNodeID()+"' and h.updatetime='"+bean.getUpdatetime()+"' and h.formKind ="+bean.getFormKind();		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	
		query.executeUpdate();	
	}

	@Override
	public void setAllHavenDataRemind(String updateTime,Integer formKind){
		
		if(null==updateTime || null == formKind)
			return;
		
		String hql = "update ReportFormsUnFilledCompany h set h.isRemind = 0 where h.updatetime='"+
				updateTime+"' and h.formKind="+formKind+" and h.isdel = 0 ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	
		query.executeUpdate();	
	}

	@Override
	public void setAllHavenDataDisRemind(String updateTime,Integer formKind){
		
		if(null==updateTime || null == formKind)
			return;
		String hql = "update ReportFormsUnFilledCompany h set h.isRemind = 1 where h.updatetime='"+
				updateTime+"' and h.formKind="+formKind+" and h.isdel = 0 ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	
		query.executeUpdate();	
	}
	
	/**
	 * @param reportTime         上报时间
	 * @param authdata      	    数据权限
	 * @param remindFormKind     未填报公司类别	
	 * @param treeTab			    查询财务树种类
	 *  
	 *  该公司为 核算类未填报公司
	 */
	public void addAllDataToRemindPlan(String reportTime, String authdata,Integer remindFormKind,String treeTab){
		
		if(!Common.notEmpty(reportTime) &&
				!Common.notEmpty(String.valueOf(remindFormKind)))
			return;
		
		String year1 = reportTime.substring(0, 4);
		
		StringBuffer hql = new StringBuffer();
		//区分查询财务树种类
		if("hh_organInfo_tree_relation_history".equals(treeTab)){
			hql.append("insert into report_forms_unFilled_company(nNodeID,isdel,isRemind,updatetime,formKind)").
			append("select hotr.nNodeID,0,0,'").append(reportTime).append("',").append(remindFormKind).
			append(" FROM (").append(treeTab).append(" hotr LEFT JOIN ( SELECT t.organalID FROM report_forms_organal t WHERE t.groupID = (select rfg.id from report_forms_group rfg where rfg.time = ").
			append(year1).append(" and rfg.type = 52004 and rfg.nameID = 52002) ");
			hql.append(" AND t.isdel = 0 AND t.reportTime = '").append(reportTime).append("'");
			hql.append(" GROUP BY t.organalID ) temp1 ON temp1.organalID = hotr.nNodeID ").
			append("left join (select * from report_forms_unFilled_company ruc_temp where ruc_temp.formKind =").append(remindFormKind).append(" and ruc_temp.updatetime = '"+reportTime+"' and ruc_temp.isdel=0) ruc on hotr.nNodeID = ruc.nNodeID)");
			hql.append("WHERE hotr.updatetime = '"+reportTime+"' AND temp1.organalID IS NULL and ruc.nNodeID is null");
		}else if("hh_organInfo_tree_relation".equals(treeTab)){
			hql.append("insert into report_forms_unFilled_company(nNodeID,isdel,isRemind,updatetime,formKind)").
			append("select hotr.nNodeID,0,0,'").append(reportTime).append("',").append(remindFormKind).
			append(" FROM (").append(treeTab).append(" hotr LEFT JOIN ( SELECT t.organalID FROM report_forms_organal t WHERE t.groupID = (select rfg.id from report_forms_group rfg where rfg.time = ").
			append(year1).append(" and rfg.type = 52004 and rfg.nameID = 52002) ");
			hql.append(" AND t.isdel = 0 AND t.reportTime = '").append(reportTime).append("'");
			hql.append(" GROUP BY t.organalID ) temp1 ON temp1.organalID = hotr.nNodeID ").
			append("left join (select * from report_forms_unFilled_company ruc_temp where ruc_temp.formKind =").append(remindFormKind).append(" and ruc_temp.updatetime = '"+reportTime+"' and ruc_temp.isdel=0) ruc on hotr.nNodeID = ruc.nNodeID)");
			hql.append("WHERE temp1.organalID IS NULL and ruc.nNodeID is null");
		}else{
			return;
		}
		
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
			return;
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		query.executeUpdate();
	}
	
}

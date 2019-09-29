package com.softline.dao.bimshow.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.bimshow.IBimShowGetDataDao;

@Repository("bimShowGetDataDao")
public class BimShowGetDataDao implements IBimShowGetDataDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<Object> getFinanceRiskData(String model_id) {
		// TODO Auto-generated method stub
		String sql= "select year,month,org_nm from index_show_data where model_id = '"+
					model_id+"'and year = (select max(year) from index_show_data where model_id= '"+
					model_id+"') and month = (select max(month) from index_show_data where model_id = '"+
					model_id+"' and year = ((select max(year) from index_show_data where model_id= '"+
					model_id+"')))";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

	@Override
	public List<Object> getFinan_risk_histogram(String model_id) {
		// TODO Auto-generated method stub
		if(!model_id.contains(","))
			return new ArrayList<Object>();
		String[] model_idArry = model_id.split(",");
		String model_idTemp = "";
		for(int i=0;i<model_idArry.length;i++){
			if(i==0)
				model_idTemp="'"+model_idArry[i]+"'";
			else
				model_idTemp+=",'"+model_idArry[i]+"'";
		}

		String sql = "select risk_red,risk_yellow,risk_green from index_show_data where model_id in ("+
					model_idTemp+") and year = (select max(year) from index_show_data where model_id in ("+
					model_idTemp+")) and month = (select max(month) from index_show_data where model_id in ("+
					model_idTemp+") and year = ((select max(year) from index_show_data where model_id in ("+
					model_idTemp+"))))";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

	/**
	 * 替换替换工商银行分析饼图数据
	 */
	@Override
	public List<Object> geticbc_analysis_num(String model_id){
		// TODO Auto-generated method stub
		String sql= "select year,month,icbc_total,icbc_diff from index_show_data where model_id = '"+
					model_id+"'and year = (select max(year) from index_show_data where model_id= '"+
					model_id+"') and month = (select max(month) from index_show_data where model_id = '"+
					model_id+"' and year = ((select max(year) from index_show_data where model_id= '"+
					model_id+"')))";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
	
	/**
	 * 替换替换工商银行差异公司
	 */
	@Override
	public List<Object> geticbc_analysis_diff(String model_id){
		
		String sql= "select icbc_diff_41company from index_show_data where model_id = '"+
				model_id+"'and year = (select max(year) from index_show_data where model_id= '"+
				model_id+"') and month = (select max(month) from index_show_data where model_id = '"+
				model_id+"' and year = ((select max(year) from index_show_data where model_id= '"+
				model_id+"'))) limit 3";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
		
	}
	
	/**
	 * 替换替换财务与管理口径总数
	 */
	@Override
	public List<Object> getFin_manage_total(String model_id){
		String sql= "select year,month,fin_manage_total from index_show_data where model_id = '"+
				model_id+"'and year = (select max(year) from index_show_data where model_id= '"+
				model_id+"') and month = (select max(month) from index_show_data where model_id = '"+
				model_id+"' and year = ((select max(year) from index_show_data where model_id= '"+
				model_id+"'))) limit 1";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
	
	/**
	 * 替换替换财务与管理口径fin_manage_sasac
	 */
	@Override
	public List<Object> getFin_manage_sasac(String model_id){
		String sql= "select org_nm,sh_sasac_total,sh_sasac_diff from index_show_data where model_id = '"+
				model_id+"'and year = (select max(year) from index_show_data where model_id= '"+
				model_id+"') and month = (select max(month) from index_show_data where model_id = '"+
				model_id+"' and year = ((select max(year) from index_show_data where model_id= '"+
				model_id+"')))";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
	
	/**
	 * 替换上市公司数据
	 */
	@Override
	public List<Object> getPublic_company(String model_id){
		String sql= "select year,month,org_nm,pub_company_total,pub_company_famc from index_show_data where model_id = '"+
				model_id+"'and year = (select max(year) from index_show_data where model_id= '"+
				model_id+"') and month = (select max(month) from index_show_data where model_id = '"+
				model_id+"' and year = ((select max(year) from index_show_data where model_id= '"+
				model_id+"'))) limit 6";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();	
	}
	
	/**
	 * 替换数据全景分析
	 */
	@Override
	public List<Object> getData_analysis(String model_id){
		String sql= "select year,month,data_Analysis_All from index_show_data where model_id = '"+
				model_id+"'and year = (select max(year) from index_show_data where model_id= '"+
				model_id+"') and month = (select max(month) from index_show_data where model_id = '"+
				model_id+"' and year = ((select max(year) from index_show_data where model_id= '"+
				model_id+"'))) limit 6";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
	
	/**
	 * 替换数据全景分析2
	 */
	@Override
	public List<Object> getData_analysis_desc(String model_id){
		String sql= "select data_Analysis_desc from index_show_data where model_id = '"+
				model_id+"'and year = (select max(year) from index_show_data where model_id= '"+
				model_id+"') and month = (select max(month) from index_show_data where model_id = '"+
				model_id+"' and year = ((select max(year) from index_show_data where model_id= '"+
				model_id+"')))";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
}

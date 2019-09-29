package com.softline.dao.warncenter.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.warncenter.IWarningCenterDao;
import com.softline.entity.DataWorktableWarningCenter;
import com.softline.entityTemp.DataWarningCenterParameter;

@Repository(value = "warningCenterDao")
public class WarningCenterDao implements IWarningCenterDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getLogger(WarningCenterDao.class); 
	
	/**
	 * 修改，每月1-15号只显示上上个月数据，
	 *       16-20号显示所有的数据
	 */
	@Override
	public List<DataWorktableWarningCenter> dataShow(
			DataWorktableWarningCenter searchBean, Integer offsize,
			Integer pageSize,Map authorMap) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		double nowYear = Double.valueOf(nowTime.substring(0, 4));
		double nowMonth = Double.valueOf(nowTime.substring(5, 7));
		double nowDay  = Double.valueOf(nowTime.substring(8, 10));
		
		StringBuffer sb = new StringBuffer();
		sb.append(" from DataWorktableWarningCenter h where 1=1 and isdel = 0");
		if(Common.isNumber(searchBean.getYear())){
			sb.append(" and h.year = "+Integer.valueOf(searchBean.getYear()));
		}
		if(Common.isNumber(searchBean.getMonth())){
			sb.append(" and h.month = "+Integer.valueOf(searchBean.getMonth()));
		}
		if(Common.isNumber(searchBean.getDay())){
			sb.append(" and h.day = "+ Integer.valueOf(searchBean.getDay()));
		}
		if(Common.isNumber(searchBean.getStatus()+"") && searchBean.getStatus()!=0){
			sb.append(" and h.status = "+searchBean.getStatus());
		}
		if(Common.notEmpty(searchBean.getModel_id())){
			sb.append(" and h.model_id = '"+ searchBean.getModel_id()+"'");
		}
		/*
		 * 修改，每月1-15号只显示上上个月数据，
	     *        16-20号显示所有的数据
		 */
		if(nowDay<15){
			sb.append(" and h.year+h.month/100<"+(nowYear+nowMonth/100));			
		}
		//控制页面访问权限
		String pageAuthorStr = String.valueOf(authorMap.get("pageAuthor"));
		if(Common.notEmpty(pageAuthorStr)){
			sb.append(" and h.page_author in ("+pageAuthorStr+")");
		}
		//控制数据权限
		String dataAuthorStr1 = String.valueOf(authorMap.get("companyAuthor"));
		String dataAuthorStr2 = String.valueOf(authorMap.get("financeCompanyAuthor"));	
		
		if(Common.notEmpty(dataAuthorStr1) && Common.notEmpty(dataAuthorStr2)){
			sb.append(" and ( h.org in ("+dataAuthorStr1+") or h.org in ("+dataAuthorStr2+"))");
		}
		
		if(Common.notEmpty(dataAuthorStr1) && !Common.notEmpty(dataAuthorStr2)){
			sb.append(" and ( h.org in ("+dataAuthorStr1+"))");
		}
		
		if(!Common.notEmpty(dataAuthorStr1) && Common.notEmpty(dataAuthorStr2)){
			sb.append(" and ( h.org in ("+dataAuthorStr2+"))");
		}
		
		sb.append(" order by h.year desc,h.month desc");
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}

	/**
	 * 工作台数据展示，查询所有记录总数。
	 * @param searchBean
	 * @return
	 */
	@Override
	public Integer dataShow(DataWorktableWarningCenter searchBean,Map authorMap){
		
		StringBuffer sb = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		double nowYear = Double.valueOf(nowTime.substring(0, 4));
		double nowMonth = Double.valueOf(nowTime.substring(5, 7));
		double nowDay  = Double.valueOf(nowTime.substring(8, 10));
		
		sb.append("select count(0) from DataWorktableWarningCenter h where 1=1 and isdel = 0");
		if(Common.isNumber(searchBean.getYear())){
			sb.append(" and h.year = "+Integer.valueOf(searchBean.getYear()));
		}
		if(Common.isNumber(searchBean.getMonth())){
			sb.append(" and h.month = "+Integer.valueOf(searchBean.getMonth()));
		}
		if(Common.isNumber(searchBean.getDay())){
			sb.append(" and h.day = "+ Integer.valueOf(searchBean.getDay()));
		}
		if(Common.isNumber(searchBean.getStatus()+"") && searchBean.getStatus()!=0){
			sb.append(" and h.status = "+searchBean.getStatus());
		}
		if(Common.notEmpty(searchBean.getModel_id())){
			sb.append(" and h.model_id = '"+ searchBean.getModel_id()+"'");
		}
		/*
		 * 修改，每月1-15号只显示上上个月数据，
	     *        16-20号显示所有的数据
		 */
		if(nowDay<15){
			sb.append(" and h.year+h.month/100<"+(nowYear+nowMonth/100));			
		}
		//控制页面访问权限
		String pageAuthorStr = String.valueOf(authorMap.get("pageAuthor"));
		if(Common.notEmpty(pageAuthorStr)){
			sb.append(" and h.page_author in ("+pageAuthorStr+")");
		}
		//控制数据权限
		String dataAuthorStr1 = String.valueOf(authorMap.get("companyAuthor"));
		String dataAuthorStr2 = String.valueOf(authorMap.get("financeCompanyAuthor"));		
		if(Common.notEmpty(dataAuthorStr1) && Common.notEmpty(dataAuthorStr2)){
			sb.append(" and ( h.org in ("+dataAuthorStr1+") or h.org in ("+dataAuthorStr2+"))");
		}
		
		if(Common.notEmpty(dataAuthorStr1) && !Common.notEmpty(dataAuthorStr2)){
			sb.append(" and ( h.org in ("+dataAuthorStr1+"))");
		}
		
		if(!Common.notEmpty(dataAuthorStr1) && Common.notEmpty(dataAuthorStr2)){
			sb.append(" and ( h.org in ("+dataAuthorStr2+"))");
		}
		
		sb.append(" order by h.year,h.month desc");
		System.out.println(sb.toString());
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	
	/**
	 * 提取未及时上报，未及时审核单位
	 * @param searchBean
	 * @param parameterArr 返回实体类参数设置
	 * 			parameterArr[0] page_author
	 * 			parameterArr[1] model_id
	 * 			parameterArr[2] model_name
	 * 			parameterArr[3] url
	 * @return
	 */
	@Override
	public List<DataWorktableWarningCenter> getUnreportedCompany(String sql,String[] parameterArr){
		if(!Common.notEmpty(sql)||parameterArr.length!=4){
			if(log.isInfoEnabled())
				log.info("传入参数有误，更新未及时填报和未及时审核公司失败！");
			return new ArrayList<DataWorktableWarningCenter>();
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List listInfo= query.list();
		//设置返回的查询数据
		List<DataWorktableWarningCenter> listGetBean = new ArrayList<DataWorktableWarningCenter>();
		String str = null;
		for (Object object : listInfo) {
			int i=0;
			DataWorktableWarningCenter bean = new DataWorktableWarningCenter();
			Object[] row= (Object[]) object;
			bean.setYear(row[i]==null?"":row[i].toString());i++;	
			bean.setMonth(row[i]==null?"":row[i].toString());i++;
			bean.setDay(row[i]==null?"":row[i].toString());i++;
			bean.setOrgname(row[i]==null?"":row[i].toString());i++;
			bean.setOrg(row[i]==null?"":row[i].toString());i++;
			bean.setStatus(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;
			bean.setCreateDate(row[i]==null?"":row[i].toString());i++;
//			bean.setCreatePersonName(row[i]==null?"":row[i].toString());i++;
//			bean.setIsdel(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;
//			bean.setReportPersonName(row[i]==null?"":row[i].toString());i++;
//			bean.setAuditorPersonName(row[i]==null?"":row[i].toString());i++;
//			bean.setReportDate(row[i]==null?"":row[i].toString());i++;
//			bean.setAuditorDate(row[i]==null?"":row[i].toString());i++;
			//父亲公司编码
			if(row.length>i){
				bean.setParentorg(row[i]==null?"":row[i].toString());		
			}
			//设置页面访问权限信息
			bean.setIsdel(0);
			bean.setPage_author(parameterArr[0]);
			//设置页面访问权限信息
			bean.setModel_id(parameterArr[1]);
			bean.setModel_name(parameterArr[2]);
			bean.setUrl(parameterArr[3]);
			listGetBean.add(bean);
		}
		return listGetBean;
	}
	
	/**
	 * 提取已上报、已审核公司查询
	 * @param searchBean
	 * @return
	 */
	@Override
	public List<DataWorktableWarningCenter> getReportedCompany(String sql){
		if(!Common.notEmpty(sql))
			return new ArrayList<DataWorktableWarningCenter>();
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List listInfo= query.list();
		//设置返回的查询数据
		List<DataWorktableWarningCenter> listGetBean = new ArrayList<DataWorktableWarningCenter>();
		for (Object object : listInfo) {
			int i=0;
			DataWorktableWarningCenter bean = new DataWorktableWarningCenter();
			Object[] row= (Object[]) object;
			bean.setId(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;	
			bean.setYear(row[i]==null?"":row[i].toString());i++;	
			bean.setMonth(row[i]==null?"":row[i].toString());i++;
			bean.setDay(row[i]==null?"":row[i].toString());i++;
			bean.setOrgname(row[i]==null?"":row[i].toString());i++;	
			bean.setOrg(row[i]==null?"":row[i].toString());i++;	
			bean.setCreatePersonName(row[i]==null?"":row[i].toString());i++;	
			bean.setIsdel(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;	
			bean.setReportPersonName(row[i]==null?"":row[i].toString());i++;	
			bean.setAuditorPersonName(row[i]==null?"":row[i].toString());i++;	
			bean.setStatus(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;	
			bean.setCreateDate(row[i]==null?"":row[i].toString());i++;	
			bean.setReportDate(row[i]==null?"":row[i].toString());i++;	
			bean.setAuditorDate(row[i]==null?"":row[i].toString());i++;	
			bean.setParentorg(row[i]==null?"":row[i].toString());i++;	
			bean.setPage_author(row[i]==null?"":row[i].toString());i++;	
			bean.setModel_id(row[i]==null?"":row[i].toString());i++;	
			bean.setModel_name(row[i]==null?"":row[i].toString());i++;
			listGetBean.add(bean);
		}
		return listGetBean;
	}
	
	/**
	 * 提取所有未填报公司的数据
	 * 		上个月
	 * @param sql
	 * @param parameterArr
	 * 			parameterArr[0] page_author
	 * 			parameterArr[1] model_id
	 * 			parameterArr[2] model_name
	 * 			parameterArr[3] url
	 * 			parameterArr[4] year
	 * 			parameterArr[5] month
	 * 
	 * @return
	 */
	@Override
	public List<DataWorktableWarningCenter> getUnFilledCompany(String sql,String[] parameterArr){
		
		if(parameterArr.length!=6 || !Common.notEmpty(sql)){
			if(log.isInfoEnabled())
				log.info("传入参数有误，更新未及时填报和未及时审核公司失败！");
			return new ArrayList<DataWorktableWarningCenter>();
		}
			
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List listInfo= query.list();
		//设置返回的查询数据
		List<DataWorktableWarningCenter> listGetBean = new ArrayList<DataWorktableWarningCenter>();
		String str = null;
		for (Object object : listInfo) {
			int i=0;
			DataWorktableWarningCenter bean = new DataWorktableWarningCenter();
			Object[] row= (Object[]) object;
			bean.setOrgname(row[i]==null?"":row[i].toString());i++;
			bean.setOrg(row[i]==null?"":row[i].toString());i++;
			//父亲公司编码
			if(row.length>i){
				bean.setParentorg(row[i]==null?"":row[i].toString());		
			}
			//设置时间信息
			bean.setYear(parameterArr[4]);	
			bean.setMonth(parameterArr[5]);
			//设置页面访问权限信息
			bean.setPage_author(parameterArr[0]);
			//设置页面访问权限信息
			bean.setModel_id(parameterArr[1]);
			bean.setModel_name(parameterArr[2]);
			bean.setUrl(parameterArr[3]);
			bean.setStatus(51111);
			listGetBean.add(bean);
		}
		return listGetBean;
		
	}
	
	/**
	 * 查询数据转换记录
	 * @param offsize
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<Object> getReportData(String model_id,Integer offsize,Integer pageSize){

		String sql = "select * from data_worktable_warning_center_log";
		if(Common.notEmpty(model_id)){
			sql+=" where model_id like '%"+model_id+"%'";
		}
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		List listInfo= query.list();
		return listInfo;
	}
	
	/**
	 * 查询所有转换记录总数
	 * @param model_id
	 * @return
	 */
	@Override
	public Integer getReportData(String model_id){
		
		String sql = "select count(0) from data_worktable_warning_center_log";
		if(Common.notEmpty(model_id)){
			sql+=" where model_id like '%"+model_id+"%'";
		}	
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	
	/**
	 * 删除对应公司的记录
	 * @param bean
	 */
	@Override
	public void deleteRecordCompany(DataWorktableWarningCenter bean){
		if(null==bean)
			return;
		String sql = "delete h from data_worktable_warning_center h where h.id="+bean.getId();
		Query query=sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public int dataWorktableWarningLog(String dataTime, String modelId,
			String msg) {
		// TODO Auto-generated method stub
		if(!Common.notEmpty(dataTime)||!Common.notEmpty(modelId)||!Common.notEmpty(msg))
			return -1;
		String sql = "insert into data_worktable_warning_center_log values(0,?,?,?)";
		Query query=sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, dataTime);
		query.setParameter(1, msg);		
		query.setParameter(2, modelId);		
		query.executeUpdate();
		return 0;
	}

	/**
	 * 获取	1、未及时上报	 UnReportIn
	 * 		2、未及时审核            UnExmineIn
	 * 		3、已上报                    ReportedOut
	 * 		4、已审核                    ExminedOut
	 * @return
	 */
	@Override
	public List<DataWarningCenterParameter> getAllParameter(){
		String sql = "select id,sql_label,page_author,model_id,model_name,url,warnTime from data_worktable_warning_center_parameter";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List listInfo= query.list();
		//设置返回的查询数据
		List<DataWarningCenterParameter> listGetBean = new ArrayList<DataWarningCenterParameter>();
		for (Object object : listInfo) {
			int i=0;
			DataWarningCenterParameter bean = new DataWarningCenterParameter();
			Object[] row= (Object[]) object;
			bean.setId(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;	
			bean.setSql_label(row[i]==null?"":row[i].toString());i++;	
			bean.setPage_author(row[i]==null?"":row[i].toString());i++;	
			bean.setModel_id(row[i]==null?"":row[i].toString());i++;	
			bean.setModel_name(row[i]==null?"":row[i].toString());i++;	
			bean.setUrl(row[i]==null?"":row[i].toString());i++;	
			bean.setWarnTime(row[i]==null?"":row[i].toString());i++;	
			listGetBean.add(bean);
		}
		return listGetBean;
	}
	
	/**
	 * 添加菜单选择项
	 * @return
	 */
	@Override
	public List<Object> getSelectOptionsForWarnCenter(){
		String sql = "select d.model_id,d.model_name from data_worktable_warning_center d GROUP BY model_name";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
	
	/**
	 * 删除未及时填报数据
	 * @param date
	 */
	@Override
	public void deleteUnFilledCompany(String date){
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}");
        if(!pattern.matcher(date).matches())
        	return;
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        
        String sql = "delete h from data_worktable_warning_center h where h.year ="+Integer.valueOf(year)+
        		      " and h.month = "+Integer.valueOf(month)+" and h.status = 51111";
		Query query=sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
}

package com.softline.dao.system.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.controller.common.commonController;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOperationLog;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhOrganInfoTreeRelationId;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.SysUsersrole;
import com.softline.entityTemp.OutCompareCompany;
import com.softline.entityTemp.PublicCompany;
import com.softline.service.system.ISystemService;

@Repository(value = "systemDao")
public class SystemDao implements ISystemDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Resource(name = "systemService")
	private ISystemService systemService;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public void saveHhOperationLogList(HhOperationLog hhOperationLog){
		sessionFactory.getCurrentSession().save(hhOperationLog);
	}
	
	
	public HhUsers getEmployeeById(String employeeId){
		StringBuffer hql = new StringBuffer();
		
		
		if(employeeId != null && !employeeId.equals("")){
			hql.append("from HhUsers t where t.vcEmployeeId = '"+employeeId+"'");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return (HhUsers) query.uniqueResult();
		}else{
			return new HhUsers();
		}
	}
	
	
	public HhOrganInfo getOrganInfoById(String id)
	{
		StringBuffer hql = new StringBuffer();
		if(id != null && !id.equals("")){
			
			hql.append("from HhOrganInfo t where t.nnodeId = '"+id+"'");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return (HhOrganInfo) query.uniqueResult();
		}else{
			return new HhOrganInfo();
		}
	}
	
	@Override
	public String getRolesByVcEmployeeId(String  vcEmployeeId) {
		StringBuffer hql = new StringBuffer();
		String result = "";
		hql.append("from SysUsersrole sra where sra.vcEmployeeId = '" + vcEmployeeId + "'");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List a = query.list();
		for(int i = 0 ; i < a.size(); i++ ){
			result = result + ((SysUsersrole)a.get(i)).getRoleId() + ","; 
		}
		if(Common.notEmpty(result)){
			result = result.substring(0,result.length()-1);
		}
		return result;
	}


	@Override
	public List<HhOrganInfo> getOrganInfos(String vcParentID) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from HhOrganInfo s where cflag=1");
		if(Common.notEmpty(vcParentID))	{
			hql.append(" and s.vcParentId = '"+vcParentID+ "' ");
		}
		
		hql.append(" order by s.ishowOrder ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * 获取某个人最高的数据权限
	 * @param vcEmployeeID
	 * @return
	 */
	public List getTopCompanyData(String vcEmployeeID)
	{
		StringBuilder  hql = new StringBuilder();
	
		if(Common.notEmpty(vcEmployeeID))	{
			hql.append(" select  DISTINCT a.company_id,vcShortName from (	 ");
			hql.append(" SELECT DISTINCT UR.role_id,RC.company_id,(ORG.orgLevel) AS orgLevel,ORG.vcShortName ");
			hql.append(" FROM sys_usersrole UR ");
			hql.append(" LEFT OUTER JOIN hh_rolecompany RC ON UR.role_id = RC.role_id ");
			hql.append(" LEFT OUTER JOIN v_organInfo ORG ON RC.company_id = ORG.nNodeID ");
			hql.append(" WHERE UR.vcEmployeeID = '"+vcEmployeeID+"' ");
			hql.append(" AND ORG.orgLevel = (SELECT MIN(orgLevel1) as orgLevel FROM  ");
			hql.append(" (SELECT DISTINCT UR1.role_id,RC1.company_id,(ORG1.orgLevel) AS orgLevel1 FROM sys_usersrole UR1 LEFT OUTER JOIN hh_rolecompany RC1 ON UR1.role_id = RC1.role_id LEFT OUTER JOIN v_organInfo ORG1 ON RC1.company_id = ORG1.nNodeID WHERE UR1.vcEmployeeID = '"+vcEmployeeID+"' ) A) ");
			hql.append(" ORDER BY UR.role_id,orgLevel,RC.company_id ) as a; ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List a=query.list();
			return a;
		}
		return new ArrayList();
	}

	/**
	 * 获取某个人(财务type=Base.financetype)最高的数据权限
	 * @param vcEmployeeID
	 * @param type
	 * @return
	 */
	public List getTopFinanceCompanyData(String vcEmployeeID,int type)
	{
		StringBuilder  hql = new StringBuilder();
		if(Common.notEmpty(vcEmployeeID))	{

			hql.append("select * from ( ");
			hql.append("SELECT DISTINCT b.company_id, c.vcFullName, length(vcOrganID) - length(REPLACE(vcOrganID, '-', '')) as companylevel ");
			hql.append("FROM sys_usersrole a, hh_rolefinancecompany b, hh_organInfo_tree_relation c WHERE a.role_id = b.role_id ");
			hql.append("AND b.company_id = c.nNodeID AND c.type = "+type+" AND c.cFlag = 1 AND a.vcEmployeeID = '"+vcEmployeeID+"' ) as tab ");
			hql.append("where tab.companylevel=( SELECT  min(length(vcOrganID) - length(REPLACE(vcOrganID, '-', ''))) as complevel ");
			hql.append("FROM sys_usersrole a, hh_rolefinancecompany b, hh_organInfo_tree_relation c ");
			hql.append("WHERE a.role_id = b.role_id AND b.company_id = c.nNodeID AND c.type = "+type+" AND c.cFlag = 1 AND a.vcEmployeeID = '"+vcEmployeeID+"') ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List a=query.list();
			return a;
		}
		return new ArrayList();
	}
	

	@Override
	public String getCompanyDataByNNodelID(String organal) {
		StringBuilder  hql = new StringBuilder();
		if(Common.notEmpty(organal))	{
			hql.append(" from HhOrganInfo where cflag=1 and nnodeId='"+organal+"' ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			HhOrganInfo a=(HhOrganInfo) query.uniqueResult();
			if(a != null && Common.notEmpty(a.getcLevel()))
			{
				String name="";
				String level=a.getcLevel()==null ? "" : a.getcLevel();
				while(!level.equals("1") && !level.equals("2") && !level.equals("3"))
				{
					a= getParentCompanyData(a.getVcParentId());
					if(a!=null)
					{
						level=a.getcLevel()==null ? "" : a.getcLevel();
					}
					else
						return "";
				}
				return a.getVcShortName();
			}
		}
		return "";
	}
	
	public HhOrganInfo getParentCompanyData(String vcOrganID) {
		StringBuilder  hql = new StringBuilder();
		if(Common.notEmpty(vcOrganID))	{
			
			hql.append(" from HhOrganInfo where cflag=1 and vcOrganId='"+vcOrganID+"' ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			HhOrganInfo a=(HhOrganInfo) query.uniqueResult();
			
			return a;
		}
		return null;
	}
	
	/**
	 * 获取该公司下所有子公司及其自身的主键拼接
	 * @param topcompanyID
	 * @return
	 */
	public String getAllChildrenOrganal(String topcompanyID)
	{
		if(Common.notEmpty(topcompanyID))
		{
			
			StringBuilder  hql = new StringBuilder();
			hql.append(" SELECT vcOrganID FROM `v_organInfo` where nNodeID='"+topcompanyID+"' ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			
			Object orgal=query.uniqueResult();
			if(orgal==null)
			{
				return "";
			}
			
			hql = new StringBuilder();
			
			hql.append(" select nNodeID from v_organInfo where vcParentID like '"+orgal+"%' or nNodeID='"+topcompanyID+"'  ");
			query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List a=query.list();
			String returndata="";
			for (Object object : a) {
				if(returndata.equals(""))
					returndata= (object==null? "": object.toString());
				else
					returndata=returndata+","+ object.toString();
			}
			return returndata;
			
		}
		return "";
	}
	
	/**
	 * 获取(财务type=Base.financetype)表中该公司下所有子公司及其自身的主键拼接
	 * @param topcompanyID
	 * @param type
	 * @return
	 */
	public String getAllChildrenFinanceOrganal(String topcompanyID,int type)
	{
		if(Common.notEmpty(topcompanyID))
		{
			
			StringBuilder  hql = new StringBuilder();
			hql.append(" SELECT nNodeID FROM hh_organInfo_tree_relation WHERE type="+type+" and  vcParentID LIKE (select CONCAT((SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE nNodeID = '"+topcompanyID+"' AND type ="+type+"), '%'))");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List a=query.list();
			String returndata=topcompanyID;
			for (Object object : a) {
					returndata=returndata+","+ object.toString();
			}
			return returndata;
			
		}
		return "";
	}
	
	
	public List<HhOrganInfo> getCompanyOrganInfos()
	{
		List<HhOrganInfo> result=new ArrayList<HhOrganInfo>();
		StringBuilder  hql = new StringBuilder();
		hql.append("select * from v_organInfo where cflag=1  ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		for (Object item : a) {
			Object[] b=(Object[]) item;
			HhOrganInfo info=new HhOrganInfo();
			info.setNnodeId(b[0]==null ? "":b[0].toString());
			info.setVcFullName(b[6]==null ? "":b[6].toString());
			result.add(info);
		}
		return result;
	}
	
	
	
	public List<HhOrganInfo> getTopChildrenCompanyOrganInfos(String authdata)
	{
		
		HhOrganInfo b=getOrganInfoById(Base.HRTop);
		List<HhOrganInfo> result=new ArrayList<HhOrganInfo>();
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" from HhOrganInfo where cflag=1 and (vcParentId='"+b.getVcOrganId()+"' or nnodeId= "+Base.HRTop+" ) and nnodeId in("+authdata+")  order by cLevel, iShowOrder");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List a= query.list();
		return a;
		
	}
	
	
	/**
	 * 根据类型获取对应类型的除顶节点之外的所有节点
	 * @param type
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getAllChildrenTreeOrganInfos(int type)
	{
		
		StringBuilder  hql = new StringBuilder();
		hql.append(" from HhOrganInfoTreeRelation where  cflag=1 and id.type="+type+" and id.nnodeId!='"+Base.HRTop+"' ORDER BY ishowOrder asc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * 根据类型和ID获取某个节点
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelation getTreeOrganInfoNode(int type,String nnodelD)
	{
		if(!Common.notEmpty(nnodelD))
			return null;
		StringBuilder  hql = new StringBuilder();
		
		hql.append("from HhOrganInfoTreeRelation where cflag=1 and id.type="+type+" and id.nnodeId='"+nnodelD+"'  ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (HhOrganInfoTreeRelation) query.uniqueResult();
	}
	
	/**
	 * 根据类型和ID获取某个节点所属业态
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelation getTreeOrganInfoNodeBusiness(int type,String nnodelD)
	{
		HhOrganInfoTreeRelation business= new HhOrganInfoTreeRelation();
		if(!Common.notEmpty(nnodelD))
			return null;
		StringBuilder  hql = new StringBuilder();
		hql.append("from HhOrganInfoTreeRelation where cflag=1 and id.type="+type+" and id.nnodeId='"+nnodelD+"'  ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		HhOrganInfoTreeRelation node= (HhOrganInfoTreeRelation) query.uniqueResult();
		
		StringBuilder  hql2 = new StringBuilder();
		if(Base.HRTopCompany.equals(node.getHrID()))//海航实业集团有限公司的业态是海航实业，其他的是实业的子一级
			hql2.append(" select a.nNodeID,a.vcFullName,a.type from hh_organInfo_tree_relation a,hh_organInfo_tree_relation b where a.type=b.type and a.type="+type+" and a.vcOrganID=b.vcParentID and b.nNodeID='"+nnodelD+"' ");
		else{
			hql2.append(" select a.nNodeID,a.vcFullName,a.type from hh_organInfo_tree_relation a where a.vcOrganID=( ");
			hql2.append(" SELECT  LEFT(vcOrganID,70) FROM `hh_organInfo_tree_relation` where nNodeID='"+nnodelD+"' and type="+type+" );");
		}
		query = sessionFactory.getCurrentSession().createSQLQuery(hql2.toString());
		Object a=query.uniqueResult();
		if(a!=null){
			Object[] b=(Object[]) a;
			
			HhOrganInfoTreeRelationId id=new HhOrganInfoTreeRelationId();
			id.setNnodeId(b[0]==null ? "":b[0].toString());
			id.setType(type);
			business.setId(id);
			business.setVcFullName(b[1]==null ? "" :b[1].toString());
			
		}
		return business;
		
	}
	
	/**
	 * 获取树的顶节点
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelation getTopTreeOrganInfoNode(int type)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("from HhOrganInfoTreeRelation where cflag=1 and id.type="+type+" and vcParentId='0-1-'  ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (HhOrganInfoTreeRelation) query.uniqueResult();
	}
	
	public HhOrganInfoTreeRelation getTopTreeOrganInfoNodeStr(int type,String str)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("from HhOrganInfoTreeRelation where cflag=1 and id.type="+type+" and nNodeID='"+str+"'  ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (HhOrganInfoTreeRelation) query.uniqueResult();
	}
	
	/**
	 * 根据类型和ID获取该节点的子节点
	 * @param type
	 * @param parentID 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HhOrganInfoTreeRelation> getChildrenTreeOrganInfos(int type,String parentID)
	{
		List<HhOrganInfoTreeRelation> result=new ArrayList<HhOrganInfoTreeRelation>();
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" from HhOrganInfoTreeRelation where cflag=1 and id.type="+type+" and vcParentId='"+parentID+"'  ORDER BY iShowOrder ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<HhOrganInfoTreeRelation> a= query.list();
		return a;
	}
	
	/**
	 * 根据类型和ID获取该节点的子节点(所有层级)
	 * @param type
	 * @param parentID 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HhOrganInfoTreeRelation> getAllChildrenTreeOrganInfos(int type,String parentID)
	{
		List<HhOrganInfoTreeRelation> result=new ArrayList<HhOrganInfoTreeRelation>();
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" from HhOrganInfoTreeRelation where cflag=1 and id.type="+type+" and vcParentId like '%"+parentID+"%'  ORDER BY vcshoworder ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<HhOrganInfoTreeRelation> a= query.list();
		return a;
	}
	
	
	public boolean saveHhOrganInfoTreeRelationcheck(int type,String vcFullName,String nNodeID)
	{
		if(!Common.notEmpty(vcFullName))
			return false;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from hh_organInfo_tree_relation  where 1=1 and cflag=1 and type ="+type +" and  vcFullName ='"+vcFullName+"'" ); 
		if(Common.notEmpty(nNodeID))
			hql.append(" and nNodeID!='"+nNodeID+"' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString())>0;
	}
	
	public String saveHhOrganInfoTreeRelationBimaCheck(String id,Integer bimaID,int type)
	{
		if(bimaID==null)
			return null;
		StringBuilder  hql = new StringBuilder();
		hql.append("select   GROUP_CONCAT(vcFullName)  from hh_organInfo_tree_relation  where 1=1 and cflag=1 and type ="+type +" and  bimaID ='"+bimaID+"'" ); 
		if(Common.notEmpty(id))
			hql.append(" and nNodeID!='"+id+"' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return (String ) query.uniqueResult();
	}
	
	public String saveHhOrganInfoTreeRelationBimaCheckInHistory(String id,Integer bimaID,int type,String time)
	{
		if(bimaID==null)
			return null;
		StringBuilder  hql = new StringBuilder();
		hql.append("select   GROUP_CONCAT(vcFullName)  from hh_organInfo_tree_relation_history  where 1=1 and type ="+type +" and  bimaID ='"+bimaID+"' and updatetime='" + time + "'" ); 
		if(Common.notEmpty(id))
			hql.append(" and nNodeID!='"+id+"' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return (String ) query.uniqueResult();
	}
	
	
	public String getOtherOrganAuthData(String nnodeID,int type)
	{
		HhOrganInfoTreeRelation a= getTreeOrganInfoNode(type, nnodeID);
		if(a==null || !Common.notEmpty(a.getVcOrganId()) )
			return "";
		StringBuilder  hql = new StringBuilder();
		hql.append(" from HhOrganInfoTreeRelation where cflag=1 and id.type="+type+" and vcParentId like '"+a.getVcOrganId()+"%'  ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<HhOrganInfoTreeRelation> child= query.list();
		String auth=nnodeID;
		for (HhOrganInfoTreeRelation item : child) {
				auth=auth+","+item.getId().getNnodeId();
		}
		
		return auth;
		
	}


	
	/**
	 * 根据节点ID和类型获取父节点ID
	 * @param nnodelID
	 * @param type
	 * @return
	 */
	public String getParentBynNodeID(String nnodelID,Integer type)
	{
		if(!Common.notEmpty(nnodelID))
			return "";
		StringBuilder  hql = new StringBuilder();
		if(type==null)
		{
			if(nnodelID.equals(Base.HRTop))
				return Base.HRTop;
			hql.append(" select nNodeID from hh_organInfo where vcOrganID =  ( select vcParentID from hh_organInfo where nNodeID='"+nnodelID+"')  ");
		}
		else
		{
			hql.append(" SELECT nNodeID FROM hh_organInfo_tree_relation WHERE type = "+type+" ");
			hql.append(" AND vcOrganID = ( CASE ( SELECT vcParentID FROM hh_organInfo_tree_relation 	WHERE type = "+type+" AND nNodeID = '"+nnodelID+"' ");
			hql.append("	) WHEN '0-1-' THEN ( SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE type = "+type+" ");
			hql.append(" AND vcParentID = '0-1-' ) ELSE (	SELECT vcParentID FROM hh_organInfo_tree_relation WHERE type = "+type+" AND nNodeID ='"+nnodelID+"')END)  ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return (String) query.uniqueResult();
	}
	
	
	/**
	 * 获取业态公司
	 * @param authdata
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getBusinessCompany(String authdata,Integer type)
	{
		List<HhOrganInfoTreeRelation> a=new ArrayList<HhOrganInfoTreeRelation>();
		StringBuilder  hql = new StringBuilder();
		if(type==null)
		{
			hql.append(" select  nNodeID,vcFullName from hh_organInfo where nNodeID in("+Common.dealinStr(authdata)+") and  ( vcParentID=(select vcOrganID from hh_organInfo where nNodeID='"+Base.HRTop+"') or nNodeID="+Base.HRTop+"  )and cFlag=1 and nNodeID!='"+Base.HRTopCompany+"' ORDER BY iShowOrder ");
		}
		else
		{
			hql.append(" SELECT nNodeID,vcFullName from hh_organInfo_tree_relation where cFlag=1 and nNodeID in("+Common.dealinStr(authdata)+") and type="+type+" and (vcParentID=(select vcOrganID from hh_organInfo_tree_relation where HrID="+Base.HRTop+" and type="+type+") or HrID="+Base.HRTop+"  )and HrID!='"+Base.HRTopCompany+"' ORDER BY vcParentID ,iShowOrder ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List list=query.list();
		for (Object item : list) {
			Object[] obj=(Object[]) item;
			HhOrganInfoTreeRelation node=new HhOrganInfoTreeRelation();
			HhOrganInfoTreeRelationId id=new HhOrganInfoTreeRelationId();
			id.setNnodeId(obj[0].toString());
			id.setType(type);
			node.setId(id);
			node.setVcFullName(obj[1].toString());
			a.add(node);
		}
		return a;
	}
	
	public List<HhOrganInfoTreeRelation> getBusinessCompanyForJudje(String org,Integer type){
		List<HhOrganInfoTreeRelation> a=new ArrayList<HhOrganInfoTreeRelation>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT nNodeID,vcFullName from hh_organInfo_tree_relation where cFlag=1 and nNodeID in("+Common.dealinStr(org)+") and type="+type);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List list=query.list();
		for (Object item : list) {
			Object[] obj=(Object[]) item;
			HhOrganInfoTreeRelation node=new HhOrganInfoTreeRelation();
			HhOrganInfoTreeRelationId id=new HhOrganInfoTreeRelationId();
			id.setNnodeId(obj[0].toString());
			id.setType(type);
			node.setId(id);
			node.setVcFullName(obj[1].toString());
			a.add(node);
		}
		return a;
	}
	
	

	/**
	 * 获取业态公司
	 * @param authdata
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getBusinessCompany(String authdata,Integer type,String topNNodeID)
	{
		List<HhOrganInfoTreeRelation> a=new ArrayList<HhOrganInfoTreeRelation>();
		StringBuilder  hql = new StringBuilder();
		if(type==null)
		{
			hql.append(" select  nNodeID,vcFullName from hh_organInfo where nNodeID in("+Common.dealinStr(authdata)+") and  ( vcParentID=(select vcOrganID from hh_organInfo where nNodeID='"+Base.HRTop+"') or nNodeID="+Base.HRTop+"  )and cFlag=1 and nNodeID!='"+Base.HRTopCompany+"' ORDER BY iShowOrder ");
		}
		else
		{
			hql.append(" SELECT nNodeID,vcFullName from hh_organInfo_tree_relation where cFlag=1 and nNodeID in("+Common.dealinStr(authdata)+") and type="+type+" and ( vcParentID = (select vcOrganID from hh_organInfo_tree_relation where nNodeID= '"+topNNodeID+"' and type="+type+") or HrID="+Base.HRTop+"  )and (HrID!='"+Base.HRTopCompany+"' or HrID is null)ORDER BY vcParentID ,iShowOrder ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List list=query.list();
		for (Object item : list) {
			Object[] obj=(Object[]) item;
			HhOrganInfoTreeRelation node=new HhOrganInfoTreeRelation();
			HhOrganInfoTreeRelationId id=new HhOrganInfoTreeRelationId();
			id.setNnodeId(obj[0].toString());
			id.setType(type);
			node.setId(id);
			node.setVcFullName(obj[1].toString());
			a.add(node);
		}
		return a;
	}
	
	
	

	@Override
	public List<HhOrganInfoTreeRelation> getPublicCompany(String str) {
		// TODO Auto-generated method stub
		str=getAllChildrenFinanceOrganal(str,Base.financetype);
		String sql = "select nNodeID,vcFullName from report_public_company where vcOrganID in(" + Common.dealinStr(str)  + ") and cFlag = '1' ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		List<HhOrganInfoTreeRelation> publicCompany = new ArrayList<HhOrganInfoTreeRelation>();
		for (Object[] company : list ) {
			HhOrganInfoTreeRelation a = new HhOrganInfoTreeRelation();
			HhOrganInfoTreeRelationId b = new HhOrganInfoTreeRelationId();
			b.setNnodeId(company[0].toString());
			a.setId(b);
			a.setVcFullName(company[1].toString());
			publicCompany.add(a);
		}
		return publicCompany;
	}


	@Override
	public HhOrganInfoTreeRelation getThePublicCompany(String org) {
		String sql = "select a.nNodeID,b.vcOrganID,a.vcFullName,b.vcParentID from report_public_company a, hh_organInfo_tree_relation b where a.vcOrganID=b.nNodeID and b.type="+Base.financetype+" and a.nNodeID='"+org+"'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		Object obj = (Object)query.uniqueResult();
		HhOrganInfoTreeRelation a = new HhOrganInfoTreeRelation();
		if(obj!=null){
			Object[] b=(Object[])obj;
			HhOrganInfoTreeRelationId id=new HhOrganInfoTreeRelationId();
			id.setNnodeId(b[0]==null? "":b[0].toString());
			a.setVcFullName(b[2]==null? "":b[2].toString());
			a.setVcOrganId(b[1]==null? "":b[1].toString());
			a.setVcParentId(b[3]==null? "":b[3].toString());
		}
		return a;
	}


	@Override
	public String getDataauth(String str) {
		// TODO Auto-generated method stub
		if(Common.notEmpty(str))
		{
			
			StringBuilder  hql = new StringBuilder();
			String [] dd = str.split(",");
			
			hql.append(" select nNodeID from hh_organInfo where cFlag = 1 and ( vcOrganID ");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					hql.append("  like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append("  like '%-"+dd[i]+ "-%' or vcOrganID");
				}
			}
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List a=query.list();
			String returndata="";
			for (Object object : a) {
				if(returndata.equals(""))
					returndata= (object==null? "": object.toString());
				else
					returndata=returndata+","+ object.toString();
			}
			if(a == null || a.size()<=0)
				returndata = "0";
			return returndata;
			
		}
		return "0";
	}


	@Override
	public Object getDescription(Integer id) {
		// TODO Auto-generated method stub
		String sql = " SELECT description FROM hh_base WHERE ID = "+id;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.uniqueResult();
	}


	@Override
	public String getTopNnodeID(Integer financetype) {
		// TODO Auto-generated method stub
		String sql = " select nNodeID from hh_organInfo_tree_relation where hrID = '"+Base.HRTop+"' and cFlag = '1' and type = "+ financetype;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.uniqueResult().toString();
	}


	@Override
	public String getFinanceCompName(String nNodeID, Integer financetype) {
		// TODO Auto-generated method stub
		String sql = " select vcFullName from hh_organInfo_tree_relation where nNodeID = '" + nNodeID + "' and cFlag = '1' and type = " + financetype;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.uniqueResult().toString();
	}
	
	@Override
	public String getFinancenNodeID(String vcFullName, Integer financetype) {
		// TODO Auto-generated method stub
		String sql = " select nNodeID from hh_organInfo_tree_relation where vcFullName = '" + vcFullName + "' and cFlag = '1' and type = " + financetype;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (query.uniqueResult() == null) {
			return null;
		}
		return query.uniqueResult().toString();
	}
	
	
	/**
	 * 获取财务树最后修改时间
	 * @return
	 */
	@Override
	public String getFinancialTreeLastModifyTime(){
		//SELECT MAX(t.`lastModifyDate`) FROM hh_organInfo_tree_relation t
		String sql=" select max(t.lastModifyDate) from hh_organInfo_tree_relation t ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (query.uniqueResult() == null) {
			return null;
		}
		return query.uniqueResult().toString();
		
	}


	@Override
	public String getPublicNoVirtualCompany(String org) {

		String sql = "select nNodeID from hh_organInfo_tree_relation where status in (50500,50502) and cFlag = '1'  and nNodeID in( "+ Common.dealinStr(org) + ")";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List a=query.list();
		String returndata="";
		for (Object object : a) {
			if(returndata.equals(""))
				returndata= (object==null? "": object.toString());
			else
				returndata=returndata+","+ object.toString();
		}
		return returndata;
	}


	@Override
	public String getHrorginfoParentNnodeId(String org) {
		// TODO Auto-generated method stub
		String sql=" SELECT nNodeID from hh_organInfo where vcOrganID = (SELECT vcParentID FROM `hh_organInfo` where nNodeID = "+org+") ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (query.uniqueResult() == null) {
			return null;
		}
		return query.uniqueResult().toString();
	}
	
	@Override
	public List<HhOrganInfo> getAuthorCompanyList(String vcOrganID){
		if(!Common.notEmpty(vcOrganID))
			return new ArrayList<HhOrganInfo>();
		
		String[] strArr = vcOrganID.split(",");
		String sql = " from HhOrganInfo h ";
		for(int i = 0;i<strArr.length;i++){
			if(i==0){
				sql+=" where h.vcOrganId like '%"+strArr[i]+"%'";
			}else{
				sql+=" or h.vcOrganId like '%"+strArr[i]+"%'";
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		List a= query.list();
		return a;
	}


	@Override
	public String getHrOrginfoNameBynnodeID(String nnodeID) {
		// TODO Auto-generated method stub
		String sql=" SELECT vcFullName from hh_organInfo where nNodeID =  "+nnodeID;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (query.uniqueResult() == null) {
			return null;
		}
		return query.uniqueResult().toString();
	}


	@Override
	public String getHrOrginfoNameByOrgID(String vcOrganID) {
		// TODO Auto-generated method stub
		String sql=" SELECT vcFullName from hh_organInfo where vcOrganId =  "+vcOrganID;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (query.uniqueResult() == null) {
			return null;
		}
		return query.uniqueResult().toString();
	}


	@Override
	public HhUsers getEmployeeByName(String username) {
		StringBuffer hql = new StringBuffer();
		if(username != null && !username.equals("")){
			hql.append("from HhUsers t where t.vcAccount = '"+username+"'");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			List list = query.list();
			if(list.size() > 0){
				return (HhUsers)list.get(0);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	
	/**
	 * 获取所有外部对标企业
	 * @param organID
	 * @param time
	 * @param type
	 * @return
	 */
	public List<OutCompareCompany> getAllOutCompareCompanyList()
	{
		List<OutCompareCompany> a=new ArrayList<OutCompareCompany>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT DISTINCT org,orgname FROM hh_outcomparecompany");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List b=query.list();
		for (Object object : b) {
			Object[] obj=(Object[]) object;
			OutCompareCompany item=new OutCompareCompany();
			
			item.setOutorg(obj[0].toString());
			item.setOutorgNm(obj[1].toString());	
			
			a.add(item);
		}	
		return a;
	}
	
	/**
	 * 获取外部对标对比企业
	 * @param organID
	 * @param time
	 * @param type
	 * @return
	 */
	public List<OutCompareCompany> getOutCompareCompanyList(String organID)
	{
		List<OutCompareCompany> a=new ArrayList<OutCompareCompany>();
		if(Common.notEmpty(organID)){
			StringBuilder  hql = new StringBuilder();
			hql.append("SELECT org,orgname,financeorg,financeorgname FROM hh_outcomparecompany where financeorg in("+Common.dealinStr(organID)+")");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List b=query.list();
			for (Object object : b) {
				Object[] obj=(Object[]) object;
				OutCompareCompany item=new OutCompareCompany();
				
				item.setOutorg(obj[0].toString());
				item.setOutorgNm(obj[1].toString());	
				item.setPublicorg(obj[2].toString());
				item.setPublicorgNm(obj[3].toString());
				a.add(item);
			}	
		}
		
		return a;
	}
	

	/**
	 * 获取该节点下能看到的上市公司列表
	 * @param organID
	 * @param time
	 * @param type
	 * @return
	 */
	public List<PublicCompany> getPublicCompanyList(String organID,String time,int type)
	{
		List<PublicCompany> publiccompany=new ArrayList<PublicCompany>();
		if(Common.notEmpty(time))
			time=getTimeFromHistory(time);
		else
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM");
			time=getTimeFromHistory(df.format(new Date()));
		}
		if(Common.notEmpty(organID))	{
			StringBuilder  hql = new StringBuilder();
			hql.append("SELECT a.nNodeID as publicorg,a.vcFullName as publicorgNm,b.nNodeID as inorg,b.vcFullName as inorgNm,b.vcshoworder FROM `hh_public_company_history` a" +
					", hh_organInfo_tree_relation_history b  where a.vcOrganID like '%-"+organID+"-%' and a.updatetime=b.updatetime and b.type="+type+" " +
					"and b.nNodeID=replace( right(a.vcOrganID,33),'-','') and a.updatetime='"+time+"' ORDER BY b.vcshoworder");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List list=query.list();
			for (Object object : list) {
				Object[] obj=(Object[]) object;
				PublicCompany item=new PublicCompany();
				item.setPublicorg(obj[0].toString());
				item.setPublicorgNm(obj[1].toString());
				item.setInorg(obj[2].toString());
				item.setInorgNm(obj[3].toString());
				publiccompany.add(item);
			}
		}
		return publiccompany;
	}
	
	
	
	/**
	 * 修正到合适的时间（选的过小，调整到最小时间，选的过大调整到最大时间，选的时间在最大最小之间调整到最近的时间）
	 * @param time
	 * @return
	 */
	public String getTimeFromHistory(String time)
	{
		
		StringBuilder  hql = new StringBuilder();
		hql.append(" select case when (SELECT max(updatetime) FROM hh_organInfo_tree_relation_history  ");
		hql.append("WHERE type="+Base.financetype+" and updatetime <= '"+time+"') is null then (SELECT min(updatetime) FROM hh_organInfo_tree_relation_history where type="+Base.financetype+") else (SELECT max(updatetime) ");
		hql.append("FROM hh_organInfo_tree_relation_history WHERE updatetime <= '"+time+"' and type="+Base.financetype+") end ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Object a=query.uniqueResult();
		if(a!=null)
			return a.toString();
		else
			return "";
	}
	
}

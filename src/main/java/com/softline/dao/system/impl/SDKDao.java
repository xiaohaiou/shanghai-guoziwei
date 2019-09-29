package com.softline.dao.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.system.ISDKDao;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhSdkLog;
import com.softline.entity.HhUsers;
import com.softline.entity.HhUsersmessageinfo;
import com.softline.entity.HhUserspostrecord;
import com.softline.entity.SdkEmpMessageInfo;
import com.softline.entity.SdkEmpPostRecord;
import com.softline.entity.SdkEmpdirectory;
import com.softline.entity.SdkOrganInfo;
import com.softline.entityTemp.SDK_empMessageInfo;
import com.softline.entityTemp.SDK_empPostRecord;
import com.softline.entityTemp.SDK_empdirector;
import com.softline.entityTemp.SDK_organinfo;

@Repository(value = "sdkDao")
public class SDKDao implements ISDKDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HhSdkLog> getlist(String StartTime, String EndTime) {
		String hql = "from HhSdkLog s where 1=1 ";
		if (StartTime != null && !StartTime.equals(""))
			hql += "and s.createDate >='" + StartTime + "'";
		if (EndTime != null && !EndTime.equals(""))
			hql += " and s.createDate<='" + EndTime + "'";
		hql += "  order by createDate desc,type";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 校验组织机构是否已经存在
	 * 
	 * @return id，如果不存在返回null
	 */
	public HhOrganInfo checkOrganInfoRepeat(String nnodeId) {
		String hql = "from HhOrganInfo s where  s.nnodeId ='" + nnodeId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		HhOrganInfo OrganInfo = (HhOrganInfo) query.uniqueResult();
		return OrganInfo;
	}

	public void deleteOrganInfoRepeat(List<SDK_organinfo> entities) {
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from hh_organInfo where nNodeID in (");
		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = entities.size();
			for (a = 0; a < b; a++) {
				SDK_organinfo item = entities.get(a);
				hql.append("'" + item.getnNodeID() + "'");
				if (a + 1 < b)
					hql.append(",");
			}
			hql.append(")");

			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 校验是否已经存在
	 * 
	 * @return id，如果不存在返回null
	 */
	@Override
	public HhUsers checkUsersRepeat(String vcEmployeeId) {
		String hql = "from HhUsers s where isOut=false and s.vcEmployeeId = '" + vcEmployeeId
				+ "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		HhUsers user = (HhUsers) query.uniqueResult();
		return user;
	}

	public void deleteUsersRepeat(List<SDK_empdirector> entities) {
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from hh_users where isOut=false and vcEmployeeID in (");
		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = entities.size();
			for (a = 0; a < b; a++) {
				SDK_empdirector item = entities.get(a);
				hql.append("'" + item.getVcEmployeeID() + "'");
				if (a + 1 < b)
					hql.append(",");
			}
			hql.append(")");

			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 校验人员联系方式是否已经存在
	 * 
	 * @return id，如果不存在返回null
	 */
	public HhUsersmessageinfo checkmessageinfoRepeat(String vcEmployeeId) {
		String hql = "from HhUsersmessageinfo s where  s.vcEmployeeId ='"
				+ vcEmployeeId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		HhUsersmessageinfo Usersmessageinfo = (HhUsersmessageinfo) query
				.uniqueResult();
		return Usersmessageinfo;
	}

	public void deleteMessageinfoRepeat(List<SDK_empMessageInfo> entities) {
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from hh_usersmessageinfo where vcEmployeeID in (");
		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = entities.size();
			for (a = 0; a < b; a++) {
				SDK_empMessageInfo item = entities.get(a);
				hql.append("'" + item.getVcEmployeeID() + "'");
				if (a + 1 < b)
					hql.append(",");
			}
			hql.append(")");

			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 校验人员任职记录是否已经存在
	 * 
	 * @return id，如果不存在返回null
	 */
	public HhUserspostrecord checkPostrecordRepeat(String vcEmployeeId,
			String iPostRecordID) {
		String hql = "from HhUserspostrecord s where  s.vcEmployeeId ='"
				+ vcEmployeeId + "'  and s.ipostRecordId=" + iPostRecordID;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		HhUserspostrecord Userspostrecord = (HhUserspostrecord) query
				.uniqueResult();
		return Userspostrecord;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object> checkPostrecordRepeat1(List<SDK_empPostRecord> entities) {
		List<String> sdk_vcEmployeeId = new ArrayList<String>();
		List<String> sdk_vcEmployeeId_new = new ArrayList<String>();
		int a;
		int b = entities.size();
		for( a = 0 ; a < b ; a++){
			SDK_empPostRecord sdk_temp = entities.get(a);
			sdk_vcEmployeeId.add(sdk_temp.getVcEmployeeID());
		}
		Set<String> set = new  HashSet<String>(); 
		for (String cd:sdk_vcEmployeeId) {
            if(set.add(cd)){
            	sdk_vcEmployeeId_new.add(cd);
            }
        }
		StringBuffer hql = new StringBuffer();
		hql.append(" select id,vcEmployeeID,iPostRecordID  from hh_userspostrecord where  vcEmployeeID in (");
		int c;
		int d = sdk_vcEmployeeId_new.size();
		for (c = 0 ; c < d ; c++) {
			String item = sdk_vcEmployeeId_new.get(c);
			hql.append("'"+item+"'");
			if (c + 1 < d){
				hql.append(" , ");
			}
		}
		hql.append(")");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
		
	}
	
	public void deletePostrecordRepeat(List<SDK_empPostRecord> entities, List<Object> old_Userspostrecord) {
		
		StringBuffer vcEmployeeID = new StringBuffer();
		int a;
		int b = entities.size();
		for( a = 0 ; a < b ; a ++){
			SDK_empPostRecord new_postrecord = entities.get(a);
			String new_vcEmployeeId = new_postrecord.getVcEmployeeID();
			String new_ipostRecordId = new_postrecord.getiPostRecordID();
			Iterator<Object> it = old_Userspostrecord.iterator();  
		    while(it.hasNext()){
		    	Object[] old_postrecord = (Object[]) it.next(); 
				String old_id = old_postrecord[0].toString();
				String old_vcEmployeeId = old_postrecord[1].toString();
				String old_ipostRecordId = old_postrecord[2].toString();
				if( new_vcEmployeeId.equals(old_vcEmployeeId) && new_ipostRecordId.equals(old_ipostRecordId) ){
					vcEmployeeID.append("'"+old_id+"',");
					it.remove(); //移除该对象
					break;
				}
		    }
		}
		
		if(Common.notEmpty(vcEmployeeID.toString())){
			String hql = " delete from hh_userspostrecord where id  in ("+vcEmployeeID.toString().substring(0, vcEmployeeID.length()-1)+")";
			try {
				ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
						.getConnectionProvider();
				Connection conn = cp.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pst = conn.prepareStatement("");
				pst.addBatch(hql);
				// 执行操作
				pst.executeBatch();
				// 提交事务
				conn.commit();
				// 清空上一次添加的数据
				hql = "";
				vcEmployeeID = new StringBuffer();
				// 头等连接
				pst.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public Integer saveSdkandHHOrganInfo(List<SDK_organinfo> entities,
			HhUsers hhUsers, String OperateTime) {
		int rightRow = 0;
		int totoalRow = entities.size();
		List<SdkOrganInfo> sdkadd = new ArrayList<SdkOrganInfo>();
		List<HhOrganInfo> hhadd = new ArrayList<HhOrganInfo>();
		try {
			// 删除已经存在的组织列表（正式表）
			deleteOrganInfoRepeat(entities);

			for (SDK_organinfo item : entities) {
				// 存临时
				sdkadd.add(saveOrganInfoTemp(item, hhUsers, OperateTime));
				// 存正式
				hhadd.add(saveOrganInfo(item, hhUsers, OperateTime));
				// 计数
				rightRow++;
				System.out.println(hhadd.size() + "," + rightRow + ","
						+ totoalRow);
				if (sdkadd.size() > 1000 || rightRow == totoalRow) {
					// 插入临时表
					if (sdkadd != null && sdkadd.size() > 0) {
						createsdkOrganInfoSql(sdkadd);
						sdkadd.clear();
					}
					// 插入正式表
					if (hhadd != null && hhadd.size() > 0) {
						createhhOrganInfoSql(hhadd);
						hhadd.clear();
					}
				}
			}
		} catch (HibernateException e) {
			// tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return rightRow;

	}

	private void createsdkOrganInfoSql(List<SdkOrganInfo> sdkadd) {
		StringBuffer hql = new StringBuffer();
		hql.append(" INSERT INTO `sdk_organinfo` (`nNodeID`, `vcOrganID`, `vcParentID`, `cFlag`, `iShowOrder`, `vcFullName`, `vcShortName`, `vcshoworder`, `dOperatorDate`, `createDate`, `createPersonID`, `createPersonName`, `lastModifyPersonId`, `lastModifyPersonName`, `lastModifyDate`) VALUES ");
		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = sdkadd.size();
			for (a = 0; a < b; a++) {
				SdkOrganInfo item = sdkadd.get(a);
				hql.append("('" + item.getNnodeId() + "','"
						+ item.getVcOrganId() + "','" + item.getVcParentId()
						+ "','" + item.getCflag() + "','"
						+ item.getIshowOrder() + "','" + item.getVcFullName()
						+ "','" + item.getVcShortName() + "','"
						+ item.getVcshoworder() + "','"
						+ item.getDoperatorDate() + "','"
						+ item.getCreateDate() + "','"
						+ item.getCreatePersonId() + "','"
						+ item.getCreatePersonName() + "','"
						+ item.getLastModifyPersonId() + "','"
						+ item.getLastModifyPersonName() + "','"
						+ item.getLastModifyDate() + "')");
				if (a + 1 < b)
					hql.append(",");
			}
			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createhhOrganInfoSql(List<HhOrganInfo> hhadd) {
		StringBuffer hql = new StringBuffer();
		hql.append(" INSERT INTO `hh_organInfo` (`nNodeID`, `vcOrganID`, `vcParentID`, `cFlag`, `iShowOrder`, `vcFullName`, `vcShortName`, `vcshoworder`, `dOperatorDate`, `createDate`, `createPersonID`, `createPersonName`, `lastModifyPersonId`, `lastModifyPersonName`, `lastModifyDate`) VALUES ");
		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = hhadd.size();
			for (a = 0; a < b; a++) {
				HhOrganInfo item = hhadd.get(a);
				hql.append("('" + item.getNnodeId() + "','"
						+ item.getVcOrganId() + "','" + item.getVcParentId()
						+ "','" + item.getCflag() + "','"
						+ item.getIshowOrder() + "','" + item.getVcFullName()
						+ "','" + item.getVcShortName() + "','"
						+ item.getVcshoworder() + "','"
						+ item.getDoperatorDate() + "','"
						+ item.getCreateDate() + "','"
						+ item.getCreatePersonId() + "','"
						+ item.getCreatePersonName() + "','"
						+ item.getLastModifyPersonId() + "','"
						+ item.getLastModifyPersonName() + "','"
						+ item.getLastModifyDate() + "')");
				if (a + 1 < b)
					hql.append(",");
			}
			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private SdkOrganInfo saveOrganInfoTemp(SDK_organinfo data, HhUsers hhUsers,
			String OperateTime) {
		SdkOrganInfo sdkOrganInfo = SdkOrganInfo.ConvertTo(data);
		sdkOrganInfo.setCreateDate(OperateTime);
		sdkOrganInfo.setCreatePersonId(hhUsers.getVcEmployeeId());
		sdkOrganInfo.setCreatePersonName(hhUsers.getVcName());
		sdkOrganInfo.setLastModifyDate(OperateTime);
		sdkOrganInfo.setLastModifyPersonId(hhUsers.getVcEmployeeId());
		sdkOrganInfo.setLastModifyPersonName(hhUsers.getVcName());
		return sdkOrganInfo;
	}

	private HhOrganInfo saveOrganInfo(SDK_organinfo data, HhUsers hhUsers,
			String OperateTime) {
		HhOrganInfo hhOrganInfo = new HhOrganInfo();
		hhOrganInfo.setNnodeId(data.getnNodeID());
		hhOrganInfo = HhOrganInfo.ConvertTo(hhOrganInfo, data);
		hhOrganInfo.setCreateDate(OperateTime);
		hhOrganInfo.setCreatePersonId(hhUsers.getVcEmployeeId());
		hhOrganInfo.setCreatePersonName(hhUsers.getVcName());
		return hhOrganInfo;
	}

	@Override
	public Integer saveSdkandHHuser(List<SDK_empdirector> entities,
			HhUsers hhUsers, String OperateTime) {
		int rightRow = 0;
		int totoalRow = entities.size();
		List<SdkEmpdirectory> sdkadd = new ArrayList<SdkEmpdirectory>();
		List<HhUsers> hhadd = new ArrayList<HhUsers>();

		try {
			// 删除已经存在的用户
			deleteUsersRepeat(entities);
			for (SDK_empdirector item : entities) {
				// 存临时
				sdkadd.add(saveEmpDirectoryTemp(item, hhUsers, OperateTime));

				hhadd.add(saveEmpDirectory(item, hhUsers, OperateTime));

				// 计数
				rightRow++;
				System.out.println(hhadd.size() + "," + rightRow + ","
						+ totoalRow);
				if (sdkadd.size() > 1000 || rightRow == totoalRow) {
					// 插入临时表
					if (sdkadd != null && sdkadd.size() > 0) {
						createsdkuserSql(sdkadd);
						sdkadd.clear();
					}
					// 插入正式表
					if (hhadd != null && hhadd.size() > 0) {
						createhhuserSql(hhadd);
						hhadd.clear();
					}
				}
			}
		} catch (HibernateException e) {
			// tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return rightRow;

	}

	private void createsdkuserSql(List<SdkEmpdirectory> sdkadd) {
		StringBuffer hql = new StringBuffer();
		hql.append(" INSERT INTO `sdk_empdirectory` (`vcEmployeeID`, `nNodeID`, `vcName`, `vcAccount`, `cSex`, `cFlag`, `dOperatorDate`, `vcOrganID`, `vcFullName`, `nAdminLevelID`, `vcAdminLevelName`, `cIfManageLeader`, `tbL_nNodeID`, `vcName1`, `vcSecondName`, `createDate`, `createPersonID`, `createPersonName`, `lastModifyPersonId`, `lastModifyPersonName`, `lastModifyDate`) VALUES ");

		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = sdkadd.size();
			for (a = 0; a < b; a++) {
				SdkEmpdirectory item = sdkadd.get(a);
				hql.append("('" + item.getVcEmployeeId() + "','"
						+ item.getNnodeId() + "','" + item.getVcName() + "','"
						+ item.getVcAccount() + "','" + item.getCsex() + "','"
						+ item.getCflag() + "','" + item.getDoperatorDate()
						+ "','" + item.getVcOrganId() + "','"
						+ item.getVcFullName() + "','"
						+ item.getNadminLevelId() + "','"
						+ item.getVcAdminLevelName() + "','"
						+ item.getCifManageLeader() + "','"
						+ item.getTbLNNodeId() + "','" + item.getVcName1()
						+ "','" + item.getVcSecondName() + "','"
						+ item.getCreateDate() + "','"
						+ item.getCreatePersonId() + "','"
						+ item.getCreatePersonName() + "','"
						+ item.getLastModifyPersonId() + "','"
						+ item.getLastModifyPersonName() + "','"
						+ item.getLastModifyDate() + "')");
				if (a + 1 < b)
					hql.append(",");
			}
			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createhhuserSql(List<HhUsers> hhadd) {
		StringBuffer hql = new StringBuffer();
		hql.append(" INSERT INTO hh_users (`vcEmployeeID`, `nNodeID`, `vcName`, `vcAccount`, `cSex`, `cFlag`, `dOperatorDate`, `vcOrganID`, `vcFullName`, `nAdminLevelID`, `vcAdminLevelName`, `cIfManageLeader`, `tbL_nNodeID`, `vcName1`, `vcSecondName`, `createDate`, `createPersonID`, `createPersonName`, `lastModifyPersonId`, `lastModifyPersonName`, `lastModifyDate`,isOut) VALUES");
		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = hhadd.size();
			for (a = 0; a < b; a++) {
				HhUsers item = hhadd.get(a);
				hql.append("('" + item.getVcEmployeeId() + "','"
						+ item.getNnodeId() + "','" + item.getVcName() + "','"
						+ item.getVcAccount() + "','" + item.getCsex() + "','"
						+ item.getCflag() + "','" + item.getDoperatorDate()
						+ "','" + item.getVcOrganId() + "','"
						+ item.getVcFullName() + "','"
						+ item.getNadminLevelId() + "','"
						+ item.getVcAdminLevelName() + "','"
						+ item.getCifManageLeader() + "','"
						+ item.getTbLNNodeId() + "','" + item.getVcName1()
						+ "','" + item.getVcSecondName() + "','"
						+ item.getCreateDate() + "','"
						+ item.getCreatePersonId() + "','"
						+ item.getCreatePersonName() + "','"
						+ item.getLastModifyPersonId() + "','"
						+ item.getLastModifyPersonName() + "','"
						+ item.getLastModifyDate() + "',false)");
				if (a + 1 < b)
					hql.append(",");
			}
			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 存入临时
	 * 
	 * @param data
	 * @param hhUsers
	 * @param operateTime
	 * @return
	 */
	private SdkEmpdirectory saveEmpDirectoryTemp(SDK_empdirector data,
			HhUsers hhUsers, String operateTime) {
		SdkEmpdirectory sdkEmpdirector = SdkEmpdirectory.ConvertTo(data);
		sdkEmpdirector.setCreateDate(operateTime);
		sdkEmpdirector.setCreatePersonId(hhUsers.getVcEmployeeId());
		sdkEmpdirector.setCreatePersonName(hhUsers.getVcName());
		sdkEmpdirector.setLastModifyDate(operateTime);
		sdkEmpdirector.setLastModifyPersonId(hhUsers.getVcEmployeeId());
		sdkEmpdirector.setLastModifyPersonName(hhUsers.getVcName());
		return sdkEmpdirector;
	}

	/**
	 * 存入正式
	 * 
	 * @param item
	 * @param Users
	 * @param operateTime
	 * @return
	 */
	private HhUsers saveEmpDirectory(SDK_empdirector item, HhUsers Users,
			String operateTime) {
		HhUsers hhUser = new HhUsers();
		hhUser = HhUsers.ConvertTo(hhUser, item);
		hhUser.setCreateDate(operateTime);
		hhUser.setCreatePersonId(Users.getVcEmployeeId());
		hhUser.setCreatePersonName(Users.getVcName());
		return hhUser;
	}

	
	@Override
	public Integer saveSdkandHHMessageInfo(List<SDK_empMessageInfo> entities,
			String OperateTime, HhUsers hhUsers) {
		int rightRow = 0;
		int totoalRow = entities.size();
		List<SdkEmpMessageInfo> sdkadd = new ArrayList<SdkEmpMessageInfo>();
		List<HhUsersmessageinfo> hhadd = new ArrayList<HhUsersmessageinfo>();

		try {
			// 删除已经存在的组织列表（正式表）
			deleteMessageinfoRepeat(entities);
			for (SDK_empMessageInfo item : entities) {
				sdkadd.add(saveEmpMessageInfoTemp(item, hhUsers, OperateTime));
				// 存正式
				hhadd.add(saveEmpMessageInfo(item, hhUsers, OperateTime));

				// 计数
				rightRow++;
				System.out.println(hhadd.size() + "," + rightRow + ","
						+ totoalRow);

				if (sdkadd.size() > 1000 || rightRow == totoalRow) {
					// 插入临时表
					if (sdkadd != null && sdkadd.size() > 0) {
						createsdkMessageinfoSql(sdkadd);
						sdkadd.clear();
					}
					// 插入正式表
					if (hhadd != null && hhadd.size() > 0) {
						createhhMessageinfoSql(hhadd);
						hhadd.clear();
					}
				}
			}

		} catch (HibernateException e) {
			// tx.rollback();
			e.printStackTrace();
		} finally {

		}
		return rightRow;
	}

	private void createsdkMessageinfoSql(List<SdkEmpMessageInfo> sdkadd) {
		StringBuffer hql = new StringBuffer();
		hql.append(" INSERT INTO sdk_empmessageinfo ( `vcEmployeeID`, `vcEmail`, `vcWorkPhone`, `vcMobile`, `dOperatorDate`, `createDate`, `createPersonID`, `createPersonName`, `lastModifyPersonId`, `lastModifyPersonName`, `lastModifyDate`) VALUES ");
		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = sdkadd.size();
			for (a = 0; a < b; a++) {
				SdkEmpMessageInfo item = sdkadd.get(a);
				hql.append("('" + item.getVcEmployeeId() + "','"
						+ item.getVcEmail() + "','" + item.getVcWorkPhone()
						+ "','" + item.getVcMobile() + "','"
						+ item.getDoperatorDate() + "','"
						+ item.getCreateDate() + "','"
						+ item.getCreatePersonId() + "','"
						+ item.getCreatePersonName() + "','"
						+ item.getLastModifyPersonId() + "','"
						+ item.getLastModifyPersonName() + "','"
						+ item.getLastModifyDate() + "')");
				if (a + 1 < b)
					hql.append(",");
			}
			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createhhMessageinfoSql(List<HhUsersmessageinfo> hhadd) {
		StringBuffer hql = new StringBuffer();
		hql.append(" INSERT INTO hh_usersmessageinfo (`vcEmployeeID`, `vcEmail`, `vcWorkPhone`, `vcMobile`, `dOperatorDate`, `createDate`, `createPersonID`, `createPersonName`, `lastModifyPersonId`, `lastModifyPersonName`, `lastModifyDate`) VALUES ");
		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = hhadd.size();
			for (a = 0; a < b; a++) {
				HhUsersmessageinfo item = hhadd.get(a);
				hql.append("('" + item.getVcEmployeeId() + "','"
						+ item.getVcEmail() + "','" + item.getVcWorkPhone()
						+ "','" + item.getVcMobile() + "','"
						+ item.getDoperatorDate() + "','"
						+ item.getCreateDate() + "','"
						+ item.getCreatePersonId() + "','"
						+ item.getCreatePersonName() + "','"
						+ item.getLastModifyPersonId() + "','"
						+ item.getLastModifyPersonName() + "','"
						+ item.getLastModifyDate() + "')");
				if (a + 1 < b)
					hql.append(",");
			}
			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private SdkEmpMessageInfo saveEmpMessageInfoTemp(SDK_empMessageInfo data,
			HhUsers hhUsers, String OperateTime) {
		SdkEmpMessageInfo EmpMessageInfo = SdkEmpMessageInfo.ConvertTo(data);
		EmpMessageInfo.setCreateDate(OperateTime);
		EmpMessageInfo.setCreatePersonId(hhUsers.getVcEmployeeId());
		EmpMessageInfo.setCreatePersonName(hhUsers.getVcName());
		EmpMessageInfo.setLastModifyDate(OperateTime);
		EmpMessageInfo.setLastModifyPersonId(hhUsers.getVcEmployeeId());
		EmpMessageInfo.setLastModifyPersonName(hhUsers.getVcName());
		return EmpMessageInfo;
	}

	private HhUsersmessageinfo saveEmpMessageInfo(SDK_empMessageInfo data,
			HhUsers hhUsers, String OperateTime) {
		HhUsersmessageinfo Usersmessageinfo = new HhUsersmessageinfo();
		Usersmessageinfo = HhUsersmessageinfo.ConvertTo(Usersmessageinfo, data);
		Usersmessageinfo.setCreateDate(OperateTime);
		Usersmessageinfo.setCreatePersonId(hhUsers.getVcEmployeeId());
		Usersmessageinfo.setCreatePersonName(hhUsers.getVcName());
		return Usersmessageinfo;
	}

	@Override
	public void saveSdkOrganInfo(SdkOrganInfo data) {
		sessionFactory.getCurrentSession().save(data);
	}

	@Override
	public Integer saveSdkandHHEmpPostRecord(List<SDK_empPostRecord> entities,
			HhUsers hhUsers, String OperateTime) {
		int rightRow = 0;
		int totoalRow = entities.size();
		List<SdkEmpPostRecord> sdkadd = new ArrayList<SdkEmpPostRecord>();
		List<HhUserspostrecord> hhadd = new ArrayList<HhUserspostrecord>();
		
		List<SDK_empPostRecord>temp = new ArrayList<SDK_empPostRecord>();
		try {
			
			int a;
			int b = entities.size();
			for( a = 0 ; a < b ; a ++){
				temp.add(entities.get(a));
				if( temp.size() > 10000 || a == b-1){
					List<Object> old_Userspostrecord = checkPostrecordRepeat1(temp);
					if( old_Userspostrecord != null && old_Userspostrecord.size()>0){
						deletePostrecordRepeat(temp, old_Userspostrecord);
					}
					temp.clear();
				}
			}
			
			for (SDK_empPostRecord item : entities) {
				// 存临时
				sdkadd.add(saveEmpPostRecordTemp(item, hhUsers, OperateTime));
				// 存正式
				hhadd.add(saveEmpPostRecord(item, hhUsers, OperateTime));
				// 计数
				rightRow++;
				System.out.println(hhadd.size() + "," + rightRow + "," + totoalRow);

				if (sdkadd.size() > 1000 || rightRow == totoalRow) {
					// 插入临时表
					if (sdkadd != null && sdkadd.size() > 0) {
						createsdkPostRecordSql(sdkadd);
						sdkadd.clear();
					}
					// 插入正式表
					if (hhadd != null && hhadd.size() > 0) {
						createhhPostRecordSql(hhadd);
						hhadd.clear();
					}
				}
			}
		} catch (HibernateException e) {
			// tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return rightRow;
	}

	private void createsdkPostRecordSql(List<SdkEmpPostRecord> sdkadd) {
		StringBuffer hql = new StringBuffer();
		hql.append(" INSERT INTO sdk_emppostrecord (`vcEmployeeID`, `iPostRecordID`, `vcOrganID`, `vcPostName`, `nNodeID`, `vcAdminLevelName`, `vcHoldPost`, `cIfMost`, `cIfSideLine`, `dOperatorDate`, `cIfMorB`, `createDate`, `createPersonID`, `createPersonName`, `lastModifyPersonId`, `lastModifyPersonName`, `lastModifyDate`) VALUES ");
		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = sdkadd.size();
			for (a = 0; a < b; a++) {
				SdkEmpPostRecord item = sdkadd.get(a);
				hql.append("('" + item.getVcEmployeeId() + "','"
						+ item.getIpostRecordId() + "','" + item.getVcOrganId()
						+ "','" + item.getVcPostName() + "','"
						+ item.getNnodeId() + "','"
						+ item.getVcAdminLevelName() + "','"
						+ item.getVcHoldPost() + "','" + item.getCifMost()
						+ "','" + item.getCifSideLine() + "','"
						+ item.getDoperatorDate() + "','" + item.getCifMorB()
						+ "','" + item.getCreateDate() + "','"
						+ item.getCreatePersonId() + "','"
						+ item.getCreatePersonName() + "','"
						+ item.getLastModifyPersonId() + "','"
						+ item.getLastModifyPersonName() + "','"
						+ item.getLastModifyDate() + "')");
				if (a + 1 < b)
					hql.append(",");
			}
			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createhhPostRecordSql(List<HhUserspostrecord> hhadd) {
		StringBuffer hql = new StringBuffer();
		hql.append(" insert into hh_userspostrecord(`vcEmployeeID`, `iPostRecordID`, `vcOrganID`, `vcPostName`, `nNodeID`, `vcAdminLevelName`, `vcHoldPost`, `cIfMost`, `cIfSideLine`, `dOperatorDate`, `cIfMorB`, `createDate`, `createPersonID`, `createPersonName`, `lastModifyPersonId`, `lastModifyPersonName`, `lastModifyDate`)  values ");
		try {
			ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
					.getConnectionProvider();
			Connection conn = cp.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("");
			int a;
			int b = hhadd.size();
			for (a = 0; a < b; a++) {
				HhUserspostrecord item = hhadd.get(a);
				hql.append("('" + item.getVcEmployeeId() + "','"
						+ item.getIpostRecordId() + "','" + item.getVcOrganId()
						+ "','" + item.getVcPostName() + "','"
						+ item.getNnodeId() + "','"
						+ item.getVcAdminLevelName() + "','"
						+ item.getVcHoldPost() + "','" + item.getCifMost()
						+ "','" + item.getCifSideLine() + "','"
						+ item.getDoperatorDate() + "','" + item.getCifMorB()
						+ "','" + item.getCreateDate() + "','"
						+ item.getCreatePersonId() + "','"
						+ item.getCreatePersonName() + "','"
						+ item.getLastModifyPersonId() + "','"
						+ item.getLastModifyPersonName() + "','"
						+ item.getLastModifyDate() + "')");
				if (a + 1 < b)
					hql.append(",");
			}
			pst.addBatch(hql.toString());
			// 执行操作
			pst.executeBatch();
			// 提交事务
			conn.commit();
			// 清空上一次添加的数据
			hql = new StringBuffer();
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private SdkEmpPostRecord saveEmpPostRecordTemp(SDK_empPostRecord data,
			HhUsers hhUsers, String OperateTime) {
		SdkEmpPostRecord empPostRecord = SdkEmpPostRecord.ConvertTo(data);
		empPostRecord.setCreateDate(OperateTime);
		empPostRecord.setCreatePersonId(hhUsers.getVcEmployeeId());
		empPostRecord.setCreatePersonName(hhUsers.getVcName());
		empPostRecord.setLastModifyDate(OperateTime);
		empPostRecord.setLastModifyPersonId(hhUsers.getVcEmployeeId());
		empPostRecord.setLastModifyPersonName(hhUsers.getVcName());
		return empPostRecord;
	}

	private HhUserspostrecord saveEmpPostRecord(SDK_empPostRecord data,
			HhUsers hhUsers, String OperateTime) {
		HhUserspostrecord hhUserspostrecord = new HhUserspostrecord();
		hhUserspostrecord = HhUserspostrecord
				.ConvertTo(hhUserspostrecord, data);
		hhUserspostrecord.setCreateDate(OperateTime);
		hhUserspostrecord.setCreatePersonId(hhUsers.getVcEmployeeId());
		hhUserspostrecord.setCreatePersonName(hhUsers.getVcName());
		return hhUserspostrecord;
	}
	
	public String getLastUpdateHhuser()
	{
		StringBuffer hql = new StringBuffer();
		hql.append("select max(t.doperatorDate) from HhUsers t ");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (String) query.uniqueResult();
	}
	public String getLastUpdateHhorganInfo()
	{
		StringBuffer hql = new StringBuffer();
		hql.append("select max(t.doperatorDate) from HhOrganInfo t ");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (String) query.uniqueResult();
	}
	public String getLastUpdateHhmessageInfo()
	{
		StringBuffer hql = new StringBuffer();
		hql.append("select max(t.doperatorDate) from HhUsersmessageinfo t ");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (String) query.uniqueResult();
	}

}

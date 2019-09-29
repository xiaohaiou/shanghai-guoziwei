package com.softline.dao.system;

import java.util.List;

import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhSdkLog;
import com.softline.entity.HhUsers;
import com.softline.entity.HhUsersmessageinfo;
import com.softline.entity.HhUserspostrecord;
import com.softline.entity.SdkOrganInfo;
import com.softline.entityTemp.SDK_empMessageInfo;
import com.softline.entityTemp.SDK_empPostRecord;
import com.softline.entityTemp.SDK_empdirector;
import com.softline.entityTemp.SDK_organinfo;

public interface ISDKDao {
	
	public List<HhSdkLog> getlist(String StartTime,String EndTime);
	
	/**
	 * 校验人员基本信息是否已经存在
	 * @return id，如果不存在返回null
	 */
	public HhUsers checkUsersRepeat(String vcEmployeeId);
	
	/**
	 * 校验组织机构是否已经存在
	 * @return id，如果不存在返回null
	 */
	public HhOrganInfo checkOrganInfoRepeat(String nnodeId);
	
	/**
	 * 校验人员联系方式是否已经存在
	 * @return id，如果不存在返回null
	 */
	public HhUsersmessageinfo checkmessageinfoRepeat(String vcEmployeeId);
	
	/**
	 * 校验人员任职记录是否已经存在
	 * @return id，如果不存在返回null
	 */
	public HhUserspostrecord checkPostrecordRepeat(String vcEmployeeId,String iPostRecordID);
	
	public void saveSdkOrganInfo(SdkOrganInfo data);
	
	public Integer saveSdkandHHOrganInfo(List<SDK_organinfo> entities,HhUsers hhUsers,String OperateTime);
	
	public Integer saveSdkandHHuser(List<SDK_empdirector> entities,HhUsers hhUsers,String OperateTime);
	
	public Integer saveSdkandHHMessageInfo(List<SDK_empMessageInfo> entities,String OperateTime,HhUsers hhUsers);
	
	public Integer saveSdkandHHEmpPostRecord(List<SDK_empPostRecord> entities,HhUsers hhUsers,String OperateTime);
	
	
	public String getLastUpdateHhuser();
	public String getLastUpdateHhorganInfo();
	public String getLastUpdateHhmessageInfo();
}

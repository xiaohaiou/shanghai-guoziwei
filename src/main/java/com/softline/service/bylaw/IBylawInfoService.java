package com.softline.service.bylaw;

import java.util.List;

import com.softline.common.BylawInfoTree;
import com.softline.entity.HhUsers;
import com.softline.entity.bylaw.BylawInfo;
import com.softline.entity.bylaw.BylawInfoSynRecord;
import com.softline.util.MsgPage;

public interface IBylawInfoService {
	/**
	 * 获取规章制度分页列表
	 * @param condition 查询条件实体
	 * @param curPageNum 当前页
	 * @param pageSize 每页记录数
	 * @return
	 */
	MsgPage getBylawInfos(String dataauth,BylawInfo condition,Integer curPageNum,Integer pageSize);
	
	/**
	 * 获取规章制度列表
	 * @param condition 查询条件实体
	 * @return
	 */
	List<BylawInfo> getBylawInfos(String dataauth,BylawInfo condition);
	
	/**
	 * 保存规章制度的关联信息
	 * @param bylawInfo 表单数据
	 * @param hhUsers 操作用户
	 */
	void saveBylawinfo(BylawInfo bylawInfo,HhUsers hhUsers);
	
	/**
	 * 调用esb接口获取规章制度信息
	 */
	String xsaveSynBylawInfo(HhUsers user);
	
	/**
	 * 调用esb接口同步规章制度信息，以年份为单位
	 * @param year 年份
	 * @param user 用户
	 * @return
	 */
	String saveSynBylawInfoInYar(String year,HhUsers user);
	
	/**
	 * 获取规章制度同步列表
	 * @param condition 查询条件实体
	 * @param curPageNum 当前页
	 * @param pageSize 每一展示数量
	 * @return
	 */
	MsgPage getBylawInfoSynRecords(BylawInfoSynRecord condition,Integer curPageNum,Integer pageSize);
	
	
	String getBylawInfoTreeHtml();
	
	/**
	 * 获取规章制度树
	 * @param bylawTitle 规章制度标题
	 * @return
	 */
	BylawInfoTree getBylawInfoTree(String bylawTitle);
	
	/**
	 * isCoreog不能为空，没有进行过关联，发文单位
	 * 获取可以替换的规章制度
	 * @param orgId 发文单位
	 * @return
	 */
	List<BylawInfo> getCanChangedBylawInfos(String orgId);
	
	/**
	 * 保存
	 * @param id
	 * @param changeBylawInfoId
	 * @return
	 */
	String saveChangeLink(Integer id,String changeBylawInfoId,HhUsers user);
}

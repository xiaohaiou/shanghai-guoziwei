package com.softline.service.project;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.project.PjAuthorityPerson;
import com.softline.util.MsgPage;

public interface IPjAuthorityPersonService {
	
	/**
	 * 获取重点基建工程授权数据 
	 * @param condition
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	MsgPage getPjAuthorityPersons(PjAuthorityPerson condition,Integer curPageNum,Integer pageSize);
	
	/**
	 * 新增
	 * @param user 操作人
	 * @param entity 
	 */
	void saveAddAuthorityPerson(HhUsers user,PjAuthorityPerson entity);
	
	/**
	 * 编辑
	 * @param user 用户名
	 * @param entity 新
	 * @param oldEntity 旧
	 */
	void saveModifyAuthorityPerson(HhUsers user,PjAuthorityPerson entity,PjAuthorityPerson oldEntity);
	
	/**
	 * 删除
	 * @param user
	 * @param id
	 */
	void deleteAuthorityPerson(HhUsers user,Integer id);
	
	
}

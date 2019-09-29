package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.bimr.BimrDuty;

/**
 * 员工问责Dao
 * @author pengguolin
 */
public interface IDutyDao {
	void delDuty(Integer id);

	BimrDuty getById(Integer id);
	
	/**
	 * 获取总记录数
	 * @param entity 带查询实体
	 * @param dataAuthority 数据权限字符串
	 * @return 总记录数
	 */
	Integer getDutyListCount(BimrDuty entity, String dataAuthority);
	
	/**
	 * 分页查询结果集
	 * @param entity 待查询实体
	 * @param offset 开始页
	 * @param pageSize 每页显示数据条数
	 * @param dataAuthority 数据权限字符串
	 * @return 每页结果集
	 */
	List<BimrDuty> getDutyList(BimrDuty entity, Integer offset,
			Integer pageSize, String dataAuthority);
	
	Integer getExamineDutyListCount(BimrDuty entity, String dataAuthority);
	
	List<BimrDuty> getExamineDutyList(BimrDuty entity, Integer offset,
			Integer pageSize, String dataAuthority);
	
	/**
	 * 保存
	 * @param entity 待保存实体
	 */
	Integer save(BimrDuty entity);
	
	/**
	 * 更新
	 * @param entity 待更新实体
	 */
	void update(BimrDuty entity);
	
	//根据问责人员Id获取问责信息
	
		BimrDuty getBimrDutyByAccountableId(Integer id);
}

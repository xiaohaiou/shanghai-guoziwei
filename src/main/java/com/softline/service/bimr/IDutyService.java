package com.softline.service.bimr;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrDuty;
import com.softline.util.MsgPage;

/**
 * 员工问责Service
 * @author pengguolin
 */
public interface IDutyService {
	/**
	 * 分页查询结果集
	 * @param entity 待查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 每页显示条数
	 * @param dataAuthority 数据权限字符串
	 * @return 每页结果集
	 */
	MsgPage getDutyList(BimrDuty entity, Integer curPageNum, Integer pageSize,
			String dataAuthority);
	
	MsgPage getExamineDutyList(BimrDuty entity, Integer curPageNum, Integer pageSize,
			String dataAuthority);
	
	//List<BimrDuty> getDutyListExport(BimrDuty entity,String dataAuthority);
	/**
	 * 获取员工问责数据
	 * @param entity 待查询实体（id）
	 * @return 员工问责数据
	 */
	BimrDuty getBimrDuty(BimrDuty entity);
	
	/**
	 * 保存
	 * @param entity 待保存实体
	 */
	void save(BimrDuty entity);
	
	
	/**
	 * 更新
	 * @param entity 待更新实体
	 */
	void update(BimrDuty entity);
	
	/**
	 * 根据id查询员工问责信息
	 * @param parseInt 待查询id
	 * @return 员工问责信息
	 */
	BimrDuty getBimrDutyById(int id);
	
	/**
	 * 保存员工问责信息
	 * @param entity 待保存员工问责信息
	 * @param user 待保存用户信息
	 * @param multFile1 待上传的附件
	 * @param package_path 待上传的路径
	 */
	void save(BimrDuty entity, HhUsers user, MultipartFile multFile, String package_path);
	
	/**
	 * 更新员工问责信息
	 * @param entity 待更新员工问责信息
	 * @param user 待更新用户信息
	 * @param multFile1 待更新上传的附件
	 * @param package_path 待更新上传的路径
	 */
	void update(BimrDuty entity, HhUsers user, MultipartFile multFile,
			 String package_path);
	
	/**
	 * 审核信息保存
	 * @param entity 待保存的审核人信息
	 * @param examStr 审核信息
	 * @param examResult 审核结果
	 * @param user 用户信息
	 */
	void saveExamine(BimrDuty entity, String examStr, Integer examResult,
			HhUsers user);

	//根据问责人员Id获取问责信息
	
	BimrDuty getBimrDutyByAccountableId(Integer id);
}

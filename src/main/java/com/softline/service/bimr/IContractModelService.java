package com.softline.service.bimr;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrContractModel;
import com.softline.util.MsgPage;

/**
 * 合同范本Service
 * @author pengguolin
 */
public interface IContractModelService {
	/**
	 * 分页查询结果集
	 * @param entity 待查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 每页显示条数
	 * @param dataAuthority 数据权限字符串
	 * @return 每页结果集
	 */
	MsgPage getContractModelList(BimrContractModel entity, Integer curPageNum, Integer pageSize,
			String dataAuthority);
	
	/**
	 * 获取合同范本数据
	 * @param entity 待查询实体（id）
	 * @return 合同范本数据
	 */
	BimrContractModel getBimrContractModel(BimrContractModel entity);
	
	/**
	 * 保存
	 * @param entity 待保存实体
	 */
	void save(BimrContractModel entity);
	
	/**
	 * 更新
	 * @param entity 待更新实体
	 */
	void update(BimrContractModel entity);
	
	/**
	 * 根据id查询合规培训信息
	 * @param parseInt 待查询id
	 * @return
	 */
	BimrContractModel getBimrContractModelById(int id);
	
	/**
	 * 保存合同范本信息
	 * @param entity 待合同范本信息
	 * @param user 待保存用户信息
	 * @param multFile1 待上传的pdf附件
	 * @param multFile2 待上传的空白模板
	 * @param package_path 待上传的路径
	 */
	void save(BimrContractModel entity, HhUsers user, MultipartFile multFile1,
			MultipartFile multFile2, String package_path);
	
	/**
	 * 更新合同范本信息
	 * @param entity 待合同范本信息
	 * @param user 待更新用户信息
	 * @param multFile1 待更新上传的pdf附件
	 * @param multFile2 待更新上传的空白模板
	 * @param package_path 待更新上传的路径
	 */
	void update(BimrContractModel entity, HhUsers user, MultipartFile multFile1,
			MultipartFile multFile2, String package_path);
	
}

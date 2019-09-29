package com.softline.service.bimr;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrTrain;
import com.softline.util.MsgPage;

/**
 * 合规培训Service
 * @author pengguolin
 */
public interface ITrainService {
	/**
	 * 分页查询结果集
	 * @param entity 待查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 每页显示条数
	 * @param dataAuthority 数据权限字符串
	 * @return 每页结果集
	 */
	MsgPage getTrainList(BimrTrain entity, Integer curPageNum, Integer pageSize,
			String dataAuthority);
	
	MsgPage getExamineTrainList(BimrTrain entity, Integer curPageNum, Integer pageSize,
			String dataAuthority);
	
	
	/**
	 * 导出
	 * @param entity 实体
	 */
	List<BimrTrain> getTrainListExport(BimrTrain entity, String dataAuthority);
	/**
	 * 获取合规培训数据
	 * @param entity 待查询实体（id）
	 * @return 合规培训数据
	 */
	BimrTrain getBimrTrain(BimrTrain entity);
	
	/**
	 * 保存
	 * @param entity 待保存实体
	 */
	void save(BimrTrain entity);
	
	/**
	 * 更新
	 * @param entity 待更新实体
	 */
	void update(BimrTrain entity);
	
	/**
	 * 根据id查询合规培训信息
	 * @param parseInt 待查询id
	 * @return
	 */
	BimrTrain getBimrTrainById(int id);
	
	/**
	 * 保存合股培训信息
	 * @param entity 待保存合规信息
	 * @param user 待保存用户信息
	 * @param multFile1 待上传的培训资料
	 * @param multFile2 待上传的培训人员
	 * @param package_path 待上传的路径
	 */
	void save(BimrTrain entity, HhUsers user, MultipartFile multFile1,
			MultipartFile multFile2, String package_path);
	
	/**
	 * 更新合股培训信息
	 * @param entity 待更新合规信息
	 * @param user 待更新用户信息
	 * @param multFile1 待更新上传的培训资料
	 * @param multFile2 待更新上传的培训人员
	 * @param package_path 待更新上传的路径
	 */
	void update(BimrTrain entity, HhUsers user, MultipartFile multFile1,
			MultipartFile multFile2, String package_path);
	
	/**
	 * 审核信息保存
	 * @param entity 待保存的审核人信息
	 * @param examStr 审核信息
	 * @param examResult 审核结果
	 * @param user 用户信息
	 */
	void saveExamine(BimrTrain entity, String examStr, Integer examResult,
			HhUsers user);

}

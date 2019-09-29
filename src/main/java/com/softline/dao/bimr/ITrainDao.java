package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.bimr.BimrTrain;

/**
 * 合规培训Dao
 * @author pengguolin
 */
public interface ITrainDao {
	void delTrain(Integer id);

	BimrTrain getById(Integer id);
	
	/**
	 * 获取总记录数
	 * @param entity 带查询实体
	 * @param dataAuthority 数据权限字符串
	 * @return 总记录数
	 */
	Integer getTrainListCount(BimrTrain entity, String dataAuthority);
	
	/**
	 * 分页查询结果集
	 * @param entity 待查询实体
	 * @param offset 开始页
	 * @param pageSize 每页显示数据条数
	 * @param dataAuthority 数据权限字符串
	 * @return 每页结果集
	 */
	List<BimrTrain> getTrainList(BimrTrain entity, Integer offset,
			Integer pageSize, String dataAuthority);
	
	Integer getExamineTrainListCount(BimrTrain entity, String dataAuthority);
	
	List<BimrTrain> getExamineTrainList(BimrTrain entity, Integer offset,
			Integer pageSize, String dataAuthority);
	
	/**
	 * 导出
	 * 	 * @param entity 
	 */
	
	List<BimrTrain> getTrainListExport(BimrTrain entity, String dataAuthority);
	/**
	 * 保存
	 * @param entity 待保存实体
	 */
	Integer save(BimrTrain entity);
	
	/**
	 * 更新
	 * @param entity 待更新实体
	 */
	void update(BimrTrain entity);
	
}

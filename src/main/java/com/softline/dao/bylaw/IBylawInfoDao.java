package com.softline.dao.bylaw;

import java.util.List;

import com.softline.entity.bylaw.BylawInfo;
import com.softline.entity.bylaw.BylawInfoSynRecord;

public interface IBylawInfoDao {
	
	/**
	 * 获取规章制度列表
	 * @param condition 查询条件实体
	 * @param offset 初始页
	 * @param pageSize 每一页记录条数，一般为10条
	 * @return
	 */
	List<BylawInfo> getBylawInfos(String dataauth,BylawInfo condition,Integer offset,Integer pageSize);
	
	/**
	 * 同步规章制度结果信息
	 * @param condition
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	List<BylawInfoSynRecord> getBylawInfoSynRecords(BylawInfoSynRecord condition,Integer offset,Integer pageSize);
	
	/**
	 * 规章制度的文件ID,唯一值
	 * @param fileId
	 * @return
	 */
	BylawInfo getBylawInfoByFileId(String fileId);
	
	/**
	 * 根据父ID获取子规章制度
	 * 范围：核心企业，进行过关联操作的
	 * @param parentId
	 * @return
	 */
	List<BylawInfo> getSonBylawInfos(Integer parentId);
	
	/**
	 * 根据核心企业ID和体系类别ID获取规章制度的总则以及没有层级的
	 * 范围：核心企业，进行过关联操作的
	 * @param deptId
	 * @param org
	 * @return
	 */
	List<BylawInfo> getZongzAndNoLevel(Integer deptId,String org);
	
	/**
	 * isCoreog不能为空，没有进行过关联，发文单位
	 * 获取可以替换的规章制度
	 * @param orgId 发文单位
	 * @return
	 */
	List<BylawInfo> getCanChangedBylawInfos(String orgId);
	
	
}

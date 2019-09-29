package com.softline.dao.social;

import java.util.List;

import com.softline.entity.DataSocialBrand;

/**
 * 
 * @author zy
 * 
 */
public interface ISocialBrandDao {

	/**
	 * 单个主键查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataSocialBrand getSocialBrand(Integer id);

	/**
	 * 一览查询画面检索
	 * 
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @param fun
	 *            功能
	 * @return
	 */
	public List<DataSocialBrand> getSocialBrandList(DataSocialBrand entity,
			Integer offsize, Integer pagesize, Integer fun);

	/**
	 * 数据件数查询
	 * 
	 * @param entity
	 * @param fun
	 *            功能
	 * @return
	 */
	public int getSocialBrandListCount(DataSocialBrand entity, Integer fun);

	/**
	 * 保存时校验重复的方法
	 * 
	 * @param entity
	 * @return
	 */
	public boolean saveSocialBrandRepeatCheck(DataSocialBrand entity,
			String type);

	/**
	 * 保存时检查数据是否被能修改
	 * 
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(DataSocialBrand entity);
}

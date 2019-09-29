package com.softline.dao.system;

import java.util.List;

import com.softline.entity.HhFile;



public interface ICommonDao {
	
	public <T> void saveOrUpdate(T t);
	
	public <T> void delete(T t);
	
	Object findById(Class a ,Integer id);
	
	public HhFile getFileByEnIdAndType(Integer entityId, Integer typeId);
	
	public HhFile getFileByUuid(String uuid);
	
	public List<HhFile> getFile(String entityIds, String types);
	
	public HhFile getFileById(Integer fileID);

	/**
	 * 删除现金流执行中所对应的三张明细表（筹资性流入明细列表、筹资性流出明细列表、投资性流出明细列表）、存量负债中境外负债按币种分类明细列表
	 * @param id（周id，月id）
	 * @param type（weekly、monthly、stockLiabilities）
	 * @param tbName（表名）
	 */
	public void deleteChildDetail(Integer id, String type, String tbName, String lastModifyDate, String lastModifyPersonId, String lastModifyPersonName);
	 
	public <T> void save(T t);
}

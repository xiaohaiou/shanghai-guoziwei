package com.softline.service.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhFile;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrComplianceBJInfo;
import com.softline.entity.bimr.BimrCompliancePerson;
import com.softline.entity.bimr.BimrComplianceSituation;
import com.softline.entity.bimr.BimrComplianceSuggest;
import com.softline.entity.bimr.BimrDuty;


public interface ICommonService {
	
	/**
	 * 李中棠
	 * 公用新增保存方法（实体）
	 * @param t
	 */
	public <T> void saveOrUpdate(T t);
	
	/**
	 * 李中棠
	 * 公用删除方法（实体）
	 * @param t
	 */
	public <T> void delete(T t);
	
	Object findById(Class a ,Integer id);
	
	
	public HhFile saveFile(MultipartFile picture, Integer entityId, Integer typeId, String package_path);
	
	public HhFile getFileByEnIdAndType(Integer entityId, Integer typeId);
	
	public HhFile getFileById(Integer fileID);

	public HhFile getFileByUuid(String uuid);
	
	public <T> void save(T t);

	 

} 

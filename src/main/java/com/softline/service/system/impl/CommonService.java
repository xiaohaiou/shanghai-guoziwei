package com.softline.service.system.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.client.task.HhPagecode;
import com.softline.common.Common;
import com.softline.dao.bimr.IComplianceDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhFile;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrComplianceBJInfo;
import com.softline.entity.bimr.BimrCompliancePerson;
import com.softline.entity.bimr.BimrComplianceSituation;
import com.softline.entity.bimr.BimrComplianceSuggest;
import com.softline.entity.bimr.BimrDuty;
import com.softline.service.bimr.IComplianceService;
import com.softline.service.system.ICommonService;

@Service("commonService")
public class CommonService implements ICommonService{
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	
	@Override
	public <T> void saveOrUpdate(T t) {
		// TODO Auto-generated method stub
		commonDao.saveOrUpdate(t);
	}

	@Override
	public <T> void delete(T t) {
		// TODO Auto-generated method stub
		commonDao.delete(t);
	}

	@Override
	public Object findById(Class a, Integer id) {
		// TODO Auto-generated method stub
		return commonDao.findById(a,id);
	}
	
	public HhFile getFileByUuid(String uuid)
	{
		return commonDao.getFileByUuid(uuid);
	}
	
	public HhFile saveFile(MultipartFile picture, Integer entityId, Integer typeId, String package_path) {
		try {
			List<String> fileStrList = Common.saveFile(picture, package_path);
			HhFile file = new HhFile();
			file.setTypeId(typeId);
			file.setReleaseId(entityId);
			file.setFileName(fileStrList.get(0));
			file.setFilePath(fileStrList.get(1));
			file.setFileUuid(fileStrList.get(2));
			// 先执行保存
			commonDao.saveOrUpdate(file);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HhFile getFileByEnIdAndType(Integer entityId, Integer typeId) {
		// TODO Auto-generated method stub
		return commonDao.getFileByEnIdAndType(entityId, typeId);
	}
	
	@Override
	public HhFile getFileById(Integer fileID) {
		// TODO Auto-generated method stub
		return commonDao.getFileById(fileID);
	}
	
	public static boolean isStrParseToNum(String str){	
	     Pattern pattern=Pattern.compile("^(([0-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,6})?$");
	     Matcher match=pattern.matcher(str);   
	     if(match.matches()==false){   
	        return false;   
	     }else{   
	        return true;   
	     }  
	}
	
	public static boolean isValidDate(String str) {
		return Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}").matcher(str).matches(); 		
	}

	@Override
	public <T> void save(T t) {
		commonDao.save(t);
	}

	
}

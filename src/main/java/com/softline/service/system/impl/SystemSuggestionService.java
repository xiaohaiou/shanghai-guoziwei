package com.softline.service.system.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemSuggestionDao;
import com.softline.entity.HhUsers;
import com.softline.entity.SysSuggestion;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemSuggestionService;
import com.softline.util.MsgPage;
@Service("systemSuggestionService")
public class SystemSuggestionService implements ISystemSuggestionService{

	@Autowired
	@Qualifier("systemSuggestionDao")
	private ISystemSuggestionDao systemSuggestionDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	
	@Override
	public MsgPage getSysSuggestions(SysSuggestion condition, Integer curPageNum,
			Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<SysSuggestion> t = systemSuggestionDao.getSysSuggestions(condition, null, null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<SysSuggestion> list = systemSuggestionDao.getSysSuggestions(condition, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}


	@Override
	public String saveSuggestion(HhUsers user, SysSuggestion entity,
			MultipartFile file, SysSuggestion oldEntity) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(entity.getId() != null){//编辑
			entity.setCreateName(oldEntity.getCreateName());
			entity.setCreateTime(oldEntity.getCreateTime());
			entity.setCreator(oldEntity.getCreator());
			
			entity.setFileName(oldEntity.getFileName());
			entity.setFilePath(oldEntity.getFilePath());
			
			entity.setModifyId(user.getVcEmployeeId());
			entity.setModifyTime(df.format(new Date()));
			entity.setModifyName(user.getVcName());
			
		}else{//新增
			entity.setCreateTime(df.format(new Date()));
			entity.setCreateName(user.getVcName());
			entity.setCreator(user.getVcEmployeeId());
		}
		
		entity.setIsDel(0);
		commonDao.saveOrUpdate(entity);
		
		//保存附件
		if (file!= null && !file.isEmpty()) {
			String filePath = File.separator + "opt_1" + File.separator + "suggestion" + File.separator;
			String fileName = "suggestion"+entity.getId()+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			Common.deleteFile(filePath, fileName);
			Common.saveWkFile(file, filePath, fileName);
			entity.setFilePath(filePath+fileName);
			entity.setFileName(cleanXSS(file.getOriginalFilename()));
		}
		
		return "success";
	}
	
	private String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		//hwx 当查询条件显示（xxx）会被和谐掉。这里修改只和谐（）
		//value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;"); 
		value = value.replaceAll("\\(\\)", ""); 
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']","\"\"");
		value = value.replaceAll("script", "");
//		value = value.replaceAll("©", "&copy;").replaceAll(" ", "&nbsp;");
		return value;
	}
	
}

package com.softline.service.knowledgeBase.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.dao.knowledgeBase.IKnowledgeBaseDao;
import com.softline.entity.KnowledgeBase;
import com.softline.entity.HhUsers;
import com.softline.service.knowledgeBase.IKnowledgeBaseService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Service("knowledgeBaseService")
public class KnowledgeBaseService implements IKnowledgeBaseService{
	
	@Autowired
	@Qualifier("knowledgeBaseDao")	
	IKnowledgeBaseDao knowledgeBaseDao ;
	
	@Resource(name = "commonService")
	private ICommonService commonService;

	@Override
	public MsgPage getKnowledgeBases(KnowledgeBase entity, Integer curPageNum,
			Integer pageSize, String doDate2) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = knowledgeBaseDao.getKnowledgeBaseListCount(entity,doDate2);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<KnowledgeBase> list = knowledgeBaseDao.getKnowledgeBaseList(entity, offset, pageSize, doDate2);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	@Override
	public void deleteKnowledgeBase(Integer id) {
		knowledgeBaseDao.deleteKnowledgeBase(id);
	}

	@Override
	public KnowledgeBase getKnowledgeBase(Integer id) {
		return knowledgeBaseDao.getKnowledgeBaseById(id);
	}

	@Override
	public void updateKnowledgeBase(KnowledgeBase entity) {
		knowledgeBaseDao.updateKnowledgeBase(entity);
	}

	@Override
	public KnowledgeBase getKnowledgeBase(KnowledgeBase entity) {
		return knowledgeBaseDao.getKnowledgeBase(entity);
	}
	
	@Override
	public void save(KnowledgeBase entity, HhUsers user, MultipartFile multFile, String package_path){
		int id = knowledgeBaseDao.saveKnowledgeBase(entity);
		if (!multFile.isEmpty()) {
			commonService.saveFile(multFile, id, Base.KNOWLEDGEBASE_TYPE, package_path);
		}
	}
}

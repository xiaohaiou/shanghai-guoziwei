package com.softline.service.knowledgeStoreHouse.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.dao.knowledgeStoreHouse.IKnowledgeStoreHouseDao;
import com.softline.entity.HhFile;
import com.softline.entity.KnowledgeStoreHouse;
import com.softline.entity.HhUsers;
import com.softline.service.knowledgeStoreHouse.IKnowledgeStoreHouseService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Service("knowledgeStoreHouseService")
public class KnowledgeStoreHouseService implements IKnowledgeStoreHouseService{
	
	@Autowired 
	@Qualifier("knowledgeStoreHouseDao")	
	IKnowledgeStoreHouseDao knowledgeStoreHouseDao;
	
	@Resource(name = "commonService")
	private ICommonService commonService;

	@Override
	public MsgPage getKnowledgeStoreHouses(KnowledgeStoreHouse entity, Integer curPageNum,
			Integer pageSize, String dataAuthority) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = knowledgeStoreHouseDao.getKnowledgeStoreHouseListCount(entity, dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<KnowledgeStoreHouse> list = knowledgeStoreHouseDao.getKnowledgeStoreHouseList(entity, offset, pageSize, dataAuthority);
    	List<KnowledgeStoreHouse> list2 = new ArrayList<KnowledgeStoreHouse>();
    	if (list.size() > 0) {
    		for (KnowledgeStoreHouse knowledgeStoreHouse : list) {
    			HhFile hfile = commonService.getFileByEnIdAndType(knowledgeStoreHouse.getId(), Base.KNOWLEDGESTOREHOUSE_TYPE);
    			knowledgeStoreHouse.setHhFile(hfile);
    			list2.add(knowledgeStoreHouse);
    		}
		}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list2);    
        return msgPage;
	}
	
	@Override
	public void deleteKnowledgeStoreHouse(Integer id) {
		knowledgeStoreHouseDao.deleteKnowledgeStoreHouse(id);
	}

	@Override
	public KnowledgeStoreHouse getKnowledgeStoreHouse(Integer id) {
		return knowledgeStoreHouseDao.getKnowledgeStoreHouseById(id);
	}

	@Override
	public void updateKnowledgeStoreHouse(KnowledgeStoreHouse entity) {
		knowledgeStoreHouseDao.updateKnowledgeStoreHouse(entity);
	}

	@Override
	public KnowledgeStoreHouse getKnowledgeStoreHouse(KnowledgeStoreHouse entity) {
		return knowledgeStoreHouseDao.getKnowledgeStoreHouse(entity);
	}
	
	@Override
	public void save(KnowledgeStoreHouse entity, HhUsers user, MultipartFile multFile, String package_path){
		int id = knowledgeStoreHouseDao.saveKnowledgeStoreHouse(entity);
		if (!multFile.isEmpty()) {
			commonService.saveFile(multFile, id, Base.KNOWLEDGESTOREHOUSE_TYPE, package_path);
		}
	}

	@Override
	public List<KnowledgeStoreHouse> getKnowledgeStoreHouseByDocumentNo(String documentNo, Integer id) {
		return knowledgeStoreHouseDao.getKnowledgeStoreHouseByDocumentNo(documentNo,id);
	}
}

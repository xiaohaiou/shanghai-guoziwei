package com.softline.service.knowledgeStoreHouse;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.KnowledgeStoreHouse;
import com.softline.entity.HhUsers;
import com.softline.util.MsgPage;

public interface IKnowledgeStoreHouseService {
	
	MsgPage getKnowledgeStoreHouses(KnowledgeStoreHouse entity, Integer curPageNum, Integer pageSize, String dataAuthority);

	void updateKnowledgeStoreHouse(KnowledgeStoreHouse entity);

	void deleteKnowledgeStoreHouse(Integer id);

	KnowledgeStoreHouse getKnowledgeStoreHouse(Integer id);
	
	List<KnowledgeStoreHouse> getKnowledgeStoreHouseByDocumentNo(String documentNo, Integer id);
	
	KnowledgeStoreHouse getKnowledgeStoreHouse(KnowledgeStoreHouse entity);

	void save(KnowledgeStoreHouse entity, HhUsers user, MultipartFile multFile, String package_path);

}

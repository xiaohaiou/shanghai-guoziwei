package com.softline.dao.knowledgeStoreHouse;

import java.util.List;

import com.softline.entity.KnowledgeStoreHouse;

public interface IKnowledgeStoreHouseDao {

	Integer getKnowledgeStoreHouseListCount(KnowledgeStoreHouse entity, String dataAuthority);
	
	List<KnowledgeStoreHouse> getKnowledgeStoreHouseList(KnowledgeStoreHouse entity, Integer offset, Integer pageSize, String dataAuthority);

	Integer saveKnowledgeStoreHouse(KnowledgeStoreHouse entity);
	
    void updateKnowledgeStoreHouse(KnowledgeStoreHouse entity);
	
    void deleteKnowledgeStoreHouse(Integer id);

	KnowledgeStoreHouse getKnowledgeStoreHouseById(Integer id);
	
	List<KnowledgeStoreHouse> getKnowledgeStoreHouseByDocumentNo(String documentNo, Integer id);
	
	KnowledgeStoreHouse getKnowledgeStoreHouse(KnowledgeStoreHouse entity);

	

}

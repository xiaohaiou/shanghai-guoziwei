package com.softline.dao.knowledgeBase;

import java.util.List;
import com.softline.entity.KnowledgeBase;

public interface IKnowledgeBaseDao {

	Integer getKnowledgeBaseListCount(KnowledgeBase entity, String doDate2);
	
	List<KnowledgeBase> getKnowledgeBaseList(KnowledgeBase entity, Integer offset,	Integer pageSize, String doDate2);

	Integer saveKnowledgeBase(KnowledgeBase entity);
	
    void updateKnowledgeBase(KnowledgeBase entity);
	
    void deleteKnowledgeBase(Integer id);

	KnowledgeBase getKnowledgeBaseById(Integer id);
	
	KnowledgeBase getKnowledgeBase(KnowledgeBase entity);

	

}

package com.softline.service.knowledgeBase;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.KnowledgeBase;
import com.softline.entity.HhUsers;
import com.softline.util.MsgPage;

public interface IKnowledgeBaseService {
	
	MsgPage getKnowledgeBases(KnowledgeBase entity, Integer curPageNum, Integer pageSize, String doDate2);

	void updateKnowledgeBase(KnowledgeBase entity);

	void deleteKnowledgeBase(Integer id);

	KnowledgeBase getKnowledgeBase(Integer id);
	
	KnowledgeBase getKnowledgeBase(KnowledgeBase entity);

	void save(KnowledgeBase entity, HhUsers user, MultipartFile multFile, String package_path);

}

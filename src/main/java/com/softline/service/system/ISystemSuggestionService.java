package com.softline.service.system;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.SysSuggestion;
import com.softline.util.MsgPage;


public interface ISystemSuggestionService {

	/**
	 * 查询记录
	 * @param condition 条件实体
	 * @param offset	分页开始位置
	 * @param pageSize	分页最大返回条数
	 * @return
	 */
	MsgPage getSysSuggestions(SysSuggestion condition,Integer offset,Integer pageSize);
	
	/**
	 * 保存意见反馈信息
	 * @param user 用户
	 * @param entity 新实体
	 * @param file 文件
	 * @param oldEntity 老实体
	 * @return
	 */
	public String saveSuggestion(HhUsers user,SysSuggestion entity,MultipartFile file,SysSuggestion oldEntity);
	
}

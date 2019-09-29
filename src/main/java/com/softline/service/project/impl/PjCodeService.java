package com.softline.service.project.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.project.IPjCodeDao;
import com.softline.entity.project.PjCode;
import com.softline.service.project.IPjCodeService;
@Service("pjCodeService")
public class PjCodeService implements IPjCodeService{
	
	@Autowired
	@Qualifier("pjCodeDao")
	private IPjCodeDao pjCodeDao;
	
	@Override
	public List<PjCode> getPjCodes(Integer codeType) {
		return pjCodeDao.getPjCodes(codeType);
	}
	
}

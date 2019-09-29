package com.softline.service.bimr.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.bimr.IContractModelDao;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrContractModel;
import com.softline.service.bimr.IContractModelService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

/**
 * 合同范本ServiceImpl
 * @author pengguolin
 */
@Service("contractModelService")
public class ContractModelServiceImpl implements IContractModelService {
	@Autowired @Qualifier("contractModelDao") private IContractModelDao contractModelDao;
	@Autowired private ICommonService commonService;
	
	@Override
	public MsgPage getContractModelList(BimrContractModel entity, Integer curPageNum,
		Integer pageSize, String dataAuthority) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = contractModelDao.getContractModelListCount(entity, dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum, pageSize);  
    	//分页查询结果集
    	List<BimrContractModel> list = contractModelDao.getContractModelList(entity, offset, pageSize, dataAuthority);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public BimrContractModel getBimrContractModel(BimrContractModel entity) {
		return contractModelDao.getById(entity.getId());
	}

	@Override
	public void save(BimrContractModel entity) {
		contractModelDao.save(entity);
	}

	@Override
	public void update(BimrContractModel entity) {
		contractModelDao.update(entity);
	}

	@Override
	public BimrContractModel getBimrContractModelById(int id) {
		return contractModelDao.getById(id);
	}

	@Override
	public void save(BimrContractModel entity, HhUsers user, MultipartFile multFile1,
			MultipartFile multFile2, String package_path) {
		int id = contractModelDao.save(entity);
		if (!multFile1.isEmpty()) {
			commonService.saveFile(multFile1, id, Base.CONTRACTMODEL_PDF, package_path);
		}
		if (!multFile2.isEmpty()) {
			commonService.saveFile(multFile2, id, Base.CONTRACTMODEL_BLANK, package_path);
		}
	}

	@Override
	public void update(BimrContractModel entity, HhUsers user,
			MultipartFile multFile1, MultipartFile multFile2,
			String package_path) {
		contractModelDao.update(entity);
		if (!multFile1.isEmpty()) {
			HhFile _file = commonService.getFileByEnIdAndType(entity.getId(), Base.CONTRACTMODEL_PDF);
			if (_file != null) {
				File file = new File(_file.getFilePath());
				file.delete();
				updateFile(_file, multFile1, package_path);
			}else{
				commonService.saveFile(multFile1, entity.getId(), Base.CONTRACTMODEL_PDF, package_path);
			}
		}
		if (!multFile2.isEmpty()) {
			HhFile _file = commonService.getFileByEnIdAndType(entity.getId(), Base.CONTRACTMODEL_BLANK);
			if (_file != null) {
				File file = new File(_file.getFilePath());
				file.delete();
				updateFile(_file, multFile2, package_path);
			}else{
				commonService.saveFile(multFile2, entity.getId(), Base.CONTRACTMODEL_BLANK, package_path);
			}
		}
	}
	
	private void updateFile(HhFile file, MultipartFile multFile, String package_path) {
		try {
			List<String> fileStrList = Common.saveFile(multFile, package_path);
			file.setFileName(fileStrList.get(0));
			file.setFilePath(fileStrList.get(1));
			file.setFileUuid(fileStrList.get(2));
			// 先执行保存
			commonService.saveOrUpdate(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

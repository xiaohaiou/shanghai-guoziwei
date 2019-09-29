package com.softline.service.bimr.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.bimr.ITrainDao;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrTrain;
import com.softline.service.bimr.ITrainService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

/**
 * 合规培训ServiceImpl
 * @author pengguolin
 */
@Service("trainService")
public class TrainServiceImpl implements ITrainService {
	@Autowired @Qualifier("trainDao") private ITrainDao trainDao;
	@Autowired private ICommonService commonService;
	@Autowired private IExamineService examineService;
	
	@Override
	public MsgPage getTrainList(BimrTrain entity, Integer curPageNum,
		Integer pageSize, String dataAuthority) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = trainDao.getTrainListCount(entity, dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum, pageSize);  
    	//分页查询结果集
    	List<BimrTrain> list = trainDao.getTrainList(entity, offset, pageSize, dataAuthority);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	@Override
	public MsgPage getExamineTrainList(BimrTrain entity, Integer curPageNum,
			Integer pageSize, String dataAuthority) {
			MsgPage msgPage = new MsgPage();       
	        //总记录数
	        Integer totalRecords = trainDao.getExamineTrainListCount(entity, dataAuthority);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum, pageSize);  
	    	//分页查询结果集
	    	List<BimrTrain> list = trainDao.getExamineTrainList(entity, offset, pageSize, dataAuthority);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);    
	        return msgPage;
		}

	@Override
	public BimrTrain getBimrTrain(BimrTrain entity) {
		return trainDao.getById(entity.getId());
	}

	@Override
	public void save(BimrTrain entity) {
		trainDao.save(entity);
	}

	@Override
	public void update(BimrTrain entity) {
		trainDao.update(entity);
	}

	@Override
	public BimrTrain getBimrTrainById(int id) {
		return trainDao.getById(id);
	}

	@Override
	public void save(BimrTrain entity, HhUsers user, MultipartFile multFile1,
			MultipartFile multFile2, String package_path) {
		int id = trainDao.save(entity);
		if (!multFile1.isEmpty()) {
			commonService.saveFile(multFile1, id, Base.TRAIN_LECTURER_ENCLOSURE, package_path);
		}
		if (!multFile2.isEmpty()) {
			commonService.saveFile(multFile2, id, Base.TRAIN_LIST_OF, package_path);
		}
	}

	@Override
	public void update(BimrTrain entity, HhUsers user,
			MultipartFile multFile1, MultipartFile multFile2,
			String package_path) {
		trainDao.update(entity);
		if (!multFile1.isEmpty()) {
			HhFile _file = commonService.getFileByEnIdAndType(entity.getId(), Base.TRAIN_LECTURER_ENCLOSURE);
			if (_file != null) {
				File file = new File(_file.getFilePath());
				file.delete();
				updateFile(_file, multFile1, package_path);
			}else{
				commonService.saveFile(multFile1, entity.getId(), Base.TRAIN_LECTURER_ENCLOSURE, package_path);
			}
		}
		if (!multFile2.isEmpty()) {
			HhFile _file = commonService.getFileByEnIdAndType(entity.getId(), Base.TRAIN_LIST_OF);
			if (_file != null) {
				File file = new File(_file.getFilePath());
				file.delete();
				updateFile(_file, multFile2, package_path);
			}else{
				commonService.saveFile(multFile2, entity.getId(), Base.TRAIN_LIST_OF, package_path);
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

	@Override
	public void saveExamine(BimrTrain entity, String examStr,
			Integer examResult, HhUsers user) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//审核不通过
		if(examResult.equals(Base.examresult_1)){
			entity.setStatus(Base.examstatus_3);
		}else if(examResult.equals(Base.examresult_2)){ //审核通过
			entity.setStatus(Base.examstatus_4);
		}
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(user.getVcEmployeeId());
		entity.setAuditorPersonName(user.getVcName());
		update(entity);
		examineService.saveExamine(entity.getId(), Base.TRAIN_EXAMINE, user, examStr, examResult);
	}

	@Override
	public List<BimrTrain> getTrainListExport(BimrTrain entity,
			String dataAuthority) {
		// TODO Auto-generated method stub
		return trainDao.getTrainListExport(entity, dataAuthority);
	}
}

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
import com.softline.dao.bimr.IDutyDao;
import com.softline.dao.bimr.IEmpArchivesDao;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrDuty;
import com.softline.service.bimr.IDutyService;
import com.softline.service.bimr.IEmpArchivesService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

/**
 * 合规培训ServiceImpl
 * @author pengguolin
 */
@Service("dutyService")
public class DutyServiceImpl implements IDutyService {
	@Autowired @Qualifier("dutyDao") private IDutyDao dutyDao;
	@Autowired private ICommonService commonService;
	@Autowired private IExamineService examineService;
	@Autowired private IEmpArchivesDao empArchivesDao;
	@Override
	public MsgPage getDutyList(BimrDuty entity, Integer curPageNum,
		Integer pageSize, String dataAuthority) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = dutyDao.getDutyListCount(entity, dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum, pageSize);  
    	//分页查询结果集
    	List<BimrDuty> list = dutyDao.getDutyList(entity, offset, pageSize, dataAuthority);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	@Override
	public MsgPage getExamineDutyList(BimrDuty entity, Integer curPageNum,
		Integer pageSize, String dataAuthority) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = dutyDao.getExamineDutyListCount(entity, dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum, pageSize);  
    	//分页查询结果集
    	List<BimrDuty> list = dutyDao.getExamineDutyList(entity, offset, pageSize, dataAuthority);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public BimrDuty getBimrDuty(BimrDuty entity) {
		return dutyDao.getById(entity.getId());
	}

	@Override
	public void save(BimrDuty entity) {
		dutyDao.save(entity);
	}

	@Override
	public void update(BimrDuty entity) {
		dutyDao.update(entity);
	}

	@Override
	public BimrDuty getBimrDutyById(int id) {
		return dutyDao.getById(id);
	}

	@Override
	public void save(BimrDuty entity, HhUsers user, MultipartFile multFile, String package_path) {
		int id = dutyDao.save(entity);
		if (!multFile.isEmpty()) {
			commonService.saveFile(multFile, id, Base.DUTY_ENCLOSURE, package_path);
		}
	}

	@Override
	public void update(BimrDuty entity, HhUsers user, MultipartFile multFile, String package_path) {
		dutyDao.update(entity);
		if (!multFile.isEmpty()) {
			HhFile _file = commonService.getFileByEnIdAndType(entity.getId(), Base.DUTY_ENCLOSURE);
			if (_file != null) {
				File file = new File(_file.getFilePath());
				file.delete();
				updateFile(_file, multFile, package_path);
			}else{
				commonService.saveFile(multFile, entity.getId(), Base.DUTY_ENCLOSURE, package_path);
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
	public void saveExamine(BimrDuty entity, String examStr,
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
		examineService.saveExamine(entity.getId(), Base.DUTY_EXAMINE, user, examStr, examResult);
	}

	@Override
	public BimrDuty getBimrDutyByAccountableId(Integer id) {
		// TODO Auto-generated method stub
		return dutyDao.getBimrDutyByAccountableId(id);
	}

	
}

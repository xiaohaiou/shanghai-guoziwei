package com.softline.service.project.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.dao.portal.IMsgDao;
import com.softline.dao.project.IContractDao;
import com.softline.dao.project.INodeDao;
import com.softline.dao.project.IPjAuthorityPersonDao;
import com.softline.dao.project.IPjPcPayrecordDao;
import com.softline.dao.project.IProjectDao;
import com.softline.dao.project.IWkReportDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.IProcedureOpDao;
import com.softline.entity.HhFile;
import com.softline.entity.HhProcedureOpRecord;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.project.PjApprove;
import com.softline.entity.project.PjAuthorityPerson;
import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjContractTemp;
import com.softline.entity.project.PjNode;
import com.softline.entity.project.PjNodeTemp;
import com.softline.entity.project.PjPcPayrecord;
import com.softline.entity.project.PjPcPayrecordTemp;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjProjectHistory;
import com.softline.entity.project.PjProjectvideo;
import com.softline.entity.project.PjWeekreport;
import com.softline.entity.project.PjWeekreportTemp;
import com.softline.service.project.IProjectService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.impl.CommonService;
import com.softline.util.MsgPage;
@Service("projectService")
public class ProjectService implements IProjectService {
	
	@Autowired
	@Qualifier("projectDao")
	private IProjectDao projectDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("commonService")
	private ICommonService commonService;
	
	@Autowired
	@Qualifier("pjPcPayrecordDao")
	private IPjPcPayrecordDao pjPcPayrecordDao;
	
	
	@Autowired
	@Qualifier("nodeDao")
	private INodeDao nodeDao;
	
	@Autowired
	@Qualifier("contractDao")
	private IContractDao contractDao;
	
	@Autowired
	@Qualifier("potalMsgService")
	private IPortalMsgService portalMsgService;
	
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;
	
	
	@Autowired
	@Qualifier("wkReportDao")
	private IWkReportDao wkReportDao;
	
	@Autowired
	@Qualifier("msgDao")
	private IMsgDao msgDao;
	
	@Autowired
	@Qualifier("pjAuthorityPersonDao")
	private IPjAuthorityPersonDao pjAuthorityPersonDao;
	
	@Autowired
	@Qualifier("procedureOpDao")
	private IProcedureOpDao procedureOpDao;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public MsgPage getPjProjects(String vcEmployeeId,String pjDeptId,PjProject condition, Integer curPageNum,
			Integer pageSize) {
		
		String ids = getPorjetcIds(vcEmployeeId);
		if("view".equals(vcEmployeeId)){//查看列表页面
			ids = "view";
		}
		MsgPage msgPage = new MsgPage();
		//总记录数
		 Integer totalRecords = projectDao.getPjProjects(ids,pjDeptId,condition,null,null).size();
       
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PjProject> list = projectDao.getPjProjects(ids,pjDeptId,condition, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	@Override
	public Integer countPjProjects(String vcEmployeeId,String pjDeptId,PjProject condition){
		String ids = getPorjetcIds(vcEmployeeId);
		if("view".equals(vcEmployeeId)){//查看列表页面
			ids = "view";
		}
		MsgPage msgPage = new MsgPage();
		//总记录数
		 Integer totalRecords = projectDao.getPjProjects(ids,pjDeptId,condition,null,null).size();
        return totalRecords;
	}

	/**
	 * 获取用户ID能上报的重点基建工程的IDs,通过逗号分隔
	 * @param vcEmployeeId 用户ID
	 * @return
	 */
	private String getPorjetcIds(String vcEmployeeId){
		PjAuthorityPerson entity = new PjAuthorityPerson();
		entity.setPersonType(0);//上报人
		entity.setPersonVcEmployeeId(vcEmployeeId);
		List<PjAuthorityPerson>  results = pjAuthorityPersonDao.getPjAuthorityPerson(entity, null, null);
		String result = "";
		for(int i = 0; i < results.size(); i++){
			result += results.get(i).getPjId()+",";
		}
		if(Common.notEmpty(result)){
			result = result.substring(0,result.length()-1);
		}
		
		return result;
	}

	@Override
	public void saveAddProject(HhUsers user, PjProject project,String nodeIds,String wkReportIds,String contractIds, MultipartFile file,MultipartFile videoFile,Integer isReport) {
		String data = df.format(new Date());
		project.setCreateDate(data);
		project.setCreatePersonId(user.getVcEmployeeId());
		project.setCreatePersonName(user.getVcName());
		project.setIsdel(0);
		project.setReportStatus(isReport);
		if(isReport == 1){
			project.setReportPersonId(user.getVcEmployeeId());
			project.setReportPersonName(user.getVcName());
			project.setReportTime(df.format(new Date()));
		}
		project.setVersion(0);
		project.setPjDate(data.substring(0,10));
		commonDao.saveOrUpdate(project);
		//保存附件
		if (file!= null && !file.isEmpty()) {
			if (project.getProjectImgFile() != null) {
				String uuid = project.getProjectImgFile().getFileUuid();
				Common.deleteFile(DES.PROJECT_PJ_PATH, uuid);
				commonDao.delete(project.getProjectImgFile());
			}
			HhFile f = commonService.saveFile(file, project.getId(),
					Base.PROJECT_FILE_TYPE,DES.PROJECT_PJ_PATH);
			project.setImgPath(f.getFilePath());
		}
		
		//保存离线视屏
		if (videoFile!= null && !videoFile.isEmpty()) {
			saveOrUpdateLxVideo(project.getId(), videoFile, user,0);
		}
		
		//关联节点
		linkNode(user,nodeIds, project.getId(),isReport);
		//关联周报
		linkWk(user,wkReportIds,project.getId(),isReport);
		//关联合同
		linkContract(user,contractIds, project.getId(),isReport);
	}
	
	/**
	 * 关联节点
	 * 将临时表的数据移到操作表中
	 * @param nodeIds 临时节点IDs
	 * @param pjId 项目ID
	 * @param isReport 是否上报
	 */
	private void linkNode(HhUsers user,String nodeIds,Integer pjId,Integer isReport){
		if(Common.notEmpty(nodeIds)){
			String[] nodes = nodeIds.split(",");
			for(int i = 0; i < nodes.length; i++){
				PjNodeTemp nodeTemp = (PjNodeTemp) commonDao.findById(PjNodeTemp.class, Common.parseInteger(nodes[i]));
				PjNode node = new PjNode();
				BeanUtils.copyProperties(nodeTemp, node);
				node.setId(null);
				commonDao.saveOrUpdate(node);
				node.setVersion(0);
				node.setReportStatus(isReport);//未上报
				if(isReport == 1){
					node.setReportPersonId(user.getVcEmployeeId());
					node.setReportPersonName(user.getVcName());
					node.setReportTime(df.format(new Date()));
				}
				node.setPjId(pjId);
				commonDao.delete(nodeTemp);
			}
		}
	}
	
	/**
	 * 关联周报
	 * 将临时表的数据移到操作表中
	 * @param wkReportIds 周报IDs
	 * @param pjId 项目ID
	 * @param isReport 是否上报
	 */
	private void linkWk(HhUsers user,String wkReportIds,Integer pjId,Integer isReport){
		if(Common.notEmpty(wkReportIds)){
			String[] wkReprots = wkReportIds.split(",");
			for(int i = 0; i < wkReprots.length; i++){
				PjWeekreportTemp wkTemp = (PjWeekreportTemp) commonDao.findById(PjWeekreportTemp.class, Common.parseInteger(wkReprots[i]));
				PjWeekreport wk = new PjWeekreport();
				BeanUtils.copyProperties(wkTemp, wk);
				wk.setId(null);
				commonDao.saveOrUpdate(wk);
				wk.setVersion(0);
				wk.setPjId(pjId);
				wk.setReportStatus(isReport);//未上报
				if(isReport == 1){
					wk.setReportPersonId(user.getVcEmployeeId());
					wk.setReportPersonName(user.getVcName());
					wk.setReportTime(df.format(new Date()));
				}
				
				//将附件关系移到wkReprots中
				File source = new File(wk.getWrLine());
				File target = new File(DES.PROJECT_WEEKLINE_NORMAL_PATH+pjId+File.separator+wk.getId()+File.separator+"z"+wk.getWrWeek()+".pdf");
				fileChannelCopy(source, target);
				Common.deleteFile(source.getParent(), source.getName());
				wk.setWrLine(DES.PROJECT_WEEKLINE_NORMAL_PATH+pjId+File.separator+wk.getId()+File.separator+"z"+wk.getWrWeek()+".pdf");
			}
		}
	}
	
	/**
	 * 利用通道实现文件复制
	 * @param source 源文件 
	 * @param target 目标文件
	 * @return
	 */
	private  boolean fileChannelCopy(File source, File target) {  
	    if(!source.exists()){  
	        return false;  
	    }  
	    File targetPath = new File(target.getParent());  
	    if(!targetPath.exists()){  
	        targetPath.mkdirs();  
	    }  
	    try {  
	        FileInputStream fileInputStream = new FileInputStream(source);  
	        FileOutputStream fileOutputStream = new FileOutputStream(target);  
	        FileChannel fileChannelIn = fileInputStream.getChannel();// 得到对应的文件通道  
	        FileChannel fileChannelOut = fileOutputStream.getChannel();// 得到对应的文件通道  
	        fileChannelIn.transferTo(0, fileChannelIn.size(), fileChannelOut);// 连接两个通道，并且从in通道读取，然后写入out通道  
	        // 人走带门  
	        fileInputStream.close();  
	        fileChannelIn.close();  
	        fileOutputStream.close();  
	        fileChannelOut.close();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	    return true;  
	}  
	
	/**
	 * 关联合同
	 * 将临时表的数据移到操作表中
	 * @param contractIds 合同节点信息
	 * @param pjId 项目ID
	 * @param isReport 是否上报
	 */
	private void linkContract(HhUsers user,String contractIds,Integer pjId,Integer isReport){
		if(Common.notEmpty(contractIds)){
			String[] contracts = contractIds.split(",");
			for(int i = 0; i < contracts.length; i++){
				PjContractTemp temp = (PjContractTemp) commonDao.findById(PjContractTemp.class, Common.parseInteger(contracts[i]));
				PjContract contract = new PjContract();
				BeanUtils.copyProperties(temp, contract);
				contract.setId(null);
				commonDao.saveOrUpdate(contract);
				contract.setVersion(0);
				contract.setPjId(pjId);
				contract.setReportStatus(isReport);//未上报
				if(isReport == 1){
					contract.setReportPersonId(user.getVcEmployeeId());
					contract.setReportPersonName(user.getVcName());
					contract.setReportTime(df.format(new Date()));
				}
				List<PjPcPayrecordTemp> recordTemps = pjPcPayrecordDao.getPayrecordTemps(temp.getId());
				for(int j = 0; j < recordTemps.size(); j++ ){
					PjPcPayrecordTemp reTemp = recordTemps.get(j);
					PjPcPayrecord record = new PjPcPayrecord();
					BeanUtils.copyProperties(reTemp, record);
					record.setId(null);
					commonDao.saveOrUpdate(record);
					record.setPcId(contract.getId());
					commonDao.delete(reTemp);
				}
				commonDao.delete(temp);
			}
		}
	}



	@Override
	public void saveModifyProject(HhUsers user, PjProject project,
			PjProject oldEntity, MultipartFile file,MultipartFile videoFile) {
		String date = df.format(new Date());
		project.setLastModifyDate(date);
		project.setLastModifyPersonId(user.getVcEmployeeId());
		project.setLastModifyPersonName(user.getLastModifyPersonName());
		
		project.setCreateDate(oldEntity.getCreateDate());
		project.setCreatePersonId(oldEntity.getCreatePersonId());
		project.setCreatePersonName(oldEntity.getCreatePersonName());
		
		project.setReportPersonId(oldEntity.getReportPersonId());
		project.setReportPersonName(oldEntity.getReportPersonName());
		project.setReportTime(oldEntity.getReportTime());
		if(oldEntity.getReportStatus() > 1){
			project.setReportStatus(0);//修改后变为未上报状态
		}else{
			project.setReportStatus(oldEntity.getReportStatus());
		}
		
		project.setVersion(oldEntity.getVersion());
		project.setApproveId(oldEntity.getApproveId());
		project.setIsdel(0);
		project.setProjectImgFile(oldEntity.getProjectImgFile());
		commonService.saveOrUpdate(project);
		
		project.setImgPath(oldEntity.getImgPath());
		project.setPjDate(date.substring(0,10));//最新更新时间
		project.setPjSort(oldEntity.getPjSort());
		project.setPjVideoId(oldEntity.getPjVideoId());
		project.setPjVideoStatus(oldEntity.getPjVideoStatus());
		project.setOuterPjId(oldEntity.getOuterPjId());
		
		//保存附件
		if (file!= null && !file.isEmpty()) {
			if (project.getProjectImgFile() != null) {
				String uuid = project.getProjectImgFile().getFileUuid();
				Common.deleteFile(DES.PROJECT_PJ_PATH, uuid);
				commonDao.delete(project.getProjectImgFile());
			}
			HhFile f = commonService.saveFile(file, project.getId(),
					Base.PROJECT_FILE_TYPE,DES.PROJECT_PJ_PATH);
			project.setImgPath(f.getFilePath());
		}
		
		//保存离线视屏
		if (videoFile!= null && !videoFile.isEmpty()) {
			saveOrUpdateLxVideo(project.getId(), videoFile, user,0);
		}
		
		
	}



	@Override
	public String saveReport(HhUsers user, Integer id) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//project
		PjProject project = (PjProject) commonDao.findById(PjProject.class, id);
		if(project.getReportStatus() >= 1){//如果在该用户还没有上报时，已有其他用户上报了。结束上报返回fail
			return "fail";
		}
		if(project.getIsdel() == 1){
			return "isdel";
		}
		project.setReportPersonId(user.getVcEmployeeId());
		project.setReportPersonName(user.getVcName());
		project.setReportTime(sdf.format(new Date()));
		if(project.getReportStatus() == 0){
			project.setReportStatus(1);//待审核
		}
		String parentCompanyId = systemService.getParentBynNodeID(project.getPjDeptId()+"",null);
		if(project.getPjDeptId().equals(Base.HRTop)){//海航实业，父id是他自己本身
			parentCompanyId = project.getPjDeptId()+"";
		}
		commonDao.saveOrUpdate(project);
//		PortalMsg projectMsg = msgDao.getPortMsg("pj_project", project.getId()+"");
//		if(projectMsg == null){
//			projectMsg = new PortalMsg();
//			projectMsg.setTitle(project.getPjName()+"项目的基本信息需要审核");
//			projectMsg.setDate(df.format(new Date()));
//			projectMsg.setCompany(project.getPjDeptId()+"");
//			projectMsg.setParentCompany(parentCompanyId);
//			projectMsg.setBusiness("bimWork_zdgcsjsh_exam");
//			projectMsg.setTableName("pj_project");
//			projectMsg.setRecordId(project.getId()+"");
//			projectMsg.setDelFlag(0);
//			projectMsg.setIsIssue(0);
//			projectMsg.setType(0);
//			projectMsg.setCreator(user.getVcEmployeeId());
//			projectMsg.setCreateTime(df.format(new Date()));
//		}else{
//			projectMsg.setModifyTime(df.format(new Date()));
//			projectMsg.setModifier(user.getVcEmployeeId());
//		}
//		commonDao.saveOrUpdate(projectMsg);
		
		//node
		List<PjNode> nodes = nodeDao.getNodes(id,null,null);
		String nodeIds = "";
		for(int i = 0;i < nodes.size(); i++){
			PjNode node = nodes.get(i);
			if(node.getReportStatus() == 0 ){//未上报的上报
				node.setReportPersonId(user.getVcEmployeeId());
				node.setReportPersonName(user.getVcName());
				node.setReportStatus(1);//待审核
				node.setReportTime(sdf.format(new Date()));
				commonDao.saveOrUpdate(node);
				nodeIds = nodeIds + node.getId() + ",";
			}
		}
//		if(Common.notEmpty(nodeIds)){
//			PortalMsg nodeMsg = msgDao.getPortMsg("pj_node", project.getId()+"");
//			if(nodeMsg == null){
//				nodeMsg = new PortalMsg();
//				nodeMsg.setTitle(project.getPjName()+"项目的节点信息需要审核");
//				nodeMsg.setDate(df.format(new Date()));
//				nodeMsg.setCompany(project.getPjDeptId()+"");
//				nodeMsg.setParentCompany(parentCompanyId);
//				nodeMsg.setBusiness("bimWork_zdgcsjsh_exam");
//				nodeMsg.setTableName("pj_node");
//				nodeMsg.setRecordId(project.getId()+"");
//				nodeMsg.setDelFlag(0);
//				nodeMsg.setIsIssue(0);
//				nodeMsg.setType(0);
//				nodeMsg.setCreator(user.getVcEmployeeId());
//				nodeMsg.setCreateTime(df.format(new Date()));
//			}else{
//				nodeMsg.setModifyTime(df.format(new Date()));
//				nodeMsg.setModifier(user.getVcEmployeeId());
//			}
//			commonDao.saveOrUpdate(nodeMsg);
//		}
		
		//wkReport
		List<PjWeekreport> wkReports = wkReportDao.getWkReports(id, null, null);
		String wkIds = "";
		for(int i = 0;i < wkReports.size(); i++){
			PjWeekreport wkReport = wkReports.get(i);
			if(wkReport.getReportStatus() == 0){//未上报的上报
				wkReport.setReportPersonId(user.getVcEmployeeId());
				wkReport.setReportPersonName(user.getVcName());
				wkReport.setReportStatus(1);//待审核
				wkReport.setReportTime(sdf.format(new Date()));
				commonDao.saveOrUpdate(wkReport);
				wkIds = wkIds + wkReport.getId() + ",";
			}
			
		}
//		if(Common.notEmpty(wkIds)){
//			PortalMsg wrMsg = msgDao.getPortMsg("pj_weekreport", project.getId()+"");
//			if(wrMsg == null){
//				wrMsg = new PortalMsg();
//				wrMsg.setTitle(project.getPjName()+"项目的周报信息需要审核");
//				wrMsg.setDate(df.format(new Date()));
//				wrMsg.setCompany(project.getPjDeptId()+"");
//				wrMsg.setParentCompany(parentCompanyId);
//				wrMsg.setBusiness("bimWork_zdgcsjsh_exam");
//				wrMsg.setTableName("pj_weekreport");
//				wrMsg.setRecordId(project.getId()+"");
//				wrMsg.setDelFlag(0);
//				wrMsg.setIsIssue(0);
//				wrMsg.setType(0);
//				wrMsg.setCreator(user.getVcEmployeeId());
//				wrMsg.setCreateTime(df.format(new Date()));
//			}else{
//				wrMsg.setModifyTime(df.format(new Date()));
//				wrMsg.setModifier(user.getVcEmployeeId());
//			}
//			commonDao.saveOrUpdate(wrMsg);
//		}
		
		//contract
		List<PjContract> contracts = contractDao.getContracts(id, null, null);
		String contratIds = "";
		for(int i = 0;i < contracts.size(); i++){
			PjContract contract = contracts.get(i);
			if(contract.getReportStatus() == 0){//未上报的上报
				contract.setReportPersonId(user.getVcEmployeeId());
				contract.setReportPersonName(user.getVcName());
				contract.setReportStatus(1);//待审核
				contract.setReportTime(sdf.format(new Date()));
				commonDao.saveOrUpdate(contract);
				contratIds = contratIds + contract.getId() + ",";
			}
		}
//		if(Common.notEmpty(contratIds)){
//			PortalMsg contractMsg = msgDao.getPortMsg("pj_contract", project.getId()+"");
//			if(contractMsg == null ){
//				contractMsg = new PortalMsg();
//				contractMsg.setTitle(project.getPjName()+"项目的合同需要审核");
//				contractMsg.setDate(df.format(new Date()));
//				contractMsg.setCompany(project.getPjDeptId()+"");
//				contractMsg.setParentCompany(parentCompanyId);
//				contractMsg.setBusiness("bimWork_zdgcsjsh_exam");
//				contractMsg.setTableName("pj_contract");
//				contractMsg.setRecordId(project.getId()+"");
//				contractMsg.setDelFlag(0);
//				contractMsg.setIsIssue(0);
//				contractMsg.setType(0);
//				contractMsg.setCreator(user.getVcEmployeeId());
//				contractMsg.setCreateTime(df.format(new Date()));
//			}else{
//				contractMsg.setModifyTime(df.format(new Date()));
//				contractMsg.setModifier(user.getVcEmployeeId());
//			}
//			commonDao.saveOrUpdate(contractMsg);
//		}
		return "success";
	}



	@Override
	public void saveandreport1(HhUsers user, PjProject project, String nodeIds,
			String wkReportIds, String contractIds, MultipartFile file,MultipartFile videoFile,
			Integer isReport) {
		//保存
		saveAddProject(user, project, nodeIds, wkReportIds, contractIds, file,videoFile, isReport);
		//上报
		saveReport(user, project.getId());
	}



	@Override
	public void saveandreport2(HhUsers user, PjProject project,
			PjProject oldProject, MultipartFile file,MultipartFile videoFile) {
		//保存
		saveModifyProject(user, project, oldProject, file,videoFile);
		//上报
		saveReport(user, project.getId());
		
	}



	@Override
	public List<PjProjectHistory> getPjProjectHistories(Integer pjId) {
		List<PjProjectHistory> results = projectDao.getPjProjectHistories(pjId);
		for(int i = 0; i < results.size(); i++){
			PjProjectHistory entity = results.get(i);
			if(entity.getApproveId()!=null){
				PjApprove approve = (PjApprove) commonDao.findById(PjApprove.class, entity.getApproveId());
				entity.setApprove(approve);
			}
		}
		return results;
	}



	@Override
	public void deleteProject(Integer id, HhUsers users) {
		// TODO Auto-generated method stub
		PjProject project = (PjProject) commonDao.findById(PjProject.class, id);
		project.setIsdel(1);
		project.setLastModifyDate(df.format(new Date()));
		project.setLastModifyPersonId(users.getVcEmployeeId());
		project.setLastModifyPersonName(users.getVcName());
		
		PjProjectHistory projectHistory = new PjProjectHistory();
		BeanUtils.copyProperties(project, projectHistory);
		projectHistory.setId(null);
		projectHistory.setProjectId(project.getId());
		commonDao.saveOrUpdate(projectHistory);
		
		project.setVersion(project.getVersion()==null?1:(project.getVersion()+1));
		
	}



	@Override
	public List<PjProject> getAllPjProjects(PjProject condition) {
		return projectDao.getAllPjProjects();
	}
	
	/**
	 * 保存或者修改离线视屏
	 * @param pjId
	 * @param videoFile
	 */
	private void saveOrUpdateLxVideo(Integer pjId,MultipartFile videoFile,HhUsers users,Integer type){
		PjProjectvideo video = projectDao.getProjectVideoByPjId(pjId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		if(video != null){ //编辑
			String uuid = video.getPjVideoUuid();
			//Common.deleteFile(DES.PROJECT_LXVIDEO_PATH, uuid);
			video.setLastModifyDate(date);
			video.setLastModifyPersonId(users.getVcEmployeeId());
			video.setLastModifyPersonName(users.getVcName());
		}else{//新增
			video = new PjProjectvideo();	
			video.setCreateDate(date);
			video.setCreatePersonId(users.getVcEmployeeId());
			video.setCreatePersonName(users.getVcName());
		}
		if(type != 1){
			video.setVideoStatus(0);
		}else{
			video.setVideoStatus(2);
		}
		video.setPjId(pjId);
		video.setIsdel(0);
		saveFile(videoFile, DES.PROJECT_LXVIDEO_PATH, video);
		
		
		commonDao.saveOrUpdate(video);
	}
	
	/**
	 * 保存文件到服务器硬盘上面
	 * @param picture
	 * @param package_path
	 * @param video
	 */
	public void saveFile(MultipartFile picture, String package_path,PjProjectvideo video) {
			List<String> fileStrList = Common.saveFile(picture, package_path);
			video.setPjVideoName(fileStrList.get(0));
			video.setPjVideoPath(fileStrList.get(1));
			video.setPjVideoUuid(fileStrList.get(2));
	}

	@Override
	public PjProjectvideo getProjectVideoByPjId(Integer pjId) {
		return projectDao.getProjectVideoByPjId(pjId);
	}


	@Override
	public void saveAdminOPProject(HhUsers user, Integer pjId, Integer pjSort,String outerPjId,
			MultipartFile videoFile) {
		PjProject project = (PjProject) commonDao.findById(PjProject.class, pjId);
		//保存离线视频
		if (videoFile!= null && !videoFile.isEmpty()) {
			saveOrUpdateLxVideo(pjId, videoFile, user,1);
		}
		//保存项目排序
		project.setPjSort(pjSort);
		
		//保存对应海建工程的项目ID
		project.setOuterPjId(outerPjId);
		
		//修改历史记录中最大的那条，使得数据转换后立即生效
		PjProjectHistory history = projectDao.getMaxVersionProject(pjId);
		if(history != null){
			history.setPjSort(pjSort);
		}
		
	}


	@Override
	public String saveProjectAdminSyn(HhUsers users) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//重点工程基本信息
		HhProcedureOpRecord record = procedureOpDao.runProjectAdminSyn();
		record.setOpTime(df.format(new Date()));
		record.setOpVcEmployeeId(users.getVcEmployeeId());
		record.setOpVcName(users.getVcName());
		commonDao.saveOrUpdate(record);
		//重点工程视频信息
		HhProcedureOpRecord record1 = procedureOpDao.runProjectVideoSyn();
		record1.setOpTime(df.format(new Date()));
		record1.setOpVcEmployeeId(users.getVcEmployeeId());
		record1.setOpVcName(users.getVcName());
		commonDao.saveOrUpdate(record1);
		
		if(record.getOpResult() == 1 && record1.getOpResult() == 1){
			return "success";
		}else{
			return "error";
		}		
	}
	

}

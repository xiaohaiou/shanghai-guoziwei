package com.softline.service.project.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.dao.portal.IMsgDao;
import com.softline.dao.project.IPjApproveDao;
import com.softline.dao.project.IPjAuthorityPersonDao;
import com.softline.dao.project.IProjectDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.project.PjApprove;
import com.softline.entity.project.PjAuthorityPerson;
import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjContractHistory;
import com.softline.entity.project.PjNode;
import com.softline.entity.project.PjNodeHistory;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjProjectHistory;
import com.softline.entity.project.PjProjectvideo;
import com.softline.entity.project.PjWeekreport;
import com.softline.entity.project.PjWeekreportHistory;
import com.softline.entityTemp.VContractChange;
import com.softline.entityTemp.VNodeChange;
import com.softline.entityTemp.VProjectChange;
import com.softline.service.project.IPjApproveService;
import com.softline.util.MsgPage;
@Service("pjApproveService")
public class PjApproveService implements IPjApproveService {
	@Autowired
	@Qualifier("pjApproveDao")
	private IPjApproveDao pjApproveDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("projectDao")
	private IProjectDao projectDao;
	
	@Autowired
	@Qualifier("msgDao")
	private IMsgDao msgDao;
	
	@Autowired
	@Qualifier("pjAuthorityPersonDao")
	private IPjAuthorityPersonDao pjAuthorityPersonDao;

	@Override
	public MsgPage getPjProjects(String vcEmployeeId,String pjDeptId,PjProject condition, Integer curPageNum,
			Integer pageSize) {
		String ids = getPorjetcIds(vcEmployeeId);
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<PjProject> t = pjApproveDao.getPjProjects(ids,pjDeptId,condition, null, null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PjProject> list = pjApproveDao.getPjProjects(ids,pjDeptId,condition, offset, pageSize);
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
		entity.setPersonType(1);//上报人
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
	public List<VProjectChange> getProjectChanges(Integer pjId) {
		// TODO Auto-generated method stub
		return pjApproveDao.getProjectChanges(pjId);
	}

	@Override
	public List<VNodeChange> getNodeChanges(Integer pjId) {
		// TODO Auto-generated method stub
		return pjApproveDao.getNodeChanges(pjId);
	}

	@Override
	public List<VContractChange> getContractChanges(Integer pjId) {
		// TODO Auto-generated method stub
		return pjApproveDao.getContractChanges(pjId);
	}

	@Override
	public String  saveProjectApprove(PjApprove approve) {
		//1、保存审核结果 2、保存基本信息的审核状态和记录审核ID， 3、保存历史表 4、基本信息version加1
		PjProject project = (PjProject) commonDao.findById(PjProject.class, approve.getPjId());
		if(project.getIsdel() == 1 || project.getReportStatus() == 0 || project.getReportStatus() == 2 || project.getReportStatus() == 3){
			return "fail";
		}
		commonDao.saveOrUpdate(approve);
		//取消公告
//		msgDao.updatePortalMsg("pj_project", project.getId()+"");
		
		if(approve.getApproveResult() == 1){//审核通过
			project.setReportStatus(2);//审核通过
		}else if(approve.getApproveResult() == 0){//审核退回
			project.setReportStatus(3);//审核退回
		}
		project.setApproveId(approve.getId());
		
		PjProjectHistory projectHistory = new PjProjectHistory();
		BeanUtils.copyProperties(project, projectHistory);
		projectHistory.setId(null);
		projectHistory.setProjectId(project.getId());
		commonDao.saveOrUpdate(projectHistory);
		
		project.setVersion(project.getVersion()==null?1:(project.getVersion()+1));
		
		PjProjectvideo video = projectDao.getProjectVideoByPjId(project.getId());
		if(video !=null && video.getVideoStatus() != 2){//离线视频不是审核通过的状态修改审核状态
			video.setVideoStatus(project.getReportStatus());
			
		}
		return "success";
	}

	@Override
	public String saveNodeApprove(String nodeIds, PjApprove approve) {
		//1、保存审核结果 2、保存基本信息的审核状态和记录审核ID， 3、保存历史表 4、基本信息version加1
		if(Common.notEmpty(nodeIds)){
			String[] nodes = nodeIds.split(",");
			//验证
			for(int i = 0; i < nodes.length; i++){
				PjNode node1 = (PjNode) commonDao.findById(PjNode.class, Common.parseInteger(nodes[i]));
				if(node1.getIsdel() == 1 || node1.getReportStatus() == 0 || node1.getReportStatus() == 2 || node1.getReportStatus() == 3){
					return "fail";
				}
			}
			commonDao.saveOrUpdate(approve);
			//取消公告
//			PjNode node1 = (PjNode) commonDao.findById(PjNode.class, Common.parseInteger(nodes[0]));
//			msgDao.updatePortalMsg("pj_node", node1.getPjId()+"");
			//
			for(int i = 0; i < nodes.length; i++){
				PjNode node = (PjNode) commonDao.findById(PjNode.class, Common.parseInteger(nodes[i]));
				
				
				if(approve.getApproveResult() == 1){//审核通过
					node.setReportStatus(2);//审核通过
				}else if(approve.getApproveResult() == 0){//审核退回
					node.setReportStatus(3);//审核退回
				}
				node.setApproveId(approve.getId());
				
				PjNodeHistory nodeHistory = new PjNodeHistory();
				BeanUtils.copyProperties(node, nodeHistory);
				nodeHistory.setId(null);
				nodeHistory.setNodeId(node.getId());
				commonDao.saveOrUpdate(nodeHistory);
				
				node.setVersion(node.getVersion()==null?1:(node.getVersion()+1));
			}
			
		}
		return "success";
	}

	@Override
	public String saveWkReportApprove(String wkReportIds, PjApprove approve) {
		//1、保存审核结果 2、保存基本信息的审核状态和记录审核ID， 3、保存历史表 4、基本信息version加1
		if(Common.notEmpty(wkReportIds)){
			String[] wkReports = wkReportIds.split(",");
			for(int i = 0; i < wkReports.length; i++){
				PjWeekreport wkReport = (PjWeekreport) commonDao.findById(PjWeekreport.class, Common.parseInteger(wkReports[i]));
				if(wkReport.getIsdel() == 1 || wkReport.getReportStatus() == 0 || wkReport.getReportStatus() == 2 || wkReport.getReportStatus() == 3){
					return "fail";
				}
			}
			commonDao.saveOrUpdate(approve);
			//取消公告
//			PjWeekreport wkReport1 = (PjWeekreport) commonDao.findById(PjWeekreport.class, Common.parseInteger(wkReports[0]));
//			msgDao.updatePortalMsg("pj_weekreport", wkReport1.getPjId()+"");
			for(int i = 0; i < wkReports.length; i++){
				PjWeekreport wkReport = (PjWeekreport) commonDao.findById(PjWeekreport.class, Common.parseInteger(wkReports[i]));
				if(approve.getApproveResult() == 1){//审核通过
					wkReport.setReportStatus(2);//审核通过
				}else if(approve.getApproveResult() == 0){//审核退回
					wkReport.setReportStatus(3);//审核退回
				}
				wkReport.setApproveId(approve.getId());
				
				PjWeekreportHistory wkReportHistory = new PjWeekreportHistory();
				BeanUtils.copyProperties(wkReport, wkReportHistory);
				wkReportHistory.setId(null);
				wkReportHistory.setWkReportId(wkReport.getId());
				commonDao.saveOrUpdate(wkReportHistory);
				//将附件关系移到wkReprots中
				File source = new File(wkReport.getWrLine());
				File target = new File(DES.PROJECT_WEEKLINE_HISTORY_PATH+wkReport.getPjId()+File.separator+wkReportHistory.getId()+File.separator+"z"+wkReport.getWrWeek()+".pdf");
				fileChannelCopy(source, target);
				Common.deleteFile(source.getParent(), source.getName());
				wkReportHistory.setWrLine(DES.PROJECT_WEEKLINE_HISTORY_PATH+wkReport.getPjId()+File.separator+wkReportHistory.getId()+File.separator+"z"+wkReport.getWrWeek()+".pdf");
				
				wkReport.setVersion(wkReport.getVersion()==null?1:(wkReport.getVersion()+1));
			}
			
		}
		return "success";
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

	@Override
	public String saveContractApprove(String contractIds, PjApprove approve) {
		//1、保存审核结果 2、保存基本信息的审核状态和记录审核ID， 3、保存历史表 4、基本信息version加1
		if(Common.notEmpty(contractIds)){
			String[] contracts = contractIds.split(",");
			for(int i = 0; i < contracts.length; i++){
				PjContract contract = (PjContract) commonDao.findById(PjContract.class, Common.parseInteger(contracts[i]));
				if(contract.getIsdel() == 1 || contract.getReportStatus() == 0 || contract.getReportStatus() == 2 || contract.getReportStatus() == 3){
					return "fail";
				}
			}
			commonDao.saveOrUpdate(approve);
			//取消公告
//			PjContract contract1 = (PjContract) commonDao.findById(PjContract.class, Common.parseInteger(contracts[0]));
//			msgDao.updatePortalMsg("pj_contract", contract1.getPjId()+"");
			
			for(int i = 0; i < contracts.length; i++){
				PjContract contract = (PjContract) commonDao.findById(PjContract.class, Common.parseInteger(contracts[i]));
				if(approve.getApproveResult() == 1){//审核通过
					contract.setReportStatus(2);//审核通过
				}else if(approve.getApproveResult() == 0){//审核退回
					contract.setReportStatus(3);//审核退回
				}
				contract.setApproveId(approve.getId());
				
				PjContractHistory contractHistory = new PjContractHistory();
				BeanUtils.copyProperties(contract, contractHistory);
				contractHistory.setId(null);
				contractHistory.setContractId(contract.getId());
				commonDao.saveOrUpdate(contractHistory);
				
				contract.setVersion(contract.getVersion()==null?1:(contract.getVersion()+1));
			}
			
		}
		return "success";
	}

	@Override
	public List<PjApprove> getPjApproves(PjApprove pjApprove) {
		return pjApproveDao.getPjApproves(pjApprove);
	}
	
	
}

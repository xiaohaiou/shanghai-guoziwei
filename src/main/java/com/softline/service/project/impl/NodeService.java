package com.softline.service.project.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.project.INodeDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.impl.CommonDao;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjApprove;
import com.softline.entity.project.PjNode;
import com.softline.entity.project.PjNodeHistory;
import com.softline.entity.project.PjProject;
import com.softline.service.project.INodeService;
import com.softline.util.MsgPage;
@Service("nodeService")
public class NodeService implements INodeService {
	@Autowired
	@Qualifier("nodeDao")
	private INodeDao nodeDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Override
	public Integer getNodeLastVersion(Integer pjId) {
		return nodeDao.getNodeLastVersion(pjId);
	}

	@Override
	public MsgPage getNodes(Integer pjId, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<PjNode> t = nodeDao.getNodes(pjId, null,null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PjNode> list = nodeDao.getNodes(pjId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public List<PjNode> getNodes(Integer pjId, Integer reportStauts) {
		// TODO Auto-generated method stub
		return nodeDao.getNodes(pjId, reportStauts);
	}

	@Override
	public List<PjNodeHistory> getNodehHistories(Integer nodeId) {
		List<PjNodeHistory> results = nodeDao.getNodehHistories(nodeId);
		for(int i = 0; i < results.size(); i++){
			PjNodeHistory entity = results.get(i);
			PjApprove approve =(PjApprove) commonDao.findById(PjApprove.class, entity.getApproveId());
			entity.setApprove(approve);
		}
		return results;
	}

	@Override
	public void deleteNode(Integer id, HhUsers users) {
		// TODO Auto-generated method stub
		PjNode node = (PjNode) commonDao.findById(PjNode.class, id);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		node.setIsdel(1);
		node.setLastModifyDate(df.format(new Date()));
		node.setLastModifyPersonId(users.getVcEmployeeId());
		node.setLastModifyPersonName(users.getVcName());
		
		
		PjNodeHistory nodeHistory = new PjNodeHistory();
		BeanUtils.copyProperties(node, nodeHistory);
		nodeHistory.setId(null);
		nodeHistory.setNodeId(node.getId());
		commonDao.saveOrUpdate(nodeHistory);
		
		node.setVersion(node.getVersion()==null?1:(node.getVersion()+1));
	}

}

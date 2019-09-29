package com.softline.service.social.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.dao.social.ISocialVoluntaryDao;
import com.softline.dao.social.ISocialVoluntaryServiceDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.DataSocialVoluntary;
import com.softline.entity.DataVoluntaryService;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.DataSocialVoluntaryView;
import com.softline.service.social.ISocialVoluntaryService;
import com.softline.service.social.ISocialVoluntaryserviceService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.util.MsgPage;

@Service("socialVoluntaryserviceService")
/**
 * 
 * @author zy
 *
 */
public class SocialVoluntaryserviceService implements ISocialVoluntaryserviceService {

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Autowired
	@Qualifier("socialVoluntaryServiceDao")
	private ISocialVoluntaryServiceDao socialVoluntaryServiceDao;

	@Resource(name = "examineService")
	private IExamineService examineService;

	@Autowired
	@Qualifier("potalMsgService")
	private IPortalMsgService portalMsgService;

	/**
	 * 一览画面查询
	 * 
	 * @param entity
	 *            查询实体
	 * @param curPageNum
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param fun
	 *            机能分类
	 * @return
	 */
	public MsgPage getSocialVoluntaryListView(DataVoluntaryService entity,
			Integer curPageNum, Integer pageSize, Integer fun) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = socialVoluntaryServiceDao.getSocialVoluntaryListCount(
				entity, fun);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<DataVoluntaryService> list = socialVoluntaryServiceDao
				.getSocialVoluntaryList(entity, offset, pageSize, fun);
		// 数据格式转换
		List<DataSocialVoluntaryView> View = new ArrayList<DataSocialVoluntaryView>();
		for (DataVoluntaryService voluntary : list) {
			DataSocialVoluntaryView item = new DataSocialVoluntaryView();
			item.setId(voluntary.getId());
			item.setYear(voluntary.getYear());
			item.setMonth(voluntary.getMonth());
			item.setServiceCount(voluntary.getServiceCount().toString());
			item.setServiceDuration(voluntary.getServiceDuration().toString());
			item.setServicePerson(voluntary.getServicePerson().toString());
			
			item.setStatus(voluntary.getStatus());
			item.setIsdel(voluntary.getIsdel());
			item.setReportDate(voluntary.getReportDate());
			item.setReportPersonId(voluntary.getReportPersonId());
			item.setReportPersonName(voluntary.getReportPersonName());
			item.setAuditorDate(voluntary.getAuditorDate());
			item.setAuditorPersonId(voluntary.getAuditorPersonId());
			item.setAuditorPersonName(voluntary.getAuditorPersonName());
			item.setCreateDate(voluntary.getCreateDate());
			item.setCreatePersonId(voluntary.getCreatePersonId());
			item.setCreatePersonName(voluntary.getCreatePersonName());
			item.setLastModifyDate(voluntary.getLastModifyDate());
			item.setLastModifyPersonId(voluntary.getLastModifyPersonId());
			item.setLastModifyPersonName(voluntary.getLastModifyPersonName());
			View.add(item);
		}
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(View);
		return msgPage;
	}

	/**
	 * 单个ID查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataVoluntaryService getSocialVoluntary(Integer id) {
		return socialVoluntaryServiceDao.getSocialVoluntary(id);
	}

	/**
	 * 更新机能
	 * 
	 * @param entity
	 * @param use
	 * @param type
	 * @return
	 */
	public CommitResult saveSocialVoluntary(DataVoluntaryService entity,
			HhUsers use, String type) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 排他校验
		CommitResult result = new CommitResult();
		if (type != null) {
			result = saveCheck(entity, type);
			if (!result.isResult()) {
				return result;
			}
		}
		// 新增的场合
		if (entity.getId() == null) {
			entity.setIsdel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			// 更新的场合
		} else {
			entity.setIsdel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		// 保存并上报按钮
		if (type != null && type.equals("saveAndReport")) {
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
		}
		commonDao.saveOrUpdate(entity);
		// 工作提醒内容追加
		if (entity.getStatus().equals(Base.examstatus_2)) {
			PortalMsg portalMsg = new PortalMsg();
			portalMsg = new PortalMsg();
			portalMsg.setTitle(entity.getYear()+"年" + entity.getMonth()+"月志愿服务数据未审核");
			portalMsg.setDate(df.format(new Date()));
			portalMsg.setCompany(Base.HRTop);
			portalMsg.setParentCompany(Base.HRTop);
			portalMsg.setBusiness("bimWork_voluntarySh_exam");
			portalMsg.setTableName("data_social_voluntary");
			portalMsg.setRecordId(entity.getId().toString());
			portalMsg.setDelFlag(0);
			portalMsg.setIsIssue(0);
			portalMsg.setType(0);
			portalMsg.setCreator(use.getVcName());
			portalMsg.setCreateTime(df.format(new Date()));
			portalMsg.setModifier(use.getVcName());
			portalMsg.setModifyTime(df.format(new Date()));
			portalMsg.setUrl("/shanghai-gzw/social/voluntaryservice/examinelist?id="+entity.getId());
			portalMsgService.savePortalMsg(portalMsg);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	/**
	 * 删除功能
	 * 
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteSocialVoluntary(Integer id, HhUsers use) {
		DataVoluntaryService entity = socialVoluntaryServiceDao.getSocialVoluntary(id);
		CommitResult result = new CommitResult();
		if (entity != null) {
			if (entity.getStatus().equals(Base.examstatus_1)
					|| entity.getStatus().equals(Base.examstatus_3)) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				entity.setIsdel(1);
				entity.setLastModifyDate(df.format(new Date()));
				entity.setLastModifyPersonId(use.getVcEmployeeId());
				entity.setLastModifyPersonName(use.getVcName());
				commonDao.saveOrUpdate(entity);
				// 工作提醒内容删除
				portalMsgService.updatePortalMsg("data_social_voluntary",
						entity.getId().toString());
			} else {
				result = CommitResult.createErrorResult("该数据已被修改，不能删除！");
				return result;
			}
		} else {
			result = CommitResult.createErrorResult("该数据已被删除！");
			return result;
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	/**
	 * 审核功能
	 * 
	 * @param entityID
	 *            实体主键
	 * @param examStr
	 *            审核备注
	 * @param examResult
	 *            审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveSocialVoluntaryExamine(Integer entityID,
			String examStr, Integer examResult, HhUsers use) {

		CommitResult result = new CommitResult();
		// 排他校验
		result = examineCheck(entityID);
		if (!result.isResult()) {
			return result;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DataVoluntaryService entity = getSocialVoluntary(entityID);
		// 审核不通过
		if (examResult.equals(Base.examresult_1)) {
			entity.setStatus(Base.examstatus_3);
			// 审核通过
		} else if (examResult.equals(Base.examresult_2)) {
			entity.setStatus(Base.examstatus_4);
		}
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		// 保存实体信息
		CommitResult voluntaryResult = saveSocialVoluntary(entity, use, null);
		if (!voluntaryResult.isResult()) {
			return voluntaryResult;
		}

		DataVoluntaryService voluntary = (DataVoluntaryService) voluntaryResult
				.getEntity();
		Integer examineentityid = voluntary.getId();
		// 保存审核信息
		examineService.saveExamine(examineentityid, Base.examkind_voluntary,
				use, examStr, examResult);
		// 工作提醒内容删除
		portalMsgService.updatePortalMsg("data_social_voluntary", entity
				.getId().toString());
		result.setResult(true);
		return result;
	}

	/**
	 * 新增时排他校验
	 * 
	 * @param entity
	 * @return
	 */
	private CommitResult saveCheck(DataVoluntaryService entity, String type) {
		CommitResult result = new CommitResult();
		if (!socialVoluntaryServiceDao.saveSocialVoluntaryRepeatCheck(entity, type)) {
			if (type.equals("report")) {
				result = CommitResult.createErrorResult("该数据已经被上报,不能修改!");
			} else {
				result = CommitResult.createErrorResult(entity.getYear() + "年"
						+ entity.getMonth() + "月数据已存在，不能再添加");
			}
			return result;
		}
		if (!socialVoluntaryServiceDao.checkCanEdit(entity)) {
			result = CommitResult.createErrorResult("该数据已经被上报,不能修改!");
			return result;
		}
		result.setResult(true);
		return result;
	}

	/**
	 * 审核时排他校验
	 * 
	 * @param entityID
	 * @return
	 */
	private CommitResult examineCheck(Integer entityID) {
		CommitResult result = new CommitResult();
		DataVoluntaryService entity = socialVoluntaryServiceDao
				.getSocialVoluntary(entityID);
		if (entity == null) {
			result = CommitResult.createErrorResult("该数据已被删除,不能审核!");
			return result;
		}
//		if (!entity.getStatus().equals(Base.examstatus_2)) {
//			result = CommitResult.createErrorResult("该数据已被审核,不能再审核!");
//			return result;
//		}
		result.setResult(true);
		return result;
	}

}

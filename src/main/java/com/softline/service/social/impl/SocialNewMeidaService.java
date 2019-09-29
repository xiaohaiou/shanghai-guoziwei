package com.softline.service.social.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.dao.social.ISocialNewMediaDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.DataSocialNewMedia;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entityTemp.CommitResult;
import com.softline.service.social.ISocialNewMediaService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.util.MsgPage;

@Service("socialNewMediaService")
/**
 * 
 * @author zy
 *
 */
public class SocialNewMeidaService implements ISocialNewMediaService {

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Autowired
	@Qualifier("socialNewMediaDao")
	private ISocialNewMediaDao socialNewMediaDao;

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
	public MsgPage getSocialNewMediaListView(DataSocialNewMedia entity,
			Integer curPageNum, Integer pageSize, Integer fun) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = socialNewMediaDao.getSocialNewMediaListCount(
				entity, fun);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<DataSocialNewMedia> list = socialNewMediaDao
				.getSocialNewMediaList(entity, offset, pageSize, fun);
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}

	/**
	 * 单个ID查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataSocialNewMedia getSocialNewMedia(Integer id) {
		return socialNewMediaDao.getSocialNewMedia(id);
	}

	/**
	 * 更新机能
	 * 
	 * @param entity
	 * @param use
	 * @param type
	 * @return
	 */
	public CommitResult saveSocialNewMedia(DataSocialNewMedia entity,
			HhUsers use, String type, MultipartFile file) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 排他校验
		CommitResult result = new CommitResult();
		if (type != null) {
			result = saveCheck(entity, type);
			if (!result.isResult()) {
				return result;
			}
		}
		if (entity.getDateFrom().length() >= 10) {
			String year = entity.getDateFrom().substring(0, 4);
			String month = entity.getDateFrom().substring(5, 7);
			entity.setYear(Integer.valueOf(year));
			if (month.startsWith("0")) {
				month = month.substring(month.length() - 1);
			}
			entity.setMonth(Integer.valueOf(month));
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
		String filePath = DES.NEWMEDIA_WEEKLINE_PATH + entity.getId()
				+ File.separator;
		String fileName = entity.getDateFrom() + "~" + entity.getDateTo()
				+ "新媒体周报.pdf";
		// 保存附件
		if (file != null && !file.isEmpty()) {
			Common.deleteFile(filePath, fileName);
			Common.saveWkFile(file, filePath, fileName);
			entity.setAttachAdress(filePath + fileName);
		}
		// 工作提醒内容追加
		if (entity.getStatus().equals(Base.examstatus_2)) {
			PortalMsg portalMsg = new PortalMsg();
			portalMsg = new PortalMsg();
			portalMsg.setTitle(entity.getDateFrom()+"到"+entity.getDateTo()+"新媒体数据未审核");
			portalMsg.setDate(df.format(new Date()));
			portalMsg.setCompany(Base.HRTop);
			portalMsg.setParentCompany(Base.HRTop);
			portalMsg.setBusiness("bimWork_newMediaSh_exam");
			portalMsg.setTableName("data_social_newMedia");
			portalMsg.setRecordId(entity.getId().toString());
			portalMsg.setDelFlag(0);
			portalMsg.setIsIssue(0);
			portalMsg.setType(0);
			portalMsg.setCreator(use.getVcName());
			portalMsg.setCreateTime(df.format(new Date()));
			portalMsg.setModifier(use.getVcName());
			portalMsg.setModifyTime(df.format(new Date()));
			portalMsg.setUrl("/shanghai-gzw/social/newMedia/examinelist?id="+entity.getId());
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
	public CommitResult deleteSocialNewMedia(Integer id, HhUsers use) {
		DataSocialNewMedia entity = socialNewMediaDao.getSocialNewMedia(id);
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
				portalMsgService.updatePortalMsg("data_social_newMedia", entity
						.getId().toString());
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
	public CommitResult saveSocialNewMediaExamine(Integer entityID,
			String examStr, Integer examResult, HhUsers use) {

		CommitResult result = new CommitResult();
		// 排他校验
		result = examineCheck(entityID);
		if (!result.isResult()) {
			return result;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DataSocialNewMedia entity = getSocialNewMedia(entityID);
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
		CommitResult newMediaResult = saveSocialNewMedia(entity, use, null,
				null);
		if (!newMediaResult.isResult()) {
			return newMediaResult;
		}

		DataSocialNewMedia newMedia = (DataSocialNewMedia) newMediaResult
				.getEntity();
		Integer examineentityid = newMedia.getId();
		// 保存审核信息
		examineService.saveExamine(examineentityid, Base.examkind_newmedia,
				use, examStr, examResult);
		// 工作提醒内容删除
		portalMsgService.updatePortalMsg("data_social_newMedia", entity.getId()
				.toString());
		result.setResult(true);
		return result;
	}

	/**
	 * 新增时排他校验
	 * 
	 * @param entity
	 * @return
	 */
	private CommitResult saveCheck(DataSocialNewMedia entity, String type) {
		CommitResult result = new CommitResult();
		if (!socialNewMediaDao.saveSocialNewMediaRepeatCheck(entity, type)) {
			if (type.equals("report")) {
				result = CommitResult.createErrorResult("该数据已经被上报,不能修改!");
			} else {
				result = CommitResult.createErrorResult("该数据已存在,不能再添加!");
			}
			return result;
		}
		if (!socialNewMediaDao.checkCanEdit(entity)) {
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
		DataSocialNewMedia entity = socialNewMediaDao
				.getSocialNewMedia(entityID);
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

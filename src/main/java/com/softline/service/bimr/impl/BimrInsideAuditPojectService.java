package com.softline.service.bimr.impl;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.dao.bimr.IBimrInsideAuditProjectDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.AuditProject;
import com.softline.entity.AuditProjectFindQuestion;
import com.softline.entity.HhFile;
import com.softline.entity.bimr.BimrDuty;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditProjectAppendix;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditWeekly;
import com.softline.service.bimr.IBimrInsideAuditProjectService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("iBimrInsideAuditProjectService")
public class BimrInsideAuditPojectService implements
		IBimrInsideAuditProjectService {

	@Autowired
	@Qualifier("iBimrInsideAuditProjectDao")
	private IBimrInsideAuditProjectDao iBimrInsideAuditProjectDao;

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Autowired
	@Qualifier("commonService")
	private ICommonService commonService;

	private String month;

	/**
	 * 审计项目新增，用户可以看见被授权的项目与项目负责人为当前用户的项目 被授权项目以审计参与公司为准
	 * */
	@Override
	public MsgPage getBimrInsideAuditProjects(BimrInsideAuditProject entity,
			Integer curPageNum, Integer pageSize, String dataAuthority,
			String type, String vcEmployeeId) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = iBimrInsideAuditProjectDao
				.getBimrInsideAuditProjectListCount(entity, dataAuthority, "",
						vcEmployeeId);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrInsideAuditProject> list = iBimrInsideAuditProjectDao
				.getBimrInsideAuditProjectList(entity, offset, pageSize,
						dataAuthority, "", vcEmployeeId);
		for (BimrInsideAuditProject biap : list) {

		}
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}

	@Override
	public List<BimrInsideAuditProject> getBimrInsideAuditProjectList(
			BimrInsideAuditProject entity, Integer offset, Integer pageSize,
			String dataAuthority, String type) {
		List<BimrInsideAuditProject> returnList = new ArrayList<BimrInsideAuditProject>();
		// TODO
		List<BimrInsideAuditProject> list = iBimrInsideAuditProjectDao
				.getBimrInsideAuditProjectList(entity, 0, 10, dataAuthority,
						"", "");

		for (BimrInsideAuditProject item : list) {
			Integer propName = item.getAuditProjectProp();
			if (propName == 80026) {
				item.setAuditProjectPropName("风险导向审计");
			} else if (propName == 80027) {
				item.setAuditProjectPropName("风险管理审计");
			} else if (propName == 80028) {
				item.setAuditProjectPropName("内部控制评价");
			} else if (propName == 80029) {
				item.setAuditProjectPropName("经济责任审计");
			} else if (propName == 80030) {
				item.setAuditProjectPropName("企业巡视");
			} else {
				item.setAuditProjectPropName("调研管理");
			}

			Integer stauts = item.getStatus();
			if (stauts == 50223) {
				item.setStatusName("未启用");
			} else if (stauts == 50224) {
				item.setStatusName("已启用");
			}
			returnList.add(item);
		}
		return returnList;
	}

	@Override
	public Integer saveBimrInsideAuditProject(BimrInsideAuditProject entity) {
		// 新增时候保存项目信息
		iBimrInsideAuditProjectDao.saveBimrInsideAuditProject(entity);
		// 需要将参与审计的公司保存到附表，以便进行数据权限控制
		String[] attCompany = entity.getAuditImplDeptId().split(",");
		String projectId = entity.getId().toString();
		for (int i = 0; i < attCompany.length; i++) {
			BimrInsideAuditProjectAppendix append = new BimrInsideAuditProjectAppendix();
			append.setProjectId(projectId);
			append.setAuditImplDeptId(attCompany[i]);
			commonDao.saveOrUpdate(append);
		}
		return entity.getId();
	}

	@Override
	public void updateBimrInsideAuditProject(BimrInsideAuditProject entity) {
		iBimrInsideAuditProjectDao.updateBimrInsideAuditProject(entity);
		String[] attCompany = entity.getAuditImplDeptId().split(",");
		String projectId = entity.getId().toString();
		// 首先需要删除历史的参与审计公司信息
		iBimrInsideAuditProjectDao
				.deleteBimrInsideAuditProjectAppendix(projectId);
		for (int i = 0; i < attCompany.length; i++) {
			BimrInsideAuditProjectAppendix append = new BimrInsideAuditProjectAppendix();
			append.setProjectId(projectId);
			append.setAuditImplDeptId(attCompany[i]);
			commonDao.saveOrUpdate(append);
		}
	}

	@Override
	public void updateBimrProjectCode(BimrInsideAuditProject entity) {
		// 主项目生成项目编号
		if (entity.getIsChildren().toString().equals("0")) {
			String code = "";
			String organ = entity.getVcOrganID();
			Calendar c = Calendar.getInstance();
			String year = String.valueOf(c.get(Calendar.YEAR));
			/**
			 * 
			 * HNAHO-2017-001 海航实业集团有限公司:HNAHO 海南海航基础设施投资集团股份有限公司:HNAIN
			 * 供销大集集团股份有限公司:CCOOP 北京海航金融控股有限公司:HNAFG 海航教育医疗产业投资有限公司:HNAEH
			 * 
			 * 海航物流整体简称：HNALO，0-1-855579- 海航物流总部简称： HNALOHQ，0-1-855579-855580-
			 * 直属企业：HNALODU，0-1-855579-856081- 海航基础：HNAINF，0-1-855579-856150-
			 * 供销大集：CCOOP，0-1-855579-856151- 香港国际建投: HKICIM，0-1-855579-856152-
			 * 海航投资:HNAINV，0-1-855579-856153- CWT:CWT，0-1-855579-856154-
			 * 海越股份:HYGF，0-1-855579-856155- 航基股份: HNAINFRA，0-1-855579-856156-
			 * 中国顺客隆:SKL，0-1-855579-856157-
			 * 
			 * 
			 * */
			/*
			 * if(organ.contains("0-1-26655-847044-") ||
			 * organ.contains("0-1-26655-26658-")){ code = "HNAHO-"+year+"-"; }
			 * if(organ.contains("0-1-26655-27534-")){ code = "HNAIN-"+year+"-";
			 * } if(organ.contains("0-1-26655-4010-")){ code =
			 * "CCOOP-"+year+"-"; } if(organ.contains("0-1-26655-101351-")){
			 * code = "HNAFG-"+year+"-"; }
			 * if(organ.contains("0-1-26655-848600-")){ code =
			 * "HNAEH-"+year+"-"; }
			 */
			if (organ.contains("0-1-855579-855580-")) {
				code = "HNALOHQ-" + year + "-";
			} else if (organ.contains("0-1-855579-856081-")) {
				code = "HNALODU-" + year + "-";
			} else if (organ.contains("0-1-855579-856150-")) {
				code = "HNAINF-" + year + "-";
			} else if (organ.contains("0-1-855579-856151-")) {
				code = "CCOOP-" + year + "-";
			} else if (organ.contains("0-1-855579-856152-")) {
				code = "HKICIM-" + year + "-";
			} else if (organ.contains("0-1-855579-856153-")) {
				code = "HNAINV-" + year + "-";
			} else if (organ.contains("0-1-855579-856154-")) {
				code = "CWT-" + year + "-";
			} else if (organ.contains("0-1-855579-856155-")) {
				code = "HYGF-" + year + "-";
			} else if (organ.contains("0-1-855579-856156-")) {
				code = "HNAINFRA-" + year + "-";
			} else if (organ.contains("0-1-855579-856157-")) {
				code = "SKL-" + year + "-";
			} else {
				code = "HNALO-" + year + "-";
			}
			List list = iBimrInsideAuditProjectDao.getBimrProjectByCode(code,
					"0");
			if (list.size() > 0) {
				BimrInsideAuditProject lastEntity = (BimrInsideAuditProject) list
						.get(0);
				String lastCode = lastEntity.getAuditProjectCode();
				String n = lastCode.substring(lastCode.length() - 3,
						lastCode.length());
				int seq = Integer.parseInt(n);
				seq = seq + 1;
				if (seq < 10) {
					n = "00" + seq;
				} else if (seq >= 10 && seq < 100) {
					n = "0" + seq;
				} else {
					n = seq + "";
				}
				code += n;
				entity.setAuditProjectCode(code);
			} else {
				entity.setAuditProjectCode(code + "001");
			}
		}
		// 子项目生成项目编号
		else {
			BimrInsideAuditProject parentEntity = iBimrInsideAuditProjectDao
					.getBimrInsideAuditProjectById(entity.getAuditParentId());
			// 获取主项目信息
			String parentCode = parentEntity.getAuditProjectCode();
			List list = iBimrInsideAuditProjectDao.getBimrProjectByCode(
					parentCode, "1");
			if (list.size() > 0) {
				BimrInsideAuditProject lastEntity = (BimrInsideAuditProject) list
						.get(0);
				String lastCode = lastEntity.getAuditProjectCode();
				String n = lastCode.substring(lastCode.indexOf("S") + 1,
						lastCode.length());
				int seq = Integer.parseInt(n);
				seq = seq + 1;
				entity.setAuditProjectCode(parentCode + "-S" + seq);
			} else {
				entity.setAuditProjectCode(parentCode + "-S1");
			}
		}
		iBimrInsideAuditProjectDao.updateBimrInsideAuditProject(entity);
	}

	@Override
	public void deleteBimrInsideAuditProject(Integer id) {
		iBimrInsideAuditProjectDao.deleteBimrInsideAuditProject(id);
	}

	@Override
	public BimrInsideAuditProject getBimrInsideAuditProject(Integer id) {
		return iBimrInsideAuditProjectDao.getBimrInsideAuditProjectById(id);
	}

	@Override
	public BimrInsideAuditProject getBimrInsideAuditProject(
			BimrInsideAuditProject entity) {
		return iBimrInsideAuditProjectDao.getBimrInsideAuditProject(entity);
	}

	@Override
	public List<BimrInsideAuditProject> getBimrInsideAuditProjectHasNoChild(
			Integer status) {
		return iBimrInsideAuditProjectDao
				.getBimrInsideAuditProjectHasNoChild(status);
	}

	@Override
	public List<BimrInsideAuditProject> getBimrInsideAuditProjectsForList(
			Integer status) {
		return iBimrInsideAuditProjectDao
				.getBimrInsideAuditProjectsForList(status);
	}

	@Override
	public List<BimrInsideAuditProject> getBimrInsideAuditProjectForChildren(
			Integer id) {
		return iBimrInsideAuditProjectDao
				.getBimrInsideAuditProjectForChildren(id);
	}

	@Override
	public List<BimrInsideAuditProject> getBimrInsideAuditProjectsIsOrNotChild(
			Integer isChildProject) {
		return iBimrInsideAuditProjectDao
				.getBimrInsideAuditProjectsIsOrNotChild(isChildProject);
	}

	@Override
	public List<BimrInsideAuditProject> getBimrInsideAuditProjectsByParent(
			Integer parent_status) {
		return iBimrInsideAuditProjectDao
				.getBimrInsideAuditProjectsByParent(parent_status);
	}

	@Override
	public MsgPage getInsideAuditProjectWeeekReport(
			BimrInsideAuditProject entity, Integer curPageNum,
			Integer pageSize, String dataAuthority, String type,
			String vcEmployeeId) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = iBimrInsideAuditProjectDao
				.getInsideAuditProjectWeekReportListCount(entity,
						dataAuthority, type, vcEmployeeId);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrInsideAuditProject> list = iBimrInsideAuditProjectDao
				.getInsideAuditProjectWeekReportList(entity, offset, pageSize,
						dataAuthority, type, vcEmployeeId);
		for (BimrInsideAuditProject biap : list) {

		}
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}

	@Override
	public MsgPage getInsideAuditProjectQuestion(BimrInsideAuditProject entity,
			Integer curPageNum, Integer pageSize, String dataAuthority,
			String type, String vcEmployeeId) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = iBimrInsideAuditProjectDao
				.getInsideAuditProjectQuestionListCount(entity, dataAuthority,
						type, vcEmployeeId);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrInsideAuditProject> list = iBimrInsideAuditProjectDao
				.getInsideAuditProjectQuestionList(entity, offset, pageSize,
						dataAuthority, type, vcEmployeeId);
		for (BimrInsideAuditProject biap : list) {

		}
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}

	@Override
	public Integer saveCloseApply(BimrInsideAuditProject entity,
			MultipartFile[] sjFile, MultipartFile mailFile) {
		String docNum = entity.getDocNum();
		Integer status = entity.getStatus();
		entity = iBimrInsideAuditProjectDao
				.getBimrInsideAuditProjectById(entity.getId());

		if (sjFile != null && sjFile.length != 0) {
			/*
			 * if (entity.getSjFileId() != null) { String uuids =
			 * entity.getSjFileId(); String[] uuid=uuids.split(","); for (int i
			 * = 0; i < uuid.length; i++) {
			 * Common.deleteFile(DES.INSIDE_AUDIT_PROJECT, uuid[i]); HhFile file
			 * = commonDao.getFileByUuid(uuid[i]); if(null!=file)
			 * commonDao.delete(file); entity.setSjFileId(null);
			 * commonDao.saveOrUpdate(entity); } }
			 */
			for (int i = 0; i < sjFile.length; i++) {
				HhFile f_1 = commonService.saveFile(sjFile[i], entity.getId(),
						Base.INSIDE_AUDIT_PROJECT, DES.INSIDE_AUDIT_PROJECT);
				if (entity.getSjFileId() == null) {
					entity.setSjFileId(f_1.getFileUuid());
				} else {
					entity.setSjFileId(entity.getSjFileId() + ","
							+ f_1.getFileUuid());
				}
			}

		}
		if (mailFile != null && !mailFile.isEmpty()) {
			if (entity.getMailFileId() != null) {
				String uuid = entity.getMailFileId();
				Common.deleteFile(DES.INSIDE_AUDIT_PROJECT, uuid);
				HhFile file = commonDao.getFileByUuid(uuid);
				commonDao.delete(file);
			}
			HhFile f_2 = commonService.saveFile(mailFile, entity.getId(),
					Base.INSIDE_AUDIT_PROJECT, DES.INSIDE_AUDIT_PROJECT);
			entity.setMailFileId(f_2.getFileUuid());
		}
		entity.setDocNum(docNum);
		if (status != null) {
			entity.setStatus(status);
		}
		return iBimrInsideAuditProjectDao.saveBimrInsideAuditProject(entity);
	}

	// @Override
	// public XSSFWorkbook getInsideExportWorkbook(
	// List<BimrInsideAuditProject> list1) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public XSSFWorkbook getInsideExportWorkbook(
			List<BimrInsideAuditProject> list1) {

		XSSFWorkbook workBook = new XSSFWorkbook();
		CellStyle style = workBook.createCellStyle();
		// CellStyle style1=workBook.createCellStyle();
		XSSFFont font = workBook.createFont();
		// XSSFFont font1 = workBook.createFont();
		font.setBold(true);
		// 创建一个工作簿
		XSSFCell cell = null;
		int count = 0;
		// 设置单元格为空
		XSSFSheet sheet = workBook.createSheet("海航物流审计整改未完成项目统计");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));
		XSSFRow row = sheet.createRow((int) 0);
		cell = row.createCell((short) 0);
		font.setFontHeightInPoints((short) 15);
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		cell.setCellStyle(style);
		cell.setCellValue("海航物流审计整改未完成项目统计");
		empArch(list1, sheet, style, 1);

		return workBook;
	}

	// "判决/调解金额(人民币万元)","已执行款项(人民币万元)","避免/挽回经济损失(人民币万元)","案件编号","案件归属单位"
	public void empArch(List<BimrInsideAuditProject> list, XSSFSheet sheet,
			CellStyle style, int type) {
		XSSFRow row = sheet.createRow((int) 1);

		String[] header1 = { "序号", "审计项目", "项目所属核心单位", "发现问题数", "当期应完成整改措施数",
				"当期已完成整改措施数", "完成率" };

		XSSFCell cell = null;
		for (int i = 0; i < header1.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header1[i]);

		}
		/*
		 * style.setFillForegroundColor(IndexedColors.RED.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		 */
		// font1.setColor(IndexedColors.RED.index);
		// style.setFont(font1);

		// int i =0;
		// for(Object obj : list) {
		// Object[] item = (Object[])obj;
		//
		// int k=0;
		// row=sheet.createRow((int) i +2);
		// //
		// String auditImplDeptName = (String)item[0];
		// String projectName = (String)item[1];
		// // String projectName = (String)item[0];
		// int projectStatusProgress =Integer.parseInt(String.valueOf(item[2]));
		// String createDate = (String)item[3];
		// // int isImportant = Integer.parseInt(String.valueOf(item[2]));
		//
		//
		// row.createCell(k).setCellValue((i+1)+"");k++; //序号
		// // 111
		// row.createCell((short)k++).setCellValue(auditImplDeptName);
		// // 集团（审计实施单位)222
		//
		// row.createCell((short)k++).setCellValue(projectName);
		// // 项目名称333
		//
		//
		// // 4444
		// if (projectStatusProgress==81080) {
		// row.createCell((short)k++).setCellValue(String.valueOf("审计立项"));
		// }else if (projectStatusProgress==81081) {
		// row.createCell((short)k++).setCellValue(String.valueOf("发出审计通知"));
		// }else if (projectStatusProgress==81082) {
		// row.createCell((short)k++).setCellValue(String.valueOf("审前调查中"));
		// }else if (projectStatusProgress==81083) {
		// row.createCell((short)k++).setCellValue(String.valueOf("现场实施中"));
		// }else if (projectStatusProgress==81084) {
		// row.createCell((short)k++).setCellValue(String.valueOf("撰写审计报告中"));
		// }else if (projectStatusProgress==81085) {
		// row.createCell((short)k++).setCellValue(String.valueOf("审计报告初稿审阅中"));
		// }else if (projectStatusProgress==81086) {
		// row.createCell((short)k++).setCellValue(String.valueOf("审计报告征求意见中"));
		// }else if (projectStatusProgress==81087) {
		// row.createCell((short)k++).setCellValue(String.valueOf("审计报告公文流转中"));
		// }else if (projectStatusProgress==81088) {
		// row.createCell((short)k++).setCellValue(String.valueOf("审计报告公文审批结束"));
		// }else if (projectStatusProgress==81089) {
		// row.createCell((short)k++).setCellValue(String.valueOf("补充审计或审计延伸"));
		// }else if (projectStatusProgress==810810) {
		// row.createCell((short)k++).setCellValue(String.valueOf("整改方案制定中"));
		// }else if (projectStatusProgress==810811) {
		// row.createCell((short)k++).setCellValue(String.valueOf("整改落实中"));
		// }else if (projectStatusProgress==810812) {
		// row.createCell((short)k++).setCellValue(String.valueOf("整改完成"));
		// }
		//
		// // 项目状态
		// row.createCell((short)k++).setCellValue(createDate);
		// //时间555
		//
		//
		// i++;
		//
		//
		//
		// }

	}

	@Override
	public List<BimrInsideAuditProject> getInsideExport(
			BimrInsideAuditProject entity, String dataAuthority,
			String vcEmployeeId) {
		List<BimrInsideAuditProject> list = iBimrInsideAuditProjectDao
				.getInsideExport(entity, dataAuthority, vcEmployeeId);
		return list;
	}

	// null
	// 查询123
	@Override
	public List<Object[]> getInsideExportXM(BimrInsideAuditProject entity,
			BimrInsideAuditWeekly entity1, String dataAuthority,
			String vcEmployeeId) {
		List<Object[]> list = iBimrInsideAuditProjectDao.getInsideExportXMDC(
				entity, entity1, dataAuthority, vcEmployeeId);
		return list;
	}

	@Override
	public List<Object[]> getInsideExportXM1(BimrInsideAuditProject entity,
			BimrInsideAuditQuestion entity1, String dataAuthority,
			String vcEmployeeId) {
		List<Object[]> list = iBimrInsideAuditProjectDao.getInsideExport1(
				entity, entity1, dataAuthority, vcEmployeeId);
		return list;
	}

	// 审计项目台账导出
	@Override
	public XSSFWorkbook getInsideExportWorkbook1(List<Object[]> list1) {

		XSSFWorkbook workBook = new XSSFWorkbook();
		CellStyle style = workBook.createCellStyle();
		// CellStyle style1=workBook.createCellStyle();
		XSSFFont font = workBook.createFont();
		// XSSFFont font1 = workBook.createFont();
		font.setBold(true);
		// 创建一个工作簿
		XSSFCell cell = null;
		int count = 0;
		// 设置单元格为空
		XSSFSheet sheet = workBook.createSheet("审计项目台账");
		// sheet.addMergedRegion(new CellRangeAddress(0,0,0,20));
		XSSFRow row = sheet.createRow((int) 0);
		cell = row.createCell((short) 0);
		// font.setFontHeightInPoints((short) 15);
		// style.setFont(font);
		// style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
		// style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
		// cell.setCellStyle(style);
		// cell.setCellValue("审计1项目台账");
		empArch1(list1, sheet, style, 1, font);

		return workBook;
	}

	// "判决/调解金额(人民币万元)","已执行款项(人民币万元)","避免/挽回经济损失(人民币万元)","案件编号","案件归属单位"
	public void empArch1(List<Object[]> list, XSSFSheet sheet, CellStyle style,
			int type, XSSFFont font) {
		XSSFCell cell = null;
		XSSFRow row = sheet.createRow((int) 0);

		String[] header1 = { "序号", "产业集团", "审计项目名称", "状态", "新增时间" };
		font.setBold(true);
		style.setFont(font);

		for (int i = 0; i < header1.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header1[i]);
			cell.setCellStyle(style);
		}
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String s = sdf.format(new Date());
		// try {
		// Date date = sdf.parse(s);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		int i = 0;
		for (Object obj : list) {
			Object[] item = (Object[]) obj;

			int k = 0;
			row = sheet.createRow((int) i + 1);
			//
			String auditImplDeptName = (String) item[0];
			int projectStatusProgress = 0;
			// System.out.println(item[1]);
			if (null == item[1]) {
				projectStatusProgress = 0;
			} else {
				projectStatusProgress = Integer.parseInt(String
						.valueOf(item[1]));
			}

			String projectName = (String) item[2];

			String createDate = (String) item[3];

			row.createCell(k).setCellValue((i + 1) + "");
			k++; // 序号
			// 111
			row.createCell((short) k++).setCellValue(auditImplDeptName);
			// 集团（审计实施单位)222

			row.createCell((short) k++).setCellValue(projectName);
			// 项目名称333
			// 4444
			if (projectStatusProgress == 81080) {
				row.createCell((short) k++)
						.setCellValue(String.valueOf("审计立项"));
			} else if (projectStatusProgress == 81081) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("发出审计通知"));
			} else if (projectStatusProgress == 81082) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("审前调查中"));
			} else if (projectStatusProgress == 81083) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("现场实施中"));
			} else if (projectStatusProgress == 81084) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("撰写审计报告中"));
			} else if (projectStatusProgress == 81085) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("审计报告初稿审阅中"));
			} else if (projectStatusProgress == 81086) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("审计报告征求意见中"));
			} else if (projectStatusProgress == 81087) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("审计报告公文流转中"));
			} else if (projectStatusProgress == 81088) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("审计报告公文审批结束"));
			} else if (projectStatusProgress == 81089) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("补充审计或审计延伸"));
			} else if (projectStatusProgress == 810810) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("整改方案制定中"));
			} else if (projectStatusProgress == 810811) {
				row.createCell((short) k++).setCellValue(
						String.valueOf("整改落实中"));
			} else if (projectStatusProgress == 810812) {
				row.createCell((short) k++)
						.setCellValue(String.valueOf("整改完成"));
			} else {
				row.createCell((short) k++).setCellValue("");
			}

			// 项目状态
			row.createCell((short) k++).setCellValue(
					createDate.substring(0, 11));
			// 时间555

			i++;

		}

	}

	// 审计结果应用化指标反馈导出
	@Override
	public XSSFWorkbook getResultsExportWorkbook1(List<Object[]> list1) {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFCell cell = null;
		XSSFSheet sheet = workBook.createSheet("审计结果应用细化指标反馈");
		CellStyle style = workBook.createCellStyle();
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 29));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 12));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 13, 18));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 19, 20));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 24, 26));
		// sheet.addMergedRegion(new CellRangeAddress(1,2,30,30));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 27, 27));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 28, 28));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 29, 29));

		XSSFRow row = sheet.createRow((int) 0);
		XSSFFont font = workBook.createFont();
		font.setBold(true);
		cell = row.createCell((short) 0);
		// 第一列
		cell.setCellValue("审计结果应用细化指标反馈表");
		// 31
		font.setFontHeightInPoints((short) 15);
		// 字号
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		cell.setCellStyle(style);

		font.setFontHeightInPoints((short) 11);
		// 字号
		style.setFont(font);
		// row=sheet.createRow((int) 0);
		cell = row.createCell((short) 30);
		cell.setCellValue("公文编号");
		style.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(style);
		// row=sheet.createRow((int)1);
		// cell = row.createCell((short) 0);
		// cell.setCellValue("序号");
		// style.setAlignment(HorizontalAlignment.CENTER);
		// cell.setCellStyle(style);
		row = sheet.createRow((int) 1);
		cell = row.createCell((short) 3);
		cell.setCellValue("审计发现问题和风险类型数量");
		style.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(style);

		cell = row.createCell((short) 19);
		cell.setCellValue("违法和违规舞弊事项");
		style.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(style);

		cell = row.createCell((short) 13);
		cell.setCellValue("问题和风险原因分析总结类型数量");
		style.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(style);

		cell = row.createCell((short) 24);
		cell.setCellValue("被审计单位的改进提升情况");
		style.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(style);

		cell = row.createCell((short) 27);
		cell.setCellValue("审计验收及整改完成率");
		style.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(style);

		cell = row.createCell((short) 28);
		cell.setCellValue("对相关人员的绩效考核分数扣减情况");
		style.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(style);

		cell = row.createCell((short) 29);
		cell.setCellValue("备注");
		style.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(style);

		// 审计发现问题和风险类型数量
		// *****

		empArch11(list1, sheet, style, 1, font);
		return workBook;
	}
	// 审计结果应用化指标反馈导出
		@Override
		public XSSFWorkbook getResultsAppRefineExportWorkbook(BimrInsideAuditProject entity,String dataAuthority,String vcEmployeeId) {
			XSSFWorkbook workBook = new XSSFWorkbook();
			XSSFCell cell = null;
			XSSFSheet sheet = workBook.createSheet("审计结果应用细化指标反馈");
			CellStyle style = workBook.createCellStyle();
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 29));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 12));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 13, 18));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 19, 20));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 24, 26));
			// sheet.addMergedRegion(new CellRangeAddress(1,2,30,30));
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 27, 27));
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 28, 28));
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 29, 29));

			XSSFRow row = sheet.createRow((int) 0);
			XSSFFont font = workBook.createFont();
			font.setBold(true);
			cell = row.createCell((short) 0);
			// 第一列
			cell.setCellValue("审计结果应用细化指标反馈表");
			// 31
			font.setFontHeightInPoints((short) 15);
			// 字号
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
			cell.setCellStyle(style);

			font.setFontHeightInPoints((short) 11);
			// 字号
			style.setFont(font);
			// row=sheet.createRow((int) 0);
			cell = row.createCell((short) 30);
			cell.setCellValue("公文编号");
			style.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style);
			// row=sheet.createRow((int)1);
			// cell = row.createCell((short) 0);
			// cell.setCellValue("序号");
			// style.setAlignment(HorizontalAlignment.CENTER);
			// cell.setCellStyle(style);
			row = sheet.createRow((int) 1);
			cell = row.createCell((short) 3);
			cell.setCellValue("审计发现问题和风险类型数量");
			style.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style);

			cell = row.createCell((short) 19);
			cell.setCellValue("违法和违规舞弊事项");
			style.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style);

			cell = row.createCell((short) 13);
			cell.setCellValue("问题和风险原因分析总结类型数量");
			style.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style);

			cell = row.createCell((short) 24);
			cell.setCellValue("被审计单位的改进提升情况");
			style.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style);

			cell = row.createCell((short) 27);
			cell.setCellValue("审计验收及整改完成率");
			style.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style);

			cell = row.createCell((short) 28);
			cell.setCellValue("对相关人员的绩效考核分数扣减情况");
			style.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style);

			cell = row.createCell((short) 29);
			cell.setCellValue("备注");
			style.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style);

			// 审计发现问题和风险类型数量
			// *****
			List<BimrInsideAuditProject> list=iBimrInsideAuditProjectDao.getBimrInsideAuditProjectList(entity, null, null, dataAuthority, "", vcEmployeeId);
			if (list==null||list.size()==0) {
				return null;
			}
			auditResultsAppRefineExport(list, sheet, style, font);
			return workBook;
		}
	// 审计结果应用化指标反馈导出
	public void empArch11(List<Object[]> list, XSSFSheet sheet,
			CellStyle style, int type, XSSFFont font) {
		XSSFCell cell = null;
		XSSFRow row = sheet.createRow((int) 2);
		// cell=row.createCell((int)5);
		// "序号","细化指标/产业集团","审计项目名称",**
		//
		String[] header1 = { "序号", "细化指标" + "\n" + "产业集团", "审计项目名称",
				"公司战略制定和执行方面问题", "财务管控方面问题（资金资产安全等）", "人力资源管理方面的问题",
				"采购管理方面问题", "基础建设方面问题", "项目投资（重组并购）方面问题", "生产组织和销售方面问题",
				"行政事务管理问题", "外部环境和竞争问题", "其他", "规章制度缺失或不完善",
				"不相容岗位职责未分离，无监督牵制", "干部员工作风问题，职业道德和操守问题", "工作技能、业务能力和管理能力不足",
				"外部政策和环境影响", "其他", "涉案金额", "挽回损失", "审计处理和责任追究（人员和处分）",
				" 现场协助被审计单位解决的问题数量", "审计建议数量", "整改反馈（当月）", "完善制定下发的制度数",
				"审计后业绩和效益提升金额" };
		font.setBold(true);
		style.setWrapText(true);
		style.setFont(font);
		row.setRowStyle(style);
		for (int i = 0; i < header1.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header1[i]);
			cell.setCellStyle(style);
		}

		int i = 0;
		for (Object obj : list) {
			Object[] item = (Object[]) obj;

			int k = 0;
			row = sheet.createRow((int) i + 3);
			//
			String auditImplDeptName = (String) item[0];
			String projectName = (String) item[1];

			BigInteger auditSuggest = (BigInteger) item[2];
			BigDecimal completion = (BigDecimal) item[3];
//			17
			String docNum = (String) item[4];
			
			BigDecimal audit_question_types1 = (BigDecimal) item[5];
			BigDecimal audit_question_types2 = (BigDecimal) item[6];
			BigDecimal audit_question_types3 = (BigDecimal) item[7];
			BigDecimal audit_question_types4 = (BigDecimal) item[8];
			BigDecimal audit_question_types5 = (BigDecimal) item[9];
			BigDecimal audit_question_types6 = (BigDecimal) item[10];
			BigDecimal audit_question_types7 = (BigDecimal) item[11];
			BigDecimal audit_question_types8 = (BigDecimal) item[12];
			BigDecimal audit_question_types9 = (BigDecimal) item[13];
			BigDecimal audit_question_types10 = (BigDecimal) item[14];
			
			BigDecimal riskDriverType11 = (BigDecimal) item[15];
			BigDecimal riskDriverType12 = (BigDecimal) item[16];
			BigDecimal riskDriverType13 = (BigDecimal) item[17];
			BigDecimal riskDriverType14 = (BigDecimal) item[18];
			BigDecimal riskDriverType15 = (BigDecimal) item[19];
			BigDecimal riskDriverType16 = (BigDecimal) item[20];
			
			
			row.createCell(k).setCellValue((i + 1) + "");
			k++; // 序号
			if (item[0] != null) {
				row.createCell((short) k++).setCellValue(auditImplDeptName);
			} else {
				row.createCell((short) k++).setCellValue(auditImplDeptName);
			}
			if (item[1] != null) {
				row.createCell((short) k++).setCellValue(projectName);
			} else {
				row.createCell((short) k++).setCellValue("");
			}	
			
			row.createCell((short) k++).setCellValue(audit_question_types1.toString());
			row.createCell((short) k++).setCellValue(audit_question_types2.toString());
			row.createCell((short) k++).setCellValue(audit_question_types3.toString());
			row.createCell((short) k++).setCellValue(audit_question_types4.toString());
			row.createCell((short) k++).setCellValue(audit_question_types5.toString());
			row.createCell((short) k++).setCellValue(audit_question_types6.toString());
			row.createCell((short) k++).setCellValue(audit_question_types7.toString());
			row.createCell((short) k++).setCellValue(audit_question_types8.toString());
			row.createCell((short) k++).setCellValue(audit_question_types9.toString());
			row.createCell((short) k++).setCellValue(audit_question_types10.toString());
			
			row.createCell((short) k++).setCellValue(riskDriverType11.toString());
			row.createCell((short) k++).setCellValue(riskDriverType12.toString());
			row.createCell((short) k++).setCellValue(riskDriverType13.toString());
			row.createCell((short) k++).setCellValue(riskDriverType14.toString());
			row.createCell((short) k++).setCellValue(riskDriverType15.toString());
			row.createCell((short) k++).setCellValue(riskDriverType16.toString());
			

			
			
				row.createCell((short) k++).setCellValue("");
				// 涉案金额
		
			
				row.createCell((short) k++).setCellValue("");
		

		
				row.createCell((short) k++).setCellValue("");
	
			// 审计处理和责任追究
			
			row.createCell((short) k++).setCellValue("");
//			
			if (item[2] != null) {
				row.createCell((short) k++).setCellValue(auditSuggest.toString());
				// 审计建议数量
			} 
			
			row.createCell((short) k++).setCellValue("");
			// 整改反馈
			row.createCell((short) k++).setCellValue("");
			// 完善制定下发的制度数

			row.createCell((short) k++).setCellValue("");
			// 审计后业绩和效益提升金额
			if (item[3] != null) {
				row.createCell((short) k++).setCellValue(completion.toString());
				// 审计验收及整改完成率completion
			}
//
			row.createCell((short) k++).setCellValue("");
//绩效考核
			row.createCell((short) k++).setCellValue("");
//			备注
			if (item[4] != null) {
				row.createCell((short) k++).setCellValue(docNum);
			} else {
				row.createCell((short) k++).setCellValue("");
			}

			i++;

		}

	}
	
	
	// 审计结果应用化指标反馈导出
		public void auditResultsAppRefineExport(List<BimrInsideAuditProject> list, XSSFSheet sheet,
				CellStyle style, XSSFFont font) {
			XSSFCell cell = null;
			XSSFRow row = sheet.createRow((int) 2);
			// cell=row.createCell((int)5);
			// "序号","细化指标/产业集团","审计项目名称",**
			//
			String[] header1 = { "序号", "细化指标" + "\n" + "产业集团", "审计项目名称",
					"公司战略制定和执行方面问题", "财务管控方面问题（资金资产安全等）", "人力资源管理方面的问题",
					"采购管理方面问题", "基础建设方面问题", "项目投资（重组并购）方面问题", "生产组织和销售方面问题",
					"行政事务管理问题", "外部环境和竞争问题", "其他", "规章制度缺失或不完善",
					"不相容岗位职责未分离，无监督牵制", "干部员工作风问题，职业道德和操守问题", "工作技能、业务能力和管理能力不足",
					"外部政策和环境影响", "其他", "涉案金额", "挽回损失", "审计处理和责任追究（人员和处分）",
					" 现场协助被审计单位解决的问题数量", "审计建议数量", "整改反馈（当月）", "完善制定下发的制度数",
					"审计后业绩和效益提升金额" };
			font.setBold(true);
			style.setFont(font);

			for (int i = 0; i < header1.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(header1[i]);
				cell.setCellStyle(style);
			}

			for (int i = 0; i < list.size(); i++) {
				BimrInsideAuditProject entity=list.get(i);
				if (null==entity) {
					continue;
				}
				row = sheet.createRow((int) 3+i);
				//序号
				cell = row.createCell(0);
				cell.setCellValue(i+1);
				
				//产业集团
				cell = row.createCell(1);
				cell.setCellValue(entity.getAuditImplDeptName()==null?"":entity.getAuditImplDeptName());
				
				//审计项目名称
				cell = row.createCell(2);
				cell.setCellValue(entity.getAuditProjectName()==null?"":entity.getAuditProjectName());
				
				//问题类型
				List<Object[]> questionTypeList=iBimrInsideAuditProjectDao.getProblemTypeCountByID(entity.getId());
				
				Object[] obj=questionTypeList.get(0);
				//
				cell = row.createCell(3);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[0])));
				
				cell = row.createCell(4);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[1])));
				
				cell = row.createCell(5);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[2])));
				
				cell = row.createCell(6);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[3])));
				
				cell = row.createCell(7);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[4])));
				
				cell = row.createCell(8);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[5])));
				
				cell = row.createCell(9);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[6])));
				
				cell = row.createCell(10);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[7])));
				
				cell = row.createCell(11);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[8])));
				
				cell = row.createCell(12);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[9])));
				
				
				//问题和风险原因分析总结类型数量
                List<Object[]> RiskDriverTypeList=iBimrInsideAuditProjectDao.getRiskDriverTypesCountByID(entity.getId());
				
				 obj=RiskDriverTypeList.get(0);
				//
				cell = row.createCell(13);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[0])));
				
				cell = row.createCell(14);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[1])));
				
				cell = row.createCell(15);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[2])));
				
				cell = row.createCell(16);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[3])));
				
				cell = row.createCell(17);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[4])));
				
				cell = row.createCell(18);
				cell.setCellValue(Integer.parseInt(judgeNull(obj[5])));
				
				//涉案金额
				cell = row.createCell(19);
				cell.setCellValue("");
				
				//挽回损失
				cell = row.createCell(20);
				cell.setCellValue("");
				
				//审计处理和责任追究（人员和处分）
				cell = row.createCell(21);
				cell.setCellValue("");
				
				//现场协助被审计单位解决的问题数量
				cell = row.createCell(22);
				cell.setCellValue("");
				
				//审计建议数量
				cell = row.createCell(23);
				cell.setCellValue(iBimrInsideAuditProjectDao.getInsideSuggestCount(entity.getId()));
				
				//整改反馈（当月）
				List<Object> feedbacklist =iBimrInsideAuditProjectDao.getfeedbackDesc(entity.getId());
				 List<Object> PJlist=new ArrayList<Object>();
				for (int j = 0; j < feedbacklist.size(); j++) {
					PJlist.add(feedbacklist.get(j));
				}
				cell = row.createCell(24);
				cell.setCellValue(PJZF(PJlist));
				
				
				//完善制定下发的制度数
				cell = row.createCell(25);
				cell.setCellValue("");
				
				//审计后业绩和效益提升金额
				cell = row.createCell(26);
				cell.setCellValue("");
				
				//审计验收及整改完成率
				Double num=iBimrInsideAuditProjectDao.getInsideRectifySuccessCount(entity.getId());
				cell = row.createCell(27);
				cell.setCellValue(num==null?0:num);
				
				
				//对相关人员的绩效考核分数扣减情况
				cell = row.createCell(28);
				cell.setCellValue("");
				
				//备注
				cell = row.createCell(29);
				cell.setCellValue(entity.getRemark());
				
				//公文号
				cell = row.createCell(30);
				cell.setCellValue(entity.getDocNum());
			}
			

		}
		
		public String judgeNull(Object obj){
			return null==obj?"0":obj.toString();
		}
		//将字符串凭借成以逗号分隔
		
		public String PJZF(List<Object> list) {
			String returndata="";
			int i=0;
			if(list == null || list.size()<=0)
				returndata = "";
			for (Object object : list) {
				if (object==null) {
					continue;
				}
				i++;
				if(returndata.equals(""))
					returndata= (object==null? "": i+"."+String.valueOf(object));
				else
					returndata=returndata+""+i+"."+ (object==null? " ": String.valueOf(object))+" ";
			}
			
			return returndata;
		}
	// 导出111
	@Override
	public XSSFWorkbook getInsideExportWorkbook2(List<Object[]> list1) {

		XSSFWorkbook workBook = new XSSFWorkbook();
		CellStyle style = workBook.createCellStyle();
		// CellStyle style1=workBook.createCellStyle();
		XSSFFont font = workBook.createFont();
		// XSSFFont font1 = workBook.createFont();
		font.setBold(true);
		// 创建一个工作簿
		XSSFCell cell = null;
		int count = 0;
		// 设置单元格为空
		XSSFSheet sheet = workBook.createSheet("审计报告台账");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
		XSSFRow row = sheet.createRow((int) 0);
		cell = row.createCell((short) 0);
		font.setFontHeightInPoints((short) 15);
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		cell.setCellStyle(style);
		cell.setCellValue("审计报告台账");
		empArch2(list1, sheet, style, 1, font);

		return workBook;
	}

	// "判决/调解金额(人民币万元)","已执行款项(人民币万元)","避免/挽回经济损失(人民币万元)","案件编号","案件归属单位"
	public void empArch2(List<Object[]> list, XSSFSheet sheet, CellStyle style,
			int type, XSSFFont font) {

		XSSFCell cell = null;
		XSSFRow row = sheet.createRow((int) 1);

		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		// 审计类型 报告名称
		String[] header1 = { "序号", "所属产业集团", "单位名称", "审计类型", "报告名称",
				"审计项目开始日期", "审计现场结束日期", "项目负责人", "是否涉及舞弊事项", "是否呈报公文",
				"报告最终审批人", "报批公文号" };

		for (int i = 0; i < header1.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header1[i]);
			cell.setCellStyle(style);

		}

		int i = 0;

		for (Object obj : list) {
			Object[] item = (Object[]) obj;

			int k = 0;
			row = sheet.createRow((int) i + 2);

			// row.createCell(k).setCellValue((i+1)+"");k++; //序号
			String auditDeptedName = (String) item[0];
			String auditImplDept_name = (String) item[1];
			int auditProjectProp = Integer.parseInt(String.valueOf(item[2]));
			// 审计项目性质
			String auditProjectName = (String) item[3];
			String auditStartDate = (String) item[4];
			String auditEndDate = (String) item[5];

			String projectPrincipal = (String) item[6];

			int isSuspected = Integer.parseInt(String.valueOf(item[7]));
			// 是否涉及舞弊
			String examinePersonName = (String) item[8];
			String docNum = (String) item[9];

			// ******
			row.createCell(k).setCellValue((i + 1) + "");
			k++; // 序号

			row.createCell((short) k++).setCellValue(auditDeptedName);

			row.createCell((short) k++).setCellValue(auditImplDept_name);

			// row.createCell((short)k++).setCellValue(auditProjectProp);
			if (auditProjectProp == 80026) {
				row.createCell((short) k++).setCellValue("风险导向设计");
			} else if (auditProjectProp == 80027) {
				row.createCell((short) k++).setCellValue("风险管理设计");
			} else if (auditProjectProp == 80028) {
				row.createCell((short) k++).setCellValue("内部控制评价");
			} else if (auditProjectProp == 80029) {
				row.createCell((short) k++).setCellValue("经济责任审计");
			} else if (auditProjectProp == 80030) {
				row.createCell((short) k++).setCellValue("企业巡视");
			} else if (auditProjectProp == 80031) {
				row.createCell((short) k++).setCellValue("调研管理");
			}

			row.createCell((short) k++).setCellValue(auditProjectName);
			row.createCell((short) k++).setCellValue(auditStartDate);
			// 开始日期

			row.createCell((short) k++).setCellValue("");
			// 结束日期
			row.createCell((short) k++).setCellValue(projectPrincipal);
			// row.createCell((short)k++).setCellValue(isSuspected);
			if (isSuspected == 0) {
				row.createCell((short) k++).setCellValue("否");
			} else if (isSuspected == 1) {
				row.createCell((short) k++).setCellValue("是");
			}
			// if(null==item[2]){
			// projectStatusProgress=0;
			// }else {
			// projectStatusProgress =Integer.parseInt(String.valueOf(item[2]));
			// }

			if (Common.notEmpty(docNum)) {
				row.createCell((short) k++).setCellValue("是");
			} else {
				row.createCell((short) k++).setCellValue("否");
			}
			row.createCell((short) k++).setCellValue("");
			row.createCell((short) k++).setCellValue(docNum);

			i++;
		}

	}

	// 总监工作日报导出222

	@Override
	public XSSFWorkbook getInsideExportWorkbook3(
			List<BimrInsideAuditWeekly> list1) {

		XSSFWorkbook workBook = new XSSFWorkbook();
		CellStyle style = workBook.createCellStyle();
		// CellStyle style1=workBook.createCellStyle();
		XSSFFont font = workBook.createFont();
		// XSSFFont font1 = workBook.createFont();
		font.setBold(true);
		// 创建一个工作簿
		XSSFCell cell = null;
		int count = 0;
		// 设置单元格为空
		XSSFSheet sheet = workBook.createSheet("总监工作周报");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
		XSSFRow row = sheet.createRow((int) 0);
		cell = row.createCell((short) 0);
		font.setFontHeightInPoints((short) 15);
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		cell.setCellStyle(style);
		cell.setCellValue("审计体系");
		empArch3(list1, sheet, style, 1);

		return workBook;
	}

	// "判决/调解金额(人民币万元)","已执行款项(人民币万元)","避免/挽回经济损失(人民币万元)","案件编号","案件归属单位"
	public void empArch3(List<BimrInsideAuditWeekly> list, XSSFSheet sheet,
			CellStyle style, int type) {
		XSSFRow row = sheet.createRow((int) 1);

		String[] header1 = { "序号", "项目名称", "项目状态" };

		XSSFCell cell = null;
		for (int i = 0; i < header1.length; i++) {
			cell = row.createCell((short) i + 1);
			cell.setCellValue(header1[i]);

		}
		/*
		 * style.setFillForegroundColor(IndexedColors.RED.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		 */
		// font1.setColor(IndexedColors.RED.index);
		// style.setFont(font1);
		for (int i = 0; i < list.size(); i++) {
			int k = 0;
			row = sheet.createRow((int) i + 2);
			BimrInsideAuditWeekly value = list.get(i);
			// 任职单位personCompany任职职务personPost happenDate发生时间
			// reason问责原因 建议问责落实情况 proposal处分问责呈报公文编号bumfNum处分问责办文编号articleNum
			row.createCell(k).setCellValue((i + 1) + "");
			k++; // 序号
			row.createCell((short) k++).setCellValue(value.getProjectName());
			// 项目名称
			row.createCell((short) k++).setCellValue(
					value.getProjectStatusProgress());
			// 项目状态

			// if (value.getStatus().equals(50223)) {
			// row.createCell((short)k++).setCellValue("未启用");
			// }else if(value.getStatus().equals(50224)){
			// row.createCell((short)k++).setCellValue("启用");
			// }else if(value.getStatus().equals(50225)){
			// row.createCell((short)k++).setCellValue("在办中");
			// }else if(value.getStatus().equals(50226)){
			// row.createCell((short)k++).setCellValue("整改中");
			// }else if(value.getStatus().equals(50227)){
			// row.createCell((short)k++).setCellValue("办结申请中");
			// }else if(value.getStatus().equals(50228)){
			// row.createCell((short)k++).setCellValue("已退回");
			// }else if(value.getStatus().equals(50229)){
			// row.createCell((short)k++).setCellValue("已完成");
			// }else if(value.getStatus().equals(50230)){
			// row.createCell((short)k++).setCellValue("关闭审核");
			// }else if(value.getStatus().equals(50231)){
			// row.createCell((short)k++).setCellValue("已关闭");
			// }
			//
			// row.createCell((short)k++).setCellValue(value.getCreateDate());
			// // 时间

		}
	}

	@Override
	public List<Object[]> getResultsDC(BimrInsideAuditProject entity,
			BimrInsideAuditQuestion entity1, AuditProjectFindQuestion entity2,
			AuditProject entity3, String dataAuthority, String vcEmployeeId) {
		// TODO Auto-generated method stub
		List<Object[]> list = iBimrInsideAuditProjectDao.getResultsDC(entity,
				entity1, entity2, entity3, dataAuthority, vcEmployeeId);
		return list;

	}

}

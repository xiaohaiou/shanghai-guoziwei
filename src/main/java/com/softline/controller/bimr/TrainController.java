package com.softline.controller.bimr;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.SysExamine;
import com.softline.entity.bimr.BimrTrain;
import com.softline.service.bimr.ITrainService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 合规培训Controller
 * @author pengguolin
 */
@Controller
@RequestMapping("/bimr/train")
public class TrainController {
	@Resource(name = "trainService") private ITrainService trainService;
	@Resource(name = "systemService") private ISystemService systemService;
	@Resource(name = "baseService") private IBaseService baseService;
	@Resource(name = "examineService") private IExamineService examineService;
	@Resource(name = "commonService") private ICommonService commonService;
	@Resource(name = "selectUserService") private ISelectUserService selectUserService;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("/list")
	public String queryList(BimrTrain entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/bimr/train";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = trainService.getTrainList(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/bimr/train/list");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		return "/bimr/train/list";
	}
	
	@RequestMapping("/lookList")
	public String queryLookListt(BimrTrain entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/bimr/train";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = trainService.getTrainList(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/bimr/train/lookList");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		return "/bimr/train/lookList";
	}
	
	@RequestMapping("/export")
	public void queryLookListtexport(BimrTrain entity, HttpServletResponse response, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
       
        List<BimrTrain> list= trainService.getTrainListExport(entity, dataAuthority);
	  
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFCell cell = null;
        XSSFSheet sheet = workBook.createSheet("合规培训数据");
        XSSFRow row = sheet.createRow((int) 0);  
		 
        String[] header={"单位名称","被培训人姓名","培训名称","培训日期","授课人","是否内部授课人","授课人所在单位","授课人职位","授课方式","培训课时","培训反馈情况","培训地点","备注"};
		 
        for (int i = 0; i < header.length; i++) {
        	cell = row.createCell((short) i);
        	cell.setCellValue(header[i]);

        }
	   
        for (int i = 0; i < list.size(); i++) {  
		 	int k=0;
		    row = sheet.createRow((int) i + 1);
		    BimrTrain value = list.get(i);
		     
		    row.createCell((short)k++).setCellValue(value.getCompanyName());
		    row.createCell((short)k++).setCellValue(value.getBeTrainPerson());
		    row.createCell((short)k++).setCellValue(value.getTrainName());
		    row.createCell((short)k++).setCellValue(value.getTrainDate());
		    row.createCell((short)k++).setCellValue(value.getLecturer());
		    if(1==value.getIsInside()){
		    	row.createCell((short)k++).setCellValue("是");
		    }else if(0==value.getIsInside()){
		    	row.createCell((short)k++).setCellValue("否");
		    }else {
		    	row.createCell((short)k++).setCellValue("");
			}
		    row.createCell((short)k++).setCellValue(value.getLecturerCompany());
		    row.createCell((short)k++).setCellValue(value.getLecturerJob());
		    if(1==value.getLecturerMode()){
		    	row.createCell((short)k++).setCellValue("现场");
		    }else if (2==value.getLecturerMode()) {
		    	row.createCell((short)k++).setCellValue("现场+视频");
			}else if(3==value.getLecturerMode()){
				row.createCell((short)k++).setCellValue("视频");
			}else {
				row.createCell((short)k++).setCellValue("");
			}
		    row.createCell((short)k++).setCellValue(value.getLecturerHour());
		    if(1==value.getFeedback()){
		    	row.createCell((short)k++).setCellValue("满意");
		    }else if (2==value.getFeedback()) {
		    	row.createCell((short)k++).setCellValue("不满意");
			}else {
				row.createCell((short)k++).setCellValue("");
			}
		    if(1==value.getAddress()){
		    	row.createCell((short)k++).setCellValue("境内");
		    }else if (2==value.getAddress()) {
		    	row.createCell((short)k++).setCellValue("境外");
			}else {
				row.createCell((short)k++).setCellValue("");
			}
		    row.createCell((short)k++).setCellValue(value.getRemarks());
        }  
	 
        // 清空response
        response.reset();
        String _name = "合规培训数据.xlsx";
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        String codedfilename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// ie
			String name = java.net .URLEncoder.encode(_name, "UTF8");
			codedfilename = name;
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
			codedfilename = new String(_name.getBytes("UTF-8"),"iso-8859-1");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + codedfilename);
		workBook.write(toClient);
		toClient.flush();
		toClient.close(); 
	}
	
	@RequestMapping("/examineList")
	public String examineList(BimrTrain entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/bimr/train";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = trainService.getExamineTrainList(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/bimr/train/examineList");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getOtherBaseList("50112",Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		return "/bimr/train/examineList";
	}
	
	@RequestMapping("/addOrModify")
	public String addOrModify(BimrTrain entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
	    BimrTrain bimrTrain;
		if(entity.getId() != null) {
			bimrTrain = trainService.getBimrTrain(entity);
		}else {
			bimrTrain = new BimrTrain();
		}
		map.put("bimrTrain", bimrTrain);
		HhFile hfile1 = commonService.getFileByEnIdAndType(entity.getId(), Base.TRAIN_LECTURER_ENCLOSURE);
		if (hfile1 != null) {
			map.put("file_Path1", hfile1.getFilePath());
			map.put("file_Name1", hfile1.getFileName());
		} else {
			map.put("file_Path1", "");
			map.put("file_Name1", "");
		}
		HhFile hfile2 = commonService.getFileByEnIdAndType(entity.getId(), Base.TRAIN_LIST_OF);
		if (hfile2 != null) {
			map.put("file_Path2", hfile2.getFilePath());
			map.put("file_Name2", hfile2.getFileName());
		} else {
			map.put("file_Path2", "");
			map.put("file_Name2", "");
		}
		return "/bimr/train/addOrModify";
	}
	
	@RequestMapping("/view")
	public String view(BimrTrain entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		BimrTrain bimrTrain = trainService.getBimrTrain(entity);
		map.put("bimrTrain", bimrTrain);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.TRAIN_EXAMINE);
		map.put("examineList", examineList);
		HhFile hfile1 = commonService.getFileByEnIdAndType(entity.getId(), Base.TRAIN_LECTURER_ENCLOSURE);
		if (hfile1 != null) {
			map.put("file_Path1", hfile1.getFilePath());
			map.put("file_Name1", hfile1.getFileName());
		} else {
			map.put("file_Path1", "");
			map.put("file_Name1", "");
		}
		HhFile hfile2 = commonService.getFileByEnIdAndType(entity.getId(), Base.TRAIN_LIST_OF);
		if (hfile2 != null) {
			map.put("file_Path2", hfile2.getFilePath());
			map.put("file_Name2", hfile2.getFileName());
		} else {
			map.put("file_Path2", "");
			map.put("file_Name2", "");
		}
		return "/bimr/train/view";
	}
	
	@RequestMapping("/examine")
	public String examine(BimrTrain entity, Map<String, Object> map, HttpServletRequest request) {
		BimrTrain bimrTrain = trainService.getBimrTrain(entity);
		map.put("bimrTrain", bimrTrain);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.TRAIN_EXAMINE);
		map.put("examineList", examineList);
		HhFile hfile1 = commonService.getFileByEnIdAndType(entity.getId(), Base.TRAIN_LECTURER_ENCLOSURE);
		if (hfile1 != null) {
			map.put("file_Path1", hfile1.getFilePath());
			map.put("file_Name1", hfile1.getFileName());
		} else {
			map.put("file_Path1", "");
			map.put("file_Name1", "");
		}
		HhFile hfile2 = commonService.getFileByEnIdAndType(entity.getId(), Base.TRAIN_LIST_OF);
		if (hfile2 != null) {
			map.put("file_Path2", hfile2.getFilePath());
			map.put("file_Name2", hfile2.getFileName());
		} else {
			map.put("file_Path2", "");
			map.put("file_Name2", "");
		}
		return "/bimr/train/examine";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute("bimrTrain") BimrTrain entity, HttpServletRequest request,
			@RequestParam(value="file1",required=false) MultipartFile lecturerEnclosure,
			@RequestParam(value="file2",required=false) MultipartFile listOf) throws IOException, ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			String package_path = DES.TRAIN_PATH;
			trainService.save(entity, user, lecturerEnclosure, listOf, package_path);
		}else {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			String package_path = DES.TRAIN_PATH;
			trainService.update(entity, user, lecturerEnclosure, listOf, package_path);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndReported(@ModelAttribute("bimrTrain") BimrTrain entity, HttpServletRequest request,
			@RequestParam(value="file1",required=false) MultipartFile lecturerEnclosure,
			@RequestParam(value="file2",required=false) MultipartFile listOf) throws IOException, ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setStatus(Base.examstatus_2);
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(user.getVcEmployeeId());
			entity.setReportPersonName(user.getVcName());
			String package_path = DES.TRAIN_PATH;
			trainService.save(entity, user, lecturerEnclosure, listOf, package_path);
		}else {
			entity.setStatus(Base.examstatus_2);
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(user.getVcEmployeeId());
			entity.setReportPersonName(user.getVcName());
			String package_path = DES.TRAIN_PATH;
			trainService.update(entity, user, lecturerEnclosure, listOf, package_path);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/reported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reported(Integer id, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		BimrTrain bimrTrain = trainService.getBimrTrainById(id);
		bimrTrain.setStatus(Base.examstatus_2);
		Date date = new Date();
		bimrTrain.setReportDate(df.format(date));
		bimrTrain.setReportPersonId(user.getVcEmployeeId());
		bimrTrain.setReportPersonName(user.getVcName());
		trainService.update(bimrTrain);
		return "success";
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String deleteContract(String id, Map<String, Object> map, HttpServletRequest request) {
		String result = "";
		if (Common.notEmpty(id)) {
			BimrTrain bimrTrain = trainService.getBimrTrainById(Integer.parseInt(id));
			if (bimrTrain != null) {
				if (bimrTrain.getStatus() == 50112 || bimrTrain.getStatus() == 50114) {
					bimrTrain.setIsDel(1);
					trainService.update(bimrTrain);
					result = "success";
				} else {
					result = "false2";
				}
			} else {
				result = "false";
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/selectCompany", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void selectCompany(String id, PrintWriter print) throws IOException { 
		HhOrganInfo HhOrganInfo = systemService.getOrganInfoById(id);
		List<HhOrganInfo> companyList = systemService.getOrganInfos(HhOrganInfo.getVcOrganId());
		String jsonString = JSON.toJSONString(companyList);
		print.print(jsonString);
		print.flush();
		print.close();
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(String id, String op){
		String result = "";
		if (Common.notEmpty(id)) {
			BimrTrain entity = trainService.getBimrTrainById(Integer.parseInt(id));
			if(entity == null){
				result = "false";
			} else {
				if (Common.notEmpty(op)) {
					if ("check".equals(op)) {//查看判断
						result = "success";
					} else if ("examine".equals(op)) {//审核判断
						if (entity.getStatus() == 50113) {
							result = "success";
						} else {
							result = "false2";
						}
					} else {
						if (entity.getStatus() == 50112 || entity.getStatus() == 50114) {//修改,上报判断
							result = "success";
						} else {
							result = "false2";
						}
					}
				} else {
					result = "false";
				} 
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String commitExamineContract(Integer entityid, String examStr, Integer examResult, HttpServletRequest request) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entityid != null) {
			BimrTrain entity = trainService.getBimrTrainById(entityid);
			if (entity != null) {
				if (entity.getStatus() == 50113) {
					trainService.saveExamine(entity, examStr, examResult, user);
					result = "success";
				} else {
					result = "false2";
				}
			} else {
				result = "false";
			}
		} else {
			result = "false";
		}		
		return result;
	}
}

package com.softline.controller.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsGroup;
import com.softline.entity.ReportFormsOrganal;
import com.softline.entity.SysExamineReport;
import com.softline.entityTemp.CellMyStyle;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ReportHtmlView;
import com.softline.entityTemp.Report_Forms_DetailView;
import com.softline.entityTemp.TableCell;
import com.softline.entityTemp.TableComparator;
import com.softline.service.report.IReportService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IFinanceHistroyTreeService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.impl.CommonService;

@Controller
@RequestMapping("/reportcommon")
public class ReportCommonController {

	@Resource(name = "reportService")
	private IReportService reportService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "examineService")
	private IExamineService examineService;
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;	
	@Resource(name = "financeHistroyTreeService")
	private IFinanceHistroyTreeService financeHistroyTreeService;
	@RequestMapping("/newmodify")
	public String reportgroupNewModify(Integer groupID,Integer grouptype,String Date,String organalID,String op,int type,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		String a=request.getParameter("groupID");
		map.put("op", op);
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		if(!Common.notEmpty(Date))
		{
			Date now=new Date();
			Date=df.format(now);
		}
		if(organalID==null)
		{
			
			String str=(String) session.getAttribute("gzwsession_financecompany");
			organalID=str;
		}
		String year= Date.split("-")[0];
		
		if(op.equals("new"))
		{
			
			String str=(String) session.getAttribute("gzwsession_financecompany");
			map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );

		}
		else
		{
			map.put("allCompanyTree", "''");
			List<ReportForms> forms=reportService.getGroupForm( groupID, grouptype, Date,type);
			for(ReportForms foo : forms){
				ReportFormsOrganal orgal=	reportService.getFormOrganal(foo.getId(),organalID,Date);
				/*HhOrganInfo orgal=systemService.getOrganInfoById(organal);*/
				if(orgal!=null)
				{
					map.put("checkedCompanyName", orgal.getOrganName());
					map.put("organalID", orgal.getOrganalId());
					break;
				}	
			}
		}	
		map.put("groupid", groupID);
		map.put("grouptype", grouptype);
		map.put("type", type);
		
		map.put("Date", Date);
		return "/report/reportcommon/reportDataNewModify";
	}
	
	
	/**
	 * 变动条件时切换组显示的报表
	 * @param groupid
	 * @param grouptype
	 * @param Date
	 * @param url
	 * @param request
	 * @param map
	 * @param op
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/changegroup", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String changegroup(String organalID,Integer groupid,Integer grouptype,String Date,int type,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		ReportFormsOrganal orgentity=new ReportFormsOrganal();
		orgentity.setReportTime(Date);
		orgentity.setGroupID(groupid);
		orgentity.setOrganalId(organalID);
		List<ReportFormsOrganal> orgforms=reportService.getReportFormsOrganal(orgentity);
		List<ReportForms> forms=reportService.getGroupForm( groupid, grouptype, Date,type);
		if(forms != null && orgforms != null){
			for(ReportForms form : forms){
				form.setIsUpload(0);
				for(ReportFormsOrganal orgal : orgforms){
					if(form.getId().equals(orgal.getFormsId())){
						form.setIsUpload(1);
						break;
					}
				}
			}
		}
		map.put("formslist", forms);
		String result="";
		if(forms!=null && forms.size()>0)
		{
			result=JSONArray.toJSONString(forms); 	
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value ="/report", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String report(Integer groupID,String organal,String Date,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		//上报
		CommitResult result= reportService.saveReportFormReport(Date,use, organal, groupID);
		if(result.isResult())
		{
			ReportFormsGroup entity=new ReportFormsGroup();
			entity.setId(groupID);
			ReportFormsGroup group= reportService.getReportFormsGroup(entity);
			String business="";
			String title=group.getName()+group.getTypeName()+"数据需要审核";
			switch(group.getNameID())
			{
			  case Base.reportgroup_BudgetEnforcement://预算
				  if(group.getType().equals(Base.reportgroup_type_simple))//单体
				  {	
					  business="bimWork_yszxbbsh_exam";
					  
				  }
				  else if(group.getType().equals(Base.reportgroup_type_collect))//汇总
				  {	  
					  business="bimWork_yszxbbhzsh_exam";
					 
				  }
				  break;
			  case Base.reportgroup_adjust://核算
				  business="bimWork_hsbbsh_exam";
				  break;
			}
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			potalMsgService.savePortalMsg(new PortalMsg(title,"财务",df.format(new Date()),0,0,0,
					use.getVcName(),df.format(new Date()),use.getVcName(),
					df.format(new Date()),organal,systemService.getParentBynNodeID(organal, Base.financetype),business,"report_forms_organal",organal+"_"+groupID+"_"+Date));
		}
		
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/examine", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String examine(Integer examineresult,String examineStr,Integer groupID,String organal,String Date,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		//审核
		CommitResult result= reportService.saveReportFormExamine(Date, use, organal, groupID, examineresult, examineStr);
		if(result.isResult()){
			potalMsgService.updatePortalMsg("report_forms_organal", organal+"_"+groupID+"_"+Date);			
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 审核时未通过，返回上层级数据
	 * @param examineresult
	 * @param examineStr
	 * @param groupID
	 * @param organal
	 * @param Date
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/examinedErrorData")
	public String examinedErrorData(Integer examineresult,String examineStr,Integer groupID,String organal,String Date,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		//审核
		CommitResult result = reportService.setBackExamineStatus(Date,organal, groupID, examineresult);
		List<ReportFormsOrganal> list= (List<ReportFormsOrganal>)result.getEntity();
		map.put("examStr", examineStr);	
		map.put("groupID", groupID);	
		map.put("date", Date);	
		map.put("checkedCompanyid", organal);	
		map.put("list", list);	
		map.put("flag", result.isResult());
		return "/report/reportcommon/reportDataErrorShow";
	}

	
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportgroupsave(int type,String Date,  String  organalID,Integer grouptype,Integer[] fileformsID, @RequestParam("file") CommonsMultipartFile files[],HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		
		CommitResult result= reportService.saveReportValue(Date,use, organalID, grouptype,fileformsID, files,type);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String delete(String Date,  String  organalID,Integer groupid,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		
		CommitResult result= reportService.deleteReportValue(Date,use, organalID, groupid);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("report_forms_organal", organalID+"_"+groupid+"_"+Date);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/collect", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String collect(String Date,  String  organalID,Integer grouptype,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		
		CommitResult result= reportService.savecollectReportValue(Date,use, organalID, grouptype);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	

	@RequestMapping(value ="/show")
	public String show( int type, Integer groupID,String organal,String Date,String op ,Integer grouptype,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		map.put("date", Date);
		map.put("year", Date.split("-")[0]);
		map.put("month", Date.split("-")[1]);
		ReportForms entity=new ReportForms();
		entity.setGroupId(groupID);
		map.put("groupID", groupID);
		List<ReportForms> forms=reportService.getGroupForm( groupID, grouptype, Date,type);
		//List<ReportForms> forms=reportService.getReportformsList(entity);
		ReportFormsOrganal orgentity=new ReportFormsOrganal();
		orgentity.setReportTime(Date);
		orgentity.setGroupID(groupID);
		orgentity.setOrganalId(organal);
		List<ReportFormsOrganal> orgforms=reportService.getReportFormsOrganal(orgentity);
		map.put("formsList", forms);
		String u_name="";
		String status="";
		if(orgforms.size()>0){
			u_name=orgforms.get(0).getOrganName();	
			status=String.valueOf(orgforms.get(0).getIsexamine());				
		}
		map.put("checkedCompanyName",u_name);
		map.put("checkedCompanyid", organal);
		//--------------------------------------------返回报表种类(预算或者核算)-----------------
		ReportFormsGroup simplegroup=new ReportFormsGroup();
		simplegroup.setId(groupID);
		ReportFormsGroup examgroup= reportService.getReportFormsGroup(simplegroup);
		map.put("formType", examgroup.getNameID());
		//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^返回报表种类(预算或者核算)^^^^^^^^^^^^^^^^^
		map.put("status", status);
		
		if(op.equals("view") || op.equals("report"))
		{
			List<SysExamineReport> examinlist=new ArrayList<SysExamineReport>();
			examinlist= examineService.getListReportExamine(groupID, Date, organal);
			map.put("entityExamineviewlist", examinlist);
		}
		map.put("op", op);
		return "/report/reportcommon/reportDataShow";
	}
	
	@ResponseBody
	@RequestMapping(value ="/showform", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String showform(Integer formid,String organalID,String Date,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
	    ReportFormsOrganal organ=	reportService.getFormOrganal(formid,organalID,Date);
	    if(organ!=null)
	    {	
	    	Report_Forms_DetailView view=   reportService.getReportDetailViewByID(organ.getId());
			if(view!=null)
			{
				String data=createReportTable(view)	;
				ReportHtmlView html=new ReportHtmlView();
				html.setTablestr(data);
				html.setCreatePersonName(view.getCreatePersonName());
				html.setCreateDate(view.getCreateDate());
				html.setReportDate(view.getReportDate());
				html.setReportPersonName(view.getReportPersonName());
				
				return JSON.toJSONString(html);
			}
	    }
		return "";
	}
	
	///end 生成hr树
	@SuppressWarnings("unchecked")
	private String createReportTable(Report_Forms_DetailView view)
	{
		Collections.sort(view.getCell(), new TableComparator());
		StringBuilder tablesb=new StringBuilder();
		int row=0;
		int col=0;
		tablesb.append("<table>");
		int allnum= view.getCell().size();
		for (int i = 0; i <allnum; i++) {
			TableCell thiscell=view.getCell().get(i);
			//行首检查
			if(i-1>=0)
			{
				TableCell procell=view.getCell().get(i-1);
				if(procell.getStartrow()<(thiscell.getStartrow()))
					tablesb.append("<tr>");
			}
			else
				tablesb.append("<tr>");
			String a=thiscell.getCellcss().trim();
			
			CellMyStyle cellstyle=new CellMyStyle(a);
			tablesb.append("<td ");
			if(thiscell.getIsdata())
				tablesb.append(" isdata ");
			if(thiscell.getIsunit())
				tablesb.append(" isunit ");
			if(thiscell.getUnit()!=null)
				tablesb.append(" unit=\""+thiscell.getUnit()+"\" ");
			tablesb.append(" colspan=\""+(thiscell.getColspan())+"\" rowspan=\""+(thiscell.getRowspan())+"\" "+createStyle(cellstyle)+">");
			if("null".equals(thiscell.getValue())){
				tablesb.append(" ");
			}else{
				if(Integer.valueOf(100).equals(thiscell.getUnit()) && Common.notEmpty(thiscell.getValue())){
					tablesb.append(thiscell.getValue()+"%");
				}else{
					tablesb.append(thiscell.getValue());
				}
			}
			
			tablesb.append("</td>");
			//行尾检查
			if(i+1<allnum)
			{
				TableCell nextcell=view.getCell().get(i+1);
				if(nextcell.getStartrow()>(thiscell.getStartrow()))
					tablesb.append("</tr>");
			}
			else
				tablesb.append("</tr>");
				
		}
		tablesb.append("</table>");
		String bb= tablesb.toString();
		return bb.toString();
	}

	private String createStyle(CellMyStyle style)
	{
		if(style==null)
			return "style=\"\"";
		StringBuilder tablesb=new StringBuilder();
		if(Common.notEmpty(style.getAlignment()))
		{
			tablesb.append(" text-align:"+style.getAlignment()+";");
		}
		if(Common.notEmpty(style.getForegroundColor()) && !style.getForegroundColor().equals("#000000"))
		{
			tablesb.append(" background-color:"+style.getForegroundColor()+";");
		}
		if(Common.notEmpty(style.getFontName()))
		{
			tablesb.append(" font-family:"+style.getFontName()+";");
		}
		if(Common.notEmpty(style.getFontName()))
		{
			tablesb.append(" font-weight:"+style.getBoldweight()+";");
		}
		tablesb.append(" Border:1px solid #000000;");
		String a=tablesb.toString();
		if(!Common.notEmpty(a))
			return "style=\"\"";
		else
			return "style=\""+a+"\"";
	}
	
	@RequestMapping(value="/downloadtemplet")
	public void download(HttpServletResponse response,String _url, String _name,
			HttpServletRequest request) throws IOException {
		if(Common.notEmpty(_url) && Common.notEmpty(_name))
		{	String nodepath = this.getClass().getClassLoader().getResource("/")
					.getPath();
			// 项目的根目录路径
			String filePath = File.separator
					+ nodepath.substring(1, nodepath.length() - 16);
			String path =filePath+StringEscapeUtils.escapeHtml(_url) ;
			File file = new File(path);
		    BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        // 清空response
	        response.reset();
	
	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	        String codedfilename = null;
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
					&& -1 != agent.indexOf("Trident")) {// ie

				String name = java.net.URLEncoder.encode(_name, "UTF8");

				codedfilename = name;
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

				codedfilename = new String(_name.getBytes("UTF-8"),
						"iso-8859-1");
			}
			
	        
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment;filename=" + codedfilename);
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close(); 
		}
	 }

	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(String Date,  String  organalID,Integer groupID,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		
		List<ReportFormsOrganal>  data=reportService.getReportFormsOrganalData(Date, organalID, groupID);
		
		if(data==null || data.size()==0)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}
	
	@RequestMapping(value="/exportReportData")
	public void exportReportData( int type, Integer groupID,String organal,String Date,Integer grouptype,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("exportSession", 0);
		try {
			//创建工作簿  
			XSSFWorkbook workBook = reportService.getExportExcel(type, Date, organal, groupID, grouptype);
			HhOrganInfoTreeRelation thisorgan=systemService.getTreeOrganInfoNode(Base.financetype, organal);
			// 清空response
			response.reset();
			HhBase typeBase = baseService.getBase(type);
			HhBase reprotTypeBase =  baseService.getBase(grouptype);
			String _name = thisorgan.getVcFullName() + Date + reprotTypeBase.getDescription() + typeBase.getDescription() + ".xlsx";
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			String codedfilename = null;
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
					&& -1 != agent.indexOf("Trident")) {// ie
				
				String name = java.net.URLEncoder.encode(_name, "UTF8");
				
				codedfilename = name;
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
				
				codedfilename = new String(_name.getBytes("UTF-8"),
						"iso-8859-1");
			}
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + codedfilename);
			workBook.write(toClient);
			toClient.flush();
			toClient.close(); 
			session.setAttribute("exportSession", 1);
		} catch (Exception e) {
			session.setAttribute("exportSession", 2);
		}
	 }
	
	@ResponseBody
	@RequestMapping(value ="/getExportSession", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getExportSession(HttpServletRequest request ,Map<String, Object> map){
		HttpSession session=request.getSession();
		Integer ExportFlag=(Integer) session.getAttribute("exportSession");
		return ExportFlag + "";
	
	}
	
	@ResponseBody
	@RequestMapping(value ="/getFinanceTreeByTime", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String getFinanceTreeByTime(String time,HttpServletRequest request ,Map<String, Object> map)  {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		List<CompanyTree> tree = financeHistroyTreeService.getOtherOrganal(str,Base.financetype,time);
		return JSON.toJSONString(tree);
	}
	
	@ResponseBody
	@RequestMapping(value ="/isVirtualCompany", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String isVirtualCompany(String organalId,HttpServletRequest request) throws IOException {
		String backInfo = "none";
		boolean flag = false;
		
		try{			
			flag = reportService.isVirtualCompany(organalId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(flag)
			backInfo = "success";
		
		return JSONArray.toJSONString(backInfo);
	}
}

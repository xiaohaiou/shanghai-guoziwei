package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.ReportFormsOrganal;
import com.softline.entity.ReportOutBenchmark;
import com.softline.entity.ReportOutCompany;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportOutBenchmarkService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;



@Controller
@RequestMapping("/reportOutBenchmark")
/**
 * 
 * @author yxk
 *
 */
public class ReportOutBenchmarkController {
	
	
	@Resource(name = "reportOutBenchmarkService")
	private IReportOutBenchmarkService reportOutBenchmarkService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportOutBenchmark entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportOutBenchmark";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=reportOutBenchmarkService.getReportOutBenchmarkListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportOutBenchmark/list");
	    map.put("entityview", entity);
		return "/report/reportOutBenchmark/reportOutBenchmarkList";
	}
	
	
	
	//跳转维护页面
	@RequestMapping("/maintain")
	public String _maintain(int id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		ReportOutBenchmark entityview=reportOutBenchmarkService.getReportOutBenchmar(id);
		map.put("entityview", entityview);		
		return "/report/reportOutBenchmark/reportOutBenchmarkMaintain";
	}
	
	
	
	//保存外部对标维护页面
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String ReportOutBenchmarkSave(ReportOutBenchmark entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		
 		CommitResult result= reportOutBenchmarkService.saveReportOutBenchmark(entity);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}

	
	
	//查询外部对标公司数据
	@RequestMapping("/dataList")
	public String _datalist(ReportOutCompany entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			String mapurl=request.getContextPath()+ "/reportOutBenchmark";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
	        Integer pageNum = Integer.valueOf(curPageNum);
	        List<ReportOutBenchmark> list = new ArrayList<ReportOutBenchmark>();
			list=reportOutBenchmarkService.getReportOutBenchmarkListView(str);
			List list1 = new ArrayList();
	         if(null != list){
	        	 for (int i = 0; i < list.size(); i++) {
	        		ReportOutBenchmark entitys = new ReportOutBenchmark();
	        		 if(null!=list.get(i).getOrg()&& !list.get(i).getOrg().isEmpty()){
	        			 entitys.setFinanceorg(list.get(i).getOrg());
	            		 entitys.setFinanceorgname(list.get(i).getOrgname()); 
	            		 entitys.setFinanceorgIsHongkong(list.get(i).getOrgIsHongkong());
	            		 list1.add(entitys); 
	        		 }  		  		 
	     		}
	        	 for (int i = 0; i < list.size(); i++) {
	        		ReportOutBenchmark entitys = new ReportOutBenchmark();
	        		 entitys.setFinanceorg(list.get(i).getFinanceorg());
	        		 entitys.setFinanceorgname(list.get(i).getFinanceorgname());
	        		 entitys.setFinanceorgIsHongkong(list.get(i).getFinanceorgIsHongkong());
	        		 list1.add(entitys);  		 
	     		}
	        }
	         map.put("list1", list1);
		    MsgPage msgPage=reportOutBenchmarkService.getReportOutCompanyListView(entity,pageNum,str,Base.pagesize);
		    map.put("msgPage", msgPage);
//		    map.put("searchurl", "/shanghai-gzw/reportFinancingAccount/list");
		    map.put("entityview", entity);
			return "/report/reportOutBenchmark/reportOutBenchmarkData";
		}
	
	
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportOutCompany entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		List<ReportOutBenchmark> list = reportOutBenchmarkService.getReportOutBenchmarkListView(str);
		List<ReportOutBenchmark> list1 = new ArrayList<ReportOutBenchmark>();
         if(null != list){
        	 for (int i = 0; i < list.size(); i++) {
        		ReportOutBenchmark entitys = new ReportOutBenchmark();
        		 if(null!=list.get(i).getOrg()&& !list.get(i).getOrg().isEmpty()){
        			 entitys.setFinanceorg(list.get(i).getOrg());
            		 entitys.setFinanceorgname(list.get(i).getOrgname()); 
            		 entitys.setFinanceorgIsHongkong(list.get(i).getOrgIsHongkong());
            		 list1.add(entitys); 
        		 }  		  		 
     		}
        	 for (int i = 0; i < list.size(); i++) {
        		ReportOutBenchmark entitys = new ReportOutBenchmark();
        		 entitys.setFinanceorg(list.get(i).getFinanceorg());
        		 entitys.setFinanceorgname(list.get(i).getFinanceorgname());
        		 entitys.setFinanceorgIsHongkong(list.get(i).getFinanceorgIsHongkong());
        		 list1.add(entitys);  		 
     		}
        }
        map.put("list1", list1);
		ReportOutCompany entityview = null;
		if(entity.getId()==null)
		{	
			entityview=new ReportOutCompany();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
		}
		else
		{	
     		entityview=reportOutBenchmarkService.getReportOutCompany(entity.getId());
//			List<SysExamine> a=new ArrayList<SysExamine>();
//			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportoutcompany);
//			map.put("entityExamineviewlist", a);
		}
		List<HhBase> seasontype= baseService.getBases(Base.seasontype);
		map.put("seasontype", seasontype);
		map.put("entityview", entityview);	
		return "/report/reportOutBenchmark/reportOutCompanyNewModify";
	}
	
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/saveReportOutCompany", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportOutCompany reportOutCompany,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		String[] a = reportOutCompany.getOrgname().split(",");
		if(reportOutCompany==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			if(null != reportOutCompany.getId()){
				ReportOutCompany reportOutCompanyold=reportOutBenchmarkService.getReportOutCompany(reportOutCompany.getId());
				reportOutCompany.setCreateDate(reportOutCompanyold.getCreateDate());
				reportOutCompany.setCreatePersonId(reportOutCompanyold.getCreatePersonId());
				reportOutCompany.setCreatePersonName(reportOutCompanyold.getCreatePersonName());
			}
			
			reportOutCompany.setOrgname(a[0]);
			reportOutCompany.setOrg(a[1]);
			reportOutCompany.setStatus(Base.examstatus_1);
			
			result= reportOutBenchmarkService.saveReportOutCompany(reportOutCompany, use);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	
	//跳转到查看页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportOutCompany entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportOutCompany();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportOutBenchmarkService.getReportOutCompany(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportoutcompany);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportOutBenchmark/reportOutCompanyView";
	}
	
	
	//查询列表页面的上报
	@RequestMapping(value ="/postexam")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		ReportOutCompany entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportOutCompany();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportOutBenchmarkService.getReportOutCompany(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportoutcompany);
		    map.put("entityExamineviewlist", a);
		}	
		List<HhBase> reportOutCompanyType = baseService.getBases(Base.report_out_company_type);
		map.put("reportOutCompanyType", reportOutCompanyType);
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportOutBenchmark/reportOutCompanyView";
	}
		
	//编辑新增页面的上报
		@ResponseBody
		@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public String _saveandreport(ReportOutCompany entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
			CommitResult result=new CommitResult();

			if(entity!=null)
			{
				HttpSession session=request.getSession();
				HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(null!=entity.getId()){
				  	entity=reportOutBenchmarkService.getReportOutCompany(entity.getId());
				}	
				if(("report").equals(op)){
					entity.setStatus(Base.examstatus_2);					
				}else{
					String[] a = entity.getOrgname().split(",");
					if(a.length!=1){
						entity.setOrg(a[1]);
					}
					entity.setOrgname(a[0]);
					entity.setStatus(Base.examstatus_2);			
				}
				entity.setReportDate(df.format(new Date()));
				entity.setReportPersonId(use.getVcEmployeeId());
				entity.setReportPersonName(use.getVcName());
				result= reportOutBenchmarkService.saveReportOutCompany(entity, use);
				if(result.isResult())
					potalMsgService.savePortalMsg(new PortalMsg("外部对标数据数据需要审核","财务",df.format(new Date()),0,0,0,
							use.getVcName(),df.format(new Date()),use.getVcName(),
							df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_rzxzsjsh_exam","report_out_company",entity.getId().toString()));
			}
			else
			{
				result=CommitResult.createErrorResult("该数据已被删除");
			}
			String data="";
			data=JSONArray.toJSONString(result); 			
			return data;
		}
		
	
	
	//删除
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportgroupdelete(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=new CommitResult();
        result=reportOutBenchmarkService.deleteReportOutCompany(id, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_out_company", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportOutCompany entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportOutBenchmark";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
//		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
      
        entity.setParentorg(str);
        MsgPage msgPage=reportOutBenchmarkService.getReportOutCompanyListView(entity,pageNum,str,Base.pagesize);
        List<ReportOutBenchmark> list = new ArrayList<ReportOutBenchmark>();
		list=reportOutBenchmarkService.getReportOutBenchmarkListView(str);
		List list1 = new ArrayList();
         if(null != list){
        	 for (int i = 0; i < list.size(); i++) {
        		ReportOutBenchmark entitys = new ReportOutBenchmark();
        		 if(null!=list.get(i).getOrg()&& !list.get(i).getOrg().isEmpty()){
        			 entitys.setFinanceorg(list.get(i).getOrg());
            		 entitys.setFinanceorgname(list.get(i).getOrgname()); 
            		 entitys.setFinanceorgIsHongkong(list.get(i).getOrgIsHongkong());
            		 list1.add(entitys); 
        		 }  		  		 
     		}
        	 for (int i = 0; i < list.size(); i++) {
        		ReportOutBenchmark entitys = new ReportOutBenchmark();
        		 entitys.setFinanceorg(list.get(i).getFinanceorg());
        		 entitys.setFinanceorgname(list.get(i).getFinanceorgname());
        		 entitys.setFinanceorgIsHongkong(list.get(i).getFinanceorgIsHongkong());
        		 list1.add(entitys);  		 
     		}
        }
         map.put("list1", list1);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportOutBenchmark/examinelist");
	    map.put("entityview", entity);
	    //数据权限
        String dataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);

        map.put("dataauth", dataauth);
		return "/report/reportOutBenchmark/reportOutBenchmarkExamineList";
	}
		
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportOutCompany entityview;
		if(id==null)
		{
			entityview=new ReportOutCompany();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
			entityview=reportOutBenchmarkService.getReportOutCompany(id);
		Integer status=0;
		status=entityview.getStatus();
		map.put("status",status);
		map.put("entityview", entityview);	
		return "/report/reportOutBenchmark/reportOutBenchmarkExamine";
	}
	
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportOutBenchmarkService.saveReportOutCompanyExamine(entityid, examStr, examResult, use);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("report_out_company", entityid.toString());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
}

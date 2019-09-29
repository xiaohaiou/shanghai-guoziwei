package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyLevelTree;
import com.softline.common.CompanyTree;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsGroup;
import com.softline.entity.ReportFormsOrganal;
import com.softline.entity.TaxSaveGroup;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ReportQueryView;
import com.softline.entityTemp.Report_forms_groupView;
import com.softline.service.report.IReportService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IFinanceHistroyTreeService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportbudgetenforcementcollect")
public class ReportBudgetEnforcementCollectController {

	@Resource(name = "reportService")
	private IReportService reportService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "financeHistroyTreeService")
	private IFinanceHistroyTreeService financeHistroyTreeService;
	/*@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			Purchase entityview=reportService.getPurchase(id);
			map.put("purchase", entityview);
		}
	}
	*/
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportQueryView entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportbudgetenforcementcollect";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer grouptype=Base.reportgroup_BudgetEnforcement;
        Integer type=Base.reportgroup_type_collect;
        Integer pageNum = Integer.valueOf(curPageNum);
        map.put("grouptype", grouptype);
        map.put("type", type);
        HttpSession session=request.getSession();
  		String str=(String) session.getAttribute("gzwsession_financecompany");
  		//获取上月年、月
		Calendar calendar = Calendar.getInstance();
		String time=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    	time = format.format(calendar.getTime());
    	if(Common.notEmpty(entity.getFinanceTime())){
    		/*map.put("financeTime", entity.getFinanceTime());
        	int res = entity.getFinanceTime().compareTo(time);
        	if(res==0){
        		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        	}else{*/
        		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherHistoryOrganal(str,Base.financetype,entity.getFinanceTime())) );
        /*	}*/
    	}else{
      		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
    	}
        entity.setGetOperateType(Base.fun_examine);
        entity.setGroupType(grouptype);
        //数据权限
        /*if(!Common.notEmpty(entity.getOrganalID()) )
        	entity.setOrganalID(systemService.getOtherOrganAuthData(str, Base.financetype));*/
        entity.setType(type);
        entity.setParentorg(str);
        MsgPage msgPage=reportService.getReportFormsOrganalList(pageNum, Base.pagesize, entity,"50501");//"50501" 实体公司
       

	    map.put("msgPage", msgPage);
	    addData(map);
	    map.put("searchurl", "/shanghai-gzw/reportbudgetenforcementcollect/examinelist");
	    map.put("changeFinaneUrl", "/shanghai-gzw/reportbudgetenforcementcollect/examinelist");
	    map.put("entityview", entity);
		return "/report/budgetenforcement/budgetenforcementCollectExamineList";
	}
	
	@RequestMapping("/list")
	public String list(ReportQueryView entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportbudgetenforcementcollect";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer grouptype=Base.reportgroup_BudgetEnforcement;
        Integer type=Base.reportgroup_type_collect;
        HttpSession session=request.getSession();
  		String str=(String) session.getAttribute("gzwsession_financecompany");
  		//获取上月年、月
		Calendar calendar = Calendar.getInstance();
		String time=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    	time = format.format(calendar.getTime());
    	if(Common.notEmpty(entity.getFinanceTime())){
    		/*map.put("financeTime", entity.getFinanceTime());
        	int res = entity.getFinanceTime().compareTo(time);
        	if(res==0){
        		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        	}else{*/
        		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherHistoryOrganal(str,Base.financetype,entity.getFinanceTime())) );
       /* 	}*/
    	}else{
      		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
    	}
        map.put("grouptype", grouptype);
        map.put("type", type);
        entity.setParentorg(str);
        Integer pageNum = Integer.valueOf(curPageNum);
        entity.setGroupType(grouptype);
        //数据权限
       /* if(!Common.notEmpty(entity.getOrganalID()) )
        	entity.setOrganalID(systemService.getOtherOrganAuthData(str, Base.financetype));*/
        entity.setType(type);
        MsgPage msgPage=reportService.getReportFormsOrganalList(pageNum, Base.pagesize, entity,"50501"); //"50501" 实体公司
       
	    map.put("msgPage", msgPage);
	    addData(map);
	    map.put("searchurl", "/shanghai-gzw/reportbudgetenforcementcollect/list");
	    map.put("changeFinaneUrl", "/shanghai-gzw/reportbudgetenforcementcollect/list");
	    map.put("entityview", entity);
		return "/report/budgetenforcement/budgetenforcementCollectList";
	}
	
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		List<HhBase> reportformkind= baseService.getBases(Base.reportformkind);
		List<HhBase> reportformfre= baseService.getBases(Base.reportformfre);
		map.put("examstatustype",examstatustype);
		map.put("reportformkind",reportformkind);
		map.put("reportformfre", reportformfre);
	}

	@RequestMapping("/newmodify")
	public String reportgroupNewModify(Integer groupID,Integer grouptype,String Date,String organalID,String op,HttpServletRequest request ,Map<String, Object> map) throws IOException {
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
		if(op.equals("new"))
		{
			
			String str=(String) session.getAttribute("gzwsession_financecompany");
			List<CompanyTree> tree=financeHistroyTreeService.getOtherOrganal(str,Base.financetype,Date);
			map.put("allCompanyTree", JSON.toJSONString(tree) );
			if(tree!=null && tree.size()>0)
			{
				if(tree.get(0) != null){
					map.put("checkedCompanyName", tree.get(0).getName());
					map.put("organalID", tree.get(0).getId());
				}else{
					map.put("checkedCompanyName", "");
					map.put("organalID", "");
				}
			}

		}
		else
		{
			map.put("allCompanyTree", "''");
			List<ReportForms> forms=reportService.getGroupForm( groupID, grouptype, Date,Base.reportgroup_type_simple);
			ReportFormsOrganal orgal=	reportService.getFormOrganal(forms.get(0).getId(),organalID,Date);
			if(orgal!=null)
			{
				map.put("checkedCompanyName", orgal.getOrganName());
				map.put("organalID", orgal.getOrganalId());
			}	
		}	
		map.put("groupid", groupID);
		map.put("grouptype", grouptype);
		map.put("Date", Date);
		return "/report/budgetenforcement/budgetenforcementCollect";
	}
	
	
	/**
	 * 进入子页面分页
	 * @param pjId
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/_itemViewList")
	public String _nodeViewList(ReportQueryView entity,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer grouptype=Base.reportgroup_BudgetEnforcement;
        Integer pageNum = Integer.valueOf(curPageNum);
		//分页		
        entity.setGetOperateType(Base.fun_search);
        entity.setGroupType(grouptype);
        entity.setOrganalID(entity.getOrganalID());
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        entity.setParentorg(str);
        entity.setType(Base.reportgroup_type_collect);
        MsgPage msgPage=reportService.getReportFormsOrganalCollectList(pageNum, Base.pagesize, entity);
        map.put("msgPage", msgPage);		
		return "/report/budgetenforcement/ItemList";
	}
	
	
}

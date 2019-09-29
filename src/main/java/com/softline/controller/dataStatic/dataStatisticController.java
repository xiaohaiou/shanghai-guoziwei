package com.softline.controller.dataStatic;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.DataHrManagerialStaff;
import com.softline.entity.DataSixRateKp;
import com.softline.entity.DataSocialBrand;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.LaborcostResourcesreWards;
import com.softline.service.hr.ILaborCostService;
import com.softline.service.hr.IManagerialStaffService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.sixrate.ISixRateKpService;
import com.softline.service.social.ISocialBrandService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;



@Controller
@RequestMapping("/dataStatistic")
/**
 * 
 * @author zy
 *
 */
public class dataStatisticController {

	@Resource(name = "socialBrandService")
	private ISocialBrandService socialBrandService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;
	@Resource(name = "laborCostService")
	private ILaborCostService laborCostService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "commonService")
	private ICommonService commonService;
	@Resource(name = "managerialStaffService")
	private IManagerialStaffService managerialStaffService;
	@Resource(name = "sixRateKpService")
	private ISixRateKpService sixRateKpService;
	
	
	@RequestMapping("/dshexaminelist")
	public String dsh_examinelist(LaborcostResourcesreWards entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(dataauth);
        }
        
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		String mapurl=request.getContextPath()+ "/laborCost";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=laborCostService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/laborCost/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/dataStatistic/dshlist";
	}
	/**
	 * 审核的列表页面
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/zcexaminelist")
	public String zc_examinelist(DataHrManagerialStaff entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);            
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(dataauth);
        }
        
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		String mapurl=request.getContextPath()+ "/managerialStaff";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=managerialStaffService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/managerialStaff/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/dataStatistic/zclist";
	}
	/**
	 * 审核的列表页面
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/xcexaminelist")
	public String xc_examinelist(DataSixRateKp entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
	    if(!Common.notEmpty(entity.getOrganalId()))
	    	entity.setOrganalId(dataauth);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		String mapurl=request.getContextPath()+ "/sixRateKp";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=sixRateKpService.findExaminePageList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/sixRateKp/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/dataStatistic/xclist";
	}
	
	/**
	 * 下拉框信息追加
	 * 
	 */
	private void addData(Map<String, Object> map) {
		// 审核状态
		List<HhBase> examstatustype = baseService.getBases(Base.examstatustype);
		map.put("examStatus", examstatustype);
		// 品牌类型
		List<HhBase> brandType = baseService.getBases(Base.brandType);
		map.put("brandType", brandType);
		// 品牌位阶
		List<HhBase> brandLevel = baseService.getBases(Base.brandLevel);
		map.put("brandLevel", brandLevel);
		// 品牌属性
		List<HhBase> brandProperty = baseService.getBases(Base.brandProperty);
		map.put("brandProperty", brandProperty);
	}
	
}

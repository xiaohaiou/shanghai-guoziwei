package com.softline.controller.queryPage.hr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.HhBase;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.LaborcostResourcesreWards;
import com.softline.service.hr.ILaborCostService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/laborCost")
public class QueryPageLaborCostController {

	@Resource(name = "laborCostService")
	private ILaborCostService laborCostService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	/**
	 * 查询条件-结果
	 * @param entity
	 * @param request
	 * @param map
	 * @param time
	 * @return
	 */
	@RequestMapping("/list")
	public String hrlist(LaborcostResourcesreWards entity,HttpServletRequest request ,Map<String, Object> map,String time) {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
	    if(!Common.notEmpty(entity.getOrganalId()))
	    	entity.setOrganalId(dataauth);
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		
		String mapurl=request.getContextPath()+ "/queryPage/laborCost";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=laborCostService.findPageList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/queryPage/laborCost/list");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
	    addData(map);
		return "/queryPage/hr/laborCost/laborCostFormList";
	}
	
	/**
	 * 获取LaborcostResourcesreWards
	 * @param id
	 * @return
	 */
	@RequestMapping("/get")
	@ResponseBody
	public LaborcostResourcesreWards get(String id) {
		LaborcostResourcesreWards dh=laborCostService.get(Integer.valueOf(id));
		return dh;
	}
	
	/**
	 * 添加信息
	 * @param map
	 */
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		map.put("examstatustype",examstatustype);
	}
	/**
	 * 跳转新增修改页面
	 */
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map, String op) throws IOException {
		LaborcostResourcesreWards entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new LaborcostResourcesreWards();
		}
		else
		{
			entity=laborCostService.get(id);
		    a= examineService.getListExamine(entity.getId(), Base.examkind_hr_laborCost);
		}	
		map.put("entity", entity);
		map.put("entityExamineview", a);
		map.put("op", op);
		return "/queryPage/hr/laborCost/laborCostView";
	}
	
}

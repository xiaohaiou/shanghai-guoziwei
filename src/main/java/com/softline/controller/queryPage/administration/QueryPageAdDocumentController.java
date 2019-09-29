package com.softline.controller.queryPage.administration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.AdDocument;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.administration.IAdDocumentService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/adDocument")
public class QueryPageAdDocumentController {
	
	@Resource(name = "adDocumentService")
	private IAdDocumentService	adDocumentService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@RequestMapping("query")
	public String queryDocumentList(AdDocument entity, String organalID, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        if(!Common.notEmpty(entity.getCompId()))
        	entity.setCompId(dataauth);
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adDocumentService.getDocumentView(entity,pageNum,Base.pagesize,Base.fun_search);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/queryPage/adDocument/query");
	    map.put("entity", entity);
	    addData(map);
	    if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/queryPage/administration/documentList";
	}
	
	@RequestMapping("view")
	public String viewDocument(AdDocument entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		AdDocument theEntity=adDocumentService.getDocument(entity);
		map.put("theEntity", theEntity);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_document);
		map.put("examineList", examineList);
		return "/queryPage/administration/documentView";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> reportStatus= baseService.getBases(Base.examstatustype);
		map.put("reportStatus",reportStatus);
	}
	
	/*@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteDocument(Integer id, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=adDocumentService.deleteDocument(id, use);
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}*/
}

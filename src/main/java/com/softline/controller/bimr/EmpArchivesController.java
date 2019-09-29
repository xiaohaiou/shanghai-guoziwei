package com.softline.controller.bimr;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.drools.compiler.lang.DRL5Expressions.neg_operator_key_return;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrDuty;
import com.softline.service.bimr.IEmpArchivesService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/bimr/empArchives")
public class EmpArchivesController {
	@Resource(name = "systemService") 
	private ISystemService systemService;
	@Resource(name = "empArchivesService")
	private IEmpArchivesService empArchivesService;

	@Resource(name = "commonService")
	private ICommonService commonService;

	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//干部员工合规档案查询
	@RequestMapping(value = "/archivesList")
	public String archivesList(BimrDuty entity, Map<String, Object> map, HttpServletRequest request) {
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = empArchivesService.getEmpAchivesList(entity, pageNum, Base.pagesize, dataAuthority);
		map.put("msgPage", msgPage);
		String mapurl = request.getContextPath()+ "/bimr/empArchives";
		map.put("mapurl", mapurl);
	    map.put("searchurl", "/shanghai-gzw/bimr/empArchives/archivesList");
	    map.put("entity", entity);
		return "/bimr/riskArchives/empArchivesList";
	}
	
	@RequestMapping(value = "/archivesView")
	public String archivesView(BimrDuty entity,int id,String personId, Map<String, Object> map, HttpServletRequest request) {
		
		List<BimrDuty> dutylist = empArchivesService.getByPersonId(personId);
		entity.setId(id);
		BimrDuty duty=empArchivesService.getBimrDuty(entity);
		map.put("duty", duty);
		List<BimrDuty> riskList=new ArrayList<BimrDuty>();
		List<BimrDuty> caseList=new ArrayList<BimrDuty>();
		List<BimrDuty> insidepList=new ArrayList<BimrDuty>();
		List<BimrDuty> complianceList=new ArrayList<BimrDuty>();
		for (BimrDuty bimrDuty : dutylist) {
			if (1==bimrDuty.getSource()) {
				riskList.add(bimrDuty);
			}else if(2==bimrDuty.getSource()){
				caseList.add(bimrDuty);
			}else if (4==bimrDuty.getSource()) {
				insidepList.add(bimrDuty);
			}else if(5==bimrDuty.getSource()){
				complianceList.add(bimrDuty);
			}
		}
	    map.put("riskList", riskList);
	    map.put("caseList", caseList);
	    map.put("insidepList", insidepList);
	    map.put("complianceList", complianceList);
		return "/bimr/riskArchives/empArchivesDetail";
	}
	
	
	
	//导出
	
			@RequestMapping(value="/empArchivesExport")
			public void empArchivesExport1(BimrDuty entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) throws IOException, ParsePropertyException, InvalidFormatException {
				/*HttpSession session = request.getSession();
				String str=(String) session.getAttribute("gzwsession_company");
				String dataAuthority = systemService.getDataauth(str);
		        Integer pageNum = Integer.valueOf(curPageNum);*/
				 String curPageNum = request.getParameter("pageNums");
			        if (curPageNum == null) {
			        	curPageNum = "1";
			        }
				HttpSession session = request.getSession();
		        String str = (String)session.getAttribute("gzwsession_company");
		        //获取数据权限
		       
		        String dataAuthority = systemService.getDataauth(str);
		        Integer pageNum = Integer.valueOf(curPageNum);
		       /* if(!Common.notEmpty(entity.getVcCompanyId())){
		        	entity.setDataauth(dataauth);
		        }*/
		       
				
				List<BimrDuty> list1=new ArrayList<BimrDuty>();

			    list1=empArchivesService.getempArchivesCaseExport(entity, dataAuthority);
					
			        
			        XSSFWorkbook workBook = new XSSFWorkbook();
//					
			        workBook=empArchivesService.getempArchivesExportWorkbook(list1);
						     
						     // 清空response档案查询
						        response.reset();
						        String _name = "干部员工合规档案查询.xlsx";
						        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
						        String codedfilename = null;
								String agent = request.getHeader("USER-AGENT");
								if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
										&& -1 != agent.indexOf("Trident")) {// ie

									String name = java.net .URLEncoder.encode(_name, "UTF8");

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
					}
}

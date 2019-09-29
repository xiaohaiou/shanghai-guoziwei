package com.softline.controller.report;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.ExcelTool;
import com.softline.entity.HhBase;
import com.softline.entity.ReportMonthlyFinancingOutDetail;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportMonthlyFinancingOutDetail")
/**
 * 
 * @author tch
 *
 */
public class ReportMonthlyFinancingOutDetailController {

	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	//维护的列表页面
	@RequestMapping(value ="/list", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _list(String stringList, Integer pageNums, String op, HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("companyTrees", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		
		List<ReportMonthlyFinancingOutDetail> list = new ArrayList<ReportMonthlyFinancingOutDetail>();
		if(stringList != null && !"".equals(stringList))
			list=JSON.parseArray(stringList, ReportMonthlyFinancingOutDetail.class);
        //根据list，设置分页
		MsgPage msgPage=new MsgPage();
    	msgPage.setPageSize(5);
    	msgPage.setTotalRecords(list.size());
    	msgPage.setTotalPage(msgPage.countTotalPage(list.size(), 5));
		if(op.equals("add")) {
			pageNums = msgPage.getTotalPage();
		}
		if(op.equals("del")) {
			pageNums = 1;
		}
		List<ReportMonthlyFinancingOutDetail> newList = new ArrayList<ReportMonthlyFinancingOutDetail>();
    	if(pageNums < msgPage.getTotalPage()) {
    		int size = (pageNums - 1)*5+5;
    		for(int i = (pageNums - 1)*5; i < size; i++) {
    			if(null!=list && list.size() !=0)
    				newList.add(list.get(i));
			}
    	}else {
    		for(int i = (pageNums - 1)*5; i < list.size(); i++) {
    			if(null!=list && list.size() !=0)
    				newList.add(list.get(i));
    		}
    	}
    	msgPage.setPageNum(pageNums);
    	msgPage.setList(newList);
    	map.put("sizeNum", newList.size());
	    map.put("msgPage", msgPage);
	    //控制分页
	    map.put("flag", "reportMonthlyFinancingOutDetail");
	    map.put("searchurl", "/shanghai-gzw/reportMonthlyFinancingOutDetail/list");
		addData(map);
		return "/report/reportCashflowMonthlyExecute/reportMonthlyFinancingOutDetail/reportMonthlyFinancingOutDetailList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/viewList")
	public String _view(String stringList, Integer pageNums,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<ReportMonthlyFinancingOutDetail> list = new ArrayList<ReportMonthlyFinancingOutDetail>();
		if(stringList != null && !"".equals(stringList))
			list=JSON.parseArray(stringList, ReportMonthlyFinancingOutDetail.class);
        //根据list，设置分页
		MsgPage msgPage=new MsgPage();
    	msgPage.setPageSize(5);
    	msgPage.setTotalRecords(list.size());
    	msgPage.setTotalPage(msgPage.countTotalPage(list.size(), 5));
		List<ReportMonthlyFinancingOutDetail> newList = new ArrayList<ReportMonthlyFinancingOutDetail>();
    	if(pageNums < msgPage.getTotalPage()) {
    		int size = (pageNums - 1)*5+5;
    		for(int i = (pageNums - 1)*5; i < size; i++) {
    			
    				newList.add(list.get(i));
			}
    	}else {
    		for(int i = (pageNums - 1)*5; i < list.size(); i++) {
    			
    				newList.add(list.get(i));
    		}
    	}
    	msgPage.setPageNum(pageNums);
    	msgPage.setList(newList);
    	map.put("sizeNum", newList.size());
	    map.put("msgPage", msgPage);
	    //控制分页
	    map.put("flag", "reportMonthlyFinancingOutDetail");
	    map.put("searchurl", "/shanghai-gzw/reportMonthlyFinancingOutDetail/viewList");
		return "/report/reportCashflowMonthlyExecute/reportMonthlyFinancingOutDetail/reportMonthlyFinancingOutDetailViewList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> financingType= baseService.getBases(Base.financingType);
		List<HhBase> financingTypeDetail= baseService.getBases(Base.financingTypeDetail);
		List<HhBase> currencyKind= baseService.getBases(Base.currencyKind);
		
		map.put("financingType",financingType);
		map.put("financingTypeDetail",financingTypeDetail);
		map.put("currencyKind",currencyKind);
		
	}
	
	//excel导出
	@ResponseBody
	@RequestMapping("/exportExcel")
	public String exportExcel(String excelName, String excelData, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = excelName; 
		List<ReportMonthlyFinancingOutDetail> dataList = new ArrayList<ReportMonthlyFinancingOutDetail>();
		if(excelData != null && !"".equals(excelData))
			dataList=JSON.parseArray(excelData, ReportMonthlyFinancingOutDetail.class);
	    List<Map<String,Object>> list =createExcelRecord(dataList);
	    //列名
	    String columnNames[]={"序号","承贷主体","还贷银行","融资类型","融资类型明细","还款日期","还款金额(万元)","币种"};
	    //Map中的key
	    String keys[]={"num","loanCompName","repayBank","financingTypeName","financingTypeDetailName","repayDate","repayAmount","currencyName"};
	    //存放excel下拉值
	    List<String[]> selectList = createSelect();
	    try  
	    {  
	    	ServletOutputStream sos = response.getOutputStream();   
	    	response.setContentType("application/binary;charset=utf-8");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(),"iso-8859-1") + ".xls");// 组装附件名称和格式  
            ExcelTool tool = new ExcelTool();
    	    tool.createWorkBook(list,keys,columnNames,sos,selectList);
        }  
        catch (IOException e)  
        {  
          e.printStackTrace();  
        }  
      return null;  
	}
	
	private List<Map<String, Object>> createExcelRecord(List<ReportMonthlyFinancingOutDetail> dataList) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "筹资性流出明细列表");
        listmap.add(map);
    	int j = dataList.size();
    	for (int i = 0; i < 500; i++) {//默认给500条
    		 if(i<j) {
	        	ReportMonthlyFinancingOutDetail detail=dataList.get(i);
	            Map<String, Object> mapValue = new HashMap<String, Object>();
	            mapValue.put("num", i+1);
	            mapValue.put("loanCompName", detail.getLoanCompName());
	            mapValue.put("repayBank", detail.getRepayBank());
	            mapValue.put("financingTypeName", systemService.getDescription(detail.getFinancingType()).toString());
	            mapValue.put("financingTypeDetailName", systemService.getDescription(detail.getFinancingTypeDetail()).toString());
	            mapValue.put("repayDate", detail.getRepayDate());
	            mapValue.put("repayAmount", detail.getRepayAmount());
	            mapValue.put("currencyName", systemService.getDescription(detail.getCurrency()).toString());
	            listmap.add(mapValue);
    		 }else {
    			Map<String, Object> mapValue = new HashMap<String, Object>();
	            mapValue.put("num", "");
	            mapValue.put("loanCompName", "");
	            mapValue.put("repayBank", "");
	            mapValue.put("financingTypeName", "");
	            mapValue.put("financingTypeDetailName", "");
	            mapValue.put("repayDate", "");
	            mapValue.put("repayAmount", "");
	            mapValue.put("currencyName", "");
	            listmap.add(mapValue);
    		 }
    	}
        return listmap;
    }
	
	private List<String[]> createSelect(){
		List<String[]> selectList = new ArrayList<String[]>();
		//下拉1
		List<HhBase> financingType= baseService.getBases(Base.financingType);
	    String[] arr1 = new String[financingType.size()];
	    int i = 0;
	    for(HhBase base:financingType) {
	    	arr1[i]=base.getDescription();
	    	i++;
	    }
	    //下拉2
	    List<HhBase> financingTypeDetail= baseService.getBases(Base.financingTypeDetail);
		String[] arr2 = new String[financingTypeDetail.size()];
	    int j = 0;
	    for(HhBase base:financingTypeDetail) {
	    	arr2[j]=base.getDescription();
	    	j++;
	    }
	    //下拉3
	    List<HhBase> currencyKind= baseService.getBases(Base.currencyKind);
	    String[] arr3 = new String[currencyKind.size()];
	    int k = 0;
	    for(HhBase base:currencyKind) {
	    	arr3[k]=base.getDescription();
	    	k++;
	    }
	    selectList.add(arr1);
	    selectList.add(arr2);
	    selectList.add(arr3);
		return selectList;
	}
	
	//excel导入
	@ResponseBody
	@RequestMapping("/importExcel")
	public String importExcel(@RequestParam(value = "upfile", required = false) MultipartFile file, String dataNum, String memberCompId, String memberCompName, String coreCompId, String coreCompName)throws Exception {
		if(file.isEmpty())
            return "";
		InputStream in =null;  
        List<Object> listob = null;  
        in = file.getInputStream();  
        listob = new ExcelTool().getDetailListByMonthExcel(in,file.getOriginalFilename(),dataNum,memberCompId,memberCompName,coreCompId,coreCompName); 
        in.close();
      //处理实体下拉中文对应的id
        dealSelect(listob);
    	return JSONArray.toJSONString(listob);
	}
	
	public void dealSelect(List<Object> listob) {
		List<HhBase> financingType= baseService.getBases(Base.financingType);
		List<HhBase> financingTypeDetail= baseService.getBases(Base.financingTypeDetail);
		List<HhBase> currencyKind= baseService.getBases(Base.currencyKind);
		for(Object obj:listob) {
			ReportMonthlyFinancingOutDetail detail = (ReportMonthlyFinancingOutDetail)obj;
			for(HhBase base:financingType){
				if(base.getDescription().equals(detail.getFinancingTypeName())) {
					detail.setFinancingType(base.getId());
					break;
				}
			}
			for(HhBase base:financingTypeDetail){
				if(base.getDescription().equals(detail.getFinancingTypeDetailName())) {
					detail.setFinancingTypeDetail(base.getId());
					break;
				}
			}
			for(HhBase base:currencyKind){
				if(base.getDescription().equals(detail.getCurrencyName())) {
					detail.setCurrency(base.getId());
					break;
				}
			}
		}
	}
	
}

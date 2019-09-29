package com.softline.common;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.softline.entity.ReportMonthlyFinancingIntoDetail;
import com.softline.entity.ReportMonthlyFinancingOutDetail;
import com.softline.entity.ReportMonthlyInvestmentOutDetail;
import com.softline.entity.ReportWeeklyFinancingIntoDetail;
import com.softline.entity.ReportWeeklyFinancingOutDetail;
import com.softline.entity.ReportWeeklyInvestmentOutDetail;
import com.softline.entityTemp.ExcelMergedRegin;

//excel处理类
public class ExcelTool {
	
	
	/**  
	* 获取单元格信息  
	* @param sheet   
	* @param row 行下标  
	* @param column 列下标  
	* @return  
	*/  
	public  ExcelMergedRegin isMergedRegion(Workbook wb,Sheet sheet,int row ,int column) {  
/*	  if(row==7 && column==10)
		  System.out.println(1);*/
	  int sheetMergeCount = sheet.getNumMergedRegions();  
	  ExcelMergedRegin returnData =new ExcelMergedRegin();
	  returnData.setStartcol(column);
	  returnData.setStartrow(row);
	  returnData.setEndcol(column);
	  returnData.setEndrow(row);
	  for (int i = 0; i < sheetMergeCount; i++) {  
			CellRangeAddress range = sheet.getMergedRegion(i);  
			int firstColumn = range.getFirstColumn();  
			int lastColumn = range.getLastColumn();  
			int firstRow = range.getFirstRow();  
			int lastRow = range.getLastRow();  
			if(row >= firstRow && row <= lastRow){  
				if(column >= firstColumn && column <= lastColumn){  
					returnData.setMergedRegin(true);
					returnData.setEndcol(lastColumn);
					returnData.setEndrow(lastRow);
					String value=getMergedRegionValue(wb,sheet,row,column);
					returnData.setValue(value);
					if(row==firstRow && column==firstColumn)
						returnData.setNeedignore(false);
					else
						returnData.setNeedignore(true);
					return returnData;
				}  
			}  
	  } 
	  Cell cell= sheet.getRow(row).getCell(column);
	  returnData.setMergedRegin(false);
	  returnData.setValue(getCellValue(wb,cell));
	  return returnData;  
	} 
	
	
	/**   
	* 获取合并单元格的值   
	* @param sheet   
	* @param row   
	* @param column   
	* @return   
	*/    
	public String getMergedRegionValue(Workbook wb,Sheet sheet ,int row , int column){    
	    int sheetMergeCount = sheet.getNumMergedRegions();    
	        
	    for(int i = 0 ; i < sheetMergeCount ; i++){    
	        CellRangeAddress ca = sheet.getMergedRegion(i);    
	        int firstColumn = ca.getFirstColumn();    
	        int lastColumn = ca.getLastColumn();    
	        int firstRow = ca.getFirstRow();    
	        int lastRow = ca.getLastRow();    
	            
	        if(row >= firstRow && row <= lastRow){    
	                
	            if(column >= firstColumn && column <= lastColumn){    
	                Row fRow = sheet.getRow(firstRow);    
	                Cell fCell = fRow.getCell(firstColumn);    
	                return getCellValue(wb,fCell) ;    
	            }    
	        }    
	    }    
	        
	    return null ;    
	} 
	
	/** 
    * 根据excel单元格类型获取excel单元格值 
    * @param cell 
    * @return 
    */  
	public String getCellValue(Workbook wb, Cell cell) {  
	   FormulaEvaluator evaluator=wb.getCreationHelper().createFormulaEvaluator();
       String cellvalue = "";  
       if (cell != null) {  
           // 判断当前Cell的Type  
           switch (cell.getCellType()) {  
           case HSSFCell.CELL_TYPE_FORMULA:
           {
        	   cellvalue=getCellValue(wb,evaluator.evaluate(cell));
        	   break;
           }
           // 如果当前Cell的Type为NUMERIC  
           case HSSFCell.CELL_TYPE_NUMERIC: {  
               short format = cell.getCellStyle().getDataFormat();  
               if(format == 14 || format == 31 || format == 57 || format == 58){   //excel中的时间格式  
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    
                   double value = cell.getNumericCellValue();    
                   Date date = DateUtil.getJavaDate(value);    
                   cellvalue = sdf.format(date);    
               }  
               // 判断当前的cell是否为Date  
               else if (HSSFDateUtil.isCellDateFormatted(cell)) {  //先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。  
                   // 如果是Date类型则，取得该Cell的Date值           // 对2014-02-02格式识别不出是日期格式  
                   Date date = cell.getDateCellValue();  
                   DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");  
                   cellvalue= formater.format(date);  
               } else { // 如果是纯数字  
                   // 取得当前Cell的数值  
                   cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());   
                     
               }  
               break;  
           }  
           // 如果当前Cell的Type为STRIN  
           case HSSFCell.CELL_TYPE_STRING:  
               // 取得当前的Cell字符串  
               cellvalue = cell.getStringCellValue().replaceAll("'", "''");  
               break;  
           case  HSSFCell.CELL_TYPE_BLANK:  //空类型的格子
               cellvalue = "";  
               break;  
           // 默认的Cell值  
           default:{  
               cellvalue = " ";  
           }  
           }  
       } else {  
           cellvalue = "";  
       }  
       return cellvalue;  
   } 
	
	
	 private  String getCellValue(Workbook wb,CellValue cell) {
		    FormulaEvaluator evaluator=wb.getCreationHelper().createFormulaEvaluator();
	        String cellValue = null;
	        switch (cell.getCellType()) {
	        case Cell.CELL_TYPE_STRING:
	            System.out.print("String :");
	            cellValue=cell.getStringValue();
	            break;

	        case Cell.CELL_TYPE_NUMERIC:
	            System.out.print("NUMERIC:");
	            cellValue=String.valueOf(cell.getNumberValue());
	            System.out.println(cellValue);
	            break;
	        case Cell.CELL_TYPE_FORMULA:
	        	cellValue=getCellValue(wb,(cell));
	            break;
	        default:
	            break;
	        }
	        
	        return cellValue;
	    }

   
	/**
	 * 判断两个sheet是否相同
	 * @param workbook1
	 * @param workbook2
	 * @return
	 */
	public Boolean judgeSheetTheSame(Sheet sheet1,Sheet sheet2)
    {
		//行校验
		int allrows1=sheet1.getPhysicalNumberOfRows();
		int allrows2=sheet2.getPhysicalNumberOfRows();
		if(allrows1!=allrows2)
			return false;
		//合并单元格校验
		int sheetMergeCount1 = sheet1.getNumMergedRegions();  
		int sheetMergeCount2 = sheet2.getNumMergedRegions();  
		if(sheetMergeCount1!=sheetMergeCount2)
			return false;
		
		for (int i = 0; i < sheetMergeCount1; i++) {
			CellRangeAddress range1 = sheet1.getMergedRegion(i);  
			
			int firstColumn1 = range1.getFirstColumn();  
			int lastColumn1 = range1.getLastColumn();  
			int firstRow1 = range1.getFirstRow();  
			int lastRow1 = range1.getLastRow(); 
			Boolean hassame=false;
			for (int j = 0; j < sheetMergeCount1; j++) {
				CellRangeAddress range2 = sheet2.getMergedRegion(j);  
				int firstColumn2 = range2.getFirstColumn();  
				int lastColumn2 = range2.getLastColumn();  
				int firstRow2 = range2.getFirstRow();  
				int lastRow2 = range2.getLastRow(); 
				if(firstColumn1==firstColumn2 && lastColumn1==lastColumn2 && firstRow1==firstRow2 && lastRow1==lastRow2)
				{
					hassame=true;
					break;
				}	
			}
			if(!hassame)
				return false;
		}
		
		//检查每行的列校验
		for (int k = 0; k < sheet1.getPhysicalNumberOfRows(); k++) {
             Row row1 = sheet1.getRow(k);
             Row row2 = sheet1.getRow(k);
             int col1=(row1==null) ? 0: row1.getPhysicalNumberOfCells();
             int col2=(row2==null) ? 0: row2.getPhysicalNumberOfCells();
             //列不一致
             if(col1!=col2)
            	 return false;
        }
	
    	return true;
    }
	
	/**
	 * 生成Excel
	 * @param list
	 * @param keys
	 * @param columnNames
	 * @return
	 */
	public void createWorkBook(List<Map<String, Object>> list,String []keys,String columnNames[],ServletOutputStream sos,List<String[]> selectList) {
        //获取sheet名字
		String sName = list.get(0).get("sheetName").toString();
		//根据名字设置此excel有多少个下拉，并加入下拉
		
		// 创建excel工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(sName);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        //设置自动换行    
        cs.setWrapText(true);  
        CellStyle cs2 = wb.createCellStyle();
        cs2.setWrapText(true); 
        
        //设置时间单元格
        CellStyle cs3 = wb.createCellStyle();
        HSSFDataFormat format= wb.createDataFormat();
        cs3.setDataFormat(format.getFormat("yyyy-MM-dd"));
        cs3.setWrapText(true);
        
        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        // 设置第三种单元格的样式（用于时间值）
        cs3.setFont(f2);
        cs3.setBorderLeft(CellStyle.BORDER_THIN);
        cs3.setBorderRight(CellStyle.BORDER_THIN);
        cs3.setBorderTop(CellStyle.BORDER_THIN);
        cs3.setBorderBottom(CellStyle.BORDER_THIN);
        cs3.setAlignment(CellStyle.ALIGN_CENTER);
        
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
            	Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                if(sName.equals("筹资性流入明细列表")) {
                	if(j == 4 || j == 5) {//日期格式
                        cell.setCellStyle(cs3);
                	} else {//其他统一格式
                        cell.setCellStyle(cs2);
                        if(j == 8) {//增加对应下拉
                    		DataValidation data_validation_list2 = this.getDataValidationByFormula(selectList.get(0),i,j);    
                            //工作表添加验证数据      
                            sheet.addValidationData(data_validation_list2);
                    	}else if(j == 9) {//增加对应下拉
                    		DataValidation data_validation_list2 = this.getDataValidationByFormula(selectList.get(1),i,j);    
                            //工作表添加验证数据      
                            sheet.addValidationData(data_validation_list2);
                    	}
                	}
                	
                }else if(sName.equals("筹资性流出明细列表")) {
                	if(j == 5) {//日期格式
                        cell.setCellStyle(cs3);
                	}else {//其他统一格式
                        cell.setCellStyle(cs2);
                		if(j == 3) {//增加对应下拉
                			DataValidation data_validation_list2 = this.getDataValidationByFormula(selectList.get(0),i,j);    
                			//工作表添加验证数据      
                			sheet.addValidationData(data_validation_list2);
                		}else if(j == 4) {//增加对应下拉
                			DataValidation data_validation_list2 = this.getDataValidationByFormula(selectList.get(1),i,j);    
                			//工作表添加验证数据      
                			sheet.addValidationData(data_validation_list2);
                		}else if(j == 7) {//增加对应下拉
                			DataValidation data_validation_list2 = this.getDataValidationByFormula(selectList.get(2),i,j);    
                			//工作表添加验证数据      
                			sheet.addValidationData(data_validation_list2);
                		}
                	}
                }else if(sName.equals("投资性流出明细列表")) {
                	if(j == 3) {//日期格式
                		cell.setCellStyle(cs3);
                	}else {//其他统一格式
                		cell.setCellStyle(cs2);
                		if(j == 4) {//增加对应下拉
                			DataValidation data_validation_list2 = this.getDataValidationByFormula(selectList.get(0),i,j);    
                			//工作表添加验证数据      
                			sheet.addValidationData(data_validation_list2);
                		}else if(j == 6) {//增加对应下拉
                			DataValidation data_validation_list2 = this.getDataValidationByFormula(selectList.get(1),i,j);    
                			//工作表添加验证数据      
                			sheet.addValidationData(data_validation_list2);
                		}
                	}
                }
            }
        }
        try  
        {  
        	wb.write(sos);  
        	sos.flush();  
        	sos.close();  
        }  
        catch (IOException e)  
        {  
           e.printStackTrace();  
        }  
        finally  
        {  
            try  
             {  
            	 sos.close();  
              }  
             catch (IOException e)  
             {  
                  e.printStackTrace();  
             }  
         }  
    }
	
	 /**   
     * 使用已定义的数据源方式设置一个数据验证   
     * @param formulaString   
     * @param naturalRowIndex   
     * @param naturalColumnIndex   
     * @return   
     */    
    public DataValidation getDataValidationByFormula(String[] formulaString,int naturalRowIndex,int naturalColumnIndex){    
        //加载下拉列表内容      
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(formulaString);     
        //设置数据有效性加载在哪个单元格上。      
        //四个参数分别是：起始行、终止行、起始列、终止列      
        int firstRow = naturalRowIndex;    
        int lastRow = naturalRowIndex;    
        int firstCol = naturalColumnIndex;    
        int lastCol = naturalColumnIndex;    
        CellRangeAddressList regions=new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);      
        //数据有效性对象     
        DataValidation data_validation_list = new HSSFDataValidation(regions,constraint);    
        return data_validation_list;      
    }
    
    /** 
     * 现金流执行-周excel
     * 描述：获取IO流中的数据，组装成List<Object>对象 
     * @param in,fileName 
     * @return 
     * @throws IOException  
     */  
    public List<Object> getDetailListByWeekExcel(InputStream in,String fileName,String dataNum, String memberCompId, String memberCompName, String coreCompId, String coreCompName) throws Exception{  
        List<Object> list = null;  
          
        //创建Excel工作薄  
        Workbook work = this.getWorkbook(in,fileName);  
        if(null == work){  
            return null;  
        }  
        Sheet sheet = null;  
        Row row = null;  
        Cell cell = null;  
          
        list = new ArrayList<Object>();  
        //遍历Excel中所有的sheet  
        for (int i = 0; i < work.getNumberOfSheets(); i++) {  
            sheet = work.getSheetAt(i);  
            if(sheet==null){continue;}  
              
            //遍历当前sheet中的所有数值行 
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) { 
                row = sheet.getRow(j);  
                if(row==null||row.getFirstCellNum()==j){continue;}  
            	if("".equals(this.getCellValue(row.getCell(0)))){continue;}//如果序号为空，则判定这一行没有数据
            	Object obj = new Object();
            	if("1".equals(dataNum)) {//筹资性流入明细列表---dataNum=1
            		ReportWeeklyFinancingIntoDetail detail = new ReportWeeklyFinancingIntoDetail();
                    detail.setLoanCompName(this.getCellValue(row.getCell(1)).toString());
                    detail.setLoanBank(this.getCellValue(row.getCell(2)).toString());
                    detail.setLoanAmount(this.getCellValue(row.getCell(3)).toString());
                    detail.setAccountDate(this.getCellValue(row.getCell(4)).toString());
                    detail.setDueDate(this.getCellValue(row.getCell(5)).toString());
                    detail.setLengthOfMaturity(this.getCellValue(row.getCell(6)).toString());
                    detail.setFinancingAccountCost(this.getCellValue(row.getCell(7)).toString());
                    detail.setTypeName(this.getCellValue(row.getCell(8)).toString());
                    detail.setLoanTypeName(this.getCellValue(row.getCell(9)).toString());
                    detail.setMemberCompId(memberCompId);
                    detail.setMemberCompName(memberCompName);
                    detail.setCoreCompId(coreCompId);
                    detail.setCoreCompName(coreCompName);
                    obj = detail;
            	}else if("2".equals(dataNum)) {
            		ReportWeeklyFinancingOutDetail detail = new ReportWeeklyFinancingOutDetail();
            		detail.setLoanCompName(this.getCellValue(row.getCell(1)).toString());
                    detail.setRepayBank(this.getCellValue(row.getCell(2)).toString());
                    detail.setFinancingTypeName(this.getCellValue(row.getCell(3)).toString());
                    detail.setFinancingTypeDetailName(this.getCellValue(row.getCell(4)).toString());
                    detail.setRepayDate(this.getCellValue(row.getCell(5)).toString());
                    detail.setRepayAmount(this.getCellValue(row.getCell(6)).toString());
                    detail.setCurrencyName(this.getCellValue(row.getCell(7)).toString());
                    detail.setMemberCompId(memberCompId);
                    detail.setMemberCompName(memberCompName);
                    detail.setCoreCompId(coreCompId);
                    detail.setCoreCompName(coreCompName);
                    obj = detail;
            	}else {
            		ReportWeeklyInvestmentOutDetail detail = new ReportWeeklyInvestmentOutDetail();
            		detail.setPayProjectName(this.getCellValue(row.getCell(1)).toString());
                    detail.setPayAmount(this.getCellValue(row.getCell(2)).toString());
                    detail.setPayDate(this.getCellValue(row.getCell(3)).toString());
                    detail.setCurrencyName(this.getCellValue(row.getCell(4)).toString());
                    detail.setSpecialFundSupportPlan(this.getCellValue(row.getCell(5)).toString());
                    detail.setInvestTypeName(this.getCellValue(row.getCell(6)).toString());
                    detail.setMemberCompId(memberCompId);
                    detail.setMemberCompName(memberCompName);
                    detail.setCoreCompId(coreCompId);
                    detail.setCoreCompName(coreCompName);
                    obj = detail;
            	}
            	list.add(obj);  
            }  
        }  
        work.close();  
        return list;  
    }  
    
    /** 
     * 现金流执行-月excel
     * 描述：获取IO流中的数据，组装成List<Object>对象 
     * @param in,fileName 
     * @return 
     * @throws IOException  
     */  
    public List<Object> getDetailListByMonthExcel(InputStream in,String fileName,String dataNum, String memberCompId, String memberCompName, String coreCompId, String coreCompName) throws Exception{  
        List<Object> list = null;  
          
        //创建Excel工作薄  
        Workbook work = this.getWorkbook(in,fileName);  
        if(null == work){  
            return null;  
        }  
        Sheet sheet = null;  
        Row row = null;  
        Cell cell = null;  
          
        list = new ArrayList<Object>();  
        //遍历Excel中所有的sheet  
        for (int i = 0; i < work.getNumberOfSheets(); i++) {  
            sheet = work.getSheetAt(i);  
            if(sheet==null){continue;}  
              
            //遍历当前sheet中的所有数值行 
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) { 
                row = sheet.getRow(j);  
                if(row==null||row.getFirstCellNum()==j){continue;}  
            	if("".equals(this.getCellValue(row.getCell(0)))){continue;}//如果序号为空，则判定这一行没有数据
            	Object obj = new Object();
            	if("1".equals(dataNum)) {//筹资性流入明细列表---dataNum=1
            		ReportMonthlyFinancingIntoDetail detail = new ReportMonthlyFinancingIntoDetail();
                    detail.setLoanCompName(this.getCellValue(row.getCell(1)).toString());
                    detail.setLoanBank(this.getCellValue(row.getCell(2)).toString());
                    detail.setLoanAmount(this.getCellValue(row.getCell(3)).toString());
                    detail.setAccountDate(this.getCellValue(row.getCell(4)).toString());
                    detail.setDueDate(this.getCellValue(row.getCell(5)).toString());
                    detail.setLengthOfMaturity(this.getCellValue(row.getCell(6)).toString());
                    detail.setFinancingAccountCost(this.getCellValue(row.getCell(7)).toString());
                    detail.setTypeName(this.getCellValue(row.getCell(8)).toString());
                    detail.setLoanTypeName(this.getCellValue(row.getCell(9)).toString());
                    detail.setMemberCompId(memberCompId);
                    detail.setMemberCompName(memberCompName);
                    detail.setCoreCompId(coreCompId);
                    detail.setCoreCompName(coreCompName);
                    obj = detail;
            	}else if("2".equals(dataNum)) {
            		ReportMonthlyFinancingOutDetail detail = new ReportMonthlyFinancingOutDetail();
            		detail.setLoanCompName(this.getCellValue(row.getCell(1)).toString());
                    detail.setRepayBank(this.getCellValue(row.getCell(2)).toString());
                    detail.setFinancingTypeName(this.getCellValue(row.getCell(3)).toString());
                    detail.setFinancingTypeDetailName(this.getCellValue(row.getCell(4)).toString());
                    detail.setRepayDate(this.getCellValue(row.getCell(5)).toString());
                    detail.setRepayAmount(this.getCellValue(row.getCell(6)).toString());
                    detail.setCurrencyName(this.getCellValue(row.getCell(7)).toString());
                    detail.setMemberCompId(memberCompId);
                    detail.setMemberCompName(memberCompName);
                    detail.setCoreCompId(coreCompId);
                    detail.setCoreCompName(coreCompName);
                    obj = detail;
            	}else {
            		ReportMonthlyInvestmentOutDetail detail = new ReportMonthlyInvestmentOutDetail();
            		detail.setPayProjectName(this.getCellValue(row.getCell(1)).toString());
                    detail.setPayAmount(this.getCellValue(row.getCell(2)).toString());
                    detail.setPayDate(this.getCellValue(row.getCell(3)).toString());
                    detail.setCurrencyName(this.getCellValue(row.getCell(4)).toString());
                    detail.setSpecialFundSupportPlan(this.getCellValue(row.getCell(5)).toString());
                    detail.setInvestTypeName(this.getCellValue(row.getCell(6)).toString());
                    detail.setMemberCompId(memberCompId);
                    detail.setMemberCompName(memberCompName);
                    detail.setCoreCompId(coreCompId);
                    detail.setCoreCompName(coreCompName);
                    obj = detail;
            	}
            	list.add(obj);  
            }  
        }  
        work.close();  
        return list;  
    }  

	/**
	 * 描述：获取IO流中的数据，组装成List<List<Object>>对象
	 * 
	 * @param in,fileName
	 * @return
	 * @throws IOException
	 */
	public List<List<Object>> getBankListByExcel(InputStream in, String fileName)
			throws Exception {
		List<List<Object>> list = null;

		// 创建Excel工作薄
		Workbook work = this.getWorkbook(in, fileName);
		if (null == work) {
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		list = new ArrayList<List<Object>>();
		// 模版数据都写在sheet1
		sheet = work.getSheetAt(0);
		if (sheet == null) {
			throw new Exception("创建Excel工作薄为空！");
		}

		// 遍历当前sheet中的所有行
		for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
			row = sheet.getRow(j);
			if (row == null || row.getFirstCellNum() == j) {
				continue;
			}

			// 遍历所有的列
			List<Object> li = new ArrayList<Object>();
			for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
				cell = row.getCell(y);
				if(cell == null){
					li.add(null);
				}else{
					li.add(this.getTaxCellValue(cell));
				}
			}
			list.add(li);
		}
		work.close();
		return list;
	}

    /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
        Workbook wb = null;  
        String fileType = fileName.substring(fileName.lastIndexOf("."));  
        if(".xls".equals(fileType)){  
            wb = new HSSFWorkbook(inStr);  //2003-  
        }else if(".xlsx".equals(fileType)){  
            wb = new XSSFWorkbook(inStr);  //2007+  
        }else{  
            return null;  
        }  
        return wb;  
    }  

    /** 
     * 描述：对纳税相关表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    public  Object getTaxCellValue(Cell cell){  
        Object value = null;  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式化  
        DecimalFormat df = new DecimalFormat("0.000000");  //格式化数字 (单位万元：六位小数) 
        String a = cell.getCellStyle().getDataFormatString();
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_FORMULA:
        	try {  
        		value = String.valueOf(cell.getStringCellValue());  
        	} catch (IllegalStateException e) {  
        		value = df.format(cell.getNumericCellValue());  
        	}  
        	break; 
        case Cell.CELL_TYPE_STRING:  
            value = cell.getRichStringCellValue().getString();  
            break;  
        case Cell.CELL_TYPE_NUMERIC:  
            if("yyyy-mm-dd".equals(cell.getCellStyle().getDataFormatString())){ 
            	value = sdf.format(cell.getDateCellValue());
            }else{  
                value = df.format(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = cell.getBooleanCellValue();  
            break;  
        case Cell.CELL_TYPE_BLANK:  
            value = "";  
            break;  
        default:  
            break;  
        }  
        return value;  
    }
    
    /** 
     * 描述：对表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    public  Object getCellValue(Cell cell){  
        Object value = null;  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式化  
        DecimalFormat df = new DecimalFormat("0.00");  //格式化数字 (单位万元：两位小数) 
        String a = cell.getCellStyle().getDataFormatString();
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_FORMULA:
        	try {  
        		value = String.valueOf(cell.getStringCellValue());  
        	} catch (IllegalStateException e) {  
        		value = df.format(cell.getNumericCellValue());  
        	}  
        	break; 
        case Cell.CELL_TYPE_STRING:  
            value = cell.getRichStringCellValue().getString();  
            break;  
        case Cell.CELL_TYPE_NUMERIC:  
            if("yyyy-mm-dd".equals(cell.getCellStyle().getDataFormatString()) || "yyyy\\-mm\\-dd".equals(a)){ 
            	value = sdf.format(cell.getDateCellValue());
            }else{  
                value = df.format(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = cell.getBooleanCellValue();  
            break;  
        case Cell.CELL_TYPE_BLANK:  
            value = "";  
            break;  
        default:  
            break;  
        }  
        return value;  
    }
	
}

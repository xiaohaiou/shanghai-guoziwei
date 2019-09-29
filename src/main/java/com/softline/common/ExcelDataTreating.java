package com.softline.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.softline.entity.HhBase;
import com.softline.entity.moneyFlow.DataMoneyFlowMonth;
import com.softline.entity.moneyFlow.DataMoneyFlowMonthCi;
import com.softline.entity.moneyFlow.DataMoneyFlowMonthCo;
import com.softline.entity.moneyFlow.DataMoneyFlowMonthIo;
import com.softline.entity.moneyFlow.DataMoneyFlowWeek;
import com.softline.entity.moneyFlow.DataMoneyFlowWeekCi;
import com.softline.entity.moneyFlow.DataMoneyFlowWeekCo;
import com.softline.entity.moneyFlow.DataMoneyFlowWeekIo;
import com.softline.service.system.IBaseService;

//excel处理类
public class ExcelDataTreating {

	public static final String EMPTY = "";
	public static final String POINT = ".";

	// 表格样式及格式化
	public static String getXValue(XSSFCell xssfCell) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			String cellValue = "";
			if (DateUtil.isCellDateFormatted(xssfCell)) {
				Date date = DateUtil
						.getJavaDate(xssfCell.getNumericCellValue());
				cellValue = sdf.format(date);
			} else {
				DecimalFormat df = new DecimalFormat("#.##");
				cellValue = df.format(xssfCell.getNumericCellValue());
				String strArr = cellValue.substring(
						cellValue.lastIndexOf(POINT) + 1, cellValue.length());
				if (strArr.equals("00")) {
					cellValue = cellValue.substring(0,
							cellValue.lastIndexOf(POINT));
				}
			}
			return cellValue;
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	// 表格样式及格式化
	public static String getHValue(Cell cell) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			String cellValue = "";
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = HSSFDateUtil
						.getJavaDate(cell.getNumericCellValue());
				cellValue = sdf.format(date);
			} else {
				DecimalFormat df = new DecimalFormat("#.##");
				cellValue = df.format(cell.getNumericCellValue());
				String strArr = cellValue.substring(
						cellValue.lastIndexOf(POINT) + 1, cellValue.length());
				if (strArr.equals("00")) {
					cellValue = cellValue.substring(0,
							cellValue.lastIndexOf(POINT));
				}
			}
			return cellValue;
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	/**
	 * 导入-解析xlsx数据
	 */
	public String readXlsx(MultipartFile file, String fileType)
			throws IOException {
		Map<String, List<ArrayList<String>>> excelMap = new HashMap<String, List<ArrayList<String>>>();
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		// IO流读取文件
		InputStream input = null;
		XSSFWorkbook wb = null;
		ArrayList<String> rowList = null;
		int totalRows; // sheet中总行数
		int totalCells; // 每一行总单元格数
		ExcelDataTreating tool = new ExcelDataTreating();
		try {
			input = file.getInputStream();
			// 创建文档
			wb = new XSSFWorkbook(input);
			// 读取sheet(页)
			for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				totalRows = xssfSheet.getLastRowNum();
				// 读取Row,从第二行开始
				for (int rowNum = 0; rowNum <= totalRows; rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						rowList = new ArrayList<String>();
						totalCells = xssfRow.getLastCellNum();
						// 读取列，从第一列开始
						for (int c = 0; c <= totalCells + 1; c++) {
							XSSFCell cell = xssfRow.getCell(c);
							if (cell == null) {
								rowList.add("");
								continue;
							}
							rowList.add(tool.getXValue(cell).trim());
						}
						list.add(rowList);
					}
				}
			}
			excelMap.put(fileType, list);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(excelMap);
	}
	
	/**
	 * 导入-解析xlsx数据
	 */
	public List<ArrayList<String>> loadXlsx(MultipartFile file, String fileType)
			throws IOException {
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		// IO流读取文件
		InputStream input = null;
		XSSFWorkbook wb = null;
		ArrayList<String> rowList = null;
		int totalRows; // sheet中总行数
		int totalCells; // 每一行总单元格数
		ExcelDataTreating tool = new ExcelDataTreating();
		try {
			input = file.getInputStream();
			// 创建文档
			wb = new XSSFWorkbook(input);
			// 读取sheet(页)
			for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				totalRows = xssfSheet.getLastRowNum();
				// 读取Row,从第二行开始
				for (int rowNum = 0; rowNum <= totalRows; rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						rowList = new ArrayList<String>();
						totalCells = xssfRow.getLastCellNum();
						// 读取列，从第一列开始
						for (int c = 0; c <= totalCells + 1; c++) {
							XSSFCell cell = xssfRow.getCell(c);
							if (cell == null) {
								rowList.add("");
								continue;
							}
							rowList.add(tool.getXValue(cell).trim());
						}
						list.add(rowList);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 导入-解析xls数据
	 */
	public List<ArrayList<String>> loadXls(MultipartFile file, String fileType)
			throws IOException {
		ArrayList<String> colList = null;
		List<ArrayList<String>> rowList=new ArrayList<ArrayList<String>>();
		try {
			// 一 使用文件输入流 读取 xls文件  
	        InputStream inp = file.getInputStream(); 
	        // 二 获得文档对象  
	        POIFSFileSystem fileSystem = new POIFSFileSystem(inp);  
	        HSSFWorkbook wb = new HSSFWorkbook(fileSystem);  
	        // 三 获得sheet对象  
	        HSSFSheet sheet = wb.getSheetAt(0);  
	        // 操作数据  
	        Iterator<Row> rowIterator = sheet.rowIterator(); 
	        int i = 1;
	        while(rowIterator.hasNext()){  
	        	if((i++)==1){//跳过表头
	        		continue; 
	        	}
	            Row row = rowIterator.next();  
	            Iterator<Cell> cellIterator = row.cellIterator(); 
	            colList = new ArrayList<String>();
	            while(cellIterator.hasNext()){ 
	                Cell cell = cellIterator.next(); 
	                if(cell.getCellType()==Cell.CELL_TYPE_STRING){  
	                    //System.out.print(cell.getStringCellValue() + ",");
	                    colList.add(cell.getStringCellValue()+"");
	                }else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
	                	if(DateUtil.isCellDateFormatted(cell)){
	                        //用于转化为日期格式
	                        Date d = cell.getDateCellValue();
	                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	                        colList.add(formater.format(d)+"");
	                        //System.out.print(formater.format(d)+",");
                        }else{
	                    	//System.out.print(cell.getNumericCellValue()+",");
	 	                    colList.add(cell.getNumericCellValue()+"");//this.getHValue(cell).trim()
                        }
	                }
	            }
	            rowList.add(colList);
	            //System.out.println();
	        }
	        //循环保存
	        
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		return rowList;
	}

	/**
	 * 设置导出表头
	 */
	public HSSFRow setPlanMonthExcelTitle(HSSFCellStyle style, HSSFRow row,
			Integer numType, String[] titleArray) {
		// 居中格式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置表头
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		for (int i = 0; i < titleArray.length; i++) {
			cell = row.createCell((i + 1));
			cell.setCellValue(titleArray[i]);
			cell.setCellStyle(style);
		}
		return row;
	}

	/**
	 * 设置导出表头
	 */
	public HSSFRow setExcelTitle(HSSFCellStyle style, HSSFRow row,
			String[] titleArray) {
		// 居中格式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置表头
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		for (int i = 0; i < titleArray.length; i++) {
			cell = row.createCell((i + 1));
			cell.setCellValue(titleArray[i]);
			cell.setCellStyle(style);
		}
		return row;
	}
	
	/**
	 * 设置导出表头-特殊序号
	 */
	public HSSFRow setBondExcelTitle(HSSFCellStyle style, HSSFRow row,
			String[] titleArray) {
		// 居中格式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置表头
		HSSFCell cell = null;
		for (int i = 0; i < titleArray.length; i++) {
			cell = row.createCell((i));
			cell.setCellValue(titleArray[i]);
			cell.setCellStyle(style);
		}
		return row;
	}
	
	
	public HSSFSheet setExcelData(HSSFSheet sheet,DataMoneyFlowMonth dh,HSSFCellStyle style,List<List<String[]>> list) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		// 循环将数据写入Excel
		int t = 1;
		for (DataMoneyFlowMonthCi ci : dh.getDataMoneyFlowMonthCi()) {
			row = sheet.createRow(t);
			// 创建单元格，设置值
			Cell cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(t++);
			
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(ci.getCommitmentShallSubject());
			
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(ci.getLendingBank());
			
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(ci.getLoanAmount());
			
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(ci.getPlaceOrderDate());
			
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(setSelectValue(sheet,ci.getFinancingType(),list,3,t-1,5));	//融资类型
			
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(setSelectValue(sheet,ci.getNewOrRenewed(),list,2,t-1,6));		//新增或续作
			
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(setSelectValue(sheet,ci.getPlaceOrderCurrency(),list,0,t-1,7));//下账币种
			
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(ci.getCompositeCost());	
			
			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue(setSelectValue(sheet,ci.getEquityFinancing(),list,1,t-1,9));	//是否权益性融
		}
		return sheet;
	}
	
	/**
	 * 设置导出数据
	 */
	public HSSFSheet setPlanMonthExcelData(HSSFSheet sheet,
			Integer numType, DataMoneyFlowMonth dh,HSSFCellStyle style,List<List<String[]>> list) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		switch (numType) {
		case 1:
			// 循环将数据写入Excel
			for (int i = 0; i < 500; i++) {//默认给500条
				row = sheet.createRow((int)i+1);
				// 创建单元格，设置值
				Cell cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(3);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(4);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(5);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,3,i+1,5));	//融资类型
				
				cell = row.createCell(6);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,2,i+1,6));		//新增或续作
				
				cell = row.createCell(7);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,0,i+1,7));//下账币种
				
				cell = row.createCell(8);
				cell.setCellStyle(style);
				cell.setCellValue("");	
				
				cell = row.createCell(9);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,1,i+1,9));	//是否权益性融
			}
			break;
		case 2:
			// 循环将数据写入Excel
			for (int i = 0; i < 500; i++) {//默认给500条
				row = sheet.createRow((int)i+1);
				// 创建单元格，设置值
				Cell cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(3);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(4);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,3,i+1,4));		//还贷类型
				
				cell = row.createCell(5);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(6);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,2,i+1,6));	//是否续作
				
				cell = row.createCell(7);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,0,i+1,7));	//下账币种
				
				cell = row.createCell(8);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(9);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,1,i+1,9));//是否有保障还款计划
			}
			break;
		case 3:
			// 循环将数据写入Excel
			for (int i = 0; i < 500; i++) {//默认给500条
				row = sheet.createRow((int)i+1);
				// 创建单元格，设置值
				Cell cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(setSelectValue(sheet,"",list,0,i+1,5));	//投资币种
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(7);
				cell.setCellValue(setSelectValue(sheet,"",list,4,i+1,7));			//投资类型
				cell.setCellStyle(style);
				
				cell = row.createCell(8);
				cell.setCellValue(setSelectValue(sheet,"",list,1,i+1,8));	//是否有保障还款计划
				cell.setCellStyle(style);
			}
			break;
		default:
			break;
		}
		return sheet;
	}
	
	
	/**
	 * 设置导出Week数据
	 */
	public HSSFSheet setPlanWeekExcelData(HSSFSheet sheet,
			Integer numType, DataMoneyFlowWeek dh,HSSFCellStyle style,List<List<String[]>> list) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		switch (numType) {
		case 1:
			// 循环将数据写入Excel
			for (int i = 0; i < 500; i++) {//默认给500条
				row = sheet.createRow((int)i+1);
				// 创建单元格，设置值
				Cell cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(3);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(4);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(5);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,3,i+1,5));	//融资类型
				
				cell = row.createCell(6);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,2,i+1,6));		//新增或续作
				
				cell = row.createCell(7);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,0,i+1,7));//下账币种
				
				cell = row.createCell(8);
				cell.setCellStyle(style);
				cell.setCellValue("");	
				
				cell = row.createCell(9);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,1,i+1,9));	//是否权益性融
			}
			break;
		case 2:
			// 循环将数据写入Excel
			for (int i = 0; i < 500; i++) {//默认给500条
				row = sheet.createRow((int)i+1);
				// 创建单元格，设置值
				Cell cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(3);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(4);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,3,i+1,4));		//还贷类型
				
				cell = row.createCell(5);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(6);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,2,i+1,6));	//是否续作
				
				cell = row.createCell(7);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,0,i+1,7));	//下账币种
				
				cell = row.createCell(8);
				cell.setCellStyle(style);
				cell.setCellValue("");
				
				cell = row.createCell(9);
				cell.setCellStyle(style);
				cell.setCellValue(setSelectValue(sheet,"",list,1,i+1,9));//是否有保障还款计划
			}
			break;
		case 3:
			// 循环将数据写入Excel
			for (int i = 0; i < 500; i++) {//默认给500条
				row = sheet.createRow((int)i+1);
				// 创建单元格，设置值
				Cell cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(setSelectValue(sheet,"",list,0,i+1,5));	//投资币种
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(style);
				
				cell = row.createCell(7);
				cell.setCellValue(setSelectValue(sheet,"",list,4,i+1,7));			//投资类型
				cell.setCellStyle(style);
				
				cell = row.createCell(8);
				cell.setCellValue(setSelectValue(sheet,"",list,1,i+1,8));	//是否有保障还款计划
				cell.setCellStyle(style);
			}
			break;
		default:
			break;
		}
		return sheet;
	}
			
	/**
	 * 设置下拉框值
	 * @param id	
	 * @param List	下拉框数据
	 * @param type	下拉框类型对应数据
	 * @param col	列
	 * @param row	行
	 * @return
	 */
	public String setSelectValue(HSSFSheet sheet,String id,List<List<String[]>> List,Integer type,Integer row,Integer col){	
		List<String[]> idList = List.get(0);
		List<String[]> selectList = List.get(1);
		//设置下拉类型
		sheet.addValidationData(getDataValidationByFormula(selectList.get(type),row,col));
		//字典数据对应
		for(int i = 0;i<idList.get(type).length;i++){
/*			if(idList.get(type)[i].equals(id)){
				return selectList.get(type)[i];
			}*/
		}
		return "";
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
	 * 表格输出
	 */
	public String outputExcel(HSSFWorkbook wb, String fileName,
			HttpServletResponse res) throws IOException {
		// 字节流
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		res.reset();
		res.setContentType("application/vnd.ms-excel;charset=utf-8");
		res.setHeader("Content-Disposition", "attachment;filename="
				+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out = res.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "导出数据失败！";
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return "success!";
	}
	
	/**
	 * 表格输出
	 */
	public List<HSSFCellStyle> setExcelStyle(HSSFWorkbook wb) {
		List<HSSFCellStyle> styleList = new ArrayList<HSSFCellStyle>();
		 // 创建两种单元格格式
		HSSFCellStyle cs = wb.createCellStyle();
        //设置自动换行    
        cs.setWrapText(true);  
        HSSFCellStyle cs2 = wb.createCellStyle();
        
        cs2.setWrapText(true); 
        
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
        /*cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);*/
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        
        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
        
        cs.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        /*cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);*/
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        
        cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
        cs2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
        cs2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
        cs2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
        cs2.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
        cs2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
        
        styleList.add(cs);
        styleList.add(cs2);
		return styleList;
	}
	
	
}

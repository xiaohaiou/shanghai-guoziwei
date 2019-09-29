package com.softline.util;

import com.softline.common.Common;

/**
 * 验证导入excel中单元格中的数据是否合法
 * 判断条件如下：
 * 1、是否必填NotEmpty or Empty
 * 2、类型（varChar、int、double）
 * 3、长度
 * @author Administrator
 *
 */
public class ExcelCellValueValidate {
	/**
	 * 
	 * @param value  需要验证的值
	 * @param isEmpty 是否必填
	 * @param dataType 数据类型
	 * @param length 数据长度
	 * @return
	 */
	public ExcelCellValueValidateResult validate(String value,String isEmpty,String dataType,int length){
		ExcelCellValueValidateResult result = new ExcelCellValueValidateResult();
		result.setResult(true);
		boolean valueIsNotEmpty = Common.notEmpty(value);
		if(!valueIsNotEmpty && "NotEmpty".equals(isEmpty)){//值为空是，限制条件不为空
			result.setDescription("数据必填");
			result.setResult(false);
			return result;
		}
		if(valueIsNotEmpty){
			if("int".equals(dataType)){//int型
				try {
					Integer.parseInt(value);
				} catch (NumberFormatException e) {
					result.setDescription("数据为整数");
					result.setResult(false);
					return result;
				}
			}
			if("double".equals(dataType)){//double型
				try {
					Double.parseDouble(value);
				} catch (Exception e) {
					result.setDescription("数据为数字");
					result.setResult(false);
					return result;
				}
			}
			if(value.length() > length){
				result.setDescription("数据长度超过" + length+"字符的限制");
				result.setResult(false);
				return result;
			}
		}
		return result;
	}
}

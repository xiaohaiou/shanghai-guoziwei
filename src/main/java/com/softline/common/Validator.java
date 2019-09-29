package com.softline.common;

import java.util.regex.Pattern;

import com.softline.common.Common;

/**
 * 
 * @author zxt
 * @since 2018-02-24
 */
public class Validator {

	private static String regShort = "\\d{4}-\\d{1,2}-\\d{1,2}";
	private static String regLong = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}:\\d{2}:\\d{2}";
	private static String regFull = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}:\\d{2}:\\d{2}.\\d{3}";

	/**
	 * 日期字符串须以 yyyy-MM-dd 开头。
	 * 
	 * regShort = "\\d{4}-\\d{1,2}-\\d{1,2}"
	 * regLong = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}:\\d{2}:\\d{2}";
	 * regFull = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}:\\d{2}:\\d{2}.\\d{3}";
	 * 
	 * @param dateStr 日期字符串
	 * @throws ValidatorException 传入参数不为预期的日期格式。
	 * @return true, 格式符合要求的日期字符串; false, 传入空字符串。
	 * @since 2018-02-24
	 */
	public static boolean checkDateStringFormat(String dateStr){
		if (Common.notEmpty(dateStr)) {
			if (Pattern.matches(regShort, dateStr)
					|| Pattern.matches(regLong, dateStr)
					|| Pattern.matches(regFull, dateStr)) {
				return true;
			}
			// 抛出异常，中断程序执行。以免调用程序未做逻辑判断，引起错误数据持久化。
			throw new ValidatorException("Unacceptable date string: " + dateStr);
		}
		return false;
	}

}

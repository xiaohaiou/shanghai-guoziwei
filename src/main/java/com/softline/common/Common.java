package com.softline.common;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;




/**
 * 通用类，提供基本的操作。
 * 
 * @author gwk
 * 
 */
public class Common {
	
	public static Object parseTime(Long string) {
		try {
			int h = (int) (string / 3600);
			int m = (int) (string % 3600 / 60);
			int s = (int) (string % 60);
			if (string != 0) {
				if (h != 0 && m != 0 && m >= 10 && s != 0 && s >= 10) {
					return (h + ":" + m + ":" + s);
				}
				if (h != 0 && m != 0 && m < 10 && s != 0 && s >= 10) {
					return (h + ":" + "0" + m + ":" + s);
				}
				if (h != 0 && m != 0 && m > 10 && s != 0 && s < 10) {
					return (h + ":" + m + ":" + "0" + s);
				}
				if (h != 0 && m != 0 && m < 10 && s != 0 && s < 10) {
					return (h + ":" + "0" + m + ":" + "0" + s);
				}
				if (h != 0 && m != 0 && m > 10 && s == 0) {
					return (h + ":" + m + ":" + "0" + s);
				}
				if (h != 0 && m != 0 && m < 10 && s == 0) {
					return (h + ":" + "0" + m + ":" + "0" + s);
				}
				if (h != 0 && m == 0 && s != 0 && s >= 10) {
					return (h + ":" + "0" + m + ":" + s);
				}
				if (h != 0 && m == 0 && s != 0 && s < 10) {
					return (h + ":" + "0" + m + ":" + "0" + s);
				}
				if (h != 0 && m == 0 && s == 0) {
					return (h + ":" + "0" + m + ":" + "0" + s);
				}
				if (h == 0 && m != 0 && m < 10 && s != 0 && s < 10) {
					return ("0" + m + ":" + "0" + s);
				}
				if (h == 0 && m != 0 && m < 10 && s != 0 && s >= 10) {
					return ("0" + m + ":" + s);
				}
				if (h == 0 && m != 0 && m >= 10 && s != 0 && s < 10) {
					return (m + ":" + "0" + s);
				}
				if (h == 0 && m != 0 && m >= 10 && s != 0 && s >= 10) {
					return (m + ":" + s);
				}
				if (h == 0 && m != 0 && m >= 10 && s == 0) {
					return (m + ":" + "0" + s);
				}
				if (h == 0 && m != 0 && m < 10 && s == 0) {
					return ("0" + m + ":" + "0" + s);
				}
				if (h == 0 && m == 0 && s != 0 && s >= 10) {
					return (s);
				}
				if (h == 0 && m == 0 && s != 0 && s < 10) {
					return ("0" + s);
				}
			}
			return 0;
		} catch (Exception e) {
			return null;
		}
	}

	// log4j对象
	private static final Log logger = LogFactory.getLog(Common.class);

	/**
	 * 格式化数字
	 * 
	 * @param num
	 * @param pattern
	 * @return
	 */
	public static String formatNumber(Number num, String pattern) {
		if (num == null)
			return "";
		try {
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(num);
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 是否是数字
	 * @param str
	 * @return
	 */
	public static boolean canparseNumber(String str){  
	    String reg = "^[+-]?\\d+(\\.\\d+)?$";  
	    return str.matches(reg);  
	}  
	/**
	 * String类型转换Long型
	 * 
	 * @param string
	 * @return
	 */
	public static Long parseLong(String string) {
		try {
			return Long.parseLong(string);
		} catch (Exception e) {
			logger.warn("parseLong error:" + string + " is not Long");
			return null;
		}
	}

	public static int compare_date(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() >= dt2.getTime()) {
				return 1;
			} else {
				return -1;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * String类型转换Double
	 * 
	 * @param string
	 * @return
	 */
	public static Double parseDouble(String string) {
		try {
			return Double.parseDouble(string);
		} catch (Exception e) {
			logger.warn("parseDouble error:" + string + " is not Double");
			return null;
		}
	}

	/**
	 * String类型转换BigDecimal
	 * 
	 * @param string
	 * @return
	 */
	public static BigDecimal parseBigDecimal(String string) {
		try {
			return new BigDecimal(string);
		} catch (Exception e) {
			logger.warn("BigDecimal error:" + string + " is not BigDecimal");
			return null;
		}
	}

	/**
	 * String类型转换Integer
	 * 
	 * @param string
	 * @return
	 */
	public static Integer parseInteger(String string) {
		try {
			return Integer.parseInt(string);
		} catch (Exception e) {
			logger.warn("parseInteger error:" + string + " is not Integer");
			return null;
		}
	}

	/**
	 * String类型转换Date
	 * 
	 * @param string
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String string, String pattern) {
		try {
			return DateUtils.parseDate(string, new String[] { pattern });
		} catch (ParseException e) {
			logger.warn("parseDate error:" + string + " is not Date");
			return null;
		}
	}

	/**
	 * Date类型转换String
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDate(Date date, String pattern) {
		return date == null ? null : FastDateFormat.getInstance(pattern)
				.format(date);
	}

	/**
	 * 把带"-"日期格式,转为8位不带"-"的日期格式
	 * 
	 * @param resource
	 *            : 带"-"日期格式
	 * @return: 8位不带"-"的日期格式
	 */
	public static String dateReduceSplit(String resource) {
		String target = "";
		String[] subResource = resource.split("-");
		if (subResource.length != 3) {
			return null;
		}
		for (int i = 0; i < subResource.length; i++) {
			subResource[i] = (subResource[i].length() == 2 || subResource[i]
					.length() == 4) ? subResource[i] : "0" + subResource[i];
			target += subResource[i];
		}
		if (StringUtils.isNumeric(target)) {
			return target;
		} else {
			return null;
		}
	}

	/**
	 * 把8位不带"-"的日期格式,转为带"-"日期格式
	 * 
	 * @param resource
	 *            ： 8位不带"-"的日期格式
	 * @return: 转为带"-"日期格式
	 */
	public static String dateAddSplit(String resource) {
		if (StringUtils.isNumeric(resource) && resource.length() == 8) {
			String target = "";
			target = resource.substring(0, 4) + "-" + resource.substring(4, 6)
					+ "-" + resource.substring(6);
			return target;
		} else {
			return resource;
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param String
	 *            s
	 * @return boolean
	 */
	public static boolean notEmpty(String s) {
		return !StringUtils.isBlank(s);
	}
 
	/**
	 * 得到当前日期的8位字符串
	 * 
	 * @return
	 */
	public static String getDate() {
		return getDate("yyyyMMdd");
	}

	/**
	 * 
	 */
	public static String getDate(String pattern) {
		return FastDateFormat.getInstance(pattern).format(
				Calendar.getInstance());
	}

	public static boolean betweenDate(String objDate, String pattern,
			String beginDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				Common.notEmpty(pattern) ? pattern : "yyyyMMdd");
		boolean flag = true;
		try {
			Date obj = sdf.parse(objDate);
			if (Common.notEmpty(beginDate)) {
				Date begin = sdf.parse(beginDate);
				flag &= begin.before(obj);
			}
			if (Common.notEmpty(endDate)) {
				Date end = sdf.parse(endDate);
				flag &= end.after(obj);
			}
		} catch (ParseException e) {
			return false;
		}
		return flag;
	}

	/**
	 * 中文解码,处理operation.js的paramEscape转码后数据
	 * 
	 * @param name
	 * @return
	 */
	public static String paramUnescape(String name) {
		if (Common.notEmpty(name)) {
			name = name.replace("_", "%");
			name = name.replace("!", "_");
			name = unescape(name);
		}
		return name;
	}

	/**
	 * 中文解码
	 * 
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * 判断字符串是正则匹配数字
	 * 
	 * @param String
	 *            id
	 */
	public static boolean isNumber(String id) {
		return NumberUtils.isNumber(id);
	}

	/**
	 * JasonTang
	 * 数字去除小数点后面多余的0 （1111.1000 转成 1111.1）
	 * @param num
	 * @return
	 */
	public static String reverse(String num){
		StringBuffer sb=new StringBuffer(num);
		Double dd = Common.parseDouble(sb.reverse().toString());
		sb=new StringBuffer(dd.toString());
		return sb.reverse().toString();
	}
	
	/**
	 * 附件上传
	 * Jason Tang
	 * 
	 * @param file
	 * @param path
	 * @param request
	 * @return
	 */
	public static List<String> saveFile(MultipartFile file, String package_path) {
		List<String> fileStrList = new ArrayList<String>();
		// 获取文件名
		String name = file.getOriginalFilename();
		// 截取文件后缀
		String nameSub = name.substring(name.lastIndexOf("."));
		// 拼接项目名 + 包名： \ upload \ 项目名 \ xxx \
//		String ITEM = Common.getPath(pkgs, request);
		// 拼接路劲 \ opt \ upload \ 项目名 \ supermarketPicture \
//		String pathDir = decyStr + ITEM;
		// 创建新uuid
		String uuid = java.util.UUID.randomUUID() + "";
		// 文件最终路径 F:\apache-tomcat\apache-tomcat-6.0.44\webapps\ETMS \ upload \ uuid+fileNameSub
		String filePath = package_path + uuid + nameSub;
		
		fileStrList.add(name);
		fileStrList.add(package_path + uuid + nameSub);
		fileStrList.add(uuid + nameSub);
		// 创建文件夹
		File savePivtureDirFile = new File(package_path);
		if (!savePivtureDirFile.exists()) {
			savePivtureDirFile.mkdirs();
		}
		try {
			// 转存文件
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileStrList;
	}
	
	/**
	 * 
	 * @param file 上传的文件
	 * @param package_path 路径
	 * @param fileName 文件名
	 */
	public static void saveWkFile(MultipartFile file, String package_path,String fileName){
		String filePath = package_path + fileName;
		// 创建文件夹
		File savePivtureDirFile = new File(package_path);
		if (!savePivtureDirFile.exists()) {
			savePivtureDirFile.mkdirs();
		}
		try {
			// 转存文件
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取项目下的包路径
	 * Jason Tang
	 * 
	 * @param path
	 * @param request
	 * @return
	 */
	public static String getPath(String pkgs, HttpServletRequest request) {
		String Item = File.separator
				+ request.getContextPath().substring(1)
				+ File.separator + pkgs
				+ File.separator;
		return Item;
	}
	
	/**
	 * 附件删除
	 * Jason Tang
	 * 
	 * @param path
	 * @param uuid
	 * @param request
	 */
	public static void deleteFile(String package_path, String uuid, HttpServletRequest request) {
//		String ITEM = "";
//		String pathDir = "";
		// 拼接项目名 + 包名： \ upload \ 项目名 \ supermarketPicture \
//		ITEM = Common.getPath(pkgs, request);
		// 拼接路劲 	\ opt \ upload \ 项目名 \ supermarketPicture \
//		pathDir = decyStr + ITEM;
		
		//获取已上传的文件
		File fileDelete = new File(package_path + uuid);
		//判断文件是否存在
		if (fileDelete.exists()) {
			//删除文件
			fileDelete.delete();
		}
	}
	
	/**
	 * 附件删除
	 * Jason Tang
	 * 
	 * @param path
	 * @param uuid
	 * @param request
	 */
	public static void deleteFile(String package_path, String uuid) {
//		String ITEM = "";
//		String pathDir = "";
		// 拼接项目名 + 包名： \ upload \ 项目名 \ supermarketPicture \
//		ITEM = Common.getPath(pkgs, request);
		// 拼接路劲 	\ opt \ upload \ 项目名 \ supermarketPicture \
//		pathDir = decyStr + ITEM;
		
		//获取已上传的文件
		File fileDelete = new File(package_path + uuid);
		//判断文件是否存在
		if (fileDelete.exists()) {
			//删除文件
			fileDelete.delete();
		}
	}
	
	public static String getDayorWeek(int day){
		String d = "";
		switch(day){
			case 2: d = "一"; break;
			case 3: d = "二"; break;
			case 4: d = "三"; break;
			case 5: d = "四"; break;
			case 6: d = "五"; break;
			case 7: d = "六"; break;
			case 1: d = "日"; break;
		}
		return d;
	}
	
	public static String dealinStr(String organ)
    {
    	String[] a=organ.split(",");
		String orgstr="";
		for (String item : a) {
			if(orgstr.equals(""))
				orgstr="'"+item+"'";
			else
				orgstr+=",'"+item+"'";
		}
    	return orgstr;
    }
	
	public static String getItemPath(String pkgs, HttpServletRequest request) {
		String Item = request.getSession().getServletContext().getRealPath("") + File.separator + "WEB-INF" + pkgs;
		return Item;
	}
	
	/**
	 * 利用反射比较两个实体的属性,返回比较之后的属性和值信息
	 * @param newEntity 新的实体
	 * @param oldEntity 旧的实体
	 * @param ignoreArr 不需要进行比较的属性名称
	 */
	public static Map<String, String> compareEntity(Object newEntity, Object oldEntity, String[] ignoreArr) {
		try{  
			Map<String, String> map = new HashMap<String, String>();
			List<String> ignoreList = null;  
			if(ignoreArr != null && ignoreArr.length > 0){  
                // array转化为list  
                ignoreList = Arrays.asList(ignoreArr);  
            } 
            if (newEntity.getClass() == oldEntity.getClass()) {// 只有两个对象都是同一类型的才有可比性  
                Class clazz = oldEntity.getClass();  
                // 获取object的属性描述  
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz,  
                        Object.class).getPropertyDescriptors();  
                for (PropertyDescriptor pd : pds) {// 这里就是所有的属性了  
                    String name = pd.getName();// 属性名  
                    if(ignoreList != null && ignoreList.contains(name)){// 如果当前属性选择忽略比较，跳到下一次循环  
                        continue;  
                    }  
                    Method readMethod = pd.getReadMethod();// get方法 
                    
                    String o1 = null;
                    String o2 = null;
                                                        
                    Object ob1 =  readMethod.invoke(newEntity);  
                    Object ob2 =  readMethod.invoke(oldEntity); 
                    
                    if(null!=ob1 && null!= ob2){
                        // 在newEntity上调用get方法等同于获得newEntity的属性值                 	                   
                    	o1 = readMethod.invoke(newEntity).toString();  
                    	// 在oldEntity上调用get方法等同于获得oldEntity的属性值  
                    	o2 = readMethod.invoke(oldEntity).toString();      	
                    }
      
                	if(!notEmpty(o1) && !notEmpty(o2)){  
                		continue;  
                	}else if(!notEmpty(o1) && notEmpty(o2)){  
                		map.put(name, "空");  
                		continue;  
                	}else if(notEmpty(o1) && !notEmpty(o2)){
                		map.put(name, o1);  
                		continue;
                	}else {
                		if (!o1.equals(o2)) {// 比较这两个值是否相等,不等就可以放入map了  
                			map.put(name, o1);  
                		}  
                	}   	                                    
                }  
            }  
            return map;
        }catch(Exception e){  
            e.printStackTrace();  
            return null;  
        }  
	}

	/**
	 * 从当前年份开始获取对应数据的年份.
	 * @param year 起始年份.
	 * @param count 累积的年份个数.
	 * @return
	 */
	public static List<CValue> getCircleYears(Integer year,int count){
		List<CValue> vList = new ArrayList<CValue>();
		CValue cv;
		for (int i = 0; i < count;i++){
			cv = new CValue();
			String key = String.valueOf(year + i);
			cv.setKey(key);
			String value = String.valueOf(year + i);
			cv.setValue(value);
			vList.add(cv);
		}
		return vList;
	}

	/**
	 * 获取当前年份的总周数.
	 * @param year 当前年份.
	 * @return
	 */
	public static List<CValue> getEveryWeekFromCurrentYear(int year){
		List<CValue> wList = new ArrayList<CValue>();
		CValue cv;
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER,31,23,59,59);
		Date date = c.getTime();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		int maxWeek = c.get(Calendar.WEEK_OF_YEAR);
		for (int i = 0;i < maxWeek;i++){
			cv = new CValue();
			String key = String.valueOf(i+1);
			cv.setKey(key);
			String value = "第"+(i+1)+"周";
			cv.setValue(value);
			wList.add(cv);
		}
		return wList;
	}
}

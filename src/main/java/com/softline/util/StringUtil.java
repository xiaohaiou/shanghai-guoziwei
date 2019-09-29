package com.softline.util;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private static char[] specialChar = { ' ', '(', ')', '+', '-', '*', '/' };
	  public static final String regExpStr = "[\\s|\\#|\\%|\\$|\\&|\\'|\\@|\\+|\\*|\\?|\\(|\\)|\\[|\\]|<|>|/|^|!|~|\"]";

	  public static boolean isSpecialChar(char c)
	  {
	    for (int i = 0; i < specialChar.length; i++) {
	      if (specialChar[i] == c)
	        return true;
	    }
	    return false;
	  }

	  public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens)
	  {
	    if ((str == null) || (str.length() == 0)) return new String[0];
	    StringTokenizer st = new StringTokenizer(str, delimiters);
	    List tokens = new ArrayList();
	    while (st.hasMoreTokens()) {
	      String token = st.nextToken();
	      if (trimTokens) {
	        token = token.trim();
	      }
	      if ((!ignoreEmptyTokens) || (token.length() > 0)) {
	        tokens.add(token);
	      }
	    }
	    return (String[])tokens.toArray(new String[tokens.size()]);
	  }

	  public static int getSubStrNumb(String str, String regex)
	  {
	    int m = 0;
	    try {
	      Matcher mat = Pattern.compile(regex).matcher(str);
	      while (mat.find())
	        m++;
	    }
	    catch (Exception localException)
	    {
	    }
	    return m;
	  }

	  public static int strFirstIndexOf(String str, String regex)
	  {
	    int m = 0;
	    try {
	      Matcher mat = Pattern.compile(regex).matcher(str);
	      if (mat.find()) {
	        m = mat.start();
	      }
	    }
	    catch (Exception localException)
	    {
	    }
	    return m;
	  }

	  public static int strLastIndexOf(String str, String regex)
	  {
	    int m = 0;
	    try {
	      Matcher mat = Pattern.compile(regex).matcher(str);
	      while (mat.find())
	        m = mat.start();
	    }
	    catch (Exception localException)
	    {
	    }
	    return m;
	  }

	  public static String delSpecialStr(String str, String regEx, String str2)
	  {
	    String newstr = "";
	    try {
	      if ((str != null) && (!str.equals(""))) {
	        if ((regEx == null) || (regEx.equals(""))) regEx = "[\\s|\\#|\\%|\\$|\\&|\\'|\\@|\\+|\\*|\\?|\\(|\\)|\\[|\\]|<|>|/|^|!|~|\"]";
	        Pattern p = Pattern.compile(regEx);
	        Matcher m = p.matcher(str);
	        newstr = m.replaceAll(str2);
	      }
	    } catch (Exception localException) {
	    }
	    return newstr;
	  }

	  public static String myFrontSubstr(String str, String substr, int seatType)
	  {
	    String returnStr = str;
	    try {
	      if ((!isNull(str)) && 
	        (str.indexOf(substr) > -1)) {
	        if (seatType == 0)
	          returnStr = str.substring(0, str.indexOf(substr));
	        else
	          returnStr = str.substring(0, str.lastIndexOf(substr));
	      }
	    }
	    catch (Exception localException)
	    {
	    }
	    return returnStr;
	  }

	  public static String myEndSubstr(String str, String substr, int seatType)
	  {
	    String returnStr = str;
	    try {
	      if ((!isNull(str)) && 
	        (str.indexOf(substr) > -1)) {
	        if (seatType == 0)
	          returnStr = str.substring(str.indexOf(substr) + substr.length(), str.length());
	        else
	          returnStr = str.substring(str.lastIndexOf(substr) + substr.length(), str.length());
	      }
	    }
	    catch (Exception localException)
	    {
	    }
	    return returnStr;
	  }

	  public static String mySubstr(String str, int seatType, int numb)
	  {
	    String returnStr = "";
	    try {
	      if (!isNull(str)) {
	        if (seatType == 0) {
	          if (str.length() >= numb)
	            returnStr = str.substring(0, str.length() - numb);
	        }
	        else if ((seatType == 1) && 
	          (str.length() >= numb))
	          returnStr = str.substring(numb, str.length());
	      }
	    }
	    catch (Exception localException)
	    {
	    }
	    return returnStr;
	  }

	  public static String myMiddStr(String str, int seatType, int startNumb, int numb)
	  {
	    String returnStr = "";
	    try {
	      if (!isNull(str)) {
	        int strL = str.length();
	        if (strL < startNumb) return returnStr;
	        if (seatType == 0) {
	          if ((strL >= startNumb) && (startNumb >= numb)) {
	            int start = strL - startNumb;
	            returnStr = str.substring(start, start + numb);
	          }
	        } else if ((seatType == 1) && 
	          (strL >= startNumb + numb)) {
	          int end = strL - startNumb + 1;
	          if (end >= numb)
	            returnStr = str.substring(end - numb, end);
	        }
	      }
	    }
	    catch (Exception localException)
	    {
	    }
	    return returnStr;
	  }

	  public static String removeLastStr(String str, int lastLen)
	  {
	    if ((str == null) || (str.length() <= lastLen)) {
	      return "";
	    }
	    return str.substring(0, str.length() - lastLen);
	  }

	  public static String repSubstr(String str, String newSubStr, int seatType, int startNumb, int numb)
	  {
	    String returnStr = "";
	    try {
	      if (!isNull(str)) {
	        int strL = str.length();
	        if (strL < startNumb) return returnStr;
	        if (newSubStr == null) newSubStr = "";
	        if (seatType == 0) {
	          if ((strL >= startNumb) && (startNumb >= numb)) {
	            int start = strL - startNumb;
	            String startStr = str.substring(0, start);
	            String endStr = str.substring(start + numb, strL);
	            returnStr = startStr + newSubStr + endStr;
	          }
	        } else if ((seatType == 1) && 
	          (strL >= startNumb + numb)) {
	          int end = strL - startNumb + 1;
	          if (end >= numb) {
	            returnStr = str.substring(end - numb, end);
	            String startStr = str.substring(0, end - numb);
	            String endStr = str.substring(end, strL);
	            returnStr = startStr + newSubStr + endStr;
	          }
	        }
	      }
	    }
	    catch (Exception localException) {
	    }
	    return returnStr;
	  }

	  public static String replace(String mainString, String oldString, String newString)
	  {
	    if (mainString == null) {
	      return null;
	    }
	    if ((oldString == null) || (oldString.length() == 0)) {
	      return mainString;
	    }
	    if (newString == null) {
	      newString = "";
	    }

	    int i = mainString.lastIndexOf(oldString);

	    if (i < 0) return mainString;

	    StringBuffer mainSb = new StringBuffer(mainString);

	    while (i >= 0) {
	      mainSb.replace(i, i + oldString.length(), newString);
	      i = mainString.lastIndexOf(oldString, i - 1);
	    }
	    return mainSb.toString();
	  }

	  public static String replace(String mainString, String oldString, String newString, int numb)
	  {
	    if (mainString == null) {
	      return null;
	    }
	    if ((oldString == null) || (oldString.length() == 0)) {
	      return mainString;
	    }
	    if (newString == null) {
	      newString = "";
	    }

	    StringBuffer mainSb = new StringBuffer(mainString);
	    int count = 0;
	    while ((mainSb.indexOf(oldString) >= 0) && (count < numb)) {
	      int i = mainSb.indexOf(oldString);
	      mainSb.replace(i, i + oldString.length(), newString);
	      count++;
	    }
	    return mainSb.toString();
	  }

	  public static String changTableArray2String(String[] str, String dlem)
	  {
	    StringBuffer sb = new StringBuffer();
	    if (str == null) return null;
	    for (int i = 0; i < str.length; i++)
	    {
	      if (i != 0) sb.append(dlem);
	      sb.append(str[i]);
	    }
	    return sb.toString();
	  }

	  public static String LFillStr(String str, String fillStr, int length)
	  {
	    int i = length - str.length();
	    String mStr = "";
	    for (int j = 0; j < i; j++)
	      mStr = mStr + fillStr;
	    mStr = mStr + str;
	    return mStr;
	  }

	  public static String RFillStr(String str, String fillStr, int length)
	  {
	    int i = length - str.length();
	    String mStr = "";
	    for (int j = 0; j < i; j++)
	      mStr = mStr + fillStr;
	    mStr = str + mStr;
	    return mStr;
	  }

	  public static String fillStr(String str, int n)
	  {
	    String mStr = "";
	    for (int j = 0; j < n; j++) {
	      mStr = mStr + str;
	    }

	    return mStr;
	  }

	  public static String ListToStr(List<String> list, String delem, boolean space)
	  {
	    String str = "";
	    try {
	      if ((list != null) && (list.size() > 0)) {
	        for (int i = 0; i < list.size(); i++) {
	          String sub = (String)list.get(i);
	          sub = sub.trim();
	          if (sub.equals("")) {
	            if (space) str = str + sub + delem; 
	          }
	          else {
	            str = str + sub + delem;
	          }
	        }
	        int leng = delem.length();
	        str = str.substring(0, str.length() - leng);
	      }
	    } catch (Exception localException) {
	    }
	    return str;
	  }

	  public static LinkedHashMap getLMap(Map map)
	  {
	    LinkedHashMap lmap = new LinkedHashMap();
	    try {
	      for (Iterator localIterator = map.keySet().iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
	        String str = (String)obj;
	        if (map.get(str) != null)
	          lmap.put(str, map.get(str)); }
	    }
	    catch (Exception localException) {
	    }
	    return lmap;
	  }

	  public static String ListToStr2(List<LinkedHashMap> dataLi, String rowSplit, String colSplit)
	  {
	    String str = "";
	    try {
	      if ((dataLi == null) || (dataLi.size() == 0)) return str;
	      for (int i = 0; i < dataLi.size(); i++) {
	        LinkedHashMap rowmap = (LinkedHashMap)dataLi.get(i);
	        Set colMap = rowmap.entrySet();
	        Iterator datait = colMap.iterator();
	        while (datait.hasNext()) {
	          Map.Entry colData = (Map.Entry)datait.next();
	          String colValue = "";
	          if (colData.getValue() != null) {
	            colValue = (String)colData.getValue();
	          }
	          if (colValue.equals("")) colValue = "";
	          str = str + colValue + colSplit;
	        }
	        str = myFrontSubstr(str, colSplit, 1);
	        str = str + rowSplit;
	      }
	      str = myFrontSubstr(str, rowSplit, 1);
	    } catch (Exception localException) {
	    }
	    return str;
	  }

	  public static List<String> stoken(String str, String delem)
	  {
	    List ts = new ArrayList();
	    if ((str != null) && (!str.equals(""))) {
	      StringTokenizer st = new StringTokenizer(str, delem);
	      while (st.hasMoreTokens()) {
	        String to = st.nextToken();
	        if (!isNull(to))
	          ts.add(to.trim());
	      }
	    }
	    return ts;
	  }

	  public static List<String> stoken2(String str, String delem)
	  {
	    String aa = "";
	    List ts = new ArrayList();
	    while (str.length() > 0) {
	      int i = str.indexOf(delem);
	      if (i >= 0) {
	        aa = str.substring(0, i);
	        ts.add(aa);
	        str = str.substring(i + delem.length()).trim();
	        if (str.length() == 0)
	          ts.add("");
	      } else {
	        ts.add(str);
	        break;
	      }
	    }
	    return ts;
	  }

	  public static List strToList(String str, String rowSplit, String colSplit, int delLastStrNumb)
	  {
	    List ts = new ArrayList();
	    try {
	      if ((str != null) && (!str.equals(""))) {
	        String[] row = str.split(rowSplit);
	        for (int i = 0; i < row.length - delLastStrNumb; i++) {
	          List colList = new ArrayList();
	          String[] col = row[i].split(colSplit);
	          for (int k = 0; k < col.length - delLastStrNumb; k++) {
	            colList.add(col[k]);
	          }
	          ts.add(colList);
	        }
	      }
	    }
	    catch (Exception localException) {
	    }
	    return ts;
	  }

	  public static double strToDouble(String str)
	  {
	    double m = 0.0D;
	    try {
	      m = Double.parseDouble(str);
	    } catch (Exception localException) {
	    }
	    return m;
	  }

	  public static String mapToStr(Map<String, String> map, String delem)
	  {
	    String str = "";
	    if (isNull(map)) return str;
	    if (isNull(delem)) delem = ";";
	    int i = 0;
	    for (String key : map.keySet()) {
	      if (i > 0) str = str + delem;
	      str = str + key + "=" + (String)map.get(key);
	      i++;
	    }
	    return str;
	  }

	  public static Map<String, String> strToMap(String str, String delem)
	  {
	    Map map = new LinkedHashMap();
	    if (isNull(str)) return map;
	    if (isNull(delem)) delem = ";";
	    String[] strArr = str.split(delem);
	    for (String key : strArr) {
	      String[] arr = key.split("=");
	      map.put(arr[0], arr[1]);
	    }
	    return map;
	  }

	  public static String getToken(String str, String delem, int start, int end)
	  {
	    String mStr = "";
	    List ts = stoken(str, delem);
	    for (int i = start; i <= end; i++) {
	      if (i >= ts.size()) break;
	      mStr = mStr + delem + (String)ts.get(i);
	    }

	    mStr = mStr.substring(delem.length());
	    return mStr;
	  }

	  public static String decode(String str, int n)
	  {
	    String temp = "";
	    if (n == 1) return decode(str);
	    if (n > 1) {
	      temp = decode(str);
	      return decode(temp, --n);
	    }return str;
	  }

	  public static String encode(String str, int n) {
	    String temp = "";
	    if (n == 1) return encode(str);
	    if (n > 1) {
	      temp = encode(str);
	      return encode(temp, --n);
	    }return str;
	  }

	  public static String decode(String stringTmp)
	  {
	    if (isNull(stringTmp)) return "";
	    String stringname = "";
	    for (int i = 0; i < stringTmp.length(); i++)
	    {
	      char charint = stringTmp.charAt(i);
	      switch (charint)
	      {
	      case '~':
	        String stringtmp = stringTmp.substring(i + 1, i + 3);
	        stringname = stringname + (char)Integer.parseInt(stringtmp, 16);
	        i += 2;
	        break;
	      case '^':
	        String stringtmp2 = stringTmp.substring(i + 1, i + 5);
	        stringname = stringname + (char)Integer.parseInt(stringtmp2, 16);
	        i += 4;
	        break;
	      default:
	        stringname = stringname + charint;
	      }

	    }

	    return stringname;
	  }

	  public static String encode(String stringname)
	  {
	    if (isNull(stringname)) return "";
	    String stringTmp = "";
	    for (int i = 0; i < stringname.length(); i++)
	    {
	      int charat = stringname.charAt(i);
	      if (charat > 255)
	      {
	        String tmp = Integer.toString(charat, 16);
	        for (int j = tmp.length(); j < 4; j++)
	          tmp = "0" + tmp;
	        stringTmp = stringTmp + "^" + tmp;
	      }
	      else if ((charat < 48) || ((charat > 57) && (charat < 65)) || ((charat > 90) && (charat < 97)) || (charat > 122))
	      {
	        String stringat = Integer.toString(charat, 16);
	        for (int j = stringat.length(); j < 2; j++) {
	          stringat = "0" + stringat;
	        }
	        stringTmp = stringTmp + "~" + stringat;
	      }
	      else {
	        stringTmp = stringTmp + stringname.charAt(i);
	      }

	    }

	    return stringTmp;
	  }

	  public static boolean isNull(String str)
	  {
	    if ((str == null) || (str.equals("null")) || (str.equals("undefined")) || (str.trim().length() == 0)) {
	      return true;
	    }
	    return false;
	  }

	  public static boolean isNull(Map map)
	  {
	    if ((map == null) || (map.size() == 0)) {
	      return true;
	    }
	    return false;
	  }

	  public static boolean isNull(List list)
	  {
	    if ((list == null) || (list.size() == 0)) {
	      return true;
	    }
	    return false;
	  }

	  public static String getNumbStr(String oldStr)
	  {
	    String str = oldStr;
	    try {
	      if ((oldStr.indexOf("0") == 0) && (oldStr.indexOf("0.") != 0)) return str;
	      BigDecimal bd = new BigDecimal(oldStr);
	      str = bd.toPlainString();
	    } catch (Exception e) {
	      str = oldStr;
	    }
	    return str;
	  }

	  public static String getFormat(String oldStr)
	  {
	    return getFormat(oldStr, 2, false);
	  }

	  public static String getFormatInt(String oldStr)
	  {
	    return getFormat(oldStr, 0);
	  }

	  public static String getFormat(String oldStr, int scale)
	  {
	    return getFormat(oldStr, scale, true);
	  }

	  public static String getFormat(String oldStr, int scale, boolean delPointEndZero)
	  {
	    String newStr = "";
	    String formatStr = ",##0";
	    if (scale > 0) {
	      formatStr = formatStr + ".";
	      for (int i = 0; i < scale; i++)
	        formatStr = formatStr + "0";
	    }
	    try
	    {
	      if ((oldStr != null) && (!oldStr.equals(""))) {
	        oldStr = oldStr.trim();
	      }
	      double oldInt = new Double(oldStr).doubleValue();
	      NumberFormat nf = new DecimalFormat(formatStr);
	      newStr = nf.format(oldInt);
	      if (delPointEndZero)
	        newStr = delPointEndZero(newStr);
	    } catch (Exception e) {
	      newStr = oldStr;
	    }

	    return newStr;
	  }

	  public static String getFormat1(String oldStr)
	  {
	    return getFormat1(oldStr, 2, false);
	  }

	  public static String getFormatInt1(String oldStr)
	  {
	    return getFormat1(oldStr, 0, false);
	  }

	  public static String getFormat1(String oldStr, int scale, boolean status)
	  {
	    String newStr = "";
	    if (oldStr.indexOf(".") > -1) {
	      String formatStr = "##0";
	      if (scale > 0) {
	        formatStr = formatStr + ".";
	        for (int i = 0; i < scale; i++)
	          formatStr = formatStr + "0";
	      }
	      try
	      {
	        double oldInt = new Double(oldStr).doubleValue();
	        NumberFormat nf = new DecimalFormat(formatStr);
	        newStr = nf.format(oldInt);
	      } catch (Exception e) {
	        newStr = oldStr;
	      }
	      if (status) newStr = delPointEndZero(newStr); 
	    }
	    else { newStr = oldStr; }

	    return newStr;
	  }

	  public static String getFormat0(String oldStr, int scale)
	  {
	    String newStr = "";
	    String formatStr = "##0";
	    if (scale > 0) {
	      formatStr = formatStr + ".";
	      for (int i = 0; i < scale; i++)
	        formatStr = formatStr + "0";
	    }
	    try
	    {
	      double oldInt = new Double(oldStr).doubleValue();
	      NumberFormat nf = new DecimalFormat(formatStr);
	      newStr = nf.format(oldInt);
	    } catch (Exception e) {
	      newStr = oldStr;
	    }
	    return newStr;
	  }

	  public static String delPointEndZero(String oldStr)
	  {
	    String newStr = oldStr;
	    try {
	      if ((oldStr != null) && (!oldStr.equals(""))) {
	        oldStr = oldStr.trim();
	        int pointSeat = oldStr.indexOf(".");
	        if (pointSeat > -1) {
	          int strLen = oldStr.length();
	          if ((strLen > pointSeat) && ((oldStr.substring(strLen - 1).equals("0")) || (oldStr.substring(strLen - 1).equals("."))))
	            newStr = oldStr.substring(0, oldStr.length() - 1);
	          else {
	            return newStr;
	          }
	          newStr = delPointEndZero(newStr);
	        }
	      }
	    }
	    catch (Exception localException) {
	    }
	    return newStr;
	  }

	  public static String strToAscii(String str)
	  {
	    char[] chars = str.toCharArray();
	    String result = "";
	    for (int i = 0; i < chars.length; i++) {
	      result = result + chars[i];
	    }
	    return result;
	  }

	  public static String asciiToStr(String ascii)
	  {
	    String[] chars = ascii.split(" ");
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < chars.length; i++) {
	      sb.append((char)Integer.parseInt(chars[i]));
	    }
	    return sb.toString();
	  }

	  public static String getStrDigit(String str, int numb)
	  {
	    String subStr = "";
	    try {
	      if ((str != null) && (!str.equals("")) && (str.length() >= numb)) {
	        int start = str.length() - numb;
	        int end = start + 1;
	        subStr = str.substring(start, end);
	      }
	    } catch (Exception localException) {
	    }
	    return subStr;
	  }

	  public static String getStrDigit1(String str, int numb)
	  {
	    String subStr = "";
	    try {
	      if ((str != null) && (!str.equals(""))) {
	        int m = Integer.parseInt(str);
	        String binaryStr = Integer.toBinaryString(m);
	        subStr = getStrDigit(binaryStr, numb);
	      }
	    } catch (Exception localException) {
	    }
	    return subStr;
	  }

	  public static boolean RunNian(int year)
	  {
	    if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
	      return true;
	    }
	    return false;
	  }

	  public static boolean isDate(String date)
	  {
	    boolean result = false;
	    if (date.length() == 8)
	    {
	      try
	      {
	        int year = Integer.valueOf(date.substring(0, 4)).intValue();
	        if (year < 1990)
	          return false;
	        int month = Integer.valueOf(date.substring(4, 6)).intValue();
	        int day = Integer.valueOf(date.substring(6, 8)).intValue();
	        if ((month > 0) && (month <= 12))
	        {
	          if (((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) && 
	            (day > 0) && (day <= 31)) {
	            result = true;
	          } else if (((month == 4) || (month == 6) || (month == 9) || (month == 11)) && 
	            (day > 0) && (day <= 30)) {
	            result = true;
	          } else if (month == 2)
	          {
	            int days = 28;
	            if (RunNian(year))
	              days = 29;
	            if ((day > 0) && (day <= days)) {
	              result = true;
	            }
	          }
	        }
	      }
	      catch (Exception localException)
	      {
	      }
	    }

	    return result;
	  }

	  public static boolean isYearMonth(String ym)
	  {
	    boolean result = false;
	    if (ym.length() == 6)
	    {
	      try
	      {
	        int year = Integer.valueOf(ym.substring(0, 4)).intValue();
	        if (year < 1990)
	          return false;
	        int month = Integer.valueOf(ym.substring(4, 6)).intValue();
	        if ((month > 0) && (month <= 12)) {
	          result = true;
	        }
	      }
	      catch (Exception localException)
	      {
	      }
	    }
	    return result;
	  }

	  public static boolean isYear(String y)
	  {
	    boolean result = false;
	    if (y.length() == 6)
	      try {
	        int year = Integer.valueOf(y.substring(0, 4)).intValue();
	        if (year > 1990) result = true;
	      }
	      catch (Exception localException)
	      {
	      }
	    return result;
	  }

	  public static String getRandom(int numb)
	  {
	    String str = "";
	    try {
	      str = Math.round(Math.random() * Math.pow(10.0D, numb))+"";
	    } catch (Exception localException) {
	    }
	    return str;
	  }

	  

	

	  public static void main(String[] args)
	  {
	    String str = "商业智能平台";

	    System.out.println(str);
	    System.out.println(decode(str, 2));
	    str = "^5546^4e1a^667a^80fd^5e73^53f0";
	    str = "~5e5546~5e4e1a~5e667a~5e80fd~5e5e73~5e53f0";
	    str = "~7e5e5546~7e5e4e1a~7e5e667a~7e5e80fd~7e5e5e73~7e5e53f0";

	    String es = encode("策析商业智能软件", 3);

	    str = "2.0150921005E7";
	    BigDecimal bd = new BigDecimal(str);
	    str = "$234,345.98%";
	    System.out.println(delSpecialStr(str, "[$|,|%]", ""));
	    str = "A001002";
	    str = mySubstr(str, 1, 1);
	    System.out.println(es);
	  }
}

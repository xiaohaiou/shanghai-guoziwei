function notNumber(strValue) {
	if (strValue == "")
		return false;
	var pattern = /^\d*$/;
	if (pattern.test(strValue))
		return false;
	else
		return true;
}
/*
 * ================================================================== �Ƿ�Ϊ��
 * ==================================================================
 */
function isEmpty(strValue) {
	strValue = strValue.replace(/^\s+|\s+$/g, "");
	return (strValue == null || strValue == "");
}

/*
 * ================================================================== �Ƿ�Ϊ��������
 * ==================================================================
 */
function isPositive(strValue) {
	return isInt(strValue, "+") || isFloat(strValue, "+");
}

/*
 * ================================================================== �Ƿ�Ϊ��������
 * ==================================================================
 */
function isNegative(strValue) {
	return isInt(strValue, "-") || isFloat(strValue, "-");
}

/*
 * ==================================================================
 * �ж�objStr�Ƿ�Ϊ�Ϸ���Email��ַ
 * ==================================================================
 */
function isValidEmail(objStr) {
	if (objStr == "")
		return false;
	var reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
	objStr = objStr.toString();
	return (objStr.match(reg) != null);
}

/*
 * ==================================================================
 * �ж�objStr�Ƿ�Ϊ�Ϸ�������(yyyy-MM-dd)����(yy-MM-dd)
 * ==================================================================
 */
function isDate(objStr) {
	var reg = /^((\d{4})|(\d{2}))-(\d{1,2})-(\d{1,2})$/;

	if (trim(objStr) == "")
		return false;
	else
		objStr = objStr.toString();

	if (objStr.match(reg) == null)
		return false;

	var arrDate = objStr.split("-");

	var year = parseInt(arrDate[0], 10);
	var month = parseInt(arrDate[1], 10);
	var day = parseInt(arrDate[2], 10);

	if (year < 100)
		if (year < 50)
			year += 2000;
		else
			year += 1900;

	var date = new Date(year, month - 1, day);
	var y = parseInt(date.getYear(), 10);
	// ��������19**��Date.getYear()���ص���ĩ��λ������Ϊ��ݣ�����һ��Ҫ����1900������ȷ�����
	if (y < 100)
		y += 1900;

	if ((y == year && parseInt(date.getMonth(), 10) == month - 1 && parseInt(
			date.getDate(), 10) == day)
			&& year >= 1900 && year <= 2049)
		return true;
	else
		return false;
}

/*
 * ================================================================== (�����ַ�,
 * sign(+��-��empty)��zero(empty��1��true) �ж��Ƿ�Ϊ��������������������+0��������+0
 * ==================================================================
 */
function isInt(objStr, sign, zero) {
	var reg;
	var bolzero;

	if (trim(objStr) == "")
		return false;
	else
		objStr = objStr.toString();

	if ((sign == null) || (trim(sign) == ""))
		sign = "+-";

	if ((zero == null) || (trim(zero) == ""))
		bolzero = false;
	else {
		zero = zero.toString();
		bolzero = (zero == "true" || zero == "1");
	}

	switch (sign) {
		case "+-" :
			// ����
			reg = /(^-?|^\+?)\d+$/;
			break;
		case "+" :
			if (!bolzero)
				// ������
				reg = /^\+?[0-9]*[1-9][0-9]*$/;
			else {
				// ������+0
				// reg=/^\+?\d+$/;
				reg = /^\+?[0-9]*[0-9][0-9]*$/;
			}
			break;
		case "-" :
			if (!bolzero)
				// ������
				reg = /^-[0-9]*[1-9][0-9]*$/;
			else
				// ������+0
				// reg=/^-\d+$/;
				reg = /^-[0-9]*[0-9][0-9]*$/;
			break;
		default :
			alert("����Ų���ֻ��Ϊ(�ա�+��-)");
			return false;
			break;
	}

	return (objStr.match(reg) != null);
}

/*
 * isFloat2(objStr, iDigit):�ж�objStr�Ƿ�Ϊ��������С��λ��С�ڵ���vDigit
 */
function isFloat2(objStr, iDigit, num) {
	num = num ? ("{1," + num + "}") : "*"
	var strReg = "^(-?|\\+?)(\\d" + num + "?)(\\.\\d{1,";
	// var reg=/^(-?|\+?)(0|[1-9]\d*?)(\.\d{1,10})?$/;
	strReg += iDigit;
	strReg += "})?$";
	if (trim(objStr) == "")
		return false;
	else
		objStr = objStr.toString();

	var reg = new RegExp(strReg);
	return (objStr.match(reg) != null);
}

/*
 * ================================================================== (�����ַ�,
 * sign(+��-��empty)��zero(empty��1��true) �ж��Ƿ�Ϊ���������������������+0����������+0
 * ==================================================================
 */
function isFloat(objStr, sign, zero) {
	var reg;
	var bolzero;

	if (trim(objStr) == "")
		return false;
	else
		objStr = trim(objStr.toString());

	if ((sign == null) || (trim(sign) == ""))
		sign = "+-";

	if ((zero == null) || (trim(zero) == ""))
		bolzero = false;
	else {
		zero = zero.toString();
		bolzero = (zero == "true" || zero == "1");
	}

	switch (sign) {
		case "+-" :
			// ������
			reg = /^((-?|\+?)\d+)(\.\d+)?$/;
			break;
		case "+" :
			if (!bolzero) {
				// �����
				reg = /^\+?(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
			} else {
				// �����+0
				reg = /^\+?\d+(\.\d+)?$/;
			}
			break;
		case "-" :
			if (!bolzero) {
				// ��������
				reg = /^-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
			} else {
				// ��������+0
				reg = /^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;
			}
			break;
		default :
			alert("����Ų���ֻ��Ϊ(�ա�+��-)");
			return false;
			break;
	}

	return (objStr.match(reg) != null);
}
/*
 * ==================================================================
 * ��srcStr�������뵽nAfterDotλС��
 * ==================================================================
 */
function FormatNumber(srcStr, nAfterDot) {
	var srcStr, nAfterDot;
	var resultStr, nTen;
	srcStr = "" + srcStr + "";
	strLen = srcStr.length;
	dotPos = srcStr.indexOf(".", 0);
	if (dotPos == -1) {
		resultStr = srcStr + ".";
		for (i = 0; i < nAfterDot; i++) {
			resultStr = resultStr + "0";
		}
	} else {
		if ((strLen - dotPos - 1) >= nAfterDot) {
			nAfter = dotPos + nAfterDot + 1;
			nTen = 1;
			for (j = 0; j < nAfterDot; j++) {
				nTen = nTen * 10;
			}
			resultStr = Math.round(parseFloat(srcStr) * nTen) / nTen;
		} else {
			resultStr = srcStr;
			for (i = 0; i < (nAfterDot - strLen + dotPos + 1); i++) {
				resultStr = resultStr + "0";
			}

		}
	}
	return resultStr;

}

/*
 * ================================================================== �ַ��������
 * ==================================================================
 */
function regInput(obj, reg, inputStr) {
	/**
	 * �������֣�/^[0-9]*$/ ��2λС��/^\d*\.?\d{0,2}$/ СдӢ�ģ�/^[a-z]*$/ ��дӢ�ģ�/^[A-Z]*$/ ��
	 * �ڣ�/^\d{1,4}([-\/](\d{1,2}([-\/](\d{1,2})?)?)?)?$/ �������ģ�input�� /^$/ paste
	 * or drop�� /^[\u4E00-\u9FA5]*$/
	 */
	var docSel = document.selection.createRange()

	if (docSel.parentElement().tagName != "INPUT")
		return false
	oSel = docSel.duplicate()
	oSel.text = ""

	var srcRange = obj.createTextRange()

	oSel.setEndPoint("StartToStart", srcRange)

	var str = oSel.text + inputStr + srcRange.text.substr(oSel.text.length)

	return reg.test(str)
}

/*
 * ==================================================================
 * ֻ����������ʹ��ʱ��form�м���ýű�����,�������븺��
 * ==================================================================
 */
function enableAllIntOnly(obj) {
	var reg = /^(-?)[0-9]*$/
	obj.onkeypress = function() {
		return regInput(this, reg, String.fromCharCode(event.keyCode))
	}
	obj.onpaste = function() {
		return regInput(this, reg, window.clipboardData.getData('Text'))
	}
	obj.ondrop = function() {
		return regInput(this, reg, event.dataTransfer.getData('Text'))
	}
}

/*
 * ==================================================================
 * ģ���ѯʱ,����������'���
 * ==================================================================
 */
function enableQuery(obj) {
	var reg = /^[^']+$/
	obj.onkeypress = function() {
		return regInput(this, reg, String.fromCharCode(event.keyCode))
	}
	obj.onpaste = function() {
		return regInput(this, reg, window.clipboardData.getData('Text'))
	}
	obj.ondrop = function() {
		return regInput(this, reg, event.dataTransfer.getData('Text'))
	}
}

/*
 * ==================================================================
 * ֻ����������ʹ��ʱ��form�м���ýű�����
 * ==================================================================
 */
function enableIntOnly(obj) {
	var reg = /^[0-9]*$/
	obj.onkeypress = function() {
		return regInput(this, reg, String.fromCharCode(event.keyCode))
	}
	obj.onpaste = function() {
		return regInput(this, reg, window.clipboardData.getData('Text'))
	}
	obj.ondrop = function() {
		return regInput(this, reg, event.dataTransfer.getData('Text'))
	}
}

function enableNumber2Only(obj) {
	obj.onkeypress = function() {
		return regInput(this, /^\d*\.?\d{0,2}$/, String
						.fromCharCode(event.keyCode))
	}
	obj.onpaste = function() {
		return regInput(this, /^\d*\.?\d{0,2}$/, window.clipboardData
						.getData('Text'))
	}
	obj.ondrop = function() {
		return regInput(this, /^\d*\.?\d{0,2}$/, event.dataTransfer
						.getData('Text'))
	}
}

function enableAllNumber2Only(obj) {
	obj.onkeypress = function() {
		return regInput(this, /^(-?)\d*\.?\d{0,2}$/, String
						.fromCharCode(event.keyCode))
	}
	obj.onpaste = function() {
		return regInput(this, /^(-?)\d*\.?\d{0,2}$/, window.clipboardData
						.getData('Text'))
	}
	obj.ondrop = function() {
		return regInput(this, /^(-?)\d*\.?\d{0,2}$/, event.dataTransfer
						.getData('Text'))
	}
}

function enableNumber4Only(obj) {
	obj.onkeypress = function() {
		return regInput(this, /^\d*\.?\d{0,4}$/, String
						.fromCharCode(event.keyCode))
	}
	obj.onpaste = function() {
		return regInput(this, /^\d*\.?\d{0,4}$/, window.clipboardData
						.getData('Text'))
	}
	obj.ondrop = function() {
		return regInput(this, /^\d*\.?\d{0,4}$/, event.dataTransfer
						.getData('Text'))
	}
}
/**
 * ������������ӷָ���,
 */
function enableIntWithSplit(obj) {
	var reg = /^[0-9,]*$/
	obj.onkeypress = function() {
		return regInput(this, reg, String.fromCharCode(event.keyCode))
	}
	obj.onpaste = function() {
		return regInput(this, reg, window.clipboardData.getData('Text'))
	}
	obj.ondrop = function() {
		return regInput(this, reg, event.dataTransfer.getData('Text'))
	}
}

/**
 * �������븡����ӷָ���,
 */
function enableFloatWithSplit(obj, type) {
	if (type == null)
		reg = /^[0-9]*$/
	else if (type == 2)
		reg = /^[0-9,]*\.?\d{0,2}$/
	else if (type == 4)
		reg = /^[0-9,]*\.?\d{0,4}$/
	else if (type == 6)
		reg = /^[0-9,]*\.?\d{0,6}$/
	else
		return;
	obj.onkeypress = function() {
		if (event.keyCode != 13) {
			return regInput(this, reg, String.fromCharCode(event.keyCode))
		} else {
			if (typeof(textForward) != 'undefined') {
				textForward(obj);
			}
		}
	}
	obj.onpaste = function() {
		return regInput(this, reg, window.clipboardData.getData('Text'))
	}
	obj.ondrop = function() {
		return regInput(this, reg, event.dataTransfer.getData('Text'))
	}
}

/*
 * ==================================================================
 * ʹ��ʱ��form�м���ýű����ɣ�typeΪ��ʱ����ֻ���������� typeΪ2,4ʱ����ʾ2,4λС��Ϊ'a'��ʾСд��Ϊ'A'��ʾ��д��Ϊ
 * 'date'��ʾֻ����������
 * ==================================================================
 */
function enableOnly(obj, type) {
	var reg;
	if (type == null)
		reg = /^[0-9]*$/
	else if (type == 2)
		reg = /^\d*\.?\d{0,2}$/
	else if (type == 4)
		reg = /^\d*\.?\d{0,4}$/
	else if (type == 'a')
		reg = /^[a-z]*$/
	else if (type == 'A')
		reg = /^[A-Z]*$/
	else if (type == 'date')
		reg = /^\d{1,4}([-\/](\d{1,2}([-\/](\d{1,2})?)?)?)?$/
	else
		return;
	obj.onkeypress = function() {

		if (event.keyCode != 13) {
			return regInput(this, reg, String.fromCharCode(event.keyCode))
		} else {
			if (typeof(textForward) != 'undefined') {
				textForward(obj);
			}
		}
	}
	obj.onpaste = function() {
		return regInput(this, reg, window.clipboardData.getData('Text'))
	}
	obj.ondrop = function() {
		return regInput(this, reg, event.dataTransfer.getData('Text'))
	}
}

/**
 * �Ƚ�����ʱ��
 * @param {} d1
 * @param {} d2
 * @return {} ǰ�ߴ󷵻�1�����ߴ󷵻�-1����ͬ����0����ʱ�䷵��false
 */
function compareDate(d1, d2) {
	var reg = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;
	if (reg.test(d1) && reg.test(d2)) {
		var day1 = d1.split("-"), day2 = d2.split("-"), date1 = new Date(), date2 = new Date();
		date1.setFullYear(parseInt(day1[0], 10), parseInt(day1[1], 10) - 1,
				parseInt(day1[2], 10));
		date2.setFullYear(parseInt(day2[0], 10), parseInt(day2[1], 10) - 1,
				parseInt(day2[2], 10));
		return date1 > date2 ? 1 : (date1 < date2 ? -1 : 0);
	} else {
		return NaN;
	}
}

//字符串转换为时间戳
function getDateTimeStamp (dateStr) {
    return Date.parse(dateStr.replace(/-/gi,"/"));
}

function getDateDiff (dateStr) {
    var publishTime = getDateTimeStamp(dateStr)/1000,
        d_seconds,
        d_minutes,
        d_hours,
        d_days,
        timeNow = parseInt(new Date().getTime()/1000),
        d,

        date = new Date(publishTime*1000),
        Y = date.getFullYear(),
        M = date.getMonth() + 1,
        D = date.getDate(),
        H = date.getHours(),
        m = date.getMinutes(),
        s = date.getSeconds();
        //小于10的在前面补0
        if (M < 10) {
            M = '0' + M;
        }
        if (D < 10) {
            D = '0' + D;
        }
        if (H < 10) {
            H = '0' + H;
        }
        if (m < 10) {
            m = '0' + m;
        }
        if (s < 10) {
            s = '0' + s;
        }

    d = timeNow - publishTime;
    d_days = parseInt(d/86400);
    d_hours = parseInt(d/3600);
    d_minutes = parseInt(d/60);
    d_seconds = parseInt(d);

    if(d_days > 0 && d_days < 30){
        return d_days + '天前';
    }else if(d_days <= 0 && d_hours > 0){
        return d_hours + '小时前';
    }else if(d_hours <= 0 && d_minutes > 0){
        return d_minutes + '分钟前';
    }else if (d_seconds < 60) {
        if (d_seconds <= 0) {
            return '刚刚';
        }else {
            return d_seconds + '秒前';
        }
    }else if (d_days >= 30) {
        return Y + '-' + M + '-' + D + ' ' + H + ':' + m + ':' + s;
    }
}


//格式化日期：yyyy-MM-dd　　 

function formatDate(date){
　 var myyear = date.getFullYear();
　 var mymonth = date.getMonth()+1;
　 var myweekday = date.getDate();
　 if(mymonth < 10){
　　　 mymonth = "0" + mymonth;
 }
　 if(myweekday < 10){
　　　 myweekday = "0" + myweekday;
 }
　 return (myyear+"-"+mymonth + "-" + myweekday);
}
　 
//这个方法将取得某年(year)第几周(weeks)的星期几(weekDay)的日期   
function getXDate(year,weeks,weekDay){   
    // 用指定的年构造一个日期对象，并将日期设置成这个年的1月1日   
    // 因为计算机中的月份是从0开始的,所以有如下的构造方法   
    var date = new Date(year,"0","1");   
    // 取得这个日期对象 date 的长整形时间 time   
    var time = date.getTime();   
    // 将这个长整形时间加上第N周的时间偏移   
    // 因为第一周就是当前周,所以有:weeks-1,以此类推   
    // 7*24*3600000 是一星期的时间毫秒数,(JS中的日期精确到毫秒)   
    time+=(weeks-2)*7*24*3600000;   
    // 为日期对象 date 重新设置成时间 time   
    date.setTime(time); 
    console.log(date);
    return getNextDate(date,weekDay);   
} 　　 
//这个方法将取得 某日期(nowDate) 所在周的星期几(weekDay)的日期   
function getNextDate(nowDate,weekDay){   
    // 0是星期日,1是星期一,...   
    weekDay%=7;   
    var day = nowDate.getDay();   
    var time = nowDate.getTime();   
    var sub = weekDay-day;   
    if(sub <= 0){   
        sub += 7;   
    }   
    time+=sub*24*3600000;   
    nowDate.setTime(time);   
    return nowDate;   
}  
　 
//获得某周的开始日期　　 
function getWeekStartDate(nowDate) {　
  var paraYear = nowDate.getFullYear();
  var paraMonth = nowDate.getMonth();
  var paraDay = nowDate.getDate();
  var paraDayOfWeek =nowDate.getDay(); 
　 var weekStartDate = new Date(paraYear, paraMonth, paraDay + 1 - paraDayOfWeek);　　　 
　 return formatDate(weekStartDate);　　 
}　　　 
　　 
//获得某周的结束日期　　 
function getWeekEndDate(nowDate) {
  var paraYear = nowDate.getFullYear();
  var paraMonth = nowDate.getMonth();
  var paraDay = nowDate.getDate();
  var paraDayOfWeek =nowDate.getDay(); 
　 var weekEndDate = new Date(paraYear, paraMonth, paraDay + (7 - paraDayOfWeek));　　　 
　 return formatDate(weekEndDate);
}

/**
 * 判断年份是否为润年
 *
 * @param {Number} year
 */
function isLeapYear(year) {
    return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
}
/**
 * 获取某一年份的某一月份的天数
 *
 * @param {Number} year
 * @param {Number} month
 */
function getMonthDays(year, month) {
    return [31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month] || (isLeapYear(year) ? 29 : 28);
}
/**
 * 获取某年的某天是第几周
 * @param {Number} y
 * @param {Number} m
 * @param {Number} d
 * @returns {Number}
 */
function getWeekNumber(y, m, d) {
    var now = new Date(y, m - 1, d),
        year = now.getFullYear(),
        month = now.getMonth(),
        days = now.getDate();
    //那一天是那一年中的第多少天
    for (var i = 0; i < month; i++) {
        days += getMonthDays(year, i);
    }
    //那一年第一天是星期几
    var yearFirstDay = new Date(year, 0, 1).getDay() || 7;
    console.log(yearFirstDay);
    var week = null;
    if (yearFirstDay == 1) {
        week = Math.ceil(days / 7);
    } else {
        days -= (7 - yearFirstDay + 1);
        week = Math.ceil(days / 7) + 1;
    }

    return week;
}
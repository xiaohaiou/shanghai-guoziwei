<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<form:form id="contractMonthform"  >
		<div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ requestScope.contractMonth.year }-${  requestScope.contractMonth.month }</label>
				<label class="w20">所属单位:</label>
				<label class="w25 setleft">${  requestScope.contractMonth.compName}</label>
				<label class="w20">信息总量:</label>
				<label class="w25 setleft">${  requestScope.contractMonth.totalContract}</label>
				<label class="w20">信息类型:</label>
				<label class="w25 setleft">${  requestScope.contractMonth.totalMoney}</label>
				<label class="w20">信息创建人:</label>
				<label class="w25 setleft">${  requestScope.contractMonth.createPersonName}</label>
			
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${  requestScope.contractMonth.createDate}</label>
				
				<label class="w20">信息上报人:</label>
				<label class="w25 setleft">${  requestScope.contractMonth.reportPersonName}</label>
				<label class="w20">上报时间:</label>
				<label class="w25 setleft">${  requestScope.contractMonth.reportDate}</label>
			</div>
	</form:form>
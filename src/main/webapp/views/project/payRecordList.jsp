<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<a class="model_btn_plus2" id="addPayRecord" onclick="mload('${pageContext.request.contextPath}/project/payRecord/_addPayRecord?pcId=${pcId}')">+</a>
	<table>
		<tr class="table_header" id="payRecordTableTr">
			<th style="width:5%">序号</th>
			<th style="width:10%">付款期次</th>
			<th style="width:10%">已支付款项</th>
			<th style="width:10%">付款比</th>
			<th style="width:15%">付款日期</th>
			<th style="width:40%">备注</th>
			<th style="width:10%">操作</th>
		</tr>
	
		<c:if test="${not empty payRecords}">
			<c:forEach items="${payRecords}" var="py" varStatus="status">
				<tr id="payRecord${py.id}">
					<td>${status.count}</td>
					<td>${py.payCount}</td>
					<td style="text-align:right;"><fmt:formatNumber type="number" value="${py.paidMoney}" pattern="0.0000" maxFractionDigits="4"/>${py.pmUnit }</td>
					<td style="text-align:right;"><fmt:formatNumber  value="${py.payPercent/100}" pattern="##0%"></fmt:formatNumber></td>
					<td>${py.planPayDate}</td>
					<td style="word-wrap:break-word;text-align:left;">${py.remark}</td>
					<td>
						<input type="hidden" name="payRecordIds" value="${py.id}"/>
						<a class="btn btn-primary model_btn_ok" onclick="mload('${pageContext.request.contextPath}/project/payRecord/_modifyPayRecord?id=${py.id}')">编辑</a>
						<a class="btn btn-primary model_btn_ok" onclick="del('${py.id}')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</div>	
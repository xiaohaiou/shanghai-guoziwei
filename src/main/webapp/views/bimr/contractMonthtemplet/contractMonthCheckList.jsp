<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h3><span class="glyphicon glyphicon-pencil"></span>审核意见列表</h3>
<div class="tablebox" >
	<table>
		<tr class="table_header">
			<th style="width:3%">序号</th>
			<th style="width:10%">审核人</th>
			<th style="width:15%">审核时间</th>
			<th style="width:72%">审核意见</th>
		</tr>
		<c:forEach items="${ requestScope.contractMonthCheckList}" var="l" varStatus="status">
		<tr>
			<td style="text-align: center;">${status.index + 1 }</td>
			<td style="text-align: left;">${l.createPersonName }</td>
			<td style="text-align: center;">${l.createDate }</td>
			<td style="text-align: left;">
			<c:if test="${l.examresult == 0}">退回。</c:if>
			<c:if test="${l.examresult == 1}">通过。</c:if>
			${l.examinestr }
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
	

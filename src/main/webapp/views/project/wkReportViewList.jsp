<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<table>
		<tr class="table_header" id="wkReportTableTr">
			<c:if test="${view == 'report'}">
				<th></th>
			</c:if>
			<th>序号</th>
			<th>周报标题</th>
			<th>年份</th>
			<th>周数</th>
			<th>日期范围</th>
			<th>周报附件</th>
			<th>操作</th>
		</tr>
		<c:if test="${not empty msgPage && not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="v" varStatus="status">
				<tr>
					<c:if test="${view == 'report'}">
						<td><c:if test="${v.reportStatus == 0 || v.reportStatus == 1  }">新</c:if></td>
					</c:if>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td style="text-align: left;">${v.wrTitle }</td>
					<td style="text-align:right;">${v.wrYear }</td>
					<td style="text-align:right;">${v.wrWeek }</td>
					<td>${v.wrDatetime}</td>
					<td><a href="${pageContext.request.contextPath}/${v.wrLine}" target="_blank">查看周报PDF</a></td>
					<td>
						<a class="btn btn-primary model_btn_ok" onclick="viewWrValidate('${v.id}')">查看</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage || empty requestScope.msgPage.list}">
			<tr>
				<td colspan="7" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty msgPage && not empty msgPage.list }">
		<jsp:include page="page.jsp"/>
	</c:if>
</div>	
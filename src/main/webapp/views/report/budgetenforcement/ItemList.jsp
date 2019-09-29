<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<table >
		<tr class="table_header" id="nodeTableTr">
			<th>序号</th>
			<th>公司名称</th>
			<th>报表标题</th>
			<th>报表状态</th>
	
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="sys" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td>${sys.organalname }</td>
					<td>${sys.time }${sys.title }</td>
					<td style="text-align: center">
						<c:if test="${ sys.examinestatus==50112}">
							<span style="color:#3366ff">${sys.examinestatusName }</span>
						</c:if>
						<c:if test="${ sys.examinestatus==50113}">
							<span style="color:#ff9933">${sys.examinestatusName }</span>
						</c:if>
						<c:if test="${ sys.examinestatus==50114}">
							<span style="color:#ff399d">${sys.examinestatusName }</span>
						</c:if>
						<c:if test="${ sys.examinestatus==50115}">
							<span style="color:#00cc66">${sys.examinestatusName }</span>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="4" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="page.jsp"/>
	</c:if>
</div>	
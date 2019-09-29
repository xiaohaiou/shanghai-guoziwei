<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<table >
		<tr class="table_header">
			<th>序号</th>
			<th>更新时间</th>
			<th>更新人</th>
			<th>项目进展情况</th>
		</tr>
		<c:if test="${not empty requestScope.msgPage.list }">
			<c:forEach items="${requestScope.msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(requestScope.msgPage.pageNum-1)*10+status.count }</td>
					<td style="text-align:center">
						${node.updateDate }
					</td>
					<td style="text-align:center;">
						${node.updatePerson }
					</td>
					<td style="text-align:left;max-width:30em;">
						${node.updateDetail }
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="4" align="center">
					无更新日志
				</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="../page.jsp"/>
	</c:if>
</div>	
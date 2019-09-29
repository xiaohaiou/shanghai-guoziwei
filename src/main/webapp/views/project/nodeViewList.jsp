<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<table >
		<tr class="table_header" id="nodeTableTr">
			<c:if test="${view == 'report'}">
				<th></th>
			</c:if>
			<th>序号</th>
			<th>节点排序</th>
			<th>节点名称</th>
			<th>计划完成日期</th>
			<th>节点进度</th>
			<th>最近更新时间</th>
			<th>操作</th>
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="node" varStatus="status">
				<tr>
					<c:if test="${view == 'report'}">
						<td><c:if test="${node.reportStatus == 0 || node.reportStatus == 1  }">新</c:if></td>
					</c:if>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td>${node.pnOrder }</td>
					<td style="text-align:left;">${node.pnName }</td>
					<td>${node.pnCompletionTime }</td>
					<td style="text-align:right;"><fmt:formatNumber  value="${node.pnProgress/100}" pattern="##0%"></fmt:formatNumber></td>
					<td>${node.lastModifyDate }</td>
					<td>
						<a class="btn btn-primary model_btn_ok" onclick="viewNodeValidate('${node.id}')">查看</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="7" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="page.jsp"/>
	</c:if>
</div>	
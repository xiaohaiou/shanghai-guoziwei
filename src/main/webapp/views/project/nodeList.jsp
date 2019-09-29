<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<c:if test="${!(view eq 'view')}">
		<a class="model_btn_plus2" id="addNode" onclick="mload('${pageContext.request.contextPath}/project/node/_addNode?pjId=${pjId}')">+</a>
	</c:if>
	<table >
		<tr class="table_header" id="nodeTableTr">
			<th>序号</th>
			<th>节点名称</th>
			<th>计划完成时间</th>
			<th>节点进度</th>
			<th>节点状态</th>
			<th>操作</th>
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td style="text-align:left;">${node.pnName }</td>
					<td>${node.pnCompletionTime }</td>
					<td style="text-align:right;"><fmt:formatNumber  value="${node.pnProgress/100}" pattern="#,##0%"></fmt:formatNumber></td>
					<td>
						<c:if test="${ node.reportStatus == 0}">
							<span style="color:#3366ff">未上报</span>
						</c:if>
						<c:if test="${ node.reportStatus==1}">
							<span style="color:#ff9933">待审核</span>
						</c:if>
						<c:if test="${ node.reportStatus==2}">
							<span style="color:#00cc66">已审核</span>
						</c:if>
						<c:if test="${ node.reportStatus==3}">
							<span style="color:#ff399d">已退回</span>
						</c:if>
					</td>
					<td>
						<c:if test="${!(view eq 'view')}">
							<a class="btn btn-primary model_btn_ok" onclick="editNodeValidate('${node.id}','${pjId}')">修改</a>
							<a class="btn btn-primary model_btn_ok" onclick="del('${node.id}','node')">删除</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="6" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="page.jsp"/>
	</c:if>
</div>	
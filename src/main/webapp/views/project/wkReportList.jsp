<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<a class="model_btn_plus2" id="addwkReport" onclick="mload('${pageContext.request.contextPath}/project/wkReport/_addWkReport?pjId=${pjId}')">+</a>
	<table>
		<tr class="table_header" id="wkReportTableTr">
			<th>序号</th>
			<th>周报标题</th>
			<th>年份</th>
			<th>周数</th>
			<th>日期范围</th>
			<th>周报附件</th>
			<th>周报状态</th>
			<th>操作</th>
		</tr>
		<c:if test="${not empty msgPage && not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="v" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td style="text-align:left;">${v.wrTitle }</td>
					<td style="text-align:right;">${v.wrYear }</td>
					<td style="text-align:right;">${v.wrWeek }</td>
					<td>${v.wrDatetime}</td>
					<td><a href="${pageContext.request.contextPath}/${v.wrLine}" target="_blank">查看周报PDF</a></td>
					<td>
						<c:if test="${v.reportStatus==0}">
							<span style="color:#3366ff">未上报</span>
						</c:if>
						<c:if test="${ v.reportStatus==1}">
							<span style="color:#ff9933">待审核</span>
						</c:if>
						<c:if test="${ v.reportStatus==2}">
							<span style="color:#00cc66">已审核</span>
						</c:if>
						<c:if test="${ v.reportStatus==3}">
							<span style="color:#ff399d">已退回</span>
						</c:if>
					</td>
					<td>
						<a class="btn btn-primary model_btn_ok" onclick="editWrValidate('${v.id}')">修改</a>
						<a class="btn btn-primary model_btn_ok" onclick="del('${v.id}','wkReport')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage || empty requestScope.msgPage.list}">
			<tr>
				<td colspan="8" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty msgPage && not empty msgPage.list }">
		<jsp:include page="page.jsp"/>
	</c:if>
</div>	
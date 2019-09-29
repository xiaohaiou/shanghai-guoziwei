<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	
	<table style="width:100%;table-layout: fixed;">
		<tr class="table_header">
			<th >序号</th>
			<th >审计时间</th>
			<th >审计项目名称</th>
			<th >项目类型</th>
			<th >审计实施单位</th>
			<th >操作</th>
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="auditProject" varStatus="status">
				<tr>
				<td style="text-align: center;">${status.index + 1 }</td>
				<td style="text-align: center;">${auditProject.auditStartDate}~${auditProject.auditEndDate}</td>
				<td style="text-align: left;">${auditProject.auditProjectName}</td>
				<td style="text-align: left;">
				<c:if test="${auditProject.isImportant == 0}">
						年度计划内项目
					</c:if>
					<c:if test="${auditProject.isImportant == 1}">
						年度计划外项目
					</c:if>
					<c:if test="${auditProject.isImportant == 2}">
						上级交办项目
					</c:if>
				</td>
				<td style="text-align: center;">${auditProject.auditImplDeptName }</td>
				
				<td style="text-align: center;">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_qyfkda_show,')==true}">
				      <a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/inside/view?type=main&id=${auditProject.id}')">查看</a>
				</c:if>
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
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox" style="overflow-x: auto">
	<style>
		td{position:relative}
	</style>
	<table style="width: 100%">
		<tr class="table_header" id="reportOverseasLiabilitiesDetailTableTr">
			<th>序号</th>
			<th>境外负债币种</th>
			<th>境外负债金额(亿元)</th>
			<th>操作</th>
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*5+status.count }</td>
					<td>
						<select id="overseasCurrency" onchange="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportOverseasLiabilitiesDetail',this)">
							<c:forEach items="${currencyKind }" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id == node.overseasCurrency }">selected</c:if>>${sys.description }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="text" id="overseasLiabilitiesAmounts" value="${node.overseasLiabilitiesAmounts }" check="NotEmpty_double_16_4_0+_。_." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportOverseasLiabilitiesDetail',this)">
					</td>
					<td>
						<a class="btn btn-primary model_btn_ok" onclick="del('${(msgPage.pageNum-1)*5+status.count }','reportOverseasLiabilitiesDetail')">删除</a>
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
	
</div>
<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="../page.jsp"/>
	</c:if>
<script type="text/javascript" src="<c:url value="/js/vailds.js"/>" charset="utf-8"></script>
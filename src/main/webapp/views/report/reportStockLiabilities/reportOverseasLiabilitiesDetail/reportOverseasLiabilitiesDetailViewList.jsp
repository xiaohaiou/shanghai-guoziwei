<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<table >
		<tr class="table_header" id="reportOverseasLiabilitiesDetailTableTr">
			<th>序号</th>
			<th>境外负债币种</th>
			<th>境外负债金额</th>
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*5+status.count }</td>
					<td style="text-align:left;">
						${node.overseasCurrencyName }
					</td>
					<td style="text-align:right;">
						<fmt:formatNumber value="${node.overseasLiabilitiesAmounts }"  pattern="#,##0.0000" />亿元
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="3" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="../page.jsp"/>
	</c:if>
</div>	
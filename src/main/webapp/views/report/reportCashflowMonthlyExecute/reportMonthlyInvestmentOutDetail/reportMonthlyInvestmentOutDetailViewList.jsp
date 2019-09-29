<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<table >
		<tr class="table_header" id="reportInvestmentOutTableTr">
			<th>序号</th>
			<!-- <th>成员公司</th>
			<th>所属核心企业</th> -->
			<th>支付项目名称</th>
			<th>支付金额</th>
			<th>支付日期</th>
			<th>支付币种</th>
			<th>专项资金保障方案</th>
			<th>投资类型</th>
		</tr>
		<c:if test="${not empty requestScope.msgPage.list }">
			<c:forEach items="${requestScope.msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(requestScope.msgPage.pageNum-1)*5+status.count }</td>
					<%-- <td style="text-align:left;">${node.memberCompName }</td>
					<td style="text-align:left;">${node.coreCompName }</td> --%>
					<td style="text-align:left;">${node.payProjectName }</td>
					<td style="text-align:right;">
						<fmt:formatNumber value="${node.payAmount }"  pattern="#,##0.00" />万元
					</td>
					<td style="text-align:center;">
						${node.payDate }
					</td>
					<td style="text-align:left;">
						${node.currencyName }
					</td>
					<td style="text-align:left;">
						${node.specialFundSupportPlan }
					</td>
					<td style="text-align:left;">
						${node.investTypeName }
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
		<jsp:include page="../page.jsp"/>
	</c:if>
</div>	
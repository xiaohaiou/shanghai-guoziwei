<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<table >
		<tr class="table_header" id="reportFinancingOutDetailTableTr">
			<th>序号</th>
			<!-- <th>成员公司</th>
			<th>所属核心企业</th> -->
			<th>承贷主体</th>
			<th>还贷银行</th>
			<th>融资类型</th>
			<th>融资类型明细</th>
			<th>还款日期</th>
			<th>还款金额</th>
			<th>币种</th>
		</tr>
		<c:if test="${not empty requestScope.msgPage.list }">
			<c:forEach items="${requestScope.msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(requestScope.msgPage.pageNum-1)*5+status.count }</td>
					<%-- <td style="text-align:left;">${node.memberCompName }</td>
					<td style="text-align:left;">${node.coreCompName }</td> --%>
					<td style="text-align:left;">${node.loanCompName }</td>
					<td style="text-align:left;">${node.repayBank }</td>
					<td style="text-align:left;">
						${node.financingTypeName }
					</td>
					<td style="text-align:left;">
						${node.financingTypeDetailName }
					</td>
					<td style="text-align:center;">
						${node.repayDate }
					</td>
					<td style="text-align:right;">
						<fmt:formatNumber value="${node.repayAmount }"  pattern="#,##0.00" />万元
					</td>
					<td style="text-align:left;">
						${node.currencyName }
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="8" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="../page.jsp"/>
	</c:if>
</div>	
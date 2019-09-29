<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<table >
		<tr class="table_header" id="reportFinancingIntoDetailTableTr">
			<th>序号</th>
			<!-- <th>成员公司</th>
			<th>所属核心企业</th> -->
			<th>承贷主体</th>
			<th>贷款银行</th>
			<th>贷款金额</th>
			<th>下账时间</th>
			<th>到期时间</th>
			<th>贷款期限</th>
			<th>融资下账成本</th>
			<th>新增或续作</th>
			<th>贷款类型</th>
		</tr>
		<c:if test="${not empty requestScope.msgPage.list }">
			<c:forEach items="${requestScope.msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(requestScope.msgPage.pageNum-1)*5+status.count }</td>
					<%-- <td style="text-align:left;">${node.memberCompName }</td>
					<td style="text-align:left;">${node.coreCompName }</td> --%>
					<td style="text-align:left;">${node.loanCompName }</td>
					<td style="text-align:left;">${node.loanBank }</td>
					<td style="text-align:right;">
						<fmt:formatNumber value="${node.loanAmount }"  pattern="#,##0.00" />万元
					</td>
					<td style="text-align:center">
						${node.accountDate }
					</td>
					<td style="text-align:center;">
						${node.dueDate }
					</td>
					<td style="text-align:left;">
						${node.lengthOfMaturity }
					</td>
					<td style="text-align:right;">
						<fmt:formatNumber value="${node.financingAccountCost }"  pattern="#,##0.00" />%
					</td>
					<td style="text-align:left;">
						${node.typeName }
					</td>
					<td style="text-align:left;">
						${node.loanTypeName }
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="10" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="../page.jsp"/>
	</c:if>
</div>	
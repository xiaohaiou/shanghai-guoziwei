<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="tablebox" style="overflow-x: auto">
	<table style="width: 450%">
		<tr class="table_header" id="nodeTableTr">
			<th>序号</th>
			<th>所属核心企业</th>
			<th>债权单位</th>
			<th>欠款单位</th>
			<th>欠款类型</th>
			<th>具体事由</th>
			<th>欠款金额</th>
			<th>其中超期欠款金额</th>
			<th>欠款时间</th>
			<th>是否超期</th>
			<th>已超期时限（月）</th>
			<th>是否催收</th>
			<th>未催收原因</th>
			<th>回款计划</th>
			<th>是否进入法律程序</th>
			<th>本周回收金额</th>
			<th>累计收回金额</th>
			<th>预计坏账</th>
			<th>催收责任人</th>
			<th>进展描述</th>
			<th>备注</th>
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td style="text-align:center;">${node.coreorgname }</td>
					<td style="text-align:center;">${node.orgname }</td>
					<td style="text-align:center;max-width:20em;">${node.debtOrgname }</td>
					<td style="text-align:center;">${node.debtTypeName }</td>
					<td>${node.jtsy }</td>
					<td style="text-align:center;">
						<fmt:formatNumber value="${node.loanMoney}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
						<fmt:formatNumber value="${node.cqLoanMoney}"  pattern="#,##0.00" />
					</td>
					<td>${node.loanTime }</td>
					<td>
						<c:if test="${node.isOvertime == 1}">是</c:if>
						<c:if test="${node.isOvertime == 0}">否</c:if>
					</td>
					<td>${node.overtimeMonth }</td>
					<td>
						<c:if test="${node.isCuishou == 1}">是</c:if>
						<c:if test="${node.isCuishou == 0}">否</c:if>
					</td>
					<td>${node.noCuishoureason }</td>
					<td>${node.hkjh }</td>
					<td>
						<c:if test="${node.isInlaw == 1}">是</c:if>
						<c:if test="${node.isInlaw == 0}">否</c:if>
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.weekBackMoney}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.totalBackMoney}"  pattern="#,##0.00" />
					</td>
					<td>${node.yjhz }</td>
					<td>${node.cuishouPerson }</td>
					<td>${node.jzDescription }</td>
					<td>${node.remark }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="21" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	
</div>	
<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="page1.jsp"/>
	</c:if>
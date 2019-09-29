<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="tablebox" style="overflow-x: auto">
	<table style="width: 150em">
		<tr class="table_header" id="nodeTableTr">
			<th>序号</th>
			<th>所属核心企业</th>
			<th>公司名称</th>
			<th>欠款人员工编号</th>
			<th>欠款人</th>
			<th>欠款类别</th>
			<th>欠款类型</th>
			<th>具体事由</th>
			<th>欠款金额(万元)</th>
			<th>欠款时间</th>
			<th>已借款期限(月)</th>
			<th>是否催收</th>
			<th>是否进入法律程序</th>
			<th>本周回收金额(万元)</th>
			<th>累计回收金额(万元)</th>
			<th>预计坏账</th>
			<th>催收责任人</th>
			<th>进展描述</th>
			<th>备注</th>
			<th>借款公文号</th>
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td style="text-align:center;">${node.coreorgname }</td>
					<td style="text-align:center;">${node.orgname }</td>
					<td style="text-align:center;">${node.respersonAccount }</td>
					<td style="text-align:center;">${node.responsibleperson }</td>
					<td style="text-align:center;">${node.loanType1Name }</td>
					<td style="text-align:center;">${node.loanType2Name }</td>
					<td style="text-align:center;max-width:20em;">${node.useway }</td>
					<td style="text-align:center;">
						<fmt:formatNumber value="${node.loanmoney}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">${node.loantime }</td>
					
					<td style="text-align:center;">${node.loanmonth }</td>
					<td style="text-align:center;">
						<c:if test="${node.isCuishou == 1 }">是</c:if>
						<c:if test="${node.isCuishou == 0 }">否</c:if>
					</td>
					<td style="text-align:center;">
						<c:if test="${node.isInLaw == 1 }">是</c:if>
						<c:if test="${node.isInLaw == 0 }">否</c:if>
					</td>
					<td style="text-align:center;">
						<fmt:formatNumber value="${node.weekBackMoney }"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
						<fmt:formatNumber value="${node.totalBackMoney }"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">${node.yjhz }</td>
					<td style="text-align:center;">${node.cszrr }</td>
					<td style="text-align:center;">${node.zjms }</td>
					<td style="text-align:center;max-width:20em;">${node.remark }</td>
					<td style="text-align:center;">${node.loannum }</td>
					
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="20" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	
</div>	
<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="page.jsp"/>
	</c:if>
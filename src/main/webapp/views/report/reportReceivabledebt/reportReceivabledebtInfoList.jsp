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
			<c:if test="${type == 0}">
				<th>对方公司全称（内部）</th>
			</c:if>
			<c:if test="${type == 1}">
				<th>对方公司全称（外部）</th>
			</c:if>
			<th>催收责任人</th>
			<th>分管领导</th>
			<th>入帐科目</th>
			<th>期初余额</th>
			<th>期初信用期内余额</th>
			<th>期初超期1-30天内余额</th>
			<th>期初超期31-60天内余额</th>
			<th>期初超期61-90天内余额</th>
			<th>期初超期91天以上余额</th>
			<th>本月新增额</th>
			<th>本月收回信用期内金额</th>
			<th>本月收回超期内金额</th>
			<th>期末余额</th>
			<th>期末信用期内余额</th>
			<th>期末超期1-30天内余额</th>
			<th>期末超期31-60天内余额</th>
			<th>期末超期61-90天内余额</th>
			<th>期末超期91天-1年内余额</th>
			<th>期末超期1-2年内余额</th>
			<th>期末超期2-3年内余额</th>
			<th>期末超期3-5年内余额</th>
			<th>期末超期5年以上余额</th>
			<th>产生原因/备注</th>
			<th>债权所属类别</th>
			<th>若债权所属类别为保证金，则列出保证金起始期限</th>
			<th>催收措施</th>
			<th>预计收回或账务核销时间</th>
			<th>原始资料是否收集完毕</th>
			<th>催收函是否发出</th>
			<th>催收函是否有回函/还款承诺</th>
			<th>是否已经报案件移交公文</th>
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td style="text-align:center;">${node.oppositeorgname }</td>
					<td style="text-align:center;">${node.collectionperson }</td>
					
					<td style="text-align:center;max-width:20em;">${node.leadership }</td>
					<td style="text-align:center;">${node.accountingsubject }</td>
					
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.beginningbalance}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.beginningcreditperiodbalance}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.beginningbalancethirtydays}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.beginningbalancesixtydays}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.beginningbalanceninetydays}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.beginningbalanceoverdays}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.monthnewaccout}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.monthreturnaccout}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.momthreturnoveraccout}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.endingbalance}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.endingcreditperiodbalance}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.endingbalancethirtydays}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.endingbalancesixtydays}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.endingbalanceninetydays}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.endingbalanceoverdays}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.endingbalancetwoyears}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.endingbalancethreeyears}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.endingbalancefiveyears}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">
					<fmt:formatNumber value="${node.endingbalanceoveryears}"  pattern="#,##0.00" />
					</td>
					<td style="text-align:center;">${node.reasonorremarks }</td>
					<td style="text-align:center;">${node.debttype }</td>
					<td style="text-align:center;">${node.cashdepositdeadline }</td>
					<td style="text-align:center;">${node.collectionmeasures }</td>
					<td style="text-align:center;">${node.planreturntime }</td>
					<td style="text-align:center;">${node.isFinish }</td>
					<td style="text-align:center;">${node.isSend }</td>
					<td style="text-align:center;max-width:10em;">${node.isPromise }</td>
					<td style="text-align:center;max-width:10em;">${node.isTurnover }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="33" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	
</div>	
<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="page1.jsp"/>
	</c:if>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<table>
		<tr class="table_header" id="contractTableTr">
			<c:if test="${view == 'report'}">
				<th></th>
			</c:if>
			<th style="width:5%">序号</th>
			<th style="width:25%">合同名称</th>
			<th style="width:15%">合同主体甲方</th>
			<th style="width:15%">合同主体乙方</th>
			<th style="width:10%">签订日期</th>
			<th style="width:10%">标的金额</th>
			<th style="width:10%">已支付金额</th>
			<th style="width:10%">操作</th>
		</tr>
		<c:if test="${not empty msgPage && not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="v" varStatus="status">
				<tr>
					<c:if test="${view == 'report'}">
						<td><c:if test="${v.reportStatus == 0 || v.reportStatus == 1  }">新</c:if></td>
					</c:if>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td style="word-wrap:break-word; text-align: left;">${v.pcName }</td>
					<td style="text-align:left;">${v.pcBodyA }</td>
					<td style="text-align:left;">${v.pcBodyB}</td>
					<td>${v.pcSignDate}</td>
					<td style="text-align:right;"><fmt:formatNumber type="number" value="${v.pcBdMoney}" pattern="0.0000" maxFractionDigits="4"/>${v.bcUnit }</td>
					<td style="text-align:right;"><fmt:formatNumber type="number" value="${v.pcPaidMoney}" pattern="0.0000" maxFractionDigits="4"/>${v.paidUnit }</td>
					<td>
						<a class="btn btn-primary model_btn_ok" onclick="viewContractValidate('${v.id}')">查看</a>
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
	<c:if test="${not empty requestScope.msgPage && not empty requestScope.msgPage.list}">
		<jsp:include page="page.jsp"/>
	</c:if>
</div>	
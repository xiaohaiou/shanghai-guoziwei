<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox">
	<a class="model_btn_plus2" id="addContract" onclick="mload('${pageContext.request.contextPath}/project/contract/_addContract?pjId=${pjId}')">+</a>
	<table>
		<tr class="table_header" id="contractTableTr">
			<th style="width:5%">序号</th>
			<th style="width:15%">合同名称</th>
			<th style="width:15%">合同主体甲方</th>
			<th style="width:15%">合同主体乙方</th>
			<th style="width:10%">签订日期</th>
			<th style="width:10%">标的金额</th>
			<th style="width:10%">已支付金额</th>
			<th style="width:10%">合同状态</th>
			<th style="width:10%">操作</th>
		</tr>
		<c:if test="${not empty msgPage && not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="v" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td style="word-wrap:break-word;text-align: left;">${v.pcName }</td>
					<td style="word-wrap:break-word;text-align: left;">${v.pcBodyA }</td>
					<td style="word-wrap:break-word;text-align: left;">${v.pcBodyB}</td>
					<td>${v.pcSignDate}</td>
					<td style="text-align:right;"><fmt:formatNumber type="number" value="${v.pcBdMoney}" pattern="0.0000" maxFractionDigits="4"/>${v.bcUnit }</td>
					<td style="text-align:right;"><fmt:formatNumber type="number" value="${v.pcPaidMoney}" pattern="0.0000" maxFractionDigits="4"/>${v.paidUnit }</td>
					<td>
						<c:if test="${v.reportStatus==0}">
							<span style="color:#3366ff">未上报</span>
						</c:if>
						<c:if test="${ v.reportStatus==1}">
							<span style="color:#ff9933">待审核</span>
						</c:if>
						<c:if test="${ v.reportStatus==2}">
							<span style="color:#00cc66">已审核</span>
						</c:if>
						<c:if test="${ v.reportStatus==3}">
							<span style="color:#ff399d">已退回</span>
						</c:if>
					</td>
					<td>
						<a class="btn btn-primary model_btn_ok" onclick="editContractValidate('${v.id}')">修改</a>
						<a class="btn btn-primary model_btn_ok" onclick="del('${v.id}','contract')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage || empty requestScope.msgPage.list}">
			<tr>
				<td colspan="10" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty requestScope.msgPage && not empty requestScope.msgPage.list}">
		<jsp:include page="page.jsp"/>
	</c:if>
</div>	
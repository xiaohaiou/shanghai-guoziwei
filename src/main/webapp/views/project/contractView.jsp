<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>合同新增、编辑页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<style>
	table{
		table-layout: fixed;
	}
	table td{
		line-height:2em;
	}
</style>
</head>
<body>
	
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>查看合同信息
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form id="form2">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"></span>项目名称:</label>
				<label class="w25 setleft">${project.pjName}</label>
				<label class="w20">合同编号:</label>
				<label class="w25 setleft">${contract.pcNo }</label>
				<label class="w20">合同名称:</label>
				<label class="w25 setleft">${contract.pcName }</label>
				<label class="w20">经办人:</label>
				<label class="w25 setleft">${contract.pcOperator }</label>
				<label class="w20">合同主体甲方:</label>
				<label class="w25 setleft">${contract.pcBodyA }</label>
				<label class="w20">合同主体乙方:</label>
				<label class="w25 setleft">${contract.pcBodyB }</label>
				<label class="w20">合同类型:</label>
				<label class="w25 setleft">
					<c:if test="${not empty contractTypes }">
						<c:forEach items="${contractTypes }" var="t">
							<c:if test="${t.codeNo eq contract.pcType }">
								${t.codeName }
							</c:if>
						</c:forEach>
					</c:if>
				</label>
				<br>
				<label class="w20">合同标的金额:</label>
				<label class="w25 setleft"><fmt:formatNumber type="number" value="${contract.pcBdMoney }" pattern="0.0000" maxFractionDigits="4"/>
					<c:if test="${not empty moneyUnits }">
						<c:forEach items="${ moneyUnits}" var="m">
							<c:if test="${m.codeNo eq contract.bcUnit }">
								${m.codeName }
							</c:if>
						</c:forEach>
					</c:if>
				</label>
				<br>
				<label class="w20">合同付款方式:</label>
				<label class="w25 setleft">
					<c:forEach items="${pcPayTypes}" var="ppt">
						<c:if test="${ppt.codeNo eq contract.pcPayType }">
							${ppt.codeName }
						</c:if>
					</c:forEach>
				</label>
				<label class="w20">合同签订日期:</label>
				<label class="w25 setleft">${contract.pcSignDate}</label>
				<br>
				<label class="w20">合同支付金额:</label>
				<label class="w25 setleft"><fmt:formatNumber type="number" value="${contract.pcPaidMoney}" pattern="0.0000" maxFractionDigits="4"/>
					<c:if test="${not empty moneyUnits }">
						<c:forEach items="${ moneyUnits}" var="m">
							<c:if test="${m.codeNo eq contract.paidUnit }">${m.codeName }</c:if>
						</c:forEach>
					</c:if>
				</label>
				<br>
				<label class="w20"><span class="required"></span>是否重大项目:</label>
				<label class="w25 setleft">
					<c:if test="${contract.isImportant eq 0 }">否</c:if>
					<c:if test="${contract.isImportant eq 1 }">是</c:if>
				</label>
				<br>
				<label class="w20"><span class="required"></span>备注:</label>
				<label class="w70 setleft">
					${contract.pcRemark }
				</label>
				<label class="w20">创建人:</label>
				<label class="w25 setleft">${contract.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${contract.createDate}</label>
				<c:if test="${contract.reportStatus == 1 && (not empty contract.reportPersonName) && (empty contract.approveId)}">
					<label class="w20">上报人:</label>
					<label class="w25 setleft">${contract.reportPersonName}</label>
					<label class="w20">上报时间:</label>
					<label class="w25 setleft">${contract.reportTime}</label>
				</c:if>
			</div>
			<h3 class="data_title1">合同付款进度信息</h3>
			<div class="model_part" id="payRecordList">
				<div class="tablebox">
				<table>
					<tr class="table_header" id="payRecordTableTr">
						<th style="width:5%">序号</th>
						<th style="width:5%">付款期次</th>
						<th style="width:5%">已支付款项</th>
						<th style="width:5%">付款比</th>
						<th style="width:5%">付款日期</th>
						<th style="width:5%">备注</th>
					</tr>
				
					<c:if test="${not empty payRecords}">
						<c:forEach items="${payRecords}" var="py" varStatus="status">
							<tr id="payRecord${py.id}">
								<td>${status.count}</td>
								<td>${py.payCount}</td>
								<td style="text-align:right;"><fmt:formatNumber type="number" value="${py.paidMoney}" pattern="0.0000" maxFractionDigits="4"/>${py.pmUnit }</td>
								<td style="text-align:right;"><fmt:formatNumber  value="${py.payPercent/100}" pattern="##0%"></fmt:formatNumber></td>
								<td>${py.planPayDate}</td>
								<td style="text-align:left;">${py.remark}</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				</div>	
			</div>
			<h3 class="data_title1">上报审核历史信息</h3>
			<div class="model_part" id="history">
			<div class="tablebox">
				<table >
					<tr class="table_header" id="historyTr">
						<th style="width:5%">序号</th>
						<th style="width:10%">上报人</th>
						<th style="width:15%">上报时间</th>
						<th style="width:10%">审核人</th>
						<th style="width:15%">审核时间</th>
						<th style="width:10%">审核结果</th>	
						<th style="width:35%">审核备注</th>
					</tr>
					<c:if test="${not empty histories }">
						<c:forEach items="${histories }" var="history" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td style="text-align:left;">${history.reportPersonName}</td>
								<td>${history.reportTime}</td>
								<td style="text-align:left;">${history.approve.createPersonName}</td>
								<td>${history.approve.createDate}</td>
								<td>
									${history.approve.approveResult == '1'?'通过':'退回' }
								</td>
								<td style="word-wrap:break-word;text-align: left">${history.approve.remark}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty histories }">
						<tr>
							<td colspan="7" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>	
				</table>
			</div>
			
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	<form>
	<jsp:include page="../system/modal.jsp"/>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/vaild.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
	//关闭弹窗
	$(".model_btn_close").click(function () {
        window.parent.mclose();
		return false;
	});
	$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
</script>
</html>
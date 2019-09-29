<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>干部员工合规档案</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>
			<span class="glyphicon glyphicon-pencil"></span>干部员工合规档案
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<form:form id="form1" modelAttribute="duty" method="post">
			<form:hidden path="id" value="${duty.id}"/>
			<div class="label_inpt_div">
				<div class="model_part">
					<label class="w20"><span class="required"> * </span>员工姓名：</label>
					<label class="w25 setleft">"${duty.person}</label>
					
					<label class="w20"><span class="required"> * </span>员工号：</label>
					<label class="w25 setleft">${duty.personId}</label>
					
					<label class="w20"><span class="required"> * </span>现任职单位：</label>
					<label class="w25 setleft">${duty.personCompany }</label>
						
					
						
					<label class="w20"><span class="required"> * </span>任职职务：</label>
					<label class="w25 setleft">${duty.personPost }</label>
					
				</div>
				<div class="model_part">
					<table>
						<caption>合规调查</caption>
						<tr>
							<th width="10%">发生时间</th>
							<th width="30%">问责原因</th>
							<th width="30%">建议问责落实情况</th>
							<th width="15%">处分问责呈报公文编号</th>
							<th width="15%">处分问责办文编号</th>
						</tr>
						<c:if test="${!empty complianceList}">
							<c:forEach items="${complianceList}" var="examine" varStatus="status">
								<tr>
									<td>${examine.happenDate }</td>
									<td>${examine.reason }</td>
									<td>${examine.proposal }</td>
									<td>${examine.bumfNum }</td>
									<td>${examine.articleNum }</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
				<div class="model_part">
					<table>
						<caption>内部审计</caption>
						<tr>
							<th width="10%">发生时间</th>
							<th width="30%">问责原因</th>
							<th width="30%">建议问责落实情况</th>
							<th width="15%">处分问责呈报公文编号</th>
							<th width="15%">处分问责办文编号</th>
						</tr>
						<c:if test="${!empty insidepList}">
							<c:forEach items="${insidepList}" var="examine" varStatus="status">
								<tr>
									<td>${examine.happenDate }</td>
									<td>${examine.reason }</td>
									<td>${examine.proposal }</td>
									<td>${examine.bumfNum }</td>
									<td>${examine.articleNum }</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
				<div class="model_part">
					<table>
						<caption>案件管理</caption>
						<tr>
							<th width="10%">发生时间</th>
							<th width="30%">问责原因</th>
							<th width="30%">建议问责落实情况</th>
							<th width="15%">处分问责呈报公文编号</th>
							<th width="15%">处分问责办文编号</th>
						</tr>
						<c:if test="${!empty caseList}">
							<c:forEach items="${caseList}" var="examine" varStatus="status">
								<tr>
									<td>${examine.happenDate }</td>
									<td>${examine.reason }</td>
									<td>${examine.proposal }</td>
									<td>${examine.bumfNum }</td>
									<td>${examine.articleNum }</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
				<div class="model_part">
					<table>
						<caption>风险信息报告</caption>
						<tr>
							<th width="10%">发生时间</th>
							<th width="30%">问责原因</th>
							<th width="30%">建议问责落实情况</th>
							<th width="15%">处分问责呈报公文编号</th>
							<th width="15%">处分问责办文编号</th>
						</tr>
						<c:if test="${!empty riskList}">
							<c:forEach items="${riskList}" var="examine" varStatus="status">
								<tr>
									<td>${examine.happenDate }</td>
									<td>${examine.reason }</td>
									<td>${examine.proposal }</td>
									<td>${examine.bumfNum }</td>
									<td>${examine.articleNum }</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
				<div class="model_btn">
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
		</form:form>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
	</script>
</html>
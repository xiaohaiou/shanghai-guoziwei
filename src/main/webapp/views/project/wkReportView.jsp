<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>采购数据新增、编辑页面</title>
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
		<span class="glyphicon glyphicon-pencil"></span>查看周报信息
		<i class="iconfont icon-guanbi"></i>
	</h4>
		<form id="form2">
			<div class="label_inpt_div">
				<div class="model_part">
					<label class="w20">项目名称:</label>
					<label class="w25 setleft">${project.pjName}</label>
					<label class="w20">周报标题:</label>
					<label class="w25 setleft">${wkReport.wrTitle }</label>
					<label class="w20">周报年份:</label>
					<label class="w25 setleft">${wkReport.wrYear }</label>
					<label class="w20">第几周:</label>
					<label class="w25 setleft">${wkReport.wrWeek }</label>
					<label class="w20">周报开始日期:</label>
					<label class="w25 setleft">${wkReport.wrStartTime }</label>
					<label class="w20">周报结束日期:</label>
					<label class="w25 setleft">${wkReport.wrEndTime }</label>
					<br/>
					<label class="w20">工程进度:</label>
					<label class="w70 setleft" style="word-wrap:break-word;">${wkReport.wrJdContent}</label>
					<br/>
					<label class="w20">工程质量:</label>
					<label class="w70 setleft" style="word-wrap:break-word;">${wkReport.wrZlContent }</label>
					<br/>
					<label class="w20">工程安全:</label>
					<label class="w70 setleft" style="word-wrap:break-word;">${wkReport.wrAqContent }</label>
					<br/>
					<label class="w20">招标采购:</label>
					<label class="w70 setleft" style="word-wrap:break-word;">${wkReport.wrCgContent }</label>
					<br/>
					<label class="w20">要协调事项:</label>
					<label class="w70 setleft" style="word-wrap:break-word;">${wkReport.wrSxContent }</label>
					<br/>
					<label class="w20">周报附件:</label>
					<label class="w25 setleft"><a href="${pageContext.request.contextPath}/${wkReport.wrLine}" target="_blank">查看周报PDF</a></label>
					<label class="w20">创建人:</label>
					<label class="w25 setleft">${wkReport.createPersonName}</label>
					<label class="w20">创建时间:</label>
					<label class="w25 setleft">${wkReport.createDate}</label>
					<c:if test="${wkReport.reportStatus == 1 && (not empty wkReport.reportPersonName) && (empty wkReport.approveId)}">
						<label class="w20">上报人:</label>
						<label class="w25 setleft">${wkReport.reportPersonName}</label>
						<label class="w20">上报时间:</label>
						<label class="w25 setleft">${wkReport.reportTime}</label>
					</c:if>
				</div>
				<h3 class="data_title1">上报审核历史信息</h3>
				<div class="tablebox model_part">
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
									<td style="word-wrap:break-word;text-align:left;">${history.approve.remark}</td>
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
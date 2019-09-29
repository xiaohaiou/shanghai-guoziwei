<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>审计问题整改跟踪表</title>
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
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>
			审计问题整改跟踪表
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<form action=""></form>
		<div class="table_content">
			<div class="tablebox">
			<input type="hidden" name="projectId" id="projectId" value="${projectId}"/>
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:10%;">反馈日期</th>
						<th style="width:35%;">措施内容</th>
						<th style="width:40%;">跟踪反馈</th>
						<th style="width:10%;">措施状态</th>
					</tr>
					<c:if test="${not empty requestScope.msgPage.list }">
					    <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="r" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: center;">
									${r.feedbackDate}
								</td>
								<td style="text-align: left">
									${r.measuresDesc}
								</td>
								<td style="text-align: left;">
									${r.feedbackDesc}
								</td>
								<td style="text-align: center;">
									<c:if test="${r.status == 1}">
										整改中
									</c:if>
									<c:if test="${r.status == 2}">
										整改完成
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="5" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		var timeoutId;
	</script>
</html>
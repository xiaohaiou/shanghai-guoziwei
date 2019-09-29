<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>工作提醒界面</title>
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
		<style>
			#form1 input,#form1 select{
			    width: calc(100% - 10em);
			}
			.table_content .tablebox{
				margin-top: 20px;
			}
		</style>
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body class="hn_grey">
		<jsp:include page="/views/public/title.jsp"></jsp:include>
		<div class="table_content">
		<form:form id="form1" class="row">
			<span class="col-md-4">
				<span class="search_title">标题：</span>
				<input type="text" value="${entity.title}" name="title" id="title"/> 
			</span>
			<span class="col-md-4">
				<span class="search_title">是否处理：</span>
				<select name="delFlag" id="delFlag" value="${entity.delFlag}">
					<option value="2">全部</option>         
				  	<option value="0" <c:if test="${'0' eq entity.delFlag}">selected</c:if> >未处理</option>     
				  	<option value="1" <c:if test="${'1' eq entity.delFlag}">selected</c:if> >已处理</option>  
				</select>  
			</span>
			<span class="form_div col-md-4">
				<input type="button" value="查询" class="form_btn" id="queryBtn" style="padding-left: 18px !important;">
			</span>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:30%;">标题</th>
						<th>处理时间</th>
						<th>是否处理</th>
						<th>创建人</th>
						<th>处理</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="msg" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: left;word-wrap:break-word;word-break:break-all;">
									${msg.title}
								</td>
								<td style="text-align: center;">
									${msg.date}
								</td>
								<c:if test="${'1' eq msg.delFlag}">
									<td style="text-align: center;">
										已处理
									</td>
								</c:if>
								<c:if test="${'0' eq msg.delFlag}">
									<td style="text-align: center;">
										未处理
									</td>
								</c:if>
								<td style="text-align: center;">
									${msg.creator}
								</td>
								<td style="text-align: center;">
									<c:if test="${'0' eq msg.delFlag && not empty msg.url}">
										<a href="${msg.url}" target="_blank">处理</a>
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
				<jsp:include page="../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		$("#queryBtn").click(function() {
			_query();	
		});
		function _query() {
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		};
	</script>
</html>
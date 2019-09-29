<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>审计项目整改完成待审核列表</title>
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
			审计项目整改完成待审核列表
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<form action=""></form>
		<div class="table_content">
			<div class="tablebox">
			<input type="hidden" name="projectId" id="projectId" value="${projectId}"/>
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width: 20%;">审计项目名称</th>
						<th>整改对接人</th>
						<th>整改责任人</th>
						<th>整改责任部门</th>
						<th>整改时限</th>
						<th>整改问题状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${not empty requestScope.msgPage.list }">
					    <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="r" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: center">
									${r.projectName}
								</td>
								<td style="text-align: left;">
									${r.rectifyPickerName}
								</td>
								<td style="text-align: right;">
									${r.rectifyResponseName}
								</td>
								<td style="text-align: right;">
									${r.rectifyResponseDept}
								</td>
								<td>
									${r.rectifyTime}
								</td>
								<td>
									<c:if test="${r.status == 1}">
										待整改
									</c:if>
									<c:if test="${r.status == 2}">
										整改中
									</c:if>
									<c:if test="${r.status == 3}">
										整改完成待审核
									</c:if>
									<c:if test="${r.status == 4}">
										整改完成
									</c:if>
								</td>
								<td>
									<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/rectify/view?id=${r.id}')">查看</a>
									<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/rectify/feedBackMsgList?rectifyId=${r.id}')">历史整改反馈记录</a>
									<c:if test="${r.status == 3}">
										<a href="javascript:void(0);" onclick="mload('${pageContext.request.contextPath}/bimr/rectify/feedBackCheckView?rectifyId=${r.id}')">审核</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="12" align="center">
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
		function del(id){
			parent.parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/bimr/rectify/delete";
					$.post(url,{id:id}, function(data) {
					    var r = data.result;
						if(r == 1) {
							win.successAlert(data.message,function(){
               					_query();
							});
						}else{
							win.errorAlert(data.message,function(){							
							   _query();
							});
						}
					});
				}
			});
		};
		
		function _query() {
			var id = document.getElementById("projectId").value;
			form.action = "${pageContext.request.contextPath}/bimr/rectify/fill?projectId="+id;
			form.method = "post";
			form.submit();
		};
		
		function applyFinish(rectifyId){
			parent.parent.win.confirm('确定要提交审核？',function(r){
				alert(r);
				if(r){
					var url = "${pageContext.request.contextPath}/bimr/rectify/applyFinishedRectify";
					$.post(url,{rectifyId : rectifyId}, function(data) {
					    var r = data.result;
						if(r == 1) {
							win.successAlert(data.message,function(){
		             			_query();
							});
						}else{
							win.errorAlert(data.message,function(){							
							   _query();
							});
						}
					});
				}
			});
		};
	</script>
</html>
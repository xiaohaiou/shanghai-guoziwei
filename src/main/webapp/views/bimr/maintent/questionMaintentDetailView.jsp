<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>审计项目维护</title>
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
<link rel="stylesheet" href="<c:url value="/css/button.css"/> ">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>审计项目维护
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2">
		<div class="label_inpt_div">
			<h4>
				<span style="font-weight: 1px;">审计项目发现问题清单</span>
				<a href="javascript:void(0)" class="button blue" onclick="mload('${pageContext.request.contextPath}/bimr/question/newOrModify?op=add&id=${entity.id}')">新增发现问题</a>
			</h4>
			<div class="model_part">
			  <div class="tablebox">
				<table style="width: 100%;" border="1">
					<tr class="table_header">
						<th style="width:8%;">序号</th>
						<th style="width:25%;">审计项目名称</th>
						<th style="width:25%;">存在问题</th>
						<th style="width:25%;">审计建议</th>
						<th style="width:17%;">操作</th>
					</tr>
					<c:if test="${not empty problem_detail }">
						<c:forEach items="${problem_detail}" var="pd" varStatus="s">
							<tr>
								<td>${s.index + 1}</td>
								<td>${pd.auditProjectName}</td>
								<td>${pd.projectPrincipal}</td>
								<td>${pd.auditStartDate}</td>
								<td>
										<a href="javascript:void(0);" onclick="mload('${pageContext.request.contextPath}/bimr/question/view?id=${pd.id}')">查看</a>
										<a href="javascript:void(0);" onclick="mload('${pageContext.request.contextPath}/bimr/question/newOrModify?id=${pd.id}')">修改</a>
										<a href="javascript:void(0);" onclick="mload('${pageContext.request.contextPath}/bimr/inside/view?id=${auditProject.id}')">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			  </div>
			</div>
			<h4>
				<span style="font-weight: 1px;">困难与措施</span>
				<a href="javascript:void(0)" class="button blue" onclick="mload('${pageContext.request.contextPath}/bimr/question/newOrModify?op=add&id=${entity.id}')">新增发现问题</a>
			</h4>
			<div class="model_part">
				<div class="tablebox">
					<table style="width: 100%;" border="1">
						<tr class="table_header">
							<th style="width:8%;">序号</th>
							<th style="width:30%;">审计项目名称</th>
							<th style="width:45%;">困难和应对措施</th>
							<th style="width:17%;">操作</th>
						</tr>
						<c:if test="${not empty difficult_detail}">
							<c:forEach items="${difficult_detail}" var="dd" varStatus="s">
								<tr>
									<td>${s.index + 1}</td>
									<td>${dd.auditProjectName}</td>
									<td>${dd.projectPrincipal}</td>
									<td>
										<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/inside/childrenView?id=${c.id}')">查看</a>
										<a href="javascript:void(0);" onclick="mload('${pageContext.request.contextPath}/bimr/question/question_maintent?id=${auditProject.id}')">修改</a>
										<a href="javascript:void(0);" onclick="mload('${pageContext.request.contextPath}/bimr/inside/view?id=${auditProject.id}')">删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
			<h4><span style="font-weight: 1px;">报告办结信息</span></h4>
			<div class="modal_part">
					<table style="width: 100%;" border="1">
						<tr>
							<td>办结方式:</td>
							<td>
								<select id="" name="" class="selectpicker">
									<option value="1">公文</option>
									<option value="2">邮件</option>
								</select>
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>公文编号:</td>
							<td><input type="text" name="documentCode" id="documentCode"></td>
							<td>邮件审批人</td>
							<td><input type="text" name="mailApproal" id="mailApproal"></td>
						</tr>
					</table>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	</form:form>
	<jsp:include page="../../system/modal.jsp"/>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
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
</script>
</html>
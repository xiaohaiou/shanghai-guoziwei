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
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>审计项目维护
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
		  <h4><span style="font-weight: 1px;">审计项目基本信息</span></h4>
			<div class="model_part">
				<label class="w20">审计项目编号:</label>
				<label class="w25 setleft">${entity.auditProjectCode}</label>
				<label class="w20">审计项目名称:</label>
				<label class="w25 setleft">${entity.auditProjectName}</label>
				
				<label class="w20">审计实施单位:</label>
				<label class="w25 setleft">${entity.auditImplDeptName}</label>
				<label class="w20">是否重点项目:</label>
				<label class="w25 setleft">${entity.isImportant==0?"否":"是"}</label>

				<label class="w20">审计项目负责人:</label>
				<label class="w25 setleft">${entity.projectPrincipal}</label>
				<label class="w20">是否子项目:</label>
				<label class="w25 setleft">${entity.isChildren==0?"否":"是"}</label>
				
				<label class="w20">审计项目目标:</label>
				<label class="w70 setleft">${entity.auditProjectGoal}</label><br>
				
				<label class="w20">审计项目性质:</label>
				<label class="w25 setleft">
					<c:if test="${entity.auditProjectProp == 80026}">
						风险导向审计
					</c:if>
					<c:if test="${entity.auditProjectProp == 80027}">
						风险管理审计
					</c:if>
					<c:if test="${entity.auditProjectProp == 80028}">
						内部控制评价
					</c:if>
					<c:if test="${entity.auditProjectProp == 80029}">
						经济责任审计
					</c:if>
					<c:if test="${entity.auditProjectProp == 80030}">
						企业巡视
					</c:if>
					<c:if test="${entity.auditProjectProp == 80031}">
						调研管理
					</c:if>
				</label>
				<label class="w20">被审计单位:</label>
				<label class="w25 setleft">${entity.auditDeptedName}</label>
				
				<label class="w20">审计项目开始日期:</label>
				<label class="w25 setleft">${entity.auditStartDate}</label>
				<label class="w20">审计项目结束日期:</label>
				<label class="w25 setleft">${entity.auditEndDate}</label>
				
				<label class="w20">审计项目内容:</label>
				<label class="w70 setleft">${entity.auditProjectContent}</label>
				<br>
				
				<label class="w20">备注:</label>
				<label class="w70 setleft">${entity.remark}</label>
			</div>
			<h4><span style="font-weight: 1px;">子项目列表信息</span></h4>
			<div class="model_part">
			  <div class="tablebox">
				<table style="width: 100%;" border="1">
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:20%;">项目名称</th>
						<th style="width:10%;">项目负责人</th>
						<th style="width:15%;">审计期间</th>
						<th style="width:10%;">累计发现问题数(个)</th>
						<th style="width:10%;">建议问责累计人员数(人)</th>
						<th style="width:30%;">操作</th>
					</tr>
					<c:if test="${not empty children }">
						<c:forEach items="${children}" var="c" varStatus="s">
							<tr>
								<td>${s.index + 1}</td>
								<td>${c.auditProjectName}</td>
								<td>${c.projectPrincipal}</td>
								<td>${c.auditStartDate}~${c.auditEndDate}</td>
								<td>0</td>
								<td>0</td>
								<td>
										<a href="javascript:void(0);" onclick="mload('${pageContext.request.contextPath}/bimr/inside/childrenView?id=${c.id}')">查看</a>
										<a href="javascript:void(0);" onclick="mload('${pageContext.request.contextPath}/bimr/question/question_maintent?id=${c.id}')">维护</a>
										<a href="javascript:void(0);" onclick="mload('${pageContext.request.contextPath}/bimr/inside/view?id=${c.id}')">申请办结</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			  </div>
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
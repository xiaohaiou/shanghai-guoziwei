<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看内部审计项目</title>
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
		<span class="glyphicon glyphicon-pencil"></span>内部审计项目查询
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
		  <h4><span style="font-weight: 1px;">查询审计项目</span></h4>
			<div class="model_part">
				<label class="w20">审计项目编号:</label>
				<label class="w25 setleft">${entity.auditProjectCode}</label>
				<label class="w20">审计项目名称:</label>
				<label class="w25 setleft">${entity.auditProjectName}</label>
				
				<label class="w20">审计实施单位:</label>
				<label class="w25 setleft">${entity.auditImplDeptName}</label>
				<label class="w20">整改跟踪人:</label>
				<label class="w25 setleft">${entity.rectifyTrackName}</label>
				<label class="w20">项目类别:</label>
				<label class="w25 setleft">
					<c:if test="${entity.isImportant == 0}">
						年度计划内项目
					</c:if>
					<c:if test="${entity.isImportant == 1}">
						年度计划外项目
					</c:if>
					<c:if test="${entity.isImportant == 2}">
						上级交办项目
					</c:if>
				</label>
				<label class="w20">审计项目负责人:</label>
				<label class="w25 setleft">${entity.projectPrincipal}</label>
				<label class="w20">是否子项目:</label>
				<label class="w25 setleft">${entity.isChildren==0?"否":"是"}</label>
				<c:if test="${type eq 'child'}">
				    <label class="w20">关联大审计项目:</label>
       				<label class="w70 setleft">
       				    <c:if test="${not empty parent}">
       				         ${parent.auditProjectName}
       				    </c:if>
       				</label><br>
				</c:if>
				<label class="w20">审计项目目标:</label>
				<label class="w25 setleft">${entity.auditProjectGoal}</label>
				
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
						经济任审计
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
				
				<c:if test="${entity.auditProjectProp == 80029}">
					<label class="w20">被审计人员:</label>
					<label class="w25 setleft">${entity.auditDeptedPerson}</label>
					<label class="w20">被审计人员职位:</label>
					<label class="w25 setleft">${entity.auditDeptedPersonPost}</label>
				</c:if>
				
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
			<h4><span style="font-weight: 1px;">审计项目发现问题清单</span></h4>
			<div class="model_part">
				<table style="width: 100%;">
					<tr>
						<th style="width:8%;">序号</th>
						<th style="width:23%;">存在问题</th>
						<th style="width:23%;">审计建议</th>
						<th style="width:23%;">发现问题类型</th>
						<th style="width:23%;">成因分析类型</th>
					</tr>
					<c:if test="${not empty problem_list }">
					    <c:forEach items="${problem_list}" var="p" varStatus="status">
					        <tr>
						      <td>${status.index + 1}</td>
						      <td>${p.problem}</td>
						      <td>${p.auditSuggest}</td>
						      <td>${p.auditQuestionTypes}</td>
						      <td>${p.riskDriverTypes}</td>
					        </tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<h4><span style="font-weight: 1px;">申请办结批复意见</span></h4>
			<div class="model_part">
				<table style="width: 100%;">
					<tr>
						<th style="width: 15%;">审核人</th>
						<th style="width: 15%">审核时间</th>
						<th style="width: 70%;">审核意见</th>
					</tr>
					<tr>
						<td>${entity.examinePersonName}</td>
						<td>${entity.examineDate}</td>
						<td>${entity.opinion}</td>
					</tr>
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
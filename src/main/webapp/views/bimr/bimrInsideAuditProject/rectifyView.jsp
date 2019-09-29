<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看内部审计项目相关问题整改</title>
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
		<span class="glyphicon glyphicon-pencil"></span>内部审计项目相关问题整改
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="rectifyQuestion" method="post">
		<div class="label_inpt_div">
		  <h4><span style="font-weight: 1px;">查询审计项目相关问题整改</span></h4>
			<div class="model_part">
				<label class="w20">审计项目名称:</label>
				<label class="w70 setleft">${entity.projectName}</label><br>

				<label class="w20">整改问题状态:</label>
				<label class="w70 setleft">
					<c:if test="${entity.status == 1}">
						待整改
					</c:if>
					<c:if test="${entity.status == 2}">
						整改中
					</c:if>
					<c:if test="${entity.status == 3}">
						整改完成待审核
					</c:if>
					<c:if test="${entity.status == 4}">
						整改完成
					</c:if>
				</label><br>

				<label class="w20">整改责任人:</label>
				<label class="w70 setleft">${entity.rectifyResponseName}</label><br>

				<label class="w20">整改时限:</label>
				<label class="w70 setleft">${entity.rectifyTime}</label><br>

				<label class="w20">整改责任部门:</label>
				<label class="w70 setleft">${entity.rectifyResponseDept}</label><br>

				<label class="w20">整改对接人:</label>
				<label class="w70 setleft">${entity.rectifyPickerName}</label><br>

				<label class="w20">存在问题:</label>
				<label class="w70 setleft">${entity.problems}</label><br>

				<label class="w20">审计建议:</label>
				<label class="w70 setleft">${entity.rectifyAdvice}</label><br>
			</div>
			<div class="tablebox">
				<table id="csTab">
					<tr class="table_header">
						<th width="80%">整改措施</th>
						<th width="20%">完成时限</th>
					</tr>
					<c:if test="${!empty requestScope.measuresList }">
						<c:forEach items="${ requestScope.measuresList}" var="l"varStatus="status">
							<tr>
								<td style="text-align: left;">${l.rectifyMeasure}</td>
								<td style="text-align: center;">${l.rectifyMeasureTime}</td>
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
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看待调查事项核查情况</title>
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
		<span class="glyphicon glyphicon-pencil"></span>查看待调查事项核查情况
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">待调查具体事项:</label>
				<label class="w70 setleft">${ entity.problem}</label>
				<br>
				<label class="w20">调查确认情况:</label>
				<label class="w70 setleft">${ entity.truth}</label>
				
				<label class="w20">是否需要问责:</label>
				<label class="w25 setleft">${ entity.isAccountability==0?"否":"是"}</label>
				
				<label class="w20">问题类型:</label>
				<label class="w25 setleft">${ entity.problemTypeName}</label>
			</div>
			
			
			<h3 class="data_title1">调查涉及人员</h3>
			<div class="tablebox">
				<table id="personTab">
					<tr class="table_header">
						<th >建议问责人员</th>
						<th >责任界定</th>
						<th >干部/员工</th>
					</tr>
					<c:if test="${!empty requestScope.personList }">
						<c:forEach items="${ requestScope.personList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${l.accountabilityPerson }</td>
								<td style="text-align: center;"> 
									<c:if test="${l.accountabilityDefinition == 0 }">直接责任</c:if>
									<c:if test="${l.accountabilityDefinition == 1 }">主要责任</c:if>
									<c:if test="${l.accountabilityDefinition == 2 }">直接管理责任</c:if>
									<c:if test="${l.accountabilityDefinition == 3 }">管理责任</c:if>
									<c:if test="${l.accountabilityDefinition == 4 }">领导责任</c:if>
								</td>
								<td style="text-align: center;">
									
									${l.personPostion==0?"干部":"员工" }
								</td>
								
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp" />
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
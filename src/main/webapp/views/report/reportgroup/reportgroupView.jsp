<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>报表组</title>
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
		<span class="glyphicon glyphicon-pencil"></span>查看报表组
		<i class="iconfont icon-guanbi"></i>
	</h4>
		

		<form:form id="form2" modelAttribute="entityview" >
			<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">报表组种类:</label>
				<label class="w25 setleft">${ entityview.group.name}</label> 
				<label class="w20">报表组属性:</label>
				<label class="w25 setleft">${ entityview.group.typeName}</label> 
				<label class="w20">年度:</label>
				<label class="w25 setleft">${ entityview.group.time}</label> 
				<c:if test="${not empty entityview.group.month }">
					<label class="w20">核算年月:</label>
						<label class="w25 setleft">${ entityview.group.month}</label> 
				</c:if>	
			</div>
			
			<h3 class="data_title1">报表明细</h3>
			
			<div class="model_part">
				<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>频度</th>
						<th>报表名称</th>
						
					</tr>
					<c:forEach items="${ entityview.group_item}" var="sys" varStatus="status">
							<tr>
								<td>
									${sys.sort}
								</td>
								<td>
									${sys.freName}
								</td>
								<td>
									${sys.formkindName }
								</td>
					
							</tr>
					</c:forEach>
				</table>
				</div>
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
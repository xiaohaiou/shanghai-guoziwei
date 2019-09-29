<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>未完成审计项目发现问题查看</title>
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
		<span class="glyphicon glyphicon-pencil"></span>未完成审计项目发现问题查看
		<i class="iconfont icon-guanbi"></i>
	</h4>
		<div class="label_inpt_div">
		  <h4><span style="font-weight: 1px;">审计项目相关问题</span></h4>
			<div class="model_part">
				<label class="w20">审计项目名称:</label>
				<label class="w25 setleft">${bimrInsideAuditQuestion.projectName}</label>
			
                <label class="w20">问题标题:</label>
				<label class="w25 setleft">${entity.problemTopic}</label>
				<label class="w20">存在的问题:</label>
				<label class="w25 setleft">${entity.problem}</label>
				
				<label class="w20">问题归属单位:</label>
				<label class="w25 setleft">${entity.problemAttrUnitName}</label>

				<label class="w20">是否为重大问题:</label>
				<label class="w25 setleft">${entity.isImportant==0?"否":"是"}</label>

			</div>	
			
			<div class="model_part">	
			<c:if test="${not empty bimrInsideAuditRectifylist }">
				  <c:forEach items="${bimrInsideAuditRectifylist}" var="r" varStatus="status">
				  <h4><span style="font-weight: 1px;">审计项目相关问题整改${status.index + 1 }</span></h4>	
				  		<label class="w20">整改问题状态:</label>
				<label class="w25 setleft">
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
				</label><br>

				<label class="w20">整改责任人:</label>
				<label class="w25 setleft">${r.rectifyResponseName}</label>

				<label class="w20">整改时限:</label>
				<label class="w25 setleft">${r.rectifyTime}</label>

				<label class="w20">整改责任部门:</label>
				<label class="w25 setleft">${r.rectifyResponseDept}</label>

				<label class="w20">整改对接人:</label>
				<label class="w25 setleft">${r.rectifyPickerName}</label>

				<label class="w20">存在问题:</label>
				<label class="w25 setleft">${r.problems}</label>

				<label class="w20">审计建议:</label>
				<label class="w25 setleft">${r.rectifyAdvice}</label>
				
				
				<div class="tablebox" >
				<table id="csTab">
					<tr class="table_header">
						<th width="60%" style="text-align: left;">整改措施</th>
						<th width="40%" style="text-align: left;">完成时限</th>
					</tr>
					<c:if test="${!empty requestScope.bimrInsideAuditMeasurelist}">
						<c:forEach items="${ requestScope.bimrInsideAuditMeasurelist}" var="l"varStatus="status">
							<c:if test="${l.rectifyId==r.id}">
							<tr>
								<td style="text-align: left;">${l.rectifyMeasure}</td>
								<td style="text-align: left;">${l.rectifyMeasureTime}</td>
							</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.feedList}">
						<c:forEach items="${ requestScope.feedList}" var="l"varStatus="status">
							<tr>
								<td colspan="4">暂无数据</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
					
				  </c:forEach>
			</c:if>
		</div>		
				
		

				
	</div>
			
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	
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
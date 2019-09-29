<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>稽核整改信息维护</title>
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
	    <span class="glyphicon glyphicon-pencil"></span>稽核整改信息维护
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">年份:</label> <label class="w25 setleft">${
					entity.year}</label> <br> <label class="w20">稽核事项所属单位:</label> <label
					class="w25 setleft">${ entity.compName}</label> <label class="w20">稽核事项所属核心企业:</label>
				<label class="w25 setleft">${ entity.compName}</label>
			</div>
			<h3 class="data_title1">稽核财务事项基本信息</h3>
			<div class="model_part">
				<label class="w20">财务事项类别:</label> <label class="w25 setleft">${
					entity.itemTypeName}</label> <label class="w20">稽核财务事项名称:</label> <label
					class="w25 setleft">${ entity.itemName}</label> <label class="w20">稽核事项负责人:</label>
				<label class="w25 setleft">${ entity.itemPerson}</label> <label
					class="w20">稽核事项开始日期:</label> <label class="w25 setleft">${
					entity.startTime}</label> <label class="w20">稽核事项结束日期:</label> <label
					class="w25 setleft">${ entity.endTime}</label> <br> <label
					class="w20">备注:</label> <label class="w70 setleft">${
					entity.remark}</label>
				</br>
				<label class="w20">上传文件:</label>
				<span>
				<c:if test="${!empty entity.fileOne.fileName}">
					<a href="${pageContext.request.contextPath}/common/download?_url=${entity.fileOne.filePath }&_name=${entity.fileOne.fileName }" data-original-title="" title="">${entity.fileOne.fileName }</a>
				</c:if>
				</span>
			</div>
			 
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:3%">序号</th>
						<th style="width:20%">存在问题</th>
						<th style="width:7%">是否整改</th>
						<th style="width:23%">整改建议措施</th>
						<th style="width:10%">整改时限</th>
						<th style="width:10%">整改跟踪人</th>
						<th style="width:12%">操作</th>
					</tr>
					<c:if test="${!empty requestScope.problemList }">
						<c:forEach items="${ requestScope.problemList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: left;max-width:20em">${l.problem }</td>
								<td style="text-align: left;">${l.isChange==0?"否":"是"}</td>
								<td style="text-align: left;max-width:20em">${l.changeContent }</td>
								<td style="text-align: center;">${l.changeLastTime }</td>
								<td style="text-align: left;">${l.changeFollowPerson }</td>
								<td><a href="javascript:void(0)" onclick="mload('${mapurl}/changeManageProblemEdit?problemId=${l.id }')">查看</a>
								  <c:if test="${l.isChange == 1}">
									<a href="javascript:void(0)" onclick="mload('${mapurl}/changeManageProblemEdit?problemId=${l.id }&isEdit=true')">修改</a>
								  </c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			
			<h3 class="data_title1">领导指令</h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:3%">序号</th>
						<th style="width:8%">领导姓名</th>
						<th style="width:8%">指示时间</th>
						<th style="width:15%">指示内容</th>
						<th style="width:8%">回复人</th>
						<th style="width:8%">回复时间</th>
						<th style="width:15%">回复情况</th>
						<th style="width:10%">操作</th>
					</tr>
					<c:if test="${!empty requestScope.orderList }">
						<c:forEach items="${ requestScope.orderList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: center;">${l.orderSenderName }</td>
								<td style="text-align: center;"><fmt:parseDate value="${l.orderDate}" var="orderDate" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${orderDate}" pattern="yyyy-MM-dd"/></td>
								<td style="text-align: left;">${l.orderDescription }</td>
								<td style="text-align: left;">${l.orderReportName }</td>
								<td style="text-align: left;">
								  <fmt:parseDate value="${l.orderReportDate}" var="orderReportDate" pattern="yyyy-MM-dd"/>
								  <fmt:formatDate value="${orderReportDate}" pattern="yyyy-MM-dd"/>
								</td>
								<td style="text-align: left;">${l.orderReportDescription }</td>
								<td style="text-align: left;"> 
									<a href="javascript:void(0)" onclick="mload('/shanghai-gzw/inspectproject/orderview?orderId=${l.id }')">查看</a>
									<c:if test="${l.orderReportDate == null}">
									  <a href="javascript:void(0)" onclick="mload('${mapurl}/orderreport?orderId=${l.id }')">回复</a>
									</c:if>
							    </td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div class="model_btn">
			    <button class="btn btn-default model model_btn_ok" id="btn_save">保存</button>
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
	function commit1(){
		
		if(!vaild.all()){
			return false;
		}
		
		var ids = new Array();
		var isPasses = new Array();
		$("[name=sel_check]").each(function(){
			ids.push($(this).attr("check_id"));
			isPasses.push($(this).val());
		});
		
		$.blockUI({ message: '提交中', css: { width: '275px' } });
		var content = $('#tex_content').val();
		var params = {'id': ${entity.id}}
	
		var url = "${pageContext.request.contextPath}/inspectproject/saveChangeManageEdit";
		$.post(url, params, function(data) {
			$.unblockUI();
			if(data.result==true){
				win.successAlert('保存成功！',function(){
					parent.mclose();
					parent.location.reload();	
			    });
			}else{
			    win.errorAlert(data.exceptionStr);
			}
		});
		return false;
	}
	
	//关闭弹窗
	$(".model_btn_close").click(function() {
		parent.mclose();
		return false;
	});
	$(".icon-guanbi").click(function () {
		parent.mclose();
		return false;
	});
	$("#btn_save").click(function(){
		commit1();
		return false;
	});
</script>
</html>
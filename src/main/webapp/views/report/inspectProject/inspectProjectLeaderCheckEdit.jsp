<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>稽核财务事项一览</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
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
	    <span class="glyphicon glyphicon-pencil"></span>${compName}公司稽核财务事项一览
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity"  method="post">
	<div class="table_content">
		<div class="tablebox">
			<table>
				<tr class="table_header">
						<tr class="table_header">
						<th style="width:3%" >序号</th>
						<th style="width:25%" >稽核财务事项名称</th>
						<th style="width:10%">稽核事项负责人</th>
						<th style="width:15%" >稽核起止时间</th>
						<th style="width:10%" >发现问题数</th>
						<th style="width:10%" >需整改问题数</th> 
						<th style="width:10%" >状态标识</th> 
						<th style="width:10%" >稽核意见</th>
						<th style="width:15%" >操作</th>
					</tr>
				
				<c:if test="${!empty requestScope.msgPage.list }"> 
				    <c:set var="recordNumber" value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.msgPage.list}" var="item" varStatus="status">
						<tr>
								<td style="text-align: center;">${recordNumber+ status.index + 1 }</td>
								<td style="text-align: left;">${item[1]}</td>
								<td style="text-align: left;">${item[2]}</td>
								<td style="text-align: center;">${item[3]}~${item[4]}</td>
								<td style="text-align: right;">${item[5]}</td>
								<td style="text-align: right;">${item[6]}</td>
								<td style="text-align: right;">
								  <c:if test="${item[7]==80040}">稽核中</c:if>
								  <c:if test="${item[7]==80041}">办结申请中</c:if>
								  <c:if test="${item[7]==80042}">已办结</c:if>
								  <c:if test="${item[7]==80043}">办结确认</c:if>
								  <c:if test="${item[7]==80044}">整改中</c:if>
								  <c:if test="${item[7]==80045}">稽核完结</c:if>								  
								</td>
								<td style="text-align: right;">
								<select name="sel_check" check_id="${item[0]}" class="selectpicker">
						          <option value="1">同意</option>
						          <option value="0">退回</option>
						        </select> 
								</td>
								<td style="text-align: center;">
								  <a href="javascript:void(0)" onclick="mload('${mapurl}/applyCheckView?id=${item[0]}')">查看</a>
								</td>
							</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.msgPage.list}">
					<tr>
						<td colspan="13" align="center">查询无记录</td>
					</tr>
				</c:if>
			</table>
		</div>
		<div class="label_inpt_div">
		    <div class="model_part">
				<label class="w20"><span class="required"> * </span>审核意见:</label>
				<textarea class="w70" rows="5" id="tex_content" name="content" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._."></textarea>
				<br>
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="btn_save" >保存</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	</div>
	<jsp:include page="/views/system/modal.jsp" />
	</form:form>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
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
		var params = {'ids': ids, 'isPasses': isPasses, 'content': content}
 
		var url = "${pageContext.request.contextPath}/inspectproject/saveInspectProjectLeaderCheck";
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

	$(".model_btn_close").click(function () {
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
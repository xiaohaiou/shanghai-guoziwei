<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>案件信息新增、编辑及保存上报页面</title>
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
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改案件信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增案件信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="riskControlCase" method="post">
		<form:hidden path="id"/>
		<form:hidden path="createPersonName"/>
		<form:hidden path="createPersonId"/>
		<form:hidden path="createDate"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>发生案件所属业态:</label>
				<select name="caseBelongCompany" id="caseBelongCompany" class="selectpicker w25">
				<option value="">全部</option>
					<c:forEach items="${requestScope.auditDepts}" var="result">
							<option value="${result.nnodeId}"  <c:if test="${riskControlCase.caseBelongCompany == result.nnodeId}">selected="selected"</c:if>>${result.vcFullName}</option>
						</c:forEach>
				</select>
				<label class="w20"><span class="required"> * </span>案件发生实体企业:</label>
				<select name="caseSubordinateCompany" id="caseSubordinateCompany" class="selectpicker w25" >
				<option value="">全部</option>
				</select><br>
				<label class="w20"><span class="required"> * </span>原告或申诉人(申请执行人):</label>
				<input class="w25" type="text" name="plaintiff" id="plaintiff" value="${riskControlCase.plaintiff}"/>
				<label class="w20"><span class="required"> * </span>被告或被诉人(被执行人):</label>
				<input class="w25" type="text" name="defendant" id="defendant" value="${riskControlCase.defendant}"/><br>
				<label class="w20"><span class="required"> * </span>案件类型(案由):</label>
				<select name="caseType" id="caseType" class="selectpicker w25" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.caseType}" var="result">
							<option value="${result.id}"  <c:if test="${riskControlCase.caseType == result.id}">selected="selected"</c:if>>${result.description}</option>
						</c:forEach>
				</select>
				<label class="w20"><span class="required"> * </span>案发时间:</label>
				<input type="text" name="caseHappenDate" id="caseHappenDate" value="${riskControlCase.caseHappenDate}" readonly="readonly" class="w25 time"/><br>
				<label class="w20"><span class="required"> * </span>诉讼标的额(万元):</label>
				<input class="w25" type="text" name="litigationAmountMoney" id="litigationAmountMoney" check="NotEmpty_double_15_4_0+_。_." value="${riskControlCase.litigationAmountMoney}"/>
				<label class="w20"><span class="required"> * </span>目前状态:</label>
				<select name="currentState" id="currentState" class="selectpicker w25" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.currentState}" var="result">
							<option value="${result.id}"  <c:if test="${riskControlCase.currentState == result.id}">selected="selected"</c:if>>${result.description}</option>
						</c:forEach>
				</select>
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2" >保存并上报</button>
				<button class="btn btn-default model model_btn_close">取消</button>
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
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		function checkcommit() {
			if($("#caseBelongCompany").val()=="") {
				win.errorAlert("发生案件所属业态不能为空");
				return false;
			}
			if($("#caseSubordinateCompany").val()=="") {
				win.errorAlert("案件发生实体企业不能为空");
				return false;
			}
			if($("#plaintiff").val()=="") {
				win.errorAlert("原告或申诉人(申请执行人)不能为空");
				return false;
			}
			if($("#defendant").val()=="") {
				win.errorAlert("被告或被诉人(被执行人)不能为空");
				return false;
			}
			if($("#caseType").val()=="") {
				win.errorAlert("案件类型(案由)不能为空");
				return false;
			}
			if($("#caseHappenDate").val()=="") {
				win.errorAlert("案发时间不能为空");
				return false;
			}
			if($("#currentState").val()=="") {
				win.errorAlert("目前状态不能为空");
				return false;
			}
			return true;
		};	
		$("#commit-1").click(function(){
			if(!checkcommit() || !vaild.all()) {
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var riskControlCaseInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/riskcontrol/case/saveOrUpdate";
			$.post(url, riskControlCaseInfo, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('保存成功！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}else{
					win.errorAlert(commitresult.exceptionStr);
				}
			});
			return false;
		});
		$("#commit-2").click(function(){
			if(!checkcommit()) {
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var riskControlCaseInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/riskcontrol/case/saveOrUpdateAndReported";
			$.post(url, riskControlCaseInfo, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('保存成功！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}else if(data == "false"){
					win.successAlert('该数据已经被上报，不能再执行当前操作',function(){
						parent.location.reload();
						parent.mclose();
					});
				}
			});
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		$("#caseBelongCompany").change(function(){
			$("#caseSubordinateCompany").empty();
			var url="${pageContext.request.contextPath}/riskcontrol/case/selectCompany";
			$.ajax({
				url: url,
				type: "POST",
				dataType: 'json',
				data:{id:$("#caseBelongCompany").val()},
				success: function(result) {
					for(var i=0;i<result.length;i++){
						$("#caseSubordinateCompany").append("<option value="+result[i].nnodeId+">"+result[i].vcFullName+"</option>");
					}
				}
			});
		});
		$(document).ready(function() {
			if('${op}' == 'modify'){
				$("#caseSubordinateCompany").empty();
				var caseBelongCompany = '${riskControlCase.caseBelongCompany}';
				var caseSubordinateCompany = '${riskControlCase.caseSubordinateCompany}';
				if(caseBelongCompany != ""){
					var url="${pageContext.request.contextPath}/riskcontrol/case/selectCompany";
					$.ajax({
						url: url,
						type: "POST",
						dataType: 'json',
						data:{id:caseBelongCompany},
						success: function(result) {
							for(var i=0;i<result.length;i++){
								if(caseSubordinateCompany == result[i].nnodeId){
									$("#caseSubordinateCompany").append("<option value='"+result[i].nnodeId+"' selected='selected'>"+result[i].vcFullName+"</option>");
								}else{
									$("#caseSubordinateCompany").append("<option value='"+result[i].nnodeId+"'>"+result[i].vcFullName+"</option>");
								}
							}
						}
					});
				}
			}
		});
	</script>
</html>
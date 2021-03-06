<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>公文审批及时率审批页面</title>
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
	<style type="text/css">
		.w75 {
			word-wrap: break-word;
		}
		.w25 {
			word-wrap: break-word;
		}
	</style>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>监管预警采集数据审核
		<i class="iconfont icon-guanbi"></i>
	</h4>
	
	<form:form id="form2" modelAttribute="theEntity" method="post">
		<form:hidden path="id"/>
		<input type="hidden" id="lastModifyDate" value="${lastModifyDate}"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ theEntity.year}</label>
				<label class="w20">季度:</label>
				<label class="w25 setleft">${ theEntity.seasonName}</label>
			</div>
			<div class="model_part">
				<label class="w20">发生单位名称:</label>
				<label class="w25 setleft">${ theEntity.riskCompName}</label>
				<label class="w20">所属核心企业:</label>
				<label class="w25 setleft">${ theEntity.coreCompName}</label>
				<label class="w20">发生日期:</label>
				<label class="w25 setleft">${ theEntity.riskDate}</label>
				<label class="w20">事件标题:</label>
				<label class="w25 setleft">${ theEntity.eventTitle}</label>
				<label class="w20" style="vertical-align:top;">事件概要:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${ theEntity.eventDescription}</label> 
				<%-- <label class="w20">事件概要:</label>
				<label class="w25 setleft">${ theEntity.eventDescription}</label> --%>
				<%-- <input class="w25" type="text" name="eventDescription" id="eventDescription" value="${theEntity.eventDescription }" /> --%>
				<%-- <span class="w25 "><textarea  rows="5" cols="50" name="eventDescription" id="eventDescription" value="${theEntity.eventDescription }" readonly="readonly"></textarea></span> --%>
			</div>
			<c:if test="${!empty requestScope.examineList}">
				<h3 class="data_title1">审核意见</h3>
				<c:forEach items="${requestScope.examineList }" var="entityExamineview">
					<div class="model_part">
						<label class="w20">审核人:</label>
						<label class="w25 setleft">${ entityExamineview.createPersonName}</label> 
						<label class="w20">审核时间:</label>
						<label class="w25 setleft">${ entityExamineview.createDate}</label> 
						<label class="w20">审核意见:</label>
						<label class="w75 setleft">${ entityExamineview.examinestr}</label> 
					</div>
				</c:forEach>
			</c:if>
			<div class="model_part">
				<label class="w20" style="vertical-align: top;"><font color=red>*</font>审核意见:</label>
				<span class="w70"><textarea  rows="5" cols="40" name="examStr" id="examStr"></textarea>
					<span id="textareaLabel" style="color:red;display:block"></span>
				</span>
			</div>
			<div class="model_btn">
				<c:if test="${theEntity.status!=50115}">
					<button class="btn btn-primary model_btn_ok" id="commit-4" >通过</button>
				</c:if>
				<button class="btn btn-primary model_btn_ok" id="commit-5" >退回</button>
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
			
		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		)
		
		function commit4(){
			if(!checkcommit()) {
				return false;
			}
			$.post("/shanghai-gzw/adRiskevent/commitexam",{entityid:$("#id").val(),lastModifyDate:$("#lastModifyDate").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('保存成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr);
	 		 });
			 return false;
		}
		
		function commit5(){
			if(!checkcommit()) {
				return false;
			}
			$.post("/shanghai-gzw/adRiskevent/commitexam",{entityid:$("#id").val(),lastModifyDate:$("#lastModifyDate").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('退回成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr);
	 		 });
	 		 return false;
		
		}

		//关闭弹窗
		$(".model_btn_close").click(function () {
		　	parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		function checkcommit() {
			if($("#examStr").val()=="") {
				win.errorAlert("审核意见不能为空");
				return false;
			}
			return true;
		};
		
		$("#commit-4").click(function(){
			var id = $("#id").val();
			if(id != null && id != ""){
				$.post("${pageContext.request.contextPath}/adRiskevent/checkDelete?id=" + id, function(data) {
					var commitresult=JSON.parse(data);
					if(commitresult.result){
						commit4();
					}else{
						win.errorAlert('此数据已被删除！',function(){
							parent.location.reload();
							parent.mclose();
						});
					}
				});
				return false;
			}else {
				commit4();
				return false;
			}
		});
		
		$("#commit-5").click(function(){
			var id = $("#id").val();
			if(id != null && id != ""){
				$.post("${pageContext.request.contextPath}/adRiskevent/checkDelete?id=" + id, function(data) {
					var commitresult=JSON.parse(data);
					if(commitresult.result){
						commit5();
					}else{
						win.errorAlert('此数据已被删除！',function(){
							parent.location.reload();
							parent.mclose();
						});
					}
				});
				return false;
			}else {
				commit5();
				return false;
			}
		});	
	</script>
</html>
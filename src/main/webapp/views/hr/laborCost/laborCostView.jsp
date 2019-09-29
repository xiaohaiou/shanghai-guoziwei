<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>董事会考评信息查看页面</title>
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
		<c:when test="${op eq 'check'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看董事会考评信息数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报董事会考评信息数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
		<form:form id="form2" modelAttribute="entity" >
			<form:hidden id="entityid" path="id"/>
			<div class="label_inpt_div"><div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft" >${entity.year}</label> 
				<label class="w20">月份:</label>
				<label class="w25 setleft" >${entity.month}</label> 
				<label class="w20">单位:</label>
				<label class="w25 setleft" >${entity.company}</label> 
			
			</div>
			<h3 class="data_title1">董事会考评信息率</h3>
			<div class="model_part">
				<label class="w20">董事会考评信息1:</label>
				<label class="w25 setleft" >${entity.yearlyBudget}</label>
				
				<label class="w20">董事会考评信息2:</label>
				<label class="w25 setleft" >${entity.monthlyBudget}</label>
				
				<label class="w20">董事会考评信息3:</label>
				<label class="w25 setleft" >${entity.monthlyPerform}</label>
				
				<label class="w20">董事会考评信息4:</label>
				<label class="w25 setleft" >${entity.laborCost}</label> 
				
			</div>
			<h3 class="data_title1">董事会考评信息5</h3>
			<div class="model_part">
				<label class="w20">董事会考评信息6:</label>
				<label class="w25 setleft" >${entity.capitalization}</label> 
				
				<label class="w20">董事会考评信息7:</label>
				<label class="w25 setleft" >${entity.retainedProfits}</label> 
			
				<label class="w20">董事会考评信息8:</label>
				<label class="w25 setleft" >${entity.hrRateReturn}</label> 
				
			</div>
			<h3 class="data_title1">操作信息</h3>
			<div class="model_part">
				<label class="w20">创建人:</label>
				<label class="w25 setleft" >${entity.createPersonName}</label> 
				<label class="w20">创建时间:</label>
				<label class="w25 setleft" >${entity.createDate}</label> 
				<c:if test="${entity.status != 50112}">
					<label class="w20">上报人:</label>
					<label class="w25 setleft" >${entity.reportPersonName}</label> 
					<label class="w20">上报时间:</label>
					<label class="w25 setleft" >${entity.reportDate}</label> 
				</c:if>
			</div>
			
			<c:if test="${fn:length(requestScope.entityExamineview)>0}">
				<h3 class="data_title1">审核意见</h3>
				<c:forEach items="${ requestScope.entityExamineview}" var="sys" varStatus="status">
					<div class="model_part">
						<label class="w20">审核人:</label>
						<label class="w25 setleft">${ sys.createPersonName}</label> 
						<label class="w20">审核时间:</label>
						<label class="w25 setleft">${ sys.createDate}</label> 
						<label class="w20">审核结果:</label>
						<label class="w25 setleft">${ sys.examresultName}</label> 
						<label class="w20"></label>
						<label class="w25 setleft"></label> 
						<label class="w20" style="vertical-align:top;">审核意见:</label>
						<label class="w75 setleft" style="word-wrap:break-word;">${ sys.examinestr}</label> 
						
					</div>
				</c:forEach>
			</c:if>
			<div class="model_btn">
				<c:if test="${op eq 'report'}">
					<button class="btn btn-primary model_btn_ok" id="commit_1">上报</button>
				</c:if>
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
		
	
		$("#commit_1").click(function(){
			$.post("/shanghai-gzw/laborCost/postexam?id="+$("#entityid").val(),function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('上报成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr);
	 		 });
			 return false;
		})
	
		var example = $(".expmale");
		example.removeClass("example").remove();
		$(".btn.new").click(function(){
			$("table tbody").append(example.clone());
		});
		$("body").on("click","i.del.btn",function(){
			$(this).closest("tr").remove();
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
	</script>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>人力资源审核页面</title>
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
				<span class="glyphicon glyphicon-pencil"></span>资产信息采集审核
				<i class="iconfont icon-guanbi"></i>
			</h4>

		<form:form id="form2" modelAttribute="entity" >
			<form:hidden path="id"/>
			<div class="label_inpt_div"><div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft" >${entity.year}</label> 
				<label class="w20">月份:</label>
				<label class="w25 setleft" >${entity.month}</label> 
				<label class="w20">单位:</label>
				<label class="w25 setleft" >${entity.company}</label> 
			
			</div>
			
			<h3 class="data_title1">管理干部</h3>
			<div class="model_part">
				<label class="w20">男性人数（人）:</label>
				<label class="w25 setleft" >${entity.manNumber}</label> 
				<label class="w20">女性人数（人）:</label>
				<label class="w25 setleft" >${entity.womanNumber}</label> 
				<label class="w20">M序列人数（人）:</label>
				<label class="w25 setleft" >${entity.sequenceM}</label> 
				<label class="w20">非M序列人数（人）:</label>
				<label class="w25 setleft" >${entity.unSequenceM}</label> 
				<input type="hidden" value="${entity.id }" name="entityid" id="entityid">
			</div>
			<div class="model_part">
				<label class="w20" style="vertical-align: top;"><font color=red>*</font>审核意见:</label>
				<span class="w70"><textarea  rows="5" cols="40" name="examStr" id="examStr"></textarea>
					<span id="textareaLabel" style="color:red;display:block"></span>
				</span>
			</div>
			<div class="model_btn">
			    <c:if test="${entity.status!=50115}">
					<button class="btn btn-primary model_btn_ok" id="commit-1">通过</button>
			    </c:if>
				<button class="btn btn-primary model_btn_ok" id="commit-2">退回</button>
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
		//意见审核
		$("#examStr").on('change',function(){
			$("#textareaLabel").text("");
		});
		$("#commit-1").click(function(){
			if($("#examStr").val()==""){
				$("#textareaLabel").text("审核意见不可为空");
				return false;
			}
			$.post("/shanghai-gzw/managerialStaff/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('操作成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr,function(){
									parent.location.reload(true);
									parent.mclose();
							});
	 		 });
			 return false;
		})
		$("#commit-2").click(function(){
			if($("#examStr").val()==""){
				$("#textareaLabel").text("审核意见不可为空");
				return false;
			}
			$.post("/shanghai-gzw/managerialStaff/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('退回成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr,function(){
									parent.location.reload(true);
									parent.mclose();
							});
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
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>规章制度同步操作页面</title>
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
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<style type="text/css">
			.selectone{
				white-space: nowrap;
				font-size: 14px;
				cursor: pointer;
			}
			.selectone:hover, .selectone.selectedOne{
				box-sizing: border-box;
				background-color: rgba(255,255,255,0.8);
				border-bottom:1px solid rgba(0,0,0,0.5);
			}
			.selectone>* {
				display:inline-block;
				min-width: 60px;
			}
			.selectedOne{
				border-color:#4a22cc;
				color: #4a22cc;
			}
			#com_ztree span {
				padding-left:0;
			}
		</style>
		<style>
			table{
				table-layout: fixed;
			}
			table td{
				line-height:2em;
				word-wrap:break-word;
			}
		</style>
</head>
<body>
	
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>规章制度同步操作
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form id="form2">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">选择时间:</label>
				<input class="w25 time" type="text" name="year"  readonly="readonly"/>
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">同步</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	<form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/vaild.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		)
		function checkcommit()
		{	
			var form  = document.getElementById("form2");
			console.log(form.year.value);
			console.log(isEmpty(form.year.value));
			if(isEmpty(form.year.value)){
				form.year.focus();
				win.generalAlert("选择时间不能为空！");
				return false;
			}
			return true;
		}
		
		$("#commit-1").click(function(){
			$.blockUI({ message: '数据同步中...', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
			var url = "${pageContext.request.contextPath}/bylaw/opSynInYear";
			var formData = new FormData($("#form2")[0]);
			console.log(formData);
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
			     timeout:0,
		         cache: false,  
		         contentType: false,  
		         processData: false, 
		         dataType:'text', 
			     success : function(data) {
				     $.unblockUI();
				     if(data=='success'){
						 win.successAlert('保存成功！',function(){
								parent.location.reload(true);
								parent.mclose();
						 });
				     }else{
				     	win.generalAlert("其他人正在操作！");
				     }
			     },  
			     error : function(request,textStatus,errorThrown) {
			     	console.log(request);
			     	console.log(textStatus);
			     	console.log(errorThrown);
			     	win.generalAlert("同步失败！");
			     	$.unblockUI();
			     }  
			}); 
			return false;
		})

</script>
</html>
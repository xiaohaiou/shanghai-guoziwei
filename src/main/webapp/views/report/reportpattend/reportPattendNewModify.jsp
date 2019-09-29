<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>报表样式上传页面</title>
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
<style>
.inlineblock
{
	display:inline-block !important;
}

</style>

</head>

<body>

	<h4>
		<span class="glyphicon glyphicon-pencil"></span>上传报表样式
		<i class="iconfont icon-guanbi"></i>
	</h4>
		

		<form id="form2">
			
			<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">报表组类型:</label>
				<label class="w25 setleft">${group.name }</label>
				<label class="w20">报表名:</label>
				<label class="w25 setleft">${entity.formkindName }</label>
				<label class="w20"><span class="required"> * </span>样式文件:</label>
				<input type="file" class="w75 inlineblock"  id="pattendFile" name="pattendFile" >
				<label class="w20"><span class="required"> * </span>导出文件:</label>
				<input type="file" class="w75 inlineblock"  id="outFile" name="outFile" >
			
				<input type="hidden" name="formID" value="${formID}">
			</div>
		
			
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				
				<button class="btn btn-default model model_btn_close">关闭</button>
				
			</div>
		</div>
	   </form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">
			
		function checkcommit()
		{
			var pattend = $("#pattendFile");
			var out = $("#outFile");
			if(pattend.val()=="")
			{
				win.errorAlert("报表样式不能为空");
				return false;
			}
			if(out.val()=="")
			{
				win.errorAlert("导出文件不能为空");
				return false;
			}
			return true;
		}
				
		$("#commit-1").click(function(){
		debugger
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/reportpattend/save";
			
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     $.unblockUI();
			     var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('保存成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr);
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		})
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
	</script>
</html>
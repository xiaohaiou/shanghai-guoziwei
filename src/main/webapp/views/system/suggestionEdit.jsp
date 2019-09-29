<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>重点基建工程新增页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<style>
	#form2{
		padding:20px;
	}
	.model_btn{
		bottom:20px;
	}
	textarea{
		display:block;
		margin:0 auto;
		width:90%;
		resize:none;
	}
	label{
		line-height:3em;
	}
	.model_body {
	    height: 70%;
	}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body style="height:450px">
	
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>填写反馈意见
	</h4>
	
	<div class="model_body">
		<form id="form2">
			<div class="label_inpt_div">
				<label class="w20"><span class="required"> * </span>意见描述:</label>
				<textarea class="w70"  rows="4"  name="description">${suggestion.description }</textarea>
				<br>
				<label class="w20">附件:</label>
				<input class="w25" type="file" id="file" name="file" />
				<c:if test="${not empty suggestion.fileName}">
					<a class="w50" href="javascript:void(0);" onclick="parent.downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(suggestion.filePath,'\\','\\\\')}&_name=${suggestion.fileName }')">${suggestion.fileName}</a>
				</c:if>
				
			</div>
			
		<form>
	</div>
	<div class="model_btn">
		<input type="hidden" name="id" value="${suggestion.id }"/>
		<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
		<button class="btn btn-default model model_btn_close">关闭</button>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		
		function checkcommit()
		{	
			var form = document.getElementById("form2");
			if(isEmpty(form.description.value)){
				win.infoAlert("意见描述不能为空！");
				return false;
			}
			if(form.description.value.length > 1000){
				win.infoAlert("意见描述不能超过1000个字符！");
				return false;
			}
			var tag = $("#file")[0];
			if(!fileChange(tag)){
				return false;
			}
			return true;
		}
		
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{	
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			
			var url = "${pageContext.request.contextPath}/suggestion/_save";
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
				     $.unblockUI();
					 win.successAlert('保存成功！',function(){
						parent.location.reload(true);
				  		window.parent.mclose();
					 });
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		})
		
		//验证文件类型和大小
		function fileChange(target){  
			//检测上传文件的类型 
			var imgName = document.all.file.value;
		    var ext,idx; 
		    if(imgName != ''){
			    //检测上传文件的大小        
			    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
			    var fileSize = 0;           
			    if (isIE && !target.files){       
			        var filePath = target.value;       
			        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
			        var file = fileSystem.GetFile (filePath);       
			        fileSize = file.Size;      
			    } else {      
			        fileSize = target.files[0].size;       
			    }     
			
			    var size = fileSize / 1024*1024;   
			
			    if(size>(1024*1024*30)){ 
			    	 win.generalAlert("上传文件不能大于31M!"); 
			      	return false; 
			    }
			    return true;
		    
		    }else{
		    		return true;
		    } 
		}     
</script>
</html>
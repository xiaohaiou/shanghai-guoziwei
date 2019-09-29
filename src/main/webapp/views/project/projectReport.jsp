<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>重点基建工程编辑页面</title>
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
</head>
<body>
	
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>上报重点基建工程
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form id="form2">
		<div class="label_inpt_div">
			<h3 class="data_title1">项目基本信息</h3>
			<div class="model_part">
				<label class="w20">项目名称:</label>
				<label class="w25 setleft">${project.pjName}</label>
				<label class="w20">项目所在单位:</label>
				<label class="w25 setleft">${project.pjDept}</label>
				<label class="w20">项目整体进度(%):</label>
				<label class="w25 setleft">${project.pjProgress}</label>
				<label class="w20">项目合同付整体款进度(%):</label>
				<label class="w25 setleft">${project.totalContractProgress}</label>
				<br/>
				<label class="w20">描述:</label>
				<label class="w70 setleft" style="word-wrap:break-word;">${project.pjDiscription}</label>
				<br/>
				<label class="w20">请选择效果图:</label>
				<img src="${pageContext.request.contextPath}${project.imgPath}" style="height:100px;width:100px;">
				<br>
				<label class="w20">离线视频:</label>
				<span><a href="javascript:downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(project.video.pjVideoPath,'\\','\\\\')}&_name=${project.video.pjVideoName}')">${project.video.pjVideoName}</a></span>
				<br>
			</div>
			<h3 class="data_title1">节点信息列表</h3>
			<div class="model_part" id="nodeList">
			
			</div>
			<h3 class="data_title1">周报信息列表</h3>
			<div class="model_part" id="wkReportList">
				
			</div>
			
			<h3 class="data_title1">合同信息列表</h3>
			<div class="model_part" id="contractList">
				
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="report" >上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		
	
	<form>
	<jsp:include page="../system/modal.jsp"/>
	<script type="text/javascript">
		function initNodeList(v,pageNums){
			$.ajax({
				url:"${pageContext.request.contextPath}/project/"+v+"/_"+v+"ViewList?pageNums="+pageNums+"&pjId=${project.id}"+"&view=report",
				type:"POST",
				dataType:"text",
				async:false,
				success:function(data){
					$("#"+v+"List").children().remove();
					$("#"+v+"List").append(data);
				}
			});	
		}
		initNodeList('node',1);//初始化节点列表
		initNodeList('wkReport',1);//初始化周报列表
		initNodeList('contract',1);//初始化合同列表
	</script>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
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
		
		$("#report").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var url = "${pageContext.request.contextPath}/project/_report?id=${project.id}";
			$.ajax({  
			     url : url,  
			     type : "GET",  
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			    	 $.unblockUI();
				     if(data == 'success'){
						 win.successAlert('上报成功！',function(){
								parent.location.reload(true);
								parent.mclose();
						 });
				     }else if(data == 'fail'){
				     	win.errorAlert('该数据已被操作',function(){
				     		parent.location.reload(true);
							parent.mclose();
				     	});
				     }else if(data == 'isdel'){
				     	win.errorAlert('该数据已被删除',function(){
				     		parent.location.reload(true);
							parent.mclose();
				     	});
				     }
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
			return false;
		
		});
		
		function downloadFile(url){
			$.ajax({  
			     url : url,  
			     type : "POST",  
		         success : function(data) {
					var iframe = document.createElement("iframe");  
					iframe.src = url;  
					iframe.style.display = "none";  
					document.body.appendChild(iframe);
			     },  
			     error : function(data) {  
			     	alert("下载失败！");
			     }  
			});
		}	            
	</script>
</html>
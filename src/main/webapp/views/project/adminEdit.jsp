<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>重点基建工程排序及离线视频维护</title>
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
				}
		</style>
		
</head>
<body>
	
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>重点基建工程排序及离线视频维护
		<i class="iconfont icon-guanbi"></i>
	</h4>
	
	<div class="model_body">
		<form id="form2">
			<div class="label_inpt_div">
				<h3 class="data_title1">项目基本信息</h3>
				<div class="model_part">
					<label class="w20"><span class="required"></span>项目名称:</label>
					<label class="w25 setleft">${project.pjName}</label>
					<label class="w20"><span class="required"></span>项目所在单位:</label>
					<label class="w25 setleft">${project.pjDept }</label>
					<br/>
					<label class="w20"><span class="required"></span>项目排序:</label>
					<input class="w25" type="text" id="pjSort" name="pjSort" value="${project.pjSort }" check="Empty_int_3_._._._."/>
					<br>
					<label class="w20"><span class="required"></span>海建项目ID:</label>
					<input class="w25" type="text" id="outerPjId" name="outerPjId" value="${project.outerPjId }" check="Empty_string_255_._._._."/>
					<br>
					<label class="w20"><span class="required"></span>离线视频:</label>
					<input class="w25" type="file" id="pjVideo" name="pjVideo"/><span><a href="javascript:downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(project.video.pjVideoPath,'\\','\\\\')}&_name=${project.video.pjVideoName}')">${project.video.pjVideoName}</a></span>
					<br/>
				</div>
				<div class="model_btn">
					<input type="hidden" name="id" value="${project.id}" id="projectId"/>
					<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
			
			
		<form>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script src="<c:url value="/js/vaild.js"/>"></script>
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
		function checkcommit()
		{	
			var form  = document.getElementById("form2");
			var tag1 = $("#pjVideo")[0];
			if(!fileChange1(tag1)){
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
			
			
			var id = $("#projectId").val(); 
			var url1 = "${pageContext.request.contextPath}/project/_validate?id=" + id;
			var url = "${pageContext.request.contextPath}/project/_saveAdminEdit";
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
			     url : url1,  
			     type : "POST",  
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     	console.log(data);
					var project = eval("(" + data +")");
					console.log(project.isdel);
					console.log(project.reportStatus);
					if(project.isdel == 1){
						 $.unblockUI();
						win.errorAlert("该数据已被删除",function(){
							parent.location.reload(true);
							 window.parent.mclose();
						});
					}else{
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
									parent.mclose();
							 });
						     },  
						     error : function(data) {
						     	$.unblockUI();
						     }  
						}); 
					}
			     }
			}); 
			return false;
		})
		
		//验证文件类型和大小
		function fileChange1(target){  
			//检测上传文件的类型 
			var imgName = document.all.pjVideo.value;
		    var ext,idx;   
		    if (imgName != ''){  
		        idx = imgName.lastIndexOf(".");   
		        if (idx != -1){   
		            ext = imgName.substr(idx+1).toUpperCase();   
		            ext = ext.toLowerCase( ); 
		            
		            console.log("ext="+ext);
		            if (ext != 'mp4'){
		                win.generalAlert("只能上传.mp4类型的文件!"); 
		                return false;  
		            }   
		        } else {  
			           	win.generalAlert("只能上传.mp4类型的文件!"); 
			            return false;
		        }   
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
			    var size = fileSize / (1024*1024);   
			    if(size>15){ 
			    	 win.generalAlert("上传文件不能大于15M!"); 
			      	return false; 
			    }
		    }
		    
		    return true;
		} 
		
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
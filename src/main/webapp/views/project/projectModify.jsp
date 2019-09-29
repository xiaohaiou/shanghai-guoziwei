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
		<span class="glyphicon glyphicon-pencil"></span>修改重点基建工程
		<i class="iconfont icon-guanbi"></i>
	</h4>
	
	<div class="model_body">
		<form id="form2">
			
			<div class="label_inpt_div">
				<h3 class="data_title1">项目基本信息</h3>
				<div class="model_part">
					<label class="w20"><span class="required"> * </span>项目名称:</label>
					<input class="w25" type="text" name="pjName" value="${project.pjName}" check="NotEmpty_string_50_._._._." />
					<label class="w20"><span class="required"> * </span>项目所在单位:</label>
					<input type="hidden" name="pjDeptId" id="pjDeptId" value="${project.pjDeptId }" />
					<input class="w25" name="pjDept" id="pjDept" value="${project.pjDept }" readonly check="NotEmpty_._._._._._.">
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				    </div>
					<label class="w20"><span class="required"> * </span>项目整体进度(%):</label>
					<input class="w25" type="text" name="pjProgress" value="<fmt:formatNumber  value="${project.pjProgress}" pattern="##0"/>" check="NotEmpty_int_11_._0+_0_100" />
					<label class="w20"><span class="required"> * </span>项目合同付整体款进度(%):</label>
					<input class="w25" type="text" name="totalContractProgress" value="<fmt:formatNumber  value="${project.totalContractProgress}" pattern="##0"/>" check="NotEmpty_int_11_._0+_0_100" />
					<br/>
					<label class="w20">描述:</label>
					<textarea class="w70"  rows="4"  name="pjDiscription" check="Empty_string_2000_._._._." >${project.pjDiscription}</textarea>
					<!--  
					<br/>
					<label class="w20"><span class="required"> * </span>操作人ID:</label>
					<input class="w25" type="text" name="operatorId" check="NotEmpty_string_50_._._._." value="${project.operatorId}"/>
					<label class="w20"><span class="required"> * </span>操作人姓名:</label>
					<input class="w25" type="text" name="operatorName" check="NotEmpty_string_50_._._._." value="${project.operatorName}"/>
					<br/>
					<label class="w20"><span class="required"> * </span>审核人ID:</label>
					<input class="w25" type="text" name="approverId" check="NotEmpty_string_50_._._._." value="${project.approverId}"/>
					<label class="w20"><span class="required"> * </span>审核人姓名:</label>
					<input class="w25" type="text" name="approverName" check="NotEmpty_string_50_._._._." value="${project.approverName}"/>
					-->
					<br/>
					<label class="w20"><span class="required"></span>请选择效果图:</label>
					<input class="w25" type="file" id="pjFile" name="pjFile" check="._._._._._._." /><span><a href="javascript:downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(project.projectImgFile.filePath,'\\','\\\\')}&_name=${project.projectImgFile.fileName}')">${project.projectImgFile.fileName}</a></span>
					<br/>
					<label class="w20"><span class="required"></span>离线视频:</label>
					<input class="w25" type="file" id="pjVideo" name="pjVideo" check="NotEmpty_._._._._._." /><span><a href="javascript:downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(project.video.pjVideoPath,'\\','\\\\')}&_name=${project.video.pjVideoName}')">${project.video.pjVideoName}</a></span>
					
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
					<input type="hidden" name="id" value="${project.id}" id="projectId"/>
					<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
					<button class="btn btn-primary model_btn_ok" id="commit-2">保存并上报</button>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
			
			
		<form>
	</div>
	<jsp:include page="../system/modal.jsp"/>
	<script type="text/javascript">
		function initNodeList(v,pageNums){
			$.ajax({
				url:"${pageContext.request.contextPath}/project/"+v+"/_"+v+"List?pageNums="+pageNums+"&pjId=${project.id}",
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
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
	var timeoutId
	$('#pjDept').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId)
		timeoutId = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})

	var zTreeObj;
    var com_ztree_setting = {
		check:{
			enable:false,
			chkStyle: "checkbox",
			chkboxType: { "Y": "ps", "N": "ps" }
		},
		data:{
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		}
    };
    
    $(function () {
	     //所有企业数据	
	    var com_data = ${allCompanyTree};
    	zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
    	var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
		var nodes = treeObj.getNodes();
		
		//transformToArray()此方法获取所有节点（父节点和子节点）
   		var childrenNodes = treeObj.transformToArray(nodes);
   		if(childrenNodes[0]!=null)
   			treeObj.expandNode(childrenNodes[0],true);
    });

	$("#com_ztree").click(function(){
			setTimeout(function(){
   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
					{
						 $("#pjDeptId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
						 $("#pjDept").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
					}
		   	});
		
	})
</script>
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
			if(isEmpty(form.pjName.value)){
				form.pjName.focus();
				win.generalAlert("项目名称不能为空！");
				return false;
			}
			if(isEmpty(form.pjDept.value)){
				form.pjDept.focus();
				win.generalAlert("项目所在单位不能为空！");
				return false;
			}
			if(isEmpty(form.pjProgress.value)){
				form.pjProgress.focus();
				win.generalAlert("项目整体进度不能为空！");
				return false;
			}
			if(isEmpty(form.totalContractProgress.value)){
				form.totalContractProgress.focus();
				win.generalAlert("项目合同付整体款进度不能为空！");
				return false;
			}
			/*
			if(isEmpty(form.operatorId.value)){
				form.operatorId.focus();
				win.generalAlert("操作人ID不能为空！");
				return false;
			}
			if(isEmpty(form.operatorName.value)){
				form.operatorName.focus();
				win.generalAlert("操作人姓名不能为空！");
				return false;
			}
			if(isEmpty(form.approverId.value)){
				form.approverId.focus();
				win.generalAlert("审核人ID不能为空！");
				return false;
			}
			if(isEmpty(form.approverName.value)){
				form.approverName.focus();
				win.generalAlert("审核人姓名不能为空！");
				return false;
			}*/
			var tag = $("#pjFile")[0];
			if(!fileChange(tag)){
				return false;
			}
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
			var url = "${pageContext.request.contextPath}/project/_saveModify";
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
					}else if(project.reportStatus == 1){
						 $.unblockUI();
						win.errorAlert("该数据已被操作",function(){
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
		
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
			var id = $("#projectId").val(); 
			var url1 = "${pageContext.request.contextPath}/project/_validate?id=" + id;
			var url = "${pageContext.request.contextPath}/project/_saveandreport2";
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
					}else if(project.reportStatus == 1){
						 $.unblockUI();
						win.errorAlert("该数据已被操作",function(){
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
								 win.successAlert('上报成功！',function(){
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
		//v是id type是删除的类型
		function del(v,type){
			win.confirm("确定删除吗？",function(r){
				if(r){
					$.blockUI({ message: '提交中', css: { width: '275px' } });
					var url = '';
					var url1 = '';
					if(type == 'node'){
						url = "${pageContext.request.contextPath}/project/node/_delNode?id="+v;
						url1 = "${pageContext.request.contextPath}/project/node/_validate?id="+v;
					}else if(type == 'wkReport'){
						url = "${pageContext.request.contextPath}/project/wkReport/_delWkReport?id="+v;
						url1 = "${pageContext.request.contextPath}/project/wkReport/_validate?id="+v;
					}else if(type == 'contract'){
						url = "${pageContext.request.contextPath}/project/contract/_delContract?id="+v;
						url1 = "${pageContext.request.contextPath}/project/contract/_validate?id="+v;
					}
					$.ajax({  
					     url : url1,  
					     type : "POST",  
				         async: false,  
				         cache: false,  
				         contentType: false,  
				         processData: false,  
					     success : function(data) {
					     	console.log(data);
							var entity = eval("(" + data +")");
							console.log(entity.isdel);
							console.log(entity.reportStatus);
							if(entity.isdel == 1){
								 $.unblockUI();
								win.errorAlert("该数据已被删除",function(){
									 initNodeList(type,1);
								});
							}else{
								$.ajax({  
								     url : url,  
								     type : "POST",
								     async: false,  
							         cache: false,  
							         contentType: false,  
							         processData: false,  
								     success : function(data) {
									     $.unblockUI();
										 initNodeList(type,1);
										 win.successAlert('删除成功！');
								     },  
								     error : function(data) {
								     	$.unblockUI();
								     }  
								}); 
							}
					     }
					}); 
				}
			});
		}
		
		//node
		function editNodeValidate(id,pjId){
			var url = "${pageContext.request.contextPath}/project/node/_validate?id=" + id;
			$.post(url, function(data) {
				var node = eval("(" + data +")");
				if(node.isdel == 1){
					win.errorAlert("该数据已被删除",function(){
						initNodeList('node',1);//初始化节点列表
					});
				}else{
					mload('${pageContext.request.contextPath}/project/node/_modifyNode?id='+id+'&pjId='+pjId);
				}
			});
		}
		//weekReport
		function editWrValidate(id){
			var url = "${pageContext.request.contextPath}/project/wkReport/_validate?id=" + id;
			$.post(url, function(data) {
				var wkReport = eval("(" + data +")");
				if(wkReport.isdel == 1){
					win.errorAlert("该数据已被删除",function(){
						initNodeList('wkReport',1);//初始化周报列表
					});
				}else{
					mload('${pageContext.request.contextPath}/project/wkReport/_modifyWkReport?id=' + id);
				}
			});
		}
		
		//contract
		function editContractValidate(id){
			var url = "${pageContext.request.contextPath}/project/contract/_validate?id=" + id;
			$.post(url, function(data) {
				var contract = eval("(" + data +")");
				if(contract.isdel == 1){
					win.errorAlert("该数据已被删除",function(){
						initNodeList('contract',1);//初始化合同列表
					});
				}else{
					mload('${pageContext.request.contextPath}/project/contract/_modifyContract?id=' + id);
				}
			});
		}
		
		//验证文件类型和大小
		function fileChange(target){  
			//检测上传文件的类型 
			var imgName = document.all.pjFile.value;
		    var ext,idx;   
		    if (imgName != ''){  
		        idx = imgName.lastIndexOf(".");   
		        if (idx != -1){   
		            ext = imgName.substr(idx+1).toUpperCase();   
		            ext = ext.toLowerCase( ); 
		           // alert("ext="+ext);
		            if (ext != 'jpg' && ext != 'png' && ext != 'jpeg' && ext != 'gif'){
		                win.generalAlert("只能上传.jpg  .png  .jpeg  .gif类型的文件!"); 
		                return false;  
		            }   
		        } else {  
			           	win.generalAlert("只能上传.jpg  .png  .jpeg  .gif类型的文件!"); 
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
			
			    var size = fileSize / 1024*1024;   
			
			    if(size>(1024*1024*30)){ 
			    	 win.generalAlert("上传文件不能大于31M!"); 
			      	return false; 
			    }
		    }
		    
		    return true;
		} 
		
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
			    if(size > 15){ 
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
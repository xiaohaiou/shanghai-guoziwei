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
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-1.8.3.min.js"/>" charset="utf-8"></script>
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
		<span class="glyphicon glyphicon-pencil"></span>新增重点基建工程
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form id="form2">
		<div class="label_inpt_div">
			<h3 class="data_title1">项目基本信息</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>项目名称:</label>
				<input class="w25" type="text" id="ppjName" name="pjName" check="NotEmpty_string_50_._._._." />
				<label class="w20"><span class="required"> * </span>项目所在单位：</label>
				<input type="hidden" name="pjDeptId" id="pjDeptId" value="${project.pjDeptId }" />
				<input class="w25" name="pjDept" id="pjDept" value="${project.pjDept }" title="${project.pjDept }" readonly check="NotEmpty_._._._._._.">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			    </div>
				<label class="w20"><span class="required"> * </span>项目整体进度(%):</label>
				<input class="w25" type="text" name="pjProgress" check="NotEmpty_int_._._0+_0_100" />
				<label class="w20"><span class="required"> * </span>项目合同付款进度(%):</label>
				<input class="w25" type="text" name="totalContractProgress" check="NotEmpty_int_._._0+_0_100" />
				<br/>
				<label class="w20">描述:</label>
				<textarea class="w70"  rows="4"  name="pjDiscription" check="Empty_string_2000_._._._." ></textarea>
				<!--  
				<br/>
				<label class="w20"><span class="required"> * </span>操作人ID:</label>
				<input class="w25" type="text" name="operatorId" check="NotEmpty_string_50_._._._." />
				<label class="w20"><span class="required"> * </span>操作人姓名:</label>
				<input class="w25" type="text" name="operatorName" check="NotEmpty_string_50_._._._." />
				<br/>
				<label class="w20"><span class="required"> * </span>审核人ID:</label>
				<input class="w25" type="text" name="approverId" check="NotEmpty_string_50_._._._." />
				<label class="w20"><span class="required"> * </span>审核人姓名:</label>
				<input class="w25" type="text" name="approverName" check="NotEmpty_string_50_._._._." />
				-->
				<br/>
				<label class="w20"><span class="required"> * </span>请选择效果图:</label>
				<input class="w25" type="file" id="pjFile" name="pjFile" check="NotEmpty_._._._._._." />
				
				<label class="w20"><span class="required"></span>离线视频:</label>
				<input class="w25" type="file" id="pjVideo" name="pjVideo" check="NotEmpty_._._._._._." />
			</div>
			<h3 class="data_title1">节点信息列表<a class="model_btn_plus" id="addNode" onclick="mload('${pageContext.request.contextPath}/project/node/_addNode')")">+</a></h3>
			<div class="model_part">
				<div class="tablebox">
				<table >
					<tr class="table_header" id="nodeTableTr">
						<th></th>
						<th>节点名称</th>
						<th>计划完成时间</th>
						<th>节点进度</th>
						<th>节点状态</th>
						<th>操作</th>
					</tr>
				</table>
				</div>	
			 </div>
			 <h3 class="data_title1">周报信息列表<a class="model_btn_plus" id="addwkReport" onclick="mload('${pageContext.request.contextPath}/project/wkReport/_addWkReport')">+</a></h3>
			 <div class="model_part">
				<div class="tablebox">
					<table>
						<tr class="table_header" id="wkReportTableTr">
							<th></th>
							<th>周报标题</th>
							<th>年份</th>
							<th>周数</th>
							<th>日期范围</th>
							<th>周报附件</th>
							<th>周报状态</th>
							<th>操作</th>
						</tr>
					</table>
				</div>	
			 </div>
			 <h3 class="data_title1">合同信息列表<a class="model_btn_plus" id="addContract" onclick="mload('${pageContext.request.contextPath}/project/contract/_addContractTemp')">+</a></h3>
			<div class="model_part">
				<div class="tablebox">
				<table>
					<tr class="table_header" id="contractTableTr">
						<th></th>
						<th>合同名称</th>
						<th>合同主体甲方</th>
						<th>合同主体乙方</th>
						<th>签订日期</th>
						<th>标的金额</th>
						<th>已支付金额</th>
						<th>合同状态</th>
						<th>操作</th>
					</tr>
				</table>
				</div>	
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2">保存并上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	<form>
	<jsp:include page="../system/modal.jsp"/>
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
					//var entityBasicInfo = $('#form2').serialize();
			
			var url = "${pageContext.request.contextPath}/project/_saveAdd";
			var formData = new FormData($("#form2")[0]);
			console.log(formData);
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
				

			return false;
		})
		
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/project/_saveandreport1";
			
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
					//var pjId = data;
					//window.location.href='${pageContext.request.contextPath}/project/_reportDetail?id='+pjId;
					 win.successAlert('上报成功！',function(){
						parent.location.reload(true);
						parent.mclose();
				 	});
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		})
		//v是id type是删除的类型
		function delTemp(v,type){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var url = '';
			if(type == 'node')
				url = "${pageContext.request.contextPath}/project/node/_delNodeTemp?id="+v;
			else if(type == 'wkReport')
				url = "${pageContext.request.contextPath}/project/wkReport/_delWkReportTemp?id="+v;
			else if(type == 'contract')
				url = "${pageContext.request.contextPath}/project/contract/_delContractTemp?id="+v;
			$.ajax({  
			     url : url,  
			     type : "POST",
			     async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     $.unblockUI();
				 if(data=='success'){
					$("#"+type+v).remove();
					win.successAlert('删除成功！');
				 }else
					win.errorAlert('出错了！');
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
		}
		
		//验证文件类型和大小
		function fileChange(target){  
			//检测上传文件的类型 
			var imgName = document.all.pjFile.value;
		    var ext,idx;   
		    if (imgName == ''){  
		         win.generalAlert("请选择需要上传的文件!");  
		        return false; 
		    } else {   
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
			    if(size>15){ 
			    	 win.generalAlert("上传文件不能大于15M!"); 
			      	return false; 
			    }
		    }
		    
		    return true;
		}         
</script>
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
						 $("#pjDept").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
					}
		   	});
		
	})
</script>
</html>
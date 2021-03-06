<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>新增、编辑页面</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/css/modal.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe-sub.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/index-modal.css"/>">
	</head>
	<body>
　　		<div class = "model_content">
			<c:choose>
				<c:when test="${op eq 'modify'}">
					<h4><span class="glyphicon glyphicon-pencil"></span>编辑</h4>
				</c:when>
				<c:otherwise>
					<h4><span class="glyphicon glyphicon-pencil"></span>新增</h4>
				</c:otherwise>
			</c:choose>
			<div class="model_body">
				<style>
					.row>*{
						vertical-align: top;
					}
					select{
						border: 1px solid #ccc;
					    border-radius: 3px;
					    width: 16em;
				    	padding-left: 7px;
				    	height:28px;
					}
					.model_content textarea{
						width:100%;
						height:7em;
						border: 1px solid #ccc;
					    border-radius: 3px;
					}
					.w16{
						width:16%;
						padding:0 15px;
					}
					input[type="file"]{
						display:inline-block;
					}
					span.download:hover{
						padding-top:4px;
						padding-bottom:4px;
						padding-left:14px;
						padding-right:14px;
						border: 1px solid #ccc;
						border-radius: 3px;
					}
					i.del{
						color: #666;
					    margin-left: 6px;
					    font-style: normal;
					    font-weight: bolder;
					    vertical-align: middle;
					    padding:1px;
					    cursor:pointer;
					}
					i.del:hover{
						background-color:#eee;
					}
					textarea{
						white-space: normal;
					}
					html, body {
					    height: 100%;
					}
				</style>
				<form id="form2" action="" method="post">
					<div class="row"><label class="w16">系统全称<font color="red">*</font>:</label>
						<input type="text" name="sysFulName" value="${system.sysFulName}"/>
						<label class="w16">系统简称<font color="red">*</font>:</label>
						<input type="text" name="sysShortName" value="${system.sysShortName}"/>
					</div>
					
					<div class="row"><label class="w16">所属公司:</label>
						<select name="sysCompanyId">
							<option style="text-align:center" value="">---请选择---</option>
							<c:forEach items="${companys}" var="company" >
								<option value="${company.id}" <c:if test="${company.id == system.sysCompanyId}">selected=selected</c:if>>${company.companyName}</option>
							</c:forEach>
						</select>
						<label class="w16">排序<font color="red">*</font>:</label>
						<input type="text" name="sysOrder" value="${system.sysOrder}"/>
					</div>
					<div class="row"><label class="w16">系统链接URL:</label>
						<input style="width:60%" type="text" name="sysUrl" value="${system.sysUrl}"/>
					</div>
					<div class="row"><label class="w16">系统logo:</label>
						<input type="file" id="logo" name="logo" /> 
						<c:if test="${not empty system.sysLogoPath}">
							<span class="w16"><a href="javascript:downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(system.sysLogoPath,'\\','\\\\')}&_name=${system.sysLogoName}')">${system.sysLogoName}</a></span>
						</c:if>
						&nbsp;&nbsp;<span style="color:red;">注：只能上传.png或.jpg的图片</span>
					</div>
					
					<div class="row"><label class="w16">PC系统宣传图片:</label>
						<input type="file" id="pcXtsp" name="pcXtsp" /> 
						<c:if test="${not empty system.sysImgPath}">
							<span class="w16"><a href="javascript:downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(system.sysImgPath,'\\','\\\\')}&_name=${system.sysImgName}')">${system.sysImgName}</a></span>
						</c:if>
						&nbsp;&nbsp;<span style="color:red;">注：只能上传.png或.jpg的图片</span>
					</div>
					<div class="row"><label class="w16">系统宣传视频:</label>
						<input type="file" id="vedio" name="vedio" />
						<c:if test="${not empty system.sysVideoPath}">
							<span class="col-md-4"><a href="javascript:downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(system.sysVideoPath,'\\','\\\\')}&_name=${system.sysVideoName}')">${system.sysVideoName}</a></span>
						</c:if>
						&nbsp;&nbsp;<span style="color:red;">注：只能上传.mp4的视频</span>
					</div>
					<div class="row"><label class="w16">系统描述:</label>
					</div>
					<div class="row">
						<textarea rows="9" name="sysDecription">${system.sysDecription}</textarea>
					</div>
					<input type="hidden" id="id" name="id" value = "${system.id }">
					<input type="hidden" id="op" name="op" value = "${op}">
				</form>
				<div class="model_btn">
					<button class="btn btn-default model model_btn_close" onclick="parent.mclose();">关闭</button>
					<button class="btn btn-primary model_btn_ok" onclick="submitCode()">提交</button>
				</div>
			</div>
		</div>
		<div class="modal-container" data-remodal-id="modal" no=text>
			<iframe id="modal-frame" src="" style></iframe>
			<a href="javascript:mclose()" class="modal-close"><img src="../../css/icon/x.svg" alt=""></a>
		</div>
	</body>
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script src="<c:url value="/js/validation.js"/>"></script>
	<script type="text/javascript">
		// modal
		window.modal_load = window.mload = function(url, callback) {
			if (url) {
				var ifm = document.getElementById('modal-frame');
					$('>.modal-close',ifm.parentNode).css('display','block')
				ifm.onload = function() {
					ifm.onload = undefined;
					$('>.modal-close',ifm.parentNode).css('display','')
					if (callback) {
						setTimeout(callback.bind(ifm.contentWindow))
					}
				};
				ifm.src = url;
				$('[data-remodal-id=modal]').remodal({
					closeOnEscape: false,
					closeOnOutsideClick: false,
				}).open();
			}
		}
		
		window.modal_close = window.mclose = function() {
			var ifm = $('[data-remodal-id=modal] iframe')[0];
			$('[data-remodal-id=modal]').remodal().close();
			ifm.src = '';
			ifm.style.height = '';
		}
		
		function submitCode(){
			var form = document.forms[0];
			//验证
			if(isEmpty(form.sysFulName.value)){
				 parent.parent.win.generalAlert("系统全称不能为空！");
				 return false;
			}
			if(isEmpty(form.sysShortName.value)){
				 parent.parent.win.generalAlert("系统简称不能为空！");
				 return false;
			}
			if(isEmpty(form.sysOrder.value)){
				 parent.parent.win.generalAlert("排序不能为空！");
				 return false;
			}
			if(notNumber(form.sysOrder.value)){
				 parent.parent.win.generalAlert("排序为数字！");
				 return false;
			}
			if(form.sysDecription.value.length > 2000){
				 parent.parent.win.generalAlert("系统描述不能超过2000个字符！");
				 return false;
			}
			var formData = new FormData(form);
			var url = "${pageContext.request.contextPath}/bimCenterSystem/_saveOrModify";
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,
		         dataType:'JSON',  
			     success : function(data) {
				     if(data.result == "success"){
				    	 parent.parent.win.successAlert("操作成功！");
			     		window.parent.location.reload();
			     	}else if(data.result == "fail"){
			     		parent.parent.win.errorAlert(data.resultInfo);
			     	}
			     },  
			     error : function(data) {
			     	parent.parent.win.errorAlert("操作失败！");
			     }  
			}); 
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
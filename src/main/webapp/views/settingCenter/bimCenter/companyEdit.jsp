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
				</style>
				
				<form id="form2" action="" method="post">
					<div class="row"><label class="w16">公司名称：<font color="red">*</font>:</label>
						<input type="text" name="companyName" value="${company.companyName}"/>
					</div>
					<div class="row"><label class="w16">应用位置：<font color="red">*</font>:</label>
						<select name="type">
							<option value="">--请选择--</option>
							<option value="0" <c:if test="${company.type == 0 }">selected = selected</c:if>>web端应用中心</option>
							<option value="1" <c:if test="${company.type == 1 }">selected = selected</c:if>>移动端应用中心</option>
						</select>
					</div>
					<div class="row"><label class="w16">排序：<font color="red">*</font>:</label>
						<input type="text" name="companyOrder" value="${company.companyOrder}"/>
					</div>
					<input type="hidden" id="id" name="id" value = "${company.id }">
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
			if(isEmpty(form.companyName.value)){
				 parent.parent.win.generalAlert("公司名称不能为空！");
				 return false;
			}
			if(isEmpty(form.type.value)){
				 parent.parent.win.generalAlert("应用位置不能为空！");
				 return false;
			}
			if(isEmpty(form.companyOrder.value)){
				 parent.parent.win.generalAlert("排序不能为空！");
				 return false;
			}
			if(notNumber(form.companyOrder.value)){
				 parent.parent.win.generalAlert("排序为数字！");
				 return false;
			}
			var formData = new FormData(form);
			var url = "${pageContext.request.contextPath}/bimCenterCompany/_saveOrModify";
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
				     if(data.toString() == "success"){
				    	 parent.parent.win.successAlert("操作成功！");
			     		window.parent.location.reload();
			     	}else if(data.toString() == "fail"){
			     		parent.parent.win.errorAlert("操作失败！");
			     	}else if(data.toString() == "changeError"){
			     		parent.parent.win.errorAlert("存在已设置的系统，应用位置不能切换！");
			     	}
			     },  
			     error : function(data) {
			     	parent.parent.win.errorAlert("操作失败！");
			     }  
			}); 
		} 
		
	</script>
</html>
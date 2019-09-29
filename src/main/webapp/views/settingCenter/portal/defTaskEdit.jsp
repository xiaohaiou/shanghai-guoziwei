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
				</style>
				<form id="form2" action="" method="post">
					<div class="row"><label class="col-md-1">功能模块代码<font color="red">*</font>:</label>
						<input type="text" name="type" value="${portalDefTask.type}"/>
					</div>
					<div class="row"><label class="col-md-1">功能模块名称<font color="red">*</font>:</label>
						<input type="text" name="typeName" value="${portalDefTask.typeName}"/>
					</div>
					<div class="row"><label class="col-md-1">指标代码<font color="red">*</font>:</label>
						<input type="text" name="mapId" value="${portalDefTask.mapId}"/>
					</div>
					<div class="row"><label class="col-md-1">指标名称<font color="red">*</font>:</label>
						<input type="text" name="mapName" value="${portalDefTask.mapName}"/>
					</div>
					<div class="row"><label class="col-md-1">接收人<font color="red">*</font>:</label>
						<input type="text" id="vcName" name="vcName" value="${portalDefTask.vcName}" readonly="readonly"/>
						<input type="hidden" id="slctPersons" name="slctPersons" value="${portalDefTask.vcName}"/>
						<input type="hidden" id="accountNameDeps" name="accountNameDeps" value="${portalDefTask.accountNameDeps}"/>
						<a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/task/instruction/_select')" class="btn" title="选择处理人"><img src="${pageContext.request.contextPath}/css/icon/+.svg" alt=""></a>
					</div>
					<div class="row"><label class="col-md-1">抄送人<font color="red">*</font>:</label>
						<input type="text" id="sbvcName" name="sbvcName" value="${portalDefTask.sbvcName}" readonly="readonly"/>
						<input type="hidden" id="sbslctPersons" name="sbslctPersons" value="${portalDefTask.sbslctPersons}"/>
						<input type="hidden" id="sbaccountNameDeps" name="sbaccountNameDeps" value="${portalDefTask.sbaccountNameDeps}"/>
						<a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/task/instruction/_selectdd')" class="btn" title="选择抄送人"><img src="${pageContext.request.contextPath}/css/icon/+.svg" alt=""></a>
					</div>
					<div class="row"><label class="col-md-1">配置人<font color="red">*</font>:</label>
						<input type="text" id="vcEmployeeName" name="vcEmployeeName" value="${portalDefTask.vcEmployeeName}" readonly="readonly"/>
						<input type="hidden" id="vcEmployeeId" name="vcEmployeeId" value="${portalDefTask.vcEmployeeId}"/>
						<a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/task/instruction/_selectOne')" class="btn" title="选择配置人"><img src="${pageContext.request.contextPath}/css/icon/+.svg" alt=""></a>
					</div>
					<div class="row"><label class="col-md-1">主题:</label>
						<textarea style="border:1px solid rgb(204, 204, 204);background-color:#fff;" name="STheme" maxlength="100" rows="4">${portalDefTask.STheme}</textarea>
					</div>
					<input type="hidden" id="id" name="id" value = "${portalDefTask.id }">
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
			if(isEmpty(form.type.value)){
				 parent.parent.win.generalAlert("功能模块代码不能为空！");
				 return false;
			}
			if(notNumber(form.type.value)){
				 parent.parent.win.generalAlert("功能模块代码为数字！");
				 return false;
			}
			if(isEmpty(form.typeName.value)){
				 parent.parent.win.generalAlert("功能模块名称不能为空！");
				 return false;
			}
			if(form.typeName.value.length > 50){
				 parent.parent.win.generalAlert("功能模块名称长度不能超过50个字！");
				 return false;
			}
			if(isEmpty(form.mapId.value)){
				 parent.parent.win.generalAlert("指标代码不能为空！");
				 return false;
			}
			if(form.mapId.value.length > 50){
				 parent.parent.win.generalAlert("指标代码长度不能超过50个字！");
				 return false;
			}
			if(isEmpty(form.mapName.value)){
				 parent.parent.win.generalAlert("指标名称不能为空！");
				 return false;
			}
			
			if(isEmpty(form.slctPersons.value)){
				 parent.parent.win.generalAlert("接收人不能为空！");
				 return false;
			}
			if(isEmpty(form.sbslctPersons.value)){
				 parent.parent.win.generalAlert("抄送人不能为空！");
				 return false;
			}
			if(form.mapName.value.length > 50){
				 parent.parent.win.generalAlert("指标名称长度不能超过50个字！");
				 return false;
			}
			if(form.STheme.value.length > 100){
				 parent.parent.win.generalAlert("指标名称长度不能超过100个字！");
				 return false;
			}
			if(isEmpty(form.vcEmployeeId.value)){
				 parent.parent.win.generalAlert("配置人不能为空！");
				 return false;
			}
			var formData = new FormData(form);
			var url = "${pageContext.request.contextPath}/portal/manage/_saveOrUpdate";
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
			     		parent.parent.win.errorAlert("存在功能模块代和码指标代码和配置人都相同的记录！");
			     	}
			     },  
			     error : function(data) {
			     	win.errorAlert("操作失败！");
			     }  
			}); 
		} 
		
	</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<!-- 库|插件 -->
	<link rel="stylesheet" href="<c:url value="/css/remodal.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/selectize.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
	<!-- 页面 -->
	<link rel="stylesheet" href="<c:url value="/css/modal.css"/>">
	<!-- 库 -->
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/ajaxfileupload.js"/>"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/standalone-selectize.min.js"/>" charset="utf-8"></script><!-- 库 select-->
	<script src="<c:url value="/js/jquery.jedate.min.js"/>" charset="utf-8"></script><!-- 库 time-->
</head>
<body>
	<header no=text>
		<span class="title">消息信息</span>
		<a href="javascript:window.parent.mclose()" class="exit"><img src="../../css/icon/x.svg" alt=""></a>
	</header>
	<form id="form1" class="body form" action="index.html" method="post">
		<div class="form-group" no=text>
			<div class="title">消息标题：</div>
			<div class="input">
				<input type="text" id="title" name="title" maxlength="100" value="${portalNews.title }" />
			</div>
		</div>
		<div class="form-group" no=text>
			<div class="title">消息内容：</div>
			<div class="input">
				<textarea name="description" id="description" rows="12" cols="80" maxlength="1000" >${portalNews.description  }</textarea>
			</div>
		</div>
		
		<div class="form-group" no=text style="text-align:right;">
			<input type="hidden" name="id" value="${portalNews.id}" />
			<input type="hidden" name="date" value="${portalNews.date}" />
			<input type="hidden" name="type" value="${portalNews.type}" />
			<input type="hidden" name="isIssue" value="${portalNews.isIssue}" />
			<input type="hidden" name="delFlag" value="${portalNews.delFlag}" />
			<input type="hidden" name="creator" value="${portalNews.creator}" />
			<input type="hidden" name="createTime" value="${portalNews.createTime}" />
			<div onclick="checkSubmit();" class="btn bg-red">消息发布</div>
		</div>
	</form>
	
	<!-- 优先处理 -->
	<script type="text/javascript">
		// 清理空格
		var toArray = Array.from || function(arrayLike){
			return [].slice.call(arrayLike)
		};
		toArray(document.body.querySelectorAll('[no~=text]')).forEach(function(el) {
			toArray(el.childNodes).forEach(function(e) {
				if (e.nodeType == 3) {
					el.removeChild(e)
				}
			})
		})
		// resize img
		toArray(document.body.querySelectorAll('.avatar>img')).forEach(function(el) {
			if (el.naturalWidth < el.naturalHeight) {
				el.style.width = "100%";
				el.style.height = "auto";
			}
		})
	</script>
	
	<script>
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
		
		function checkSubmit() {
			var form = document.forms[0];
			if (form.title.value == '') {
				alert('消息标题不能为空！');
				return false;
			}
			if(form.title.value.length > 50){
				alert('消息标题最多填写50个字！');
				return false;
			}
			if (form.description.value == '') {
				alert('消息内容不能为空！');
				return false;
			}
			if(form.description.value.length > 255){
				alert('消息内容最多填写255个字！');
				return false;
			}
			var formData = new FormData(form);
			var url = "${pageContext.request.contextPath}/portal/news/_save";
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
				     	window.parent.location.reload();
			     	}else{
			     		alert("消息发布失败！");
			     	}
			     },  
			     error : function(data) {
			     	alert("消息发布失败！");
			     }  
			}); 
		}
		
		// 初始化 selectize
		$('.selectize').selectize({});

		// 初始化 时间
		$('.input input.time').jeDate({
			format:"YYYY-MM-DD",
		})
	</script>
</body>
</html>
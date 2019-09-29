<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>日常工作管理</title>
	<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
	<!-- 页面 -->
	<link rel="stylesheet" href="<c:url value="/css/modal.css"/>">
	<!-- 库 -->
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/jquery.jedate.min.js"/>" charset="utf-8"></script><!-- 库 time-->
</head>

<body>
	<header no=text>
		<span class="title">日常工作</span>
		<a href="javascript:window.parent.mclose()" class="exit"><img src="<c:url value="/css/icon/x.svg"/>" alt=""></a>
	</header>
	<form class="body form" id="form1" method="post">
		<div class="form-group" no=text>
			<div class="title">主题 Topic：</div>
			<div class="input">
				<input type="text" name="title" id="title" value="${portalWork.title }" placeholder="">
			</div>
		</div>
		<div class="form-group" no=text>
			<div class="title">时间 Time：</div>
			<div class="input">
				<input class="time" type="text" name="date" id="date" value="${portalWork.date }">
			</div>
		</div>
		<div class="form-group" no=text>
			<div class="title">内容 Content：</div>
			<div class="input">
				<textarea name="description" rows="8" cols="80">${portalWork.description }</textarea>
			</div>
		</div>
		<div class="form-group" no=text style="text-align:right;">
			<!-- <div onclick="checkSubmit();" class="btn bg-red">保存save</div> -->
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
</body>
</html>
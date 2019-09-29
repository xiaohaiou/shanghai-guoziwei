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
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
	<!-- 库 -->
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/ajaxfileupload.js"/>"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/standalone-selectize.min.js"/>" charset="utf-8"></script><!-- 库 select-->
	<script src="<c:url value="/js/jquery.jedate.min.js"/>" charset="utf-8"></script><!-- 库 time-->
	<style>
		tr{
			height: 50px;
		}
		
		table {
			margin-bottom: 30px;
		}
	</style>
</head>
<body>
	<header no=text>
		<span class="title">工作日程</span>
		<a href="javascript:window.parent.mclose()" class="exit"><img src="../../css/icon/x.svg" alt=""></a>
	</header>
	<form id="form1" class="body form" action="index.html" method="post">
		
		<table class="detail table-bordered col-xs-12">
				<tr>
					<th>日期</th>
					<th>主题</th>
				</tr>
				<c:if test="${!empty workList }">
					<c:forEach items="${workList }" var="work" varStatus="status">
						<tr>
							<td>${work.date }</td>
							<td>${work.title }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty workList}">
					<tr>
						<td colspan="2" align="center">
							查询无记录
						</td>
					</tr>
				</c:if>
		</table>
		
		<div class="form-group" no=text style="text-align:right;">
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
		
		// 初始化 selectize
		$('.selectize').selectize({});

		// 初始化 时间
		$('.input input.time').jeDate({
			format:"YYYY-MM-DD",
		})
	</script>
</body>
</html>
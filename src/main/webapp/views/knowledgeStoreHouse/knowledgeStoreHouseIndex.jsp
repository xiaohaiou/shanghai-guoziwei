<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, user-scalable=no" />
	<title>知识库</title>
	<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>"/>
	<!-- 库|插件 -->
	<!-- 新加样式 -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/knowledgeBase.css"/>">
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<style type="text/css">
		* {
			box-sizing: border-box;
		}
		body {
			background-color: #4d4d4d;
		}
		.center {
			text-align: center;
		}
		.bg-book {
			text-align: center;
			width: 100%;
			height: calc(100vh - 270px);
			background-image: url(<c:url value="/img/knowledgeBase.jpg"/>);
			background-repeat: no-repeat;
			background-position: 50% 50%;
			background-size: cover;
		}
		.search-content {
			display: block;
			color: #555;
			font-size: 16px;
			line-height: 18px;
			height: 50px;
			width: 600px;
			padding: 16px 22px;
			box-shadow: 0 0 3px #999 inset;
		}
		.search-content::placeholder {
			color: #999;
		}
		.search {
			display: block;
			color: #fff;
			font-size: 16px;
			line-height: 18px;
			height: 50px;
			min-width: 115px;
			padding: 16px;
			margin-left: 8px;
			background-color: #fe8f01;
		}
		.p2 {
			width: 100%;
			position: absolute;
			top: calc( 50% - 25px);
		}
		.keys {
			text-align: left;
			margin: auto;
			width: 720px;
		}
		.keys a {
			display: inline-block;
			text-decoration: underline;
			font-size: 14px;
			color: #ccc;
			text-shadow: 0 0 1px #666;
			min-width: 74px;
			margin: 24px 24px 0 0;
		}
		.keys a:hover {
			text-decoration: underline;
			color: #fe8f01;
		}
		.feature{
			padding-top: 40px;
			padding-bottom: 20px;
			min-height: 200px;
			background-color: #fff;
			text-align: center;
		}
		.feature .item{
			box-sizing: content-box;
			width: 174px;
			padding: 0 32px;
		}
		.feature .item:hover{
			box-shadow: 1px 1px 10px #363f58;
			cursor: default;
		}
		.feature .icon{
			height: 64px;
			width: 100%;
		}
		.feature .icon img{
			max-width: 100%;
			max-height: 100%;
		}
		.feature .icon i{
			font-size: 50px;
			color: #2f374e;
		}
		.feature .f-title{
			line-height: 34px;
			font-size: 16px;
			color: #555;
		}
		.feature .f-info{
			line-height: 1.4;
			font-size: 13px;
			color: #bbb;
			word-break: break-all;
		}
		.inline-m{
			display:inline-block;
			vertical-align: middle;
		}
	</style>
</head>
<body onkeydown="keyOnClick(event);">
	<div class="center">
		<jsp:include page="/views/public/title.jsp"></jsp:include>
		<div class="bg-book" style="position:relative;">
			<div class="p2">
				<div class="table" style="margin:auto">
					<c><input class="search-content" type="text" id="documentName" placeholder="请输入文件名" value="<c:out value="${entity.documentName}"></c:out>"></c>
					<c><input type="button" class="search" id="queryBtn" value="开始搜索"></c>
				</div>
			</div>
		</div>
		<div class="feature">
			<ul class="table" style="display:inline-table;">
				<li class="item">
					<div class="icon">
						<a href="${pageContext.request.contextPath}/knowledgeStoreHouse/moduleList?type=cw" target="_blank"><i class="iconfont icon-caiwu1"></i></a>
					</div>
					<div class="f-title"><span>财务</span></div>
				</li>
				<li class="item">
					<div class="icon">
						<a href="${pageContext.request.contextPath}/knowledgeStoreHouse/moduleList?type=xzbg" target="_blank"><i class="iconfont icon-hangzheng"></i></a>
					</div>
					<div class="f-title"><span>行政办公</span></div>
				</li>
				<li class="item">
					<div class="icon">
						<a href="${pageContext.request.contextPath}/knowledgeStoreHouse/moduleList?type=rlzy" target="_blank"><i class="iconfont icon-renliziyuan"></i></a>
					</div>
					<div class="f-title"><span>人力资源</span></div>
				</li>
				<li class="item">
					<div class="icon">
						<a href="${pageContext.request.contextPath}/knowledgeStoreHouse/moduleList?type=cgsj" target="_blank"><i class="iconfont icon-caigouguanli1"></i></a>
					</div>
					<div class="f-title"><span>采购数据</span></div>
				</li>
				<li class="item">
					<div class="icon">
						<a href="${pageContext.request.contextPath}/knowledgeStoreHouse/moduleList?type=szsj" target="_blank"><i class="iconfont icon-shzr"></i></a>
					</div>
					<div class="f-title"><span>社责数据</span></div>
				</li>
				<li class="item">
					<div class="icon">
						<a href="${pageContext.request.contextPath}/knowledgeStoreHouse/moduleList?type=fxkz" target="_blank"><i class="iconfont icon-fk"></i></a>
					</div>
					<div class="f-title"><span>风险控制</span></div>
				</li>
				<%-- <li class="item">
					<div class="icon">
						<a href="${pageContext.request.contextPath}/knowledgeStoreHouse/moduleList?type=zsk" target="_blank"><i class="iconfont icon-vip-book"></i></a>
					</div>
					<div class="f-title"><span>知识库</span></div>
				</li>
				<li class="item">
					<div class="icon">
						<a href="${pageContext.request.contextPath}/knowledgeStoreHouse/list?type=zsk" target="_blank"><i class="iconfont icon-menhuhoutai"></i></a>
					</div>
					<div class="f-title"><span>后台</span></div>
				</li> --%>
			</ul>
		</div>
	</div>
	<jsp:include page="../system/modal.jsp"/>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
	function search(){
		var documentName = $("#documentName").val();
		if(documentName == ""){
			win.errorAlert('请输入文件名！');
		}else{
			var url = "${pageContext.request.contextPath}/knowledgeStoreHouse/searchKnowledgeData?documentName="+documentName;
			mload(url);
		}
	};
	$("#queryBtn").click(function() {
		search();
	});
	function keyOnClick(e){
	    var theEvent = window.event || e;
	    var code = theEvent.keyCode || theEvent.which;
	    if (code==13) {  //回车键的键值为13
	        search();  //调用搜索事件
	    }
	}
	
	if('${param.documentName}' != ''){
		search();
	}
</script>
</html>

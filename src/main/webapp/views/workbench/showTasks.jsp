<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>${title}</title>
	<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
	<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
	<!-- 库 -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/font/iconfont.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bimc.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/header.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
	
	<style>
	body{
		overflow-x: hidden;
	}
	.search{
		text-align: right;
		padding: 20px;
	    /* border-bottom: 1px solid #ccc; */
	}
	.search input[name="taskName"]{
	    border-radius: 5px;
	    border: 1px solid #ccc;
	    margin-right: 20px;
	    height: 32px;
	    display: inline-block;
	    line-height: 32px;
        width: 20em;
    	margin-left: 10px;
	}
	.search input[value="查询"]{
		border-radius: 5px;
	    margin-right: 20px;
	    display: inline-block;
	    line-height: 32px;
	    padding: 0 13px;
	    border: 1px solid #ccc;
	}
	.search input[value="查询"]:hover{
		background:#ccc;
	}
	.clearfix {
	    text-align: right;
	}
	.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
	    background-color: #25a8dc;
	    border-color: #25a8dc;
	}
	</style>
</head>
<body>
	<jsp:include page="/views/public/title.jsp"></jsp:include>
	<script type="text/javascript">
		var to_def_img = function(el){
			el.src = "<c:url value="/img/head.png"/>";
		};
	</script>
	<form id="form1"  class="row"  >
		<div class="search">
		<span>创建时间：</span>
		<span class="date_border">
			<input placeholder="起始时间" style="width:120px;text-align:center;cursor: pointer;" type="text" class="query-time" name="createStartTime" id="createStartTime" value="${instruction.createStartTime}"/> ~
			<input placeholder="结束时间" style="width:120px;text-align:center;cursor: pointer;" type="text" class="query-time" name="createEndTime" id="createEndTime" value="${instruction.createEndTime}"/>
		</span>
		<span style="margin-left: 40px;">截止时间：</span>
		<span class="date_border">
			<input placeholder="起始时间" style="width:120px;text-align:center;cursor: pointer;" type="text" class="query-time" name="deadlineStartTime" id="deadlineStartTime" value="${instruction.deadlineStartTime}"/> ~
			<input placeholder="结束时间" style="width:120px;text-align:center;cursor: pointer;" type="text" class="query-time" name="deadlineEndTime" id="deadlineEndTime" value="${instruction.deadlineEndTime}"/>
		</span>
		<span style="margin-left: 40px;">指令名称：</span>
		<span class="">
			<input type="text" name="taskName" value="${instruction.taskName}" />
			<input type="hidden" name="num" value="${num}" />
		</span>
			<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
		</div>
	</form>
	<div class="module_body" id="tasklist">
		<c:if test="${not empty results}">
			<c:forEach items="${results}" var="t" varStatus="status">
				<div class="module_list" id="${status.index+1}">
					<div>
						${(pageNum-1)*10 + status.index+1}
					</div>
					<div class="list_avater">
						<a><img src="http://file.hna.net/image/headimage/emp/${fn:substring(t.creator,0,6)}0000/${t.creator}.jpg" height="102" width="104"
							onerror="to_def_img(this)" title="系统管理员"> </a>
					</div>
					<div class="list_text">
						<p class="list_title">
							<span><a class="a_title">${t.taskName }</a>
							</span><i class="iconfont icon-shijian"></i><span class="format">${t.taskProgress.creatorTime }</span>
						</p>
						<p class="list_body">
							<a class="list_name">${t.taskProgress.createName}说：</a><span><a class="a_content">${t.taskProgress.progressRemark}</a>
							</span>
						</p>
					</div>
					<div class="list_progress">
						<p>任务截至时间：${t.deadLine}</p>
					</div>
					<div class="list_btn">
						<a href="javascript:;"
							onclick="openNewWindow('${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&amp;url=/bim_task/system/indexFirst?fromsystem=detail%26taskId=${t.id}')"><span>任务处置</span>
						</a>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<jsp:include page="page.jsp"/>
	</div>
</body>
<script type="text/javascript" src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
<script src="<c:url value="/js/jquery.jedate.min.js"/>" charset="utf-8"></script><!-- 库 time-->
<script>
	// 初始化 时间
	$('input.query-time').jeDate({
		format:"YYYY-MM-DD"
	})
	
	function openNewWindow(url) {
		window.open(url, "_blank");
	}	
	
	$(".format").each(function(){
		$(this).text(getDateDiff($(this).text()));
	});
	
	function _query(){
		var url = '${searchUrl}'+'&pageNum=1';
		var form = document.forms[0];
		form.action = url;
		form.method = "post";
		form.submit();		
	}
</script>
</html>

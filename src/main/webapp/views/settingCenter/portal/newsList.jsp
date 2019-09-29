<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>工作台</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">
		<style type="text/css">
			/*modal*/
			.remodal-wrapper {
				padding: 0px;
			}
			
			.modal-container {
				position: relative;
				padding: 0;
				margin: 0;
				height: 100%;
				background-color: rgba(0, 0, 0, 0);
				max-width: 900px;
			}
			
			[data-remodal-id=modal] iframe {
				position: relative;
				width: 100%;
				max-height: 100%;
			}
			
			.modal-close {
				display: none;
				position: absolute;
				height: 20px;
				right: 12px;
				top: 12px;
			}
			
			.modal-close>img {
				height: 100%;
			}
			
			.remodal-overlay.night-theme {
				background-color: #f44336;
			}
			
			.remodal.night-theme {
				background: #fff;
			}
		</style>
	</head>
	<body>
		<div class="top-title d-position ">
			<h1 style="padding-top:30px;">消息中心<a class="bg-lightgreen"  href="javascript:;" onclick="mload('${pageContext.request.contextPath}/portal/news/_add')">新增</a></h1>
		</div>
		
		<div class="middle-content clearfix">
			
			<div class="white-bg">
				<span class="title">消息中心列表</span>
				<div class="seachbox ">
					<div class="col-sm-4">
					</div>
					<div class="col-sm-4"></div>
					<div class="col-sm-4">
						<form id="form1" action="${pageContext.request.contextPath}/portal/news/_query" modelAttribute="portalNews" >
							<input type="submit" id="query" name="query" style="display:none"/>
							<div class="input-box">
								<input placeholder="消息标题" type="text" id="title" name="title" value="${portalNews.title }" class="form-control">
								<a href="javascript:;" onclick="document.getElementById('query').click();">
									<i class="iconfont icon-sousuo"></i>
								</a>
							</div>
						</form>
					</div>
				</div>
				<div class="tablebox">
					<table>
						<thead>
							<tr>
								<th>序号</th>
								<th>消息标题</th>
								<th>消息内容</th>
								<th>发布时间</th>
								<th>是否发布</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty requestScope.newsList }">
								<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
								<c:forEach items="${ requestScope.newsList}" var="news" varStatus="status">
									<tr>
										<td>
											${recordNumber + status.index + 1 }
										</td>
										<td>
											${news.title }
										</td>
										<td>
											${news.description }
										</td>
										<td>
											${news.date }
										</td>
										<td>
											<c:if test="${news.isIssue == 1}">
												是
											</c:if>
											<c:if test="${news.isIssue == 0}">
												否
											</c:if>
										</td>
										<td>
											<c:if test="${news.isIssue == 0}">
												<a href="javascript:;" onclick="issue('${news.id}')">发布</a>
											</c:if>
											<c:if test="${news.isIssue == 1}">
												<a href="javascript:;" onclick="issueNo('${news.id}')">撤回</a>
											</c:if>
											<a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/portal/news/_edit?id=${news.id }')">编辑</a>
											<a href="javascript:;" onclick="del('${news.id}')">删除</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty requestScope.newsList}">
								<tr>
									<td colspan="7" align="center">
										查询无记录
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<div class="clearfix"> 
						<ul class="pagination">
							<li class="previous"><a href="javascript:;" onclick="prev()">«</a></li>
							
							<c:if test="${msgPage.totalPage>6}">
								<c:if test="${msgPage.pageNum<=6/2}">
									<c:forEach var="x" begin="1" end="6">
										<c:if test="${msgPage.pageNum==x}">
											<li class="active"><a href="#">${x}</a></li>
										</c:if>
										<c:if test="${msgPage.pageNum!=x}">
											<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${msgPage.pageNum>6/2&&msgPage.pageNum<=msgPage.totalPage - 6/2}">
									<c:forEach var="x" begin="${msgPage.pageNum +1 - 6/2}" end="${msgPage.pageNum + 6/2}">
										<c:if test="${msgPage.pageNum==x}">
											<li class="active"><a href="#">${x}</a></li>
										</c:if>
										<c:if test="${msgPage.pageNum!=x}">
											<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${msgPage.pageNum>msgPage.totalPage - 6/2}">
									<c:forEach var="x" begin="${msgPage.totalPage +1 - 6}" end="${msgPage.totalPage}">
										<c:if test="${msgPage.pageNum==x}">
											<li class="active"><a href="#">${x}</a></li>
										</c:if>
										<c:if test="${msgPage.pageNum!=x}">
											<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
										</c:if>
									</c:forEach>
								</c:if>
							</c:if>
							<c:if test="${msgPage.totalPage<=6}">
								<c:forEach var="x" begin="1" end="${msgPage.totalPage}">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a href="#">${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
							
							<li class="next"><a href="javascript:;" onclick="next()">»</a></li>
						</ul>
					</div>
					<input type="hidden" id="pageNum" name="pageNum" value="${msgPage.pageNum }">
					<input type="hidden" id="totalPage" name="totalPage" value="${msgPage.totalPage }">
				</div>
			</div>
		</div>
	</body>
	<div class="modal-container" data-remodal-id="modal" no=text>
		<iframe id="modal-frame" src="" style></iframe>
		<a href="javascript:mclose()" class="modal-close"><img src="../../css/icon/x.svg" alt=""></a>
	</div>
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript">
		// modal
		window.modal_load = window.mload = function(url,callback){
			if(url){
				var ifm = document.getElementById('modal-frame');
					$('>.modal-close',ifm.parentNode).css('display','block')
				ifm.onload = function(){
					ifm.onload = undefined;
					$('>.modal-close',ifm.parentNode).css('display','')
					var h = ifm.contentDocument.body.scrollHeight;
					ifm.style.height = h+'px'
					modal_resize();
					if(callback){
						setTimeout(callback.bind(ifm.contentWindow))
					}
				};
				ifm.src = url;
				$('[data-remodal-id=modal]').remodal({
					closeOnEscape:false,
					closeOnOutsideClick:false,
				}).open();
			}
		}
		window.modal_close = window.mclose = function(){
			var ifm = $('[data-remodal-id=modal] iframe')[0];
			$('[data-remodal-id=modal]').remodal().close();
			ifm.src = '';
			ifm.style.height = '';
		}
		// modal - 居中
		var modal_resize = function(){
			var ifm = document.getElementById('modal-frame');
			var hc = ifm.offsetHeight;
			if(hc == 0) return;
			var h = parseInt(ifm.style.height) || 0
			var c = ifm.parentNode
			var padding = (c.offsetHeight - hc)/2
			if(hc == h){
				c.style['padding-top'] = padding + 'px'
				c.style['padding-bottom'] = padding + 'px'
			}else{
				c.style['padding-top'] = ''
				c.style['padding-bottom'] = ''
			}
		}
		$(window).resize(modal_resize);
	</script>
	<script type="text/javascript">
		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/portal/news/_del?id=" + id;
					$.post(url, function(data) {
						parent.win.successAlert('删除成功！');
						window.location.reload();
					});
				}
			});
		}
		function issue(id){
			parent.win.confirm('确定要发布？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/portal/news/_issue?id=" + id;
					$.post(url, function(data) {
						parent.win.successAlert('发布成功！');
						window.location.reload();
					});
				}
			});
		}
		function issueNo(id){
			parent.win.confirm('确定要撤回？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/portal/news/_issueNo?id=" + id;
					$.post(url, function(data) {
						parent.win.successAlert('撤回成功！');
						window.location.reload();
					});
				}
			});
		}
		function prev(){
			var entity = $('#form1').serialize();
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) - 1;
			if(pageNum == 0){
				pageNum = 1;
			}
  			params.pageNum = pageNum; 
	     	var url = "${pageContext.request.contextPath}/portal/news/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function next(){
			var entity = $('#form1').serialize();
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) + 1;
			if(pageNum >= totalPage){
				pageNum = totalPage;
			}
  			params.pageNum = pageNum; 
	     	
		    var url = "${pageContext.request.contextPath}/portal/news/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function page(pageNum){
			var entity = $('#form1').serialize();
			var url = "${pageContext.request.contextPath}/portal/news/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
	</script>
</html>
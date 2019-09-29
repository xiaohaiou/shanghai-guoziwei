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
		<link rel="stylesheet" href="<c:url value="/css/index.css"/>">
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
	</head>
	<body>
		<h4>管理路径配置</h4>
		<div class="table_content">
			<form id="form1" action="${pageContext.request.contextPath}/portal/manage/_query" class="row">
				<span class="col-md-4">功能模块名称：<input type="text" name="typeName" value="${defTask.typeName}"></span>
				<span class="col-md-3">指标名称：<input type="text" name="mapName" value="${defTask.mapName}"></span>
				<span class="col-md-3">配置人：<input type="text" name="vcEmployeeName" value="${defTask.vcEmployeeName}"></span>
				<div class="form_div col-md-2">
					<input type="button" value="查询" class="form_btn" onclick="query()">
					<input type="button" value="新增" class="form_btn" id="model_add" onclick="mload('${pageContext.request.contextPath}/portal/manage/_edit')">
				</div>
			</form>
			<div class="tablebox">
				<table id="table1">
					<tr class="table_header">
						<th>序号</th>
						<th>功能模块代码</th>
						<th>功能模块名称</th>
						<th>指标代码</th>
						<th>指标名称</th>
						<th>接收人</th>
						<th>抄送人</th>
						<th>主题</th>
						<th>配置人</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.taskDefList }">
						<c:forEach items="${ requestScope.taskDefList}" var="t" varStatus="status">
							<tr>
								<td>
									${(msgPage.pageNum-1)*10 + status.index + 1 }
								</td>
								<td>
									${t.type }
								</td>
								<td>
									${t.typeName }
								</td>
								<td>
									${t.mapId }
								</td>
								<td>
									${t.mapName }
								</td>
								<td>
									${t.vcName }
								</td>
								<td>
									${t.sbvcName }
								</td>
								<td>
									${t.STheme }
								</td>
								<td>
									${t.vcEmployeeName }
								</td>
								<td>
									<a href="javascript:;" onclick="del('${t.id}')">删除</a>
									<a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/portal/manage/_edit?id=${t.id }')">编辑</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.taskDefList}">
						<tr>
							<td colspan="10" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<div class="clearfix"> 
						<ul class="pagination" style="line-height:34px">
							&nbsp;	${msgPage.totalPage}页 /共${msgPage.totalRecords}条
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
		<div class="modal-container" data-remodal-id="modal" no=text>
			<iframe id="modal-frame" src="" style></iframe>
			<a href="javascript:mclose()" class="modal-close"><img src="${pageContext.request.contextPath}/css/icon/x.svg" alt=""></a>
		</div>
	</body>
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
					var url = "${pageContext.request.contextPath}/portal/manage/_del?id=" + id;
					$.post(url, function(data) {
						parent.win.successAlert('删除成功！');
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
	     	var url = "${pageContext.request.contextPath}/portal/manage/_query?pageNum="+pageNum+"&"+entity;
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
	     	
		    var url = "${pageContext.request.contextPath}/portal/manage/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function page(pageNum){
			var entity = $('#form1').serialize();
			var url = "${pageContext.request.contextPath}/portal/manage/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function query(){
			$("#form1").submit();
		}
	</script>
</html>
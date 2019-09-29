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
		<h4>系统集成中心App配置</h4>
		<div class="table_content">
			<form id="form1" action="${pageContext.request.contextPath}/bimCenterApp/_list" class="row">
				<span class="col-md-3">app系统名称：<input type="text" name="sysShortName" value="${app.sysShortName}"></span>
				<span class="col-md-3">所属公司：
					<select name="sysCompanyId">
						<option value="">---全部---</option>
						<c:forEach items="${companys}" var="company" >
							<option value="${company.id}" <c:if test="${company.id == app.sysCompanyId}">selected=selected</c:if>>${company.companyName}</option>
						</c:forEach>
					</select>
				</span>
				<div class="form_div col-md-2">
					<input type="button" value="查询" class="form_btn" onclick="query()">
					<input type="button" value="新增" class="form_btn" id="model_add" onclick="mload('${pageContext.request.contextPath}/bimCenterApp/_edit')">
				</div>
			</form>
			<div class="tablebox">
				<table id="table1">
					<tr class="table_header">
						<th>序号</th>
						<th>app系统名称</th>
						<th>系统所在公司</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.appList }">
						<c:forEach items="${ requestScope.appList}" var="t" varStatus="status">
							<tr>
								<td>
									${(msgPage.pageNum-1)*10 + status.index + 1 }
								</td>
								<td>
									${t.sysShortName }
								</td>
								<td>
									${t.companyName}
								</td>
								<td>
									<a href="javascript:;" onclick="del('${t.id}')">删除</a>
									<a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/bimCenterApp/_edit?id=${t.id }')">编辑</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.appList}">
						<tr>
							<td colspan="10" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="page.jsp" flush="true"></jsp:include>
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
			var h = parseInt(ifm.style.height) || 0;
			var c = ifm.parentNode;
			var padding = (c.offsetHeight - hc)/2;
			if(hc == h){
				c.style['padding-top'] = padding + 'px';
				c.style['padding-bottom'] = padding + 'px';
			}else{
				//c.style['padding-top'] = '';
				//c.style['padding-bottom'] = '';
			};
		}
		$(window).resize(modal_resize);
		
		
		
	</script>
	<script type="text/javascript">
		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/bimCenterApp/_del?id=" + id;
					$.post(url, function(data) {
						parent.win.successAlert('删除成功！');
						window.location.reload();
					});
				};
			});
		}
		
		function query(){
			$("#form1").submit();
		}
		
		</script>
</html>
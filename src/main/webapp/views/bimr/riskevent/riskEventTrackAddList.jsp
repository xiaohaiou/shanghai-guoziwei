<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 新增风险目录</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>跟踪上报
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
		
			<h3 class="data_title1">事件跟踪 <a href="javascript:void(0)"
											onclick="mload('${mapurl}/trackadd?id=${entity.id}')" class="form_btn">新增</a></h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:3%">序号</th>
						<th style="width:37%">采取策略</th>
						<th style="width:30%">备注</th>
						<th style="width:10%">状态</th>
						<th >操作</th>
					</tr>
					<c:if test="${!empty requestScope.relevancetrackList }">
						<c:forEach items="${ requestScope.relevancetrackList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: center;">${l.takestrategytxt }</td>
								<td style="text-align: center;">${l.takestrategyremark}</td>
								<td style="text-align: center;">${l.statusName}</td>
								<td>
									<a href="javascript:void(0)"
											onclick="mload('${mapurl}/trackView?trackid=${l.id}')">查看</a>
									<c:if test="${l.status == 82103 || l.status == 82107}">
										<a href="javascript:void(0)"
											onclick="mload('${mapurl}/trackadd?id=${entity.id}&trackid=${l.id}')">修改</a>
										<a href="javascript:void(0)"
											onclick="updatestautsfortrack('${mapurl}/updatestautsfortrack?id=${entity.id}&trackid=${l.id }')">上报</a>
										<a href="javascript:void(0)"
											onclick="del('${mapurl}/deletetrack?trackid=${l.id }')">删除</a>				
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.relevancetrackList}">
						<tr>
							<td colspan="4" align="center">查询无记录</td>
						</tr>
					</c:if>
				</table>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp" />
	</form:form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">
		function updatestautsfortrack(url){
			win.confirm('确定要上报？',function(r){
				if(r){
					$.post(url, function(data) {
						var commitresult1=JSON.parse(data);
						if(commitresult1.result){
							win.successAlert('上报成功！',function(){
								location.reload();
							});
						}else{
							win.errorAlert('操作失败！');
						}
					
					});
				}
			});
			return false;
		}
	function del(url){
			win.confirm('确定要删除？',function(r){
				if(r){
					$.post(url, function(data) {
						var commitresult1=JSON.parse(data);
						if(commitresult1.result){
							win.successAlert('删除成功！',function(){
								location.reload();
							});
						}else{
							win.errorAlert('操作失败！');
						}
					
					});
				}
			});
			return false;
		}

	
	//关闭弹窗
	$(".model_btn_close").click(function() {
		parent.mclose();
		return false;
	});
	$(".icon-guanbi").click(function () {
		parent.mclose();
		return false;
	});

	 
</script>
</html>
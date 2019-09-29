<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>动态新增、编辑页面</title>
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
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改动态信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增动态信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="portalNews" method="post">
		<form:hidden path="id"/>
		<form:hidden path="isIssue"/>
		<form:hidden path="creator"/>
		<form:hidden path="createTime"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>消息主题:</label>
				<input class="w25" type="text" name="title" id="title" placeholder="请输入消息主题" maxlength="50" value="${portalNews.title}"/>
				<label class="w20"><span class="required"> * </span>消息描述:</label>
				<input class="w25" type="text" name="description" id="description" placeholder="请输入消息描述" maxlength="50" value="${portalNews.description}"/><br>
				<label class="w20"><span class="required"> * </span>发布时间:</label>
				<input type="text" name="date" id="date" value="${portalNews.date}" placeholder="请输入发布时间" readonly="readonly" class="w25 time"/>
				<label class="w20"><span class="required"> * </span>消息类型:</label>
				<select name="type" id="type" class="selectpicker w25" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.newsType}" var="result">
							<option value="${result.id}"  <c:if test="${portalNews.type == result.id}">selected="selected"</c:if>>${result.description}</option>
						</c:forEach>
				</select>
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2" >保存并发布</button>
				<button class="btn btn-default model model_btn_close">取消</button>
			</div>
		</div>
	</form:form>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">	
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		function checkcommit() {
			if($("#title").val()=="") {
				win.errorAlert("消息主题不能为空");
				return false;
			}
			if($("#description").val()=="") {
				win.errorAlert("消息描述不能为空");
				return false;
			}
			if($("#date").val()=="") {
				win.errorAlert("发布时间不能为空");
				return false;
			}
			if($("#type").val()=="") {
				win.errorAlert("消息类型不能为空");
				return false;
			}
			return true;
		};	
		$("#commit-1").click(function(){
			if(!checkcommit() || !vaild.all()) {
				return false;
			} 
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var newsInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/portal/news/saveOrUpdate";
			$.post(url, newsInfo, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('保存成功！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}else if(data == "false"){
					win.errorAlert('该数据已被删除！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}else if(data == "false2"){
					win.errorAlert('该数据已经发布,不允许修改！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}
			});
			return false;
		});
		$("#commit-2").click(function(){
			if(!checkcommit()) {
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var newsInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/portal/news/saveOrUpdateAndPublish";
			$.post(url, newsInfo, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('保存成功！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}else if(data == "false"){
					win.errorAlert('该数据已被删除！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}else if(data == "false2"){
					win.errorAlert('该数据已经发布,不允许修改！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}
			});
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
	</script>
</html>
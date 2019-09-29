<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>动态维护界面</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>动态填报</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title">消息主题：</span>
				<input type="text" value="${entity.title}" name="title" id="title"/> 
			</span>
			<span class="col-md-4">
				<span class="search_title">消息描述：</span>
				<input type="text" value="${entity.description}" name="description" id="description"/> 
			</span>
			<span class="col-md-4">
				<span class="search_title">消息类型：</span>
				<select name="type" class="selectpicker" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.newsType}" var="result">
						<option value="${result.id }" <c:if test="${entity.type == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_zxdt_query,')==true}">
					<input type="button" value="查询" class="form_btn" id="queryBtn">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_zxdt_new,')==true}">
					<input type="button" value="新增" class="form_btn" id="model_add" onclick="mload('${mapurl}/addOrModify?op=add')">
				</c:if>
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:15%;">消息主题</th>
						<th style="width:15%;">消息描述</th>
						<th>发布时间</th>
						<th>是否发布</th>
						<th>消息类型</th>
						<th>创建人</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="news" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: left;word-wrap:break-word;word-break:break-all;">
									${news.title}
								</td>
								<td style="text-align: left;word-wrap:break-word;word-break:break-all;">
									${news.description}
								</td>
								<td style="text-align: center;">
									${news.date}
								</td>
								<c:if test="${'1' eq news.isIssue}">
									<td style="text-align: center;">
										是
									</td>
								</c:if>
								<c:if test="${'0' eq news.isIssue}">
									<td style="text-align: center;">
										否
									</td>
								</c:if>
								<td style="text-align: left;">
									${news.typeName}
								</td>
								<td style="text-align: center;">
									${news.creator}
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_zxdt_query,')==true}">
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=check&id=${news.id}',${news.id},'check')">查看</a>
									</c:if>
									<c:if test="${'0' eq news.isIssue}">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_zxdt_edit,')==true}">
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/addOrModify?op=modify&id=${news.id}',${news.id},'modify')" >修改</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_zxdt_issue,')==true}">
											<a href="javascript:void(0)" onclick="checkdata2('${mapurl}/issue?id=${news.id}',${news.id},'issue')">发布</a>
										</c:if>
									</c:if>
									<c:if test="${'1' eq news.isIssue}">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_zxdt_issueNo,')==true}">
											<a href="javascript:void(0)" onclick="checkdata2('${mapurl}/issueNo?id=${news.id}',${news.id},'issueNo')">撤销发布</a>
										</c:if>
									</c:if>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_zxdt_delete,')==true}">
										<a href="javascript:;" onclick="del(${news.id})">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="8" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
		function checkdata(url,id,op) {
			var checkurl = "${mapurl}/hasCheck?id="+id+"&op="+op;
			$.ajax({
			  type: 'POST',
			  url: checkurl,
			  success: function (data){
		  	  	if(data == "success"){
					mload(url);
				}else if(data == "false"){
					win.errorAlert('该数据已被删除！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}
			  }
		   });
		};
		//发布与撤销发布
		function checkdata2(url,id,op) {
			var checkurl = "${mapurl}/hasCheck?id="+id+"&op="+op;
			$.ajax({
			  type: 'POST',
			  url: checkurl,
			  success: function (data){
		  	  	if(data == "success"){
		  	  		if(op == "issue"){
			  	  		parent.win.confirm('确定要发布？',function(r){
							if(r){
								$.post(url, function(data) {
									if(data == "success") {
										win.successAlert('发布成功！');
										parent.location.reload();
										parent.mclose();
									}else{
										win.errorAlert('该数据已发布！',function(){
											parent.location.reload();
											parent.mclose();
										});
									}
								});
							}
						});
		  	  		}else if(op == "issueNo"){
			  	  		parent.win.confirm('确定要撤销发布？',function(r){
							if(r){
								$.post(url, function(data) {
									if(data == "success") {
										win.successAlert('撤销发布成功！');
										parent.location.reload();
										parent.mclose();
									}else{
										win.errorAlert('该数据已撤销发布！',function(){
											parent.location.reload();
											parent.mclose();
										});
									}
								});
							}
						});
		  	  		}
				}else if(data == "false"){
					win.errorAlert('该数据已被删除！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}
			  }
		   });
		};
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		function _query() {
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		};
		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${mapurl}/delete?id="+id;
					$.post(url, function(data) {
						if(data == "success") {
							win.successAlert('删除成功！');
							parent.location.reload();
							parent.mclose();
						}else if(data == "false"){
							win.errorAlert('该数据已被删除！',function(){
								parent.location.reload();
								parent.mclose();
							});
						}
					});
				}
			});
		};
		$("#queryBtn").click(function() {
			_query();		
		});
	</script>
</html>
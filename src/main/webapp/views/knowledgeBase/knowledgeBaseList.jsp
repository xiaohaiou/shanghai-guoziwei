<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>知识库维护页面</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		<style type="text/css">
			.search_title {
			    display: inline-block;
			    width: 10em;
			    padding: 0 !important;
			    text-align: right;
			}
			body{
				padding:0;
			}
			h4{
				margin: 20px 40px;
			}
			.table_content {
			    margin: 0 30px;
			}
		</style>
	</head>
	<body class="hn_grey">
		<jsp:include page="/views/public/title.jsp"></jsp:include>
		<h4>知识库数据维护</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title">标题：</span>
				<input type="text" value="${entity.title}" name="title"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">类型：</span>
				<select  name="typeId" class="selectpicker">
				<option value="">全部</option>
					<c:forEach items="${requestScope.typeId}" var="result">
						<option value="${result.id }" <c:if test="${entity.typeId == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">文号：</span>
				<input type="text" value="${entity.no}" name="no"/>
			</span>
			<span class="col-md-6">
				<span class="search_title">日期范围：</span>
				<input type="text" value="${entity.doDate}" name="doDate" id="doDate" readonly="readonly" class="time"/> ~ <input type="text" value="${doDate2}" name="doDate2" id="doDate2" readonly="readonly" class="time"/>
			</span>
			<input id="ids" name="ids" type="hidden">
			<div class="form_div col-md-12">
				<input type="button" value="查询" class="form_btn" id="queryBtn">
				<input type="button" value="新增" class="form_btn" id="model_add" onclick="mload('${pageContext.request.contextPath}/knowledgeBase/addOrModify?op=add')">
				<input type="button" value="置顶" class="form_btn" id="topBtn">
				<input type="button" value="置顶撤除" class="form_btn" id="delTopBtn">
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="text-align:center" width="5%"><input id="all" onchange="chk()" type="checkbox"></th>
						<th style="width:5%;">序号</th>
						<th style="width:20%;">标题</th>
						<th style="width:20%;">文号</th>
						<th style="width:10%;">类型</th>
						<th style="width:10%;">区域</th>
						<th style="width:10%;">日期</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="knowledge" varStatus="status">
							<tr>
								<td class="tl-center"><input name="checkbox"
									value="${knowledge.id}" onclick="getId();" type="checkbox">
								</td>
								<td style="text-align:center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align:left;word-wrap:break-word;word-break:break-all;">
									<c:if test="${knowledge.top == 0}">
										<img src="${pageContext.request.contextPath}/img/top.png" style="margin-right:5px">
										<font color="red"><c:out value="${knowledge.title}"></c:out></font>
									</c:if>
									<c:if test="${knowledge.top == 1}">
										<c:out value="${knowledge.title}"></c:out>
									</c:if>
								</td>
								<td style="text-align:left;word-wrap:break-word;word-break:break-all;">
									${knowledge.no}
								</td>
								<td style="text-align:left;">
									${knowledge.typeName}
								</td>
								<td style="text-align:left;">
									${knowledge.areaName}
								</td>
								<td style="text-align:center;">
									${knowledge.doDate}
								</td>
								<td>
									<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?id=${knowledge.id}',${knowledge.id})">查看</a>
									<a href="javascript:void(0)" onclick="checkdata('${mapurl}/addOrModify?op=modify&id=${knowledge.id}',${knowledge.id})">修改</a>
									<a href="javascript:;" onclick="del(${knowledge.id})">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="7" align="center">
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
		function checkdata(url,id) {
			var checkurl = "${mapurl}/hasCheck?id=" + id;
			$.ajax({
			  type: 'POST',
			  url: checkurl,
			  success: function (data){
		  	  	if(data == "success"){
					mload(url);
				}else{
					win.successAlert('该数据已被删除！');
					_query();
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
							_query();
						}else{
							win.successAlert('该数据已被删除！');
							_query();
						}
					});
				}
			});
		};
		$("#queryBtn").click(function() {
			if(checkTime($("#doDate").val(),$("#doDate2").val()) == false){
				return false;
			}
			_query();	
		});
		//查询时时间校验
		function checkTime(start,end) {
			var flag = true;
			if (start > end) {
				parent.win.generalAlert("结束时间不应在开始时间之前!");
				flag = false;
				return flag;
			}
		};
		function getId() {
			var ids = document.getElementById('ids').value.split(',');
			var chk_value = [];
			var userid = {};
			for ( var i = 0; i < ids.length; ++i) {
				if (!userid[ids[i]])
					chk_value.push(ids[i]);
				userid[ids[i]] = true;
			}
			$('input[name="checkbox"]:checked').each(function() {
				if (!userid[$(this).val()])
					chk_value.push($(this).val());
			});
			$("#ids").val(chk_value);
		};
		var chknum = 0;
		function chk() {
			if (chknum == 0) {
				$("input[name='checkbox']").prop("checked", true);
				chknum = 1;
			} else {
				$("input[name='checkbox']").prop("checked", false);
				chknum = 0;
			}
		};
		$("#topBtn").click(function() {
			var chk_value = [];
			$('input[name="checkbox"]:checked').each(function() {
				chk_value.push($(this).val());
			});
			if (chk_value.length == 0) {
				parent.win.generalAlert('你还没有选择任何内容！');
			} else {
				parent.win.confirm('确定要置顶？', function(r) {
					if (r) {
						var url = "${pageContext.request.contextPath}/knowledgeBase/top?ids="+ chk_value + "";
						$.post(url, function(data) {
							parent.win.successAlert('置顶成功！');
							window.location.reload();
							$('#all').prop("checked", false);
							$('input[name="checkbox"]:checked').each(
								function() {
									$(this).prop("checked", false);
								});
						});
					}
				});
			}
			return false;
		});
		$("#delTopBtn").click(function() {
			var chk_value = [];
			$('input[name="checkbox"]:checked').each(
					function() {
						chk_value.push($(this).val());
					});
			if (chk_value.length == 0) {
				parent.win.generalAlert('你还没有选择任何内容！');
			} else {
				parent.win.confirm('确定要撤除置顶？',function(r) {
					if (r) {
						var url = "${pageContext.request.contextPath}/knowledgeBase/delTop?ids="+ chk_value+ "";
						$.post(url, function(data) {
							parent.win.successAlert('置顶撤除成功！');
							window.location.reload();
							$('#all').prop("checked", false);
							$('input[name="checkbox"]:checked').each(
								function() {
									$(this).prop("checked",false);
							});
						});
					}
				});
			}
			return false;
		});
	</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>合规培训页面</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>合规培训填报</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			
			<span class="col-md-4">
				<span class="search_title">培训日期：</span>
				<input type="text" value="${entity.startDate}" id="startDate" name="startDate" readonly="readonly" class="time time_short"/> ~ <input type="text" value="${entity.endDate}" name="endDate" id="endDate" readonly="readonly" class="time time_short"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">培训名称：</span>
				<input type="text" value="${entity.trainName}" name="trainName" id="trainName"/> 
			</span>
			<span class="col-md-4">
				<span class="search_title">授课人：</span>
				<input type="text" value="${entity.lecturer}" name="lecturer" id="lecturer"/> 
			</span>
			<span class="col-md-4">
				<span class="search_title">审核状态：</span>
				<select  name="status" class="selectpicker" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.reportStatus}" var="result">
						<option value="${result.id }" <c:if test="${entity.status == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span>
			
			<div class="form_div col-md-12">
			        <c:if test="${fn:contains(gzwsession_code,',bimWork_hgpxtb_query,')==true}">
					     <input type="button" value="查询" class="form_btn" id="queryBtn">
					</c:if>
					<c:if test="${fn:contains(gzwsession_code,',bimWork_hgpxtb_new,')==true}">
					     <input type="button" value="新增" class="form_btn" id="model_add" onclick="mload('${mapurl}/addOrModify?op=add')">
			        </c:if>
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:20%;">培训日期</th>
						<th>单位名称</th>
						<th>培训名称</th>
						<th>授课人</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="train" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: left;word-wrap:break-word;word-break:break-all;">
									${train.trainDate}
								</td>
								<td style="text-align: left;">
									${train.companyName}
								</td>
								<td style="text-align: left;">
									${train.trainName}
								</td>
								<td style="text-align: left;">
									${train.lecturer}
								</td>
								<td style="text-align: left;">
									${train.createPersonName}
								</td>
								<td style="text-align: center;">
									${train.reportPersonName}
								</td>
								<td style="text-align: center;">
									${train.examineName}
								</td>
								<td>
									<c:if test="${train.status==50112}">
										<span style="color:#3366ff">${train.statusName}</span>
									</c:if>
									<c:if test="${train.status==50113}">
										<span style="color:#00a59d">${train.statusName}</span>
									</c:if>
									<c:if test="${train.status==50114}">
										<span style="color:#ff399d">${train.statusName}</span>
									</c:if>
									<c:if test="${train.status==50115}">
										<span style="color:#00cc66">${train.statusName}</span>
									</c:if>
								</td>
								<td>
								    <c:if test="${fn:contains(gzwsession_code,',bimWork_hgpxtb_show,')==true}">
									    <a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=check&id=${train.id}',${train.id},'check')">查看</a>
									</c:if>
									<c:if test="${train.status==50114 || train.status==50112 }">
									    <c:if test="${fn:contains(gzwsession_code,',bimWork_hgpxtb_update,')==true}">
										    <a href="javascript:void(0)" onclick="checkdata('${mapurl}/addOrModify?op=modify&id=${train.id}',${train.id},'modify')" >修改</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_hgpxtb_report,')==true}">
										     <a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=reported&id=${train.id}',${train.id},'reported')">上报</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_hgpxtb_delete,')==true}">
										     <a href="javascript:;" onclick="del(${train.id})">删除</a>
										 </c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="10" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
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
						_query();
					});
				}else{
					win.errorAlert('该数据已被操作！',function(){
						_query();
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
							_query();
						}else if(data == "false"){
							win.errorAlert('该数据已被删除！',function(){
								_query();
							});
						}else{
							win.errorAlert('该数据已被操作！',function(){
								_query();
							});
						}
					});
				}
			});
		};
		$("#queryBtn").click(function() {
			if(checkTime($("#startDate").val(),$("#endDate").val()) == false){
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
	</script>
</html>
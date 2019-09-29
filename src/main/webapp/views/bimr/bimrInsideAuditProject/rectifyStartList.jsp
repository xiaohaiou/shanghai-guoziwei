<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>审计项目整改发起页面</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		<style type="text/css">
			#com_ztree span {
				padding-left:0;
			}
		</style>
	</head>
	<body>
		<h4>审计项目整改发起</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title" style="width:14em;">审计项目名称: </span>
				<input type="text" name="auditProjectName" value="${entity.auditProjectName}" style="width: calc(100% - 15em);"/>
			</span>
			<span class="col-md-4">
				<span class="search_title" style="width:14em;">审计项目性质: </span>
				<select  name="auditProjectProp" class="selectpicker" style="width: calc(100% - 15em);">
					<option value="">全部</option>
					<c:forEach items="${requestScope.projectStatusProgress}" var="result">
						<option value="${result.id }" <c:if test="${entity.auditProjectProp == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">审计时间: </span>
				<input type="text" value="${entity.auditStartDate}" name="auditStartDate" id="auditStartDate" readonly="readonly" class="time time_short"/>
				~
				<input type="text" value="${entity.auditEndDate}" name="auditEndDate" id="auditEndDate" readonly="readonly" class="time time_short"/>
			</span>
			<div class="form_div col-md-12">
		     	<c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmzgfq_query,')==true}">
					<input type="button" value="查询" class="form_btn" id="queryBtn"/>
				</c:if>
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th>审计项目编号</th>
						<th style="width:15%;">被审计单位</th>
						<th style="width:15%;">审计项目名称</th>
						<th>审计项目性质</th>
						<th>审计起止时间</th>
						<th>子项目</th>
						<th>项目负责人</th>
						<th>创建人</th>
						<th>项目状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					    <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="auditProject" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: center;">
									${auditProject.auditProjectCode}
								</td>
								<td style="text-align: left">
									${auditProject.auditDeptedName}
								</td>
								<td style="text-align: left;">
									${auditProject.auditProjectName}
								</td>
								<td style="text-align: left;word-wrap:break-word;word-break:break-all;">
									<c:if test="${auditProject.auditProjectProp == 80026}">
										<span>风险导向审计</span>
									</c:if>
									<c:if test="${auditProject.auditProjectProp == 80027}">
										<span>风险管理审计</span>
									</c:if>
									<c:if test="${auditProject.auditProjectProp == 80028}">
										<span>内部控制评价</span>
									</c:if>
									<c:if test="${auditProject.auditProjectProp == 80029}">
										<span>经济责任审计</span>
									</c:if>
									<c:if test="${auditProject.auditProjectProp == 80030}">
										<span>企业巡视</span>
									</c:if>
									<c:if test="${auditProject.auditProjectProp == 80031}">
										<span>调研管理</span>
									</c:if>
								</td>
								<td style="text-align: center;">
									${auditProject.auditStartDate}~${auditProject.auditEndDate}
								</td>
								<td style="text-align: center;">
									<c:if test="${auditProject.isChildren == 1}">是</c:if>
									<c:if test="${auditProject.isChildren == 0}">否</c:if>
								</td>
								<td style="text-align: center;">
									${auditProject.projectPrincipal}
								</td>
								<td style="text-align: center;">
									${auditProject.createPersonName}
								</td>
								<td>
									<c:if test="${auditProject.status == 50223}">
										<span style="color:#3366FF">未启用</span>
									</c:if>
									<c:if test="${auditProject.status == 50224}">
										<span style="color:#FF0000">已启用</span>
									</c:if>
									<c:if test="${auditProject.status == 50225}">
										<span style="color:#FF0000">在办中</span>
									</c:if>
									<c:if test="${auditProject.status == 50226}">
										<span style="color:#FF0000">整改中</span>
									</c:if>
									<c:if test="${auditProject.status == 50227}">
										<span style="color:#FF0000">办结申请中</span>
									</c:if>
									<c:if test="${auditProject.status == 50228}">
										<span style="color:#FF0000">已退回</span>
									</c:if>
									<c:if test="${auditProject.status == 50229}">
										<span style="color:#FF0000">已完成</span>
									</c:if>
									<c:if test="${auditProject.status == 50230}">
										<span style="color:#FF0000">关闭审核中</span>
									</c:if>
									<c:if test="${auditProject.status == 50231}">
										<span style="color:#FF0000">已关闭</span>
									</c:if>
								</td>
								<td>
								     <c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmzgfq_viewChangeInformation,')==true}">
									     <a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/rectify/fill?projectId=${auditProject.id}')">查看整改信息</a>
									 </c:if>
									 <c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmzgfq_fillInitiation,')==true}">
										<c:if test="${auditProject.projectPrincipalId == requestScope.vcEmployeeId}">
									       <a href="javascript:void(0);" onclick="mload('${pageContext.request.contextPath}/bimr/rectify/newOrModify?op=add&projectId=${auditProject.id}')">整改发起</a>
										</c:if>
									 </c:if>
								</td>
								
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="12" align="center">
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
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
		var timeoutId;

		var zTreeObj;
		var com_ztree_setting = {
			check:{
				enable:false,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			}
		};
		$(function () {
		     //所有企业数据	
		    var com_data = ${allCompanyTree};
			zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
			//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
			var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = treeObj.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
			var childrenNodes = treeObj.transformToArray(nodes);
			if(childrenNodes[0]!=null){
				treeObj.expandNode(childrenNodes[0],true);
			}
		});
		/*$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					$("#problemAttrUnitId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#problemAttrUnitName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});*/
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		function _query() {
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/bimr/rectify/list";
			form.method = "post";
			form.submit();
		};
		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/bimr/question/rectify/delete";
					$.post(url,{id:id}, function(data) {
					    var r = data.result;
						if(r == 1) {
							win.successAlert(data.message,function(){
               					_query();
							});
						}else{
							win.errorAlert(data.message,function(){							
							   _query();
							});
						}
					});
				}
			});
		};
		$("#queryBtn").click(function() {
			/*if(checkTime($("#auditStartDate").val(),$("#auditEndDate").val()) == false){
				return false;
			}*/
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
		/*$('.clear').on('click',function(){
			$(this).siblings("input[name='problemAttrUnitName']").val("");
			$(this).siblings("input[name='problemAttrUnitId']").val("");
		});*/
	</script>
</html>
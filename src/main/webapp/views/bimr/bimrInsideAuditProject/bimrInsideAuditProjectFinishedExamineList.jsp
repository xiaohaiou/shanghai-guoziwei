<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>审计项目关闭审核列表</title>
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
		<h4>审计项目关闭审核</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title" style="width:9em;">审计实施单位: </span>
				<input type="hidden" id="auditImplDeptId" name="auditImplDeptId" value="${entity.auditImplDeptId}" >
				<input style="width: calc(100% - 10em);" name="auditImplDeptName" id="auditImplDeptName" value="${entity.auditImplDeptName}" readonly="readonly">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
			</span>
			<span class="col-md-4">
				<span class="search_title" style="width: 9em;">审计项目性质: </span>
				<select  name="auditProjectProp" class="selectpicker" style="width: calc(100% - 10em);">
					<option value="">全部</option>
					<c:forEach items="${requestScope.auditProperty}" var="result">
						<option value="${result.id }" <c:if test="${entity.auditProjectProp == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title" style="width: 9em;">审计项目状态: </span>
				<select  name="estatus" class="selectpicker" style="width: calc(100% - 10em);">
					<option value="">全部</option>
					<c:forEach items="${requestScope.rstatus}" var="result">
						<option value="${result.id }" <c:if test="${entity.estatus == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title" style="width: 9em;">审计项目编号: </span>
				<input type="text" name="auditProjectCode" value="${entity.auditProjectCode}" style="width: calc(100% - 10em);"/>
			</span>
			<span class="col-md-4">
				<span class="search_title" style="width: 9em;">审计项目名称: </span>
				<input type="text" name="auditProjectName" value="${entity.auditProjectName}" style="width: calc(100% - 10em);"/>
			</span>
			<div class="form_div col-md-12">
			     <c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmgbsh_query,')==true}">
					<input type="button" value="查询" class="form_btn" id="queryBtn">
				  </c:if>
				<%--<c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmsjsb_new,')==true}">
					<input type="button" value="新增" class="form_btn" id="model_add" onclick="mload('${pageContext.request.contextPath}/bimr/bimrInsideAuditProject/newOrModify?op=add')">
				</c:if>--%>
				<%-- <c:if test="${fn:contains(gzwsession_code,'bimWork_sjxmsjsb_export,') == true}"> --%>
					<!-- <input type="button" value="导出" class="form_btn" id="exportBtn"> -->
				<%-- </c:if> --%>
			</div>
		</form:form>
			<div class="tablebox">
				<table style="width: 100%;">
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:15%;">审计项目名称</th>
						<th style="width:15%;">审计项目编号</th>
						<th style="width:10%;">审计项目性质</th>
						<th style="width:10%">项目负责人</th>
						<th style="width:10%;">创建人</th>
						<th style="width:10%;">状态</th>
						<th style="width:10%;">操作</th>
					</tr>
					<c:if test="${not empty requestScope.msgPage.list }">
					    <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="auditProject" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: left">
									${auditProject.auditProjectName}
								</td>
								<td style="text-align: left;">
									${auditProject.auditProjectCode}
								</td>
								<td style="text-align: center;">
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
									${auditProject.projectPrincipal}
								</td>
								<td style="text-align: center;">
									${auditProject.createPersonName}
								</td>
								<td style="text-align: center;">
									<c:if test="${auditProject.status == 50223}">
										<span style="color:#3366FF">未启用</span>
									</c:if>
									<c:if test="${auditProject.status == 50224}">
										<span style="color:#3366FF">已启用</span>
									</c:if>
									<c:if test="${auditProject.status == 50225}">
										<span style="color:#3366FF">在办中</span>
									</c:if>
									<c:if test="${auditProject.status == 50226}">
										<span style="color:#3366FF">整改中</span>
									</c:if>
									<c:if test="${auditProject.status == 50227}">
										<span style="color:#FF0000">办结申请中</span>
									</c:if>
									<c:if test="${auditProject.status == 50228}">
										<span style="color:#3366FF">已退回</span>
									</c:if>
									<c:if test="${auditProject.status == 50229}">
										<span style="color:#3366FF">已完成</span>
									</c:if>
									<c:if test="${auditProject.status == 50230}">
										<span style="color:#FF0000">关闭审核中</span>
									</c:if>
									<c:if test="${auditProject.status == 50231}">
										<span style="color:#FF0000">已关闭</span>
									</c:if>
								</td>
								<td>
								     <c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmgbsh_show,')==true}">
									    <a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/inside/eview?id=${auditProject.id}')">查看</a>
									 </c:if>
									  <c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmgbsh_examine,')==true}">
											<c:if test="${auditProject.status==50230}">
												<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/inside/examineFinishedView?id=${auditProject.id}')">审核</a>
											</c:if>
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
		$('#auditImplDeptName').on('focus click',function(){
			$(this).next('.com_ztree_box').css('display','block')
		}).parent().on('mouseenter',function(){
			clearTimeout(timeoutId)
		}).on('mouseleave',function(){
			clearTimeout(timeoutId)
			timeoutId = setTimeout(function(el){
				$(el).find('.com_ztree_box').css('display','none')
			},3e2,this);
		});
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
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					$("#auditImplDeptId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#auditImplDeptName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		function _query() {
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/bimr/inside/examineFinishedList";
			form.method = "post";
			form.submit();
		};
		$("#queryBtn").click(function() {
			if(checkTime($("#auditStartDate").val(),$("#auditEndDate").val()) == false){
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
		$('.clear').on('click',function(){
			$(this).siblings("input[name='auditImplDeptName']").val("");
			$(this).siblings("input[name='auditImplDeptId']").val("");
		});
	</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>查询办结审计项目页面</title>
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
		<h4>查询办结审计项目</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title" style="width:9.3em;">审计实施单位: </span>
				<input type="hidden" id="auditImplDeptId" name="auditImplDeptId" value="${entity.auditImplDeptId}" >
				<input style="width: calc(100% - 10.6em);" name="auditImplDeptName" id="auditImplDeptName" value="${entity.auditImplDeptName}" readonly="readonly">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
			</span>
			<span class="col-md-4">
				<span class="search_title">项目名称: </span>
				<input type="text" name="auditProjectName" value="${entity.auditProjectName}"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">项目性质: </span>
				<select  name="auditProjectProp" class="selectpicker" >
					<option value="">全部</option>
					<c:forEach items="${requestScope.auditProjectProperty}" var="result">
						<option value="${result.id }" <c:if test="${entity.auditProjectProp == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">起止时间: </span>
				<input type="text" value="${entity.auditStartDate}" name="auditStartDate" id="auditStartDate" readonly="readonly" class="time time_short"/>
				~
				<input type="text" value="${entity.auditEndDate}" name="auditEndDate" id="auditEndDate" readonly="readonly" class="time time_short"/>
			</span>
			
			
			<span class="col-md-4">项目类别：
				<select  name="isImportant" id="isImportant" class="selectpicker" >
					<option value="" <c:if test="${entity.isImportant eq ''}">selected</c:if>>全部</option>
					<option value="0" <c:if test="${entity.isImportant eq '0'}">selected</c:if>>年度计划内项目</option>
					<option value="1" <c:if test="${entity.isImportant eq '1'}">selected</c:if>>年度计划外项目</option>
					<option value="2" <c:if test="${entity.isImportant eq '2'}">selected</c:if>>上级交办项目</option>
				</select>
			</span>
			
			
			<%-- <span class="col-md-4"><span class="search_title">项目类别：</span>
				<select name="isImportant" class="selectpicker" >
					<option value="">全部</option>
					<c:forEach items="${requestScope.important_type}" var="result">
						<option value="${result.id }"
							<c:if test="${entity.isImportant == result.id}">selected="selected"</c:if>>${result.description}
						</option>
					</c:forEach>
				</select> 
			</span>  --%>
			
			<div class="form_div col-md-12">
			     <c:if test="${fn:contains(gzwsession_code,',bimWork_cxbjsjxm_query,')==true}">
					<input type="button" value="查询" class="form_btn" id="queryBtn"、>
				 </c:if>
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width: 20%;">审计项目名称</th>
						<th>被审计单位或人员</th>
						<th>审计项目开始日期</th>
						<th>审计项目结束日期</th>
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
									${auditProject.auditProjectName}
								</td>
								<td>
									${auditProject.auditDeptedName}
								</td>
								<td style="text-align: left;">
									${auditProject.auditStartDate}
								</td>
								<td style="text-align: right;">
									${auditProject.auditEndDate}
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_cxbjsjxm_show,')==true}">
										<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/inside/closedView?id=${auditProject.id}')">查看</a>
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
			form.action = "${pageContext.request.contextPath}/bimr/inside/closed";
			form.method = "post";
			form.submit();
		};
		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/bimr/inside/delete";
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
			if(checkTime($("#auditStartDate").val(),$("#auditEndDate").val()) == false){
				return false;
			}
			_query();		
		});
		//查询时时间校验
		function checkTime(start,end) {
			var flag = true;
			if(start!=''&&end!=''){
			if (start > end) {
				parent.win.generalAlert("结束时间不应在开始时间之前!");
				flag = false;
				return flag;
			}
			}
		};
		$('.clear').on('click',function(){
			$(this).siblings("input[name='auditImplDeptName']").val("");
			$(this).siblings("input[name='auditImplDeptId']").val("");
		});
	</script>
</html>
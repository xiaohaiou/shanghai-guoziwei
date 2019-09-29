<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title> 审计项目发现问题类型与成因分析查询</title>
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
	</head>
	<body>
		<h4> 审计项目发现问题类型与成因分析查询</h4>
		<div class="table_content">
		<form:form id="form1" class="row">
			<span class="col-md-4"><span class="search_title">年月：</span> 
			  <input type="text" name="yearMonth" value="${yearMonth}" readonly="true" class="time"/> 
			</span>
			<span class="col-md-4"> <span class="search_title" style="width:9em;">审计实施单位：</span>
				<input type="hidden" id="auditImplDeptId" name="auditImplDeptId" value="">
				<input name="auditImplDeptName" id="auditImplDeptName" value="${entity.auditImplDeptName}" readonly title=""  style="width: calc(100% - 10em);">
				<div class="com_ztree_box"
					style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
				</div> <i class="clear iconfont icon-guanbi"></i> 
			</span> 
			
			<span class="col-md-4">
				<span class="search_title" style="width:9em;">审计项目名称: </span>
				<input type="text" name="auditProjectName" value="${entity.auditProjectName}" style="width: calc(100% - 10em);"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">项目性质: </span>
				<select  name="auditProjectProp" class="selectpicker">
					<option value="">全部</option>
					<c:forEach items="${auditProps}" var="result">
						<option value="${result.id }" <c:if test="${entity.auditProjectProp == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span>
			<div class="form_div col-md-12">
			  <c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmfxwtlxycyfxcx_query,')==true}">
				<input type="button" value="查询" class="form_btn" id="btn_query" onclick="_query()">
			   </c:if>
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:3%;">序号</th>
						<th style="width: 15%;">被审计单位</th>
						<th style="width: 15%;">审计项目名称</th>
						<th style="width: 10%;">人力资源管理方面的问题</th>
						<th style="width: 10%;">采购管理方面问题</th>
						<th style="width: 10%;">基础建设方面问题</th>
						<th style="width: 10%;">项目投资（重组并购）方面问题</th>
						<th style="width: 10%;">生产组织和销售方面问题</th>
						<th style="width: 10%;">行政事务管理问题</th>
						<th style="width: 10%;">外部环境和竞争问题</th>
						<th style="width: 10%;">其他</th>
					</tr>
					<c:if test="${not empty requestScope.msgPage.list }">
					    <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="q" varStatus="status">
							<tr>
								<td style="text-align: center;">${recordNumber + status.index + 1 }</td>
								<td style="text-align: left;">${q[0]}</td>
								<td style="text-align: left;">${q[1]}</td>
								<td style="text-align: left;">${q[2]}</td>
								<td style="text-align: left;">${q[3]}</td>
								<td style="text-align: left;">${q[4]}</td>
								<td style="text-align: left;">${q[5]}</td>
								<td style="text-align: left;">${q[6]}</td>
								<td style="text-align: left;">${q[7]}</td>
								<td style="text-align: left;">${q[8]}</td>
								<td style="text-align: left;">${q[9]}</td>
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
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
		var timeoutId
 		$('#auditImplDeptName').on('focus click',function(){
 			$(this).next('.com_ztree_box').css('display','block')
 		}).parent().on('mouseenter',function(){
 			clearTimeout(timeoutId)
 		}).on('mouseleave',function(){
 			clearTimeout(timeoutId)
 			timeoutId = setTimeout(function(el){
 				$(el).find('.com_ztree_box').css('display','none')
 			},3e2,this);
 		})
 		$('.clear').on('click',function(){
			$(this).siblings("input[name='auditImplDeptId']").val("");
			$(this).siblings("input[name='auditImplDeptName']").val("");
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
			<c:if test="${not empty entity.auditImplDeptId}">
    		var nodes = treeObj.getNodeByParam("id", "${entity.auditImplDeptId}", null);
    		if(nodes){
    			 $("#auditImplDeptId").val(nodes.id)
				 $("#auditImplDeptName").val(nodes.name);
    		}
    		</c:if>
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
			format:"YYYY-MM"
		});
		
		function _query() {
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/bimr/question/analysis";
			form.method = "post";
			form.submit();
		};
	</script>
</html>
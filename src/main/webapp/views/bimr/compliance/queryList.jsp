<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 查询合规调查</title>
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
		<style type="text/css">
			#com_ztree span {
				padding-left:0;
			}
		</style>
</head>
<body class="hn_grey">
	<h4> 查询合规调查</h4>
	<div class="table_content">
		<form id="form1" class="row">
			<span class="col-md-3"><span class="search_title">调查时间：</span> 
			  <input type="text" name="startTime" value="${entity.startTime}" readonly="true" class="time" style="width:calc(50% - 6em);"/> 
			  &nbsp;&nbsp;~&nbsp;&nbsp; 
			  <input type="text" name="endTime" value="${entity.endTime}" readonly="true" class="time" style="width:calc(50% - 6em);"/>
			</span>
			<span class="col-md-3"><span class="search_title">调查项目名称：</span>
			  <input type="text" name="projectName" value="${entity.projectName}" style="width: 65%;"/> 
			</span> 
			<span class="col-md-3"><span class="search_title">调查涉及单位：</span>
			  <%-- <input type="text" name="compName" value="${entity.compName}"/>  --%>
			  <input type="hidden" id="compId" name="compId" value="${entity.compId}" >
				<input name="compName" id="compName" value="${entity.compName}" readonly="readonly" style="width: 65%;">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
			</span> 
			<span class="col-md-3"><span class="search_title">调查项目状态：</span>
				<select name="status" class="selectpicker" id="status" style="width: 65%;">
				    <option value="">全部</option>
				    <option value="81011" <c:if test="${entity.status ==81011}">selected="selected"</c:if>>已退回</option>
				    <option value="81012" <c:if test="${entity.status ==81013}">selected="selected"</c:if>>申请办结</option>
					<option value="81013" <c:if test="${entity.status ==81013}">selected="selected"</c:if>>已办结</option>
					<option value="81014" <c:if test="${entity.status ==81014}">selected="selected"</c:if>>整改中</option>
					<option value="81015" <c:if test="${entity.status ==81015}">selected="selected"</c:if>>已完成</option>
				</select> 
			</span>
			<br>
			<br>
			<div class="form_div col-md-12">
		     	 <c:if test="${fn:contains(gzwsession_code,',bimWork_cxhgdc_query,')==true}">
				     <input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				  </c:if>
				
			
				<c:if test="${fn:contains(gzwsession_code,',bimWork_cxhgdc_export,')==true}">
					     <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export1()">
				</c:if>
			
			
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th style="width:3%" >序号</th>
					<th style="width:15%" >调查涉及单位</th>
					<th style="width:20%" >调查项目名称</th>
					<th style="width:10%" >调查负责人</th>
					<th style="width:15%" >调查时间</th>
					<th style="width:7%" >状态</th>
					<th style="width:8%" >操作</th>
				</tr>
				
				<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.msgPage.list}" var="l" varStatus="status">
						<tr>
							<td style="text-align: center;">${recordNumber+ status.index + 1 }</td>
							<td style="text-align: center;">${l.compName }</td>
							<td style="text-align: left;">${l.projectName }</td>
							<td style="text-align: left;">${l.responsiblePerson }</td>
							<td style="text-align: left;">${l.startTime }-${l.endTime }</td>
							<td>${l.statusName }</td>
							<td>
							    <c:if test="${fn:contains(gzwsession_code,',bimWork_cxhgdc_show,')==true}">
							         <a href="javascript:void(0)" onclick="mload('${mapurl}/manageView?type=${type }&id=${l.id }')">查看</a>
							    </c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.msgPage.list}">
					<tr>
						<td colspan="7" align="center">查询无记录</td>
					</tr>
				</c:if>
			</table>
			<jsp:include page="/views/system/page.jsp" />
		</div>
	</div>
	<jsp:include page="/views/system/modal.jsp" />
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
	var timeoutId;
		$('#compName').on('focus click',function(){
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
					$("#compId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#compName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		$('.clear').on('click',function(){
			$(this).siblings("input[name='compName']").val("");
			$(this).siblings("input[name='compId']").val("");
		});
	
	$('input.time').jeDate({
		format:"YYYY-MM"
	});
	
	function _query(){
		var form = document.forms[0];
		form.action = "${searchurl}";
		form.method = "post";
		form.submit();
	}
	
	function _export1()
		{
		 var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/bimr/compliance/queryExportExporttest";
			form.method = "post";
			form.submit();
		}
	</script>
</html>
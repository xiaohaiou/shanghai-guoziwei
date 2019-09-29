<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>审计项目相关数据审核列表页面</title>
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
		<h4>审计项目相关数据审核</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title">审计单位：</span>
				<input type="hidden" id="auditDept" name="auditDept" value="${entity.auditDept}" >
				<input name="auditDeptName" id="auditDeptName" value="${entity.auditDeptName}" readonly="readonly">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
			</span>
			<span class="col-md-4">
				<span class="search_title">项目名称：</span>
				<input type="text" name="auditProjectName"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">审计状态：</span>
				<select  name="status" class="selectpicker" >
					<option value="">全部</option>
					<c:forEach items="${requestScope.reportStatus}" var="result">
						<option value="${result.id }" <c:if test="${entity.status == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">审计类型：</span>
				<select name="auditType" class="selectpicker">
					<option value="">全部</option>
					<c:forEach items="${requestScope.auditType}" var="result">
						<option value="${result.id }" <c:if test="${entity.auditType == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">审计时间段：</span>
				<input type="text" value="${entity.auditStartDate}" name="auditStartDate" id="auditStartDate" readonly="readonly" class="time time_short"/> ~ <input type="text" value="${entity.auditEndDate}" name="auditEndDate" id="auditEndDate" readonly="readonly" class="time time_short"/>
			</span>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmsjsh_query,')==true}">
					<input type="button" value="查询" class="form_btn" id="queryBtn">	
				</c:if>
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th>审计开始时间</th>
						<th>审计单位</th>
						<th style="width:20%;">审计项目名称</th>
						<th>审计类型</th>
						<th>审计发现问题数</th>
						<th>审计整改问题数</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
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
									${auditProject.auditStartDate}
								</td>
								<td style="text-align: left;">
									${auditProject.auditDeptName}
								</td>
								<td style="text-align: left;word-wrap:break-word;word-break:break-all;">
									${auditProject.auditProjectName}
								</td>
								<td style="text-align: left;">
									${auditProject.auditTypeName}
								</td>
								<td style="text-align: right;">
									${auditProject.isQuestion==1 ? auditProject.findQuestionNum : 0}个
								</td>
								<td style="text-align: right;">
									${auditProject.isAbarbeitung==1 ? auditProject.abarbeitungQuestionNum : 0}个
								</td>
								<td style="text-align: center;">
									${auditProject.createPersonName}
								</td>
								<td style="text-align: center;">
									${auditProject.reportPersonName}
								</td>
								<td style="text-align: center;">
									${auditProject.auditorPersonName}
								</td>
								<td>
									<c:if test="${auditProject.status==50112}">
										<span style="color:#3366ff">${auditProject.statusName}</span>
									</c:if>
									<c:if test="${auditProject.status==50113}">
										<span style="color:#00a59d">${auditProject.statusName}</span>
									</c:if>
									<c:if test="${auditProject.status==50114}">
										<span style="color:#ff399d">${auditProject.statusName}</span>
									</c:if>
									<c:if test="${auditProject.status==50115}">
										<span style="color:#00cc66">${auditProject.statusName}</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmsjsh_query,')==true}">
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=check&id=${auditProject.id}',${auditProject.id},'check')">查看</a>
									</c:if>
									<c:if test="${auditProject.status==50113 }">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_sjxmsjsh_exam,')==true}">
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/examine?id=${auditProject.id}',${auditProject.id},'examine')">审核</a>
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
		$('#auditDeptName').on('focus click',function(){
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
					$("#auditDept").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#auditDeptName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		$("#queryBtn").click(function() {
			if(checkTime($("#auditStartDate").val(),$("#auditEndDate").val()) == false){
				return false;
			}
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
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
			$(this).siblings("input[name='auditDeptName']").val("");
			$(this).siblings("input[name='auditDept']").val("");
		});
	</script>
</html>
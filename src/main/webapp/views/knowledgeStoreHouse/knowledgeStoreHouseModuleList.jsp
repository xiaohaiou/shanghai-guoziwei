<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>知识库列表页面</title>
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
		<h4>知识库列表</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title">文件号：</span>
				<input type="text" value="${entity.documentNo}" name="documentNo"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">文件名：</span>
				<input type="text" value="${entity.documentName}" name="documentName"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">时间：</span>
				<input type="text" value="${entity.year}" name="year" class="time time_short" readonly="readonly"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">机构：</span>
				<input type="hidden" id="organizationId" name="organizationId" value="${entity.organizationId}" >
				<input name="organizationName" id="organizationName" value="${entity.organizationName}" readonly="readonly">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
			</span>
			<%-- <span class="col-md-4">
				<span class="search_title">模块：</span>
				<select  name="moduleId" class="selectpicker">
				<option value="">全部</option>
					<c:forEach items="${requestScope.moduleId}" var="result">
						<option value="${result.id }" <c:if test="${entity.moduleId == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</span> --%>
			<input type="hidden" name="type" value="${type}">
			<div class="form_div col-md-12">
				<input type="button" value="查询" class="form_btn" id="queryBtn">
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:15%;">文件号</th>
						<th style="width:15%;">文件名</th>
						<th style="width:10%;">机构</th>
						<th style="width:10%;">模块</th>
						<th style="width:5%;">时间</th>
						<th style="width:10%;">创建人</th>
						<th style="width:15%;">附件</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="knowledgeStoreHouse" varStatus="status">
							<tr>
								<td style="text-align:center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align:left;word-wrap:break-word;word-break:break-all;">
									${knowledgeStoreHouse.documentNo}
								</td>
								<td style="text-align:left;word-wrap:break-word;word-break:break-all;">
									${knowledgeStoreHouse.documentName}
								</td>
								<td style="text-align:left;">
									${knowledgeStoreHouse.organizationName}
								</td>
								<td style="text-align:left;">
									${knowledgeStoreHouse.moduleName}
								</td>
								<td style="text-align:center;">
									${knowledgeStoreHouse.year}
								</td>
								<td style="text-align: center;">
									${knowledgeStoreHouse.createPersonName}
								</td>
								<td style="text-align:left;word-wrap:break-word;word-break:break-all;">
									<a href="${pageContext.request.contextPath}/common/download?_url=${knowledgeStoreHouse.hhFile.filePath}&_name=${knowledgeStoreHouse.hhFile.fileName}">${knowledgeStoreHouse.hhFile.fileName==null ? "" :knowledgeStoreHouse.hhFile.fileName}</a>
								</td>
								<td>
									<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?id=${knowledgeStoreHouse.id}','${knowledgeStoreHouse.id}')">查看</a>
									<a href="javascript:void(0)" onclick="checkdata('${mapurl}/addOrModify?op=modify&type=${type}&id=${knowledgeStoreHouse.id}','${knowledgeStoreHouse.id}')">修改</a>
									<a href="javascript:;" onclick="del(${knowledgeStoreHouse.id})">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="9" align="center">
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
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
		var timeoutId;
		$('#organizationName').on('focus click',function(){
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
					$("#organizationId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#organizationName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		$('input.time').jeDate({
			format:"YYYY"
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
			_query();	
		});
		$('.clear').on('click',function(){
			$(this).siblings("input[name='organizationName']").val("");
			$(this).siblings("input[name='organizationId']").val("");
		});
		function checkdata(url,id) {
			var checkurl = "${mapurl}/hasCheck?id="+id;
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
				}
			  }
		   });
		};
	</script>
</html>
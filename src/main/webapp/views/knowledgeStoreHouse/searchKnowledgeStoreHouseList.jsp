<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body class="hn_grey">
		<h4>
			<span class="glyphicon glyphicon-pencil"></span>知识库列表
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<div class="table_content">
			<form:form id="form1" class="row">
				<input type="hidden" value="${entity.documentName}" name="documentName"/>
			</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:15%;">文件号</th>
						<th style="width:15%;">文件名</th>
						<th style="width:20%;">机构</th>
						<th style="width:10%;">模块</th>
						<th style="width:5%;">时间</th>
						<th style="width:20%;">附件</th>
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
								<td style="text-align:left;word-wrap:break-word;word-break:break-all;">
									<a href="${pageContext.request.contextPath}/common/download?_url=${knowledgeStoreHouse.hhFile.filePath}&_name=${knowledgeStoreHouse.hhFile.fileName}">${knowledgeStoreHouse.hhFile.fileName==null ? "" :knowledgeStoreHouse.hhFile.fileName}</a>
								</td>
								<td>
									<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?id=${knowledgeStoreHouse.id}','${knowledgeStoreHouse.id}')">查看</a>
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
	<script type="text/javascript">	
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
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
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>合同范本查看页面</title>
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
		<h4>合同范本查看</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			
			<span class="col-md-4">
				<span class="search_title">合同类型：</span>
				<select  name="contractType" class="selectpicker" >
					<option value="">全部</option>
					<option value="1" <c:if test="${entity.contractType == 1}">selected="selected"</c:if>>租赁合同</option>
					<option value="2" <c:if test="${entity.contractType == 2}">selected="selected"</c:if>>买卖合同</option>
					<option value="3" <c:if test="${entity.contractType == 3}">selected="selected"</c:if>>财务合同</option>
					<option value="4" <c:if test="${entity.contractType == 4}">selected="selected"</c:if>>技术合同</option>
					<option value="5" <c:if test="${entity.contractType == 5}">selected="selected"</c:if>>融资租赁合同</option>
				</select>
			</span>
			
			<div class="form_div col-md-12">
		   	   <c:if test="${fn:contains(gzwsession_code,',bimWork_htfbck_query,')==true }"> 
				   <input type="button" value="查询" class="form_btn" id="queryBtn">
				</c:if>
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th>单位名称</th>
						<th>合同类型</th>
						<th>pdf附件</th>
						<th>空白模板</th>
						<th>创建人</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="contractModel" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: left;">
									${contractModel.companyName}
								</td style="text-align: left;">
								<td>
									<c:if test="${contractModel.contractType==1}">
										<span>租赁合同</span>
									</c:if>
									<c:if test="${contractModel.contractType==2}">
										<span>买卖合同</span>
									</c:if>
									<c:if test="${contractModel.contractType==3}">
										<span>财务合同</span>
									</c:if>
									<c:if test="${contractModel.contractType==4}">
										<span>技术合同</span>
									</c:if>
									<c:if test="${contractModel.contractType==5}">
										<span>融资租赁合同</span>
									</c:if>
								</td>
								<td style="text-align: left;">
									<c:if test="${!empty contractModel.filePath1}">
										<a href="${pageContext.request.contextPath}/common/download?_url=${contractModel.filePath1}&_name=${contractModel.fileName1}">${contractModel.fileName1==null ? "" :contractModel.fileName1}</a>
									</c:if>
								</td>
								<td style="text-align: left;">
									<c:if test="${!empty contractModel.filePath2}">
										<a href="${pageContext.request.contextPath}/common/download?_url=${contractModel.filePath2}&_name=${contractModel.fileName2}">${contractModel.fileName2==null ? "" :contractModel.fileName2}</a>
									</c:if>
								</td>
								<td style="text-align: left;">
									${contractModel.createPersonName}
								</td>
								<td>
								     <c:if test="${fn:contains(gzwsession_code,',bimWork_htfbck_show,')==true }"> 
									      <a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=check&id=${contractModel.id}',${contractModel.id},'check')">查看</a>
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
		function _query() {
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		};
		$("#queryBtn").click(function() {
			_query();		
		});
	</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>功能注册列表页面</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
		
	</head>
	<body>
		<h4>功能注册</h4>
		<div class="table_content">
			<form id="form1" class="row" action="${pageContext.request.contextPath}/page/code/_query" method="post">
				<span class="col-md-3">功能名称：<input type="text" name="qCodeName" value="${qCodeName }"></span>
				<span class="col-md-3">所属模块：
					<select name="qModelId" onchange="getThePages(this)">
						<option value="">---请选择---</option>
						<c:forEach items="${ requestScope.modelRegistedList}" var="model">
							<option value="${model.id }" <c:if test="${model.id eq qModelId }">selected="selected"</c:if>>${model.modelName }</option>
						</c:forEach>
					</select>
				</span>
				<span class="col-md-3">所属页面：
					<select name="qPageId" id="qPageId">
						<option value="">---请选择---</option>
						<c:forEach items="${ requestScope.pageRegistedList}" var="page">
							<option value="${page.id }" <c:if test="${page.id eq qPageId }">selected="selected"</c:if>>${page.pageName }</option>
						</c:forEach>
					</select>
				</span>
				<div class="form_div col-md-3">
					<input type="button" value="查询" class="form_btn" onclick="query()">
					<!-- 
					<c:if test="${fn:contains(rolePagecodeNums,',code_add,')==true}">
						<input type="button" value="新增" class="form_btn" id="model_add" onclick="openDialog('${pageContext.request.contextPath}/page/code/_addOrModify?op=add&pageNum=${msgPage.pageNum }&qCodeName=${qCodeName }&qModelId=${qModelId }&qPageId=${qPageId }')">
					</c:if> 
					-->
					<input type="button" value="新增" class="form_btn" id="model_add" onclick="openDialog('${pageContext.request.contextPath}/page/code/_addOrModify?op=add&pageNum=${msgPage.pageNum }&qCodeName=${qCodeName }&qModelId=${qModelId }&qPageId=${qPageId }')">
			
				</div>
			</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>功能名称</th>
						<th>所属页面</th>
						<th>所属模块</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.pageCodeList }">
						<c:set var="recordNumber" value="${(msgPage.pageNum - 1)*10 }"></c:set>
						<c:forEach items="${ requestScope.pageCodeList}" var="code" varStatus="status">
							<tr>
								<td>
									${recordNumber + status.index + 1 }
								</td>
								<td>
									${code.codeName }
								</td>
								<td>
									${code.pageName }
								</td>
								<td>
									${code.modelName }
								</td>
								<td>
									<!-- 
									<c:if test="${fn:contains(rolePagecodeNums,',code_edit,')==true}">
										<a href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/page/code/_addOrModify?op=modify&id=${code.id}&pageNum=${msgPage.pageNum }&qCodeName=${qCodeName }&qModelId=${qModelId }&qPageId=${qPageId }&randomId=<%=Math.random()%>')" >编辑</a>
									</c:if>
									<c:if test="${fn:contains(rolePagecodeNums,',code_del,')==true}">
										<a href="javascript:;" onclick="del(${code.id})">删除</a>
									</c:if>
									<c:if test="${fn:contains(rolePagecodeNums,',code_show,')==true}">
									<a href="javascript:;" onclick="show(${code.id})">查看人员</a>
									</c:if> -->
									<a href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/page/code/_addOrModify?op=modify&id=${code.id}&pageNum=${msgPage.pageNum }&qCodeName=${qCodeName }&qModelId=${qModelId }&qPageId=${qPageId }&randomId=<%=Math.random()%>')" >编辑</a>
									<a href="javascript:;" onclick="del(${code.id})">删除</a>
									<a href="javascript:;" onclick="show(${code.id})">查看人员</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.pageCodeList}">
						<tr>
							<td colspan="5" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<div class="clearfix"> 
					<ul class="pagination" style="line-height:34px">
						&nbsp;	${msgPage.totalPage}页 /共${msgPage.totalRecords}条
						<li class="previous"><a href="javascript:;" onclick="prev()">«</a></li>
						
						<c:if test="${msgPage.totalPage>6}">
							<c:if test="${msgPage.pageNum<=6/2}">
								<c:forEach var="x" begin="1" end="6">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a>${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${msgPage.pageNum>6/2&&msgPage.pageNum<=msgPage.totalPage - 6/2}">
								<c:forEach var="x" begin="${msgPage.pageNum +1 - 6/2}" end="${msgPage.pageNum + 6/2}">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a>${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${msgPage.pageNum>msgPage.totalPage - 6/2}">
								<c:forEach var="x" begin="${msgPage.totalPage +1 - 6}" end="${msgPage.totalPage}">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a >${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
						</c:if>
						<c:if test="${msgPage.totalPage<=6}">
							<c:forEach var="x" begin="1" end="${msgPage.totalPage}">
								<c:if test="${msgPage.pageNum==x}">
									<li class="active"><a >${x}</a></li>
								</c:if>
								<c:if test="${msgPage.pageNum!=x}">
									<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
								</c:if>
							</c:forEach>
						</c:if>
						
						<li class="next"><a href="javascript:;" onclick="next()">»</a></li>
					</ul>
				</div>
				<input type="hidden" id="pageNum" name="pageNum" value="${msgPage.pageNum }">
				<input type="hidden" id="totalPage" name="totalPage" value="${msgPage.totalPage }">
			</div>
		</div>
		<!-- 弹窗div -->
		<jsp:include page="/views/system/modal.jsp" />
		<div class = "bg_model" id="tanchuang">
			
		</div>
	</body>
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
	<script type="text/javascript">
		function query(){
		    $("#form1").submit();
		} 
		
		function prev(){
			var entity = $('#form1').serialize();
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) - 1;
			if(pageNum == 0){
				pageNum = 1;
			}
  			params.pageNum = pageNum; 
	     	var url = "${pageContext.request.contextPath}/page/code/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function next(){
			var entity = $('#form1').serialize();
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) + 1;
			if(pageNum >= totalPage){
				pageNum = totalPage;
			}
  			params.pageNum = pageNum; 
	     	
		    var url = "${pageContext.request.contextPath}/page/code/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function page(pageNum){
			var entity = $('#form1').serialize();
			var url = "${pageContext.request.contextPath}/page/code/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		
		//弹窗方法
		function openDialog(url){
			$("#tanchuang").load(url);
		　　$(".bg_model").css("display","block");
		　　$(".bg_model").fadeTo(300,1);
		　　$("body").css({ "overflow": "hidden" });
		}
		
		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/page/code/_del?id=" + id;
					$.post(url, function(data) {
						parent.win.successAlert('删除成功！');
						$("#form1").submit();
					});
				}
			});
		}
		
		$(function(){
			var save = "${save}";
			if(save == 'success'){
				parent.win.successAlert('保存成功！');
				save = "";
			}
		});
		
		
		function show(id){
			var url = "${pageContext.request.contextPath}/page/code/PageCodePersonQuery?modelId=" + id;
			mload(url);
		}
		
		//选择所属模块后，获取对应模块下的所有页面
		function getThePages(obj) {
			var modelId = obj.value;
			var sHtml = "<option value=''>---请选择---</option>";
			$.ajax({
				url:"${pageContext.request.contextPath}/page/code/_getPages",
				type:"POST",
				data:{
						modelId:modelId
					 },
				success:function(data){
					if(data.length > 0){//先判断是否有该系统下对应的注册模板，如果有，加入对应页面的select里面
				  		for(var i=0;i<data.length;i++){
				  			sHtml+="<option value='"+data[i].id+"'>"+data[i].pageName+"</option>";
				  		}
				  	}
				  	$("#qPageId").html(sHtml);
				}
			});
		}
		
	</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>角色权限列表页面</title>
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
	<h4>用户组管理</h4>
	<div class="table_content">
		<form id="form1" action="${pageContext.request.contextPath}/user/group/_query" method="post" class="row">
			<span class="col-md-3">用户组名称：<input type="text" id="qGroupName" name="qGroupName" value="${qGroupName }"></span>
			<span class="col-md-3">状态：
				<select name="qGroupStatus">
					<option value="">---请选择---</option>
					<option value="1" <c:if test="${'1' eq qGroupStatus }">selected="selected"</c:if>>启用</option>
					<option value="2" <c:if test="${'2' eq qGroupStatus }">selected="selected"</c:if>>禁用</option>
				</select>
			</span>
			<div class="form_div col-md-6">
				<input type="submit" id="query" name="query" style="display:none"/>
				<input type="button" value="查询" class="form_btn" onclick="document.getElementById('query').click();">
				<c:if test="${fn:contains(rolePagecodeNums,',group_add,')==true}">
					<input type="button" value="新增" class="form_btn" onclick="openDialog('${pageContext.request.contextPath}/user/group/_addOrModify?op=add&pageNum=${msgPage.pageNum }&qGroupName=${qGroupName }&qGroupStatus=${qGroupStatus }')" >
				</c:if>
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th>序号</th>
					<th>用户组编码</th>
					<th>用户组名称</th>
					<th>用户组描述</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:if test="${!empty requestScope.groupList }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.groupList}" var="l" varStatus="status">
						<tr>
							<td>${recordNumber + status.index + 1 }</td>
							<td>${l.groupNum }</td>
							<td>${l.groupName }</td>
							<td>${l.groupDescription }</td>
							<td>
								<c:if test="${l.groupStatus == 1 }">启用</c:if>
								<c:if test="${l.groupStatus == 2 }">禁用</c:if>
							</td>
							<td>
								<c:if test="${fn:contains(rolePagecodeNums,',group_edit,')==true}">
									<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/user/group/_addOrModify?id=${l.id}&op=modify&pageNum=${msgPage.pageNum }&qGroupName=${qGroupName }&qGroupStatus=${qGroupStatus }&randomId=<%=Math.random()%>')" >编辑</a>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',group_check,')==true}">
									<a class="ckzh" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/user/group/_checkGroupUsers?id=${l.id}&randomId=<%=Math.random()%>')">查看</a>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',group_start,')==true}">
									<c:if test="${l.groupStatus != '1'}">
										<a class="qy" href="javascript:;" onclick="startGroup('${l.id}')">启用</a>
									</c:if>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',group_stop,')==true}">
									<c:if test="${l.groupStatus == '1'}">
										<a class="jy" href="javascript:;" onclick="stopGroup('${l.id}')">禁用</a>
									</c:if>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',group_del,')==true}">
									<c:if test="${l.groupStatus == '2' }">
										<a class="sc" href="javascript:;" onclick="del(${l.id})">删除</a>
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.groupList}">
					<tr>
						<td colspan="6" align="center">
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
									<li class="active"><a >${x}</a></li>
								</c:if>
								<c:if test="${msgPage.pageNum!=x}">
									<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${msgPage.pageNum>6/2&&msgPage.pageNum<=msgPage.totalPage - 6/2}">
							<c:forEach var="x" begin="${msgPage.pageNum +1 - 6/2}" end="${msgPage.pageNum + 6/2}">
								<c:if test="${msgPage.pageNum==x}">
									<li class="active"><a >${x}</a></li>
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
	<div class="bg_model bg_model_xg" id="bg_model"></div>
	
</body>

	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap.min.js"/>" type="text/javascript"></script>
	<%-- <script src="<c:url value="/js/iframe.js"/>" type="text/javascript"></script> --%>
	<script type="text/javascript">
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
	     	var url = "${pageContext.request.contextPath}/user/group/_query?pageNum="+pageNum+"&"+entity;
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
	     	
		    var url = "${pageContext.request.contextPath}/user/group/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function page(pageNum){
			var entity = $('#form1').serialize();
			var url = "${pageContext.request.contextPath}/user/group/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
	
		//弹窗方法
		function openDialog(url){
			$("#bg_model").load(url);
			$(".bg_model").css("display","block");
			$(".bg_model").fadeTo(300,1);
			$("body").css({ "overflow": "hidden" });
		}
		
		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/user/group/_del?id=" + id;
					$.post(url, function(data) {
						parent.win.successAlert('删除成功！');
						$("#form1").submit();
					});
				}
			});
		}
		
		function startGroup(id){
			parent.win.confirm('确定要启用？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/user/group/_groupStart?id=" + id;
					$.post(url, function(data)	{
						parent.win.successAlert('启用成功！');
						$("#form1").submit();
					});
				}
			});
		}
		
		function stopGroup(id){
			parent.win.confirm('确定要禁用？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/user/group/_groupStop?id=" + id;
					$.post(url, function(data)	{
						if(data == 'success'){
							parent.win.successAlert('禁用成功！');
							$("#form1").submit();
						}else{
							parent.win.successAlert('此用户组已绑定角色，请先从对应角色中清除此用户组,再进行禁用！');
							$("#form1").submit();
						}
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
			if(save == 'success1'){
				parent.win.successAlert('分配成功！');
				save = "";
			}
		});
	</script>
</body>
</html>
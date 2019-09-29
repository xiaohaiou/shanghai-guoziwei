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
<body style="overflow:auto;">
	<h4>角色组创建</h4>
	<div class="table_content">
		<form id="form1" action="${pageContext.request.contextPath}/sys/role/_query?type=role" method="post" modelAttribute="hhRole" class="row">
			<span class="col-md-3">用户账号：<input type="text" id="vcAccount" name="vcAccount" value="${vcAccount }"></span>
			<span class="col-md-3">角色名称：<input type="text" id="qRoleName" name="qRoleName" value="${qRoleName }"></span>
			<span class="col-md-3">状态：
				<select name="qRoleStatus">
					<option value="">---请选择---</option>
					<option value="1" <c:if test="${'1' eq qRoleStatus }">selected="selected"</c:if>>启用</option>
					<option value="2" <c:if test="${'2' eq qRoleStatus }">selected="selected"</c:if>>禁用</option>
				</select>
			</span>
			<div class="form_div col-md-3">
				<input type="submit" id="query" name="query" style="display:none"/>
				<input type="button" value="查询" class="form_btn" onclick="document.getElementById('query').click();">
				<!-- 
				<c:if test="${fn:contains(rolePagecodeNums,',js_add,')==true}">
					<input type="button" value="新增" class="form_btn" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddOrModify?op=add&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&qRoleStatus=${qRoleStatus }')" >
				</c:if>
				 -->
				<input type="button" value="新增" class="form_btn" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddOrModify?op=add&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&qRoleStatus=${qRoleStatus }')" >
				
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th width="5%">序号</th>
					<th width="17%">角色名称</th>
					<th width="17%">角色描述</th>
					<th width="5%">角色状态</th>
					<th width="13%">角色操作</th>
					<th width="18%">功能授权</th>
					<th width="12%">数据授权</th>
					<th width="13%">人员配置</th>
				</tr>
				<c:if test="${!empty requestScope.roleList }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.roleList}" var="l" varStatus="status">
						<tr>
							<td>${recordNumber + status.index + 1 }</td>
							<td>${l.roleName }</td>
							<td>${l.roleDescription }</td>
							<td>
								<c:if test="${l.roleStatus == 1 }">启用</c:if>
								<c:if test="${l.roleStatus == 2 }">禁用</c:if>
							</td>
							<td>
								<!-- 
								<c:if test="${fn:contains(rolePagecodeNums,',js_edit,')==true}">
									<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddOrModify?id=${l.id}&op=modify&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&qRoleStatus=${qRoleStatus }&vcAccount=${vcAccount}&randomId=<%=Math.random()%>')" >编辑</a>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',js_check,')==true}">
									<a class="ckzh" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddOrModify?id=${l.id}&op=check&randomId=<%=Math.random()%>')">查看</a>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',js_start,')==true}">
									<c:if test="${l.roleStatus != '1'}">
										<a class="qy" href="javascript:;" onclick="startRole('${l.id}')">启用</a>
									</c:if>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',js_stop,')==true}">
									<c:if test="${l.roleStatus == '1'}">
										<a class="jy" href="javascript:;" onclick="stopRole('${l.id}')">禁用</a>
									</c:if>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',js_del,')==true}">
									<c:if test="${l.roleStatus == '2' }">
										<a class="sc" href="javascript:;" onclick="del(${l.id})">删除</a>
									</c:if>
								</c:if>
								 -->
									<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddOrModify?id=${l.id}&op=modify&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&qRoleStatus=${qRoleStatus }&vcAccount=${vcAccount}&randomId=<%=Math.random()%>')" >编辑</a>
									<a class="ckzh" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddOrModify?id=${l.id}&op=check&randomId=<%=Math.random()%>')">查看</a>
									<c:if test="${l.roleStatus != '1'}">
										<a class="qy" href="javascript:;" onclick="startRole('${l.id}')">启用</a>
									</c:if>
									<c:if test="${l.roleStatus == '1'}">
										<a class="jy" href="javascript:;" onclick="stopRole('${l.id}')">禁用</a>
									</c:if>
									<c:if test="${l.roleStatus == '2' }">
										<a class="sc" href="javascript:;" onclick="del(${l.id})">删除</a>
									</c:if>
							</td>
							<td>
								<!-- 
								<c:if test="${fn:contains(rolePagecodeNums,',role_mkfp,')==true}">
									<a class="mkfp" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddFunction?id=${l.id }&op=addModel&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&randomId=<%=Math.random()%>')">模块</a>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',role_ymfp,')==true}">
									<a class="ymfp" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddFunction?id=${l.id}&op=addPage&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&randomId=<%=Math.random()%>')" >页面</a>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',role_gnfp,')==true}">
									<a class="gnfp" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddFunction?id=${l.id}&op=addPagecode&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&randomId=<%=Math.random()%>')" >功能</a>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',role_gnfp,')==true}">
									<a class="gnfp" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleCopy?id=${l.id}&randomId=<%=Math.random()%>')" >角色复制</a>
								</c:if>
								 -->
								<a class="mkfp" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddFunction?id=${l.id }&op=addModel&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&randomId=<%=Math.random()%>')">模块</a>
								<a class="ymfp" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddFunction?id=${l.id}&op=addPage&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&randomId=<%=Math.random()%>')" >页面</a>
								<a class="gnfp" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleAddFunction?id=${l.id}&op=addPagecode&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&randomId=<%=Math.random()%>')" >功能</a>
								<a class="gnfp" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleCopy?id=${l.id}&randomId=<%=Math.random()%>')" >角色复制</a>
							</td>
							<td>
								<!-- 
								<c:if test="${fn:contains(rolePagecodeNums,',role_zdgssjqxfp,')==true}">
									<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleCompanyAdd?id=${l.id }&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&companyType=manage&randomId=<%=Math.random()%>')" >公司授权</a>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',role_zdgssjqxfp,')==true}">
									<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleCompanyAdd?id=${l.id }&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&companyType=finance&randomId=<%=Math.random()%>')" >财务授权</a>
								</c:if>
								 -->
								<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleCompanyAdd?id=${l.id }&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&companyType=manage&randomId=<%=Math.random()%>')" >公司授权</a>
								<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleCompanyAdd?id=${l.id }&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&companyType=finance&randomId=<%=Math.random()%>')" >财务授权</a>
							</td>
							<td>
								<!-- 
								<c:if test="${fn:contains(rolePagecodeNums,',role_ryssxz,')==true}">
									<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleUserAddBySearch?id=${l.id}&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&qRoleStatus=${qRoleStatus }&randomId=<%=Math.random()%>')" >人员搜索</a>
								</c:if>
								<c:if test="${fn:contains(rolePagecodeNums,',role_ryxz,')==true}">
									<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleUserAddByUser?id=${l.id}&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&qRoleStatus=${qRoleStatus }&vcAccount=${vcAccount}&randomId=<%=Math.random()%>')" >人员选择</a>
								</c:if>
								 -->
								<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleUserAddBySearch?id=${l.id}&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&vcAccount=${vcAccount}&qRoleStatus=${qRoleStatus }&randomId=<%=Math.random()%>')" >人员搜索</a>
								<a class="xg" href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_roleUserAddByUser?id=${l.id}&pageNum=${msgPage.pageNum }&qRoleName=${qRoleName }&qRoleStatus=${qRoleStatus }&vcAccount=${vcAccount}&randomId=<%=Math.random()%>')" >人员选择</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.roleList}">
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
	<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
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
	     	var url = "${pageContext.request.contextPath}/sys/role/_query?type=role&pageNum="+pageNum+"&"+entity;
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
	     	
		    var url = "${pageContext.request.contextPath}/sys/role/_query?type=role&pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function page(pageNum){
			var entity = $('#form1').serialize();
			var url = "${pageContext.request.contextPath}/sys/role/_query?type=role&pageNum="+pageNum+"&"+entity;
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
			win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/sys/role/_del?id=" + id;
					$.post(url, function(data) {
						win.successAlert('删除成功！');
						$("#form1").submit();
					});
				}
			});
		}
		
		function startRole(id){
			win.confirm('确定要启用？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/sys/role/_roleStart?id=" + id;
					$.post(url, function(data)	{
						win.successAlert('启用成功！');
						$("#form1").submit();
					});
				}
			});
		}
		
		function stopRole(id){
			win.confirm('确定要禁用？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/sys/role/_roleStop?id=" + id;
					$.post(url, function(data)	{
						win.successAlert('禁用成功！');
						$("#form1").submit();
					});
				}
			});
		}
		
		$(function(){
			var save = "${save}";
			if(save == 'success'){
				win.successAlert('保存成功！');
				save = "";
			}
			if(save == 'success1'){
				win.successAlert('分配成功！');
				save = "";
			}
		});
	</script>
</body>
</html>
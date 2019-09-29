<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>系统注册新增、编辑页面</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
		
		<style>
			.char_li{
				position: relative;
			}
			.char_check{
				display: none;
			}
			.char_label{
				/*position: absolute;
				top: 3px;*/
			
				display: inline-block;
				margin: 0;
				width: 11px;
				height: 11px;
				/*border: 1px solid #444;*/
				border-radius: 3px;
				
				box-shadow: 1px solid #444;
			}
			.char_check:checked+.char_label{
				display: inline-block;
				width: 11px;
				height: 11px;
				background: #63d0f1;
				border-radius: 3px;
				border: none;
				
				box-shadow: 0 0 0 1px #777;
			}
			.char_li span{
				padding: 0 18px;
				padding-left:0;
				margin-top: 5px;
			}
		</style>
		
	</head>
	<body>
		<!-- 角色编辑，以及角色新建模态 -->
　　		<div class = "model_content">
			<h4><span class="glyphicon glyphicon-pencil"></span>编辑</h4>
			<div style="overflow:scroll;overflow-x:hidden" class="model_body">
				<form id="form2" action="${pageContext.request.contextPath}/sys/role/_save" method="post">
					<div class="row">
						<label class="col-md-1">角色编码:</label>
						<input type="text" id="roleNum" name="roleNum" value="${hhRole.roleNum }">
					</div>
					<div class="row">
						<label class="col-md-1">角色名称:</label>
						<input type="text" id="roleName" name="roleName" value="${hhRole.roleName }">
					</div>
					<div class="row">
						<label class="col-md-1">角色描述:</label>
						<div class="jsms_textarea">
							<textarea id="roleDescription" name="roleDescription">${hhRole.roleDescription }</textarea>
						</div>
					</div>
					<div class="row">
						<label class="col-xs-1">所属系统：</label>
						<div class="col-xs-11 js1">
							<c:if test="${!empty requestScope.sysList }">
								<c:forEach items="${ requestScope.sysList}" var="l" varStatus="status">
									<c:if test="${hhRole.sysId == l.id }">
										<a class="btn btn-info" id="js1_${l.id }" onclick="getSysId(this);" >${l.sysName }</a>
									</c:if>
									<c:if test="${hhRole.sysId != l.id }">
										<a class="btn btn-default" id="js1_${l.id }" onclick="getSysId(this);" >${l.sysName }</a>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${empty requestScope.sysList }">
								暂无数据
							</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-10 col-xs-offset-1">
							<c:if test="${!empty requestScope.sysList }">
								<c:forEach items="${ requestScope.sysList}" var="l" varStatus="status">
									<c:if test="${hhRole.sysId == l.id }">
										<ul class="js1_${l.id } show">
											<!-- 系统页面 -->
											<c:forEach var="l1" items="${l.pageList }" varStatus="status1">
												<li class="char_li">
												    <c:if test="${fn:contains(pageIds,l1.id)==true}"> 
												    	<input class="char_check" type="checkbox" name="pageIds" value="${l1.id}" id="js1_${l.id }_${l1.id}" checked="checked">
														<label class="char_label" for="js1_${l.id }_${l1.id}"></label>
														<span class="char_text">${l1.pageName }</span>
														<!-- <input type="checkbox" id="js1_${l.id }_${l1.id}" name="pageIds" value="${l1.id}" checked="checked">${l1.pageName } -->
												    </c:if>
												    <c:if test="${fn:contains(pageIds,l1.id)!=true}"> 
														<input class="char_check" type="checkbox" name="pageIds" value="${l1.id}" id="js1_${l.id }_${l1.id}">
														<label class="char_label" for="js1_${l.id }_${l1.id}"></label>
														<span class="char_text">${l1.pageName }</span>
														<!-- <input type="checkbox" id="js1_${l.id }_${l1.id}" name="pageIds" value="${l1.id}" >${l1.pageName } -->
												    </c:if>
													<!-- 页面功能 -->
													<c:forEach var="l2" items="${l1.codeList }" varStatus="status2">
														<c:if test="${fn:contains(pageCodeIds,l2.id)==true}"> 
															<input class="char_check" type="checkbox" name="codeIds" value="${l.id }_${l1.id}_${l2.id}" id="js1_${l.id }_${l1.id}_${l2.id}" checked="checked">
															<label class="char_label" for="js1_${l.id }_${l1.id}_${l2.id}"></label>
															<span>${l2.codeName }</span>
															<!-- <input type="checkbox" id="js1_${l.id }_${l1.id}_${l2.id}" name="codeIds" value="${l.id }_${l1.id}_${l2.id}" checked="checked">${l2.codeName }  -->
														</c:if>
														<c:if test="${fn:contains(pageCodeIds,l2.id)!=true}"> 
															<input class="char_check" type="checkbox" name="codeIds" value="${l.id }_${l1.id}_${l2.id}" id="js1_${l.id }_${l1.id}_${l2.id}">
															<label class="char_label" for="js1_${l.id }_${l1.id}_${l2.id}"></label>
															<span>${l2.codeName }</span>
															<!-- <input type="checkbox" id="js1_${l.id }_${l1.id}_${l2.id}" name="codeIds" value="${l.id }_${l1.id}_${l2.id}" >${l2.codeName } -->
														</c:if>
													</c:forEach>
												</li>
											</c:forEach>
										</ul>
									</c:if>
									<c:if test="${hhRole.sysId != l.id }">
										<ul class="js1_${l.id } hidden">
											<!-- 系统页面 -->
											<c:forEach var="l1" items="${l.pageList }" varStatus="status1">
												<li class="char_li">
													<input class="char_check" type="checkbox" name="pageIds" value="${l1.id}" id="js1_${l.id }_${l1.id}">
													<label class="char_label" for="js1_${l.id }_${l1.id}"></label>
													<span class="char_text">${l1.pageName }</span>
													<!-- <input type="checkbox" id="js1_${l.id }_${l1.id}" name="pageIds" value="${l1.id}">${l1.pageName } -->
													<!-- 页面功能 -->
													<c:forEach var="l2" items="${l1.codeList }" varStatus="status2">
														<input class="char_check" type="checkbox" name="codeIds" value="${l.id }_${l1.id}_${l2.id}" id="js1_${l.id }_${l1.id}_${l2.id}">
														<label class="char_label" for="js1_${l.id }_${l1.id}_${l2.id}"></label>
														<span>${l2.codeName }</span>
														<!-- <input type="checkbox" id="js1_${l.id }_${l1.id}_${l2.id}" name="codeIds" value="${l.id }_${l1.id}_${l2.id}">${l2.codeName } -->
													</c:forEach>
												</li>
											</c:forEach>
										</ul>
									</c:if>
								</c:forEach>
							</c:if>
						</div>
					</div>
					<!-- <div class="row">
						<label class="col-xs-1">系统页面: </label>
						<div class="col-xs-11 js1">
							<a class="btn btn-info" id="js2_1">股权投资结构图</a>
							<a class="btn btn-default" id="js2_1">股权投资结构图1</a>
						</div>
					</div>
					<div class="row">
						<label class="col-xs-1">页面功能: </label>
						<div class="col-xs-11 js1">
							<a class="btn btn-info" id="js3_1">资产</a>
							<a class="btn btn-default" id="js3_2">股权</a>
							<a class="btn btn-default" id="js3_3">工商</a>
							<a class="btn btn-default" id="js3_4">印章</a>
							<a class="btn btn-default" id="js3_5">其他</a>
						</div>
					</div> -->
					<input type="hidden" id="sysId" name="sysId" value="${hhRole.sysId }">
					<input type="hidden" id="id" name="id" value="${hhRole.id }">
					<input type="hidden" id="isDel" name="isDel" value="${hhRole.isDel }">
					<input type="hidden" id="isUse" name="isUse" value="${hhRole.isUse }">
					<input type="hidden" id="creator" name="creator" value="${hhRole.creator }">
					<input type="hidden" id="createTime" name="createTime" value="${hhRole.createTime }">
				</form>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
				<button class="btn btn-primary model_btn_ok" onclick="saveRole()">提交</button>
			</div>
		</div>
	</body>
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap-treeview.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/js/iframe.js"/>" type="text/javascript"></script>
	
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
	<script type="text/javascript">
		function getSysId(a){
			var idStr = a.id;
			var strs = idStr.split("_");
			document.getElementById("sysId").value = strs[1];
		}
		
		function saveRole(){
	       	/* var checkbox = document.getElementsByName("codeIds");  
	       	var id ="";  
	       	for ( var i = 0; i < checkbox.length; i++) {  
	           	if(checkbox[i].checked){  
	               	id = id + checkbox[i].value+",";  
	           	}  
	       	}   */
			/* var entity = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/sys/role/_save?"+entity;
		    window.location.href=url; */
		    $("#form2").submit();
		} 
		
		function checkSysNum(obj){
			var sysNum = obj.value;
			$.ajax({
			  type:'POST',
			  url:"${pageContext.request.contextPath}/sys/register/_checkSysNum",
			  data:{"sysNum":sysNum},
			  success:function(data){
			  	var jsonObj = eval("(" + data + ")"); 
			  	if(jsonObj.num > 0){
			  		alert("系统编号不唯一！请重新输入！")
			  	}
			  }
			});
		}
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　　$(".bg_model").hide();
		});
	</script>
</html>
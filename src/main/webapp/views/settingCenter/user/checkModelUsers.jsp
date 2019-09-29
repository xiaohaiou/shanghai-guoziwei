<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>默认模块已分配人员查看页面</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
		<style type="text/css">
			.btn-info{
				background:#FFF;
				border:none;
				color:#337ab7;			
			}	
			.btn-info:hover, .btn-info:active, .btn-info:focus{
				background:#FFF;
				border:none;
				color:#337ab7;		
			}	
		</style>
	</head>
	<body>
　　		<div class = "model_content">
			<h4><span class="glyphicon glyphicon-pencil"></span>默认模块已分配人员查看</h4>
			<div style="overflow:scroll;overflow-x:hidden" class="model_body">
				<form action="">
					<div class="row"><label class="col-md-1">模块编号:</label><input type="text" id="modelNum" name="modelNum" disabled="" value="${register.modelNum }"></div>
					<div class="row"><label class="col-md-1">模块名称:</label><input type="text" id="modelName" name="modelName" disabled="" value="${register.modelName }"></div>
					<div class="row"><label class="col-md-1">模块描述:</label><div class="jsms_textarea"><textarea name="modelDescription" id="modelDescription" disabled="">${register.modelDescription }</textarea></div></div>
					<div class="tablebox">
						<table>
							<tr class="table_header">
								<th>序号</th>
								<!-- <th>所属公司</th> -->
								<th>所属部门</th>
								<th>人员名称</th>
							</tr>
							<c:if test="${!empty requestScope.selectedHhUsersList }">
								<c:forEach items="${requestScope.selectedHhUsersList }" var="user" varStatus="status">
									<tr>
										<td>${status.index +1}</td>
										<td>${user.vcFullName}</td>
										<td>${user.vcName }</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty requestScope.selectedHhUsersList}">
								<tr>
									<td colspan="3" align="center">
										未分配人员
									</td>
								</tr>
							</c:if>
						</table>
					</div>
				</form>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	</body>
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
	
	<!--树  -->
	<script src="<c:url value="/settingCenter/assets1/js/jquery.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/assets1/js/bootstrap.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/assets1/js/menu.js"/>"></script>
	<script type="text/javascript">
		function submitSys(){
			if($("#groupNum").val() == "") {
				win.errorAlert("用户组编号不能为空");
				return false;
			}
			if($("#groupName").val() == "") {
				win.errorAlert("用户组名称不能为空");
				return false;
			}
			if($("#groupDescription").val() == "") {
				win.errorAlert("用户组描述不能为空");
				return false;
			}
		    $("#form2").submit();
		} 
		
		//给所有Label标签绑定点击触发事件
		$(document).ready(function(){
			$('.tree-box').on('click','label',function(){
				// 增加、删除已选中员工名单(隐藏div里面)
				var vcEmployeeId = $(this).attr('for');
				if ($(this).attr('class') == 'checkboxList'){//人员未被选中
					for(var i=0;i<$("#list_choosed input").length;i++){
						if(vcEmployeeId==$("#list_choosed input")[i].value){
							$("#list_choosed input")[i].remove();
						}
					}
				}else{//人员被选中
					$("#list_choosed").append("<input type='hidden' name='vcEmployeeIds' value='" + vcEmployeeId + "'>");
				}
			});
		});
		
		function searchdata(compId) {
			if ($("#a"+compId).next().attr('class') == 'level-2 active') {//判断此a标签是否点击过，如果已点击a标签，人员列表已出现，则收起人员
				$("#a"+compId).next().remove();
			}else {
				var vcEmployeeIds = "";
				for(var i=0;i<$("#list_choosed input").length;i++){
					vcEmployeeIds += $("#list_choosed input")[i].value + ",";
				}
				var url = "${pageContext.request.contextPath}/user/group/_searchPerson";
				$.ajax({
					url : url,
					type : "POST",
					dataType : 'json',
					data : {
						id : compId,
						vcEmployeeIds : vcEmployeeIds,
					},
					success : function(result) {
						$("#a"+compId).parent().append(result);
					},
					error : function() {
					}
				});
			}
		}
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　　$(".bg_model").hide();
			$(".bg_model").empty();
		});
	</script>
</html>
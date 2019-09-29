<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>人员组新增、编辑页面</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<!-- 树 -->
		<link href="<c:url value="/settingCenter/assets1/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/settingCenter/assets1/css/font/iconfont.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/settingCenter/assets1/css/custom.css"/>" rel="stylesheet" type="text/css" />
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
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
			<c:choose>
				<c:when test="${op eq 'modify'}">
					<h4><span class="glyphicon glyphicon-pencil"></span>编辑</h4>
				</c:when>
				<c:otherwise>
					<h4><span class="glyphicon glyphicon-pencil"></span>新增</h4>
				</c:otherwise>
			</c:choose>
			<div style="overflow:scroll;overflow-x:hidden" class="model_body">
				<form id="form2" action="${pageContext.request.contextPath}/user/group/_saveOrUpdate" method="post">
					<input type="hidden" name="pageNum" value="${pageNum }">
					<input type="hidden" name="qGroupName" value="${qGroupName }">
					<input type="hidden" name="qGroupStatus" value="${qGroupStatus }">
					<input type="hidden" name="groupStatus" value="${theGroup.groupStatus }">
					<input type="hidden" id="id" name="id" value = "${theGroup.id }">
					<input type="hidden" id="createTime" name="createTime" value = "${theGroup.createTime }">
					<input type="hidden" id="creator" name="creator" value ="${theGroup.creator }">
					<div class="row"><label class="col-md-1">用户组编号:</label><input type="text" id="groupNum" name="groupNum" value="${theGroup.groupNum }"></div>
					<div class="row"><label class="col-md-1">用户组名称:</label><input type="text" id="groupName" name="groupName" value="${theGroup.groupName }"></div>
					<div class="row"><label class="col-md-1">用户组描述:</label><div class="jsms_textarea"><textarea name="groupDescription" id="groupDescription">${theGroup.groupDescription }</textarea></div></div>
					<div class="people_select_list">
						<label class="col-md-12">人员选择:</label>
						<div class="list_company" id="tree_user">
							<div class="tree-box">
								<ul class="checkbox-group role-group">
									${userShtml }
								</ul>
							</div>
						</div>
					</div>
					<!--已选中的人员，隐藏  -->
					<div style="display:none" id="list_choosed">
						<c:forEach items="${selectedHhUsersList }" var="selectUser">
							<input type="hidden" name="vcEmployeeIds" value="${selectUser.vcEmployeeId }">
						</c:forEach>
					</div>
				</form>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
				<button class="btn btn-primary model_btn_ok" onclick="submitSys()">提交</button>
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
							if (isIE()) {
						         $("#list_choosed input")[i].removeNode();
						    }else{
						    	$("#list_choosed input")[i].remove();
						    }
						}
					}
				}else{//人员被选中
					$("#list_choosed").append("<input type='hidden' name='vcEmployeeIds' value='" + vcEmployeeId + "'>");
				}
			});
		});
		
		//判断浏览器版本，解决兼容性方法问题
		function isIE() {
		     if (!!window.ActiveXObject || "ActiveXObject" in window){
		         return true;
		     }else{
		         return false;
		     }
	    } 
		
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
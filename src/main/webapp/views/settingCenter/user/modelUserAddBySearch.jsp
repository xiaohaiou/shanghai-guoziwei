<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
		<title>角色搜索人员分配</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font_1/iconfont.css"/>" />
		<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/style.min.css"/>" />
		
		<!-- 树 -->
		<link href="<c:url value="/settingCenter/assets1/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/settingCenter/assets1/css/font/iconfont.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/settingCenter/assets1/css/custom.css"/>" rel="stylesheet" type="text/css" />
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
			.clearboth{
				clear:both;
				padding:0 15px !important;
				margin-bottom:15px;
			}
			.clearboth input{
				width:14em;
				margin-left:1em;
			}
			.people_select label{
				line-height:3em;
			}
			.people_select_list{
			    width: 80%;
    			margin: 0 auto;
			}
		</style>
		
</head>
<body>
<!-- 员工角色分配 -->
　　<div class = "model_content people_model_content">
		<h4><span class="glyphicon glyphicon-user"></span> 人员搜索分配</h4>
		<div style="overflow:auto;overflow-y:hiddens" class="model_body model_shadow">
			<form id="form2"  method="post" action="${pageContext.request.contextPath}/model/user/_modelUserSaveOrUpdate">
				<input type="hidden" name="pageNum" id="pageNum" value="${pageNum }">
				<input type="hidden" name="qModelNum" id="qModelNum" value="${qModelNum }">
				<input type="hidden" name="qModelName" id="qModelName" value="${qModelName }">
				<input type="hidden" name="qSysId" id="qSysId" value="${qSysId }">
				<input type="hidden" name="modelId" id="modelId" value="${register.id }">
				<div class="model_body row">
					<div class="clearboth col-md-12">
						<div class="col-md-4"><label>模块编号:</label><input type="text" value="${register.modelNum }" disabled=""></div>
						<div class="col-md-4"><label>模块名称:</label><input type="text" value="${register.modelName }" disabled=""></div>
						<div class="col-md-4"><label>模块描述:</label><input type="text" value="${register.modelDescription }" disabled=""></div>
					</div>
					<div class="people_select row" id="tree_user">
						<label class="col-md-12">人员姓名:<input type="text" id="userName" value="${userName }"><a onclick="searchUser()">搜索</a></label>
						<label class="col-md-12">人员选择:</label>
						<div class="people_select_list">
							<div class="tree-box">
								<ul class="checkbox-group role-group" id="theUsers">
									
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
				</div>
			</form>
		</div>
		<div class="model_btn">
			<button class="btn btn-default model model_btn_close">关闭</button>
			<button class="btn btn-primary model_btn_ok" onclick="submitUsersModelBySearch()">提交</button>
		</div>
　　</div>
	
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap-treeview.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/js/iframe.js"/>" type="text/javascript"></script>
	
	
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/jstree.min.js"/> "type="text/javascript"></script>
	
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
	<!--树  -->
	<script src="<c:url value="/settingCenter/assets1/js/jquery.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/assets1/js/bootstrap.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/assets1/js/menu.js"/>"></script>
	<script type="text/javascript">
		function submitUsersModelBySearch(){
			/* var vcEmployeeIds = "";
			//提交时，将已选择用户的vcEmployeeId进行字符串相加，并以“，”相隔
			for(var i=0;i<$("#list_choosed li").length;i++){
				vcEmployeeIds += $("#list_choosed li")[i].getAttribute('value') + ",";
			}
		    $("#vcEmployeeIds").val(vcEmployeeIds); */
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
					//对此人员ID进行校验，如果此人员已绑定其他模块，则取消选中
					var modelId = $("#modelId").val();
					$.ajax({
						url:"${pageContext.request.contextPath}/model/user/_checkPerson",
						type:"POST",
						data:{vcEmployeeId:vcEmployeeId,modelId:modelId},
						success:function(data){
							if(data == "success"){
								$("#list_choosed").append("<input type='hidden' name='vcEmployeeIds' value='" + vcEmployeeId + "'>");
							}else{
								win.errorAlert("此人员已绑定其他模块！");
								$("#label_"+vcEmployeeId).removeClass("checkbox_checked");
							}
						}
					});
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
		
		/* function searchdata(compId) {
			if ($("#a"+compId).next().attr('class') == 'level-2 active') {//判断此a标签是否点击过，如果已点击a标签，人员列表已出现，则收起人员
				$("#a"+compId).next().remove();
			}else {
				var vcEmployeeIds = "";
				for(var i=0;i<$("#list_choosed input").length;i++){
					vcEmployeeIds += $("#list_choosed input")[i].value + ",";
				}
				var url = "${pageContext.request.contextPath}/sys/role/_searchPerson";
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
		} */
		
		function searchUser(){
			$("#theUsers").empty();
			var vcEmployeeIds = "";
			for(var i=0;i<$("#list_choosed input").length;i++){
				vcEmployeeIds += $("#list_choosed input")[i].value + ",";
			}
			var url = "${pageContext.request.contextPath}/model/user/_searchPersonByName";
			var userName = $("#userName").val();
			if(userName != "" && userName != null){
				$.ajax({
					type:"POST",
					dataType : 'json',
					data:{userName:userName,
						  vcEmployeeIds:vcEmployeeIds},
					url:url,
					success:function(data){
						$("#theUsers").append(data);
					}
				});
			}else{
				win.errorAlert("请输入需要人员姓名！");
				return false;
			}
		}

		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　　$(".bg_model").hide();
			$(".bg_model").empty();
		});
	</script>
</body>

</html>

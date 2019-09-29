<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
		<title>人员选择</title>
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
		<style type="text/css">
			html{
				overflow: visible;
				height:100%;
			}
			body{
				position: relative;
				hegiht:auto !important;
			}
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
			#form2>div label, #formId>div>label{
				text-align:left;
			}
		</style>
		
</head>
<body>
<!-- 员工角色分配 -->
　　<div class = "model_content people_model_content">
		<div style="overflow:auto;overflow-y:hiddens" class="model_body model_shadow">
			<form id="form2"  method="post" action="${pageContext.request.contextPath}/sys/role/_roleUserByUserSaveOrUpdate?type=roleUser">
				<div class="model_body row">
					<div class="people_select row" id="tree_user">
						<label class="col-md-6">姓名:<input type="text" id="userName" value="${userName}"><a onclick="searchUser()">搜索</a></label>
						<%-- <label class="col-md-6">已选中人员:<input type="text" id="selectUser" value="${selectUser}" readonly="true"></label> --%>
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
			<button class="btn btn-primary model_btn_ok" onclick="submitUsersRoleByUser()">确认</button>
		</div>
　　</div>
</body>
</html>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.ztree.all.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
	<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		
		function submitUsersRoleByUser(){
			var vcEmployeeId = "";
			var vcEmployeeName = "";
			var count = 0;
			//提交时，将已选择用户的vcEmployeeId进行字符串相加，并以“，”相隔
			$.each($('input:checkbox'),function(){
                if(this.checked){
                    count = count + 1 ;
                	vcEmployeeId += $(this).val();
                	vcEmployeeName += $(this).attr("vcName");
                }
            });
		    if(count > 1){
		    	alert("只能选择一名员工！");
		    	return false;
		    }
		    debugger;
		    var responsiblePerson = "${personname}";
		    var personid = "${personid}";
		    window.parent.document.getElementById(""+responsiblePerson+"").value = vcEmployeeName;
		    window.parent.document.getElementById(""+personid+"").value = vcEmployeeId;
		    window.parent.mclose();
			return false;
		}
		
		//给所有Label标签绑定点击触发事件
		$(document).ready(function(){
			$('.tree-box').on('click','label',function(){
				//增加、删除已选中员工名单(隐藏div里面)
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
						alert(vcEmployeeId);
					}
				}else{//人员被选中
					$("#list_choosed").append("<input type='hidden' name='vcEmployeeIds' value='" + vcEmployeeId + "'>");
				}
			});
		});
		
		//判断浏览器类型，处理不兼容的方法
		function isIE() {
		     if (!!window.ActiveXObject || "ActiveXObject" in window){
		         return true;
		     }else{
		         return false;
		     }
	    }    
		
		function searchUser(){
			$("#theUsers").empty();
			var vcEmployeeIds = "";
			for(var i=0;i<$("#list_choosed input").length;i++){
				vcEmployeeIds += $("#list_choosed input")[i].value + ",";
			}
			var url = "${pageContext.request.contextPath}/common/_searchPersonByName";
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
	</script>
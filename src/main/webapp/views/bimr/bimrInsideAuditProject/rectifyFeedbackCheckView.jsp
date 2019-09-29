<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>内部审计项目跟踪反馈</title>
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
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>内部审计项目跟踪反馈
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="rectifyQuestion" method="post">
		<div class="label_inpt_div">
		  <h4><span style="font-weight: 1px;">查询审计项目相关问题整改</span></h4>
			<div class="model_part">
				<label class="w20">审计项目名称:</label>
				<label class="w70 setleft">${entity.projectName}</label><br>

				<label class="w20">整改问题状态:</label>
				<label class="w70 setleft">${entity.status == 1?"整改中":"已完成"}</label><br>

				<label class="w20">整改责任人:</label>
				<label class="w70 setleft">${entity.rectifyResponseName}</label>

				<label class="w20">整改时限:</label>
				<label class="w70 setleft">${entity.rectifyTime}</label><br>

				<label class="w20">整改责任部门:</label>
				<label class="w70 setleft">${entity.rectifyResponseDept}</label>

				<label class="w20">整改对接人:</label>
				<label class="w70 setleft">${entity.rectifyPickerName}</label><br>

				<label class="w20">存在问题:</label>
				<label class="w70 setleft">${entity.problems}</label>

				<label class="w20">审计建议:</label>
				<label class="w70 setleft">${entity.rectifyAdvice}</label><br>
			</div>
			<div class="tablebox">
				<table id="csTab">
					<tr class="table_header">
						<th width="30%">整改措施</th>
						<th width="10%">反馈时间</th>
						<th width="50%">反馈内容</th>
						<th width="20%">措施状态</th>
					</tr>
					<c:if test="${!empty requestScope.feedList}">
						<c:forEach items="${ requestScope.feedList}" var="l"varStatus="status">
							<tr>
								<td style="text-align: left;">${l.measuresDesc}</td>
								<td style="text-align: left;">${l.feedbackDate}</td>
								<td style="text-align: left;">${l.feedbackDesc}</td>
								<td>
									<c:if test="${'1' eq l.status}">整改中</c:if>
									<c:if test="${'2' eq l.status}">整改完成</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.feedList}">
						<c:forEach items="${ requestScope.feedList}" var="l"varStatus="status">
							<tr>
								<td colspan="4">暂无数据</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div class="model_part">
				<lable class="w20"><span class="required"> * </span>审核意见</lable>
				<textarea name="opinion" id="opinion" cols="5" rows="3" class="w70" placeholder="请输入审核意见" maxlength="500" check="NotEmpty_string_500_._._._."></textarea>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_pass" data-id="${entity.id}">通过</button>
				<button class="btn btn-default model model_btn_reject" data-id="${entity.id}">退回</button>
				<button class="btn btn-default model model_btn_close" data-id="${entity.id}">取消</button>
			</div>
		</div>
	</form:form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
		//审核通过按钮.
		$(".model_btn_pass").click(function () {
            if(!vaild.all()) {
                return false;
            }
		    var _url = "${pageContext.request.contextPath}/bimr/rectify/commitexam";
			var _id = $(this).attr("data-id");
            var op = $('#opinion').val();
		    $.ajax({  
			     url : _url,  
			     type : "POST",  
			     data:{"entityid" : _id, "examStr" : op, "examResult" : 50115},
		         async: false,  
		         cache: false,  
		         dataType:"json",  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert("审核通过！",function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },   
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
					 alert(XMLHttpRequest.status);
					 alert(XMLHttpRequest.readyState);
					 alert(textStatus);
				 },
			}); 
			return false;
		});
         
		//审核退回按钮.
		$(".model_btn_reject").click(function () {
			if(!vaild.all()) {
                return false;
            }
			var _url = "${pageContext.request.contextPath}/bimr/rectify/commitexam";
			var _id = $(this).attr("data-id");
            var op = $('#opinion').val();
		    $.ajax({  
			     url : _url,  
			     type : "POST",  
			     data:{"entityid" : _id, "examStr" : op, "examResult" : 50114},
		         async: false,  
		         cache: false,  
		         dataType:"json",  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert("审核不通过！",function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },   
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
					 alert(XMLHttpRequest.status);
					 alert(XMLHttpRequest.readyState);
					 alert(textStatus);
				 },
			}); 
			return false;
		});
</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>审计项目审核界面</title>
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
		<span class="glyphicon glyphicon-pencil"></span>审计项目审核界面
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
		  <h4><span style="font-weight: 1px;">查询审计项目</span></h4>
			<div class="model_part">
				<label class="w20">审计项目编号:</label>
				<label class="w25 setleft">${entity.auditProjectCode}</label>
				<label class="w20">审计项目名称:</label>
				<label class="w25 setleft">${entity.auditProjectName}</label>
				
				<label class="w20">审计实施单位:</label>
				<label class="w25 setleft">${entity.auditImplDeptName}</label>
				<label class="w20">项目类别:</label>
				<label class="w25 setleft">
					<c:if test="${entity.isImportant == 0}">
						年度计划内项目
					</c:if>
					<c:if test="${entity.isImportant == 1}">
						年度计划外项目
					</c:if>
					<c:if test="${entity.isImportant == 2}">
						上级交办项目
					</c:if>
				</label>

				<label class="w20">审计项目负责人:</label>
				<label class="w25 setleft">${entity.projectPrincipal}</label>
				<label class="w20">是否子项目:</label>
				<label class="w25 setleft">${entity.isChildren==0?"否":"是"}</label>
				
				<label class="w20">审计项目目标:</label>
				<label class="w70 setleft">${entity.auditProjectGoal}</label><br>
				
				<label class="w20">审计项目性质:</label>
				<label class="w25 setleft">
					<c:if test="${entity.auditProjectProp == 80026}">
						<span>风险导向审计</span>
					</c:if>
					<c:if test="${entity.auditProjectProp == 80027}">
						<span>风险管理审计</span>
					</c:if>
					<c:if test="${entity.auditProjectProp == 80028}">
						<span>内部控制评价</span>
					</c:if>
					<c:if test="${entity.auditProjectProp == 80029}">
						<span>经济责任审计</span>
					</c:if>
					<c:if test="${entity.auditProjectProp == 80030}">
						<span>企业巡视</span>
					</c:if>
					<c:if test="${entity.auditProjectProp == 80031}">
						<span>调研管理</span>
					</c:if>
				</label>
				<label class="w20">被审计单位:</label>
				<label class="w25 setleft">${entity.auditDeptedName}</label>
				
				<c:if test="${entity.auditProjectProp == 80029}">
					<label class="w20">被审计人员:</label>
					<label class="w25 setleft">${entity.auditDeptedPerson}</label>
					<label class="w20">被审计人员职位:</label>
					<label class="w25 setleft">${entity.auditDeptedPersonPost}</label>
				</c:if>
				
				<label class="w20">审计项目开始日期:</label>
				<label class="w25 setleft">${entity.auditStartDate}</label>
				<label class="w20">审计项目结束日期:</label>
				<label class="w25 setleft">${entity.auditEndDate}</label>
				
				<label class="w20">审计项目内容:</label>
				<label class="w70 setleft">${entity.auditProjectContent}</label>
				<br>
				<label class="w20">报批公文号:</label>
				<label class="w25 setleft">${entity.docNum}</label>
				<label class="w20">审计报告及附件:</label>
				
				<label class="w25 setleft"><c:if test="${not empty entity.sjFileId}">
					<c:if test="${not empty sjFiles}">
						<c:forEach items="${sjFiles}" var="sjFile" varStatus="index">
						${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(sjFile.filePath,"\\","\\\\")}&_name=${sjFile.fileName}')" target="_blank">${sjFile.fileName}</a> ,
						</c:forEach>
						
					</c:if>
				</c:if></label>
				
				<label class="w20">邮件审批:</label>
				<label class="w25 setleft"><c:if test="${not empty entity.mailFileId}">
					<c:if test="${not empty mailFile}">
						<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(mailFile.filePath,"\\","\\\\")}&_name=${mailFile.fileName}')" target="_blank">${mailFile.fileName}</a>
					</c:if>
				</c:if></label>
				<br>
				<label class="w20">备注:</label>
				<label class="w70 setleft">${entity.remark}</label>
			</div>
			<h4><span style="font-weight: 1px;">申请办结批复意见</span></h4>
			<div class="model_part">
			    <table style="width: 100%;">
					<tr>
					    <th style="width: 15%;">审核人</th>
					    <th style="width: 15%">审核时间</th>
					    <th style="width: 70%;">审核意见</th>
					</tr>
					<tr>
						<td>${entity.examinePersonName}</td>
						<td>${entity.examineDate}</td>
						<td>${entity.opinion}</td>
					</tr>
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
		<jsp:include page="/views/system/modal.jsp" />
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
		function downloadFile(url){
			$.ajax({  
			     url : url,  
			     type : "POST",  
		         success : function(data) {
					var iframe = document.createElement("iframe");  
					iframe.src = url;  
					iframe.style.display = "none";  
					document.body.appendChild(iframe);
			     },  
			     error : function(data) {  
			     	win.errorAlert("下载失败！");
			     }  
			});
		}
		//审核通过按钮.
		$(".model_btn_pass").click(function () {
             if(!vaild.all()) {
                 return false;
             }
		     var _url = '${pageContext.request.contextPath}/bimr/inside/pass';
             var _id = $(this).attr("data-id");
             var op = $('#opinion').val();
			 $.post(_url,{id:_id,op:op},function (data) {
				 var r = data.result;
				 if (r == 1){
                     win.successAlert(data.message,function(){
                         parent.mclose();
                         parent.location.reload();
                     });
				 }else{
                     win.errorAlert(data.message,function () {
                         parent.mclose();
                         parent.location.reload();
                     });
				 }
             });
			 return false;
         });
		 //审核退回按钮.
		 $(".model_btn_reject").click(function () {
             if(!vaild.all()) {
                 return false;
             }
		     var _url = '${pageContext.request.contextPath}/bimr/inside/reject';
             var _id = $(this).attr("data-id");
             var op = $('#opinion').val();
		     $.post(_url,{id:_id,op:op},function (data) {
                 var r = data.result;
                 if (r == 1){
                     win.successAlert(data.message,function(){
                         parent.location.reload(true);
                         parent.mclose();
                     });
                 }else{
                     win.errorAlert(data.message,function () {
                         parent.location.reload(true);
                         parent.mclose();
                     });
                 }
             });
		     return false;
         });

</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>合规培训审核页面</title>
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
	<style type="text/css">
		.w75 {
			word-wrap: break-word;
		}
		.w25 {
			word-wrap: break-word;
		}
	</style>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>合规培训审核
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="bimrTrain" method="post">
		<form:hidden path="id"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${ bimrTrain.companyName}</label><br>
				<label class="w20">被培训人姓名:</label>
				<label class="w25 setleft">${ bimrTrain.beTrainPerson}</label>
				<label class="w20">培训名称:</label>
				<label class="w25 setleft">${ bimrTrain.trainName}</label><br>
				<label class="w20">培训日期:</label>
				<label class="w25 setleft">${ bimrTrain.trainDate}</label>
				<label class="w20">授课人:</label>
				<label class="w25 setleft">${ bimrTrain.lecturer}</label><br>
				<label class="w20">是否内部授课人:</label>
				<label class="w25 setleft"><c:if test="${bimrTrain.isInside eq 1}">是</c:if><c:if test="${bimrTrain.isInside eq 0}">否</c:if></label>
				<label class="w20">授课人所在单位:</label>
				<label class="w25 setleft">${ bimrTrain.lecturerCompany}</label><br>
				<label class="w20">授课人职位:</label>
				<label class="w25 setleft">${ bimrTrain.lecturerJob}</label>
				<label class="w20">授课方式:</label>
				<label class="w25 setleft"><c:if test="${bimrTrain.lecturerMode eq 1}">现场</c:if><c:if test="${bimrTrain.lecturerMode eq 2}">现场+视频</c:if><c:if test="${bimrTrain.lecturerMode eq 3}">视频</c:if></label>
				<label class="w20">培训课时:</label>
				<label class="w25 setleft">${ bimrTrain.lecturerHour}</label><br>
				<label class="w20">培训反馈情况:</label>
				<label class="w25 setleft"><c:if test="${bimrTrain.feedback eq 1}">满意</c:if><c:if test="${bimrTrain.feedback eq 2}">不满意</c:if></label>
				<label class="w20">培训地点:</label>
				<label class="w25 setleft"><c:if test="${bimrTrain.address eq 1}">境内</c:if><c:if test="${bimrTrain.address eq 2}">境外</c:if></label><br>
				<label class="w20">备注:</label>
				<label class="w25 setleft" style="word-wrap:break-word;word-break:break-all;">${ bimrTrain.remarks}</label><br>
				<label class="w20">培训资料附件:</label>
				<label class="w25 setleft"><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path1}&_name=${file_Name1}">${file_Name1==null ? "" :file_Name1}</a></label><br>
				<label class="w20">培训人员名单导入:</label>
				<label class="w25 setleft"><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path2}&_name=${file_Name2}">${file_Name2==null ? "" :file_Name2}</a></label><br>
			</div>
			<h3 class="data_title1">创建上报信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ bimrTrain.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ bimrTrain.createDate}</label><br>
				<c:if test="${bimrTrain.status != 50112}">
					<label class="w20">数据上报人:</label>
					<label class="w25 setleft">${ bimrTrain.reportPersonName}</label>
					<label class="w20">上报时间:</label>
					<label class="w25 setleft">${ bimrTrain.reportDate}</label>
				</c:if>
			</div>
			<c:if test="${!empty requestScope.examineList}">
				<h3 class="data_title1">审核意见列表</h3>
				<c:forEach items="${requestScope.examineList }" var="entityExamineview">
					<div class="model_part">
						<label class="w20">审核人:</label>
						<label class="w25 setleft">${ entityExamineview.createPersonName}</label> 
						<label class="w20">审核时间:</label>
						<label class="w25 setleft">${ entityExamineview.createDate}</label> 
						<label class="w20">审核结果:</label>
						<label class="w25 setleft">${ entityExamineview.examresultName}</label><br> 
						<label class="w20">审核意见:</label>
						<label class="w75 setleft" style="word-wrap:break-word;">${ entityExamineview.examinestr}</label> 
					</div>
				</c:forEach>
			</c:if>
			<div class="model_part">
				<label class="w20" style="vertical-align: top;"><span class="required"> * </span>审核意见:</label>
				<textarea class="w70" rows="3" cols="5" type="text" name="examStr" id="examStr" placeholder="不超过500个字符" maxlength="500" class="w25"></textarea>
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-4" >通过</button>
				<button class="btn btn-primary model_btn_ok" id="commit-5" >退回</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	</form:form>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">	
		$("#commit-4").click(function(){
			if(!checkcommit()) {
				return false;
			}
			$.post("/shanghai-gzw/bimr/train/commitexam",{entityid:$("#id").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
				if(data == "success"){
					win.successAlert('保存成功！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}else if(data == "false"){
					win.errorAlert('该数据已被删除！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}else{
					win.errorAlert('该数据已被操作！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}
	 		 });
			 return false;
		});
		$("#commit-5").click(function(){
			if(!checkcommit()) {
				return false;
			}
			$.post("/shanghai-gzw/bimr/train/commitexam",{entityid:$("#id").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
				if(data == "success"){
					win.successAlert('退回成功！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}else if(data == "false"){
					win.errorAlert('该数据已被删除！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}else{
					win.errorAlert('该数据已被操作！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}			
	 		 });
	 		 return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　	parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		function checkcommit() {
			if($("#examStr").val()=="") {
				win.errorAlert("审核意见不能为空");
				return false;
			}
			return true;
		};	
	</script>
</html>
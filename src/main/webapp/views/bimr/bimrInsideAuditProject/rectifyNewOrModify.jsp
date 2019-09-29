<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>新增审计项目整改</title>
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
			#com_ztree span {
				padding-left:0;
			}
		</style>
	</head>
	<body>
		<h4>
			<span class="glyphicon glyphicon-pencil"></span>审计项目整改发起
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<form:form id="form2" modelAttribute="rectify" method="post">
			<form:hidden path="id" value="${rectify.id}"/>
			<form:hidden path="createPersonId" value="${rectify.createPersonId}"/>
			<form:hidden path="createPersonName" value="${rectify.createPersonName}"/>
			<form:hidden path="createDate" value="${rectify.createDate}"/>
			<form:hidden path="isDel" value="${rectify.isDel}"/>
			<form:hidden path="projectId" value="${rectify.projectId}"/>
			<div class="label_inpt_div">
				<div class="model_part">
				    
				    <label class="w20"><span class="required">*</span>审计项目名称:</label>
				    	<input class="w25" type="text" name="projectName" id="projectName" value="${rectify.projectName}" readonly="readonly"><br>
					
					<label class="w20">审计发现问题:</label>
						<select name="answerId" id="answerId" class="selectpicker w70">
							<option value="0"><font color="red">请选择审计发现问题</font></option>
							<c:if test="${not empty requestScope.problems}">
								<c:forEach items="${requestScope.problems}" var="r">
									<option value="${r.id}" <c:if test="${r.id == rectify.answerId}">selected="selected"</c:if>>${r.problemTopic}</option>
								</c:forEach>
							</c:if>
						</select>
					
					<label class="w20"><span class="required"> * </span>具体需整改问题:</label>
						<textarea rows="3" cols="5" class="w70" name="problems" id="problems" placeholder="请输入具体整改问题" maxlength="500" check="NotEmpty_string_500_._._._.">${rectify.problems}</textarea><br>
	
					<label class="w20"><span class="required"> * </span>审计建议:</label>
						<textarea rows="3" cols="5" class="w70" name="rectifyAdvice" id="rectifyAdvice" placeholder="请输入审计建议" maxlength="500" check="NotEmpty_string_500_._._._.">${rectify.rectifyAdvice}</textarea><br>
						
				    <label class="w20"><span class="required">*</span>整改问题状态</label>
						<select class="w25" name="status" id="status" class="selectpicker w70">
							<option value="1" <c:if test="${'1' eq rectify.status}">selected</c:if> >待整改</option>
							<option value="2" <c:if test="${'2' eq rectify.status}">selected</c:if> >整改中</option>
							<option value="3" <c:if test="${'3' eq rectify.status}">selected</c:if> >整改完成</option>
						</select>
					
					<label class="w20"><span class="required"> * </span>整改完成时限:</label>
					<input type="text" name="rectifyTime" id="rectifyTime" value="${rectify.rectifyTime}"  readonly="readonly" class="w25 time" check="NotEmpty"/><br>
	
					<label class="w20"><span class="required"> * </span>整改责任人:</label>
					<input class="w25" type="text" name="rectifyResponseName" id="rectifyResponseName" value="${rectify.rectifyResponseName}" placeholder="请输入整改责任人" check="NotEmpty_string_50_._._._."/>
					<input type="hidden" name="rectifyResponseId" id="rectifyResponseId" value="${rectify.rectifyResponseId}"/>
	
					<label class="w20"><span class="required"> * </span>整改跟踪人:</label>
					<input class="w25" type="text" name="rectifyTracerName" id="rectifyTracerName" value="${rectify.rectifyTracerName}" placeholder="请输入跟踪人" check="NotEmpty_string_50_._._._."/>
					<input type="hidden" name="rectifyTracerId" id="rectifyTracerId" value="${rectify.rectifyTracerId}"/>
					
					<label class="w20"><span class="required"> * </span>整改对接人:</label>
					<input class="w25" type="text" name="rectifyPickerName" id="rectifyPickerName" value="${rectify.rectifyPickerName}" placeholder="请输入整改对接人" check="NotEmpty_string_50_._._._."/>
					<input type="hidden" name="rectifyPickerId" id="rectifyPickerId" value="${rectify.rectifyPickerId}"/>
	
					<label class="w20"><span class="required"> * </span>整改责任部门:</label>
					<input class="w25" type="text" name="rectifyResponseDept" id="rectifyResponseDept" value="${rectify.rectifyResponseDept}" placeholder="请输入整改责任部门" check="NotEmpty_string_50_._._._."/><br>
					<input type="hidden" name="rectifyResponseDeptId" id="rectifyResponseDeptId" value="${rectify.rectifyResponseDeptId}"/>
					
				</div>
				
				<h3 class="data_title1">整改措施&nbsp;&nbsp;<a href="javascript:void(0)" onclick="addCS()" class="form_btn">新增</a></h3>
				<div class="tablebox">
					<table id="csTab">
						<tr class="table_header">
							<th width="70%">整改措施</th>
							<th width="20%">整改完成时限</th>
							<th >操作</th>
						</tr>
						<c:if test="${!empty requestScope.measuresList }">
						<c:forEach items="${ requestScope.measuresList}" var="l"varStatus="status">
							<tr>
								<td style="text-align: left;"><input name="cs" class="w25" style="width:100% !important;"  maxlength="500" value="${l.rectifyMeasure}"/></td>
								<td style="text-align: left;"><input name="csTime" readonly="readonly" class="time" style="width:100% !important;"  maxlength="500" value="${l.rectifyMeasureTime}" check="NotEmpty"/></td>
								<td style="text-align: center;"><a href="javascript:void(0)" onclick="removePerson(this)">删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
					</table>
				</div>
				
				<div class="row model_btn">
					<button class="btn btn-primary model_btn_ok" id="save">保存</button>
					<button class="btn btn-primary model_btn_ok" id="commit">发起整改</button>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
		</form:form>
		<jsp:include page="../../system/modal.jsp"/>
	</body>

	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<%-- <script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script> --%>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.ztree.all.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
	<script type="text/javascript">
		
		$(".icon-guanbi").click(function () {
			window.parent.mclose();
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
		var timeoutId;
		var zTreeObj;
		var com_ztree_setting = {
			check:{
				enable:false,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			}
		};
		
		$('#rectifyResponseName').on('focus click',function(){
			mload("${pageContext.request.contextPath}//bimr/inside/searchPerson?personname=rectifyResponseName&personid=rectifyResponseId");
		});
		$('#rectifyTracerName').on('focus click',function(){
			mload("${pageContext.request.contextPath}//bimr/inside/searchPerson?personname=rectifyTracerName&personid=rectifyTracerId");
		});
		
		$('#rectifyPickerName').on('focus click',function(){
			mload("${pageContext.request.contextPath}//bimr/inside/searchPerson?personname=rectifyPickerName&personid=rectifyPickerId");
		});
		
	    $("#answerId").change(function(){
	    	var id = $("#answerId").val();
	    	var url = "${pageContext.request.contextPath}/bimr/question/getQuestionDetail";
	    	$.ajax({
                url : url,
				type : "POST",
				data : { id:id },
				dataType: "json" ,
                success : function(data) {
                    $("#problems").val(data.problem);
			    	$("#rectifyAdvice").val(data.auditSuggest);
			    	$("#rectifyTime").val(data.rectifyTime);
			    	$("#rectifyResponseName").val(data.rectifyResponseName);
			    	$("#rectifyTracerName").val(data.rectifyTrackName);
			    	$("#rectifyTracerId").val(data.rectifyTrackId);
			    	$("#rectifyPickerName").val(data.rectifyPicker);
			    	$("#rectifyResponseDept").val(data.rectifyResponseDept);
                }
            });
	    });
		
		function addCS(){
			var newCS="<tr><td><input name=\"cs\" class=\"w25\" style=\"width:100% !important;\"  maxlength=\"500\"/></td>"
			  +"<td style=\"text-align: left;\"><input name=\"csTime\" readonly=\"readonly\" class=\"w25 time\" style=\"width:100% !important;\" value=\"${l.rectifyMeasureTime}\" check=\"NotEmpty\"/></td>"
			  +"<td style=\"text-align: center;\"><a href=\"javascript:void(0)\" onclick=\"removePerson(this)\">删除</a></td></tr>";
			$("#csTab").append(newCS);	
		
			$('input.time').jeDate({
				format:"YYYY-MM-DD"
			});
		}
		
		function removePerson(o){
			$(o).parent().parent().remove();
		}
		
		$("#save").click(function(){
		var answerId = $("#answerId").val();
			if(answerId == 0 || answerId ==null || answerId == ""){
			 win.generalAlert("请选择问题！");
			}
		if(!vaild.all())
			{
				return false;
			}
		
            var question = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/rectify/saveOrUpdate";
            $.ajax({
                url : url,
                type : "POST",
                data: question,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success : function(data) {
                    var r = data.result;
                    if (r == 1){
                        win.successAlert(data.message,function(){
                            parent.location.reload(true);
                            parent.mclose();
                        });
					}else{
                        win.errorAlert(data.message);
					}
                },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
				}
            });
			return false;
		});
		
		$("#commit").click(function(){
			
			var answerId = $("#answerId").val();
			if(answerId == 0 || answerId ==null || answerId == ""){
			 win.generalAlert("请选择问题！");
			}
			if(!vaild.all())
			{
				return false;
			}
            var question = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/rectify/commit";
            $.ajax({
                url : url,
                type : "POST",
                data: question,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success : function(data) {
                    var r = data.result;
                    if (r == 1){
                        win.successAlert(data.message,function(){
                            parent.location.reload(true);
                            parent.mclose();
                        });
					}else{
                        win.errorAlert(data.message);
					}
                },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
				}
            });
			return false;
		});

	</script>
</html>
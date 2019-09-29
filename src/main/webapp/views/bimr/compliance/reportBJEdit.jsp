<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>维护报告办结信息</title>
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
		<span class="glyphicon glyphicon-pencil"></span>维护报告办结信息
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">举报投诉、专项调查项目名称:</label>
				<label class="w25 setleft">${ entity.projectName}</label>
				
				<label class="w20">举报投诉、专项调查项目编号:</label>
				<label class="w25 setleft">${ entity.projectCode}</label>
				
				<label class="w20">调查类型:</label>
				<label class="w25 setleft">${ entity.investigationTypeName}</label>
				
				<label class="w20">调查承办部门:</label>
				<label class="w25 setleft">${ entity.investigationDep}</label>
				
				<label class="w20">调查涉及企业名称:</label>
				<label class="w25 setleft">${ entity.compName}</label>
				
				<label class="w20">调查涉及经营管理事项:</label>
				<label class="w25 setleft">${ entity.itemName}</label>
				
				<label class="w20">调查来源:</label>
				<label class="w25 setleft">${ entity.investigationFromName}</label>
				
				<label class="w20">是否是集团内部投诉人:</label>
				<label class="w25 setleft">${ entity.isInside==0?"否":"是"}</label>
				
				<label class="w20">是否匿名:</label>
				<label class="w25 setleft">${ entity.isAnonymous==0?"否":"是"}</label>
				
				<label class="w20">举报投诉收到时间:</label>
				<label class="w25 setleft">${ entity.accusationTime}</label>
				
				<label class="w20">调查交办领导:</label>
				<label class="w25 setleft">${ entity.assignLeader}</label>
				
				<label class="w20">调查负责人:</label>
				<label class="w25 setleft">${ entity.responsiblePerson}</label>
				<br>
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ entity.createPersonName}</label>
				
				<label class="w20">数据创建时间:</label>
				<label class="w25 setleft">${ entity.createDate}</label>
			</div>
			
			
			<h3 class="data_title1">调查涉及人员</h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th >序号</th>
						<th >调查涉及人员</th>
						<th >现任职务</th>
						<th >员工编号</th>
					</tr>
					<c:if test="${!empty requestScope.personList }">
						<c:forEach items="${ requestScope.personList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: center;">${l.employeeName }</td>
								<td style="text-align: left;">${l.employeePostion }</td>
								<td style="text-align: left;">${l.employeeNumber}</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			
			<h3 class="data_title1">报告办结信息</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>调查报告公文编号:</label>
				<input class="w25" name="officeId" id="officeId" value="${bjInfo.officeId}" check="NotEmpty_string_._._._._."/>
				<label class="w20"><span class="required"> * </span>报告名称:</label>
				<input class="w25" name="name" id="name" value="${bjInfo.name}" check="NotEmpty_string_._._._._."/>
				<label class="w20"><span class="required"> * </span>呈报人:</label>
				<input class="w25" name="submitPersonName" id="submitPersonName" value="${bjInfo.submitPersonName}" check="NotEmpty_string_._._._._."/>
				<label class="w20"><span class="required"> * </span>报告呈报时间:</label>
				<input class="w25 time" name="submitTime" id="submitTime" value="${bjInfo.submitTime}" check="NotEmpty_string_._._._._." readonly="true"/>
				<label class="w20"><span class="required"> * </span>报告最终审批人:</label>
				<input class="w25" name="auditPersonName" id="auditPersonName" value="${bjInfo.auditPersonName}" check="NotEmpty_string_._._._._."/>
				<label class="w20"><span class="required"> * </span>最终审批人批示意见:</label>
				<input class="w25" name="auditContent" id="auditContent" value="${bjInfo.auditContent}" check="NotEmpty_string_._._._._."/>
				<input type="hidden" name="id" id="id" value="${bjInfo.id}"/>
				<label class="w20">附件:</label>
				<input class="w25" type="file" id="file1" name="file1"  />
			</div>
			
			<div class="model_btn">
			    <button class="btn btn-primary model_btn_save" id="but_save" >保存</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
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

    $('input.time').jeDate({
		format:"YYYY-MM-DD"
	});
		
	$(".model_btn_close").click(function () {
		parent.mclose();
		return false;
	});
		
	$(".icon-guanbi").click(function () {
		parent.mclose();
		return false;
	});
		
	function saveReportBJ(){
		if(!vaild.all()){
			return false;
		}
		$.blockUI({ message: '提交中', css: { width: '275px' } });
		var complianceId = ${entity.id};
		var formData = new FormData($("#form2")[0]);
		var url = "${mapurl}/saveReportBJ?complianceId="+complianceId;
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     $.unblockUI();
				 win.successAlert('保存成功！',function(){
						parent.location.reload(true);
						parent.mclose();
				 });
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			});
			return false;
	}
		
	$("#but_save").click(function(){
		saveReportBJ();
		return false;
	});
</script>
</html>
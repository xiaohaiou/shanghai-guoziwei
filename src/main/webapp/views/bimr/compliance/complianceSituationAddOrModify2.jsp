<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>举报投诉反映问题线索核查情况</title>
	<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
	<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
	<!-- 库|插件 -->
	<!-- 新加样式 -->
	<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body style="height:500px;">
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改调查发现其他违规违纪事件情况
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增调查发现其他违规违纪事件情况
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	
	<form:form id="form2" modelAttribute="entity" method="post" >
	<form:hidden path="id"/>
		<div class="label_inpt_div">
			<h3 class="data_title1">基本信息</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>其他违规违纪事项描述:</label>
				<textarea class="w70" rows="5" cols="" id="problem" name="problem" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${entity.problem}</textarea>
				
				<label class="w20"><span class="required"> * </span>调查确认情况:</label>
				<textarea class="w70" rows="5" cols="" id="truth" name="truth" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${entity.truth}</textarea>
				
				<label class="w20"><span class="required"> * </span>是否需要问责:</label>
				<span class="w25">
					<select name="isAccountability" id="isAccountability" class="selectpicker">
							<option value="0">否</option>
							<option value="1">是</option>
					</select>
				</span>
				
				<label class="w20"><span class="required"> * </span>问题类型:</label>
				<span class="w25">
					<select name="problemType" id="problemType" class="selectpicker">
						<c:forEach items="${requestScope.problemType}" var="result">
							<option value="${result.id }"
								<c:if test="${entity.problemType == result.id}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
					</select> 
				</span> 
				
			</div>
			<h3 class="data_title1">建议问责人员&nbsp;&nbsp;<a href="javascript:void(0)" onclick="addPerson()" class="form_btn">新增</a> </h3>
			<div class="tablebox">
				<table id="personTab">
					<tr class="table_header">
						<th >建议问责人员</th>
						<th >问责人员员工号</th>
						<th >责任界定</th>
						<th >干部/员工</th>
						<th >操作</th>
					</tr>
					<c:if test="${!empty requestScope.personList }">
						<c:forEach items="${ requestScope.personList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;"><input name="accountabilityPerson" value="${l.accountabilityPerson }" class="w25" style="width:100% !important; "></td>
								<td style="text-align: center;"><input name="accountabilityPersonId" value="${l.accountabilityPersonId }" class="w25" style="width:100% !important; "></td>
								<td style="text-align: left;">
									<select name="accountabilityDefinition" id="accountabilityDefinition" class="selectpicker" class="w25" style="width:100% !important;">
										<option value="0" <c:if test="${l.accountabilityDefinition == 0}">selected="selected"</c:if>>直接责任</option>
										<option value="1" <c:if test="${l.accountabilityDefinition == 1}">selected="selected"</c:if>>主要责任</option>
										<option value="2" <c:if test="${l.accountabilityDefinition == 2}">selected="selected"</c:if>>直接管理责任</option>
										<option value="3" <c:if test="${l.accountabilityDefinition == 3}">selected="selected"</c:if>>管理责任</option>
										<option value="4" <c:if test="${l.accountabilityDefinition == 4}">selected="selected"</c:if>>领导责任</option>
									</select> 
								</td>
								<td style="text-align: left;">
									<select name="personPostion" id="personPostion" class="selectpicker" class="w25" style="width:100% !important;">
										<option value="0" <c:if test="${l.personPostion == 0}">selected="selected"</c:if>>干部</option>
										<option value="1" <c:if test="${l.personPostion == 1}">selected="selected"</c:if>>员工</option>
									</select> 
								</td>
								<td><a href="javascript:void(0)" onclick="removePerson(this)">删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div class="model_btn">
				<input type="hidden" value="${entity.dataType }" name="dataType" id="dataType">
				<input type="hidden" value="${entity.complianceId }" name="complianceId" id="complianceId">
				<input type="hidden" value="${entity.id }" name="situationId" id="situationId">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
				
			</div>
		</div>
		
	</form:form>
	<jsp:include page="/views/system/modal.jsp" />
	<!-- <style>
		[data-remodal-id=modal] iframe {
			width:80%;
			height:500px;
		}
	</style> -->
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>

<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		$('input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
				
		function commit1(){
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
		
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/compliance/saveOrUpdateSituation";
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
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　	parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		$("#commit-1").click(commit1);
		
		$(function(){
			if("${entity.isAccountability}"!="")
				$("#isAccountability").val("${entity.isAccountability}")
		})
		
		function addPerson(){
			debugger;
			var newPerson="<tr><td style=\"text-align: center;\"><input name=\"accountabilityPerson\" class=\"w25\" style=\"width:100% !important;\"></td>"
							+"<td style=\"text-align: center;\"><input name=\"accountabilityPersonId\" class=\"w25\" style=\"width:100% !important;\"></td>"
								+"<td style=\"text-align: left;\">"
									+"<select name=\"accountabilityDefinition\" id=\"accountabilityDefinition\" class=\"selectpicker\" class=\"w25\" style=\"width:100% !important;\">"
										+"<option value=\"0\">直接责任</option>"
										+"<option value=\"1\">主要责任</option>"
										+"<option value=\"2\">直接管理责任</option>"
										+"<option value=\"3\">管理责任</option>"
										+"<option value=\"4\">领导责任</option>"
									+"</select> "
								+"</td>"
								+"<td style=\"text-align: left;\">"
									+"<select name=\"personPostion\" id=\"personPostion\" class=\"selectpicker\" class=\"w25\" style=\"width:100% !important;\">"
										+"<option value=\"0\">干部</option>"
										+"<option value=\"1\">员工</option>"
									+"</select> "
								+"</td>"
								+"<td><a href=\"javascript:void(0)\" onclick=\"removePerson(this)\">删除</a></td></tr>";
			$("#personTab").append(newPerson);			
		}
		
		function removePerson(o){
			$(o).parent().parent().remove();
		}
		
	</script>
</html>
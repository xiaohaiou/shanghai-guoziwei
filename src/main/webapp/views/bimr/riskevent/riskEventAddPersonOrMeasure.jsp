<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>风险事件</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/zTreeStyle.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'event'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>事件填写 <i
					class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>

		<c:when test="${op eq 'risk'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>风险审核 <i
					class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>

		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>风险事件查看 <i
					class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<input type="hidden" id="titletype" value="${titletype}" />
	<form:form id="form2" modelAttribute="entity" method="post">

		<div class="label_inpt_div">
			<h3 class="data_title1">报告单位</h3>
			<div class="model_part">
				<input type="hidden" value="${entity.id}" name="id" id="id">
				<label class="w20"><span class="required"> * </span>报告单位:</label> <span
					class="w25"> <input type="hidden" id="compId" name="compId"
					value="${entity.compId}"> <input name="compName"
					id="compName" value="${entity.compName}" readonly="readonly"
					class="w70"> </span> <label class="w20"><span
					class="required"> * </span>风险征兆事件编号:</label> <input class="w25"
					name="eventNum" id="eventNum" value="${entity.eventNum}"
					readonly="readonly" /> <label class="w20"><span
					class="required"> * </span>报告日期:</label> <input class="w25"
					name="reportTime" id="reportTime" value="${entity.reportTime}"
					readonly="true" /> <label class="w20"><span
					class="required"> * </span>风险征兆事件标题:</label> <input class="w25"
					name="eventTitle" id="eventTitle" value="${entity.eventTitle}"
					readonly="readonly" /> <br> <label class="w20"><span
					class="required"> * </span>风险征兆事件具体描述:</label>
				<textarea class="w70" rows="5" cols="" id="eventDescribe"
					name="eventDescribe" style="height:5em;" maxlength="500"
					readonly="readonly">${entity.eventDescribe}</textarea>

				<label class="w20"><span class="required"> * </span>风险征兆可能导致的风险:</label>
				<input class="w25" name="eventForRisk" id="eventForRisk"
					value="${entity.eventForRisk}" readonly="readonly" /> <label
					class="w20"><span class="required"> * </span>应对策略:</label> <span
					class="w25"> <select name="copingStrategy"
					class="selectpicker" id="copingStrategy" disabled="disabled">
						<c:forEach items="${requestScope.copingStrategy}" var="result">
							<option value="${result.id }"
								<c:if test="${entity.copingStrategy == result.id}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
				</select> </span>
				<%--
			    <label class="w20"><span class="required"> * </span>应对完成时间:</label>
				<input class="w25" name="completeTime" id="completeTime"  value="${entity.completeTime}" readonly="true"/>
				<br>
				<label class="w20"><span class="required"> * </span>备注:</label>
				<textarea class="w70" rows="5" cols="" id="remark" name="remark" style="height:5em;" maxlength="500" readonly="readonly" >${entity.remark}</textarea>
				--%>
				<label class="w20"><span class="required"> * </span>是否需要提醒重要风险指标:</label>
				<span class="w25"> <select name="isImportant"
					id="isImportant" class="selectpicker" disabled="disabled">
						<option value="0">否</option>
						<option value="1">是</option>
				</select> </span> <label class="w20"><span class="required"> * </span>风险征兆事件发生地点:</label>
				<span class="w25"> <select name="provinceId"
					class="selectpicker" id="provinceId" disabled="disabled">
						<c:forEach items="${requestScope.province}" var="result">
							<option value="${result.num }"
								<c:if test="${entity.provinceId == result.num}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
				</select> </span>
			</div>

			<label class="w20"><span class="required"> * </span>是否提请上级单位统筹:</label>
			<span class="w25"> <select name="ishighunit" id="ishighunit"
				class="selectpicker" disabled="disabled">
					<option value="0"
						<c:if test="${entity.ishighunit == 0}">selected="selected"</c:if>>否</option>
					<option value="1"
						<c:if test="${entity.ishighunit == 1}">selected="selected"</c:if>>是</option>
			</select> </span>
			<c:if test="${entity.ishighunit == 1}">
				<label class="w20"><span class="required"> * </span>处理方式:</label>
				<span class="w25"> <select name="highunitmeasure"
					class="selectpicker" id="highunitmeasure" disabled="disabled">
						<c:forEach items="${requestScope.handleway}" var="result">
							<option value="${result.id }"
								<c:if test="${entity.highunitmeasure == result.id}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
				</select> </span>
			</c:if>
			<c:if test="${entity.ishighunit == 0}">
				<label class="w20">拟采取的风险应对措施:</label>
				<input class="w25" name="planmeasure" id="planmeasure"
					value="${entity.planmeasure}" readonly="readonly" />

				<label class="w20"><span class="required"> * </span>预计风险征兆事件关闭的时间:</label>
				<span class="w25"> <select name="plancloesetime"
					class="selectpicker" id="plancloesetime" disabled="disabled">
						<option value="0"
							<c:if test="${entity.plancloesetime == 0}">selected="selected"</c:if>>半个月内</option>
						<option value="1"
							<c:if test="${entity.plancloesetime == 1}">selected="selected"</c:if>>一个月内</option>
						<option value="2"
							<c:if test="${entity.plancloesetime == 2}">selected="selected"</c:if>>季度内</option>
						<option value="3"
							<c:if test="${entity.plancloesetime == 3}">selected="selected"</c:if>>半年度内</option>
						<option value="4"
							<c:if test="${entity.plancloesetime == 4}">selected="selected"</c:if>>年度内</option>
						<option value="5"
							<c:if test="${entity.plancloesetime == 5}">selected="selected"</c:if>>不确定</option>
				</select> </span>
			</c:if>

			<c:if test="${entity.copingStrategy != 82002}">
				<label class="w20">风险征兆事件跟踪人:</label>
				<input class="w25" name="trackpersonname" id="trackpersonname"
					value="${entity.trackpersonname}" readonly="readonly" />
				<label class="w20"></label>
				<label class="w25"></label>
			</c:if>
			  <c:if test="${titletype == 1||titletype == 2||titletype == 3}">
					<label class="w20">风险体系内部跟踪人：</label>
					<input type="text" name="insidetrackpersonname"
						id="insidetrackpersonname" value="${entity.insidetrackpersonname}"
						class="w25" check="NotEmpty" />
					<input type="hidden" name="insidetrackpersonid"
						id="insidetrackpersonid" value="${entity.insidetrackpersonid}" />
			</c:if>  
			 <c:if test="${titletype==4}">
				<c:if test="${entity.ishighunit == 1}">
					<label class="w20">风险体系内部跟踪人：</label>
					<input type="text" name="insidetrackpersonname" id=""
						value="${entity.insidetrackpersonname}" class="w25"
						check="NotEmpty" readonly="readonly" />
					<input type="hidden" name="insidetrackpersonid"
						id="insidetrackpersonid" value="${entity.insidetrackpersonid}" />
					<h3 class="data_title1">
						应对措施&nbsp;&nbsp;<a href="javascript:void(0)" onclick="addPerson()"
							class="form_btn">新增</a>
					</h3>
					<div class="tablebox">
						<table id="personTab">
							<tr class="table_header">
								<th>风险应对措施</th>
								<th>预计措施达成时间</th>
								<th>责任部门</th>
								<th>操作</th>
							</tr>
							<c:if test="${!empty requestScope.adoptStrategyList }">
								<c:forEach items="${ requestScope.adoptStrategyList}" var="l"
									varStatus="status">
									<tr>
										<td style="text-align: left;"><input type="text" value="${l.adoptStrategyname }"
											name="adoptStrategy" class="selectpicker" id="adoptStrategy" style="width:100% !important; ">
												</td>
										<td style="text-align: left;"><input
											name="planfinishtime" value="${l.planfinishtime }"
											class="w25 time" style="width:100% !important; ">
										</td>
										<td style="text-align: left;"><input
											name="responsibleCompName" value="${l.responsibleCompName }"
											class="w25" style="width:100% !important; ">
										</td>
										<td><a href="javascript:void(0)"
											onclick="removePerson(this)">删除</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</div>
				</c:if>
			</c:if>
 

			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				<%--
				<c:if test="${op=='riskexamine' }">
					<button class="btn btn-primary model_btn_ok" id="commit-3" >通过</button>
				</c:if>
				<c:if test="${op=='riskback' }">
					<button class="btn btn-primary model_btn_ok" id="commit-4" >退回</button>
				</c:if>
				--%>
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
<script type="text/javascript"
	src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>"
	charset="utf-8"></script>

<script src="<c:url value="/js/jquery.ztree.all.min.js"/>"
	type="text/javascript"></script>
<script type="text/javascript">
		$('input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
		
		$('#insidetrackpersonname').on('focus click',function(){
			mload('${pageContext.request.contextPath}/bimr/riskEvent/searchPerson?inputname=insidetrackpersonname&inputid=insidetrackpersonid');
		});
		$("#commit-1").click(function(){
		var id = $("#entityid").val();
		var insidetrackpersonname=$("#insidetrackpersonname").val();
		var insidetrackpersonid=$("#insidetrackpersonid").val();
				var titletype=$("#titletype").val();
				var formData = new FormData($("#form2")[0]);
				var url=null;
				if(4==titletype){
					 url = "${pageContext.request.contextPath}/bimr/riskEvent/saveAdoptstrategy";
				}else{
				//?id="+id+"&insidetrackpersonname="+insidetrackpersonname+"&insidetrackpersonid="+insidetrackpersonid
					 url = "${pageContext.request.contextPath}/bimr/riskEvent/savePerson";
				}
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
		})
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　	parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		
		$(function(){
			if('${entity.isImportant}'!='')
				$("#isImportant").val('${entity.isImportant}');
			
		})
		var count=0;
		function addPerson(){
			var id1 ="adoptStrategy"+count;
			var newPerson="<tr>"
							+"<td style=\"text-align: left;\">"
								+"<input type=\"text\" class=\"w25\" name=\"adoptStrategy\"  id=\"adoptStrategy\" style=\"width:100% !important;\"/>"
											
							+"</td>"
							+"<td style=\"text-align: left;\"><input name=\"planfinishtime\" class=\"w25 time\" style=\"width:100% !important;\"></td>"
							+"<td style=\"text-align: left;\"><input name=\"responsibleCompName\" class=\"w25\" style=\"width:100% !important;\"></td>"
							+"<td><a href=\"javascript:void(0)\" onclick=\"removePerson(this)\">删除</a></td></tr>";
			$("#personTab").append(newPerson);	
			count++;
			//初始化将text赋值
			$("#"+id1+"").next().val("专业公司风控部负责");
			$('input.time').jeDate(
					{
						format:"YYYY-MM-DD"
					}
				)
			
		}
		//每次改变将text赋值
		function levelonechange(o){
			var text =$(o).find("option:selected").text()
			$(o).next().val(text);
		}
		
		function removePerson(o){
			$(o).parent().parent().remove();
		}

		
	</script>
</html>
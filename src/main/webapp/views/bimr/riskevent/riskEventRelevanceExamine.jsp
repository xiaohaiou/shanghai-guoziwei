<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>数据看报审核</title>
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
<body style="height:700px;">
<c:choose>
		<c:when test="${op eq 'examine'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>数据看报目录审核
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>数据看报目录查看
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="entity" method="post" >
	<form:hidden path="id"/>
		<div class="label_inpt_div">
			<h3 class="data_title1">报告单位</h3>
			<div class="model_part">
				<input type="hidden" value="${entity.id}" name="entityid" id="entityid">
				<label class="w20"><span class="required"> * </span>上报单位:</label>
				<span class="w25"> 
					<input type="hidden" id="compId" name="compId" value="${entity.compId}" >
					<input name="compName" id="compName" value="${entity.compName}" readonly="readonly" class="w70" >
			    </span>
				
				<label class="w20"><span class="required"> * </span>数据编号:</label>
				<input class="w25" name="eventNum" id="eventNum"  value="${entity.eventNum}" readonly="readonly"/>
				
				<label class="w20"><span class="required"> * </span>上报日期:</label>
				<input class="w25 time" name="reportTime" id="reportTime" value="${entity.reportTime}" readonly="true"/>
				
				<label class="w20"><span class="required"> * </span>数据信息标题:</label>
				<input class="w25" name="eventTitle" id="eventTitle"  value="${entity.eventTitle}" readonly="readonly"/>
				<br>
				
				<label class="w20"><span class="required"> * </span>数据具体描述:</label>
				<textarea class="w70" rows="5" cols="" id="eventDescribe" name="eventDescribe" style="height:5em;" maxlength="500"  readonly="readonly">${entity.eventDescribe}</textarea>
				
				<label class="w20"><span class="required"> * </span>数据类型:</label>
				<input class="w25" name="eventForRisk" id="eventForRisk"  value="${entity.eventForRisk}" readonly="readonly"/>
				
				
				
				<label class="w20"><span class="required"> * </span>数据处理方式:</label>
				<span class="w25">
					<select name="copingStrategy" class="selectpicker" id="copingStrategy" disabled="disabled">
						<c:forEach items="${requestScope.copingStrategy}" var="result">
							<option value="${result.id }"
								<c:if test="${entity.copingStrategy == result.id}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
					</select> 
				</span>
				
				<%-- <label class="w20"><span class="required"> * </span>应对策略:</label>
				<textarea class="w70" rows="5" cols="" id="copingStrategy" name="copingStrategy" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${entity.copingStrategy}</textarea>
				<br>
				
				<label class="w20"><span class="required"> * </span>责任单位:</label>
				<input class="w25" name="responsibleCompName" id=responsibleCompName check="NotEmpty_string_._._._._." value="${entity.responsibleCompName}"/>
				
				<label class="w20"><span class="required"> * </span>责任部门:</label>
				<input class="w25" name="responsibleDep" id="responsibleDep" check="NotEmpty_string_._._._._." value="${entity.responsibleDep}"/>
				
				
				<label class="w20"><span class="required"> * </span>责任人员:</label>
				<input class="w25" name="responsiblePerson" id="responsiblePerson" check="NotEmpty_string_._._._._." value="${entity.responsiblePerson }" readonly="true" onclick="mload('${pageContext.request.contextPath}/inspectproject/_selectpeople?eleId=responsiblePerson')"/>
				<input type="hidden" id="responsiblePersonId" name="responsiblePersonId">
				
			    <label class="w20"><span class="required"> * </span>应对完成时间:</label>
				<input class="w25" name="completeTime" id="completeTime"  value="${entity.completeTime}" readonly="true"/>
				<br>
				<label class="w20"><span class="required"> * </span>备注:</label>
				<textarea class="w70" rows="5" cols="" id="remark" name="remark" style="height:5em;" maxlength="500" readonly="readonly" >${entity.remark}</textarea>
				 --%>
				<label class="w20"><span class="required"> * </span>是否需要进一步处理数据:</label>
				<span class="w25">
					<select name="isImportant" id="isImportant" class="selectpicker" disabled="disabled">
							<option value="0">否</option>
							<option value="1">是</option>
					</select>
				</span>
				
				<label class="w20"><span class="required"> * </span>报告单位所在省份:</label>
				<span class="w25">
					<select name="provinceId" class="selectpicker" id="provinceId" disabled="disabled">
						<c:forEach items="${requestScope.province}" var="result">
							<option value="${result.num }"
								<c:if test="${entity.provinceId == result.num}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
					</select> 
				</span>
			</div>
			
			<h3 class="data_title1">关联数据列表&nbsp;&nbsp;
			</h3>
			<div class="tablebox">
				<table id="RelevanceTab">
					<tr class="table_header">
						<th >一级目录</th>
						<th >二级目录</th>
						<th >三级目录</th>
					</tr>
					<c:if test="${!empty requestScope.relevanceList }">
						<c:forEach items="${ requestScope.relevanceList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">
								<input type="hidden"  name="leveloneid" value="${l.leveloneid}" >
								<input type="hidden"  name="levelonename" value="${l.levelonename}" >
									${l.levelonename}
								</td>
								<td style="text-align: center;">
								<input type="hidden"  name="leveltwoid" value="${l.leveltwoid}" >
								<input type="hidden"  name="leveltwoname" value="${l.leveltwoname}" >
									${l.leveltwoname}
								</td>
								<td style="text-align: center;">
								<input type="hidden"  name="levelthreeid" value="${l.levelthreeid}" >
								<input type="hidden"  name="levelthreename" value="${l.levelthreename}" >
									${l.levelthreename}
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		
		<c:if test="${ !empty entityExamineviewlist  }">
			<h3 class="data_title1">审核意见</h3>
			<div class="model_part">
			<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
				<label class="w20">审核人:</label>
				<label class="w25 setleft">${ sys.createPersonName}</label> 
				<label class="w20">审核时间:</label>
				<label class="w25 setleft">${ sys.createDate}</label> 
				<label class="w20">审核结果:</label>
				<label class="w25 setleft">${ sys.examresultName}</label> 
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20" style="vertical-align:top;">审核意见:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${sys.examinestr}</label> 
			</c:forEach>
			</div>
			</c:if>
			
			<c:if test="${op=='examine' }">
				<div class="model_part">
					<label class="w20" style="vertical-align: top;"><span class="required"> * </span>审核意见:</label>
					<span class="w70 "><textarea  rows="5" cols="50" name="examStr" id="examStr"></textarea></span>
				</div>	
			</c:if>

			<div class="model_btn">
				<c:if test="${op=='examine' }">
					<button class="btn btn-primary model_btn_ok" id="commit-1" >通过</button>
					<button class="btn btn-primary model_btn_ok" id="commit-2" >退回关联目录</button>
					<button class="btn btn-primary model_btn_ok" id="commit-3" >退回审核风险事件</button>
				</c:if>
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
		function check()
		{
			if($("#examStr").val().length>=600)
			{
				win.errorAlert("审核意见不能输入超过600字");
				return false;
			}
			if($("#examStr").val()=="")
			{
				win.errorAlert("请输入审核意见");
				return false;
			}
			return true;
		}		
		$("#commit-1").click(function(){
			if(check())
			{
				$.post("${pageContext.request.contextPath}/bimr/riskEvent/commitexam",{id:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('保存成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
						{
							win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
							
						}
		 		 });
		 	}
			return false;
		})
		$("#commit-2").click(function(){
			if(check())
			{
				$.post("${pageContext.request.contextPath}/bimr/riskEvent/commitexam",{id:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
		    		var commitresult=JSON.parse(data);
							if(commitresult.result==true)
								win.successAlert('退回成功！',function(){
										parent.location.reload(true);
										parent.mclose();
								});
							else
							{
								win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
								
							}
		 		 });
		 	}
	 		 return false;
		
		})
		$("#commit-3").click(function(){
			if(check())
			{
				$.post("${pageContext.request.contextPath}/bimr/riskEvent/riskeventcommitexam",{id:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50117,type:"event"},function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('退回成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
						{
							win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
							
						}
		 		 });
		 	}
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
		


		
	</script>
</html>
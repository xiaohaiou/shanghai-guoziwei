<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>风险征兆事件反馈</title>
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
<body >
<c:choose>
		<c:when test="${op eq 'feedbackreport'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>风险征兆事件反馈上报
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		
		<c:when test="${op eq 'feedbackexamine'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>风险征兆事件反馈审核
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>风险事件查看
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
				<label class="w20"><span class="required"> * </span>报告单位:</label>
				<span class="w25"> 
					<input type="hidden" id="compId" name="compId" value="${entity.compId}" >
					<input name="compName" id="compName" value="${entity.compName}" readonly="readonly" class="w70" >
			    </span>
				
				<label class="w20"><span class="required"> * </span>风险征兆事件编号:</label>
				<input class="w25" name="eventNum" id="eventNum"  value="${entity.eventNum}" readonly="readonly"/>
				
				<label class="w20"><span class="required"> * </span>报告日期:</label>
				<input class="w25 time" name="reportTime" id="reportTime" value="${entity.reportTime}" readonly="true"/>
				
				<label class="w20"><span class="required"> * </span>风险征兆事件标题:</label>
				<input class="w25" name="eventTitle" id="eventTitle"  value="${entity.eventTitle}" readonly="readonly"/>
				<br>
				
				<label class="w20"><span class="required"> * </span>风险征兆事件具体描述:</label>
				<textarea class="w70" rows="5" cols="" id="eventDescribe" name="eventDescribe" style="height:5em;" maxlength="500"  readonly="readonly">${entity.eventDescribe}</textarea>
				
				<label class="w20"><span class="required"> * </span>风险征兆可能导致的风险:</label>
				<input class="w25" name="eventForRisk" id="eventForRisk"  value="${entity.eventForRisk}" readonly="readonly"/>
				
				
				
				<label class="w20"><span class="required"> * </span>应对策略:</label>
				<span class="w25">
					<select name="copingStrategy" class="selectpicker" id="copingStrategy" disabled="disabled">
						<c:forEach items="${requestScope.copingStrategy}" var="result">
							<option value="${result.id }"
								<c:if test="${entity.copingStrategy == result.id}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
					</select> 
				</span>
				<%--
			    <label class="w20"><span class="required"> * </span>应对完成时间:</label>
				<input class="w25" name="completeTime" id="completeTime"  value="${entity.completeTime}" readonly="true"/>
				<br>
				<label class="w20"><span class="required"> * </span>备注:</label>
				<textarea class="w70" rows="5" cols="" id="remark" name="remark" style="height:5em;" maxlength="500" readonly="readonly" >${entity.remark}</textarea>
				--%>
				<label class="w20"><span class="required"> * </span>是否需要提醒重要风险指标:</label>
				<span class="w25">
					<select name="isImportant" id="isImportant" class="selectpicker" disabled="disabled">
							<option value="0">否</option>
							<option value="1">是</option>
					</select>
				</span>
				
				<label class="w20"><span class="required"> * </span>风险征兆事件发生地点:</label>
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
			
			<label class="w20"><span class="required"> * </span>是否提请上级单位统筹:</label>
			<span class="w25">
				<select name="ishighunit" id="ishighunit" class="selectpicker"  disabled="disabled">
						<option value="0" <c:if test="${entity.ishighunit == 0}">selected="selected"</c:if>>否</option>
			 			<option value="1" <c:if test="${entity.ishighunit == 1}">selected="selected"</c:if>>是</option>
				</select>
			</span>
			<c:if test="${entity.ishighunit == 1}">
				<label class="w20"><span class="required"> * </span>处理方式:</label>
				<span class="w25">
					<select name="highunitmeasure" class="selectpicker" id="highunitmeasure" disabled="disabled">
						<c:forEach items="${requestScope.handleway}" var="result">
							<option value="${result.id }"
								<c:if test="${entity.highunitmeasure == result.id}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
					</select> 
				</span>
			</c:if>
			<c:if test="${entity.ishighunit == 0}">
				<label class="w20">拟采取的风险应对措施:</label>
				<input class="w25" name="planmeasure" id="planmeasure" value="${entity.planmeasure}" readonly="readonly"/>
				
				<label class="w20"><span class="required"> * </span>预计风险征兆事件关闭的时间:</label>
				<span class="w25">
					<select name="plancloesetime" class="selectpicker" id="plancloesetime" disabled="disabled">
						<option value="0" <c:if test="${entity.plancloesetime == 0}">selected="selected"</c:if>>半个月内</option>
						<option value="1" <c:if test="${entity.plancloesetime == 1}">selected="selected"</c:if>>一个月内</option>
						<option value="2" <c:if test="${entity.plancloesetime == 2}">selected="selected"</c:if>>季度内</option>
						<option value="3" <c:if test="${entity.plancloesetime == 3}">selected="selected"</c:if>>半年度内</option>
						<option value="4" <c:if test="${entity.plancloesetime == 4}">selected="selected"</c:if>>年度内</option>
						<option value="5" <c:if test="${entity.plancloesetime == 5}">selected="selected"</c:if>>不确定</option>
					</select> 
				</span>
			</c:if>
			<c:if test="${entity.copingStrategy != 82002}">
				<label class="w20">风险征兆事件跟踪人:</label>
				<input class="w25" name="trackpersonname" id="trackpersonname" value="${entity.trackpersonname}" readonly="readonly"/>
				<label class="w20"></label>
				<label class="w25"></label>
			</c:if>
			
			<c:if test="${entity.ishighunit == 1}">
				<label class="w20">风险体系内部跟踪人：</label>
				<input type="text" name="insidetrackpersonname" id="insidetrackpersonname" value="${entity.insidetrackpersonname}" class="w25" check="NotEmpty" readonly="true"/>
				<input type="hidden" name="insidetrackpersonid" id="insidetrackpersonid" value="${entity.insidetrackpersonid}"/>
			</c:if>
			

			
			<h3 class="data_title1">即时反馈&nbsp;&nbsp;<a href="javascript:void(0)"
					onclick="mload('${mapurl}/feedbackadd?id=${entity.id}')" class="form_btn">新增</a></h3>
				<div class="tablebox">
					<table id="personTab">
						<tr class="table_header">
							<th >反馈日期</th>
							<th >目前具体情况</th>
							<th >风险征兆事件现状</th>
							<c:if test="${entity.ishighunit == 0}">
							<th >风险应对措施目前落实情况</th>
							</c:if>
							<th >操作</th>
						</tr>
						<c:if test="${!empty requestScope.feedbackList }">
							<c:forEach items="${ requestScope.feedbackList}" var="l"
								varStatus="status">
								<tr>
									<td style="text-align: center;">${l.feedbacktime }</td>
									<td style="text-align: center;">
										<c:if test="${l.status == 0}">好转改观</c:if>
										<c:if test="${l.status == 1}">保持关注</c:if>
										<c:if test="${l.status == 2}">恶化蔓延</c:if>
									</td>
									<td style="text-align: center;">
										${l.nowdetailsituation }
									</td>
									<c:if test="${entity.ishighunit == 0}">
										<td style="text-align: center;">
											${l.measuresituation} 
										</td>
									</c:if>
									<td>
										<a href="javascript:void(0)"
											onclick="mload('${mapurl}/feedbackadd?id=${entity.id}&feedbackid=${l.id}')">修改</a>
										<a href="javascript:void(0)"
											onclick="del('${mapurl}/deletefeedback?feedbackid=${l.id }')">删除</a>		
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			
			
			<c:if test="${entity.ishighunit == 1}">
				<h3 class="data_title1">应对措施&nbsp;&nbsp;<a href="javascript:void(0)"
					onclick="mload('${mapurl}/feedbackadd2?id=${entity.id}')" class="form_btn">新增</a></h3>
				<div class="tablebox">
					<table id="personTab">
						<tr class="table_header">
							<th >反馈日期</th>
							<th >风险应对措施</th>
							<th >预计措施达成时间</th>
							<th >责任部门</th>
							<th >目前应对措施落实完成情况</th>
							<th >操作</th>
						</tr>
						<c:if test="${!empty requestScope.adoptstrategyFeedbackList }">
							<c:forEach items="${ requestScope.adoptstrategyFeedbackList}" var="l"
								varStatus="status">
								<tr>
									<td style="text-align: center;">${l.feedbacktime }</td>
									<td style="text-align: center;">
										${l.adoptStrategyname}
									</td>
									<td style="text-align: center;">${l.planfinishtime }</td>
									<td style="text-align: center;">${l.responsibleCompName }</td>
									<td style="text-align: center;">${l.nowfinishsituation }</td>
									<td>
										<a href="javascript:void(0)"
											onclick="mload('${mapurl}/feedbackadd2Edit?adoptstrategyid=${l.id}')">修改</a>
										<a href="javascript:void(0)"
											onclick="del('${mapurl}/deletefeedbacks?feedbackid=${l.id }')">删除</a>	
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</c:if>
						
			<%--
			<h3 class="data_title1">应对措施&nbsp;&nbsp;<a href="javascript:void(0)" onclick="addPerson()" class="form_btn">新增</a> </h3>
			<div class="tablebox">
				<table id="personTab">
					<tr class="table_header">
						<th >风险应对措施</th>
						<th >预计措施达成时间</th>
						<th >责任部门</th>
						<th >操作</th>
					</tr>
					<c:if test="${!empty requestScope.adoptStrategyList }">
						<c:forEach items="${ requestScope.adoptStrategyList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: left;">
									<select name="adoptStrategy"  class="selectpicker" class="w25" style="width:100% !important;" onchange="levelonechange(this)">
										<option value="1" <c:if test="${entity.adoptStrategy == 1}">selected="selected"</c:if>>专业公司风控部负责</option>
										<option value="2" <c:if test="${entity.adoptStrategy == 2}">selected="selected"</c:if>>核心企业风控部负责</option>
										<option value="3" <c:if test="${entity.adoptStrategy == 3}">selected="selected"</c:if>>产业集团风控部负责</option>
									</select>
									<input type="hidden"  name="adoptStrategyname" value="${l.adoptStrategyname }" >
								</td>
								<td style="text-align: left;"><input name="planfinishtime" value="${l.planfinishtime }" class="w25 time" style="width:100% !important; "></td>
								<td style="text-align: left;"><input name="responsibleCompName" value="${l.responsibleCompName }" class="w25" style="width:100% !important; "></td>
								<td><a href="javascript:void(0)" onclick="removePerson(this)">删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>	
			--%>
			
				<c:if test="${ !empty entityExamineviewlist  }">
					<h3 class="data_title1">审核意见</h3>
					<div class="model_part">
					<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
						<label class="w20">审核人:</label>
						<label class="w25 setleft">${ sys.createPersonName}</label> 
						<label class="w20">审核时间:</label>
						<label class="w25 setleft">${ sys.createDate}</label> 
						<label class="w20" style="vertical-align:top;">审核意见:</label>
						<label class="w75 setleft" style="word-wrap:break-word;">${sys.examinestr}</label> 
					</c:forEach>
					</div>
					</c:if>
			<c:if test="${op eq 'feedbackexamine'}">		
					<h3 class="data_title1">审核意见&nbsp;&nbsp;</h3>
						<div class="model_part">
							<label class="w20" style="vertical-align: top;"><span class="required"> * </span>审核意见:</label>
							<span class="w70 "><textarea  rows="5" cols="50" name="examStr" id="examStr"></textarea></span>
						</div>	
			</c:if>
			
			<div class="model_btn">
				<c:if test="${op eq 'feedbackreport'}">
					<button class="btn btn-primary model_btn_ok" id="commit-1" >申请办结</button>
				</c:if>
				<c:if test="${op eq 'feedbackexamine'}">
					<button class="btn btn-primary model_btn_ok" id="commit-2" >审核通过</button>
					<button class="btn btn-primary model_btn_ok" id="commit-3" >审核退回</button>
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
		function del(url){
			win.confirm('确定要删除？',function(r){
				if(r){
					$.post(url, function(data) {
						var commitresult1=JSON.parse(data);
						if(commitresult1.result){
							win.successAlert('删除成功！',function(){
								location.reload();
							});
						}else{
							win.errorAlert('操作失败！');
						}
					
					});
				}
			});
			return false;
		}
		

		$("#commit-1").click(function(){
			var id = $("#entityid").val();
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/riskEvent/updatestautsforfeedback?id="+id+"";
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
			     	win.successAlert('申请办结成功！',function(){
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
		//通过
		$("#commit-2").click(function(){
			if(check())
			{
				var id = $("#entityid").val();
				var examStr =$("#examStr").val();
				var type ="event";
				var examResult=50116;
				var formData = new FormData($("#form2")[0]);
				var url = "${pageContext.request.contextPath}/bimr/riskEvent/commitexamfeedback?id="+id+"&examStr="+examStr+"&type="+type+"&examResult="+examResult+"";
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
				     	win.successAlert('通过成功！',function(){
								parent.location.reload(true);
								parent.mclose();
						});
				     },  
				     error : function(data) {
				     	$.unblockUI();
				     }  
				}); 
		 	}
			return false;
		})
		//不通过
		$("#commit-3").click(function(){
			if(check())
			{
				var id = $("#entityid").val();
				var examStr =$("#examStr").val();
				var examResult=50117;
				var formData = new FormData($("#form2")[0]);
				var url = "${pageContext.request.contextPath}/bimr/riskEvent/commitexamfeedback?id="+id+"&examStr="+examStr+"&examResult="+examResult+"";
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
				     	win.successAlert('退回成功！',function(){
								parent.location.reload(true);
								parent.mclose();
						});
				     },  
				     error : function(data) {
				     	$.unblockUI();
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
		var count=0;
	/* 	function addPerson(){
			var id1 ="adoptStrategy"+count;
			var newPerson="<tr>"
							+"<td style=\"text-align: left;\">"
								+"<select name=\"adoptStrategy\" id=\""+id1+"\" class=\"selectpicker\" class=\"w25\" style=\"width:100% !important;\" onchange=\"levelonechange(this)\">"
									
								
								+"<input type=\"hidden\"  name=\"adoptStrategyname\" value=\"\" >"
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
			
		} */
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
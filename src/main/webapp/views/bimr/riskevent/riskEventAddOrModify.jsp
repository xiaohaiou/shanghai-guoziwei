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
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body style="">
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改风险事件
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:when test="${op eq 'report'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报风险事件
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:when test="${op eq 'trackreport'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>跟踪上报风险事件
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增风险事件
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	
	<form:form id="form2" modelAttribute="entity" method="post" >
	<form:hidden path="id"/>
		<div class="label_inpt_div">
			<h3 class="data_title1">报告单位</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>上报单位:</label>
				<span class="w25"> 
					<input type="hidden" id="compId" name="compId" value="${entity.compId}" >
					 <input name="compName" id="compName" value="${entity.compName}" readonly="readonly" class="w70" check="NotEmpty"> 
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:500px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
						</ul>
				   </div>
			    </span>
				
				<label class="w20"><span class="required"> * </span>风险征兆事件编号:</label>
				<input class="w25" name="eventNum" id="eventNum"  value="${entity.eventNum}" readonly="true"/>
				
				<label class="w20"><span class="required"> * </span>报告日期:</label>
				<input class="w25 time" name="reportTime" id="reportTime" check="NotEmpty_string_._._._._." value="${entity.reportTime}" readonly="true"/>
				
				<label class="w20"><span class="required"> * </span>风险征兆事件标题:</label>
				<input class="w25" name="eventTitle" id="eventTitle" check="NotEmpty_string_._._._._." value="${entity.eventTitle}"/>
				<br>
				
				<label class="w20"><span class="required"> * </span>风险征兆事件具体描述:</label>
				<textarea class="w70" rows="5" cols="" id="eventDescribe" name="eventDescribe" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${entity.eventDescribe}</textarea>
				
				<label class="w20"><span class="required"> * </span>事件可能导致的风险:</label>
				<input class="w25" name="eventForRisk" id="eventForRisk" check="NotEmpty_string_._._._._." value="${entity.eventForRisk}"/>
				
				
				
				<label class="w20"><span class="required"> * </span>应对策略:</label>
				<span class="w25">
					<select name="copingStrategy" class="selectpicker" id="copingStrategy" onchange="PersonShow(this.value)">
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
				<input type="hidden" id="responsiblePersonId" name="responsiblePersonId"> --%>
				<%-- 
			    <label class="w20"><span class="required"> * </span>应对完成时间:</label>
				<input class="w25 time" name="completeTime" id="completeTime" check="NotEmpty_string_._._._._." value="${entity.completeTime}" readonly="true"/>
				<br>
				<label class="w20"><span class="required"> * </span>备注:</label>
				<textarea class="w70" rows="5" cols="" id="remark" name="remark" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${entity.remark}</textarea>
				 --%>
				
				
				<label class="w20"><span class="required"> * </span>事件发生地点:</label>
				<span class="w25">
					<select name="provinceId" class="selectpicker" id="provinceId">
						<c:forEach items="${requestScope.province}" var="result">
							<option value="${result.num }"
								<c:if test="${entity.provinceId == result.num}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
					</select> 
				</span>
				<div id="trackpersonDiv" style="display:block">
				<label class="w20"><span class="required"> * </span>是否需要提醒重要风险征兆事件:</label>
				<span class="w25">
					<select name="isImportant" id="isImportant" class="selectpicker">
							<option value="0">否</option>
							<option value="1">是</option>
					</select>
				</span>
					<label class="w20"><span class="required"> * </span>是否提请上级单位统筹:</label>
					<span class="w25">
						<select name="ishighunit" id="ishighunit" class="selectpicker" onchange="DivShow(this.value)">
								<option value="0" <c:if test="${entity.ishighunit == 0}">selected="selected"</c:if>>否</option>
					 			<option value="1" <c:if test="${entity.ishighunit == 1}">selected="selected"</c:if>>是</option>
						</select>
					</span>
					<div id="ishighunitDiv" style="display:none">
						<label class="w20"><span class="required"> * </span>处理方式:</label>
						<span class="w25">
							<select name="highunitmeasure" class="selectpicker" id="highunitmeasure">
								<c:forEach items="${requestScope.handleway}" var="result">
									<option value="${result.id }"
										<c:if test="${entity.highunitmeasure == result.id}">selected="selected"</c:if>>
										${result.description}</option>
								</c:forEach>
							</select> 
						</span>
					</div>
					
					<div id="nothighunitDiv" style="display:block">
						<label class="w20"><span class="required"> * </span>拟采取的风险应对措施:</label>
						<input class="w25" name="planmeasure" id="planmeasure" value="${entity.planmeasure}" />
						
						<label class="w20"><span class="required"> * </span>预计风险征兆事件关闭的时间:</label>
						<span class="w25">
							<select name="plancloesetime" class="selectpicker" id="plancloesetime">
								<option value="0" <c:if test="${entity.plancloesetime == 0}">selected="selected"</c:if>>半个月内</option>
								<option value="1" <c:if test="${entity.plancloesetime == 1}">selected="selected"</c:if>>一个月内</option>
								<option value="2" <c:if test="${entity.plancloesetime == 2}">selected="selected"</c:if>>季度内</option>
								<option value="3" <c:if test="${entity.plancloesetime == 3}">selected="selected"</c:if>>半年度内</option>
								<option value="4" <c:if test="${entity.plancloesetime == 4}">selected="selected"</c:if>>年度内</option>
								<option value="5" <c:if test="${entity.plancloesetime == 5}">selected="selected"</c:if>>不确定</option>
							</select> 
						</span>
					</div>
					
					
						<label class="w20">风险征兆事件跟踪人：</label>
							<input type="text" name="trackpersonname" id="trackpersonname" value="${entity.trackpersonname}" class="w25" check="NotEmpty" readonly="true"/>
							<input type="hidden" name="trackpersonid" id="trackpersonid" value="${entity.trackpersonid}"/>
				</div>
				
			</div>
			
			
<%-- 			<h3 class="data_title1">采取策略&nbsp;&nbsp;<a href="javascript:void(0)" onclick="addPerson()" class="form_btn">新增</a> </h3>
			<div class="tablebox">
				<table id="personTab">
					<tr class="table_header">
						<th >采取策略</th>
						<th >责任部门</th>
						<th >操作</th>
					</tr>
					<c:if test="${!empty requestScope.adoptStrategyList }">
						<c:forEach items="${ requestScope.adoptStrategyList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: left;"><input name="adoptStrategy" value="${l.adoptStrategy }" class="w25" style="width:100% !important; "></td>
								<td style="text-align: left;"><input name="responsibleCompName" value="${l.responsibleCompName }" class="w25" style="width:100% !important; "></td>
								<td><a href="javascript:void(0)" onclick="removePerson(this)">删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div> --%>
			
			<div class="model_btn">
			<c:if test="${op eq 'modify'}">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
			</c:if>	
			<c:if test="${op eq 'add'}">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-4">事件保存并上报</button>
			</c:if>	
			<c:if test="${op eq 'report'}">
				<button class="btn btn-primary model_btn_ok" id="commit-2">事件上报</button>
			</c:if>	
			<c:if test="${op eq 'trackreport'}">
				<button class="btn btn-primary model_btn_ok" id="commit-3">事件跟踪上报</button>
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
		$(function(){
			debugger;
			if(${op != 'add' }){
				if('${entity.ishighunit}' =='0'){
					$("#ishighunitDiv").css('display','none');
		    		$("#nothighunitDiv").css('display','block');
				}else{
					$("#ishighunitDiv").css('display','block');
		    		$("#nothighunitDiv").css('display','none');
				}
				
				if('${entity.copingStrategy}' =='82002'){
					$("#trackpersonDiv").css('display','none');
				}else{
					$("#trackpersonDiv").css('display','block');
				}
			}

			if('${entity.isImportant}'!='')
				$("#isImportant").val('${entity.isImportant}');
			
		})
	
		$('input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
				
		function commit1(){
		
			
			
			if($("#copingStrategy").val() != "82002"){
				if($("#ishighunit").val() == 0){
				$("#planmeasure").attr("check","NotEmpty_string_._._._._.");
			    }
				if($("#ishighunit").val() == 1){
				$("#planmeasure").removeAttr('check');
				}
				 $("#trackpersonname").attr("check","NotEmpty_string_._._._._.");
			}else{
			$("#planmeasure").removeAttr("check");
				$("#trackpersonname").removeAttr("check");
			}
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
		
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/riskEvent/saveOrUpdate?type=0";
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
		function DivShow(value) {
			if (value == "0") {
				$("#ishighunitDiv").css('display','none');
	    		$("#nothighunitDiv").css('display','block');
			}else{
				$("#ishighunitDiv").css('display','block');
	    		$("#nothighunitDiv").css('display','none');
			}
		}
		function PersonShow(value) {
			if (value != "82002") {
	    		$("#trackpersonDiv").css('display','block');
			}else{//应对策略选择接受
	    		$("#trackpersonDiv").css('display','none');
			}
		}
		
		$('#trackpersonname').on('focus click',function(){
			mload('${pageContext.request.contextPath}/bimr/riskEvent/searchPerson?inputname=trackpersonname&inputid=trackpersonid');
		});
		
		function commit4(){
		if($("#copingStrategy").val() != "82002"){
				if($("#ishighunit").val() == 0){
				$("#planmeasure").attr("check","NotEmpty_string_._._._._.");
			    }
				if($("#ishighunit").val() == 1){
				$("#planmeasure").removeAttr('check');
				}
				 $("#trackpersonname").attr("check","NotEmpty_string_._._._._.");
			}else{
			$("#planmeasure").removeAttr("check");
				$("#trackpersonname").removeAttr("check");
			}
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
		
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/riskEvent/saveOrUpdate?type=1";
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
		//事件 上报
		function commit2(){
		if($("#copingStrategy").val() != "82002"){
				if($("#ishighunit").val() == 0){
				$("#planmeasure").attr("check","NotEmpty_string_._._._._.");
			    }
				if($("#ishighunit").val() == 1){
				$("#planmeasure").removeAttr('check');
				}
				 $("#trackpersonname").attr("check","NotEmpty_string_._._._._.");
			}else{
			$("#planmeasure").removeAttr("check");
				$("#trackpersonname").removeAttr("check");
			}
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/riskEvent/updatestauts?stauts=82101";
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
					 win.successAlert('事件上报成功！',function(){
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
		//事件 上报
		function commit3(){
		if($("#copingStrategy").val() != "82002"){
				if($("#ishighunit").val() == 0){
				$("#planmeasure").attr("check","NotEmpty_string_._._._._.");
			    }
				if($("#ishighunit").val() == 1){
				$("#planmeasure").removeAttr('check');
				}
				 $("#trackpersonname").attr("check","NotEmpty_string_._._._._.");
			}else{
			$("#planmeasure").removeAttr("check");
				$("#trackpersonname").removeAttr("check");
			}
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/riskEvent/updatestauts?stauts=82104";
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
					 win.successAlert('事件上报成功！',function(){
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
		$("#commit-2").click(commit2);
		$("#commit-3").click(commit3);
		$("#commit-4").click(commit4);
		
		
		
		
		
		var timeoutId;
		$('#compName').on('focus click',function(){
			$(this).next('.com_ztree_box').css('display','block')
		}).parent().on('mouseenter',function(){
			clearTimeout(timeoutId)
		}).on('mouseleave',function(){
			clearTimeout(timeoutId)
			timeoutId = setTimeout(function(el){
				$(el).find('.com_ztree_box').css('display','none')
			},3e2,this);
		});
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
		$(function () {
		     //所有企业数据	
		    var com_data = ${allCompanyTree};
			zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
			//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
			var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = treeObj.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
			var childrenNodes = treeObj.transformToArray(nodes);
			if(childrenNodes[0]!=null){
				treeObj.expandNode(childrenNodes[0],true);
			}
		});
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					$("#compId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
					$("#compName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		

		
	</script>
</html>
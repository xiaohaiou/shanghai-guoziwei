<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>数据看报查看</title>
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
		<c:when test="${op eq 'relevance'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>数据看报目录
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>数据看报查看
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
			
			<h3 class="data_title1">关联数据列表(若修改请删除后新增)&nbsp;&nbsp;
				<c:if test="${op eq 'relevance'}">
				<a href="javascript:void(0)" onclick="addRelevance()" class="form_btn">新增</a> 
				</c:if>
			</h3>
			<div class="tablebox">
				<table id="RelevanceTab">
					<tr class="table_header">
						<th >一级目录</th>
						<th >二级目录</th>
						<th >三级目录</th>
						<th >操作</th>
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
								<td>
									<c:if test="${op eq 'relevance'}">
									<a href="javascript:void(0)" onclick="removeRelevance(this)">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			
			<div class="model_btn">
				<c:if test="${op eq 'relevance'}">
					<button class="btn btn-primary model_btn_ok" id="commit-1" >上报</button>
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
				
		function commit1(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
		
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/riskEvent/relevancesaveOrUpdate";
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
					 win.successAlert('上报成功！',function(){
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
			if('${entity.isImportant}'!='')
				$("#isImportant").val('${entity.isImportant}');
			
		})
		


		function levelonechange(o){
			var v = $(o).val();
			setLevel2(v,o.id,false);
			var text =$(o).find("option:selected").text()
			$(o).next().val(text);
		}
		function leveltwochange(o){
			var v = $(o).val();
			setLevel3(v,o);
			
		}
		
		function levelthreechange(o){
			var text =$(o).find("option:selected").text()
			$(o).next().val(text);
		}

		
		
		function setLevel2(id,idstr,isfirsttime){
		
		 var idnum = idstr.substring(10);
		 var selectid = "leveltwoid"+idnum;
			$.get('${mapurl}/getChildren', {'parentId': id}, function(data){
				$("#"+selectid+" option").remove();
				$("#"+selectid+"").append("<option value='-1'>请选择</option>");
				$.each(data.entity, function(i, o){
					$("#"+selectid+"").append("<option value='"+o.id+"'>"+o.name+"</option>");
				});
			});
			if(isfirsttime == true){
				$("#"+idstr+"").next().val("市场风险");
			}	
		}
		
		function setLevel3(id,o){
		var text =$(o).find("option:selected").text();
		$(o).next().val(text);
		var idstr = o.id;
		 var idnum = idstr.substring(10);
		 var selectid = "levelthreeid"+idnum;
			$.get('${mapurl}/getChildren', {'parentId': id}, function(data){
				$("#"+selectid+" option").remove();
				$("#"+selectid+"").append("<option value='-1'>请选择</option>");
				$.each(data.entity, function(i, o){
					$("#"+selectid+"").append("<option value='"+o.id+"'>"+o.name+"</option>");
				});
			});
		}

		var count=0;
		function addRelevance(){
		var id1 ="leveloneid"+count;
		var id2 ="leveltwoid"+count;
		var id3 ="levelthreeid"+count;
		var newRelevance="<tr>"
								+"<td style=\"text-align: left;\">"
									+"<select name=\"leveloneid\" id=\""+id1+"\" class=\"selectpicker\" class=\"w25\" style=\"width:100% !important;\" onchange=\"levelonechange(this)\">"
										+"<option value=\"2\">市场风险</option>"
										+"<option value=\"3\">战略风险</option>"
										+"<option value=\"4\">法律风险</option>"
										+"<option value=\"5\">财务风险</option>"
										+"<option value=\"6\">运营风险</option>"
									+"</select> "
									+"<input type=\"hidden\"  name=\"levelonename\" value=\"\" >"
								+"</td>"
								+"<td style=\"text-align: left;\">"
									+"<select name=\"leveltwoid\" id=\""+id2+"\" class=\"selectpicker\" class=\"w25\" style=\"width:100% !important;\" onchange=\"leveltwochange(this)\" >"
										+"<option value=\"-1\">请选择</option>"
									+"</select> "
									+"<input type=\"hidden\"  name=\"leveltwoname\" value=\"\" >"
								+"</td>"
								+"<td style=\"text-align: left;\">"
									
									+"<select name=\"levelthreeid\" id=\""+id3+"\" class=\"selectpicker\" class=\"w25\" style=\"width:100% !important;\" onchange=\"levelthreechange(this)\">"
										+"<option value=\"-1\">请选择</option>"
									+"</select> "
									+"<input type=\"hidden\"  name=\"levelthreename\" value=\"\" >"
								+"</td>"
								+"<td><a href=\"javascript:void(0)\" onclick=\"removeRelevance(this)\">删除</a></td></tr>";
			$("#RelevanceTab").append(newRelevance);
			count++;
			//首次加载二级目录
			setLevel2(2,id1,true);
		}
		
		function removeRelevance(o){
			$(o).parent().parent().remove();
		}
		
	</script>
</html>
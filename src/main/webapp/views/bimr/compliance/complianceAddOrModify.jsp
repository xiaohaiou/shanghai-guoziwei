<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>合规调查</title>
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
</head>
<body style="">
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改合规调查
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增合规调查
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	
	<form:form id="form2" modelAttribute="entity" method="post" >
	<form:hidden path="id"/>
		<div class="label_inpt_div">
			<h3 class="data_title1">合规调查基本信息</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>举报投诉、专项调查项目名称:</label>
				<input class="w25" name="projectName" id="projectName" check="NotEmpty_string_._._._._." value="${entity.projectName}"/>
				
				<label class="w20">举报投诉、专项调查项目编号:</label>
				<input class="w25" name="projectCode" id="projectCode"  value="${entity.projectCode}" readonly="readonly"/>
				
			
				<label class="w20"><span class="required"> * </span>调查类型:</label>
				<span class="w25">
					<select name="investigationType" id="investigationType" class="selectpicker">
						<c:forEach items="${requestScope.investigationType}" var="result">
							<option value="${result.id }"
								<c:if test="${entity.investigationType == result.id}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
					</select> 
				</span>
					<br>
				<label class="w20"><span class="required"> * </span>调查承办企业:</label>
				<span> 
						<input type="hidden" id="investigationId" name="investigationId" value="${entity.investigationId}" >
						<input name="investigationName" id="investigationName" value="${entity.investigationName}" readonly="readonly" class="w25" check="NotEmpty">
						<div class="com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:500px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
							<ul id="com_ztree" class="ztree">
							</ul>
					   </div>
				</span>
				<label class="w20"><span class="required"> * </span>调查承办部门:</label>
				<input class="w25" name="investigationDep" id="investigationDep" check="NotEmpty_string_._._._._." value="${entity.investigationDep}"/>
				<br/>
				<label class="w20"><span class="required"> * </span>调查涉及企业名称:</label>
				<span class="w25"> 
					<input type="hidden" id="compId" name="compId" value="${entity.compId}" >
					<input name="compName" id="compName" value="${entity.compName}" readonly="readonly" class="w70" check="NotEmpty">
					<div class="com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:500px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree1" class="ztree">
						</ul>
				   </div>
			    </span>
			    <br/>
				<label class="w20"><span class="required"> * </span>调查涉及经营管理事项:</label>
				<input class="w25" name="itemName" id="itemName" check="NotEmpty_string_._._._._." value="${entity.itemName}"/>
				
				<label class="w20"><span class="required"> * </span>调查来源:</label>
				<span class="w25">
					<select name="investigationFrom" id="investigationFrom" class="selectpicker">
						<c:forEach items="${requestScope.investigationFrom}" var="result">
							<option value="${result.id }"
								<c:if test="${entity.investigationFrom == result.id}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
					</select> 
				</span>
				
				<div id="forInvestigationType">
				<label class="w20"><span class="required"> * </span>是否是集团内部投诉人:</label>
				<span class="w25"> 
					<select name="isInside" id="isInside" class="selectpicker">
							<option value="0">否</option>
							<option value="1">是</option>
					</select>
			    </span>
			    
			    <label class="w20"><span class="required"> * </span>是否匿名:</label>
				<span class="w25"> 
					<select name="isAnonymous" id="isAnonymous" class="selectpicker">
							<option value="0">否</option>
							<option value="1">是</option>
					</select>
			    </span>
			    </div>
			    <label class="w20"><span class="required"> * </span>举报投诉收到时间:</label>
				<input class="w25 time" name="accusationTime" id="accusationTime" check="NotEmpty_string_._._._._." value="${entity.accusationTime}" readonly="true"/>
				
				<label class="w20"><span class="required"> * </span>调查交办领导:</label>
				<input class="w25" name="assignLeader" id="assignLeader" check="NotEmpty_string_._._._._." value="${entity.assignLeader }" readonly="true" onclick="mload('${pageContext.request.contextPath}/inspectproject/_selectpeople?eleId=assignLeader')"/>
				<input type="hidden" id="assignLeaderId" name="assignLeaderId">
				<label class="w20"><span class="required"> * </span>调查负责人:</label>
				<input class="w25" name="responsiblePerson" id="responsiblePerson" check="NotEmpty_string_._._._._." value="${entity.responsiblePerson }" readonly="true" onclick="mload('${pageContext.request.contextPath}/inspectproject/_selectpeople?eleId=responsiblePerson')"/>
				<input type="hidden" id="responsiblePersonId" name="responsiblePersonId">
				<br>
				<label class="w20">投诉举报信:</label>
					<input class="w25" type="file" id="indictmentFile" name="indictmentFile"/>
					<input class="w25" type="hidden" id="indictment" name="indictment" value="${entity.indictment}"/>
					<c:if test="${not empty entity.indictment}">
					<c:if test="${not empty indictmentFile}">
						<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(indictmentFile.filePath,"\\","\\\\")}&_name=${indictmentFile.fileName}')" target="_blank">${indictmentFile.fileName}</a>
					</c:if>
					</c:if> 
				<br/>
				<label class="w20"><span class="required"> * </span>待调查事项:</label>
				<input type="text" name="investigationMatters" id="investigationMatters" value="${entity.investigationMatters}" class="w25" check="NotEmpty_string_._._._._."/>
				<br/>
							
				
				
				
			</div>
			<h3 class="data_title1">调查涉及人员&nbsp;&nbsp;<a href="javascript:void(0)" onclick="addPerson()" class="form_btn">新增</a> </h3>
			<div class="tablebox">
				<table id="personTab">
					<tr class="table_header">
						<th >调查涉及人员</th>
						<th >现任职务</th>
						<th >员工编号</th>
						<th >操作</th>
					</tr>
					<c:if test="${!empty requestScope.personList }">
						<c:forEach items="${ requestScope.personList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;"><input name="employeeName" value="${l.employeeName }" class="w25" style="width:100% !important; "></td>
								<td style="text-align: left;"><input name="employeePostion" value="${l.employeePostion }" class="w25" style="width:100% !important; "></td>
								<td style="text-align: left;"><input name="employeeNumber" value="${l.employeeNumber }" class="w25" style="width:100% !important; "></td>
								<td><a href="javascript:void(0)" onclick="removePerson(this)">删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div class="model_btn">
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
<script type="text/javascript" src="<c:url value="/js/jquery.ztree.all.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>

<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		$('input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
		var timeoutId;
		$('#investigationName').on('focus click',function(){
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
					$("#investigationId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
					$("#investigationName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});		
		function commit1(){
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
		
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/compliance/saveOrUpdate";
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
		
		$("#com_ztree1").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree1").getSelectedNodes()[0]) {
					$("#compId").val($.fn.zTree.getZTreeObj("com_ztree1").getSelectedNodes()[0].id);
					$("#compName").val($.fn.zTree.getZTreeObj("com_ztree1").getSelectedNodes()[0].name);
				}
		   	});
		});
		
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
		$(function () {
		     //所有企业数据	
		    var com_data = ${allCompanyTree};
			zTreeObj = $.fn.zTree.init($("#com_ztree1"), com_ztree_setting, com_data);
			//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
			var treeObj = $.fn.zTree.getZTreeObj("com_ztree1");
			var nodes = treeObj.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
			var childrenNodes = treeObj.transformToArray(nodes);
			if(childrenNodes[0]!=null){
				treeObj.expandNode(childrenNodes[0],true);
			}
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
		$(function(){
			if('${entity.isInside}'!='')
				$("#isInside").val('${entity.isInside}');
			if('${entity.isAnonymous}'!='')	
				$("#isAnonymous").val('${entity.isAnonymous}');
				
			$("#investigationType").change(function(){
				var value=$(this).val();
				if(value==81001){
					$("#forInvestigationType").hide();
				}else{
					$("#forInvestigationType").show();
				}
			});
			$("#investigationType").change();
		})
		
		function addPerson(){
			debugger;
			var newPerson="<tr><td style=\"text-align: center;\"><input name=\"employeeName\" class=\"w25\" style=\"width:100% !important;\"></td>"
						  +"<td style=\"text-align: left;\"><input name=\"employeePostion\" class=\"w25\" style=\"width:100% !important;\"></td>"
						  +"<td style=\"text-align: left;\"><input name=\"employeeNumber\" class=\"w25\" style=\"width:100% !important;\"></td>"
						  +"<td><a href=\"javascript:void(0)\" onclick=\"removePerson(this)\">删除</a></td></tr>";
			$("#personTab").append(newPerson);			
		}
		
		function removePerson(o){
			$(o).parent().parent().remove();
		}
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
	</script>
</html>
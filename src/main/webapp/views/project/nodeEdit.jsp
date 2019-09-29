<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>采购数据新增、编辑页面</title>
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
		<c:if test="${ not empty node.id }">
			<span class="glyphicon glyphicon-pencil"></span>修改项目节点
		</c:if>
		<c:if test="${empty node.id }">
			<span class="glyphicon glyphicon-pencil"></span>新增项目节点
		</c:if>
		<i class="iconfont icon-guanbi"></i>
	</h4>
	
		<form id="form2">
			<div class="label_inpt_div">
				<label class="w20"><span class="required"></span>项目名称:</label>
				<c:if test="${ not empty project}">
					<input class="w25" type="text" name="pjName" value="${project.pjName }" readonly="readonly"/>
				</c:if>
				<c:if test="${empty project}">
					<input class="w25" type="text" id="pjName" name="pjName" value="" readonly="readonly"/>
				</c:if>
				<label class="w20"><span class="required"> * </span>节点名称:</label>
				<input class="w25" type="text" name="pnName" value="${node.pnName }" check="NotEmpty_string_50_._._._." />
				<label class="w20"><span class="required"> * </span>计划完成日期:</label>
				<input class="w25 time" type="text" name="pnCompletionTime" value="${node.pnCompletionTime }"  readonly="readonly"/>
				<label class="w20">实际完成日期:</label>
				<input class="w25 time" type="text" name="pnFinishTime" value="${node.pnFinishTime }"  readonly="readonly"/>
				<label class="w20"><span class="required"> * </span>节点进度(%):</label>
				<input class="w25" type="text" name="pnProgress" value="<fmt:formatNumber  value="${node.pnProgress }" pattern="##0"/>" check="NotEmpty_int_11_._0+_0_100" />	
				<label class="w20"><span class="required"> * </span>节点显示顺序:</label>
				<input class="w25" type="text" name="pnOrder" value="${node.pnOrder }" check="NotEmpty_int_11_._0+_._." />
				
				<label class="w20"><span class="required"> * </span>节点状态:</label>
				<select name="pnStatus" class="w25">
					<option value="0" <c:if test="${node.pnStatus == 0 }"> selected = "selected"</c:if>>未开始</option>
					<option value="1" <c:if test="${node.pnStatus == 1 }"> selected = "selected"</c:if>>节点进行中 </option>
					<option value="2" <c:if test="${node.pnStatus == 2 }"> selected = "selected"</c:if>>节点完成</option>
				</select>
				<label class="w20"><span class="required"> * </span>是否要求:</label>
				<select name="pnIsrequired" class="w25">
					<option value="0" <c:if test="${node.pnIsrequired == 0 }"> selected = "selected"</c:if>>否</option>
					<option value="1" <c:if test="${node.pnIsrequired == 1 }"> selected = "selected"</c:if>>是 </option>
				</select>
				<div class="model_btn">
					<input type="hidden" name="id" value="${node.id }" />
					<input type="hidden" name="pjId" value="${project.id }" />
					<c:if test="${not empty project}">
						<button class="btn btn-primary model_btn_ok" id="commit-2">保存</button>
					</c:if>
					<c:if test="${empty project}">
						<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
					</c:if>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
			
		<form>
	<jsp:include page="../system/modal.jsp"/>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/vaild.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
		//初始化项目名称
		$("#pjName").val($("#ppjName", window.parent.document).val());
		
		$(' input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		function checkcommit()
		{
			var form = document.getElementById("form2")
			if(isEmpty(form.pnName.value)){
				form.pnName.focus();
				win.generalAlert("节点名称不能为空！");
				return false;
			}
			if(isEmpty(form.pnCompletionTime.value)){
				form.pnCompletionTime.focus();
				win.generalAlert("计划完成日期不能为空！");
				return false;
			}
			if(isEmpty(form.pnProgress.value)){
				form.pnProgress.focus();
				win.generalAlert("节点进度不能为空！");
				return false;
			}
			if(isEmpty(form.pnOrder.value)){
				form.pnOrder.focus();
				win.generalAlert("节点显示顺序不能为空！");
				return false;
			}
			return true;
		}
		
		
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/project/node/_saveNode";
			
			var formData = new FormData($("#form2")[0]);
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
					 		parent.initNodeList('node',1);
							parent.mclose();
					 });
			     },  
			     error : function(data) {
			     	console.log(data)
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		
		});
		
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/project/node/_saveNodeTemp";
			
			var formData = new FormData($("#form2")[0]);
			
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
			     	var nodeTemp = eval("("+data+")");
			     	var nodeId = '${node.id}';
			     	if(nodeId != '')
			     		modifyNodeTableTr(nodeTemp);//编辑在父页面nodeTable中的tr
			     	else
			     		createNodeTableTr(nodeTemp);// 在父页面创建nodeTable中的tr
					 win.successAlert('保存成功！',function(){
							//parent.location.reload(true);
							parent.mclose();
					 });
			     },  
			     error : function(data) {
			     	console.log(data)
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		})
		
	// 在父页面创建nodeTable中的tr
	function createNodeTableTr(nodeTemp) {
		var reportStatus = nodeTemp.reportStatus;
		var status = '';
		if(reportStatus == 0){
			status = "<span style=\"color:#3366ff\">未上报</span>";
		}else if(reportStatus == 1){
			status = "<span style=\"color:#ff9933\">待审核</span>";
		}else if(reportStatus == 2){
			status = "<span style=\"color:#00cc66\">已审核</span>";
		}else if(reportStatus == 3){
			status = "<span style=\"color:#ff399d\">已退回</span>";
		}
		var b1 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"mload('${pageContext.request.contextPath}/project/node/_modifyNodeTemp?id="+nodeTemp.id+"')\">编辑</a>";
		var b2 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"delTemp('"+nodeTemp.id+"','node')\">删除</a>";
		var h1 = "<input type=\"hidden\" name=\"nodeIds\" value=\""+nodeTemp.id+"\"/>";
		var e1 = $("<tr id=\"node"+nodeTemp.id+"\"><td>新</td><td>" + nodeTemp.pnName + "</td><td>"
				+ nodeTemp.pnCompletionTime + "</td><td>" + nodeTemp.pnProgress
				+ "%</td><td>" + status + "</td><td>" + b1
				+ b2 + h1 + "</td></tr>");
		$("#nodeTableTr", window.parent.document).after(e1);
	}
	
	//编辑在父页面nodeTable中的tr
	function modifyNodeTableTr(nodeTemp){
		var reportStatus = nodeTemp.reportStatus;
		var status = '';
		if(reportStatus == 0){
			status = "<span style=\"color:#3366ff\">未上报</span>";
		}else if(reportStatus == 1){
			status = "<span style=\"color:#ff9933\">待审核</span>";
		}else if(reportStatus == 2){
			status = "<span style=\"color:#00cc66\">已审核</span>";
		}else if(reportStatus == 3){
			status = "<span style=\"color:#ff399d\">已退回</span>";
		}
		var e1 = $("#node"+nodeTemp.id, window.parent.document).empty();//清除tr中的td
		var b1 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"mload('${pageContext.request.contextPath}/project/node/_modifyNodeTemp?id="+nodeTemp.id+"')\">编辑</a>";
		var b2 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"delTemp('"+nodeTemp.id+"','node')\">删除</a>";
		var h1 = "<input type=\"hidden\" name=\"nodeIds\" value=\""+nodeTemp.id+"\"/>";
		var td1 = $("<td>新</td>");
		var td2 = $("<td>" + nodeTemp.pnName + "</td>");
		var td3 = $("<td>"+ nodeTemp.pnCompletionTime + "</td>");
		var td4 = $("<td>" + nodeTemp.pnProgress + "%</td>");
		var td5 = $("<td>" + status + "</td>");
		var td6 = $("<td>" + b1 + b2 + h1 + "</td>");
		e1.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6);
	}
</script>
</html>
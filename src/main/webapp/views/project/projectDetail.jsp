<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>重点基建工程编辑页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<style>
	table td{
		line-height:2em;
	}
</style>
</head>
<body>
	
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>查看重点基建工程详细
		<i class="iconfont icon-guanbi"></i>
	</h4>
		<form id="form2">
			<div class="label_inpt_div">
				<h3 class="data_title1">项目基本信息</h3>
				<div class="model_part">
					<label class="w20">项目名称:</label>
					<label class="w25 setleft">${project.pjName}</label>
					<label class="w20">项目所在单位:</label>
					<label class="w25 setleft">${project.pjDept}</label>
					<label class="w20">项目整体进度(%):</label>
					<label class="w25 setleft"><fmt:formatNumber  value="${project.pjProgress}" pattern="#,##0"/></label>
					<label class="w20">项目合同付整体款进度(%):</label>
					<label class="w25 setleft"><fmt:formatNumber  value="${project.totalContractProgress}" pattern="#,##0"/></label>
					<br/>
					<label class="w20">描述:</label>
					<label class="w70 setleft" style="word-wrap:break-word;">${project.pjDiscription}</label>
					<br/>
					<label class="w20">效果图:</label>
					<img src="${pageContext.request.contextPath}${project.imgPath}" style="height:100px;width:100px;">
					<br>
					<label class="w20">离线视频:</label>
					<span><a href="javascript:downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(project.video.pjVideoPath,'\\','\\\\')}&_name=${project.video.pjVideoName}')">${project.video.pjVideoName}</a></span>
					<br>
					<!-- <label class="w25 setleft">${project.projectImgFile.fileName}</label>-->
					<label class="w20">创建人:</label>
					<label class="w25 setleft">${project.createPersonName}</label>
					<label class="w20">创建时间:</label>
					<label class="w25 setleft">${project.createDate}</label>
					<c:if test="${project.reportStatus == 1 && (not empty project.reportPersonName) && (empty project.approveId)}">
						<label class="w20">上报人:</label>
						<label class="w25 setleft">${project.reportPersonName}</label>
						<label class="w20">上报时间:</label>
						<label class="w25 setleft">${project.reportTime}</label>
					</c:if>
				</div>
				<h3 class="data_title1">节点信息列表</h3>
				<div class="model_part" id="nodeList">
				
				</div>
				<h3 class="data_title1">周报信息列表</h3>
				<div class="model_part" id="wkReportList">
					
				</div>
				<h3 class="data_title1">合同信息列表</h3>
				<div class="model_part" id="contractList">
					
				</div>
				<h3 class="data_title1">上报审核历史信息</h3>
				<div class="model_part" id="history">
					<div class="tablebox">
						<table >
							<tr class="table_header" id="historyTr">
								<th style="width:5%">序号</th>
								<th style="width:10%">上报人</th>
								<th style="width:15%">上报时间</th>
								<th style="width:10%">审核人</th>
								<th style="width:15%">审核时间</th>
								<th style="width:10%">审核结果</th>	
								<th style="width:35%">审核备注</th>
							</tr>
							<c:if test="${not empty histories }">
								<c:forEach items="${histories }" var="history" varStatus="status">
									<tr>
										<td >${status.count}</td>
										<td style="text-align:left;">${history.reportPersonName}</td>
										<td>${history.reportTime}</td>
										<td style="text-align:left;">${history.approve.createPersonName}</td>
										<td>${history.approve.createDate}</td>
										<td>
											${history.approve.approveResult == '1'?'通过':'退回' }
										</td>
										<td style="word-wrap:break-word;text-align:left;">${history.approve.remark}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty histories }">
								<tr>
									<td colspan="7" align="center">
										查询无记录
									</td>
								</tr>
							</c:if>	
						</table>
					</div>
				</div>
				<div class="model_btn">
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
		<form>
	</div>
	<jsp:include page="../system/modal.jsp"/>
	<script type="text/javascript">
		function initNodeList(v,pageNums){
			$.ajax({
				url:"${pageContext.request.contextPath}/project/"+v+"/_"+v+"ViewList?pageNums="+pageNums+"&pjId=${project.id}&view=view",
				type:"POST",
				dataType:"text",
				async:false,
				success:function(data){
					$("#"+v+"List").children().remove();
					$("#"+v+"List").append(data);
				}
			});	
		}
		initNodeList('node',1);//初始化节点列表
		initNodeList('wkReport',1);//初始化周报列表
		initNodeList('contract',1);//初始化合同列表
	</script>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		//node
		function viewNodeValidate(id){
			var url = "${pageContext.request.contextPath}/project/node/_validate?id=" + id;
			$.post(url, function(data) {
				var node = eval("(" + data +")");
				if(node.isdel == 1){
					win.errorAlert("该数据已被删除",function(){
						initNodeList('node',1);//初始化节点列表
					});
				}else{
					mload('${pageContext.request.contextPath}/project/node/_nodeView?id='+id);
				}
			});
		}
		//weekReport
		function viewWrValidate(id){
			var url = "${pageContext.request.contextPath}/project/wkReport/_validate?id=" + id;
			$.post(url, function(data) {
				var wkReport = eval("(" + data +")");
				if(wkReport.isdel == 1){
					win.errorAlert("该数据已被删除",function(){
						initNodeList('wkReport',1);//初始化周报列表
					});
				}else{
					mload('${pageContext.request.contextPath}/project/wkReport/_WkReportView?id=' + id);
				}
			});
		}
		
		//contract
		function viewContractValidate(id){
			var url = "${pageContext.request.contextPath}/project/contract/_validate?id=" + id;
			$.post(url, function(data) {
				var contract = eval("(" + data +")");
				if(contract.isdel == 1){
					win.errorAlert("该数据已被删除",function(){
						initNodeList('contract',1);//初始化合同列表
					});
				}else{
					mload('${pageContext.request.contextPath}/project/contract/_contractView?id=' + id);
				}
			});
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
			     	alert("下载失败！");
			     }  
			});
		}	            
</script>
</html>
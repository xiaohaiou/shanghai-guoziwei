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
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<style>
	table{
		table-layout: fixed;
	}
	table td{
		line-height:2em;
		word-wrap:break-word;
	}
	.label_inpt_div{
		min-height:100px;
	}
</style>
</head>
<body>
	
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>审核在建重点基建工程项目<i class="iconfont icon-guanbi"></i>
	</h4>
	
		<form id="projectForm">
			<div class="label_inpt_div">
				<h3 class="data_title1">项目进度调整</h3>
				<div class="model_part">
					<div class="tablebox">
						<table>
							<tr>
								<th>项目名称</th>
								
								<th>整体进度<br>（调整前）</th>
								<th>整体进度<br>（调整后）</th>
								<th>合同付款整体进度<br>（调整前）</th>
								<th>合同付款整体进度<br>（调整后）</th>
							</tr>
							<c:if test="${not empty  projectChanges}">
								<c:forEach items="${projectChanges}" var="projectChange">
									<tr>
										<td style="text-align:left;">${projectChange.pjName }</td>
										
										<td style="text-align:right;">${projectChange.oldPjProgress }<c:if test="${projectChange.oldPjProgress!=null  }">%</c:if></td>
										<td style="text-align:right;">${projectChange.newPjProgress }<c:if test="${projectChange.newPjProgress!=null  }">%</c:if></td>
										<td style="text-align:right;">${projectChange.oldContractProgress }<c:if test="${projectChange.oldContractProgress!=null  }">%</c:if></td>
										<td style="text-align:right;">${projectChange.newContractProgress }<c:if test="${projectChange.newContractProgress!=null  }">%</c:if></td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</div>
					<c:if test="${not empty  projectChanges}">
						<div id="project_div">
							<label class="w20"><span class="required"> * </span>审核意见:</label>
							<textarea class="w70" name="remark" check="NotEmpty_string_1000_._._._." ></textarea>
							<br>
							<div class="model_btn">
								<input type="hidden" name="pjId" value="${project.id}"/>
								<input type="hidden" name="type" value="0"/>
								<input type="hidden" name="approveResult">
								<a class="btn btn-primary model_btn_ok" onclick="approve('project',1)">通过</a>
								<a class="btn btn-primary model_btn_ok" onclick="approve('project',0)">退回</a>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</form>
		<form id="nodeForm">
			<div class="label_inpt_div">
			<h3 class="data_title1">项目节点调整</h3>
			<div class="model_part">
				<div class="tablebox">
					<table>
						<tr>
							<th>序号</th>
							<th>节点排序</th>
							<th>节点名称</th>
							<th>计划完成时间<br>(调整前)</th>
							<th>计划完成时间<br>(调整后)</th>
							<th>节点进度<br>(调整前)</th>
							<th>节点进度<br>(调整后)</th>
						</tr>
						<c:if test="${not empty  nodeChanges}">
							<c:forEach items="${nodeChanges}" var="nodeChange" varStatus="status">
								<tr>
									<td>${status.count} <input type="hidden" name="nodeIds" value="${nodeChange.id}"/></td>
									<td>${nodeChange.pnOrder }</td>
									<td style="text-align:left">${nodeChange.pnName }</td>
									<td>${nodeChange.oldCompletionTime }</td>
									<td>${nodeChange.newCompletionTime }</td>
									<td style="text-align:right">${nodeChange.oldProgress }<c:if test="${nodeChange.oldProgress != null  }">%</c:if></td>
									<td style="text-align:right">${nodeChange.newProgress }<c:if test="${nodeChange.newProgress != null  }">%</c:if></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
				<c:if test="${not empty  nodeChanges}">
					<div id="node_div">
						<label class="w20"><span class="required"> * </span>审核意见:</label>
						<textarea class="w70" name="remark" check="NotEmpty_string_1000_._._._." ></textarea>
						<br>
						<div class="model_btn">
							<input type="hidden" name="pjId" value="${project.id}"/>
							<input type="hidden" name="type" value="1"/>
							<input type="hidden" name="approveResult">
							<a class="btn btn-primary model_btn_ok" onclick="approve('node',1)">通过</a>
							<a class="btn btn-primary model_btn_ok" onclick="approve('node',0)">退回</a>
						</div>
					</div>
				</c:if>
			</div>
			</div>
		</form>
		<form id="wkReportForm">
			<div class="label_inpt_div">
			<h3 class="data_title1">周报信息调整</h3>
			<div class="model_part">
				<div class="tablebox">
					<table>
						<tr>
							<th>序号</th>
							<th>周报标题</th>
							<th>年份</th>
							<th>周数</th>
							<th>日期范围</th>
							<th>周报附件</th>
						</tr>
						<c:if test="${not empty  wkReports}">
							<c:forEach items="${wkReports}" var="wkReport" varStatus="status">
								<tr>
									<td>${status.count} <input type="hidden" name="wkReportIds" value="${wkReport.id}"/></td>
									<td style="text-align:left;">${wkReport.wrTitle }</td>
									<td style="text-align:right;">${wkReport.wrYear }</td>
									<td style="text-align:right;">${wkReport.wrWeek }</td>
									<td>${wkReport.wrDatetime}</td>
									<td><a href="${pageContext.request.contextPath}/${wkReport.wrLine}" target="_blank">查看周报PDF</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
				<c:if test="${not empty  wkReports}">
					<div id="wkReport_div">
						<label class="w20"><span class="required"> * </span>审核意见:</label>
						<textarea class="w70" name="remark" check="NotEmpty_string_1000_._._._." ></textarea>
						<br>
						<div class="model_btn">
							<input type="hidden" name="pjId" value="${project.id}"/>
							<input type="hidden" name="type" value="2"/>
							<input type="hidden" name="approveResult">
							<a class="btn btn-primary model_btn_ok" onclick="approve('wkReport',1)">通过</a>
							<a class="btn btn-primary model_btn_ok" onclick="approve('wkReport',0)">退回</a>
						</div>
					</div>
				</c:if>
			</div>
			</div>
		</form>
		<form id="contractForm">
			<div class="label_inpt_div">
			<h3 class="data_title1">合同信息调整</h3>
			<div class="model_part">
				<div class="tablebox">
					<table>
						<tr>
							<th>序号</th>
							<th>合同名称</th>
							<th>合同主体甲方</th>
							<th>合同主体乙方</th>
							<th>标的金额</th>
							<th>已支付金额<br>(调整前)</th>
							<th>已支付金额<br>(调整后)</th>
						</tr>
						<c:if test="${not empty  contractChanges}">
							<c:forEach items="${contractChanges}" var="contractChange" varStatus="status">
								<tr>
									<td>${status.count} <input type="hidden" name="contractIds" value="${contractChange.id}"/></td>
									<td style="text-align:left;">${contractChange.pcName }</td>
									<td style="text-align:left;">${contractChange.pcBodyA }</td>
									<td style="text-align:left;">${contractChange.pcBodyB }</td>
									<td style="text-align:right;"><fmt:formatNumber type="number" value="${contractChange.pcBdMoney }" pattern="0.0000" maxFractionDigits="4"/></td>
									<td style="text-align:right;"><fmt:formatNumber type="number" value="${contractChange.oldPaidMoney }" pattern="0.0000" maxFractionDigits="4"/></td>
									<td style="text-align:right;"><fmt:formatNumber type="number" value="${contractChange.newPaidMoney }" pattern="0.0000" maxFractionDigits="4"/></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
				<c:if test="${not empty  contractChanges}">
					<div id="contract_div">
						<label class="w20"><span class="required"> * </span>审核意见:</label>
						<textarea class="w70" name="remark" check="NotEmpty_string_1000_._._._." ></textarea>
						<br>
						<div class="model_btn">
							<input type="hidden" name="pjId" value="${project.id}"/>
							<input type="hidden" name="type" value="3"/>
							<input type="hidden" name="approveResult">
							<a class="btn btn-primary model_btn_ok" onclick="approve('contract',1)">通过</a>
							<a class="btn btn-primary model_btn_ok" onclick="approve('contract',0)">退回</a>
						</div>
					</div>
				</c:if>
			</div>
			<div class="model_btn">
					<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
			</div>
			
		</form>
		
	<jsp:include page="../system/modal.jsp"/>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
	        parent.location.reload(true);
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		function approve(key,isPass){
			
					
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var url = "${pageContext.request.contextPath}/project/approve/_approve"+key;
			var form = document.getElementById(key+"Form");
			if(isEmpty(form.remark.value)){
				win.generalAlert("审核意见不能为空！");
				form.remark.focus();
				$.unblockUI();
				return false;
			}
			form.approveResult.value = isPass;
			var formData = new FormData(form);
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
				     if(data == 'success'){
				 		if(isPass == 0){
				 			$(form).find("#"+key+"_div").empty().append("<div>审核退回</div>");
				 		}else if(isPass == 1){
				 			$(form).find("#"+key+"_div").empty().append("<div>审核通过</div>");
				 		}
				     }else if(data == 'fail'){
				     	win.errorAlert("该数据已被操作",function(){
							window.location.reload(true);
						});
				     }
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
			return false;
		}
</script>
</html>
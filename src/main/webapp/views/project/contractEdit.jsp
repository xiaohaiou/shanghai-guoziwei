<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>合同新增、编辑页面</title>
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
	}
</style>
</head>
<body>
	
	<h4>
		<c:if test="${ empty contract.id }">
			<span class="glyphicon glyphicon-pencil"></span>新增项目合同
		</c:if>
		<c:if test="${ not empty contract.id }">
			<span class="glyphicon glyphicon-pencil"></span>修改项目合同
		</c:if>
		<i class="iconfont icon-guanbi"></i>
	</h4>
	
		<form id="form2">
			<div class="label_inpt_div">
				<div class="model_part">
				<label class="w20"><span class="required"></span>项目名称:</label>
				<c:if test="${ not empty project}">
					<input class="w25" type="text" name="pjName" value="${project.pjName}" readonly="readonly"/>
				</c:if>
				<c:if test="${empty project}">
					<input class="w25" type="text" id="pjName" name="pjName" value="" readonly="readonly"/>
				</c:if>
				<label class="w20"><span class="required"> * </span>合同编号:</label>
				<input class="w25" type="text" name="pcNo" value="${contract.pcNo }" check="NotEmpty_string_50_._._._." />
				<label class="w20"><span class="required"> * </span>合同名称:</label>
				<input class="w25" type="text" id="ppcName" name="pcName" value="${contract.pcName }" check="NotEmpty_string_50_._._._." />
				<label class="w20"><span class="required"> * </span>经办人:</label>
				<input class="w25" type="text" name="pcOperator" value="${contract.pcOperator }" check="NotEmpty_string_50_._._._." />
				<label class="w20"><span class="required"> * </span>合同主体甲方:</label>
				<input class="w25" type="text" name="pcBodyA" value="${contract.pcBodyA }" check="NotEmpty_string_200_._._._." />
				<label class="w20"><span class="required"> * </span>合同主体乙方:</label>
				<input class="w25" type="text" name="pcBodyB" value="${contract.pcBodyB }" check="NotEmpty_string_200_._._._." />
				<label class="w20"><span class="required"> * </span>合同类型:</label>
				<select  class="w25" name="pcType" check="NotEmpty_._._._._._.">
					<c:if test="${not empty contractTypes }">
						<c:forEach items="${contractTypes }" var="t">
							<option value="${t.codeNo}" <c:if test="${t.codeNo eq contract.pcType }">selected</c:if>>${t.codeName }</option>
						</c:forEach>
					</c:if>
				</select>
				<br>
				<label class="w20"><span class="required"> * </span>合同标的金额:</label>
				<input class="w25" type="text" name="pcBdMoney" value="<fmt:formatNumber type="number" value="${contract.pcBdMoney }" pattern="0.0000" maxFractionDigits="4"/>" check="NotEmpty_double_15_4_0+_._." />
				<select name="bcUnit">
					<c:if test="${not empty moneyUnits }">
						<c:forEach items="${ moneyUnits}" var="m">
							<option value="${m.codeNo }" <c:if test="${m.codeNo eq contract.bcUnit }">selected</c:if>>${m.codeName }</option>
						</c:forEach>
					</c:if>
				</select>
				<br>
				<label class="w20"><span class="required"> * </span>合同付款方式:</label>
				<select  class="w25" name="pcPayType" check="NotEmpty_._._._._._.">
					<c:forEach items="${pcPayTypes}" var="ppt">
							<option value="${ppt.codeNo }" <c:if test="${ppt.codeNo eq contract.pcPayType }">selected</c:if>>${ppt.codeName }</option>
					</c:forEach>
				</select>
				<label class="w20"><span class="required"> * </span>合同签订日期:</label>
				<input class="w25 time" type="text" readonly="readonly" name="pcSignDate" value="${contract.pcSignDate}"  />
				<br>
				<label class="w20"><span class="required"></span>合同支付金额:</label>
				<input class="w25" type="text" name="pcPaidMoney" value="<fmt:formatNumber type="number" value="${contract.pcPaidMoney }" pattern="0.0000" maxFractionDigits="4"/>" check="Empty_double_15_4_0+_._." />
				<select name="paidUnit">
					<c:if test="${not empty moneyUnits }">
						<c:forEach items="${ moneyUnits}" var="m">
							<option value="${m.codeNo }" <c:if test="${m.codeNo eq contract.paidUnit }">selected</c:if>>${m.codeName }</option>
						</c:forEach>
					</c:if>
				</select>
				<br>
				<label class="w20"><span class="required"></span>是否重大项目:</label>
				<select name="isImportant" check="Empty_._._._._._.">
					<option value="0" <c:if test="${contract.isImportant eq 0 }">selected </c:if>>否</option>
					<option value="1" <c:if test="${contract.isImportant eq 1 }">selected </c:if>>是</option>
				</select>
				<br>
				<label class="w20"><span class="required"></span>备注:</label>
				<textarea class="w70" type="text" name="pcRemark" rows="4" check="Empty_string_1000_._._._." >${contract.pcRemark }</textarea>
				</div>
				<h3 class="data_title1">合同付款进度信息<a class="model_btn_plus" id="addPayRecord" onclick="mload('${pageContext.request.contextPath}/project/payRecord/_addPayRecordTemp?pcId=${contract.id}')">+</a></h3>
				<div class="model_part" id="payRecordList">
					<div class="tablebox">
					
					<table>
						<tr class="table_header" id="payRecordTableTr">
							<th style="width:5%"></th>
							<th style="width:10%">付款期次</th>
							<th style="width:10%">已支付款项</th>
							<th style="width:10%">付款比</th>
							<th style="width:15%">付款日期</th>
							<th style="width:40%">备注</th>
							<th style="width:10%">操作</th>
						</tr>
					
						<c:if test="${not empty payRecords}">
							<c:forEach items="${payRecords}" var="py" varStatus="status">
								<tr id="payRecord${py.id}">
									<td>${status.count}</td>
									<td>${py.payCount}</td>
									<td style="text-align:right;"><fmt:formatNumber type="number" value="${py.paidMoney}" pattern="0.0000" maxFractionDigits="4"/>${py.pmUnit }</td>
									<td style="text-align:right;"><fmt:formatNumber  value="${py.payPercent/100}" pattern="##0%"></fmt:formatNumber></td>
									<td>${py.planPayDate}</td>
									<td style="word-wrap:break-word;text-align:left;">${py.remark}</td>
									<td>
										<input type="hidden" name="payRecordIds" value="${py.id}"/>
										<a class="btn btn-primary model_btn_ok" onclick="mload('${pageContext.request.contextPath}/project/payRecord/_modifyPayRecordTemp?id=${py.id}')">编辑</a>
										<a class="btn btn-primary model_btn_ok" onclick="delTemp('${py.id}','payRecord')">删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					</div>	
				</div>
				<div class="model_btn">
					<input type="hidden" name="id" value="${contract.id }" />
					<input type="hidden" name="pjId" value="${project.id }" />
					<c:if test="${ not empty project}">
						<button class="btn btn-primary model_btn_ok" id="commit-2">保存</button>
					</c:if>
					<c:if test="${ empty project}">
						<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
					</c:if>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
			
			
			
			
			
		<form>
	<jsp:include page="../system/modal.jsp"/>
	<script type="text/javascript">
		//初始化项目名称
		$("#pjName").val($("#ppjName", window.parent.document).val());
		
		function initPayRecordList(pageNums){
			$.ajax({
				url:"${pageContext.request.contextPath}/project/payRecord/_payRecordList?pageNums="+pageNums+"&pcId=${contract.id}",
				type:"POST",
				dataType:"text",
				async:false,
				success:function(data){
					$("#payRecordList").children().remove();
					$("#payRecordList").append(data);
				}
			});	
		}
		
		var editFlag = '${contract.id }';
		var isTemp = ${isTemp};
		if(editFlag != '' && !isTemp){//编辑状态初始化列表
			initPayRecordList(1);//初始化付款记录列表
		}
		
	</script>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/vaild.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
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
			var form = document.getElementById("form2");
			if(isEmpty(form.pcNo.value)){
				form.pcNo.focus();
				win.generalAlert("合同编号不能为空！");
				return false;
			}
			if(isEmpty(form.pcName.value)){
				form.pcName.focus();
				win.generalAlert("合同名称不能为空！");
				return false;
			}
			if(isEmpty(form.pcOperator.value)){
				form.pcOperator.focus();
				win.generalAlert("经办人不能为空！");
				return false;
			}
			if(isEmpty(form.pcBodyA.value)){
				form.pcBodyA.focus();
				win.generalAlert("合同主体甲方不能为空！");
				return false;
			}
			if(isEmpty(form.pcBodyB.value)){
				form.pcBodyB.focus();
				win.generalAlert("合同主体乙方不能为空！");
				return false;
			}
		
			if(isEmpty(form.pcBdMoney.value)){
				form.pcBdMoney.focus();
				win.generalAlert("合同标的金额不能为空！");
				return false;
			}
			if(isEmpty(form.pcSignDate.value)){
				form.pcSignDate.focus();
				win.generalAlert("合同签订日期不能为空！");
				return false;
			}
			if(isEmpty(form.pcPaidMoney.value)){
				form.pcPaidMoney.focus();
				win.generalAlert("合同支付金额不能为空！");
				return false;
			}
			return true;
		}
		
		$("#commit-2").click(function(){//新增正式表
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/project/contract/_saveContract";
			
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
					 		parent.initNodeList('contract',1);
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
		
		$("#commit-1").click(function(){//临时表操作
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/project/contract/_saveContractTemp";
			
			var formData = new FormData($("#form2")[0]);
			console.log(formData);
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
				     var flag = '${contract.id}';
				     var dd = eval("("+data+")");
				     createOrModify(flag,dd);
					 win.successAlert('保存成功！',function(){
							parent.mclose();
					 });
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		})
		
		
		//v是id type是删除的类型
		function delTemp(v,type){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var url = '';
			if(type == 'payRecord'){
				url = "${pageContext.request.contextPath}/project/payRecord/_delPayRecordTemp?id="+v;
			}	
			$.ajax({  
			     url : url,  
			     type : "POST",
			     async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     $.unblockUI();
				 if(data=='success'){
					$("#"+type+v).remove();
					win.successAlert('删除成功！');
				 }else
					win.errorAlert('出错了！');
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
		}
		//v是id type是删除的类型
		function del(v){
			console.log("进入删除方法");
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
			var url = "${pageContext.request.contextPath}/project/payRecord/_delPayRecord?id="+v;
			$.ajax({  
			     url : url,  
			     type : "POST",
			     async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
	    			 $.unblockUI();
	    			 initPayRecordList(1);
					 win.successAlert('删除成功！');
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
		}
		
		function createOrModify(flag,data){
		 	if(flag == '')
		 		createTableTr(data);
		 	else
		 		modifyTableTr(data);	
		}
		
	// 在父页面创建nodeTable中的tr
	function createTableTr(v) {
		var reportStatus = v.reportStatus;
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
		var b1 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"mload('${pageContext.request.contextPath}/project/contract/_modifyContractTemp?id="+v.id+"')\">编辑</a>";
		var b2 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"delTemp('"+v.id+"','contract')\">删除</a>";
		var h1 = "<input type=\"hidden\" name=\"contractIds\" value=\""+v.id+"\"/>";
		var e1 = $("<tr id=\"contract"+v.id+"\"></tr>");
		var td1 = $("<td>新</td>");
		var td2 = $("<td style=\"word-wrap:break-word;\">" + v.pcName +"</td>");
		var td3 = $("<td>"+ v.pcBodyA +"</td>");
		var td4 = $("<td>" + v.pcBodyB + "</td>");
		var td5 = $("<td>" + v.pcSignDate + "</td>");
		var td6 = $("<td>" + v.pcBdMoney+v.bcUnit + "</td>");
		var td7 = $("<td>" + v.pcPaidMoney+v.paidUnit + "</td>");
		var td8 = $("<td>" + status + "</td>");
		var td9 = $("<td>" + b1 + b2 + h1 + "</td>");
		e1.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9);
		$("#contractTableTr", window.parent.document).after(e1);
	}
	
	//编辑在父页面nodeTable中的tr
	function modifyTableTr(v){
		var reportStatus = v.reportStatus;
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
		var e1 = $("#contract"+v.id, window.parent.document).empty();//清除tr中的td
		var b1 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"mload('${pageContext.request.contextPath}/project/contract/_modifyContractTemp?id="+v.id+"')\">编辑</a>";
		var b2 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"delTemp('"+v.id+"','contract')\">删除</a>";
		var h1 = "<input type=\"hidden\" name=\"contractIds\" value=\""+v.id+"\"/>";
		var td1 = $("<td>新</td>");
		var td2 = $("<td style=\"word-wrap:break-word;\">" + v.pcName +"</td>");
		var td3 = $("<td>"+ v.pcBodyA +"</td>");
		var td4 = $("<td>" + v.pcBodyB + "</td>");
		var td5 = $("<td>" + v.pcSignDate + "</td>");
		var td6 = $("<td>" + v.pcBdMoney+v.bcUnit + "</td>");
		var td7 = $("<td>" + v.pcPaidMoney+v.paidUnit + "</td>");
		var td8 = $("<td>" + status + "</td>");
		var td9 = $("<td>" + b1 + b2 + h1 + "</td>");
		e1.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9);
	}	
</script>
</html>
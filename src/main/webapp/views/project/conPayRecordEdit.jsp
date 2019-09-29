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
		<c:if test="${ not empty payRecord.id }">
			<span class="glyphicon glyphicon-pencil"></span>修改合同付款计划信息
		</c:if>
		<c:if test="${ empty payRecord.id }">
			<span class="glyphicon glyphicon-pencil"></span>新增合同付款计划信息
		</c:if>
		<i class="iconfont icon-guanbi"></i>
	</h4>
	
	<div class="model_body">
		<form id="form2">
			<div class="label_inpt_div">
				<label class="w20">合同名称:</label>
				<c:if test="${not empty contract }">
					<input class="w25" type="text" name="pcName" value="${contract.pcName }" readonly="readonly"/>
				</c:if>
				<c:if test="${ empty contract }">
					<input class="w25" type="text" id="pcName" name="pcName" value="" readonly="readonly"/>
				</c:if>
				<label class="w20"><span class="required"> * </span>付款期次:</label>
				<select class="w25" type="text" name="payCount" value="${payRecord.payCount }" check="NotEmpty_._._._._._." >
					<c:if test="${not empty pcPayCounts }">
						<c:forEach items="${pcPayCounts}" var="p">
							<option value="${p.codeNo}" <c:if test="${p.codeNo eq payRecord.payCount }">selected</c:if>>${p.codeName }</option>
						</c:forEach>
					</c:if>
				</select>
				<label class="w20"><span class="required"> * </span>付款比例(%):</label>
				<input class="w25" type="number" name="payPercent" value="${payRecord.payPercent }" check="NotEmpty_int_11_._0+_0_100" />
				<br>
				<label class="w20"><span class="required"> * </span>已支付款项:</label>
				<input class="w25" type="text" name="paidMoney"  value="<fmt:formatNumber type="number" value="${payRecord.paidMoney }" pattern="0.0000" maxFractionDigits="4"/>" check="NotEmpty_double_15_4_0+_._." />	
				<select name="pmUnit">
					<c:if test="${not empty moneyUnits }">
						<c:forEach items="${ moneyUnits}" var="m">
							<option value="${m.codeNo }" <c:if test="${m.codeNo eq payRecord.pmUnit }">selected</c:if>>${m.codeName }</option>
						</c:forEach>
					</c:if>
				</select>
				<br>
				<label class="w20 "><span class="required"> * </span>计划付款日期:</label>
				<input class="w25 time" type="text" readonly="readonly" name="planPayDate" value="${payRecord.planPayDate }" />
				<br>
				<label class="w20">备注:</label>
				<textarea class="w70" type="text" name="remark" rows="4" check="Empty_string_1000_._._._." maxlength="1000">${payRecord.remark }</textarea>
			</div>
			
			<div class="model_btn">
				<input type="hidden" name="id" value="${payRecord.id }" />
				<input type="hidden" name="pcId" value="${contract.id }" />
				<c:choose>
					<c:when test="${!isTemp && not empty contract}">
						<button class="btn btn-primary model_btn_ok" id="commit-2">保存</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
					</c:otherwise>
				</c:choose>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		<form>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/vaild.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
		//初始化合同名称
		$("#pcName").val($("#ppcName", window.parent.document).val());
		
		$(' input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		
		function checkcommit()
		{
			var form = document.getElementById("form2");
			if(isEmpty(form.payCount.value)){
				form.payCount.focus();
				win.generalAlert("付款期次不能为空！");
				return false;
			}
			if(isEmpty(form.payPercent.value)){
				form.payPercent.focus();
				win.generalAlert("付款比例不能为空！");
				return false;
			}
			if(isEmpty(form.paidMoney.value)){
				form.paidMoney.focus();
				win.generalAlert("已支付款项不能为空！");
				return false;
			}
			if(isEmpty(form.planPayDate.value)){
				form.planPayDate.focus();
				win.generalAlert("计划付款日期不能为空！");
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
			var url = "${pageContext.request.contextPath}/project/payRecord/_savePayRecord";
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
				     parent.initPayRecordList(1);
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
		
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/project/payRecord/_savePayRecordTemp";
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
				     var pay = eval("("+data+")");
				     var flag = '${payRecord.id}';
				    createOrModify(flag,pay);
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
		
		function createOrModify(flag,data){
		 	if(flag == '')
		 		createTableTr(data);
		 	else
		 		modifyTableTr(data);	
		}
		
	// 在父页面创建nodeTable中的tr
	function createTableTr(v) {
		var b1 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"mload('${pageContext.request.contextPath}/project/payRecord/_modifyPayRecordTemp?id="+v.id+"')\">编辑</a>";
		var b2 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"delTemp('"+v.id+"','payRecord')\">删除</a>";
		var h1 = "<input type=\"hidden\" name=\"payRecordIds\" value=\""+v.id+"\"/>";
		var e1 = $("<tr id=\"payRecord"+v.id+"\"></tr>");
		var td1 = $("<td>新</td>");
		var td2 = $("<td>" + v.payCount +"</td>");
		var td3 = $("<td>"+ v.paidMoney + v.pmUnit + "</td>");
		var td4 = $("<td>" + v.payPercent + "%</td>");
		var td5 = $("<td>" + v.planPayDate + "</td>");
		var td6 = $("<td style=\"word-wrap:break-word;\">" + v.remark + "</td>");
		var td7 = $("<td>" + b1 + b2 + h1 + "</td>");
		e1.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7);
		$("#payRecordTableTr", window.parent.document).after(e1);
	}
	
	//编辑在父页面nodeTable中的tr
	function modifyTableTr(v){
		var e1 = $("#payRecord"+v.id, window.parent.document).empty();//清除tr中的td
		var b1 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"mload('${pageContext.request.contextPath}/project/payRecord/_modifyPayRecordTemp?id="+v.id+"')\">编辑</a>";
		var b2 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"delTemp('"+v.id+"','payRecord')\">删除</a>";
		var h1 = "<input type=\"hidden\" name=\"payRecordIds\" value=\""+v.id+"\"/>";
		var td1 = $("<td>新</td>");
		var td2 = $("<td>" + v.payCount +"</td>");
		var td3 = $("<td>"+ v.paidMoney + v.pmUnit + "</td>");
		var td4 = $("<td>" + v.payPercent + "%</td>");
		var td5 = $("<td>" + v.planPayDate + "</td>");
		var td6 = $("<td style=\"word-wrap:break-word;\">" + v.remark + "</td>");
		var td7 = $("<td>" + b1 + b2 + h1 + "</td>");
		e1.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7);
	}	
</script>
</html>
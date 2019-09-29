<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>合同信息新增、编辑及保存上报页面</title>
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
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改合同信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增合同信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="riskControlContract" method="post">
		<form:hidden path="id"/>
		<form:hidden path="createPersonName"/>
		<form:hidden path="createPersonId"/>
		<form:hidden path="createDate"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>合同所属业态公司:</label>
				<select name="contractBelongCompany" id="contractBelongCompany" class="selectpicker w25" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.auditDepts}" var="result">
							<option value="${result.nnodeId}"  <c:if test="${riskControlContract.contractBelongCompany == result.nnodeId}">selected="selected"</c:if>>${result.vcFullName}</option>
						</c:forEach>
				</select>
				<label class="w20"><span class="required"> * </span>合同所在实体企业:</label>
				<select name="contractSubordinateCompany" id="contractSubordinateCompany" class="selectpicker w25" >
				<option value="">全部</option>
				</select><br>
				<label class="w20"><span class="required"> * </span>合同公文号:</label>
				<input class="w25" type="text" name="contractDocumentNo" id="contractDocumentNo" placeholder="请输入合同公文号" maxlength="50" value="${riskControlContract.contractDocumentNo}"/>
				<label class="w20"><span class="required"> * </span>合同名称:</label>
				<input class="w25" type="text" name="contractName" id="contractName" placeholder="不超过200个字符" maxlength="200" value="${riskControlContract.contractName}"/><br>
				<label class="w20"><span class="required"> * </span>合同类型:</label>
				<select name="contractType" id="contractType" class="selectpicker w25" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.contractType}" var="result">
							<option value="${result.id}"  <c:if test="${riskControlContract.contractType == result.id}">selected="selected"</c:if>>${result.description}</option>
						</c:forEach>
				</select>
				<label class="w20"><span class="required"> * </span>我方合同主体:</label>
				<input class="w25" type="text" name="ourContractSubject" id="ourContractSubject" placeholder="不超过50个字符" maxlength="50" value="${riskControlContract.ourContractSubject}"/><br>
				<label class="w20"><span class="required"> * </span>对方合同主体:</label>
				<input class="w25" type="text" name="otherContractSubject" id="otherContractSubject" placeholder="不超过50个字符" maxlength="50" value="${riskControlContract.otherContractSubject}"/>
				<label class="w20"><span class="required"> * </span>履行跟踪人:</label>
				<input class="w25" type="text" name="trackingMan" id="trackingMan" placeholder="不超过10个字符" maxlength="10" value="${riskControlContract.trackingMan}"/><br>
				<label class="w20"><span class="required"> * </span>合同标的总额(万元):</label>
				<input class="w25" type="text" name="contractTotalAmount" id="contractTotalAmount" check="NotEmpty_double_15_4_0+_。_." value="${riskControlContract.contractTotalAmount}"/>
				<label class="w20"><span class="required"> * </span>是否属于重大合同:</label>
				<select class="w25" name="isMajorContract" id="isMajorContract"  value="${riskControlContract.isMajorContract}"> 
				  <option value="0" <c:if test="${'0' eq riskControlContract.isMajorContract}">selected</c:if> >否</option>       
				  <option value="1" <c:if test="${'1' eq riskControlContract.isMajorContract}">selected</c:if> >是</option>       
				</select><br>
				<label class="w20"><span class="required"> * </span>合同主要内容:</label>
				<textarea class="w70" rows="3" cols="5" type="text" name="contractContent" id="contractContent" placeholder="不超过500个字符" maxlength="500" class="w25">${riskControlContract.contractContent}</textarea><br>
				<label class="w20"><span class="required"> * </span>合同主要履约节点:</label>
				<textarea class="w70" rows="3" cols="5" type="text" name="contractNode" id="contractNode" placeholder="不超过500个字符" maxlength="500" class="w25">${riskControlContract.contractNode}</textarea><br>
				<label class="w20"><span class="required"> * </span>法务审核人:</label>
				<input type="text" name="legalPerson" id="legalPerson" value="${riskControlContract.legalPerson}" placeholder="不超过10个字符" maxlength="10" class="w25"/>
				<label class="w20"><span class="required"> * </span>合同签订日期:</label>
				<input type="text" name="contractSignDate" id="contractSignDate" value="${riskControlContract.contractSignDate}" readonly="readonly" class="w25 time"/><br>
				<label class="w20"><span class="required"> * </span>是否违约:</label>
				<select class="w25" name="isDefault" id="isDefault"  value="${riskControlContract.isDefault}"> 
				  <option value="0" <c:if test="${'0' eq riskControlContract.isDefault}">selected</c:if> >否</option>       
				  <option value="1" <c:if test="${'1' eq riskControlContract.isDefault}">selected</c:if> >是</option>       
				</select><br>
				<div id="wyInfo">
					<label class="w20" style="text-align:right;">我方违约情况:</label>
					<input type="text" name="ourDefault" id="ourDefault" placeholder="不超过50个字符" maxlength="50" value="${riskControlContract.ourDefault}" class="w25"/>
					<label class="w20" style="text-align:right;">对方违约情况:</label>
					<input type="text" name="otherDefault" id="otherDefault" placeholder="不超过50个字符" maxlength="50" value="${riskControlContract.otherDefault}" class="w25"/><br>
					<label class="w20" style="text-align:right;">风险等级:</label>
					<input type="text" name="riskGrade" id="riskGrade" value="${riskControlContract.riskGrade}" class="w25"/>
					<label class="w20" style="text-align:right;">风险处置方案及进展:</label>
					<input type="text" name="riskManage" id="riskManage" value="${riskControlContract.riskManage}" class="w25"/><br>
					<label class="w20" style="text-align:right;">我方逾期应付款总额(万元):</label>
					<input type="text" name="ourOverPay" id="ourOverPay" check="NotEmpty_double_15_4_0+_。_." value="${riskControlContract.ourOverPay}" class="w25"/>
					<label class="w20" style="text-align:right;">我方违约产生的违约金总额(万元):</label>
					<input type="text" name="ourDefaultPay" id="ourDefaultPay" check="NotEmpty_double_15_4_0+_。_." value="${riskControlContract.ourDefaultPay}" class="w25"/><br>
					<label class="w20" style="text-align:right;">对方逾期应付款总额(万元):</label>
					<input type="text" name="otherOverPay" id="otherOverPay" check="NotEmpty_double_15_4_0+_。_." value="${riskControlContract.otherOverPay}" class="w25"/>
					<label class="w20" style="text-align:right;">对方违约产生的违约金总额(万元):</label>
					<input type="text" name="otherDefaultPay" id="otherDefaultPay" check="NotEmpty_double_15_4_0+_。_." value="${riskControlContract.otherDefaultPay}" class="w25"/>
				</div>
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2" >保存并上报</button>
				<button class="btn btn-default model model_btn_close">取消</button>
			</div>
		</div>
	</form:form>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">	
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		$("#isDefault").change(function(){
			if(this.value == 0){
				$("#wyInfo").hide();
			}else{
				$("#wyInfo").show();
			};
		});	
		function checkcommit() {
			if($("#contractBelongCompany").val()=="") {
				win.errorAlert("合同所属业态公司不能为空");
				return false;
			}
			if($("#contractSubordinateCompany").val()=="") {
				win.errorAlert("合同所在实体企业不能为空");
				return false;
			}
			if($("#contractDocumentNo").val()=="") {
				win.errorAlert("合同公文号不能为空");
				return false;
			}
			if($("#contractName").val()=="") {
				win.errorAlert("合同名称不能为空");
				return false;
			}
			if($("#contractType").val()=="") {
				win.errorAlert("合同类型不能为空");
				return false;
			}
			if($("#ourContractSubject").val()=="") {
				win.errorAlert("我方合同主体不能为空");
				return false;
			}
			if($("#otherContractSubject").val()=="") {
				win.errorAlert("对方合同主体不能为空");
				return false;
			}
			if($("#trackingMan").val()=="") {
				win.errorAlert("履行跟踪人不能为空");
				return false;
			}
			if($("#isMajorContract").val()=="") {
				win.errorAlert("是否属于重大合同不能为空");
				return false;
			}
			if($("#contractContent").val()=="") {
				win.errorAlert("合同主要内容不能为空");
				return false;
			}
			if($("#contractNode").val()=="") {
				win.errorAlert("合同主要履约节点不能为空");
				return false;
			}
			if($("#legalPerson").val()=="") {
				win.errorAlert("法务审核人不能为空");
				return false;
			}
			if($("#contractSignDate").val()=="") {
				win.errorAlert("合同签订日期不能为空");
				return false;
			}
			if($("#isDefault").val()=="") {
				win.errorAlert("是否违约不能为空");
				return false;
			}
			return true;
		};	
		$("#commit-1").click(function(){
			if(!checkcommit() || !vaild.all()) {
				return false;
			} 
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var riskControlContractInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/riskcontrol/contract/saveOrUpdate";
			$.post(url, riskControlContractInfo, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('保存成功！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}else{
					win.errorAlert(commitresult.exceptionStr);
				}
			});
			return false;
		});
		$("#commit-2").click(function(){
			if(!checkcommit()) {
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var riskControlContractInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/riskcontrol/contract/saveOrUpdateAndReported";
			$.post(url, riskControlContractInfo, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('保存成功！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}else if(data == "false"){
					win.successAlert('该数据已经被上报，不能再执行当前操作',function(){
						parent.location.reload();
						parent.mclose();
					});
				}
			});
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		$("#contractBelongCompany").change(function(){
			$("#contractSubordinateCompany").empty();
			var url="${pageContext.request.contextPath}/riskcontrol/contract/selectCompany";
			$.ajax({
				url: url,
				type: "POST",
				dataType: 'json',
				data:{id:$("#contractBelongCompany").val()},
				success: function(result) {
					for(var i=0;i<result.length;i++){
						$("#contractSubordinateCompany").append("<option value="+result[i].nnodeId+">"+result[i].vcFullName+"</option>");
					}
				}
			});
		});
		$(document).ready(function() {
			var isDefault = $("#isDefault").val();
			if(isDefault == 1){
				$("#wyInfo").show();
			}else{
				$("#wyInfo").hide();
			}
			if('${op}' == 'modify'){
				$("#contractSubordinateCompany").empty();
				var contractBelongCompany = '${riskControlContract.contractBelongCompany}';
				var contractSubordinateCompany = '${riskControlContract.contractSubordinateCompany}';
				if(contractBelongCompany != ""){
					var url="${pageContext.request.contextPath}/riskcontrol/contract/selectCompany";
					$.ajax({
						url: url,
						type: "POST",
						dataType: 'json',
						data:{id:contractBelongCompany},
						success: function(result) {
							for(var i=0;i<result.length;i++){
								if(contractSubordinateCompany == result[i].nnodeId){
									$("#contractSubordinateCompany").append("<option value='"+result[i].nnodeId+"' selected='selected'>"+result[i].vcFullName+"</option>");
								}else{
									$("#contractSubordinateCompany").append("<option value='"+result[i].nnodeId+"'>"+result[i].vcFullName+"</option>");
								}
							}
						}
					});
				}
			}
		});
	</script>
</html>
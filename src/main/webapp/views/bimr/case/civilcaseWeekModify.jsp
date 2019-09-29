<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>民事案件填报</title>
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
	<body>
		<h4>
			<span class="glyphicon glyphicon-pencil"></span>民事案件上报
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<form:form id="form2" modelAttribute="entity" method="post">
			<form:hidden path="id" value="${entity.id}"/>
			<form:hidden path="version" value="${entity.version}"/>
			<form:hidden path="createPersonId" value="${entity.createPersonId}"/>
			<form:hidden path="createPersonName" value="${entity.createPersonName}"/>
			<form:hidden path="createDate" value="${entity.createDate}"/>
			<input type="hidden" id="op" value="${op}"/>
			<input type="hidden" id="caseid" value="${entity.id}"/>
			<div class="label_inpt_div">
				<div class="model_part">
					<label class="w20"><span class="required"></span>案件编号：</label>
						<input type="text" name="caseNum" id="caseNum" value="${entity.caseNum}" class="w25" readonly="true"/><br>
						
					<label class="w20"><span class="required"> * </span>承办单位:</label>
					<span> 
						<input type="hidden" id="vcCompanyId" name="vcCompanyId" value="${entity.vcCompanyId}" >
						<input name="vcCompanyName" id="vcCompanyName" value="${entity.vcCompanyName}" readonly="readonly" class="w70" check="NotEmpty">
						<div class="com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:500px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
							<ul id="com_ztree" class="ztree">
							</ul>
					   </div>
				    </span>
				    <br>
						
					<label class="w20"><span class="required"> * </span>原告/申请人/第三人：</label>
						<input type="text" name="plaintiff" id="plaintiff" value="${entity.plaintiff}" class="w25" check="NotEmpty_string_30_._._._."/>
						
					<label class="w20"><span class="required"> * </span>被告/被申请人/第三人：</label>
						<input type="text" name="defendant" id="defendant" value="${entity.defendant}" class="w25" check="NotEmpty_string_30_._._._."/>
					
					<label class="w20"><span class="required"> * </span>我方诉讼地位：</label>
						<form:select path="litigation" class="w25" >
							<form:option value="1">主诉</form:option>
							<form:option value="2">被诉</form:option>
							<form:option value="3">申请人</form:option>
							<form:option value="4">被申请人</form:option>
						</form:select>
					
					<label class="w20"><span class="required"> * </span>受理法院/仲裁机构：</label>
						<input type="text" name="admissibleCourt" id="admissibleCourt" value="${entity.admissibleCourt}" class="w25" check="NotEmpty_string_50_._._._."/>
					
					<label class="w20"><span class="required"> * </span>受案单位所在地(填写具体省份)：</label>
						<input type="text" name="province" id="province" value="${entity.province}" class="w25" check="NotEmpty_string_30_._._._."/>
					
					<label class="w20"><span class="required"> * </span>承办法官/仲裁员：</label>
						<input type="text" name="judge" id="judge" value="${entity.judge}" class="w25" check="NotEmpty_string_50_._._._."/>
					
					<label class="w20"><span class="required"> * </span>涉案金额（万元）：</label>
						<input type="text" name="amount" id="amount" value="${entity.amount}" class="w25" check="NotEmpty_double_12_2_*"/>
					
					<label class="w20"><span class="required"> * </span>案件类型：</label>
						<form:select path="type" class="w25" id="casetype">
							<form:option value="1">合同纠纷</form:option>
							<form:option value="2">劳动纠纷</form:option>
							<form:option value="3">侵权纠纷</form:option>
							<form:option value="4">行政纠纷</form:option>
							<form:option value="5">其他纠纷</form:option>
						</form:select>
					
					<div id="reason1" style="display: none;">
					<label class="w20"><span class="required"> * </span>案由：</label>
						<input type="text" name="reason" id="reasoninput1" value="${entity.reason}" class="w25"  check="NotEmpty_string_50_._._._."/>
					</div>
					<div id="reason2">
					<label class="w20"><span class="required"> * </span>案由：</label>
					<form:select path="reason" class="w25" id="reasoninput2">
					<c:forEach items="${reasonlist}" var="r">
						<form:option value="${r.num}" >${r.description}</form:option>
					</c:forEach>
					</form:select>
					</div>
					
					<label class="w20"><span class="required"> * </span>基本案情：</label>
						<input type="text" name="baseMessage" id="baseMessage" value="${entity.baseMessage}" class="w25"  check="NotEmpty_string_250_._._._."/>
					
					<label class="w20"><span class="required"> * </span>是否为重大案件（根据集团重大案件标准）：</label>
						<form:select path="isMajorCase" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
					<br>	
					<label class="w20"><span class="required"> * </span>是否是清案遗留案件：</label>
						<form:select path="isLeftoverCases" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
					<label class="w20"><span class="required"> * </span>是否是新增案件(回头看)：</label>
						<form:select path="isNewadd" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
					<label class="w20"><span class="required"> * </span>是否因人员优化工作引发案件：</label>
						<form:select path="isStaffOptimization" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
					<br>
					<label class="w20"><span class="required"> * </span>调处阶段：</label>
						<form:select path="mediatingStage" class="w25" >
							<form:option value="1">一审</form:option>
							<form:option value="2">二审</form:option>
							<form:option value="3">再审</form:option>
							<form:option value="4">执行</form:option>
							<form:option value="5">结案</form:option>
						</form:select>
						
					<label class="w20"><span class="required"> * </span>最新进展：</label>
						<input type="text" name="lastProgress" id="lastProgress" value="${entity.lastProgress}" class="w25"  check="NotEmpty_string_200_._._._."/>
											
					<label class="w20"><span class="required"> * </span>合作开始时间：</label>
						<input type="text" name="cooperationDate" id="cooperationDate" value="${entity.cooperationDate}" readonly="true" class="w25 time" check="NotEmpty"/>
					
					<label class="w20"><span class="required"> * </span>纠纷发生时间：</label>
						<input type="text" name="disputeDate" id="disputeDate" value="${entity.disputeDate}"  readonly="true" class="w25 time" check="NotEmpty"/>
					
					<label class="w20"><span class="required"> * </span>业务部门解决纠纷的过程：</label>
						<input type="text" name="dealDisputeProcess" id="dealDisputeProcess" value="${entity.dealDisputeProcess}" class="w25" check="NotEmpty_string_150_._._._."/>
					
					<label class="w20"><span class="required"> * </span>成诉原因：</label>
						<input type="text" name="caseCause" id="caseCause" value="${entity.caseCause}" class="w25" check="NotEmpty_string_100_._._._."/>
					
					<label class="w20"><span class="required"> * </span>案发时间：</label>
						<input type="text" name="caseDate" id="caseDate" value="${entity.caseDate}"  readonly="true" class="w25 time" check="NotEmpty"/>
					
					<label class="w20"><span class="required"> * </span>案龄(年)：</label>
						<form:input path="caseAge" value="${entity.caseAge}"  class="w25" check="NotEmpty_int"/><br>
					
					<label class="w20"><span class="required"> * </span>承办法务：</label>
						<input type="text" name="lawWork" id="lawWork" value="${entity.lawWork}" class="w25" check="NotEmpty_string_30_._._._."/>
					
					<label class="w20"><span class="required"> * </span>案件调处责任人：</label>
						<input type="text" name="responsiblePerson" id="responsiblePerson" value="${entity.responsiblePerson}" class="w25" check="NotEmpty" readonly="true"/>
						<input type="hidden" name="responsiblePersonId" id="responsiblePersonId" value="${entity.responsiblePersonId}"/>
					   
					<label class="w20"><span class="required"> * </span>外聘律所及律师：</label>
						<input type="text" name="externalLaws" id="externalLaws" value="${entity.externalLaws}" class="w25" check="NotEmpty_string_30_._._._."/>
					
					<label class="w20"><span class="required"> * </span>律师费总额（人民币万元）：</label>
						<input type="text" name="lawsAmount" id="lawsAmount" value="${entity.lawsAmount}" class="w25" check="NotEmpty_string_50_._._._."/>
					
					<label class="w20"><span class="required"> * </span>已支付金额（人民币万元）：</label>
						<input type="text" name="payAmount" id="payAmount" value="${entity.payAmount}" class="w25" check="NotEmpty_double_12_2_*"/>
					
					<label class="w20"><span class="required"> * </span>对方外聘律所及律师：</label>
						<input type="text" name="foreignLaw" id="foreignLaw" value="${entity.foreignLaw}" class="w25" check="NotEmpty_string_50_._._._."/>
					
					<label class="w20"><span class="required"> * </span>预测结果：</label>
						<input type="text" name="predictionResluts" id="predictionResluts" value="${entity.predictionResluts}" class="w25" check="NotEmpty"/>
					
					<label class="w20"><span class="required"> * </span>调处时间计划表：</label>
						<input type="text" name="planTime" id="planTime" value="${entity.planTime}" class="w25" check="NotEmpty"/>
					
					<label class="w20"><span class="required"> * </span>预计结果：</label>
						<input type="text" name="expectedResults" id="expectedResults" value="${entity.expectedResults}" class="w25" check="NotEmpty_string_80_._._._."/>
					
					<label class="w20"><span class="required"> * </span>是否问责：</label>
						<form:select path="isAccountability" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
					
					<label class="w20"><span class="required"> * </span>是否进行/完成成因分析：</label>
						<form:select path="isAnalysis" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
					<label class="w20"><span class="required"> * </span>案件成因(实业)：</label>
						<input type="text" name="analysisCause" id="analysisCause" value="${entity.analysisCause}" class="w25" check="NotEmpty"/>
					<label class="w20"><span class="required"> * </span>拟问责建议（若无需问责，请说明具体原因）（实业）：</label>
						<input type="text" name="accountabilitySuggest" id="accountabilitySuggest" value="${entity.accountabilitySuggest}" class="w25" check="NotEmpty"/>	
					<label class="w20"><span class="required"> * </span>问责进展情况（含问责人数及名单）（实业）：</label>
						<input type="text" name="accountabilityResults" id="accountabilityResults" value="${entity.accountabilityResults}" class="w25" check="NotEmpty"/>
					<label class="w20"><span class="required"> * </span>办案思路、调处重点及工作计划（实业）：</label>
						<input type="text" name="caseThoughtFocalPoint" id="caseThoughtFocalPoint" value="${entity.caseThoughtFocalPoint}" class="w25" check="NotEmpty"/>		
					<label class="w20"><span class="required"> * </span>协办人(实业)：</label>
						<input type="text" name="assistingPeople" id="assistingPeople" value="${entity.assistingPeople}" class="w25" check="NotEmpty"/>
					<label class="w20"><span class="required"> * </span>双方保全情况(实业)：</label>
						<input type="text" name="bothPreserveSituation" id="bothPreserveSituation" value="${entity.bothPreserveSituation}" class="w25" check="NotEmpty"/>		
					<label class="w20"><span class="required"> * </span>一案双处实施情况 （实业）：</label>
						<input type="text" name="twoCasesImplementation" id="twoCasesImplementation" value="${entity.twoCasesImplementation}" class="w25" check="NotEmpty"/>		
					
					<label class="w20"><span class="required"></span>判决结果：</label>
						<input type="text" name="verdictResult" id="verdictResult" value="${entity.verdictResult}" class="w25" check="Empty_string_100_._._._."/>
					
					<label class="w20"><span class="required"></span>判决/调解金额（人民币万元）：</label>
						<input type="text" name="verdictAmount" id="verdictAmount" value="${entity.verdictAmount}" class="w25" check="Empty_double_12_2_*"/>
					
					<label class="w20"><span class="required"></span>已执行款项（人民币万元）：</label>
						<input type="text" name="implementMoney" id="implementMoney" value="${entity.implementMoney}" class="w25" check="Empty_double_12_2_*"/>
					
					<label class="w20"><span class="required"></span>避免/挽回经济损失（人民币万元）：</label>
						<input type="text" name="saveLoss" id="saveLoss" value="${entity.saveLoss}" class="w25 "check="Empty_double_12_2_*"/>
					
					<label class="w20">案件移交公文编号（实业）：</label>
						<input type="text" name="transferredNumber" id="transferredNumber" value="${entity.transferredNumber}" class="w25" "/>
					<label class="w20">案件结案公文编号（实业）：</label>
						<input type="text" name="closingNumber" id="closingNumber" value="${entity.closingNumber}" class="w25" "/>
					
					<label class="w20">裁判/调解/和解主文（实业）：</label>
						<input type="text" name="reconciliationDocument" id="reconciliationDocument" value="${entity.reconciliationDocument}" class="w25" "/>
					
					<label class="w20"><span class="required"> * </span>规划结案时间：</label>
						<input type="text" name="casePlanDate" id="casePlanDate" value="${entity.casePlanDate}" readonly="true" class="w25 time" check="NotEmpty"/>
						
					<label class="w20">备注(实业)：</label>
						<input type="text" name="remark1" id="remark1" value="${entity.remark1}" class="w25" "/>
						
						
					<label class="w20">起诉状:</label>
					<!-- <input class="w25" type="file" id="indictmentFile" name="indictmentFile"/> -->
					<button class="" id="selectFile" style="margin-bottom: 10px;">选择文件</button>
					<div id="files" style="display:none;"></div>
					<input class="w25" type="hidden" id="indictment" name="indictment" value="${entity.indictment}"/>
					<c:if test="${not empty entity.indictment}">
					<c:if test="${not empty indictmentFile}">
						<c:forEach items="${indictmentFile}" var="indictmentFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(indictmentFile.filePath,"\\","\\\\")}&_name=${indictmentFile.fileName}')" target="_blank">${indictmentFile.fileName}</a> 
						<i class=del style="cursor: pointer;" onclick="delfile(${index.index+1},'${indictmentFile.fileUuid}',1)">&times;</i></span>
						</c:forEach>
						
					</c:if>
					</c:if>
					<br>
					<label class="w20">判决书/调解书:</label>
					<!-- <input class="w25" type="file" id="judgmentFile" name="judgmentFile"/> -->
					<button class="" id="selectFile2" style="margin-bottom: 10px;">选择文件</button>
					<div id="files2" style="display:none;"></div>
					<input class="w25" type="hidden" id="judgment" name="judgment" value="${entity.judgment}"/>
					<c:if test="${not empty entity.judgment}">
					<c:if test="${not empty judgmentFile}">
						<c:forEach items="${judgmentFile}" var="judgmentFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(judgmentFile.filePath,"\\","\\\\")}&_name=${judgmentFile.fileName}')" target="_blank">${judgmentFile.fileName}</a> 
						<i class=del style="cursor: pointer;" onclick="delfile(${index.index+1},'${judgmentFile.fileUuid}',2)">&times;</i></span>
						</c:forEach>
					</c:if>
					</c:if>
					<br>
					<label class="w20">其它:</label>
					<!-- <input class="w25" type="file" id="oFile" name="oFile"/> -->
					<button class="" id="selectFile3" style="margin-bottom: 10px;">选择文件</button>
					<div id="files3" style="display:none;"></div>
					<input class="w25" type="hidden" id="otherFile" name="otherFile" value="${entity.otherFile}"/>
					<c:if test="${not empty entity.otherFile}">
					<c:if test="${not empty oFile}">
						<c:forEach items="${oFile}" var="oFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(oFile.filePath,"\\","\\\\")}&_name=${oFile.fileName}')" target="_blank">${oFile.fileName}</a> 
						<i class=del style="cursor: pointer;" onclick="delfile(${index.index+1},'${oFile.fileUuid}',3)">&times;</i></span>
						</c:forEach>
					</c:if>
					</c:if>
				<br>		
				</div>
				<div class="model_btn">
					<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
					<button class="btn btn-primary model_btn_ok" id="commit-2" >保存并上报</button>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
		</form:form>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.ztree.all.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
	<script type="text/javascript">
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		
		$("#commit-1").click(function(){
		
		if($("#casetype").val()!=1){
				$("#reasoninput2").val("");
			}else{
				$("#reasoninput1").removeAttr('check');
				$("#reasoninput1").removeAttr('name');
			}
		
			if(!vaild.all())
			{
				return false;
			}
			
			
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/case/saveCivilcase";
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert('保存成功！',function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },  
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
					 alert(XMLHttpRequest.status);
					 alert(XMLHttpRequest.readyState);
					 alert(textStatus);
				 }
			}); 
			return false;
		});
		
		$("#commit-2").click(function(){
		if($("#casetype").val()!=1){
				$("#reasoninput2").val("");
			}else{
				$("#reasoninput1").removeAttr('check');
				$("#reasoninput1").removeAttr('name');
			}
			if(!vaild.all())
			{
				return false;
			}
			
			
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/case/commitCivilcase";
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert('提交审核成功！',function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },  
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
					 alert(XMLHttpRequest.status);
					 alert(XMLHttpRequest.readyState);
					 alert(textStatus);
				 }  
			}); 
			return false;
		});
		
		
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
		var timeoutId;
		$('#vcCompanyName').on('focus click',function(){
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
					$("#vcCompanyId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
					$("#vcCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		
		function checkcommit() {
			return true;
		};	
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
		$('#responsiblePerson').on('focus click',function(){
			mload('${pageContext.request.contextPath}/common/searchPerson');
		});
		
		$(function(){
			if($("#casetype").val()!=1){
				$("#reason1").css('display','');
				$("#reason2").css('display','none');
			}else{
				$("#reason1").css('display','none');
				$("#reason2").css('display','');
			}
		
			$("#casetype").change(function(){
				$("#reasoninput1").val("");
				var value=$(this).val();
				if(value==1){
				$("#reason1").css('display','none');
				$("#reason2").css('display','');
				}else{
				$("#reason1").css('display','');
				$("#reason2").css('display','none');
				}
			});
		});
		function delfile(e,f,type){
			var id= $("#caseid").val();
			var url = "${pageContext.request.contextPath}/bimr/case/delfile?id="+id+"&uuid="+f+"&type="+type;
			 $.ajax({
                url : url,
                type : "POST",
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success : function(data) {
                    var r = data.result;
                    if (r == 1){
                        win.successAlert(data.message,function(){
                        //$("#"+e).empty();
                           location.reload(true);
                           // parent.mclose();
                           return;
                        });
					}else{
                        win.errorAlert(data.message);
                        return;
					}
                }
            });
		}
		(function() {
					var count = 0;
					 var clicked = function() {
						var files = $(this).closest('#files')
						$(this).closest('label').remove()
						if (files.find('label').length == 0) {
							files.css('display', 'none');
						}
						return false;
					}
					var changed = function() {
						if (this.value) {
							$(this).closest('label')
								.removeClass('hidden')
								.find('span')
								.text(this.value.substring(this.value.replace(/\\/g, '/').lastIndexOf('/') + 1));
						}
						$('.file.hidden').remove();
					}
					$("#files").on('click', 'label.file i.del', clicked)
						.on('change', 'label.file input[type=file]', changed)
						.on('blur', 'label.file input[type=file]', changed)
				    $("#selectFile").click(function(){
						var files = $(this).parent().find('#files')	
						files.css('display', 'block')
						var id = 'file-' + (++count)
						var el = $('<label class="file hidden"><input class="file" style="display : none;" type="file" name="indictmentFile" id="' + id +
								'" value=""/><span></span><i class=del>&times;</i></label>')
							.appendTo(files)
							.trigger('click')
							return false;
					});
				})();
				(function() {
					var count = 0;
					 var clicked = function() {
						var files = $(this).closest('#files2')
						$(this).closest('label').remove()
						if (files.find('label').length == 0) {
							files.css('display', 'none');
						}
						return false;
					}
					var changed = function() {
						if (this.value) {
							$(this).closest('label')
								.removeClass('hidden')
								.find('span')
								.text(this.value.substring(this.value.replace(/\\/g, '/').lastIndexOf('/') + 1));
						}
						$('.file.hidden').remove();
					}
					$("#files2").on('click', 'label.file i.del', clicked)
						.on('change', 'label.file input[type=file]', changed)
						.on('blur', 'label.file input[type=file]', changed)
				    $("#selectFile2").click(function(){
						var files = $(this).parent().find('#files2')	
						files.css('display', 'block')
						var id = 'file-' + (++count)
						var el = $('<label class="file hidden"><input class="file" style="display : none;" type="file" name="judgmentFile" id="' + id +
								'" value=""/><span></span><i class=del>&times;</i></label>')
							.appendTo(files)
							.trigger('click')
							return false;
					});
				})();
				(function() {
					var count = 0;
					 var clicked = function() {
						var files = $(this).closest('#files3')
						$(this).closest('label').remove()
						if (files.find('label').length == 0) {
							files.css('display', 'none');
						}
						return false;
					}
					var changed = function() {
						if (this.value) {
							$(this).closest('label')
								.removeClass('hidden')
								.find('span')
								.text(this.value.substring(this.value.replace(/\\/g, '/').lastIndexOf('/') + 1));
						}
						$('.file.hidden').remove();
					}
					$("#files3").on('click', 'label.file i.del', clicked)
						.on('change', 'label.file input[type=file]', changed)
						.on('blur', 'label.file input[type=file]', changed)
				    $("#selectFile3").click(function(){
						var files = $(this).parent().find('#files3')	
						files.css('display', 'block')
						var id = 'file-' + (++count)
						var el = $('<label class="file hidden"><input class="file" style="display : none;" type="file" name="oFile" id="' + id +
								'" value=""/><span></span><i class=del>&times;</i></label>')
							.appendTo(files)
							.trigger('click')
							return false;
					});
				})();
	</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>合规台账新增、编辑及保存上报页面</title>
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
	<style type="text/css">
		#com_ztree span {
			padding-left:0;
		}
	</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改合规台账
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增合规台账
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="riskControlAccount" method="post">
		<form:hidden path="id"/>
		<form:hidden path="createPersonName"/>
		<form:hidden path="createPersonId"/>
		<form:hidden path="createDate"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>投诉(专项调查)项目名称:</label>
				<input class="w25" type="text" name="complainProjectName" id="complainProjectName" maxlength="50" placeholder="请输入投诉项目名称,50字符以内" value="${riskControlAccount.complainProjectName}"/>
				<label class="w20"><span class="required"> * </span>承办部门:</label>
				<input class="w25" type="text" name="undertakeDept" id="undertakeDept" maxlength="50" placeholder="请输入承办部门,50字符以内" value="${riskControlAccount.undertakeDept}"/><br>
				<label class="w20"><span class="required"> * </span>是否集团内部投诉:</label>
				<select class="w25" name="isGroupInternalComplain" id="isGroupInternalComplain"  value="${riskControlAccount.isGroupInternalComplain}"> 
				  <option value="0" <c:if test="${'0' eq riskControlAccount.isGroupInternalComplain}">selected</c:if> >否</option>       
				  <option value="1" <c:if test="${'1' eq riskControlAccount.isGroupInternalComplain}">selected</c:if> >是</option>       
				</select>
				<label class="w20"><span class="required"> * </span>投诉渠道:</label>
				<select class="w25" name="complainChannel" id="complainChannel"  value="${riskControlAccount.complainChannel}"> 
				  <option value="0" <c:if test="${'0' eq riskControlAccount.isGroupInternalComplain}">selected</c:if> >电话</option>       
				  <option value="1" <c:if test="${'1' eq riskControlAccount.isGroupInternalComplain}">selected</c:if> >邮件</option>   
				  <option value="2" <c:if test="${'2' eq riskControlAccount.isGroupInternalComplain}">selected</c:if> >其他</option>      
				</select><br>
				<label class="w20"><span class="required"> * </span>被投诉人员工编号:</label>
				<input class="w25" type="text" name="complainedPersonNo" id="complainedPersonNo" maxlength="50" placeholder="请输入被投诉人员工编号,50字符以内" value="${riskControlAccount.complainedPersonNo}"/>
				<label class="w20">投诉主要内容类型:</label>
				<select class="w25" name="complainContentType" id="complainContentType"  value="${riskControlAccount.complainContentType}"> 
				  <option value="0" <c:if test="${'0' eq riskControlAccount.complainContentType}">selected</c:if> >工资拖欠</option>       
				  <option value="1" <c:if test="${'1' eq riskControlAccount.complainContentType}">selected</c:if> >辞退</option>   
				  <option value="2" <c:if test="${'2' eq riskControlAccount.complainContentType}">selected</c:if> >其他</option>      
				</select><br>
				<label class="w20"><span class="required"> * </span>被投诉人单位:</label>
				<span class="w25"> 
					<input type="hidden" id="complainedPersonDept" name="complainedPersonDept" value="${riskControlAccount.complainedPersonDept}" >
					<input name="complainedPersonDeptName" id="complainedPersonDeptName" value="${riskControlAccount.complainedPersonDeptName}" placeholder="请输入被投诉人单位" readonly="readonly">
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
						</ul>
				   </div>
			    </span>
				<label class="w20"><span class="required"> * </span>是否匿名投诉:</label>
				<select class="w25" name="isAnonymityComplain" id="isAnonymityComplain"  value="${riskControlAccount.isAnonymityComplain}"> 
				  <option value="0" <c:if test="${'0' eq riskControlAccount.isAnonymityComplain}">selected</c:if> >否</option>       
				  <option value="1" <c:if test="${'1' eq riskControlAccount.isAnonymityComplain}">selected</c:if> >是</option>       
				</select><br>
				<label class="w20"><span class="required"> * </span>被投诉人姓名:</label>
				<input class="w25" type="text" name="complainedPersonName" id="complainedPersonName" maxlength="10" placeholder="请输入被投诉人姓名,10字符以内" value="${riskControlAccount.complainedPersonName}"/>
				<label class="w20"><span class="required"> * </span>投诉处理时间:</label>
				<input type="text" name="complainManageDate" id="complainManageDate" value="${riskControlAccount.complainManageDate}" placeholder="请输入投诉处理时间" readonly="readonly" class="w25 time"/><br>
				<label class="w20"><span class="required"> * </span>被投诉人职务:</label>
				<input class="w25" type="text" name="complainedPersonDuty" id="complainedPersonDuty" maxlength="10" placeholder="请输入被投诉人职务,10字符以内" value="${riskControlAccount.complainedPersonDuty}"/>
				<label class="w20"><span class="required"> * </span>投诉上报时间:</label>
				<input type="text" name="complainEscalateDate" id="complainEscalateDate" value="${riskControlAccount.complainEscalateDate}" placeholder="请输入投诉上报时间" readonly="readonly" class="w25 time"/><br>
				<label class="w20"><span class="required"> * </span>被投诉人电话:</label>
				<input class="w25" type="text" name="complainedPersonPhone" id="complainedPersonPhone" maxlength="20" placeholder="请输入被投诉人电话,20字符以内" value="${riskControlAccount.complainedPersonPhone}"/>
				<label class="w20"><span class="required"> * </span>投诉最终审批人:</label>
				<input class="w25" type="text" name="complainFinalApprover" id="complainFinalApprover" maxlength="10" placeholder="请输入投诉最终审批人,10字符以内" value="${riskControlAccount.complainFinalApprover}"/><br>
				<label class="w20"><span class="required"> * </span>投诉收到时间:</label>
				<input type="text" name="complainReceiveDate" id="complainReceiveDate" value="${riskControlAccount.complainReceiveDate}" placeholder="请输入投诉收到时间" readonly="readonly" class="w25 time"/>
				<label class="w20"><span class="required"> * </span>是否开展后续调查:</label>
				<select class="w25" name="isFollowupInvestigation" id="isFollowupInvestigation"  value="${riskControlAccount.isFollowupInvestigation}"> 
				  <option value="0" <c:if test="${'0' eq riskControlAccount.isFollowupInvestigation}">selected</c:if> >否</option>       
				  <option value="1" <c:if test="${'1' eq riskControlAccount.isFollowupInvestigation}">selected</c:if> >是</option>   
				  <option value="2" <c:if test="${'2' eq riskControlAccount.isFollowupInvestigation}">selected</c:if> >待定</option>      
				</select><br>
				<div id="jbdcnr" style="display:inline;text-align:right;">
					<label class="w20"><span class="required"> * </span>举报调查具体内容:</label>
					<textarea rows="5" cols="5" id="reportInvestigation" name="reportInvestigation" maxlength="500" placeholder="请输入举报调查具体内容,500字符以内" class="w70">${riskControlAccount.reportInvestigation}</textarea>
				</div>
				<div id="wkzdcyy" style="display:inline;text-align:right;">
					<label class="w20"><span class="required"> * </span>未开展后续调查原因:</label>
					<textarea rows="5" cols="5" id="noFollowupInvestigationReason" name="noFollowupInvestigationReason" maxlength="500" placeholder="请输入未开展后续调查原因,500字符以内" class="w70">${riskControlAccount.noFollowupInvestigationReason}</textarea>
				</div>
			</div>
			<div id="hgdcapyjj">
				<h3 class="data_title1">合规调查安排与结果</h3>
					<div class="model_part">
						<label class="w20"><span class="required"> * </span>调查负责人:</label>
						<input class="w25" type="text" name="investigateHeader" id="investigateHeader" maxlength="10" placeholder="请输入调查负责人,10字符以内" value="${riskControlAccount.investigateHeader}"/>
						<label class="w20"><span class="required"> * </span>相关责任人:</label>
						<input class="w25" type="text" name="responsiblePerson" id="responsiblePerson" maxlength="10" placeholder="请输入相关责任人,10字符以内" value="${riskControlAccount.responsiblePerson}"/><br>
						<label class="w20"><span class="required"> * </span>调查启动时间:</label>
						<input class="w25 time" type="text" name="surveyStartupTime" id="surveyStartupTime" readonly="readonly" placeholder="请输入调查启动时间" value="${riskControlAccount.surveyStartupTime}"/>
						<label class="w20"><span class="required"> * </span>调查结束时间:</label>
						<input class="w25 time" type="text" name="surveyEndTime" id="surveyEndTime" readonly="readonly" placeholder="请输入调查结束时间" value="${riskControlAccount.surveyEndTime}"/><br>
						<label class="w20"><span class="required"> * </span>调查涉及单位:</label>
						<span class="w25"> 
							<input type="hidden" name="investigateInvolveDept" id="investigateInvolveDept" value="${riskControlAccount.investigateInvolveDept}" />
							<input name="investigateInvolveDeptName" id="investigateInvolveDeptName" value="${riskControlAccount.investigateInvolveDeptName}" placeholder="请输入调查涉及单位" readonly="readonly"/>
							<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
								<ul id="com_ztree2" class="ztree">
								</ul>
						   </div>
					    </span>
						<label class="w20"><span class="required"> * </span>被调查人员姓名:</label>
						<input class="w25" type="text" name="investigatePersonName" id="investigatePersonName" maxlength="10" placeholder="请输入被调查人员姓名,10字符以内" value="${riskControlAccount.investigatePersonName}"/><br>
						<label class="w20"><span class="required"> * </span>建议人员问责情况:</label>
						<textarea rows="5" cols="5" class="w25" type="text" name="suggestPersonBlameCondition" maxlength="500" placeholder="请输入建议人员问责情况,500字符以内" id="suggestPersonBlameCondition">${riskControlAccount.suggestPersonBlameCondition}</textarea>
						<label class="w20"><span class="required"> * </span>提出合规建议情况:</label>
						<textarea rows="5" cols="5" class="w25" type="text" name="raiseSuggestCondition" maxlength="500" placeholder="请输入提出合规建议情况,500字符以内" id="raiseSuggestCondition">${riskControlAccount.raiseSuggestCondition}</textarea><br>
						<label class="w20"><span class="required"> * </span>被调查人员职务:</label>
						<input class="w25" type="text" name="investigatePersonDuty" id="investigatePersonDuty" maxlength="20" placeholder="请输入被调查人员职务,20字符以内" value="${riskControlAccount.investigatePersonDuty}"/>
						<label class="w20"><span class="required"> * </span>调查报告办文编号:</label>
						<input class="w25" type="text" name="investigateReportNo" id="investigateReportNo" maxlength="20" placeholder="请输入调查报告办文编号,20字符以内" value="${riskControlAccount.investigateReportNo}"/><br>
						<label class="w20"><span class="required"> * </span>被调查人员管理级别:</label>
						<select name="investigatedPersonLevel" id="investigatedPersonLevel" class="selectpicker w25" >
						<option value="">全部</option>
							<c:forEach items="${requestScope.personManageLevel}" var="result">
									<option value="${result.id}"  <c:if test="${riskControlAccount.investigatedPersonLevel == result.id}">selected="selected"</c:if>>${result.description}</option>
								</c:forEach>
						</select>
						<label class="w20"><span class="required"> * </span>报告所属产业集团:</label>
						<span class="w25"> 
							<input type="hidden" name="reportBelongCompany" id="reportBelongCompany" value="${riskControlAccount.reportBelongCompany}"/>
							<input name="reportBelongCompanyName" id="reportBelongCompanyName" value="${riskControlAccount.reportBelongCompanyName}" placeholder="请输入报告所属产业集团" readonly="readonly"/>
					    </span>
						<label class="w20"><span class="required"> * </span>被调查单位是否配合:</label>
						<select class="w25" name="isInvestigatedCooperate" id="isInvestigatedCooperate"  value="${riskControlAccount.isInvestigatedCooperate}"> 
						  <option value="0" <c:if test="${'0' eq riskControlAccount.isInvestigatedCooperate}">selected</c:if> >否</option>       
						  <option value="1" <c:if test="${'1' eq riskControlAccount.isInvestigatedCooperate}">selected</c:if> >是</option>   
						  <option value="2" <c:if test="${'1' eq riskControlAccount.isInvestigatedCooperate}">selected</c:if> >待定</option>      
						</select>
						<label class="w20"><span class="required"> * </span>报告名称:</label>
						<input class="w25" type="text" name="reportName" id="reportName" maxlength="50" placeholder="请输入报告名称,50字符以内" value="${riskControlAccount.reportName}"/><br>
						<label class="w20"><span class="required"> * </span>责任界定:</label>
						<textarea rows="5" cols="5" class="w25" type="text" name="responsibilityFor" id="responsibilityFor" placeholder="请输入责任界定,50字符以内" maxlength="50">${riskControlAccount.responsibilityFor}</textarea>
						<label class="w20"><span class="required"> * </span>调查结论:</label>
						<textarea rows="5" cols="5" class="w25" type="text" name="investigateConclusion" id="investigateConclusion" placeholder="请输入调查结论,500字符以内" maxlength="500">${riskControlAccount.investigateConclusion}</textarea><br>
						<label class="w20"><span class="required"> * </span>报告类型:</label>
						<input class="w25" type="text" name="reportType" id="reportType" value="${riskControlAccount.reportType}" placeholder="请输入报告类型,20字符以内" maxlength="20"/>
						<label class="w20"><span class="required"> * </span>报告撰写人:</label>
						<input class="w25" type="text" name="reportWriter" id="reportWriter" value="${riskControlAccount.reportWriter}" placeholder="请输入报告撰写人,10字符以内" maxlength="10"/><br>
						<label class="w20"><span class="required"> * </span>报告复核人:</label>
						<input class="w25" type="text" name="reportReviewer" id="reportReviewer" value="${riskControlAccount.reportReviewer}" placeholder="请输入报告复核人,10字符以内" maxlength="10"/>
						<label class="w20"><span class="required"> * </span>报告提交时间:</label>
						<input class="w25 time" type="text" name="reportCommitTime" id="reportCommitTime" readonly="readonly" placeholder="请输入报告提交时间" value="${riskControlAccount.reportCommitTime}"/><br>
						<label class="w20"><span class="required"> * </span>报告复核通过时间:</label>
						<input class="w25 time" type="text" name="reportReviewTime" id="reportReviewTime" readonly="readonly" placeholder="请输入报告复核通过时间" value="${riskControlAccount.reportReviewTime}"/>
						<label class="w20"><span class="required"> * </span>报告最终审批人:</label>
						<input class="w25" type="text" name="reportFinalExaminer" id="reportFinalExaminer" value="${riskControlAccount.reportFinalExaminer}" placeholder="请输入报告最终审批人,10字符以内" maxlength="10"/><br>
						<label class="w20"><span class="required"> * </span>调查发现其他违规事件:</label>
						<textarea rows="5" cols="5" class="w70" type="text" name="investigateFindOtherInfraction" id="investigateFindOtherInfraction" placeholder="请输入调查发现其他违规事件,500字符以内" maxlength="500">${riskControlAccount.investigateFindOtherInfraction}</textarea><br>
						<label class="w20"><span class="required"> * </span>是否涉及整改:</label>
						<select class="w25" name="isInvolveAbarbeitung" id="isInvolveAbarbeitung"  value="${riskControlAccount.isInvolveAbarbeitung}"> 
						  <option value="0" <c:if test="${'0' eq riskControlAccount.isInvolveAbarbeitung}">selected</c:if> >否</option>       
						  <option value="1" <c:if test="${'1' eq riskControlAccount.isInvolveAbarbeitung}">selected</c:if> >是</option>   
						  <option value="2" <c:if test="${'2' eq riskControlAccount.isInvolveAbarbeitung}">selected</c:if> >待定</option>      
						</select>
					</div>
			</div>
			<div id="zgqk">
				<h3 class="data_title1">整改情况</h3>
				<div class="model_part">
					<label class="w20"><span class="required"> * </span>处分问责呈报公文编号:</label>
					<input class="w25" type="text" name="punishReportNo" id="punishReportNo" value="${riskControlAccount.punishReportNo}" placeholder="请输入处分问责呈报公文编号,20字符以内" maxlength="20"/>
					<label class="w20"><span class="required"> * </span>整改通知书下发时间:</label>
					<input class="w25 time" type="text" name="abarbeitungNoticeTime" id="abarbeitungNoticeTime" readonly="readonly" placeholder="请输入整改通知书下发时间" value="${riskControlAccount.abarbeitungNoticeTime}"/><br>
					<label class="w20"><span class="required"> * </span>处分问责办文编号:</label>
					<input class="w25" type="text" name="punishReportNo2" id="punishReportNo2" value="${riskControlAccount.punishReportNo2}" placeholder="请输入处分问责办文编号,20字符以内" maxlength="20"/>
					<label class="w20"><span class="required"> * </span>是否整改验收:</label>
					<select class="w25" name="isAbarbeitungAccept" id="isAbarbeitungAccept"  value="${riskControlAccount.isAbarbeitungAccept}"> 
					  <option value="0" <c:if test="${'0' eq riskControlAccount.isAbarbeitungAccept}">selected</c:if> >否</option>       
					  <option value="1" <c:if test="${'1' eq riskControlAccount.isAbarbeitungAccept}">selected</c:if> >是</option>   
					  <option value="2" <c:if test="${'2' eq riskControlAccount.isAbarbeitungAccept}">selected</c:if> >待定</option>      
					</select>
					<div id="sfzgys">
						<label class="w20" style="text-align: right;"><span class="required"> * </span>整改责任人:</label>
						<input class="w25" type="text" name="abarbeitungOfficer" id="abarbeitungOfficer" value="${riskControlAccount.abarbeitungOfficer}" placeholder="请输入整改责任人,10字符以内" maxlength="10"/>
						<label class="w20" style="text-align: right;"><span class="required"> * </span>整改验收通过时间:</label>
						<input class="w25 time" type="text" name="abarbeitungPassTime" id="abarbeitungPassTime" readonly="readonly" placeholder="请输入整改验收通过时间" value="${riskControlAccount.abarbeitungPassTime}"/><br>
						<label class="w20" style="text-align: right;"><span class="required"> * </span>要求整改完成时间:</label>
						<input class="w25 time" type="text" name="requireAbarbeitungTime" id="requireAbarbeitungTime" readonly="readonly" placeholder="请输入要求整改完成时间" value="${riskControlAccount.requireAbarbeitungTime}"/>
						<label class="w20" style="text-align: right;"><span class="required"> * </span>整改验收人:</label>
						<input class="w25" type="text" name="abarbeitungAccepter" id="abarbeitungAccepter" value="${riskControlAccount.abarbeitungAccepter}" placeholder="请输入整改验收人,10字符以内" maxlength="10"/><br>
						<label class="w20" style="text-align: right;"><span class="required"> * </span>建议问责落实情况:</label>
						<textarea rows="5" cols="5" class="w25" type="text" name="suggestWorkCondition" id="suggestWorkCondition" placeholder="请输入建议问责落实情况,500字符以内" maxlength="500">${riskControlAccount.suggestWorkCondition}</textarea>
						<label class="w20" style="text-align: right;"><span class="required"> * </span>整改结果:</label>
						<textarea rows="5" cols="5" class="w25" type="text" name="abarbeitungResult" id="abarbeitungResult" placeholder="请输入整改结果,500字符以内" maxlength="500">${riskControlAccount.abarbeitungResult}</textarea>
					</div>
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
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		var timeoutId;
		$('#complainedPersonDeptName,#investigateInvolveDeptName').on('focus click',function(){
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
			zTreeObj2 = $.fn.zTree.init($("#com_ztree2"), com_ztree_setting, com_data);
			//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
			var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var treeObj2 = $.fn.zTree.getZTreeObj("com_ztree2");
			var nodes = treeObj.getNodes();
			var nodes2 = treeObj2.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
			var childrenNodes = treeObj.transformToArray(nodes);
			var childrenNodes2 = treeObj2.transformToArray(nodes2);
			if(childrenNodes[0]!=null){
				treeObj.expandNode(childrenNodes[0],true);
			}
			if(childrenNodes2[0]!=null){
				treeObj2.expandNode(childrenNodes2[0],true);
			}
		});
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					$("#complainedPersonDept").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#complainedPersonDeptName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		$("#com_ztree2").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree2").getSelectedNodes()[0]) {
					$("#investigateInvolveDept").val($.fn.zTree.getZTreeObj("com_ztree2").getSelectedNodes()[0].id)
					$("#investigateInvolveDeptName").val($.fn.zTree.getZTreeObj("com_ztree2").getSelectedNodes()[0].name);
					var companyId = $("#investigateInvolveDept").val();
					var url = "${pageContext.request.contextPath}/riskcontrol/account/getCoreComp";
					$.ajax({
						type:'POST',
					 	data:{companyId:companyId},
					 	dataType:'json',
					 	url:url,
					 	success:function(data){
					 		$("#reportBelongCompany").val(data.nnodeId);
					 		$("#reportBelongCompanyName").val(data.vcFullName);
					 	}
					});
				}
		   	});
		});
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		$("#isFollowupInvestigation").change(function(){
			if(this.value == 1){
				$("#hgdcapyjj").show();
				$("#wkzdcyy").hide();
				$("#jbdcnr").show();
				var isInvolveAbarbeitung = $("#isInvolveAbarbeitung").val();
				if(isInvolveAbarbeitung == 1){
					$("#zgqk").show();
					var isAbarbeitungAccept = $("#isAbarbeitungAccept").val();
					if(isAbarbeitungAccept == 1){
						$("#sfzgys").show();
					}
				}
			}else{
				$("#hgdcapyjj").hide();
				$("#wkzdcyy").show();
				$("#jbdcnr").hide();
				$("#zgqk").hide();
			};
		});	
		$("#isInvolveAbarbeitung").change(function(){
			if(this.value == 1){
				$("#zgqk").show();
			}else{
				$("#zgqk").hide();
			};
		});	
		$("#isAbarbeitungAccept").change(function(){
			if(this.value == 1){
				$("#sfzgys").show();
			}else{
				$("#sfzgys").hide();
			};
		});	
		function checkcommit() {
			if($("#complainProjectName").val()=="") {
				win.errorAlert("投诉(专项调查)项目名称不能为空");
				return false;
			}
			if($("#undertakeDept").val()=="") {
				win.errorAlert("承办部门不能为空");
				return false;
			}
			if($("#isGroupInternalComplain").val()=="") {
				win.errorAlert("是否集团内部投诉不能为空");
				return false;
			}
			if($("#complainChannel").val()=="") {
				win.errorAlert("投诉渠道不能为空");
				return false;
			}
			if($("#complainedPersonNo").val()=="") {
				win.errorAlert("被投诉人员工编号不能为空");
				return false;
			}
			if($("#complainedPersonDept").val()=="") {
				win.errorAlert("被投诉人单位不能为空");
				return false;
			}
			if($("#isAnonymityComplain").val()=="") {
				win.errorAlert("是否匿名投诉不能为空");
				return false;
			}
			if($("#complainedPersonName").val()=="") {
				win.errorAlert("被投诉人姓名不能为空");
				return false;
			}
			if($("#complainManageDate").val()=="") {
				win.errorAlert("投诉处理时间不能为空");
				return false;
			}
			if($("#complainedPersonDuty").val()=="") {
				win.errorAlert("被投诉人职务不能为空");
				return false;
			}
			if($("#complainEscalateDate").val()=="") {
				win.errorAlert("投诉上报时间不能为空");
				return false;
			}
			if($("#complainedPersonPhone").val()=="") {
				win.errorAlert("被投诉人电话不能为空");
				return false;
			}      
			if($("#complainFinalApprover").val()=="") {
				win.errorAlert("投诉最终审批人不能为空");
				return false;
			}
			if($("#complainReceiveDate").val()=="") {
				win.errorAlert("投诉收到时间不能为空");
				return false;
			}
			if($("#isFollowupInvestigation").val()=="") {
				win.errorAlert("是否开展后续调查不能为空");
				return false;
			}
			if($("#isFollowupInvestigation").val()!="1") {
				if($("#noFollowupInvestigationReason").val()=="") {
					win.errorAlert("未开展后续调查原因不能为空");
					return false;
				}
			}else{
				if($("#reportInvestigation").val()=="") {
					win.errorAlert("举报调查具体内容不能为空");
					return false;
				}
				if($("#investigateHeader").val()=="") {
					win.errorAlert("调查负责人不能为空");
					return false;
				}
				if($("#responsiblePerson").val()=="") {
					win.errorAlert("相关责任人不能为空");
					return false;
				}
				if($("#surveyStartupTime").val()=="") {
					win.errorAlert("调查启动时间不能为空");
					return false;
				}
				if($("#surveyEndTime").val()=="") {
					win.errorAlert("调查结束时间不能为空");
					return false;
				}
				if($("#investigateInvolveDept").val()=="") {
					win.errorAlert("调查涉及单位不能为空");
					return false;
				}
				if($("#investigatePersonName").val()=="") {
					win.errorAlert("被调查人员姓名不能为空");
					return false;
				}
				if($("#suggestPersonBlameCondition").val()=="") {
					win.errorAlert("建议人员问责情况不能为空");
					return false;
				}
				if($("#raiseSuggestCondition").val()=="") {
					win.errorAlert("提出合规建议情况不能为空");
					return false;
				}
				if($("#investigatePersonDuty").val()=="") {
					win.errorAlert("被调查人员职务不能为空");
					return false;
				}
				if($("#investigateReportNo").val()=="") {
					win.errorAlert("调查报告办文编号不能为空");
					return false;
				}
				if($("#investigatedPersonLevel").val()=="") {
					win.errorAlert("被调查人员管理级别不能为空");
					return false;
				}
				if($("#reportBelongCompany").val()=="") {
					win.errorAlert("报告所属产业集团不能为空");
					return false;
				}
				if($("#isInvestigatedCooperate").val()=="") {
					win.errorAlert("被调查单位是否配合不能为空");
					return false;
				}
				if($("#reportName").val()=="") {
					win.errorAlert("报告名称不能为空");
					return false;
				}
				if($("#responsibilityFor").val()=="") {
					win.errorAlert("责任界定不能为空");
					return false;
				}
				if($("#investigateConclusion").val()=="") {
					win.errorAlert("调查结论不能为空");
					return false;
				}
				if($("#reportType").val()=="") {
					win.errorAlert("报告类型不能为空");
					return false;
				}
				if($("#reportWriter").val()=="") {
					win.errorAlert("报告撰写人不能为空");
					return false;
				}
				if($("#reportReviewer").val()=="") {
					win.errorAlert("报告复核人不能为空");
					return false;
				}
				if($("#reportCommitTime").val()=="") {
					win.errorAlert("报告提交时间不能为空");
					return false;
				}
				if($("#reportReviewTime").val()=="") {
					win.errorAlert("报告复核通过时间不能为空");
					return false;
				}
				if($("#reportFinalExaminer").val()=="") {
					win.errorAlert("报告最终审批人不能为空");
					return false;
				}
				if($("#investigateFindOtherInfraction").val()=="") {
					win.errorAlert("调查发现其他违规事件不能为空");
					return false;
				}
				if($("#isInvolveAbarbeitung").val()=="") {
					win.errorAlert("是否涉及整改不能为空");
					return false;
				}
				if($("#isInvolveAbarbeitung").val()=="1") {
					if($("#punishReportNo").val()=="") {
						win.errorAlert("处分问责呈报公文编号不能为空");
						return false;
					}
					if($("#abarbeitungNoticeTime").val()=="") {
						win.errorAlert("整改通知书下发时间不能为空");
						return false;
					}
					if($("#punishReportNo2").val()=="") {
						win.errorAlert("处分问责办文编号不能为空");
						return false;
					}
					if($("#isAbarbeitungAccept").val()=="") {
						win.errorAlert("是否整改验收不能为空");
						return false;
					}
					if($("#isAbarbeitungAccept").val()=="1") {
						if($("#abarbeitungOfficer").val()=="") {
							win.errorAlert("整改责任人不能为空");
							return false;
						}
						if($("#abarbeitungPassTime").val()=="") {
							win.errorAlert("整改验收通过时间不能为空");
							return false;
						}
						if($("#requireAbarbeitungTime").val()=="") {
							win.errorAlert("要求整改完成时间不能为空");
							return false;
						}
						if($("#abarbeitungAccepter").val()=="") {
							win.errorAlert("整改验收人不能为空");
							return false;
						}
						if($("#suggestWorkCondition").val()=="") {
							win.errorAlert("建议问责落实情况不能为空");
							return false;
						}
						if($("#abarbeitungResult").val()=="") {
							win.errorAlert("整改结果不能为空");
							return false;
						}
					}
				}
			}
			return true;
		};	
		$("#commit-1").click(function(){
			if(!checkcommit() || !vaild.all()) {
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var riskControlAccountInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/riskcontrol/account/saveOrUpdate";
			$.post(url, riskControlAccountInfo, function(data) {
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
			var riskControlAccountInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/riskcontrol/account/saveOrUpdateAndReported";
			$.post(url, riskControlAccountInfo, function(data) {
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
		$(document).ready(function() {
			var isFollowupInvestigation = $("#isFollowupInvestigation").val();
			var isInvolveAbarbeitung = $("#isInvolveAbarbeitung").val();
			var isAbarbeitungAccept = $("#isAbarbeitungAccept").val();
			if(isFollowupInvestigation == 1){
				$("#jbdcnr").show();
				$("#hgdcapyjj").show();
				$("#wkzdcyy").hide();
				if(isInvolveAbarbeitung == 1){
					$("#zgqk").show();
				}else{
					$("#zgqk").hide();
				}
				if(isAbarbeitungAccept == 1){
					$("#sfzgys").show();
				}else{
					$("#sfzgys").hide();
				}
			}else{
				$("#jbdcnr").hide();
				$("#hgdcapyjj").hide();
				$("#sfzgys").hide();
				$("#zgqk").hide();
			}
		});
	</script>
</html>
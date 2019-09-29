<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
	td {
	overflow:hidden; 
	white-space:nowrap;
	 text-overflow:ellipsis;
}
</style>
<h3>
	<span class="glyphicon glyphicon-pencil"></span>合同列表清单

</h3>

<form:form id="contractMonthListMsgPageFrom"
	modelAttribute="contractMonthListMsgPage">

	<div class="tablebox">
		<div style="overflow-x: scroll; overflow-y: auto; width:100%;">
		<table style="width:500%;table-layout: fixed;">
			<tr class="table_header" >
				<th>序号</th>
				<th>核心企业</th>
				<th>专业公司</th>
				<th>合同公文号</th>
				<th>合同名称</th>
				<th>我方合同主体</th>
				<th>对方合同主体</th>
				<th>是否属于集团内部企业间合同</th>
				<th>项目名称</th>
				<th>合同类型</th>
				<th>合同类别</th>
				<th>行业领域</th>
				<th>合同履行跟踪人</th>
				<th>合同承办部门</th>
				<th>合同标的金额</th>
				<th>是否属于重大合同</th>
				<th>主要内容</th>
				<th>合同主要 履约节点</th>
				<th>法务 审核人</th>
				<!-- <th>合同 签订日期</th> -->
				<th>生效日期</th>
				<th>到期日期</th>
				<th>履约阶段</th>
				<th>是否存在违约/异常</th>
				<th>我方违约情况</th>
				<th>逾期违约条款约定</th>
				<th>逾期应付(6个月以下)</th>
				<th>逾期应付(6个月~1年)</th>
				<th>逾期应付(1年~2年)</th>
				<th>逾期应付(2年~3年)</th>
				<th>逾期应付(3年以上)</th>
				<th>逾期应付款总额</th>
				<th>应付违约金总额</th>
				<th>对方违约情况</th>
				<th>逾期应收(6个月以下)</th>
				<th>逾期应收(6个月~1年)</th>
				<th>逾期应收(1年~2年)</th>
				<th>逾期应收(2年~3年)</th>
				<th>逾期应收(3年以上)</th>
				<th>逾期应收款总额</th>
				<th>当期坏账损失</th>
				<th>对方违约产生的违约金总额</th>
				<th>是否涉诉</th>
				<th>涉诉案件标的金额</th>
				<th>风险等级</th>
				<th>是否属于重点关注合同</th>
				<th>风险描述</th>
				<th>风险成因</th>
				<th>风险类型</th>
				<th>预警日期及形式</th>
				<th>风险应对策略</th>
				<th>风险应对措施</th>
				<th>风险处置 方案及进展</th>
				<th>风险是否排除及排除情况</th>
				<th>业务责任人</th>
				<th>法务责任人</th>
				<th>备注</th>
				
			</tr>

			<c:if test="${!empty requestScope.msgPage.list }">
				<c:set var="recordNumber"
					value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
				<c:forEach items="${ requestScope.msgPage.list}"
					var="resultList" varStatus="status">
					<tr>
						<!-- 序号 -->
						<td style="text-align: center;">${recordNumber+ status.index
							+ 1 }</td>
						<!-- 所属核心企业 -->
						<td>${resultList.contractBelongCompany }</td>
						<!-- 所在实体企业 -->
						<td title="${resultList.contractSubordinateCompany }">${resultList.contractSubordinateCompany }</td>
						<!-- 合同公文号 -->
						<td>${resultList.contractDocumentNo }</td>
						<!-- 合同名称 -->
						<td title="${resultList.contractName }">${resultList.contractName }</td>
						<!-- 我方合同主体 -->
						<td title="${resultList.ourContractSubject }">${resultList.ourContractSubject }</td>
						<!-- 对方合同主体 -->
						<td title="${resultList.otherContractSubject }">${resultList.otherContractSubject }</td>
						<!-- 集团内部企业间合同 -->
						<td><c:if test="${resultList.isCom}">是</c:if> <c:if
								test="${!resultList.isCom}">否</c:if></td>
						<!-- 项目名称 -->
						<td title="${resultList.projectName }">${resultList.projectName }</td>
						<!-- 合同类型 -->
						<td title="${resultList.contractTypeText }">${resultList.contractTypeText }</td>
						<!-- 合同类别 -->
						<td title="${resultList.contractKindText }">${resultList.contractKindText }</td>
						<!-- 行业领域-->
						<td title="${resultList.industrySector }">${resultList.industrySector }</td>
						<!-- 履行跟踪人-->
						<td>${resultList.trackingMan }</td>
						<!-- 合同承办部门-->
						<td title="${resultList.undertakeDepartment }">${resultList.undertakeDepartment }</td>
						<!-- 合同标的总额(万元) -->
						<td>${resultList.contractTotalAmount }</td>
						<!-- 是否属于重大合同 -->
						<td><c:if test="${resultList.isMajorContract}">是</c:if> <c:if
								test="${!resultList.isMajorContract}">否</c:if></td>
						<!-- 合同主要内容  显示20个字符，超出部门显示“……”-->
						<td title="${resultList.contractContent}"><c:choose>
								<c:when test="${fn:length(resultList.contractContent) > 20}">
									<c:out value="${fn:substring(testStr, 0, 14)}......" />
								</c:when>
								<c:otherwise>
									<c:out value="${resultList.contractContent}" />
								</c:otherwise>
							</c:choose></td>
						<!-- 合同主要履约节点-->
						<td title="${resultList.contractNode }">${resultList.contractNode }</td>
						<!-- 法务审核人 -->
						<td>${resultList.legalPerson }</td>
						<%-- <!-- 合同签订日期-->
						<td>${resultList.contractSignDate }</td> --%>
						<!-- 生效日期-->
						<td>${resultList.contractEffectiveDate }</td>
						<!-- 到期日期-->
						<td>${resultList.contractDeadlineDate }</td>
						<!-- 履约阶段-->
						<td>${resultList.performancePhase }</td>
						<!-- 是否存在违约/异常 -->
						<td><c:if test="${resultList.isDefault}">是</c:if> <c:if
								test="${!resultList.isDefault}">否</c:if></td>
						<!-- 我方违约情况 -->
						<td title="${resultList.ourDefault }">${resultList.ourDefault }</td>
						<!-- 逾期违约条款约定 -->
						<td title="${resultList.overdueDefaultClauseAgreement }">${resultList.overdueDefaultClauseAgreement }</td>
						<!-- 逾期应付(6个月以下)-->
						<td>${resultList.overduePay1 }</td>
						<!-- 逾期应付(6个月~1年)-->
						<td>${resultList.overduePay2 }</td>
						<!-- 逾期应付(1年~2年)-->
						<td>${resultList.overduePay3 }</td>
						<!-- 逾期应付(2年~3年)-->
						<td>${resultList.overduePay4 }</td>
						<!-- 逾期应付(3年以上)-->
						<td>${resultList.overduePay5 }</td>
						<!--我方逾期应付款总额-->
						<td>${resultList.ourOverPay }</td>
						<!--我方违约产生的违约金总额-->
						<td>${resultList.ourDefaultPay }</td>
						<!-- 对方违约情况-->
						<td title="${resultList.otherDefault }">${resultList.otherDefault }</td>
						<!-- 逾期应收(6个月以下)-->
						<td>${resultList.overdueReceivables1 }</td>
						<!-- 逾期应收(6个月~1年)-->
						<td>${resultList.overdueReceivables2 }</td>
						<!-- 逾期应收(1年~2年)-->
						<td>${resultList.overdueReceivables3 }</td>
						<!-- 逾期应收(2年~3年)-->
						<td>${resultList.overdueReceivables4 }</td>
						<!-- 逾期应收(3年以上)-->
						<td>${resultList.overdueReceivables5 }</td>
						<!--逾期应付款总额-->
						<td>${resultList.otherOverPay }</td>
						<!--当期坏账损失-->
						<td>${resultList.currentBadLosses }</td>
						<!--对方违约产生的违约金总额-->
						<td>${resultList.otherDefaultPay }</td>
						<!-- 是否涉诉 -->
						<td><c:if test="${resultList.isInvolving}">是</c:if> <c:if
								test="${!resultList.isInvolving}">否</c:if></td>
						<!-- 涉诉案件标的金额-->
						<td>${resultList.involvingAccount }</td>
						<!-- 风险等级-->
						<td title="${resultList.riskGrade }">${resultList.riskGrade }</td>
						<!-- 是否属于重点关注合同-->
						<td><c:if test="${resultList.isFocus}">是</c:if> <c:if
								test="${!resultList.isFocus}">否</c:if></td>
						<!-- 风险描述-->
						<td title="${resultList.riskDescription }">${resultList.riskDescription }</td>
						<!-- 风险成因-->
						<td title="${resultList.riskCause }">${resultList.riskCause }</td>
						<!-- 风险类型-->
						<td>${resultList.riskType }</td>
						<!-- 预警日期及形式-->
						<td title="${resultList.warningDateForm }">${resultList.warningDateForm }</td>
						<!-- 风险应对策略-->
						<td title="${resultList.riskSteategy }">${resultList.riskSteategy }</td>
						<!-- 风险应对措施-->
						<td title="${resultList.riskCountermeasures }">${resultList.riskCountermeasures }</td>
						<!--风险处置方案及进展-->
						<td title="${resultList.riskManage }">${resultList.riskManage }</td>
						<!--风险是否排除及排除情况-->
						<td title="${resultList.riskExcluded }">${resultList.riskExcluded }</td>
						<!--业务责任人-->
						<td>${resultList.businessOwner }</td>
						<!--法务责任人-->
						<td>${resultList.legalResponsibilities }</td>
						<!--备注-->
						<td title="${resultList.remark }">${resultList.remark }</td>
						
				</c:forEach>
			</c:if>
			<c:if test="${empty requestScope.msgPage.list}">
				<tr>
					<td colspan="24" align="center">查询无记录</td>
				</tr>
			</c:if>
		</table>
		</div>
		<jsp:include page="/views/system/page.jsp" />
	</div>

</form:form>
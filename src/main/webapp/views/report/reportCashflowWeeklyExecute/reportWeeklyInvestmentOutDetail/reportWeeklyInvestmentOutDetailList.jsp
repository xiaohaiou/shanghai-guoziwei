<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox" style="overflow-x: auto">
	<table style="width: 250em">
		<tr class="table_header" id="reportWeeklyInvestmentOutDetailTableTr">
			<th>序号</th>
			<!-- <th>成员公司</th>
			<th>所属核心企业</th> -->
			<th>支付项目名称</th>
			<th>支付金额(万元)</th>
			<th>支付日期</th>
			<th>支付币种</th>
			<th>专项资金保障方案</th>
			<th>投资类型</th>
			<th>操作</th>
		</tr>
		<c:if test="${not empty requestScope.msgPage.list }">
			<c:forEach items="${requestScope.msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(requestScope.msgPage.pageNum-1)*5+status.count }</td>
					<%-- <td>${node.memberCompName }</td>
					<td>${node.coreCompName }</td> --%>
					<td>
						<input type="text" id="payProjectName" value="${node.payProjectName }" check="NotEmpty_string_._._._._." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyInvestmentOutDetail',this)">
					</td>
					<td>
						<input type="text" id="payAmount" value="${node.payAmount }" check="NotEmpty_double_16_2_0+_。_." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyInvestmentOutDetail',this)">
					</td>
					<td>
						<input class="w25 date4" type="text" id="payDate" readonly="true" check="NotEmpty_string_._._._._." value="${node.payDate }" />
					</td>
					<td>
						<select id="currency" onchange="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyInvestmentOutDetail',this)">
							<c:forEach items="${currencyKind }" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id == node.currency }">selected</c:if>>${sys.description }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="text" id="specialFundSupportPlan" value="${node.specialFundSupportPlan }" check="NotEmpty_string_._._._._." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyInvestmentOutDetail',this)">
					</td>
					<td>
						<select id="investType" onchange="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyInvestmentOutDetail',this)">
							<c:forEach items="${investType }" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id == node.investType }">selected</c:if>>${sys.description }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<a class="btn btn-primary model_btn_ok" onclick="del('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyInvestmentOutDetail')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="8" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
</div>
<c:if test="${not empty requestScope.msgPage.list}">
	<jsp:include page="../page.jsp"/>
</c:if>
<script type="text/javascript" src="<c:url value="/js/vailds.js"/>" charset="utf-8"></script>
<script type="text/javascript">
	$(' input.date4').jeDate(
		{
			format:"YYYY-MM-DD",
			choosefun:function(elem, val, date) {changeDate4(val,$(elem).closest('tr').find('td')[0].innerText);},
				clearfun:function(elem, val) {changeDate4('',$(elem).closest('tr').find('td')[0].innerText);},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {changeDate4(val,$(elem).closest('tr').find('td')[0].innerText);}, 
		}
	)
	function changeDate4(val,text){
		 var obj = {};
		 obj.id="payDate";
		 obj.value=val;
		 modifyEntity(text,'reportWeeklyInvestmentOutDetail',obj);
	}
</script>

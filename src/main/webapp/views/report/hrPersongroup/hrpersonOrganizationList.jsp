<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox" style="overflow-x: auto">
	<table style="width: 250em">
		<tr class="table_header" id="nodeTableTr">
			<th>序号</th>
			<th>核心公司</th>
			<th>公司名</th>
			<th>岗位</th>
			<th>所属岗位类别</th>
			<th>【其他】工作类别备注栏</th>
			<th>干部/员工</th>
			<th>管理级别</th>
			<th>编制人数A</th>
			<th>实际在岗人数B</th>
			<th>在岗人员姓名</th>
			<th>实际级别</th>
			<th>超编+/缺编-(A-B)</th>
			<th>备注</th>
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td style="text-align:center;">${node.businesscompanyName }</td>
					<td style="text-align:center;">${node.companyname }</td>
					<td style="text-align:center;">${node.post }</td>
					
					<td style="text-align:center;">${node.postkindName }</td>
					<td style="text-align:center;">${node.otherWorkRemark }</td>
					
					<td style="text-align:center;">${node.postStatusName }</td>
					<td style="text-align:center;">${node.manageLevelName }</td>
					
					<td style="text-align:center;">${node.numberPeopleA }</td>
					<td style="text-align:center;">${node.actualNumberPeopleB }</td>
					
					<td style="text-align:center;">${node.nameActualPeople }</td>
					<td style="text-align:center;">${node.actualLevelName }</td>
					
					<td style="text-align:center;">${node.superLack }</td>
					<td style="text-align:center;">${node.remark }</td>
					
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="28" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
	
</div>	
<c:if test="${not empty requestScope.msgPage.list}">
		<jsp:include page="page.jsp"/>
	</c:if>
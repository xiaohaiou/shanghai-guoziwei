<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox" style="overflow-x: auto">
	<table style="width: 250em">
		<tr class="table_header" id="nodeTableTr">
			<th>序号</th>
			<th>公司全称</th>
			<th>岗位</th>
			<th>所属岗位类别</th>
			<th>【其他】工作类别备注栏</th>
			<th>干部/员工</th>
			<th>管理级别</th>
			<th>姓名</th>
			<th>性别</th>
			<th>出生年月日</th>
			<th>年龄</th>
			<th>海航工龄</th>
			<th>现岗位定岗时间</th>
			<th>学历</th>
			<th>毕业院校</th>
			<th>院校类别</th>
			<th>学习形式</th>
			<th>院校是否达标</th>
			<th>专业全称</th>
			<th>是否为财经专业</th>
			<th>财务职称或资格最高级别</th>
			<th>职称是否达标</th>
			<th>财务专业技术职称及资格全称（初级及以上）</th>
			<th>英语水平</th>
			<th>英语是否达标</th>
			<th>【其他】英语水平备注栏</th>
			<th>工作地点</th>
			<th>是否有境外留学或工作经历</th>
			<th>业态公司</th>
		</tr>
		<c:if test="${not empty msgPage.list }">
			<c:forEach items="${msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(msgPage.pageNum-1)*10+status.count }</td>
					<td style="text-align:left;">${node.companyname }</td>
					<td style="text-align:left;">${node.post }</td>
					
					<td style="text-align:left;">${node.postkindName }</td>
					<td style="text-align:left;">${node.otherWorkRemark }</td>
					
					<td style="text-align:left;">${node.postStatusName }</td>
					<td style="text-align:left;">${node.manageLevelName }</td>
					
					<td style="text-align:left;">${node.name }</td>
					<td style="text-align:left;">${node.sexName }</td>
					
					<td style="text-align:left;">${node.birthday }</td>
					<td style="text-align:left;">${node.age }</td>
					
					<td style="text-align:left;">${node.workage }年</td>
					<td style="text-align:left;">${node.setupTime }</td>
					
					<td style="text-align:left;">${node.educationName }</td>
					<td style="text-align:left;">${node.school }</td>
					
					<td style="text-align:left;">${node.schoollevelName }</td>
					
					<td style="text-align:left;">${node.learnfunName }</td>
					<td style="text-align:left;">${node.schoolqualifiedName }</td>
					
					<td style="text-align:left;">${node.major }</td>
					<td style="text-align:left;">${node.isfinanceName }</td>
					
					<td style="text-align:left;">${node.financialtitleName }</td>
					<td style="text-align:left;">${node.titlequalifiedName }</td>
					
					<td style="text-align:left;">${node.titlename }</td>
					<td style="text-align:left;">${node.englishlevelName }</td>
					
					<td style="text-align:left;">${node.englishqualifiedName }</td>
					<td style="text-align:left;">${node.englishlevelremark }</td>
					
					<td style="text-align:left;">${node.workplace }</td>
					<td style="text-align:left;">${node.overseasexperienceName }</td>
					
					<td style="text-align:left;">${node.businesscompanyName }</td>
					
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
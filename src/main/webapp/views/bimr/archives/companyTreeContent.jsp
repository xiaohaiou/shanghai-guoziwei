<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<style>
	td {
	overflow:hidden; 
	white-space:nowrap;
	 text-overflow:ellipsis;
}
</style>
<div>
<c:if test="${not empty company }">
<h3 class="data_title1">企业基本信息</h3>
<div class="model_part">
	<label class="w20">企业名称:</label> 
	<label class="w25 setleft">${company.vcFullName}</label> 
	<input type="hidden" id="compId" value="${company.nnodeId}"/>
	<!-- <label class="w20">企业类型:</label> 
	<label class="w25 setleft"></label>
	<label class="w20">企业注册资本:</label> 
	<label class="w25 setleft"></label> 
	<label class="w20">法人代表:</label> 
	<label class="w25 setleft"></label> 
	<label class="w20">成立日期:</label> 
	<label class="w25 setleft"></label> 
	<label class="w20">企业地址:</label> 
	<label class="w25 setleft"></label> -->
</div>
<h3 class="data_title1">涉及审计项目情况</h3>
<div class="tablebox" id="companyContentAuditp">
	
</div>
<h3 class="data_title1">涉及合规调查情况</h3>
<div class="tablebox" id="companyContentCompliance">
	
</div>
 <h3 class="data_title1">法务管理情况-案件（民事）</h3>
<div class="tablebox" id="companyContentCivilcaseWeek">
	
</div>
<h3 class="data_title1">法务管理情况-案件（刑事）</h3>
<div class="tablebox" id="companyContentCriminalcaseWeek">
	
</div>
<h3 class="data_title1">法务管理情况-合同</h3>
<div class="tablebox" id="companyContentContractMonth">
	
</div>
<h3 class="data_title1">报告风险事件情况</h3>
<div class="tablebox" id="companyContentRisk">
	
</div>
</c:if>
<script type="text/javascript">
	function initNodeList(v,pageNums){
		var compId=$("#compId").val();
			$.ajax({
				url:"${pageContext.request.contextPath}/bimr/archives/companyTree/"+v+"?pageNums="+pageNums+"&compId="+compId,
				type:"POST",
				dataType:"text",
				async:false,
				success:function(data){
					$("#"+v).children().remove();
					$("#"+v).append(data);
				}
			});	
		}
		initNodeList('companyContentAuditp',1);//初始化内审列表
		initNodeList('companyContentCompliance',1);//初始化合规列表
		initNodeList('companyContentCivilcaseWeek',1);//初始化民事案件列表
		initNodeList('companyContentCriminalcaseWeek',1);//初始化刑事案件列表
		initNodeList('companyContentContractMonth',1);//初始化合同列表
		initNodeList('companyContentRisk',1);//初始化风险列表
	</script>
</div>
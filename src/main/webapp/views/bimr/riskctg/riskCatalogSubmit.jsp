<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>上报风险目录</title>
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
		<span class="glyphicon glyphicon-pencil"></span>上报风险目录
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">一级目录:</label> 
				<label class="w25 setleft">${entities[0].name}</label>
				<label class="w20">一级目录定义:</label> 
				<label class="w25 setleft">${entities[0].define}</label>
				<label class="w20">二级目录:</label>
				<label class="w25 setleft">${ entities[1].name}</label>
				<label class="w20">二级目录定义:</label>
				<label class="w25 setleft">${ entities[1].define}</label>
				<label class="w20">三级目录:</label>
				<label class="w25 setleft">${entitiesCount == 3 ? entities[2].name: ""}</label>
				<label class="w20">三级目录定义:</label>
				<label class="w25 setleft">${entitiesCount == 3 ? entities[2].define: ""}</label>
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${entitiesCount == 3 ? entities[2].createPersonName: entities[1].createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${entitiesCount == 3 ? entities[2].createDate: entities[1].createDate}</label>
				<label class="w20">数据上报人:</label>
				<label class="w25 setleft">${entitiesCount == 3 ? entities[2].submitAuditPersonName: entities[1].submitAuditPersonName}</label>
				<label class="w20">上报时间:</label>
				<label class="w25 setleft">${entitiesCount == 3 ? entities[2].submitAuditDate: entities[1].submitAuditDate}</label>
			</div>
			
			<h3 class="data_title1">审核意见列表</h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:3%">序号</th>
						<th style="width:10%">审核人</th>
						<th style="width:15%">审核时间</th>
						<th style="width:72%">审核意见</th>
					</tr>
					<c:forEach items="${ requestScope.auditList}" var="l" varStatus="status">
					  <tr>
					   <td style="text-align: center;">${status.index + 1 }</td>
					  <td style="text-align: left;">${l.createPersonName }</td>
					  <td style="text-align: center;">${l.createDate }</td>
					  <td style="text-align: left;">
					  <c:if test="${l.examresult == 0}">审核退回。</c:if>
					  <c:if test="${l.examresult == 1}">审核通过。</c:if>
					  ${l.examinestr }
					  </td>
					  </tr>
					</c:forEach>
				</table>
			</div>
			</br>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_save" id="btn_submit">上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp" />
	</form:form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">
	//关闭弹窗
	$(".model_btn_close").click(function() {
		parent.mclose();
		return false;
	});
	$(".icon-guanbi").click(function () {
		parent.mclose();
		return false;
	});
	
    function commit1(){
		
		if(!vaild.all()){
			return false;
		}
		
		$.blockUI({ message: '提交中', css: { width: '275px' } });
		var id = "${entitiesCount == 3 ? entities[2].id: entities[1].id}";
		var params = {id: id};
	
		var url = "${pageContext.request.contextPath}/bimr/riskCatalog/submitAudit";
		$.post(url, params, function(data) {
			$.unblockUI();
			if(data.result==true){
				win.successAlert('上报成功！',function(){
					parent.mclose();
					parent.location.reload();	
			    });
			}else{
			    win.errorAlert(data.exceptionStr);
			}
		});
	}
	
	$("#btn_submit").click(function(){
		commit1();
		return false;
	});
	
</script>
</html>
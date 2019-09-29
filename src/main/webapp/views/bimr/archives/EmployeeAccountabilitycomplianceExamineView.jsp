<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看合规调查</title>
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
		<span class="glyphicon glyphicon-pencil"></span>查看合规调查
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
			<div class="model_part">
			<input type="hidden" value="${ requestScope.projectId}" id="projectId"/>
				<label class="w20">举报投诉、专项调查项目名称:</label>
				<label class="w25 setleft">${ entity.projectName}</label>
				
				<label class="w20">调查类型:</label>
				<label class="w25 setleft">${ entity.investigationTypeName}</label>
				
				<label class="w20">调查承办部门:</label>
				<label class="w25 setleft">${ entity.investigationDep}</label>
				
				<label class="w20">调查涉及企业名称:</label>
				<label class="w25 setleft">${ entity.compName}</label>
				
				<label class="w20">调查涉及经营管理事项:</label>
				<label class="w25 setleft">${ entity.itemName}</label>
				
				<label class="w20">调查来源:</label>
				<label class="w25 setleft">${ entity.investigationFromName}</label>
				
				<label class="w20">是否是集团内部投诉人:</label>
				<label class="w25 setleft">${ entity.isInside==0?"否":"是"}</label>
				
				<label class="w20">是否匿名:</label>
				<label class="w25 setleft">${ entity.isAnonymous==0?"否":"是"}</label>
				
				<label class="w20">举报投诉收到时间:</label>
				<label class="w25 setleft">${ entity.accusationTime}</label>
				
				<label class="w20">调查交办领导:</label>
				<label class="w25 setleft">${ entity.assignLeader}</label>
				
				<label class="w20">调查负责人:</label>
				<label class="w25 setleft">${ entity.responsiblePerson}</label>
				<br>
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ entity.createPersonName}</label>
				
				<label class="w20">数据创建时间:</label>
				<label class="w25 setleft">${ entity.createDate}</label>
			</div>
			
			
			<h3 class="data_title1">调查涉及人员</h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th >序号</th>
						<th >调查涉及人员</th>
						<th >现任职务</th>
						<th >员工编号</th>
					</tr>
					<c:if test="${!empty requestScope.personList }">
						<c:forEach items="${ requestScope.personList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: center;">${l.employeeName }</td>
								<td style="text-align: center;">${l.employeePostion }</td>
								<td style="text-align: center;">${l.employeeNumber}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.personList}">
						<tr>
							<td colspan="4" align="center">查询无记录</td>
						</tr>
					</c:if>
				</table>
			</div>
			<h3 class="data_title1">调查基本信息</h3>
			<div class="model_part">
				<label class="w20">调查启动时间:</label>
				<label class="w25 setleft">${ entity.startTime}</label>
				
				<label class="w20">现场调查结束时间:</label>
				<label class="w25 setleft">${ entity.endTime}</label>
			</div>
			
			
			<h3 class="data_title1">举报投诉反映问题线索核查情况(举报投诉调查必填) </h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:3%">序号</th>
						<th style="width:20%">举报投诉反映问题线索</th>
						<th style="width:7%">问题线索是否属实</th>
						<th style="width:23%">具体事实情况</th>
						<th style="width:22%">操作</th>
					</tr>
					<c:if test="${!empty requestScope.situationList0 }">
						<c:forEach items="${ requestScope.situationList0}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: left;">${l.problem }</td>
								<td style="text-align: center;">${l.isTruthName}</td>
								<td style="text-align: left;">${l.truth }</td>
								<td>
									<a href="javascript:void(0)"
											onclick="mload('${mapurl}/viewsituation?situationId=${l.id }&dataType=0')">查看</a>
									</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.situationList0}">
						<tr>
							<td colspan="5" align="center">查询无记录</td>
						</tr>
					</c:if>
				</table>
			</div>
			
			<h3 class="data_title1">待调查事项核查情况(专项合规检查必填) </h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:3%">序号</th>
						<th style="width:20%">待调查具体事项</th>
						<th style="width:7%">是否需要问责</th>
						<th style="width:23%">调查确认情况</th>
						<th style="width:22%">操作</th>
					</tr>
					<c:if test="${!empty requestScope.situationList1 }">
						<c:forEach items="${ requestScope.situationList1}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: left;">${l.problem}</td>
								<td style="text-align: center;">${l.isAccountability==0?"否":"是" }</td>
								<td style="text-align: left;">${l.truth }</td>
								<td>
									<a href="javascript:void(0)"
										onclick="mload('${mapurl}/viewsituation?situationId=${l.id }&dataType=1')">查看</a>
								
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.situationList1}">
						<tr>
							<td colspan="5" align="center">查询无记录</td>
						</tr>
					</c:if>
				</table>
			</div>
			
			
			<h3 class="data_title1">调查发现其他违规违纪事件情况  </h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:3%">序号</th>
						<th style="width:20%">其他违规违纪事项描述</th>
						<th style="width:7%">是否需要问责</th>
						<th style="width:23%">调查确认情况</th>
						<th style="width:22%">操作</th>
					</tr>
					<c:if test="${!empty requestScope.situationList2 }">
						<c:forEach items="${ requestScope.situationList2}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: left;">${l.problem}</td>
								<td style="text-align: center;">${l.isAccountability==0?"否":"是" }</td>
								<td style="text-align: left;">${l.truth }</td>
								<td>
									<a href="javascript:void(0)"
										onclick="mload('${mapurl}/viewsituation?situationId=${l.id }&dataType=2')">查看</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.situationList2}">
						<tr>
							<td colspan="5" align="center">查询无记录</td>
						</tr>
					</c:if>
				</table>
			</div>
			
			<h3 class="data_title1">审计问责人员  </h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:3%">序号</th>
						<th >项目名称</th>
						<th >类型</th>
						<th >事项</th>
						<th >被问责人员</th>
						<th >操作</th>
					</tr>
					<c:if test="${!empty requestScope.compliancePersionAbilities }">
						<c:forEach items="${ requestScope.compliancePersionAbilities}" var="l" varStatus="status">
						
							 <tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								 <td style="text-align: left;">${l[0]}</td> 
								<c:if test="${l[1]==0 }"><td style="text-align: left;">举报投诉反映问题线索核查情况</td></c:if>
								<c:if test="${l[1]==1 }"><td style="text-align: left;">待调查事项核查情况</td></c:if>
								<c:if test="${l[1]==2 }"><td style="text-align: left;">调查发现其他违规违纪事件情况</td></c:if>
								<td style="text-align: left;">${l[2] }</td>
								<td style="text-align: left;">${l[3] }</td>
							</tr> 
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.suggestList}">
						<tr>
							<td colspan="5" align="center">查询无记录</td>
						</tr>
					</c:if>
				</table>
			</div>
			
			<c:if test="${!empty examineList}">
					<div class="model_part">
						<table>
						<caption>历史审核记录</caption>
						<tr>
							<th width="15%">审核时间</th>
							<th width="10%">审核人</th>
							<th width="10%">审核结果</th>
							<th width="60%">审核意见</th>
						</tr>
							<c:forEach items="${examineList}" var="examine" varStatus="status">
								<tr>
									<td>${examine.createDate }</td>
									<td>${examine.createPersonName }</td>
									<c:if test="${examine.examresult eq '50116'}">
										<td>审核通过</td>
									</c:if>
									<c:if test="${examine.examresult eq '50117'}">
										<td>审核不通过</td>
									</c:if>
									<td>${examine.examinestr }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
				<c:if test="${empty showtype}">
				<div class="model_part">
					
					<label class="w20" style="vertical-align: top;"><span class="required"> * </span>审核意见:</label>
					<textarea  rows="3" cols="50" name="examStr" id="examStr" check="NotEmpty_string_50_._._._."></textarea>
				</div>
				<div class="model_btn">
					<button class="btn btn-primary model_btn_ok" id="commit-1">通过</button>
					<button class="btn btn-primary model_btn_ok" id="commit-2">退回</button>
				
				</div>
				</c:if>
			<div class="model_btn">
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
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		function changeStatus(status){
		var projectId=$("#projectId").val();
			if(!vaild.all())
			{
				return false;
			}
			var url = "${pageContext.request.contextPath}/bimr/archives/companyTree/saveEmployeeAccountabilityComplianceExamine?examResult="+status+"&projectId="+projectId;
			//var formData = new FormData($("#form2")[0]);
			var params = $('#form2').serialize();
			$.post(url, params ,function(data) {
				
				if(data=="success"){
					win.successAlert('审核成功！',function(){
						parent.location.reload(true);
						parent.mclose();
						
					});
				}else{
					win.errorAlert('审核失败！');
				}
			
			});
				
			return false;
		}
		
		$("#commit-1").click(function(){
			return changeStatus(50116);//通过
		});
		
		$("#commit-2").click(function(){
			return changeStatus(50117);//退回
		});
</script>
</html>
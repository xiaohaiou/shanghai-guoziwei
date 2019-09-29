<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>>稽核财务事项信息维护</title>
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
		<span class="glyphicon glyphicon-pencil"></span>稽核财务事项信息修改 <i
			class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">年份:</label> <label class="w25 setleft">${
					entity.year}</label> <br> <label class="w20">稽核事项所属单位:</label> <label
					class="w25 setleft">${ entity.compName}</label> <label class="w20">稽核事项所属核心企业:</label>
				<label class="w25 setleft">${ entity.compName}</label>
			</div>
			<h3 class="data_title1">稽核财务事项基本信息</h3>
			<div class="model_part">
				<label class="w20">财务事项类别:</label> <label class="w25 setleft">${
					entity.itemTypeName}</label> <label class="w20">稽核财务事项名称:</label> <label
					class="w25 setleft">${ entity.itemName}</label> <label class="w20">稽核事项负责人:</label>
				<label class="w25 setleft">${ entity.itemPerson}</label> <label
					class="w20">稽核事项开始日期:</label> <label class="w25 setleft">${
					entity.startTime}</label> <label class="w20">稽核事项结束日期:</label> <label
					class="w25 setleft">${ entity.endTime}</label> <br> <label
					class="w20">备注:</label> <label class="w70 setleft">${
					entity.remark}</label>
				<br>
				<c:if test="${!empty entity.fileOne.fileName}">
						<label class="w20">已上传文件:</label>
						<span>
							<a
								href="${pageContext.request.contextPath}/common/download?_url=${entity.fileOne.filePath }&_name=${entity.fileOne.fileName }"
								data-original-title="" title="">${entity.fileOne.fileName }
							</a>
						</span>
				</c:if>
			</div>
			<h3 class="data_title1">稽核财务事项发现问题 <a href="javascript:void(0)"
											onclick="mload('${mapurl}/addOrModifyProblem?op=add&inspectProjectId=${entity.id }')" class="form_btn">新增稽核发现问题</a></h3>
			<div class="tablebox">
				<table id="sample_1_1">
					<tr class="table_header">
						<th style="width:3%">序号</th>
						<th style="width:20%">存在问题</th>
						<th style="width:7%">是否整改</th>
						<th style="width:23%">整改建议措施</th>
						<th style="width:7%">整改时限</th>
						<th style="width:7%">整改跟踪人</th>
						<th style="width:22%">操作</th>
					</tr>
					<c:if test="${!empty requestScope.problemList }">
						<c:forEach items="${ requestScope.problemList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: left;">${l.problem }</td>
								<td style="text-align: left;">${l.isChange==0?"否":"是"}</td>
								<td style="text-align: left;">${l.changeContent }</td>
								<td style="text-align: center;">${l.changeLastTime }</td>
								<td style="text-align: left;">${l.changeFollowPerson }</td>
								<td><c:if
										test="${fn:contains(gzwsession_code,',bimWork_voluntarySh_query,')==true }">
										<a href="javascript:void(0)"
											onclick="mload('${mapurl}/problemview?problemId=${l.id }')">查看</a>
									</c:if> <c:if
										test="${fn:contains(gzwsession_code,',bimWork_voluntarySh_exam,')==true }">
										<a href="javascript:void(0)"
											onclick="mload('${mapurl}/addOrModifyProblem?op=modify&problemId=${l.id }')">修改</a>
									</c:if> <c:if
										test="${fn:contains(gzwsession_code,',bimWork_bmfxsjsjsb_delete,')==true }">
										<a href="javascript:;"
											onclick="del('${l.id}')">删除</a>
									</c:if></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			
			<h3 class="data_title1">领导指令</h3>
			<div class="tablebox">
				<table >
					<tr class="table_header">
						<th style="width:3%">序号</th>
						<th style="width:10%">领导姓名</th>
						<th style="width:10%">指示时间</th>
						<th style="width:23%">指示内容</th>
						<th style="width:22%">操作</th>
					</tr>
					<c:if test="${!empty requestScope.orderList }">
						<c:forEach items="${ requestScope.orderList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: center;">${l.orderSenderName }</td>
								<td style="text-align: center;"><fmt:parseDate value="${l.orderDate}" var="orderDate" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${orderDate}" pattern="yyyy-MM-dd"/></td>
								<td style="text-align: left;">${l.orderDescription }</td>
								<td><c:if
										test="${fn:contains(gzwsession_code,',bimWork_voluntarySh_query,')==true }">
										<a href="javascript:void(0)"
											onclick="mload('/shanghai-gzw/inspectproject/orderview?orderId=${l.id }')">查看</a>
									</c:if> <c:if
										test="${fn:contains(gzwsession_code,',bimWork_voluntarySh_exam,')==true }">
										<a href="javascript:void(0)"
											onclick="mload('${mapurl}/orderreport?orderId=${l.id }')">回复</a>
									</c:if> </td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp" />
	</form:form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>"
	charset="utf-8"></script>
<script src="<c:url value="/js/select/bootstrap-select.js"/>"></script>
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
	
	function del(id){
			win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/inspectproject/deleteproblem?problemId=" + id;
					$.post(url, function(data) {
						var commitresult1=JSON.parse(data);
						if(commitresult1.result){
								win.successAlert('删除成功！',function(){
								location.reload();
							});
						}else{
							win.errorAlert('数据已被其他人员操作，此操作失败！',_query);
						}
					
					});
				}
			});
		}
		
		
		var init_fuvk_jq_dataTable = function(id) {
		$(id).dataTable({
			"ordering" : false,
			"language" : {
				"metronicAjaxRequestGeneralError" : "请求错误",
				"aria" : {
					"sortAscending" : ": 升序排列",
					"sortDescending" : ": 降序排列"
				},
				"emptyTable" : "没有相关记录",
				"info" : "第 _START_ 到 _END_ 条记录，共 _TOTAL_ 条",
				"infoEmpty" : "共 0 条",
				"infoFiltered" : "(从 _MAX_ 条记录中检索)",
				"lengthMenu" : " _MENU_",
				"search" : "搜索:",
				"zeroRecords" : "没有相关记录",
				"paginate" : {
					"previous" : "前一页",
					"next" : "下一页",
					"last" : "最后一页",
					"first" : "上一页"
				}
			},
			"dom" : "t r<'row'<'col-md-4 col-sm-4'l i><'col-md-8 col-sm-8'p>>",
			"lengthMenu" : [ [ 5,10, 15, 20 ], [ '5','10', '15', '20' ] ],
			"pagingType" : "bootstrap_full_number"
		})
	};

	$(document).ready(function() {
		
		init_fuvk_jq_dataTable("#sample_1_1");

	});
</script>
</html>
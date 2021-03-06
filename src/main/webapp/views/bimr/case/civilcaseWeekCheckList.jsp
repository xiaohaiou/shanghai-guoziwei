<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>民事案件审核</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		<style type="text/css">
			.search_title {
			    display: inline-block;
			    width: 10em;
			    padding: 0 !important;
			    text-align: right;
			}
			body{
				padding:0;
			}
			h4{
				margin: 20px 40px;
			}
			.table_content {
			    margin: 0 30px;
			}
		</style>
	</head>
	<body class="hn_grey">
		<h4>民事案件审核</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4">案发日期：
				<input  type="text" name="caseDate" id="caseDate" value="${entity.caseDate}" readonly="true" class="time" />
			</span>
			<span class="col-md-4">审核状态：
				<select  name="approvalState" id="approvalState" class="selectpicker" >
					<option value="" <c:if test="${entity.approvalState eq ''}">selected</c:if>>全部</option>
					<option value="0" <c:if test="${entity.approvalState eq '0'}">selected</c:if>>未上报</option>
					<option value="1" <c:if test="${entity.approvalState eq '1'}">selected</c:if>>审核中</option>
					<option value="2" <c:if test="${entity.approvalState eq '2'}">selected</c:if>>审核通过</option>
					<option value="3" <c:if test="${entity.approvalState eq '3'}">selected</c:if>>审核未通过</option>
				</select>
			</span>
			<div class="form_div col-md-12">	
				 <c:if test="${fn:contains(gzwsession_code,',bimWork_msajsh_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">

						<!-- <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export()"> -->
				 </c:if>

			</div>
		</form>
		
		
			<div class="tablebox">
				<table>
					<tr class="table_header">
						
						<th style="width:5%;">序号</th>
						<th style="width:10%;">原告/申请人/第三人</th>
						<th style="width:10%;">被告/被申请人/第三人</th>
						<th style="width:10%;">案件阶段</th>
						<th style="width:10%;">是否重大案件</th>
						
						<th style="width:10%;">涉案金额</th>
						<th style="width:10%;">案件类型</th>
					    <th style="width:8%;">案件编号</th>
						<th style="width:15%;">操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="case" varStatus="status">
							<tr>
								
								<td style="text-align:center;">
									${recordNumber + status.index + 1 }
								</td>
									<td style="text-align:center;">
								   ${case.plaintiff}
								</td>
								<td style="text-align:center;">
									 ${case.defendant}
								</td>
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									<c:if test="${case.mediatingStage eq '1'}">一审</c:if>
									<c:if test="${case.mediatingStage eq '2'}">二审</c:if>
									<c:if test="${case.mediatingStage eq '3'}">再审</c:if>
									<c:if test="${case.mediatingStage eq '4'}">执行</c:if>
									<c:if test="${case.mediatingStage eq '5'}">结案</c:if>
								</td>
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									<c:if test="${case.isMajorCase eq '0'}">否</c:if>
									<c:if test="${case.isMajorCase eq '1'}">是</c:if>
								</td>
								
								<td style="text-align:right;">
									${case.amount}
								</td>
								<td style="text-align:center;">
									<c:if test="${case.type eq '1'}">合同纠纷</c:if>
									<c:if test="${case.type eq '2'}">劳动纠纷</c:if>
									<c:if test="${case.type eq '3'}">侵权纠纷</c:if>
									<c:if test="${case.type eq '4'}">行政纠纷</c:if>
									<c:if test="${case.type eq '5'}">其他纠纷</c:if>
								</td>
							    <td style="text-align:center;">
									${case.caseNum}
								</td>
								<td>
								    <c:if test="${fn:contains(gzwsession_code,',bimWork_msajsh_show,')==true }">
									     <a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/case/viewCivilcase?id=${case.id}')">查看</a>
									</c:if>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_msajsh_examine,')==true }">
										<c:if test="${case.approvalState == 1}">
											<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/case/viewCivilcaseCheck?id=${case.id}')">审核</a>
										</c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="11" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
		function _query() {
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		};
		$("#queryBtn").click(function() {
			if(checkTime($("#doDate").val(),$("#doDate2").val()) == false){
				return false;
			}
			_query();	
		});
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
		
		/* function _export()
		{
		 var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/bimr/case/minshiShExport";
			form.method = "post";
			form.submit();
		} */
		
		
		
		function getId() {
			var ids = document.getElementById('ids').value.split(',');
			var chk_value = [];
			var userid = {};
			for ( var i = 0; i < ids.length; ++i) {
				if (!userid[ids[i]])
					chk_value.push(ids[i]);
				userid[ids[i]] = true;
			}
			$('input[name="checkbox"]:checked').each(function() {
				if (!userid[$(this).val()])
					chk_value.push($(this).val());
			});
			$("#ids").val(chk_value);
		};
		var chknum = 0;
		function chk() {
			if (chknum == 0) {
				$("input[name='checkbox']").prop("checked", true);
				chknum = 1;
			} else {
				$("input[name='checkbox']").prop("checked", false);
				chknum = 0;
			}
		};
		$("#topBtn").click(function() {
			var chk_value = [];
			$('input[name="checkbox"]:checked').each(function() {
				chk_value.push($(this).val());
			});
			if (chk_value.length == 0) {
				parent.win.generalAlert('你还没有选择任何内容！');
			} else {
				parent.win.confirm('确定要置顶？', function(r) {
					if (r) {
						var url = "${pageContext.request.contextPath}/knowledgeBase/top?ids="+ chk_value + "";
						$.post(url, function(data) {
							parent.win.successAlert('置顶成功！');
							window.location.reload();
							$('#all').prop("checked", false);
							$('input[name="checkbox"]:checked').each(
								function() {
									$(this).prop("checked", false);
								});
						});
					}
				});
			}
			return false;
		});
		$("#delTopBtn").click(function() {
			var chk_value = [];
			$('input[name="checkbox"]:checked').each(
					function() {
						chk_value.push($(this).val());
					});
			if (chk_value.length == 0) {
				parent.win.generalAlert('你还没有选择任何内容！');
			} else {
				parent.win.confirm('确定要撤除置顶？',function(r) {
					if (r) {
						var url = "${pageContext.request.contextPath}/knowledgeBase/delTop?ids="+ chk_value+ "";
						$.post(url, function(data) {
							parent.win.successAlert('置顶撤除成功！');
							window.location.reload();
							$('#all').prop("checked", false);
							$('input[name="checkbox"]:checked').each(
								function() {
									$(this).prop("checked",false);
							});
						});
					}
				});
			}
			return false;
		});
	</script>
</html>
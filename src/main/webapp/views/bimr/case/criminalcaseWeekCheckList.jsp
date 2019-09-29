<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>刑事案件审核</title>
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
		<h4>刑事案件审核</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4">案发日期：
				<input  type="text" name="caseDate" value="${entity.caseDate}" readonly="true" class="time" />
			</span>
			<span class="col-md-4">审核状态：
				<select  name="approvalState" class="selectpicker" >
					<option value="" <c:if test="${entity.approvalState eq ''}">selected</c:if>>全部</option>
					<option value="0" <c:if test="${entity.approvalState eq '0'}">selected</c:if>>未上报</option>
					<option value="1" <c:if test="${entity.approvalState eq '1'}">selected</c:if>>审核中</option>
					<option value="2" <c:if test="${entity.approvalState eq '2'}">selected</c:if>>审核通过</option>
					<option value="3" <c:if test="${entity.approvalState eq '3'}">selected</c:if>>审核未通过</option>
				</select>
			</span>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_ssajsh_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
			</div>
		</form>
		
		
			<div class="tablebox">
				<table>
					<tr class="table_header">
						
						<th style="width:5%;">序号</th>
						<th style="width:5%;">犯罪嫌疑人</th>
						<th style="width:10%;">所在单位</th>
						<th style="width:10%;">职务</th>
						<th style="width:10%;">涉嫌罪名</th>
						<th style="width:8%;">涉案金额</th>
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
								   ${case.suspect}
								</td>
								<td style="text-align:center;">
									 ${case.vcCompanyName}
								</td>
								<td style="text-align:center;">
									 ${case.post}
								</td>
								<td style="text-align:center;">
									${case.accusation}
								</td>
								<td style="text-align:center;">
									${case.amount}
								</td>
								<td style="text-align:center;">
									${case.caseNum}
								</td>
							
								<td>
								    <c:if test="${fn:contains(gzwsession_code,',bimWork_ssajsh_show,')==true }">
									    <a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/case/viewCriminalcase?id=${case.id}')">查看</a>
									</c:if>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_ssajsh_examine,')==true }">
										<c:if test="${case.approvalState == 1}">
											<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/case/viewCriminalcaseCheck?id=${case.id}')">审核</a>
										</c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="9" align="center">
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
		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/bimr/case/delCriminalcase?id="+id;
					$.post(url, function(data) {
						if(data == "success") {
							win.successAlert('删除成功！');
							_query();
						}else{
							win.successAlert('该数据已被删除！');
							_query();
						}
					});
				}
			});
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
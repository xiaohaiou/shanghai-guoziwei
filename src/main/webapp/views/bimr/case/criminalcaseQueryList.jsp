<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>刑事案件查询</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
				<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
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
		<h4>刑事案件查询</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4">案发日期：
				<input  type="text" name="caseDate" id=caseDate value="${entity.caseDate}" readonly="true" class="time" />
			</span>
			
			<span class="col-md-4">
				<span class="search_title">案件承办单位: </span>
				<input type="hidden" id="vcCompanyId" name="vcCompanyId" value="${entity.vcCompanyId}" >
				<input name="vcCompanyName" id="vcCompanyName" value="${entity.vcCompanyName}" readonly="readonly">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
			</span>
			
			<span class="col-md-4">涉嫌罪名：
			<input  type="text" name="accusation" id="accusation" value="${entity.accusation}"  />
			</span>
			
			
			
			<div class="form_div col-md-12">	
					<c:if test="${fn:contains(gzwsession_code,',bimWork_xsajcx_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				  </c:if>
				  <c:if test="${fn:contains(gzwsession_code,',bimWork_xsajcx_export,')==true }">
						 <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export()"> 
				  </c:if>
			</div>
		</form>
		
		
			<div class="tablebox">
				<table>
					<tr class="table_header">
						
						<th style="width:5%;">序号</th>
						<th style="width:5%;">犯罪嫌疑人</th>
						<th style="width:10%;">案件承办单位</th>
						<th style="width:10%;">职务</th>
						<th style="width:10%;">涉嫌罪名</th>
						<th style="width:8%;">涉案金额</th>
					<th style="width:8%;">审核状态</th>
						<th style="width:5%;">操作</th>
						
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
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									<c:if test="${case.approvalState eq '0'}">未上报</c:if>
									<c:if test="${case.approvalState eq '1'}">待审核</c:if>
									<c:if test="${case.approvalState eq '2'}">审核通过</c:if>
									<c:if test="${case.approvalState eq '3'}">审核未通过</c:if>
									
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_xsajcx_show,')==true }">
									    <a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/case/viewCriminalcase?id=${case.id}')">查看</a>
								    </c:if>
								    <%-- <c:if test="${fn:contains(gzwsession_code,',bimWork_xsajcx_show,')==true }">
										<c:if test="${case.approvalState == 1}">
											<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/case/viewCriminalcase?id=${case.id}')">审核</a>
										</c:if>
									</c:if> --%>
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
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
	
	var timeoutId;
		$('#vcCompanyName').on('focus click',function(){
			$(this).next('.com_ztree_box').css('display','block')
		}).parent().on('mouseenter',function(){
			clearTimeout(timeoutId)
		}).on('mouseleave',function(){
			clearTimeout(timeoutId)
			timeoutId = setTimeout(function(el){
				$(el).find('.com_ztree_box').css('display','none')
			},3e2,this);
		});
		var zTreeObj;
		var com_ztree_setting = {
			check:{
				enable:false,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			}
		};
		$(function () {
		     //--所有企业数据	
		    var com_data = ${allCompanyTree};
			zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
			//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
			var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = treeObj.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
			var childrenNodes = treeObj.transformToArray(nodes);
			if(childrenNodes[0]!=null){
				treeObj.expandNode(childrenNodes[0],true);
			}
		});
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					$("#vcCompanyId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#vcCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
	
	
	   $('.clear').on('click',function(){
			$(this).siblings("input[name='vcCompanyName']").val("");
			$(this).siblings("input[name='vcCompanyId']").val("");
		});
	
	
	
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
		
		/* 导出 */
		/* var tp=$("#type").val();
		if(tp==null||tp==''){
			alert("请选择案件类型")
			return false;
		} */
		function _export()
		{
		
		 var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/bimr/case/criminalcaseExport";
			form.method = "post";
			form.submit();
		}
		
		
		
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
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>案件周报</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
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
		<h4>案件周报生成</h4>
		<div class="table_content">
		
			<form id="form1" class="row">
				<span class="col-md-4" style="width: 20%;">
					<span class="search_title">案发日期：</span>
					<input  type="text" name="caseDate" id="caseDate" value="${entity.caseDate}" readonly="true" class="time" />
				</span>
				<span class="col-md-4">
					<span class="search_title" style="width: 9em;">案件归属公司：</span>
					<input type="hidden" id="vcCompanyId" name="vcCompanyId" value="${entity.vcCompanyId}" />
					<input name="vcCompanyName" id="vcCompanyName" value="${entity.vcCompanyName}" readonly="readonly" class="w70" style="width: calc(100% - 10em);"/>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:500px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
						</ul>
				   	</div>
			   		<i class="clear iconfont icon-guanbi"></i>
				</span>
				
				<span class="col-md-4" style="width: 20%;">
				<span class="search_title">年份：</span>
						<input class="time" type="text" name="year" id="year1" style="width: 40%;" value="${entity.year}" readonly="true" />
				</span>		
				 <span class="col-md-4" style="width: 20%;"> 
					<span class="search_title">周:</span>
						<select class=" week choose_date" name="week" id="week"  style="width: 30%;">
									<option></option>
								<c:forEach var="num" begin="1" end="53">
									<option <c:if test="${entity.week == num }">selected</c:if>>${num }</option>
								</c:forEach>
				</select>
				 </span>	 
				<div class="form_div col-md-12">
						<c:if test="${fn:contains(gzwsession_code,',bimWork_ajzb_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
						</c:if>
						<c:if test="${fn:contains(gzwsession_code,',bimWork_ajzb_create,')==true }">
						<input type="button" value="生成周报" class="form_btn" id="reportbtn" onclick="mload('${pageContext.request.contextPath}/bimr/case/createReport')">
						</c:if>
						<c:if test="${fn:contains(gzwsession_code,',bimWork_ajzb_bzxz_export,')==true }">
						<input type="button" value="本周新增导出" class="form_btn" id="qrybtn" onclick="_export(1)">
						</c:if>
						<c:if test="${fn:contains(gzwsession_code,',bimWork_ajzb_bnxz_export,')==true }">
						<input type="button" value="本年新增导出" class="form_btn" id="qrybtn" onclick="_export(2)">
						</c:if>
				</div>
			</form>
		
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:10%;">案件编号</th>
						<th style="width:10%;">版本号</th>
						<th style="width:15%;">案件归属单位</th>
						<th style="width:10%;">案件种类</th>
						<th style="width:10%;">案件类型</th>
						<th style="width:10%;">案发省份</th>
						<th style="width:10%;">涉案金额</th>
						<th style="width:10%;">案发日期</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="case" varStatus="status">
							<tr>
								
								<td style="text-align:center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									${case[3]}
								</td>
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									${case[10]}
								</td>
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									${case[5]}
								</td>
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									<c:if test="${case[4] eq '1'}">民事案件</c:if>
									<c:if test="${case[4] eq '2'}">刑事案件</c:if>
									
								</td>
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									<c:if test="${case[4] eq '1'}">
										<c:if test="${case[6] eq '1'}">合同纠纷</c:if>
										<c:if test="${case[6] eq '2'}">劳动纠纷</c:if>
										<c:if test="${case[6] eq '3'}">侵权纠纷</c:if>
										<c:if test="${case[6] eq '4'}">行政纠纷</c:if>
										<c:if test="${case[6] eq '5'}">其他纠纷</c:if>
									</c:if>
									<c:if test="${case[4] eq '2'}">
										<c:if test="${case[6] eq '1'}">一般刑事案件</c:if>
										<c:if test="${case[6] eq '2'}">重大刑事案件</c:if>
										<c:if test="${case[6] eq '3'}">涉外刑事案件</c:if>
									</c:if>
								</td>
								<td style="text-align:center;">
									${case[7]}
								</td>
								<td style="text-align:right;">
									${case[8]}
								</td>
								<td style="text-align:center;">
									${case[9]}
								</td>
								<td>
									<c:if test="${case[4] eq '1'}">
										<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/case/viewCivilcase?id=${case[2]}')">查看</a>
									</c:if>
									<c:if test="${case[4] eq '2'}">
										<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/case/viewCriminalcase?id=${case[2]}')">查看</a>
									</c:if>
									
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="10" align="center">
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
	<script type="text/javascript" src="<c:url value="/js/jquery.ztree.all.min.js"/>"></script>
	<script type="text/javascript">
		function _query() {
		var year = $("#year1").val();
		var week = $("#week").val();
		if(year!=''&&week==''||year!=null&&week==null){
		win.errorAlert("请选择周");
			return false;
		}
		if(year==''&&week!=''||year==null&&week!=null){
		win.errorAlert("请选择年份");
			return false;
		}
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		};
		function _export(e) {
		var year = $("#year1").val();
		var week = $("#week").val();
		if(year!=''&&week==''||year!=null&&week==null){
		win.errorAlert("请选择周");
			return false;
		}
		if(year==''&&week!=''||year==null&&week!=null){
		win.errorAlert("请选择年份");
			return false;
		}
			var form = document.forms[0];
			form.action = "${exporturl}"+"?type="+e;
			form.method = "post";
			form.submit();
		};
		$("#queryBtn").click(function() {
			if(checkTime($("#doDate").val(),$("#doDate2").val()) == false){
				return false;
			}
			_query();	
		});
		$('#year1').jeDate({
			format:"YYYY"
		});		
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
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
		     //所有企业数据	
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
					$("#vcCompanyId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
					$("#vcCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		$('.clear').on('click',function(){
			$(this).siblings("input[name='vcCompanyName']").val("");
			$(this).siblings("input[name='vcCompanyId']").val("");
		});
	</script>
</html>
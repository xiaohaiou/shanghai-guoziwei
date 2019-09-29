<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>现金流周计划审核</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>现金流周计划数据审核</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4">
				<span class="search_title">时间：</span>
				<input type="text" name="startDate" value="${entityview.startDate}" readonly="readonly" class="time time_short" />~
				<input type="text" name="endDate" value="${entityview.endDate}" readonly="readonly" class="time time_short" />
			</span>
			<span class="col-md-4">
				<span class="search_title">审核状态：</span>
				<select  name="status" class="selectpicker" >
					<option value=""  >全部</option>
					<c:forEach items="${requestScope.examstatustype}" var="result">
							<option value="${result.id }"  <c:if test="${entityview.status == result.id}">selected="selected"</c:if>>${result.description }</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">单位名称：</span>
				<input name="orgname" id="company" value="${entityview.orgname }" readonly title="${entityview.orgname }">
				
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
		   </span>	
		    <input type="hidden" name="org" id="organalId" value="${entityview.org}" />
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_xjlzjhSh_q,')==true }">
					<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>	
						<%-- <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="mload('${mapurl}/export')"> --%>
						<%-- <c:if test="${fn:contains(rolePagecodeNums,'sys_add')==true}"> --%>
							<%-- <input type="button" value="新增" class="form_btn" id="model_add" onclick="mload('${pageContext.request.contextPath}/reportgroup/newmodify')"> --%>
						<%-- </c:if> --%>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>时间</th>
						<th>单位名称</th>
						<th>现金流入</th>
						<th>现金流出</th>
						<th>现金净流量</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list}">
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${(requestScope.msgPage.pageNum-1)*10+ status.index + 1 }
								</td>
								<td style="text-align: center;">
									${sys.week}周（<fmt:formatDate value="${sys.weekStart}" pattern="yyyy-MM-dd"/> ~<fmt:formatDate value="${sys.weekEnd}" pattern="yyyy-MM-dd"/>）
								</td>
								<td style="text-align: left;">
									${sys.orgname}
								</td>
								<td style="text-align: right;">
									${sys.cashInflow}<%-- <fmt:formatNumber value="${sys.cashInflow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%>万元
								</td>
								<td style="text-align: right;">
									${sys.cashOutflow}<%-- <fmt:formatNumber value="${sys.cashOutflow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%>万元
								</td>
								<td style="text-align: right;">
									${sys.netCashFlow}<%-- <fmt:formatNumber value="${sys.netCashFlow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%>万元
								</td>
								<td style="text-align: center;">
									${sys.createPersonName}
								</td>
								<td style="text-align: center;">
									${sys.reportPersonName}
								</td>
								<td style="text-align: center;">
									${sys.auditorPersonName}
								</td>
								<td>
									<c:if test="${ sys.status==50112}">
										<span style="color:#3366ff">未上报</span>
									</c:if>
									<c:if test="${ sys.status==50113}">
										<span style="color:#00a59d">待审核</span>
									</c:if>
									<c:if test="${ sys.status==50114}">
										<span style="color:#ff399d">已退回</span>
									</c:if>
									<c:if test="${ sys.status==50115}">
										<span style="color:#00cc66">已审核</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_xjlzjhSh_q,')==true }">
										<a href="javascript:void(0)" onclick="mload('${mapurl}/view?op=modify&pid=${sys.pid}')" >查看</a>
									</c:if>
									<c:if test="${sys.status==50113 }">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_xjlzjhSh_e,')==true }">
											<a href="javascript:void(0)" onclick="mload('${mapurl}/exam?pid=${sys.pid}')" >审核</a>
										</c:if>
									</c:if>
									<c:if test="${sys.status==50115 }">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_xjlzjhSh_return,')==true }">
											<a href="javascript:void(0)" onclick="mload('${mapurl}/exam?pid=${sys.pid}')" >回退</a>
										</c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="12" align="center">
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
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
		var timeoutId;
		$('#company').on('focus click',function(){
			$(this).next('.com_ztree_box').css('display','block');
		}).parent().on('mouseenter',function(){
			clearTimeout(timeoutId);
		}).on('mouseleave',function(){
			clearTimeout(timeoutId);
			timeoutId = setTimeout(function(el){
				$(el).find('.com_ztree_box').css('display','none');
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
			if(childrenNodes[0]!=null)
				treeObj.expandNode(childrenNodes[0],true);
		});
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					 $("#organalId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					 $("#company").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);vaild.vaild($("#company")[0]);
					  $("#company").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});		
		$(".clear").on('click',function(){
			$(this).parent().siblings('input[name="orgname"]').val('');
			$("#organalId").val("");
		})
		
		$(' input.time').jeDate(
			{
				format:"YYYY-MM"
			}
		)
	
	
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}

		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="orgname"]').val('');
			$("#organalId").val("");
			$("#company").attr('title','');
		});
		
	</script>
</html>
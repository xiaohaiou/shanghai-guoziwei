<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>数据看报目录审核</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
</head>
<body>
	<h4>数据看报目录审核</h4>
	<div class="table_content">
		<form id="form1" class="row">
			
			<span class="col-md-4"> 
				<span class="search_title">上报单位：</span>
				<input type="hidden" id="compId" name="compId" value="${entity.compId}" >
				<input name="compName" id="compName" value="${entity.compName}" readonly="readonly" class="w25" check="NotEmpty">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:500px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
			   </div>
		    </span>
		    
			<span class="col-md-4"><span class="search_title">数据名称：</span> <input type="text" name="eventTitle" value="${entity.eventTitle}"  class="w25"/> </span> 
			
			<span class="col-md-4"><span class="search_title">数据类型：</span>
				<select name="relevancestatus" class="selectpicker" id="relevancestatus">
					<option value="">全部</option>
					<option value="3">待审核</option>
					<option value="4">已审核</option>
					<option value="5">已退回</option>
				</select> 
			</span> 
			
			 <span class="col-md-4">
				<span class="search_title">上报日期: </span>
				<input type="text" value="${entity.reportTime}" name="reportTime" id="reportTime" readonly="readonly" class="time time_short"/>
			</span>
			
			<br>
			<br>
			<div class="form_div col-md-12">
			    <c:if test="${fn:contains(gzwsession_code,',bimWork_fxsjglmlsh_query,')==true}">
				    <input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th style="width:3%" >序号</th>
					<th style="width:10%" >日期</th>
					<th style="width:10%" >数据名称</th>
					<th style="width:15%" >数据上报公司</th>
					<th >数据编号</th>
					<th style="width:15%" >数据类型</th>
					<th style="width:10%" >数据操作人</th>
					<th style="width:10%" >数据审核人</th>
					<th style="width:8%" >数据状态</th>
					<th style="width:15%" >操作</th>
				</tr>
				
				<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.msgPage.list}" var="l" varStatus="status">
						<tr>
							<td style="text-align: center;">${recordNumber+ status.index + 1 }</td>
							<td style="text-align: center;">${l.reportTime}</td>
							<td style="text-align: center;">${l.eventTitle}</td>
							<td style="text-align: center;">${l.compName}</td>
							<td style="text-align: center;">${l.eventNum}</td>
							<td style="text-align: center;">${l.relevancethreeName}</td>
							<td style="text-align: center;">${l.relevancePerson}</td>
							<td style="text-align: center;">${l.relevanceauditor}</td>
							
							<c:choose>
								<c:when test="${l.relevancestatus==1 }">
								<td style="text-align: center;">未关联</td>
								</c:when>
								<c:when test="${l.relevancestatus==2 }">
								<td style="text-align: center;">未上报</td>
								</c:when>
								<c:when test="${l.relevancestatus==3 }">
								<td style="text-align: center;">待审核</td>
								</c:when>
								<c:when test="${l.relevancestatus==4 }">
								<td style="text-align: center;">已审核</td>
								</c:when>
								<c:otherwise>
								<td style="text-align: center;">已退回</td>
								</c:otherwise>
							</c:choose>
							
							<td style="text-align: center;">
							    <c:if test="${fn:contains(gzwsession_code,',bimWork_fxsjglmlsh_show,')==true}">
							         <a href="javascript:void(0)" onclick="mload('${mapurl}/relevanceExamine?op=view&id=${l.id }')">查看</a>
							    </c:if>
							    <c:if test="${fn:contains(gzwsession_code,',bimWork_fxsjglmlsh_examine,')==true}">
									    <c:if test="${l.relevancestatus ==3}">
											<a href="javascript:void(0)" onclick="mload('${mapurl}/relevanceExamine?op=examine&id=${l.id }')">审核</a>
										</c:if>
							    </c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.msgPage.list}">
					<tr>
						<td colspan="13" align="center">查询无记录</td>
					</tr>
				</c:if>
			</table>
			<jsp:include page="/views/system/page.jsp" />
		</div>
	</div>
	<jsp:include page="/views/system/modal.jsp" />
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		$('input.time').jeDate({
			format:"YYYY-MM"
		});
		function _query(){
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		}
		
		function del(id){
			parent.win.confirm('确定要删除吗？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/bimr/riskEvent/delete";
					var param = {id: id};
					$.post(url, param, function(data) {
						win.successAlert('删除成功！',_query);
					});
				}
			});
		}
		
		
		var timeoutId;
		$('#compName').on('focus click',function(){
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
			if('${entity.relevancestatus}'!='')
				$("#relevancestatus").val('${entity.relevancestatus}');
		});
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					$("#compId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
					$("#compName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
	</script>
</html>
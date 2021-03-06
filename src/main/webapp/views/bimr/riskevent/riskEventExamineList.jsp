<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>风险事件审核</title>
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
	<c:choose>
		<c:when test="${titletype==1}">
			<h4>专业公司协调统筹</h4>
		</c:when>
		<c:when test="${titletype==2}">
			<h4>核心企业协调统筹</h4>
		</c:when>
		<c:when test="${titletype==3}">
			<h4>产业集团协调统筹</h4>
		</c:when>
		<c:when test="${titletype==4}">
			<h4>协调统筹措施填写</h4>
		</c:when>
		<c:otherwise>
			<h4>风险事件审核</h4>
		</c:otherwise>
	</c:choose>
	
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
		
			
			<span class="col-md-4"><span class="search_title">状态：</span>
				<select name="status" class="selectpicker" id="status">
					<option value="">全部</option>
					<c:forEach items="${requestScope.status}" var="result">
						<option value="${result.id }"
							<c:if test="${entity.status == result.id}">selected="selected"</c:if>>
							${result.description}</option>
					</c:forEach>
				</select> 
			</span> 
			<span class="col-md-4"><span class="search_title">事件编号：</span> <input type="text" name="eventNum" value="${entity.eventNum}"  class="w25"/> </span> 
			<span class="col-md-4"><span class="search_title">事件名称：</span> <input type="text" name="eventTitle" value="${entity.eventTitle}"  class="w25"/> </span> 
			<span class="col-md-4"><span class="search_title">应对策略：</span>
				<select name="copingStrategy" class="selectpicker" id="copingStrategy">
					<option value="">全部</option>
					<c:forEach items="${requestScope.copingStrategy}" var="result">
						<option value="${result.id }"
							<c:if test="${entity.copingStrategy == result.id}">selected="selected"</c:if>>
							${result.description}</option>
					</c:forEach>
				</select> 
			</span> 
			
			<br>
			<br>
			<div class="form_div col-md-12">
		    	<c:if test="${fn:contains(gzwsession_code,',bimWork_fxsjsh_query,')==true}">
				    <input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				 </c:if>
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th style="width:3%" >序号</th>
					<th style="width:10%" >上报时间</th>
					<!-- <th style="width:10%" >应对完成时限</th> -->
					<th style="width:15%" >上报单位名称</th>
					<th style="width:10%" >风险事件名称</th>
					<th style="width:10%" >采取策略</th>
					<th style="width:10%" >事件编号</th>
					<th style="width:8%" >状态</th>
					<th style="width:15%" >操作</th>
				</tr>
				
				<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.msgPage.list}" var="l" varStatus="status">
						<tr>
							<td style="text-align: center;">${recordNumber+ status.index + 1 }</td>
							<td style="text-align: center;">${l.reportTime}</td>
							<td style="text-align: center;">${l.compName}</td>
							<td style="text-align: center;">${l.eventTitle}</td>
							<td style="text-align: center;">${l.copingStrategyName}</td>
							<td style="text-align: center;">${l.eventNum}</td>
							<td style="text-align: center;">${l.statusName}</td>
							
							<td style="text-align: left;">
							   
							    <c:if test="${not empty titletype}">
							    
							     
							       
							        	<c:if test="${titletype==1}">
							        	<c:if test="${fn:contains(gzwsession_code,',bimWork_zygsxttc_show,')==true}"> 
							               <a href="javascript:void(0)" onclick="mload('${mapurl}/view?id=${l.id }')">查看</a>
	   						           </c:if> 
	   						           <c:if test="${l.status == 82102}">
							        	 <c:if test="${fn:contains(gzwsession_code,',bimWork_zygsxttc_fillPerson,')==true}">
									   <a href="javascript:void(0)" onclick="mload('${mapurl}/riskEventExamine?op=event&id=${l.id }&titletype=${titletype}')">跟踪人填写</a>
								       </c:if>
								       </c:if>
								       </c:if>
								       <c:if test="${titletype==2}">
								       <c:if test="${fn:contains(gzwsession_code,',bimWork_hxqyxttc_show,')==true}"> 
							               <a href="javascript:void(0)" onclick="mload('${mapurl}/view?id=${l.id }')">查看</a>
	   						           </c:if>
	   						           <c:if test="${l.status == 82102}">
							        	 <c:if test="${fn:contains(gzwsession_code,',bimWork_hxqyxttc_fillPerson,')==true}">
									   <a href="javascript:void(0)" onclick="mload('${mapurl}/riskEventExamine?op=event&id=${l.id }&titletype=${titletype}')">跟踪人填写</a>
								       </c:if>
								        </c:if>
								       </c:if>
								       <c:if test="${titletype==3}">
								       <c:if test="${fn:contains(gzwsession_code,',bimWork_cyjtxttc_show,')==true}"> 
							               <a href="javascript:void(0)" onclick="mload('${mapurl}/view?id=${l.id }')">查看</a>
	   						           </c:if>
	   						           <c:if test="${l.status == 82102}">
							        	 <c:if test="${fn:contains(gzwsession_code,',bimWork_cyjtxttc_fillPerson,')==true}">
									   <a href="javascript:void(0)" onclick="mload('${mapurl}/riskEventExamine?op=event&id=${l.id }&titletype=${titletype}')">跟踪人填写</a>
								       </c:if>
								         </c:if>
								       </c:if>
								       
								       <c:if test="${titletype==4}">
								       <c:if test="${fn:contains(gzwsession_code,',bimWork_xttccstx_show,')==true}"> 
							               <a href="javascript:void(0)" onclick="mload('${mapurl}/view?id=${l.id }')">查看</a>
	   						           </c:if>
	   						           <c:if test="${l.status == 82102}">
								        <c:if test="${fn:contains(gzwsession_code,',bimWork_xttccstx_fillMeasures,')==true}">
									   <a href="javascript:void(0)" onclick="mload('${mapurl}/riskEventExamine?op=event&id=${l.id }&titletype=${titletype}')">措施填写</a>
								       </c:if>
								       </c:if>
								       </c:if>
							    
							    </c:if>
							    <c:if test="${empty titletype}">
							     <c:if test="${fn:contains(gzwsession_code,',bimWork_fxsjsh_show,')==true}"> 
							        <a href="javascript:void(0)" onclick="mload('${mapurl}/view?id=${l.id }')">查看</a>
							    </c:if> 
							      <c:if test="${l.status == 82101}">
							        <c:if test="${fn:contains(gzwsession_code,',bimWork_fxsjsh_examineEvent,')==true}">
									   <a href="javascript:void(0)" onclick="mload('${mapurl}/riskEventExamine?op=event&id=${l.id }&titletype=${titletype}')">事件审核</a>
								     </c:if> 
								</c:if>
							    </c:if>
							  
								<%--
								<c:if test="${l.status == 82101}">
									<a href="javascript:void(0)" onclick="mload('${mapurl}/riskEventExamine?op=eventback&id=${l.id }')">事件退回</a>
								</c:if>
								--%>
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
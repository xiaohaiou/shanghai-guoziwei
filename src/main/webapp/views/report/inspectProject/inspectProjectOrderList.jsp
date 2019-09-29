<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>稽核事项领导查询</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<style type="text/css">
			.selectone{
				white-space: nowrap;
				font-size: 14px;
				cursor: pointer;
			}
			.selectone:hover, .selectone.selectedOne{
				box-sizing: border-box;
				background-color: rgba(255,255,255,0.8);
				border-bottom:1px solid rgba(0,0,0,0.5);
			}
			.selectone>* {
				display:inline-block;
				min-width: 60px;
			}
			.selectedOne{
				border-color:#4a22cc;
				color: #4a22cc;
			}
			#com_ztree span {
				padding-left:0;
			}
		</style>
	</head>
	<body>
		
		<h4>${stype=='leader'?'稽核事项领导查询':'稽核事项一般查询' }</h4>
		<div class="table_content">
		
		<form:form id="form1" class="row"  >
			<span class="col-md-4"><span class="search_title">年份：</span> <input type="text" name="year"
				value="${entityview.year}" readonly="true" class="time" /> 
			</span> 
			<span class="col-md-4"> <span class="search_title">稽核公司：</span>
				<input type="hidden" id="compId" name="compId" value="${entityview.compId }">
				<input name="compName" id="compName" value="${entityview.compName }" readonly title="${entityview.compName }" >
				<div class="com_ztree_box"
					style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
				</div> <i class="clear iconfont icon-guanbi"></i> 
			</span> 
			<span class="col-md-4"><span class="search_title">稽核类型：</span>
				<select name="inspectType" class="selectpicker">
					<option value="">全部</option>
					<c:forEach items="${requestScope.inspectType}" var="result">
						<option value="${result.id }"
							<c:if test="${entityview.inspectType == result.id}">selected="selected"</c:if>>
							${result.description}</option>
					</c:forEach>
				</select> 
			</span>
			<span class="col-md-4"><span class="search_title">事项类别：</span>
				<select name="itemType" class="selectpicker">
					<option value="">全部</option>
					<c:forEach items="${requestScope.itemType}" var="result">
						<option value="${result.id }"
							<c:if test="${entityview.itemType == result.id}">selected="selected"</c:if>>
							${result.description}</option>
					</c:forEach>
				</select> 
			</span>
			<span class="col-md-4"><span class="search_title">稽核状态：</span>
				<select name="status" class="selectpicker" id="status">
					<option value="">全部</option>
					<c:forEach items="${requestScope.inspectStatus}" var="result">
						<option value="${result.id }"
							<c:if test="${entityview.status == result.id}">selected="selected"</c:if>>
							${result.description}</option>
					</c:forEach>
				</select> 
			</span>
			
			<span class="col-md-4"><span class="search_title">事项名称：</span> <input type="text" name="itemName"
				value="${entityview.itemName}" id="itemName"/> 
			</span>
			
			<span class="col-md-4"><span class="search_title">是否指示：</span>
				<select name="isOrder" id="isOrder" class="selectpicker">
					<option value="">全部</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select> 
			</span> 
			
			<div class="form_div col-md-12">
				<!-- 领导查看和一般查询公用一个页面，所以权限分别判断 -->
				<c:if test="${fn:contains(stype,'leader')==true }">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_jhsxldcx_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn">
					</c:if>
				</c:if>
				<c:if test="${fn:contains(stype,'common')==true }">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_jhsxybcx_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn">
					</c:if>
				</c:if>
						<!-- <input type="button" value="导出" class="form_btn" id="qrybtn"> -->
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:3%" >序号</th>
						<th style="width:6%" >年份</th>
						<th style="width:21%" >稽核公司</th>
						<th style="width:23%" >稽核财务事务名称</th>
						<th style="width:7%" >是否做过指示</th>
						<th style="width:7%" >稽核事项负责人</th>
						<th style="width:7%" >状态标识</th>
						<th style="width:12%" >操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber"
						value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.msgPage.list}" var="l"
						varStatus="status">
						<tr>
							<td style="text-align: center;">${recordNumber+ status.index
								+ 1 }</td>
							<td style="text-align: center;">${l.year
								}</td>
							<td style="text-align: left;max-width:20em;">
								${l.compName}
							</td>
							<td style="text-align: left;max-width:20em;">${l.itemName
								}</td>
							<td style="text-align: center;">${l.isOrder>0?"是":"否" }
							</td>
							<td style="text-align: left;">${l.itemPerson }
							</td>
							
							<td>${l.statusName }</td>
							<td> 
								<!-- 领导查看和一般查询公用一个页面，所以权限分别判断 -->
								<c:if test="${fn:contains(stype,'leader')==true }">
									<c:if test="${fn:contains(gzwsession_code,',bimWork_jhsxldcx_view,')==true }">
										<a href="javascript:void(0)" onclick="mload('${mapurl}/view?type=${type }&id=${l.id }')">查看</a>
									</c:if>
									
									<c:if test="${l.status!=80045 }">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_jhsxldcx_ldzs,')==true }">
											<a href="javascript:void(0)" onclick="mload('${mapurl}/view?type=${type }&stype=${stype }&id=${l.id }')">指示</a>
										</c:if>
									</c:if>
								</c:if>
								<c:if test="${fn:contains(stype,'common')==true }">
									<c:if test="${fn:contains(gzwsession_code,',bimWork_jhsxybcx_view,')==true }">
										<a href="javascript:void(0)" onclick="mload('${mapurl}/view?type=${type }&id=${l.id }')">查看</a>
									</c:if>
								</c:if>
							</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="14" align="center">
								查询无记录
							</td>
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
		var timeoutId
 		$('#compName').on('focus click',function(){
 			$(this).next('.com_ztree_box').css('display','block')
 		}).parent().on('mouseenter',function(){
 			clearTimeout(timeoutId)
 		}).on('mouseleave',function(){
 			clearTimeout(timeoutId)
 			timeoutId = setTimeout(function(el){
 				$(el).find('.com_ztree_box').css('display','none')
 			},3e2,this);
 		})
	
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
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#compId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#compName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})
		
		$('input.time').jeDate(
			{
				format:"YYYY"
			}
		)
	
	
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}

		$("#qrybtn").click(_query);
		
		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="compName"]').val('');
			$("#compId").val("");
			$("#compName").attr('title','');
		});
		
		function checkDelete(url,id){
			$.post("${pageContext.request.contextPath}/adDocument/checkDelete?id=" + id, function(data) {
				var commitresult=JSON.parse(data);
				if(commitresult.result){
					mload(url);
				}else{
					win.errorAlert('此数据已被删除！',_query);
				}
			});
		}
		
		$(function(){
			
			$("#isOrder").val('${entityview.isOrder}');
		})
	</script>
</html>
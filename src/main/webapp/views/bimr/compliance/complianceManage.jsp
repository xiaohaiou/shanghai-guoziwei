<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>合规调查数据填报</title>
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
<body class="hn_grey">
	<h4>合规调查数据填报</h4>
	<div class="table_content">
		<form id="form1" class="row">
			
			<span class="col-md-4"><span class="search_title">调查类型：</span>
				<select name="investigationType" class="selectpicker">
					<option value="">全部</option>
					<c:forEach items="${requestScope.investigationType}" var="result">
						<option value="${result.id }"
							<c:if test="${entity.investigationType == result.id}">selected="selected"</c:if>>
							${result.description}</option>
					</c:forEach>
				</select> 
			</span>
			<%-- <span class="col-md-4"> <span class="search_title">单位名称：</span>
				<input type="hidden" id="compId" name="compId" value="${entity.compId }">
				<input name="compName" id="compName" value="${entity.compName }" readonly title="${entity.compName }" >
				<div class="com_ztree_box"
					style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
				</div> <i class="clear iconfont icon-guanbi"></i> 
			</span>  --%>
			
			
			<span class="col-md-4"><span class="search_title">状态：</span>
				<select name="status" class="selectpicker" id="status">
					<option value="">全部</option>
					<c:forEach items="${requestScope.complianceStatus}" var="result">
						<option value="${result.id }"
							<c:if test="${entity.status == result.id}">selected="selected"</c:if>>
							${result.description}</option>
					</c:forEach>
				</select> 
			</span>
			<span class="col-md-4"><span class="search_title">调查时间：</span> <input type="text" name="startTime"
				value="${entity.startTime }" readonly="true" class="time" style="width:calc(50% - 8em);"/> - <input type="text" name="endTime"
				value="${entity.endTime }" readonly="true" class="time" style="width:calc(50% - 8em);"/>
			</span> 
			<span class="col-md-4"><span class="search_title">项目名称：</span> <input type="text" name="projectName"
				value="${entity.projectName}" id="projectName"/> 
			</span> 
			<br>
			<br>
			<div class="form_div col-md-12">
			     <c:if test="${fn:contains(gzwsession_code,',bimWork_hgdcsjtb_query,')==true}">
					<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				 </c:if>
				<%-- <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="mload('${mapurl}/export')"> --%>
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th style="width:3%" >序号</th>
					<th style="width:6%" >调查单位(或人员)</th>
					<th style="width:21%" >项目名称</th>
					<th style="width:23%" >调查起止时间</th>
					<th style="width:7%" >调查类型</th>
					<th style="width:7%" >项目状态</th>
					<th style="width:22%" >操作</th>
				</tr>
				
				<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber"
						value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.msgPage.list}" var="l"
						varStatus="status">
						<tr>
							<td style="text-align: center;">${recordNumber+ status.index + 1 }</td>
							<td style="text-align: center;">${l.investigationDep }</td>
							<td style="text-align: left;">${l.projectName }</td>
							<td style="text-align: left;">${l.startTime } ~ ${l.endTime }</td>
							<td style="text-align: left;">${l.investigationTypeName }</td>
							<td>${l.statusName }</td>
							<td>
							    <c:if test="${fn:contains(gzwsession_code,',bimWork_hgdcsjtb_show,')==true}">
								    <a href="javascript:void(0)" onclick="mload('${mapurl}/manageView?id=${l.id }')">查看</a>
								</c:if>
								<!-- 状态为在办中才能修改 -->
								<c:if test="${fn:contains(gzwsession_code,',bimWork_hgdcsjtb_update,')==true}">
									<c:if test="${l.status==81010 || l.status==81011 }">
										<a href="javascript:void(0)" onclick="mload('${mapurl}/manageedit?op=modify&id=${l.id }')">修改</a>
									</c:if>
								</c:if>
								<c:if test="${fn:contains(gzwsession_code,',bimWork_hgdcsjtb_applySettlement,')==true}">
									<c:if test="${l.status==81010 || l.status==81011}">
											<a href="javascript:;" onclick="changeStatus('${l.id}')">申请办结</a>
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
		/* var timeoutId
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
 		$('.clear').on('click',function(){
			$(this).siblings("input[name='compId']").val("");
			$(this).siblings("input[name='compName']").val("");
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
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#compId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#compName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		}) */


		function checkdata(url,id){
			var commitresult="";
			var checkurl = "${mapurl}/hasCheck?id=" + id;
			$.ajax({
				type: 'POST',
				url: checkurl,
				// async:false,
				success: function (data){
					commitresult=JSON.parse(data);
					if(commitresult.result == true){
						mload(url);
					} else {
						win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
					}
				}
			});
		}

		$('input.time').jeDate({
				format:"YYYY-MM-DD"
			}
		);
	
		function _query(){
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		}
		
		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/bimr/compliance/delete?id=" + id;
					$.post(url, function(data) {
						var commitresult1=JSON.parse(data);
						if(commitresult1.result){
							win.successAlert('删除成功！',function(){
								location.reload();
							});
						}else{
							win.errorAlert('数据已被其他人员操作，此操作失败！');
						}
					
					});
				}
			});
		}
		
		function changeStatus(id){
			parent.win.confirm('确定要申请办结？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/bimr/compliance/updatestatus?status=81012&id=" + id ;
					$.post(url, function(data) {
						var commitresult1=JSON.parse(data);
						if(commitresult1.result){
							win.successAlert('申请办结成功！',function(){
								location.reload();
							});
						}else{
							win.errorAlert('申请办结失败！');
						}
					
					});
				}
			});
		}
		
	</script>
</html>
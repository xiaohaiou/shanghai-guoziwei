<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>企业信息采集汇总统计</title>
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
		<h4>企业信息采集汇总统计</h4>
		<div class="table_content">
		
		<form:form id="form1" class="row"  >
			<!-- <span class="col-md-3">单位：<input type="text" name="compId"/></span> -->
			<span class="col-md-4">
				<span class="search_title">单位名称：</span>
				<input type="hidden" id="compId" name="compId" value="${entity.compId }" >
				<input name="compName" id="compName" value="${entity.compName }" readonly title="${entity.compName }">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			   </div>
			    <i class="clear iconfont icon-guanbi"></i>
		   </span>
		   <span class="col-md-4">
				<span class="search_title">包含子公司：</span>
				<select  name="isallcompany" class="selectpicker">
					<option value="isall" <c:if test="${isallcompany eq 'isall'}">selected="selected"</c:if>>是</option>
					<option value="notall" <c:if test="${isallcompany eq 'notall'}">selected="selected"</c:if>>否</option>					
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">年份：</span>
				<input type="text" value="${entity.year }" id="year" name="year" readonly="true" class="time"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">月份：</span>
				<select  name="month" class="selectpicker" id="month">
				<option value=""  >全部</option>
					<option value="1"  <c:if test="${entity.month == 1}">selected="selected"</c:if>>1月</option>
					<option value="2"  <c:if test="${entity.month == 2}">selected="selected"</c:if>>2月</option>
					<option value="3"  <c:if test="${entity.month == 3}">selected="selected"</c:if>>3月</option>
					<option value="4"  <c:if test="${entity.month == 4}">selected="selected"</c:if>>4月</option>
					<option value="5"  <c:if test="${entity.month == 5}">selected="selected"</c:if>>5月</option>
					<option value="6"  <c:if test="${entity.month == 6}">selected="selected"</c:if>>6月</option>
					<option value="7"  <c:if test="${entity.month == 7}">selected="selected"</c:if>>7月</option>
					<option value="8"  <c:if test="${entity.month == 8}">selected="selected"</c:if>>8月</option>
					<option value="9"  <c:if test="${entity.month == 9}">selected="selected"</c:if>>9月</option>
					<option value="10"  <c:if test="${entity.month == 10}">selected="selected"</c:if>>10月</option>
					<option value="11"  <c:if test="${entity.month == 11}">selected="selected"</c:if>>11月</option>
					<option value="12"  <c:if test="${entity.month == 12}">selected="selected"</c:if>>12月</option>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">状态：</span>
				<select  name="status" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.reportStatus}" var="result">
							<option value="${result.id }"  <c:if test="${entity.status == result.id}">selected="selected"</c:if>>${result.description }</option>
						</c:forEach>
				</select>
			</span>
			
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_gwsjsb_query,')==true }">	
					<input type="button" value="查询" class="form_btn" id="qrybtn">
				</c:if>
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>时间</th>
						<th>单位名称</th>
						<th>采集总量</th>
						<th>超时采集总量</th>
						<th>平均采集时间</th>
						<th>平均流转节点</th>
						<th>采集率</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="document" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: center;">
									${document.year }-${document.month }
								</td>
								<td style="text-align: left;">
									${document.compName }
								</td>
								<td style="text-align: right;">
									${document.totalDocument }条
								</td>
								<td style="text-align: right;">
									${document.totalOverTimeDocument }条
								</td>
								<td style="text-align: right;">
									${document.avgDocumentTime }小时
								</td>
								<td style="text-align: right;">
									${document.avgNode }个
								</td>
								<td style="text-align: right;">
									${document.examineRatio }%
								</td>
								<td style="text-align: center;">
									${document.createPersonName }
								</td>
								<td style="text-align: center;">
									${document.reportPersonName }
								</td>
								<td style="text-align: center;">
									${document.auditorPersonName }
								</td>
								<td>
									<c:if test="${ document.status==50112}">
										<span style="color:#3366ff">${document.statusName }</span>
									</c:if>
									<c:if test="${ document.status==50113}">
										<span style="color:#00a59d">${document.statusName }</span>
									</c:if>
									<c:if test="${ document.status==50114}">
										<span style="color:#ff399d">${document.statusName }</span>
									</c:if>
									<c:if test="${ document.status==50115}">
										<span style="color:#00cc66">${document.statusName }</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_gwsjsb_query,')==true }">
										<a href="javascript:void(0)" onclick="checkDelete('${pageContext.request.contextPath}/adDocument/view?op=check&id=${document.id}','${document.id }')">查看</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="13" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
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
    		//反绑	
			var tid = $("#compId").val();
			if(tid){
				var fbNode = treeObj.getNodeByParam("id", tid);
				if(fbNode){
					expandParent(treeObj, fbNode);
					treeObj.selectNode(fbNode);
				}
			}		
	    });
	    
	    /**
			展开上级节点
		*/
		function expandParent(treeObj,fbNode){
			var pfbNode = treeObj.getNodeByParam("id", fbNode.pId);
			if(pfbNode != null){
				treeObj.expandNode(pfbNode,true);
				expandParent(treeObj,pfbNode);
			}
		}
	
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
			/* var NodeId = $("#compId").val();
		     console.log(NodeId); */
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}
		
		function _export()
		{
			/* var NodeId = $("#compId").val();
		     console.log(NodeId); */
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/adDocument/export";
			form.method = "post";
			form.submit();		
		}	

		function del(id,lastModifyDate){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					$.post("${pageContext.request.contextPath}/adDocument/checkDelete?id=" + id, function(data) {
						var commitresult=JSON.parse(data);
						if(commitresult.result){
							var url = "${pageContext.request.contextPath}/adDocument/delete?id=" + id +"&lastModifyDate=" + lastModifyDate;
							$.post(url, function(data) {
								var commitresult1=JSON.parse(data);
								if(commitresult1.result){
									win.successAlert('删除成功！',_query);
								}else{
									win.errorAlert('数据已被其他人员操作，此操作失败！',_query);
								}
							
							});
						}else{
							win.errorAlert('此数据已被删除！',_query);
						}
					});
				}
			});
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
		function tb(){
		var year = $("#year").val();
		var month=$("#month").val();
		
		var NodeId = $("#compId").val();
		if(NodeId.length>20){
		NodeId = "";
		}
		//
		console.log(NodeId);
		var url="${pageContext.request.contextPath}/adDocument/synIDocument?year="+year+"&month="+month+"&NodeId="+NodeId;
		$.blockUI({ message: '同步中', css: { width: '275px' } });
			$.post(url, function(data) {
			$.unblockUI();
			console.log(data);
				var commitresult=JSON.parse(data);
				if(commitresult.result){
					win.successAlert("同步成功",_query);
				}else{
				
					win.errorAlert(commitresult.exceptionStr,_query);
				}
			});
		}
		
		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="compName"]').val('');
			$("#compId").val("");
			$("#compName").attr('title','');
		});
	</script>
</html>
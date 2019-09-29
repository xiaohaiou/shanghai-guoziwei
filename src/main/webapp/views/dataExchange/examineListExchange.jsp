<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>企业财务数据交换情况</title>
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
		<h4>企业财务数据交换情况</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title">年份：</span>
				<input type="text" value="${entity.year }" name="year" readonly="true" class="time"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">月份：</span>
				<select  name="month" class="selectpicker" >
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
				<span class="search_title">要情类别：</span>
				<select  name="importantType" class="selectpicker" >
					<option value=""  >全部</option>
					<c:forEach items="${requestScope.importantTypes}" var="result">
						<option value="${result.id }"  <c:if test="${entity.importantType == result.id}">selected="selected"</c:if>>${result.description }</option>
					</c:forEach>
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
			<span class="col-md-4">
				<span class="search_title">单位名称：</span>
				<input type="hidden" id="importantCompId" name="importantCompId" value="${entity.importantCompId }" >
				<input name="importantCompName" id="importantCompName" value="${entity.importantCompName }" readonly title="${entity.importantCompName }">
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
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yqsjsjsh_query,')==true }">
					<input type="button" value="查询" class="form_btn" id="qrybtn">
				</c:if>
				<!-- <input type="button" value="导出" class="form_btn" id="qrybtn"> -->
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>时间</th>
						<th>发生单位</th>
						<th>数据类别</th>
						<th style="width:20%">数据标题</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="important" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: center;">
									${important.year }-${important.month }
								</td>
								<td style="text-align: left;">
									${important.importantCompName }
								</td>
								<td style="text-align: left;">
									${important.importantTypeName }
								</td>
								<td style="text-align: left;">
									${important.importantTitle }
								</td>
								<td style="text-align: center;">
									${important.createPersonName }
								</td>
								<td style="text-align: center;">
									${important.reportPersonName }
								</td>
								<td style="text-align: center;">
									${important.auditorPersonName }
								</td>
								<td>
									<c:if test="${ important.status==50112}">
										<span style="color:#3366ff">${important.statusName }</span>
									</c:if>
									<c:if test="${ important.status==50113}">
										<span style="color:#00a59d">${important.statusName }</span>
									</c:if>
									<c:if test="${ important.status==50114}">
										<span style="color:#ff399d">${important.statusName }</span>
									</c:if>
									<c:if test="${ important.status==50115}">
										<span style="color:#00cc66">${important.statusName }</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_yqsjsjsh_query,')==true }">
										<a href="javascript:void(0)" onclick="checkDelete('${pageContext.request.contextPath}/adImportant/view?op=check&id=${important.id}','${important.id }')">查看</a>
									</c:if>
									<%-- <c:if test="${fn:contains(gzwsession_code,',bimWork_yqsjsjsh_exam,')==true }">
										<c:if test="${important.status==50113 }">
											<a href="javascript:void(0)" onclick="checkDelete('${pageContext.request.contextPath}/adImportant/examine?id=${important.id}&lastModifyDate=${important.lastModifyDate }','${important.id }')">审核</a>
										</c:if>
									</c:if>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_yqsjsjsh_examback,')==true }"><!-- bimWork_yqsjsjsh_examback -->
										<c:if test="${important.status==50115 }">
											<a href="javascript:void(0)" onclick="checkDelete('${pageContext.request.contextPath}/adImportant/examine?id=${important.id}&lastModifyDate=${important.lastModifyDate }','${important.id }')">退回</a>
										</c:if>
									</c:if>	 --%>								
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
				<jsp:include page="../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
		var timeoutId
 		$('#importantCompName').on('focus click',function(){
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
			var tid = $("#importantCompId").val();
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
							 $("#importantCompId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#importantCompName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})
		
		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="importantCompName"]').val('');
			$("#importantCompId").val("");
			$("#importantCompName").attr('title','');
		});
		
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
		
		function checkDelete(url,id){
			$.post("${pageContext.request.contextPath}/adImportant/checkDelete?id=" + id, function(data) {
				var commitresult=JSON.parse(data);
				if(commitresult.result){
					mload(url);
				}else{
					win.errorAlert('此数据已被删除！',_query);
				}
			});
		}
	
	</script>
</html>
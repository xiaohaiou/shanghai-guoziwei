<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>数据权限用户查询（管理树）</title>
	<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
	<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
	<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
	<!-- 库|插件 -->
	<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
	<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
	
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/settingCenter/css/zTreeStyle.css"/>">
	<!-- 新加样式 -->
	<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
	<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
</head>
<body style="overflow:auto;">
	<h4>数据权限用户查询（管理树）</h4>
	<div class="table_content">
		<form id="form1" action="${pageContext.request.contextPath}/user/hrAndFinanceCompany/getUserHrCompanys" method="post" class="row">
			<div class="row">
				<span class="col-md-3">
					用户账号：<input type="text" id="vcAccount" name="vcAccount" value="${vcAccount}">
				</span>
				<span class="col-md-3">
					用户姓名：<input type="text" id="userName" name="userName" value="${userName}">
				</span>
				<span class="col-md-3">
					组织机构：<input  type="hidden" id="vcOrganId" name="vcOrganId" value="${vcOrganId}">
					<input name="compName" id="compName" value="${compName}" readonly title="${compName}">
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				   </div>
				</span>
				<span class="col-md-3">
					是否包含上级机构用户：
					<select id="isHavingSuper" name="isHavingSuper">
						<option value="1" <c:if test="${isHavingSuper eq 1}">selected=selected</c:if>>是</option>
						<option value="0" <c:if test="${isHavingSuper eq 0}">selected=selected</c:if>>否</option>
					</select>
				</span>
			</div>
			<div class="row">
				<div class="form_div col-md-3 right" style="margin-top: 20px;float: right;">
					<input type="button" value="查询" class="form_btn" onclick="query();">
					<input type="button" value="导出" class="form_btn" onclick="export_1();">
				</div>
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th width="5%">序号</th>
					<th width="15%">人员名称</th>
					<th width="20%">用户账号</th>					
					<th width="20%">所属部门</th>
					<th width="20%">权限查看</th>
					<th width="20%">权限配置</th>
				</tr>
				<c:if test="${not empty requestScope.msgPage.list}">
				<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
					<c:forEach var="usr" items="${requestScope.msgPage.list}" varStatus="status">
						<tr>
							<td>${recordNumber + status.index + 1 }</td>
							<td>${usr[4]}</td>
							<td>${usr[3]}</td>
							<td>${usr[5]}</td>
							<td><a href="javascript:;" onclick="openDialog('${pageContext.request.contextPath}/sys/role/_detailList?roleIds=${usr[0]}&randomId=<%=Math.random()%>')">权限查看</a></td>
							<td><a href="${pageContext.request.contextPath}/sys/role/_query?type=role&vcAccount=${usr[3]}" target="_blank">权限配置</a></td>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.msgPage.list}">
					<tr>
						<td colspan="6" align="center">
							查询无记录
						</td>
					</tr>
				</c:if>
			</table>
			<jsp:include page="page.jsp" flush="true"></jsp:include>
		</div>
	</div>
	<!-- 弹窗div -->
	<div class="bg_model bg_model_xg" id="bg_model"></div>
</body>

<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/settingCenter/js/bootstrap.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/settingCenter/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
 
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
			var tid = $("#vcOrganId").val();
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
							 $("#vcOrganId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#compName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})
		
		//弹窗方法
		function openDialog(url){
			$("#bg_model").load(url);
			$(".bg_model").css("display","block");
			$(".bg_model").fadeTo(300,1);
			$("body").css({ "overflow": "hidden" });
		}
		
		/**
		 *  导出excel
		 */
		function export_1(){
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/user/hrAndFinanceCompany/_export1";
			form.method = "post";
			form.submit();
		}
		
		function query(){
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/user/hrAndFinanceCompany/getUserHrCompanys";
			form.method = "post";
			form.submit();
		}
</script>
</body>
</html>
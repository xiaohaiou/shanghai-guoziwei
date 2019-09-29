<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>重点基建工程</title>
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
		<h4>重点基建工程数据填报</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4">项目名称：
					<input type="text" name="pjName" value="${project.pjName }" />
			</span>
			<span class="col-md-4">
				<span class="search_title">项目单位： </span>
				<input type="hidden" name="pjDeptId" id="pjDeptId" value="${project.pjDeptId }" />
				<input name="pjDept" id="pjDept" value="${project.pjDept }" readonly title="${project.pjDept }">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
			</span>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_zdgcsjtb_query,')}">
					<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_zdgcsjtb_new,')}">
					<input type="button" value="新增" class="form_btn" id="addbtn" onclick="mload('${pageContext.request.contextPath}/project/_add')">
				</c:if>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>项目名称</th>
						<th>项目所在单位</th>
						<th>最近更新时间</th>
						<th>项目整体进度</th>
						<th>项目合同整体付款进度</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>${(msgPage.pageNum-1)*10+status.count}</td>
								<td style="text-align:left;">${sys.pjName}</td>
								<td style="text-align:left;">${sys.pjDept}</td>
								<td>${sys.lastModifyDate==null?sys.createDate:sys.lastModifyDate}</td>
								<td style="text-align:right;"><fmt:formatNumber  value="${sys.pjProgress/100}" pattern="#,##0%"></fmt:formatNumber></td>
								<td style="text-align:right;"><fmt:formatNumber  value="${sys.totalContractProgress/100}" pattern="#,##0%"></fmt:formatNumber></td>
								<td>${sys.createPersonName}</td>
								<td>${sys.reportPersonName}</td>
								<td>${sys.approvePersonName}</td>
								<td>
									<c:if test="${ sys.reportStatus==0}">
										<span style="color:#3366ff">未上报</span>
									</c:if>
									<c:if test="${ sys.reportStatus==1}">
										<span style="color:#ff9933">待审核</span>
									</c:if>
									<c:if test="${ sys.reportStatus==2}">
										<span style="color:#00cc66">已审核</span>
									</c:if>
									<c:if test="${ sys.reportStatus==3}">
										<span style="color:#ff399d">已退回</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_zdgcsjtb_view,')}">
										<a  href="javascript:;" id="qrybtn" onclick="viewValidate('${sys.id}')">查看</a>
									</c:if>
									<c:if test="${ sys.reportStatus != 1}">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_zdgcsjtb_edit,')}">
												<a  href="javascript:;" id="qrybtn2" onclick="editValidate('${sys.id}')">修改</a>
										</c:if>
									</c:if>
									<c:if test="${ sys.reportStatus == 0}">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_zdgcsjtb_report,')}">
												<a  href="javascript:;" id="qrybtn" onclick="reportValidate('${sys.id}')">上报</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_zdgcsjtb_delete,')}">
												<a  href="javascript:;" id="qrybtn" onclick="del('${sys.id}')">删除</a>
										</c:if>
									</c:if>
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
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
	
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

		function del(id){
			win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/project/_validate?id=" + id;
					$.post(url, function(data) {
						var project = eval("(" + data +")");
						if(project.isdel == 1){
							win.errorAlert("该数据已被删除",function(){
								_query();
							});
						}else if(project.reportStatus > 0){
							win.errorAlert("该数据已被操作",function(){
								_query();
							});
						}else{
							var url = "${pageContext.request.contextPath}/project/_del?id=" + id;
							$.post(url, function(data) {
								if(data == 'success'){
									win.successAlert('删除成功！');
									_query();
								}
								
							});
						}
					});
					
				}
			});
		}

		function viewValidate(id){
			var url = "${pageContext.request.contextPath}/project/_validate?id=" + id;
			$.post(url, function(data) {
				var project = eval("(" + data +")");
				if(project.isdel == 1){
					win.errorAlert("该数据已被删除",function(){
						_query();
					});
				}else{
					mload('${pageContext.request.contextPath}/project/_detail?id='+id);
				}
			});
		}
		function editValidate(id){
			var url = "${pageContext.request.contextPath}/project/_validate?id=" + id;
			$.post(url, function(data) {
				var project = eval("(" + data +")");
				if(project.isdel == 1){
					win.errorAlert("该数据已被删除",function(){
						_query();
					});
				}else if(project.reportStatus == 1){
					win.errorAlert("该数据已被操作",function(){
						_query();
					});
				}else{
					mload('${pageContext.request.contextPath}/project/_edit?id='+id);
				}
			});
		}
		
		function reportValidate(id){
			var url = "${pageContext.request.contextPath}/project/_validate?id=" + id;
			$.post(url, function(data) {
				var project = eval("(" + data +")");
				if(project.isdel == 1){
					win.errorAlert("该数据已被删除",function(){
						_query();
					});
				}else if(project.reportStatus != 0){
					win.errorAlert("该数据已被操作",function(){
						_query();
					});
				}else{
					mload('${pageContext.request.contextPath}/project/_reportDetail?id= '+id);
				}
			});
		}
		
	</script>
	<script type="text/javascript">
		var timeoutId
 		$('#pjDept').on('focus click',function(){
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
							 $("#pjDeptId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#pjDept").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 $("#pjDept").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})
		
		//清空
		$('.clear').on('click',function(){
			$("#pjDeptId").val("");
			$("#pjDept").val("");
			$("#pjDept").attr('title','');
		});
	</script>
</html>
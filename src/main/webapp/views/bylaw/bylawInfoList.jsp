<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>规章制度关联类别维护</title>
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
		<h4>规章制度关联类别维护</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4">
			<span class="search_title">发文单位：</span>
					<%-- <input type="text" name="orgNm" value="${bylawInfo.orgNm }" /> --%>
				<input type="hidden" id="org" name="org" value="${bylawInfo.org}" >
				<input name="orgNm" id="orgNm" value="${bylawInfo.orgNm}" readonly="readonly">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
			</span>
			<span class="col-md-4">
			<span class="search_title">制度标题：</span>
					<input type="text" name="fileName" value="${bylawInfo.fileName }" />
			</span>
			<span class="col-md-4">
			<span class="search_title">是否关联：</span>
				<select name="isLinked">
					<option value="">全部</option>
					<option value="1" <c:if test="${bylawInfo.isLinked == 1 }">selected="selected"</c:if>>是</option>
					<option value="0" <c:if test="${bylawInfo.isLinked == 0 }">selected="selected"</c:if>>否</option>
				</select>
			</span>
			<span class="col-md-4">
			<span class="search_title">是否废止：</span>
				<select name="fileIsAbolish">
					<option value="">全部</option>
					<option value="1" <c:if test="${bylawInfo.fileIsAbolish == 1 }">selected="selected"</c:if>>是</option>
					<option value="0" <c:if test="${bylawInfo.fileIsAbolish == 0 }">selected="selected"</c:if>>否</option>
				</select>
			</span>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_gzzdgllbwh_query,')==true }">
					<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_gzzdgllbwh_synchronize,')==true }">
					<input type="button" value="同步" class="form_btn" id="qrybtn" onclick="_syn()">
				</c:if>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>发文单位</th>
						<th>规章制度标题</th>
						<th>建立关联描述</th>
						<th>修改时间</th>
						<th>是否废止</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td style="width:5%">${(msgPage.pageNum-1)*10+status.count}</td>
								<td style="text-align:left; width:10%">${sys.orgNm}</td>
								<td style="text-align:left; width:10%">${sys.fileName}</td>
								<td style="text-align:left;width:55%">${sys.linkDiscription}</td>
								<td style="width:10%">${sys.lastModifyDate}</td>
								<td>
									<c:if test="${sys.fileIsAbolish == 0 }">否</c:if>
									<c:if test="${sys.fileIsAbolish == 1 }">是</c:if>
								</td>
								<td style="width:10%">
							     	<c:if test="${fn:contains(gzwsession_code,',bimWork_gzzdgllbwh_show,')==true }">
									     <a  href="javascript:;" id="qrybtn" onclick="viewValidate('${sys.id}')">查看</a>
									</c:if>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_gzzdgllbwh_relete,')==true }">
										<c:if test="${sys.isLinked != 1}">
											<a  href="javascript:;" id="qrybtn2" onclick="editValidate('${sys.id}')">关联</a>
										</c:if>
									</c:if>
									<c:if test="${sys.isLinked == 1}">
									    <c:if test="${fn:contains(gzwsession_code,',bimWork_gzzdgllbwh_replace,')==true }">
										    <a  href="javascript:;" id="qrybtn3" onclick="changeLinkValidate('${sys.id}')">替换</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_gzzdgllbwh_untie,')==true }">
										     <a  href="javascript:;" id="qrybtn4" onclick="clearLinkValidate('${sys.id}')">解绑</a>
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
		var timeoutId;
		$('#orgNm').on('focus click',function(){
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
					$("#org").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#orgNm").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		$('.clear').on('click',function(){
			$(this).siblings("input[name='orgNm']").val("");
			$(this).siblings("input[name='org']").val("");
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


		function viewValidate(id){
			mload('${pageContext.request.contextPath}/bylaw/bylawDetail?id='+id);
		}
		
		function editValidate(id){
			mload('${pageContext.request.contextPath}/bylaw/bylawInfoEdit?id='+id);
		}
		
		function changeLinkValidate(id){
			mload('${pageContext.request.contextPath}/bylaw/bylawChangeLink?id='+id);
		}
		function clearLinkValidate(id){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var url = "${pageContext.request.contextPath}/bylaw/bylawclearLink?id="+id;
			$.ajax({
				 url : url,  
			     type : "POST",  
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data){
			     	 $.unblockUI();
			     	 win.successAlert('保存成功！',function(){
						window.location.reload(true);
						
					 });
			     },
			     error : function(data) {
			     	$.unblockUI();
			     }  
			});
		}
		
		function _syn(){
			mload('${pageContext.request.contextPath}/bylaw/synPage');
		}
		
	</script>
	<script type="text/javascript">
		
	</script>
</html>
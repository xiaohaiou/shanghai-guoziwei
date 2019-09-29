<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>超期外部应收账款无催收进展一览</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>">
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
		<h4>超期外部应收账款无催收进展一览</h4>
		<div class="table_content">
		
		<form id="form1"  class="row">
		
			<span class="col-md-4">
				<span class="search_title">时间：</span>
					<input  type="text" style="text-align: center" name="date" value="${entityview.date }" readonly="true" class="time" />
			</span>
			<span class="col-md-4">
				<span class="search_title">核心企业：</span>
					<select  name="coreorg" class="selectpicker" id="selectpicker1">
						<option value=""  >全部</option>
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
								<option value="${result.id.nnodeId }"  <c:if test="${entityview.coreorg eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
			</span>
			<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_cqyszqzkwcsyl_wb_query,')==true }">	
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>

			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>上报年月</th>
						<th>所属核心业态</th>
						<th>填报公司名称</th>
						<th>期初债权金额(万元)</th>
						<th>期末债权金额(万元)</th>
						<th>超期金额(万元)</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					  <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber+ status.index + 1 }
								</td>
								<td>
									${sys.year}-${sys.month }
								</td>
								<td>
									${sys.coreorgname }
								</td>
								<td>
									${sys.debtorgname }
								</td>
								<td>
									<fmt:formatNumber value="${sys.beginningbalance/10000 }"  pattern="#,##0.00" />
								</td>
								<td>
									<fmt:formatNumber value="${sys.endingbalance/10000 }"  pattern="#,##0.00" />
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.overmoney/10000 }"  pattern="#,##0.00" />
								</td>
								<td>
									 <c:if test="${fn:contains(gzwsession_code,',bimWork_cqyszqzkwcsyl_wb_query,')==true }">	
									<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=modify&id=${sys.groupid}')" >查看</a>
									 </c:if>	
									
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="8" align="center">
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
	
		function exportfile()
		{
			document.getElementById('exportfile').click();
		}
	
		function checkdata(url)
		{
			mload(url);
		}
		
		$(' input.time').jeDate(
			{
				format:"YYYY-MM"
			}
		)
	
 		 var timeoutId
 		$('#debtorgname').on('focus click',function(){
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
							 $("#debtorg").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#debtorgname").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 $("#debtorgname").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})

	 	//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="debtorgname"]').val('');
			$("#debtorg").val("");
			$("#debtorgname").attr('title','');
		}); 

		
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}




		
		
	</script>
</html>
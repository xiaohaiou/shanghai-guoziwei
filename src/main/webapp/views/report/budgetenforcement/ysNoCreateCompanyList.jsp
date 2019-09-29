<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>公司未填报预算报表列表</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
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
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>预算未填报报表公司查询</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4">
				<span class="search_title">单位名称：</span>
				<input type="hidden" id="organalID" name="organalID" value="${organalID}" >
				<input name="organalname" id="checkedCompanyName" value="${organalname}" readonly title="${organalname}">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			    </div>
			    <i class="clear iconfont icon-guanbi"></i>
		    </span>
			<span class="col-md-4">
				<span class="search_title">时间：</span>
				<input  type="text" name="reportTime" value="${reportTime}" readonly="true" class="time" />
			</span>
			<span class="col-md-4">
				<span class="search_title">单体/汇总：</span>
				<select id="formKind" name="formKind">
					<option value="52004" <c:if test="${formKind == 52004}">selected = "selected"</c:if>>单体</option>
					<option value="52005" <c:if test="${formKind == 52005}">selected = "selected"</c:if>>汇总</option>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">公司类型：</span>
				<select id="CompanyKind" name="CompanyKind">
					<option value="">全部</option>
					<option value="50500" <c:if test="${CompanyKind == 50500}">selected = "selected"</c:if>>实体公司</option>
					<option value="50501" <c:if test="${CompanyKind == 50501}">selected = "selected"</c:if>>虚体公司</option>
					<option value="50502" <c:if test="${CompanyKind == 50502}">selected = "selected"</c:if>>壳公司</option>
				</select>
			</span>
			<span class="col-md-12" style="text-align: right">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yswcjgscx_query,')==true }">	
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yswcjgscx_export,')==true }">	
					<input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yswcjgscx_remind,')==true }">	<!-- bimWork_yswcjgscx_remind -->
					<input type="button" value="填报提醒" class="form_btn" id="qrybtn" onclick="_remind()">
				</c:if>			
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yswcjgscx_remind_all,')==true }">	<!-- bimWork_yswcjgscx_remind_all -->
					<input type="button" value="全选" class="form_btn" id="qrybtn" onclick="_remindAll()">
				</c:if>	
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yswcjgscx_disRemind_all,')==true }">	<!-- bimWork_yswcjgscx_disRemind_all -->
					<input type="button" value="不全选" class="form_btn" id="qrybtn" onclick="_disRemindAll()">
				</c:if>		
			</span>
			<input type="hidden" name="pageNums" value="${pageNums}"/>
			<input type="hidden" name="orgTemp" id="orgTemp" value="${orgTemp}"/>
			<input type="hidden" name="remindKind" id="remindKind" value="${remindKind}"/>
			<input type="hidden" name="remindAllKind" id="remindAllKind" value="${remindAllKind}"/>
			<input type="hidden" name="updateTime" id="updateTime" value="${updateTime}"/>
		</form>
		<form id="form_"></form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th width="20%">公司全称</th>
						<th width="20%">公司简称</th>
						<th>负责人</th>						
						<th>负责人账号</th>						
						<th width="20%">负责人邮箱</th>		
						<th width="10%">最后提醒时间</th>
						<th>加入本期提醒</th>
						<th>本期提醒</th>						
					</tr>
					<c:if test="${not empty requestScope.msgPage.list}">
						<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status1">
							<tr>
								<td>
									${recordNumber+ status1.index + 1 }
								</td>
								<td style="text-align: center">
									${sys[0]}
								</td>
								<td style="text-align: center">
									${sys[1]}
								</td>
								<td style="text-align: center">
									${sys[3]}
								</td>
								<td style="text-align: center">
									${sys[4]}
								</td>
								<td style="text-align: center">
									${sys[5]}
								</td>	
								<td style="text-align: center">
									${sys[6]}
								</td>	
								<td>
									<c:if test="${sys[8]==0}">
										Y
									</c:if>
								</td>									
								<td style="text-align: center">
									<c:if test="${fn:contains(gzwsession_code,',bimWork_yswcjgscx_remind_add_temp,')==true }">	<!-- bimWork_yswcjgscx_remind_add_temp -->
										<input type="button" value="+" class="form_btn" id="qrybtn" onclick="_addRemind('${sys[7]}','add')">
									</c:if>	
									<c:if test="${fn:contains(gzwsession_code,',bimWork_yswcjgscx_remind_remove_temp,')==true }">	<!-- bimWork_yswcjgscx_remind_remove_temp-->
										<input type="button" value="-" class="form_btn" id="qrybtn" onclick="_removeRemind('${sys[7]}')">
									</c:if>	
								</td>																													
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="3" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
	   $(' input.time').jeDate(
			{
				format:"YYYY-MM"
			}
		)
	
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}
	
		function _remind(){
			$("input[name='pageNums']").val('');
			$("input[name='reportTime']").val('${reportTime}');
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/reportbudgetenforcement/remindCompanyList";
			form.method = "post";
			form.submit();	
		}
	
		function _remindAll(){		
			var form = document.forms[0];
			$('#remindAllKind').val('addAll');
			form.action = "${pageContext.request.contextPath}/reportbudgetenforcement/remindAllCompanyList";
			form.method = "post";
			form.submit();			
		}

		function _disRemindAll(){		
			var form = document.forms[0];
			$('#remindAllKind').val('removeAll');
			form.action = "${pageContext.request.contextPath}/reportbudgetenforcement/remindAllCompanyList";
			form.method = "post";
			form.submit();			
		}
		function _addRemind(org,remindKind){		
			var form = document.forms[0];
			document.getElementById("orgTemp").value = org;
			document.getElementById("remindKind").value = remindKind;
			form.action = "${pageContext.request.contextPath}/reportbudgetenforcement/addRemindCompanyList";
			form.method = "post";
			form.submit();	
		}
	
		function _removeRemind(org){		
			_addRemind(org,'remove');	
		}	

	
	function _export()
		{
		
		 var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/reportbudgetenforcement/ysNoCreateCompanyListExport";
			form.method = "post";
			form.submit();
		}
		var timeoutId
 		$('#checkedCompanyName').on('focus click',function(){
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
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]){
							 $("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
							 $("#checkedCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 $("#checkedCompanyName").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});		
		})
		
		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="organalname"]').val('');
			$("#organalID").val("");
			$("#checkedCompanyName").attr('title','');
		});
	
	</script>
</html>
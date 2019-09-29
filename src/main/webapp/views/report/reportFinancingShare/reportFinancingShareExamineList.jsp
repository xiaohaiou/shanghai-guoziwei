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
		<title>共享类融资项目进度数据</title>
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
		<h4>共享类融资项目进度数据审核</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4">
				<span class="search_title">融资类别：</span>
					<select  name="category" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.financingCategory}" var="result">
							<option value="${result.id }"  <c:if test="${entityview.category == result.id}">selected="selected"</c:if>>${result.description }</option>
						</c:forEach>
					</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">审核状态：</span>
					<select  name="status" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.examstatustype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.status == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span>
	<!-- <span class="col-md-4">
			<span class="search_title">单位名称：</span>
					<select  name="coreOrg" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
								<option value="${result.id.nnodeId }"  <c:if test="${entityview.coreOrg eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
							</c:forEach>
					</select>
				
			</span> -->
			<span class="col-md-4">
				<input type="hidden" name="groupid" id="groupid" value="${groupid}">
				<input type="hidden" name="grouptype" id="grouptype" value="${grouptype}">
				<input type="hidden" id="operationType" value="${op }"> 
				<span class="search_title">单位名称:</span>
				<input name="checkedCompanyName" id="checkedCompanyName" value="${checkedCompanyName }" readonly="readonly">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
		
					</ul>
			   </div>
			   <input type="hidden" id="organalID" name="organalID" value="${organalID}" >
			</span>
			
			<span class="col-md-4">
				<span class="search_title" style="width:9em" >洽谈金融机构：</span>
					<input style="width:calc(100% - 10em)" type="text" id="financialInstitution" name="financialInstitution" value="${entityview.financialInstitution }" >
			</span>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_gxlrzxzSh_q,')==true }">	
					<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_gxlrzxzSh_k_e,')==true }">	
					<input type="button" value="一键审核" class="form_btn" id="qrybtn" onclick="keyExmine()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_gxlrzxzSh_hzcx,')==true }">
					<a  href="${mapurl}/g_query"  target="_blank"><input type="button" value="汇总查询"/></a>
				</c:if>	
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>单位名称</th>
						<th>洽谈金融机构</th>
						<th>类别</th>
						<th>已下账/规模（亿）</th>
						<th>综合成本</th>
						<th>最新更新时间</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					  <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber+ status.index + 1 }
								</td>
								<td style="text-align: left;">
									${sys.coreOrgname }
								</td>
								<td style="text-align: left;max-width:20em">
									${sys.financialInstitution }
								</td>
								<td style="text-align: center;max-width:8em">
									<c:forEach items="${requestScope.financingCategory}" var="result">
										<c:if test="${ sys.category==result.id}">
											${result.description }
										</c:if>
									</c:forEach>
									<%-- ${sys.category } --%>
								</td>
								<td style="text-align: right;max-width: 7em;">
									<fmt:formatNumber value="${sys.alreadyAccountAmounts }"  pattern="#,##0.0000" />/<fmt:formatNumber value="${sys.scale }"  pattern="#,##0.0000" />亿元 
								</td>
								<td style="text-align: center">
									${sys.comprehensiveFinancingCostRatio }
								</td>
								<c:choose>
									<c:when test="${empty sys.lastModifyDate}">
										<td><a href="javascript:void(0)" onclick="checkdata('${mapurl}/logView?id=${sys.id}',${sys.id })">${sys.createDate}</a></td>
									</c:when>
									<c:otherwise>
										<td><a href="javascript:void(0)" onclick="checkdata('${mapurl}/logView?id=${sys.id}',${sys.id })">${sys.lastModifyDate}</a></td>
									</c:otherwise>
								</c:choose>
								<td style="text-align: center">
									${sys.createPersonName }
								</td>
								<td style="text-align: center">
									${sys.reportPersonName }
								</td>
								<td style="text-align: center">
									${sys.auditorPersonName }
								</td>
								<td style="text-align: center">
									<c:if test="${ sys.status==50112}">
										<span style="color:#3366ff">${sys.statusName }</span>
									</c:if>
									<c:if test="${ sys.status==50113}">
										<span style="color:#ff9933">${sys.statusName }</span>
									</c:if>
									<c:if test="${ sys.status==50114}">
										<span style="color:#ff399d">${sys.statusName }</span>
									</c:if>
									<c:if test="${ sys.status==50115}">
										<span style="color:#00cc66">${sys.statusName }</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_gxlrzxzSh_q,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?id=${sys.id}',${sys.id })" >查看</a>
									</c:if>
									<c:if test="${sys.status==50113 }">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_gxlrzxzSh_e,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/exam?id=${sys.id}',${sys.id })" >审核</a>
										</c:if>
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
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/listPageCompanyTreeSelect.js"/>" type="text/javascript"></script>
		
	<script type="text/javascript">
	
	    /**
	     * 初始化财务树	
	     */
	    var oldorgan="";
 		var olddate="";
	    function initFinanceTree(checked_data,com_data){
			zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = treeObj.getNodes();
			
			//transformToArray()此方法获取所有节点（父节点和子节点）
    		var childrenNodes = treeObj.transformToArray(nodes);
    		if(childrenNodes[0]!=null)
    			treeObj.expandNode(childrenNodes[0],true);
	    	for(var i=0;i<childrenNodes.length;i++){
	    		for(var j=0;j<checked_data.length;j++){
	    			if(childrenNodes[i].id == checked_data[j].id){
	    				treeObj.expandNode(childrenNodes[i], true); //展开选中的  
                    	treeObj.checkNode(childrenNodes[i], true);
                    	//treeObj.updateNode(childrenNodes[i]);
	    			}
	    		}
	    	}
	    }

	    $(function () {
	    
		    if($("#operationType").val()=="new")
			{   
			
				$(' input.time').jeDate(
					{
						format:"YYYY-MM",
						choosefun:function(elem, val, date) {changedate(val)},
						clearfun:function(elem, val) {changedate("")},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
			  				okfun:function(elem, val, date) {}, 
					}
				)
				var checked_data = '${organalID}';
				var com_data = ${allCompanyTree};
				initFinanceTree(checked_data,com_data);
			
			}
			else{
			
				$("#checkedCompanyName").attr("readOnly","true");
			}
    	
    	});
	    
	   

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
	
	
		$(' input.time1').jeDate(
			{
				format:"YYYY-MM-DD",
			}
		)
		$(' input.time2').jeDate(
			{
				format:"YYYY-MM-DD",
			}
		)
	
		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#operateOrg").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#operateOrgname").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 $("#operateOrgname").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})

	
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}

		function checkdata(url,id)
			{
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
						}
						else
							win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
		
				  }
				});
			}

		function del(id){
			win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${mapurl}/delete?id=" + id;
					$.post(url, function(data) {
					var commitresult=JSON.parse(data);
						if(commitresult.result == true){
							win.successAlert('删除成功！');
							_query();
						}else
						{
							win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
						}
						
					});
				}
			});
		}

		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="operateOrgname"]').val('');
			$("#operateOrg").val("");
			$("#operateOrgname").attr('title','');
		});
		
		function keyExmine(){
			var form = document.forms[0];
			form.action = "/shanghai-gzw/reportFinancingShare/keyEamine";
			form.method = "post";
			form.submit();	
		
		}
		
		$(document).ready(function(){
		  var keyExminevalue = "${keyExmineValue}";
	  	  if(keyExminevalue == "keyExmineValue"){
	  	  	_query();
	  	  }	  	  
		}); 
		
	</script>
</html>
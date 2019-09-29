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
		<title>管理口径核算数据</title>
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
		<h4>管理口径核算数据审核</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<%-- <span class="col-md-4">
				<span class="search_title">单位名称：</span>
				<input type="hidden" id="organalID" name="org" value="${entityview.org }" >
				<input name="orgname" id="checkedCompanyName" value="${entityview.orgname }" readonly="readonly" title="${entityview.orgname}">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
			    
		   </span> --%>
		<%--    <span class="col-md-4">
				<span class="search_title">单位选择：</span>
					<select  name="org" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.allCompanys}" var="result">
							<option value="${result.id.nnodeId }"  <c:if test="${entityview.org eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
			</span>
		--%>	
			
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
			
			
			
			
			
			<%-- <span class="col-md-4">
				<span class="search_title">审核状态：</span>
					<select  name="status" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.examstatustype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.status == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span> --%>
			<span class="col-md-4">
				<span class="search_title">审核状态：</span>
					<select  name="status" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.examstatustype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.status == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">时间：</span>
				<input  type="text" value="${entityview.starttime }" name="starttime" readonly class="time1 time_short" />
				<input type="hidden" id="startyear" name="startyear" value="${entityview.startyear }">
				<input type="hidden" id="startmonth" name="startmonth" value="${entityview.startmonth }">
				至
				<input  type="text" value="${entityview.endtime }" name="endtime" readonly class="time2 time_short" />
				<input type="hidden" id="endyear" name="endyear" value="${entityview.endyear }">
				<input type="hidden" id="endmonth" name="endmonth" value="${entityview.endmonth }">
			</span>
			<%-- <span class="col-md-4">
				<span class="search_title">年份：</span>
					<input  type="text" name="year" value="${entityview.year }" readonly="true" class="time" />
			</span>
			<span class="col-md-4">
				<span class="search_title">填报频度：</span>
					<select  name="fre" class="selectpicker" id="fre" onchange="SetSecond() " >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.fretype}" var="result">
						
								<option value="${result.id }"  <c:if test="${entityview.fre == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span>
			
			<span class="col-md-4">
				<span class="search_title">月份/周数：</span>
					<select  name="fredata" class="selectpicker" id="fredata" >
				<option value=""  >全部</option>
						<c:forEach items="${requestScope.freDatatype}" var="result">
						
								<option value="${result.num }"  <c:if test="${entityview.fredata == result.num}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span> --%>
			
		
			<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_glkjhssh_query,')==true }">	
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>
					<c:if test="${fn:contains(gzwsession_code,',bimWork_glkjhssh_export,')==true }">
				        <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export()">
				    </c:if>
					 	<%-- <c:if test="${fn:contains(rolePagecodeNums,'sys_add')==true}"> --%>
							<%-- <input type="button" value="新增" class="form_btn" id="model_add" onclick="mload('${pageContext.request.contextPath}/reportgroup/newmodify')"> --%>
						<%-- </c:if> --%>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>时间</th>
						
						<th>单位名称</th>
						<th>总资产</th>
						<th>总负债</th>
						<th>净资产</th>
						<!-- <th>资本金</th> -->
						<th>EBITDA</th>
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
								<td>
									${sys.year }-${sys.month}
								</td>
								<td>
									${sys.orgname }
								</td>
								<td style="text-align: right">
								
									<fmt:formatNumber value="${sys.totalAssets }"  pattern="#,##0.000000" />亿元
								</td>
								<td style="text-align: right">
									<fmt:formatNumber value="${sys.totalLiabilities }"  pattern="#,##0.000000" />亿元
									
								</td>
								<td style="text-align: right">
									<fmt:formatNumber value="${sys.netAssets }"  pattern="#,##0.000000" />亿元
									
								</td>
								<td style="text-align: right">
							<%-- 	<fmt:formatNumber value="${sys.capital }"  pattern="#,##0.000000" />亿元 --%>
							         <fmt:formatNumber value="${sys.ebitda }"  pattern="#,##0.000000" />亿元
									
								</td>
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
									<c:if test="${fn:contains(gzwsession_code,',bimWork_glkjhssh_show,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=modify&id=${sys.id}',${sys.id })" >查看</a>
									</c:if>
									<c:if test="${sys.status==50113 }">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_glkjhssh_exam,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/exam?id=${sys.id}',${sys.id })" >审核</a>
										</c:if>
									</c:if>
									<c:if test="${sys.status==50115 }">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_glkjhssh_return,')==true }">
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/exam?id=${sys.id}',${sys.id })" >回退</a>
										</c:if>
									</c:if>
									
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="12" align="center">
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
				
				console.log(checked_data);
				console.log(com_data);
				initFinanceTree(checked_data,com_data);
			
			}
			else{
			
				$("#checkedCompanyName").attr("readOnly","true");
			}
    	
    	});
	
	
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

 		/* var timeoutId
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
	    }); */
	
	
		$(' input.time1').jeDate(
			{
				format:"YYYY-MM",
				choosefun:function(elem, val, date) {changeDate1(val)},
				clearfun:function(elem, val) {changeDate1("")},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {},
			}
		)
		$(' input.time2').jeDate(
			{
				format:"YYYY-MM",
				choosefun:function(elem, val, date) {changeDate2(val)},
				clearfun:function(elem, val) {changeDate2("")},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {},
			}
		)
		
		function _export(){
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/reportManageAdjust/export";
			form.method = "post";
			form.submit();
		}
		
		function changeDate1(val){
			if(val == ""){
				$("#startyear").val("");
				$("#startmonth").val("");
			}else{
				var year = parseInt((val.split("-"))[0]);
				$("#startyear").val(year);
				var month = parseInt((val.split("-"))[1]);
				$("#startmonth").val(month);
			}
		}
		function changeDate2(val){
			if(val == ""){
				$("#endyear").val("");
				$("#endmonth").val("");
			}else{
				var year = parseInt((val.split("-"))[0]);
				$("#endyear").val(year);
				var month = parseInt((val.split("-"))[1]);
				$("#endmonth").val(month);
			}
		}
	
		/* $("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#checkedCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 $("#checkedCompanyName").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		}) */

	 
		/* function SetSecond() {
			var type2 = document.getElementById("fredata");
			$("#fredata").show();
			type2.options.length = 0;
			Query( $("#fre").val(), "fredata");
		} */	
		
		/* var count_query = 0
		function Query(b, c) {
			var this_query = ++count_query;
			$.ajax({
				url : '${pageContext.request.contextPath}'+"/reportManageAdjust/_childtype",
				type : "POST",
				dataType:"json",
				data : {
					val : b,
				},
				success : function(array) {
					if(this_query!==count_query) return
					var dom = document.getElementById(c);
					if (array.length == 0) {
						$("#" + c).hide();
					}
					dom.innerHTML = "";
					var allopt = document.createElement("option");
						allopt.value = "";
						allopt.innerText = "全部";
						dom.appendChild(allopt);
					for ( var i = 0; i < array.length; i++) {
						var opt = document.createElement("option");
						opt.value = array[i].num;
						opt.innerText = array[i].description;
						dom.appendChild(opt);
					} */
					/* $(dom).selectpicker('refresh') */
				/* }
			});
		} 	 */
		
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}

//清空
		/* $('.clear').on('click',function(){
			$(this).siblings('input[name="orgname"]').val('');
			$("#organalID").val("");
			$("#checkedCompanyName").attr('title','');
		}); */
		
	</script>
</html>
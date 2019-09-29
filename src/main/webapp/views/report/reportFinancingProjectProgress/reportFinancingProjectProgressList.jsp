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
		<title>融资项目进度数据</title>
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
	<!-- 融资项目进度数据填报导出按钮 -->
		<h4>融资项目进度数据填报</h4>
		<div class="table_content">
		<form id="form1"  class="row"  >
			<input type = "hidden" id ="orgType" name="orgType">
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
				<span class="search_title" style="padding: 0 !important;width: 8em;margin-left: -1em;">预计下账日期：</span>
				<input  type="text" value="${entityview.starttime }" name="starttime" readonly class="time1 time_short" />
				至
				<input  type="text" value="${entityview.endtime }" name="endtime" readonly class="time2 time_short" />
			</span>
			<span class="col-md-4">
				<span class="search_title">项目进展：</span>
					<select  name="projectProgress" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.projectProgressType}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.projectProgress == result.id}">selected="selected"</c:if>>${result.description }</option>
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
		<!--  	<span class="col-md-4">
				<span class="search_title">单位名称：</span>
					<select  name="coreOrg" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
								<option value="${result.id.nnodeId }"  <c:if test="${entityview.coreOrg eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
							</c:forEach>
					</select>
			</span>
			-->
			
			<span class="col-md-4">
				<input type="hidden" name="groupid" id="groupid" value="${groupid}">
				<input type="hidden" name="grouptype" id="grouptype" value="${grouptype}">
				<input type="hidden" id="operationType" value="${op }"> 
				<span class="search_title">单位名称:</span>
				<input name="checkedCompanyName" id="checkedCompanyName" value="${checkedCompanyName}" readonly="readonly">
				<%-- <input type="textarea"  style="visibility:hidden;" id="allNotInIds" value="${allNotInIds}"/> --%>
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
		
					</ul>
			   </div>
			   <input type="hidden" id="organalID" name="organalID" value="${organalID}" >
			</span>
					
			<span class="col-md-4">
				<span class="search_title">操作主体：</span>
				<%-- <input type="hidden" id="operateOrg" name="operateOrg" value="${entityview.operateOrg }" > --%>
				<input name="operateOrgname" id="operateOrgname" value="${entityview.operateOrgname }" >
		    </span>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_q,')==true }">	
					<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_n,')==true }">	
					<input type="button" value="新增" class="form_btn" id="addbtn" onclick="mload('${mapurl}/newmodify')">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_dr,')==true }">
					<input type="button" value="导入" class="form_btn" id="addbtn" onclick="mload('${mapurl}/report')">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_dc,')==true }">
					<input type="button" value="导出" class="form_btn" id="addbtn" onclick="_export(1)">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_dcmb,')==true }">
					<input type="button" value="导出模板" class="form_btn" id="addbtn" onclick="exportout()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_hzcx,')==true }">
					<input type="button" value="汇总查询" class="form_btn" id="addbtn" onclick="mload('${mapurl}/g_query')">
				</c:if>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>单位名称</th>
						<th>操作主体</th>
						<th>已下账/规模(亿元)</th>
						<th>计划下账日期</th>
						<th>项目审批进展</th>
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
								<td style="text-align: left;">
									${sys.operateOrgname }
								</td>
								<td style="text-align: right">
									<fmt:formatNumber value="${sys.alreadyAccountAmounts }"  pattern="#,##0.0000" />/<fmt:formatNumber value="${sys.scale }"  pattern="#,##0.0000" />
								</td>
								<td>
									${sys.expectAccountDate }
								</td>
								<td style="text-align: center">
									<c:if test="${ sys.projectProgress==300}">
										<span style="color:#3366ff">${sys.projectProgressName }</span>
									</c:if>
									<c:if test="${ sys.projectProgress==301}">
										<span style="color:#ff9933">${sys.projectProgressName }</span>
									</c:if>
									<c:if test="${ sys.projectProgress==302}">
										<span style="color:#ff399d">${sys.projectProgressName }</span>
									</c:if>
									<c:if test="${ sys.projectProgress==303}">
										<span style="color:#00cc66">${sys.projectProgressName }</span>
									</c:if>
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
									<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_q,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?id=${sys.id}',${sys.id })" >查看</a>
									</c:if>
									<c:if test="${sys.status==50114 || sys.status==50112 || sys.status==50115}">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_e,')==true }">	
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/newmodify?op=modify&id=${sys.id}',${sys.id })" >修改</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_r,')==true }">	
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/postexam?op=report&id=${sys.id}',${sys.id })">上报</a>
										</c:if>							
										<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_d,')==true && sys.status!=50115}">
											<a href="javascript:void(0)" onclick="del('${sys.id}')" >删除</a>
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
	<%-- <script src="<c:url value="/js/listPageCompanyTreeSelect.js"/>" type="text/javascript"></script>  --%>
	<script type="text/javascript">
	
		/*
		 * listPageCompanyTreeSelect.js
		 * 公共js;用于列表页面多选公司，并查询
		 * 涉及页面元素：
		 * 	#checkedCompanyName 选择的公司名称
		 * 	.com_ztree_box  存放z-tree的div
		 * 	#com_ztree 公司树
		 * 	#organalID  选择公司的nodeId 通过逗号分隔
		 * 	#checkedCompanyName 选择公司的名称 通过逗号进行分隔
		 * 	.clear   清除选择按钮
		 * 	#allCompanyTree 财务树公司所有数据
		 * 
		 * 以预算报表上报列表页面为例
		 * 
		 */

		//设置禁用的复选框节点  
        function setDisabledNode(id){ 
		    var treeObj = $.fn.zTree.getZTreeObj("com_ztree");  
            var allNotInIds = ${allNotInIds}; 
            var disabledNode;
            for(var n=0;n<allNotInIds.length;n++){
            	if(allNotInIds[n]==id){
            	   console.log(id);
		           disabledNode = treeObj.getNodeByParam("id", id); 
		           treeObj.setChkDisabled(disabledNode, true); 
            	}
            }
        } 

		var timeoutId
		$('#checkedCompanyName').on('click',function(){
			//console.log($(this).next('.com_ztree_box'));
			$(this).next('.com_ztree_box').css('display','block');
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
				enable:true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "s", "N": "s"}
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			},
			callback: {
				onCheck: zTreeOnCheck
			}
		};
		/**
		*  勾选树节点时触发事件
		*/
		   function zTreeOnCheck(event, treeId, treeNode){
		   	var nodes = $.fn.zTree.getZTreeObj("com_ztree").getCheckedNodes(true);//获取勾选的节点
			var comIds = '';
			var comName = '';
			for(var i = 0; i < nodes.length; i ++){
				comIds += nodes[i].id + ',';
				comName += nodes[i].name + ',';
			}
			if( comIds != undefined && comName != undefined){
				comIds = comIds.substring(0, comIds.length-1);
				comName = comName.substring(0, comName.length-1);
			}
			$("#organalID").val(comIds);
			 $("#checkedCompanyName").val(comName);
			 $("#checkedCompanyName").attr('title',comName);
		   }
		

		
		/**
			展开上级节点
		*/
		function expandParent(treeObj,fbNode){
			treeObj.expandNode(fbNode,true);
			var pfbNode = treeObj.getNodeByParam("id", fbNode.pId);
			if(pfbNode != null){
				expandParent(treeObj,pfbNode);
			}
		}
		/**
		 * 清空
		 */
		$('.clear').on('click',function(){
			$(this).siblings('#checkedCompanyName').val('');
			$("#organalID").val("");
			$("#checkedCompanyName").attr('title','');
			$.fn.zTree.getZTreeObj("com_ztree").checkAllNodes(false);//取消勾选 全部节点
		});
			
	
 
	
	
		/**
	     * 初始化财务树	
	     */
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
	    		//这里设置初始化财务树下拉框不可选属性
	    		setDisabledNode(childrenNodes[i].id);
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
	
		function _query()
		{
			var form = document.forms[0];
			form.target = "_self";
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}
		
		function _export(type){
			var form = document.forms[0];
			form.target = "_self";
			form.action = "${exporturl}";
			form.method = "post";
			if(type == 0)
				$("#orgType").val("0");
			form.submit();
			/* var checkurl = "${exporturl}?id=" + id;
			window.open(checkurl); */
		}
		
		function exportout(){
			var form  = document.forms[0];
			var _url  = "WEB-INF/template/reportfinancingprojectprogresslist/reportfinancingprojectprogresslist.xlsx";
			var _name = "reportfinancingprojectprogresslist.xlsx";
			
			with (form) {
				form.action = '${pageContext.request.contextPath}/reportcommon/downloadtemplet?_url='+_url+'&_name='+_name;
				form.method = "post";
				form.submit();
			}
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
	</script>
</html>
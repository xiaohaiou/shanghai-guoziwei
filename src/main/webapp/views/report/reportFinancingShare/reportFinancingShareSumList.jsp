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
		<title>共享项目最新汇总数据查询</title>
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
		<h4>共享项目最新汇总数据查询</h4>
		<div class="table_content">
		<form id="form1"  class="row"  >
			<input type = "hidden" id ="orgType" name="orgType">
		<!-- 	<span class="col-md-4">
				<span class="search_title">单位名称：</span>
					<select  name="coreOrg" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
								<option value="${result.id.nnodeId }"  <c:if test="${entityview.coreOrg eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
							</c:forEach>
					</select>				
			</span> -->
			
			<label class="w20"><span class="required"> * </span>单位名称:</label>
			<span class="w25"">
				<input type="hidden" name="groupid" id="groupid" value="${groupid}">
				<input type="hidden" name="grouptype" id="grouptype" value="${grouptype}">
				<input type="hidden" id="operationType" value="${op}"> 
				<input name="checkedCompanyName" id="checkedCompanyName" value="${checkedCompanyName}" style="width:200px" readonly="readonly"/>
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:40%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			   </div>
			   <input type="hidden" id="organalID" name="organalID" value="${organalID}" >
			</span>
			
			
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_gxlrzxzCx_q,')==true }">	
					<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_gxlrzxzCx_dc,')==true }">
					<input type="button" value="导出" class="form_btn" id="addbtn" onclick="_export(1)">
				</c:if>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>单位名称</th>
						<th>项目类型</th>
						<th>操作条件</th>
						<th>用途介绍</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					  <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber+ status.index + 1 }
								</td>
								<td style="text-align: center;"><!-- left -->
									${sys.coreOrgname }
								</td>
								<td style="text-align: center;max-width:20em">
									${sys.categoryName }
								</td>
								<td style="text-align: center;max-width:20em"><!-- left -->
									${sys.hypothecationInformation }
								</td>
								<td style="text-align: center;max-width:20em"><!-- left -->
									${sys.purposeToIntroduce }
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="5" align="center">
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
	
	$("#operate_com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("operate_com_ztree").getSelectedNodes()[0])
						{
							 $("#operateOrg").val($.fn.zTree.getZTreeObj("operate_com_ztree").getSelectedNodes()[0].id)
							 $("#operateOrgname").val($.fn.zTree.getZTreeObj("operate_com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})
		
		$("#financing_com_ztree").click(function(){
			setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("financing_com_ztree").getSelectedNodes()[0])
				{
					 $("#financingOrg").val($.fn.zTree.getZTreeObj("financing_com_ztree").getSelectedNodes()[0].id)
					 $("#financingOrgname").val($.fn.zTree.getZTreeObj("financing_com_ztree").getSelectedNodes()[0].name);
				}
		   	});
			
		})
		
		var timeoutId
 		$('#operateOrgname').on('focus click',function(){
 			$(this).next('.com_ztree_box').css('display','block')
 		}).parent().on('mouseenter',function(){
 			clearTimeout(timeoutId)
 		}).on('mouseleave',function(){
 			clearTimeout(timeoutId)
 			timeoutId = setTimeout(function(el){
 				$(el).find('.com_ztree_box').css('display','none')
 			},3e2,this);
 		})
 		var timeoutId1
 		$('#financingOrgname').on('focus click',function(){
 			$(this).next('.com_ztree_box').css('display','block')
 		}).parent().on('mouseenter',function(){
 			clearTimeout(timeoutId1)
 		}).on('mouseleave',function(){
 			clearTimeout(timeoutId1)
 			timeoutId1 = setTimeout(function(el){
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
	    	var zTreeObj = $.fn.zTree.init($("#operate_com_ztree"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj = $.fn.zTree.getZTreeObj("operate_com_ztree");
			var nodes = treeObj.getNodes();
			
			//transformToArray()此方法获取所有节点（父节点和子节点）
    		var childrenNodes = treeObj.transformToArray(nodes);
    		if(childrenNodes[0]!=null)
    			treeObj.expandNode(childrenNodes[0],true);
	    });
	    $(function () {
		     //所有企业数据	
		    var com_data = ${allCompanyTree};
			//加载列表公司选择
	   		var zTreeObj1 = $.fn.zTree.init($("#financing_com_ztree"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj1 = $.fn.zTree.getZTreeObj("financing_com_ztree");
			var nodes1 = treeObj1.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes1 = treeObj1.transformToArray(nodes1);
	   		if(childrenNodes1[0]!=null){
	   			treeObj1.expandNode(childrenNodes1[0],true);
   			}
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
			else
			{
			
				$("#checkedCompanyName").attr("readOnly","true");
			}
	    	
	    	
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
	    		for(var j=0;j<checked_data.length;j++){
	    			if(childrenNodes[i].id == checked_data[j].id){
	    				treeObj.expandNode(childrenNodes[i], true); //展开选中的  
                    	treeObj.checkNode(childrenNodes[i], true);
                    	//treeObj.updateNode(childrenNodes[i]);
	    			}
	    		}
	    	}
	    }

		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#checkedCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 if(oldorgan!=$("#organalID").val())
							 	initNodeList("",1);
							 oldorgan=$("#organalID").val();
						}
			   	});
			
		})
	
	
	
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
			form.target = "_blank";
			form.action = "${exporturl}";
			form.method = "post";
			if(type == 0)
				$("#orgType").val("0");
			form.submit();
			/* var checkurl = "${exporturl}?id=" + id;
			window.open(checkurl); */
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
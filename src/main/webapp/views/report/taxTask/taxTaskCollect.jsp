<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>节税任务数据汇总查看</title>
	<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
	<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
	<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
	<!-- 库|插件 -->
	<!-- 新加样式 -->
	<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<style>
		.search_title {
		    font-weight:bold;
		}
		#company{
			width:16em;
		}
		@media screen and (max-width: 1630px){
			.clear {
			    right: -16em;
			    width: auto;
			    top: 2px
			}
		}
	</style>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>节税任务数据汇总查看
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<div class="model_body">
		<form:form id="form2" modelAttribute="entityview" >	
			<div class="label_inpt_div">
				<div class="model_part">
			
					<label class="w20"><span class="required"> <font color=red>*</font> </span>年份:</label>
					<input  type="text" name="year" value="${entity.year}" readonly="true" class="w25 time" check="NotEmpty" />
<%-- 					<label class="w20"><span class="required"> <font color=red>*</font> </span>单位名称:</label>
					<input type="hidden" id="organalId" name="organalId" value="${entityview.organalId }" >
					<input class="w25" name="company" id="company" value="${entityview.company }" readonly title="${entityview.company } " check="NotEmpty"> --%>
					
				<label class="w20"><span class="required"> * </span>纳税数据上报单位:</label>
				<span class="w25">
					<input type="hidden" id="organalID" name="org" value="${entity.org }" >
					<textarea style="display:none;" id="allCompanyTree" >${allCompanyTree}</textarea>
					<input id="company" name="orgName" style="width: 100% !important;" id="company" value="${entity.orgName }" check="NotEmpty_string_._._._._." readonly>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
						</ul>
					</div>
				</span>
				   <div class="model_btn">
						<input type="button" value="汇总查看" class="form_btn" id="exportbtn" onclick="_collect()">
				        <input name="id" type="hidden"  value="${entityview.id}"  />
				        <input name="status" type="hidden"  value="${entityview.status}"  />
				        <input name="isdel" type="hidden"  value="${entityview.isdel}"  />
				        <input name="createPersonId" type="hidden"  value="${entityview.createPersonId}"  />
				        <input name="createPersonName" type="hidden"  value="${entityview.createPersonName}"  />
				        <input name="createDate" type="hidden"  value="${entityview.createDate}"  />
				        <input name="parentorg" type="hidden"  value="${entityview.parentorg}"  />
					</div> 
				</div>	
				<div>	
					<h3 class="data_title1">汇总数据</h3>
						<div class="model_part">
							<label class="w20"><font color=red>*</font>年度节税汇总目标(万元):</label>
							<input name="taxPlan"  readonly="readonly"  id ="taxPlan" class="w25"  value='<fmt:formatNumber  value="${entityview.taxTask}" pattern="0.00"/>'/>					        
							<label class="w20"><font color=red>*</font>税收筹划年度汇总目标(万元):</label>
					        <input name="taxPlan"  readonly="readonly" id ="taxPlan" class="w25"  value='<fmt:formatNumber  value="${entityview.taxPlan}"pattern="0.00"/>' /> 
					        
					        <label class="w20"><font color=red>*</font>优惠政策申请年度节税汇总目标(万元):</label>
					        <input name="favouredPolicy" readonly="readonly"  id ="favouredPolicy" class="w25"  value='<fmt:formatNumber  value="${entityview.favouredPolicy}" pattern="0.00"/>' /> 
							<label class="w20"><font color=red>*</font>税收返还年度汇总目标(万元):</label>
					        <input name="taxReturn"  readonly="readonly" id ="taxReturn" class="w25"  value='<fmt:formatNumber  value="${entityview.taxReturn}" pattern="0.00"/>' /> 
					        <label class="w20"><font color=red>*</font>非税收返还年度汇总目标(万元):</label>
					        <input name="inTaxReturn" readonly="readonly"  id ="inTaxReturn" class="w25"  value='<fmt:formatNumber  value="${entityview.inTaxReturn}" pattern="0.00"/>' /> 
							 <div class="tablebox">
								      <table>
								            <tr class="table_header">
								                 <th>单位</th>
								                 <th>年度节税汇总目标(万元)</th>
								                 <th>税收筹划年度汇总目标(万元)</th>
								                 <th>优惠政策申请年度节税汇总目标(万元)</th>
								                 <th>税收返还年度汇总目标(万元)</th>
								                 <th>非税收返还年度汇总目标(万元)</th>
								            </tr>
								             <c:forEach items="${ requestScope.entityviews}" var="list" varStatus="status">
							                       <tr>
							                            <td>${list[0]}</td>
							                            <td><fmt:formatNumber type="number" value="${list[1]}" pattern="#.00"/></td>
							                          	    <td><fmt:formatNumber type="number" value="${list[2]}" pattern="#.00"/></td>
							                          	    <td><fmt:formatNumber type="number" value="${list[3]}" pattern="#.00"/></td>
							                          	    <td><fmt:formatNumber type="number" value="${list[4]}" pattern="#.00"/></td>
							                          	    <td><fmt:formatNumber type="number" value="${list[5]}" pattern="#.00"/></td>
							                       </tr>
							                  </c:forEach>
								      </table>
								</div>
						</div>
						
					<div class="model_btn">
<!-- 						<button class="btn btn-primary model_btn_ok" id="commit_1">保存</button>
						<button class="btn btn-primary model_btn_ok" id="commit_2">保存并上报</button> -->
						<button class="btn btn-default model model_btn_close">关闭</button>
						
					</div>
				</div>
			</div>
		</form:form> 
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		// 组织机构树
		var timeoutId;
		$("#company").on('focus click',function(){
			console.log($(this).next('.com_ztree_box'));
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
				chkboxType: { "Y": "s", "N": "s" }
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
			comIds = comIds.substring(0, comIds.length-1);
			comName = comName.substring(0, comName.length-1);
			$("#organalID").val(comIds);
			 $("#company").val(comName);
			 $("#company").attr('title',comName);
		   }
		
		  $(function () {
			     //所有企业数据	
		    var com_data = eval("("+$("#allCompanyTree").val()+")");
			zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
			//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
			var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = treeObj.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
			var childrenNodes = treeObj.transformToArray(nodes);
			if(childrenNodes[0]!=null)
				treeObj.expandNode(childrenNodes[0],true);
			//反绑	
			var ids = $("#organalID").val();
			if(ids != undefined && ids != ''){
				var idArray = ids.split(",");
				for(var i = 0; i < idArray.length; i++){
					var fbNode = treeObj.getNodeByParam("id", idArray[i]);
					expandParent(treeObj, fbNode);
					fbNode.checked = true;
					treeObj.updateNode(fbNode,false );
				}
			}	
		});
		
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
		$(".clear").on('click',function(){
			$(this).parent().siblings('input[name="company"]').val('');
			$("organalId").val("");
		})
		
		$('input.time').jeDate({
			format:"YYYY"
		});
		function checkcommit() {
			return true;
		};				
		$("#commit_1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var op = '${requestScope.op}';
			var url="${pageContext.request.contextPath}/taxTask/saveOrUpdate?op=save";
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     $.unblockUI();
			     var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('保存成功！',function(){
								parent.location.reload(true);
								parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr);
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
			return false;
		});
		
		
		function _collect()
		{
		if($(":input[name='keleyi']").val()==''||$("#company").val()==''){
		return false
		}
			var form = document.forms[0];
			form.action = "${collecturl}";
			form.method = "post";
			form.submit();
		}
		$("#commit_2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/taxTask/saveAndReport?op=saveR";
			
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     $.unblockUI();
			     var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('上报成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr);
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
			return false;
		});
		var example = $(".expmale");
		example.removeClass("example").remove();
		$(".btn.new").click(function(){
			$("table tbody").append(example.clone());
		});
		$("body").on("click","i.del.btn",function(){
		});
		//关闭弹窗
		$(".model_btn_close").click(function (){
		　	parent.mclose();
			return false;
		});
/* 		$("#sumNowpay").change(function(){
			calrate();
		});	
		$("#sumPrepay").change(function(){
			calrate();
		});	
	
		function calrate() {
			var sumNowpay=$("#sumNowpay").val();
			var sumPrepay=$("#sumPrepay").val();

		} */
		//关闭弹窗
		
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
	</script>
</html>
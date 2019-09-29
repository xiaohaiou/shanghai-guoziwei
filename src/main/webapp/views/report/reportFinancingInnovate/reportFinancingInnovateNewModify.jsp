<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>创新类融资项目进展数据</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
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
			.tablebox table tr:first-child {
			    background: rgba(0, 0, 0, 0);
			}
			.tablebox table tr:first-child:hover {
			    background: #f2f8ff;
			}
		</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改创新类融资项目进展数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增创新类融资项目进展数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
		
	</c:choose>
	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<form:hidden path="id"/>
			<div class="model_part">
			<!-- 	<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25">
					<select  name="coreOrg" id="coreOrg" class="selectpicker" onchange="getCoreOrgname()">
						<option value=""  ></option>
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
							<option value="${result.id.nnodeId }"  <c:if test="${entityview.coreOrg eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
				</span>
				 -->
				
				<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25"">
					<input type="hidden" name="groupid" id="groupid" value="${groupid}">
					<input type="hidden" name="grouptype" id="grouptype" value="${grouptype}">
					<input type="hidden" id="operationType" value="${op}"> 
					<input name="checkedCompanyName" id="checkedCompanyName" value="${checkedCompanyName}" with="200px" readonly="readonly"/>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				   </div>
				   <input type="hidden" id="organalID" name="organalID" value="${organalID}" >
				</span>
				
				
				
				
				
				<form:hidden path="coreOrgname" id="coreOrgname"/>
				<label class="w20"><span class="required"> * </span>项目名称:</label>
				<form:input class="w25" path="projectName" id="projectName" check="NotEmpty_string_._._._._."/>
				<label class="w20" style="vertical-align: top;"><span class="required"> * </span>抵质押信息:</label>
				<span class="w70 "><textarea  rows="5" cols="50" name="hypothecationInformation" id="hypothecationInformation" >${entityview.hypothecationInformation}</textarea></span>
				
				<label class="w20"><span class="required"> * </span>模式:</label>
				<form:input class="w25" path="pattern" id="pattern" check="NotEmpty_string_._._._._."/>
				<%-- <span class="w25">
					<select  name="pattern" id="pattern" class="selectpicker" >
					<option value=""  ></option>
						<c:forEach items="${requestScope.financingPattern}" var="result">
							<option value="${result.id }"  <c:if test="${entityview.pattern == result.id}">selected="selected"</c:if>>${result.description }</option>
						</c:forEach>
					</select>
				</span> --%>
				<label class="w20"><span class="required"> * </span>融资主体:</label>
				<form:input class="w25" path="financialInstitution" id="financialInstitution" check="NotEmpty_string_._._._._."/>
				<label class="w20"><span class="required"> * </span>规模(亿元):</label>
				<form:input class="w25" path="scale" id="scale" check="NotEmpty_double_16_4_0+_._."/>
				<label class="w20"><span class="required"> * </span>期限(月):</label>
				<form:input class="w25" path="term" id="term" check="NotEmpty_int_16_._0+_._."/>
				<label class="w20"><span class="required"> * </span>项目进展:</label>
				<span class="w25">
					<select  name="projectProgress" id="projectProgress" class="selectpicker" >
					<option value=""  ></option>
						<c:forEach items="${requestScope.projectProgressType}" var="result">
							<option value="${result.id }"  <c:if test="${entityview.projectProgress == result.id}">selected="selected"</c:if>>${result.description }</option>
						</c:forEach>
					</select>
				</span>
				<br/>
				<label class="w20" style="vertical-align: top;"><span class="required"> * </span>目前存在的问题:</label>
				<span class="w70 "><textarea  rows="5" cols="50" name="problem" id="problem" >${entityview.problem}</textarea></span>
				<label class="w20"><span class="required"> * </span>综合成本:</label>
				<form:input class="w25" path="comprehensiveFinancingCostRatio" id="comprehensiveFinancingCostRatio" check="NotEmpty_string_._._._._."/>
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20" style="vertical-align: top;"><span class="required"> * </span>操作主体:</label>
				<span class="w70 "><textarea  rows="5" cols="50" name="operationDepartment" id="operationDepartment" >${entityview.operationDepartment}</textarea></span>
				
				<label class="w20" style="vertical-align: top;"><span class="required"> * </span>融资结构:</label>
				<span class="w70 "><textarea  rows="5" cols="50" name="financingStructure" id="financingStructure" >${entityview.financingStructure}</textarea></span>
				<label class="w20" style="vertical-align: top;"><span class="required"> * </span>融资进展:</label>
				<span class="w70 "><textarea  rows="5" cols="50" name="financingProgress" id="financingProgress" >${entityview.financingProgress}</textarea></span>
				<label class="w20"><span class="required"> * </span>预计下账时间:</label>
				<input class="w25 date" type="text" id="expectAccountDate" name="expectAccountDate" value="${entityview.expectAccountDate }" readonly="true" check="NotEmpty_string_._._._._." class="date" />
				<label class="w20">已下账金额(亿元):</label>
				<form:input class="w25" path="alreadyAccountAmounts" id="alreadyAccountAmounts" check="._double_16_4_0+_._."/>
				<label class="w20"><span class="required"> * </span>责任人:</label>
				<form:input class="w25" path="personLiable" id="personLiable" check="NotEmpty_string_._._._._."/>
				<label class="w20"><span class="required"> * </span>经办人:</label>
				<form:input class="w25" path="operator" id="operator" check="NotEmpty_string_._._._._."/>
				<label class="w20"><span class="required"> * </span>经办人联系方式:</label>
				<form:input class="w25" path="operatorNum" id="operatorNum" check="NotEmpty_string_._._._._."/>
			</div>
			<h3 class="data_title1">附件列表
				<a class="model_btn_plus" onclick="addEnclosure()">+</a>
			</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="enclosureTb">
						<thead>
							<tr class="table_header">
								<th>附件序号</th>
								<th>附件选择</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty requestScope.enclosureList }">
								<c:forEach items="${requestScope.enclosureList }" var="node" varStatus="status">
									<tr>
										<td class="enclosure">附件${status.count }</td>
										<td>
											<a href="${pageContext.request.contextPath}/${node.enclosureAddress}" target="_blank">${node.enclosureName }</a>
											<input type="file" name="enclosure" >
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty requestScope.enclosureList}">
								<tr>
									<td class="no-enclosure" colspan="2" align="center">
										无附件
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<c:if test="${ !empty entityExamineviewlist  }">
				<h3 class="data_title1">审核意见</h3>
				<div class="model_part">
					<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
						<label class="w20">审核人:</label>
						<label class="w25 setleft">${ sys.createPersonName}</label> 
						<label class="w20">审核时间:</label>
						<label class="w25 setleft">${ sys.createDate}</label> 
						<label class="w20">审核结果:</label>
						<label class="w25 setleft">${ sys.examresultName}</label> 
						<label class="w20"></label>
						<label class="w25 setleft"></label> 
						<label class="w20" style="vertical-align:top;">审核意见:</label>
						<label class="w75 setleft" style="word-wrap:break-word;">${ sys.examinestr}</label> 
					</c:forEach>
				</div>
			</c:if>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2">保存并上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
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
  				if($.fn.zTree.getZTreeObj("financing_com_ztree").getSelectedNodes()[0]){
					var temp_organalId = $.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id;
					var temp_checkedCompanyName = $.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name;
					var url = "${pageContext.request.contextPath}/reportFinancingInnovate/isVirtualCompany";
					$.ajax({  
					     url : url,  
					     type : "POST",  
					     data: {organalId:temp_organalId,companyName:temp_checkedCompanyName},
					 async: false,  
					 cache: false,  
					 dataType:'json', 
					     success : function(data) {
						 if("success" == data ){
								 $("#organalID").val(temp_organalId);							 
								 $("#checkedCompanyName").val(temp_checkedCompanyName); 	 
						 }else{							     	 
								$("#organalID").val('');							 
								$("#checkedCompanyName").val(''); 
						 }							     
					     },  
					     error : function(data) {
					     }  
					});
					
					if(oldorgan!=$("#organalID").val())
					 	initNodeList("",1);
					 oldorgan=$("#organalID").val();
					
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
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]){
						var temp_organalId = $.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id;
						var temp_checkedCompanyName = $.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name;
						var url = "${pageContext.request.contextPath}/reportFinancingProjectProgress/isVirtualCompany";
						$.ajax({  
						     url : url,  
						     type : "POST",  
						     data: {organalId:temp_organalId,companyName:temp_checkedCompanyName},
						 async: false,  
						 cache: false,  
						 dataType:'json', 
						     success : function(data) {
							 if("success" == data ){
									 $("#organalID").val(temp_organalId);							 
									 $("#checkedCompanyName").val(temp_checkedCompanyName); 	 
							 }else{							     	 
									$("#organalID").val('');							 
									$("#checkedCompanyName").val(''); 
							 }							     
						     },  
						     error : function(data) {
						     }  
						});
								
						 if(oldorgan!=$("#organalID").val())
						 	initNodeList("",1);
						 oldorgan=$("#organalID").val();
						}
			   	});
			
		})




		var update_enclosure = function(){
			$("table tbody .enclosure").each(function(i){
				this.innerText = "附件"+(i+1);
			});
		};
		//["+($("table tbody .enclosure").length)+"]
		function addEnclosure(){
			var tr="<tr>";
			tr+="<td class=\"enclosure\"></td>";
			tr+="<td><input type=\"file\" name=\"enclosure\" ></td>";
			tr+="</tr>";
			$("table tbody").append(tr).find(".no-enclosure").remove();
			update_enclosure();
		}

		function getCoreOrgname()
		{
			$("#coreOrgname").val($("#coreOrg").find("option:selected").text());
		}

		function commitcheck()
		{
			if($("#coreOrg").val()=="")
			{
				win.errorAlert("请选择业态公司");
				return false;
			}
			if($("#hypothecationInformation").val()=="")
			{
				win.errorAlert("抵质押信息不能为空");
				return false;
			}
			if($("#pattern").val()=="")
			{
				win.errorAlert("请选择模式");
				return false;
			}
			if($("#financingStructure").val()=="")
			{
				win.errorAlert("融资结构信息不能为空");
				return false;
			}
			if($("#operationDepartment").val()=="")
			{
				win.errorAlert("操作主体不能为空");
				return false;
			}
			if($("#financingProgress").val()=="")
			{
				win.errorAlert("融资进展不能为空");
				return false;
			}
			return true;
		}
				
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !commitcheck())
			{
					$.unblockUI();
					return false;
			}
			//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/reportFinancingInnovate/save";
			
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
			     },  /* 
			     error : function(data) {
			     	$.unblockUI();
			     }, */  
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
		         	 $.unblockUI();
					 alert(XMLHttpRequest.status);
					 alert(XMLHttpRequest.readyState);
					 alert(textStatus);
				 }  
			});
			return false;
		});
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !commitcheck())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/reportFinancingInnovate/saveandreport";
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
						{
							win.errorAlert(commitresult.exceptionStr);
							
						}
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
			return false;
		});

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
	
		$(' input.date').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
	
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

	 
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		
	</script>
</html>
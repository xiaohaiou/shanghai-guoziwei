<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>下周融资项目进展数据</title>
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
			.tablebox{
				overflow-x: auto;
			}
			.tablebox>table{
			    width: 150em;
			}
		</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改下周融资项目进展数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增下周融资项目进展数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
		
	</c:choose>
	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<form:hidden path="id"/>
			<div class="model_part">
		<!-- 		<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25">
					<select  name="org" id="org" class="selectpicker" onchange="getCoreOrgname()">
						<option value=""  ></option>
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
							<option value="${result.id.nnodeId }"  <c:if test="${entityview.org eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
				</span>
		 -->		
		 		<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25">
					<input type="hidden" name="groupid" id="groupid" value="${groupid}">
					<input type="hidden" name="grouptype" id="grouptype" value="${grouptype}">
					<input type="hidden" id="operationType" value="${op }"> 
					<input name="checkedCompanyName" id="checkedCompanyName" value="${checkedCompanyName }" readonly="readonly">
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
			
						</ul>
				   </div>
				   <input type="hidden" id="organalID" name="organalID" value="${organalID}" >
				</span>
				
				
				
				
				
				
				
				
				
				<form:hidden path="orgname" id="orgname"/>
				<label class="w20"><span class="required"> * </span>年月:</label>
				
				<input class="w25 date1" type="text" id="date" name="date" value="<c:if test="${!empty entityview.year && !empty entityview.month}"><fmt:parseDate value="${entityview.year }-${entityview.month }" var="date" pattern="yyyy-MM"/><fmt:formatDate value="${date}" pattern="yyyy-MM" /></c:if>" readonly="true" check="NotEmpty_string_._._._._." /><!-- class="date1"  -->
				
				<label class="w20"><span class="required"> * </span>周数:</label>
				<span class="w25">
					<select  name="week" id="week" class="selectpicker" >
					<option value=""  ></option>
						<c:forEach var="n" begin="1" end="53" step="1">
							<option value="${n}"  <c:if test="${entityview.week == n}">selected="selected"</c:if>>${n}</option>
						</c:forEach>
					</select>
				</span>
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				
				<label class="w20"><span class="required"> * </span>周日期范围:</label>
				<input class="w25 date" type="text" id="weekStratDate" name="weekStratDate" value="${entityview.weekStratDate }" readonly="true" check="NotEmpty_string_._._._._." class="date" />
				~
				<input class="w25 date" type="text" id="weekEndDate" name="weekEndDate" value="${entityview.weekEndDate }" readonly="true" check="NotEmpty_string_._._._._." class="date" />
				
			</div>
			<h3 class="data_title1">下账金融机构：
				<input class="w25" id="selOrg" name="selOrg"/>
				<input type="button" value="查询" class="form_btn" onclick="selData()">
			</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="xzjgTableId">
						<tr class="table_header">
							<th>序号</th>
							<th>金融机构</th>
							<th>操作主体</th>
							<th>预下账金额(亿元)</th>
							<th>预下账日期</th>
							<th>项目审批进展</th>
							<th>最新更新时间</th>
							<th class="czTh">操作</th>
						</tr>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pagexzjgTableId" class="pagination" style="line-height:34px">
						
					</ul>
				</div>
			</div>
			
			<h3 class="data_title1">下周下账清单：</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="bzqdTableId">
						<tr class="table_header">
							<th>序号</th>
							<th>金融机构</th>
							<th>操作主体</th>
							<th>预下账金额(亿元)</th>
							<th>预下账日期</th>
							<th>项目审批进展</th>
							<th>最新更新时间</th>
							<th class="czTh">操作</th>
						</tr>
						<c:forEach items="${entityview.weekNextSet}" var="result" varStatus="s">
							<tr id="bzqdTr${result.fid}">
								<td class="bzqdTableIdtdNum">${ s.index + 1}</td>
								<td style="width: 20em;">${result.financialInstitution}</td>
								<td>${result.operateOrgname}</td>
								<td>${result.alreadyAccountAmounts}</td>
								<td>${result.accountDate}</td>
								<td>${result.projectProgressName}</td>
								<td>${result.lastModifyDate}
									<input type="hidden" name="weekNextList[${ s.index }].fid" value ="${result.fid}">
									<input type="hidden" name="weekNextList[${ s.index }].financialInstitution" value = "${result.financialInstitution}">
									<input type="hidden" name="weekNextList[${ s.index }].operateOrgname" value = "${result.operateOrgname}">
									<input type="hidden" name="weekNextList[${ s.index }].operateOrg" value = "${result.operateOrg}">
									<input type="hidden" id="aaaInputId${result.fid}" name="weekNextList[${ s.index }].alreadyAccountAmounts" value ="${result.alreadyAccountAmounts}">
									<input type="hidden" id="adInputId${result.fid}" name="weekNextList[${ s.index }].accountDate" value = "${result.accountDate}">
									<input type="hidden" name="weekNextList[${ s.index }].projectProgressName" value = "${result.projectProgressName}">
									<input type="hidden" name="weekNextList[${ s.index }].projectProgress" value = "${result.projectProgress}">
									<input type="hidden" name="weekNextList[${ s.index }].lastModifyDate" value = "${result.lastModifyDate}">	
								</td>
								<td><input type="button" class="btn btn-primary model_btn_ok" value="删除" onclick="del(this,${result.fid})"></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pagebzqdTableId" class="pagination" style="line-height:34px">
						
					</ul>
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
<script type="text/javascript" src="<c:url value="/js/paging.js"/>"></script>
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


		var tempData;
		//下周下账清单数组
		var fidArray = '${fidArray}';
		var setSize = '${setSize}';
		$(document).ready(function(){  
			$("#xzjgTableId tr:not(.table_header)").remove();
			//$("#bzqdTableId tr:not(.table_header)").remove();
			var noDataHtml="<tr id='xzjgTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
			$("#xzjgTableId").append(noDataHtml); 
			pagequery(1,"xzjgTableId");
			pagequery(1,"bzqdTableId");
			if(setSize<1){
				$("#bzqdTableId tr:not(.table_header)").remove();
				var noDataHtml1="<tr id='bzqdTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
				$("#bzqdTableId").append(noDataHtml1); 
			}
		});
		function selData(){
			var url = "${pageContext.request.contextPath}/reportFinancingWeekNext/getAccountsData";
			var orgnm = $("#selOrg").val();
			if(!orgnm){
				win.errorAlert("下账金融机构不可为空！");
				return;
			}
			$.ajax({  
			     url : url,  
			     type : "POST", 
			     data: {"orgnm":orgnm},
			     dataType:"json",
		       	 contentType:"application/x-www-form-urlencoded", 
			     success : function(data) {
			     	if(data==null||data==""){
			     		win.errorAlert("该单位未查询到最近头寸数据！");
			     		//清空数据
						$("#xzjgTableId tr:not(.table_header)").remove();
						var noDataHtml="<tr id='xzjgTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
						$("#xzjgTableId").append(noDataHtml);
				 		pagequery(1,"xzjgTableId");
			     		return;
			     	}
			     	tempData=data
				    loadData(data,2);
			     },  
			     error : function(data) {
			     	win.errorAlert("该单位未查询到最近头寸数据！");
			     }  
			}); 
		}

		//加载数据
		//loadData();
		function loadData(entity,flag){
			//清空数据
			$("#xzjgTableId tr:not(.table_header)").remove();
			var noDataHtml="<tr id='xzjgTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
			$("#xzjgTableId").append(noDataHtml);
			
			//加载数据
			
			if(entity==undefined||entity==null){
				return;
			}else{
				for(var i = 0;i<entity.length;i++){
					addTableHtml(i+1,entity[i],flag);
				} 
				pagequery(1,"xzjgTableId");
			}
		};

		//添加tableHtml
		function addTableHtml(trNum,data,flag){
			var tr="";
		    if(data!=null&&flag==2){
		    	tr+="<tr><td>"+trNum+"</td>";//序号
		    	tr+="<td>"+data[0]+"</td>";//金融机构
		    	tr+="<td>"+data[2]+"</td>";//操作主体
				tr+="<td><input type='text' style='width:80%' id='alreadyAccountAmounts"+data[7]+"' value='"+data[3]+"' ></td>";//已下账(亿元)	check='._double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'
				tr+="<td><input type='text' style='width:60%' id='expectAccountDate"+data[7]+"' value='"+data[4]+"' readonly='true' class='date'/></td>";						//下账时间 check='._string_._._._._.'
				tr+="<td>"+data[5]+"</td>";//项目审批进展
				tr+="<td>"+(data[6]==null?data[9]:data[6])+"</td>";//最新更新时间
				tr+="<td><input type='button' value='加入下周下账清单' class='form_btn' onclick='subWeekNext("+data[7]+")'></td></tr>";//加入下周下账清单
				$("#xzjgTableId").append(tr);
				loadJeDate();//初始化时间控件
			}else if(flag==1){
				if(fidArray.indexOf("&bzqdTr"+trNum+"&")<0){
					fidArray+="&bzqdTr"+trNum+"&,";
					var listNum = $("#bzqdTableId tr:not(#bzqdTableIdNoDataTrId)").length-1;
					tr+="<tr id='bzqdTr"+trNum+"'><td class='bzqdTableIdtdNum'>"+trNum+"</td>";//序号"+listNum+"
			    	tr+="<td>"+data[0]+"</td>"; 	 //金融机构
			    	tr+="<td>"+data[2]+"</td>";		 //操作主体
					tr+="<td>"+data[3]+"</td>";	     //已下账(亿元)
					tr+="<td>"+data[4]+"</td>";		 //下账时间
					tr+="<td>"+data[5]+"</td>";		 //项目审批进展
					tr+="<td>"+(data[6]==null?data[9]:data[6])+"</td>";		 //最新更新时间
					tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this,"+trNum+")'></td>";
					tr+="<input type='hidden' name='weekNextList["+listNum+"].fid' value ='"+data[7]+"'>";
					tr+="<input type='hidden' name='weekNextList["+listNum+"].financialInstitution' value = '"+data[0]+"'>";
					tr+="<input type='hidden' name='weekNextList["+listNum+"].operateOrgname' value = '"+data[2]+"'>";
					tr+="<input type='hidden' name='weekNextList["+listNum+"].operateOrg' value = '"+data[1]+"'>";
					tr+="<input type='hidden' id='aaaInputId"+trNum+"' name='weekNextList["+listNum+"].alreadyAccountAmounts' value ='"+data[3]+"'>";
					tr+="<input type='hidden' id='adInputId"+trNum+"' name='weekNextList["+listNum+"].accountDate' value = '"+data[4]+"'>";
					tr+="<input type='hidden' name='weekNextList["+listNum+"].projectProgress' value = '"+data[8]+"'>";
					tr+="<input type='hidden' name='weekNextList["+listNum+"].lastModifyDate' value = '"+(data[6]==null?data[9]:data[6])+"'></tr>";
					
					$("#bzqdTableId").append(tr);
				}
				$("#bzqdTr"+trNum+" td:eq(3)").text($("#alreadyAccountAmounts"+trNum).val());
				$("#bzqdTr"+trNum+" td:eq(4)").text($("#expectAccountDate"+trNum).val());
				$("#aaaInputId"+trNum).val($("#alreadyAccountAmounts"+trNum).val());
				$("#adInputId"+trNum).val($("#expectAccountDate"+trNum).val());
			}
		}
		
		//删除某一行
		function del(tr,fidNum){
			$(tr).parent().parent().remove();
			fidArray=fidArray.replace("&bzqdTr"+fidNum+"&,","");
			pagequery(1,"bzqdTableId");
			loadNum("bzqdTableId");
		}
		
	   /**
		* 加入下账清单
		*/
		function subWeekNext(num){
			//清空数据
			if($("#bzqdTableId tr:not(#bzqdTableIdNoDataTrId)").length==1){
				$("#bzqdTableId tr:not(.table_header)").remove();
			}
			var tempVal=-1;
			for(var i = 0;i<tempData.length;i++){
				if(tempData[i][7]==num){
					tempVal=tempData[i];
				}
			}
			if(tempVal!=-1){
				addTableHtml(num,tempVal,1);
			}
			loadNum("bzqdTableId");
			pagequery(1,"bzqdTableId");
		}
		
		//序号生成
		function loadNum(tableId){
			var trNum = $("#"+tableId+" tr").length-1;
			for(var i=0;i<trNum;i++){
				$("."+tableId+"tdNum:eq("+i+")").html(i+1);
			}
		}
		
		function loadJeDate(){
			$(' input.date').jeDate({
				format:"YYYY-MM-DD"
			})
		}

		function getCoreOrgname()
		{
			$("#orgname").val($("#org").find("option:selected").text());
		}

		function commitcheck()
		{
			if($("#org").val()=="")
			{
				win.errorAlert("请选择业态公司");
				return false;
			}
			if($("#week").val()=="")
			{
				win.errorAlert("请选择周数");
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
			var url = "${pageContext.request.contextPath}/reportFinancingWeekNext/save";
			
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
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
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !commitcheck())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/reportFinancingWeekNext/saveandreport";
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
	
		$(' input.date').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
		
		$(' input.date1').jeDate(
			{
				format:"YYYY-MM"
			}
		)
	

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
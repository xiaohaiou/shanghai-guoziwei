<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>现金流周计划新增、编辑页面</title>
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
	.tablebox{
		overflow-x: auto;
	}
	.tablebox>table{
	    width: 150em;
	}
</style>
</head>
<body onload="loadData(null)">
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改现金流周计划数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增现金流周计划数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<div class="model_body">
		<form:form id="form2" modelAttribute="entityview" >
				<form:hidden path="pid"/>
				<div class="label_inpt_div">
					<div class="model_part">
					   <span class="" style="display:block">
					   		<label class="w20"><span class="required"> * </span>单位名称:</label>
								 <!-- <i class="clear iconfont icon-guanbi"></i> -->
							<input type="hidden" id="organalId" name="org" value="${entityview.org }" >
							<input  class="w25" name="orgname" id="company" value="${entityview.orgname }" readonly title="${entityview.orgname }" check="NotEmpty">
							
							<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
								<ul id="com_ztree" class="ztree">
				
								</ul>
						    </div>
							<label class="w20"><span class="required"> * </span>所属核心企业:</label>
								<!-- <i class="clear iconfont icon-guanbi"></i> -->
							<input type="hidden" id="parentOrganalId" name="parentOrganalId" value="${entityview.parentOrganalId }" ><%-- ${entityview.parentOrganalId } --%>
							<input  class="w25" id="parentCompany" name="parentCompany" value="${entityview.parentCompany }" readonly title="${entityview.parentCompany }"><%-- ${entityview.parentCompany } --%>
							
				 		</span>
				 		<span class="" style="display:block">
					 		<label class="w20"><span class="required"> <font color=red>*</font> </span>时间:</label>
							<input  type="text" id="date" name="date" value="${entityview.date}" readonly="true" class="w25 time1" check="NotEmpty" />
							<label class="w20"><span class="required"> * </span>周数:</label>
							<span class="w25">
								<select  name="week" id="week" class="selectpicker"  >
									<c:forEach var= "temp1" begin= "1" step= "1" end= "54">
										<option value="${temp1}" <c:if test="${entityview.week == temp1}">selected="selected"</c:if>>
											${temp1}
										</option>
									</c:forEach>
								</select>
							</span>
						</span>
						<span class="" style="display:block">
							<label class="w20"><span class="required"> <font color=red>*</font> </span>周开始时间:</label>
							<input  type="text" name="startDate" id="startDate" value="<fmt:formatDate value="${entityview.weekStart}" pattern="yyyy-MM-dd"/>" readonly="true" class="w25 time" check="NotEmpty" />
							<label class="w20"><span class="required"> <font color=red>*</font> </span>周结束时间:</label>
							<input  type="text" name="endDate" id="endDate" value="<fmt:formatDate value="${entityview.weekEnd}" pattern="yyyy-MM-dd"/>" readonly="true" class="w25 time" check="NotEmpty" />
						<input type="button" id='hzcxID' value="汇总" style='background:#009999' onclick='sumData()'/>	
						</span>
				</div>
				<h3 class="data_title1">经营性现金流</h3>
					<div class="model_part">
						<label class="w20"><font color=red>*</font>经营性流入（万元）:</label>
						<form:input class="w25" path="operationalInflow" check="NotEmpty_double_17_2_0+_._."/>
						<label class="w20"><font color=red>*</font>经营性流出（万元）:</label>
						<form:input class="w25" path="operationalOutflow" check="NotEmpty_double_17_2_0+_._."/>
						<label class="w20"><font color=red>*</font>经营性净流量（万元）:</label>
						<form:input class="w25" id="operationalNetFlow" path="operationalNetFlow" readonly="true"/>
					</div>
				
				<h3 class="data_title1">筹资性现金流</h3>
					<div class="model_part">
						<label class="w20"><font color=red>*</font>筹资性流入（万元）:</label>
						<form:input class="w25" path="capitalInflow" check="NotEmpty_double_17_2_0+_._."/>
						<label class="w20"><font color=red>*</font>筹资性流出（万元）:</label>
						<form:input class="w25" path="capitalOutflow" check="NotEmpty_double_17_2_0+_._."/>
						<label class="w20"><font color=red>*</font>筹资性净流量（万元）:</label>
						<form:input class="w25" id="capitalNetFlow" path="capitalNetFlow" readonly="true"/>
					</div>
				<h3 class="data_title1">投资性现金流</h3>
					<div class="model_part">
						<label class="w20"><font color=red>*</font>投资性流入（万元）:</label>
						<form:input class="w25" path="investmentInflow" check="NotEmpty_double_17_2_0+_._."/>
						<label class="w20"><font color=red>*</font>投资性流出 （万元）:</label>
						<form:input class="w25" path="investmentOutflow" check="NotEmpty_double_17_2_0+_._."/>
						<label class="w20"><font color=red>*</font>投资性净流量（万元）:</label>
						<form:input class="w25" id="investmentNetFlow" path="investmentNetFlow" readonly="true"/>
					</div>
				<h3 class="data_title1">现金流合计</h3>
					<div class="model_part">
						<label class="w20"><font color=red>*</font>现金流入（万元）:</label>
						<form:input class="w25" id="cashInflow" path="cashInflow" readonly="true"/>
						<label class="w20"><font color=red>*</font>现金流出（万元）:</label>
						<form:input class="w25" id="cashOutflow" path="cashOutflow" readonly="true"/>
						<label class="w20"><font color=red>*</font>现金净流量（万元）:</label>
						<form:input class="w25" id="netCashFlow" path="netCashFlow" readonly="true"/>
						<label class="w20"><font color=red>*</font>期初可用头寸（万元）:</label>
						<form:input class="w25" path="initialAvailableCash" check="NotEmpty_double_17_2_0+_._."/>
						<label class="w20"><font color=red>*</font>期末可用头寸（万元）:</label>
						<form:input class="w25" id="finalAvailableCash" path="finalAvailableCash" readonly="true"/>
						<!-- <label class="w20"><font color=red>*</font>备注:</label>
						<span class="w25">
							<textarea  rows="5" cols="40" name="remarks" id="remarks"></textarea>
						</span> -->
					</div>
					<div class="model_part">
						<label class="w20" style="vertical-align: top;">备注:</label>
						<span class="w70">
							<textarea  rows="5" cols="40" name="remarks" id="remarks" placeholder="期末可用头寸为负时，填写备注给出解释说明。">${entityview.remarks }</textarea>
						</span>
					</div> 
				<h3 class="data_title1">筹资性流入明细列表
					<input type="button" value="导入" class="form_btn" class="openReportWinClass" onclick="javascript:openBrowse(1)">
					<input type="button" value="导出" class="form_btn" onclick="excelOutput(1)">
					<a class="model_btn_plus" onClick="addTr('czlrTableId')">+</a>
				</h3>
					<div class="model_part">
						<div class="tablebox">
							<table id="czlrTableId">
								<tr class="table_header">
									<th>序号</th>
									<th>承贷主体</th>
									<th>贷款银行</th>
									<th>贷款金额（万元）</th>
									<th>计划下账日期</th>
									<th>融资类型</th>
									<th>新增或续作</th>
									<th>下账币种</th>
									<th>综合成本（%）</th>
									<th>是否权益性融资</th>
									<th>操作</th>
								</tr>
							</table>
						</div>
						<div class="clearfix"> 
							<ul id="pageczlrTableId" class="pagination" style="line-height:34px">
									
							</ul>
						</div>
					</div>
				<h3 class="data_title1">筹资性流出明细列表
					<input type="button" value="导入" class="form_btn" class="openReportWinClass" onclick="javascript:openBrowse(2)">
					<input type="button" value="导出" class="form_btn" onclick="excelOutput(2)">
					<a class="model_btn_plus" onClick="addTr('czlcTableId')">+</a>
				</h3>
					<div class="model_part">
						<div class="tablebox">
							<table id="czlcTableId">
								<tr class="table_header">
									<th>序号</th>
									<th>操作单位</th>
									<th>还贷日期</th>
									<th>金融机构</th>
									<th>还贷类型</th>
									<th>还款金额(万元)</th>
									<th>是否续作</th>
									<th>还款币种</th>
									<th>保障还款项目</th>
									<th>是否有保障还款计划</th>
									<th>操作</th>
								</tr>
							</table>
						</div>
						<div class="clearfix"> 
							<ul id="pageczlcTableId" class="pagination" style="line-height:34px">
									
							</ul>
						</div>
					</div>
					
				<h3 class="data_title1">投资性流出明细列表
					<input type="button" value="导入" class="form_btn" class="openReportWinClass" onclick="javascript:openBrowse(3)">
					<input type="button" value="导出" class="form_btn" onclick="excelOutput(3)">
					<a class="model_btn_plus" onClick="addTr('tzlcTableId')">+</a>
				</h3>
					<div class="model_part">
						<div class="tablebox">
							<table id="tzlcTableId">
								<tr class="table_header">
									<th>序号</th>
									<th>投资单位</th>
									<th>投资日期</th>
									<th>投资项目名称</th>
									<th>投资金额（万元）</th>
									<th>投资币种</th>
									<th>专项资金保障方案</th>
									<th>投资类型</th>
									<th>是否有保障还款计划</th>
									<th>操作</th>
								</tr>
							</table>
						</div>
						<div class="clearfix"> 
							<ul id="pagetzlcTableId" class="pagination" style="line-height:34px">
									
							</ul>
						</div>
					</div>
				<div class="model_btn">
					<button class="btn btn-primary model_btn_ok" id="commit_1">保存</button>
					<button class="btn btn-primary model_btn_ok" id="commit_2">保存并上报</button>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
	</form:form>
	<form:form id="fileForm" enctype="multipart/form-data" method="post" target="rfFrame">
		<input id="czlrExcelImportId" type="file" name="excelFile" accept="*" style="display:none;"  onchange="changeFiles()"/>
		<input type="hidden" id="fileTypeId" name="fileType" value="0"/>
		<input type="hidden" id="excelNameId" name="excelName" value="0"/>
	</form:form>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vailds.js"/>" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/js/paging.js"/>"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		$(document).ready(function(){
			$('#hzcxID').hide();
		});
		var timeoutId;
		var weekStart = "";
		var weekEnd = "";
 		$('#company').on('focus click',function(){
 			$(this).next('.com_ztree_box').css('display','block');
 		}).parent().on('mouseenter',function(){
 			clearTimeout(timeoutId);
 		}).on('mouseleave',function(){
 			clearTimeout(timeoutId);
 			timeoutId = setTimeout(function(el){
 				$(el).find('.com_ztree_box').css('display','none');
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
    		if(childrenNodes[0]!=null)
    			treeObj.expandNode(childrenNodes[0],true);
	    });
	
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					 $("#organalId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
					 getMonthPlan(null,null);
					 $("#company").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);vaild.vaild($("#company")[0]);
					 $("#company").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
					 var url = "${pageContext.request.contextPath}/moneyFlowPlanWeek/getParentCompany";
					 $.ajax({
					 	type:'POST',
					 	data:{organalID:$("#organalId").val()},
					 	dataType:'json',
					 	url:url,
					 	success:function(data){
							 		$("#coreCompId").val(data.id.nnodeId);
							 		$("#parentCompany").val(data.vcFullName);
							 		
							 		console.log(data.vcFullName);
							 		$('#hzcxID').hide();
							 		if(data.status==50501)
							 			$('#hzcxID').show();							 			
							 		$("#operationalInflow").val('');
							 		$("#operationalOutflow").val('');
							 		$("#operationalNetFlow").val('');
							 		$("#capitalInflow").val('');				 		
							 		$("#capitalOutflow").val('');				 		
							 		$("#capitalNetFlow").val(data.financingOut);				 		
							 		$("#investmentInflow").val('');
							 		$("#investmentOutflow").val('');				 		
							 		$("#investmentNetFlow").val('');				 		
							 		$("#cashInflow").val('');				 		
							 		$("#cashOutflow").val('');
							 		$("#netCashFlow").val('');				 		
							 		$("#initialAvailableCash").val('');				 		
							 		$("#finalAvailableCash").val('');
							 }
					 });
				}
		   	});
		});		
		$(".clear").on('click',function(){
			$(this).parent().siblings('input[name="company"]').val('');
			$("organalId").val("");
		})
		
		$(' input.time').jeDate(
			{
				format:"YYYY-MM-DD",
				choosefun:function(elem, val, date) {getMonthPlan(elem,val);},
				clearfun:function(elem, val) {getMonthPlan(elem,'');},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {}, 
			}
		)
		
		$(' input.time1').jeDate(
			{
				format:"YYYY-MM"
			}
		)
		
		function commitcheck()
		{
			if($("#finalAvailableCash").val()=="")
			{
				win.errorAlert("现金净流量(万元)不能为空");
				return false;
			}else{
				var finalAvailableCash = parseFloat($("#finalAvailableCash").val());
				if(finalAvailableCash < 0 && $("#remarks").val() == ""){
					win.errorAlert("期末可用头寸为负时，填写备注给出解释说明。");
					return false;
				}
			}
			return true;
		}
		
		//明细列表赋值
		$("#commit_1").click(function(){
			/* var dataMoneyFlowWeekCi = {};
			$("#dataMoneyFlowWeekCi").val(dataMoneyFlowWeekCi); */
		
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all()|| !commitcheck())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var op = '${requestScope.op}';
			var url="${pageContext.request.contextPath}/moneyFlowPlanWeek/saveOrUpdate";
			if(op=='modify'){url="${pageContext.request.contextPath}/moneyFlowPlanWeek/update";}
			var formData = new FormData($("#form2")[0]);
			console.log(JSON.stringify(formData));
			
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
						if(commitresult.result==true){
							win.successAlert('保存成功！',function(){
								parent.location.reload(true);
								parent.mclose();
							});
						}else{
							win.errorAlert(commitresult.exceptionStr);
						};
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }
			}); 
				

			return false;
		})
		$("#commit_2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/moneyFlowPlanWeek/saveandreport";
			
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
		})

	
		var example = $(".expmale");
		example.removeClass("example").remove();
		$(".btn.new").click(function(){
			$("table tbody").append(example.clone());
		});
		$("body").on("click","i.del.btn",function(){
			$(this).closest("tr").remove();
		});
		
		
		//关闭弹窗
		
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
	
		//新增tr
		function addTr(tableId){
			var trNum = $("#"+tableId+" tr:not(.table_header):not(#"+tableId+"NoDataTrId)").length+1;
			addTableHtml(null,tableId,trNum);
			if(trNum<5){
				pagequery(1,tableId);
			}else{
				if(trNum%5==0){
					pagequery(parseInt(trNum/5),tableId);
				}else{
					pagequery(parseInt(trNum/5)+1,tableId);
				}
			}
		}
		
		//分页
		//paging(0,1,"czlrTableId");
		//添加tableHtml
		function addTableHtml(data,tableId,trNum){
			var financingType = eval("(" + '${financingType}' + ")");
			var sequelNew = eval("(" + '${sequelNew}' + ")");
			var currencyKind = eval("(" + '${currencyKind}' + ")");
			var truefalse = eval("(" + '${trueOrFalse}' + ")");
			var investType = eval("(" + '${investType}' + ")");
			if(tableId=="czlrTableId"&&data==null){
				var tr="<tr><td class='czlrTableIdtdNum'>"+trNum+"</td>";//序号
				tr+="<td><input type='text' name='dataMoneyFlowWeekCiTemp.commitmentShallSubject' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//操作单位
				tr+="<td><input type='text' name='dataMoneyFlowWeekCiTemp.lendingBank' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//贷款银行
				tr+="<td><input type='text' name='dataMoneyFlowWeekCiTemp.loanAmount' style='width:100%' check='NotEmpty_double_17_2_0+_._.' placeholder='必填,17位数字,最多2位小数'></td>";//贷款金额
				tr+="<td><input type='text' name='dataMoneyFlowWeekCiTemp.placeOrderDate' style='width:100%' readonly='true' class='timeCi' check='NotEmpty' placeholder='必填'></td>";//计划下账日期
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.financingType",financingType);//融资类型
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.newOrRenewed",sequelNew);//新增或续作
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.placeOrderCurrency",currencyKind);//下账币种
				tr+="<td><input type='text' name='dataMoneyFlowWeekCiTemp.compositeCost' style='width:100%' check='NotEmpty_double_7_4_0+_._.' placeholder='必填,7位数字,最多2位小数'></td>";//综合成本
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.equityFinancing",truefalse);//是否权益性融
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this,\"czlrTableId\")'></td></tr>";
				$("#czlrTableId").append(tr);
				loadJeDate();
			}else if(tableId=="czlrTableId"&&data!=null&&trNum!=(-999)){
				var tr="<tr><td class='czlrTableIdtdNum'>"+trNum+"</td>";//序号
				tr+="<td><input type='text' value='"+data.commitmentShallSubject+"' name='dataMoneyFlowWeekCiTemp.commitmentShallSubject' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//操作单位
				tr+="<td><input type='text' value='"+data.lendingBank+"' name='dataMoneyFlowWeekCiTemp.lendingBank' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//贷款银行
				tr+="<td><input type='text' value='"+data.loanAmount+"' name='dataMoneyFlowWeekCiTemp.loanAmount' style='width:100%' check='NotEmpty_double_17_2_0+_._.' placeholder='必填,17位数字,最多2位小数'></td>";//贷款金额
				tr+="<td><input type='text' value='"+data.placeOrderDate+"' name='dataMoneyFlowWeekCiTemp.placeOrderDate' style='width:100%' readonly='true' class='timeCi' check='NotEmpty' placeholder='必填'></td>";//计划下账日期
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.financingType",financingType);//融资类型
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.newOrRenewed",sequelNew);//新增或续作
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.placeOrderCurrency",currencyKind);//下账币种
				tr+="<td><input type='text' value='"+data.compositeCost+"' name='dataMoneyFlowWeekCiTemp.compositeCost' style='width:100%' check='NotEmpty_double_7_4_0+_._.' placeholder='必填,7位数字,最多2位小数'></td>";//综合成本
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.equityFinancing",truefalse);//是否权益性融
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this,\"czlrTableId\")'></td></tr>";
				$("#czlrTableId").append(tr);
			}else if(tableId=="czlrTableId"&&data!=null&&trNum==(-999)){
				var tr="<tr><td class='czlrTableIdtdNum'>"+data[0]+"</td>";//序号
				tr+="<td><input type='text' value='"+data[1]+"' name='dataMoneyFlowWeekCiTemp.commitmentShallSubject' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//操作单位
				tr+="<td><input type='text' value='"+data[2]+"' name='dataMoneyFlowWeekCiTemp.lendingBank' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//贷款银行
				tr+="<td><input type='text' value='"+data[3]+"' name='dataMoneyFlowWeekCiTemp.loanAmount' style='width:100%' check='NotEmpty_double_17_2_0+_._.' placeholder='必填,17位数字,最多2位小数'></td>";//贷款金额
				tr+="<td><input type='text' value='"+data[4]+"' name='dataMoneyFlowWeekCiTemp.placeOrderDate' style='width:100%' readonly='true' class='timeCi' check='NotEmpty' placeholder='必填'></td>";//计划下账日期
				tr+=loadSelect(data[5],"dataMoneyFlowWeekCiTemp.financingType",financingType,1);//融资类型
				tr+=loadSelect(data[6],"dataMoneyFlowWeekCiTemp.newOrRenewed",sequelNew,1);//新增或续作
				tr+=loadSelect(data[7],"dataMoneyFlowWeekCiTemp.placeOrderCurrency",currencyKind,1);//下账币种
				tr+="<td><input type='text' value='"+data[8]+"' name='dataMoneyFlowWeekCiTemp.compositeCost' style='width:100%' check='NotEmpty_double_7_4_0+_._.' placeholder='必填,7位数字,最多2位小数'></td>";//综合成本
				tr+=loadSelect(data[9],"dataMoneyFlowWeekCiTemp.equityFinancing",truefalse,1);//是否权益性融
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this,\"czlrTableId\")'></td></tr>";
				$("#czlrTableId").append(tr);
			}else if(tableId=="czlcTableId"&&data==null){
				var tr="<tr><td class='czlcTableIdtdNum'>"+trNum+"</td>";//序号
				tr+="<td><input type='text' name='dataMoneyFlowWeekCoTemp.theLoanBusiness' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//操作单位/还贷企业
				tr+="<td><input type='text' name='dataMoneyFlowWeekCoTemp.dateOfPayment' style='width:100%' readonly='true' class='timeCi' check='NotEmpty' placeholder='必填'></td>";//还贷日期
				tr+="<td><input type='text' name='dataMoneyFlowWeekCoTemp.financingInstitution' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//金融机构
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.paymentType",financingType);//还贷类型
				tr+="<td><input type='text' name='dataMoneyFlowWeekCoTemp.repaymentAmount' style='width:100%'  check='NotEmpty_double_17_2_0_._.' placeholder='必填,17位数字,最多2位小数'></td>";//还款金额
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.newOrRenewedCo",sequelNew);//是否续作
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.paymentCurrency",currencyKind);//下账币种
				tr+="<td><input type='text' name='dataMoneyFlowWeekCoTemp.guaranteeRepaymentProject' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//金融机构
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.guaranteeRepaymentPlan",truefalse);//是否有保障还款计划
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this,\"czlcTableId\")'></td></tr>";
				$("#czlcTableId").append(tr);
				loadJeDate();
			}else if(tableId=="czlcTableId"&&data!=null&&trNum!=(-999)){
				var tr="<tr><td class='czlcTableIdtdNum'>"+trNum+"</td>";//序号
				tr+="<td><input type='text' value='"+data.theLoanBusiness+"' name='dataMoneyFlowWeekCoTemp.theLoanBusiness' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//操作单位/还贷企业
				tr+="<td><input type='text' value='"+data.dateOfPayment+"' name='dataMoneyFlowWeekCoTemp.dateOfPayment' style='width:100%' readonly='true' class='timeCi' check='NotEmpty' placeholder='必填'></td>";//还贷日期
				tr+="<td><input type='text' value='"+data.financingInstitution+"' name='dataMoneyFlowWeekCoTemp.financingInstitution' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//金融机构
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.paymentType",financingType);//还贷类型
				tr+="<td><input type='text' value='"+data.repaymentAmount+"' name='dataMoneyFlowWeekCoTemp.repaymentAmount' style='width:100%' check='NotEmpty_double_17_2_0_._.' placeholder='必填,17位数字,最多2位小数'></td>";//还款金额
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.newOrRenewedCo",sequelNew);//是否续作
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.paymentCurrency",currencyKind);//下账币种
				tr+="<td><input type='text' value='"+data.guaranteeRepaymentProject+"' name='dataMoneyFlowWeekCoTemp.guaranteeRepaymentProject' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//金融机构
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.guaranteeRepaymentPlan",truefalse);//是否有保障还款计划
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this,\"czlcTableId\")'></td></tr>";
				$("#czlcTableId").append(tr);
			}else if(tableId=="czlcTableId"&&data!=null&&trNum==(-999)){
				var tr="<tr><td class='czlcTableIdtdNum'>"+data[0]+"</td>";//序号
				tr+="<td><input type='text' value='"+data[1]+"' name='dataMoneyFlowWeekCoTemp.theLoanBusiness' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//操作单位/还贷企业
				tr+="<td><input type='text' value='"+data[2]+"' name='dataMoneyFlowWeekCoTemp.dateOfPayment' style='width:100%' readonly='true' class='timeCi' check='NotEmpty' placeholder='必填'></td>";//还贷日期
				tr+="<td><input type='text' value='"+data[3]+"' name='dataMoneyFlowWeekCoTemp.financingInstitution' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//金融机构
				tr+=loadSelect(data[4],"dataMoneyFlowWeekCoTemp.paymentType",financingType,1);//还贷类型
				tr+="<td><input type='text' value='"+data[5]+"' name='dataMoneyFlowWeekCoTemp.repaymentAmount' style='width:100%' check='NotEmpty_double_17_2_0_._.' placeholder='必填,17位数字,最多2位小数'></td>";//还款金额
				tr+=loadSelect(data[6],"dataMoneyFlowWeekCoTemp.newOrRenewedCo",sequelNew,1);//是否续作
				tr+=loadSelect(data[7],"dataMoneyFlowWeekCoTemp.paymentCurrency",currencyKind,1);//下账币种
				tr+="<td><input type='text' value='"+data[8]+"' name='dataMoneyFlowWeekCoTemp.guaranteeRepaymentProject' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//金融机构
				tr+=loadSelect(data[9],"dataMoneyFlowWeekCoTemp.guaranteeRepaymentPlan",truefalse,1);//是否有保障还款计划
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this,\"czlcTableId\")'></td></tr>";
				$("#czlcTableId").append(tr);
			}else if(tableId=="tzlcTableId"&&data==null){
				var tr="<tr><td class='tzlcTableIdtdNum'>"+trNum+"</td>";//序号
				tr+="<td><input type='text' name='dataMoneyFlowWeekIoTemp.unitOfInvestment' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//投资单位
				tr+="<td><input type='text' name='dataMoneyFlowWeekIoTemp.dateOfInvestment' style='width:100%' readonly='true' class='timeCi' check='NotEmpty' placeholder='必填'></td>";//投资日期
				tr+="<td><input type='text' name='dataMoneyFlowWeekIoTemp.nameOfInvestmentProject' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//投资项目名称
				tr+="<td><input type='text' name='dataMoneyFlowWeekIoTemp.investmentAmount' style='width:100%' check='NotEmpty_double_17_2_0+_._.' placeholder='必填,17位数字,最多2位小数'></td>";//投资金额(万元)
				tr+=loadSelect(data,"dataMoneyFlowWeekIoTemp.theInvestmentCurrency",currencyKind);//投资币种
				tr+="<td><input type='text' name='dataMoneyFlowWeekIoTemp.specialFundGuaranteeScheme' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//专项资金保障方案
				tr+=loadSelect(data,"dataMoneyFlowWeekIoTemp.investmentType",investType);//投资类型
				tr+=loadSelect(data,"dataMoneyFlowWeekIoTemp.guaranteeRepaymentPlan",truefalse);//是否有保障还款计划
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this,\"tzlcTableId\")'></td></tr>";
				$("#tzlcTableId").append(tr);
				loadJeDate();
			}else if(tableId=="tzlcTableId"&&data!=null&&trNum!=(-999)){
				var tr="<tr><td class='tzlcTableIdtdNum'>"+trNum+"</td>";//序号
				tr+="<td><input type='text' value='"+data.unitOfInvestment+"' name='dataMoneyFlowWeekIoTemp.unitOfInvestment' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//投资单位
				tr+="<td><input type='text' value='"+data.dateOfInvestment+"' name='dataMoneyFlowWeekIoTemp.dateOfInvestment' style='width:100%' readonly='true' class='timeCi' check='NotEmpty' placeholder='必填'></td>";//投资日期
				tr+="<td><input type='text' value='"+data.nameOfInvestmentProject+"' name='dataMoneyFlowWeekIoTemp.nameOfInvestmentProject' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//投资项目名称
				tr+="<td><input type='text' value='"+data.investmentAmount+"' name='dataMoneyFlowWeekIoTemp.investmentAmount' style='width:100%' check='NotEmpty_double_17_2_0+_._.' placeholder='必填,17位数字,最多2位小数'></td>";//投资金额(万元)
				tr+=loadSelect(data,"dataMoneyFlowWeekIoTemp.theInvestmentCurrency",currencyKind);//投资币种
				tr+="<td><input type='text' value='"+data.specialFundGuaranteeScheme+"' name='dataMoneyFlowWeekIoTemp.specialFundGuaranteeScheme' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//专项资金保障方案
				tr+=loadSelect(data,"dataMoneyFlowWeekIoTemp.investmentType",investType);//投资类型
				tr+=loadSelect(data,"dataMoneyFlowWeekIoTemp.guaranteeRepaymentPlan",truefalse);//是否有保障还款计划
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this,\"tzlcTableId\")'></td></tr>";
				$("#tzlcTableId").append(tr);
			}else if(tableId=="tzlcTableId"&&data!=null&&trNum==(-999)){
				var tr="<tr><td class='tzlcTableIdtdNum'>"+data[0]+"</td>";//序号
				tr+="<td><input type='text' value='"+data[1]+"' name='dataMoneyFlowWeekIoTemp.unitOfInvestment' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//投资单位
				tr+="<td><input type='text' value='"+data[2]+"' name='dataMoneyFlowWeekIoTemp.dateOfInvestment' style='width:100%' readonly='true' class='timeCi' check='NotEmpty' placeholder='必填'></td>";//投资日期
				tr+="<td><input type='text' value='"+data[3]+"' name='dataMoneyFlowWeekIoTemp.nameOfInvestmentProject' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//投资项目名称
				tr+="<td><input type='text' value='"+data[4]+"' name='dataMoneyFlowWeekIoTemp.investmentAmount' style='width:100%' check='NotEmpty_double_17_2_0+_._.' placeholder='必填,17位数字,最多2位小数'></td>";//投资金额(万元)
				tr+=loadSelect(data[5],"dataMoneyFlowWeekIoTemp.theInvestmentCurrency",currencyKind,1);//投资币种
				tr+="<td><input type='text' value='"+data[6]+"' name='dataMoneyFlowWeekIoTemp.specialFundGuaranteeScheme' style='width:100%' check='NotEmpty' placeholder='必填'></td>";//专项资金保障方案
				tr+=loadSelect(data[7],"dataMoneyFlowWeekIoTemp.investmentType",investType,1);//投资类型
				tr+=loadSelect(data[8],"dataMoneyFlowWeekIoTemp.guaranteeRepaymentPlan",truefalse,1);//是否有保障还款计划
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this,\"tzlcTableId\")'></td></tr>";
				$("#tzlcTableId").append(tr);
			}
		}
		//初始化详细数据
		function loadData(monthPlanData){
			var dataMoneyFlowWeekCi = {};
			var dataMoneyFlowWeekCo = {};
			var dataMoneyFlowWeekIo = {};
			if(monthPlanData!=null){//获取月数据
				dataMoneyFlowWeekCi = monthPlanData['ci'];
				dataMoneyFlowWeekCo = monthPlanData['co'];
				dataMoneyFlowWeekIo = monthPlanData['io'];
			}else{					//加载表数据
				var entityList = eval("(" + '${entityList}' + ")");
				if(entityList==undefined||entityList==null){
					return;
				}
				dataMoneyFlowWeekCi = entityList.dataMoneyFlowWeekCi;
				dataMoneyFlowWeekCo = entityList.dataMoneyFlowWeekCo;
				dataMoneyFlowWeekIo = entityList.dataMoneyFlowWeekIo;
			}
			//筹资流入数据加载
			$("#czlrTableId tr:not(.table_header)").remove();
			var noDataHtml="<tr id='czlrTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
			$("#czlrTableId").append(noDataHtml);
			for(var i = 0;i<dataMoneyFlowWeekCi.length;i++){
				if(monthPlanData!=null){
					var data = {};
					data.commitmentShallSubject=dataMoneyFlowWeekCi[i].commitment_shall_subject;
					data.lendingBank=dataMoneyFlowWeekCi[i].lending_bank;
					data.loanAmount=dataMoneyFlowWeekCi[i].loan_amount;
					data.placeOrderDate=dataMoneyFlowWeekCi[i].place_order_date;
					data.financingType=dataMoneyFlowWeekCi[i].financing_type;
					data.newOrRenewed=dataMoneyFlowWeekCi[i].new_or_renewed;
					data.placeOrderCurrency=dataMoneyFlowWeekCi[i].place_order_currency;
					data.compositeCost=dataMoneyFlowWeekCi[i].composite_cost;
					data.equityFinancing=dataMoneyFlowWeekCi[i].equity_financing;
					addTableHtml(data,"czlrTableId",(i+1));
				}else{
					addTableHtml(dataMoneyFlowWeekCi[i],"czlrTableId",(i+1));
				}
				loadJeDate();//初始化时间控件
			}
			//刷新页码
			pagequery(1,"czlrTableId");
			//筹资流出数据加载
			$("#czlcTableId tr:not(.table_header)").remove();
			var noDataHtml="<tr id='czlcTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
			$("#czlcTableId").append(noDataHtml);
			for(var i = 0;i<dataMoneyFlowWeekCo.length;i++){
				if(monthPlanData!=null){
					var data = {};
					data.theLoanBusiness=dataMoneyFlowWeekCo[i].the_loan_business;
					data.dateOfPayment=dataMoneyFlowWeekCo[i].date_of_payment;
					data.financingInstitution=dataMoneyFlowWeekCo[i].financing_institution;
					data.paymentType=dataMoneyFlowWeekCo[i].payment_type;
					data.repaymentAmount=dataMoneyFlowWeekCo[i].repayment_amount;
					data.newOrRenewedCo=dataMoneyFlowWeekCo[i].new_or_renewed_co;
					data.paymentCurrency=dataMoneyFlowWeekCo[i].payment_currency;
					data.guaranteeRepaymentProject=dataMoneyFlowWeekCo[i].guarantee_repayment_project;
					data.guaranteeRepaymentPlan=dataMoneyFlowWeekCo[i].guarantee_repayment_plan;
					addTableHtml(data,"czlcTableId",(i+1));
				}else{
					addTableHtml(dataMoneyFlowWeekCo[i],"czlcTableId",(i+1));
				}
				loadJeDate();//初始化时间控件
			}
			pagequery(1,"czlcTableId");
			//投资流出数据加载
			$("#tzlcTableId tr:not(.table_header)").remove();
			var noDataHtml="<tr id='tzlcTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
			$("#tzlcTableId").append(noDataHtml);
			for(var i = 0;i<dataMoneyFlowWeekIo.length;i++){
				if(monthPlanData!=null){
					var data = {};
					data.unitOfInvestment=dataMoneyFlowWeekIo[i].unit_of_investment;
					data.dateOfInvestment=dataMoneyFlowWeekIo[i].date_of_investment;
					data.nameOfInvestmentProject=dataMoneyFlowWeekIo[i].name_of_investment_project;
					data.investmentAmount=dataMoneyFlowWeekIo[i].investment_amount;
					data.theInvestmentCurrency=dataMoneyFlowWeekIo[i].the_investment_currency;
					data.specialFundGuaranteeScheme=dataMoneyFlowWeekIo[i].special_fund_guarantee_scheme;
					data.investmentType=dataMoneyFlowWeekIo[i].investment_type;
					data.guaranteeRepaymentPlan=dataMoneyFlowWeekIo[i].guarantee_repayment_plan;
					addTableHtml(data,"tzlcTableId",(i+1));
				}else{
					addTableHtml(dataMoneyFlowWeekIo[i],"tzlcTableId",(i+1));
				}
				
				loadJeDate();//初始化时间控件
			}
			pagequery(1,"tzlcTableId");
		}
		
		function loadJeDate(){
			$(' input.timeCi').jeDate(
				{
					format:"YYYY-MM-DD"
				}
			)
		}
		
		//获取月计划数据
		function getMonthPlan(elem,val){
			var org = $("#organalId").val();
			if(elem!=null&&elem[0].id=="startDate"){
				weekStart = val;
			}else if(elem!=null&&elem[0].id=="endDate"){
				weekEnd = val;
			}
			if(org!=""&&weekStart!=""&&weekEnd!=""){
				var url="${pageContext.request.contextPath}/moneyFlowPlanWeek/getMonthPlan";
				$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: {"org":org,
			    		"startD":weekStart,
			     		"endD":weekEnd},
		         contentType:"application/x-www-form-urlencoded",
			     success : function(data) {
			     //$.unblockUI();
			     var monthPlanData=JSON.parse(data);
						loadData(monthPlanData);
			     },  
			     error : function(data) {
			     	//$.unblockUI();
			     }  
			}); 
			}
		}
			
		/*
		 * 计算
		 */
		$(".w25").on("change",function(){
			//数据计算*****-- 经营性净流量=经营性流入-经营性流出
			var operationalInflow = $("#operationalInflow").val();
			var operationalOutflow = $("#operationalOutflow").val();
			if(operationalInflow!=""&&operationalOutflow!=""){
				$("#operationalNetFlow").val(parseFloat(Subtr(operationalInflow,operationalOutflow)).toFixed(2));//parseFloat(operationalInflow)-parseFloat(operationalOutflow)
			};
			//数据计算*****-- 筹资性净流量=筹资性流入-筹资性流出
			var capitalInflow = $("#capitalInflow").val();	//筹资性流入
			var capitalOutflow = $("#capitalOutflow").val();//筹资性流出
			if(capitalInflow!=""&&capitalOutflow!=""){
				$("#capitalNetFlow").val(parseFloat(Subtr(capitalInflow,capitalOutflow)).toFixed(2));//parseFloat(capitalInflow)-parseFloat(capitalOutflow)
			};
			
			//数据计算*****-- 投资性净流量(万元)=投资性流入-投资性流出
			var investmentInflow = $("#investmentInflow").val();	//投资性流入
			var investmentOutflow = $("#investmentOutflow").val();	//投资性流出
			if(investmentInflow!=""&&investmentOutflow!=""){
				$("#investmentNetFlow").val(parseFloat(Subtr(investmentInflow,investmentOutflow)).toFixed(2));//parseFloat(investmentInflow)-parseFloat(investmentOutflow)
			};
			//数据计算*****-- 现金流入=经营性流入+筹资性流入+投资性流入
			if(operationalInflow!=""&&capitalInflow!=""&&investmentInflow!=""){
				$("#cashInflow").val(parseFloat(accAdd(accAdd(operationalInflow,capitalInflow).toString(),investmentInflow)).toFixed(2));//parseFloat(operationalInflow)+parseFloat(capitalInflow)+parseFloat(investmentInflow)
			};
			//数据计算*****-- 现金流出=经营性流出+筹资性流出+投资性流出
			if(operationalOutflow!=""&&capitalOutflow!=""&&investmentOutflow!=""){
				$("#cashOutflow").val(parseFloat(accAdd(accAdd(operationalOutflow,capitalOutflow).toString(),investmentOutflow)).toFixed(2));//parseFloat(operationalOutflow)+parseFloat(capitalOutflow)+parseFloat(investmentOutflow)
			};
			//数据计算*****-- 现金净流量=经营性净流量+筹资性净流量+投资性净流量
			if(operationalInflow!=""&&capitalInflow!=""&&investmentInflow!=""&&operationalOutflow!=""&&capitalOutflow!=""&&investmentOutflow!=""){
				var xjlr = accAdd(accAdd(operationalInflow,capitalInflow).toString(),investmentInflow);
				var xjlc = accAdd(accAdd(operationalOutflow,capitalOutflow).toString(),investmentOutflow);
				$("#netCashFlow").val(parseFloat(Subtr(xjlr,xjlc)).toFixed(2));//(parseFloat(operationalInflow)+parseFloat(capitalInflow)+parseFloat(investmentInflow)) - (parseFloat(operationalOutflow)+parseFloat(capitalOutflow)+parseFloat(investmentOutflow))
			};
			//数据计算*****-- 期末可用头寸=期初可用头寸+现金净流量
			var initialAvailableCash = $("#initialAvailableCash").val();//筹资性流出
			if(operationalInflow!=""&&capitalInflow!=""&&investmentInflow!=""&&operationalOutflow!=""&&capitalOutflow!=""&&investmentOutflow!=""&&initialAvailableCash!=""){
				var qmxjlr = accAdd(accAdd(operationalInflow,capitalInflow).toString(),investmentInflow);
				var qmxjlc = accAdd(accAdd(operationalOutflow,capitalOutflow).toString(),investmentOutflow);
				$("#finalAvailableCash").val(parseFloat(accAdd(Subtr(qmxjlr,qmxjlc).toString(),initialAvailableCash)).toFixed(2));//+parseFloat(initialAvailableCash)
			};
		});
		
		//加
		function accAdd(arg1,arg2){  
			var r1,r2,m;  
			try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
			try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
			m=Math.pow(10,Math.max(r1,r2))  
			return (arg1*m+arg2*m)/m
		}
		//减
		function Subtr(arg1,arg2){ 
			var r1,r2,m,n; 
			try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
			try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
			m=Math.pow(10,Math.max(r1,r2)); 
			n=(r1>=r2)?r1:r2; 
			return ((arg1*m-arg2*m)/m).toFixed(n); 
		}
		
		
		
		//下拉框加载
		function loadSelect(data,option,map){
			var tr = "";
			tr+="<td><select name='"+option+"' style='width: 100%;'>";
			option = option.split(".")[1];
			for(var i=0;i<map.length;i++){
				if(data!=null&&map[i].id==data[option]){
					tr+="<option selected='selected' value='"+map[i].id+"'>"+map[i].description+"</option>";
				}else{
					tr+="<option value='"+map[i].id+"'>"+map[i].description+"</option>";
				}
			}
			tr+="</select></td>";
			/* if(option=="financingType"){
				var financingType = eval("(" + '${financingType}' + ")");
				tr+="<td><select name='dataMoneyFlowWeekCiTemp.financingType' style='width: 100%;'>";
				for(var i=0;i<financingType.length;i++){
					if(data!=null&&financingType[i].id==data.financingType){
						tr+="<option selected='selected' value='"+financingType[i].id+"'>"+financingType[i].description+"</option>";
					}else{
						tr+="<option value='"+financingType[i].id+"'>"+financingType[i].description+"</option>";
					}
				}
				tr+="</select></td>";
			}else if(option=="newOrRenewed"){
				var sequelNew = eval("(" + '${sequelNew}' + ")");
				tr+="<td><select name='dataMoneyFlowWeekCiTemp.newOrRenewed' style='width: 100%;'>";
				for(var i=0;i<sequelNew.length;i++){
					if(data!=null&&sequelNew[i].id==data.newOrRenewed){
						tr+="<option selected='selected' value='"+sequelNew[i].id+"'>"+sequelNew[i].description+"</option>";
					}else{
						tr+="<option value='"+sequelNew[i].id+"'>"+sequelNew[i].description+"</option>";
					}
				}
				tr+="</select></td>";
			}else if(option=="placeOrderCurrency"){
				var currencyKind = eval("(" + '${currencyKind}' + ")");
				tr+="<td><select name='dataMoneyFlowWeekCiTemp.placeOrderCurrency' style='width: 100%;'>";
				for(var i=0;i<currencyKind.length;i++){
					if(data!=null&&currencyKind[i].id==data.placeOrderCurrency){
						tr+="<option selected='selected' value='"+currencyKind[i].id+"'>"+currencyKind[i].description+"</option>";
					}else{
						tr+="<option value='"+currencyKind[i].id+"'>"+currencyKind[i].description+"</option>";
					}
				}
				tr+="</select></td>";
			}else if(option=="equityFinancing"){
				var truefalse = eval("(" + '${trueOrFalse}' + ")");
				tr+="<td><select name='dataMoneyFlowWeekCiTemp.equityFinancing' style='width: 100%;'>";
				for(var i=0;i<truefalse.length;i++){
					if(data!=null&&truefalse[i].id==data.equityFinancing){
						tr+="<option selected='selected' value='"+truefalse[i].id+"'>"+truefalse[i].description+"</option>";
					}else{
						tr+="<option value='"+truefalse[i].id+"'>"+truefalse[i].description+"</option>";
					}
				}
				tr+="</select></td>";
			}else if(option=="paymentType"){
				var financingType = eval("(" + '${financingType}' + ")");
				tr+="<td><select name='dataMoneyFlowWeekCoTemp.paymentType' style='width: 100%;'>";
				for(var i=0;i<financingType.length;i++){
					if(data!=null&&financingType[i].id==data.paymentType){
						tr+="<option selected='selected' value='"+financingType[i].id+"'>"+financingType[i].description+"</option>";
					}else{
						tr+="<option value='"+financingType[i].id+"'>"+financingType[i].description+"</option>";
					}
				}
				tr+="</select></td>";
			}else if(option=="newOrRenewedCo"){
				var sequelNew = eval("(" + '${sequelNew}' + ")");
				tr+="<td><select name='dataMoneyFlowWeekCoTemp.newOrRenewedCo' style='width: 100%;'>";
				for(var i=0;i<sequelNew.length;i++){
					if(data!=null&&sequelNew[i].id==data.newOrRenewed){
						tr+="<option selected='selected' value='"+sequelNew[i].id+"'>"+sequelNew[i].description+"</option>";
					}else{
						tr+="<option value='"+sequelNew[i].id+"'>"+sequelNew[i].description+"</option>";
					}
				}
				tr+="</select></td>";
			}else if(option=="paymentCurrency"){
				var currencyKind = eval("(" + '${currencyKind}' + ")");
				tr+="<td><select name='dataMoneyFlowWeekCoTemp.paymentCurrency' style='width: 100%;'>";
				for(var i=0;i<currencyKind.length;i++){
					if(data!=null&&currencyKind[i].id==data.newOrRenewed){
						tr+="<option selected='selected' value='"+currencyKind[i].id+"'>"+currencyKind[i].description+"</option>";
					}else{
						tr+="<option value='"+currencyKind[i].id+"'>"+currencyKind[i].description+"</option>";
					}
				}
				tr+="</select></td>";
			}else if(option=="guaranteeRepaymentPlan"){
				var truefalse = eval("(" + '${trueOrFalse}' + ")");
				tr+="<td><select name='dataMoneyFlowWeekCoTemp.guaranteeRepaymentPlan' style='width: 100%;'>";
				for(var i=0;i<truefalse.length;i++){
					if(data!=null&&truefalse[i].id==data.equityFinancing){
						tr+="<option selected='selected' value='"+truefalse[i].id+"'>"+truefalse[i].description+"</option>";
					}else{
						tr+="<option value='"+truefalse[i].id+"'>"+truefalse[i].description+"</option>";
					}
				}
				tr+="</select></td>";
			} */
			
			return tr;
		}
		
		//删除某一行
		function del(tr,tableId){
			$(tr).parent().parent().remove();
			pagequery(1,tableId);
			loadNum(tableId);
		}
		
		//删除序号生成
		function loadNum(tableId){
			var trNum = $("#"+tableId+" tr").length-1;
			for(var i=0;i<trNum;i++){
				$("."+tableId+"tdNum:eq("+i+")").html(i+1);
			}
		}
		
		//文件选择事件
		function openBrowse(num){
			$("#fileTypeId").val(num);// num:  0未选择/1筹资流入/2筹资流出/3投资流出
			var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false; 
			if(ie){ 
				document.getElementById("czlrExcelImportId").click(); 
			}else{
				var a=document.createEvent("MouseEvents");//处理 
				a.initEvent("click", true, true);  
				document.getElementById("czlrExcelImportId").dispatchEvent(a); 
			} 
		} 
		
		//文件选择后
		function changeFiles(){
			/* if($(".openReportWinClass").val()==""){
				return;
			} */
				
			$("#excelNameId").val($("#czlrExcelImportId").val().split(".xl")[1]);//sx&&s
			if(!fileChange($("#czlrExcelImportId")[0])){
				 $("#czlrExcelImportId").val("");
				return false;
			}
			
			var formData = new FormData($("#fileForm")[0]);
		   	//var file = $("#czlrExcelImportId").get(0).files[0];
		   	//异步文件上传
		    $.ajax({  
			     url : "${pageContext.request.contextPath}/moneyFlowPlanWeek/excelReport",  
			     type : "POST",  
			     data: formData,
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     	$.unblockUI();
			    		console.info(JSON.parse(data));	
			    		var dataArray = JSON.parse(data);
			    		loadExcelData(dataArray);
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
		}
	 
		//加载Excel数据
		function loadExcelData(excelData){
			if(excelData[1]){
				$("#czlrTableId tr:not(.table_header)").remove();
				var noDataHtml="<tr id='czlrTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
				$("#czlrTableId").append(noDataHtml);
				for(var i = 1;i<excelData[1].length;i++){
					addTableHtml(excelData[1][i],"czlrTableId",-999);
				}
				loadNum("czlrTableId");
				pagequery(1,"czlrTableId");
			}else if(excelData[2]){
				$("#czlcTableId tr:not(.table_header)").remove();
				var noDataHtml="<tr id='czlcTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
				$("#czlcTableId").append(noDataHtml);
				for(var i = 1;i<excelData[2].length;i++){
					addTableHtml(excelData[2][i],"czlcTableId",-999);
				}
				loadNum("czlcTableId");
				pagequery(1,"czlcTableId");
			}else if(excelData[3]){
				$("#tzlcTableId tr:not(.table_header)").remove();
				var noDataHtml="<tr id='tzlcTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
				$("#tzlcTableId").append(noDataHtml);
				for(var i = 1;i<excelData[3].length;i++){
					addTableHtml(excelData[3][i],"tzlcTableId",-999);
				}
				loadNum("tzlcTableId");
				pagequery(1,"tzlcTableId");
				
			}
			loadJeDate();
			$("#czlrExcelImportId").val("");
			$("#czlrExcelImportId").text("");
		}
		
		//Excel导出
		function excelOutput(numType){// num:  0未选择/1筹资流入/2筹资流出/3投资流出
			var pid = $("#pid").val();
			/*if(!pid){
				win.generalAlert("请在修改或查看页面导出数据！");
				return;
			}*/
			window.open("${pageContext.request.contextPath}/moneyFlowPlanWeek/excelOutput?pid="+pid+"&numType="+numType);
		}
		
		//file校验
		//验证文件类型和大小
		function fileChange(target){
		console.log(target.value)
			//检测上传文件的类型 
			var imgName = target.value;
		    var ext,idx;   
		    if (imgName == ''){  
		        win.generalAlert("请选择需要上传的文件!");  
		        return false; 
		    } else {   
	           //检测上传文件的大小        
			    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
			    var fileSize = 0;
			               
			    if (isIE && !target.files){       
			        var filePath = target.value;       
			        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
			        var file = fileSystem.GetFile (filePath);       
			        fileSize = file.Size;      
			    } else {      
			        fileSize = target.files[0].size;       
			    }     
			
			    var size = fileSize / 1024*1024;   
			
			    if(size>(1024*1024*3)){ 
			    	 win.errorAlert("上传文件不能大于3M!"); 
			      	return false; 
			    }
	            return true;    
		    }
		}
		
		function sumData(){
				 var org = $("#organalId").val();
				 var date = $('#date').val();
				 var week = $('#week').val();
				 var startDate = $('#startDate').val();
				 var endDate=$('#endDate').val();
				 
				 var url = "${pageContext.request.contextPath}/moneyFlowPlanWeek/sumDataForNew";
				 $.ajax({
				 	type:'POST',
				 	data:{org:org,date:date,week:week,startDate:startDate,endDate:endDate},
				 	dataType:'json',
				 	url:url,
				 	success:function(data){
				 	  $.each(data, function(i,item){
							if(i=='bean'){						
							 	$("#operationalInflow").val(item.operationalInflow);
						 		$("#operationalOutflow").val(item.operationalOutflow);
						 		$("#operationalNetFlow").val(item.operationalNetFlow);
						 		$("#capitalInflow").val(item.capitalInflow);				 		
						 		$("#capitalOutflow").val(item.capitalOutflow);				 		
						 		$("#capitalNetFlow").val(item.capitalNetFlow);				 		
						 		$("#investmentInflow").val(item.investmentInflow);
						 		$("#investmentOutflow").val(item.investmentOutflow);				 		
						 		$("#investmentNetFlow").val(item.investmentNetFlow);				 		
						 		$("#cashInflow").val(item.cashInflow);				 		
						 		$("#cashOutflow").val(item.cashOutflow);
						 		$("#netCashFlow").val(item.netCashFlow);				 		
						 		$("#initialAvailableCash").val(item.initialAvailableCash);				 		
						 		$("#finalAvailableCash").val(item.finalAvailableCash);												
							}
							if(i=='beanInfo'){				
								console.log(item);			
								loadData(item);											
							}									
 					  });	 						 		
					}
				 }); 
		}
	</script>
	
</html>
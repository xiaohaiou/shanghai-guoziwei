<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>融资下账数据</title>
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
		</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改融资下账数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增融资下账数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
		
	</c:choose>
	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<form:hidden path="id"/>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25">
						<input type="hidden" id="organalID" name="org" value="${entityview.org }" >
						<input name="orgname" id="checkedCompanyName" value="${entityview.orgname }" check="NotEmpty_string_._._._._." readonly="true">
						<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
							<ul id="com_ztree" class="ztree">
			
							</ul>
					   </div>
					   
				</span>
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
			    <label class="w20"><span class="required"> * </span>年份:</label>
				<input class="w25 time" type="text" id="year" name="year" value="${entityview.year }" readonly="true" check="NotEmpty_string_._._._._." class="time" />
				<label class="w20"><span class="required"> * </span>月份:</label>
				<span class="w25">
					<select  name="month" id="month" class="selectpicker"  >
						<c:forEach var= "temp" begin= "1" step= "1" end= "12">
							<option value="${temp}" <c:if test="${entityview.month == temp}">selected="selected"</c:if>>
								${temp}
							</option>
						</c:forEach>
					</select>
				</span>
				<div style="margin-left: 215px;margin-bottom: 4px">
				    <input type="button" value="汇总" class="form_btn" id="qrybtn" onclick="summary()">
				</div>
				<label class="w20"><span class="required"> * </span>境内下账总额(亿元):</label>
				<form:input class="w25" path="domesticAccountAmounts" id="domesticAccountAmounts" check="NotEmpty_double_16_4_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>境外下账总额(亿美元):</label>
				<form:input class="w25" path="overseasAccountAmounts" id="overseasAccountAmounts" check="NotEmpty_double_16_4_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>月度美元汇率值:</label>
				<form:input class="w25" path="monthlyDollarExchangeRate" id="monthlyDollarExchangeRate" check="NotEmpty_double_16_4_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>融资下账总额(亿元):</label>
				<form:input class="w25" path="financingAccountAmounts" id="financingAccountAmounts" readonly="true"/> 
				<label class="w20"><span class="required"> * </span>境内下账成本(%):</label>
				<form:input class="w25" path="domesticAccountCostRate" id="domesticAccountCostRate" check="NotEmpty_double_6_2_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>境外下账成本(%):</label>
				<form:input class="w25" path="overseasAccountCostRate" id="overseasAccountCostRate" check="NotEmpty_double_6_2_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>年息(亿元):</label>
				<form:input class="w25" path="annualInterest" id="annualInterest" readonly="true"/>
				<label class="w20"><span class="required"> * </span>综合融资下账成本(%):</label>
				<form:input class="w25" path="financingAccountCost" id="financingAccountCost" readonly="true"/>
				
				<label class="w20">权益性融资(亿元):</label>
				<form:input class="w25" path="equityFinancing" id="equityFinancing" onblur="calrate()"/>
				<label class="w20">股权盘活融资(亿元):</label>
				<form:input class="w25" path="stockEquityFinancing" id="stockEquityFinancing" onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>债券融资(亿元):</label>
				<form:input class="w25" path="bondFinancing" id="bondFinancing" check="NotEmpty_double_16_4_0+_。_." onblur="calrate()"/>
				<label id='typeLabelID' class="w20"><span class="required"></span>类型：</label>
					<span id='typeSelected' class="w25">
						<select  name="type" id="type" class="selectpicker"  >
							<option value="-2" >------请选择---------</option>
							<c:forEach items="${reportFinanceAccountType}" var="atype">
								<option value="${atype.num}"  <c:if test="${entityview.type == atype.num}">selected="selected"</c:if>>
									${atype.description}
								</option>
							</c:forEach>
						</select>
					</span>
					<br/>
				<span>	
				<label class="w20">备注:</label>
				<textarea rows="3" cols="5" class="w70" name="remark" id="remark"  path="remark"   maxlength="500">${entityview.remark}</textarea>
				</span>
				<br>
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
<script type="text/javascript" src="<c:url value="/js/vailds.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>

<script type="text/javascript">

		$(document).ready(function(){
			var type = '${entityview.type}';
			if('-1'==type){
				$('#typeLabelID').hide();
				$('#typeSelected').hide();				
				$('#type').find("option:selected").val('-1');
				$('#type').find("option:selected").val('-1');
			}	
		});

		function commitcheck()
		{
			if($("#financingAccountAmounts").val()=="")
			{
				win.errorAlert("融资下账总额(亿元)不能为空");
				return false;
			}
			if($("#annualInterest").val()=="")
			{
				win.errorAlert("年息(亿元)不能为空");
				return false;
			}
			if($("#financingAccountCost").val()=="")
			{
				win.errorAlert("综合融资下账成本(%)不能为空");
				return false;
			}
			/*
			if(parseFloat($("#financingAccountAmounts").val()) <= parseFloat($("#equityFinancing").val())+parseFloat($("#stockEquityFinancing").val())+parseFloat($("#bondFinancing").val())){
				win.errorAlert("权益性融资(亿元)+股权盘活融资(亿元)+债券融资(亿元)之和应小于融资下账总额(亿元)");
				return false;
			}*/
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
			var url = "${pageContext.request.contextPath}/reportFinancingAccount/save";
			
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
		})
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !commitcheck())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/reportFinancingAccount/saveandreport";
			
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
		})

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
	
	
		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		)
		
		$(' input.date').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
	
		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#checkedCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
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
		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="orgname"]').val('');
		});
		
		function calrate()
		{
			//境内下账总额
			var domesticAccountAmounts=$("#domesticAccountAmounts").val();
			//境内下账成本
			var domesticAccountCostRate=$("#domesticAccountCostRate").val();
			//境外下账总额
			var overseasAccountAmounts=$("#overseasAccountAmounts").val();
			//境外下账成本
			var overseasAccountCostRate=$("#overseasAccountCostRate").val();
			//月度美元汇率
			var monthlyDollarExchangeRate=$("#monthlyDollarExchangeRate").val();
			//年息
			var annualInterest="";
			//融资下账总额
			var financingAccountAmounts="";
			//权益性融资(亿元)
			var equityFinancing=$("#equityFinancing").val();
			//股权盘活融资(亿元)
			var stockEquityFinancing=$("#stockEquityFinancing").val();
			//债券融资(亿元)
			var bondFinancing=$("#bondFinancing").val();
			
			//备注
			var remark=$("#remark").val();
			
			//计算融资下账总额
			if(domesticAccountAmounts != "" && overseasAccountAmounts != "" && monthlyDollarExchangeRate != ""){
				financingAccountAmounts=(parseFloat(domesticAccountAmounts)+parseFloat(overseasAccountAmounts)*parseFloat(monthlyDollarExchangeRate)).toFixed(4);
				$("#financingAccountAmounts").text(financingAccountAmounts);
				$("#financingAccountAmounts").val(financingAccountAmounts);
			}else{
				$("#financingAccountAmounts").text("");
				$("#financingAccountAmounts").val("");
				financingAccountAmounts="";
			}
			//检验境内下账成本、境外下账成本
			if(domesticAccountCostRate !=""){
				if(parseFloat(domesticAccountCostRate) > 100){
					win.errorAlert("境内下账成本(%)不能大于100%");
					$("#domesticAccountCostRate").text("");
					$("#domesticAccountCostRate").val("");
					domesticAccountCostRate="";
				}
			}
			if(overseasAccountCostRate !=""){
				if(parseFloat(overseasAccountCostRate) > 100){
					win.errorAlert("境外下账成本(%)不能大于100%");
					$("#overseasAccountCostRate").text("");
					$("#overseasAccountCostRate").val("");
					overseasAccountCostRate="";
				}
			}
			//计算年息
			if(domesticAccountAmounts != ""&& domesticAccountCostRate != "" && overseasAccountAmounts != "" && monthlyDollarExchangeRate != "" && overseasAccountCostRate != "")
			{	
				annualInterest=((parseFloat(domesticAccountAmounts)*parseFloat(domesticAccountCostRate)+parseFloat(overseasAccountAmounts)*parseFloat(monthlyDollarExchangeRate)*parseFloat(overseasAccountCostRate))/100).toFixed(4);
				$("#annualInterest").text(annualInterest);
				$("#annualInterest").val(annualInterest);
			}else	
			{	
				$("#annualInterest").text("");
				$("#annualInterest").val("");
				annualInterest="";
			}
			//计算综合融资下账成本
			if(annualInterest !="" && financingAccountAmounts !="")
			{	
				var rate=((parseFloat(annualInterest)/parseFloat(financingAccountAmounts))*100).toFixed(2);
				$("#financingAccountCost").text(rate);
				$("#financingAccountCost").val(rate);
			}else	
			{	
				$("#financingAccountCost").text("");
				$("#financingAccountCost").val("");
			}
			/*
			//检验：权益性融资+股权盘活融资+债券融资之和应小于融资下账总额
			if(equityFinancing !="" && stockEquityFinancing !="" && bondFinancing !="" && financingAccountAmounts !="")
			{	
				if(financingAccountAmounts <= (parseFloat(equityFinancing)+parseFloat(stockEquityFinancing)+parseFloat(bondFinancing))){
					win.errorAlert("权益性融资(亿元)+股权盘活融资(亿元)+债券融资(亿元)之和应小于融资下账总额(亿元)");
				}
			}
			*/
		}
		
		

	function summary(){
		
				$('#typeLabelID').hide();
				$('#typeSelected').hide();
				
				$('#type').find("option:selected").val('-1');
						
				var	year = $("#year").val();
				var compId = $("#organalID").val();
				var month = $("#month").val();


					if(compId != "" && year != "" && month != ""){
						$.ajax({
							type:"POST",
							url:"${pageContext.request.contextPath}/reportFinancingAccount/getSummaryData",
							dataType:'json',
							data:{
								compId:compId,
								year:year,
								month:month
							},
							success:function(data){						  
							  $("#domesticAccountAmounts").val(data.domesticAccountAmounts);
							  $("#overseasAccountAmounts").val(data.overseasAccountAmounts);
							  $("#equityFinancing").val(data.equityFinancing);
							  $("#stockEquityFinancing").val(data.stockEquityFinancing);
							  $("#bondFinancing").val(data.bondFinancing);
							  $('#type').find("option:selected").val('-1');
							}
						});
					}else{
					          $("#domesticAccountAmounts").val();
							  $("#overseasAccountAmounts").val();
							  $("#equityFinancing").val();
							  $("#stockEquityFinancing").val();
							  $("#bondFinancing").val();
					}

		}
	</script>
</html>
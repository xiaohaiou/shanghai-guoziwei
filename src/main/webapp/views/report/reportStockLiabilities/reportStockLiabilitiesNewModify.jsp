<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>存量负债数据</title>
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
				<span class="glyphicon glyphicon-pencil"></span>修改存量负债数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增存量负债数据
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
						<input type="hidden" id="organalID" name="org" value="${entityview.org}" >
						<input name="orgname" id="checkedCompanyName" value="${entityview.orgname}" check="NotEmpty_string_._._._._." readonly="true">
						<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
							<ul id="com_ztree" class="ztree">
			
							</ul>
					   </div>			   
				</span>
			<!--<label class="w20"></label>
				<label class="w25 setleft"></label>-->
			    <label class="w20"><span class="required"> * </span>年份:</label>
				<span class="w25">
					<input class="w25 time" type="text" id="year" name="year" value="${entityview.year}" check="NotEmpty_string_._._._._." readonly="true" class="time" />
				</span>
				<label class="w20"><span class="required"> * </span>月份:</label>
				<span class="w20">
					<select  name="month" id="month" class="selectpicker"  >
						<c:forEach var= "temp" begin= "1" step= "1" end= "12">
							<option value="${temp}" <c:if test="${entityview.month == temp}">selected="selected"</c:if>>
								${temp}
							</option>
						</c:forEach>
					</select>
				</span>
				<label class="w25"></label>
				<span class="w20"><input type="button" id="sumId" name="sum" style="background:#009999" value="汇总"/></span>
				<br/><span id="tip" style='color:#ff0000'/>
			</div>
			<h3 class="data_title1">境内人民币负债占比</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>境内存量负债(亿元):</label>
				<form:input class="w25" path="domesticStockLiabilities" id="domesticStockLiabilities" check="NotEmpty_double_16_4_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>境内人民币负债(亿元):</label>
				<form:input class="w25" path="domesticRmbLiabilities" id="domesticRmbLiabilities" check="NotEmpty_double_16_4_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>境内人民币负债占比(%):</label>
				<form:input class="w25" path="proportionRmbLiabilities" id="proportionRmbLiabilities" readonly="true"/>
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
			</div>
			<h3 class="data_title1">境外负债按币种分类明细<a class="model_btn_plus" id="reportOverseasLiabilitiesDetail" onclick="addEntity(this,null,null)">+</a></h3> 
			<div class="model_part">
				<div id="reportOverseasLiabilitiesDetailList">
				
				</div>
			</div>
			<h3 class="data_title1">境外负债占比</h3> 
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>境外存量负债(亿元):</label>
				<form:input class="w25" path="overseasStockLiabilities" id="overseasStockLiabilities" check="NotEmpty_double_16_4_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>整体存量负债(亿元):</label>
				<form:input class="w25" path="totalStockLiabilities" id="totalStockLiabilities" readonly="true"/>
				<label class="w20"><span class="required"> * </span>境外负债占比(%):</label>
				<form:input class="w25" path="proportionForeignLiabilities" id="proportionForeignLiabilities" readonly="true"/>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
			</div>
			<h3 class="data_title1">整体存量负债按负债类型分类</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>银行贷款类存量负债(亿元):</label>
				<form:input class="w25" path="bankLoanStockLiabilities" id="bankLoanStockLiabilities" check="NotEmpty_double_16_4_0+_。_."/>
				<label class="w20"><span class="required"> * </span>信托类存量负债(亿元):</label>
				<form:input class="w25" path="entrustStockLiabilities" id="entrustStockLiabilities" check="NotEmpty_double_16_4_0+_。_."/>
				<label class="w20"><span class="required"> * </span>票据类存量负债(亿元):</label>
				<form:input class="w25" path="billStockLiabilities" id="billStockLiabilities" check="NotEmpty_double_16_4_0+_。_."/>
				<label class="w20"><span class="required"> * </span>基金类存量负债(亿元):</label>
				<form:input class="w25" path="fundStockLiabilities" id="fundStockLiabilities" check="NotEmpty_double_16_4_0+_。_."/>
				<label class="w20"><span class="required"> * </span>债券类存量负债(亿元):</label>
				<form:input class="w25" path="bondStockLiabilities" id="bondStockLiabilities" check="NotEmpty_double_16_4_0+_。_."/>
				<label class="w20"><span class="required"> * </span>融资租赁类存量负债(亿元):</label>
				<form:input class="w25" path="financingRentStockLiabilities" id="financingRentStockLiabilities" check="NotEmpty_double_16_4_0+_。_."/>
				<label class="w20"><span class="required"> * </span>其它负债类存量负债(亿元):</label>
				<form:input class="w25" path="othersStockLiabilities" id="othersStockLiabilities" check="NotEmpty_double_16_4_0+_。_."/>
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
			</div>
			<h3 class="data_title1">存量负债成本</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>存量负债成本(%):</label>
				<form:input class="w25" path="costStockLiabilities" id="costStockLiabilities" check="NotEmpty_double_6_2_0+_。_." onblur="calrate()"/>
				<input type="hidden" id="financialCostStockLiabilities" name="financialCostStockLiabilities"> 
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
			</div>
			<h3 class="data_title1">长期负债占比</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>长期负债(亿元):</label>
				<form:input class="w25" path="longTermLiabilities" id="longTermLiabilities" check="NotEmpty_double_16_4_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>长期负债占比(%):</label>
				<form:input class="w25" path="proportionLongTermLiabilities" id="proportionLongTermLiabilities" readonly="true"/>
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2">保存并上报</button>
				<button id="closeID" class="btn btn-default model model_btn_close">关闭</button>
				
			</div>
			<input type="hidden" id="stringList1" name="stringList1">
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

	var data1 = ${data1};
	if(data1 == 1){
		data1 = [];
	}
	function initNodeList(v,pageNums,op,data){
		var stringList = "";
		if(data.length != 0){
			stringList = JSON.stringify(data);
		}
		$.ajax({
			data:{
				stringList:stringList,
				pageNums:pageNums,
				op:op
			},
			url:"${pageContext.request.contextPath}/"+v+"/list",
			type:"POST",
			dataType:"text",
			async:false,
			success:function(data){
				$("#"+v+"List").children().remove();
				$("#"+v+"List").append(data);
			}
		});	
	}
	initNodeList('reportOverseasLiabilitiesDetail',1,"",data1);//初始化境外负债按币种分类明细表
	
	//新增
	function addEntity(obj,overseasCurrency,overseasLiabilitiesAmounts){
		/* var myDate = new Date();
		var str = myDate.getFullYear()+"-"+(myDate.getMonth())+"-"+myDate.getDate(); */
		var v=obj.id;
		var newEntity= {};
		newEntity.id=null;
		newEntity.stockLiabilitiesId="";
		newEntity.overseasCurrency=69;
		newEntity.overseasLiabilitiesAmounts="";
		if(null!=overseasCurrency)	
			newEntity.overseasCurrency=overseasCurrency;
		if(null!=overseasLiabilitiesAmounts)
			newEntity.overseasLiabilitiesAmounts=overseasLiabilitiesAmounts;	
		data1.push(newEntity);
		initNodeList(v,1,"add",data1);
	}
	
	//编辑
	function modifyEntity(num,v,obj){
		var propertyName = obj.id;
		var propertyVal = obj.value;
		//需要修改的index
		var modifyNum = num -1;
		var theEntity = data1[modifyNum];
		theEntity[propertyName] = propertyVal;
		data1.splice(modifyNum,1,theEntity);
		console.log(data1);
	}

	//删除
	function del(num,v){
		win.confirm('确定要删除此明细？',function(r){
			if(r){
				//需要删除的index
				var delNum = num -1;
				data1.splice(delNum,1);
				win.successAlert('删除成功！');
				initNodeList(v,1,"del",data1);
			}
		});
	}

</script>
<script type="text/javascript">

		function commitcheck()
		{
			if($("#proportionRmbLiabilities").val()=="")
			{
				win.errorAlert("境内人民币负债占比不能为空");
				return false;
			}
			if($("#totalStockLiabilities").val()=="")
			{
				win.errorAlert("整体存量负债不能为空");
				return false;
			}
			if($("#proportionForeignLiabilities").val()=="")
			{
				win.errorAlert("境外负债占比不能为空");
				return false;
			}
			if((parseFloat($("#bankLoanStockLiabilities").val())+parseFloat($("#entrustStockLiabilities").val())+parseFloat($("#billStockLiabilities").val())+parseFloat($("#fundStockLiabilities").val())+parseFloat($("#bondStockLiabilities").val())+parseFloat($("#financingRentStockLiabilities").val())+parseFloat($("#othersStockLiabilities").val())).toFixed(2)!=parseFloat($("#totalStockLiabilities").val()).toFixed(2))
			{
				win.errorAlert("整体存量负债按负债类型分类合计不等于整体存量负债");
				return false;
			}
			if($("#proportionLongTermLiabilities").val()=="")
			{
				win.errorAlert("长期负债占比不能为空");
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
			var url = "${pageContext.request.contextPath}/reportStockLiabilities/save";
			
			var stringList1 = "";
			if(data1.length != 0){
				stringList1 = JSON.stringify(data1);
				$("#stringList1").val(stringList1);
			}
			
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
			var url = "${pageContext.request.contextPath}/reportStockLiabilities/saveandreport";
			
			var stringList1 = "";
			if(data1.length != 0){
				stringList1 = JSON.stringify(data1);
				$("#stringList1").val(stringList1);
			}
			
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
			//境内存量负债
			var domesticStockLiabilities=$("#domesticStockLiabilities").val();
			//境内人民币负债
			var domesticRmbLiabilities=$("#domesticRmbLiabilities").val();
			//境外存量负债
			var overseasStockLiabilities=$("#overseasStockLiabilities").val();
			//整体存量负债
			var totalStockLiabilities="";
			//存量负债成本
			var costStockLiabilities=$("#costStockLiabilities").val();
			//长期负债
			var longTermLiabilities=$("#longTermLiabilities").val();
			
			//计算整体存量负债
			if(domesticStockLiabilities != "" && overseasStockLiabilities != ""){
				totalStockLiabilities=parseFloat(domesticStockLiabilities)+parseFloat(overseasStockLiabilities);
				$("#totalStockLiabilities").text(totalStockLiabilities);
				$("#totalStockLiabilities").val(totalStockLiabilities);
			}else{
				$("#totalStockLiabilities").text("");
				$("#totalStockLiabilities").val("");
				totalStockLiabilities="";
			}
			//计算境内人民币负债占比
			if(domesticRmbLiabilities !="" && domesticStockLiabilities !="")
			{	
				if(parseFloat(domesticRmbLiabilities) <= parseFloat(domesticStockLiabilities)){
					var rate=((parseFloat(domesticRmbLiabilities)/parseFloat(domesticStockLiabilities))*100).toFixed(2);
					$("#proportionRmbLiabilities").text(rate);
					$("#proportionRmbLiabilities").val(rate);
				}else{
					win.errorAlert("境内人民币负债(亿元)不能大于境内存量负债(亿元)");
					$("#proportionRmbLiabilities").text("");
					$("#proportionRmbLiabilities").val("");				
				}
			}else	
			{	
				$("#proportionRmbLiabilities").text("");
				$("#proportionRmbLiabilities").val("");
			}
			//计算境外负债占比
			if(overseasStockLiabilities !="" && totalStockLiabilities !="")
			{	
				var rate=((parseFloat(overseasStockLiabilities)/parseFloat(totalStockLiabilities))*100).toFixed(2);
				$("#proportionForeignLiabilities").text(rate);
				$("#proportionForeignLiabilities").val(rate);
			}else	
			{	
				$("#proportionForeignLiabilities").text("");
				$("#proportionForeignLiabilities").val("");
			}
			//计算长期负债占比
			if(longTermLiabilities !="" && totalStockLiabilities !="")
			{	
				if(parseFloat(longTermLiabilities) <= parseFloat(totalStockLiabilities)){
					var rate=((parseFloat(longTermLiabilities)/parseFloat(totalStockLiabilities))*100).toFixed(2);
					$("#proportionLongTermLiabilities").text(rate);
					$("#proportionLongTermLiabilities").val(rate);
				}else{
					win.errorAlert("长期负债(亿元)不能大于整体存量负债(亿元)");
					$("#proportionLongTermLiabilities").text("");
					$("#proportionLongTermLiabilities").val("");				
				}
			}else	
			{	
				$("#proportionLongTermLiabilities").text("");
				$("#proportionLongTermLiabilities").val("");
			}
			//检验存量负债成本
			if(costStockLiabilities !=""){
				if(parseFloat(costStockLiabilities) > 100){
					win.errorAlert("存量负债成本(%)不能大于100%");
					$("#proportionLongTermLiabilities").text("");
					$("#proportionLongTermLiabilities").val("");
					costStockLiabilities="";
				}
			}
			//计算存量负债财务成本
			if(costStockLiabilities !="" && totalStockLiabilities !="")
			{
				var val=(parseFloat(costStockLiabilities)*parseFloat(totalStockLiabilities)/100).toFixed(4);
				$("#financialCostStockLiabilities").text(val);
				$("#financialCostStockLiabilities").val(val);
			}
		}
		
		
		$('#reportOverseasLiabilitiesDetail').bind("myEvent", function (event, message1, message2) {
			addEntity(this,message1,message2);	
		});
		
		var arrayList = new Array();	
		$('#sumId').bind('click',function(){
			//var i = 0;
			//var tempStr = $("#checkedCompanyName").val()+$("#year").val()+$("#month").val();
			//arrayList.push('');	
			//for(i;i<arrayList.length;i++){
			//	if(tempStr == arrayList[i]){
			//		alert("请勿重复汇总!");
			//		$('#closeID').trigger("click");
			//	}else{		
					$('#tip').val('汇总前，清空境外负债按币种分类明细！');
					$('#tip').text('汇总前，清空境外负债按币种分类明细！');
					initNodeList('reportOverseasLiabilitiesDetail',1,"del","");//初始化境外负债按币种分类明细表
					var url = "${pageContext.request.contextPath}/reportStockLiabilities/sumData";
					var formData = new FormData($("#form2")[0]);
					$.ajax({  
					     url : url,  
					     type : "POST",  
					     data: formData,
				         async: false,  
				         cache: false,  
				         contentType: false,  
				         processData: false,  
					     success : function(data){
					     	var commitresult=JSON.parse(data);			     	
					     	$.each(commitresult, function(i,item){
					     		if(i == 'bean'){		     			
									$('#domesticStockLiabilities').val(item.domesticStockLiabilities);
									$('#domesticRmbLiabilities').val(item.domesticRmbLiabilities);			     			
									$('#proportionRmbLiabilities').val(item.proportionRmbLiabilities);			     			
									$('#overseasStockLiabilities').val(item.overseasStockLiabilities);			     			
									$('#totalStockLiabilities').val(item.totalStockLiabilities);				     			
									$('#proportionForeignLiabilities').val(item.proportionForeignLiabilities);			     			
									$('#bankLoanStockLiabilities').val(item.bankLoanStockLiabilities);			     			
									$('#entrustStockLiabilities').val(item.entrustStockLiabilities);			     			
									$('#billStockLiabilities').val(item.billStockLiabilities);	
									$('#fundStockLiabilities').val(item.fundStockLiabilities);			     			
									$('#bondStockLiabilities').val(item.bondStockLiabilities);			     			
									$('#financingRentStockLiabilities').val(item.financingRentStockLiabilities);			     			
									$('#othersStockLiabilities').val(item.othersStockLiabilities);	
									$('#costStockLiabilities').val(item.costStockLiabilities);			     			
									$('#longTermLiabilities').val(item.longTermLiabilities);			     			
									$('#proportionLongTermLiabilities').val(item.proportionLongTermLiabilities);			     							     																			     			
					     		}
					     		if(i == 'list'){			     		
					     			$.each(item, function(j,item_j){
										//alert(item_j.overseasCurrencyName);
										//$('#reportOverseasLiabilitiesDetail').trigger("click");
										if(item_j.overseasCurrency != null && item_j.overseasLiabilitiesAmounts!=null)
											$('#reportOverseasLiabilitiesDetail').trigger("myEvent", [item_j.overseasCurrency,item_j.overseasLiabilitiesAmounts]);  
									})
					     		}
		  					 });
					     	},  
					    	error : function(data) {
					     		$.unblockUI();
					     	}  
					   });
			//	}
			//}
			//arrayList.push($("#checkedCompanyName").val()+$("#year").val()+$("#month").val());	
		})
				
	</script>
</html>
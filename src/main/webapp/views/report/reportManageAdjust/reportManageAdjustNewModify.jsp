<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<!-- 修改按钮界面 -->
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改管理口径核算数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增管理口径核算数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
		
	</c:choose>
		<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
		
				<form:hidden path="id"/>
				
				<div class="model_part">
				
				<%--	<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25">
				 <input name="orgname" id="checkedCompanyName" value="${entityview.orgname }" check="NotEmpty_string_._._._._." readonly>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				   </div> --%>
				<%-- 	<select  name="org" id="org" class="selectpicker" onchange="changeOrgname()">
					
						<c:forEach items="${requestScope.allCompanys}" var="result">
							<option value="${result.id.nnodeId }"  <c:if test="${entityview.org eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
					<input type="hidden" id="orgname" name="orgname" value="${entityview.orgname }" >   
				</span>
				--%>
				
				
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
	
				
			    <label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20"><span class="required"> * </span>年份:</label>
				<input class="w25 time" type="text" id="year" name="year" value="${entityview.year }" readonly="true" class="time" check="NotEmpty_double_4_0_+_._." />
				<label class="w20"><span class="required"> * </span>月份:</label>
				<span class="w25">
					<select  name="month" id="month" class="selectpicker" onchange="getBeginningData()">
						<c:forEach var= "temp" begin= "1" step= "1" end= "12">
							<option value="${temp}" <c:if test="${entityview.month == temp}">selected="selected"</c:if>>
								${temp}
							</option>
						</c:forEach>
					</select>
				</span>
				<%-- <label class="w20"><span class="required"> * </span>填报频度:</label>
				<span class="w25">
					<select  name="fre" class="selectpicker" id="fre" onchange="SetSecond()">
							<c:forEach items="${requestScope.fretype}" var="result">
									<option value="${result.id }"  <c:if test="${entityview.fre == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
				</span>
				<label class="w20"><span class="required"> * </span>月份(或周数):</label>
				<span class="w25">
					<select  name="fredata" class="selectpicker" id="fredata" >
							<c:forEach items="${requestScope.freDatatype}" var="result">
									<option value="${result.num }"  <c:if test="${entityview.fredata == result.num}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
				</span> --%>
				<label class="w20"><span class="required"> * </span>总资产 (亿元):</label>
				<form:input class="w25" path="totalAssets" id="totalAssets" check="NotEmpty_double_17_6_0+_。_." onblur="mathData()"/>
				<label class="w20"><span class="required"> * </span>总负债(亿元):</label>
				<form:input class="w25" path="totalLiabilities" id="totalLiabilities" check="NotEmpty_double_17_6_0+_._." onblur="mathData()"/>
				<label class="w20"><span class="required"> * </span>净资产(亿元):</label>
				<form:input class="w25" path="netAssets" id="netAssets" check="NotEmpty_double_17_6_._._." onblur="mathData();changeReturnOnEquityStatus();"/>
				<%-- <label class="w20"><span class="required"> * </span>资本金(亿元):</label>
				<form:input class="w25" path="capital" id="capital" check="NotEmpty_double_17_6_0+_._." onblur="mathData()"/> --%>
				<label class="w20"><span class="required"> * </span>EBITDA(亿元):</label>
				<form:input class="w25" path="ebitda" id="ebitda" check="NotEmpty_double_17_6_0+_._." onblur="mathData()"/> 
				<label class="w20"><span class="required"> * </span>实收资本(亿元):</label>
				<form:input class="w25" path="paidInCapital" id="paidInCapital" check="NotEmpty_double_17_6_0+_._."/>
				<label class="w20"><span class="required"> * </span>收入(亿元):</label>
				<form:input class="w25" path="businessIncome" id="businessIncome" check="NotEmpty_double_17_6_0+_._." onblur="mathData()"/>
				<label class="w20"><span class="required"> * </span>净利润(亿元):</label>
				<form:input class="w25" path="netProfit" id="netProfit" check="NotEmpty_double_17_6_._._." onblur="mathData();changeReturnOnEquityStatus();"/>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
				<label class="w20"><span class="required"> * </span>资产负债率(%):</label>
				<form:input class="w25" path="assetLiabilityRatio" id="assetLiabilityRatio" check="NotEmpty_double_10_2_._._."/>
				 <label class="w20">计算公式:</label>
				<label class="w25 setleft">资产负债率=期末总负债/期末总资产*100%</label> 
				<label class="w20"><span class="required"> * </span>资产周转率(%):</label>
				<form:input class="w25" path="assetTurnover" id="assetTurnover" check="NotEmpty_double_10_2_._._."/>
				 <label class="w20">计算公式:</label>
				<label class="w25 setleft">资产周转率=当期累计营业总收入/[(期初总资产+期末总资产)/2]*100%</label> 
				<!-- <label class="w20"><span class="required"> </span>资本利润率(%):</label>
				<form:input class="w25" path="capitalProfitMargin" id="capitalProfitMargin" check="Empty_double_10_2_._._."/> -->
				<!--  <label class="w20">计算公式:</label>
				<label class="w25 setleft">资本利润率=净利润/平均资本金*100%</label>  -->
				<label class="w20"><span class="required"> * </span>净资产收益率(%):</label>
				<form:input class="w25" path="returnOnEquity" id="returnOnEquity"  check="NotEmpty_double_10_2_._._."/>
				 <label class="w20">计算公式:</label>
				<label class="w25 setleft">净资产收益率=调整后的预算净利润/[(期初净资产+期末净资产)/2]=(净利润+财务费用)/[(期初净资产+期末净资产)/2]*100%</label> 
				
				
				<label class="w20"><span class="required"> * </span>EBITDA利润率(%):</label>
				<form:input class="w25" path="ebitdaMargins" id="ebitdaMargins"  check="NotEmpty_double_10_2_._._."/>
				 <label class="w20">计算公式:</label>
				<label class="w25 setleft">EBITD利润率=调整后的EBITDA/收入=（净利润-投资收益或损失-资产处置损益+所得税+财务费用+折旧摊销）/营业总收入 *100%</label> 
				
				<label class="w20"><span class="required"> * </span>EBITDA利息保障倍数:</label>
				<form:input class="w25" path="interestCover" id="interestCover"  check="NotEmpty_double_10_2_._._."/>
				 <label class="w20">计算公式:</label>
				<label class="w25 setleft"> EBITDA利息保障倍数=调整后的EBITDA/利息支出=（净利润-投资收益或损失-资产处置损益+所得税+财务费用+折旧摊销）/利息支出 </label> 
			
				<label class="w20"><span class="required"> * </span>偿债备付率(%):</label>
				<form:input class="w25" path="repaymentRate" id="repaymentRate"  check="NotEmpty_double_10_2_._._."/>
				 <label class="w20">计算公式:</label>
				<label class="w25 setleft">偿债备付率=（调整后的EBITDA-企业所得税）/当期应还本付息金额=（净利润-投资收益或损失-资产处置损益+财务费用+折旧摊销）/当期应还本付息金额 *100%</label> 
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
<script src="<c:url value="/js/listPageCompanyTreeSelect.js"/>" type="text/javascript"></script>
<script type="text/javascript">
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



		function checkCommit()
		{
			if($("#org").val()=="")
			{
				win.errorAlert("请选择单位");
				return false;
			}
			return true;
		}
		
		function changeOrgname(){
			$("#orgname").val($("#org").find("option:selected").text());
			getBeginningData();
		}
				
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !checkCommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/reportManageAdjust/save";
			
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
			if(!vaild.all() || !checkCommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/reportManageAdjust/saveandreport";
			
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
	
	
		$(' input.time').jeDate(
			{
				format:"YYYY",
				choosefun:function(elem, val, date) {changeDate(val)},
				clearfun:function(elem, val) {changeDate("")},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {changeDate(val)},
			}
		)
		function changeDate(val){
			getBeginningData();
		}
		/* $("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#checkedCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
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
					for ( var i = 0; i < array.length; i++) {
						var opt = document.createElement("option");
						opt.value = array[i].num;
						opt.innerText = array[i].description;
						dom.appendChild(opt);
					}
					/* $(dom).selectpicker('refresh') */
				/*}
			});
		} */ 	
		
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
		
		var beginningData = "";
		function getBeginningData(){
			//单位
			var org = $("#org").val();
			//年份
			var year = $("#year").val();
			//月份
			var month = $("#month").val();
			if(org != "" && year != "" && month != ""){
				$.ajax({
					url:'${pageContext.request.contextPath}'+"/reportManageAdjust/_getBeginningData",
					type:'POST',
					async:false,
					data:{
						year:year,
						org:org
					},
					dataType:'json',
					success:function(data){
						if(data != 1){
							beginningData = data;
						}else{
							beginningData = "";
						}
					}
				});
			}else{
				beginningData = "";
			}
			mathData();
		}
		
		function mathData(){
			//总资产 (亿元)
			var totalAssets = $("#totalAssets").val();
			//总负债(亿元)
			var totalLiabilities = $("#totalLiabilities").val();
			//净资产(亿元)
			var netAssets = $("#netAssets").val();
			//资本金(亿元)
			var capital = $("#capital").val();
			//收入(亿元)
			var businessIncome = $("#businessIncome").val();
			//净利润(亿元)
			var netProfit = $("#netProfit").val();
			
			//资产负债率=总负债/总资产*100%
			if(totalAssets != "" && totalLiabilities != ""){
				$("#assetLiabilityRatio").val((parseFloat(totalLiabilities)/parseFloat(totalAssets)*100).toFixed(2));
			}else{
				$("#assetLiabilityRatio").val("");
				$("#assetLiabilityRatio").text("");
			}
			
			//资产周转率=收入/平均总资产*100%
			if(businessIncome != "" && totalAssets != "" && beginningData != ""){
				$("#assetTurnover").val((parseFloat(businessIncome)/((parseFloat(totalAssets)+parseFloat(beginningData.totalAssets))/2)*100).toFixed(2));
			}else{
				$("#assetTurnover").val("");
				$("#assetTurnover").text("");
			}
			
			//资本利润率=净利润/平均资本金*100%
			if(capital != "" && netProfit != "" && beginningData != ""){
				$("#capitalProfitMargin").val((parseFloat(netProfit)/((parseFloat(capital)+parseFloat(beginningData.capital))/2)*100).toFixed(2));
			}else{
				$("#capitalProfitMargin").val("");
				$("#capitalProfitMargin").text("");
			}
			
			//净资产收益率=净利润/平均净资产*100%
			if(netAssets != "" && netProfit != "" && beginningData != ""){
				$("#returnOnEquity").val((parseFloat(netProfit)/((parseFloat(netAssets)+parseFloat(beginningData.netAssets))/2)*100).toFixed(2));
			}else{
				$("#returnOnEquity").val("");
				$("#returnOnEquity").text("");
			}
			
		}
		
		/**
			净资产与净利润为负数时 净资产收益率未disbale状态
		*/
		function changeReturnOnEquityStatus(){
			//净资产(亿元)
			var netAssets = $("#netAssets").val();
			//净利润(亿元)
			var netProfit = $("#netProfit").val();
			
			if(netAssets != "" && netProfit != ""){
				if(parseFloat(netProfit) < 0 && parseFloat(netAssets) < 0){
					$("#returnOnEquity").val("").attr("readonly","true");
				}else{
					$("#returnOnEquity").removeAttr("readonly");
				}
			}else{
				$("#returnOnEquity").removeAttr("readonly");
			}
		}
		
		changeReturnOnEquityStatus();
	</script>
</html>
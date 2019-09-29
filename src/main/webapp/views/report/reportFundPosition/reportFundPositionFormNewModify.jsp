<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>资金头寸新增、编辑页面</title>
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
	.toucun:hover{
		color: #fff;
	    background-color: #4492d4;
	    border-color: #4492d4;
	}
	.toucun{
		background: #fff;
	    color: #a8a8a8;
	    border-color: #ccc !important;
	    border-radius: 10px;
	}
</style>
</head>
<body onload="loadData(null,1)">
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改资金头寸数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增资金头寸数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<div class="model_body">
		<form:form id="form2" modelAttribute="entityview" >
			<form:hidden path="id"/>
				<div class="label_inpt_div">
					<div class="model_part">
						<c:if test="${getCashBtn=='show'}">
							<span class="" style="display:block;text-align:right;height:60px;">
								<input type="button" value="获取近日头寸" class="form_btn toucun" id="getCashBtnId" onclick="getCash()">
							</span>
						</c:if>
						<span class="" style="display:block">
							<label class="w20"><span class="required"> * </span>单位名称:</label>
								 <!-- <i class="clear iconfont icon-guanbi"></i> -->
							<input type="hidden" id="organalId" name="org" value="${entityview.org }" >
							<input  class="w25" name="orgname" id="company" value="${entityview.orgname }" readonly title="${entityview.orgname }" check="NotEmpty" >
							
							<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
								<ul id="com_ztree" class="ztree">
				
								</ul>
						   </div>
						   <label class="w20"><span class="required"> <font color=red>*</font> </span>时间:</label>
						<input  type="text" id="date" name="date" value="${entityview.date}" readonly="readonly" class="w25 time" check="NotEmpty" />
					   	</span>
						<label class="w20"><font color=red>*</font>当日余额（亿元）:</label>
						<form:input class="w25" id="dailyBalance" path="dailyBalance" check="NotEmpty_double_13_4_0+_._."/>
					</div>
				<h3 class="data_title1">境内资金头寸</h3>
					<div class="model_part">
						<form:hidden path="dataFundPositionRmbs.currencyR" value="69"/>
						<label class="w20"><font color=red></font>境内头寸（亿元）:</label>
						<form:input class="w25" id="cash0" path="dataFundPositionRmbs.cashR" readonly="true"/>
						<label class="w20"><font color=red></font>境内可用头寸（亿元）:</label>
						<form:input class="w25" id="availableCash0" path="dataFundPositionRmbs.availableCashR" readonly="true"/>
						<label class="w20"><font color=red></font>境内不可用头寸（亿元）:</label>
						<form:input class="w25" id="navailableCash0" path="dataFundPositionRmbs.navailableCashR" readonly="true"/>
					</div>	
					<div class="model_part">	
						<label class="w20"><font color=red></font>境内财务公司头寸（亿元）:</label>
						<form:input class="w25" id="fcCash0" path="dataFundPositionRmbs.fcCashR" readonly="true"/>
						<label class="w20"><font color=red>*</font>境内财务公司可用头寸（亿元）:</label>
						<form:input class="w25" id="fcAvailableCash0" path="dataFundPositionRmbs.fcAvailableCashR" onChange="count(0)" check="NotEmpty_double_13_4_0+_._."/>
						<label class="w20"><font color=red>*</font>境内财务公司不可用头寸（亿元）:</label>
						<form:input class="w25" id="fcNavailableCash0" path="dataFundPositionRmbs.fcNavailableCashR" onChange="count(0)" check="NotEmpty_double_13_4_0+_._."/>
						
						<label class="w20"><font color=red>*</font>境内银行可用头寸（亿元）:</label>
						<form:input class="w25" id="BAvailableCash0" path="dataFundPositionRmbs.BAvailableCashR" onChange="count(0)" check="NotEmpty_double_13_4_0+_._."/>
						<label class="w20"><font color=red></font>境内银行不可用头寸（亿元）:</label>
						<form:input class="w25" id="BNavailableCash0" path="dataFundPositionRmbs.BNavailableCashR" readonly="true"/>
						<label class="w20"><font color=red>*</font>境内银行一类不可用头寸（亿元）:</label>
						<form:input class="w25" id="BNavailableCashOne0" path="dataFundPositionRmbs.BNavailableCashOneR" onChange="count(0)" check="NotEmpty_double_13_4_0+_._."/>
						<label class="w20"><font color=red>*</font>境内银行二类不可用头寸（亿元）:</label>
						<form:input class="w25" id="BNavailableCashTwo0" path="dataFundPositionRmbs.BNavailableCashTwoR" onChange="count(0)" check="NotEmpty_double_13_4_0+_._."/>
						
					</div>
					<h3 class="data_title1">境外资金头寸（折合美元）
						<a class="model_btn_plus" id="add_btn_one">+</a>
					</h3>
					<div class="model_part">
						<div class="tablebox">
							<table id="czTableId" style="width: 160em;">
								<tr class="table_header">
									<th>序号</th>
									<th>境外头寸币种</th>
									<th>境外头寸（亿美元）</th>
									<th>境外可用头寸（亿美元）</th>
									<th>境外不可用头寸（亿美元）</th>
									<th>境外财务公司头寸（亿美元）</th>
									<th>境外财务公司可用头寸（亿美元）<font color=red></font></th>
									<th>境外财务公司不可用头（亿美元）<font color=red></font></th>
									<th>境外银行可用头寸（亿美元）<font color=red></font></th>
									<th>境外银行不可用头寸（亿美元）</th>
									<th>境外银行一类不可用头寸（亿美元）<font color=red></font></th>
									<th>境外银行二类不可用头寸（亿美元）<font color=red></font></th>
									<th>操作</th>
								</tr>
							</table>
						</div>
						<div class="clearfix"> 
							<ul id="pageczTableId" class="pagination" style="line-height:34px">
									
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
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vailds.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/js/paging.js"/>"></script>
<script type="text/javascript">
		var timeoutId;
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
					 $("#company").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);vaild.vaild($("#company")[0]);
					  $("#company").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});		
		$(".clear").on('click',function(){
			$(this).parent().siblings('input[name="company"]').val('');
			$("organalId").val("");
		})
		
		$(' input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)

		$("#commit_1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var op = '${requestScope.op}';
			var url="${pageContext.request.contextPath}/reportFundPosition/saveOrUpdate";
			if(op=='modify'){url="${pageContext.request.contextPath}/reportFundPosition/update";}
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
			var url = "${pageContext.request.contextPath}/reportFundPosition/saveandreport";
			
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
		
		//计算
		function count(num){
			var fcAvailableCash    = $("#fcAvailableCash"+num).val();		//境内财务公司可用头寸
			var fcNavailableCash   = $("#fcNavailableCash"+num).val();		//境内财务公司不可用头寸
			var BAvailableCash     = $("#BAvailableCash"+num).val();		//境内银行可用头寸
			var BNavailableCashOne = $("#BNavailableCashOne"+num).val();
			var BNavailableCashTwo = $("#BNavailableCashTwo"+num).val();
			//数据计算*****-- 境内财务公司头寸=境内财务公司可用头寸+境内财务公司不可用头寸
			if(fcAvailableCash!=""&&fcNavailableCash!=""){
				$("#fcCash"+num).val((accAdd(fcAvailableCash,fcNavailableCash)).toFixed(4));//parseFloat(fcAvailableCash)+parseFloat(fcNavailableCash)
			};
			
			//境内银行不可用头寸 =境内银行一类不可用头寸+境内银行二类不可用头寸
			if(BNavailableCashOne!=""&&BNavailableCashTwo!=""){
				$("#BNavailableCash"+num).val((accAdd(BNavailableCashOne,BNavailableCashTwo)).toFixed(4));//parseFloat(BNavailableCashOne)+parseFloat(BNavailableCashTwo)
			};
			
			//境内可用头寸 =境内财务公司可用头寸+境内银行可用头寸
			if(fcAvailableCash!=""&&BAvailableCash!=""){
				$("#availableCash"+num).val((accAdd(fcAvailableCash,BAvailableCash)).toFixed(4));//parseFloat(fcAvailableCash)+parseFloat(BAvailableCash)
			};
			
			//境内不可用头寸 =境内财务公司不可用头寸+境内银行不可用头寸
			if(fcNavailableCash!=""&&BNavailableCashOne!=""&&BNavailableCashTwo!=""){
				$("#navailableCash"+num).val((accAdd(accAdd(fcNavailableCash,BNavailableCashOne),BNavailableCashTwo)).toFixed(4));//parseFloat(fcNavailableCash)+parseFloat(BNavailableCashOne)+parseFloat(BNavailableCashTwo)
			};
			
			//境内头寸 =境内可用头寸+境内不可用头寸
			if(fcNavailableCash!=""&&BNavailableCashOne!=""&&BNavailableCashTwo!=""&&fcAvailableCash!=""&&BAvailableCash!=""){
				var sumCash = accAdd(BNavailableCashOne,BNavailableCashTwo).toString();
				sumCash=accAdd(sumCash,BAvailableCash);
				sumCash=accAdd(sumCash,accAdd(fcNavailableCash,fcAvailableCash).toString());
				$("#cash"+num).val((sumCash).toFixed(4));//parseFloat(fcNavailableCash)+parseFloat(BNavailableCashOne)+parseFloat(BNavailableCashTwo)+parseFloat(fcAvailableCash)+parseFloat(BAvailableCash)
			};
		}  
		
		
		//计算
		function count1(num){
			var fcAvailableCash    = $("#fcAvailableCash"+num).val();		//境内财务公司可用头寸
			var fcNavailableCash   = $("#fcNavailableCash"+num).val();		//境内财务公司不可用头寸
			var BAvailableCash     = $("#BAvailableCash"+num).val();		//境内银行可用头寸
			var BNavailableCashOne = $("#BNavailableCashOne"+num).val();
			var BNavailableCashTwo = $("#BNavailableCashTwo"+num).val();
			//数据计算*****-- 境内财务公司头寸=境内财务公司可用头寸+境内财务公司不可用头寸
			if($("#fcCash"+num).val() == '')
				$("#fcCash"+num).val((accAdd(fcAvailableCash,fcNavailableCash)).toFixed(4));//parseFloat(fcAvailableCash)+parseFloat(fcNavailableCash)
			
			
			//境内银行不可用头寸 =境内银行一类不可用头寸+境内银行二类不可用头寸
			if($("#BNavailableCash"+num).val() == '')
				$("#BNavailableCash"+num).val((accAdd(BNavailableCashOne,BNavailableCashTwo)).toFixed(4));//parseFloat(BNavailableCashOne)+parseFloat(BNavailableCashTwo)
		
			
			//境内可用头寸 =境内财务公司可用头寸+境内银行可用头寸
			
				$("#availableCash"+num).val((accAdd(fcAvailableCash,BAvailableCash)).toFixed(4));//parseFloat(fcAvailableCash)+parseFloat(BAvailableCash)
		
			
			//境内不可用头寸 =境内财务公司不可用头寸+境内银行不可用头寸
			
				$("#navailableCash"+num).val((accAdd($("#BNavailableCash"+num).val(),fcNavailableCash)).toFixed(4));//parseFloat(fcNavailableCash)+parseFloat(BNavailableCashOne)+parseFloat(BNavailableCashTwo)
		
			
			//境内头寸 =境内可用头寸+境内不可用头寸
			
				
				var sumCash=accAdd($("#navailableCash"+num).val(),$("#availableCash"+num).val());
				$("#cash"+num).val((sumCash).toFixed(4));//parseFloat(fcNavailableCash)+parseFloat(BNavailableCashOne)+parseFloat(BNavailableCashTwo)+parseFloat(fcAvailableCash)+parseFloat(BAvailableCash)
			
		}    
		//加
		function accAdd(arg1,arg2){  
			var r1,r2,m;  
			try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
			try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
			m=Math.pow(10,Math.max(r1,r2))  
			if(arg1 == '' || arg1 == undefined){
				arg1 = 0;
			}
			if(arg2 == '' || arg2 == undefined){
				arg2 = 0;
			}
			return (arg1*m+arg2*m)/m
		}
		//新增tr
		$('#add_btn_one').on('click',function(){
			var trNum = $("#czTableId tr:not(.table_header):not(#czTableIdNoDataTrId)").length+1;
			addTableHtml(trNum,null);
			if(trNum<5){
				pagequery(1,"czTableId");
			}else{
				if(trNum%5==0){
					pagequery(parseInt(trNum/5),"czTableId");
				}else{
					pagequery(parseInt(trNum/5)+1,"czTableId");
				}
			}
		});
		//分页
		pagequery(1);
		
		//添加tableHtml
		function addTableHtml(trNum,data,flag){
			var tr="";
		    if(data!=null){
				tr+=loadCurrency(trNum,tr,data.currencyO);//操作单位//境外头寸币种
				tr+="<td><input type='text' style='width:100%' id='cash"+trNum+"' 			    name='dataFundPositionOthersTemp["+(trNum-1)+"].cashO' 			  		onChange='count1("+trNum+")' value='"+f4(data.cashO)+"' 			readonly='readonly'></td>";	//境外头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='availableCash"+trNum+"' 	    name='dataFundPositionOthersTemp["+(trNum-1)+"].availableCashO' 	    onChange='count1("+trNum+")' value='"+f4(data.availableCashO)+"'    readonly='readonly'></td>";	//境外可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='navailableCash"+trNum+"' 	name='dataFundPositionOthersTemp["+(trNum-1)+"].navailableCashO' 	    onChange='count1("+trNum+")' value='"+f4(data.navailableCashO)+"'   readonly='readonly'></td>";	//境外不可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='fcCash"+trNum+"' 			name='dataFundPositionOthersTemp["+(trNum-1)+"].fcCashO' 			    onChange='count1("+trNum+")' value='"+f4(data.fcCashO)+"' 	check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";	//境外财务公司头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='fcAvailableCash"+trNum+"'	name='dataFundPositionOthersTemp["+(trNum-1)+"].fcAvailableCashO'  		onChange='count1("+trNum+")' value='"+f4(data.fcAvailableCashO)+"' 	 check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";					//境外财务公司可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='fcNavailableCash"+trNum+"'   name='dataFundPositionOthersTemp["+(trNum-1)+"].fcNavailableCashO'   	onChange='count1("+trNum+")' value='"+f4(data.fcNavailableCashO)+"'  check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";						//境外财务公司不可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='BAvailableCash"+trNum+"' 	name='dataFundPositionOthersTemp["+(trNum-1)+"].BAvailableCashO' 	    onChange='count1("+trNum+")' value='"+f4(flag==1?data.bAvailableCashO:data.bavailableCashO)+"' 	 check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";					//境外银行可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='BNavailableCash"+trNum+"'    name='dataFundPositionOthersTemp["+(trNum-1)+"].BNavailableCashO'   	onChange='count1("+trNum+")' value='"+f4(flag==1?data.bNavailableCashO:data.bnavailableCashO)+"'  check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";	//境外银行不可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='BNavailableCashOne"+trNum+"' name='dataFundPositionOthersTemp["+(trNum-1)+"].BNavailableCashOneO' 	onChange='count1("+trNum+")' value='"+f4(flag==1?data.bNavailableCashOneO:data.bnavailableCashOneO)+"'  check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";					//境外银行一类不可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='BNavailableCashTwo"+trNum+"' name='dataFundPositionOthersTemp["+(trNum-1)+"].BNavailableCashTwoO' 	onChange='count1("+trNum+")' value='"+f4(flag==1?data.bNavailableCashTwoO:data.bnavailableCashTwoO)+"'  check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";					//境外银行二类不可用头寸（亿元）
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this)'></td></tr>";
				$("#czTableId").append(tr);
			}else{
				tr+=loadCurrency(trNum,tr,null);//操作单位//境外头寸币种
				tr+="<td><input type='text' style='width:100%' id='cash"+trNum+"' 			    name='dataFundPositionOthersTemp["+(trNum-1)+"].cashO' 			  	  onChange='count1("+trNum+")' readonly='readonly'></td>";	//境外头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='availableCash"+trNum+"' 	    name='dataFundPositionOthersTemp["+(trNum-1)+"].availableCashO' 	  onChange='count1("+trNum+")' readonly='readonly'></td>";	//境外可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='navailableCash"+trNum+"' 	name='dataFundPositionOthersTemp["+(trNum-1)+"].navailableCashO' 	  onChange='count1("+trNum+")' readonly='readonly'></td>";	//境外不可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='fcCash"+trNum+"' 			name='dataFundPositionOthersTemp["+(trNum-1)+"].fcCashO' 			  onChange='count1("+trNum+")' check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'>></td>";	//境外财务公司头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='fcAvailableCash"+trNum+"'	name='dataFundPositionOthersTemp["+(trNum-1)+"].fcAvailableCashO' 	  onChange='count1("+trNum+")' check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";						//境外财务公司可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='fcNavailableCash"+trNum+"'   name='dataFundPositionOthersTemp["+(trNum-1)+"].fcNavailableCashO'    onChange='count1("+trNum+")' check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";						//境外财务公司不可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='BAvailableCash"+trNum+"' 	name='dataFundPositionOthersTemp["+(trNum-1)+"].BAvailableCashO' 	  onChange='count1("+trNum+")' check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";						//境外银行可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='BNavailableCash"+trNum+"'    name='dataFundPositionOthersTemp["+(trNum-1)+"].BNavailableCashO' 	  onChange='count1("+trNum+")' check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";	//境外银行不可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='BNavailableCashOne"+trNum+"' name='dataFundPositionOthersTemp["+(trNum-1)+"].BNavailableCashOneO'  onChange='count1("+trNum+")' check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";						//境外银行一类不可用头寸（亿元）
				tr+="<td><input type='text' style='width:100%' id='BNavailableCashTwo"+trNum+"' name='dataFundPositionOthersTemp["+(trNum-1)+"].BNavailableCashTwoO'  onChange='count1("+trNum+")' check='Empty_double_13_4_0+_._.' placeholder='13个字符以内的数字,最多包含4位小数'></td>";						//境外银行二类不可用头寸（亿元）
				tr+="<td><input type='button' class='btn btn-primary model_btn_ok' value='删除' onclick='del(this)'></td></tr>";
				$("#czTableId").append(tr);
			}
		}
		//删除某一行
		function del(tr){
			$(tr).parent().parent().remove();
			pagequery(1,"czTableId");
			loadNum();
		}
		
		//序号，币种
		function loadCurrency(trNum,tr,currency){
			var currencyKind = eval("(" + '${currencyKind}' + ")");
			var currencyKindSize = currencyKind.length;
			tr+="<tr><td class='trNum'>"+trNum+"</td><td><select name='dataFundPositionOthersTemp["+(trNum-1)+"].currencyO'>";
			for(var i=0;i<currencyKindSize;i++){
				if(currencyKind[i].id=='69'){
					continue;
				}else if(currencyKind[i].id==currency){
					tr+="<option selected='selected' value='"+currencyKind[i].id+"'>"+currencyKind[i].description+"</option>";
				}else{
					tr+="<option value='"+currencyKind[i].id+"'>"+currencyKind[i].description+"</option>";
				}
			}
			tr+="</select></td>";
			return tr;
		}
		
		//删除序号生成
		function loadNum(){
			var trNum = $("#czTableId tr").length-1;
			for(var i=0;i<trNum;i++){
				$(".trNum:eq("+i+")").html(i+1);
			}
		}
		//加载数据
		//loadData();
		function loadData(entity,flag){
			//清空数据
			$("#czTableId tr:not(.table_header)").remove();
			var noDataHtml="<tr id='czTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
			$("#czTableId").append(noDataHtml);
			//加载数据
			if(entity==null){
				entity = eval("(" + '${entity}' + ")");
			}
			if(entity==undefined||entity==null){
				return;
			}
			var data = {};
			
			if(flag==2){
				$("#date").val(entity.date);
		 		$("#dailyBalance").val(entity.dailyBalance);
		 		$("#cash0").val(entity.dataFundPositionRmbs.cashR);
		 		$("#availableCash0").val(entity.dataFundPositionRmbs.availableCashR);
		 		$("#navailableCash0").val(entity.dataFundPositionRmbs.navailableCashR);
		 		$("#fcCash0").val(entity.dataFundPositionRmbs.fcCashR);
		 		$("#fcAvailableCash0").val(entity.dataFundPositionRmbs.fcAvailableCashR);
		 		$("#fcNavailableCash0").val(entity.dataFundPositionRmbs.fcNavailableCashR);
		 		$("#BAvailableCash0").val(entity.dataFundPositionRmbs.bavailableCashR);
		 		$("#BNavailableCash0").val(entity.dataFundPositionRmbs.bnavailableCashR);
		 		$("#BNavailableCashOne0").val(entity.dataFundPositionRmbs.bnavailableCashOneR);
		 		$("#BNavailableCashTwo0").val(entity.dataFundPositionRmbs.bnavailableCashTwoR);
			}
			if(entity.dataFundPositionOthers!==undefined){
				if(entity.dataFundPositionOthers.length<1){
					return;
				}
				 for(var i = 0;i<entity.dataFundPositionOthers.length;i++){
					addTableHtml(i+1,entity.dataFundPositionOthers[i],flag);
				} 	
			}
			pagequery(1,"czTableId");
		};
		
		//获取头寸
		function getCash(){
			var org = $("#organalId").val();
			if(org==""||org==null){
				win.errorAlert("请选择单位！");
				return;
			}
			var url = "${pageContext.request.contextPath}/reportFundPosition/getCash";
			$.ajax({  
			     url : url,  
			     type : "POST", 
			     data: {"org":org},
		         contentType:"application/x-www-form-urlencoded", 
			     success : function(data) {
			     	if(data==null||data==""){
			     		win.errorAlert("该单位未查询到最近头寸数据！");
			     		//清空数据
						$("#czTableId tr:not(.table_header)").remove();
						var noDataHtml="<tr id='czTableIdNoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
						$("#czTableId").append(noDataHtml);
						$("#date").val("");
				 		$("#dailyBalance").val("");
				 		$("#cash0").val("");
				 		$("#availableCash0").val("");
				 		$("#navailableCash0").val("");
				 		$("#fcCash0").val("");
				 		$("#fcAvailableCash0").val("");
				 		$("#fcNavailableCash0").val("");
				 		$("#BAvailableCash0").val("");
				 		$("#BNavailableCash0").val("");
				 		$("#BNavailableCashOne0").val("");
				 		$("#BNavailableCashTwo0").val("");
				 		pagequery(1,"czTableId");
			     		return;
			     	}
				     loadData(data,2);
			     },  
			     error : function(data) {
			     	win.errorAlert("该单位未查询到最近头寸数据！");
			     }  
			}); 
		}
	</script>
</html>
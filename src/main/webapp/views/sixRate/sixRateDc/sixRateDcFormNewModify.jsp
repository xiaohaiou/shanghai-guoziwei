<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>信访数据新增、编辑页面</title>
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
	.w20{
		text-align: right;
    	padding: 0 20px;
	}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改信访数据采集
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增信访数据采集
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<div class="model_body">
		<form:form id="form2" modelAttribute="entityview" >
		
				<form:hidden path="id"/>
				<div class="label_inpt_div">
					<div class="model_part">
						<label class="w20"><span class="required"> <font color=red>*</font> </span>年份:</label>
						<input  type="text" name="year" value="${entityview.year}" readonly="true" class="w25 time" check="NotEmpty" />
						<label class="w20"><span class="required"> <font color=red>*</font> </span>月份:</label>
						<span class="w25">
							<select  name="month" class="selectpicker" >
								<option value="1" <c:if test="${'1' eq entityview.month}">selected</c:if>>1</option>
								<option value="2" <c:if test="${'2' eq entityview.month}">selected</c:if>>2</option>
								<option value="3" <c:if test="${'3' eq entityview.month}">selected</c:if>>3</option>
								<option value="4" <c:if test="${'4' eq entityview.month}">selected</c:if>>4</option>
								<option value="5" <c:if test="${'5' eq entityview.month}">selected</c:if>>5</option>
								<option value="6" <c:if test="${'6' eq entityview.month}">selected</c:if>>6</option>
								<option value="7" <c:if test="${'7' eq entityview.month}">selected</c:if>>7</option>
								<option value="8" <c:if test="${'8' eq entityview.month}">selected</c:if>>8</option>
								<option value="9" <c:if test="${'9' eq entityview.month}">selected</c:if>>9</option>
								<option value="10" <c:if test="${'10' eq entityview.month}">selected</c:if>>10</option>
								<option value="11" <c:if test="${'11' eq entityview.month}">selected</c:if>>11</option>
								<option value="12" <c:if test="${'12' eq entityview.month}">selected</c:if>>12</option>
							</select>
						</span>
						<span class="" style="display:block">
							<span class="search_title w20">单位名称:</span>
							<label class="w20 setleft">海航地产</label>
							<input type="hidden" id="organalId" name="organalId" value="850766" >
							<input type="hidden" id="company" name="company" value="海航地产" >
							<%-- <input name="company" id="company" value="${entityview.company }" readonly title="${entityview.company }">
							
							<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
								<ul id="com_ztree" class="ztree">
				
								</ul>
						   </div> --%>
					   </span>
					</div>
				<h3 class="data_title1">地产市场占有率</h3>
						<div class="model_part">
							<label class="w20"><font color=red>*</font>信访数据采集1:</label>
							<form:input class="w25" id="marketingAmountHh" path="marketingAmountHh" check="NotEmpty_double_13_4_0+_._."/>
							<label class="w20"><font color=red>*</font>信访数据采集2:</label>
							<form:input class="w25" id="marketingAmountNationwide" path="marketingAmountNationwide" check="NotEmpty_double_13_4_0+_._."/>
							<label class="w20"><font color=red>*</font>信访数据采集3:</label>
							<form:input class="w25" id="marketShareDc" path="marketShareDc" readonly="true"/>
							<label class="w20"><font color=red>*</font>信访数据采集4:</label>
							<form:input class="w25" id="marketShareYears" path="marketShareYears" check="NotEmpty_double_7_4_0+_._."/>
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
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		//计算酷铺市场占有率= 海航地产销售额/全国房地产市场销售额*100%
		$("#marketingAmountHh,#marketingAmountNationwide").on("change",function(){
			var marketingAmountHh = $("#marketingAmountHh").val();
			var marketingAmountNationwide = $("#marketingAmountNationwide").val();
			if(parseFloat(marketingAmountHh)>parseFloat(marketingAmountNationwide)){
				win.errorAlert("全国房地产市场销售额应多大于海航地产销售额！",function(){});//window.location.reload(true);
			}else{
				if(marketingAmountHh!=""&&marketingAmountNationwide!=""){
				$("#marketShareDc").val((accDiv(marketingAmountHh,marketingAmountNationwide)*100).toFixed(4));//(parseFloat(marketingAmountHh)/parseFloat(marketingAmountNationwide)
			}
			}
		});
		//除
		function accDiv(arg1,arg2){
			 var t1=0,t2=0,r1,r2;  
			 try{t1=arg1.toString().split(".")[1].length}catch(e){}  
			 try{t2=arg2.toString().split(".")[1].length}catch(e){}  
			 with(Math){
				 r1=Number(arg1.toString().replace(".",""))
				 r2=Number(arg2.toString().replace(".",""))
				 return accMul((r1/r2),pow(10,t2-t1));  
			}
		}
		//乘
		function accMul(arg1,arg2){  
			var m=0,s1=arg1.toString(),s2=arg2.toString();  
			try{m+=s1.split(".")[1].length}catch(e){}  
			try{m+=s2.split(".")[1].length}catch(e){}  
			return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)  
		}
		
		$("#marketShareYears").on("change",function(){
			var targetValueYears = $("#marketShareYears").val();
			if(parseFloat(targetValueYears)>100){
				$("#marketShareYears").val("");
				//alert("");
				win.errorAlert("海航地产市场占有率年度目标应小于100%！",function(){});
			}
		});
		
		
		function checkcommit() {
			return true;
		};	
		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		);

		$("#commit_1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var op = '${requestScope.op}';
			var url="${pageContext.request.contextPath}/sixRateDc/saveOrUpdate";
			if(op=='modify'){url="${pageContext.request.contextPath}/sixRateDc/update";}
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
		$("#commit_2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/sixRateDc/saveandreport";
			
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
			$(this).closest("tr").remove();
		});
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
	</script>
</html>
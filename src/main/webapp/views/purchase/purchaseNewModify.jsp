<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>流程处理新增、编辑页面</title>
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
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改流程处理信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增流程处理信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
		
	</c:choose>
			
		<form:form id="form2" modelAttribute="entityview" >
		
			<form:hidden path="id"/>
			
			<div class="label_inpt_div">
				<div class="model_part">
					<label class="w20"><span class="required"> * </span>年份:</label>
					<input class="w25 time" type="text" id="year" name="year" value="${entityview.year }" readonly="true" class="time" check="NotEmpty_int_4_0_+_._." />
					<label class="w20"><span class="required"> * </span>季度:</label>
					<span class="w25">
						<select  name="season" class="selectpicker"  >
							<c:forEach items="${requestScope.seasontype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.season == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
						</select>
					</span>
				</div>
				<h3 class="data_title1">处理前事件处理状态</h3>
				<div class="model_part">
					<label class="w20"><span class="required"> * </span>事件名称:</label>
					<form:input class="w25" path="constructioClass" check="NotEmpty_int_5_0_0+_._." />
					<label class="w20"><span class="required"> * </span>事件类型:</label>
					<form:input class="w25" path="designClass" check="NotEmpty_int_5_0_0+_._." />
					<label class="w20"><span class="required"> * </span>事件处理状态:</label>
					<form:input class="w25" path="supervisionClass" check="NotEmpty_int_5_0_0+_._."/>
					<label class="w20"><span class="required"> * </span>事件处理方式:</label>
					<form:input class="w25" path="consultingCostClass" check="NotEmpty_int_5_0_0+_._."/>
					<label class="w20"><span class="required"> * </span>当前流程:</label>
					<form:input class="w25" path="materialEquipmentClass" check="NotEmpty_int_5_0_0+_._."/>
				</div>
				<h3 class="data_title1">处理后事件处理状态</h3>
				<div class="model_part">
					<label class="w20"><span class="required"> * </span>事件处理状态:</label>
					<form:input class="w25" path="engineeringClass" check="NotEmpty_double_17_4_0+_._."/>
					<label class="w20"><span class="required"> * </span>事件处理方式:</label>
					<form:input class="w25" path="materialCategoryClass" check="NotEmpty_double_17_4_0+_._."/>
					<label class="w20"><span class="required"> * </span>事件处理结果:</label>
					<form:input class="w25" path="channelClass" check="NotEmpty_double_17_4_0+_._."/>
					<label class="w20"><span class="required"> * </span>当前流程:</label>
					<form:input class="w25" path="serviceClass" check="NotEmpty_double_17_4_0+_._."/>
				</div>
				<h3 class="data_title1">事件处理状态改变</h3>
				<div class="model_part">
					<label class="w20"><span class="required"> * </span>处理前:</label>
					<form:input class="w25" path="incursTotalAmount" id="incursTotalAmount" check="NotEmpty_double_17_4_0+_._."/>
					<label class="w20"><span class="required"> * </span>处理后:</label>
					<form:input class="w25" path="savingsTotalAmount" id="savingsTotalAmount" check="NotEmpty_double_17_4_0+_._."/>
					<label class="w20">状态变化:</label>
					<form:input class="w25" path="purchaseSaveRate" id="purchaseSaveRate" readonly="true" check="NotEmpty_._._._._._."></form:input>
					<form:hidden path="status" id="status"></form:hidden>
				</div>
				<c:if test="${ !empty entityExamineviewlist  }">
				<h3 class="data_title1">审核意见</h3>
				<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
				<div class="model_part">
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
				</div>
			
				</c:forEach>
				</c:if>
			
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2">保存并上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
			</div>
	</form:form>

</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>

<script type="text/javascript">
			
		$("#savingsTotalAmount").change(function(){
		
			calrate();
		
		})	
		
		$("#incursTotalAmount").change(function(){
		
			calrate();
			
		})	
		
		function calrate()
		{
		debugger
			var incursTotalAmount=$("#incursTotalAmount").val();
				var savingsTotalAmount=$("#savingsTotalAmount").val();
				if(incursTotalAmount !="" && savingsTotalAmount !="" )
				{
				
							var rate=((parseFloat(savingsTotalAmount)/parseFloat(parseFloat(incursTotalAmount)+parseFloat(savingsTotalAmount)))*100).toFixed(2);
							if(!isNaN(rate))
							{
								$("#purchaseSaveRate").text(rate);
								$("#purchaseSaveRate").val(rate);
							}
							else
							{
								var a=0;
								$("#purchaseSaveRate").text(a.toFixed(2));
								$("#purchaseSaveRate").val(a.toFixed(2));
								//win.errorAlert("计算工程采购节约率失败，输入的工程招采总金额或工程采购节约金额不符合条件");
							}
			
				}
		}
			
		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		)

		function checkcommit()
		{
		    if($("#year").val()=="")
		    {
		   		 win.errorAlert("年份不能为空");
		   		 return false
		    }
		    if($("#incursTotalAmount").val()-$("#savingsTotalAmount").val()<0 )
		    {
		     	 win.errorAlert("不能改变状态");
		   		 return false
		    }
			return true;
		}
				
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !checkcommit() )
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/purchase/save";
			
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
			if(!vaild.all() || !checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/purchase/saveandreport";
			
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
							//
						}
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
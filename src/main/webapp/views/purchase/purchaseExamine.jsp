<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>流程处理审核页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />

<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>

			<h4>
				<span class="glyphicon glyphicon-pencil"></span>审核流程处理信息
				<i class="iconfont icon-guanbi"></i>
			</h4>

	
		<form:form id="form2" modelAttribute="entityview" >
			<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ entityview.year}</label> 
				<label class="w20">季度:</label>
				<label class="w25 setleft">${ entityview.seasonName}</label> 
			</div>
			
			<h3 class="data_title1">处理前事件处理状态</h3>
			
			<div class="model_part">
				<label class="w20">事件名称:</label>
				<label class="w25 setleft">${ entityview.constructioClass}</label> 
				<label class="w20">事件类型:</label>
				<label class="w25 setleft">${ entityview.designClass}</label> 
				<label class="w20">事件处理状态:</label>
				<label class="w25 setleft">${ entityview.supervisionClass}</label> 
				<label class="w20">事件处理方式:</label>
				<label class="w25 setleft">${ entityview.consultingCostClass}</label> 
				<label class="w20">当前流程:</label>
				<label class="w25 setleft">${ entityview.materialEquipmentClass}</label> 
			
			</div>
			<h3 class="data_title1">处理后事件处理状态</h3>
			
			<div class="model_part">
				<label class="w20">事件处理状态:</label>
				<label class="w25 setleft">${ entityview.engineeringClass}</label> 
				<label class="w20">事件处理方式:</label>
				<label class="w25 setleft">${ entityview.materialCategoryClass}</label> 
				<label class="w20">事件处理结果:</label>
				<label class="w25 setleft">${ entityview.channelClass}</label> 
				<label class="w20">当前流程:</label>
				<label class="w25 setleft">${ entityview.serviceClass}</label> 
			
			</div>
			<h3 class="data_title1">事件处理状态改变</h3>
			
			<div class="model_part">
				<label class="w20">处理前:</label>
				<label class="w25 setleft">${ entityview.incursTotalAmount}</label> 
				<label class="w20">处理后:</label>
				<label class="w25 setleft">${ entityview.savingsTotalAmount}</label> 
				<label class="w20">状态变化:</label>
				<label class="w25 setleft">${ entityview.purchaseSaveRate}</label> 
				
				<input type="hidden" value="${entityview.id }" name="entityid" id="entityid">
			
			</div>
			<%-- <c:choose>
					<c:when test="${op eq 'report'}">
						
					</c:when>
					<c:otherwise> --%>
						<h3 class="data_title1">创建上报信息</h3>
							
							<div class="model_part">
								<label class="w20">数据创建人:</label>
								<label class="w25 setleft">${ entityview.createPersonName}</label>
								<label class="w20">创建时间:</label>
								<label class="w25 setleft">${ entityview.createDate}</label>
								<label class="w20">数据上报人:</label>
								<label class="w25 setleft">${ entityview.reportPersonName}</label>
								<label class="w20">上报时间:</label>
								<label class="w25 setleft">${ entityview.reportDate}</label>
							
							</div>
				<%-- 	</c:otherwise>
			</c:choose> --%>
			
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
			
			
		
			<div class="model_part">
				<label class="w20" style="vertical-align: top;"><span class="required"> * </span>审核意见:</label>
				<span class="w70 "><textarea  rows="5" cols="50" name="examStr" id="examStr"></textarea></span>
				
			</div>
			
			
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">通过</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2">退回</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
				
			</div>
			</div>
	</form:form>
	
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">
		
		function check()
		{
			if($("#examStr").val().length>=600)
			{
				win.errorAlert("审核意见不能输入超过600字");
				return false;
			}
			if($("#examStr").val()=="")
			{
				win.errorAlert("请输入审核意见");
				return false;
			}
			return true;
		}
		
		
		
		$("#commit-1").click(function(){
			if(check())
			{
				$.post("/shanghai-gzw/purchase/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('保存成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
						{
							win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
							
						}
		 		 });
		 	}
			return false;
		})
		$("#commit-2").click(function(){
			if(check())
			{
				$.post("/shanghai-gzw/purchase/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
		    		var commitresult=JSON.parse(data);
							if(commitresult.result==true)
								win.successAlert('退回成功！',function(){
										parent.location.reload(true);
										parent.mclose();
								});
							else
							{
								win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
								
							}
		 		 });
		 	}
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
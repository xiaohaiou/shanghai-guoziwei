<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>个人借款数据</title>
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
</head>
<body>

			<h4>
			<c:if test="${entityview.type ==0}">
				<span class="glyphicon glyphicon-pencil"></span>审核应收债权(内部)台账信息
			</c:if>	
			<c:if test="${entityview.type ==1}">
				<span class="glyphicon glyphicon-pencil"></span>审核应收债权(外部)台账信息
			</c:if>	
				<i class="iconfont icon-guanbi"></i>
			</h4>


		<form:form id="form2" modelAttribute="entityview" >
			<form:hidden path="id"/>
				
				<div class="label_inpt_div">
				<c:if test="${entityview.type ==0}">
					<h3 class="data_title1">应收债权(内部)台账基本信息</h3>
				</c:if>	
				<c:if test="${entityview.type ==1}">
					<h3 class="data_title1">应收债权(外部)台账基本信息</h3>
				</c:if>	
					<div class="model_part">
					<label class="w20">所属单位:</label>
					<label class="w25 setleft">${entityview.debtorgname}</label> 
					<label class="w20"></label>
					<label class="w25 setleft"></label> 
					
					<label class="w20">年份:</label>
					<label class="w25 setleft">${ entityview.year}</label> 
					<label class="w20">月份:</label>
					<label class="w25 setleft">${ entityview.month}</label> 
				</div>
				
				<c:if test="${entityview.type ==0}">
					<h3 class="data_title1">应收债权(内部)信息(单位：元)</h3>
				</c:if>	
				<c:if test="${entityview.type ==1}">
					<h3 class="data_title1">应收债权(外部)信息(单位：元)</h3>
				</c:if>	
				
				<div class="model_part" id="nodeList">
				
				</div>
				
				<input type="hidden" value="${entityview.id }" name="entityid" id="entityid">
				
			
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
		function initNodeList(v,pageNums){
			$.ajax({
				url:"${pageContext.request.contextPath}/receivabledebt/_itemViewList?pageNums="+pageNums+"&id=${entityview.id}",
				type:"POST",
				dataType:"text",
				async:false,
				success:function(data){
					$("#nodeList").children().remove();
					$("#nodeList").append(data);
				}
			});	
		}
		initNodeList("",1);//初始化节点列表
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
				$.post("/shanghai-gzw/receivabledebt/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
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
				$.post("/shanghai-gzw/receivabledebt/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
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
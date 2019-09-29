<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>外部对标关系</title>
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
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>外部对标关系维护
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<form:hidden path="id"/>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>内部公司名称:</label>
				<form:input class="w25" path="financeorgname" id="financeorgname" check="NotEmpty_double_16_4_0+_。_." />
				<label class="w20"><span class="required"> * </span>内部公司代码:</label>
				<form:input class="w25" path="financecode" id="financecode" check="NotEmpty_double_16_4_0+_。_." />
				<label class="w20"><span class="required"> * </span>对标公司名称:</label>
				<form:input class="w25" path="orgname" id="orgname" check="NotEmpty" />
				<label class="w20"><span class="required"> * </span>对标公司代码:</label>
				<form:input class="w25" path="orgcode" id="orgcode" check="NotEmpty"/> 
				<label class="w20"><span class="required"> * </span>对标公司类型:</label>
						<form:select path="orgIsHongkong" class="w25" >
							<form:option value="1">境外上市</form:option>
							<form:option value="0">境内上市</form:option>
						</form:select>
				<br>
			  </div>

			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
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
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>

<script type="text/javascript">



       function commitcheck()
		{
			if($("#financeorgname").val()=="")
			{
				win.errorAlert("内部公司名不能为空");
				return false;
			}
			if($("#financeorg").val()=="")
			{
				win.errorAlert("内部公司代码不能为空");
				return false;
			}
			if($("#orgname").val()=="")
			{
				win.errorAlert("对标公司名不能为空");
				return false;
			}
			if($("#org").val()=="")
			{
				win.errorAlert("对标公司代码不能为空");
				return false;
			}
			if($("#orgIsHongkong").val()=="")
			{
				win.errorAlert("对标公司类型不能为空");
				return false;
			}
			return true;
		}
	
		$("#commit-1").click(function(){

			$.blockUI({ message: '提交中', css: { width: '275px' } });
	
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/reportOutBenchmark/save";
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
		
	
		

	</script>
</html>
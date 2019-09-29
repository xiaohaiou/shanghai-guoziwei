<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>调查反映风险事项提示</title>
	<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
	<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
	<!-- 库|插件 -->
	<!-- 新加样式 -->
	<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body style="height:500px;">
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改调查反映风险事项提示
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增调查反映风险事项提示
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	
	<form:form id="form2" modelAttribute="entity" method="post" >
	<form:hidden path="id"/>
		<div class="label_inpt_div">
			<h3 class="data_title1">基本信息</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>调查反映风险事项描述:</label>
				<textarea class="w70" rows="5" cols="" id="promptDescribe" name="promptDescribe" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${entity.promptDescribe}</textarea>
				<div>
				<div >
				<label class="w20"><span class="required"> * </span>归属一级风险类目:</label>
				<span class="w25">
					<select name="risk1" id="risk1" class="selectpicker">
						
					</select> 
				</span>
				<br>
				<label class="w20"><span class="required"> * </span>归属二级风险类目:</label>
				<span class="w25">
					<select name="risk2" id="risk2" class="selectpicker">
						
					</select> 
				</span>
				<br>
				<label class="w20"><span class="required"> * </span>归属三级风险类目:</label>
				<span class="w25">
					<select name="risk3" id="risk3" class="selectpicker">
						
					</select> 
				</span>
				
				</div>
				<div style="float: right;margin-top: -9%;margin-right: 7%;width: 40%;overflow-y:auto;height:100px;">
				
				<div id="define"></div>
				</div>
				</div>
			</div>
			
			<div class="model_btn">
				<input type="hidden" value="${entity.complianceId }" name="complianceId" id="complianceId">
				<input type="hidden" value="${entity.id }" name="promptId" id="promptId">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
				
			</div>
		</div>
		
	</form:form>
	<jsp:include page="/views/system/modal.jsp" />
	<!-- <style>
		[data-remodal-id=modal] iframe {
			width:80%;
			height:500px;
		}
	</style> -->
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>

<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		
				
		function commit1(){
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
		
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/compliance/saveOrUpdatePrompt";
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
					 win.successAlert('保存成功！',function(){
							parent.location.reload(true);
							parent.mclose();
					 });
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
			return false;
		}
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　	parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		$("#commit-1").click(commit1);
		
		
		function bindRisk(parentId,id){
			/* $.get('/bimr/riskCatalog/getChildren', {'parentId': id}, function(data){
				$("#sel_level2Id option").remove();
				$.each(data.entity, function(i, o){
					$("#sel_level2Id").append("<option value='"+o.id+"'>"+o.name+"</option>");
				});
			}); */
			/* onchange='getdefine("+o.define+")' */
			var url = "${pageContext.request.contextPath}/bimr/riskCatalog/getChildren";
			$.ajax({  
			     url : url,  
			     type : "GET",
		         async: false,
		         data:{"parentId":parentId},   
			     success : function(data) {
			     	$(id+" option").remove();
			     	$.each(data.entity, function(i, o){
						$(id).append("<option value='"+o.id+"'>"+o.name+"</option>");
						
					});
			     },  
			     error : function(data) {
			     }  
			}); 
			return false;
		}
		function getdefine(parentId,id){
			
			var url = "${pageContext.request.contextPath}/bimr/riskCatalog/getChildren";
			$.ajax({  
			     url : url,  
			     type : "GET",
		         async: false,
		         data:{"parentId":parentId},   
			     success : function(data) {
			     	$(id+" option").remove();
			     	$.each(data.entity, function(i, o){
						if(o.id==id){
						$("#define").html(o.define);
						return false
						}
						
					});
			     },  
			     error : function(data) {
			     }  
			}); 
			
		}
		$(function(){
			debugger;
			//var v = $('#sel_level1Id').val();
			bindRisk("","#risk1");
			
			var parentId1 = $("#risk1").val();
			bindRisk(parentId1,"#risk2");
			
			var parentId2 = $("#risk2").val();
			bindRisk(parentId2,"#risk3");
			
			$('select[name=risk1]').change(function(){
				debugger;
				var parentId1 = $(this).val();
				bindRisk(parentId1,"#risk2");
				$('select[name=risk2]').change();
			});
			$('select[name=risk2]').change(function(){
				var parentId1 = $("#risk1").val();
				var parentId2 = $(this).val();
				bindRisk(parentId2,"#risk3");
				 getdefine(parentId1,parentId2)
			});
			$('select[name=risk3]').change(function(){
				var parentId1 = $("#risk2").val();
				var parentId2 = $(this).val();
				
				 getdefine(parentId1,parentId2)
			});
		});
		
		
	</script>
</html>
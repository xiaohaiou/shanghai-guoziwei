<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>层级信息新增、编辑页面</title>
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
				<span class="glyphicon glyphicon-pencil"></span>编辑层级信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增层级信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="entityview" >
		<form:hidden path="id"/>
		<form:hidden path="status"/>
		<form:hidden path="createPersonId"/>
		<form:hidden path="createPersonName"/>
		<form:hidden path="createDate"/>
		<form:hidden path="reportPersonId"/>
		<form:hidden path="reportPersonName"/>
		<form:hidden path="reportDate"/>
		<form:hidden path="auditorPersonId"/>
		<form:hidden path="auditorPersonName"/>
		<form:hidden path="auditorDate"/>
		<div class="label_inpt_div">
			<h3 class="data_title1">层级信息</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>公司名称:</label>
				<form:input class="w25" path="name" check="NotEmpty_" />
				<!-- <label class="w20"><span class="required"> * </span>核心品牌类别:</label>
				<select name="type" class="w25 selectpicker">
					<option value="">请选择</option>
					<c:forEach items="${requestScope.brandType}" var="result">
						<option value="${result.id }" <c:if test="${entityview.type == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
				<label class="w20"><span class="required"> * </span>行业地位:</label>
				<form:input class="w25" path="position" check="NotEmpty_"/>
				<label class="w20"><span class="required"> * </span>品牌抽屉类别:</label>
				<form:input class="w25" path="drawerType" check="NotEmpty_String_200_0_0+_._."/>
				-->
				<label class="w20"><span class="required"> * </span>层级名称:</label>
				<select name="level" class="w25 selectpicker">
					<option value="">请选择</option>
					<c:forEach items="${requestScope.brandLevel}" var="result">
						<option value="${result.id }" <c:if test="${entityview.level == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
				<label class="w20"><span class="required"> * </span>层级位阶:</label>
				<form:input class="w25" path="income" check="NotEmpty_"/>
				<label class="w20"><span class="required"> * </span>上级层级名称:</label>
				<form:input class="w25" path="incomeIncrease" check="NotEmpty_"/>
				<label class="w20"><span class="required"> * </span>是否存在子层级:</label>
				<form:input class="w25" path="fund" check="NotEmpty_"/>
				<label class="w20"><span class="required"> * </span>相同层级数量:</label>
				<form:input class="w25" path="coverageArea" check="NotEmpty_"/>
				<label class="w20"><span class="required"> * </span>层级类型:</label>
				<select name="property" class="w25 selectpicker">
					<option value="">请选择</option>
					<c:forEach items="${requestScope.brandProperty}" var="result">
						<option value="${result.id }" <c:if test="${entityview.property == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
			</div>
			<c:if test="${ !empty entityExamineviewlist }">
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
			
		$(' input.time').jeDate({
				format:"YYYY"
			}
		);

		function checkcommit(){
			if($("#name").val()==""){
				win.errorAlert("公司名称不能为空");
				return false;
			}
			if($("#tpye").val()==""){
				win.errorAlert("请选择层级类别");
				return false;
			}
			if($("#level").val()==""){
				win.errorAlert("请选择层级位阶");
				return false;
			}
			if($("#income").val()==""){
				win.errorAlert("层级位阶不能为空");
				return false;
			}
			if($("#incomeIncrease").val()==""){
				win.errorAlert("上级层级名称不能为空");
				return false;
			}
			if($("#fund").val()==""){
				win.errorAlert("是否存在子层级不能为空");
				return false;
			}
			if($("#coverageArea").val()==""){
				win.errorAlert("相同层级数量不能为空");
				return false;
			}
			if($("#position").val()==""){
				win.errorAlert("层级类型不能为空");
				return false;
			}
			if($("#property").val()==""){
				win.errorAlert("请选层级属性");
				return false;
			}
			if($("#drawerType").val()==""){
				win.errorAlert("层级类别不能为空");
				return false;
			}
			return true;
		}
		// 保存
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit()){
				$.unblockUI();
				return false;
			}
			var url = "${pageContext.request.contextPath}/social/brand/save";
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
					} else {
						win.errorAlert(commitresult.exceptionStr);
					}
				},
				error : function(data) {
					$.unblockUI();
				}
			});
			return false;
		});
		// 保存并上报
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit()){
				$.unblockUI();
				return false;
			}
			var url = "${pageContext.request.contextPath}/social/brand/saveandreport";
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
						win.successAlert('上报成功！',function(){
								parent.location.reload(true);
								parent.mclose();
						});
					} else {
						win.errorAlert(commitresult.exceptionStr);
					}
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
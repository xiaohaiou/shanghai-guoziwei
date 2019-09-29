<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>征信数据采集新增、编辑页面</title>
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
				<span class="glyphicon glyphicon-pencil"></span>征信数据采集
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>征信数据采集
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
			<h3 class="data_title1">征信数据采集</h3>
			<div class="model_part">
				<label class="w20">
					<span class="required"> * </span>年份:
				</label>
				<input id="year" class="w25 time" type="text" name="year" value="${entityview.year }" readonly="true" class="time" />
				<label class="w20">
					<span class="required"> * </span>月份:
				</label>
				<span class="w25">
					<select id="month" name="month" class="selectpicker" >
						<c:forEach var= "temp" begin= "1" step= "1" end= "12">
							<option value="${temp}" <c:if test="${entityview.month == temp}">selected="selected"</c:if>>
								${temp}
							</option>
						</c:forEach>
					</select>
				</span>
				<label class="w20"><span class="required"> * </span>征信数据采集1:</label>
				<form:input class="w25" path="meetCount" check="NotEmpty_int_10_0_0+_._." />
				<label class="w20"><span class="required"> * </span>征信数据采集2:</label>
				<form:input class="w25" path="mediaPopulation" check="NotEmpty_int_10_0_0+_._." />
				<label class="w20"><span class="required"> * </span>征信数据采集3:</label>
				<form:input class="w25" path="mediaAmount" check="NotEmpty_int_10_0_0+_._."/>
				<label class="w20"><span class="required"> * </span>征信数据采集4:</label>
				<select name="mediaClass" class="w25 selectpicker" check="NotEmpty_int_5_0_+_._.">
					<option value="">请选择</option>
					<c:forEach items="${requestScope.mediaClass}" var="result">
						<option value="${result.id }" <c:if test="${entityview.mediaClass == result.id}">selected="selected"</c:if>>${result.description}</option>
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
			if($("#year").val()==""){
				win.errorAlert("年份不能为空");
				return false;
			}
			if($("#month").val()==""){
				win.errorAlert("月份不能为空");
				return false;
			}
			if($("#meetCount").val()==""){
				win.errorAlert("拜会媒体数量(家)不能为空");
				return false;
			}
			if($("#mediaPopulation").val()==""){
				win.errorAlert("拜会媒体人数(人)不能为空");
				return false;
			}
			if($("#mediaAmount").val()==""){
				win.errorAlert("拜会媒体数量(家)不能为空");
				return false;
			}
			if($("#mediaClass").val()==""){
				win.errorAlert("请选择拜会媒体类型");
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
			var url = "${pageContext.request.contextPath}/social/media/save";
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
			var url = "${pageContext.request.contextPath}/social/media/saveandreport";
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
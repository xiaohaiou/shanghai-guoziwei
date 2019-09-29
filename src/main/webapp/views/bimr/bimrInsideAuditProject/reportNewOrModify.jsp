<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>新增审计项目周报</title>
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
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<style type="text/css">
		#com_ztree span {
			padding-left:0;
		}
	</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改审计项目周报
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增审计项目周报
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="weekly" method="post">
		<form:hidden path="id" value="${weekly.id}"/>
		<form:hidden path="createPersonId" value="${weekly.createPersonId}"/>
		<form:hidden path="createPersonName" value="${weekly.createPersonName}"/>
		<form:hidden path="createDate" value="${weekly.createDate}"/>
		<form:hidden path="isDel" value="${weekly.isDel}"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required">*</span>审计项目名称:</label>
				
				<input type="hidden" name="projectId" id="projectId" value="${weekly.projectId}">
				<input type="text" name="projectName" id="projectName" value="${weekly.projectName}" class="w70" readonly="readonly"><br>

				

				<label class="w20"><span class="required"> * </span>周开始日期:</label>
				<input type="text" name="weekStartDate" id="weekStartDate" value="${weekly.weekStartDate}" readonly="readonly" class="w25 time" check="NotEmpty"/>
				<label class="w20"><span class="required"> * </span>周结束日期:</label>
				<input type="text" name="weekEndDate" id="weekEndDate" value="${weekly.weekEndDate}" readonly="readonly" class="w25 time" check="NotEmpty"/><br>

				<label class="w20">周审计累计发现问题数(个):</label>
				<input type="text" name="problemCount" id="problemCount" value="${weekly.problemCount}" class="w25" />
				<label class="w20">周建议问责人员累计数(人):</label>
				<input type="text" name="accountPerson" id="accountPerson" value="${weekly.accountPerson}" class="w25" /><br>

				<%--<label class="w20"><span class="required"> * </span>附件:</label>
				<input class="w25" type="file" id="file1" name="file1"  />
				<br>
				<c:if test="${not empty weekly.fileOne.fileName}">
					<label class="w20">已上传文件:</label>
					<label class="w70 setleft"><a href="${pageContext.request.contextPath}/common/download?_url=${weekly.fileOne.filePath }&_name=${weekly.fileOne.fileName }"
												  data-original-title="" title="">${weekly.fileOne.fileName }</a></label>
				</c:if>--%>

				<label class="w20"><span class="required"> * </span>项目计划结束日期:</label>
				<input type="text" name="projectEndDate" id="projectEndDate" value="${weekly.projectEndDate}" readonly="readonly" class="w70 time" check="NotEmpty"/><br>

				<label class="w20"><span class="required"> * </span>项目状态:</label>
				<select name="projectStatusProgress" id="projectStatusProgress" class="selectpicker w25" check="NotEmpty">
					<c:if test="${not empty weeks}">
						<c:forEach items="${requestScope.projectStatusProgress}" var="result">
						<option value="${result.id}" <c:if test="${auditProject.projectStatusProgress == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
					</c:if>
				</select><br>
				
				<label class="w20"><span class="required"> * </span>本周进展:</label>
				<textarea rows="3" cols="5" class="w70" name="projectprogress" id="projectprogress" placeholder="本周进展" maxlength="500"  check="NotEmpty_string_500_._._._.">${weekly.projectprogress}</textarea><br>

				<label class="w20">审计发现的重大问题或可疑事项:</label>
				<textarea rows="3" cols="5" class="w70" name="importantAndSuspicious" id="importantAndSuspicious" placeholder="请输入审计发现的重大问题或可疑事项" maxlength="500" >${weekly.importantAndSuspicious}</textarea><br>

				<label class="w20">审计工作面临的困难和应对措施:</label>
				<textarea rows="3" cols="5" class="w70" name="difficultAndMeasures" id="difficultAndMeasures" placeholder="请输入审计工作面临的困难和应对措施" maxlength="500" >${weekly.difficultAndMeasures}</textarea><br>

				<label class="w20"><span class="required"> * </span>下周工作计划:</label>
				<textarea rows="3" cols="5" class="w70" name="nextWeekPlan" id="nextWeekPlan" placeholder="请输入下周工作计划" maxlength="500" check="NotEmpty_string_500_._._._.">${weekly.nextWeekPlan}</textarea><br>
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit" >保存</button>
				<button class="btn btn-default model model_btn_close">取消</button>
			</div>
		</div>
	</form:form>
	<jsp:include page="../../system/modal.jsp"/>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});

		$("#commit").click(function(){
		    if(!vaild.all()) {
				return false;
			}
			var startDate = $("#weekStartDate").val();
		    var endDate = $("#weekEndDate").val();
		    if (checkTime(startDate,endDate)){
                var ety = new FormData($("#form2")[0]);
                var url = "${pageContext.request.contextPath}/bimr/weekly/saveOrUpdate";
                $.ajax({
                    url : url,
                    type : "POST",
                    data: ety,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success : function(data) {
                        var r = data.result;
                        if (r == 1){
                            win.successAlert(data.message,function(){
                                parent.location.reload(true);
                                parent.mclose();
                            });
                        }else{
                            win.errorAlert(data.message);
                        }
                    }
                });
			}

			return false;
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

		function del(id,nRow){
			win.confirm('确定要删除？',function(r){
				if(r){
					$("#abarbeitungQuestion tbody").find("tr").eq(nRow).remove();
					var rowNums = $("#abarbeitungQuestion tbody tr").length; 
					for(var m=0; m<rowNums; m++){
						$("#abarbeitungQuestion tbody").find("tr").eq(m).children('td').eq(0).html(m+1);
					}
				}
			});
		};

		$(document).ready(function() {
            $("#projectId").change(function(){
                var v = $("#projectId").find('option:selected').text();
                $('#projectName').empty();
                $('#projectName').val(v);
            });
		});
		//时间校验
		function checkTime(start,end) {
			var flag = true;
			if (start > end) {
				win.generalAlert("周结束时间不应在周开始时间之前!");
				flag = false;
			}
			return flag;
		};
	</script>
</html>
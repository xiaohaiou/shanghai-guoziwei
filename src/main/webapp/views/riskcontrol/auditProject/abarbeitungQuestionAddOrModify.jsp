<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>审计项目整改问题新增、编辑页面</title>
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
		<c:when test="${op eq 'add'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增审计项目整改问题
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改审计项目整改问题
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form id="form2">
		<div class="label_inpt_div">
			<div class="model_part">
				<input type="hidden" name="id" id="id" value="${auditProjectAbarbeitungQuestion.id}"> 
				<c:if test="${op eq 'modify'}">
					<label class="w20">审计项目名称:</label>
					<input class="w25" type="text" id="auditProjectName" value="${auditProjectAbarbeitungQuestion.auditProjectName}" readonly="readonly"/>
				</c:if>
				<label class="w20"><span class="required"> * </span>整改完成状态:</label>
				<select class="w25" name="abarbeitungStatus" id="abarbeitungStatus"  value="${auditProjectAbarbeitungQuestion.abarbeitungStatus}">  
				  <option value="0" <c:if test="${'0' eq auditProjectAbarbeitungQuestion.abarbeitungStatus}">selected</c:if> >未完成</option>        
				  <option value="1" <c:if test="${'1' eq auditProjectAbarbeitungQuestion.abarbeitungStatus}">selected</c:if> >已完成</option>     
				</select><br>
				<label class="w20"><span class="required"> * </span>整改负责人:</label>
				<input type="text" name="abarbeitungOfficer" id="abarbeitungOfficer" placeholder="请输入负责人" value="${auditProjectAbarbeitungQuestion.abarbeitungOfficer}" class="w25"/>
				<label class="w20"><span class="required"> * </span>整改时限:</label>
				<input type="text" name="abarbeitungTimeLimit" id="abarbeitungTimeLimit" value="${auditProjectAbarbeitungQuestion.abarbeitungTimeLimit}" readonly="readonly" class="w25 time"/>
				<label class="w20"><span class="required"> * </span>存在问题:</label>
				<textarea rows="3" cols="5" class="w70" name="existentialQuestion" id="existentialQuestion" maxlength="500" placeholder="不超过五百字符">${auditProjectAbarbeitungQuestion.existentialQuestion}</textarea><br>
				<label class="w20"><span class="required"> * </span>审计建议:</label>
				<textarea rows="3" cols="5" class="w70" name="auditSuggestion" id="auditSuggestion" maxlength="500" placeholder="不超过五百字符">${auditProjectAbarbeitungQuestion.auditSuggestion}</textarea><br>
				<label class="w20"><span class="required"> * </span>整改措施:</label>
				<textarea rows="3" cols="5" class="w70" name="abarbeitungMeasures" id="abarbeitungMeasures" maxlength="500" placeholder="不超过五百字符">${auditProjectAbarbeitungQuestion.abarbeitungMeasures}</textarea><br>
				<label class="w20">备注:</label>
				<textarea rows="3" cols="5" class="w70" name="remark" id="remark" maxlength="500" placeholder="不超过五百字符">${auditProjectAbarbeitungQuestion.remark}</textarea>	
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >提交</button>
				<button class="btn btn-default model model_btn_close">取消</button>
			</div>
		</div>
	</form>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">	
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		function checkcommit() {
			if($("#abarbeitungStatus").val()=="") {
				win.errorAlert("整改完成状态不能为空");
				return false;
			}
			if($("#abarbeitungOfficer").val()=="") {
				win.errorAlert("整改负责人不能为空");
				return false;
			}
			if($("#abarbeitungTimeLimit").val()=="") {
				win.errorAlert("整改时限不能为空");
				return false;
			}
			if($("#existentialQuestion").val()=="") {
				win.errorAlert("存在问题不能为空");
				return false;
			}
			if($("#auditSuggestion").val()=="") {
				win.errorAlert("审计建议不能为空");
				return false;
			}
			if($("#abarbeitungMeasures").val()=="") {
				win.errorAlert("整改措施不能为空");
				return false;
			}
			return true;
		};	
		$("#commit-1").click(function(){
			var op = '${op}';
			if(!checkcommit() || !vaild.all()) {
				return false;
			}
			var status = $('#abarbeitungStatus').val();
			var abarbeitungStatus = "";
			var abarbeitungOfficer = $("#abarbeitungOfficer").val();
			var abarbeitungTimeLimit = $("#abarbeitungTimeLimit").val();
			var existentialQuestion = $("#existentialQuestion").val();
			var auditSuggestion = $("#auditSuggestion").val();
			var abarbeitungMeasures = $("#abarbeitungMeasures").val();
			if(status == '0'){
				abarbeitungStatus = "未完成";
			}else if(status == '1'){
				abarbeitungStatus = "已完成";
			}
			var remark = $("#remark").val();
			var num = $("#abarbeitungQuestion tbody tr", window.parent.document).length;
			if(op == 'add'){
				$("#abarbeitungQuestion tbody", window.parent.document).append("<tr><td style='text-align: center'>"+(num+1)+
			    		"</td><td style='text-align:left;word-wrap:break-word;'>"+existentialQuestion+
			    		"</td><td style='text-align:left;word-wrap:break-word;'>"+auditSuggestion+
			    		"</td><td style='text-align:left;word-wrap:break-word;'>"+abarbeitungMeasures+
			    		"</td><td style='text-align:center;word-wrap:break-word;'>"+abarbeitungTimeLimit+
			    		"</td><td style='text-align:center;word-wrap:break-word;'>"+abarbeitungOfficer+
			    		"</td><td style='text-align:left;word-wrap:break-word;'>"+remark+
			    		"</td><td style='text-align:center;word-wrap:break-word;'>"+abarbeitungStatus+"</td><td style='text-align:center'><a title='修改' class=\'btn btn-primary model_btn_ok updateQuestion\'>修改</a><a title='删除' class=\'btn btn-primary model_btn_ok deleteQuestion\' id='del'>删除</a></td></tr>");
			    win.successAlert('保存成功！',function(){
					parent.mclose();
				});
			}else{
				var nRow = '${nRow}';
				var tabTr = $("#abarbeitungQuestion tbody",window.parent.document).find("tr").eq(nRow); 
				tabTr.children('td').eq(0).html(parseInt(nRow)+1);
				tabTr.children('td').eq(1).html(existentialQuestion);
				tabTr.children('td').eq(2).html(auditSuggestion);
				tabTr.children('td').eq(3).html(abarbeitungMeasures);
				tabTr.children('td').eq(4).html(abarbeitungTimeLimit);
				tabTr.children('td').eq(5).html(abarbeitungOfficer);
				tabTr.children('td').eq(6).html(remark);
				tabTr.children('td').eq(7).html(abarbeitungStatus);
				win.successAlert('修改成功！',function(){
					parent.mclose();
				});
			}/* else{
				var abarbeitungQuestionInfo = $('#form2').serialize();
				var url = "${pageContext.request.contextPath}/riskcontrol/auditProject/updateAbarbeitungQuestion";
				$.post(url, abarbeitungQuestionInfo, function(data) {
					$.unblockUI();
					if(data == "success"){
						win.successAlert('保存成功！',function(){
							parent.location.reload();
							parent.mclose();
						});
					}
				});
			} */
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
	</script>
</html>
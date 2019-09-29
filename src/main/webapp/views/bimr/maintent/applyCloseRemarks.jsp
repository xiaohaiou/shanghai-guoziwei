<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>新增审计项目相关问题</title>
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
	<h4>
		<span class="glyphicon glyphicon-pencil"></span> 申请办结备注
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="bimr" method="post">
		<form:hidden path="id" value="${id}"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>申请办结备注:</label>
				<textarea rows="3" cols="5" class="w70" name="applycloseremarks" id="applycloseremarks" placeholder="申请办结备注" maxlength="500" check="NotEmpty_string_500_._._._."></textarea><br>
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

		/*$("#projectId").change(function(){
            var v = $("#projectId").find('option:selected').text();
            $('#projectName').empty();
            $('#projectName').val(v);
		});*/

		$("#commit").click(function(){
		    if(!vaild.all()) {
				return false;
			}
			/*var QTypes = $('.QTypes').val();
            $("#auditQuestionTypes").empty();
		    $("#auditQuestionTypes").val(QTypes);
            var RTypes = $('.RTypes').val();
            $("#riskDriverTypes").val(RTypes);*/

            var question = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/inside/applyFor";
            $.ajax({
                url : url,
                type : "POST",
                data: question,
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

		/*$(document).ready(function() {
		    var qtype = $('#auditQuestionTypes').val().split(",");
		    if (qtype != null && qtype != ''){
                for (var qid in qtype){
                    $('.QTypes option').each(function (index,data) {
                        if (index == qid){
                            $(".QTypes option[value='"+qid+"']").attr("selected","selected");
                        }
                    });
                }
			}

		    var rtype = $('#riskDriverTypes').val().split(",");
		    if (rtype != null && rtype != ''){
                for (var rid in rtype){
                    $('.RTypes option').each(function (index,data) {
                        if (index == rid){
                            $(".RTypes option[value='"+rid+"']").attr("selected","selected");
                        }
                    });
                }
			}
		});*/

		//时间校验
		function checkTime(start,end) {
			var flag = true;
			if (start > end) {
				win.generalAlert("结束时间不应在开始时间之前!");
				flag = false;
				return flag;
			}
		};
	</script>
</html>
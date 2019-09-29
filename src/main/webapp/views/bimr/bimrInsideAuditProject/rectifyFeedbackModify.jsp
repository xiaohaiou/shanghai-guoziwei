<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>内部审计项目跟踪反馈</title>
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
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>内部审计项目跟踪反馈
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="rectifyQuestion" method="post">
		<div class="label_inpt_div">
		  <h4><span style="font-weight: 1px;">查询审计项目相关问题整改</span></h4>
			<div class="model_part">
				<label class="w20">审计项目名称:</label>
				<label class="w70 setleft">${entity.projectName}</label><br>

				<label class="w20">整改问题状态:</label>
				<label class="w70 setleft">${entity.status == 1?"整改中":"已完成"}</label><br>

				<label class="w20">整改责任人:</label>
				<label class="w70 setleft">${entity.rectifyResponseName}</label><br>

				<label class="w20">整改时限:</label>
				<label class="w70 setleft">${entity.rectifyTime}</label><br>

				<label class="w20">整改责任部门:</label>
				<label class="w70 setleft">${entity.rectifyResponseDept}</label><br>

				<label class="w20">整改对接人:</label>
				<label class="w70 setleft">${entity.rectifyPickerName}</label><br>

				<label class="w20">存在问题:</label>
				<label class="w70 setleft">${entity.problems}</label><br>

				<label class="w20">审计建议:</label>
				<label class="w70 setleft">${entity.rectifyAdvice}</label><br>
				
				<label class="w20"><span class="required"> * </span>反馈日期:</label>
					<input type="text" name="feedbackTime" id="feedbackTime" readonly="readonly" class="w25 time" check="NotEmpty"/><br>
			</div>
			<div class="tablebox">
				<table id="csTab">
					<tr class="table_header">
						<th width="30%">整改措施</th>
						<th width="10%">完成时限</th>
						<th width="40%">本月反馈</th>
						<th width="20%">措施整改状态</th>
					</tr>
					<c:if test="${!empty requestScope.measuresList }">
						<c:forEach items="${ requestScope.measuresList}" var="l"varStatus="status">
							<tr>
								<td style="display:none">${l.id}</td>
								<td style="display:none">${l.rectifyId}</td>
								<td style="text-align: center;">${l.rectifyMeasure}</td>
								<td style="text-align: center;">${l.rectifyMeasureTime}</td>
								<td><input type="text" id="feedbackDesc" check="NotEmpty_string_200_._._._." value="${l.feedbackDesc}" ></td>
								<td>
									<select id="measureStatus">
										<option value="1" <c:if test="${'1' eq l.status}">selected</c:if> >整改中</option>
				    					<option value="2" <c:if test="${'2' eq l.status}">selected</c:if> >整改完成</option>
									</select>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="save">保存</button>
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
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
		$("#save").click(function(){
		    if(!vaild.all()) {
				return false;
			}
			var mytable = document.getElementById("csTab");
		    var tab = new Array();
		    tab.push(document.getElementById("feedbackTime").value);
		    for(var i = 1; i < mytable.rows.length; i++){
		    	var cell_0 = mytable.rows[i].cells[0].innerHTML;
		    	var cell_1 = mytable.rows[i].cells[1].innerHTML;
		    	var cell_2 = mytable.rows[i].cells[2].innerHTML;
		    	var cell_3 = mytable.rows[i].cells[4].children[0].value;
		    	var cell_4 = mytable.rows[i].cells[5].children[0].value;
		    	tab.push(cell_0 + ";-;" + cell_2 + ";-;" + cell_3 + ";-;" + cell_4 + ";-;" + cell_1);
		    }
			var tabData = JSON.stringify(tab);
			var url = "${pageContext.request.contextPath}/bimr/rectify/feedBackSaveOrUpdate";
            $.ajax({
                url : url,
                type : "POST",
                data: {"tableVal":tabData},
                async: false,
                datatype: "json",
                processData: true,//processData 默认为false，当设置为true的时候,jquery ajax 提交的时候不会序列化 data，而是直接使用data
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
</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改合规整改问题维护</title>
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
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>修改合规整改问题维护
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>提出合规建议情况:</label> 
				<label class="w25 setleft">${entity.suggest}</label></br>
				<label class="w20"><span class="required"> * </span>整改跟踪人:</label> 
				<input class="w25" name="followPerson" id="inp_followPerson" value="${entity.followPerson }" check="NotEmpty_string_._._._._." readonly="readonly"/></br>
				<label class="w20">整改对接人:</label>
				<input class="w25" name="abutmentPerson" id="inp_abutmentPerson"  value="${entity.abutmentPerson }" /></br>
				<label class="w20">整改责任人:</label>
				<input class="w25" name="accountabilityPerson" id="inp_accountabilityPerson"  value="${entity.accountabilityPerson }" /></br>
				<label class="w20"><span class="required"> * </span>整改责任单位:</label>
				<span class="w25"> 
					<input type="hidden" id="accountabilityDepId" name="accountabilityDepId" value="${entity.accountabilityDepId}" >
					<input name="accountabilityDep" id="accountabilityDep" value="${entity.accountabilityDep}" readonly="readonly" class="w70" check="NotEmpty">
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:500px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
						</ul>
				   </div>
			    </span>
				<label class="w20"><span class="required"> * </span>整改完成预计时限:</label>
				<input class="w25 time" name="changeLastTime" id="changeLastTime"  value="${entity.changeLastTime }" readonly="true" check="NotEmpty_string_._._._._."/></br>
				<label class="w20"><span class="required"> * </span>整改完成状态:</label>
				<td style="text-align: left;">
					<select name="changeStatus" id="sel_changeStatus" class="selectpicker">
						<option value="0" <c:if test="${entity.changeStatus == 0}">selected="selected"</c:if>>未整改</option>
						<option value="1" <c:if test="${entity.changeStatus == 1}">selected="selected"</c:if>>已整改</option>
						<option value="2" <c:if test="${entity.changeStatus == 2}">selected="selected"</c:if>>整改中</option>
					</select> 
				</td></br>
				<label class="w20"><span class="required"> * </span>整改工作进展:</label>
				<textarea class="w70" rows="5" cols="" id="tex_changeProgress" name="changeProgress" style="height:5em;" maxlength="200" check="NotEmpty_string_._._._._.">${entity.changeProgress}</textarea></br>
			    <input type="hidden" name="id" value="${entity.id}"/>
			</div>
			</br>
			<div class="model_btn">
			    <button class="btn btn-default model model_btn_save" id="but_save">保存</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp" />
	</form:form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		var timeoutId;
		$('#accountabilityDep').on('focus click',function(){
			$(this).next('.com_ztree_box').css('display','block')
		}).parent().on('mouseenter',function(){
			clearTimeout(timeoutId)
		}).on('mouseleave',function(){
			clearTimeout(timeoutId)
			timeoutId = setTimeout(function(el){
				$(el).find('.com_ztree_box').css('display','none')
			},3e2,this);
		});
		var zTreeObj;
		var com_ztree_setting = {
			check:{
				enable:false,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			}
		};
		$(function () {
		     //所有企业数据	
		    var com_data = ${allCompanyTree};
			zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
			//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
			var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = treeObj.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
			var childrenNodes = treeObj.transformToArray(nodes);
			if(childrenNodes[0]!=null){
				treeObj.expandNode(childrenNodes[0],true);
			}
		});
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					$("#accountabilityDepId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
					$("#accountabilityDep").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
	//关闭弹窗
	$(".model_btn_close").click(function() {
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
	
    function saveProblem(){
		
		if(!vaild.all()){
			return false;
		}
		
		$.blockUI({ message: '提交中', css: { width: '275px' } });
		var params = $('#form2').serialize();
	
		var url = "${pageContext.request.contextPath}/bimr/compliance/saveCorrectProblem";
		$.post(url, params, function(data) {
			$.unblockUI();
			if(data.result==true){
				win.successAlert('保存成功！',function(){
					parent.mclose();
					parent.location.reload();	
			    });
			}else{
			    win.errorAlert(data.exceptionStr);
			}
		});
	}
    
    $('#but_save').click(function(){
    	saveProblem();
    	return false;
    });
	 
</script>
</html>
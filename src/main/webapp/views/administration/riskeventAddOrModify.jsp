<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>监管预警数据新增、编辑及保存上报页面</title>
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
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改监管预警数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增监管预警数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	
	<form:form id="form2" modelAttribute="theEntity" method="post">
		<form:hidden path="id"/>
		<form:hidden path="createPersonName" />
		<form:hidden path="createPersonId" />
		<form:hidden path="createDate" />
		<form:hidden path="reportPersonName" />
		<form:hidden path="reportPersonId" />
		<form:hidden path="reportDate" />
		<form:hidden path="auditorPersonName" />
		<form:hidden path="auditorPersonId" />
		<form:hidden path="auditorDate" />
		<input type="hidden" id="lastModifyDate" name="lastModifyDate" value="${lastModifyDate}"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>发生日期:</label>
				<input class="w25 moreTime" type="text" name="riskDate" id="riskDate" value="${theEntity.riskDate }" check="NotEmpty_string_._._._._." readonly="readonly"/>
				<label class="w20"><span class="required"> * </span>年份:</label>
				<input class="w25" type="text" name="year" id="year" value="${theEntity.year }" readonly/>
				<label class="w20"><span class="required"> * </span>季度:</label>
				<input class="w25" type="text" name="seasonName" id="seasonName" value="${theEntity.seasonName }" readonly/>
				<%-- <span class="w25">
					<select  name="season" class="selectpicker" >
						<option value="">
						<c:forEach items="${requestScope.seasontype}" var="result">
							<option value="${result.id }"  <c:if test="${theEntity.season == result.id}">selected="selected"</c:if>>${result.description }</option>
						</c:forEach>
					</select>
				</span> --%>
			<!-- </div>
			<div class="label_inpt_div"> -->
				<label class="w20"><span class="required"> * </span>监管预警数据单位名称:</label>
				<span  class="w25"> 
					<input name="riskCompName" id="riskCompName" value="${theEntity.riskCompName }" check="NotEmpty_string_._._._._." readonly>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:29%;position:absolute;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				   </div>
			    </span>
			    <input type="hidden" name="riskCompId" id="riskCompId" value="${theEntity.riskCompId}" />
			    <label class="w20"><span class="required"> * </span>监管预警数据所属企业:</label>
				<input class="w25" type="text" name="coreCompName" id="coreCompName" readonly value="${theEntity.coreCompName }" />
				<input type="hidden" name="coreCompId" id="coreCompId" value="${theEntity.coreCompId}" />
				<label class="w20"><span class="required"> * </span>监管预警数据标题:</label>
				<input class="w25" type="text" name="eventTitle" id="eventTitle" value="${theEntity.eventTitle }" check="NotEmpty_string_._._._._." />
				<p><label class="w20" style="text-align:right;margin-bottom: 0"><span class="required"> * </span>监管预警数据概要:</label></p>
				<%-- <input class="w25" type="text" name="eventDescription" id="eventDescription" value="${theEntity.eventDescription }" /> --%>
				<textarea style="margin: 5px 5%;width: 90%;height:6em" rows="5" name="eventDescription" id="eventDescription">${theEntity.eventDescription }</textarea>
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2" >保存并上报</button>
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
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		var timeoutId
 		$('#riskCompName').on('focus click',function(){
 			$(this).next('.com_ztree_box').css('display','block')
 		}).parent().on('mouseenter',function(){
 			clearTimeout(timeoutId)
 		}).on('mouseleave',function(){
 			clearTimeout(timeoutId)
 			timeoutId = setTimeout(function(el){
 				$(el).find('.com_ztree_box').css('display','none')
 			},3e2,this);
 		})
	
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
    		if(childrenNodes[0]!=null)
    			treeObj.expandNode(childrenNodes[0],true);
	    });
	
		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#riskCompId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#riskCompName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 var riskCompId = $("#riskCompId").val();
							 var url = "${pageContext.request.contextPath}/adRiskevent/getCoreComp";
							 $.ajax({
							 	type:'POST',
							 	data:{riskCompId:riskCompId},
							 	dataType:'json',
							 	url:url,
							 	success:function(data){
							 		$("#coreCompId").val(data.nnodeId);
							 		$("#coreCompName").val(data.vcFullName);
							 	}
							 });
						}
			   	});
			
		})
		
		$('input.time').jeDate(
			{
				format:"YYYY"
			}
		)
		
		$('input.moreTime').jeDate(
			{
				format:"YYYY-MM-DD",
				choosefun:function(elem, val, date) {changeDate(val)},
				clearfun:function(elem, val) {changeDate("")},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {changeDate(val)}, 
			}
		)
		
		function changeDate(val){
			if(val == ""){
				$("#year").val("");
				$("#year").text("");
				$("#seasonName").val("");
				$("#seasonName").text("");
			}else{
				var year = parseInt((val.split("-"))[0]);
				$("#year").val(year);
				$("#year").text(year);
				var month = parseInt((val.split("-"))[1]);
				if(month <= 3){
					$("#seasonName").val("第一季度");
					$("#seasonName").text("第一季度");
				}else if(month >3 && month <= 6){
					$("#seasonName").val("第二季度");
					$("#seasonName").text("第二季度");
				}else if(month >6 && month <=9){
					$("#seasonName").val("第三季度");
					$("#seasonName").text("第三季度");
				}else{
					$("#seasonName").val("第四季度");
					$("#seasonName").text("第四季度");
				}
			}
		}
		
		function checkcommit()
		{
			if($("#eventDescription").val()=="")
			{
				win.errorAlert("事件概要不能为空");
				return false;
			}
			return true;
		}
		
		function commit1(){
			if(!vaild.all() || !checkcommit())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
			var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/adRiskevent/saveOrUpdate";
			$.post(url, entityBasicInfo, function(data) {
					$.unblockUI();
					var commitresult=JSON.parse(data);
					if(commitresult.result==true)
						win.successAlert('保存成功！',function(){
								parent.location.reload();
								parent.mclose();
						});
					else
						{
							win.errorAlert(commitresult.exceptionStr);
						}
			});
			return false;
		}
		
		function commit2(){
			if(!vaild.all() || !checkcommit())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
			var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/adRiskevent/saveOrUpdateAndReported";
			$.post(url, entityBasicInfo, function(data) {
					$.unblockUI();
					var commitresult=JSON.parse(data);
					if(commitresult.result==true)
						win.successAlert('保存成功！',function(){
								parent.location.reload();
								parent.mclose();
						});
					else
						{
							win.errorAlert(commitresult.exceptionStr);
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
		
		/* $(' input.time').jeDate(
			{
				format:"YYYY-MM",
				choosefun:function(elem, val, date) {changeDate(val)},
				clearfun:function(elem, val) {changeDate("")},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {}, 
			}
		) */
		
		$("#commit-1").click(function(){
			var id = $("#id").val();
			if(id != null && id != ""){
				$.post("${pageContext.request.contextPath}/adRiskevent/checkDelete?id=" + id, function(data) {
					var commitresult=JSON.parse(data);
					if(commitresult.result){
						commit1();
					}else{
						win.errorAlert('此数据已被删除！',function(){
							parent.location.reload();
							parent.mclose();
						});
					}
				});
				return false;
			}else {
				commit1();
				return false;
			}
		});
		
		$("#commit-2").click(function(){
			var id = $("#id").val();
			if(id != null && id != ""){
				$.post("${pageContext.request.contextPath}/adRiskevent/checkDelete?id=" + id, function(data) {
					var commitresult=JSON.parse(data);
					if(commitresult.result){
						commit2();
					}else{
						win.errorAlert('此数据已被删除！',function(){
							parent.location.reload();
							parent.mclose();
						});
					}
				});
				return false;
			}else {
				commit2();
				return false;
			}
		});
		
	</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>企业财务数据新增、编辑及保存上报页面</title>
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
				<span class="glyphicon glyphicon-pencil"></span>修改企业财务数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增企业财务数据
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
				<label class="w20"><span class="required"> * </span>年份:</label>
				<input class="w25 time" type="text" name="year" id="year" value="${theEntity.year }" check="NotEmpty_string_._._._._." readonly="true" class="time" check="NotEmpty_int_4_0_+_._." />
				<label class="w20"><span class="required"> * </span>月份:</label>
				<span class="w25">
					<select  name="month" id="month" class="selectpicker"  >
						<c:forEach var= "temp" begin= "1" step= "1" end= "12">
							<option value="${temp}" <c:if test="${theEntity.month == temp}">selected="selected"</c:if>>
								${temp}
							</option>
						</c:forEach>
					</select>
				</span>
				<br>
			<!-- </div>
			<div class="label_inpt_div"> -->
				<label class="w20"><span class="required"> * </span>企业财务数据单位:</label>
				<span  class="w25"> 
					<input name="importantCompName" id="importantCompName" value="${theEntity.importantCompName }" check="NotEmpty_string_._._._._." readonly>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				   </div>
			    </span>
			    <input type="hidden" name="importantCompId" id="importantCompId" value="${theEntity.importantCompId}" />
			    <label class="w20"><span class="required"> * </span>企业财务数据所属企业:</label>
				<input class="w25" type="text" name="coreCompName" id="coreCompName" readonly value="${theEntity.coreCompName }" />
				<input type="hidden" name="coreCompId" id="coreCompId" value="${theEntity.coreCompId}" />
				<label class="w20"><span class="required"> * </span>企业财务数据类别:</label>
				<span class="w25">
					<select  name="importantType" id="importantType" class="selectpicker"  >
						<c:forEach items="${requestScope.importantTypes}" var="result">
							<option value="${result.id }"  <c:if test="${theEntity.importantType == result.id}">selected="selected"</c:if>>${result.description }</option>
						</c:forEach>
					</select>
				</span>
				<label class="w20"><span class="required"> * </span>企业财务数据采集时间:</label>
				<input class="w25 moreTime" type="text" name="importantDate" id="importantDate" check="NotEmpty_string_._._._._." readonly="true" value="${theEntity.importantDate }" />
				<label class="w20"><span class="required"> * </span>企业财务数据时间:</label>
				<input class="w25 moreTime" type="text" name="reportedCoreDate" id="reportedCoreDate" check="NotEmpty_string_._._._._." readonly="true" value="${theEntity.reportedCoreDate }" />
				<label class="w20"><span class="required"> * </span>核心企业财务数据时间:</label>
				<input class="w25 moreTime" type="text" name="reportedDepDate" id="reportedDepDate" check="NotEmpty_string_._._._._." readonly="true" value="${theEntity.reportedDepDate }" />
				<label class="w20"><span class="required"> * </span>企业财务数据时间:</label>
				<input class="w25 moreTime" type="text" name="reportedGroupDate" id="reportedGroupDate" check="NotEmpty_string_._._._._." readonly="true" value="${theEntity.reportedGroupDate }" />
				<label class="w20"><span class="required"> * </span>企业财务数据标题:</label>
				<input class="w25" type="text" name="importantTitle" id="importantTitle" value="${theEntity.importantTitle }"  check="NotEmpty_string_._._._._."/>
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
 		$('#importantCompName').on('focus click',function(){
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
							 $("#importantCompId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#importantCompName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 var importantCompId = $("#importantCompId").val();
							 var url = "${pageContext.request.contextPath}/adImportant/getCoreComp";
							 $.ajax({
							 	type:'POST',
							 	data:{importantCompId:importantCompId},
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
				format:"YYYY-MM-DD hh:mm:ss"
			}
		)
		
		function checkcommit()
		{
			if(checkTime($("#importantDate").val(),$("#reportedCoreDate").val(),$("#reportedDepDate").val(),$("#reportedGroupDate").val()) == false){
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
			var url = "${pageContext.request.contextPath}/adImportant/saveOrUpdate";
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
			var url = "${pageContext.request.contextPath}/adImportant/saveOrUpdateAndReported";
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
		
		//时时间校验
		function checkTime(start,next,mid,end) {
			var flag = true;
			if (start > next) {
				win.generalAlert("成员企业上报核心企业时间不应在要情发生时间之前!");
				flag = false;
				return flag;
			}
			if (start > mid) {
				win.generalAlert("核心企业上报物流总部时间不应在要情发生时间之前!");
				flag = false;
				return flag;
			}
			if (start > end) {
				win.generalAlert("物流总部上报集团总部时间不应在要情发生时间之前!");
				flag = false;
				return flag;
			}
			if (mid > end) {
				win.generalAlert("物流总部上报集团总部时间不应在核心企业上报物流总部时间之前!");
				flag = false;
				return flag;
			}
			if (next > end) {
				win.generalAlert("物流总部上报集团总部时间不应在成员企业上报核心企业时间之前!");
				flag = false;
				return flag;
			}
			if (next > mid) {
				win.generalAlert("核心企业上报物流总部时间不应在成员企业上报核心企业时间之前!");
				flag = false;
				return flag;
			}
		};
		
		$("#commit-1").click(function(){
			var id = $("#id").val();
			if(id != null && id != ""){
				$.post("${pageContext.request.contextPath}/adImportant/checkDelete?id=" + id, function(data) {
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
				$.post("${pageContext.request.contextPath}/adImportant/checkDelete?id=" + id, function(data) {
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
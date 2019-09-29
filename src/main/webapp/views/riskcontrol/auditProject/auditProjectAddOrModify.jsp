<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>审计项目相关信息新增、编辑及保存上报页面</title>
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
				<span class="glyphicon glyphicon-pencil"></span>修改审计项目相关信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增审计项目相关信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="auditProject" method="post">
		<form:hidden path="id"/>
		<form:hidden path="createPersonName"/>
		<form:hidden path="createPersonId"/>
		<form:hidden path="createDate"/>
		<input type="hidden" name="abarbeitungQuestionList" id="abarbeitungQuestionList"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>审计单位:</label>
				<span class="w25"> 
					<input type="hidden" id="auditDept" name="auditDept" value="${auditProject.auditDept}" >
					<input name="auditDeptName" id="auditDeptName" value="${auditProject.auditDeptName}" readonly="readonly">
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
						</ul>
				   </div>
			    </span>
				<label class="w20"><span class="required"> * </span>审计项目名称:</label>
				<input class="w25" type="text" name="auditProjectName" id="auditProjectName" placeholder="请输入审计项目名称" maxlength="50" value="${auditProject.auditProjectName}"/><br>
				<label class="w20"><span class="required"> * </span>审计类型一:</label>
				<select name="auditType" id="auditType" class="selectpicker w25" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.auditType}" var="result">
							<option value="${result.id}"  <c:if test="${auditProject.auditType == result.id}">selected="selected"</c:if>>${result.description}</option>
						</c:forEach>
				</select>
				<label class="w20">审计类型二:</label>
				<select name="auditType2" class="selectpicker w25" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.auditType2}" var="result">
							<option value="${result.id}"  <c:if test="${auditProject.auditType2 == result.id}">selected="selected"</c:if>>${result.description}</option>
						</c:forEach>
				</select><br>
				<label class="w20"><span class="required"> * </span>审计开始日期:</label>
				<input type="text" name="auditStartDate" id="auditStartDate" value="${auditProject.auditStartDate}" readonly="readonly" class="w25 time"/>
				<label class="w20"><span class="required"> * </span>审计结束日期:</label>
				<input type="text" name="auditEndDate" id="auditEndDate" value="${auditProject.auditEndDate}" readonly="readonly" class="w25 time"/><br>
				<label class="w20">备注:</label>
				<textarea rows="3" cols="5" class="w70" name="remark" id="remark" placeholder="请输入备注" maxlength="500">${auditProject.remark}</textarea><br>
				<label class="w20">涉案金额（元）:</label>                                             
				<input class="w25" type="text" name="caseTotalAmount" id="caseTotalAmount" check="NotEmpty_double_20_4_0+_。_." value="${auditProject.caseTotalAmount}"/>
				<label class="w20">挽回损失（元）:</label>
				<input class="w25" type="text" name="saveLoss" id="saveLoss" check="NotEmpty_double_20_4_0+_。_." value="${auditProject.saveLoss}"/><br>
				<label class="w20">审计处理和责任追究（人员和处分）:</label>
				<textarea rows="3" cols="5" class="w70" name="auditManage" id="auditManage" placeholder="请输入审计处理和责任追究（人员和处分）" maxlength="500">${auditProject.auditManage}</textarea>
				<label class="w20"><span class="required"> * </span>审计是否发现问题:</label>
				<select class="w25" name="isQuestion" id="isQuestion"  value="${auditProject.isQuestion}"> 
					<option value="0" <c:if test="${'0' eq auditProject.isQuestion}">selected</c:if> >否</option>        
				    <option value="1" <c:if test="${'1' eq auditProject.isQuestion}">selected</c:if> >是</option>     
				</select>
				<label class="w20"><span class="required"> * </span>审计发现问题是否整改:</label>
				<select class="w25" name="isAbarbeitung" id="isAbarbeitung"  value="${auditProject.isAbarbeitung}">   
					<option value="0" <c:if test="${'0' eq auditProject.isAbarbeitung}">selected</c:if> >否</option>       
				    <option value="1" <c:if test="${'1' eq auditProject.isAbarbeitung}">selected</c:if> >是</option>     
				</select>
			</div>
			<div id="sjfxwtslwh">
				<h3 class="data_title1">审计发现问题数量维护</h3>
				<div class="model_part">
					<label class="w20">公司战略制定和执行方面问题数(个):</label>
					<input class="w25" type="text" name="corporateStrategyMakeAndExecute" maxlength="4" value="${auditProject.auditProjectFindQuestion.corporateStrategyMakeAndExecute}"/>
					<label class="w20">财务管控(资金资产安全等)方面问题数(个):</label>
					<input class="w25" type="text" name="financialControl" maxlength="4" value="${auditProject.auditProjectFindQuestion.financialControl}"/><br>
					<label class="w20">人力资源管理方面问题数(个):</label>
					<input class="w25" type="text" name="humanResourceManagement" maxlength="4" value="${auditProject.auditProjectFindQuestion.humanResourceManagement}"/>
					<label class="w20">采购管理方面问题数(个):</label>
					<input class="w25" type="text" name="purchaseManagement" maxlength="4" value="${auditProject.auditProjectFindQuestion.purchaseManagement}"/><br>
					<label class="w20">基础建设方面问题数(个):</label>
					<input class="w25" type="text" name="infrastructure" maxlength="4" value="${auditProject.auditProjectFindQuestion.infrastructure}"/>
					<label class="w20">项目投资(重组并购)方面问题数(个):</label>
					<input class="w25" type="text" name="projectInvestment" maxlength="4" value="${auditProject.auditProjectFindQuestion.projectInvestment}"/><br>
					<label class="w20">生产组织和销售方面问题数(个):</label>
					<input class="w25" type="text" name="productionOrganizationAndSales" maxlength="4" value="${auditProject.auditProjectFindQuestion.productionOrganizationAndSales}"/>
					<label class="w20">行政事务管理问题数(个):</label>
					<input class="w25" type="text" name="administrativeAffairs" maxlength="4" value="${auditProject.auditProjectFindQuestion.administrativeAffairs}"/><br>
					<label class="w20">外部环境和竞争问题数(个):</label>
					<input class="w25" type="text" name="externalEnvironmentAndCompetition" maxlength="4" value="${auditProject.auditProjectFindQuestion.externalEnvironmentAndCompetition}"/>
					<label class="w20">其他方面问题数(个):</label>
					<input class="w25" type="text" name="others" maxlength="4" value="${auditProject.auditProjectFindQuestion.others}"/>
				</div>
			</div>
			<div id="sjzgwtwh">
				<h3 class="data_title1">审计整改问题维护</h3>
				<div class="model_part">
					<input type="button" value="新增整改问题" class="form_btn" style="float:right" onclick="mload('${pageContext.request.contextPath}/riskcontrol/auditProject/addOrModifyAbarbeitungQuestion?op=add')">
					<table id="abarbeitungQuestion" style="table-layout:fixed;">
						<thead>
							<tr class="table_header">
								<th>序号</th>
								<th>存在问题</th>
								<th>审计建议</th>
								<th>整改措施</th>
								<th>整改时限</th>
								<th>整改负责人</th>
								<th>备注</th>
								<th>整改完成状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty requestScope.auditProject.auditProjectAbarbeitungQuestionList}">
								<c:forEach items="${ requestScope.auditProject.auditProjectAbarbeitungQuestionList}" var="auditProjectAbarbeitungQuestion" varStatus="status">
									<tr>
										<td style="text-align:center;">
											${status.index + 1}
										</td>
										<td style="text-align:left;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.existentialQuestion}
										</td>
										<td style="text-align:left;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.auditSuggestion}
										</td>
										<td style="text-align:left;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.abarbeitungMeasures}
										</td>
										<td style="text-align:center;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.abarbeitungTimeLimit}
										</td>
										<td style="text-align:center;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.abarbeitungOfficer}
										</td>
										<td style="text-align:left;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.remark}
										</td>
										<td style="text-align:center;word-wrap:break-word;">
											<c:if test="${auditProjectAbarbeitungQuestion.abarbeitungStatus == 0}">
												未完成
											</c:if>
											<c:if test="${auditProjectAbarbeitungQuestion.abarbeitungStatus == 1}">
												已完成
											</c:if>
										</td>
										<td>
											<a href="javascript:void(0)" onclick="view(${auditProjectAbarbeitungQuestion.id},${status.index})">查看</a>
											<a href="javascript:void(0)" onclick="modify(${auditProjectAbarbeitungQuestion.id},${status.index})">修改</a>
											<a href="javascript:;" onclick="del(${auditProjectAbarbeitungQuestion.id},${status.index})">删除</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2" >保存并上报</button>
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
		var timeoutId;
		$('#auditDeptName').on('focus click',function(){
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
					$("#auditDept").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#auditDeptName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		var abarbeitungStatusList = new Array();
		var abarbeitungOfficerList = new Array();
		var abarbeitungTimeLimitList = new Array();
		var existentialQuestionList = new Array();
		var auditSuggestionList = new Array();
		var abarbeitungMeasuresList = new Array();
		var remarkList = new Array();
		function getAbarbeitungQuestion(){
	        var rowNums = $("#abarbeitungQuestion tbody tr").length; 
	        for(var m=0; m<rowNums; m++){
        		existentialQuestionList[m] = $("#abarbeitungQuestion tbody").find("tr").eq(m).children('td').eq(1).html();
        		auditSuggestionList[m] = $("#abarbeitungQuestion tbody").find("tr").eq(m).children('td').eq(2).html();
        		abarbeitungMeasuresList[m] = $("#abarbeitungQuestion tbody").find("tr").eq(m).children('td').eq(3).html();
        		abarbeitungTimeLimitList[m] = $("#abarbeitungQuestion tbody").find("tr").eq(m).children('td').eq(4).html();
        		abarbeitungOfficerList[m] = $("#abarbeitungQuestion tbody").find("tr").eq(m).children('td').eq(5).html();
        		remarkList[m] = $("#abarbeitungQuestion tbody").find("tr").eq(m).children('td').eq(6).html();
        		abarbeitungStatusList[m] = $("#abarbeitungQuestion tbody").find("tr").eq(m).children('td').eq(7).html();
            }
	        var data = [];
            for(var i=0; i<existentialQuestionList.length; i++){
                data.push({"abarbeitungStatus":abarbeitungStatusList[i],
                           "abarbeitungOfficer":abarbeitungOfficerList[i],
                           "abarbeitungTimeLimit":abarbeitungTimeLimitList[i],
                           "existentialQuestion":existentialQuestionList[i],
                           "auditSuggestion":auditSuggestionList[i],
                           "abarbeitungMeasures":abarbeitungMeasuresList[i],
                           "remark":remarkList[i]});
            }
            //将json对象转为json字符串，前后台传递是以字符串的形式
            var strdata = JSON.stringify(data);
            //将strdata传递到html隐藏域中
            $("#abarbeitungQuestionList").val(strdata);
		};   
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		$("#isQuestion").change(function(){
			if(this.value == 0){
				$("#sjfxwtslwh").hide();
				$("#sjzgwtwh").hide();
				$("#isAbarbeitung").val(0);
			}else{
				$("#sjfxwtslwh").show();
			};
		});	
		$("#isAbarbeitung").change(function(){
			if(this.value == 0){
				$("#sjzgwtwh").hide();
			}else{
				$("#sjzgwtwh").show();
			};
		});	
		function checkcommit() {
			if($("#auditDept").val()=="") {
				win.errorAlert("审计单位不能为空");
				return false;
			}
			if($("#auditProjectName").val()=="") {
				win.errorAlert("审计项目名称不能为空");
				return false;
			}
			if($("#auditType").val()=="") {
				win.errorAlert("审计类型一不能为空");
				return false;
			}
			if($("#auditStartDate").val()=="") {
				win.errorAlert("审计开始日期不能为空");
				return false;
			}
			if($("#auditEndDate").val()=="") {
				win.errorAlert("审计结束日期不能为空");
				return false;
			}
			if(checkTime($("#auditStartDate").val(),$("#auditEndDate").val()) == false){
				return false;
			}
			if($("#isQuestion").val()=="") {
				win.errorAlert("审计是否发现问题不能为空");
				return false;
			}
			if($("#isAbarbeitung").val()=="") {
				win.errorAlert("审计发现问题是否整改不能为空");
				return false;
			}
			return true;
		};	
		$("#commit-1").click(function(){
			if(!checkcommit() || !vaild.all()) {
				return false;
			}
			getAbarbeitungQuestion();
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var auditProjectInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/riskcontrol/auditProject/saveOrUpdate";
			$.post(url, auditProjectInfo, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('保存成功！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}
			});
			return false;
		});
		$("#commit-2").click(function(){
			if(!checkcommit()) {
				return false;
			}
			getAbarbeitungQuestion();
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var auditProjectInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/riskcontrol/auditProject/saveOrUpdateAndReported";
			$.post(url, auditProjectInfo, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('保存成功！',function(){
						parent.location.reload();
						parent.mclose();
					});
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
		var table =  $("#abarbeitungQuestion tbody");
		table.on('click', '.deleteQuestion', function (e) {
	      	var nRow = $(this).parents('tr')[0].sectionRowIndex;
			$("#abarbeitungQuestion tbody tr").eq(nRow).remove();
			var rowNums = $("#abarbeitungQuestion tbody tr").length; 
			for(var m=0; m<rowNums; m++){
				$("#abarbeitungQuestion tbody").find("tr").eq(m).children('td').eq(0).html(m+1);
			}
	    });
		table.on('click', '.updateQuestion', function (e) {
	      	var nRow = $(this).parents('tr')[0].sectionRowIndex;
	      	var abarbeitungStatus = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(7).html();
	      	var abarbeitungOfficer = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(5).html();
	      	var abarbeitungTimeLimit = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(4).html();
	      	var existentialQuestion = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(1).html();
	      	var auditSuggestion = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(2).html();
	      	var abarbeitungMeasures = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(3).html();
	      	var remark = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(6).html();
	      	mload("${pageContext.request.contextPath}/riskcontrol/auditProject/modifyAbarbeitungQuestion?op=update&abarbeitungStatus="+abarbeitungStatus
	      			+"&abarbeitungOfficer="+abarbeitungOfficer+"&abarbeitungTimeLimit="+abarbeitungTimeLimit+"&existentialQuestion="+existentialQuestion
	      			+"&auditSuggestion="+auditSuggestion+"&abarbeitungMeasures="+abarbeitungMeasures+"&remark="+remark+"&nRow="+nRow+"&id="+"");
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
		function modify(id,nRow){
			var abarbeitungStatus = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(7).html();
	      	var abarbeitungOfficer = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(5).html();
	      	var abarbeitungTimeLimit = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(4).html();
	      	var existentialQuestion = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(1).html();
	      	var auditSuggestion = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(2).html();
	      	var abarbeitungMeasures = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(3).html();
	      	var remark = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(6).html();
			mload("${pageContext.request.contextPath}/riskcontrol/auditProject/modifyAbarbeitungQuestion?op=modify&id="+id+"&nRow="+nRow
					+"&abarbeitungStatus="+abarbeitungStatus+"&abarbeitungOfficer="+abarbeitungOfficer+"&abarbeitungTimeLimit="+abarbeitungTimeLimit
					+"&existentialQuestion="+existentialQuestion+"&auditSuggestion="+auditSuggestion+"&abarbeitungMeasures="+abarbeitungMeasures
					+"&remark="+remark+"");
		}
		function view(id,nRow){
			var abarbeitungStatus = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(7).html();
	      	var abarbeitungOfficer = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(5).html();
	      	var abarbeitungTimeLimit = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(4).html();
	      	var existentialQuestion = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(1).html();
	      	var auditSuggestion = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(2).html();
	      	var abarbeitungMeasures = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(3).html();
	      	var remark = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(6).html();
			mload("${pageContext.request.contextPath}/riskcontrol/auditProject/viewAbarbeitungQuestion?op=check&id="+id+"&nRow="+nRow
					+"&abarbeitungStatus="+abarbeitungStatus+"&abarbeitungOfficer="+abarbeitungOfficer+"&abarbeitungTimeLimit="+abarbeitungTimeLimit
					+"&existentialQuestion="+existentialQuestion+"&auditSuggestion="+auditSuggestion+"&abarbeitungMeasures="+abarbeitungMeasures
					+"&remark="+remark+"");
		}
		$(document).ready(function() {
			var isQuestion = $("#isQuestion").val();
			var isAbarbeitung = $("#isAbarbeitung").val();
			if(isQuestion == 1){
				$("#sjfxwtslwh").show();
			}else{
				$("#sjfxwtslwh").hide();
				$("#sjzgwtwh").hide();
			}
			if(isAbarbeitung == 1){
				$("#sjzgwtwh").show();
			}else{
				$("#sjzgwtwh").hide();
			}
		});
		//时时间校验
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
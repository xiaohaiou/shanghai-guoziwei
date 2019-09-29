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
		
		.model_content_div{
				border:1px solid #9AC0CD;
			}
	</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改审计项目相关问题
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增审计项目相关问题
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="auditQuestion" method="post">
		<form:hidden path="id" value="${auditQuestion.id}"/>
		<form:hidden path="createPersonId" value="${auditQuestion.createPersonId}"/>
		<form:hidden path="createPersonName" value="${auditQuestion.createPersonName}"/>
		<form:hidden path="createDate" value="${auditQuestion.createDate}"/>
		<form:hidden path="isDel" value="${auditQuestion.isDel}"/>
		<div class="label_inpt_div">
			<div class="model_part">
			    <label class="w20">审计项目名称:</label>
				<input type="hidden" name="projectId" id="projectName" value="${project.id}">
				<input type="text" name="projectName" id="projectName" value="${project.auditProjectName}" readonly="readonly" class="w70">
				    <%--<input type="hidden" name="projectName" id="projectName" value="${auditQuestion.projectName}">
					<select name="projectId" id="projectId" class="selectpicker w70">
					<option value=""></option>
					<c:if test="${not empty requestScope.projects}">
						<c:forEach items="${requestScope.projects}" var="r">
							<option value="${r.id}" <c:if test="${r.id == auditQuestion.projectId}">selected="selected"</c:if>>${r.auditProjectName}</option>
						</c:forEach>
					</c:if>
				</select>--%>
				<br>
				<label class="w20"><span class="required"> * </span>问题标题:</label>
				<input class="w25" type="text" name="problemTopic" id="problemTopic" value="${auditQuestion.problemTopic}" placeholder="请输入问题标题"/ maxlength="30" check="NotEmpty_string_30_._._._."><br>
				<label class="w20"><span class="required"> * </span>存在的问题:</label>
				<textarea rows="3" cols="5" class="w70" name="problem" id="problem" placeholder="请输入存在的问题" maxlength="500" check="NotEmpty_string_500_._._._.">${auditQuestion.problem}</textarea><br>

				<label class="w20"><span class="required"> * </span>问题归属单位:</label>
				<span>
					<input type="hidden" id="problemAttrUnitId" name="problemAttrUnitId" value="${auditQuestion.problemAttrUnitId}" >
					<input name="problemAttrUnitName" id="problemAttrUnitName" value="${auditQuestion.problemAttrUnitName}" readonly="readonly" class="w70" check="NotEmpty">
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
						</ul>
				   </div>
			    </span><br>

				<label class="w20"><span class="required"> * </span>是否为重大问题:</label>
				<select name="isImportant" id="isImportant" class="selectpicker w70" >
				<option value="0" <c:if test="${'0' eq auditQuestion.isImportant}">selected</c:if> >否</option>
				    <option value="1" <c:if test="${'1' eq auditQuestion.isImportant}">selected</c:if> >是</option>
				</select>

				<label class="w20"><span class="required"> * </span>是否涉嫌舞弊:</label>
				<select name="isSuspected" id="isSuspected" class="selectpicker w70" >
					<option value="0" <c:if test="${'0' eq auditQuestion.isSuspected}">selected</c:if> >否</option>
					<option value="1" <c:if test="${'1' eq auditQuestion.isSuspected}">selected</c:if> >是</option>
				</select>

				<label class="w20"><span class="required"> * </span>是否问责:</label>
				<select name="isBlamed" id="isBlamed" class="selectpicker w70" >
					<option value="0" <c:if test="${'0' eq auditQuestion.isBlamed}">selected</c:if> >否</option>
					<option value="1" <c:if test="${'1' eq auditQuestion.isBlamed}">selected</c:if> >是</option>
				</select>

				<label class="w20"><span class="required"> * </span>审计建议:</label>
				<textarea rows="3" cols="5" class="w70" name="auditSuggest" id="auditSuggest" placeholder="请输入审计建议" maxlength="500" check="NotEmpty_string_500_._._._.">${auditQuestion.auditSuggest}</textarea><br>

				<label class="w20">是否需要整改:</label>
				<select name="isRectify" id="isRectify" class="selectpicker w70" >
					
					<option value="1" <c:if test="${'1' eq auditQuestion.isRectify}">selected</c:if> >是</option>
					<option value="0" <c:if test="${'0' eq auditQuestion.isRectify}">selected</c:if> >否</option>
				</select>

				<label class="w20">整改跟踪人:</label>
				<input class="w25" type="text" name="rectifyTrackName" id="rectifyTrackName" value="${auditQuestion.rectifyTrackName}" placeholder="请输入整改跟踪人"/><br>
				<input type="hidden" name="rectifyTrackId" id="rectifyTrackId" value="${auditQuestion.rectifyTrackId}"/>

				<label class="w20">整改对接人:</label>
				<input class="w25" type="text" name="rectifyPicker" id="rectifyPicker" value="${auditQuestion.rectifyPicker}" placeholder="请输入整改对接人"/><br>

				<label class="w20">整改责任人:</label>
				<input class="w25" type="text" name="rectifyResponseName" id="rectifyResponseName" value="${auditQuestion.rectifyResponseName}" placeholder="请输入整改责任人"/><br>

				<label class="w20">整改责任部门:</label>
				<input class="w25" type="text" name="rectifyResponseDept" id="rectifyResponseDept" value="${auditQuestion.rectifyResponseDept}" placeholder="请输入整改责任部门"/><br>

				<label class="w20">整改完成时限:</label>
				<input class="w25 time" type="text" name="rectifyTime" id="rectifyTime" value="${auditQuestion.rectifyTime}" readonly="readonly"/><br>

				<label class="w20"><span class="required"> * </span>审计发现问题类型(支持多选)</label>
				<%-- <input type="hidden" id="auditQuestionTypes" name="auditQuestionTypes" value="${auditQuestion.auditQuestionTypes}"> --%>
				<div class="model_content_div w70 ">
				<ul class="show">
					<li class="char_li">
						<input class="char_check" type="checkbox" name="auditQuestionTypes" value="0" <c:if test="${fn:contains(auditQuestion.auditQuestionTypes,'0')}">checked</c:if> id="model_0">
						<label class="char_label" for="model_0"></label>
						<span class="char_text">人力资源管理方面的问题</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="auditQuestionTypes" value="1" <c:if test="${fn:contains(auditQuestion.auditQuestionTypes,'1')}">checked</c:if> id="model_1">
						<label class="char_label" for="model_1"></label>
						<span class="char_text">采购管理方面的问题</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="auditQuestionTypes" value="2" <c:if test="${fn:contains(auditQuestion.auditQuestionTypes,'2')}">checked</c:if> id="model_2">
						<label class="char_label" for="model_2"></label>
						<span class="char_text">基础建设方面的问题</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="auditQuestionTypes" value="3" <c:if test="${fn:contains(auditQuestion.auditQuestionTypes,'3')}">checked</c:if> id="model_3">
						<label class="char_label" for="model_3"></label>
						<span class="char_text">项目投资(重组并购)方面的问题</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="auditQuestionTypes" value="4" <c:if test="${fn:contains(auditQuestion.auditQuestionTypes,'4')}">checked</c:if> id="model_4">
						<label class="char_label" for="model_4"></label>
						<span class="char_text">生产组织和销售方面的问题</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="auditQuestionTypes" value="5" <c:if test="${fn:contains(auditQuestion.auditQuestionTypes,'5')}">checked</c:if> id="model_5">
						<label class="char_label" for="model_5"></label>
						<span class="char_text">行政事务管理的问题</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="auditQuestionTypes" value="6" <c:if test="${fn:contains(auditQuestion.auditQuestionTypes,'6')}">checked</c:if> id="model_6">
						<label class="char_label" for="model_6"></label>
						<span class="char_text">外部环境和竞争的问题</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="auditQuestionTypes" value="7" <c:if test="${fn:contains(auditQuestion.auditQuestionTypes,'7')}">checked</c:if> id="model_7">
						<label class="char_label" for="model_7"></label>
						<span class="char_text">公司战略制定和执行方面问题</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="auditQuestionTypes" value="8" <c:if test="${fn:contains(auditQuestion.auditQuestionTypes,'8')}">checked</c:if> id="model_8">
						<label class="char_label" for="model_8"></label>
						<span class="char_text">财务管控方面问题（资金资产安全等）</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="auditQuestionTypes" value="9" <c:if test="${fn:contains(auditQuestion.auditQuestionTypes,'9')}">checked</c:if> id="model_9">
						<label class="char_label" for="model_9"></label>
						<span class="char_text">其他</span>
					</li>
				</ul>
				</div>
<!-- 				<select class="selectpicker w70 QTypes" multiple="multiple" style="height: 200px;" onchange="check_QTypes()">
				  <optgroup label="审计发现问题类型">
					<option value="0">人力资源管理方面的问题</option>
					<option value="1">采购管理方面的问题</option>
					<option value="2">基础建设方面的问题</option>
					<option value="3">项目投资(重组并购)方面的问题</option>
					<option value="4">生产组织和销售方面的问题</option>
					<option value="5">行政事务管理的问题</option>
					<option value="6">外部环境和竞争的问题</option>
					<option value="7">其他</option>
				  </optgroup>
				</select> -->
				
				

				<label class="w20"><span class="required"> * </span>风险动因类型(支持多选)</label>
				<%-- <input type="hidden" id="riskDriverTypes" name="riskDriverTypes" value="${auditQuestion.riskDriverTypes}"> --%>
				<div class="model_content_div w70 ">
				<ul class="show">
					<li class="char_li">
						<input class="char_check" type="checkbox" name="riskDriverTypes" value="0" <c:if test="${fn:contains(auditQuestion.riskDriverTypes,'0')}">checked</c:if> id="model_10">
						<label class="char_label" for="model_10"></label>
						<span class="char_text">规章制度缺失或不完善</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="riskDriverTypes" value="1" <c:if test="${fn:contains(auditQuestion.riskDriverTypes,'1')}">checked</c:if> id="model_11">
						<label class="char_label" for="model_11"></label>
						<span class="char_text">不相容岗位职责未分离,无监督牵制</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="riskDriverTypes" value="2" <c:if test="${fn:contains(auditQuestion.riskDriverTypes,'2')}">checked</c:if> id="model_12">
						<label class="char_label" for="model_12"></label>
						<span class="char_text">干部员工作风问题,职业道德和操守问题</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="riskDriverTypes" value="3" <c:if test="${fn:contains(auditQuestion.riskDriverTypes,'3')}">checked</c:if> id="model_13">
						<label class="char_label" for="model_13"></label>
						<span class="char_text">工作技能、业务能力和管理能力不足</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="riskDriverTypes" value="4" <c:if test="${fn:contains(auditQuestion.riskDriverTypes,'4')}">checked</c:if> id="model_14">
						<label class="char_label" for="model_14"></label>
						<span class="char_text">外部政策和环境影响</span>
					</li>
					<li class="char_li">
						<input class="char_check" type="checkbox" name="riskDriverTypes" value="5" <c:if test="${fn:contains(auditQuestion.riskDriverTypes,'5')}">checked</c:if> id="model_15">
						<label class="char_label" for="model_15"></label>
						<span class="char_text">其他</span>
					</li>
				</ul>
				</div>
				
<!-- 				<select class="selectpicker w70 RTypes" multiple="multiple" style="height: 200px;" onchange="check_RTypes()">
				  <optgroup label="风险动因类型">
					<option value="0">
						规章制度缺失或不完善
					</option>
					<option value="1">
						不相容岗位职责未分离,无监督牵制
					</option>
					<option value="2">
						干部员工作风问题,职业道德和操守问题
					</option>
					<option value="3">
						工作技能、业务能力和管理能力不足
					</option>
					<option value="4">
						外部政策和环境影响
					</option>
					<option value="5">
						其他
					</option>
				  </optgroup>
				</select> -->
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit" >保存</button>
				<button class="btn btn-default model model_btn_close">取消</button>
			</div>
		</div>
	 <input type="hidden" name="test"><!-- 解决IE无法保存问题 lcc -->
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
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		var timeoutId;
		$('#problemAttrUnitName').on('focus click',function(){
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
					$("#problemAttrUnitId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
					$("#problemAttrUnitName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});

		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});

		var check_QTypes = function(){
			var error = $('.QTypes')[0].value ==='';
			$('.QTypes')[error?'addClass':'removeClass']('error')
			return !error
		}
		var check_RTypes = function(){
			var error = $('.RTypes')[0].value ==='';
			$('.RTypes')[error?'addClass':'removeClass']('error')
			return !error
		}
		$("#commit").click(function(){

		    if(!vaild.all()) {
				return false;
			}
		if(!selectOne1()){
		  return false;
		}
		if(!selectOne2()){
		  return false;
		}
            var question = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/question/saveOrUpdate";
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

	  //必须选择一个
        function selectOne1() {
			var names = document.getElementsByName("auditQuestionTypes");          
            var flag = false ;//标记判断是否选中一个               
            for(var i=0;i<names.length;i++){
                if(names[i].checked){
                        flag = true ;
                        break ;
                 }
             }
             if(!flag){
                win.generalAlert("清至少勾选一项审计发现问题类型!");
                return false ;
             }
             return true;
		}
	  //必须选择一个
        function selectOne2() {
			var names = document.getElementsByName("riskDriverTypes");          
            var flag = false ;//标记判断是否选中一个               
            for(var i=0;i<names.length;i++){
                if(names[i].checked){
                        flag = true ;
                        break ;
                 }
             }
             if(!flag){
               win.generalAlert("清至少勾选一项风险动因类型!");
                return false ;
             }
              return true;
		}
	
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
/* 		    var qtype = $('#auditQuestionTypes').val().split(",");
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
			} */
		});

		//时间校验
		function checkTime(start,end) {
			var flag = true;
			if (start > end) {
				win.generalAlert("结束时间不应在开始时间之前!");
				flag = false;
				return flag;
			}
		};
		$('#rectifyTrackName').on('focus click',function(){
			mload("${pageContext.request.contextPath}//bimr/inside/searchPerson?personname=rectifyTrackName&personid=rectifyTrackId");
		});
		$(".char_text").on("click",function(){
			$(this).siblings(".char_label").click();
		})
	</script>
</html>
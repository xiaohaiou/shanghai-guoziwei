<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>新增审计项目</title>
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
	<c:if test="${type eq 'main'}">
		<c:choose>
				<c:when test="${op eq 'modify'}">
					<h4>
						<span class="glyphicon glyphicon-pencil"></span>修改审计项目
						<i class="iconfont icon-guanbi"></i>
					</h4>
				</c:when>
				<c:otherwise>
					<h4>
						<span class="glyphicon glyphicon-pencil"></span>新增审计项目
						<i class="iconfont icon-guanbi"></i>
					</h4>
				</c:otherwise>
		</c:choose>
	</c:if>	
	<c:if test="${type eq 'child'}">
		<c:choose>
				<c:when test="${op eq 'modify'}">
					<h4>
						<span class="glyphicon glyphicon-pencil"></span>修改审计子项目
						<i class="iconfont icon-guanbi"></i>
					</h4>
				</c:when>
				<c:otherwise>
					<h4>
						<span class="glyphicon glyphicon-pencil"></span>新增审计子项目
						<i class="iconfont icon-guanbi"></i>
					</h4>
				</c:otherwise>
		</c:choose>
	</c:if>	
	<form:form id="form2" modelAttribute="auditProject" method="post">
		<form:hidden path="id" value="${auditProject.id}"/>
		<form:hidden path="createPersonId" value="${auditProject.createPersonId}"/>
		<form:hidden path="createPersonName" value="${auditProject.createPersonName}"/>
		<form:hidden path="createDate" value="${auditProject.createDate}"/>
		<form:hidden path="isDel" value="${auditProject.isDel}"/>
		<form:hidden path="estatus" value="${auditProject.estatus}"/>
		<form:hidden path="status" value="${auditProject.status}"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<c:choose>
					<c:when test="${type eq 'main'}">
						<label class="w20">审计项目编号:</label>
						<input class="w25" type="text" name="auditProjectCode" id="auditProjectCode" placeholder="请输入审计项目编号" maxlength="50" value="${auditProject.auditProjectCode}" readonly="readonly" ${projectisstart?"disabled='disabled'":''}/>
						<label class="w20"><span class="required"> * </span>审计项目名称:</label>
						<input class="w25" type="text" name="auditProjectName" id="auditProjectName" placeholder="请输入审计项目名称" maxlength="50" value="${auditProject.auditProjectName}" check="NotEmpty_string_50_._._._." ${projectisstart?"disabled='disabled'":''}/>
						<label class="w20"><span class="required"> * </span>审计实施单位:</label>
						<span>
							<input type="hidden" id="auditImplDeptId" name="auditImplDeptId" value="${auditProject.auditImplDeptId}" >
							<input name="auditImplDeptName" id="auditImplDeptName" value="${auditProject.auditImplDeptName}" readonly="readonly" class="w70"check="NotEmpty" ${projectisstart?"disabled='disabled'":''}>
							<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
								<ul id="com_ztree" class="ztree">
								</ul>
						   </div>
					    </span><br>
					</c:when>
					<c:otherwise>
						<label class="w20">审计子项目编号:</label>
						<input class="w25" type="text" name="auditProjectCode" id="auditProjectCode" placeholder="请输入审计子项目编号" maxlength="50" value="${auditProject.auditProjectCode}"  readonly="readonly"/>
						<label class="w20"><span class="required"> * </span>审计子项目名称:</label>
						<input class="w25" type="text" name="auditProjectName" id="auditProjectName" placeholder="请输入审计子项目名称" maxlength="50" value="${auditProject.auditProjectName}" check="NotEmpty_string_50_._._._."/>
						<label class="w20"><span class="required"> * </span>审计子实施单位:</label>
						<span>
							<input type="hidden" id="auditImplDeptId" name="auditImplDeptId" value="${auditProject.auditImplDeptId}" >
							<input name="auditImplDeptName" id="auditImplDeptName" value="${auditProject.auditImplDeptName}" readonly="readonly" class="w70"check="NotEmpty">
							<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
								<ul id="com_ztree" class="ztree">
								</ul>
						   </div>
					    </span><br>
					</c:otherwise>
				</c:choose>
				
				<label class="w20"><span class="required"> * </span>项目类别:</label>
				<select name="isImportant" id="isImportant" class="selectpicker w25" ${projectisstart?"disabled='disabled'":''}>
					<option value="0" <c:if test="${'0' eq auditProject.isImportant}">selected</c:if> >年度计划内项目</option>
				    <option value="1" <c:if test="${'1' eq auditProject.isImportant}">selected</c:if> >年度计划外项目</option>
				     <option value="2" <c:if test="${'2' eq auditProject.isImportant}">selected</c:if> >上级交办项目</option>
				</select>
				<label class="w20"><span class="required"> * </span>审计项目负责人:</label>
				<input type="text" name="projectPrincipal" id="projectPrincipal" value="${auditProject.projectPrincipal}" class="w25" placeholder="请输入审计项目负责人" check="NotEmpty" readonly="true"/>
				<input type="hidden" name="projectPrincipalId" id="projectPrincipalId" value="${auditProject.projectPrincipalId}"/>
				<label class="w20"><span class="required"> * </span>是否子项目:</label>
				<c:choose>
					<c:when test="${type eq 'main'}">
						<select name="isChildren" id="isChildren" class="selectpicker w70" disabled="disabled">
							<option value="0" selected="selected">否</option>
							<option value="1">是</option>
						</select>
					</c:when>
					<c:otherwise>
						<select name="isChildren" id="isChildren" class="selectpicker w25" disabled="disabled">
							<option value="0">否</option>
							<option value="1" selected="selected">是</option>
						</select>
						<c:if test="${not empty requestScope.mainProject}">
							<label class="w20">关联主审计项目:</label>
							<input type="hidden" name="auditParentId" = id="auditParentId" value="${mainProject.id}"/>
							<input type="hidden" name="auditParentCode" = id="auditParentCode" value="${mainProject.auditProjectCode}"/>
							<input type="text" name="auditParentName" = id="auditParentName" value="${mainProject.auditProjectName}" class="w25" readonly="true"/>
						</c:if>
					</c:otherwise>
				</c:choose>

				<label class="w20"><span class="required"> * </span>审计项目目标:</label>
				
				<textarea rows="3" cols="5" class="w70" name="auditProjectGoal" id="auditProjectGoal" placeholder="请输入审计项目目标" maxlength="500" check="NotEmpty_string_500_._._._." ${projectisstart?"disabled='disabled'":''}>${auditProject.auditProjectGoal}</textarea><br>
				<label class="w20"><span class="required"> * </span>审计项目性质:</label>
				<select name="auditProjectProp" id="auditProjectProp" class="selectpicker w25"  ${projectisstart?"disabled='disabled'":''}>
					<c:forEach items="${requestScope.auditProjectProperty}" var="result">
						<option value="${result.id}" <c:if test="${auditProject.auditProjectProp == result.id}">selected="selected"</c:if>>${result.description}</option>
					</c:forEach>
				</select>
				<label class="w20"></label>
				<label class="w25"></label>
				<label class="w20"><span class="required"> * </span>被审计单位:</label>
				 <span>
					<input type="hidden" id="auditDepted" name="auditDepted" value="${auditProject.auditDepted}">
					<input name="auditDeptedName" id="auditDeptedName" value="${auditProject.auditDeptedName}" readonly="readonly" class="w70" check="NotEmpty" ${projectisstart?"disabled='disabled'":''}>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree1" class="ztree">
						</ul>
				   	</div>
			    </span> 
			  	<div id="showauditDeptedPerson" style="">
			  	<label class="w20"><span class="required"> * </span>被审计人员名称:</label>
				<input class="w25" type="text" name="auditDeptedPerson" id="auditDeptedPerson" value="${auditProject.auditDeptedPerson}" placeholder="请输入被审计人员名称" check="NotEmpty_string_._._._._." ${projectisstart?"disabled='disabled'":''}/>
				<label class="w20">被审计人员员工号:</label>
				<input class="w25" type="text" name="auditDeptedPersonId" id="auditDeptedPersonId" value="${auditProject.auditDeptedPersonId}" placeholder="请输入被审计人员员工号" check="._int_12_._._._." ${projectisstart?"disabled='disabled'":''}/><br>
			  	<label class="w20"><span class="required"> * </span>被审计人员职务:</label>
				<input class="w25" name="auditDeptedPersonPost" id="auditDeptedPersonPost" check="NotEmpty_string_._._._._." value="${auditProject.auditDeptedPersonPost}" ${projectisstart?"disabled='disabled'":''}/>
			  	</div>
			    <label class="w20">整改跟踪人:</label>
				<input class="w25" type="text" name="rectifyTrackName" id="rectifyTrackName" value="${auditProject.rectifyTrackName}" placeholder="请输入整改跟踪人" /><br>
				<input type="hidden" name="rectifyTrackId" id="rectifyTrackId" value="${auditProject.rectifyTrackId}"/>
				<%--<input type="text" name="auditDeptedName" id="auditDeptedName" value="${auditProject.auditDeptedName}" class="w25"check="NotEmpty_string_50_._._._."/>--%>
				<label class="w20"><span class="required"> * </span>审计项目开始日期:</label>
				<input type="text" name="auditStartDate" id="auditStartDate" value="${auditProject.auditStartDate}" readonly="readonly" class="w25 time" check="NotEmpty" ${projectisstart?"disabled='disabled'":''}/>
				<label class="w20"><span class="required"> * </span>审计项目结束日期:</label>
				<input type="text" name="auditEndDate" id="auditEndDate" value="${auditProject.auditEndDate}" readonly="readonly" class="w25 time" check="NotEmpty" ${projectisstart?"disabled='disabled'":''}/><br>
				<label class="w20"><span class="required"> * </span>审计项目内容:</label>
				<textarea rows="3" cols="5" class="w70" name="auditProjectContent" id="auditProjectContent" placeholder="请输入审计项目内容" maxlength="500"check="NotEmpty_string_500_._._._." ${projectisstart?"disabled='disabled'":''}>${auditProject.auditProjectContent}</textarea><br>
				<label class="w20">备注:</label>
				<textarea rows="3" cols="5" class="w70" name="remark" id="remark" placeholder="请输入备注" maxlength="500" ${projectisstart?"disabled='disabled'":''}>${auditProject.remark}</textarea><br>
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

		/* var auditProjectCode = document.getElementById("auditProjectCode");
		vaild.bind(auditProjectCode,function(value){
			var reg = /^[0-9a-zA-Z-]*$/;
			var max_len = 20;
			var vaild_info = [{
				vaild:value!=='',
				msg:'必填'
			},{
				vaild:value.length<=max_len,
				msg:'不超过'+max_len+'个字符'
			},{
				vaild:reg.test(value),
				msg:'数字,字母和-的组合'
			}]
			return vaild_info
		}); */

		var timeoutId;
		$('#auditImplDeptName').on('focus click',function(){
			$(this).next('.com_ztree_box').css('display','block')
		}).parent().on('mouseenter',function(){
			clearTimeout(timeoutId)
		}).on('mouseleave',function(){
			clearTimeout(timeoutId)
			timeoutId = setTimeout(function(el){
				$(el).find('.com_ztree_box').css('display','none')
			},3e2,this);
		});
		
		
		$('#auditDeptedName').on('focus click',function(){
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
				enable:true,
				chkStyle: "checkbox",
				chkboxType: {  "Y" : "", "N" : ""  }
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			}, 
            callback: {   
                onClick: function (e, treeId, treeNode, clickFlag) {   
                	zTreeObj.checkNode(treeNode, !treeNode.checked, true);   
                }   
            }, 
		};
		
		var zTreeObj1;
		var com_ztree_setting1 = {
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
			},
			 
		};

		
		$(function () {
			  //所有企业数据	
		    var com_data = ${allCompanyTree};
			zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
			zTreeObj1 = $.fn.zTree.init($("#com_ztree1"), com_ztree_setting1, com_data);
			
			
		   
			//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
			var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var treeObj1 = $.fn.zTree.getZTreeObj("com_ztree1");
			var nodes = treeObj.getNodes();
			var nodes1 = treeObj1.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
			var childrenNodes = treeObj.transformToArray(nodes);
			var childrenNodes1 = treeObj1.transformToArray(nodes1);
			if(childrenNodes[0]!=null){
				treeObj.expandNode(childrenNodes[0],true);
			}
			if(childrenNodes1[0]!=null){
				treeObj1.expandNode(childrenNodes1[0],true);
			}
		});
		
		
		
		
		
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					var nodes=zTreeObj.getCheckedNodes(true);
					var id='';
					var name='';
					for(var i=0;i<nodes.length;i++){
						id+=nodes[i].id + ",";
						name+=nodes[i].name + ",";
					}
					$("#auditImplDeptId").val(id.substring(0,id.length-1));
					$("#auditImplDeptName").val(name.substring(0,name.length-1));
				}
				vaild.vaild($("#auditImplDeptName")[0]);
		   	});
		});
		
		$("#com_ztree1").click(function(){
			setTimeout(function(){
			
				if($.fn.zTree.getZTreeObj("com_ztree1").getSelectedNodes()[0]) {
				
					$("#auditDepted").val($.fn.zTree.getZTreeObj("com_ztree1").getSelectedNodes()[0].id);
					$("#auditDeptedName").val($.fn.zTree.getZTreeObj("com_ztree1").getSelectedNodes()[0].name); 
					
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

		/*$("#isChildren").change(function(){
		  if(this.value == 0){//否
			  $('.auditPro').attr("disabled","disabled");
			  $('.auditPro').val('');
		  }else{//是
		      $('.auditPro').removeAttr("disabled");
		  }
		});*/

		$("#commit").click(function(){
		    if(!vaild.all()) {
				return false;
			}
			$("#auditProjectCode").attr("disabled",false);
			$('#auditProjectName').attr("disabled",false); 
			$('#isImportant').attr("disabled",false); 
			$('#auditImplDeptName').attr("disabled",false); 
			
			$('#auditProjectGoal').attr("disabled",false); 
			$('#auditProjectProp').attr("disabled",false); 
			$('#auditDeptedName').attr("disabled",false); 
			
			$('#rectifyTrackName').attr("disabled",false); 
			$('#auditStartDate').attr("disabled",false); 
			$('#auditEndDate').attr("disabled",false); 
			$('#auditProjectContent').attr("disabled",false); 
			$('#remark').attr("disabled",false); 
			$('#auditDeptedPersonPost').attr("disabled",false);
			$('#auditDeptedPerson').attr("disabled",false);
			$('#auditDeptedPersonId').attr("disabled",false); 
			var startDate = $("#auditStartDate").val();
			debugger;
			var endDate = $("#auditEndDate").val();
			if(checkTime(startDate,endDate)){
			    var ety = new FormData($("#form2")[0]);
			    var url = "${pageContext.request.contextPath}/bimr/inside/saveOrUpdate";
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

			var auditPro = $('.auditPro').val();
			if(auditPro != null && auditPro != ''){
                $('.auditPro').removeAttr("disabled");
            }
		});
		//时间校验
		function checkTime(start,end) {
			var flag = true;
			if (start > end) {
				win.generalAlert("审计项目结束日期必须大于审计项目开始日期!");
				flag = false;
			}
			return flag;
		};
		$('#rectifyTrackName').on('focus click',function(){
			mload("${pageContext.request.contextPath}//bimr/inside/searchPerson?personname=rectifyTrackName&personid=rectifyTrackId");
		});
		$('#projectPrincipal').on('focus click',function(){
			mload("${pageContext.request.contextPath}//bimr/inside/searchPerson?personname=projectPrincipal&personid=projectPrincipalId");
		});
		/* $('#auditDeptedPerson').on('focus click',function(){
			mload("${pageContext.request.contextPath}//bimr/inside/searchPerson?personname=auditDeptedPerson&personid=auditDeptedPersonId");
		}); */
		
		$(function(){
			if($("#auditProjectProp").val()!=80029){
			$("#showauditDeptedPerson").hide();
			$("#auditDeptedPerson").removeAttr('check');
			$("#auditDeptedPersonPost").removeAttr('check');
			}
			if('${entity.auditDeptedPerson}'!='')
				$("#auditDeptedPerson").val('${entity.auditDeptedPerson}');
			if('${entity.auditDeptedPersonId}'!='')	
				$("#auditDeptedPersonId").val('${entity.auditDeptedPersonId}');
			if('${entity.auditDeptedPersonPost}'!='')	
				$("#auditDeptedPersonPost").val('${entity.auditDeptedPersonPost}');	
			$("#auditProjectProp").change(function(){
				var value=$(this).val();
				if(value==80029){
				$("#showauditDeptedPerson").show();
				$("#auditDeptedPerson").attr('check','NotEmpty');
				$("#auditDeptedPersonPost").attr('check','NotEmpty');
				}else{
					$("#showauditDeptedPerson").hide();
					$("#auditDeptedPerson").removeAttr('check');
					$("#auditDeptedPersonPost").removeAttr('check');
				}
			});
			$("#investigationType").change();
		})
	</script>
</html>
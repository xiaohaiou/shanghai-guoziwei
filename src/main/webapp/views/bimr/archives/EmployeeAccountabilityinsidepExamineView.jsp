<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看审计项目</title>
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
			<span class="glyphicon glyphicon-pencil"></span>审计项目问责查看
			<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
		  <h4><span style="font-weight: 1px;">审计项目</span></h4>
			<div class="model_part">
				<input type="hidden" value="${entity.id}" id="projectId"/>
				<label class="w20">审计项目编号:</label>
				<label class="w25 setleft">${entity.auditProjectCode}</label>
				<label class="w20">审计项目名称:</label>
				<label class="w25 setleft">${entity.auditProjectName}</label>
				
				<label class="w20">审计实施单位:</label>
				<label class="w25 setleft">${entity.auditImplDeptName}</label>
				<label class="w20">整改跟踪人:</label>
				<label class="w25 setleft">${entity.rectifyTrackName}</label>
				<label class="w20">是否重点项目:</label>
				<label class="w25 setleft">${entity.isImportant==0?"否":"是"}</label>

				<label class="w20">审计项目负责人:</label>
				<label class="w25 setleft">${entity.projectPrincipal}</label>
				<label class="w20">是否子项目:</label>
				<label class="w25 setleft">${entity.isChildren==0?"否":"是"}</label>
				<c:if test="${type eq 'child'}">
				    <label class="w20">关联大审计项目:</label>
       				<label class="w70 setleft">
       				    <c:if test="${not empty parent}">
       				         ${parent.auditProjectName}
       				    </c:if>
       				</label><br>
				</c:if>
				<label class="w20">审计项目目标:</label>
				<label class="w25 setleft">${entity.auditProjectGoal}</label>
				
				<label class="w20">审计项目性质:</label>
				<label class="w25 setleft">
					<c:if test="${entity.auditProjectProp == 80026}">
						风险导向审计
					</c:if>
					<c:if test="${entity.auditProjectProp == 80027}">
						风险管理审计
					</c:if>
					<c:if test="${entity.auditProjectProp == 80028}">
						内部控制评价
					</c:if>
					<c:if test="${entity.auditProjectProp == 80029}">
						经济责任审计
					</c:if>
					<c:if test="${entity.auditProjectProp == 80030}">
						企业巡视
					</c:if>
					<c:if test="${entity.auditProjectProp == 80031}">
						调研管理
					</c:if>
				</label>
				<label class="w20">被审计单位:</label>
				<label class="w25 setleft">${entity.auditDeptedName}</label>
				
				<label class="w20">审计项目开始日期:</label>
				<label class="w25 setleft">${entity.auditStartDate}</label>
				<label class="w20">审计项目结束日期:</label>
				<label class="w25 setleft">${entity.auditEndDate}</label>
				
				<label class="w20">审计项目内容:</label>
				<label class="w70 setleft">${entity.auditProjectContent}</label>
				<br>
				
				<label class="w20">备注:</label>
				<label class="w70 setleft">${entity.remark}</label>
			</div>
			<h4><span style="font-weight: 1px;">审计项目发现问题清单</span></h4>
			<div class="tablebox">
				<table style="width: 100%;">
					<tr class="table_header">
						<th style="width:8%;">序号</th>
						<th style="width:20%;">问题标题</th>
						<th style="width:20%;">审计建议</th>
						<th style="width:20%;">发现问题类型</th>
						<th style="width:20%;">成因分析类型</th>
					</tr>
					<c:if test="${not empty problem_list }">
						<c:forEach items="${problem_list}" var="p" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>${p.problemTopic}</td>
								<td>${p.auditSuggest}</td>
								<td>${p.auditQuestionTypes}</td>
								<td>${p.riskDriverTypes}</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<h4><span style="font-weight: 1px;">审计问责人员</span></h4>
			<div class="tablebox">
				<table style="width: 100%;">
					<tr class="table_header">
						<th >序号</th>
						<th >项目编号</th>
						<th >项目名称</th>
						<th >关联问题标题</th>
						<th >被问责人员</th>
						<th >状态</th>
						<th >操作</th>
					</tr>
					<c:if test="${not empty accountable_list }">
						<c:forEach items="${accountable_list}" var="p" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>${p.projectCode}</td>
								<td>${p.projectName}</td>
								<td>${p.questionTopic}</td>
								<td>${p.accountabilityPersion}</td>
								
								<td><c:if test="${p.status==1}">未上报</c:if><c:if test="${p.status==2}">已上报</c:if><c:if test="${p.status==4}">已退回</c:if><c:if test="${p.status==3||p.status==5}">已审核</c:if></td>
								
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			
			<c:if test="${!empty examineList}">
					<div class="model_part">
						<table>
						<caption>历史审核记录</caption>
						<tr>
							<th width="15%">审核时间</th>
							<th width="10%">审核人</th>
							<th width="10%">审核结果</th>
							<th width="60%">审核意见</th>
						</tr>
							<c:forEach items="${examineList}" var="examine" varStatus="status">
								<tr>
									<td>${examine.createDate }</td>
									<td>${examine.createPersonName }</td>
									<c:if test="${examine.examresult eq '50116'}">
										<td>审核通过</td>
									</c:if>
									<c:if test="${examine.examresult eq '50117'}">
										<td>审核不通过</td>
									</c:if>
									<td>${examine.examinestr }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
			
			
			<c:if test="${empty showtype}">
				<div class="model_part">
					<label class="w20" style="vertical-align: top;"><span class="required"> * </span>审核意见:</label>
					<textarea  rows="3" cols="50" name="examStr" id="examStr" check="NotEmpty_string_50_._._._."></textarea>
				</div>
				<div class="model_btn">
					<input type="hidden" value="${entity.id}" id="projectId"/>
					<button class="btn btn-primary model_btn_ok" id="commit-1">通过</button>
					<button class="btn btn-primary model_btn_ok" id="commit-2">退回</button>
					
				</div>
			</c:if>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
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
        function del(id,type){
            win.confirm('确定要删除？',function(r){
                if(r){
        		var _p_url = "${pageContext.request.contextPath}/bimr/question/delete";
                var _d_url = "${pageContext.request.contextPath}/bimr/difficult/delete";
                    var _url = "";
                    if (type == 'p'){
                        _url = _p_url;
                    }else if(type == 'd'){
                        _url = _d_url;
                    }
                    $.post(_url,{id:id}, function(data) {
                        var r = data.result;
                        if(r == 1) {
                            win.successAlert(data.message,function(){
                                window.location.reload();
                            });
                        }else{
                            win.errorAlert(data.message,function(){
                                window.location.reload();
                            });
                        }
                    });
			}
        })
        };
        //审核通过按钮.
		$("#commit-1").click(function () {
            if(!vaild.all()) {
                return false;
            }
		    var _url = "${pageContext.request.contextPath}/bimr/archives/companyTree/saveEmployeeAccountabilityInsidepExamine";
			var projectId=$('#projectId').val();
            var op = $('#examStr').val();
            var examResult=50116;
		    $.ajax({  
			     url : _url,  
			     type : "POST",  
			     data:{"projectId":projectId,"examStr" : op, "examResult" : examResult},
		         async: false,  
		         cache: false,  
		         dataType:"text",  
			     success : function(data) {
				   if(data == "success"){
						win.successAlert("审核通过！",function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },   
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
					 win.successAlert("审核失败！",function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
				 },
			}); 
			return false;
		});
         
		//审核退回按钮.
		$("#commit-2").click(function () {
			if(!vaild.all()) {
                return false;
            }
			var _url = "${pageContext.request.contextPath}/bimr/archives/companyTree/saveEmployeeAccountabilityInsidepExamine";
			var projectId=$('#projectId').val();
            var op = $('#examStr').val();
            var examResult=50117;
		    $.ajax({  
			     url : _url,  
			     type : "POST",  
			     data:{"projectId" : projectId, "examStr" : op, "examResult" : examResult},
		         async: false,  
		         cache: false,  
		         dataType:"text",  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert("审核不通过！",function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },   
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
					 win.successAlert("审核失败！",function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
				 },
			}); 
			return false;
		});
</script>
</html>
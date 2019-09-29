<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>采购数据新增、修改页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
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
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改报表组数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增报表组数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	
	</c:choose>

		<form:form id="form2" modelAttribute="entityview" >
		
				<form:hidden path="group.id"/>
				<div class="label_inpt_div">
				<div class="model_part">
					<label class="w20"><span class="required"> * </span>报表组种类:</label>
					<span class="w25">
					<select class="selectpicker " name="group.nameID" id="nameIdSelect" onchange="nameIDChange();">	
							<c:forEach items="${requestScope.reportgroupkind}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.group.nameID == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
				</span>
				<label class="w20"><span class="required"> * </span>报表组属性:</label>
					<span class="w25">
					<select class="selectpicker " name="group.type">	
							<c:forEach items="${requestScope.reportgrouptype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.group.type == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
				</span>
				<label class="w20"><span class="required"> * </span>年度:</label>
				<span class="w25">
					<form:input class=" time" id="grouptime" path="group.time" readonly="true"/>
					<form:hidden path="group_itemstr" id="group_itemstr"/>
				</span>
				<div id="hesuan" style="display:none;">
					<label class="w20"><span class="required"> * </span>核算月:</label>
					<span class="w25">
						<select class="selectpicker " name="group.month" id="groupMonth">	
							<option value="" >--请选择--</option>
							<c:forEach begin="1" end="12" var="m">
								<option value="${m}" <c:if test="${entityview.group.month == m}">selected="selected"</c:if> >${m}</option>
							</c:forEach>
					</select>
					</span>
				</div>
				<div>
				<label style="text-align: right;" class="w20">导出excel文件:</label>
				<input type="file" class="w75 inlineblock" style="display:inline" name="reportGroupFile" >
				<label style="text-align: right;" class="w20">导入excel文件:</label>
				<input type="file" class="w75 inlineblock" style="display:inline"  name="reportGroupExportFile" >
				</div>
				</div>
				
			
			<h3 class="data_title1">报表明细</h3>
			
			<div class="model_part">
			<div class="tablebox">
					<table class="row" id="grouptable">
						<tr class="table_header">
							<th style="min-width:50px">报表频度</th>
							<th style="min-width: 300px">报表名称</th>
							
							<th style="min-width: 50px">操作</th>
						</tr>
						<tr class="expmale">
							<td style="display: none">
								<input type="hidden" class="reportid">
							</td>
							
							<td>
								<select class="selectpicker reportformfre">
									<c:forEach items="${requestScope.reportformfre}" var="result">
										<option value="${result.id }"  >${result.description }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select class="selectpicker reportformkind" >
									<c:forEach items="${requestScope.reportformkind}" var="result">
										<option value="${result.id }"  >${result.description }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<i class='del btn'>×</i>
							</td>
						</tr>
						<c:if test="${ !empty entityview.group_item }">
						<c:forEach items="${ entityview.group_item}" var="itemsys" varStatus="status">
							<tr >
								
								<td style="display: none">
									<input type="hidden" value=${ itemsys.id} class="reportid">
								</td>
								
								<td>
									<select class="selectpicker reportformfre">
										<c:forEach items="${requestScope.reportformfre}" var="result">
											<option value="${result.id }"  <c:if test="${itemsys.fre == result.id}">selected="selected"</c:if>>${result.description }</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<select class="selectpicker reportformkind">
										<c:forEach items="${requestScope.reportformkind}" var="result">
											<option value="${result.id }"  <c:if test="${itemsys.formkind == result.id}">selected="selected"</c:if>>   ${result.description }</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<i class='del btn'>×</i>
								</td>
							</tr>
						</c:forEach>
						</c:if>
					</table>
			</div>
		
			<div class="row">
				<div class="btn btn-default new"
					style="display:inline-block;margin-left:6.7%;width:10%;line-height:1;">新增报表</div>
			</div>
			</div>
			
			
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
			</div>
			<input type="hidden" name="fdsfsdfsdfs">  
	</form:form>
	
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">
			
		var start = {
			format:"YYYY"
		}
		$('input.time').jeDate(start);
		
		//判断是否是核算报表组
		nameIDChange();
		function nameIDChange(){
			if($("#nameIdSelect option:selected").val() == 52002){
				$("#hesuan").show();
			}else{
				$("#hesuan").hide();
			}
		}
		
		var groupitem=[];
		function checkcommit()
		{
			groupitem=[];
			if($("#reportname").val()=="")
			{
				win.errorAlert("报表组名称不能为空");
				return false;
			}
			if($("#grouptime").val()=="")
			{
				win.errorAlert("使用时间不能为空");
				return false;
			}
			if($("#nameIdSelect option:selected").val() == 52002 && $("#groupMonth").val() == "" ){
				win.errorAlert("核算月不能为空");
				return false;
			}
			
			
			var size=0;
			$.each($("table tbody tr"),function (index,item)
			{
				if(index!=0)
				{
					var obj= {};
					obj.id=$(item).find(".reportid").val();
					obj.formkind=$(item).find(" .reportformkind").val();
					obj.fre=$(item).find(" .reportformfre").val();
					groupitem.push(obj);
					size++;
				}
			})
			
			//报表是否重复
			var flag=false
			if(size>0)
			{
				for(var i=0;i<size;i++)
				{
					var obj=groupitem[i]
					for(var j=i+1;j<size;j++)
					{
						var obj2=groupitem[j];
						if(obj.formkind==obj2.formkind && obj.fre==obj2.fre)
						{
							flag=true;
							break;
						}	
					
					}
					if(flag==true)
						break;
				}
				if(flag)
				{
					win.errorAlert("该报表组内有重复的报表,不能保存");
					return false;
				}
				$("#group_itemstr").val(JSON.stringify(groupitem))
			}
			else
				$("#group_itemstr").val("")
			return true;
		}
				
		$("#commit-1").click(function(){
		
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{	
				$.unblockUI();
				return false;
			}
			
			var url = "${pageContext.request.contextPath}/reportgroup/save";
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     $.unblockUI();
			     var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('保存成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
						{	
							win.errorAlert(commitresult.exceptionStr);
							
						}
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
			
			
			return false;
		})


	
		var example = $(".expmale");
		example.removeClass("example").remove();
		$(".btn.new").click(function(){
			$("table tbody").append(example.clone());
		});
		$("body").on("click","i.del.btn",function(){
			$(this).closest("tr").remove();
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
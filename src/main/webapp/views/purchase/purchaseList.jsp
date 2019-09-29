<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>流程处理页面</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>流程处理查看</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			
			<span class="col-md-4">
			<span class="search_title">年份：</span>
					<input  type="text" name="year" value="${entityview.year }" readonly="true" class="time" />
			</span>
			<span class="col-md-4">
			<span class="search_title">事件类型：</span>
					<select  name="season" class="selectpicker"  >
					<option value="">全部</option>
						<c:forEach items="${requestScope.seasontype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.season == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span>
			<span class="col-md-4">
			<span class="search_title">审核状态：</span>
					<select  name="status" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.examstatustype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.status == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span><br><br>
			<div class="form_div col-md-12">
						<c:if test="${fn:contains(gzwsession_code,',bimWork_cgsjsb_query,')==true }">
							<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
						</c:if>
						<c:if test="${fn:contains(gzwsession_code,',bimWork_cgsjsb_new,')==true }">
							<input type="button" value="新增" class="form_btn" id="addbtn" onclick="mload('${mapurl}/newmodify')">
						</c:if>
						
						<%-- <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="mload('${mapurl}/export')">
					 --%>	<%-- <c:if test="${fn:contains(rolePagecodeNums,'sys_add')==true}"> --%>
							<%-- <input type="button" value="新增" class="form_btn" id="model_add" onclick="mload('${pageContext.request.contextPath}/reportgroup/newmodify')"> --%>
						<%-- </c:if> --%>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>时间</th>						
						<th>事件名称</th>
						<th>事件类型</th>
						<th>事件处理状态</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					  <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber+ status.index + 1 }
								</td>
					
								<td>
									${sys.year }${sys.season }
								</td>
								<td style="text-align: right">
									${sys.supportamount }
								</td>
								<td style="text-align: right">
									${sys.cumulativepurchaseamount }
								</td>
								<td style="text-align: right">
									${sys.rate }%
								</td>
								<td style="text-align: center">
									${sys.createPersonName }
								</td>
								<td style="text-align: center">
									${sys.reportPersonName }
								</td>
								<td style="text-align: center">
									${sys.auditorPersonName }
								</td>
								<td style="text-align: center">
									<c:if test="${ sys.status==50112}">
										<span style="color:#3366ff">${sys.statusName }</span>
									</c:if>
									<c:if test="${ sys.status==50113}">
										<span style="color:#ff9933">${sys.statusName }</span>
									</c:if>
									<c:if test="${ sys.status==50114}">
										<span style="color:#ff399d">${sys.statusName }</span>
									</c:if>
									<c:if test="${ sys.status==50115}">
										<span style="color:#00cc66">${sys.statusName }</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_cgsjsb_query,')==true }">
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=modify&id=${sys.id}',${sys.id })" >查看</a>
									</c:if>
									<c:if test="${sys.status==50114 || sys.status==50112 }">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_cgsjsb_edit,')==true }">
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/newmodify?op=modify&id=${sys.id}',${sys.id })" >修改</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_cgsjsb_report,')==true }">
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/postexam?op=report&id=${sys.id}',${sys.id })">上报</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_cgsjsb_delete,')==true }">
											<a href="javascript:void(0)" onclick="del('${sys.id}')" >删除</a>
										</c:if>
									</c:if>
									
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="10" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
	
		function checkdata(url,id)
		{
		    var commitresult="";
			var checkurl = "${mapurl}/hasCheck?id=" + id;
			
			$.ajax({
			  type: 'POST',
			  url: checkurl,
			 // async:false,
			  success: function (data){
			  		commitresult=JSON.parse(data);
			  		if(commitresult.result == true){
						mload(url);
					}
					else
						win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
	
			  }
			});
		}
		

		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		)
	
	
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}

		function del(id){
			win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${mapurl}/delete?id=" + id;
					$.post(url, function(data) {
					var commitresult=JSON.parse(data);
						if(commitresult.result == true){
							win.successAlert('删除成功！');
							_query();
						}
						else
							win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
						
					});
				}
			});
		}

		function exam(id){
			win.confirm('确定要上报？',function(r){
				if(r){
					var url = "${mapurl}/saveandreport?id=" + id;
					$.post(url, function(data) {
						
						var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('上报成功！',function(){
								_query();
							});
						else
							{
								win.errorAlert(commitresult.exceptionStr);
							}
					});
				}
			});
		}
		
	</script>
</html>
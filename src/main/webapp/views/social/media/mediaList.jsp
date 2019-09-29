<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>征信信息采集填报</title>
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
		<h4>征信信息采集填报</h4>
		<div class="table_content">
		
		<form id="form1"  class="row" >
			<span class="col-md-4">年份：
				<input  type="text" name="year" value="${entityview.year }" readonly="true" class="time" />
			</span>
			<span class="col-md-4">月份：
				<select  name="month" class="selectpicker"  >
					<option value="">全部</option>
					<c:forEach var= "temp" begin= "1" step= "1" end= "12">
						<option value="${temp}" <c:if test="${entityview.month == temp}">selected="selected"</c:if>>
							${temp}
						</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">审核状态：
				<select  name="status" class="selectpicker" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.examStatus}" var="result">
						<option value="${result.id }" <c:if test="${entityview.status == result.id}">selected="selected"</c:if>>
							${result.description}
						</option>
					</c:forEach>
				</select>
			</span><br><br>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_mediaSb_query,')==true }">
					<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_mediaSb_new,')==true }">
					<input type="button" value="新增" class="form_btn" id="addbtn" onclick="mload('${mapurl}/newmodify')">
				</c:if>
				<%-- <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="mload('${mapurl}/export')"> --%>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>时间</th>
						<th>征信信息采集数据1</th>
						<th>征信信息采集数据2</th>
						<th>征信信息采集数据3</th>
						<th>征信信息采集数据4</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="resultList" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber+ status.index + 1 }
								</td>
								<td style="text-align: center;">
									${resultList.year }-${resultList.month }
								</td>
								<td style="text-align: right;">
									${resultList.meetCount }次
								</td>
								<td style="text-align: right;">
									${resultList.mediaPopulation }人
								</td>
								<td style="text-align: right;">
									${resultList.mediaAmount }家
								</td>
								<td style="text-align: left;">
									${resultList.mediaClassName}
								</td>
								<td style="text-align: center;">
									${resultList.createPersonName }
								</td>
								<td style="text-align: center;">
									${resultList.reportPersonName }
								</td>
								<td style="text-align: center;">
									${resultList.auditorPersonName }
								</td>
								<td>
									<c:if test="${ resultList.status==50112}">
										<span style="color:#3366ff">未上报</span>
									</c:if>
									<c:if test="${ resultList.status==50113}">
										<span style="color:#00a59d">待审核</span>
									</c:if>
									<c:if test="${ resultList.status==50114}">
										<span style="color:#ff399d">已退回</span>
									</c:if>
									<c:if test="${ resultList.status==50115}">
										<span style="color:#00cc66">已审核</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_mediaSb_query,')==true }">
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=modify&id=${resultList.id}',${resultList.id })" >查看</a>
									</c:if>
									<c:if test="${resultList.status==50114 || resultList.status==50112 }">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_mediaSb_edit,')==true }">
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/newmodify?op=modify&id=${resultList.id}',${resultList.id })" >修改</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_mediaSb_report,')==true }">
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/postexam?op=report&id=${resultList.id}',${resultList.id })">上报</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_mediaSb_delete,')==true }">
											<a href="javascript:void(0)" onclick="del('${resultList.id}')" >删除</a>
										</c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="11" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="/views/system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
	<script type="text/javascript">
		function checkdata(url,id){
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
					} else {
						win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
					}
				}
			});
		}

		$(' input.time').jeDate({
				format:"YYYY"
			}
		);
	
		function _query(){
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
						} else {
							win.errorAlert('该数据已被修改，不能删除！',function(){window.location.reload(true);});
						}
					});
				}
			});
		}
	</script>
</html>
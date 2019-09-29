<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>日志中心</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" media="screen"/>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
		
		<!--时间样式  -->
		<link rel="stylesheet" media="screen" href="<c:url value="/settingCenter/css/bootstrap-datetimepicker.min.css"/>">
		
		<style>
			#form1 .control-label{
				display: inline-block;
    			height: 2.5em;
    			line-height: 2em;
			}
			#form1,#form1 div,#form1 label{
				margin-bottom:0;
			}
			#form1 label{
				font-weight: normal;
			}
			#form1 span{
				padding-left:0;
				padding-right:0;
			}
			#form1 .input-group-addon{
				padding-left: 1em;
				padding-right: 1em;
			}
			#form1>.input_lest {
			    text-align: left;
			    margin: 10px 0px;
			    font-size: 16px;
			}
			.date_div{
				width:33%;
			}
			#form1 div{
				text-align:left;
			}
			#form1 .input-group-addon{
				padding: 0 10px;
			}
			.tablebox table{
			    table-layout: fixed;
			}
			.tablebox table td{
		        line-height: 2em;
		        padding: 5px;
		        word-break: break-all;
			}
			.tablebox table th:last-child{
				width: 50%;
			}
			.tablebox table th:first-child{
				width: 6em;
			}
			.tablebox tr td:last-child{
				width: 50%;
				overflow:hidden;
				text-overflow: ellipsis;
			    white-space: nowrap;
			    padding: 0 20px;
			}
			/* .description{
				display: inline-block;
				width: 50%;
				text-overflow: ellipsis;
			    white-space: nowrap;
		        
			} */
			body{
				overflow: auto;
			}
		</style>
	</head>
	<body>
		<h4>日志中心</h4>
		<div class="table_content">
			<form id="form1" class="row" action="${pageContext.request.contextPath}/portal/log/_query">
				<div class="form-group col-md-12 row input_list">
					<div class="form-group date_div">
						<label for="" class="col-md-2 control-label">用户账号：</label>
						<input type="text" name="name" value="${portalLog.name }">
					</div>
					<div class="form-group date_div">
						<label for="" class="col-md-2 control-label">用户姓名：</label>
						<input type="text" name="userName" value="${portalLog.userName }">
					</div>
					<div class="form-group date_div">
			        	<label for="" class="col-md-2 control-label">访问类型：</label>
						<select name="model">
							<option value="">---请选择---</option>
							<option value="com.softline.util.XssHttpServletRequestWrapper" <c:if test="${portalLog.model eq 'com.softline.util.XssHttpServletRequestWrapper' }">selected="selected"</c:if>>访问系统</option>
							<option value="java.lang.String" <c:if test="${portalLog.model eq 'java.lang.String' }">selected="selected"</c:if>>登录系统</option>
							<option value="APP登入" <c:if test="${portalLog.model eq 'APP登入' }">selected="selected"</c:if>>APP访问</option>
							<option value="visitApp" <c:if test="${portalLog.model eq 'visitApp' }">selected="selected"</c:if>>APP宣传监控</option>
							<option value="扫码登录" <c:if test="${portalLog.model eq '扫码登录' }">selected="selected"</c:if>>扫码登录</option>
						</select>
					</div>
				</div>
				<div class="form-group col-md-12 row">
					<div class="form-group date_div">
			            <label for="dtp_input2" class="col-md-2 control-label">开始时间：</label>
			            <div class="input-group date form_date col-md-5 date_star" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
			                <input class="form-control" name="starttime" size="16" type="text" value="${portalLog.starttime }" readonly>
			                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
			            </div>
						<input type="hidden" id="dtp_input2" value="" />
			        </div>
					<div class="form-group date_div">
			            <label for="dtp_input2" class="col-md-2 control-label">结束时间：</label>
			            <div class="input-group date form_date col-md-5 date_end" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
			                <input class="form-control" name="endtime" size="16" type="text" value="${portalLog.endtime }" readonly>
			                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
			            </div>
						<input type="hidden" id="dtp_input2" value="" />
			        </div>
			        <input type="submit" value="查询" class="form_btn">
		        </div>
		       
				<div class="form_div col-md-1">
					
				</div>
			</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>登录用户</th>
						<th>用户姓名</th>
						<th>日志时间</th>
						<th>日志内容</th>
					</tr>
					<c:if test="${!empty requestScope.logList }">
						<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }"/>
						<c:forEach items="${ requestScope.logList}" var="log" varStatus="status">
							<tr>
								<td>
									${recordNumber + status.index + 1 }
								</td>
								<td>
									${log.name }
								</td>
								<td>
									${log.userName }
								</td>
								<td>
									${log.operationTime }
								</td>
								<td title="${log.description }">
									${log.description }
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.logList}">
						<tr>
							<td colspan="5" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<div class="clearfix"> 
					<ul class="pagination" style="line-height:34px">
						&nbsp;	${msgPage.totalPage}页 /共${msgPage.totalRecords}条
						<li class="previous"><a href="javascript:;" onclick="prev()">«</a></li>
						
						<c:if test="${msgPage.totalPage>6}">
							<c:if test="${msgPage.pageNum<=6/2}">
								<c:forEach var="x" begin="1" end="6">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a >${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${msgPage.pageNum>6/2&&msgPage.pageNum<=msgPage.totalPage - 6/2}">
								<c:forEach var="x" begin="${msgPage.pageNum +1 - 6/2}" end="${msgPage.pageNum + 6/2}">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a >${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${msgPage.pageNum>msgPage.totalPage - 6/2}">
								<c:forEach var="x" begin="${msgPage.totalPage +1 - 6}" end="${msgPage.totalPage}">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a >${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
						</c:if>
						<c:if test="${msgPage.totalPage<=6}">
							<c:forEach var="x" begin="1" end="${msgPage.totalPage}">
								<c:if test="${msgPage.pageNum==x}">
									<li class="active"><a >${x}</a></li>
								</c:if>
								<c:if test="${msgPage.pageNum!=x}">
									<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
								</c:if>
							</c:forEach>
						</c:if>
						
						<li class="next"><a href="javascript:;" onclick="next()">»</a></li>
					</ul>
				</div>
				<input type="hidden" id="pageNum" name="pageNum" value="${msgPage.pageNum }">
				<input type="hidden" id="totalPage" name="totalPage" value="${msgPage.totalPage }">
			</div>
		</div>
	</body>
	<!--时间js  -->
	<script type="text/javascript" src="<c:url value="/settingCenter/js/jquery-1.8.3.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/settingCenter/js/bootstrap.min.js"/>" charset="UTF-8"></script>
	<script type="text/javascript" src="<c:url value="/settingCenter/js/bootstrap-datetimepicker.min.js"/>" charset="UTF-8"></script>
	<script type="text/javascript" src="<c:url value="/settingCenter/js/bootstrap-datetimepicker.zh-CN.js"/>" charset="UTF-8"></script>

	<script type="text/javascript">
		function prev(){
			var entity = $('#form1').serialize();
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) - 1;
			if(pageNum == 0){
				pageNum = 1;
			}
  			params.pageNum = pageNum; 
	     	var url = "${pageContext.request.contextPath}/portal/log/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function next(){
			var entity = $('#form1').serialize();
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) + 1;
			if(pageNum >= totalPage){
				pageNum = totalPage;
			}
  			params.pageNum = pageNum; 
	     	
		    var url = "${pageContext.request.contextPath}/portal/log/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function page(pageNum){
			var entity = $('#form1').serialize();
			var url = "${pageContext.request.contextPath}/portal/log/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		
		/* 时间js */
		$('.form-control').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  0,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
			format: 'yyyy-mm-dd'
	    });
	    $(".input-group-addon").on("click",function(){
	    	$(this).siblings('input').attr("value","");
	    })
	    $("#search_data").on("click",function(){
	    	alert($("#star_data").val()+"至"+$("#end_data").val());
	    })
	</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>个人借款数据审核</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<style type="text/css">
			.selectone{
				white-space: nowrap;
				font-size: 14px;
				cursor: pointer;
			}
			.selectone:hover, .selectone.selectedOne{
				box-sizing: border-box;
				background-color: rgba(255,255,255,0.8);
				border-bottom:1px solid rgba(0,0,0,0.5);
			}
			.selectone>* {
				display:inline-block;
				min-width: 60px;
			}
			.selectedOne{
				border-color:#4a22cc;
				color: #4a22cc;
			}
			#com_ztree span {
				padding-left:0;
			}
		</style>
	
	</head>
	<!-- 核算个人借款审核的主页面 -->
	<body>
		<h4>个人借款数据审核</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
				
				<span class="col-md-4">
					<span class="search_title">年份：</span>
						<input  type="text" style="text-align: center" name="year" value="${entityview.year }" readonly="true" class="time" />
				</span>
				<%-- <span class="col-md-4">
					<span class="search_title">周数：</span>
						<input  type="text" style="text-align: center" name="week" value="${entityview.week }"/>
				</span> --%>
				
				
			<span class="col-md-4"><span class="search_title">周数：</span> 
			<input type="text" name="weekStart" 
				value="${weekStart}"   style="width:calc(50% - 8em);"/> 
			
			- 
				<input type="text" name="weekEnd"
				value="${weekEnd}"   style="width:calc(50% - 8em);"/>
			</span>
				<span class="col-md-4">
					<span class="search_title">上报单位：</span>
					<input type="hidden" id="organalID" name="organalID" value="${entityview.org }" >
					<textarea  id="allCompanyTree" style="display:none;">${allCompanyTree}</textarea>
					<input name="orgname" id="checkedCompanyName" value="${entityview.orgname }" readonly title="${entityview.orgname }">
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				   </div>
				   <i class="clear iconfont icon-guanbi"></i>
				</span>		   
		   
			
		
			<span class="col-md-4">
				<span class="search_title">审核状态：</span>
					<select  name="status" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.examstatustype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.status == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span>

			<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_grjksjsh_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>
					<c:if test="${fn:contains(gzwsession_code,',bimWork_grjksjsh_export,')==true }">
						<input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export()">
					</c:if>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>年份</th>
						<th>周数</th>
						<th>上报单位</th>
						<th>创建人</th>
						<th>复核人</th>
						<th>审核人</th>
						<th>审核状态</th>
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
									${sys.year}
								</td>
								<td>
									${sys.week}
								</td>
								<td>
									${sys.orgname }
								</td>
								<td style="text-align: center">
									${sys.createPersonName }
								</td>
								<td style="text-align: center">
									${sys.recheckPersonName }
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
									<c:if test="${fn:contains(gzwsession_code,',bimWork_grjksjsh_query,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=modify&id=${sys.id}',${sys.id})" >查看</a>
									</c:if>
										<%-- <c:if test="${fn:contains(gzwsession_code,',bimWork_grjksjsh_exam2,')==true }">
											<a  target="_blank" href="${pageContext.request.contextPath}/reportcommon/exam?op=examine&type=${type }&groupID=${sys.groupID}&organal=${sys.organalID }&Date=${sys.time}&grouptype=${grouptype }" checkdata="${sys.groupID},${sys.organalID },${sys.time}" >审1核</a>	
											<!-- 审核页面直接展示的审核状态按钮 -->		
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/exam?id=${sys.id}',${sys.id })" >审核</a>			
										</c:if>	 --%>
									<c:if test="${sys.status==50113}">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_grjksjsh_exam,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/exam?id=${sys.id}',${sys.id })" >审核</a>
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
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/listPageCompanyTreeSelect.js"/>" type="text/javascript"></script>
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

		function _export(){
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/reportpersonalLoanNew/grjktbExprot";
			form.method = "post";
			form.submit();
		}

	
	</script>
</html>
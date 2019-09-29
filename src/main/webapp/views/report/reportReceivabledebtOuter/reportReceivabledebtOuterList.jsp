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
		<title>应收债权(内部)数据填报</title>
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
	<body>
		<h4>应收债权(外部)数据填报</h4>
		<div class="table_content">
		
		<form id="form1"  class="row">
		
			<span class="col-md-4">
				<span class="search_title">年份：</span>
					<input  type="text" style="text-align: center" name="year" value="${entityview.year }" readonly="true" class="time" />
			</span>
			<span class="col-md-4">
				<span class="search_title">周数：</span>
					<input  type="text" style="text-align: center" name="week" value="${entityview.week }"/>
			</span>
			<span class="col-md-4">
				<span class="search_title" style="width: 9em;">债权单位名称：</span>
				<input type="hidden" id="organalID" name="org" value="${entityview.org }" >
				<textarea  id="allCompanyTree" style="display:none;">${allCompanyTree}</textarea>
				<input name="orgname" id="checkedCompanyName" value="${entityview.orgname }" readonly title="${entityview.orgname }" style="width: calc(100% - 10em);">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			   </div>
			    <i class="clear iconfont icon-guanbi"></i>
		   </span> 
			
			<span class="col-md-4">
				<span class="search_title" style="width: 9em;">台账审核状态：</span>
					<select  name="status" class="selectpicker" style="width: calc(100% - 10em);">
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.examstatustype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.status == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span>

			<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_yszqsjtb_wb_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>
					<c:if test="${fn:contains(gzwsession_code,',bimWork_yszqsjtb_wb_add,')==true }">	
						<input type="button" value="新增" class="form_btn" id="addbtn" onclick="mload('${mapurl}/newmodify')">
					</c:if>
					<c:if test="${fn:contains(gzwsession_code,',bimWork_yszqsjtb_wb_export,')==true }">	
						<input type="button" value="应收债权表格下载" class="form_btn" id="export" onclick="exportfile()">
						<a href="${pageContext.request.contextPath}/views/report/reportReceivabledebtOuter/receivabledebt1.xlsx" id="exportfile" style="display:none"></a>
					</c:if>
					<c:if test="${fn:contains(gzwsession_code,',bimWork_yszqsjtb_wb_export,')==true }">
						<input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export()">
					</c:if>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>上报期间</th>
						<th>所属业态</th>
						<th>债权单位名称</th>
						<th>导入人</th>
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
									${sys.year}年第${sys.week }周
								</td>
								<td>
									${sys.coreorgname }
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
									<c:if test="${fn:contains(gzwsession_code,',bimWork_yszqsjtb_wb_query,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=modify&id=${sys.id}',${sys.id })" >查看</a>
								 	</c:if>	
									<c:if test="${sys.status==50114 || sys.status==50112 }">

									<c:if test="${fn:contains(gzwsession_code,',bimWork_yszqsjtb_wb_recheck,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/postexam?op=report&id=${sys.id}',${sys.id })">复核</a>
									</c:if>	
									<c:if test="${fn:contains(gzwsession_code,',bimWork_yszqsjtb_wb_del,')==true  }">	
										<a href="javascript:void(0)" onclick="del('${sys.id}')" >删除</a>
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
				<jsp:include page="page.jsp"/>
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
	
		function exportfile()
		{
			document.getElementById('exportfile').click();
		}
	
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
						}else
						{
							win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
						}
						
					});
				}
			});
		}

		function _export(){
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/receivabledebtOuter/reportExport";
			form.method = "post";
			form.submit();
		}
		
		
	</script>
</html>
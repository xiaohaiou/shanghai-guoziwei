<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>预算执行报表上报</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
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
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>预算执行报表审核</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
		<input type="hidden" name="groupType" value="${grouptype }">
		
		    <span class="col-md-4">
				<span class="search_title">财务树时间：</span>
				<input  type="text" id="financeTime" name="financeTime" value="${entityview.financeTime }" readonly="true" class="times"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">财务树选择：</span>
				<input type="hidden" id="organalID" name="organalID" value="${entityview.organalID }" >
				<textarea  id="allCompanyTree" style="display:none;">${allCompanyTree}</textarea>
				<input name="organalname" id="checkedCompanyName" value="${entityview.organalname }" readonly title="${entityview.organalname }">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			    </div>
			    <i class="clear iconfont icon-guanbi"></i>
		    </span>
		    <span class="col-md-4">
			<span class="search_title">审核状态：</span>
					<select  name="examinestatus" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.examstatustype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.examinestatus == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span>
			<span class="col-md-4">
			<span class="search_title">使用时间：</span>
					<input  type="text" name="starttime" value="${entityview.starttime }" readonly="true" class="time time_short" />
					至
					<input  type="text" name="endtime" value="${entityview.endtime }" readonly="true" class="time time_short" />
			</span>
			
			<span class="col-md-12 " style="text-align: right">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_yszxbbsh_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>
						<%-- <c:if test="${fn:contains(rolePagecodeNums,'sys_add')==true}"> --%>
							<%-- <input type="button" value="新增" class="form_btn" id="model_add" onclick="mload('${pageContext.request.contextPath}/reportgroup/newmodify')"> --%>
						<%-- </c:if> --%>
			</span>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>报表标题</th>
						<th>单位名称</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>上报时间</th>
						<th>审核状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					  <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status1">
							<tr>
								<td>
									${recordNumber+ status1.index + 1 }
								</td>
							
								<td>
									${sys.time }${sys.title }
								</td>
								<td>
									${sys.organalname }
								</td>
								<td style="text-align: center">
									${sys.createpersonName }
								</td>
								<td style="text-align: center">
									${sys.reportpersonName }
								</td>
								<td style="text-align: center">
									${sys.auditorpersonName }
								</td>
								<td style="text-align: center">
									${sys.reporttime }
								</td>
								<td style="text-align: center">
									<c:if test="${ sys.examinestatus==50112}">
										<span style="color:#3366ff">${sys.examinestatusName }</span>
									</c:if>
									<c:if test="${ sys.examinestatus==50113}">
										<span style="color:#ff9933">${sys.examinestatusName }</span>
									</c:if>
									<c:if test="${ sys.examinestatus==50114}">
										<span style="color:#ff399d">${sys.examinestatusName }</span>
									</c:if>
									<c:if test="${ sys.examinestatus==50115}">
										<span style="color:#00cc66">${sys.examinestatusName }</span>
									</c:if>
								</td>
								<td>
								<c:if test="${fn:contains(gzwsession_code,',bimWork_yszxbbsh_query,')==true }">
									<a  href="${pageContext.request.contextPath}/reportcommon/show?op=view&type=${type }&groupID=${sys.groupID}&organal=${sys.organalID }&Date=${sys.time}&grouptype=${grouptype }"  checkdata="${sys.groupID},${sys.organalID },${sys.time}"  target="_blank">查看</a>
									<c:if test="${ sys.examinestatus==50115}">
										<a href="javascript:;" onclick="downloadFile('${pageContext.request.contextPath}/reportcommon/exportReportData?type=${type}&groupID=${sys.groupID}&organal=${sys.organalID}&Date=${sys.time}&grouptype=${grouptype }')"  checkdata="${sys.groupID},${sys.organalID },${sys.time}" target="_blank">导出</a>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_yszxbbsh_exam_2,')==true }">
											<a  target="_blank" href="${pageContext.request.contextPath}/reportcommon/show?op=examine&type=${type }&groupID=${sys.groupID}&organal=${sys.organalID }&Date=${sys.time}&grouptype=${grouptype }" checkdata="${sys.groupID},${sys.organalID },${sys.time}" >审核</a>								
										</c:if>	
									</c:if>
								</c:if>
								<c:if test="${fn:contains(gzwsession_code,',bimWork_yszxbbsh_exam,')==true }">
									<c:if test="${sys.examinestatus==50113 }">
										<a  target="_blank" href="${pageContext.request.contextPath}/reportcommon/show?op=examine&type=${type }&groupID=${sys.groupID}&organal=${sys.organalID }&Date=${sys.time}&grouptype=${grouptype }" checkdata="${sys.groupID},${sys.organalID },${sys.time}" >审核</a>
									</c:if>
								</c:if>
									
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="9" align="center">
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
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>

	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/listPageCompanyTreeSelect.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
		
		$('a[checkdata]').click(function(){
			return checkdata.apply(this,$(this).attr("checkdata").split(','))
		})
		
		function checkdata(groupID,organ,time)
		{

			var commitresult="";
			var checkurl = '/shanghai-gzw/reportcommon/hasCheck';
			var data = $.ajax({
			  type: 'POST',
			  url: checkurl,
			  async:false, 
			  data:{"Date":time,"organalID":organ,"groupID":groupID},
			}).responseText;

			commitresult=JSON.parse(data);
			if(commitresult.result==false)
				win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
			return commitresult.result
			
			/* if(commitresult.result == true){
				  var a = document.createElement('a');  
	              a.setAttribute('href', url);  
	              a.setAttribute('target', '_blank');  
	           	  a.setAttribute('id', 'newopenwindow');
	              // 防止反复添加  
	              if(!document.getElementById( 'newopenwindow')) {
	              	document.body.appendChild(a);  
	              }  
	              a.click();  
	          
			}
			else
				  win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);}); */

		}
		$(' input.time').jeDate(
			{
				format:"YYYY-MM"
			}
		)
	
		$(' input.times').jeDate(
				{
					format:"YYYY-MM",
					choosefun:function(val){
					    changeFinanceTree();
					}
				}
			)
	
	   function changeFinanceTree(){
            var form = document.forms[0];
			form.action = "${changeFinaneUrl}";
			form.method = "post";
			form.submit();
        }
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}
		
		var interval;
		function downloadFile(url){
			$.blockUI({ message: '导出中', css: { width: '275px' } });
			var iframe = document.createElement("iframe");  
			iframe.src = url;  
			iframe.style.display = "none"; 
			document.body.appendChild(iframe);
			interval = setInterval("getExportFlag()", 500);
			
		}
		
		function getExportFlag(){
			$.ajax({
				url:"${pageContext.request.contextPath}/reportcommon/getExportSession",
				type:'POST',
				dataType:'text',
				success:function(data){
					var exportSession = data;
					if(exportSession!=undefined && exportSession != '' && exportSession == 1){
						if(interval){
							clearInterval(interval);
						}
						$.unblockUI();
					}else if(exportSession!=undefined && exportSession != '' && exportSession == 2){
						if(interval){
							clearInterval(interval);
						}
						win.errorAlert("下载失败！");
						$.unblockUI();
					}
				},
				error:function(data,status,e){
					if(interval){
						clearInterval(interval);
					}
					win.errorAlert("error:下载失败！");
					$.unblockUI();
				}
			});
			
		}
	</script>
</html>
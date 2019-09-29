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
		<title>外部对标填报</title>
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
		<h4>外部对标数据审核</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
					<span class="col-md-4">
						<span class="search_title">年份：</span>
							<input  type="text" style="text-align: center" name="year" value="${entityview.year }" readonly="true" class="time" />
					</span>
         	<%-- 		<span class="col-md-4">
						<span class="search_title">周数：</span>
							<input  type="text" style="text-align: center" name="week" value="${entityview.week }"/>
					</span>  --%>
					<span class="col-md-4">
						<span class="search_title">公司名称：</span>
						<span class="w25">
							<select  name="orgname" class="selectpicker" id="orgname"  style="width:330px;">
							 <option value=""  >全部</option>
									<c:forEach items="${requestScope.list1}" var="result">
											<option value="${result.financeorgname }" <c:if test="${entityview.orgname == result.financeorgname}">selected="selected"</c:if> >${result.financeorgname }</option>
									</c:forEach>
							</select>
						</span> 
				   </span>

			<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_wbdbsjsh_query,')==true }">	
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
 				   </c:if> 
				
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>公司名称</th>
						<th>时间</th>
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
									${sys.orgname }
								</td>
								<td style="text-align: center;">
									${sys.year }
									<c:if test="${sys.season eq '50108'}">一季度</c:if>
									<c:if test="${sys.season eq '50109'}">二季度</c:if>
									<c:if test="${sys.season eq '50110'}">三季度</c:if>
									<c:if test="${sys.season eq '50111'}">四季度</c:if>
								</td>
								<td style="text-align: center">
									<c:if test="${ sys.status==50112}">
										<span style="color:#3366ff">未上报</span>
									</c:if>
									<c:if test="${ sys.status==50113}">
										<span style="color:#ff9933">待审核</span>
									</c:if>
									<c:if test="${ sys.status==50114}">
										<span style="color:#ff399d">已退回</span>
									</c:if>
									<c:if test="${ sys.status==50115}">
										<span style="color:#00cc66">已审核</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_wbdbsjsh_show,')==true }">
									     <a href="javascript:void(0)" onclick="mload('${mapurl}/view?op=show&id=${sys.id}')" >查看</a>
									</c:if> 
									<c:if test="${sys.status==50113 }">
									     <c:if test="${fn:contains(gzwsession_code,',bimWork_wbdbsjsh_examine,')==true }">
									          <a href="javascript:void(0)" onclick="mload('${mapurl}/exam?id=${sys.id}')" >审核</a>
								         </c:if>
								    </c:if>
								   <c:if test="${sys.status==50115 }">
									     <c:if test="${fn:contains(gzwsession_code,',bimWork_wbdbsjsh_return,')==true }">
									          <a href="javascript:void(0)" onclick="mload('${mapurl}/exam?id=${sys.id}')" >回退</a>
								         </c:if>
								    </c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="13" align="center">
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
						}else
						{
							win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
						}
						
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
								win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
							}
					});
				}
			});
		}
		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="orgname"]').val('');
			$("#organalID").val("");
			$("#checkedCompanyName").attr('title','');
		});
	</script>
</html>
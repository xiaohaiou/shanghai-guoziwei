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
		<title>公司员工借款相邻周差异比较</title>
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
		<h4>公司员工借款相邻周差异比较查询</h4>
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
				<span class="search_title">核心企业：</span>
					<select  name="coreorg" class="selectpicker" id="coreorg" onchange="changeOrgname(this.value)">
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
								<option value="${result.id.nnodeId }"  <c:if test="${entityview.coreorg eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
			</span>
			
			<span class="col-md-4">
				<span class="search_title">公司名称：</span>
					<select  name="org" id="org" class="selectpicker">
						
					</select>
			</span>
			<input type="hidden" id="orgname" name="orgname">
			
			<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_gsygjkxlyfcybj_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>
					<c:if test="${fn:contains(gzwsession_code,',bimWork_gsygjkxlyfcybj_export,')==true }">
						<input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export()">
					</c:if>

			</div>
		</form>
			<div class="tablebox">
			<h4>整体情况列表</h4>
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>上报期间</th>
						<th>所属核心企业</th>
						<th>借款人总额(万元)</th>
						<th>借款人数(人)</th>
						<th>借款超期人数(人)</th>
						<th>超期金额(万元)</th>
						<th>本周新增借款人数(人)</th>
						<th>本周还完借款人数(人)</th>
					</tr>
					<c:if test="${!empty requestScope.nearweeks }">
						<c:forEach items="${ requestScope.nearweeks}" var="sys" varStatus="status">
							<tr>
								<td>
									${status.index + 1 }
								</td>
								<td>
									${sys.year}年第${sys.week }周
								</td>
								<td>
									${sys.coreorgname }
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.loantotalmoney }"  pattern="#,##0.00" />
								</td>
								<td style="text-align: center">
									${sys.loansumperson }
								</td>
								<td style="text-align: center">
									${sys.loansumoverperson }
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.loantotalovermoney }"  pattern="#,##0.00" />
								</td>
								<td style="text-align: center">
									${sys.monthsumaddperson }
								</td>
								<td style="text-align: center">
									${sys.monthsumfinishperson }
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.nearweeks}">
						<tr>
							<td colspan="10" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
			</div>
			
			<div class="tablebox">
				<h4>公司员工个人借款增减变动情况</h4>
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>上报期间</th>
						<th>所属核心企业</th>
						<th>公司名称</th>
						<th>欠款人员工编号</th>
						<th>员工姓名</th>
						<th>期初余额(万元)</th>
						<th>本周新增(万元)</th>
						<th>本周已还(万元)</th>
						<th>期末余额(万元)</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					  <c:set var="recordNumber1" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber1+ status.index + 1 }
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
									${sys.personAccount }
								</td>
								<td style="text-align: center">
									${sys.personname }
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.beginningbalance }"  pattern="#,##0.00" />
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.monthaddmoney }"  pattern="#,##0.00" />
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.monthreturnmoney }"  pattern="#,##0.00" />
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.endingbalance }"  pattern="#,##0.00" />
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
	
		function changeOrgname(value){
			
			$.ajax({
				url:"${pageContext.request.contextPath}/reportpersonalLoanNew/getcompanyName",
				type:"POST",
				async:false,
				data:{"parentID":value},
				success:function(data){
					var org = '${entityview.org}';
					var data1 = JSON.parse(data);
					var html = "<option value=\"\"  >全部</option>";
					$.each(data1, function(i, p) {
						if(org == p.id.nnodeId){
							html +="<option value=\""+p.id.nnodeId+"\" selected=\"selected\">"+p.vcFullName+"</option>";
							
						}else{
							html +="<option value=\""+p.id.nnodeId+"\" >"+p.vcFullName+"</option>";
						}
					});
					$("#org").html(html);
					
				}
			});	
		}
		
		changeOrgname($("#coreorg").val());
		
		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		)
		
		function _query()
		{
			var name = $("#org").find("option:selected").text();
			$("#orgname").val(name);
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
	

		}

		function _export(){
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/reportpersonalLoanNew/nearweekcompareExport";
			form.method = "post";
			form.submit();
		}

		
		
	</script>
</html>
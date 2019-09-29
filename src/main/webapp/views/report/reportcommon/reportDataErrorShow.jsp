<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>报表详细页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
<style>

.selected{
	background-color:#4492d4 !important;

}

</style>
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>
			<span class="glyphicon glyphicon-pencil"></span>预算报表(已审核)回退提示
			<c:if test="${!flag}">
					<th5 style="color:#ff0000">上级公司已存在数据，请先回退上级单位数据</th5>
			</c:if>
		</h4>
		
		<div class="label_inpt_div">
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
	
	<div class="tablebox">
		<table>
			<tr class="table_header">
				<th>序号</th>
				<th>报表时间</th>
				<th>单位名称</th>
				<th>表单类型</th>
				<th>创建人</th>
				<th>上报人</th>
				<th>审核人</th>
				<th>审核状态</th>
			</tr>
			<c:if test="${!empty requestScope.list }">
				<c:forEach items="${ requestScope.list}" var="sys" varStatus="status1">
					<tr>
						<td>
							${status1.index + 1 }
						</td>
						<td>
							${sys.organName}
						</td>
						<td style="text-align: center">
							${sys.reportTime}
						</td>
						<td>
							<c:if test="${sys.formsKind==51004}">利润汇总表</c:if>
							<c:if test="${sys.formsKind==51005}">财务收支汇总表</c:if>
						</td>						
						<td style="text-align: center">
							${sys.createPersonName}
						</td>
						<td style="text-align: center">
							${sys.reportPersonName }
						</td>
						<td style="text-align: center">
							${sys.auditorPersonName }
						</td>
						<td style="text-align: center">
							<c:if test="${ sys.isexamine==50112}">
								<span style="color:#3366ff">未上报</span>
							</c:if>
							<c:if test="${ sys.isexamine==50113}">
								<span style="color:#ff9933">待审核</span>
							</c:if>
							<c:if test="${ sys.isexamine==50114}">
								<span style="color:#ff399d">已退回</span>
							</c:if>
							<c:if test="${ sys.isexamine==50115}">
								<span style="color:#00cc66">已审核</span>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty requestScope.list}">
				<tr>
					<td colspan="9" align="center">
						上级公司无汇总数据！
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	
	<div class="model_btn">
		<c:if test="${flag}">
			<button class="btn btn-primary model_btn_ok" id="commit-3">退回</button>
		</c:if>
		<button class="btn btn-default model model_btn_close">关闭</button>
	</div>
	<script type="text/javascript">
		//执行回退操作
		$("#commit-3").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			$.post("/shanghai-gzw/reportcommon/examine",{examineStr:'${examStr}',examineresult:50117,groupID:'${groupID}',Date:'${date}',organal:'${checkedCompanyid}'},function(data){
    			var commitresult=JSON.parse(data);
	    		$.unblockUI();
				if(commitresult.result==true)
					win.successAlert('退回成功！',function(){
							parent.window.close();
							parent.window.opener._query();
							parent.mclose();
							window.close();
					});
				else
				{
					win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
				}
	 		 });
		 	return false;
		})
	
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		
	</script>
</html>
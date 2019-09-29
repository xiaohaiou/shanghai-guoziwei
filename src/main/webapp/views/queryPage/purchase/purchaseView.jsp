<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>采购数据</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
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
		<c:when test="${op eq 'report'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报采购数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看采购数据
				<i class="iconfont icon-guanbi"></i>		
			</h4>
		</c:otherwise>
		
			
	</c:choose>
	
		<form:form id="form2" modelAttribute="entityview" >
			<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ entityview.year}</label> 
				<label class="w20">季度:</label>
				<label class="w25 setleft">${ entityview.seasonName}</label> 
			</div>
			
			<h3 class="data_title1">库内工程类供应商数量(单位：个)</h3>
			
			<div class="model_part">
				<label class="w20">施工类:</label>
				<label class="w25 setleft">${ entityview.constructioClass}</label> 
				<label class="w20">设计类:</label>
				<label class="w25 setleft">${ entityview.designClass}</label> 
				<label class="w20">监理类:</label>
				<label class="w25 setleft">${ entityview.supervisionClass}</label> 
				<label class="w20">咨询造价类:</label>
				<label class="w25 setleft">${ entityview.consultingCostClass}</label> 
				<label class="w20">材料设备类:</label>
				<label class="w25 setleft">${ entityview.materialEquipmentClass}</label> 
			</div>
			
			<h3 class="data_title1">累计完成采购金额（单位：万元）</h3>
			
			<div class="model_part">
				<label class="w20">工程类:</label>
				<label class="w25 setleft">${ entityview.engineeringClass}</label> 
				<label class="w20">物资类:</label>
				<label class="w25 setleft">${ entityview.materialCategoryClass}</label> 
				<label class="w20">渠道类:</label>
				<label class="w25 setleft">${ entityview.channelClass}</label> 
				<label class="w20">服务类:</label>
				<label class="w25 setleft">${ entityview.serviceClass}</label> 

			
			</div>
			<h3 class="data_title1">工程招采成本节约率</h3>
			
			<div class="model_part">
				<label class="w20">工程招采总金额(万元):</label>
				<label class="w25 setleft">${ entityview.incursTotalAmount}</label> 
				<label class="w20">工程招采节约金额(万元):</label>
				<label class="w25 setleft">${ entityview.savingsTotalAmount}</label> 
				<label class="w20">工程招采成本节约率(%):</label>
				<label class="w25 setleft">${ entityview.purchaseSaveRate}</label> 
			</div>
			
			<%-- <c:choose>
					<c:when test="${op eq 'report'}">
						
					</c:when>
					<c:otherwise> --%>
						<h3 class="data_title1">创建上报信息</h3>
							
							<div class="model_part">
								<label class="w20">数据创建人:</label>
								<label class="w25 setleft">${ entityview.createPersonName}</label>
								<label class="w20">创建时间:</label>
								<label class="w25 setleft">${ entityview.createDate}</label>
								<c:if test="${ entityview.status!=50112}">
									<label class="w20">数据上报人:</label>
									<label class="w25 setleft">${ entityview.reportPersonName}</label>
									<label class="w20">上报时间:</label>
									<label class="w25 setleft">${ entityview.reportDate}</label>
								</c:if>
							
							</div>
				<%-- 	</c:otherwise>
			</c:choose> --%>
			
			
			
			<c:if test="${ !empty entityExamineviewlist  }">
			<h3 class="data_title1">审核意见</h3>
			<div class="model_part">
			<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
			
			
				<label class="w20">审核人:</label>
				<label class="w25 setleft">${ sys.createPersonName}</label> 
				<label class="w20">审核时间:</label>
				<label class="w25 setleft">${ sys.createDate}</label> 
				<label class="w20">审核结果:</label>
				<label class="w25 setleft">${ sys.examresultName}</label> 
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20" style="vertical-align:top;">审核意见:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${ sys.examinestr}</label> 
			
			
			</c:forEach>
			</div>
			</c:if>
			<div class="model_btn">
				<c:if test="${op eq 'report' }">
				<button class="btn btn-primary model_btn_ok" id="commit-2">上报</button>
				
				</c:if>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
			</div>
	</form:form>
	
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">
		
		$("#commit-2").click(function(){
		
			exam(${entityview.id});
			return false;
		})
		
		
		function exam(id){
		
			
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
		
			var url = "/shanghai-gzw/purchase/saveandreport?id=" + id;
			$.post(url, function(data) {
				
				var commitresult=JSON.parse(data);
				$.unblockUI();
				if(commitresult.result==true)
					
					win.successAlert('上报成功！',function(){
						
						parent._query();
						parent.mclose();
					});
				else
					{
						win.errorAlert(commitresult.exceptionStr);
					}
					
			});
			
		
			
		}
	
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
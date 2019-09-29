<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
	<title>上市公司数据分析</title>
	<link rel="stylesheet" type="text/css" href="../css/financial_risk.css">
	<style type="text/css">
		 .top_switch {
   			 display: inline-block;
  			  position: absolute;
    		right: 2%;
    		line-height: 65px;
		}
		 .top_switch span {
    		background: #0b1c47;
    		border: 1px solid #099cb4;
    		padding: 9px 34px;
    		border-radius: 5px;
		}
		 .top_switch img {
   			 vertical-align: middle;
    		margin-left: 10px;
		}
	</style>
</head>
<body>
	<input type="hidden" id="year" value="${year}"/>
	<input type="hidden" id="season" value="${season}"/>
	<div class="top">
		<h1>上市公司数据分析</h1>
		<span>上市公司股价（截止到2018.8.28 收盘）</span>
		<span class="top_switch">
			<span>上市公司股价</span>
			<span style="cursor: pointer;" onclick="turnToContrast()">上市公司对标</span>
			<a href="javascript:window.opener=null;window.open('','_self');window.close();"><img src="../img/exit.png" ></a>
		    </span>
		<!-- <div class="top_select">
			<span>公司名称</span>
			<select>
				<option value ="">上海电气集团有限公司</option>
  				<option value ="">上海电气集团有限公司</option>
  				<option value="">上海电气集团有限公司</option>
			</select>
			<span>时间</span>
			<select>
				<option value ="">2018年</option>
  				<option value ="">2017年</option>
  				<option value="">2016年</option>
			</select>
			<img src="../img/exit.png">
		</div> -->
	</div>
	<div class="line"></div>
	<div class="container">
	
	
		<c:forEach var="OutCompareCode" items="${ OutCompareCodeList}">
			<div class="case">
			<div class="case_title" style="display: flex;" ><div>${OutCompareCode[1]}</div><div style="flex:1;text-align: right;"><span class="case_subtext case_subtext1">${OutCompareCode[5]}亿</span><span class="case_subtext case_subtext2">${ OutCompareCode[6]}亿</span></div></div>
			<div class="case_left ${OutCompareCode[3]<0?'bg_green':'bg_red'}  fl">
				<c:if test="${OutCompareCode[3]<0}">
					<img class="case_left_icon" src="../img/down.png">
				</c:if>
				<c:if test="${OutCompareCode[3]>=0}">
					<img class="case_left_icon" src="../img/up.png">
				</c:if>
				<div class="case_left_block">
					<div class="case_left_title">${ OutCompareCode[2]}</div>
					<div class="clearfix"></div>
					<div class="case_left_content">${OutCompareCode[3] }%</div>
					<div class="case_left_content">${OutCompareCode[4]}
					
					<c:if test="${OutCompareCode[3]<0}">
					<img src="../img/down2.png">
				</c:if>
				<c:if test="${OutCompareCode[3]>=0}">
					<img src="../img/up2.png">
				</c:if>
					
					
					</div>
				</div>
			</div>
			<table>
			 	<tr>
			 	  	<th>总股本</th>
			 	  	<th>流通股</th>
			 	  	<th>占比</th>
			 	  	<th>非流通</th>
			 	  	<th>占比</th>
			 	</tr>
			 	<tr>
			 	 	 <td>${OutCompareCode[7] }亿股</td>
			 	 	 <td>${OutCompareCode[8] }亿股</td>
			 	 	 <td>${OutCompareCode[9] }%</td>
			 	 	 <td>${OutCompareCode[10] }亿股</td>
			 	 	 <td>${OutCompareCode[11] }%</td>
			 	</tr>
			 	<tr>
			 	  	<th>已质押股数</th>
			 	  	<th>${OutCompareCode[12] }亿股</th>
			 	</tr>
			 	<tr>
			 	 	 <td>股票质押率</td>
			 	 	 <td>${OutCompareCode[13]}%</td>
			 	</tr>
			</table>
		</div>
		
		</c:forEach>
		
		
		 <div class="clearfix" style="height: 35px;"></div>
	</div>
	<script src="<c:url value="/js/jquery.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/echarts.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/bootstrap-datetimepicker.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/bootstrap-datetimepicker.zh-CN.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/mCustomScrollbar.min.js"/>" charset="utf-8"></script>
	<script type="text/javascript">
	
		function turnToContrast(){
			
			
			
			var year=$("#year").val();
			var season=$("#season").val();
			
			
			window.location.href = '${pageContext.request.contextPath}/outcompare/page?year='+year+'&season='+season;
			
			
		}
	
	</script>
</body>
</html>
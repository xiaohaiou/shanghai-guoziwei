<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<style>
		.clearfix {
		    position: relative;
		    right: 10px;
		    bottom: 0;
		}
	</style>
	<div class="clearfix" id="${flag}"> 
		<ul class="pagination" style="line-height:34px">
		&nbsp;	${msgPage.totalPage}页 /共${msgPage.totalRecords}条
			<li class="previous"><a href="javascript:;" onclick="prev('${flag}','${msgPage.pageNum }')">«</a></li>
			
			<c:if test="${msgPage.totalPage>6}">
				<c:if test="${msgPage.pageNum<=6/2}">
					<c:forEach var="x" begin="1" end="6">
						<c:if test="${msgPage.pageNum==x}">
							<li class="active"><a >${x}</a></li>
						</c:if>
						<c:if test="${msgPage.pageNum!=x}">
							<li><a href="javascript:;" onclick="page('${flag}','${x}');">${x}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${msgPage.pageNum>6/2&&msgPage.pageNum<=msgPage.totalPage - 6/2}">
					<c:forEach var="x" begin="${msgPage.pageNum +1 - 6/2}" end="${msgPage.pageNum + 6/2}">
						<c:if test="${msgPage.pageNum==x}">
							<li class="active"><a >${x}</a></li>
						</c:if>
						<c:if test="${msgPage.pageNum!=x}">
							<li><a href="javascript:;" onclick="page('${flag}','${x}');">${x}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${msgPage.pageNum>msgPage.totalPage - 6/2}">
					<c:forEach var="x" begin="${msgPage.totalPage +1 - 6}" end="${msgPage.totalPage}">
						<c:if test="${msgPage.pageNum==x}">
							<li class="active"><a >${x}</a></li>
						</c:if>
						<c:if test="${msgPage.pageNum!=x}">
							<li><a href="javascript:;" onclick="page('${flag}','${x}');">${x}</a></li>
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
						<li><a href="javascript:;" onclick="page('${flag}','${x}');">${x}</a></li>
					</c:if>
				</c:forEach>
			</c:if>
			
			<li class="next"><a href="javascript:;" onclick="next('${flag}','${msgPage.pageNum }','${msgPage.totalPage }')">»</a></li>
		</ul>
	</div>
	<script type="text/javascript">
		
		function prev(flag,tempPageNum){
			var params = {};
			var pageNum = parseInt(tempPageNum) - 1;
			if(pageNum == 0){
				pageNum = 1;
			}
  			params.pageNum = pageNum; 
  			var data = [];
  			if(flag == 'reportMonthlyFinancingIntoDetail'){
  				data = data1;
  			}else if(flag == 'reportMonthlyFinancingOutDetail'){
  				data = data2;
  			}else{
  				data = data3;
  			}
  			initNodeList(flag,pageNum,"",data);
	     // initNodeList(flag,pageNum);
		}
		function next(flag,tempPageNum,totalPage){
			var params = {};
			var pageNum = parseInt(tempPageNum) + 1;
			if(pageNum >= totalPage){
				pageNum = totalPage;
			}
  			params.pageNum = pageNum; 
  			var data = [];
  			if(flag == 'reportMonthlyFinancingIntoDetail'){
  				data = data1;
  			}else if(flag == 'reportMonthlyFinancingOutDetail'){
  				data = data2;
  			}else{
  				data = data3;
  			}
			initNodeList(flag,pageNum,"",data);
			   // initNodeList(flag,pageNum);
		}
		function page(flag,pageNum){
		   // initNodeList(flag,pageNum);
		   var data = [];
 			if(flag == 'reportMonthlyFinancingIntoDetail'){
 				data = data1;
 			}else if(flag == 'reportMonthlyFinancingOutDetail'){
 				data = data2;
 			}else{
 				data = data3;
 			}
	    	initNodeList(flag,pageNum,"",data);
		}
	</script>
</html>
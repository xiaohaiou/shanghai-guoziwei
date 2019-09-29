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
	<div class="clearfix"> 
		<ul class="pagination" style="line-height:34px">
		&nbsp;	${totalPage}页 /共${totalRecords}条
			<li class="previous"><a href="javascript:;" onclick="prev('${pageNum }')">«</a></li>
			
			<c:if test="${totalPage>6}">
				<c:if test="${pageNum<=6/2}">
					<c:forEach var="x" begin="1" end="6">
						<c:if test="${pageNum==x}">
							<li class="active"><a >${x}</a></li>
						</c:if>
						<c:if test="${pageNum!=x}">
							<li><a href="javascript:;" onclick="page('${x}');">${x}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${pageNum>6/2&&pageNum<=totalPage - 6/2}">
					<c:forEach var="x" begin="${pageNum +1 - 6/2}" end="${pageNum + 6/2}">
						<c:if test="${pageNum==x}">
							<li class="active"><a >${x}</a></li>
						</c:if>
						<c:if test="${pageNum!=x}">
							<li><a href="javascript:;" onclick="page('${x}');">${x}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${pageNum>totalPage - 6/2}">
					<c:forEach var="x" begin="${totalPage +1 - 6}" end="${totalPage}">
						<c:if test="${pageNum==x}">
							<li class="active"><a >${x}</a></li>
						</c:if>
						<c:if test="${pageNum!=x}">
							<li><a href="javascript:;" onclick="page('${x}');">${x}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
			</c:if>
			<c:if test="${totalPage<=6}">
				<c:forEach var="x" begin="1" end="${totalPage}">
					<c:if test="${pageNum==x}">
						<li class="active"><a >${x}</a></li>
					</c:if>
					<c:if test="${pageNum!=x}">
						<li><a href="javascript:;" onclick="page('${x}');">${x}</a></li>
					</c:if>
				</c:forEach>
			</c:if>
			
			<li class="next"><a href="javascript:;" onclick="next('${pageNum }','${totalPage }')">»</a></li>
		</ul>
	</div>
	<script type="text/javascript">
		var searchurl = '${searchUrl}';
		function prev(tempPageNum){
			var pageNum = parseInt(tempPageNum) - 1;
			if(pageNum == 0){
				pageNum = 1;
			}
  			 
  			page(pageNum);
		}
		function next(tempPageNum,totalPage){
			
			var pageNum = parseInt(tempPageNum) + 1;
			if(pageNum >= totalPage){
				pageNum = totalPage;
			}
  			
			 page(pageNum);
		}
		function page(pageNum){
		 	var url = searchurl+"&pageNum="+pageNum;
			var form = document.forms[0];
			form.action =url;
			form.method = "post";
			form.submit();	
		}
	</script>
</html>
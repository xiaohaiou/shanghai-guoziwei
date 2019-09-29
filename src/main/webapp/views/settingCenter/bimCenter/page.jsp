<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="clearfix">
	<ul class="pagination" style="line-height:34px">
		&nbsp; ${msgPage.totalPage}页 /共${msgPage.totalRecords}条
		<li class="previous"><a href="javascript:;" onclick="prev()">«</a>
		</li>

		<c:if test="${msgPage.totalPage>6}">
			<c:if test="${msgPage.pageNum<=6/2}">
				<c:forEach var="x" begin="1" end="6">
					<c:if test="${msgPage.pageNum==x}">
						<li class="active"><a href="#">${x}</a>
						</li>
					</c:if>
					<c:if test="${msgPage.pageNum!=x}">
						<li><a href="javascript:;" onclick="page(${x});">${x}</a>
						</li>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if
				test="${msgPage.pageNum>6/2&&msgPage.pageNum<=msgPage.totalPage - 6/2}">
				<c:forEach var="x" begin="${msgPage.pageNum +1 - 6/2}"
					end="${msgPage.pageNum + 6/2}">
					<c:if test="${msgPage.pageNum==x}">
						<li class="active"><a href="#">${x}</a>
						</li>
					</c:if>
					<c:if test="${msgPage.pageNum!=x}">
						<li><a href="javascript:;" onclick="page(${x});">${x}</a>
						</li>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${msgPage.pageNum>msgPage.totalPage - 6/2}">
				<c:forEach var="x" begin="${msgPage.totalPage +1 - 6}"
					end="${msgPage.totalPage}">
					<c:if test="${msgPage.pageNum==x}">
						<li class="active"><a href="#">${x}</a>
						</li>
					</c:if>
					<c:if test="${msgPage.pageNum!=x}">
						<li><a href="javascript:;" onclick="page(${x});">${x}</a>
						</li>
					</c:if>
				</c:forEach>
			</c:if>
		</c:if>
		<c:if test="${msgPage.totalPage<=6}">
			<c:forEach var="x" begin="1" end="${msgPage.totalPage}">
				<c:if test="${msgPage.pageNum==x}">
					<li class="active"><a href="#">${x}</a>
					</li>
				</c:if>
				<c:if test="${msgPage.pageNum!=x}">
					<li><a href="javascript:;" onclick="page(${x});">${x}</a>
					</li>
				</c:if>
			</c:forEach>
		</c:if>

		<li class="next"><a href="javascript:;" onclick="next();">»</a>
		</li>
	</ul>
	<input type="hidden" id="pageNum" name="pageNum"
		value="${msgPage.pageNum }"> <input type="hidden"
		id="totalPage" name="totalPage" value="${msgPage.totalPage }">
</div>

<script type="text/javascript">
		function prev(){
			var entity = $('#form1').serialize();
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) - 1;
			if(pageNum == 0){
				pageNum = 1;
			}
  			params.pageNum = pageNum; 
	     	var url = "${url}?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function next(){
			var entity = $('#form1').serialize();
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) + 1;
			if(pageNum >= totalPage){
				pageNum = totalPage;
			}
  			params.pageNum = pageNum; 
	     	
		    var url = "${url}?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		};
		function page(pageNum){
			var entity = $('#form1').serialize();
			var url = "${url}?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		};
		
</script>		

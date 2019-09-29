<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.page {
	margin-top: 15px;
	margin-bottom: 10px;
	font-size: 12px;
	height: auto;
	text-align: center;
	vertical-align: middle;
}

.page div.link {
	padding: 1px 3px 0px 3px;
	width: auto;
	border: 1px solid #99b4d1;
	margin: 1px 3px 1px 3px;
}

.page div.link:hover {
	padding: 1px 3px 0px 3px;
	width: auto;
	border: 1px solid #99b4d1;
	background-color: #bddadf;
	margin: 1px 3px 1px 3px;
}

.page div {
	padding: 1px 3px 0px 3px;
	width: auto;
	margin: 1px 3px 1px 3px;
}

.page div {
	display: inline;
}
</style>
<script type="text/javascript">
	function page(methed,currentPage){	   
		var entity = $('#sample_1').serialize();
		var pageNumber = $("#nowPage").val();
		var totalPages = $("#totalPages").val();;
		var number=Number(pageNumber);
		if(isNaN(number)){
			$("#nowPage").val("");
			return;
		}
	 	var reg = /(^-?|^\+?)\d+$/;
	 	if(!reg.test(number)){
			$("#nowPage").val("");
			return;
		}
		if(number > totalPages){
			number = totalPages;
		}
		var page = 0;
		if(methed==""){
			methed = "next";
			if(number!='') 
				page=number-1;
			if(page < 1){
				page = 0;
			}
			url = "${url_p}"+"&pagerMethod="+methed+"&currentPage="+page+"&"+entity;
		}else{
			url ="${url_p}"+"&pagerMethod="+methed+"&currentPage="+currentPage+"&"+entity;
		}
		window.location.href=url;	
	}
</script>
<div class="page">
	<input id="totalPages" value="${PAGER.totalPages}" type="hidden">${PAGER.totalPages}页 /共${PAGER.totalRows}条
	<c:if test="${PAGER.currentPage>1}">
		<div>
			<a href="javascript:;" onclick="page('first',${PAGER.currentPage});">首&nbsp;&nbsp;页</a>
			<a href="javascript:;" onclick="page('previous',${PAGER.currentPage});">上一页</a>
		</div>
	</c:if>
	<c:if test="${PAGER.totalPages>6}">
		<c:if test="${PAGER.currentPage<=6/2}">
			<c:forEach var="x" begin="1" end="6">
				<c:if test="${PAGER.currentPage==x}">
					<div class="active">${x}</div>
				</c:if>
				<c:if test="${PAGER.currentPage!=x}">
					<div class="link">
						<a href="javascript:;" onclick="page('next',${x-1});">${x}</a>
					</div>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if
			test="${PAGER.currentPage>6/2&&PAGER.currentPage<=PAGER.totalPages - 6/2}">
			<c:forEach var="x" begin="${PAGER.currentPage +1 - 6/2}"
				end="${PAGER.currentPage + 6/2}">
				<c:if test="${PAGER.currentPage==x}">
					<div class="active">${x}</div>
				</c:if>
				<c:if test="${PAGER.currentPage!=x}">
					<div class="link">
						<a href="javascript:;" onclick="page('next',${x-1});">${x}</a>
					</div>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${PAGER.currentPage>requestScope.PAGER.totalPages - 6/2}">
			<c:forEach var="x" begin="${PAGER.totalPages +1 - 6}"
				end="${PAGER.totalPages}">
				<c:if test="${PAGER.currentPage==x}">
					<div class="active">${x}</div>
				</c:if>
				<c:if test="${PAGER.currentPage!=x}">
					<div class="link">
						<a href="javascript:;" onclick="page('next',${x-1});">${x}</a>
					</div>
				</c:if>
			</c:forEach>
		</c:if>
	</c:if>
	<c:if test="${PAGER.totalPages<=6}">
		<c:forEach var="x" begin="1" end="${PAGER.totalPages}">
			<c:if test="${PAGER.currentPage==x}">
				<div class="active">${x}</div>
			</c:if>
			<c:if test="${PAGER.currentPage!=x}">
				<div class="link">
					<a href="javascript:;" onclick="page('next',${x-1});">${x}</a>
				</div>
			</c:if>
		</c:forEach>
	</c:if>
	&nbsp;&nbsp;到第<input style="border:1px solid #99b4d1;" size="10"
		type="text" id="nowPage" value="${PAGER.currentPage}" />页&nbsp;&nbsp;<input
		style="border: 0px;cursor: pointer;" size="5" type="button" value="确定"
		onclick="page('',0)" />
	<c:if test="${PAGER.currentPage<PAGER.totalPages}">
		<div>
			<a href="javascript:;" onclick="page('next',${PAGER.currentPage});">下一页</a>
			<a href="javascript:;" onclick="page('last',${PAGER.currentPage});">尾&nbsp;&nbsp;页</a>
		</div>
	</c:if>
</div>
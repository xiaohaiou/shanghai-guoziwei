<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<p class="part_title2">应用中心</p>
<c:forEach items="${companys}" var="company" varStatus="status">
	
		<div class="enter" name="gs${status.index }" <c:if test="${status.index ==0}">style="display:block"</c:if> >
		<c:forEach items="${company.systems}" var="system">
			<div class="center_icon" >
				<c:if test="${empty system.sysLogoPath}">
					<c:choose>
						<c:when test="${system.sysUrl == 'BIMF'}">
							<img src="<c:url value="/img/BIM_GB.png"/>" height="80" width="80" alt="" onclick="trunToDetail(2,'${pageContext.request.contextPath}/system/menu?menu=2')">
						</c:when>
						<c:when test="${system.sysUrl == 'BIMR'}">
							<img src="<c:url value="/img/BIM_GB.png"/>" height="80" width="80" alt="" onclick="trunToDetail(7,'${pageContext.request.contextPath}/system/menu?menu=7')">
						</c:when>
						<c:otherwise>
							<img src="<c:url value="/img/BIM_GB.png"/>" height="80" width="80" alt="" onclick="openNewWindow('${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=${system.sysUrl}')">
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${not empty system.sysLogoPath}">
					<c:choose>
						<c:when test="${system.sysUrl == 'BIMF'}">
							<img src="${pageContext.request.contextPath}${system.sysLogoPath}" height="80" width="80" alt="" onclick="trunToDetail(2,'${pageContext.request.contextPath}/system/menu?menu=2')">
						</c:when>
						<c:when test="${system.sysUrl == 'BIMR'}">
							<img src="${pageContext.request.contextPath}${system.sysLogoPath}" height="80" width="80" alt="" onclick="trunToDetail(7,'${pageContext.request.contextPath}/system/menu?menu=7')">
						</c:when>
						<c:otherwise>
							<img src="${pageContext.request.contextPath}${system.sysLogoPath}" height="80" width="80" alt="" onclick="openNewWindow('${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=${system.sysUrl}')">
						</c:otherwise>
					</c:choose>
				</c:if>
				<p>${system.sysShortName}</p>
			</div>
		</c:forEach>
		
	</div>
</c:forEach>
<div class="center_btn">
	<table>
		<tr>
			<c:forEach items="${companys}" var="company111" varStatus="status">
			
					<td <c:if test="${status.index eq 0}">class="td_active"</c:if> id="gs${status.index}">${company111.companyName}</td>
				
			</c:forEach>
		</tr>
	</table>
</div>
<script type="text/javascript">
	$('.center_btn td').on('click',function(){
		$(this).addClass('td_active').siblings('td').removeClass('td_active');
		div_name = $(this).attr('id');
		$("div[name = "+ div_name +"]").show().siblings("div[class=enter]").hide();
	})
</script>


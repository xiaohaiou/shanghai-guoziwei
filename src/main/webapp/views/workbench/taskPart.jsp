<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<p class="part_title">接口统计</p>
<div class="part_body">
	<div class="detail" >
		<c:if test="${num1 == 0}">
		<img src="<c:url value="/img/left_fs.png"/>" height="60" width="60" alt=""/>
		</c:if>
		<c:if test="${num1 > 0}">
		<img src="<c:url value="/img/left_fs.png"/>" height="60" width="60" alt="" onclick="openNewWindow('${pageContext.request.contextPath}/task/instruction/taskList?num=1')">
		</c:if>
		<p>已发送指令数</p>
		<span class="num num1">${num1}</span>
	</div>
	<div class="detail" >
		<c:if test="${num3 == 0}">
		<img src="<c:url value="/img/left_js.png"/>" height="60" width="60" alt=""/>
		</c:if>
		<c:if test="${num3 > 0}">
		<img src="<c:url value="/img/left_js.png"/>" height="60" width="60" alt="" onclick="openNewWindow('${pageContext.request.contextPath}/task/instruction/taskList?num=3')">
		</c:if>
		<p>接收指令/任务数</p>
		<span class="num num2">${num3}</span>
	</div>
</div>
<div class="part_body">
	<div class="detail" >
		<c:if test="${num2 == 0 }">
			<img src="<c:url value="/img/left_zl.png"/>" height="60" width="60" alt="" onclick="">
		</c:if>
		<c:if test="${num2 > 0 }">
			<img src="<c:url value="/img/left_zl.png"/>" height="60" width="60" alt="" onclick="openNewWindow('${pageContext.request.contextPath}/task/instruction/taskList?num=2')">
		</c:if>
		<p>逾期指令数</p>
		<span class="num num3" id="s-yuqi">${num2}</span>
	</div>
	<div class="detail" >
		<c:if test="${num4 == 0}">
		<img src="<c:url value="/img/left_rw.png"/>" height="60" width="60" alt=""/>
		</c:if>
		<c:if test="${num4 > 0}">
		<img src="<c:url value="/img/left_rw.png"/>" height="60" width="60" alt="" onclick="openNewWindow('${pageContext.request.contextPath}/task/instruction/taskList?num=4')">
		</c:if>
		<p>逾期任务数</p>
		<span class="num num3">${num4}</span>
	</div>
</div>


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
	<title>table</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/home/bimShow/assets/css/table.css">
</head>
<body>
	<div class="top">
		<h1>上海公司数据分析</h1>
		<div class="top_select">
			<img src="assets/images/exit.png">
		</div>
	</div>
	<div class="line"></div>
	<div class="container">
		<div class="case">
			<div class=" financial_tree">
				<div class="financial_tree2">
					<div class="case_title_left">
						<div class="case_title_left_1">
							<div class="case_title_left_2">${name}(${number}家)</div>
						</div>
					</div>
					<div class="case_title_right">
					   <c:forEach var="financeProperty" items="${list}">
					        <ul>
							    <li><span  style='color: #00FFFF;text-decoration:none; cursor:pointer' onClick=turnToComapny('${financeProperty[3]}')>${financeProperty[0]}（${financeProperty[1]}家，${financeProperty[2]}家）</span></li>
							 </ul>
					   </c:forEach>
					</div>
				</div>
			</div>
			<div class=" company_list">
				<div class=" company_list2">
					<div class="top_select2">
						<span>公司名称</span>
					<!-- 	<select id="option">
							<option value ="">上海电气集团有限公司</option>
  							<option value ="">上海电气集团有限公司</option>
  							<option value="">上海电气集团有限公司</option>
						</select> -->
						<select onchange=turnToComapnys() id="company" name="company">
						       <c:forEach var="financeProperty" items="${list}">
								    <option value="${financeProperty[3]}" >${financeProperty[0]}</option> 
							   </c:forEach>
					     </select>
						
					</div>
					<div class="top_select2_table">
						<div class="case_title" >财务口径公司列表<span style="font-size: 12px;color: #7f8294;margin-left: 2%;">共200家</span></div>
						<table cellspacing="0" id="tr1">
							<tr>
  							  <th>公司名称</th>
  							</tr>
						</table>
					</div>
					<div class="top_select2_table">
						<div class="case_title" >产品口径公司列表<span style="font-size: 12px;color: #7f8294;margin-left: 2%;">共200家</span></div>
						<table cellspacing="0" id="tr2">
							<tr>
  							  <th>公司名称</th>
  							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<div class="clearfix" style="height: 35px;"></div>
	</div>
	<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/plugins/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/plugins/echarts/echarts.js"></script>
	<script type="text/javascript">
	var basePath = '${pageContext.request.contextPath}';
	turnToComapny("19606439db5347c48a93865f7fab1ad9");
	function turnToComapny(org){
	    	$.ajax({
				url : basePath+"/financialProperty/getFinanceData?org="+org,
				type : "POST",
				dataType: "json", 
				success : function(data) {
					var s="<tr> <th>公司名称</th></tr>";
					var s1="<tr> <th>公司名称</th></tr>";
					for (var i = 0; i < data.length; i++) {
						 s+="<tr><td>"+data[i]+"</td></tr>";
					}
					var a = data[Math.round(Math.random()*(data.length-1))];
					for (var i = 0; i < data.length; i++) {
                         if(data[i] == a){
                        	 s1+="<tr style=\"background-color:red\"><td>"+data[i]+"</td></tr>";
                         }else{
                        	 s1+="<tr><td>"+data[i]+"</td></tr>";
                         }
						 
					}
					 $("#tr1").html(s);
					 $("#tr2").html(s1);
				}
			});
	    }
	function turnToComapnys(){
		var org = $("#company").val();
		turnToComapny(org);
	}
	</script>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
		<title>工作台</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/heaader.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/font/iconfont.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		<link rel="stylesheet" href="<c:url value="/css/scroll/jquery.mCustomScrollbar.css"/>" />
		<script>
		var toArray = Array.from || function(arrayLike) {
			return [].slice.call(arrayLike)
		};
		</script>
        
        <style type="text/css">
        html, body {
            height: 100%;
            margin: 0px;
            padding: 0px;
            overflow: hidden;
        }
		.container-fluid{
			height: calc(100% - 70px);
			margin:0;
			padding:0;
		}
		.row.left_height{
			width:100%;
			margin:0;
			padding:0;
		}
		.left_panel{
			width: 84px;
		}
		.content {
			width:calc(100% - 84px);
		}
        /* 导航栏整体样式 */

        .panel-heading{
            background: #131313;
        }
        .left_height{
            height: 100%;
        }
        .left_panel{
            background: #1d3654;
            color: #a8a8a8;
            height: 100%;
            padding: 0;
            float: left;
        }
        .left_panel a{
            color: #a8a8a8;
        }
        .leftMenu h4,.leftMenu li{
            text-align: center;
            position: relative;
        }
        .leftMenu h4>span,.leftMenu li>span{
            position: absolute;
            right: 3px;
        }
        a{
            color: #aaa;
        }
        a:hover{
            color: #CFCFCF;
            text-decoration: none;
        }
        .content{
            float: right;
            height: 100%;
            background: #eee;
        }
        /* 正在展示的iframe内容的标签 */
        .list_content_show{
            background: #EEE;
            /* color: #000; */
        }
        .list_content_show a{
            color: #000;
        }
        #iframeContent{
            width: 100%;
            height: 100%;
            background: #eee;
        }
        .left_panel .iconfont{
            font-size: 40px;
    		padding: 10px 0;
   			display: inline-block;
        }
        .left_panel{
            /* position: absolute; */
            z-index: 1;
            /* overflow: hidden; */
            /* overflow-y: hidden; */
            /* overflow-x: visible; */
        }
        .panel_inner{
            padding-left: 0;
            position: relative;
            top: 0;
            background: #1d3654;
        }
        .panel_inner>li{
            position: relative;
            list-style: none;
            width: 84px;
            text-align: center;
        }
        .panel_inner>li p{
            line-height: 1.5em;
            font-size: 16px;
            margin-bottom: 0;
            margin-top: -10px;
        }
        .panel_inner>li>ul{
            position: absolute;
            left: 84px;
            top: 0;

            list-style: none;
            background: #172c44;
            font-size: 16px;
            line-height: 3em;
            padding: 0;
            text-align: left;
        }
        .panel_inner>li>.left_panel_bottom{
            top: auto;
            bottom: 0;
        }
        .ban_li_checked{
            background: #cd1b0d;
            color: #FFF;
        }
        .ban_li_checked>a{
        	color: #fff;
        }
        .left_panel .panel_choosed{
            background: #cd1b0d;
        }
        .left_panel .panel_choosed>a{
            color: #FFF;
        }
        
        /* 无法选中 */
        .dis_check{
            background: #444;
        }
        .panel_inner>.dis_check:hover{
            background: #444;
            color: #CFCFCF;
        }
        .panel_inner>.dis_check:hover a {
            color: #CFCFCF;
        }
        @media screen and ( max-width: 1440px ) {
            .left_panel .iconfont{
                font-size: 40px;
            }
            .panel_inner>li{
                position: relative;
                list-style: none;
                width: 60px;
                padding-bottom: 10px;
                text-align: center;
            }
            .panel_inner>li p{
                font-size: 12px;
            }
            .panel_inner>li>ul{
                left: 60px;
            }
            .panel_inner>li>ul>li{
                padding-left: 1.5em;
                /* word-wrap:break-word; */
                width: 15em;
            }
        }
        .l1{
            padding-bottom: 10px;
            transition: all 0.2s linear;
            cursor: default;
        }
        .l1:hover,.l2:hover{
            background: #cd1b0d;
        }
        .l1:hover>a{
            color: #fff;
        }
        .l1>a{
            display: block;
        }
        .l1>a>span{
            display: block;
            margin-top: -10px;
            padding: 0 5px;
        }
        .l2{
            transition: all 0.2s linear;
            position: relative; 
            display: flex;
            padding-left: 1.5em;
            width: 12em;
            padding-right: 1em;
        }
        .l2:hover>a{
            color: #fff;
        }
        .l2>ul{
            position: absolute;
            right: -15em;
            width: 15em;
            list-style: none;
            background: #172c44;
            top: 0;
            padding-left: 0;
        }
        .l2 li{
            padding-left: 1.5em;
            display: flex;
            padding-right: 1em;
        }
        .l2 a{
            display: inline-block;
            width: 100%;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            line-height: 3em;
        }
        .l2 li:hover{
            background: #cd1b0d;
            transition: all 0.2s linear;
        }
        .l2 li:hover a{
            color: #fff;
            transition: all 0.2s linear;
        }
        .header{
        	position: relative;
        	z-index: 2;
        }
        </style>
    </head>
    <body>
    <!-- 左边菜单界面 -->
    <!-- 头部导航 -->
    <section class="header">
           <jsp:include page="/views/public/title.jsp"></jsp:include>
        <script type="text/javascript">
			var to_def_img = function(el){
				el.src = "<c:url value="/img/head.png"/>";
			};
		</script>
    </section>
    <!-- 主体 -->
    <section class="container-fluid">
    <div class="row left_height">
        <!-- 左侧目录导航 -->
        <div class="left_panel" id="left_panel">
            <ul id="panel_inner" class="panel_inner">
            	<c:if test="${fn:contains(gzwsession_page,',bimWork_yszxbbsb,')==true || fn:contains(gzwsession_page,',bimWork_yszxbbsh,')==true ||
	                    	 fn:contains(gzwsession_page,',bimWork_yszxbbhzsb,')==true || fn:contains(gzwsession_page,',bimWork_yszxbbhzsh,')==true ||
	                    	 fn:contains(gzwsession_page,',bimWork_yswcjgscx,')==true}">
	                <li id="yujing" class="l1">
	                    <a>
	                        <i class="iconfont icon-xiugai"></i>
	                        <span>预算</span>
	                    </a>
	                    <ul class=" hidden">
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_yszxbbsb,')==true}">
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/reportbudgetenforcement/list" target="mainFrame" title="预算执行报表上报">预算执行报表上报</a></li>
	                  		</c:if>
	                  		<c:if test="${fn:contains(gzwsession_page,',bimWork_yszxbbsh,')==true}">
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/reportbudgetenforcement/examinelist" target="mainFrame" title="预算执行报表审核">预算执行报表审核</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_yszxbbhzsb,')==true}">
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/reportbudgetenforcementcollect/list" target="mainFrame" title="预算执行报表汇总上报">预算执行报表汇总上报</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_yszxbbhzsh,')==true}">
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/reportbudgetenforcementcollect/examinelist" target="mainFrame" title="预算执行报表汇总审核">预算执行报表汇总审核</a></li>
	                  		</c:if>
	                  		<c:if test="${fn:contains(gzwsession_page,',bimWork_yswcjgscx,')==true}">
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/reportbudgetenforcement/ysNoCreateCompanyList" target="mainFrame" title="预算未填报公司查询">预算未填报公司查询</a></li>
	                   		</c:if>
	                    </ul>
	                </li>
                </c:if>
                 <c:if test="${fn:contains(gzwsession_page,',bimWork_ndzdgztb,')==true || fn:contains(gzwsession_page,',bimWork_ndzdgzsh,')==true 
                			|| fn:contains(gzwsession_page,',bimWork_yblrzxztb,')==true || fn:contains(gzwsession_page,',bimWork_yblrzxzsh,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_rzxmtjqkCx,')==true || fn:contains(gzwsession_page,',bimWork_gxlrzxztb,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_gxlrzxzsh,')==true || fn:contains(gzwsession_page,',bimWork_gxxmhzCx,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_cxlrzxztb,')==true || fn:contains(gzwsession_page,',bimWork_cxlrzxzsh,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_zqlrzxztb,')==true || fn:contains(gzwsession_page,',bimWork_zqlrzxzsh,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_zqlrzhzCx,')==true || fn:contains(gzwsession_page,',bimWork_bzrzxzTb,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_bzrzxzSh,')==true || fn:contains(gzwsession_page,',bimWork_zrzxzqkCx,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_xzrzxzTb,')==true || fn:contains(gzwsession_page,',bimWork_xzrzxzSh,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_zdrzxzqkCx,')==true || fn:contains(gzwsession_page,',bimWork_zjtctb,')==true 
                        	|| fn:contains(gzwsession_page,',bimWork_zjtcsh,')==true || fn:contains(gzwsession_page,',bimWork_rzxzsjtb,')==true 
                        	|| fn:contains(gzwsession_page,',bimWork_rzxzsjsh,')==true || fn:contains(gzwsession_page,',bimWork_xjlzzxsjtb,')==true 
                        	|| fn:contains(gzwsession_page,',bimWork_xjlzzxsjsh,')==true || fn:contains(gzwsession_page,',bimWork_xjlzjhTb,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_xjlzjhSh,')==true || fn:contains(gzwsession_page,',bimWork_xjlyjhTb,')==true 
                        	|| fn:contains(gzwsession_page,',bimWork_xjlyjhSh,')==true || fn:contains(gzwsession_page,',bimWork_xjlyzxsjtb,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_xjlyzxsjsh,')==true || fn:contains(gzwsession_page,',bimWork_clfzsjtb,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_clfzsjsh,')==true || fn:contains(gzwsession_page,',bimWork_jwzjcjssjtb,')==true
                        	|| fn:contains(gzwsession_page,',bimWork_jwzjcjssjsh,')==true}">
                <li id="yujing" class="l1">
                    <a>
                        <i class="iconfont icon-qianbi"></i>
                        <span>资金</span>
                    </a>
                    <ul class=" hidden">
                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_ndzdgztb,')==true || fn:contains(gzwsession_page,',bimWork_ndzdgzsh,')==true }">
	                        <li class="l2"><a title="年度重点工作">年度重点工作</a>
	                        	 <ul class="hidden">
		                       	 	 <c:if test="${fn:contains(gzwsession_page,',bimWork_ndzdgztb,')==true}">
			                    		<li><a href="${pageContext.request.contextPath}/keywork/list" target="mainFrame" title="年度重点工作填报">年度重点工作填报</a></li>
			                    	</c:if>
			                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_ndzdgzsh,')==true}">
			                    		<li><a href="${pageContext.request.contextPath}/keywork/examinelist" target="mainFrame" title="年度重点工作审核">年度重点工作审核</a></li>
			                   		</c:if>
	                        	 </ul>
	                        </li>
                        </c:if>
                       
                        <c:if test="${fn:contains(gzwsession_page,',bimWork_zjtctb,')==true || fn:contains(gzwsession_page,',bimWork_zjtcsh,')==true}">
	                        <li class="l2"><a title="资金头寸">资金头寸</a>
	                        	<ul class="hidden">
	                        		<c:if test="${fn:contains(gzwsession_page,',bimWork_zjtctb,')==true}">
			                        	<li><a href="${pageContext.request.contextPath}/reportFundPosition/list" target="mainFrame" title="资金头寸数据填报">资金头寸数据填报</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_zjtcsh,')==true}">
			                        	<li><a href="${pageContext.request.contextPath}/reportFundPosition/examinelist" target="mainFrame" title="资金头寸数据审核">资金头寸数据审核</a></li>
			                       </c:if>
	                        	</ul>
	                        </li>
                        </c:if>
                        <c:if test="${fn:contains(gzwsession_page,',bimWork_rzxzsjtb,')==true || fn:contains(gzwsession_page,',bimWork_rzxzsjsh,')==true}">
	                        <li class="l2"><a title="融资下账">融资下账</a>
	                        	<ul class="hidden">
	                        		<c:if test="${fn:contains(gzwsession_page,',bimWork_rzxzsjtb,')==true}">
			                        	<li><a href="${pageContext.request.contextPath}/reportFinancingAccount/list" target="mainFrame" title="融资下账数据填报">融资下账数据填报</a></li>
			                       </c:if>
			                        <c:if test="${fn:contains(gzwsession_page,',bimWork_rzxzsjsh,')==true}">
			                        	<li><a href="${pageContext.request.contextPath}/reportFinancingAccount/examinelist" target="mainFrame" title="融资下账数据审核">融资下账数据审核</a></li>
			                       </c:if>
	                        	</ul>
	                        </li>
                        </c:if>
                        <c:if test="${fn:contains(gzwsession_page,',bimWork_xjlzzxsjtb,')==true || fn:contains(gzwsession_page,',bimWork_xjlzzxsjsh,')==true}">
	                        <li class="l2"><a title="现金流周执行数据">现金流周执行数据</a>
	                       	 <ul class="hidden">
	                        	<c:if test="${fn:contains(gzwsession_page,',bimWork_xjlzzxsjtb,')==true}">
	                       			<li><a href="${pageContext.request.contextPath}/reportCashflowWeeklyExecute/list" target="mainFrame" title="现金流周执行数据填报">现金流周执行数据填报</a></li>
		                        </c:if>
		                        <c:if test="${fn:contains(gzwsession_page,',bimWork_xjlzzxsjsh,')==true}">
		                       		<li><a href="${pageContext.request.contextPath}/reportCashflowWeeklyExecute/examinelist" target="mainFrame" title="现金流周执行数据审核">现金流周执行数据审核</a></li>
	                      	    </c:if>
	                      	  </ul> 
	                        </li>
                        </c:if>
                        <c:if test="${fn:contains(gzwsession_page,',bimWork_xjlzjhTb,')==true || fn:contains(gzwsession_page,',bimWork_xjlzjhSh,')==true}">
	                         <li class="l2"><a title="现金流周计划数据">现金流周计划数据</a>
	                         	<ul class="hidden">
	                        	<c:if test="${fn:contains(gzwsession_page,',bimWork_xjlzjhTb,')==true}">    
			                        <li><a href="${pageContext.request.contextPath}/moneyFlowPlanWeek/list" target="mainFrame" title="现金流周计划数据填报">现金流周计划数据填报</a></li>
			                   </c:if>
			                   <c:if test="${fn:contains(gzwsession_page,',bimWork_xjlzjhSh,')==true}">     
			                        <li><a href="${pageContext.request.contextPath}/moneyFlowPlanWeek/examinelist" target="mainFrame" title="现金流周计划数据审核">现金流周计划数据审核</a></li>
		                   		</c:if>
		                   		</ul>
	                        </li>
                        </c:if>
                        <c:if test="${fn:contains(gzwsession_page,',bimWork_xjlyjhTb,')==true || fn:contains(gzwsession_page,',bimWork_xjlyjhSh,')==true }">
	                         <li class="l2"><a title="现金流月计划数据">现金流月计划数据</a>
	                         	<ul class="hidden">
	                        	  <c:if test="${fn:contains(gzwsession_page,',bimWork_xjlyjhTb,')==true}">
			                    	 	<li><a href="${pageContext.request.contextPath}/moneyFlowPlanMonth/list" target="mainFrame" title="现金流月计划数据填报">现金流月计划数据填报</a></li>
				                   </c:if>
				                   <c:if test="${fn:contains(gzwsession_page,',bimWork_xjlyjhSh,')==true}">   
				                        <li><a href="${pageContext.request.contextPath}/moneyFlowPlanMonth/examinelist" target="mainFrame" title="现金流月计划数据审核">现金流月计划数据审核</a></li>
				                   </c:if>
				                 </ul>  
	                        </li>
                        </c:if>
                        <c:if test="${fn:contains(gzwsession_page,',bimWork_xjlyzxsjtb,')==true || fn:contains(gzwsession_page,',bimWork_xjlyzxsjsh,')==true}">
	                         <li class="l2"><a title="现金流月执行数据">现金流月执行数据</a>
	                         	<ul class="hidden">
		                        	 <c:if test="${fn:contains(gzwsession_page,',bimWork_xjlyzxsjtb,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportCashflowMonthlyExecute/list" target="mainFrame" title="现金流月执行数据填报">现金流月执行数据填报</a></li>
		                       	    </c:if>
		                       	    <c:if test="${fn:contains(gzwsession_page,',bimWork_xjlyzxsjsh,')==true}">
			                      		 <li><a href="${pageContext.request.contextPath}/reportCashflowMonthlyExecute/examinelist" target="mainFrame" title="现金流月执行数据审核">现金流月执行数据审核</a></li>
			                        </c:if>
		                        </ul>
	                        </li>
                        </c:if>
                        <c:if test="${fn:contains(gzwsession_page,',bimWork_clfzsjtb,')==true || fn:contains(gzwsession_page,',bimWork_clfzsjsh,')==true}">
	                        <li class="l2"><a title="存量负债数据">存量负债数据</a>
	                        	<ul class="hidden">
		                        	<c:if test="${fn:contains(gzwsession_page,',bimWork_clfzsjtb,')==true}">
			                      	 	<li><a href="${pageContext.request.contextPath}/reportStockLiabilities/list" target="mainFrame" title="存量负债数据填报">存量负债数据填报</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_clfzsjsh,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportStockLiabilities/examinelist" target="mainFrame" title="存量负债数据审核">存量负债数据审核</a></li>
			                       </c:if>
		                       </ul>
	                        </li>
                        </c:if>
                        <c:if test="${fn:contains(gzwsession_page,',bimWork_jwzjcjssjtb,')==true || fn:contains(gzwsession_page,',bimWork_jwzjcjssjsh,')==true }">
	                        <li class="l2"><a title="境外资金池建设数据">境外资金池建设数据</a>
	                        	<ul class="hidden">
		                        	<c:if test="${fn:contains(gzwsession_page,',bimWork_jwzjcjssjtb,')==true}">
			                        	<li><a href="${pageContext.request.contextPath}/reportOverseasCapitalPool/list" target="mainFrame" title="境外资金池建设数据填报">境外资金池建设数据填报</a></li>
			                       </c:if>
			                        <c:if test="${fn:contains(gzwsession_page,',bimWork_jwzjcjssjsh,')==true}">
			                        	<li><a href="${pageContext.request.contextPath}/reportOverseasCapitalPool/examinelist" target="mainFrame" title="境外资金池建设数据审核">境外资金池建设数据审核</a></li>
			                       </c:if>
		                       </ul>
	                        </li>
                        </c:if>
                        <c:if test="${fn:contains(gzwsession_page,',bimWork_yblrzxztb,')==true || fn:contains(gzwsession_page,',bimWork_yblrzxzsh,')==true
                        			  || fn:contains(gzwsession_page,',bimWork_rzxmtjqkCx,')==true || fn:contains(gzwsession_page,',bimWork_gxlrzxztb,')==true
                        			  || fn:contains(gzwsession_page,',bimWork_gxlrzxzsh,')==true || fn:contains(gzwsession_page,',bimWork_gxxmhzCx,')==true
                        			  || fn:contains(gzwsession_page,',bimWork_cxlrzxztb,')==true || fn:contains(gzwsession_page,',bimWork_cxlrzxzsh,')==true
                        			  || fn:contains(gzwsession_page,',bimWork_zqlrzxztb,')==true || fn:contains(gzwsession_page,',bimWork_zqlrzxzsh,')==true
                        			  || fn:contains(gzwsession_page,',bimWork_zqlrzhzCx,')==true || fn:contains(gzwsession_page,',bimWork_bzrzxzTb,')==true
                        			  || fn:contains(gzwsession_page,',bimWork_bzrzxzSh,')==true || fn:contains(gzwsession_page,',bimWork_zrzxzqkCx,')==true
                        			  || fn:contains(gzwsession_page,',bimWork_xzrzxzTb,')==true || fn:contains(gzwsession_page,',bimWork_xzrzxzSh,')==true
                        			  || fn:contains(gzwsession_page,',bimWork_zdrzxzqkCx,')==true }">
	                        <li class="l2"><a title="融资项目管理">融资项目管理</a>
	                        	<ul class="hidden" style="top: -20vh;">
		                       	 	<c:if test="${fn:contains(gzwsession_page,',bimWork_yblrzxztb,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingProjectProgress/list" target="mainFrame" title="融资项目进展数据填报">融资项目进展数据填报</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_yblrzxzsh,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingProjectProgress/examinelist" target="mainFrame" title="融资项目进展数据审核">融资项目进展数据审核</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_rzxmtjqkCx,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingProjectProgress/sumList" target="mainFrame" title="融资项目推进情况">融资项目推进情况</a></li>
			                       	</c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_gxlrzxztb,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingShare/list" target="mainFrame" title="共享类融资项目进展数据填报">共享类融资项目进展数据填报</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_gxlrzxzsh,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingShare/examinelist" target="mainFrame" title="共享类融资项目进展数据审核">共享类融资项目进展数据审核</a></li>
			                       </c:if>
			                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_gxxmhzCx,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingShare/sumList" target="mainFrame" title="共享项目最新汇总数据查询">共享项目最新汇总数据查询</a></li>
			                       	</c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_cxlrzxztb,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingInnovate/list" target="mainFrame" title="创新类融资项目进展数据填报">创新类融资项目进展数据填报</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_cxlrzxzsh,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingInnovate/examinelist" target="mainFrame" title="创新类融资项目进展数据审核">创新类融资项目进展数据审核</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_zqlrzxztb,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingBond/list" target="mainFrame" title="债券类融资项目进展数据填报">债券类融资项目进展数据填报</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_zqlrzxzsh,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingBond/examinelist" target="mainFrame" title="债券类融资项目进展数据审核">债券类融资项目进展数据审核</a></li>
			                       </c:if>
			                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_zqlrzhzCx,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingBond/sumList" target="mainFrame" title="债券类融资项目最新汇总数据查询">债券类融资项目最新汇总数据查询</a></li>
			                       	</c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_bzrzxzTb,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingWeekThis/list" target="mainFrame" title="本周融资项目进展数据填报">本周融资项目进展数据填报</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_bzrzxzSh,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingWeekThis/examinelist" target="mainFrame" title="本周融资项目进展数据审核">本周融资项目进展数据审核</a></li>
			                       </c:if>
			                       <!-- 
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_zrzxzqkCx,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingWeekThis/sumList" target="mainFrame" title="周融资下账情况">周融资下账情况</a></li>
			                       	</c:if>
			                       	 -->
				                    <c:if test="${fn:contains(gzwsession_page,',bimWork_rzxzqkCx,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingWeekThis/sumList" target="mainFrame" title="融资下账进度">融资下账进度</a></li>
			                       	</c:if>		                       	
          	
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_xzrzxzTb,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingWeekNext/list" target="mainFrame" title="下周融资项目进展数据填报">下周融资项目进展数据填报</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_xzrzxzSh,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingWeekNext/examinelist" target="mainFrame" title="下周融资项目进展数据审核">下周融资项目进展数据审核</a></li>
			                       </c:if>
			                  		 <!-- 
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_zdrzxzqkCx,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/reportFinancingWeekNext/sumList" target="mainFrame" title="周待融资下账情况">周待融资下账情况</a></li>
			                       	</c:if>			                       	
			                       	 -->
	                        	 </ul>
	                        </li>
                        </c:if>
                    </ul>
                </li>
                </c:if>
                <c:if test="${fn:contains(gzwsession_page,',bimWork_grjksjtb,')==true || fn:contains(gzwsession_page,',bimWork_grjksjsh,')==true
	                    	 || fn:contains(gzwsession_page,',bimWork_gsygxlyfcybj,')==true || fn:contains(gzwsession_page,',bimWork_grjkcqxxyl,')==true
	                    	 || fn:contains(gzwsession_page,',bimWork_grjkxxhz,')==true || fn:contains(gzwsession_page,',bimWork_yszqsjtb_nb,')==true 
	                    	 || fn:contains(gzwsession_page,',bimWork_yszqsjsh_nb,')==true || fn:contains(gzwsession_page,',bimWork_yszqmxcx_nb,')==true 
	                    	 || fn:contains(gzwsession_page,',bimWork_yszhzcx_nb,')==true || fn:contains(gzwsession_page,',bimWork_gsdeyszqcx_nb,')==true 
	                    	 || fn:contains(gzwsession_page,',bimWork_hxqydeyszqcx_nb,')==true || fn:contains(gzwsession_page,',bimWork_yszqsjtb_wb,')==true 
	                    	 || fn:contains(gzwsession_page,',bimWork_yszqsjsh_wb,')==true
	                         || fn:contains(gzwsession_page,',bimWork_yszqmxcx_wb,')==true || fn:contains(gzwsession_page,',bimWork_yszhzcx_wb,')==true
	                         || fn:contains(gzwsession_page,',bimWork_gsdeyszqcx_wb,')==true || fn:contains(gzwsession_page,',bimWork_hxqydeyszqcx_wb,')==true
	                         || fn:contains(gzwsession_page,',bimWork_cqyszqzkwcsyl_wb,')==true
	                         || fn:contains(gzwsession_page,',bimWork_jwzczbsjtb,')==true || fn:contains(gzwsession_page,',bimWork_jwzczbsjsh,')==true
	                         || fn:contains(gzwsession_page,',bimWork_hsbbsb,')==true || fn:contains(gzwsession_page,',bimWork_hsbbsh,')==true
	                         || fn:contains(gzwsession_page,',bimWork_hswcjgscx,')==true || fn:contains(gzwsession_page,',bimWork_glkjhsjtb,')==true
	                         || fn:contains(gzwsession_page,',bimWork_glkjhsjsh,')==true || fn:contains(gzwsession_page,',bimWork_zycwzbsjtb,')==true
	                         || fn:contains(gzwsession_page,',bimWork_wbdbgx,')==true || fn:contains(gzwsession_page,',bimWork_wbdbsjtb,')==true
	                         || fn:contains(gzwsession_page,',bimWork_wbdbsjsh,')==true}">
	                <li id="" class="l1">
	                    <a>
	                        <i class="iconfont icon-xiaoxi"></i>
	                        <span>核算</span>
	                    </a>
	                    <ul class=" hidden">
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_grjksjtb,')==true || fn:contains(gzwsession_page,',bimWork_grjksjsh,')==true
	                    				|| fn:contains(gzwsession_page,',bimWork_gsygxlyfcybj,')==true || fn:contains(gzwsession_page,',bimWork_grjkcqxxyl,')==true
	                    				|| fn:contains(gzwsession_page,',bimWork_grjkxxhz,')==true}">
		                        <li class="l2"><a title="个人借">个人借款</a>
		                            <ul class="hidden">
		                                <c:if test="${fn:contains(gzwsession_page,',bimWork_grjksjtb,')==true}">
					                    	<li><a href="${pageContext.request.contextPath}/reportpersonalLoanNew/list" target="mainFrame" title="核算个人借款填报">核算个人借款填报</a></li>
					                    </c:if>
					                    <c:if test="${fn:contains(gzwsession_page,',bimWork_grjksjsh,')==true}">
				                    		<li><a href="${pageContext.request.contextPath}/reportpersonalLoanNew/examinelist" target="mainFrame" title="核算个人借款审核">核算个人借款审核</a></li>
			                    	  	</c:if>
			                    	  		<c:if test="${fn:contains(gzwsession_page,',bimWork_gsygxlyfcybj,')==true}">
			                    			<li><a href="${pageContext.request.contextPath}/reportpersonalLoanNew/nearweekcompare" target="mainFrame" title="公司员工借款相邻周差异比较查询">公司员工借款相邻周差异比较查询</a></li>
			                    		</c:if>
			                    		<c:if test="${fn:contains(gzwsession_page,',bimWork_grjkcqxxyl,')==true}">
			                    			<li><a href="${pageContext.request.contextPath}/reportpersonalLoanNew/personnalovertimeDetail" target="mainFrame" title="个人借款超期信息一览">个人借款超期信息一览</a></li>
			                    		</c:if>
			                    		<c:if test="${fn:contains(gzwsession_page,',bimWork_grjkxxhz,')==true}">
			                    	  		<li><a href="${pageContext.request.contextPath}/reportpersonalLoanNew/coreComovertimeDetail" target="mainFrame" title="个人借款信息汇总查询">个人借款信息汇总查询</a></li>
			                    	  	</c:if>
			                    	</ul>
		                        </li>
	                        </c:if>
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_yszqsjtb_nb,')==true || fn:contains(gzwsession_page,',bimWork_yszqsjsh_nb,')==true
	                        			 || fn:contains(gzwsession_page,',bimWork_yszqmxcx_nb,')==true || fn:contains(gzwsession_page,',bimWork_yszhzcx_nb,')==true
	                        			 || fn:contains(gzwsession_page,',bimWork_gsdeyszqcx_nb,')==true || fn:contains(gzwsession_page,',bimWork_hxqydeyszqcx_nb,')==true}">
		                        <li class="l2"><a title="应收债权（内部）">应收债权（内部）</a>
		                            <ul class="hidden">
		                            	<c:if test="${fn:contains(gzwsession_page,',bimWork_yszqsjtb_nb,')==true}">
				                    	  	<li><a href="${pageContext.request.contextPath}/receivabledebt/list?type=0" target="mainFrame" title="应收债权(内部)数据填报">应收债权(内部)数据填报</a></li>
			                    	  	</c:if>
			                    	  	<c:if test="${fn:contains(gzwsession_page,',bimWork_yszqsjsh_nb,')==true}">
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebt/examinelist?type=0" target="mainFrame" title="应收债权(内部)数据审核">应收债权(内部)数据审核</a></li>
			                    		</c:if>
			                    		<c:if test="${fn:contains(gzwsession_page,',bimWork_yszqmxcx_nb,')==true}">
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebt/detaillist?type=0" target="mainFrame" title="应收债权(内部)明细查询">应收债权(内部)明细查询</a></li>
			                    		</c:if>
			                    		<c:if test="${fn:contains(gzwsession_page,',bimWork_yszhzcx_nb,')==true}">
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebt/collectlist?type=0" target="mainFrame" title="应收债权(内部)汇总查询">应收债权(内部)汇总查询</a></li>
			                    		</c:if>
			                    		<c:if test="${fn:contains(gzwsession_page,',bimWork_gsdeyszqcx_nb,')==true}">
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebt/orglist?type=0" target="mainFrame" title="公司大额应收债权(内部)查询">公司大额应收债权(内部)查询</a></li>
			                    		</c:if>
		                               	<c:if test="${fn:contains(gzwsession_page,',bimWork_hxqydeyszqcx_nb,')==true}">
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebt/coreorglist?type=0" target="mainFrame" title="核心企业大额应收债权(内部)查询">核心企业大额应收债权(内部)查询</a></li>
			                    		</c:if>
		                            </ul>
		                        </li>
	                        </c:if>
	                       
	                         <c:if test="${fn:contains(gzwsession_page,',bimWork_yszqsjtb_wb,')==true || fn:contains(gzwsession_page,',bimWork_yszqsjsh_wb,')==true
	                        			|| fn:contains(gzwsession_page,',bimWork_yszqmxcx_wb,')==true || fn:contains(gzwsession_page,',bimWork_yszhzcx_wb,')==true
	                        			|| fn:contains(gzwsession_page,',bimWork_gsdeyszqcx_wb,')==true || fn:contains(gzwsession_page,',bimWork_hxqydeyszqcx_wb,')==true
	                        			|| fn:contains(gzwsession_page,',bimWork_cqyszqzkwcsyl_wb,')==true }">
		                        <li class="l2"><a title="应收债权（外部）">应收债权（外部）</a>
		                            <ul class="hidden">
		                                <c:if test="${fn:contains(gzwsession_page,',bimWork_yszqsjtb_wb,')==true}">
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebtOuter/list" target="mainFrame" title="应收债权(外部)数据填报">应收债权(外部)数据填报</a></li>
				                    	</c:if>	
				                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_yszqsjsh_wb,')==true}">	
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebtOuter/examinelist" target="mainFrame" title="应收债权(外部)数据审核">应收债权(外部)数据审核</a></li>
				                    	</c:if>	
				                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_yszqmxcx_wb,')==true}">	
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebtOuter/detaillist" target="mainFrame" title="应收债权(外部)明细查询">应收债权(外部)明细查询</a></li>
			                    		</c:if>	
			                    		<c:if test="${fn:contains(gzwsession_page,',bimWork_yszhzcx_wb,')==true}">	
			                    			<li><a href="${pageContext.request.contextPath}/receivabledebtOuter/collectlist" target="mainFrame" title="应收债权(外部)汇总查询">应收债权(外部)汇总查询</a></li>	
				                    	</c:if>	
				                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_gsdeyszqcx_wb,')==true}">	
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebtOuter/orglist" target="mainFrame" title="公司大额应收债权(外部)查询">公司大额应收债权(外部)查询</a></li>
				                    	</c:if>	
				                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_hxqydeyszqcx_wb,')==true}">	
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebtOuter/coreorglist" target="mainFrame" title="核心企业大额应收债权(外部)查询">核心企业大额应收债权(外部)查询</a></li>
				                    	</c:if>	
				                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_cqyszqzkwcsyl_wb,')==true}">	
				                    		<li><a href="${pageContext.request.contextPath}/receivabledebtOuter/overoutlist" target="mainFrame" title="超期外部应收账款无催收进展一览">超期外部应收账款无催收进展一览</a></li>
			                    		</c:if>
		                            </ul>
		                        </li>
	                        </c:if>
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_jwzczbsjtb,')==true || fn:contains(gzwsession_page,',bimWork_jwzczbsjsh,')==true }">
		                        <li class="l2"><a title="境外资产占比">境外资产占比</a>
		                            <ul class="hidden">
		                               <c:if test="${fn:contains(gzwsession_page,',bimWork_jwzczbsjtb,')==true}">
				                        	<li><a href="${pageContext.request.contextPath}/reportOverseasAsset/list" target="mainFrame" title="境外资产占比数据填报">境外资产占比数据填报</a></li>
				                        </c:if>
				                        <c:if test="${fn:contains(gzwsession_page,',bimWork_jwzczbsjsh,')==true}">
				                   			<li><a href="${pageContext.request.contextPath}/reportOverseasAsset/examinelist" target="mainFrame" title="境外资产占比数据审核">境外资产占比数据审核</a></li>
				                   		</c:if>
		                            </ul>
		                        </li>
	                        </c:if>
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_hsbbsb,')==true || fn:contains(gzwsession_page,',bimWork_hsbbsh,')==true 
	                        	|| fn:contains(gzwsession_page,',bimWork_hswcjgscx,')==true}">
		                        <li class="l2"><a title="股权口径企业核算报表">股权口径企业核算报表</a>
		                            <ul class="hidden">
		                               <c:if test="${fn:contains(gzwsession_page,',bimWork_hsbbsb,')==true}">
				                        	<li><a href="${pageContext.request.contextPath}/reportadjust/list" target="mainFrame" title="核算报表上报">核算报表上报</a></li>
				                        </c:if>
				                        <c:if test="${fn:contains(gzwsession_page,',bimWork_hsbbsh,')==true}">
				                        	<li><a href="${pageContext.request.contextPath}/reportadjust/examinelist" target="mainFrame" title="核算报表审核">核算报表审核</a></li>
				                        </c:if>
				                         <c:if test="${fn:contains(gzwsession_page,',bimWork_hswcjgscx,')==true}">
				                        	<li><a href="${pageContext.request.contextPath}/reportadjust/noCreateCompanyList" target="mainFrame" title="核算未填报公司查询">核算未填报公司查询</a></li>
				                        </c:if>
		                            </ul>
		                        </li>
	                        </c:if>
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_glkjhsjtb,')==true || fn:contains(gzwsession_page,',bimWork_glkjhsjsh,')==true}">
		                        <li class="l2"><a title="管理口径核算数据">管理口径核算数据</a>
		                            <ul class="hidden">
		                               <c:if test="${fn:contains(gzwsession_page,',bimWork_glkjhsjtb,')==true}">
				                        	<li><a href="${pageContext.request.contextPath}/reportManageAdjust/list" target="mainFrame" title="管理口径核算数据填报">管理口径核算数据填报</a></li>
				                       	</c:if> 
				                        <c:if test="${fn:contains(gzwsession_page,',bimWork_glkjhsjsh,')==true}">
				                        	<li><a href="${pageContext.request.contextPath}/reportManageAdjust/examinelist" target="mainFrame" title="管理口径核算数据审核">管理口径核算数据审核</a></li>
				                        </c:if>
		                            </ul>
		                        </li>
	                        </c:if>
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_zycwzbsjtb,')==true}">
	                        	<li class="l2"><a href="${pageContext.request.contextPath}/mainFinancialIndicators/list" target="mainFrame" title="主要财务指标数据填报">主要财务指标数据填报</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_wbdbgx,')==true || fn:contains(gzwsession_page,',bimWork_wbdbsjtb,')==true 
	                        	|| fn:contains(gzwsession_page,',bimWork_wbdbsjsh,')==true}"> 
		                        <li class="l2"><a title="外部对标填报">外部对标填报</a>
		                            <ul class="hidden">
		                                <c:if test="${fn:contains(gzwsession_page,',bimWork_wbdbgx,')==true}"> 
				                        	<li><a href="${pageContext.request.contextPath}/reportOutBenchmark/list" target="mainFrame" title="外部对标关系">外部对标关系</a></li>
				                        </c:if>
				                        <c:if test="${fn:contains(gzwsession_page,',bimWork_wbdbsjtb,')==true}"> 
				                        	<li><a href="${pageContext.request.contextPath}/reportOutBenchmark/dataList" target="mainFrame" title="外部对标数据填报">外部对标数据填报</a></li>
				                        </c:if>
				                        <c:if test="${fn:contains(gzwsession_page,',bimWork_wbdbsjsh,')==true}"> 
				                        	<li><a href="${pageContext.request.contextPath}/reportOutBenchmark/examinelist" target="mainFrame" title="外部对标数据审核">外部对标数据审核</a></li>
				                        </c:if> 
		                            </ul>
		                        </li>
	                        </c:if> 
	                    </ul>
	                </li>
                </c:if>
               
              	<c:if test="${fn:contains(gzwsession_page,',bimWork_nssjtb,')==true || fn:contains(gzwsession_page,',bimWork_nssjsh,')==true ||
              	                          fn:contains(gzwsession_page,',bimWork_swwtbgscx,')==true || fn:contains(gzwsession_page,',bimWork_jsrwsjtb,')==true || 
              	                          fn:contains(gzwsession_page,',bimWork_jsrwsjsh,')==true || fn:contains(gzwsession_page,',bimWork_jsjesjtb,')==true || 
              	                          fn:contains(gzwsession_page,',bimWork_jsjesjsh,')==true}">
	                <li id="" class="l1">
	                    <a>
	                        <i class="iconfont icon-moneybag"></i>
	                        <span>税务</span>
	                    </a>
	                    <ul class=" hidden">
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_nssjtb,')==true || fn:contains(gzwsession_page,',bimWork_nssjsh,')==true ||
	                    	              fn:contains(gzwsession_page,',bimWork_swwtbgscx,')==true}">
		                        <li class="l2"><a title="纳税数据">纳税数据</a>
		                        	<ul class="hidden">
		                        		<c:if test="${fn:contains(gzwsession_page,',bimWork_nssjtb,')==true}">
				                       		<li><a href="${pageContext.request.contextPath}/taxPay/list" target="mainFrame" title="纳税数据填报">纳税数据填报</a></li>
				                       </c:if>
				                       <c:if test="${fn:contains(gzwsession_page,',bimWork_nssjsh,')==true}">
				                       		<li><a href="${pageContext.request.contextPath}/taxPay/examinelist" target="mainFrame" title="纳税数据审核">纳税数据审核</a></li>
				                       </c:if>
				                       <c:if test="${fn:contains(gzwsession_page,',bimWork_swwtbgscx,')==true}">
				                            <li class="l2"><a href="${pageContext.request.contextPath}/taxPay/taxNoCreateCompanyList" target="mainFrame" title="税务未填报公司查询">税务未填报公司查询</a></li>
		                        	   </c:if>
		                        	</ul>
		                        </li>
	                        </c:if>
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_jsrwsjtb,')==true || fn:contains(gzwsession_page,',bimWork_jsrwsjsh,')==true}">
		                        <li class="l2"><a title="节税任务数据">节税任务数据</a>
		                        	<ul class="hidden">
		                        		<c:if test="${fn:contains(gzwsession_page,',bimWork_jsrwsjtb,')==true}">
				                        	<li><a href="${pageContext.request.contextPath}/taxTask/list" target="mainFrame" title="节税任务数据填报">节税任务数据填报</a></li>
				                       	</c:if>
				                        <c:if test="${fn:contains(gzwsession_page,',bimWork_jsrwsjsh,')==true}">
				                        	<li><a href="${pageContext.request.contextPath}/taxTask/examinelist" target="mainFrame" title="节税任务数据审核">节税任务数据审核</a></li>
				                       </c:if>
		                        		
		                        	</ul>
		                        </li>
	                        </c:if>
	                        <li class="l2"><a title="节税金额数据">节税金额数据</a>
	                        	<ul class="hidden">
	                      			<c:if test="${fn:contains(gzwsession_page,',bimWork_jsjesjtb,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/taxSave/list" target="mainFrame" title="节税金额数据填报">节税金额数据填报</a></li>
			                       </c:if>
			                       <c:if test="${fn:contains(gzwsession_page,',bimWork_jsjesjsh,')==true}">
			                       		<li><a href="${pageContext.request.contextPath}/taxSave/examinelist" target="mainFrame" title="节税金额数据审核">节税金额数据审核</a></li>
			                       </c:if>
	                        	</ul>
	                        </li>
	                    </ul>
	                </li>
                </c:if>
                <c:if test="${fn:contains(gzwsession_page,',bimWork_cwryzzsjtb,')==true || fn:contains(gzwsession_page,',bimWork_cwryzzsjsh,')==true
                			|| fn:contains(gzwsession_page,',bimWork_ndjhjhgl,')==true || fn:contains(gzwsession_page,',bimWork_jhxmjbxxwh,')==true
	                        || fn:contains(gzwsession_page,',bimWork_jhxmsxxxwh,')==true || fn:contains(gzwsession_page,',bimWork_sqbjxmsh,')==true
	                        || fn:contains(gzwsession_page,',bimWork_ldshgsjhsx,')==true || fn:contains(gzwsession_page,',bimWork_jhzgxxwh,')==true
	                        || fn:contains(gzwsession_page,',bimWork_jhsxldcx,')==true || fn:contains(gzwsession_page,',bimWork_jhsxybcx,')==true
	                        || fn:contains(gzwsession_page,',bimWork_ndjhgsfxwtqd,')==true || fn:contains(gzwsession_page,',bimWork_ndjhzgcswtqd,')==true
	                        || fn:contains(gzwsession_page,',bimWork_ndjhgsfxwttj,')==true}">
	                <li id="" class="l1">
	                    <a>
	                        <i class="iconfont icon-three"></i>
	                        <span>稽核与人事发展</span>
	                    </a>
	                    <!-- 二级菜单字段修改  -->
	                    <ul class="hidden">
	                    	
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_ndjhjhgl,')==true || fn:contains(gzwsession_page,',bimWork_jhxmjbxxwh,')==true
	                        			|| fn:contains(gzwsession_page,',bimWork_jhxmsxxxwh,')==true || fn:contains(gzwsession_page,',bimWork_sqbjxmsh,')==true
	                        			|| fn:contains(gzwsession_page,',bimWork_ldshgsjhsx,')==true || fn:contains(gzwsession_page,',bimWork_jhzgxxwh,')==true
	                        			|| fn:contains(gzwsession_page,',bimWork_jhsxldcx,')==true || fn:contains(gzwsession_page,',bimWork_jhsxybcx,')==true
	                        			|| fn:contains(gzwsession_page,',bimWork_ndjhgsfxwtqd,')==true || fn:contains(gzwsession_page,',bimWork_ndjhzgcswtqd,')==true
	                        			|| fn:contains(gzwsession_page,',bimWork_ndjhgsfxwttj,')==true}">
		                        <li class="l2"><a title="稽核管理">稽核管理</a>
		                        	 <ul class="hidden">
		                        	 	<c:if test="${fn:contains(gzwsession_page,',bimWork_ndjhjhgl,')==true}">
				                       		<li><a href="${pageContext.request.contextPath}/inspectproject/leveltree?type=50000" target="mainFrame" title="年度稽核计划管理">年度稽核计划管理</a></li>
										</c:if>
										<c:if test="${fn:contains(gzwsession_page,',bimWork_jhxmjbxxwh,')==true}">
											<li><a href="${pageContext.request.contextPath}/inspectproject/list?type=basic" target="mainFrame" title="稽核项目基本信息维护">稽核项目基本信息维护</a></li>
										</c:if>
										<c:if test="${fn:contains(gzwsession_page,',bimWork_jhxmsxxxwh,')==true}">
											<li><a href="${pageContext.request.contextPath}/inspectproject/list?type=manage" target="mainFrame" title="稽核财务事项信息维护">稽核财务事项办理维护</a></li>
										</c:if>
										<c:if test="${fn:contains(gzwsession_page,',bimWork_sqbjxmsh,')==true}">
											<li><a href="${pageContext.request.contextPath}/inspectproject/applyCheckList" target="mainFrame" title="申请办结项目审核">申请办结项目审核</a></li>
										</c:if>
										<c:if test="${fn:contains(gzwsession_page,',bimWork_ldshgsjhsx,')==true}">
											<li><a href="${pageContext.request.contextPath}/inspectproject/leaderCheckList" target="mainFrame" title="领导审核公司稽核事项">领导审核公司稽核事项</a></li>
										</c:if>
										<c:if test="${fn:contains(gzwsession_page,',bimWork_jhzgxxwh,')==true}">
											<li><a href="${pageContext.request.contextPath}/inspectproject/changeManageList" target="mainFrame" title="稽核整改信息维护">稽核整改信息维护</a></li>
										</c:if>
										<c:if test="${fn:contains(gzwsession_page,',bimWork_jhsxldcx,')==true}">
											<li><a href="${pageContext.request.contextPath}/inspectproject/list?type=order&stype=leader" target="mainFrame" title="稽核事项领导查询">稽核事项领导查询</a></li>
										</c:if>
										<c:if test="${fn:contains(gzwsession_page,',bimWork_jhsxybcx,')==true}">
											<li><a href="${pageContext.request.contextPath}/inspectproject/list?type=order&stype=common" target="mainFrame" title="稽核事项一般查询">稽核事项一般查询</a></li>
										</c:if>
										<c:if test="${fn:contains(gzwsession_page,',bimWork_ndjhgsfxwtqd,')==true}">
											<li><a href="${pageContext.request.contextPath}/inspectproject/problemList" target="mainFrame" title="年度稽核公司发现问题清单">年度稽核公司发现问题清单</a></li>
										</c:if>
										<c:if test="${fn:contains(gzwsession_page,',bimWork_ndjhzgcswtqd,')==true}">
											<li><a href="${pageContext.request.contextPath}/inspectproject/problemTimeOutList" target="mainFrame" title="年度稽核整改问题清单">年度稽核整改问题清单</a></li>
										</c:if>
										<c:if test="${fn:contains(gzwsession_page,',bimWork_ndjhgsfxwttj,')==true}">
											<li><a href="${pageContext.request.contextPath}/inspectproject/problemStatisticList" target="mainFrame" title="年度稽核公司发现问题统计">年度稽核公司发现问题统计</a></li>
										</c:if>
		                        	 </ul>
		                        </li>
	                        </c:if>
	                        
	                        <c:if test="${fn:contains(gzwsession_page,',bimWork_cwryzzsjtb,')==true || fn:contains(gzwsession_page,',bimWork_cwryzzsjsh,')==true}">
		                        <li class="l2"><a title="财务人员资质数据">财务人员资质数据</a>
		                        	 <ul class="hidden">
		                        	 	<c:if test="${fn:contains(gzwsession_page,',bimWork_cwryzzsjtb,')==true}">
				                       		<li><a href="${pageContext.request.contextPath}/hrPersongroup/list" target="mainFrame" title="财务人员资质数据填报">财务人员资质数据填报</a></li>
				                       </c:if>
				                       <c:if test="${fn:contains(gzwsession_page,',bimWork_cwryzzsjsh,')==true}">
				                       		<li><a href="${pageContext.request.contextPath}/hrPersongroup/examinelist" target="mainFrame" title="财务人员资质数据审核">财务人员资质数据审核</a></li>
				                       </c:if>
		                        	 </ul>
		                        </li>
	                        </c:if>
	                    </ul>
	                </li>
                </c:if>
                <c:if test="${fn:contains(gzwsession_page,',bimWork_bbz,')==true}">
	                <li id="" class="l1">
	                	<a href="${pageContext.request.contextPath}/reportgroup/list" target="mainFrame">
	                		 <i class="iconfont icon-tubiaozoushitu"></i>
	                        <span>报表组</span>
	                	</a>
	                </li> 
                </c:if>
                <c:if test="${fn:contains(gzwsession_page,',bimWork_bbys,')==true}">
                	<li id="" class="l1">
                		<a href="${pageContext.request.contextPath}/reportpattend/list" target="mainFrame">
                		 	<i class="iconfont icon-line"></i>
	                        <span>报表样式</span>
                		</a>
                	</li>
                </c:if>
                <c:if test="${fn:contains(gzwsession_page,',bimWork_cwstz,')==true || fn:contains(gzwsession_page,',bimWork_cwlsstz,')==true}">
           			<li id="" class="l1">
           				<a>
           					<i class="iconfont icon-dendrogram-children"></i>
           					<span>财务树</span>
           				</a>
         				<ul class="hidden">
         					 <c:if test="${fn:contains(gzwsession_page,',bimWork_cwstz,')==true}">
         						<li class="l2"><a title="财务树" target="mainFrame" href="${pageContext.request.contextPath}/tree/leveltree?type=50000">财务树</a></li>
         					</c:if>
         					<c:if test="${fn:contains(gzwsession_page,',bimWork_cwlsstz,')==true}">
         						<li class="l2"><a title="财务历史树" target="mainFrame" href="${pageContext.request.contextPath}/treeHistory/leveltree?type=50000">财务历史树</a></li>
         					</c:if>
         				</ul>
           				
           			</li>
           		</c:if>
              	<c:if test="${fn:contains(gzwsession_page,',bimWork_zsk_cwgl,')==true}">
               	    <li id="" class="l1">
               	    	<a href="${pageContext.request.contextPath}/knowledgeStoreHouse/list?type=cw" target="mainFrame">
               	    		<i class="iconfont icon-danganguanli"></i>
               	    		<span>知识库</span>
               	    	</a>
               	    </li>
               	</c:if> 	
                <li id="" class="l1">
             	   	<a href="${pageContext.request.contextPath}/reportbudgetenforcement/leveltree?type=50000" target="mainFrame">
             	    	<i class="iconfont icon-dendrogram-children"></i>
             	    		<span>财务树查询</span>
             	    </a>
               </li>                    
            </ul>
        </div>
        <!-- 内容 -->
        <div class="content">
            <c:if test="${ !empty  gzwsession_page}">
				<iframe id="mainFrame" name="mainFrame" scrolling="auto" src="" frameborder="0" style="height: 100%;padding: 0px; width: 100%;display:block;">
				</iframe>
			</c:if>
			<c:if test="${ empty  gzwsession_page}">
				暂无权限，请联系管理员
			</c:if>
        </div>
    </div>
    </section>
    
	<script>
		// 整页面缩放 仅针对 pc 模拟移动设备viewpoint标签效果
		if(!/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){
			// 设计宽度  1920
			var resize_to_width = 1920;
			var doc = document.documentElement;
			doc.style.height = '100%'
			doc.style.width = '100%'
			// 手动计算缩放比例, 使之适应部分缩放范围
			var calc_scale = function(){
				// 清除滚动条
				doc.style.overflow = 'hidden'
				if(doc.offsetWidth > resize_to_width*2 || doc.offsetWidth < 1024){
					doc.style.overflow = 'auto'
					return
				}
				var scale = doc.offsetWidth/resize_to_width
				
				var body = document.body;
				body.style.width = resize_to_width + 'px';
				body.style['transform-origin'] = '0 0'
				body.style.transform = 'scale('+scale+')'
				
				// 调整高度 仅当页面为整屏幕设计
				var should_be_height = resize_to_width*(doc.offsetHeight/doc.offsetWidth);
				body.style.height = should_be_height + 'px';
				
				// 恢复滚动条
				doc.style.overflow = 'auto'
			}
			window.addEventListener('load',calc_scale);
			window.addEventListener('resize',calc_scale);
			calc_scale()
		}
	</script>
    <script type="text/javascript" src="<c:url value="/js/jquery-1.8.3.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.mousewheel.min.js"/>"></script>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
    <script type="text/javascript">
        $(function(){
            //
            var src = $(".l2 a")[0].getAttribute("href")||$(".l2>ul>li>a")[0].getAttribute("href");
            $("#mainFrame").attr("src",src);
            $(".panel_inner>li>ul>li").on("click",function(){
                var ban_class = $(this)[0].className;
                var class_arr=ban_class.split(" ");
                // console.log(ban_class.split(" "))
                //$(".content iframe").attr('src', 'views/'+class_arr[0]+".html");
                $(".panel_inner>li>ul>li").each(function() {
                    $(".panel_inner>li>ul>li").removeClass('ban_li_checked')
                });
                $(this).addClass('ban_li_checked');
                $(this).parent().parent().addClass('panel_choosed').siblings('li').removeClass('panel_choosed');
            })
            //首页跳转 
             $("#shouye").on("click",function(){
                var ban_class = $(this)[0].className;
                var class_arr=ban_class.split(" ");
                // console.log(ban_class.split(" "))
                $(".content iframe").attr('src', 'views/shouye.html');
                $(".panel_inner>li>ul>li").each(function() {
                    $(".panel_inner>li>ul>li").removeClass('ban_li_checked')
                });
                $(this).addClass('panel_choosed').siblings('li').removeClass('panel_choosed');
            })

            // 滚动开始
            var get_scroll = 
            // $('#panel_inner').on("hover",function(){
            //     console.log("asd")
            // })
        
            $('#panel_inner').on('mousewheel', function(event, delta) {
                if($("#left_panel").height()>=$("#panel_inner").height()){
                    //console.log("123");
                    return;
                }
                var dir = delta > 0 ? 'Up' : 'Down';
                //console.log(delta)
                var top = Number($('#panel_inner').css('top').slice(0, -2));
                // console.log(-$('#panel_inner').height()+102*4);
                if (dir == 'Down') {
                    if(top<=(-$('#panel_inner').height()+102*4)){
                        return
                    }else{
                        top-=102;
                        $('#panel_inner:not(:animated)').animate({
                            "top": top+"px"},
                            200, function() {
                                // console.log("up",top);
                        });
                    }
                } else {
                    if(top>=0){
                        return
                    }else{
                        top+=102;
                        $('#panel_inner:not(:animated)').animate({
                            "top": top+"px"
                            },200, function() {
                                // console.log("down",top);
                        });
                    }
                }
                return false;
            });
            // 滚动结束
            // 鼠标移动到菜单上的样式调整
            $("#panel_inner>li").hover(function() {
                $(this).children("ul").removeClass('hidden');
                $(".l2").hover(function() {
                    $(this).children("ul").removeClass('hidden');
                    
                }, function(){
                    $(this).children("ul").addClass('hidden');
                });
            }, function(){
                $(this).children("ul").addClass('hidden');
            });
                // 检查二级菜单是否需要滚动
                /*if($("."+ban_id).height()>=$(".left_panel").height()*0.5){
                    $("."+ban_id).on('mousewheel', function(event, delta) {
                        var dir = delta > 0 ? 'Up' : 'Down';
                        var top = Number($("."+ban_id).css('top').slice(0, -2));
                        // console.log(-$('#panel_inner').height()+102*4);
                        $("."+ban_id).height("500px");
                        if (dir == 'Down') {
                            if(top<=(-$('#panel_inner').height()+102*4)){
                                return
                            }else{
                                top-=102;
                                $('#panel_inner:not(:animated)').animate({
                                    "top": top+"px"},
                                    200, function() {
                                        // console.log("up",top);
                                });
                            }
                        } else {
                            if(top>=0){
                                return
                            }else{
                                top+=102;
                                $('#panel_inner:not(:animated)').animate({
                                    "top": top+"px"
                                    },200, function() {
                                        // console.log("down",top);
                                });
                            }
                        }
                        return false;
                    });     
                }
            }, function() {
                var ban_id = $(this)[0].id;
                $("."+ban_id).addClass('hidden'); */
            $("a,l2").on("click",function(){
            	if(!$(this).attr("href")){
            		return
            	}
            })
            $(".l1").on("click",function(){
            	$(this).addClass("panel_choosed").siblings(".l1").removeClass("panel_choosed");
            	$(".ban_li_checked").removeClass("ban_li_checked")
            })
    })
    </script>
    </body>
</html>
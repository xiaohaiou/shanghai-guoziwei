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
		<link rel="stylesheet" type="text/css" href="<c:url value="/bimrFont/iconfont.css"/>">
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
            	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_civilcaseList,')==true || fn:contains(gzwsession_page,',bimWork_bimr_civilcaseCheckList,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_criminalcaseList,')==true || fn:contains(gzwsession_page,',bimWork_bimr_criminalcaseCheckList,')==true ||
					  			fn:contains(gzwsession_page,',bimWork_bimr_comprehensiveQuery,')==true || fn:contains(gzwsession_page,',bimWork_bimr_civilcaseQuery,')==true ||
					  			fn:contains(gzwsession_page,',bimWork_bimr_criminalcaseQuery,')==true}">
	                <li id="yujing" class="l1">
	                    <a>
	                        <i class="iconfont icon-xiugai"></i>
	                        <span>案件管理</span>
	                    </a>
	                    <ul class=" hidden">
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_civilcaseList,')==true}">
                  				<li class="l2"><a href="${pageContext.request.contextPath}/bimr/case/civilcaseList" target="mainFrame">民事案件上报</a></li>
                       		</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_civilcaseCheckList,')==true}">
                       			<li class="l2"><a href="${pageContext.request.contextPath}/bimr/case/civilcaseCheckList" target="mainFrame">民事案件审核</a></li>
                       		</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_criminalcaseList,')==true}">
                       			<li class="l2"><a href="${pageContext.request.contextPath}/bimr/case/criminalcaseList" target="mainFrame">刑事案件上报</a></li>
                  			</c:if>
                  			<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_criminalcaseCheckList,')==true}">
                  				<li class="l2"><a href="${pageContext.request.contextPath}/bimr/case/criminalcaseCheckList" target="mainFrame">刑事案件审核</a></li>
							</c:if>
							<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_comprehensiveQuery,')==true}">
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/case/comprehensiveQuery" target="mainFrame">案件周报生成</a></li>
							</c:if>
							<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_civilcaseQuery,')==true}">
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/case/civilcaseQuery" target="mainFrame">民事案件查询</a></li>
							</c:if>
							<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_criminalcaseQuery,')==true}">
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/case/criminalcaseQuery" target="mainFrame">刑事案件查询</a></li>
							</c:if>
	                    </ul>
	                </li>
                </c:if>
                
                <c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_insidefill,')==true || fn:contains(gzwsession_page,',bimWork_bimr_weeklylist,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_insidemaintent,')==true || fn:contains(gzwsession_page,',bimWork_bimr_insideexamine,')==true ||
					  			fn:contains(gzwsession_page,',bimWork_bimr_rectifylist,')==true || fn:contains(gzwsession_page,',bimWork_bimr_insideclosed,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_tracklist,')==true || fn:contains(gzwsession_page,',bimWork_bimr_trackchecklist,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_questionunclosed,')==true || fn:contains(gzwsession_page,',bimWork_bimr_questionanalysis,')==true ||
					  			fn:contains(gzwsession_page,',bimWork_bimr_examineFinishedList,')==true }">
	                <li id="yujing" class="l1">
	                    <a>
	                        <i class="iconfont icon-xiaoxi"></i>
	                        <span>审计管理</span>
	                    </a>
	                    <ul class=" hidden">
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_insidefill,')==true}">
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/bimr/inside/fill" target="mainFrame">审计项目填报</a></li>
	                       	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_weeklylist,')==true}">	
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/weekly/list" target="mainFrame">审计项目周报填报</a></li>
	                       	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_insidemaintent,')==true}">	
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/question/list" target="mainFrame">审计项目问题维护</a></li>
	                  		</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_insideexamine,')==true}">	
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/bimr/inside/examine" target="mainFrame">审计项目办结审核</a></li>
							</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_rectifylist,')==true}">	
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/rectify/list" target="mainFrame">审计整改发起</a></li>
							</c:if>
							<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_tracklist,')==true}">
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/rectify/trackList" target="mainFrame">审计整改反馈</a></li>
							</c:if>
							<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_trackchecklist,')==true}">
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/rectify/trackCheckList" target="mainFrame">审计整改反馈审核</a></li>
							</c:if>
							<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_examineFinishedList,')==true}">
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/inside/examineFinishedList" target="mainFrame">审计整改关闭审核</a></li>
							</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_insideclosed,')==true}">	
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/inside/closed" target="mainFrame">查询办结审计项目</a></li>
							</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_questionunclosed,')==true}">	
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/question/unclosed" target="mainFrame">未完成审计项目发现问题查询</a></li>
							</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_questionanalysis,')==true}">	
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/question/analysis" target="mainFrame">审计项目发现问题类型与成因分析查询</a></li>
							</c:if>
	                    </ul>
	                </li>
                </c:if>
                
                 <c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_contractimport,')==true || fn:contains(gzwsession_page,',bimWork_bimr_contractexamine,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_contractlook,')==true || fn:contains(gzwsession_page,',bimWork_bimr_contractdetailLook,')==true ||
					  			fn:contains(gzwsession_page,',bimWork_bimr_contractModellist,')==true || fn:contains(gzwsession_page,',bimWork_bimr_contractModellookList,')==true}">
	                <li id="yujing" class="l1">
	                    <a>
	                        <i class="iconfont icon-qianbi"></i>
	                        <span>合同管理</span>
	                    </a>
	                    <ul class=" hidden">
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_contractimport,')==true}">
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/bimr/contractmonth/import?type=import" target="mainFrame">合同台帐数据导入</a></li>
	                       	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_contractexamine,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/contractmonth/import?type=examine" target="mainFrame">合同台帐数据审核</a></li>
	                       	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_contractlook,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/contractmonth/import?type=look" target="mainFrame">合同台帐数据查询</a></li>
	                       	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_contractdetailLook,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/contractmonth/detailLook" target="mainFrame">合同信息查询</a></li>
	                  		</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_contractModellist,')==true}">		
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/bimr/contractModel/list" target="mainFrame">合同范本维护</a></li>
	                       	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_contractModellookList,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/contractModel/lookList" target="mainFrame">合同范本查看</a></li>
                       		</c:if>
	                    </ul>
	                </li>
                </c:if>
                
                 <c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskCataloglist,')==true || fn:contains(gzwsession_page,',bimWork_bimr_riskCatalogauditList,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_riskCatalogtreeView,')==true || fn:contains(gzwsession_page,',bimWork_bimr_riskCatalogScorelist,')==true||
					  			fn:contains(gzwsession_page,',bimWork_bimr_riskEventManage,')==true || fn:contains(gzwsession_page,',bimWork_bimr_riskEventExamine,')==true ||
					  			fn:contains(gzwsession_page,',bimWork_bimr_relevancelist,')==true || fn:contains(gzwsession_page,',bimWork_bimr_relevanceexamine,')==true ||
					  			fn:contains(gzwsession_page,',bimWork_bimr_relevancequery,')==true || fn:contains(gzwsession_page,',bimWork_bimr_secondlist,')==true ||
					  			fn:contains(gzwsession_page,',bimWork_bimr_examineTrack,')==true || fn:contains(gzwsession_page,',bimWork_bimr_riskEventFeedbacklist,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_riskEventFeedbackExamine,')==true || fn:contains(gzwsession_page,',bimWork_bimr_riskEventProfessionalCompany,')==true||
					  			fn:contains(gzwsession_page,',bimWork_bimr_riskEventCoreEnterprise,')==true||fn:contains(gzwsession_page,',bimWork_bimr_riskEventIndustrialGroup,')==true||
					  			fn:contains(gzwsession_page,',bimWork_bimr_riskEventFillMeasures,')==true}">
	                <li id="yujing" class="l1">
	                    <a>
	                        <i class="iconfont icon-moneybag"></i>
	                        <span>风险管理</span>
	                    </a>
	                    <ul class=" hidden" style="height:575px;overflow-y:scroll;">
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskEventManage,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/list?type=list" target="mainFrame">风险征兆事件管理</a></li>
	                       	</c:if>	
	                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskEventExamine,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/list?type=examine0" target="mainFrame">风险征兆事件审核</a></li>
	                       	</c:if>
	                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskEventProfessionalCompany,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/list?type=examine1" target="mainFrame">专业公司协调统筹</a></li>
	                       	</c:if>
	                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskEventCoreEnterprise,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/list?type=examine2" target="mainFrame">核心企业协调统筹</a></li>
	                       	</c:if>
	                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskEventIndustrialGroup,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/list?type=examine3" target="mainFrame">产业集团协调统筹</a></li>
	                       	</c:if>
	                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskEventFillMeasures,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/list?type=examine4" target="mainFrame">协调统筹措施填写</a></li>
	                       	</c:if>
	                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskEventFeedbacklist,')==true}">	
								<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/feedbacklist?type=feedback" target="mainFrame">风险征兆事件反馈</a></li>
							</c:if>
	                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskEventFeedbackExamine,')==true}">	
                 				<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/feedbacklist?type=examine" target="mainFrame">风险征兆事件反馈审核</a></li>
                 			</c:if>
							<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_relevancelist,')==true}">	
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/relevancelist?type=relevancelist" target="mainFrame">风险事件关联目录</a></li>
	                       	</c:if>	
	                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_relevanceexamine,')==true}">
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/relevancelist?type=relevanceexamine" target="mainFrame">风险事件关联目录审核</a></li>
	                    	</c:if>	
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_relevancequery,')==true}">
	                    		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/relevancelist?type=relevancequery" target="mainFrame">风险事件关联目录查询</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_secondlist,')==true}">
	                    		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskEvent/secondlist" target="mainFrame">风险管理二级风险TOP排名</a></li>
	                   		</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskCataloglist,')==true}">	
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskCatalog/list" target="mainFrame">风险目录管理</a></li>
	                       	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskCatalogauditList,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskCatalog/auditList" target="mainFrame">风险目录审核</a></li>
	                       	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskCatalogtreeView,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskCatalog/treeView" target="mainFrame">风险目录管理树</a></li>
	                       	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_riskCatalogScorelist,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/riskCatalogScore/list" target="mainFrame">二级风险目录评分</a></li>
	                       	</c:if>	
	                    </ul>
	                </li>
                </c:if>
                
                 <c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_compliancebasic,')==true || fn:contains(gzwsession_page,',bimWork_bimr_compliancemanage,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_complianceexamine,')==true || fn:contains(gzwsession_page,',bimWork_bimr_compliancereportBJList,')==true ||
					  			fn:contains(gzwsession_page,',bimWork_bimr_compliancequeryList,')==true || fn:contains(gzwsession_page,',bimWork_bimr_complianceassignList,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_compliancecorrectList,')==true || fn:contains(gzwsession_page,',bimWork_bimr_trainlist,')==true ||
					  			fn:contains(gzwsession_page,',bimWork_bimr_trainexamineList,')==true || fn:contains(gzwsession_page,',bimWork_bimr_trainlookList,')==true}">
	                <li id="yujing" class="l1">
	                    <a>
	                        <i class="iconfont icon-three"></i>
	                        <span>合规管理</span>
	                    </a>
	                    <ul class=" hidden">
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_compliancebasic,')==true}">	
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/bimr/compliance/list?type=basic" target="mainFrame">合规调查基本信息维护</a></li>
                			</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_compliancemanage,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/compliance/list?type=manage" target="mainFrame">合规调查信息填报</a></li>
                			</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_complianceexamine,')==true}">
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/compliance/list?type=examine" target="mainFrame">合规调查办结审核</a></li>
                  			</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_compliancereportBJList,')==true}">
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/compliance/reportBJList" target="mainFrame">合规调查报告维护</a></li>
                       		</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_compliancecorrectList,')==true}">
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/compliance/correctList" target="mainFrame"> 合规调查整改信息维护</a></li>
                  			</c:if>
                  			<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_compliancecorrectExamineList,')==true}">
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/compliance/correctExamineList" target="mainFrame"> 合规调查归档审核</a></li>
                  			</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_compliancequeryList,')==true}">
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/compliance/queryList" target="mainFrame">查询合规调查</a></li>
                       		</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_complianceassignList,')==true}">
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/compliance/assignList" target="mainFrame"> 合规调查分配</a></li>
                       		</c:if>	
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_trainlist,')==true}">
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/train/list" target="mainFrame">合规培训</a></li>
                       		</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_trainexamineList,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/train/examineList" target="mainFrame">合规培训审核</a></li>
                       		</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_trainlookList,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/train/lookList" target="mainFrame">合规培训查看</a></li>
	                       	</c:if>
	                    </ul>
	                </li>
                </c:if>
                      
                <c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_bylawInfoList,')==true || fn:contains(gzwsession_page,',bimWork_bimr_synRecords,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_bylawInfoTree,')==true}">
	                <li id="yujing" class="l1">
	                    <a>
	                        <i class="iconfont icon-tubiaozoushitu"></i>
	                        <span>规章制度</span>
	                    </a>
	                    <ul class=" hidden">
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_bylawInfoList,')==true}">	
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/bylaw/bylawInfoList" target="mainFrame">规章制度关联类别维护</a></li>
	                    	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_synRecords,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bylaw/synRecords" target="mainFrame">规章制度同步结果</a></li>
	                    	</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_bylawInfoTree,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bylaw/bylawInfoTree" target="mainFrame">规章制度树</a></li>
	                    	</c:if>
	                    </ul>
	                </li>
                </c:if>   
                
                <c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_dutylist,')==true || fn:contains(gzwsession_page,',bimWork_bimr_dutyexaminelist,')==true || 
					  			fn:contains(gzwsession_page,',bimWork_bimr_archivesList,')==true || fn:contains(gzwsession_page,',bimWork_bimr_archivescompanyTree,')==true||
					  			fn:contains(gzwsession_page,',bimWork_bimr_dutyListReport,')==true||fn:contains(gzwsession_page,',bimWork_bimr_dutyListReportExamine,')==true}">
	                <li id="yujing" class="l1">
	                    <a>
	                        <i class="iconfont icon-line"></i>
	                        <span>风控档案</span>
	                    </a>
	                    <ul class=" hidden">
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_dutyListReport,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/archives/companyTree/EmployeeAccountabilitylist" target="mainFrame">员工问责填报</a></li>
	                       	</c:if>	
	                       	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_dutyListReportExamine,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/archives/companyTree/EmployeeAccountabilityExaminelist" target="mainFrame">员工问责填报审核</a></li>
	                       	</c:if>	
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_dutylist,')==true}">	
	                  			<li class="l2"><a href="${pageContext.request.contextPath}/bimr/duty/list" target="mainFrame">员工问责</a></li>
                       		</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_dutyexaminelist,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/duty/examineList" target="mainFrame">员工问责审核</a></li>
                  			</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_archivesList,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/empArchives/archivesList" target="mainFrame">干部员工合规档案查询</a></li>
                  			</c:if>
                       		<c:if test="${fn:contains(gzwsession_page,',bimWork_bimr_archivescompanyTree,')==true}">		
	                       		<li class="l2"><a href="${pageContext.request.contextPath}/bimr/archives/companyTree/list" target="mainFrame">风控档案查询</a></li>
	                       	</c:if>	
	                       
	                    </ul>
	                </li>
                </c:if>  
                
                 <c:if test="${fn:contains(gzwsession_page,',bimWork_zsk_fxkz,')==true}">
           			<li id="" class="l1">
           				<a href="${pageContext.request.contextPath}/knowledgeStoreHouse/list?type=fxkz" target="mainFrame">
           					<i class="iconfont icon-danganguanli"></i>
               	    		<span>知识库</span>
           				</a>
           			</li>
           		</c:if>                               
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
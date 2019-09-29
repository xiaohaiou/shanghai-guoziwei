<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, ">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>首页</title>
		<link rel="stylesheet" href="<c:url value="/css/liu.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<script src="<c:url value="/js/jquery-1.11.0.js"/>"></script>
		<script src="<c:url value="/js/echart/echarts.min.js"/>"></script>
		<!--[if IE]>
		<script src="http://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	     <![endif]-->
	</head>
	<style>
	</style>

	<body>
		<div id="main" style="width: 600px;height:400px;"></div>
		
	</body>
	

	<script>
		 // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        
		option = {
    title: {
        text: '漏斗图',
        subtext: '纯属虚构',
        left: 'left',
        top: 'bottom'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}%"	
    },
    toolbox: {
        orient: 'vertical',
        top: 'center',
        feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
        }
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['展现','点击','访问','咨询','订单']
    },
    calculable: true,
    series: [
        {
            name: '漏斗图',
            type: 'funnel',
            width: '40%',
            height: '45%',
            left: '5%',
            top: '50%',
            data:[
                {value: 60, name:'访问'},
                {value: 30, name:'咨询'},
                {value: 10, name:'订单'},
                {value: 80, name:'点击'},
                {value: 100, name:'展现'}
            ]
        },
        {
            name: '金字塔',
            type: 'funnel',
            width: '40%',
            height: '45%',
            left: '5%',
            top: '5%',
            sort: 'ascending',
            data:[
                {value: 60, name:'访问'},
                {value: 30, name:'咨询'},
                {value: 10, name:'订单'},
                {value: 80, name:'点击'},
                {value: 100, name:'展现'}
            ]
        },
        {
            name: '漏斗图',
            type:'funnel',
            width: '40%',
            height: '45%',
            left: '55%',
            top: '5%',
            label: {
                normal: {
                    position: 'left'
                }
            },
            data:[
                {value: 60, name: '访问'},
                {value: 30, name: '咨询'},
                {value: 10, name: '订单'},
                {value: 80, name: '点击'},
                {value: 100, name: '展现'}
            ]
        },
        {
            name: '金字塔',
            type:'funnel',
            width: '40%',
            height: '45%',
            left: '55%',
            top: '50%',
            sort: 'ascending',
            label: {
                normal: {
                    position: 'left'
                }
            },
            data:[
                {value: 60, name: '访问'},
                {value: 30, name: '咨询'},
                {value: 10, name: '订单'},
                {value: 80, name: '点击'},
                {value: 100, name: '展现'}
            ]
        }
    ]
};
		  // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	</script>

</html>
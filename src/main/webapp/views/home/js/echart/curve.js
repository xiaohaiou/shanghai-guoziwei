var myChart_1 = echarts.init(document.getElementById('main'));
option = {
    title: {
        text: '请求数',
        show:false,
        textStyle: {
            fontWeight: 'normal',
            fontSize: 16,
            color: '#F1F1F3'
        },
        left: '0%'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            lineStyle: {
                color: '#233545'
            }
        }
    },
    legend: {
        icon: 'rect',
        itemWidth: 14,
        itemHeight: 5,
        itemGap: 13,
        right: '0%',
        textStyle: {
            fontSize: 12,
            color: '#F1F1F3'
        }
    },
     grid: {
        left: '-2%',
        right: '0%',
        bottom: '0%',
        containLabel: true
    },
    xAxis: [{
        axisLabel:{
            margin:-16,
        },
        type: 'category',
        boundaryGap: false,
        axisLine: {
            lineStyle: {
            fontSize: 32,
            color:'#fff'
            }
        },
        data: ['2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016', '2017']
    }],
    yAxis: [{
        type: 'value',
        name: '单位（%）',
        show:false,
        axisTick: {
            show: false
        },
        axisLine: {
            lineStyle: {
                color: '#233545'
            }
        },
        axisLabel: {
            margin: -10,
            textStyle: {
                fontSize: 14
            }
        },
        splitLine: {
            lineStyle: {
                color: '#233545'
            }
        }
    }],
    series: [{
        name: '电信',
        type: 'line',
        smooth: true,
        symbol: 'circle',
        symbolSize: 5,
        showSymbol: false,
        lineStyle: {
            normal: {
                width: 1
            }
        },
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(35, 53, 69, 0.3)'
                }, {
                    offset: 0.8,
                    color: 'rgba(35, 53, 69, 0.8)'
                }], false),
                shadowColor: 'rgba(0, 0, 0, 0.1)',
                shadowBlur: 10
            }
        },
        itemStyle: {
            normal: {
                color: 'rgba(255, 255, 255,0.2)',
                borderColor: 'rgba(255, 255, 255,0.1)',
                borderWidth: 12

            }
        },
        data: [123, 110, 45, 123, 123, 133, 122, 156, 113, 143]
    }, ]
};

myChart_1.setOption(option);
window.onresize = myChart_1.resize;


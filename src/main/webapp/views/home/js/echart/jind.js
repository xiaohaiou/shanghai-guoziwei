var MyJd_1 = echarts.init(document.getElementById('Jind'));
var data = [17];
var xMax = 100;
option = {
	tooltip: {
		show: true,
		formatter: "{b} {c}"
	},
	grid: {
		left: 0,
		top: 0,
		bottom: '0',
		right: '0'
	},
	xAxis: [{
		max: xMax,
		type: 'value',
		axisTick: {
			show: false
		},
		axisLine: {
			show: false
		},
		axisLabel: {
			show: false
		},
		splitLine: {
			show: false
		}
	}],
	yAxis: [{
		type: 'category',
		data: [''],
		nameTextStyle: {
			color: '#b7ce9e',
			fontSize: '18px'
		},
		axisLabel: {
			show: false
		},
		axisTick: {
			show: false
		},
		axisLine: {
			show: false
		}
	}],
	series: [{
		name: ' ',
		type: 'bar',
		barWidth: 12,
		silent: true,
		itemStyle: {
			normal: {
				color: 'rgba(255,255,255,0.2)',
				barBorderRadius: [15, 5, 5, 15],
			}
		},
		barGap: '-100%',
		barCategoryGap: '50%',
		data: data.map(function(d) {
			return xMax
		}),
	}, {
		name: ' ',
		type: 'bar',
		barWidth: 12,
		label: {
			normal: {
				show: false,
				position: 'right',
				formatter: '{c}%'
			}
		},
		data: [{
			value: 50,
			itemStyle: {
				normal: {
					color: '#cd1c0d',
					barBorderRadius: [15, 5, 5, 15],
				}
			}
		}]
	}]
};
MyJd_1.setOption(option);
window.onresize = MyJd_1.resize;

var MyBar_1 = echarts.init(document.getElementById('bar_2'));
option = {
	color: ['#cc8e13'],
	tooltip: {
		trigger: 'axis',
		show: false,
	},
	legend: {
		show: false,
		x: 'left',
		padding: [10, 0, 0, 30],
		itemWidth: 18,
		data: ['完成']
	},
	grid: {
		left: '-3%',
		right: '0%',
		bottom: '-5%',
		containLabel: true
	},
	xAxis: [{

		axisLabel: {
			show: false,
			interval: 0
		},
		show: true,
		barGap: 0,
		show: false,
		//  boundaryGap: false,

		padding: 0,
		barMaxWidth: 1,
		barCategoryGap: true,
		type: 'category',
		data: ['1', '2', '3', '4', '5', '6', '1', '2', '3', '4', '5', '6'],
		fontSize: 6,
		scale: true,
		lineStyle: 2,
		axisTick: {
			show: false,
			alignWithLabel: false
		}
	}],
	yAxis: [{
		show: false,
		type: 'value',
		barGap: 1,
		padding: 0,
		//barMaxWidth: 1,
		axisLabel: {
			//formatter: '{value}W'
			formatter: '{value}'
		},
	}],
	yAxis: [{
		type: 'value',
		show: false,
	}],
	series: [{
			name: '完成',
			type: 'bar',
			data: [12.0, 4.9, 7.0, 13.2, 21.6, 16.7, 9.0, 4.9, 7.0, 12, 7, 4],


		}

	]
};

MyBar_1.setOption(option);
window.onresize = MyBar_1.resize;
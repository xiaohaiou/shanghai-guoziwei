var deptid=1;//当前选择的id;

/**
 * 根据deptid 展示二级页面
 * @param deptid
 */
function sixRateInit(){
	var sixRateDate = $('.form-control').val();
	var sixRateOrgId = $('#org option:selected').val();
	var orgName = $("#org").find("option:selected").text();
	var sixRateYear;
	var sixRateMonth;
	if (!sixRateDate){
		var mydate = new Date();
		sixRateYear = "" + mydate.getFullYear();
		sixRateMonth = "" + mydate.getMonth();
	} else {
		sixRateYear = sixRateDate.slice(0,4);
		sixRateMonth =  sixRateDate.slice(5,7);
		if (sixRateMonth.indexOf("0") != -1 && sixRateMonth != '10'){
			month = sixRateMonth.slice(1);
		}
	}
	$.ajax({
		url : basePath+"/sixRate/getSixRateDate",
		type : "POST",
		data : {
			year:sixRateYear,
			month:sixRateMonth,
			deptid:deptid,
			sixRateOrgId:sixRateOrgId
		},
		success : function(data) {
			// 今年首月数据
			var firstData=data[0].firstData;
			// 今年1月总资产
			var firstM4 = null;
			var firstM4Array = new Array();
			if (firstData[0].SHOWTAB_M4 != null){
				for(var i = 0; i < firstData[0].SHOWTAB_M4.length; i++){
					var row = firstData[0].SHOWTAB_M4[i];
					if (row == null){
						continue;
					}
					var tempData={};
					if (row.org == sixRateOrgId){
						firstM4 = row.a00070;
					}
					tempData["value"] = row.a00070;
					tempData["orgid"] = row.org;
					firstM4Array.push(tempData);
				}
			}
			// 今年首月资本金
			var firstM8 = null;
			var firstM8Array = new Array();
			if (firstData[1].SHOWTAB_M8 != null){
				for(var i = 0; i < firstData[1].SHOWTAB_M8.length; i++){
					var row = firstData[1].SHOWTAB_M8[i];
					if (row == null){
						continue;
					}
					var tempData={};
					if (row.org == sixRateOrgId){
						firstM8 = row.a00073;
					}
					tempData["value"] = row.a00073;
					tempData["orgid"] = row.org;
					firstM8Array.push(tempData);
				}
			}
			// 今年首月净资产
			var firstM10 = null;
			var firstM10Array = new Array();
			if (firstData[2].SHOWTAB_M10 != null){
				for(var i = 0; i < firstData[2].SHOWTAB_M10.length; i++){
					var row = firstData[2].SHOWTAB_M10[i];
					if (row == null){
						continue;
					}
					var tempData={};
					if (row.org == sixRateOrgId){
						firstM10 = row.a00071;
					}
					tempData["value"] = row.a00071;
					tempData["orgid"] = row.org;
					firstM10Array.push(tempData);
				}
			}
			// 去年首月数据
			var lastFitstData=data[0].lastFitstData;
			// 去年1月总资产
			var lastFirstM4 = null;
			if (lastFitstData[0].SHOWTAB_M4 != null){
				for(var i = 0; i < lastFitstData[0].SHOWTAB_M4.length; i++){
					var row = lastFitstData[0].SHOWTAB_M4[i];
					if (row == null){
						continue;
					}
					if (row.org == sixRateOrgId){
						lastFirstM4 = row.a00070;
						break;
					}
				}
			}
			// 去年首月资本金
			var lastFirstM8 = null;
			if (lastFitstData[1].SHOWTAB_M8 != null){
				for(var i = 0; i < lastFitstData[1].SHOWTAB_M8.length; i++){
					var row = lastFitstData[1].SHOWTAB_M8[i];
					if (row == null){
						continue;
					}
					if (row.org == sixRateOrgId){
						lastFirstM8 = row.a00073;
						break;
					}
				}
			}
			// 去年首月净资产
			var lastFirstM10 = null;
			if (lastFitstData[2].SHOWTAB_M10 != null){
				for(var i = 0; i < lastFitstData[2].SHOWTAB_M10.length; i++){
					var row = lastFitstData[2].SHOWTAB_M10[i];
					if (row == null){
						continue;
					}
					if (row.org == sixRateOrgId){
						lastFirstM10 = row.a00071;
						break;
					}
				}
			}
			// 文字数据
			var wordData=data[0].wordData;
			loadWordData(wordData,sixRateYear,sixRateMonth,orgName,firstM4,firstM8,firstM10,lastFirstM4,lastFirstM8,lastFirstM10);
			// 图表数据
			var echarsData=data[0].echarsData;
			loadEcharsData(echarsData,sixRateYear,sixRateOrgId,firstM4,firstM8,firstM10);
			// 下属单位数据
			var orgData=data[0].orgData;

			var data1 = '';
			var data2 = '';
			if (wordData[1]["SHOWTAB_M1"].length > 0){
				data1 = wordData[1]["SHOWTAB_M1"][0]["a00003"];
				data2 = wordData[1]["SHOWTAB_M1"][0]["a00004"];
			}
			loadOrgData(orgData,data1,data2,wordData[1],firstM4Array,firstM8Array,firstM10Array);
			if (sixRateOrgId == '27534'){
				$('#sczy').show();
				$('#kupu').hide();
				$('#dichan').show();
			} else if (sixRateOrgId == '4010'){
				$('#sczy').show();
				$('#dichan').hide();
				$('#kupu').show();
			} else if (sixRateOrgId == '26655'){
				$('#sczy').show();
				$('#kupu').show();
				$('#dichan').show();
			} else {
				$('#sczy').hide();
			}
		},
		error : function() {
			alert("获取数据失败");
		}
	});

}

/**
 * 加载文字数据
 */
function loadWordData(wordData,sixRateYear,sixRateMonth,orgName,firstM4,firstM8,firstM10,lastFirstM4,lastFirstM8,lastFirstM10){
	// 当月数据
	var now = wordData[1];
	// 上月数据
	var lastMonth = wordData[2];
	// 去年数据
	var lastYear = wordData[3];
	// 去年年末数据
	var yearEnd = wordData[4];
	// 标题名
	$('#rdsc_title').html(orgName + '劳动生产率');
	$('#rdsc_date').html(sixRateYear+'年' + sixRateMonth + '月');
	// 劳动生产率
	if(now["SHOWTAB_M1"].length > 0){
		// 人均收入
		var shouruEle= '<p class="ll1_1p">人均收入:<span>';
		shouruEle += parseFloat(now["SHOWTAB_M1"][0]["a00003"]).toFixed(2);
		shouruEle += '</span>万元</p>';
		shouruEle += '<p class="ll1_2p">劳动用工人数<span>' + now["SHOWTAB_M1"][0]["a00002"] + '人</span></p>';
		shouruEle += '<p class="ll1_2p">上月<span>';
		if(lastMonth["SHOWTAB_M1"].length > 0){
			shouruEle += parseFloat(lastMonth["SHOWTAB_M1"][0]["a00003"]).toFixed(2);
		}
		shouruEle += '万元</span></p>';
		shouruEle += '<p class="ll1_3p">去年同期<span>';
		if(lastYear["SHOWTAB_M1"].length > 0){
			shouruEle += parseFloat(lastYear["SHOWTAB_M1"][0]["a00003"]).toFixed(2);
		}
		shouruEle += '万元</span></p>';
		$('#ld_shouru').html(shouruEle);
		// 人均利润
		var lirunEle = '<p class="ll1_1p">人均利润:<span>';
		lirunEle += parseFloat(now["SHOWTAB_M1"][0]["a00004"]).toFixed(2);
		lirunEle += '</span>万元</p>';
		lirunEle += '<p class="ll1_2p">劳动用工人数<span>' + now["SHOWTAB_M1"][0]["a00002"] + '人</span></p>';
		lirunEle += '<p class="ll1_2p">上月<span>';
		if(lastMonth["SHOWTAB_M1"].length > 0){
			lirunEle += parseFloat(lastMonth["SHOWTAB_M1"][0]["a00004"]).toFixed(2);
		}
		lirunEle += '万元</span></p>';
		lirunEle += '<p class="ll1_3p">去年同期<span>';
		if(lastYear["SHOWTAB_M1"].length > 0){
			lirunEle += parseFloat(lastYear["SHOWTAB_M1"][0]["a00004"]).toFixed(2);
		}
		lirunEle += '万元</span></p>';
		$('#ld_lirun').html(lirunEle);
	} else {
		// 人均收入
		var shouruEle= '<p class="ll1_1p">人均收入:<span>';
		shouruEle += '</span>-万元</p>';
		shouruEle += '<p class="ll1_2p">劳动用工人数<span>' + '-</span></p>';
		shouruEle += '<p class="ll1_2p">上月<span>';
		shouruEle += '-万元</span></p>';
		shouruEle += '<p class="ll1_3p">去年同期<span>';
		shouruEle += '-万元</span></p>';
		$('#ld_shouru').html(shouruEle);
		// 人均利润
		var lirunEle = '<p class="ll1_1p">人均利润:<span>';
		lirunEle += '</span>-万元</p>';
		lirunEle += '<p class="ll1_2p">劳动用工人数<span>' + '-</span></p>';
		lirunEle += '<p class="ll1_2p">上月<span>';
		lirunEle += '-万元</span></p>';
		lirunEle += '<p class="ll1_3p">去年同期<span>';
		lirunEle += '-万元</span></p>';
		$('#ld_lirun').html(lirunEle);
	}
	// 资产负债率
	// 标题名
	$('#zcfz_title').html(orgName + '资产负债率');
	$("#zcfz_date").html(sixRateYear+'年' + sixRateMonth + '月');
	if(now["SHOWTAB_M6"].length > 0){
		var zcfzEle = '<p class="ll2_per1">';
		zcfzEle += parseFloat(now["SHOWTAB_M6"][0]["a00067"]).toFixed(2);
		zcfzEle += '<span>%</span></p>';
		// 总负债
		zcfzEle += '<table><tr><th></th><th>当期</th><th>去年同期</th><th>上月期末</th><th>去年期末</th></tr><tr><td>总负债</td><td>'
		zcfzEle += now["SHOWTAB_M6"][0]["a00072"] + '亿元';
		zcfzEle += '</td><td>';
		if(lastYear["SHOWTAB_M6"].length > 0){
			zcfzEle += lastYear["SHOWTAB_M6"][0]["a00072"] + '亿元';
		} else {
			zcfzEle += '-';
		}
		zcfzEle += '</td><td>';
		if(lastMonth["SHOWTAB_M6"].length > 0){
			zcfzEle += lastMonth["SHOWTAB_M6"][0]["a00072"] + '亿元';
		} else {
			zcfzEle += '-';
		}
		zcfzEle += '</td><td>';
		if(yearEnd["SHOWTAB_M6"].length > 0){
			zcfzEle += yearEnd["SHOWTAB_M6"][0]["a00072"] + '亿元';
		} else {
			zcfzEle += '-';
		}
		zcfzEle += '</td></tr>';
		// 总资产
		zcfzEle += '<tr><td>总资产</td><td>';
		zcfzEle += now["SHOWTAB_M6"][0]["a00070"] + '亿元';
		zcfzEle += '</td><td>';
		if(lastYear["SHOWTAB_M6"].length > 0){
			zcfzEle += lastYear["SHOWTAB_M6"][0]["a00070"] + '亿元';
		} else {
			zcfzEle += '-';
		}
		zcfzEle += '</td><td>';
		if(lastMonth["SHOWTAB_M6"].length > 0){
			zcfzEle += lastMonth["SHOWTAB_M6"][0]["a00070"] + '亿元';
		} else {
			zcfzEle += '-';
		}
		zcfzEle += '</td><td>';
		if(yearEnd["SHOWTAB_M6"].length > 0){
			zcfzEle += yearEnd["SHOWTAB_M6"][0]["a00070"] + '亿元';
		} else {
			zcfzEle += '-';
		}
		zcfzEle += '</td></tr>';
		// 当期资产负债率
		zcfzEle += '<tr><td>资产负债率</td><td>';
		zcfzEle += parseFloat(now["SHOWTAB_M6"][0]["a00067"]).toFixed(2) + '%';
		zcfzEle += '</td><td>';
		if(lastYear["SHOWTAB_M6"].length > 0){
			zcfzEle += parseFloat(lastYear["SHOWTAB_M6"][0]["a00067"]).toFixed(2) + '%';
		} else {
			zcfzEle += '-';
		}
		zcfzEle += '</td><td>';
		if(lastMonth["SHOWTAB_M6"].length > 0){
			zcfzEle += parseFloat(lastMonth["SHOWTAB_M6"][0]["a00067"]).toFixed(2) + '%';
		} else {
			zcfzEle += '-';
		}
		zcfzEle += '</td><td>';
		if(yearEnd["SHOWTAB_M6"].length > 0){
			zcfzEle += parseFloat(yearEnd["SHOWTAB_M6"][0]["a00067"]).toFixed(2) + '%';
		} else {
			zcfzEle += '-';
		}
		zcfzEle += '</td></tr></table>';
		$('#zcfz').html(zcfzEle);
	} else {
		var zcfzEle = '<p class="ll2_per1">';
		zcfzEle += '<span>-%</span></p>';
		// 总负债
		zcfzEle += '<table><tr><th></th><th>当期</th><th>去年同期</th><th>上月期末</th><th>去年期末</th></tr><tr><td>总负债</td><td>'
		zcfzEle += '</td>';
		zcfzEle += '<td></td>';
		zcfzEle += '<td></td>';
		zcfzEle += '<td></td></tr>';
		// 总资产
		zcfzEle += '<tr><td>总资产</td><td>';
		zcfzEle += '</td>';
		zcfzEle += '<td></td>';
		zcfzEle += '<td></td>';
		zcfzEle += '<td></td></tr>';
		// 当期资产负债率
		zcfzEle += '<tr><td>资产负债率</td><td>';
		zcfzEle += '</td>';
		zcfzEle += '<td></td>';
		zcfzEle += '<td></td>';
		zcfzEle += '<td></td></tr></table>';
		$('#zcfz').html(zcfzEle);
	}

	// 资产周转率
	$('#zczz_title').html(orgName + '资产周转率');
	$("#zczz_date").html(sixRateYear+'年' + sixRateMonth + '月');
	if(now["SHOWTAB_M4"].length > 0){
		var zczzEle = '<p class="ll2_per1">';
		zczzEle += parseFloat(now["SHOWTAB_M4"][0]["a00066"]).toFixed(2);
		zczzEle += '<span>%</span></p>';
		// 营业收入
		zczzEle += '<table><tr><th></th><th>当期</th><th>去年同期</th><th>上月期末</th><th>去年期末</th></tr><tr><td>营业收入</td><td>';
		zczzEle += now["SHOWTAB_M4"][0]["a00063"] + '亿元';
		zczzEle += '</td><td>';
		if(lastYear["SHOWTAB_M4"].length > 0){
			zczzEle += lastYear["SHOWTAB_M4"][0]["a00063"] + '亿元';
		} else {
			zczzEle += '-';
		}
		zczzEle += '</td><td>';
		if(lastMonth["SHOWTAB_M4"].length > 0){
			zczzEle += lastMonth["SHOWTAB_M4"][0]["a00063"] + '亿元';
		} else {
			zczzEle += '-';
		}
		zczzEle += '</td><td>';
		if(yearEnd["SHOWTAB_M4"].length > 0){
			zczzEle += yearEnd["SHOWTAB_M4"][0]["a00063"] + '亿元';
		} else {
			zczzEle += '-';
		}
		zczzEle += '</td></tr>';
		// 平均资产总额
		zczzEle += '<tr><td>平均资产总额</td><td>';
		zczzEle += avgCal(firstM4,now["SHOWTAB_M4"][0]["a00070"]) + '亿元';
		zczzEle += '</td><td>';
		if (lastYear["SHOWTAB_M4"].length > 0){
			zczzEle += avgCal(lastFirstM4,lastYear["SHOWTAB_M4"][0]["a00070"]) + '亿元';
		}
		zczzEle += '</td><td>';
		if (lastMonth["SHOWTAB_M4"].length > 0){
			zczzEle += avgCal(firstM4,lastMonth["SHOWTAB_M4"][0]["a00070"]) + '亿元';
		}
		zczzEle += '</td><td>';
		if (yearEnd["SHOWTAB_M4"].length > 0){
			zczzEle += avgCal(lastFirstM4,yearEnd["SHOWTAB_M4"][0]["a00070"]) + '亿元';
		}
		zczzEle += '</td></tr>';
		// 当期资产周转率
		zczzEle += '<tr><td>总资产周转率</td><td>';
		zczzEle += parseFloat(now["SHOWTAB_M4"][0]["a00066"]).toFixed(2) + '%';
		zczzEle += '</td><td>';
		if(lastYear["SHOWTAB_M4"].length > 0){
			zczzEle += parseFloat(lastYear["SHOWTAB_M4"][0]["a00066"]).toFixed(2) + '%';
		} else {
			zczzEle += '-';
		}
		zczzEle += '</td><td>';
		if(lastMonth["SHOWTAB_M4"].length > 0){
			zczzEle += parseFloat(lastMonth["SHOWTAB_M4"][0]["a00066"]).toFixed(2) + '%';
		} else {
			zczzEle += '-';
		}
		zczzEle += '</td><td>';
		if(yearEnd["SHOWTAB_M4"].length > 0){
			zczzEle += parseFloat(yearEnd["SHOWTAB_M4"][0]["a00066"]).toFixed(2) + '%';
		} else {
			zczzEle += '-';
		}
		zczzEle += '</td></tr></table>';
		$('#zczz').html(zczzEle);
	} else {
		var zczzEle = '<p class="ll2_per1">';
		zczzEle += '<span>-%</span></p>';
		// 营业收入
		zczzEle += '<table><tr><th></th><th>当期</th><th>去年同期</th><th>上月期末</th><th>去年期末</th></tr><tr><td>营业收入</td><td>';
		zczzEle += '</td>';
		zczzEle += '<td></td>';
		zczzEle += '<td></td>';
		zczzEle += '<td></td></tr>';
		// 平均资产总额
		zczzEle += '<tr><td>平均资产总额</td><td>';
		zczzEle += '</td>';
		zczzEle += '<td></td>';
		zczzEle += '<td></td>';
		zczzEle += '<td></td></tr>';
		// 当期资产周转率
		zczzEle += '<tr><td>总资产周转率</td><td>';
		zczzEle += '</td>';
		zczzEle += '<td></td>';
		zczzEle += '<td></td>';
		zczzEle += '<td></td></tr></table>';
		$('#zczz').html(zczzEle);
	}
	
	// 资本利润率
	// 标题
	$('#zblr_title').html(orgName + '资本利润率');
	$("#zblr_date").html(sixRateYear+'年' + sixRateMonth + '月');
	if(now["SHOWTAB_M8"].length > 0){
		var zblrEle = '<p class="ll2_per1">';
		zblrEle += parseFloat(now["SHOWTAB_M8"][0]["a00068"]).toFixed(2);
		zblrEle += '<span>%</span></p>';
		// 净利润
		zblrEle += '<table><tr><th></th><th>当期</th><th>去年同期</th><th>上月期末</th><th>去年期末</th></tr><tr><td>净利润</td><td>';
		zblrEle += now["SHOWTAB_M8"][0]["a00065"] + '亿元';
		zblrEle += '</td><td>';
		if (lastYear["SHOWTAB_M8"].length > 0){
			zblrEle += lastYear["SHOWTAB_M8"][0]["a00065"] + '亿元';
		} else {
			zblrEle += '-';
		}
		zblrEle += '</td><td>';
		if (lastMonth["SHOWTAB_M8"].length > 0){
			zblrEle += lastMonth["SHOWTAB_M8"][0]["a00065"] + '亿元';
		} else {
			zblrEle += '-';
		}
		zblrEle += '</td><td>';
		if (yearEnd["SHOWTAB_M8"].length > 0){
			zblrEle += yearEnd["SHOWTAB_M8"][0]["a00065"] + '亿元';
		} else {
			zblrEle += '-';
		}
		zblrEle += '</td></tr>';
		// 平均资本金
		zblrEle += '<tr><td>平均资本金</td><td>';
		zblrEle += avgCal(firstM8,now["SHOWTAB_M8"][0]["a00073"]) + '亿元';
		zblrEle += '</td><td>';
		if (lastYear["SHOWTAB_M8"].length > 0){
			zblrEle += avgCal(lastFirstM8,lastYear["SHOWTAB_M8"][0]["a00073"]) + '亿元';
		}
		zblrEle += '</td><td>';
		if (lastMonth["SHOWTAB_M8"].length > 0){
			zblrEle += avgCal(firstM8,lastMonth["SHOWTAB_M8"][0]["a00073"]) + '亿元';
		}
		zblrEle += '</td><td>';
		if (yearEnd["SHOWTAB_M8"].length > 0){
			zblrEle += avgCal(lastFirstM8,yearEnd["SHOWTAB_M8"][0]["a00073"]) + '亿元';
		}
		zblrEle += '</td></tr>';
		// 当期资本利润率
		zblrEle += '<tr><td>资本利润率</td><td>';
		zblrEle += parseFloat(now["SHOWTAB_M8"][0]["a00068"]).toFixed(2) + '%';
		zblrEle += '</td><td>';
		if (lastYear["SHOWTAB_M8"].length > 0){
			zblrEle += parseFloat(lastYear["SHOWTAB_M8"][0]["a00068"]).toFixed(2) + '%';
		} else {
			zblrEle += '-';
		}
		zblrEle += '</td><td>';
		if (lastMonth["SHOWTAB_M8"].length > 0){
			zblrEle += parseFloat(lastMonth["SHOWTAB_M8"][0]["a00068"]).toFixed(2) + '%';
		} else {
			zblrEle += '-';
		}
		zblrEle += '</td><td>';
		if (yearEnd["SHOWTAB_M8"].length > 0){
			zblrEle += parseFloat(yearEnd["SHOWTAB_M8"][0]["a00068"]).toFixed(2) + '%';
		} else {
			zblrEle += '-';
		}
		zblrEle += '</td></tr></table>';
		$('#zblr').html(zblrEle);
	} else {
		var zblrEle = '<p class="ll2_per1">';
		zblrEle += '<span>-%</span></p>';
		// 净利润
		zblrEle += '<table><tr><th></th><th>当期</th><th>去年同期</th><th>上月期末</th><th>去年期末</th></tr><tr><td>净利润</td><td>';
		zblrEle += '</td>';
		zblrEle += '<td></td>';
		zblrEle += '<td></td>';
		zblrEle += '<td></td></tr>';
		// 平均资产总额
		zblrEle += '<tr><td>平均资本金</td><td>';
		zblrEle += '</td>';
		zblrEle += '<td></td>';
		zblrEle += '<td></td>';
		zblrEle += '<td></td></tr>';
		// 当期资本利润率
		zblrEle += '<tr><td>资本利润率</td><td>';
		zblrEle += '</td>';
		zblrEle += '<td></td>';
		zblrEle += '<td></td>';
		zblrEle += '<td></td></tr></table>';
		$('#zblr').html(zblrEle);
	}

	// 净资产收益率
	// 标题
	$('#zcsy_title').html(orgName + '净资产收益率');
	$("#zcsy_date").html(sixRateYear+'年' + sixRateMonth + '月');
	if(now["SHOWTAB_M10"].length > 0){
		var zcsyEle = '<p class="ll2_per1">';
		zcsyEle += parseFloat(now["SHOWTAB_M10"][0]["a00069"]).toFixed(2);
		zcsyEle += '<span>%</span></p>';
		// 净利润
		zcsyEle += '<table><tr><th></th><th>当期</th><th>去年同期</th><th>上月期末</th><th>去年期末</th></tr><tr><td>净利润</td><td>';
		zcsyEle += now["SHOWTAB_M10"][0]["a00065"] + '亿元';
		zcsyEle += '</td><td>';
		if(lastYear["SHOWTAB_M10"].length > 0){
			zcsyEle += lastYear["SHOWTAB_M10"][0]["a00065"] + '亿元';
		} else {
			zcsyEle += '-';
		}
		zcsyEle += '</td><td>';
		if (lastMonth["SHOWTAB_M10"].length > 0){
			zcsyEle += lastMonth["SHOWTAB_M10"][0]["a00065"] + '亿元';
		} else {
			zcsyEle += '-';
		}
		zcsyEle += '</td><td>';
		if(yearEnd["SHOWTAB_M10"].length > 0){
			zcsyEle += yearEnd["SHOWTAB_M10"][0]["a00065"] + '亿元';
		} else {
			zcsyEle += '-';
		}
		zcsyEle += '</td></tr>';
		// 平均净资产
		zcsyEle += '<tr><td>平均净资产</td><td>';
		zcsyEle += avgCal(firstM10,now["SHOWTAB_M10"][0]["a00071"]) + '亿元';
		zcsyEle += '</td><td>';
		if(lastYear["SHOWTAB_M10"].length > 0){
			zcsyEle += avgCal(lastFirstM10,lastYear["SHOWTAB_M10"][0]["a00071"]) + '亿元';
		}
		zcsyEle += '</td><td>';
		if(lastMonth["SHOWTAB_M10"].length > 0){
			zcsyEle += avgCal(firstM10,lastMonth["SHOWTAB_M10"][0]["a00071"]) + '亿元';
		}
		zcsyEle += '</td><td>';
		if(yearEnd["SHOWTAB_M10"].length > 0){
			zcsyEle += avgCal(lastFirstM10,yearEnd["SHOWTAB_M10"][0]["a00071"]) + '亿元';
		}
		zcsyEle += '</td></tr>';
		// 当期净资产收益率
		zcsyEle += '<tr><td>净资产收益率</td><td>';
		zcsyEle += parseFloat(now["SHOWTAB_M10"][0]["a00069"]).toFixed(2) + '%';
		zcsyEle += '</td><td>';
		if(lastYear["SHOWTAB_M10"].length > 0){
			zcsyEle += parseFloat(lastYear["SHOWTAB_M10"][0]["a00069"]).toFixed(2) + '%';
		} else {
			zcsyEle += '-';
		}
		zcsyEle += '</td><td>';
		if (lastMonth["SHOWTAB_M10"].length > 0){
			zcsyEle += parseFloat(lastMonth["SHOWTAB_M10"][0]["a00069"]).toFixed(2) + '%';
		} else {
			zcsyEle += '-';
		}
		zcsyEle += '</td><td>';
		if(yearEnd["SHOWTAB_M10"].length > 0){
			zcsyEle += parseFloat(yearEnd["SHOWTAB_M10"][0]["a00069"]).toFixed(2) + '%';
		} else {
			zcsyEle += '-';
		}
		zcsyEle += '</td></tr></table>';
		$('#zcsy').html(zcsyEle);
	} else {
		var zcsyEle = '<p class="ll2_per1">';
		zcsyEle += '<span>-%</span></p>';
		// 净利润
		zcsyEle += '<table><tr><th></th><th>当期</th><th>去年同期</th><th>上月期末</th><th>去年期末</th></tr><tr><td>净利润</td><td>';
		zcsyEle += '</td>';
		zcsyEle += '<td></td>';
		zcsyEle += '<td></td>';
		zcsyEle += '<td></td></tr>';
		// 平均净资产
		zcsyEle += '<tr><td>平均净资产</td><td>';
		zcsyEle += '</td>';
		zcsyEle += '<td></td>';
		zcsyEle += '<td></td>';
		zcsyEle += '<td></td></tr>';
		// 当期净资产收益率
		zcsyEle += '<tr><td>净资产收益率</td><td>';
		zcsyEle += '</td>';
		zcsyEle += '<td></td>';
		zcsyEle += '<td></td>';
		zcsyEle += '<td></td></tr></table>';
		$('#zcsy').html(zcsyEle);
	}

	// 市场占有率
	// 标题
	$('#sczy_title').html(orgName + '市场占有率');
	$("#sczy_date").html(sixRateYear+'年' + sixRateMonth + '月');
	var nowM2 = now["SHOWTAB_M2"];
	var nowKupu = '-';
	var nowDichan = '-';
	var lastKupu = '同比 - %';
	var lastDichan = '同比 - %';
	var lastYearM2 = lastYear["SHOWTAB_M2"];
	for (var i = 0;i<nowM2.length;i++){
		var row=nowM2[i];
		// 酷铺
		if (row["org"] == "4010"){
			if (row["a00008"] || row["a00008"] === 0){
				nowKupu += parseFloat(row["a00008"]);
				$('#sczy_kp').html('<span class="font_g">' + parseFloat(row["a00008"]).toFixed(2) +'</span>%');
			} else {
				$('#sczy_kp').html('-');
			}
		}
		// 海航地产
		if (row["org"] == "27534"){
			if (row["a00008"] || row["a00008"] === 0){
				nowDichan += parseFloat(row["a00008"]);
				$('#sczy_hhdc').html('<span class="font_g">' + parseFloat(row["a00008"]).toFixed(2) + '</span>%');
			} else {
				$('#sczy_hhdc').html('-');
			}
		}
	}
	if (nowM2.length == 0){
		$('#sczy_kp').html('-%');
		$('#sczy_hhdc').html('-%');
	}
	for (var i = 0;i<lastYearM2.length;i++){
		var row=lastYearM2[i];
		// 酷铺
		if (nowKupu!= '-' && row["org"] == "4010"){
			if (row["a00008"] || row["a00008"] === 0){
				nowKupu=parseFloat(nowKupu - row["a00008"]).toFixed(2);
				if (nowKupu > 0){
					lastKupu = '同比<span class="font_r">+' + nowKupu + '</span>%';
				} else {
					lastKupu = '同比<span class="font_g">' + nowKupu + '</span>%';
				}
			}
		}
		// 海航地产
		if (nowDichan!= '-' && row["org"] == "27534"){
			if (row["a00008"] || row["a00008"] === 0){
				nowDichan=parseFloat(nowDichan - row["a00008"]).toFixed(2);
				if (nowDichan > 0){
					lastDichan = '同比<span class="font_r">+' + nowDichan + '</span>%';
				} else {
					lastDichan = '同比<span class="font_g">' + nowDichan + '</span>%';
				}
			}
		}
	}
	$('#sczy_last_kp').html(lastKupu);
	$('#sczy_last_hhdc').html(lastDichan);
}

/**
 * 加载图表数据
 */
function loadEcharsData(echarsData,sixRateYear,sixRateOrgId,firstM4,firstM8,firstM10){
	var titledata=echarsData[0];
	var valdata=echarsData[1];
	// 劳动生产率折线图
	if(valdata["SHOWTAB_M1"].length > 0){
		$('#ldsc_picTitle').html(valdata["SHOWTAB_M1"][0].orgnm + sixRateYear + '年劳动生产率趋势图');
	} else {
		$('#ldsc_picTitle').html('');
	}
	create_ld_line(titledata.SHOWTAB_M1,valdata.SHOWTAB_M1);
	// 资产4率柱状图
	if(valdata["SHOWTAB_M6"].length > 0){
		$('#zcfz_picTitle').html(valdata["SHOWTAB_M6"][0].orgnm + sixRateYear + '年资产负债率趋势图');
	} else {
		$('#zcfz_picTitle').html('');
	}
	create_barLine1(titledata.SHOWTAB_M6,valdata.SHOWTAB_M6);
	if(valdata["SHOWTAB_M4"].length > 0){
		$('#zczz_picTitle').html(valdata["SHOWTAB_M4"][0].orgnm + sixRateYear + '年资产周转率趋势图');
	} else {
		$('#zczz_picTitle').html('');
	}
	create_barLine2(titledata.SHOWTAB_M4,valdata.SHOWTAB_M4,firstM4);
	if(valdata["SHOWTAB_M8"].length > 0){
		$('#zblr_picTitle').html(valdata["SHOWTAB_M8"][0].orgnm + sixRateYear + '年资本利润率趋势图');
	} else {
		$('#zblr_picTitle').html('');
	}
	create_barLine3(titledata.SHOWTAB_M8,valdata.SHOWTAB_M8,firstM8);
	if(valdata["SHOWTAB_M10"].length > 0){
		$('#zcsy_picTitle').html(valdata["SHOWTAB_M10"][0].orgnm + sixRateYear + '年净资产收益率趋势图');
	} else {
		$('#zcsy_picTitle').html('');
	}
	create_barLine4(titledata.SHOWTAB_M10,valdata.SHOWTAB_M10,firstM10);
	if(valdata["SHOWTAB_M2"].length > 0){
		// 供销大集柱状折线图
		if (sixRateOrgId == '26655' || sixRateOrgId == '4010'){
			$('#kp_picTitle').html(sixRateYear + '年酷铺市场占有率趋势图');
			create_sczy_line1(valdata.SHOWTAB_M2);
		}
		// 海航地产柱状折线图
		if (sixRateOrgId == '26655' || sixRateOrgId == '27534'){
			$('#dc_picTitle').html(sixRateYear + '年海航地产市场占有率趋势图');
			create_sczy_line2(valdata.SHOWTAB_M2);
		}
	}
}

/**
 * 加载下属企业数据
 */
function loadOrgData(orgData,shouru,lirun,now,firstM4Array,firstM8Array,firstM10Array){
	// 人均收入
	create_pie1(orgData[1].SHOWTAB_M1,shouru,now);
	// 人均利润
	create_pie2(orgData[1].SHOWTAB_M1,lirun,now);
	// 资产负债率
	var zcfzdata=orgData[1].SHOWTAB_M6;
	var zcfzEle = '<tr><th>序号</th><th>机构</th><th>当期总负债</th><th>当期总资产</th><th>当期资产负债率</th></tr>';
	if (zcfzdata.length == 0){
		zcfzEle += '<tr><td colspan=\"5\" align=\"center\">无数据</td></tr>';
	}
	for(var i = 0; i < zcfzdata.length; i++){
		zcfzEle += '<tr><td>' + (i+1) + '</td>';
		zcfzEle += '<td>' + zcfzdata[i].orgnm + '</td>';
		if (zcfzdata[i].a00072 || zcfzdata[i].a00072 === 0){
			zcfzEle += '<td>' + zcfzdata[i].a00072 + '亿元</td>';
		} else {
			zcfzEle += '<td>-</td>';
		}
		if (zcfzdata[i].a00070 || zcfzdata[i].a00070 === 0){
			zcfzEle += '<td>' + zcfzdata[i].a00070 + '亿元</td>';
		} else {
			zcfzEle += '<td>-</td>';
		}
		if (zcfzdata[i].a00067 || zcfzdata[i].a00067 === 0){
			zcfzEle += '<td>' + parseFloat(zcfzdata[i].a00067).toFixed(2) + '%</td></tr>';
		} else {
			zcfzEle += '<td>-</td></tr>';
		}
	}
	$('#zcfz_org').html(zcfzEle);
	// 资产周转率
	var zczzdata=orgData[1].SHOWTAB_M4;
	var zczzEle = '<tr><th>序号</th><th>机构</th><th>当期营业收入</th><th>当期平均资产总额</th><th>当期总资产周转率</th></tr>';
	if (zczzdata.length == 0){
		zczzEle += '<tr><td colspan=\"5\" align=\"center\">无数据</td></tr>';
	}
	for(var i = 0; i < zczzdata.length; i++){
		zczzEle += '<tr><td>' + (i+1) + '</td>';
		zczzEle += '<td>' + zczzdata[i].orgnm + '</td>';
		if(zczzdata[i].a00063 || zczzdata[i].a00063 === 0){
			zczzEle += '<td>' + zczzdata[i].a00063 + '亿元</td>';
		} else {
			zczzEle += '<td>-</td>';
		}
		zczzEle += '<td>';
		var last='-';
		for(var j = 0; j < firstM4Array.length; j++){
			if (firstM4Array[j].orgid == zczzdata[i].org){
				last = avgCal(zczzdata[i].a00070,firstM4Array[j].value) + '亿元';
				break;
			}
		}
		zczzEle += last;
		zczzEle += '</td>';
		if(zczzdata[i].a00066 || zczzdata[i].a00066 === 0){
			zczzEle += '<td>' + parseFloat(zczzdata[i].a00066).toFixed(2) + '%</td></tr>';
		} else {
			zczzEle += '<td>-</td></tr>';
		}
	}
	$('#zczz_org').html(zczzEle);
	// 资本利润率
	var zblrdata=orgData[1].SHOWTAB_M8;
	var zblrEle = '<tr><th>序号</th><th>机构</th><th>当期净利润</th><th>当期平均资本金</th><th>当期资本利润率</th></tr>';
	if (zblrdata.length == 0){
		zblrEle += '<tr><td colspan=\"5\" align=\"center\">无数据</td></tr>';
	}
	for(var i = 0; i < zblrdata.length; i++){
		zblrEle += '<tr><td>' + (i+1) + '</td>';
		zblrEle += '<td>' + zblrdata[i].orgnm + '</td>';
		if(zblrdata[i].a00065 || zblrdata[i].a00065 === 0){
			zblrEle += '<td>' + zblrdata[i].a00065 + '亿元</td>';
		} else {
			zblrEle += '<td>-</td>';
		}
		zblrEle += '<td>';
		var last='-';
		for(var j = 0; j < firstM8Array.length; j++){
			if (firstM8Array[j].orgid == zblrdata[i].org){
				last = avgCal(zblrdata[i].a00073,firstM8Array[j].value) + '亿元';
				break;
			}
		}
		zblrEle += last;
		zblrEle += '</td>';
		if(zblrdata[i].a00068 || zblrdata[i].a00068 === 0){
			zblrEle += '<td>' + parseFloat(zblrdata[i].a00068).toFixed(2) + '%</td></tr>';
		} else {
			zblrEle += '<td>-</td></tr>';
		}
	}
	$('#zblr_org').html(zblrEle);
	// 净资产收益率
	var zcsydata=orgData[1].SHOWTAB_M10;
	var zcsyEle = '<tr><th>序号</th><th>机构</th><th>当期净利润</th><th>当期平均净资产</th><th>当期净资产收益率</th></tr>';
	if (zcsydata.length == 0){
		zcsyEle += '<tr><td colspan=\"5\" align=\"center\">无数据</td></tr>';
	}
	for(var i = 0; i < zcsydata.length; i++){
		zcsyEle += '<tr><td>' + (i+1) + '</td>';
		zcsyEle += '<td>' + zcsydata[i].orgnm + '</td>';
		if (zcsydata[i].a00065 || zcsydata[i].a00065 === 0){
			zcsyEle += '<td>' + zcsydata[i].a00065 + '亿元</td>';
		} else {
			zcsyEle += '<td>-</td>';
		}
		zcsyEle += '<td>';
		var last='-';
		for(var j = 0; j < firstM10Array.length; j++){
			if (firstM10Array[j].orgid == zcsydata[i].org){
				last = avgCal(zcsydata[i].a00071,firstM10Array[j].value) + '亿元';
			}
		}
		zcsyEle += last;
		zcsyEle += '</td>';
		if (zcsydata[i].a00069 || zcsydata[i].a00069 === 0){
			zcsyEle += '<td>' + parseFloat(zcsydata[i].a00069).toFixed(2) + '%</td>';
		} else {
			zcsyEle += '<td>-</td>';
		}
	}
	$('#zcsy_org').html(zcsyEle);
}

// 劳动生产率趋势图
function create_ld_line(titledata,valdata){
	var xAxis=new Array(); //xAxis x轴
	xAxis = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	var series=new Array(); //series 分度数据
	var seriesSRData = new Array(); // 人均收入数据
	seriesSRData = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var seriesLRData = new Array(); // 人均利润数据
	seriesLRData = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var stack=['0','1']; // 叠加参数
	var legend=['人均收入','人均利润']; // legend 选项
	var type='line'; //bar 柱状图 line  折线图
	
	for(var i=0;i<xAxis.length;i++){
		for(var j=0;j<valdata.length;j++){
			if (valdata[j].date_m+"月" == xAxis[i]){
				seriesSRData[i] = valdata[j].a00003;
				seriesLRData[i] = valdata[j].a00004;
				break;
			}
		}
	}
	//设置series
	series.push(seriesSRData);
	series.push(seriesLRData);
	//创建Echarts
	createLineCharts("line_1",legend,xAxis,series,stack,type);
}

// 资产4率双Y轴柱线混合图
function create_barLine1(titledata,valdata){
	var xAxis=new Array(); //xAxis x轴
	xAxis = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	var series=new Array(); //series 分度数据
	var seriesDataBar1 = new Array(); // 柱型数据1
	seriesDataBar1 = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var seriesDataBar2 = new Array(); // 柱型数据2
	seriesDataBar2 = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var seriesDataline = new Array(); // 折线数据
	seriesDataline = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var legend=['总负债','总资产','资产负债率']; // legend 选项

	for(var i=0;i<xAxis.length;i++){
		for(var j=0;j<valdata.length;j++){
			if (valdata[j].date_m+"月" == xAxis[i]){
				seriesDataBar1[i] = valdata[j].a00072;
				seriesDataBar2[i] = valdata[j].a00070;
				seriesDataline[i] = valdata[j].a00067;
				break;
			}
		}
	}
	//设置series
	series.push(seriesDataBar1);
	series.push(seriesDataBar2);
	series.push(seriesDataline);
	//创建Echarts
	create4lvBarCharts("bar_1",legend,xAxis,series);
}

function create_barLine2(titledata,valdata,firstM4){
	var xAxis=new Array(); //xAxis x轴
	xAxis = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	var series=new Array(); //series 分度数据
	var seriesDataBar1 = new Array(); // 柱型数据1
	seriesDataBar1 = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var seriesDataBar2 = new Array(); // 柱型数据2
	seriesDataBar2 = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var seriesDataline = new Array(); // 折线数据
	seriesDataline = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var legend=['营业收入','平均资产总额','资产周转率']; // legend 选项

	for(var i=0;i<xAxis.length;i++){
		for(var j=0;j<valdata.length;j++){
			if (valdata[j].date_m+"月" == xAxis[i]){
				seriesDataBar1[i] = valdata[j].a00063;
				seriesDataBar2[i] = avgCal(firstM4,valdata[j].a00070);
				seriesDataline[i] = valdata[j].a00066;
				break;
			}
		}
	}
	//设置series
	series.push(seriesDataBar1);
	series.push(seriesDataBar2);
	series.push(seriesDataline);
	//创建Echarts
	create4lvBarCharts("bar_2",legend,xAxis,series);
}

function create_barLine3(titledata,valdata,firstM8){
	var xAxis=new Array(); //xAxis x轴
	xAxis = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	var series=new Array(); //series 分度数据
	var seriesDataBar1 = new Array(); // 柱型数据1
	seriesDataBar1 = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var seriesDataBar2 = new Array(); // 柱型数据2
	seriesDataBar2 = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var seriesDataline = new Array(); // 折线数据
	seriesDataline = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var legend=['净利润','平均资本金','资本利润率']; // legend 选项

	for(var i=0;i<xAxis.length;i++){
		for(var j=0;j<valdata.length;j++){
			if (valdata[j].date_m+"月" == xAxis[i]){
				seriesDataBar1[i] = valdata[j].a00065;
				seriesDataBar2[i] = avgCal(firstM8,valdata[j].a00073);
				seriesDataline[i] = valdata[j].a00068;
				break;
			}
		}
	}
	//设置series
	series.push(seriesDataBar1);
	series.push(seriesDataBar2);
	series.push(seriesDataline);
	//创建Echarts
	create4lvBarCharts("bar_3",legend,xAxis,series);
}

function create_barLine4(titledata,valdata,firstM10){
	var xAxis=new Array(); //xAxis x轴
	xAxis = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	var series=new Array(); //series 分度数据
	var seriesDataBar1 = new Array(); // 柱型数据1
	seriesDataBar1 = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var seriesDataBar2 = new Array(); // 柱型数据2
	seriesDataBar2 = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var seriesDataline = new Array(); // 折线数据
	seriesDataline = ['-','-','-','-','-','-','-','-','-','-','-','-'];
	var legend=['净利润','平均净资产','净资产收益率']; // legend 选项

	for(var i=0;i<xAxis.length;i++){
		for(var j=0;j<valdata.length;j++){
			if (valdata[j].date_m+"月" == xAxis[i]){
				seriesDataBar1[i] = valdata[j].a00065;
				seriesDataBar2[i] = avgCal(firstM10,valdata[j].a00071);
				seriesDataline[i] = valdata[j].a00069;
				break;
			}
		}
	}
	//设置series
	series.push(seriesDataBar1);
	series.push(seriesDataBar2);
	series.push(seriesDataline);
	//创建Echarts
	create4lvBarCharts("bar_4",legend,xAxis,series);
}

// 市场占有率
function create_sczy_line1(valdata){
	var xAxis=new Array(); //xAxis x轴
	var series=new Array(); //series 分度数据
	var seriesData1 = new Array(); // 市场占有率数据
	var seriesData2 = new Array(); // 目标数据
	var legend=['市场占有率','目标值']; // legend 选项
	
	for(var i=0;i<valdata.length;i++){
		if (valdata[i]["org"] == "4010"){
			var xTemp=valdata[i].date_m+"月";
			xAxis.push(xTemp);
			seriesData1.push(valdata[i].a00008);
			seriesData2.push(valdata[i].a00213);
		}
	}
	//设置series
	series.push(seriesData1);
	series.push(seriesData2);
	//创建Echarts
	createBarCharts("sczy_line_1",legend,xAxis,series);
}

function create_sczy_line2(valdata){
	var xAxis=new Array(); //xAxis x轴
	var series=new Array(); //series 分度数据
	var seriesData1 = new Array(); // 市场占有率数据
	var seriesData2 = new Array(); // 目标数据
	var legend=['市场占有率','目标值']; // legend 选项
	
	for(var i=0;i<valdata.length;i++){
		if (valdata[i]["org"] == "27534"){
			var xTemp=valdata[i].date_m+"月";
			xAxis.push(xTemp);
			seriesData1.push(valdata[i].a00008);
			seriesData2.push(valdata[i].a00213);
		}
	}
	//设置series
	series.push(seriesData1);
	series.push(seriesData2);
	//创建Echarts
	createBarCharts("sczy_line_2",legend,xAxis,series);
}

// 劳动生产率业态公司
function create_pie1(valdata,shouru,now){
	var legend = new Array();
	var seriesData = new Array();
	var title = '';
	var sum = 0;
	for(var i=1;i<valdata.length;i++){
		//if (valdata[i].a00003 > 0){
			legend.push(valdata[i].orgnm);
			var seriesTempData={};
			seriesTempData["value"] = valdata[i].a00003;
			seriesTempData["name"] = valdata[i].orgnm;
			//sum += valdata[i].a00003;
			seriesData.push(seriesTempData);
		//}
	}
	//if (shouru > sum){
//		legend.push('其他');
//		var seriesTempData={};
//		seriesTempData["value"] = shouru-sum;
//		seriesTempData["name"] = '其他';
	//}
	if (valdata.length > 0){
		title = '各单位人均收入';
	} else {
		seriesTempData={};
	}
	//seriesData.push(seriesTempData);
	createNdgeCharts("pie_1",legend,seriesData,title);
}

function create_pie2(valdata,lirun,now){
	var legend = new Array();
	var seriesData = new Array();
	var title = '';
	var sum = 0;
	for(var i=1;i<valdata.length;i++){
		//if (valdata[i].a00004 > 0){
			legend.push(valdata[i].orgnm);
			var seriesTempData={};
			seriesTempData["value"] = valdata[i].a00004;
			seriesTempData["name"] = valdata[i].orgnm;
			//sum += valdata[i].a00004;
			seriesData.push(seriesTempData);
		//}
	}
	//if (lirun > sum) {
//		legend.push('其他');
//		var seriesTempData={};
//		seriesTempData["value"] = lirun-sum;
//		seriesTempData["name"] = '其他';
	//}
	if (valdata.length > 0){
		title = '各单位人均利润';
	} else {
		seriesTempData={};
	}
	//seriesData.push(seriesTempData);
	createNdgeCharts("pie_2",legend,seriesData,title);
}

/**
 * 根据选择时间、单位加载数据
 */
function changOrgAndDate(){
	sixRateInit();
}

/**
 * 取平均值
 */
function avgCal(num1, num2){
	if (isNaN(num1) || isNaN(num2) || num1 == '' || num2 == '' || num1 == null || num2 == null ){
		return '-';
	} else {
		var sum = (parseFloat(num1) + parseFloat(num2))/2;
		return sum.toFixed(4);
	}
}

/**
 * 折线图
 * chartsId 接受图标dom的Id
 * legend 选项
 * xAxis x轴
 * series 分度数据
 */
function createLineCharts(chartsId,legend,xAxis,seriesData,stack,type){
	var obj = echarts.init(document.getElementById(chartsId));
	option = {
		tooltip : {
			trigger: 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend: {
			data:legend // ['人均收入','人均利润']
		},
		xAxis : [
		    {
		        type : 'category',
	            data : xAxis
		    }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            name: '金额（万元）',
			    position: 'left'
	        }
	    ],
	    series : 
	    	[
                {
                    name:legend[0],
                    type:type,
                    stack:stack[0],
                    data:seriesData[0]
                },
                {
                    name:legend[1],
                    type:type,
                    stack:stack[1],
                    data:seriesData[1]
                }
            ]
	};
	obj.setOption(option,true);
	window.onresize = obj.resize;
}

/**
 * 双Y轴折柱混合图
 * obj echars对象
 * legend 选项
 * xAxis x轴
 * series 分度数据
 */
function create4lvBarCharts(chartsId,legend,xAxis,seriesData){
	var obj = echarts.init(document.getElementById(chartsId));
	option = {
		tooltip : {
			trigger: 'axis',
			axisPointer : {
				type : 'cross'
			}
		},
		legend: {
			data:legend
		},
		xAxis : [
		    {
		        type : 'category',
	            data : xAxis
		    }
	    ],
	    yAxis : [
			{
			    type: 'value',
			    name: '金额（亿元）',
			    position: 'left'
			},
			{
			    type: 'value',
			    name: legend[2] + '（%）',
			    position: 'right',
			    axisLabel: {
			        formatter: '{value} '
			    }
			}
	    ],
	    series : 
	    	[
                {
                    name:legend[0],
                    type:'bar',
                    data:seriesData[0]
                },
                {
                    name:legend[1],
                    type:'bar',
                    data:seriesData[1]
                },
                {
                    name:legend[2],
                    type:'line',
                    yAxisIndex: 1,
                    data:seriesData[2]
                }
            ]
	};
	obj.setOption(option,true);
	window.onresize = obj.resize;
}

/**
 * 柱状图/折线图
 * obj echars对象
 * legend 选项
 * xAxis x轴
 * series 分度数据
 */
function createBarCharts(chartsId,legend,xAxis,seriesData){
	var obj = echarts.init(document.getElementById(chartsId));
	option = {
		tooltip : {
			trigger: 'axis',
			axisPointer : {
				type : 'shadow'
			}
		},
		legend: {
			data:legend
		},
		xAxis : [
		    {
		        type : 'category',
	            data : xAxis
		    }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            name: '占有率（%）',
				position: 'left'
	        }
	    ],
	    series : 
	    	[
                {
                    name:legend[0],
                    type:"line",
                    data:seriesData[0]
                },
                {
                	name:legend[1],
                    type:"line",
                    data:seriesData[1]
                }
            ]
	};
	obj.setOption(option,true);
	window.onresize = obj.resize;
}

/**
 * 饼图
 * obj echars对象
 * series 分度数据
 */
function createPieCharts(chartsId,legend,seriesData,title){
	var obj = echarts.init(document.getElementById(chartsId));
	option = {
		    title : {
		        text: title,
		        //subtext: '纯属虚构',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left:'left',
		        data: legend
		    },
		    series : [
		        {
		        	labelLine:{
			    		 normal:{
			    			 show:false
			    		 }
			    	 },
			    	label:{
			    		normal:{show:false}
			    	},
			    	name: '类型',
		            type: 'pie',
		            radius : '80%',
		            center: ['65%', '60%'],
		            data:seriesData,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	obj.setOption(option,true);
	window.onresize = obj.resize;
}

/**
 * 南丁格尔图
 * obj echars对象
 * series 分度数据
 */
function createNdgeCharts(chartsId,legend,seriesData,title){
	var obj = echarts.init(document.getElementById(chartsId));
	option = {
		    title : {
		        text: title,
		        //subtext: '纯属虚构',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{b} : {c}"
		    },
		    legend: {
		        orient: 'vertical',
		        left:'left',
		        data: legend
		    },
		    series : [
		        {
		        	labelLine:{
			    		 normal:{
			    			 show:false
			    		 }
			    	 },
			    	label:{
			    		normal:{show:false}
			    	},
			    	name: '面积模式',
		            type: 'pie',
		            radius : [30, 110],
		            center: ['65%', '60%'],
		            roseType : 'area',
		            data:seriesData,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	obj.setOption(option,true);
	window.onresize = obj.resize;
}
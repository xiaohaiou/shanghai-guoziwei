/**
 * 根据deptid 激活左边选择的职能样式并且修改显示位置并且加载数据
 * @param deptid
 */
function activeDeptIcon(deptid){
	try{
		$("[id^='dept_']").attr("class", "");
		$("#dept_"+deptid).attr("class", "active");
		if(deptid == 1){
			$("#mainFrame").attr("src","/bim_show/views/home/homeTB/sixRate.jsp");
		}
		if(deptid == 2){
			$("#mainFrame").attr("src","/bim_show/views/home/homeTB/administration.jsp");
		}
		if(deptid == 3){
			$("#mainFrame").attr("src","/bim_show/purchase/purchase");
		}
		if(deptid == 4){
			$("#mainFrame").attr("src","/bim_show/views/home/homeTB/hr.jsp");
		}
		if(deptid == 7){
			$("#mainFrame").attr("src","/bim_show/risk/risk");
		}
		if(deptid == 5){
			$("#mainFrame").attr("src","/bim_show/views/home/homeTB/societyResponsibility.jsp");
		}
		if(deptid == 8){
			$("#mainFrame").attr("src",basePath+"/marketing/_getMarketing");
		}
		if(deptid == 10){
			//location.href = basePath + "/home/getTBDataByDept1?year="+WholeYear+"&month="+WholeMonth+"&deptid="+deptid;
			location.href = "/bim_show/views/home/homeTB/unConstrut.jsp";
		}
	}catch(e){alert(e)}
}
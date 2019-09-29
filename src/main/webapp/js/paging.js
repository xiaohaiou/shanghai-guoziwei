function paging(pageNum,tableId){
	var html="";
	var trNum = $("#"+tableId+" tr:not(.table_header):not(#"+tableId+"NoDataTrId)").length;
	var totalPage = Math.ceil(trNum/5);//页数
	if(totalPage==0)totalPage=1;
	var totalRecords = trNum;	//条数
	
	html +="&nbsp;	"+totalPage+"页 /共"+totalRecords+"条";
	if(trNum<6){//上一页按钮
		html+="<li class='previous'><a href='javascript:;'>«</a></li>";
	}else{
		html+="<li class='previous'><a href='javascript:;' onclick='prev("+(pageNum-1)+",\""+tableId+"\")'>«</a></li>";
	}
	if(totalPage>6){
		if(pageNum<=6/2){
			for(var i = 1;i<=6;i++){
				if(pageNum==i){
					html+="<li class='active'><a >"+i+"</a></li>";
				}else{
					html+="<li><a href='javascript:;' onclick='page("+i+",\""+tableId+"\");'>"+i+"</a></li>";
				}
			}
		}else if(pageNum>6/2&&pageNum<=(totalPage-6/2)){
			for(var i = (pageNum+1-6/2);i<(pageNum+6/2);i++){
				if(pageNum==i){
					html+="<li class='active'><a >"+i+"</a></li>";
				}else{
					html+="<li><a href='javascript:;' onclick='page("+i+",\""+tableId+"\");'>"+i+"</a></li>";
				}
			}
		}else if(pageNum>(totalPage-6/2)){
			for(var i = (totalPage+1-6);i<totalPage;i++){
				if(pageNum==i){
					html+="<li class='active'><a >"+i+"</a></li>";
				}else{
					html+="<li><a href='javascript:;' onclick='page("+i+",\""+tableId+"\");'>"+i+"</a></li>";
				}
			}
		}
	}else if(totalPage<=6){
		for(var i = 1;i<=totalPage;i++){
			if(pageNum==i){
				html+="<li class='active'><a >"+i+"</a></li>";
			}else{
				html+="<li><a href='javascript:;' onclick='page("+i+",\""+tableId+"\");'>"+i+"</a></li>";
			}
		}
	}
	if(trNum<6){//下一页按钮
		html+="<li class='next'><a href='javascript:;'>»</a></li>";
	}else{
		html+="<li class='next'><a href='javascript:;' onclick='next("+(pageNum+1)+","+totalPage+",\""+tableId+"\")'>»</a></li>";
	}
	$("#page"+tableId).html(html);
}

function prev(pageNum,tableId){
	if(pageNum == 0){
		pageNum = 1;
	}
    pagequery(pageNum,tableId);
}

function next(pageNum,totalPage,tableId){
	if(pageNum >= totalPage){
		pageNum = totalPage;
	}
    pagequery(pageNum,tableId);
}

function page(pageNum,tableId){
    pagequery(pageNum,tableId);
}

function pagequery(pageNum,tableId){
	paging(pageNum,tableId);
	isShow(pageNum,tableId);
}

function isShow(pageNum,tableId){
	noDataShow(tableId);
	var showOne = (pageNum-1)*5;
	$("#"+tableId+" tr:not(.table_header)").hide();
	$("#"+tableId+" tr:not(.table_header):eq("+showOne+")").show();
	$("#"+tableId+" tr:not(.table_header):eq("+(showOne+1)+")").show();
	$("#"+tableId+" tr:not(.table_header):eq("+(showOne+2)+")").show();
	$("#"+tableId+" tr:not(.table_header):eq("+(showOne+3)+")").show();
	$("#"+tableId+" tr:not(.table_header):eq("+(showOne+4)+")").show();
}

//无数据显示
function noDataShow(tableId){
	var trNum = $("#"+tableId+" tr:not(.table_header):not(#"+tableId+"NoDataTrId)").length;
	var noDataTrNum = $("#"+tableId+"NoDataTrId").length;
	var noDataHtml="";
	if(trNum==0&&noDataTrNum==0){
		noDataHtml="<tr id='"+tableId+"NoDataTrId'><td colspan='14' align='center'>无记录</td></tr>";
		$("#"+tableId).append(noDataHtml);
	}else if(trNum!=0){
		$("#"+tableId+"NoDataTrId").remove();
	}
}

//数据格式化String——>float取2位
function f4(value){
	return isNaN(parseFloat(value).toFixed(4))?"":parseFloat(value).toFixed(4);
}
function f2(value){
	return isNaN(parseFloat(value).toFixed(2))?"":parseFloat(value).toFixed(2);
}
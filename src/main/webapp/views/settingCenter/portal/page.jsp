<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page" style="text-align:right;margin-top: 10px;">
	<span id="spanFirst1"></span>
	<span id="spanPre1"></span>
	<span id="spanNext1"></span>
	<span id="spanLast1"></span>
	<span id="spanFirst1"></span>
	<span id="spanFirst1"></span>
	第<span id="spanPageNum1"></span>页/
	共<span id="spanTotalPage1"></span>页
</div>
<script>  
	
     //获取各个控件的值  
     var theTable1 = document.getElementById("table1");  
     //总页数  
     var totalPage1 = document.getElementById("spanTotalPage1");  
     //页码  
     var pageNum1 = document.getElementById("spanPageNum1");  
     //上一页  
     var spanPre1 = document.getElementById("spanPre1");  
     //下一页  
     var spanNext1 = document.getElementById("spanNext1");  
     //第一页  
     var spanFirst1 = document.getElementById("spanFirst1");  
     //最后一页  
     var spanLast1 = document.getElementById("spanLast1");  
  
     //获取表格的行数  
     var numberRowsInTable1 = theTable1.rows.length;  
     console.log(numberRowsInTable1);
     //页面显示记录条数  
     var pageSize1 = 10;  
     //   
     var page1 = 1;  
  
  
     //下一页  
     function next1() {  
  
         //隐藏表格  
        hideTable1();  
  
         //当前行数=页面大小*页码  
         currentRow1 = pageSize1 * page1+1;  
         //最大行数=当前行数+页面大小  
         maxRow1 = currentRow1 + pageSize1;  
         //如果最大行数比表格行数大，最大行数为表格行数  
         if (maxRow1 > numberRowsInTable1) maxRow1 = numberRowsInTable1;  
         //  
         for (var i = currentRow1; i < maxRow1; i++) {  
             theTable1.rows[i].style.display = '';  
         }  
         //页码加一，到下一页  
         page1++;  
  
         //最后一页  
         if (maxRow1 == numberRowsInTable1) { nextText1(); lastText1(); }  
         showPage1();  
         preLink1();  
         firstLink1();  
     }  
  
  
     //上一页  
     function pre1() {  
  
  
         hideTable1();  
  
  
         page1--;  
  
  
         currentRow = pageSize1 * page1+1;  
         maxRow = currentRow - pageSize1;  
         if (currentRow > numberRowsInTable1) currentRow = numberRowsInTable1;  
         for (var i = maxRow; i < currentRow; i++) {  
             theTable1.rows[i].style.display = '';  
         }  
  
  		if( page1 == 1){
	  		for (var i = 1; i < pageSize1+1; i++) {  
	             theTable1.rows[i].style.display = '';  
	         }  
	         showPage1();  
	         preText1();  
	         nextLink1();  
	         lastLink1();
  		}
  
  
         if (maxRow == 0) { preText1(); firstText1(); }  
         showPage1();  
         nextLink1();  
         lastLink1();  
     }  
  
  
     //第一页  
     function first1() {  
         hideTable1();  
         page1 = 1;  
         for (var i = 1; i < pageSize1+1; i++) {  
             theTable1.rows[i].style.display = '';  
         }  
         showPage1();  
         preText1();
         firstText1();  
         nextLink1();  
         lastLink1();  
     }  
  
  
     //最后一页  
     function last1() {  
         hideTable1();  
         page1 = pageCount1();  
         currentRow = pageSize1 * (page1 - 1);  
         for (var i = currentRow; i < numberRowsInTable1; i++) {  
             theTable1.rows[i].style.display = '';  
         }  
         showPage1();  
         preLink1();
         lastText1();  
         nextText1();  
         firstLink1();  
     }  
  
     //表头不能隐藏  
     //隐藏表格  
     function hideTable1() {  
         for (var i = 1; i < numberRowsInTable1; i++) {  
             theTable1.rows[i].style.display = 'none';  
         }  
     }  
  
     //显示页  
     function showPage1() {  
         pageNum1.innerHTML = page1;  
     }  
  
  
     //总共页数  
     function pageCount1() {  
         var count = 0;  
         if (numberRowsInTable1 % pageSize1 != 0) count = 1;  
         return parseInt(numberRowsInTable1 / pageSize1) + count;  
     }  
  
  
     //显示链接  
     function preLink1() { spanPre1.innerHTML = "<a href='javascript:pre1();'>上一页</a>"; }  
     function preText1() { spanPre1.innerHTML = "上一页"; }  
  
  
     function nextLink1() { spanNext1.innerHTML = "<a href='javascript:next1();'>下一页</a>"; }  
     function nextText1() { spanNext1.innerHTML = "下一页"; }  
  
  
     function firstLink1() { spanFirst1.innerHTML = "<a href='javascript:first1();'>第一页</a>"; }  
     function firstText1() { spanFirst1.innerHTML = "第一页"; }  
  
  
     function lastLink1() { spanLast1.innerHTML = "<a href='javascript:last1();'>最后一页</a>"; }  
     function lastText1() { spanLast1.innerHTML = "最后一页"; }  
  
  
     //隐藏表格  
     function hide1() {  
         for (var i = 11; i < numberRowsInTable1; i++) {  
             theTable1.rows[i].style.display = 'none';  
         }  
         totalPage1.innerHTML = pageCount1();  
         pageNum1.innerHTML = '1'; 
         if(numberRowsInTable1 < pageSize1 ){
	         firstText1();
	         preText1();
	         nextText1(); 
	         lastText1();  
         }else{
          	firstText1();
	         preText1();
	         nextLink1();  
	         lastLink1();  
         }
     }  
     hide1();
    
</script>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>上海公司数据分析</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/home/bimShow/assets/css/analysis.css" />
</head>
<body>
<div class="top">
    <h1>上海公司数据分析</h1>
    <div class="top_select">
        <a href="javascript:window.opener=null;window.open('','_self');window.close();">
            <img src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/exit.png" />
        </a>
    </div>
</div>
<div class="line"></div>
<div class="container">
    <div class="case">
        <div class=" financial_tree">
            <div class="financial_tree2">
                <div class="case_title_left">
                    <div class="case_title_left_1">
                        <div class="case_title_left_2">
                            上海国资委
                        </div>
                    </div>
                </div>
                <div class="case_title_right">
                    <ul id="companyList">
                   <!-- 
                        <li><span onclick='getIndustryCommerceCaliberData(this.innerHTML)' style="cursor:pointer;">光明集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        <li><span>上海国盛集团</span></li>
                        -->
                    </ul>
                </div>
            </div>
        </div>
        <div class=" company_list">
            <div class=" company_list2">
                <!-- 			<div class="top_select2">
                                  <span>公司名称</span>
                                  <select>
                                      <option value ="">上海电气集团有限公司</option>
                                        <option value ="">上海电气集团有限公司</option>
                                        <option value="">上海电气集团有限公司</option>
                                  </select>
                              </div> -->
                <div class="top_select2_table">
                    <div class="case_title">
                        国资口径数据
                    </div>
                    <h2>工商信息</h2>
                    <div class="forms">
                        <div>
                            <span>法人代表：</span>
                            <input type="text" name="name" id="commerceName1" readonly/>
                        </div>
                        <div>
                            <span>经营状态：</span>
                            <input type="text" name="status" id="commerceStatus1" readonly/>
                        </div>
                        <div>
                            <span>注册资本：</span>
                            <input type="text" name="zc" id="commerceZC1" readonly/>
                        </div>
                        <div>
                            <span>实收资本：</span>
                            <input type="text" name="ss" id="commerceSS1" readonly/>
                        </div>
                        <div>
                            <span>人员规模：</span>
                            <input type="text" name="people" id="commercePeople1" readonly/>
                        </div>
                        <div>
                            <span>所属行业</span>
                            <input type="text" name="field" id="commerceField1" readonly/>
                        </div>
                        <div>
                            <span>经营期限：</span>
                            <input type="text" name="yearF" id="commerceYearF1" readonly/>
                        </div>
                        <div>
                            <span>至</span>
                            <input type="text" name="yearT" id="commerceYearT1" readonly/>
                        </div>
                    </div>
                    <h2>董监高信息</h2>
                    <table cellspacing="0">
                        <tbody id='supervisor1'>
                         <tr>
                            <th >序号</th>
                            <th>姓名</th>
                            <th>职务类别</th>
                            <th>类型</th>
                            <th>就职日期</th>
                            <th>是否离职</th>
                        </tr>   
                        <!--            
                        <tr>
                            <td>1</td>
                            <td>王丽霞</td>
                            <td>董事长</td>
                            <td>外部人员</td>
                            <td>2018-10-10</td>
                            <td>是</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>徐璐神</td>
                            <td>副董事长</td>
                            <td>内部人员</td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>温水</td>
                            <td>部门经理</td>
                            <td>人员</td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                         -->
                        </tbody>
                    </table>
                    <h2>股权信息</h2>
                    <table cellspacing="0">
                        <tbody id='shareInformation1'>
                        <tr>
                            <th>序号</th>
                            <th>股东名称</th>
                            <th>股东类别</th>
                            <th>认缴金额</th>
                            <th>认缴日期</th>
                            <th>认缴方式</th>
                            <th>认缴日期</th>
                            <th>认缴方式</th>
                            <th>货币</th>
                            <th>占比</th>
                        </tr>
                        <!-- 
                        <tr>
                            <td>1</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                         -->
                        </tbody>
                    </table>
                </div>
                <div class="top_select2_table">
                    <div class="case_title">
                        工商口径数据
                    </div>
                    <h2>工商信息</h2>
                    <div class="forms">
                        <div>
                            <span>法人代表：</span>
                            <input type="text" name="name" id="commerceName2" readonly/>
                        </div>
                        <div>
                            <span>经营状态：</span>
                            <input type="text" name="status" id="commerceStatus2" readonly/>
                        </div>
                        <div>
                            <span>注册资本：</span>
                            <input type="text" name="zc" id="commerceZC2" readonly/>
                        </div>
                        <div>
                            <span>实收资本：</span>
                            <input type="text" name="ss" id="commerceSS2" readonly/>
                        </div>
                        <div>
                            <span>人员规模：</span>
                            <input type="text" name="people" id="commercePeople2" readonly/>
                        </div>
                        <div>
                            <span>所属行业</span>
                            <input type="text" name="field" id="commerceField2" readonly/>
                        </div>
                        <div>
                            <span>经营期限：</span>
                            <input type="text" name="yearF" id="commerceYearF2" readonly/>
                        </div>
                        <div>
                            <span>至</span>
                            <input type="text" name="yearT" id="commerceYearT2" readonly/>
                        </div>
                    </div>
                    <h2>董监高信息</h2>
                    <table cellspacing="0">
                        <tbody id='supervisor2'>                     
                        <tr >
                            <th>序号</th>
                            <th>姓名</th>
                            <th>职务类别</th>
                            <th>类型</th>
                            <th>就职日期</th>
                            <th>是否离职</th>
                        </tr>
                        <!-- 
                        <tr>
                            <td>1</td>
                            <td>王丽霞</td>
                            <td>董事长</td>
                            <td>外部人员</td>
                            <td>2018-10-10</td>
                            <td>是</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>徐璐神</td>
                            <td>副董事长</td>
                            <td>内部人员</td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>温水</td>
                            <td>部门经理</td>
                            <td>人员</td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>温水</td>
                            <td></td>
                            <td></td>
                            <td>2018-10-10</td>
                            <td>否</td>
                        </tr>
                         -->
                        </tbody>
                    </table>
                    <h2>股权信息</h2>
                    <table cellspacing="0">
                        <tbody id='shareInformation2'>
                        <tr>
                            <th>序号</th>
                            <th>股东名称</th>
                            <th>股东类别</th>
                            <th>认缴金额</th>
                            <th>认缴日期</th>
                            <th>认缴方式</th>
                            <th>认缴日期</th>
                            <th>认缴方式</th>
                            <th>货币</th>
                            <th>占比</th>
                        </tr>
                        <!-- 
                        <tr>
                            <td>1</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>XXX</td>
                            <td>自然人</td>
                            <td>100</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>2018-10-10</td>
                            <td>现金</td>
                            <td>人民币</td>
                            <td>10%</td>
                        </tr>
                         -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="clearfix" style="height: 35px;"></div>
</div>
<script type="text/javascript">
    var date = '${date}';
    var basePath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/plugins/echarts/echarts.js"></script>
<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/js/analysis.js"></script>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>企业角色分配</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<link href="<c:url value="/settingCenter/assets/css/font/iconfont.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/settingCenter/assets/css/custom.css"/>" rel="stylesheet" type="text/css" />
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/settingCenter/css/zTreeStyle.css"/>">
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
		
		<style type="text/css">
			.selectone{
				white-space: nowrap;
				font-size: 14px;
				cursor: pointer;
			}
			.selectone:hover, .selectone.selectedOne{
				box-sizing: border-box;
				background-color: rgba(255,255,255,0.8);
				border-bottom:1px solid rgba(0,0,0,0.5);
			}
			.selectone>* {
				display:inline-block;
				min-width: 60px;
			}
			.selectedOne{
				border-color:#4a22cc;
				color: #4a22cc;
			}
		</style>
	</head>
	<body>
		<!-- 企业角色分配 -->
		<div class = "model_content">
			<h4><span class="glyphicon glyphicon-user"></span> 企业角色分配</h4>
			<div class="model_body model_shadow" style="overflow:hidden">
				<form id="form2" method="post" action="${pageContext.request.contextPath}/sys/role/_roleCompanySaveOrUpdate?type=roleCompany&companyType=${companyType }">
					<input type="hidden" name="pageNum" value="${pageNum }">
					<input type="hidden" name="qRoleName" value="${qRoleName }">
					<input type="hidden" name="roleId" value="${hhRole.id }">
					<input type="hidden" name="sysId" value="${hhRole.sysId }">
					<input type="hidden" name="companyIds" id="companyIds">
					<input type="hidden" name="vcAccount" value="${vcAccount }">
					<div style="overflow:hidden;overflow-x:hidden" class="">
						<div class="row">
							<label class="col-md-2 col-md-offset-1">角色名称:</label><input type="text" value="${hhRole.roleName }" disabled="">
						</div>
						<div class="row">
							<label class="col-md-2 col-md-offset-1">查询公司:</label><input type="text" id="searchNodes" onchange="getParamByName()">
						</div>
						<div class="row com_edit">
							<div class="col-md-11 col-md-offset-1 row">
								<label class="col-md-2">企业列表:</label>
								<div class="col-md-8 com_ztree_box" style="overflow:auto">
									<ul id="com_ztree" class="ztree">
					
									</ul>
								</div>
							</div>
						</div>
					</div>
				</form>
				</div>
				<div class="model_btn">
					<button class="btn btn-default model model_btn_close">关闭</button>
					<button class="btn btn-primary model_btn_ok" onclick="submitRoleCompany()">提交</button>
				</div>
			</div>
		</div>
	</body>
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap-treeview.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/js/iframe.js"/>" type="text/javascript"></script>
	
	<script src="<c:url value="/settingCenter/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
	<script type="text/javascript">
		function submitRoleCompany(){
			//获取选中的企业,提交时，将已选择公司的id(此id对应HhUsers表中的nNodeID)进行字符串相加，并以“，”相隔
			var companyIds = "";
			var treeObj=$.fn.zTree.getZTreeObj("com_ztree");
	        var nodes=treeObj.getCheckedNodes(true)//e.check_Child_State -1 0 1 2
	        for(var i=0;i<nodes.length;i++){
	        	companyIds += nodes[i].id +",";
	        }
	        $("#companyIds").val(companyIds);
	        $("#form2").submit();
	        
		}
		
	    var zTreeObj;
	    var com_ztree_setting = {
			check:{
				enable:true,
				chkboxType: { "Y": "", "N": "" },
				chkStyle: "checkbox",
				//radioType: "all",
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			},
	    };
	    
	    //所有企业数据	
	    var com_data = ${allCompanyTree};
	    //此角色保存的企业Id（此id对应HhUsers表中的nNodeID）,用于编辑时回显
	    var checked_data = ${checkedCompany};
	    /* [
	    	{id:"parent 1",pId:0,name:"海航实业"},
	    	{id:"child 1",pId:"parent 1",name:"海航实业集团有限公司"},
	    	{id:"child 2",pId:"parent 1",name:"海航基础"},
	    	{id:"grandchild 1",pId:"child 2",name:"海航基础控股有限公司"},
	    	{id:"great_grandchild 1",pId:"grandchild 1",name:"三沙海航投资有限公司"},
	    	{id:"great_grandchild 2",pId:"grandchild 1",name:"成都新体网络科技有限公司"},
	    	{id:"grandchild 2",pId:"child 1",name:"海航地产集团有限公司"},
	    	{id:"great_grandchild 3",pId:"grandchild 2",name:"上海大新华实业有限公司"},
	    	{id:"great_great_grandchild 1",pId:"great_grandchild 3",name:"上海海航航信投资有限公司"}
	    ]; */
	    $(function () {
	    	zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			initZtree(treeObj,checked_data);
	    })
	    
	    /**
	    *	初始化树的选择
	    */
	    function initZtree(treeObj,checked_data){
	    	var nodes = treeObj.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
    		var childrenNodes = treeObj.transformToArray(nodes);
	    	for(var i=0;i<childrenNodes.length;i++){
	    		for(var j=0;j<checked_data.length;j++){
	    			if(childrenNodes[i].id == checked_data[j].id){
	    				treeObj.expandNode(childrenNodes[i], true); //展开选中的  
                    	treeObj.checkNode(childrenNodes[i], true);
                    	//treeObj.updateNode(childrenNodes[i]);
                    	expandParent(treeObj,childrenNodes[i]);
	    			}
	    		}
	    	}
	    }
	    /**
			展开上级节点
		*/
		function expandParent(treeObj,fbNode){
			treeObj.expandNode(fbNode, true);
			console.log(fbNode.pId);
			var pfbNode = treeObj.getNodeByParam("id", fbNode.pId);
			if(pfbNode != null){
				expandParent(treeObj,pfbNode);
			}
		}
	    function getParamByName(){
	    	var name = document.getElementById("searchNodes").value;
	    	//zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
	    	//var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = zTreeObj.getNodesByParamFuzzy("name", name, null);
			if (nodes.length>0) {
				zTreeObj.selectNode(nodes[0]);
				
			}
			//initZtree(treeObj,checked_data);
	    }
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　　$(".bg_model").hide();
			$(".bg_model").empty();
		});
	</script>
</html>
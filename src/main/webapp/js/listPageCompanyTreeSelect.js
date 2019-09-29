/*
 * listPageCompanyTreeSelect.js
 * 公共js;用于列表页面多选公司，并查询
 * 涉及页面元素：
 * 	#checkedCompanyName 选择的公司名称
 * 	.com_ztree_box  存放z-tree的div
 * 	#com_ztree 公司树
 * 	#organalID  选择公司的nodeId 通过逗号分隔
 * 	#checkedCompanyName 选择公司的名称 通过逗号进行分隔
 * 	.clear   清除选择按钮
 * 	#allCompanyTree 财务树公司所有数据
 * 
 * 以预算报表上报列表页面为例
 * 
 */

var timeoutId
$('#checkedCompanyName').on('focus click',function(){
	console.log($(this).next('.com_ztree_box'));
	$(this).next('.com_ztree_box').css('display','block');
}).parent().on('mouseenter',function(){
	clearTimeout(timeoutId)
}).on('mouseleave',function(){
	clearTimeout(timeoutId)
	timeoutId = setTimeout(function(el){
		$(el).find('.com_ztree_box').css('display','none')
	},3e2,this);
})

var zTreeObj;
var com_ztree_setting = {
	check:{
		enable:true,
		chkStyle: "checkbox",
		chkboxType: { "Y": "s", "N": "s" }
	},
	data:{
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: 0
		}
	},
	callback: {
		onCheck: zTreeOnCheck
	}
};
/**
*  勾选树节点时触发事件
*/
   function zTreeOnCheck(event, treeId, treeNode){
   		var nodes = $.fn.zTree.getZTreeObj("com_ztree").getCheckedNodes(true);//获取勾选的节点
	var comIds = '';
	var comName = '';
	for(var i = 0; i < nodes.length; i ++){
		comIds += nodes[i].id + ',';
		comName += nodes[i].name + ',';
	}
	comIds = comIds.substring(0, comIds.length-1);
	comName = comName.substring(0, comName.length-1);
	$("#organalID").val(comIds);
	 $("#checkedCompanyName").val(comName);
	 $("#checkedCompanyName").attr('title',comName);
   }

  $(function () {
	     //所有企业数据	
    var com_data = eval("("+$("#allCompanyTree").val()+")");
	zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
	var nodes = treeObj.getNodes();
	//transformToArray()此方法获取所有节点（父节点和子节点）
	var childrenNodes = treeObj.transformToArray(nodes);
	if(childrenNodes[0]!=null)
		treeObj.expandNode(childrenNodes[0],true);
	//反绑	
	var ids = $("#organalID").val();
	if(ids != undefined && ids != ''){
		var idArray = ids.split(",");
		for(var i = 0; i < idArray.length; i++){
			var fbNode = treeObj.getNodeByParam("id", idArray[i]);
			expandParent(treeObj, fbNode);
			fbNode.checked = true;
			treeObj.updateNode(fbNode,false );
		}
	}	
});

/**
	展开上级节点
*/
function expandParent(treeObj,fbNode){
	treeObj.expandNode(fbNode,true);
	var pfbNode = treeObj.getNodeByParam("id", fbNode.pId);
	if(pfbNode != null){
		expandParent(treeObj,pfbNode);
	}
}
/**
 * 清空
 */
$('.clear').on('click',function(){
	$(this).siblings('#checkedCompanyName').val('');
	$("#organalID").val("");
	$("#checkedCompanyName").attr('title','');
	$.fn.zTree.getZTreeObj("com_ztree").checkAllNodes(false);//取消勾选 全部节点
});

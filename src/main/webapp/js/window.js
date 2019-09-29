var win = new function () {
	// 确认框和提示框宽度
    this.width = 400;

    // 确认框和提示框高度
    this.height = 172;

    // 手动关闭窗体
    this.close = function () {
        $('.win iframe').remove();
        $('.win').remove();
    };

    // 显示消息框
    function messageBox(html, message) {
        win.close();
        var jq = $(html);

        jq.find(".window-panel").height(win.height).width(win.width).css("margin-left", -win.width / 2).css("margin-top", -win.height / 2);
        jq.find(".title-panel").height(win.height);
        //jq.find(".title").find(":header").html(title);
        jq.find(".body-panel").height(win.height - 36);
        jq.find(".content").html(message.replace('\r\n', '<br/>'));
        jq.prependTo('body').show();
        $(".win .w-btn:first").focus();
    }

    // 确认框
    this.confirm = function (message, selected) {
        this._close = function (r) {
            this.close();
            if ($.isFunction(selected)) selected(r);
        };

        var html = '<div class="win"><div class="mask-layer"></div><div class="window-panel question"><div class="title-panel" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></div><div class="title title-question"></div><a href="javascript:;" onclick="win._close(false);" class="close-btn" title="关闭">×</a><div class="body-panel"><p class="content"></p><p class="btns"><a href="javascript:;" class="w-btn" tabindex="1" onclick="win._close(true);">确定</a><a href="javascript:;" class="w-btn qx" onclick="win._close(false);">取消</a></p></div></div></div>';
        messageBox(html, message);
    };

	// 工作提示-确认框
    this.confirm_1 = function (message, selected) {
        this._close = function (r) {
            this.close();
            if ($.isFunction(selected)) selected(r);
        };

        var html = '<div class="win"><div class="mask-layer"></div><div class="window-panel question"><div class="title-panel" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></div><div class="title title-question"></div><a href="javascript:void(0)" onclick="win.close();" class="close-btn" title="关闭">×</a><div class="body-panel"><p class="content"></p><p class="btns"><a href="#" class="w-btn" tabindex="1" onclick="win._close(true);">是</a><a href="#" class="w-btn qx" onclick="win._close(false);">否</a></p></div></div></div>';
        messageBox(html, message);
    };
    // 等待框
    this.generalWait = function (message, closed) {
     
        var html = '<div class="win"><div class="mask-layer"></div><div class="window-panel"><div class="title-panel" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></div><div class="title"><h3></h3></div><div class="body-panel"><p class="content"></p><p class="btns"></p></div></div></div>';
		messageBox(html, message);
		   
	             closed();
	           
    }
    // 提示框
    this.generalAlert = function (message, closed) {
        this._close = function () {
            this.close();
            if ($.isFunction(closed)) closed();
        };

        var html = '<div class="win"><div class="mask-layer"></div><div class="window-panel"><div class="title-panel" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></div><div class="title"><h3></h3></div><a href="javascript:void(0)" onclick="win._close();" class="close-btn" title="关闭">×</a><div class="body-panel"><p class="content"></p><p class="btns"><a href="#" class="w-btn" tabindex="1" onclick="win._close();">确定</a></p></div></div></div>';
		messageBox(html, message);
		
    }
    
    //成功提示框
     this.successAlert = function (message, closed) {
        this._close = function () {
            this.close();
            if ($.isFunction(closed)) closed();
        };
        var html = '<div class="win"><div class="mask-layer"></div><div class="window-panel success"><div class="title-panel" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></div><div class="title title-success"></div><a href="javascript:void(0)" onclick="win._close();" class="close-btn" title="关闭">×</a><div class="body-panel"><p class="content"></p><p class="btns"></p></div></div></div>';
        messageBox(html, message);
		setTimeout(function () { win.close();closed();}, 2000);
    }

     
     //错误提示框
     this.errorAlert = function (message, closed) {
        this._close = function () {
            this.close();
            if ($.isFunction(closed)) closed();
        }
        var html = '<div class="win"><div class="mask-layer"></div><div class="window-panel error"><div class="title-panel" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></div><div class="title title-error"></div><a href="javascript:void(0)" onclick="win._close();" class="close-btn" title="关闭">×</a><div class="body-panel"><p class="content"></p><p class="btns"><a href="#" class="w-btn" tabindex="1" onclick="win._close();">确定</a></p></div></div></div>';
        messageBox(html, message);
    }
     
     //信息提示框
     this.infoAlert= function ( message, closed) {
        this._close = function () {
            this.close();
            if ($.isFunction(closed)) closed();
        }
        var html = '<div class="win"><div class="mask-layer"></div><div class="window-panel info"><div class="title-panel" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></div><div class="title title-info"></div><div class="body-panel"><p class="content"></p><p class="btns"></p></div></div></div>';
        messageBox(html, message);
		setTimeout(function () { win.close(); closed(); }, 2000);
    }
     
     // 审批通过确认框
	 this.approveConfirm = function (message, selected) {
	     this._close = function (r) {
	         this.close();
	         if ($.isFunction(selected)) selected(r);
	     };
	
	     var html = '<div class="win"><div class="mask-layer"></div><div class="window-panel question"><div class="title-panel" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></div><div class="title title-question"></div><a href="javascript:void(0)" onclick="win._close(false);" class="close-btn" title="关闭">×</a><div class="body-panel"><p class="content"></p><p class="btns"><a href="#" class="w-btn" tabindex="1" onclick="win._close(111);">通过</a><a href="#" class="w-btn" tabindex="1" onclick="win._close(222);">不通过</a><a href="#" class="w-btn qx" onclick="win._close(false);">取消</a></p></div></div></div>';
	     messageBox(html, message);
	 };
};




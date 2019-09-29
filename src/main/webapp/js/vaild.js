! function(W) {
	W.vaild = {}
	W.rule = {
		'number&char&-':function(str){
			var r = /^[0-9a-zA-Z-]*$/.test(str)
			return r? [{
				vaild:true,
				msg:'数字,字母和-的'
			}]:[{
				vaild:false,
				msg:'数字,字母和-的组合'
			}];
		}
	}
	W.vaild.key = [
		'opt_empty',
		'type',
		'max_len',
		'small_part',
		'sign',
		'min',
		'max',
	]
	var format = W.vaild.format = function(check_info){
		var o = {};
		check_info.split('_').forEach(function(v,i){
			if(W.vaild.key[i] && v!='*'){
				o[W.vaild.key[i]]=v
			}
		})
		return o;
	}
	var format_number = function(s){
		if(s.length === 0)
			return s
		s = s.replace(/,/g, '')
		if(s.length === 0)
			return ''
		s = s.trim().replace(/^0*/,function(m){
			return s[m.length] === '.' ? '0' : '';
		})
		if(s.length === 0)
			return '0'
		if(s.lastIndexOf('.') !== -1 && s[s.length-1] !== '.')
			s = s.replace(/0*$/, '').replace(/\.$/, '')
		return s || '0'
	}
	/* vaild graph
	opt_empty
	type <- number <- double.small_part
				   <- range	<- min
							<- max
				   <- max_len
		 <- string <- max_len */
	var check = W.vaild.check = function(value, arg) {
		var result = {}
		if (arg.opt_empty === 'NotEmpty') {
			result.opt_empty = value !== ""
		}
		if (arg.type == 'double') {
			value = format_number(value)
			result.type = /^[+-]?\d+(\.\d+)?$/.test(value);
			if (!result.type) return result;
			result.type = true

			var max_len = parseInt(arg.max_len)
			if (!isNaN(max_len)) {
				result.max_len = value.length <= max_len
			}

			var small_part = parseInt(arg.small_part)
			if (!isNaN(small_part)) {
				var p = value.indexOf('.')
				if (p !== -1) {
					sm = value.substring(p + 1)
					result.small_part = sm.length <= small_part
				}else{
					result.small_part = true
				}
			}
			var num = parseFloat(value)
			var min = parseInt(arg.min)
			if (!isNaN(min)) {
				result.min = num >= min;
			}
			var max = parseInt(arg.max)
			if (!isNaN(max)) {
				result.max = num <= max;
			}
			if (arg.sign !== undefined) {
				result.sign = arg.sign == '+' ? num > 0 :
					arg.sign == "-" ? num < 0 :
					arg.sign == "0+" ? num >= 0 :
					arg.sign == "0-" ? num <= 0 : undefined;
			}
		} else if (arg.type == 'int') {
			value = format_number(value)
			result.type = /^[+-]?\d+$/.test(value);
			if (!result.type) return result;

			if (!isNaN(arg.max_len)) {
				result.max_len = value.length <= parseInt(arg.max_len)
			}
			var num = parseInt(value)

			var min = parseInt(arg.min)
			if (!isNaN(min)) {
				result.min = num >= min;
			}
			var max = parseInt(arg.max)
			if (!isNaN(max)) {
				result.max = num <= max;
			}
			if (arg.sign !== undefined) {
				result.sign = arg.sign == '+' ? num > 0 :
					arg.sign == "-" ? num < 0 :
					arg.sign == "0+" ? num >= 0 :
					arg.sign == "0-" ? num <= 0 : undefined;
			}
		} else {
			if (!isNaN(arg.max_len)) {
				result.max_len = value.length <= parseInt(arg.max_len)
			}
		}
		return result;
	}
	var stand_msg = W.vaild.stand_msg = function(arg){
		var msg = {};
		if (arg.opt_empty === 'NotEmpty') {
			msg.opt_empty = '必填';
		}
		if (!isNaN(arg.max_len)) {
			msg.max_len = '少于'+arg.max_len + '字符'
		}
		if (arg.type === 'int') {
			msg.type = '整数'
		} else if (arg.type === 'double') {
			msg.type = '数字'
			if (!isNaN(arg.small_part)) {
				msg.small_part = arg.small_part + '小位数'
			}
		}else{
			return msg
		}
		if(!isNaN(arg.min)){
			msg.min = '大于'+arg.min
		}
		if(!isNaN(arg.max)){
			msg.max = '小于'+arg.max
		}
		msg.sign = arg.sign == '+' ? '正数' :
			arg.sign == "-" ? '负数' :
			arg.sign == "0+" ? '大于等于0':
			arg.sign == "0-" ? '小于等于0': undefined;

		var min = parseFloat(arg.min)
		var max = parseFloat(arg.max)
		if (arg.min !== undefined && !isNaN(min)) {
			if (arg.max !== undefined &&  !isNaN(max)) {
				if (min < 0 && (arg.sign === '+' || arg.sign === '0+')) {
					msg.range = '0到'
				} else {
					msg.range = arg.min + '到';
				}
				var max = parseFloat(arg.max)
				if (max > 0 && (arg.sign === '-' || arg.sign === '0-')) {
					msg.range += '0之间'
				} else {
					msg.range += arg.max + '之间'
				}
			} else if (min < 0) {
				if (arg.sign === '+') {
					msg.range = '大于0'
				} else if (arg.sign === '0+') {
					msg.range = '不小于0'
				} else {
					msg.range = '不小于' + arg.min;
				}
			} else {
				msg.range = '不小于' + arg.min;
			}
		} else if (arg.max !== undefined) {
			var max = parseFloat(arg.max)
			if (max > 0) {
				if (arg.sign === '-') {
					msg.range = '小于0'
				} else if (arg.sign === '0-') {
					msg.range = '不大于0'
				} else {
					msg.range = '不大于' + arg.max
				}
			} else {
				msg.range = '不大于' + arg.max
			}
		}
		return msg
	}

	var vaild_value = W.vaild.vaild_value = function(value,arg){
		var result = check(value,arg)
		var msg = stand_msg(arg)
		return Object.keys(arg).filter(function(k){
			return msg[k]
		}).map(function(k){
			return {
				key:k,
				check:arg[k],
				vaild:result[k],
				msg:msg[k],
			}
		})
	}
	var vaild = W.vaild.vaild = function(el){
		var value = el.value || '';
		var arg = format(el.getAttribute('check'))
		var r = vaild_value(value,arg)
		if(r.length==0){
			return true
		}
		if(r.filter(function(e){ return !e.vaild }).length==0 || value=='' && arg.opt_empty !='NotEmpty'){
			$(el).removeClass('error')
			return true
		}else{
			$(el).addClass('error')
			return false
		}
	}
	var add_tip = W.vaild.add_tip = function(el,msgs){
		var t = $('<div class="v-confirm-tip">'+msgs.map(function(e){
			return '<div class="tip-item"'+(e.vaild?'ok':'')+'><span no>×</span><span ok>√</span>'+e.msg+'</div>'
		}).join('')+'</div>').css({
			'min-width':el.offsetWidth+"px",
		}).appendTo(el.parentNode);
		t.css({
			top:(el.offsetTop-t[0].offsetHeight-6)+"px",
			left:el.offsetLeft+el.offsetWidth/2-t[0].offsetWidth/2+"px",
		});
	}
	var tip = W.vaild.tip = function(el){
		var value = el.value || '';
		var arg = format(el.getAttribute('check'))
		var r = vaild_value(value,arg)
		if(r.length==0){
			return
		}

		add_tip(el,r)
	}

	W.vaild.all = function(el){
		return $('input[check],textarea[check]').get().filter(function(el){
			return !W.vaild.vaild(el)
		}).length+$('[custom-check]').get().filter(function(el){
			var r = el.$ckeck(el.value)
			var flag = true;
			r.map(function(v){ flag = flag && v.vaild })
			$(el)[flag?'removeClass':'addClass']('error')
			return !flag
		}) == 0;
	}

	W.vaild.bind = function(el,check){
		var t = typeof check;
		var fn = function(){
			var r = el.$ckeck(el.value)
			add_tip(el,r);
			var flag = true;
			r.map(function(v){ flag = flag && v.vaild })
			$(el)[flag?'removeClass':'addClass']('error')
		}

		if(t === 'string'){
			if(check in W.rule){
				el.$ckeck = W.rule[check]
			}else{
				return
			}
		}else if(t !== 'function'){
			return
		}else{
			el.$ckeck = check
		}
		if(el.getAttribute('check')!==null){
			el.removeAttribute('check')
		}
		el.setAttribute('custom-check','');
		$(el).on('input click', function() {
			$('.v-confirm-tip').remove()
			fn()
		}).on('blur', function() {
			$('.v-confirm-tip').remove()
		})
	}

	// stand_value
	W.vaild.vaild_1 = function(el) {
		var value = el.value || '';
		var args = el.getAttribute('check').split('_')
		// var msg = el.getAttribute('error-msg')
		var error = W.vaild.check(value, args)
		if (error) {
			el.value = ''
			$(el).attr('placeholder', W.vaild.stand_msg(args)[error])
			$(el).addClass('error')
		} else {
			$(el).attr('placeholder', '')
			$(el).removeClass('error')
		}
		return error
	}

	W.vaild.init = function(){
		$('input[check]').each(function(){
			if($(this).attr('placeholder')) return
			var r = W.vaild.stand_msg(W.vaild.format($(this).attr('check')));
			if(!r.opt_empty) return
			$(this).attr('placeholder',r.opt_empty)
		})
	}

	$(function() {
		W.vaild.init()
		setTimeout(function(){
			$('input[check],textarea[check]').on('input click', function() {
				this.setAttribute('self-bind',true)
				// tip
				$('.v-confirm-tip').remove()
				W.vaild.tip(this)
			}).on('focus', function() {
				this.setAttribute('self-bind',true)
				// valid
				// $(this).removeClass('error')
			}).on('blur', function() {
				this.setAttribute('self-bind',true)
				// tip
				$('.v-confirm-tip').remove()
				// vaild
				W.vaild.vaild(this)
			})
			$('body').on('input click', 'input[check],textarea[check]', function() {
				if(this.getAttribute('self-bind',true)==='true') return;
				// tip
				$('.v-confirm-tip').remove()
				W.vaild.tip(this)
			}).on('focus', 'input[check],textarea[check]', function() {
				if(this.getAttribute('self-bind',true)=='true') return;
				// valid
				// $(this).removeClass('error')
			}).on('blur', 'input[check],textarea[check]', function() {
				if(this.getAttribute('self-bind',true)==='true') return;
				// tip
				$('.v-confirm-tip').remove()
				// vaild
				W.vaild.vaild(this)
			})
		},1e3)
	})
}(window)

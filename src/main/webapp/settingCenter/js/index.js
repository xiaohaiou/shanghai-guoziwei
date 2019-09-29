// orders-tabs
$('.select-group').on('click', '>*', function() {
	if (!$(this).hasClass('select')) {
		$(this).parent().find('>*').removeClass('select')
		$(this).addClass('select')
	}
})

// circle
var colors = [
	['#dedede', '#669ce2'],
	['#dedede', '#49b8b0'],
	['#dedede', '#df67a6']
]
var circles_init = function() {
	$('[circle]').each(function(i) {
		var id = this.id = Math.random()
		var percentage = parseFloat(this.getAttribute('circle-data') || "") || 0;
		Circles.create({
			id: id,
			value: percentage,
			radius: 30,
			width: 7,
			colors: colors[i >= colors.length ? i % colors.length : i],
			text: function(value) {
				return value + '%';
			},
			styleText: false,
		})
	})
}


	// live msg
	var LiveMsg = (function() {
		var default_option = {
			'init-position': 0.1, /* 0-1 */
			'scroll-direction': 'right',
			'data-margin': 50,
			'speed': 1,
		}
		var requestAnimFrame = function(callback) {
			setTimeout(callback, 1000 / 30);
		};
		// window.requestAnimationFrame ||
		// window.webkitRequestAnimationFrame ||
		// window.mozRequestAnimationFrame ||
		// window.oRequestAnimationFrame ||
		// window.msRequestAnimationFrame ||

		var get_element_index = function(el) {
			if (el.parentNode) {
				var l = el.parentNode.childNodes;
				for (var i = 0; i < l.length; ++i) {
					if (el == l[i])
						return i
				}
			}
			return -1
		}
		// scroll-direction
		var init = function(el, opt2) {
			if(el.attr('msg-living')=='true') return
			var opt1 = (el.find('[live-msg]').attr('live-msg') || '').split(';').filter(function(e) {
				return e
			}).map(function(e) {
				return e.split(':')
			}).filter(function(e) {
				return e.length == 2;
			}).reduce(function(o, e) {
				return (o[e[0]] = e[1], o)
			}, {})
			var setting = {
				el: el,
				bar: el.find('.live'),
				msg: el.find('.msg'),
			}
			$.extend(setting, opt1, opt2 || {})
			var dir = 'scroll-direction'
			setting.speed = ((setting[dir] || default_option[dir]) == 'right' ? 1 : -1) * (setting.speed || default_option.speed);
			var p = setting['init-position'] || default_option['init-position'];
			if (p > 1) p = 1;
			if (p < 0) p = 0;
			setting['init-position'] = p;
			setting.is_scroll = false;
			setting.is_pause = false;
			setting.add_left = function(msg) {
				var first = setting.bar[0].childNodes[0];
				if (first) {
					if (first.offsetLeft <= 0) {
						return;
					}
					var index = parseInt(first.getAttribute('msg-index')) || 0
					if (index > msg.length - 1)
						var index = -1;
				} else {
					var el = msg[0].cloneNode(true)
					el.setAttribute('msg-index', 0)
					setting.bar[0].appendChild(el)
					var at = setting['init-position'];
					el.style.left = setting.bar[0].offsetWidth * at + 'px';
					if (at == 0) return
					var index = 0
				}
				while (true) {
					index += 1;
					if (index > msg.length - 1)
						index = 0;
					var el = msg[index].cloneNode(true)
					el.setAttribute('msg-index', index)
					var b = setting.bar[0].childNodes[0];
					setting.bar[0].insertBefore(el, b)
					el.style.left = b.offsetLeft - (setting['data-margin'] || default_option['data-margin']) - el.offsetWidth + 'px'
					if (el.offsetLeft < 0)
						break
				}
			}
			setting.add_right = function(msg) {
				var last = setting.bar[0].childNodes[setting.bar[0].childNodes.length - 1];
				var right_at = setting.bar[0].offsetWidth;
				if (last) {
					if (last.offsetLeft + last.offsetWidth > right_at) {
						return;
					}
					var index = parseInt(last.getAttribute('msg-index')) || 0
					if (index >= msg.length - 1)
						index = -1;
				} else {
					var el = msg[0].cloneNode(true)
					el.setAttribute('msg-index', 0)
					setting.bar[0].appendChild(el)
					var at = setting['init-position'];
					el.style.left = setting.bar[0].offsetWidth * (1 - at) + 'px';
					if (at == 1) return
					var index = 0

				}
				while (true) {
					index += 1;
					if (index > msg.length - 1)
						index = 0;
					var el = msg[index].cloneNode(true)
					el.setAttribute('msg-index', index)
					var b = setting.bar[0].childNodes[setting.bar[0].childNodes.length - 1];
					setting.bar[0].appendChild(el)
					el.style.left = b.offsetLeft + b.offsetWidth + (setting['data-margin'] || default_option['data-margin']) + 'px'
					if (el.offsetLeft > right_at)
						break

				}
			}
			setting.bar.on('mouseover', function() {
				setting.is_pause = true
			}).on('mouseout', function() {
				setting.is_pause = false
			})
			setting.scroll = function(el) {
				setting.is_scroll = true;
				setting.start_scroll = function() {
					if (!setting.is_scroll) return;
					if (setting.is_pause) {
						setTimeout(setting.start_scroll, 1e3);;
						return
					}
					var children = setting.bar.find('>*').get();
					var bar_width = setting.bar[0].offsetWidth
					if (children.length > 0) {
						// 1 移动
						children.forEach(function(e) {
							e.style.left = (e.offsetLeft + setting.speed) + 'px';
						})
						// 1.1 移除
						if (setting.speed > 0) {
							children.forEach(function(e) {
								if (e.offsetLeft > bar_width) {
									setting.bar[0].removeChild(e)
								}
							})
						} else {
							children.forEach(function(e) {
								if (e.offsetLeft + e.offsetWidth < 0) {
									setting.bar[0].removeChild(e)
								}
							})
						}
					}
					// 2 添加
					var msg = setting.msg.find('>*').get();
					if (msg.length <= 0){
						setTimeout(setting.start_scroll, 6e4);
						return
					}
					// add
					if ((setting['scroll-direction'] || default_option['scroll-direction']) == 'right') {
						setting.add_left(msg)
					} else {
						setting.add_right(msg)
					}
					requestAnimFrame(setting.start_scroll);
				};
				requestAnimFrame(setting.start_scroll);
			}
			setting.scroll();
			el.attr('msg-living','true')
			return setting
		}

		return init;
	})();

	

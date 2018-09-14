window.onload=function() {
	var alldata, oindex = 0;
	listPoint();
	$(document).on('click', '.treeleft_stu_m_name', function(){
		$('.treeHasdata').removeClass('active');
		$('.treeleft_stu_m_name').removeClass('active');
		$(this).addClass('active');
	})
	$(document).on('click', '.treeleft_stu_camera', function(){
			$('.treeHasdata').removeClass('active');
			$('.treeleft_stu_m_name').removeClass('active');
			$(this).find('.treeHasdata').addClass('active');
			$(this).parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').addClass('active');
			$('#fir_pro_menu').hide();
			listIPCPoint01();
	});
/*	$(document).on('touchend','.vio_content',function(e){
		e.stopPropagation();
		
	})*/
	/*新添加监视点*/
	$(document).on('click', '.pic_co_save', function() {
		var nums = $('.pic_con_l_out li.pic_con_list').length + 1;
		var newstr = '<li class="fl pic_con_list  swiper-slide"><div class="pic_co_s_info"><p class="clear"><span class="pic_co_s_i_eye fl">监视点' + nums + '</span><span class="pic_co_s_i_num fr"></span></p>' +
			'<div class="pic_co_s_i_list clear"><ul class="fl pic_co_s_i_l_l"><li><label>采集开始时间</label><span></span><input style="display:inline-block" type="text" name="" class="begintime" value="" placeholder="00:00:00" /></li>' +
			'<li><label>采集结束时间</label><span></span><input type="text" style="display:inline-block" name="" class="endtime" value="" placeholder="00:00:00" /></li>' +
			'<li><label>采集间隔时间</label><span></span><input type="text" style="display:inline-block" name="" class="monitorId" />分钟</li>' +
			'<li><label>采集周期</label><span></span><input type="text" name="" style="display:inline-block" class="cycleday" value="" />天</li>' +
			'<li><label>状态</label><p style="display:inline"> - -' +
			'</p></li></ul>' +
			'<ul class="fr pic_co_s_i_l_r"><li><a href="##" class="pics_picst" style="margin-bottom:.4rem">删除</a></li><li><a href="##" class="pics_edit">保存</a></li></ul>' +
			'</div></div></li>';
		$('.pic_con_l_out').append(newstr);
		$('.pics_picst').click(function() {
			$(this).parents('.pic_con_list').remove();
		});
		$('.pics_edit').click(function() {
			var Othis = $(this).parents('.pic_con_list');
			addIPCPoint(Othis);
		});
	});
};
function secondClick(){
	var browser = {
			  versions: function () {
			  var u = navigator.userAgent, app = navigator.appVersion;
			  return {//移动终端浏览器版本信息
			   trident: u.indexOf('Trident') > -1, //IE内核
			   presto: u.indexOf('Presto') > -1, //opera内核
			   webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
			   gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
			   mobile: !!u.match(/AppleWebKit.*Mobile/i) || !!u.match(/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/), //是否为移动终端
			   ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
			   android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
			   iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
			   iPad: u.indexOf('iPad') > -1, //是否iPad
			   webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
			  };
			  } (),
			  language: (navigator.browserLanguage || navigator.language).toLowerCase()
			}
			if (browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
				getDeviceIDiOS(active().attr('data-deviceid'));
			}
			if (browser.versions.android) {
				window.AndroidView.showView(active().attr('data-deviceid'),0,0,0,0);
			}
}
/*摄像头*/
function active() {        
	var state;
	$('.treeHasdata').each(function() {
		if($(this).hasClass('active')) {
			state = $(this)
		}
	})
	return state
}

/*编辑之后保存*/
/*function active3() {
	var state;
	$('.pic_con_l_out .pic_con_list').each(function() {
		if($(this).find('.pics_edit').text() == '保存') {
			state = $(this)
		}
	})
	return state
}*/
/*图片集*/
function active4() {
	var state;
	$('.pic_con_l_out .pic_con_list').each(function() {
		if($(this).find('.pics_picst').hasClass('active')) {
			state = $(this)
		}
	})
	return state
}
//传感器信息图片---下面的监视点

/*新添加监视点*/
function swiper() {
	 var galleryThumbs = new Swiper('.gallery-bottom', {
        spaceBetween: 10,
        slidesPerView: 1.1,
        //centeredSlides: true,
       /* observer: true, //修改swiper自己或子元素时，自动初始化swiper
		observeParents: true //修改swiper的父元素时，自动初始化swiper*/
    });
}
function listIPCPoint01() {
	loadShow()
	$.ajax({
		url: http + "listIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify({
			"cksid": sessionStorage.getItem('cksid'),
			"ckuid": sessionStorage.getItem('ckuid'),
			'id': active().attr('data-tp_id'),
			'deviceId': active().attr('data-deviceId'),
		}),
		cache: false,
		success: function(data) {
			removeload();
			if(data.state == 0) {
				$('.pic_con_l_out').empty();
				if(data.object!=null){
				if(data.object.length == 0) {
					$('.pic_con_l_out').find('.ts').show();
				} else {
					$('.pic_con_l_out').find('.ts').hide();
					var num = data.object.length;
					$(data.object).each(function(i, el) {
						var texts;
						var x = i + 1;
						var str = '';
						if(data.object[i].success == '1') {
							texts = '成功'
						} else {
							texts = '失败'
						}
						str +=
							'<li class="fl pic_con_list  swiper-slide"><div class="pic_co_s_info"><p class="clear"><span class="pic_co_s_i_eye fl">监视点' + x + '</span><span class="pic_co_s_i_num fr">' + x + '/' + num + '</span></p>' +
							'<div class="pic_co_s_i_list clear"><ul class="fl pic_co_s_i_l_l"><li><label>采集开始时间</label><span>' + data.object[i].beginTime + '</span><input type="text" name="" class="begintime" value="' + data.object[i].beginTime + '" /></li>' +
							'<li><label>采集结束时间</label><span>' + data.object[i].endTime + '</span><input type="text" name="" class="endtime" value="' + data.object[i].endTime + '" /></li>' +
							'<li><label>采集间隔时间</label><span>' + data.object[i].rateSecond + '</span><input type="text" name="" class="monitorId" value="' + data.object[i].rateSecond + '"/>分钟</li>' +
							'<li><label>采集周期</label><span>' + data.object[i].cycleDay + '</span><input type="text" name="" class="cycleday" value="' + data.object[i].cycleDay + '"  />天</li>' +
							'<li><label>状态</label><p style="display:inline">' + texts + '</p></li></ul>' +
							'<ul class="fr pic_co_s_i_l_r"><li><a href="##" class="pics_picst"  data-monitorId="' + data.object[i].monitorId + '"  data-id="' + data.object[i].id + '" >图片集</a></li><li><a href="##" class="pics_visit" data-monitorid="' + data.object[i].monitorId + '">预览</a></li><li><a href="##" class="pics_edit">编辑</a></li></ul>' +
							'</div></div></li>'
						$('.pic_con_l_out').append(str);
					});
					swiper();
					$('.pics_edit').click(function() {
						var text = $(this).text();
						if(text == '编辑') {
							$('.pic_co_save').show();
							$(this).parent().siblings('li').find('.pics_visit').hide();
							$(this).parent().siblings('li').find('.pics_picst').text('删除').css({ 'background-color': '#fff', 'border': '1px solid #E2846D', 'color': '#E2846D', 'margin-bottom': '.3rem' });
							$(this).parents('.pic_con_list').find('.pic_co_s_i_l_l span').hide();
							$(this).parents('.pic_con_list').find('.pic_co_s_i_l_l input').css('display', 'inline-block');
							$(this).text('保存');
						} else if(text == '保存') {
							var thisObj = $(this).parents('.pic_con_list');
							updateIPCPoint(thisObj);
							/*$(this).text('编辑');*/
							/*$(this).parents('.pic_con_list').find('.pic_co_s_i_l_l span').show();
							$(this).parents('.pic_con_list').find('.pic_co_s_i_l_l input').css('display', 'none');
							$(this).parent().siblings('li').find('.pics_picst a').css('margin-bottom','.125rem');*/
						}
					});
					$('.pics_picst').click(function() {
						var text = $(this).text();
						if(text == '删除') {
							var id = $(this).attr('data-id')
							if(confirm("是否删除该监视点")) {
								deleteIPCPoint(id)
							}
						} else if(text == '图片集') {
							$(this).addClass('active');
							$(this).parents('.pic_con_list').siblings().find('.pics_picst').removeClass('active');
							listIPCPointIMG($(this).attr('data-monitorid'), 1);
						}
					});
					/*预览*/
					$('.pics_visit').on('click', function() {
						/*$("#shipin01").contents().find('#preset').val($(this).attr('data-monitorid'));
						$("#shipin01").contents().find('#diaoyong').click();*/
					})
					/*$('.shexiangtoujsdList2>li').eq(0).addClass('active');
					listIPCPointIMG($('.shexiangtoujsdList2>li').eq(0).attr('data-monitorid'), 1);
					
					$('.shexiangtoujsdList2>li').on('click', function() {
						$(this).addClass('active').siblings().removeClass('active')
						listIPCPointIMG($(this).attr('data-monitorid'))
					})*/
				}
				}
			} else if(data.state == 2) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				}, function() {
					window.location.href = './login.html'
				});
				return
			} else {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
				return
			}
		}
	});
};
//删除监视点
function deleteIPCPoint(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.id = id;
	obj.deviceId = active().attr('data-deviceId');
	obj.pointEntity = { "ip": active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-ip'), "port": active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-port'), "tp_id": active().attr('data-tp_id') };
	$.ajax({
		url: http + "deleteIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.success) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
				listIPCPoint01(); /*后台已经删除重新展示*/
			} else if(data.state == 2) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				}, function() {
					window.location.href = './login.html'
				});
				return
			} else {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
				return
			}
		}
	});
}
//添加监视点-验证
function addIPCPoint(objpro) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = {};
	obj.pointEntity.ip = active().parents('.treeleft_stu_cameras').siblings('div.treetwo').find('.treeleft_stu_m_name').attr('data-ip');
	obj.pointEntity.port = active().parents('.treeleft_stu_cameras').siblings('div.treetwo').find('.treeleft_stu_m_name').attr('data-port');
	obj.id = active().parents('.treeleft_stu_cameras').siblings('div.treetwo').find('.treeleft_stu_m_name').attr('data-tp_id');
	obj.deviceId = active().attr('data-deviceId');
	obj.beginTime = objpro.find('.begintime').val();
	if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test(objpro.find('.begintime').val()) == false) {
		swal({
			title: '采集开始时间格式不正确',
			text: "",
			type: "warning",
			showCancelButton: false,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "确定",
			closeOnConfirm: false
		});
		return
	}
	obj.endTime = objpro.find('.endtime').val();
	if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test(objpro.find('.endtime').val()) == false) {
		swal({
			title: '采集结束时间格式不正确',
			text: "",
			type: "warning",
			showCancelButton: false,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "确定",
			closeOnConfirm: false
		});
		return
	}
	obj.cycleDay = objpro.find('.cycleday').val();
	if(/^[1-9]\d*$/.test(objpro.find('.cycleday').val()) == false) {
		swal({
			title: '采集周期必须是正整数',
			text: "",
			type: "warning",
			showCancelButton: false,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "确定",
			closeOnConfirm: false
		});
		return
	}

	obj.monitorId = objpro.find('.monitorId').val();
	if(/^[1-9]\d*$/.test(objpro.find('.monitorId').val()) == false) {
		swal({
			title: '采集时间间隔必须是正整数',
			text: "",
			type: "warning",
			showCancelButton: false,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "确定",
			closeOnConfirm: false
		});
		return
	};
	$.ajax({
		url: http + "addIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.success) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
				listIPCPoint01();
			} else if(data.state == 2) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				}, function() {
					window.parent.location.href = './login.html'
				});
				return
			} else {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
			}
		},
		error: function() {
			swal({
				title: '请求失败，请重新尝试',
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#3366FF",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return
		}
	});
}

//编辑保存监视点-验证
function updateIPCPoint(thisObj) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = {};
	obj.pointEntity.ip = active().parents('.treeleft_stu_cameras').siblings('div.treetwo').find('.treeleft_stu_m_name').attr('data-ip');
	obj.pointEntity.port = active().parents('.treeleft_stu_cameras').siblings('div.treetwo').find('.treeleft_stu_m_name').attr('data-port');
	obj.id = active().parents('.treeleft_stu_cameras').siblings('div.treetwo').find('.treeleft_stu_m_name').attr('data-tp_id');
	obj.deviceId = active().attr('data-deviceId');
	obj.beginTime = thisObj.find('.begintime').val();
	if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test(thisObj.find('.begintime').val()) == false) {
		swal({
			title: '采集开始时间格式不正确',
			text: "",
			type: "warning",
			showCancelButton: false,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "确定",
			closeOnConfirm: false
		});
		return
	}
	obj.endTime = thisObj.find('.endtime').val();
	if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test(thisObj.find('.endtime').val()) == false) {
		swal({
			title: '采集结束时间格式不正确',
			text: "",
			type: "warning",
			showCancelButton: false,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "确定",
			closeOnConfirm: false
		});
		return
	}
	obj.cycleDay = thisObj.find('.cycleday').val();
	if(/^[1-9]\d*$/.test(thisObj.find('.cycleday').val()) == false) {
		swal({
			title: '采集周期必须是正整数',
			text: "",
			type: "warning",
			showCancelButton: false,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "确定",
			closeOnConfirm: false
		});
		return
	}

	obj.monitorId = thisObj.find('.monitorId').val();
	if(/^[1-9]\d*$/.test(thisObj.find('.monitorId').val()) == false) {
		swal({
			title: '采集时间间隔必须是正整数',
			text: "",
			type: "warning",
			showCancelButton: false,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "确定",
			closeOnConfirm: false
		});
		return
	};
	$.ajax({
		url: http + "updateIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.success) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
				/*location.reload(); */
			} else if(data.state == 2) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				}, function() {
					window.location.href = './login.html'
				});
				return
			} else {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
				return
			}
		}
	});
}
/*图片集合*/
function listIPCPoint03() {
	$.ajax({
		url: http + "listIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify({
			"cksid": sessionStorage.getItem('cksid'),
			"ckuid": sessionStorage.getItem('ckuid'),
			'id': active().attr('data-tp_id'),
			'deviceId': active().attr('data-deviceId')
		}),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$('.pics_content').empty();
				if(data.object.length == 0) {
					$('.pic_con_l_out').find('.ts').show();
				} else {
					$('.pic_con_l_out').find('.ts').hide();
				}
			} else if(data.state == 2) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				}, function() {
					window.location.href = './login.html'
				});
				return
			} else {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
				return
			}
		}
	});
}
/*每次调用每次新建*/


//传感器信息表格编辑

/*传感器信摄像头捕获*/
function getShopCamera(id) {
	loadShow();
	$.ajax({
		url: http + "getShopCamera",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify({ "mapingDeviceId": id }),
		cache: false,
		success: function(data) {
			removeload()
			if(data.state == 0) {
				if(data.object != null) {
					sessionStorage.setItem('szIP', active().attr('data-ip')),
						sessionStorage.setItem('szPort', data.object.s_proxy),
						sessionStorage.setItem('szUsername', data.object.username),
						sessionStorage.setItem('szPassword', data.object.password);
					$('#shipin01').attr('src', './demo.html').addClass('fiexleft').removeClass('fiexright').show()
					setTimeout(function() {
						$("#shipin01").contents().find('#denglu').click();
					}, 300)
				} else {
					swal({
						title: '请求失败，请重新尝试',
						text: "",
						type: "warning",
						showCancelButton: false,
						confirmButtonColor: "#3366FF",
						confirmButtonText: "确定",
						closeOnConfirm: false
					});
					return
				}

			} else if(data.state == 2) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				}, function() {
					window.parent.location.href = './login.html'
				});
				return
			} else {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
				return
			}
		},
		error: function() {
			removeload()
			swal({
				title: '请求失败，请重新尝试',
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#3366FF",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return
		}
	});
}
//传感器信息摄像头---下面的监视点
function listIPCPoint02() {
	loadShow();
	$.ajax({
		url: http + "listIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify({
			"cksid": sessionStorage.getItem('cksid'),
			"ckuid": sessionStorage.getItem('ckuid'),
			'id': active().attr('data-tp_id'),
			'deviceId': active().attr('data-deviceId')
		}),
		cache: false,
		success: function(data) {
			removeload();
			if(data.state == 0) {
				$('.shexiangtoujsdList1').empty();
				if(data.object.length == 0) {
					$('.r_bot6').find('p.ts').show()
				} else {
					$('.r_bot6').find('p.ts').hide()
					$(data.object).each(function(i, el) {
						$('.shexiangtoujsdList1').append("<li data-monitorId=" + data.object[i].monitorId + ">" + data.object[i].monitorName + "</li>")
					})
					$('.shexiangtoujsdList1>li').on('click', function() {
						$(this).addClass('active').siblings().removeClass('active')
						$("#shipin01").contents().find('#preset').val($(this).attr('data-monitorid'));
						$("#shipin01").contents().find('#diaoyong').click();
					})
				}
			} else if(data.state == 2) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				}, function() {
					window.parent.location.href = './login.html'
				});
				return
			} else {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
				return
			}
		},
		error: function() {
			removeload()
			swal({
				title: '请求失败，请重新尝试',
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#3366FF",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return
		}
	});
}
function listPoint() {
		$.ajax({
			url: http + "listPoint",
			type: "post",
			contentType: "application/json",
			cache: false,
			data: JSON.stringify({
				"ckuid": sessionStorage.getItem('ckuid'),
				"cksid": sessionStorage.getItem('cksid'),
				"tp_pid": 0,
				"u_type":sessionStorage.getItem('utype'),
			}),
			success: function(data) {
				if(data.state == 0) {
					var str = "";var num=0;
					$(data.object).each(function(i, el) {
						str += '<div class="points"><p class="treeleft_head">' + data.object[i].tp_name + '</p>'
						$(data.object[i].rank).each(function(l, el) {
							str += '<ul class="treeleft_second"><li class="treeleft_student"><span class="leftTops"></span><span class="leftLies">' + data.object[i].rank[l].tp_name + '</span><ul class="treeleft_stu_lists">'
							$(data.object[i].rank[l].rank).each(function(y, el) {
								num++;
								str += '<li class="treeleft_stu_menu"><span class="treeleft_stu_m_lline"></span><div class="treetwo"><img src="./img/treen/online.png" class="treetwoImg"/><span class="treeleft_stu_m_name" data-tp_id="' +
										data.object[i].rank[l].rank[y].tp_id + '" data-supervisername=" ' + data.object[i].rank[l].rank[y].supervisername +
										'"data-state="' + data.object[i].rank[l].rank[y].state + '" data-producername="' + data.object[i].rank[l].rank[y].producername + '" nums="'+num+'" data-name=" ' + data.object[i].rank[l].rank[y].name + '" data-exportorname=" ' +
										data.object[i].rank[l].rank[y].exportorname + '" data-x="' + data.object[i].rank[l].rank[y].x + '" data-y="' + data.object[i].rank[l].rank[y].x + '" data-deviceId="' +
										data.object[i].rank[l].rank[y].deviceId + '" data-ip="' + data.object[i].rank[l].rank[y].ip + '" data-port="' + data.object[i].rank[l].rank[y].port + '">' +
									data.object[i].rank[l].rank[y].tp_name + '</span></div><ul class="treeleft_stu_cameras">'
								$(data.object[i].rank[l].rank[y].rank).each(function(z, el) {
									str += '<li class="treeleft_stu_camera" class="treeleftHasdate"><span class="fatherLine"></span><span class="treeHasdata" data-ip="'+data.object[i].rank[l].rank[y].rank[z].ip+'" data-deviceId="'
									+data.object[i].rank[l].rank[y].rank[z].deviceId+'" data-tp_id="'+data.object[i].rank[l].rank[y].rank[z].tp_id+'">' + data.object[i].rank[l].rank[y].rank[z].tp_name + '</span></li>'
								})
								str += '</ul></li>';
								/*	未显示个数*/
							})
							str += '</ul></li></ul>'
						})
						/*str+='<li class="treeleft_stu_last"><span id="">[3号] 王大富 草莓</span></li>'*/
						str += '</div>'
						$(".treeleft").empty().append(str);
						$('.treetwo').each(function() {
						if($(this).find('.treeleft_stu_m_name').attr('data-state') == 2) { //2不在线
							$(this).find('img').attr('src', './img/shexiang/ico_disconnect.png');
							$(this).find('.treeleft_stu_m_name').addClass('noline').removeClass('online')
						} else {
							$(this).find('img').attr('src', './img/shexiang/ico_device.png');
							$(this).find('.treeleft_stu_m_name').addClass('online').removeClass('noline')
						}
					})
						if(getUrlKey('num')){
							var nus=getUrlKey('num');
							$('.treeleft_stu_m_name').each(function(){
								if($(this).attr('nums')==nus){
									$(this).parents('.treetwo').click();
									$(this).parents('.treetwo').siblings('.treeleft_stu_cameras').show().find('.treeleft_stu_camera').find('.treeHasdata').addClass('active').click();
								}
							})
						}else{
							$('.treeleft_second:eq(0)').find('.treeleft_student:eq(0)').find('.treeleft_stu_lists').find('.treeleft_stu_menu:eq(0)').find('.treetwo:eq(0)').find('.treeleft_stu_m_name').click();
							$('.treeleft_second:eq(0)').find('.treeleft_student:eq(0)').find('.treeleft_stu_lists').find('.treeleft_stu_menu:eq(0)').find('.treeleft_stu_cameras').find('.treeleft_stu_camera:eq(0)').find('.treeHasdata').addClass('active').click();
						}
						$('.treeleft_stu_cameras').find('.treeleft_stu_camera:eq(0)').addClass('treeleft_stu_camera_first');
						
						/*切换摄像头*/
						 $('.treetwo').each(function(){
                    	if($(this).find('.treeleft_stu_m_name').attr('data-state')==2){//2不在线
                    		$(this).find('img').attr('src','./img/shexiang/ico_disconnect.png');
                    	}else{
                    		$(this).find('img').attr('src','./img/shexiang/ico_device.png');
                    	}
                    })
						$('.leftLies').on('click', function() {
							if($(this).siblings('ul').is(":visible") == false) {
								$(this).siblings('ul').show();
							} else {
								$(this).siblings('ul').hide();
							}
						})
						$('.treetwo').on('click', function() {
							if($(this).siblings('ul').is(":visible") == false) {
								$(this).siblings('ul').show();
							} else {
								$(this).siblings('ul').hide();
							}
						})
					})
				} else if(data.state == 2) {
					 swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#3366FF",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                },function(){
                    window.location.href='./login.html'
                });
					return 
				} else {
					swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#3366FF",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                });
                return
				}
			}

		})

	};
	/*摄像头结束*/
	function getUrlKey(name) {
			return decodeURIComponent((
				new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
		};
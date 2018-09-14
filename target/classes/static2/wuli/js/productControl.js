var configNum = 0;
var biaozhunarr = ['', '有机', '绿色', '无公害'];
var viewId = '';
var diviceios;
$(function() {
	listPoint();
	$(document).on('click', '.treetwo', function() {
		$('.treetwo').css('background-color', '#fff');
		$('.treeHasdata').removeClass('active');
		$('.treeleft_stu_m_name').removeClass('active');
		$('.treeleft_stu_camera').css('background-color', '#fff');
		$(this).css('background-color', '#3366FF');
		$(this).find('.treeleft_stu_m_name').addClass('active');
		$(this).siblings('.treeleft_stu_cameras').find('.treeleft_stu_camera').eq(0).css('background-color', '#3366FF').find('.treeHasdata').addClass('active');
		$("#fir_pro_menu").hide();
		$('.inte_v_c_deinpuOut>input').val('');
		/*控制管理*/
		listControlSetting();
		/*检测设备实时显示*/
		listSensorChartInfo();
	})
	$(document).on('click', '.treeleft_stu_camera', function() {
		$('.treetwo').css('background-color', '#fff');
		$('.treeleft_stu_camera').css('background-color', '#fff');
		$(this).parent().siblings('.treetwo').css('background-color', '#3366FF');
		$('.treeHasdata').removeClass('active');
		$('.treeleft_stu_m_name').removeClass('active');
		$(this).css('background-color', '##3366FF');
		$(this).parent().siblings('.treetwo').find('.treeleft_stu_m_name').addClass('active');
		$(this).find('.treeHasdata').addClass('active');
		if(active1() == undefined) {

		} else {
			$("#fir_pro_menu").hide();
			takePic();
		}
	});

	// 控温设备
	/*	$('.inte_v_con_kongwen').find('.inte_v_contro_li').click(function(){
			var index = $(this).index();
			var that=$(this);
			var test=$(this).text();
			swal({
	            title: '确定更换为'+test+'？',
	            text: "",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#3366FF",
	            confirmButtonText: "确定",
	            cancelButtonText: "取消",
	            closeOnConfirm: true
	        },function(){
	        	that.addClass('actived').siblings().removeClass('actived');
	        	if(index==0){
	        		$('.inte_vi_dataGroup').find('.inte_v_control').eq(0).show().siblings().hide();
	        	}else{
	        		$('.inte_vi_dataGroup').find('.inte_v_control').eq(2).show().siblings().hide();
	        		intellen(1);
	        	};
	        })
		});*/
	//非控温设备
	$('.inte_v_con_feiwen').find('.inte_v_contro_li').click(function() {
		var index = $(this).index();
		var that = $(this);
		var test = $(this).text();
		swal({
			title: '您确认要切换控制模式么？',
			text:'',
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "确认切换",
			cancelButtonText: "再等一下",
			closeOnConfirm: true
		}, function(){
				swal({
					title: '切换成功',
					type: "warning",
					confirmButtonColor: "#3366FF",
					confirmButtonText: "好",
					closeOnConfirm: true
				})
					that.addClass('actived').siblings().removeClass('actived');
					$('.inte_v_control').eq(index).show().siblings().hide();
					if(index == 1) {
						setRuleList(1,2); //预约控制   第二个参数 2代表切换提示   1代表默认展示
					} else if(index == 2) { //智能     
						intellen(1,2);		//第二个参数 2代表切换提示   1代表默认展示
					}
		})
	});
	//电磁阀控制开关--//电机右侧第一个智能图
	$('.line_water>img:eq(0)').click(function() {
		controlDev(0);
	});
	$('.line_water>img:eq(1)').click(function() {
		controlDev(-1);
	});
	//电机执行开关
	$('.inte_v_c_img>img').click(function() {
		var index = $(this).index();
		$(this).addClass('acs').siblings().removeClass('acs');
		if(index == 0) {
			$(this).attr('src', './img/control/2687.svg');
			$('.inte_v_c_img>img:eq(1)').attr('src', './img/control/2694.svg');
			$('.inte_v_c_img>img:eq(2)').attr('src', './img/control/2695.svg');
		} else if(index == 1) {
			$(this).attr('src', './img/control/2697.svg');
			$('.inte_v_c_img>img:eq(0)').attr('src', './img/control/2693.svg');
			$('.inte_v_c_img>img:eq(2)').attr('src', './img/control/2695.svg');
		} else {
			$(this).attr('src', './img/control/2698.svg');
			$('.inte_v_c_img>img:eq(0)').attr('src', './img/control/2693.svg');
			$('.inte_v_c_img>img:eq(1)').attr('src', './img/control/2694.svg');
		}
	})
	$('.inte_v_c_degree>img').on('click', function() {
		var oInde = $(this).parent().siblings('.inte_v_c_img').find('.acs').index() + 1;
		var that = $(this).siblings('input');
		controlDev('', oInde, that); //电机右侧第一个智能图
	})

	/* 数据实时*/
	/*$('.dataShow').click(function() {
		$('.product_content').show();
		$('.proFirData').hide();
	});*/
	//实时关闭
	/*$('.backData').click(function() {
		$('.product_content').hide();
		$('.proFirData').show();
	});*/
	/*    $('.inte_vi_dataGroup').css('width','')*/
});

//关闭摄像头

//预加载图片视频
function loadImage(url) {
	var img = new Image();
	img.src = url;
	img.onload = function() { //图片下载完毕时异步调用callback函数。
		$('.inte_vido').attr('src', img.src)
	};
}
//获取视频图片
function takePic() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.deviceId = active1().attr('data-deviceid');
	loadShow();
	$.ajax({
		url: http + "listLastIPCPointIMG",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.state == 0) {
				if(data.object!=null){
					var strImg = http + 'getReportImage?file_name=' + data.object.fileName
					var mohuimg = http + 'getReportImage?file_name=' + data.object.smallName;
					$('.inte_vido').attr('src', mohuimg);
					loadImage(strImg);
					$('.inte_vido').click(function() {
						secondClick();
					})
				}else{
					var strImg = ''
					var mohuimg = '';
					$('.inte_vido').attr('src', mohuimg);
					$('.inte_vido').click(function() {
						return
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
			return;
		}
	});
}

/*初始化视频下面的控制*/

function secondClick() {
	var browser = {
		versions: function() {
			var u = navigator.userAgent,
				app = navigator.appVersion;
			return { //移动终端浏览器版本信息
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
		}(),
		language: (navigator.browserLanguage || navigator.language).toLowerCase()
	}
	if(browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
		getDeviceIDiOS(active1().attr('data-deviceid'), 0, 0, 0, 0);
	}
	if(browser.versions.android) {
		window.AndroidView.showView(active1().attr('data-deviceid'), 0, 0, 0, 0);
	}
}

function getControlDevStatus(id, type, ctrl) { //ctrl 1电机2电磁阀   type控制类型 1光 2温 3水
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.tp_id = active().attr('data-tp_id');
	obj.ctrl_id = id;
	obj.pointEntity = { 'deviceId': active().attr('data-deviceId'), 'ip': active().attr('data-ip'), 'port': active().attr('data-port') };
	obj.hbm = {};
	loadShow();
	$.ajax({
		url: http + "getControlDevStatus",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.state == 0) {
				if(data.object!=null){
						var oIne;
						//默认展示         //记住上次的选择--oIne 
						$(data.object.list).each(function(i, el) { //state 0自动  1 预约 2 智能
							if(el.state == 1) {
								oIne = i
								return;
							}
						});
						$('.inte_v_con_feiwen').find('.inte_v_contro_li').eq(oIne).addClass('actived').siblings().removeClass('actived');
						$('.inte_v_control').eq(oIne).show().siblings().hide();
						if(oIne == 0) { //自动控制
							//电磁阀控制开关--//电机右侧第一个智能图
							if(ctrl == 1) { //电机
								$(".line_water").hide();
								$('.inte_v_dianji').show();
								$('.inte_v_c_degree>input').val('');
								if(data.object.list[0].state == 1) { //1 开
									$('.inte_v_c_degree>input').val(data.object.s_state + '%');
									$('.inte_v_c_img>img').each(function(i, el) {
										if(i == 0) {
											$(this).attr('src', './img/control/2693.svg')
										} else if(i == 1) {
											$(this).attr('src', './img/control/2694.svg');
										} else {
											$(this).attr('src', './img/control/2695.svg');
										}
									})
									$('.inte_v_c_degree').find('img').attr('src', './img/control/2699.svg');
		
								} else {
									$('.inte_v_c_degree>input').val(data.object.s_state + '%');
									$('.inte_v_c_img>img').each(function(i, el) {
										if(i == 0) {
											$(this).attr('src', './img/control/2693.svg')
										} else if(i == 1) {
											$(this).attr('src', './img/control/2694.svg')
										} else {
											$(this).attr('src', './img/control/2698.svg')
										}
									})
									$('.inte_v_c_degree').find('img').attr('src', './img/control/2696.svg')
								}
								$('.inte_v_c_img>img').click();
							} else if(ctrl == 2) { //电磁阀
								$(".line_water").show();
								$('.inte_v_dianji').hide();
								if(data.object.s_state == 1) { //开
									$('.line_water').find('.line_water_stop').attr('src', './img/control/2695.svg');
									$('.line_water').find('.fr').attr('src', './img/control/2700.svg');
									$('.line_water>img:eq(1)').click();
								} else {
									$('.line_water').find('.line_water_stop').attr('src', './img/control/2698.svg');
									$('.line_water').find('.fr').attr('src', './img/control/2701.svg');
									$('.line_water>img:eq(0)').click();
								}
		
							}
						} else if(oIne == 1) {
							//预约
							setRuleList(1,1); //预约控制  第二个参数1代表默认展示
						} else {
							//智能                       //第二个参数1代表默认展示
							intellen(1,1);
						}
						//先不做
						if(type == 1) { //光
							if(data.object.s_state != 0) { //开
								$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/light_on.svg');
							} else {
								$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/light_off.svg');
							}
						}
						if(type == 2) { //温
							if(data.object.s_state != 0) { //开
								$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/unfold.svg');
							} else {
								$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/fold.svg');
							}
						}
						if(type == 3) { //水
							if(data.object.s_state != 0) { //开
								$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/water_on.svg');
							} else {
								$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/water_off.svg');
							}
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
			return;
		}
	});
}

function active() {
	var state;
	$('.treeleft_stu_m_name').each(function() {
		if($(this).hasClass('active')) {
			state = $(this)
		}
	})
	return state
}

function active1() {
	var state;
	$('.treeHasdata').each(function() {
		if($(this).hasClass('active')) {
			state = $(this);
		}
	})
	return state
}

/*function diviceiosId(active1().attr('data-deviceid')){
	
}*/
/*获取设置按钮*/
function active2() {
	var state;
	$('.menu_eq_list ul>li').each(function() {
		if($(this).hasClass('active')) {
			state = $(this)
		}
	})
	return state
}

function active3() {
	var state;
	$('.inte_v_contro>div').each(function() {
		if($(this).hasClass('actived')) {
			state = $(this)
		}
	})
	return state
}
/*控制管理*/
function listControlSetting() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = { 'deviceId': active().attr('data-deviceId'), 'tp_id': active().attr('data-tp_id') }
	loadShow();
	$.ajax({
		url: http + "listControlSetting",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			active();
			removeload();
			if(data.state == 0) {
				$('.menu_eq_list ul').empty().append('<li class="fl active nowsPlace">现场监测</li>');
				if(data.object!=null){
					$(data.object).each(function(i, el) {
						console.log( data.object);
						$('.menu_eq_list ul').append("<li class='fl' data-ctrl_id=" + data.object[i].ctrl_id + " ctrl-type=" + data.object[i].ctrl_type + " data-picType=" + data.object[i].ctrl_picturetype + ">" + data.object[i].ctrl_name + "</li>")
					});
					var len = data.object.length + 1;
					var marRigh = (parseInt($('.menu_eq_list ul li').eq(0).outerWidth()) + parseInt($('.menu_eq_list ul li').eq(0).css('margin-right'))) * len;
	
					$('.menu_eq_list ul').css('width', marRigh + 'px');
					var oTr=marRigh-$(window).innerWidth();
					if(oTr>10){
						$('.toRight').show();
					}else{
						$('.toRight').hide();
					}
					$('.menu_eq_list').on('scroll', function() {
						if($('.menu_eq_list').scrollLeft() >= oTr) {
							$('.toRight').hide();
						} else {
							$('.toRight').show();
						}
					});
					$(document).scroll(function(){
						if($(document).scrollTop()>0){
							$('.toRight').hide();
						}else{
							if(oTr>10){
								$('.toRight').show();
							}else{
								$('.toRight').hide();
							}
						}
					})
					
				}
				$('.menu_eq_list ul li').on('click', function() {//点击控温 控水
					$(this).addClass('active').siblings().removeClass('active');
					if($(this).index() == 0) {
						$('.inte_v_contro_out').hide();
						$('.inte_vi_dataBg').hide();
						$('.product_content').show();
						$('.picHave').show();
					} else {
						//纵向的滚动条
						$('.picHave').hide();
						//纵向的滚动条
						$('.picHave').hide();
						$('.inte_v_contro_out').show();
						$('.inte_vi_dataBg').show();
						$('.product_content').hide();
						$('.inte_v_con_feiwen').show();
						//attr('ctrl-type') == 1)电机--控温
						//attr('ctrl-type') == 2)电阀--控光控水
						$('.product_content').find('.li1>.menu_e_l_t').text('智能' + $(this).text() + '状态');
						$("#hiddenName").val($(this).text()); // id  type控制类型 1光 2温 3水      电磁阀   //ctrl-type 1电机2电磁阀
						//自动控制       电机与电磁阀显示与否
						console.log(active2().attr('ctrl-type'));
						if(active2().attr('ctrl-type')==1){
							$('.line_water').hide();
							$('.inte_v_dianji').show();
						}else if(active2().attr('ctrl-type')==2){
							$('.line_water').show();
							$('.inte_v_dianji').hide();
						}
						//默认显示
						getControlDevStatus(active2().attr('data-ctrl_id'), active2().attr('data-pictype'), active2().attr('ctrl-type'));
					}
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
				}, function() {
					window.location.href = './login.html'
				});
				return;
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
				return;
			}
		},
		error: function() {
			removeload();
			swal({
				title: '请求失败，请重新尝试',
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#3366FF",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
		}
	});
}
//自动控制开和关
function controlDev(num, up, that) {
	var obj = new Object();
	obj.ruleEntity = {};
	obj.hbm = {};
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.ctrl_id = active2().attr('data-ctrl_id');
	if(active2().attr('ctrl-type') == 1 && up < 3) { //电机上升下降
		obj.distanceOrDuration = that.val(); //执行开启度值
		if(obj.distanceOrDuration == '') {
			swal({
				title: '开启度不能为空',
				text: "",
				confirmButtonText: "确定",
				confirmButtonColor: "#3366FF",
			});
			return
		}
	} else {
		obj.distanceOrDuration = num; //-1 开 0 关--电磁阀
	}
	obj.hbm.direction = up;
	obj.tp_id = active().attr('data-tp_id');
	obj.pointEntity = { 'deviceId': active().attr('data-deviceid'), 'ip': active().attr('data-ip'), 'port': active().attr('data-port') };
	parent.loadShow();
	$.ajax({
		url: http + "controlDev",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload();
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					
					customClass: 'leftss'
				});
				//true
				if(data.object == 0) { //0 关   -1或大于0开
					if(active2().attr('data-pictype') == 1) {
						$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/light_off.svg');
					} else if(active2().attr('data-pictype') == 2) {
						$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/fold.svg');
					} else if(active2().attr('data-pictype') == 3) {
						$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/water_off.svg');
					}
				} else { 
					if(active2().attr('data-pictype') == 1) {
						$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/light_on.svg');
					} else if(active2().attr('data-pictype') == 2) {
						$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/unfold.svg');
					} else if(active2().attr('data-pictype') == 3) {
						$('li.li1>.menu_e_l_pic').find('img').attr('src', './img/sensor/water_on.svg');
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
			parent.removeload()
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
//预约控制
function setRuleList(num,types) {  //types==2代表切换
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.r_name = ''
	obj.type = '1';
	obj.app=1;
	obj.r_deviceId = active().attr('data-deviceId')
	obj.cycleDay = '1';
	obj.ctrl_id = active2().attr('data-ctrl_id');
	obj.pointEntity = { 'ip': active().attr('data-ip'), 'port': active().attr('data-port'), 'deviceId': active().attr('data-deviceId') }
	if(num == -1) {
		obj.ctrl_id = active2().attr('data-id')
	} else {

	}
	loadShow();
	$.ajax({
		url: http + "setRuleList",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.success) {
				$('.wa_intel').empty();
				if(data.object!=null) {
						var execEndTime;
						if(data.object.execEndTime != null){
							 execEndTime = data.object.execEndTime;
						}else{
							 execEndTime  = '无';
						}
						var beginTime = data.object.beginTime.substr(0,10).replace(/\//g,'.');
						var endTime=data.object.endTime.substr(0,10).replace(/\//g,'.');
						$('.wa_intel').append('<li style="height:.225rem"><span>' + data.object.execTime + '打开</span><span>' + execEndTime + '关闭</span></li><li><span>开启度</span><span>'+data.object.grade+'%</span><span style="margin-right:0">'+beginTime +'-'+endTime+'</span></li>')
				} else {
					$('.wa_intel').html('当前没有预约规则，请去添加');
					swal({
						title: '当前没有预约规则',
						text: "",
						type: "warning",
						confirmButtonText: "确定",
						confirmButtonColor: "#3366FF",
					});
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
//预约内容----去掉
function listRule() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.r_type = '1';
	//obj.r_deviceId=active().attr('data-deviceId')
	obj.ctrl_id = active2().attr('data-ctrl_id')
	$.ajax({
		url: http + "listRule",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				if(Object.prototype.toString.call(data.object) === '[object Array]') {
					$('.wa_intel').empty()
					$(data.object).each(function(i, el) {
						$('.wa_intel').append('<li><span style="padding-right:.3rem">启动时间:' + data.object[i].execTime + '</span><span>执行时长:' + data.object[i].duration + '</span></li>')
					})
				} else {
					$('.wa_intel').html('当前没有预约规则，请去添加');
					swal({
						title: '当前没有预约规则',
						text: "",
						type: "warning",
						confirmButtonText: "确定",
						confirmButtonColor: "#3366FF",
					});
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


/*检测设备实时显示*/
function listSensorChartInfo() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.tp_id = active().attr('data-tp_id');
	obj.deviceId = active().attr('data-deviceId')
	loadShow();
	$.ajax({
		url: http + "listSensorChartInfo",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			$(".menu_equip").hide();
			removeload()
			if(data.state == 0) {
				var arrS = ['', '正常', '不正常'];
				$('.menu_data_bg').empty();
				//var str = '<li class="li1 menu_e_listinfo"> <p class="menu_e_l_t">智能控温状态</p> <div class="menu_e_l_pic"><img src="" alt=""></div><p class="menu_e_l_b"></p></li>';
				var str=''
				$(data.object.unit).each(function(i, el) {
					if(data.object.unit[i].chartID == 0) {

					}
					//雨量3  0-100
					if(data.object.unit[i].chartID == 3) {
						str += '<li data-t=' + data.object.unit[i].chartID + ' data-num="' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + '" class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/Rainfall_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/Rainfall_B.svg" alt=""></div><p class="menu_e_l_b">' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + data.object.unit[i].unit + '</p></li>'
					}
					//风向 4  16种
					if(data.object.unit[i].chartID == 4) {
						str += '<li data-t=' + data.object.unit[i].chartID + ' data-num="' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + '" class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><img src="./img/sensor/Weathercock_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/Weathercock_B.svg" alt=""></div><p class="menu_e_l_b">' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + data.object.unit[i].unit + '</p></li>'
					}
					//风速5 0-32.4m/s
					if(data.object.unit[i].chartID == 5) {
						str += '<li data-t=' + data.object.unit[i].chartID + ' data-num="' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + '" class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/Windspeed_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/Windspeed_B.svg" alt=""></div><p class="menu_e_l_b">' + data.object.unit[i].name + "：" + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + data.object.unit[i].unit + '</p></li>'
					}
					//na 9 氮肥
					if(data.object.unit[i].chartID == 9) {
						str += '<li data-t=' + data.object.unit[i].chartID + ' data-num="' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + '" class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/N_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/N_B.svg" alt=""></div><p class="menu_e_l_b">' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + data.object.unit[i].unit + '</p></li>'
					}
					//ka 10 钾肥
					if(data.object.unit[i].chartID == 10) {
						str += '<li data-t=' + data.object.unit[i].chartID + ' data-num="' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + '" class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/ka_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/ka_B.svg" alt=""></div><p class="menu_e_l_b">' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + data.object.unit[i].unit + '</p></li>'
					}
					//co2 11 0-3000
					if(data.object.unit[i].chartID == 11) {
						str += '<li data-t=' + data.object.unit[i].chartID + ' data-num="' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + '" class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/co2_front.svg" alt="" style="z-index: 2"> <img src="./img/sensor/co2_back.svg" alt=""></div><p class="menu_e_l_b">' + data.object.unit[i].name + "：" + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + data.object.unit[i].unit + '</p></li>'
					}
					//o2 12
					if(data.object.unit[i].chartID == 12) {
						str += '<li data-t=' + data.object.unit[i].chartID + ' data-num="' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + '" class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/o2_B.svg" alt="" style="z-index: 2"> <img src="./img/sensor/o2_F.svg" alt=""></div><p class="menu_e_l_b">' + data.object.unit[i].name + "：" + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + data.object.unit[i].unit + '</p></li>'
					}
					//ph 13
					if(data.object.unit[i].chartID == 13) {
						str += '<li data-t=' + data.object.unit[i].chartID + ' data-num="' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + '" class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/pH_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/pH_B.svg" alt=""></div><p class="menu_e_l_b">' + data.object.unit[i].name + "：" + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + data.object.unit[i].unit + '</p></li>'
					}
					//空气温度1  土壤温度8  -40~60
					if(data.object.unit[i].chartID == 1 || data.object.unit[i].chartID == 8) {
						str += '<li data-t=' + data.object.unit[i].chartID + ' data-num="' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + '" class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/temperature_front.svg" alt="" style="z-index: 2"> <img src="./img/sensor/temperature_back.svg" alt=""></div><p class="menu_e_l_b">' + data.object.unit[i].name + "：" + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + data.object.unit[i].unit + '</p></li>'
					}
					//空气湿度2   土壤水分7  0-100
					if(data.object.unit[i].chartID == 2 || data.object.unit[i].chartID == 7) {
						str += '<li data-t=' + data.object.unit[i].chartID + ' data-num="' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + '" class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><img src="./img/sensor/humidity_pointer.svg" alt="" style="z-index: 2;top: .25rem;"> <img src="./img/sensor/humidity_dashboard.svg" alt=""></div><p class="menu_e_l_b">' + data.object.unit[i].name + "：" + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + " 单位:" + data.object.unit[i].unit + '</p></li>'
					}
					//光 0-200000
					if(data.object.unit[i].chartID == 6) {
						str += '<li data-t=' + data.object.unit[i].chartID + '  class="menu_e_listinfo"><p class="menu_e_l_t">' + data.object.unit[i].name + '</p><div class="menu_e_l_pic"><img src="./img/sun.svg" alt=""></div><p class="menu_e_l_b">' + picNumber(data.object.unit[i].fieldName, data.object.data[0]) + " 单位:" + data.object.unit[i].unit + '</p></li>'
					}
				});
				$('.menu_data_bg').append(str);

				$('.menu_data_bg>li').each(function(i, el) {
					//雨量
					if(i % 2 == 0) {
						$(this).css('float', "right");
					}
					//温度     -40~60
					//旋转空气湿度2   土壤水分7 
					if($(this).attr('data-t') == 2 || $(this).attr('data-t') == 7) {
						var h = 120;
						var maxh = 100;
						var h1 = parseInt($(this).attr('data-num'))
						var h2 = h1 * h / maxh;
						if(h1 <= 0) {
							h2 = 0
						}
						$(this).find('img').eq(0).rotate({ animateTo: h2, duration: 2000 });
					}
					//雨量
					if($(this).attr('data-t') == 3 || $(this).attr('data-t') == 9 || $(this).attr('data-t') == 10 || $(this).attr('data-t') == 12 || $(this).attr('data-t') == 13) {
						var h = 120;
						var maxh = 100;
						var h2;
						if(parseInt($(this).attr('data-num')) > 100) {
							h2 = 100
						} else {
							var h1 = Math.abs(parseInt($(this).attr('data-num')))
							h2 = h - h1 * h / maxh
						}
						$(this).find('div.bg').animate({ 'height': h2 + 'px' }, 2000)
					};
					//空气温度1  土壤温度8  -40~60
					if($(this).attr('data-t') == 1 || $(this).attr('data-t') == 8) {
						var h = 120;
						var maxh = 100;
						var h2;
						var h1 = parseInt($(this).attr('data-num')) + 40
						h2 = h - h1 * h / maxh
						$(this).find('div.bg').animate({ 'height': h2 + 'px' }, 2000)
					}
					//co2 11 0-3000
					if($(this).attr('data-t') == 11) {
						var h = 120;
						var maxh = 3000;
						var h2;
						var h1 = parseInt($(this).attr('data-num'))
						h2 = h - h1 * h / maxh
						$(this).find('div.bg').animate({ 'height': h2 + 'px' }, 2000)
					};
					//风速5 0-32.4m/s
					if($(this).attr('data-t') == 5) {
						var h = 120;
						var maxh = 32.4;
						var h2;
						var h1 = parseInt($(this).attr('data-num'))
						h2 = h - h1 * h / maxh
						$(this).find('div.bg').animate({ 'height': h2 + 'px' }, 2000)
					}
					//风向 4  16种
					if($(this).attr('data-t') == 4) {
						var h1 = $(this).attr('data-num');
						switch(h1) {
							case '北':
								h2 = 0;
								break;
							case '东北偏北':
								h2 = 22.5;
								break;
							case '东北':
								h2 = 45;
								break;
							case '东北偏东':
								h2 = 67.5;
								break;
							case '东':
								h2 = 90;
								break;
							case '东南偏东':
								h2 = 112.5;
								break;
							case '东南':
								h2 = 135;
								break;
							case '东南偏南':
								h2 = 157.5;
								break;
							case '南':
								h2 = 180;
								break;
							case '西南偏南':
								h2 = 202.5;
								break;
							case '西南':
								h2 = 225;
								break;
							case '西南偏西':
								h2 = 247.5;
								break;
							case '西':
								h2 = 270;
								break;
							case '西北偏西':
								h2 = 292.5;
								break;
							case '西北':
								h2 = 315;
								break;
							case '西北偏北':
								h2 = 337.5;
								break;
						}
						$(this).find('img').eq(0).rotate({ animateTo: h2, duration: 2000 });
					}

				});
				//显示更多的图标
				a = $(document).height() - $(window).height();
				if(a){
					$('.picHave').show()
				};
				$(window).on('scroll', function() {
					if($(document).scrollTop() >= a) {
						$('.picHave').hide()
					} else {
						$('.picHave').show()
					}
				})
				//显示更多的图标
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
//返回图片数值
function picNumber(name, data) {
	var num = '';
	for(x in data) {
		if(x == name) {
			num = data[x]
		}
	}
	return num
}
//确定开启
function controlMode(s) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.state_type = s;
	obj.ctrl_id = active2().attr('data-ctrl_id')
	loadShow();
	$.ajax({
		url: http + "controlMode",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.state == 0) {
				swal({
					title: data.msg,
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
				}, function() {
					getControlDevStatus(active2().attr('data-ctrl_id'), active2().attr('data-pictype'), active2().attr('ctrl-type'))
				});
				getControlDevStatus(active2().attr('data-ctrl_id'), active2().attr('data-pictype'), active2().attr('ctrl-type'))
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
				}, function() {
					active2().click();
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
//智能
function intellen(num,type) {//1代表默认展示     2代表切换
	var obj = new Object(); //{"ctrl_id":"5","type":"1"(type为1启动,type为2是停止)}
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.ctrl_id = active2().attr('data-ctrl_id')
	obj.type = num;
	obj.mo_deviceId = active().attr('data-deviceid');
	parent.loadShow();
	$.ajax({
		url: http + "setMonitorList",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload();
			if(data.state == 0) {
				//展示内容
				$('.contro_cont_zhi').empty();
					if(data.object!=null){
						//等待添加。。。。。。
						if(type==2){  
							swal({
								title: data.msg,
								confirmButtonText: "确定",
								confirmButtonColor: "#3366FF",
							});
						}
					}else{
						$('.contro_cont_zhi').html('当前没有智能规则，请去添加')
						swal({
							title: '当前没有智能规则',
							text: "",
							type: "warning",
							confirmButtonText: "确定",
							confirmButtonColor: "#3366FF",
						});
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
			parent.removeload()
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
};

function listPoint() {
	loadShow();
	$.ajax({
		url: http + "listPoint",
		type: "post",
		contentType: "application/json",
		cache: false,
		data: JSON.stringify({
			"ckuid": sessionStorage.getItem('ckuid'),
			"cksid": sessionStorage.getItem('cksid'),
			"tp_pid": 0,
			"u_type": sessionStorage.getItem('utype'),
		}),
		success: function(data) {
			removeload();
			if(data.state == 0) {
				var str = "";
				var num = 0;
				$(data.object).each(function(i, el) {
					str += '<div class="points"><p class="treeleft_head">' + data.object[i].tp_name + '</p>'
					$(data.object[i].rank).each(function(l, el) {
						str += '<ul class="treeleft_second"><li class="treeleft_student"><span class="leftTops"></span><span class="leftLies">' + data.object[i].rank[l].tp_name + '</span><ul class="treeleft_stu_lists">'
						$(data.object[i].rank[l].rank).each(function(y, el) {
							num++;
							str += '<li class="treeleft_stu_menu"><span class="treeleft_stu_m_lline"></span><div class="treetwo"><img src="./img/treen/online.png" class="treetwoImg"/><span class="treeleft_stu_m_name" data-tp_id="' +
								data.object[i].rank[l].rank[y].tp_id + '" data-supervisername=" ' + data.object[i].rank[l].rank[y].supervisername +
								'"data-state="' + data.object[i].rank[l].rank[y].state + '" data-producername="' + data.object[i].rank[l].rank[y].producername + '" nums="' + num + '" data-name=" ' + data.object[i].rank[l].rank[y].name + '" data-exportorname=" ' +
								data.object[i].rank[l].rank[y].exportorname + '" data-x="' + data.object[i].rank[l].rank[y].x + '" data-y="' + data.object[i].rank[l].rank[y].x + '" data-deviceId="' +
								data.object[i].rank[l].rank[y].deviceId + '" data-ip="' + data.object[i].rank[l].rank[y].ip + '" data-port="' + data.object[i].rank[l].rank[y].port + '">' +
								data.object[i].rank[l].rank[y].tp_name + '</span></div><ul class="treeleft_stu_cameras">'
							$(data.object[i].rank[l].rank[y].rank).each(function(z, el) {
								str += '<li class="treeleft_stu_camera" class="treeleftHasdate"><span class="fatherLine"></span><span class="treeHasdata" data-ip="' + data.object[i].rank[l].rank[y].rank[z].ip + '" data-deviceId="' +
									data.object[i].rank[l].rank[y].rank[z].deviceId + '" data-tp_id="' + data.object[i].rank[l].rank[y].rank[z].tp_id + '">' + data.object[i].rank[l].rank[y].rank[z].tp_name + '</span></li>'
							})
							str += '</ul></li>';
							/*	未显示个数*/
						})
						str += '</ul></li></ul>'
					})
					/*str += '<li class="treeleft_stu_last"><span id="">[3号] 王大富 草莓</span></li>'*/
					str += '</div>'
					$(".treeleft").empty().append(str);
					$('.treeleft_stu_cameras').find('.treeleft_stu_camera:eq(0)').addClass('treeleft_stu_camera_first');
					/*切换摄像头*/
					$('.treetwo').each(function() {
						if($(this).find('.treeleft_stu_m_name').attr('data-state') == 2) { //2不在线
							$(this).find('img').attr('src', './img/shexiang/ico_disconnect.png');
							$(this).find('.treeleft_stu_m_name').addClass('noline').removeClass('online')
						} else {
							$(this).find('img').attr('src', './img/shexiang/ico_device.png');
							$(this).find('.treeleft_stu_m_name').addClass('online').removeClass('noline')
						}
					})
					if(getUrlKey('num')) {
						var nus = getUrlKey('num');
						$('.treeleft_stu_m_name').each(function() {
							if($(this).attr('nums') == nus) {
								$(this).parents('.treetwo').click();
								$(this).parents('.treetwo').siblings('.treeleft_stu_cameras').show().find('.treeleft_stu_camera:eq(0)').click();
							}
						})
					} else {
						$('.treeleft_second:eq(0)').find('.treeleft_student:eq(0)').find('.treeleft_stu_lists:eq(0)').find('.treeleft_stu_menu:eq(0)').find('.treetwo').click();
						$('.treeleft_second:eq(0)').find('.treeleft_student:eq(0)').find('.treeleft_stu_lists:eq(0)').find('.treeleft_stu_menu:eq(0)').find('.treeleft_stu_camera:eq(0)').click();
					}
					
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
				});
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
	})

};

function getUrlKey(name) {
	return decodeURIComponent((
		new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
};

//自动控制

function controlMode(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.ctrl_id=active2().attr('data-ctrl_id');
    parent.loadShow()
    $.ajax({
        url: http+"controlMode",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload();
            if(data.state==0){
            	
            }
           }
       })
   }


//时间戳
function dealdata(ele) {
	var time = new Date(ele * 1000);
	var n = time.getFullYear();
	var y = time.getMonth() + 1;
	var r = time.getDate();
	var h = time.getHours() < 10 ? "0" + time.getHours() : time.getHours();
	var m = time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes();
	var s = time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds();
	var result = n + '/' + y + '/' + r + '/';
	return result;
}

function newTime(ele) {
      var time = new Date(ele);
      var h=time.getHours()<10 ? "0" + time.getHours():time.getHours();
      var m=time.getMinutes()<10 ? "0" + time.getMinutes() :time.getMinutes();
      var s=time.getSeconds()<10? "0"+time.getSeconds():time.getSeconds();
      var result=h+':'+m+':'+s;
      return result;
    }
$(function() {
	listPointCheck();
		if(window.location.href.substring('shows')!=-1){
			if(getUrlKey('shows')=='1'){
			$('.nav_l_de').click();
		}else{
			$('.nav_l_morede').click()
			}
		};
		$('.datas').each(function(){
		$(this).ionDatePicker({
			lang: 'zh-cn',
			format: 'YYYY-MM-DD'
		});
	});
	$('.nav_l_morede').click(function() {//多数据分析
		$('#bdequipSelOut').show();
		$('.bdequipSele').hide();
		$('.nav_l_de').removeClass('active')
		$(this).addClass('active');
		$('.bdequipselect').attr('datalist', '1');
		$('.bdouterAll').show();
		$('.bdequipshow').hide();
	})
	$('.nav_l_de').click(function() { //单数据分析
		$('#bdequipSelOut').hide();
		$('.bdequipSele').show();
		$('.nav_l_morede').removeClass('active')
		$(this).addClass('active');
		$('.bdequipselect').attr('datalist', '0');
		$('.bdouterAll').show();
		$('.bdequipshow').hide();
	})
	$('.bdequipselect').click(function() {
		var obj = new Object();
		var obj1 = new Object();
		obj1.ckdata='1'
		obj1.deviceList = []; 
		obj.deviceList = addEqui(); //所选检测器数组
		if(obj.deviceList.length == 0) {
			swal({
				title: '请选择设备',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		};
		obj1.channelList=[];
		if($(this).attr('datalist') == 0) { //单选 土壤 co2
			obj.b  = new Array($('.bdequipSele').find('option:selected').text());
		} else {
			obj.channelList = channelList(); //多选 土壤 co2
		}
		obj.beginTime = $('.bdequipdataB').val();
		obj.endTime = $('.bdequipdataE').val();
		obj1.beginTime = $('.bdequipdataB').val();
		obj1.endTime = $('.bdequipdataE').val();
		if(obj.beginTime.length != 10) {
			swal({
				title: '日期格式不正确',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		if(checkTime(obj.beginTime) == false) {
			swal({
				title: '日期格式不正确',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		if(obj.endTime.length != 10) {
			swal({
				title: '日期格式不正确',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		if(checkTime(obj.endTime) == false) {
			swal({
				title: '日期格式不正确',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		};
		 parent.loadShow();
		$.ajax({
			type: "post",
			url: http + "getStaticData",
			contentType: "application/json",
			headers: { 'Content-type': 'application/json;charset=UTF-8'},
			data: JSON.stringify(obj1),
			cache: false,
			success: function(data) {
				parent.removeload();
				if(!data.success){
					swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
					},function(){
						$('.bdouterAll').show();
						$('.bdequipshow').hide();
					});
				}else{
					$('.bdouterAll').hide();
					$('.bdequipshow').show();
				if($('.bdequipselect').attr('datalist')=='0'){
					
					var Oids = $('.bdequipSele').find('option:selected').val();
					var look = $('.bdequipSele').find('option:selected').text();
					var title=$('.bdequipSele').find('option:selected').attr('data-dan');
					window.location.href='./bigDataline.html?m_id=0&targe='+Oids+'&texts='+look+'&value='+title+'&addEqui='+addEqui()+'&beginTime='+$('.bdequipdataB').val()+'&endTime='+$('.bdequipdataE').val();
					
				}else{
					$("#mains").hide();
					$('.bdequipshList').show();
					$('.bdequipshList>li').hide();
					var newStr=channelList().join('-');
					window.location.href='./bigDataline.html?m_id=1&selects='+newStr+'&addEqui='+addEqui()+'&channelList='+channelList()+'&beginTime='+$('.bdequipdataB').val()+'&endTime='+$('.bdequipdataE').val();
				}
		}
	},
		error: function() {
			swal({
				title: '请求失败，请重新尝试',
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#30862B",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return
		}

});
})
	});
//时间格式
function checkTime(str) {
	var state = true;
	var date = str;
	var result = date.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
	if(result === null) {
		state = false;
	}
	return state
}
//添加检测器
function addEqui() {
	var oArr = [];
	var i = 0;
	$('.fa2Out').each(function() {
		if($(this).find('.checkEquip').prop('checked')) {
			oArr.push($(this).find('.pinline1').attr('data-deviceid'));
		}
	})
	return oArr;
}
//多选co2 土壤
function channelList() {
	var oarr = [];
	$('.bdequipSel').find('.bdequipSeles').each(function() {
		if($(this).prop('checked')) {
			oarr.push($(this).next().text());
		}
	});
	return oarr;
}

//选择设备
function listPointCheck() {
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
				//sessionStorage.setItem('objData',JSON.stringify(data.object))
				var str = "";
				$(data.object).each(function(i, el) {
					str += "<div class='point'><div class='icon student'> <img src='./img/shipin/ico_home.png' alt=''/> </div> <p class='pinline' style='font-size: 0.12rem'> " + data.object[i].tp_name + "</p>"
					$(data.object[i].rank).each(function(l, el) {
						str += " <ul class='navigation' style='font-size: 0.12rem'> <li> <div class='icon fa1'> <img src='./img/shipin/ico_group.png' alt=''/> </div> <p  class='pinline'>" + data.object[i].rank[l].tp_name + "</p>"
						$(data.object[i].rank[l].rank).each(function(y, el) {
							str += "<ul class='tree'> <div class='fa2Out'> <li><div class='icon fa2'> <img src='./img/shipin/ico_device.png' alt=''/> </div><input type='checkbox' class='checkEquip'/> <p  class='pinline pinline1' data-tp_id=" + data.object[i].rank[l].rank[y].tp_id + " data-supervisername=" + data.object[i].rank[l].rank[y].supervisername + " data-state=" + data.object[i].rank[l].rank[y].state + " data-producername=" + data.object[i].rank[l].rank[y].producername + " data-name=" + data.object[i].rank[l].rank[y].name + " data-exportorname=" + data.object[i].rank[l].rank[y].exportorname + " data-x=" + data.object[i].rank[l].rank[y].x + " data-y=" + data.object[i].rank[l].rank[y].y + " data-deviceId=" + data.object[i].rank[l].rank[y].deviceId + " data-ip=" + data.object[i].rank[l].rank[y].ip + " data-port=" + data.object[i].rank[l].rank[y].port + ">" + data.object[i].rank[l].rank[y].tp_name + "</p></div> <ul class='tree2'> "
						/*	$(data.object[i].rank[l].rank[y].rank).each(function(z, el) {
								str += "<li id=" + data.object[i].rank[l].rank[y].rank[z].tp_id + "><div class='icon fa3'><spna class='border'></spna><img src='./img/camera.svg' alt=''/></div><a href='javascript:;' class='pinline piline3' datasheatri='" + data.object[i].rank[l].rank[y].rank[z].deviceId + "'>" + data.object[i].rank[l].rank[y].rank[z].tp_name + "</a></li>"
							})*/
							str += "</ul></li></ul>"
						})
						str += " </li> </ul>"
					})
					str += "</div>"
				})
				$(".bdequips").empty().append(str);
				$('.navigation:eq(0)').find('li:eq(0)').find('.tree:eq(0)').find('.fa2Out').click();
				$('.navigation:eq(0)').find('li:eq(0)').find('.tree:eq(0)').find('.tree2').find('li:eq(0)').click();
				$('.tree').on('click', function() {
					if($(this).siblings('ul').is(":visible") == false) {
						$(this).siblings('ul').find('.tree2').show();
					} else {
						$(this).siblings('ul').find('.tree2').hide();
					}
				})
				$('.fa2Out').each(function() {
					if($(this).find('.pinline1').attr('data-state') == 2) { //2不在线
						$(this).find('img').attr('src', './img/shipin/ico_disconnect.png');
						$(this).find('.checkEquip').hide();
					} else {
						$(this).find('img').attr('src', './img/shipin/ico_device.png');
						$(this).find('.checkEquip').show();
					}
				})
				$('.fa2Out').on('click', function() {
					if($(this).siblings('ul').is(":visible") == false) {
						$(this).siblings('ul').show()
					} else {
						$(this).siblings('ul').hide()
					}
				})
				/*	if(sessionStorage.getItem('tp_id')) {
						$('p.pinline1').each(function() {
							if($(this).attr('data-tp_id') == sessionStorage.getItem('tp_id')) {
								var that = $(this)
								setTimeout(function() {
									that.parent().parent().show().siblings('ul.tree').show()
									that.click();
								}, 50)
							}
						})
					}*/
			} else if(data.state == 2) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#30862B",
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
					confirmButtonColor: "#30862B",
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
				confirmButtonColor: "#30862B",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return
		}
	})
};

/*function showLin(tar, title, datas, look) { //id  title  data  光
	console.log(datas);

}*/


//公共页面的摄像头
function listPoint() {
	$.ajax({
		url: http + "listPoint",
		type: "post",
		contentType: "application/json",
		cache: false,
		data: JSON.stringify({
			"ckuid": sessionStorage.getItem('ckuid'),
			"cksid": sessionStorage.getItem('cksid'),
			"tp_pid": 0
		}),
		success: function(data) {
			if(data.state == 0) {
				//sessionStorage.setItem('objData',JSON.stringify(data.object))
				var str = "";
				$(data.object).each(function(i, el) {
					str += "<div class='point'><div class='icon student'> <img src='./img/shipin/ico_home.png' alt=''/> </div> <p class='pinline' style='font-size: 0.12rem'> " + data.object[i].tp_name + "</p>"
					$(data.object[i].rank).each(function(l, el) {
						str += " <ul class='navigation' style='font-size: 0.12rem'> <li> <div class='icon fa1'> <img src='./img/shipin/ico_group.png' alt=''/> </div> <p  class='pinline'>" + data.object[i].rank[l].tp_name + "</p>"
						$(data.object[i].rank[l].rank).each(function(y, el) {
							str += "<ul class='tree'> <div class='fa2Out'> <li><div class='icon fa2'> <img src='./img/shipin/ico_device.png' alt=''/> </div> <p  class='pinline pinline1' data-tp_id=" + data.object[i].rank[l].rank[y].tp_id + " data-supervisername=" + data.object[i].rank[l].rank[y].supervisername + " data-state=" + data.object[i].rank[l].rank[y].state + " data-producername=" + data.object[i].rank[l].rank[y].producername + " data-name=" + data.object[i].rank[l].rank[y].name + " data-exportorname=" + data.object[i].rank[l].rank[y].exportorname + " data-x=" + data.object[i].rank[l].rank[y].x + " data-y=" + data.object[i].rank[l].rank[y].y + " data-deviceId=" + data.object[i].rank[l].rank[y].deviceId + " data-ip=" + data.object[i].rank[l].rank[y].ip + " data-port=" + data.object[i].rank[l].rank[y].port + ">" + data.object[i].rank[l].rank[y].tp_name + "</p></div> <ul class='tree2'> "
							$(data.object[i].rank[l].rank[y].rank).each(function(z, el) {
								str += "<li id=" + data.object[i].rank[l].rank[y].rank[z].tp_id + "><div class='icon fa3'><spna class='border'></spna><img src='./img/camera.svg' alt=''/></div><a href='javascript:;' class='pinline piline3' datasheatri='" + data.object[i].rank[l].rank[y].rank[z].deviceId + "'>" + data.object[i].rank[l].rank[y].rank[z].tp_name + "</a></li>"
							})
							str += "</ul></li></ul>"
						})
						str += " </li> </ul>"
					})
					str += "</div>"
				})
				$(".nav_left").empty().append(str);
				$('.navigation:eq(0)').find('li:eq(0)').find('.tree:eq(0)').find('.fa2Out').click();
				$('.navigation:eq(0)').find('li:eq(0)').find('.tree:eq(0)').find('.tree2').find('li:eq(0)').click();
				$('.tree').on('click', function() {
					if($(this).siblings('ul').is(":visible") == false) {
						$(this).siblings('ul').find('.tree2').show();
					} else {
						$(this).siblings('ul').find('.tree2').hide();
					}
				})
					$('.fa2Out').each(function() {
						if($(this).find('.pinline1').attr('data-state') == 2) { //2不在线
							$(this).find('img').attr('src', './img/shipin/ico_disconnect.png');
						} else {
							$(this).find('img').attr('src', './img/shipin/ico_device.png');
						}

					})
				$('.fa2Out').on('click', function() {
					if($(this).siblings('ul').is(":visible") == false) {
						$(this).siblings('ul').show()
					} else {
						$(this).siblings('ul').hide()
					}
				})
				if(sessionStorage.getItem('tp_id')) {
					$('p.pinline1').each(function() {
						if($(this).attr('data-tp_id') == sessionStorage.getItem('tp_id')) {
							var that = $(this)
							setTimeout(function() {
								that.parent().parent().show().siblings('ul.tree').show()
								that.click();
							}, 50)
						}
					})
				}
			} else if(data.state == 2) {
				swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#30862B",
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
					confirmButtonColor: "#30862B",
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
				confirmButtonColor: "#30862B",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return
		}

	})

}

function getUrlKey(name) {
			return decodeURIComponent((
				new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
		};
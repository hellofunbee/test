var sheng, shi, qu;
var oldconfig = {};
var confignum = 0;
var confignumChild = 0;
$(function() {
	listPoint();
	setTimeout(function() {
		var right = $(".right").outerHeight();
		var top_h = $(".r_top").outerHeight();
		var nav_h = $(".r_mid").outerHeight();
		$(".r_bot").outerHeight(right - top_h - nav_h)
	}, 80);
	//展开、收起
	$(document).on('click','.fa2Out',function(e){
		  e.stopPropagation();
		  $('.pinline1').removeClass('active')
		  $(this).find('.pinline1').addClass('active');
	});
	$(document).on('click','.tree2>li',function(e){
		  e.stopPropagation();
		  $(this).parents('.tree').eq(0).siblings().find('.piline3').removeClass('active');
		  $(this).siblings('li').find('.piline3').removeClass('active');
		 $(this).find('.piline3').addClass('active');
	});
	$(document).on("click", ".switch", function(){
		var index = $(this).parents(".type>li").index();
		if($(this).text() == "收起") {
			$(this).text("展开").css("background", "url('img/down.svg')no-repeat 0.52rem 0.05rem/0.18rem 0.18rem");
		} else {
			$(this).text("收起").css("background", "url('img/up.svg')no-repeat 0.52rem 0.15rem/0.18rem 0.18rem");
		}
		$(this).parents("li").find(".add_bot").slideToggle(300);
	})
	$(document).on('click','#zhezhimin',function(){
		$('#ms1').show();
		setMainDevice();
		$('#ms1').find('.addMainPoint').text('设置主设备');
       $('.sub1').attr('data-type',1);                          
	});
	$('.ul_top>li').on('click', function() {
		$(this).addClass('choose').siblings().removeClass('choose')
		init1($(this).index());
	})
	//添加主设备
	$('.mid_five>div.addMin').on('click', function() {
		$('#ms1').show();
		$('#ms1').find('.addMainPoint').text('添加主设备');
		addnewMainDevice(0);
	})
	$('.tbpz').on('click', function() {
		togetter();
	})
	$('.mid_five>div.addMinM').on('click', function() {
		$('#ms12').show()
	})
	$('#ms12').find('.addM').on('click', function() {
		$('#ms12').find('ul').append('<li class="li' + ($('#ms12').find('ul').length + 1) + '"> <p>设备号</p> <div> <input type="text"> </div> </li>')
	})
	$('.sub1').on('click', function() {
        var arr=$(this).attr('data-type');
		saveAndUpdateMainDevice(arr)
	})
	$(document).on('click', '.fa2Out', function() {
		sessionStorage.setItem('tp_id', $(this).find('.pinline1').attr('data-tp_id'))
		$('.r_mid').show()
		$('.pinline1').attr('data-s', 0)
		$(this).find('.pinline1').attr('data-s', 1);
		//$(this).addClass('active').attr('data-s', 1).parents('tree').eq(0).siblings('ul').find('.pinline1').removeClass('active').attr('data-s', 0);
		if(confignum == 0) {
			$('p.title').text($(this).find('.pinline1').text())
			$('.r_bot6').show().siblings('div.r_bot').hide()
			$('.mid_five').show().siblings().hide()
			listControlSetting()
			getMainDeviceSetting($(this).find('.pinline1').attr('data-deviceId'), $(this).find('.pinline1').attr('data-ip'), $(this).find('.pinline1').attr('data-port'))
		} else if(confignum == 1) {
			$('.mid_one').show().siblings().hide()
			$('.r_bot7').show().siblings('div.r_bot').hide()
			listIPC(active().attr('data-deviceId'))
		} else if(confignum == 2) {

		}
	});
	$(document).on('click','.tree2>li', function(e) {
		e.stopPropagation()
		if(active() == undefined) {
			swal({
				title: '请先选择摄像头对应设备',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		} else {
			$('.piline3').removeClass('active')
			$(this).find('.piline3').addClass('active');
		}
	})
	$('.mid_info2>li').on('click', function() {
		$(this).addClass('choose').siblings().removeClass('choose')
		var num = $(this).index();
		confignumChild = $(this).index();
		$('.r_bot8').show().siblings('div.r_bot').hide()
		if(num == 0) {
			listPointGroup()
		} else {
			listClass1(num)
		}
	})
	//重置主设备
	$('#chongzhi').on('click', function() {
		active().click();
	})
	//写入主设备
	$('#xierushebei').on('click', function() {
		writeMainDeviceSetting(active().attr('data-deviceId'), active().attr('data-ip'), active().attr('data-port'))
	})
	$('#baocuncanshu').on('click', function() {
		var v1, v2, v3, v4;
		$('.pinline1').each(function() {
			if($(this).attr('data-s') == 1) {
				v1 = $(this).attr('data-tp_id')
				v2 = $(this).attr('data-deviceId')
				v3 = $(this).attr('data-port')
				v4 = $(this).attr('data-ip')
			}
		})
		setMainDeviceSetting(v1, v2, v3, v4)
	})
	//添加摄像头
	$('.addsxt').on('click', function() {
		$('#ms2').show().find('li.li2>div').text(active().find('.tree2>li').length + 1);
	})
	var num=10;
	$('#ms2').find('.pop_save').on('click', function() {
		num++;
		var v1 = active().attr('data-deviceId')
		var v2 = active().attr('data-tp_id')
		var v3 = active().attr('data-ip')
		var v4 = active().attr('data-port')
		var v5 = $('#ms2').find('li.li3').find('input').val()
		if(!v5) {
			swal({
				title: '请填写设备名称',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v6 = num;
		var v7 = $('#ms2').find('li.li4').find('input.ads_one').val()
		if(!v7) {
			swal({
				title: '请填写IPC设备地址',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v8 = $('#ms2').find('li.li4').find('input.ads_two').val()
		if(!v8) {
			swal({
				title: '请填写IPC设备地址端口',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v9 = $('#ms2').find('li.li5').find('input').val()
		if(!v9) {
			swal({
				title: '请填写设备用户名',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v10 = $('#ms2').find('li.li6').find('input').val()
		if(!v10) {
			swal({
				title: '请填写设备密码',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v11 = $('#ms2').find('li.li7').find('select>option:selected').val()
		var v12 = $('#ms2').find('li.li8').find('select>option:selected').val()
		addIPC(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12)
	})
	$('#ms9').find('.pop_save').on('click', function() {
		var v1 = $(this).attr('data-id_2')
		var v11 = $('#ms9').find('li.li4').find('input').val();
		if(!v11) {
			swal({
				title: '设备名称不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v2 = $('#ms9').find('li.li5').find('input').eq(0).val();
		if(!v2) {
			swal({
				title: 'IPC设备地址不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v4 = $('#ms9').find('li.li5').find('input').eq(1).val();
		if(!v4) {
			swal({
				title: 'IPC设备地址端口不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v7 = $('#ms9').find('li.li6').find('input').val();
		if(!v7) {
			swal({
				title: '设备用户名不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v8 = $('#ms9').find('li.li7').find('input').val();
		if(!v8) {
			swal({
				title: '设备密码不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v9 = $('#ms9').find('li.li8').find('select>option:selected').val();
		var v10 = $('#ms9').find('li.li9').find('select>option:selected').val();
		var v5 = $(this).attr('data-id')
		var v6 = $(this).attr('data-id_1')
		var type = 1
		updateIPCParam(v1, v2, v4, v5, v6, v7, v8, v9, v10, v11, type)
	})
	$('#ms8').find('.pop_save').on('click', function() {
		var v1 = $(this).attr('data-id_2')
		var v2 = $('#ms8').find('li.li1').find('input').val();
		if(!v2) {
			swal({
				title: '视频设备地址不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v3 = $('#ms8').find('li.li3').find('input').val();
		if(!v3) {
			swal({
				title: '终端映射端口不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v4 = $('#ms8').find('li.li2').find('input').val();
		if(!v4) {
			swal({
				title: '视频设备端口不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v5 = $(this).attr('data-id')
		var v6 = $(this).attr('data-id_1')
		var type = 2
		updateIPCProxy(v1, v2, v3, v4, v5, v6, type)
	})
	$('#ms10').find('.pop_save').on('click', function() {
		var v1 = $(this).attr('data-id_2')
		var v2 = $('#ms10').find('li.li1').find('input').val();
		if(!v2) {
			swal({
				title: '视频设备地址不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v3 = $('#ms10').find('li.li3').find('input').val();
		if(!v3) {
			swal({
				title: '终端映射端口不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v4 = $('#ms10').find('li.li2').find('input').val();
		if(!v4) {
			swal({
				title: '视频设备端口不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v5 = $(this).attr('data-id')
		var v6 = $(this).attr('data-id_1')
		var type = 1
		updateIPCProxy(v1, v2, v3, v4, v5, v6, type)
	})
	$('#ms11').find('.pop_save').on('click', function() {
		var v1 = $(this).attr('data-mapid')
		var v2 = $(this).attr('data-id1')
		var v3 = $('#ms11').find('li.li1').find('select>option:selected').val();
		var v4 = $('#ms11').find('li.li5').find('select>option:selected').val();
		if(v4 == -1) {
			swal({
				title: '请选择编码类型',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v5 = $('#ms11').find('li.li6').find('select>option:selected').val();
		if(v5 == -1) {
			swal({
				title: '请选择编码复杂程度',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v6 = $('#ms11').find('li.li2').find('select>option:selected').val();
		if(v6 == -1) {
			swal({
				title: '请选择分辨率',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v7 = $('#ms11').find('li.li3').find('select>option:selected').val();
		if(v7 == -1) {
			swal({
				title: '请选择帧率',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v8 = $('#ms11').find('li.li4').find('select>option:selected').val();
		if(v8 == -1) {
			swal({
				title: '请选择码率',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var v9 = $('#ms11').find('li.li7').find('select>option:selected').val();
		setIPCCodeing(v1, v2, v3, v4, v5, v6, v7, v8, v9)
	})
	$('#ms12').find('.pop_save').on('click', function() {
		addMainDeviceList()
	})
	$(document).find('.pop_cancel').on('click', function() {
		$(this).parents('.mask').find('input').val('')
	})
	listProvice();
	$(document).on('click', '.mask span.close', function() {
		$(this).parent().parent().parent().hide();
	})
	//添加控制设备
	$('.add_con').on('click', function() {
		$('#ms3').show();
		listChannel(1)
	})
	$('#addControl').on('click', function(e) {
		e.stopPropagation()
		addControlSetting()
	})
	$('#baocunagain').on('click', function(e) {
		e.stopPropagation()
		updateControlSetting()
	})
	//规格文件
	$('#guige').on('click', function() {
		$('#ms4').show()
		getGatherSettings()
	})
	//添加
	$('.pop_ul4>li.li1').on('click', function() {
		$('.tab3>tbody').append("<tr class='addguige guigeList'> <td><span id='delAdd'>取消</span></td> <td><input type='text' class='channel'></td> <td><input class='name' type='text'></td> <td><input class='beginPosition' type='text'></td> <td><input type='text' class='len'></td> <td><input class='fieldName' type='text'></td> <td><input class='decimalFormat' type='text'></td> <td><input class='lowerLimit' type='text'></td> <td><input class='upperLimit' type='text'></td> <td class='diffPercent'>0%</td> <td><input class='formula' type='text' style='width: 2rem'></td> <td><input class='unit' type='text'></td> <td><input class='listDisplay' style='width: 0.5rem' type='checkbox'></td> <td><input class='statDisplay' style='width: 0.5rem' type='checkbox'></td> <td><input class='chartDisplay' style='width: 0.5rem' type='checkbox'></td> <td><input class='chartOrderIndex' type='text'></td> <td><select class='chartID' name='' class='guigeType'><option value='0'>无数据</option><option value='1'>空气温度</option><option value='2'>空气湿度</option><option value='3'>雨量</option><option value='4'>风向</option><option value='5'>风速</option><option value='6'>光照度</option><option value='7'>土壤水分</option><option value='8'>土壤温度</option><option value='9'>氮肥含量</option><option value='10'>钾肥含量</option><option value='11'>二氧化碳含量</option><option value='12'>氧气含量</option><option value='13'>PH值</option></select></td> </tr>")
		$('#delAdd').on('click', function() {
			$(this).parents('.addguige').remove()
		})
	})
	//保存
	$('.pop_ul4>li.li6').on('click', function() {
		var obj = new Object();
		obj.ckuid = sessionStorage.getItem('ckuid');
		obj.cksid = sessionStorage.getItem('cksid');
		obj.tp_id = active().attr('data-tp_id');
		obj.deviceId = active().attr('data-deviceId');
		obj.settingEntity = [];
		var status = true;
		$('.tab3>tbody>tr.guigeList').each(function() {
			var obj01 = new Object();
			obj01.channel = $(this).find('.channel').val();
			if(!obj01.channel) {
				swal({
					title: '频道编号不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return false
			}
			var str = active().attr('data-deviceId') + '.' + ($(this).index() + 1)
			obj01.id = str
			obj01.deviceId = active().attr('data-deviceId');
			obj01.name = $(this).find('.name').val();
			if(!obj01.name) {
				swal({
					title: '名称不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return
			}
			obj01.beginPosition = $(this).find('.beginPosition').val();
			if(!obj01.beginPosition) {
				swal({
					title: '开始位置不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return
			}
			obj01.len = $(this).find('.len').val();
			if(!obj01.len) {
				swal({
					title: '长度不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return
			}
			obj01.fieldName = $(this).find('.fieldName').val();
			if(!obj01.fieldName) {
				swal({
					title: '字段名不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return
			}
			obj01.decimalFormat = $(this).find('.decimalFormat').val();
			if(!obj01.decimalFormat) {
				swal({
					title: '数据格式不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return
			}
			obj01.lowerLimit = $(this).find('.lowerLimit').val();
			if(!obj01.lowerLimit) {
				swal({
					title: '下限值不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return
			}
			obj01.upperLimit = $(this).find('.upperLimit').val();
			if(!obj01.upperLimit) {
				swal({
					title: '上限值不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return
			}
			obj01.diffPercent = '0';
			obj01.formula = $(this).find('.formula').val();
			if(!obj01.formula) {
				swal({
					title: '计算公式不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return
			}
			obj01.unit = $(this).find('.unit').val();
			if(!obj01.unit) {
				swal({
					title: '单位不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return
			}
			if($(this).find('.listDisplay').prop('checked') == false) {
				obj01.listDisplay = '0'
			} else {
				obj01.listDisplay = '1'
			}
			if($(this).find('.statDisplay').prop('checked') == false) {
				obj01.statDisplay = '0'
			} else {
				obj01.statDisplay = '1'
			}
			if($(this).find('.chartDisplay').prop('checked') == false) {
				obj01.chartDisplay = '0'
			} else {
				obj01.chartDisplay = '1'
			}
			obj01.unit = $(this).find('.unit').val();
			if(!obj01.unit) {
				swal({
					title: '单位不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				return
			}
			obj01.chartOrderIndex = $(this).find('.chartOrderIndex').val();
			if(!obj01.chartOrderIndex) {
				swal({
					title: '图表排序不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				status = false
				return
			}
			obj01.chartID = $(this).find('.chartID').find('option:selected').val();
			obj.settingEntity.push(obj01)
		})

		if(status == true) {
			parent.loadShow()
			$.ajax({
				url: http + "setGatherSettings",
				type: "post",
				contentType: "application/json",
				headers: { 'Content-type': 'application/json;charset=UTF-8' },
				data: JSON.stringify(obj),
				cache: false,
				success: function(data) {
					parent.removeload()
					if(data.state == 0) {
						swal({
							title: data.msg,
							text: "2秒后关闭",
							confirmButtonText: "确定",
							confirmButtonColor: "#30862B",
							timer: 2000
						});
						getGatherSettings()
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
					parent.removeload()
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
		}

	})
	//全选
	$('#allCheck').on('click', function() {
		if($(this).prop('checked')) {
			$('.tab3>tbody>tr.defguigelist').each(function() {
				$(this).find('input:checkbox.del').prop('checked', true)
			})
		} else {
			$('.tab3>tbody>tr.defguigelist').each(function() {
				$(this).find('input:checkbox.del').prop('checked', false)
			})
		}
	})
	//删除
	$('.pop_ul4>li.li2').on('click', function() {
		var state = false
		var ids = ""
		$('.tab3>tbody>tr.defguigelist').each(function() {
			if($(this).find('input:checkbox.del').prop('checked')) {
				state = true
				ids += "\'" + $(this).find('input:checkbox.del').attr('data-id') + "\',"
			}
		})
		if(state == true) {
			var obj = new Object();
			obj.ckuid = sessionStorage.getItem('ckuid');
			obj.cksid = sessionStorage.getItem('cksid');
			obj.tp_id = active().attr('data-tp_id')
			obj.deviceId = active().attr('data-deviceId')
			obj.ids = ids.substr(0, ids.length - 1);
			console.log(obj)
			parent.loadShow()
			$.ajax({
				url: http + "deleteGatherSettings",
				type: "post",
				contentType: "application/json",
				headers: { 'Content-type': 'application/json;charset=UTF-8' },
				data: JSON.stringify(obj),
				cache: false,
				success: function(data) {
					parent.removeload()
					if(data.state == 0) {
						swal({
							title: data.msg,
							text: "2秒后关闭",
							confirmButtonText: "确定",
							confirmButtonColor: "#30862B",
							timer: 2000
						});
						getGatherSettings()
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
					parent.removeload()
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
		} else {
			swal({
				title: '请选择要删除的条目',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});

			return
		}
	})
	//重启
	$('#chongqi').on('click', function() {
		var obj = new Object();
		obj.ckuid = sessionStorage.getItem('ckuid');
		obj.cksid = sessionStorage.getItem('cksid');
		obj.tp_id = active().attr('data-tp_id')
		parent.loadShow()
		$.ajax({
			url: http + "restartGather",
			type: "post",
			contentType: "application/json",
			headers: { 'Content-type': 'application/json;charset=UTF-8' },
			data: JSON.stringify(obj),
			cache: false,
			success: function(data) {
				parent.removeload()
				if(data.state == 0) {
					swal({
						title: data.msg,
						text: "2秒后关闭",
						confirmButtonText: "确定",
						confirmButtonColor: "#30862B",
						timer: 2000
					});

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
				parent.removeload()
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
	//软件更新
	$('#updataFile').on('change', function() {
		var obj = new FormData();
		obj.append('ckuid', sessionStorage.getItem('ckuid'))
		obj.append('cksid', sessionStorage.getItem('cksid'))
		obj.append('deviceId', active().attr('data-deviceId'))
		obj.append('ip', active().attr('data-ip'))
		obj.append('port', active().attr('data-port'))
		obj.append('file', $('#updataFile').get(0).files[0])
		parent.loadShow()
		$.ajax({
			url: http + "updateSoftWare",
			type: "post",
			//contentType: "application/json",
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			//headers: {'Content-type': 'application/json;charset=UTF-8'},
			data: obj,
			cache: false,
			success: function(data) {
				parent.removeload()
				if(data.state == 0) {
					swal({
						title: data.msg,
						text: "2秒后关闭",
						confirmButtonText: "确定",
						confirmButtonColor: "#30862B",
						timer: 2000
					});
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
				parent.removeload()
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
	//新增一级类目
	$(document).on('click', '.addone', function() {
		var title = '',
			ts = '';
		if(confignumChild == 1) {
			title = '作物种类'
		}
		if(confignumChild == 2) {
			title = '农资产品'
		}
		if(confignumChild == 7) {
			title = '一级分类名称'
		}
		var str = '<li><div class="add_top"><div class="type_tit fl">' + title + '</div><input type="text" placeholder="请输入一级分类信息" class="typeone fl"/><div class="type_del fr addClass1">保存</div></div><div class="add_bot">';
		$(".type").append(str);
	});
	//新增二级类目
	$(document).on("click", '.addtwo', function() {
		var title = '';
		if(confignumChild == 1) {
			title = '作物品种'
		}
		if(confignumChild == 2) {
			title = '农资产品'
		}
		if(confignumChild == 7) {
			title = '二级分类名称'
		}
		var str = '<li><div class="type_tit2 fl">' + title + '</div><input type="text" placeholder="请填写二级分类" class="typeone2 fl"/><div class="type_del addClass2 fr">保存</div></li>';
		$(this).next(".typetwo").append(str);
	});
	//添加一级
	$(document).on('click', '.addClass1', function() {
		var name = $(this).siblings('input.typeone').val();
		if(!name) {
			swal({
				title: '请填写作物一级类目名称',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
		} else {
			if(confignumChild == 0) {
				addPoint(name, '1')
			} else {
				addClass(1, '', name, confignumChild)
			}
		}

	})
	//添加二级
	$(document).on('click', '.addClass2', function() {
		var id = $(this).parents('.addClassList').attr('data-c_rid');
		var name = $(this).siblings('input').val()
		if(!name) {
			swal({
				title: '请填写作物二级类目名称',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		} else {
			if(confignumChild == 0) {
				addPoint(name, '2', id)
			} else {
				addClass(2, id, name, confignumChild)
			}

		}

	})
	//删除类目
	$(document).on("click", ".type_del1", function() {
		var id = $(this).attr('data-id')
		swal({
				title: "是否删除该分类?",
				text: "",
				type: "warning",
				showCancelButton: true,
				cancelButtonText: "取消",
				confirmButtonColor: "#30862B",
				confirmButtonText: "确定",
				closeOnConfirm: false
			},
			function() {
				if(confignumChild == 0) {
					deletePoint(id)
				} else {
					deleteClass(id);
				}

			});
	});
	//搜索
	$('#search').on('click', function() {
		if($(this).attr('data-s') == 0) {
			listProfessor(1, 0)
		}
		if($(this).attr('data-s') == 1) {
			listUser(1, 0, 3)
		}
		if($(this).attr('data-s') == 3) {
			listUser(1, 0, 1)
		}
	})
	//添加管理员
	$('.addguanlizhe').on('click', function() {
		if($(this).attr('data-type') == '3') {
			$('#msuser').show().siblings('.ms').hide();
			$('#msuser').find('input').val('');
		} else if($(this).attr('data-type') == '1') {
			$('#ms7').show().siblings('.ms').hide();
			$('#ms7').find('input').val('');
		};
	})
	//重置--管理员
	$('#ms7result').on('click', function() {
		$('#ms7').find('input').val('')
	});
	//重置--用户
	$('#msuserresult').on('click', function() {
		$('#msuser').find('input').val('')
	})
	//保存
	$('#ms7sureT').on('click', function() {
		addUser(1)
	})
	$('#msusersureT').on('click', function() {
		addUser(3)
	})
	//重启主设备
	$('#chongqimin').on('click', function() {
		restart(active().attr('data-deviceId'), active().attr('data-ip'), active().attr('data-port'))
	})
	//智能规则

	//预约规则
	$('#addRule').on('click', function() {
		$('#ms13').show().find('.popupAdd').hide().siblings('.popupList').show()
		listRule()
	})
	$('.closeRoule').on('click', function() {
		$('#ms13').hide()
	})
	$('#allCheckRoule').on('click', function() {
		if($(this).prop('checked')) {
			$('#ms13').find('table>tbody>tr').find('input').prop('checked', 'checked')

		} else {
			$('#ms13').find('table>tbody>tr').find('input').removeAttr('checked')
		}
	})
	$('.delRoule').on('click', function() {
		swal({
				title: "确定删除预约规则?",
				text: "",
				type: "warning",
				showCancelButton: true,
				confirmButtonColor: "#30862B",
				cancelButtonText: "取消",
				confirmButtonText: "确定",
				closeOnConfirm: false
			},
			function() {
				deleteRuleIds()
			});
	})
	$('.addNewRoule').on('click', function() {
		$('#ms13').find('.popupAdd').show().siblings('.popupList').hide();
		$('#ms13').find('.popupAdd').find('.giveUpRoule').text('放弃新建');
		$('#ms13').find('.popupAdd').find('.pop_tit').html("新建预约规则<span class='close'>X</span>");
		$('.pop_record').find('input').val('');

	})
	$('.giveUpRoule').on('click', function() {
		$('#ms13').find('.popupAdd').hide().siblings('.popupList').show()
	})
	$('.saveRoule').on('click', function() {
		if($(this).attr('data-id')) {
			updateRule($(this).attr('data-id'))
		} else {
			updateRule()
		}

	})

});

function active() {
	var state;
	$('.pinline1').each(function() {
		if($(this).hasClass('active')) {
			state = $(this)
		}
	})
	return state
}

function active1() {
	var state;
	$('.equis>li').each(function() {
		if($(this).index() != 0) {
			if($(this).hasClass('select_eq')) {
				state = $(this)
			}
		}
	})
	return state
}

function active3() {
	var state;
	$('.ul_top>li').each(function() {
		if($(this).hasClass('choose')) {
			state = $(this)
		}
	})
	return state
}

function pinlineTh() {
	var state;
	$('.piline3').each(function() {
		if($(this).hasClass('active')) {
			state = $(this)
		}
	})
	return state
}
//同步配置
function togetter(){
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.mapingDeviceId = pinlineTh().attr('datasheatri');
	obj.deviceId = active().attr('data-deviceId');
	obj.pointEntity = { 'ip': active().attr('data-ip'), 'port': active().attr('data-port') };
	$.ajax({
		url: http + "autoSyn",
		type: 'post',
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			swal({
				title: data.msg,
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
		},
		error: function(){
			swal({
				title: data.msg,
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
		}
	})
}

function init1(num) {
	confignum = num;
	if(confignum == 0) {
		$('.r_mid').show()
		$('.mid_five').show().siblings().hide()
		if(active() == undefined) {
			$('.r_bot').hide()
		} else {
			$('.r_bot6').show().siblings('div.r_bot').hide()
			listControlSetting()
			getMainDeviceSetting(active().attr('data-deviceId'), active().attr('data-ip'), active().attr('data-port'))
		}
	} else if(confignum == 1 || confignum == 2) {
		if(active() == undefined) {
			$('.r_mid').hide()
			$('.r_bot').hide()
		} else {
			$('.r_mid').show()
			if(confignum == 1) {
				$('.mid_one').show().siblings().hide()
				$('.r_bot7').show().siblings('div.r_bot').hide()
				listIPC(active().attr('data-deviceid'))
			} else if(confignum == 2) {

			}
		}
	} else {
		$('.r_mid').show()
		if(confignum == 3) {
			$('.mid_four').show().siblings().hide()
			$('.mid_info2>li').eq(0).click()
		}
		if(confignum == 4) {
			$('.sear').show().find('input').attr('placeholder', '请填写要查询的姓名和手机号').siblings('#search').attr('data-s', 0)
			$('.mid_three').show().siblings().hide()
			$('.r_bot2').show().siblings('div.r_bot').hide()
			$('.addguanlizhe').hide()
			listProfessor(1, 0)
		}//用户
		if(confignum == 5) {
			$('.sear').show().find('input').attr('placeholder', '请填写要查询的姓名').siblings('#search').attr('data-s', 1)
			$('.mid_three').show().siblings().hide()
			$('.r_bot23').show().siblings('div.r_bot').hide()
			$('.r_bot23').find('.title').text('姓名')
			$('.addguanlizhe').show().attr('data-type', 3).text('添加用户')
			listUser(1, 0, 3)
		}
		if(confignum==6) {
		    $('.sear').hide().find('#search').attr('data-s',2)
		    $('.mid_three').show().siblings().hide()
		    $('.r_bot3').find('.title').text('生产者姓名')
		    $('.r_bot3').show().siblings('div.r_bot').hide()
		    $('.addguanlizhe').hide()
		    listRelationShip(1);
		}
		if(confignum == 7) {
			$('.sear').show().find('input').attr('placeholder', '请填写要查询的管理者姓名').siblings('#search').attr('data-s', 3)
			$('.mid_three').show().siblings().hide()
			$('.r_bot33').find('.title').text('管理者姓名')
			$('.r_bot33').show().siblings('div.r_bot').hide()
			$('.addguanlizhe').show().attr('data-type', 1).text('添加管理者')
			listUser(1, 0, 1);
		}
		if(confignum == 8) {
			$('.r_bot9').show().siblings('div.r_bot').hide();
			$('.sear').hide();
			$('.addguanlizhe').hide();
			$('.r_mid>div').hide();
			quanxian();
		}
	}
}

function restart(v1, v2, v3) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.deviceId = v1;
	obj.ip = v2;
	obj.port = v3;
	parent.loadShow()
	$.ajax({
		url: http + "restart",
		type: 'post',
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
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
			parent.removeload()
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

function setMainDeviceSetting(v1, v2, v3, v4) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.tp_id = v1;
	obj.deviceId = v2;
	obj.port = v3;
	obj.ip = v4;
	obj.tp_type = 3;
	obj.config = {};
	$('.wangluocanshu input:text').each(function() {
		if(!$(this).val()) {
			swal({
				title: "第" + ($(this).parent().index() + 1) + "条，不能为空!",
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		obj.config[$(this).attr('name')] = $(this).val()
	})
	obj.config['power_mode'] = $('#power_mode').find('option:selected').val()
	obj.config["nouse_ctrl"] = $('#nouse_ctrl').find('option:selected').val()
	if(!$('#adc_cap_time').val()) {
		swal({
			title: 'ADC上报时间间隔不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.config["adc_cap_time"] = $('#adc_cap_time').val()
	if(!$('#use_light_ctrl').val()) {
		swal({
			title: '光照度控制不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.config["use_light_ctrl"] = $('#use_light_ctrl').val()
	if(!$('#beginTime')) {
		swal({
			title: '开始时间控制不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test($('#beginTime').val()) == false) {
		swal({
			title: '开始时间格式不正确',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	if(!$('#endendTime')) {
		swal({
			title: '结束时间控制不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test($('#endendTime').val()) == false) {
		swal({
			title: '开始时间格式不正确',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	var str01 = $('#beginTime').val().replace(/:/g, '')
	var str02 = $('#endendTime').val().replace(/:/g, '')
	var str03 = str01 + "~" + str02
	obj.config["use_time_ctrl"] = str03
	obj.oldconfig = oldconfig
	parent.loadShow()
	$.ajax({
		url: http + "setMainDeviceSetting",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
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
			parent.removeload()
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
}

function getMainDeviceSetting(v1, v2, v3) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.deviceId = v1;
	obj.ip = v2;
	obj.port = v3;
	$.ajax({
		url: http + "getMainDeviceSetting",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				//var configStr="";
				$('#s_deviceIdServIp').val(data.object.s_deviceIdServIp.split(':')[0])
				$('#s_deviceIdServPort').val(data.object.s_deviceIdServIp.split(':')[1])
				//delete data.object.s_deviceIdServIp
				$('#s_logServIp').val(data.object.s_logServIp.split(':')[0])
				$('#s_logServPort').val(data.object.s_logServIp.split(':')[1])
				//delete data.object.s_logServIp
				$('#s_imgServIP').val(data.object.s_imgServIP.split(':')[0])
				$('#s_imgServPort').val(data.object.s_imgServIP.split(':')[1])
				//delete data.object.s_imgServIP
				$('#s_alarmServIP').val(data.object.s_alarmServIP.split(':')[0])
				$('#s_alarmServPort').val(data.object.s_alarmServIP.split(':')[1])
				//delete data.object.s_alarmServIP
				$('#s_timeServIP').val(data.object.s_timeServIP.split(':')[0])
				$('#s_timeServPort').val(data.object.s_timeServIP.split(':')[1])
				//delete data.object.s_timeServIP
				$('#s_localIP').val(data.object.s_localIP)
				//delete data.object.s_localIP
				$('#s_repIpServIp1').val(data.object.s_repIpServIp1.split(':')[0])
				$('#s_repIpServPort1').val(data.object.s_repIpServIp1.split(':')[1])
				//delete data.object.s_repIpServIp1
				$('#s_repIpServIp2').val(data.object.s_repIpServIp2.split(':')[0])
				$('#s_repIpServPort2').val(data.object.s_repIpServIp2.split(':')[1])
				//delete data.object.s_repIpServIp2
				$('#s_repIpServIp3').val(data.object.s_repIpServIp3.split(':')[0])
				$('#s_repIpServPort3').val(data.object.s_repIpServIp3.split(':')[1])
				//delete data.object.s_repIpServIp3
				$('#s_adcServIp1').val(data.object.s_adcServIp1.split(':')[0])
				$('#s_adcServPort1').val(data.object.s_adcServIp1.split(':')[1])
				//delete data.object.s_adcServIp1
				$('#s_adcServIp2').val(data.object.s_adcServIp2.split(':')[0])
				$('#s_adcServPort2').val(data.object.s_adcServIp2.split(':')[1])
				//delete data.object.s_adcServIp2
				$('#s_adcServIp3').val(data.object.s_adcServIp3.split(':')[0])
				$('#s_adcServPort3').val(data.object.s_adcServIp3.split(':')[1])
				//delete data.object.s_adcServIp3
				$('#s_netmask').val(data.object.s_netmask)
				//delete data.object.s_netmask
				$('#s_gatewayIP').val(data.object.s_gatewayIP)
				//delete data.object.s_gatewayIP
				$('#changemoren').on('change', function() {
					if($(this).prop('checked')) {
						$('#s_gatewayIP').show()
					} else {
						$('#s_gatewayIP').hide()
					}
				})
				$('#s_dnsIP').val(data.object.s_dnsIP)
				//delete data.object.s_dnsIP
				$('#power_mode>option').each(function() {
					if($(this).val() == data.object.power_mode) {
						$(this).prop('selected', 'selected')
					}
				})
				//delete data.object.power_mode
				$('#nouse_ctrl>option').each(function() {
					if($(this).val() == data.object.nouse_ctrl) {
						$(this).prop('selected', 'selected')
					}
				})
				//delete data.object.nouse_ctrl
				$('#adc_cap_time').val(data.object.adc_cap_time)
				delete data.object.adc_cap_time
				$('#use_light_ctrl').val(data.object.use_light_ctrl)
				//delete data.object.use_light_ctrl
				var start = ""
				var end = ''
				for(var i = 0, len = data.object.use_time_ctrl.split('~')[0].length; i < len; i++) {
					start += data.object.use_time_ctrl.split('~')[0][i];
					if(i % 2 == 1) start += ':';
				}
				for(var y = 0, len = data.object.use_time_ctrl.split('~')[1].length; y < len; y++) {
					end += data.object.use_time_ctrl.split('~')[1][y];
					if(y % 2 == 1) end += ':';
				}

				$('#beginTime').val(start.substr(0, end.length - 1))
				$('#endendTime').val(end.substr(0, end.length - 1))
				//delete data.object.use_time_ctrl
				$('.wangluocanshu input:text').each(function() {
					oldconfig[$(this).attr('name')] = $(this).val()
				})
				oldconfig['power_mode'] = $('#power_mode').find('option:selected').val()
				oldconfig["nouse_ctrl"] = $('#nouse_ctrl').find('option:selected').val()
				oldconfig["adc_cap_time"] = $('#adc_cap_time').val()
				oldconfig["use_light_ctrl"] = $('#use_light_ctrl').val()
				oldconfig["use_time_ctrl"] = data.object.use_time_ctrl
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
	});
}

function writeMainDeviceSetting(v1, v2, v3) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.deviceId = v1;
	obj.ip = v2;
	obj.port = v3;
	parent.loadShow()
	$.ajax({
		url: http + "writeMainDeviceSetting",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
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
			parent.removeload()
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
}

function addIPC(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.deviceId = v1;
	obj.pointEntity = {};
	obj.pointEntity.tp_pid = v2;
	obj.pointEntity.ip = v3;
	obj.pointEntity.port = v4;
	obj.pointEntity.tp_name = v5;
	obj.s_nod = v6;
	obj.s_ip = v7;
	obj.s_port = v8;
	obj.s_username = v9;
	obj.s_password = v10;
	obj.s_stream = v11;
	obj.s_power = v12;
	var mapingDeviceIdStr;
	mapingDeviceIdStr = (parseInt(v6) + 1) < 10 ? "0" + (parseInt(v6) + 1) : (parseInt(v6) + 1)
	obj.mapingDeviceId = v1 + '.' + mapingDeviceIdStr
	obj.ipc = { 's_pwr': '0', 's_pwrval': '0', 'deviceId': v1 }
	parent.loadShow()
	$.ajax({
		url: http + "addIPC",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				$('#ms2').hide()
				listPoint();
				listIPC(active().attr('data-deviceid'))
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
			parent.removeload()
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
}
//省市区
function listProvice(sheng,shi,qu) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	$.ajax({
		url: http + "listProvice",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$('#sheng').empty()
				$(data.object).each(function(i, el) {
					$('#sheng').append("<option value=" + data.object[i].ar_id + ">" + data.object[i].a_name + "</option>")
				})
				var ar_id = data.object[0].ar_id;
				if(sheng){
					$('#sheng').find('option').each(function(){
						if($(this).val()==sheng){
							$(this).prop('selected','selected')
						}
					})
					listCity(sheng,shi,qu);
				}else{
					listCity(ar_id);
				}
				$('#sheng').unbind('change')
				$('#sheng').on('change', function() {
					listCity($('#sheng').find('option:selected').val());
					$('#qu').empty()
				})
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
	});
}

function listCity(ar_id,shi,qu) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.ar_id = ar_id;
	$.ajax({
		url: http + "listCity",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$('#shi').empty()
				$(data.object).each(function(i, el) {
					$('#shi').append("<option value=" + data.object[i].ar_id + ">" + data.object[i].a_name + "</option>")
				})
				var ar_id = data.object[0].ar_id;
				/*$('#shi').unbind('change');
				$('#shi').on('change', function() {
					listDistrict($('#shi').find('option:selected').val())
				})*/
				if(shi){
					listDistrict(shi,qu);
					$('#shi').find('option').each(function(){
						if($(this).val()==shi){
							$(this).prop('selected','selected');
						}
					})
				}else{
					listDistrict(ar_id);
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
	});
}

function listDistrict(ar_id,qu) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.ar_id = ar_id;
	$.ajax({
		url: http + "listDistrict",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$('#qu').empty()
				$(data.object).each(function(i, el) {
					$('#qu').append("<option value=" + data.object[i].ar_id + ">" + data.object[i].a_name + "</option>")
				})
				if(qu){
					$('#qu').find('option').each(function(){
						if($(this).val()==qu){
							$(this).prop('selected','selected');
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
	});
}
listUser1()
listPoint3()
expertnew();

function expertnew() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	$.ajax({
		url: http + "listProfessor",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$('#zhidaozhuanjia').empty()
				$(data.object).each(function(i, el) {
					$('#zhidaozhuanjia').append("<option value=" + data.object[i].u_id + ">" + data.object[i].u_uname + "</option>")
				})
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
	});
}

function listUser1() {
	var obj = new Object();

	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.tu_type=3;
	$.ajax({
		url: http + "listUser",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$('#shengchan').empty()
				$(data.object).each(function(i, el) {
					$('#shengchan').append("<option value=" + data.object[i].uid + ">" + data.object[i].tu_name + "</option>")
				})
				$('#jianguan').empty()
				$(data.object).each(function(i, el) {
					$('#jianguan').append("<option value=" + data.object[i].uid + ">" + data.object[i].tu_name + "</option>")
				})

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
	});
}

function listPoint3(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	if(arguments.length == 1) {
		obj.tp_pid = id
	}
	$.ajax({
		url: http + "listPoint3",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$('#zhandian').empty()
				$(data.object.site).each(function(i, el) {
					$('#zhandian').append("<option value=" + data.object.site[i].tp_id + ">" + data.object.site[i].tp_name + "</option>")
				})
				$('#fufenlei').empty()
				$(data.object.group).each(function(x, el) {
					$('#fufenlei').append("<option value=" + data.object.group[x].tp_id + ">" + data.object.group[x].tp_name + "</option>")
				})

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
	});
}
function addnewMainDevice(){
		$('#xuhao').val('');
		$('#neiwang').val('');
		$('#neiwangduankou').val('');
		$("#waiwang").val('');
		$("#waiwangduankou").val('');
		$("#daili").val('');
		$("#dailiduankou").val('');
		$("#shi").val('');
		$("#qu").val('');
		$("#zhushebei").val('');
		$("#suoyouzhe").val('');
		$("#duankou").val('');
		$("#lat").val('');
		$("#lng").val('');
		$("#zoom").val('');
		$("#sheng").val('');
}
function setMainDevice(){
	var obj = new Object();
		obj.ckuid = sessionStorage.getItem('ckuid');
		obj.cksid = sessionStorage.getItem('cksid');
		obj.tp_id = active().attr('data-tp_id');
		obj.tp_type = 3;
		obj.tp_name =active().attr('data-name');
		$.ajax({
				url: http + "getSetting",
				type: "post",
				contentType: "application/json",
				headers: { 'Content-type': 'application/json;charset=UTF-8' },
				data: JSON.stringify(obj),
				cache: false,
				success: function(data) {
					console.log(data)
					parent.removeload()
					if(data.state == 0) {
						$('#zhandian').find('option').each(function(){
							if($(this).val()==data.object.siteid){
								$(this).prop('selected','selectd');
							}
						});
						$('#fufenlei').find('option').each(function(){
							if($(this).val()==data.object.groupid){
								$(this).prop('selected','selectd');
							}
						});
						$('#hardWareVersion').text(data.object.hardWareVersion);
						$('#softWareVersion').text(data.object.softWareVersion);
						$('#xuhao').val(data.object.orderNo);
						$('#neiwang').val(data.object.nip);
						$('#neiwangduankou').val(data.object.nport);
						$("#waiwang").val(data.object.ip);
						$("#waiwangduankou").val(data.object.port);
						$("#daili").val(data.object.proxyIp);
						$("#dailiduankou").val(data.object.proxyPort);
						listProvice(data.object.province,data.object.city,data.object.district);
						$("#zhidaozhuanjia").find('option').each(function(){
							if($(this).val()==data.object.exportorid){
								$(this).prop('selected','selected');
							}
						});
						$("#lianjiefangshi").find('option').each(function(){
							if($(this).val()==data.object.useIPConnect){
								$(this).prop('selected','selected');
							}
						});
						$("#zhushebei").val(data.object.deviceId);
						$("#suoyouzhe").val(data.object.name);
						$("#duankou").val(data.object.softWareUpdatePort);
						$("#lat").val(data.object.x);
						$("#lng").val(data.object.y);
						$("#zoom").val(data.object.zoom);
						$("#sheng").val(data.object.province);
					/*	$("#shi").val(listCity(data.object.city));
						$("#qu").val(listDistrict(data.object.district));*/
						$("#jianguan").find('option').each(function(){
							if($(this).val()==data.object.superviserid){
								$(this).prop('selected','selected');
							}
						})
						$("#shengchan").find('option').each(function(){
							if($(this).val()==data.object.producerid){
								$(this).prop('selected','selected');
							}
						});
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
					parent.removeload()
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
	
}
function saveAndUpdateMainDevice(arrs) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = { 'tp_pid': $('#fufenlei').find('option:selected').val() }
	obj.orderNo = $('#xuhao').val();
   if(arrs==1){
		obj.id=active().attr('data-tp_id');
	}
	//设备序号
	if(!obj.orderNo) {
		swal({
			title: '请填写设备序号',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.nip = $('#neiwang').val();
	if(!obj.nip) {
		swal({
			title: '请填写内网IP',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.nport = $('#neiwangduankou').val();
	if(!obj.nport) {
		swal({
			title: '请填写内网端口',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ip = $('#waiwang').val();
	if(!obj.ip) {
		swal({
			title: '请填写外网IP',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.port = $('#waiwangduankou').val();
	if(!obj.port) {
		swal({
			title: '请填写外网端口',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.proxyIp = $('#daili').val();
	if(!obj.proxyIp) {
		swal({
			title: '请填写代理IP',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.proxyPort = $('#dailiduankou').val();
	if(!obj.proxyPort) {
		swal({
			title: '请填写代理端口',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.x = $('#lat').val();
	if(!obj.x) {
		swal({
			title: '请填写纬度',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.y = $('#lng').val();
	if(!obj.y) {
		swal({
			title: '请填写经度',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.zoom = $('#zoom').val();
	if(!obj.zoom) {
		swal({
			title: '请填写ZOOM',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	//软件更新端口
	obj.softWareUpdatePort = $('#duankou').val();
	if(!obj.softWareUpdatePort) {
		swal({
			title: '请填写软件更新端口',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.deviceId = $('#zhushebei').val();
	if(!obj.deviceId) {
		swal({
			title: '请填写主设备编号',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	//连接方式
	obj.useIPConnect = $('#lianjiefangshi').find('option:selected').val();
	obj.name = $('#suoyouzhe').val();
	if(!obj.name) {
		swal({
			title: '请填写所有者姓名',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.superivsername = $('#jianguan').find('option:selected').text();
	obj.superviserid = $('#jianguan').find('option:selected').val();
	obj.producername = $('#shengchan').find('option:selected').text();
	obj.producerid = $('#shengchan').find('option:selected').val();
	obj.exportorname = $('#zhidaozhuanjia').find('option:selected').text();
	obj.exportorid = $('#zhidaozhuanjia').find('option:selected').val();
	obj.province = $('#sheng').find('option:selected').val();
	obj.city = $('#shi').find('option:selected').val();
	obj.district = $('#qu').find('option:selected').val();
	obj.siteid = $('#zhandian').find('option:selected').val();
	obj.groupid = $('#fufenlei').find('option:selected').val();
	$.ajax({
		url: http + "saveAndUpdateMainDevice",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				$('#ms1').hide()
				listPoint();
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
	});
}
//添加分类
function addClass(v1, v2, v3, type) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.c_lev = v1;
	if(v1 == 2) {
		obj.c_rid = v2
	}
	obj.c_name = v3;
	obj.c_type = type;
	$.ajax({
		url: http + "addClass",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.success) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				listClass1(type)
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
	});
}

function addPoint(v1, v2, v3) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.tp_name = v1;
	obj.tp_type = v2;
	if(v2 == 1) {
		obj.tp_pid = 0
	} else {
		obj.tp_pid = v3
	}
	$.ajax({
		url: http + "addPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				listPoint();
				listPointGroup()
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
	});
}
//查看一级分类
function listClass1(type) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.c_type = type;
	$.ajax({
		url: http + "listClass1",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$(".type").empty();
				if(data.object != null) {
					var title = '',
						btn = '',
						titlt1 = '',
						ts = '';
					if(confignumChild == 1) {
						title = '作物种类';
						btn = '新增作物品种';
						titlt1 = '作物品种';
					}
					if(confignumChild == 2) {
						title = '农资分类';
						btn = '新增农资产品';
						titlt1 = '作物产品';
					}
					if(confignumChild == 7) {
						title = '一级分类名称';
						btn = '新增二级分类';
						titlt1 = '二级分类名';
					}
					if(type == 1 || type == 2 || type == 7) {
						for(var i = 0; i < data.object.length; i++) {
							var str = ''
							str += '<li class="addClassList" data-c_rid=' + data.object[i].c_id + '><div class="add_top"><div class="type_tit fl">' + title + '</div><input type="text" value=' + data.object[i].c_name + ' class="typeone fl" readonly/><div class="switch fr">展开</div><div class="type_del type_del1 fr" data-id=' + data.object[i].c_id + '>删除</div></div> <div class="add_bot" style="display: none"> <div class="addtwo">' + btn + '</div> <ul class="typetwo">'
							if(data.object[i].list.length != 0 || data.object[i].list.length != null) {
								$(data.object[i].list).each(function(y, el) {
									str += ' <li class="addClassListChild"> <div class="type_tit2 fl">' + titlt1 + '</div> <input type="text" class="typeone2 fl" readonly value=' + data.object[i].list[y].c_name + ' /> <div class="type_del type_del1 fr" data-id=' + data.object[i].list[y].c_id + '>删除</div> </li>'
								})
							}
							str += '</ul> </div></li>';
							$(".type").append(str);
						}
					} else {
						for(var i = 0; i < data.object.length; i++) {
							var str = ''
							str += '<li class="addClassList" data-c_rid=' + data.object[i].c_id + '><div class="add_top"><div class="type_tit fl">' + title + '</div><input type="text" value=' + data.object[i].c_name + ' class="typeone fl" readonly/><div class="type_del type_del1 fr" data-id=' + data.object[i].c_id + '>删除</div></div> <div class="add_bot" style="display: none"></div></li>';
							$(".type").append(str);
						}
					}

				} else {
					swal({
						title: data.msg,
						text: "2秒后关闭",
						confirmButtonText: "确定",
						confirmButtonColor: "#30862B",
						timer: 2000
					});
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
	});
}

function listPointGroup() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	$.ajax({
		url: http + "listPointGroup",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$(".type").empty();
				if(data.object != null) {
					var title = '',
						btn = '',
						titlt1 = '',
						ts = '';
					if(confignumChild == 0) {
						title = '站点';
						btn = '新增分组';
						titlt1 = '分组';
					}
					for(var i = 0; i < data.object.length; i++) {
						var str = ''
						str += '<li class="addClassList" data-c_rid=' + data.object[i].tp_id + '><div class="add_top"><div class="type_tit fl">' + title + '</div><input type="text" value=' + data.object[i].tp_name + ' class="typeone fl" readonly/><div class="switch fr">展开</div><div class="type_del type_del1 fr" data-id=' + data.object[i].tp_id + '>删除</div></div> <div class="add_bot" style="display: none"> <div class="addtwo">' + btn + '</div> <ul class="typetwo">'
						if(data.object[i].list.length != 0 || data.object[i].list.length != null) {
							$(data.object[i].list).each(function(y, el) {
								str += ' <li class="addClassListChild"> <div class="type_tit2 fl">' + titlt1 + '</div> <input type="text" class="typeone2 fl" readonly value=' + data.object[i].list[y].tp_name + ' /> <div class="type_del type_del1 fr" data-id=' + data.object[i].list[y].tp_id + '>删除</div> </li>'
							})
						}
						str += '</ul> </div></li>';
						$(".type").append(str);
					}

				} else {
					swal({
						title: data.msg,
						text: "2秒后关闭",
						confirmButtonText: "确定",
						confirmButtonColor: "#30862B",
						timer: 2000
					});
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
	});
}
//删除分类
function deleteClass(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.c_id = id;
	$.ajax({
		url: http + "deleteClass",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.success) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				listClass1(confignumChild)
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
	});
}

function deletePoint(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.tp_id = id;
	$.ajax({
		url: http + "deletePoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.success) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				listPointGroup()
				listPoint()
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
	});
}

function listPoint(){
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
			if(data.state==0){
                    //sessionStorage.setItem('objData',JSON.stringify(data.object))
                    var str="";
                    $(data.object).each(function(i,el){
                        str+="<div class='point'><div class='icon student'> <img src='./img/shipin/ico_home.png' alt=''/> </div> <p class='pinline' style='font-size: 0.12rem'> "+data.object[i].tp_name+"</p>"
                        $(data.object[i].rank).each(function(l,el){
                            str+=" <ul class='navigation' style='font-size: 0.12rem'> <li> <div class='icon fa1'> <img src='./img/shipin/ico_group.png' alt=''/> </div> <p  class='pinline'>"+data.object[i].rank[l].tp_name+"</p>"
                            $(data.object[i].rank[l].rank).each(function(y,el){
                                str+="<ul class='tree'> <div class='fa2Out'> <li><div class='icon fa2'> <img src='./img/shipin/ico_device.png' alt=''/> </div> <p  class='pinline pinline1' data-tp_id="+data.object[i].rank[l].rank[y].tp_id+" data-supervisername="+data.object[i].rank[l].rank[y].supervisername+" data-state="+data.object[i].rank[l].rank[y].state+" data-producername="+data.object[i].rank[l].rank[y].producername+" data-name="+data.object[i].rank[l].rank[y].name+" data-exportorname="+data.object[i].rank[l].rank[y].exportorname+" data-x="+data.object[i].rank[l].rank[y].x+" data-y="+data.object[i].rank[l].rank[y].y+" data-deviceId="+data.object[i].rank[l].rank[y].deviceId+" data-ip="+data.object[i].rank[l].rank[y].ip+" data-port="+data.object[i].rank[l].rank[y].port+">"+data.object[i].rank[l].rank[y].tp_name+"</p></div> <ul class='tree2'> "
                                $(data.object[i].rank[l].rank[y].rank).each(function(z,el){
                                    str+="<li id="+data.object[i].rank[l].rank[y].rank[z].tp_id+"><div class='icon fa3'><spna class='border'></spna><img src='./img/camera.svg' alt=''/></div><a href='javascript:;' class='pinline piline3' datasheatri='"+data.object[i].rank[l].rank[y].rank[z].deviceId+"'>"+data.object[i].rank[l].rank[y].rank[z].tp_name+"</a></li>"
                                })
                                str+="</ul></li></ul>"
                            })
                            str+=" </li> </ul>"
                        })
                        str+="</div>"
                    })
                    $(".nav_left").empty().append(str);
                    $('.navigation:eq(0)').find('li:eq(0)').find('.tree:eq(0)').find('.fa2Out').click();
					$('.navigation:eq(0)').find('li:eq(0)').find('.tree:eq(0)').find('.tree2').find('li:eq(0)').click();
                    $('.tree').on('click',function(){
                    if($(this).siblings('ul').is(":visible")==false){
                        $(this).siblings('ul').find('.tree2').show();
                    }else{
                        $(this).siblings('ul').find('.tree2').hide();
                    }
                })
				$('.fa2Out').each(function(){
                    	if($(this).find('.pinline1').attr('data-state')==2){//2不在线
                    		$(this).find('img').attr('src','./img/shipin/ico_disconnect.png');
                    	}else{
                    		$(this).find('img').attr('src','./img/shipin/ico_device.png');
                    	}
                    	
                    })
				$('.fa2Out').on('click',function(){
                    if($(this).siblings('ul').is(":visible")==false){
                        $(this).siblings('ul').show()
                    }else{
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

function listIPC(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.deviceId = id;
	obj.pointEntity = { 'ip': active().attr('data-ip'), 'port': active().attr('data-port'), 'deviceId': id };
	$.ajax({
		url: http + "listIPC",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				var streamType = ["主码流", "子码流"]
				var s_power = ["不控制", "控制"]
				var stauts = ["未同步", "已同步"]
				$('#td>tbody').empty();
				$(data.object).each(function(y, el) {
					$('#td>tbody').append("<tr><td>" + (y + 1) + "</td><td>" + data.object[y].deviceId + "</td><td>" + data.object[y].name + "</td><td>" + data.object[y].s_ip + "</td><td>" + data.object[y].s_port + "</td><td>" + streamType[data.object[y].streamType] + "</td><td>" + s_power[data.object[y].s_power] + "</td><td>已同步</td><td>" + stauts[data.object[y].stauts] + "</td><td><span class='sbj' data-id=" + data.object[y].id + " onclick='getIPCProxy(\"" + data.object[y].mapingDeviceId + "\"," + '1' + ",\"" + data.object[y].id + "\"," + '1' + ")'>编辑</span></td><td><span class='sxg1' onclick='getIPCProxy(\"" + data.object[y].mapingDeviceId + "\"," + '1' + ",\"" + data.object[y].id + "\"," + '2' + ")'>修改</span></td><td><span class='sxg2' onclick='getIPCProxy(\"" + data.object[y].mapingDeviceId + "\"," + '2' + ",\"" + data.object[y].id + "\"," + '3' + ")'>修改</span></td><td><span class='sxg3' onclick='getIPCCodeing(\"" + data.object[y].mapingDeviceId + "\",\"" + data.object[y].id + "\",\"" + data.object[y].deviceId + "\")'>修改</span></td></tr>")
				})
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
	});
}
//控制设备列表
function listControlSetting() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = { 'deviceId': active().attr('data-deviceId'), 'tp_id': active().attr('data-tp_id') }
	$.ajax({
		url: http + "listControlSetting",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$('.equis').empty().append(" <li class='select_eq'>主设备</li>")
				$('.list1').show().siblings('.list').hide()
				$(data.object).each(function(i, el) {
					$('.equis').append("<li data-ctrl_id=" + data.object[i].ctrl_id + ">" + data.object[i].ctrl_name + "</li>")
				})
				$('.equis>li').on('click', function() {
					$(this).addClass('select_eq').siblings().removeClass('select_eq')
					if($(this).index() == 0) {
						$('.list1').show().siblings('.list').hide()
					} else {
						$('.list2').show().siblings('.list').hide()
						var id = $(this).attr('data-ctrl_id')
						listChannel(2, id);
					}
				})
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
	});
}

function listChannel(num, id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.deviceId = active().attr('data-deviceId')
	obj.tp_pid = active().attr('data-tp_id')
	$.ajax({
		url: http + "listChannel",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				if(num == 1) {
					$('#ms3 .kongwen').empty()
					$('#ms3 .kongshui').empty()
					$('#ms3 .parentshipinId').empty()
					$(data.object.listChannel).each(function(i, el) {
						$('#ms3 .kongwen').append("<option value=" + data.object.listChannel[i].fieldName + ">" + data.object.listChannel[i].name + "</option>")
						$('#ms3 .kongshui').append("<option value=" + data.object.listChannel[i].fieldName + ">" + data.object.listChannel[i].name + "</option>")
					})
					$(data.object.listPoint).each(function(y, el) {
						$('#ms3 .parentshipinId').append("<option value=" + data.object.listPoint[y].deviceId + ">" + data.object.listPoint[y].tp_name + "</option>")
					})
					$('#ms3 .parentId').val(active().attr('data-deviceId'))
				} else {
					$('.list2 .kongwen').empty()
					$('.list2 .kongshui').empty()
					$('.list2 .parentshipinId').empty()
					$(data.object.listChannel).each(function(i, el) {
						$('.list2 .kongwen').append("<option value=" + data.object.listChannel[i].fieldName + ">" + data.object.listChannel[i].name + "</option>")
						$('.list2 .kongshui').append("<option value=" + data.object.listChannel[i].fieldName + ">" + data.object.listChannel[i].name + "</option>")
					})
					$(data.object.listPoint).each(function(y, el) {
						$('.list2 .parentshipinId').append("<option value=" + data.object.listPoint[y].deviceId + ">" + data.object.listPoint[y].tp_name + "</option>")
					})
					$('.list2 .parentId').val(active().attr('data-deviceId'))
					getControlSetting(id)
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
	});
}

function addControlSetting() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = { 'tp_id': active().attr('data-tp_id') }
	obj.ctrl_deviceId = active().attr('data-deviceId')
	obj.ctrl_mapingdeviceId = $('#ms3 .parentshipinId').find('option:selected').val()
	obj.ctrl_name = $('#ms3 .controlname').val()
	if(!obj.ctrl_name) {
		swal({
			title: '控制设备不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_nickname = $('#ms3 .controlbieming').val()
	if(!obj.ctrl_nickname) {
		swal({
			title: '控制设备别名不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_min = $('#ms3 .minnum').val()
	if(!obj.ctrl_min) {
		swal({
			title: '映射最小值不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_max = $('#ms3 .ctrl_man').val()
	if(!obj.ctrl_max) {
		swal({
			title: '映射最大值不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_channel = $('#ms3 .ctrl_channel').val()
	if(!obj.ctrl_channel) {
		swal({
			title: '传感器通道不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	if(parseInt(obj.ctrl_channel) > 50 || parseInt(obj.ctrl_channel) < 1) {
		swal({
			title: '传感器通道不能大于50小于1',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_type = $('#ms3 .controltype').find('option:selected').val()
	obj.ctrl_temperature = $('#ms3 .kongwen').find('option:selected').val()
	obj.ctrl_water = $('#ms3 .kongshui').find('option:selected').val()
	obj.ctrl_raise_groupId = $('#ms3 .upnum').val()
	if(!obj.ctrl_raise_groupId) {
		swal({
			title: '上升通道组号不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_raise_switchId = $('#ms3 .upnumkg').val()
	if(!obj.ctrl_raise_switchId) {
		swal({
			title: '上升通道开关号不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_down_groupId = $('#ms3 .downnum').val()
	if(!obj.ctrl_down_groupId) {
		swal({
			title: '下降通道组号不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_down_switchId = $('#ms3 .ctrl_mix').val()
	if(!obj.ctrl_down_switchId) {
		swal({
			title: '下降通道开关号不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_count = $('#ms3 .jiliang').val()
	if(!obj.ctrl_count) {
		swal({
			title: '计量参数不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_picturetype = $('#ms3 .zhuangtaitu').find('option:selected').val()
	obj.ctrl_picturetitle = $('#ms3 .zhuangtaitutitle').val()
	if(!obj.ctrl_picturetitle) {
		swal({
			title: '状态图标题不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	parent.loadShow()
	$.ajax({
		url: http + "addControlSetting",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				$('#ms3').hide()
				listControlSetting()
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
			parent.removeload()
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
}

function updateControlSetting() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = { 'tp_id': active().attr('data-tp_id') }
	obj.deviceId = active().attr('data-deviceId')
	obj.ctrl_mapingdeviceId = $('.list2 .parentshipinId').find('option:selected').val()
	obj.ctrl_name = $('.list2 .controlname').val()
	obj.ctrl_id = active1().attr('data-ctrl_id')
	if(!obj.ctrl_name) {
		swal({
			title: '控制设备不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_nickname = $('.list2 .controlbieming').val()
	if(!obj.ctrl_nickname) {
		swal({
			title: '控制设备别名不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_min = $('.list2 .minnum').val()
	if(!obj.ctrl_min) {
		swal({
			title: '映射最小值不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_max = $('.list2 .ctrl_man').val()
	if(!obj.ctrl_max) {
		swal({
			title: '映射最大值不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_channel = $('.list2 .ctrl_channel').val()
	if(!obj.ctrl_channel) {
		swal({
			title: '传感器通道不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	if(parseInt(obj.ctrl_channel) > 50 || parseInt(obj.ctrl_channel) < 1) {
		swal({
			title: '传感器通道不能大于50小于1',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_type = $('.list2 .controltype').find('option:selected').val()
	obj.ctrl_temperature = $('.list2 .kongwen').find('option:selected').val()
	obj.ctrl_water = $('.list2 .kongshui').find('option:selected').val()
	obj.ctrl_raise_groupId = $('.list2 .upnum').val()
	if(!obj.ctrl_raise_groupId) {
		swal({
			title: '上升通道组号不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_raise_switchId = $('.list2 .upnumkg').val()
	if(!obj.ctrl_raise_switchId) {
		swal({
			title: '上升通道开关号不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_down_groupId = $('.list2 .downnum').val()
	if(!obj.ctrl_down_groupId) {
		swal({
			title: '下降通道组号不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_down_switchId = $('.list2 .ctrl_mix').val()
	if(!obj.ctrl_down_switchId) {
		swal({
			title: '下降通道开关号不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_count = $('.list2 .jiliang').val()
	if(!obj.ctrl_count) {
		swal({
			title: '计量参数不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	obj.ctrl_picturetype = $('.list2 .zhuangtaitu').find('option:selected').val()
	obj.ctrl_picturetitle = $('.list2 .zhuangtaitutitle').val()
	if(!obj.ctrl_picturetitle) {
		swal({
			title: '状态图标题不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	};
	console.log(obj);
	parent.loadShow()
	$.ajax({
		url: http + "updateControlSetting",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				$('#ms3').hide()
				listControlSetting()
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
			parent.removeload()
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
}

function getControlSetting(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.ctrl_id = id
	obj.pointEntity = { 'tp_id': active().attr('data-tp_id'), 'deviceId': active().attr('data-deviceId') }
	parent.loadShow()
	$.ajax({
		url: http + "getControlSetting",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				$('.list2 .controlname').val(data.object.ctrl_name)
				$('.list2 .controlbieming').val(data.object.ctrl_nickname)
				$('.list2 .ctrl_man').val(data.object.ctrl_max)
				$('.list2 .minnum').val(data.object.ctrl_min)
				$('.list2 .ctrl_channel').val(data.object.ctrl_channel)
				$('.list2 .upnum').val(data.object.ctrl_raise_groupId)
				$('.list2 .upnumkg').val(data.object.ctrl_raise_switchId)
				$('.list2 .downnum').val(data.object.ctrl_down_groupId)
				$('.list2 .ctrl_mix').val(data.object.ctrl_down_switchId)
				$('.list2 .jiliang').val(data.object.ctrl_count)
				$('.list2 .zhuangtaitutitle').val(data.object.ctrl_picturetitle)
				$('.parentshipinId>option').each(function() {
					if($(this).val() == data.object.ctrl_mapingdeviceId) {
						$(this).prop('selected', 'selected')
					}
				})
				$('.kongwen>option').each(function() {
					if($(this).val() == data.object.ctrl_temperature) {
						$(this).prop('selected', 'selected')
					}
				})
				$('.kongshui>option').each(function() {
					if($(this).val() == data.object.ctrl_water) {
						$(this).prop('selected', 'selected')
					}
				})
				$('.controltype>option').each(function() {
					if($(this).val() == data.object.ctrl_type) {
						$(this).prop('selected', 'selected')
					}
				})
				$('.zhuangtaitu>option').each(function() {
					if($(this).val() == data.object.ctrl_picturetype) {
						$(this).prop('selected', 'selected')
					}
				})
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
			parent.removeload()
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
}
//获得规格文件
function getGatherSettings() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.tp_id = active().attr('data-tp_id')
	obj.deviceId = active().attr('data-deviceId')
	parent.loadShow()
	$.ajax({
		url: http + "getGatherSettings",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				$('.tab3>tbody').empty()
				$(data.object).each(function(i, el) {
					$('.tab3>tbody').append("<tr class='guigeList defguigelist'> <td><input class='del' type='checkbox' data-id=" + data.object[i].id + "></td> <td><input type='text' class='channel' value=" + data.object[i].channel + "></td> <td><input  class='name' type='text' value=" + data.object[i].name + "></td> <td><input  class='beginPosition' type='text' value=" + data.object[i].beginPosition + "></td> <td><input  type='text' class='len' value=" + data.object[i].len + "></td> <td><input  class='fieldName' type='text' value=" + data.object[i].fieldName + "></td> <td><input class='decimalFormat' type='text' value=" + data.object[i].decimalFormat + "></td> <td><input  class='lowerLimit' type='text' value=" + data.object[i].lowerLimit + "></td> <td><input  class='upperLimit' type='text' value=" + data.object[i].upperLimit + "></td> <td class='diffPercent'>0%</td> <td><input  class='formula' type='text' style='width: 2rem' value='" + data.object[i].formula + "'></td> <td><input  class='unit' type='text' value=" + data.object[i].unit + "></td> <td><input class='listDisplay' style='width: 0.5rem' type='checkbox'></td> <td><input class='statDisplay' style='width: 0.5rem' type='checkbox'></td> <td><input class='chartDisplay' style='width: 0.5rem' type='checkbox'></td> <td><input  class='chartOrderIndex' type='text' value=" + data.object[i].chartOrderIndex + "></td> <td><select class='chartID' name='' class='guigeType'><option value='0'>无数据</option><option value='1'>空气温度</option><option value='2'>空气湿度</option><option value='3'>雨量</option><option value='4'>风向</option><option value='5'>风速</option><option value='6'>光照度</option><option value='7'>土壤水分</option><option value='8'>土壤温度</option><option value='9'>氮肥含量</option><option value='10'>钾肥含量</option><option value='11'>二氧化碳含量</option><option value='12'>氧气含量</option><option value='13'>PH值</option></select></td> </tr>")
				})
				$('.tab3>tbody>tr').each(function(y, el) {
					if(data.object[y].listDisplay == 1) {
						$('.tab3>tbody>tr').eq(y).find('.listDisplay').prop('checked', 'checked')
					}
					if(data.object[y].statDisplay == 1) {
						$('.tab3>tbody>tr').eq(y).find('.statDisplay').prop('checked', 'checked')
					}
					if(data.object[y].chartDisplay == 1) {
						$('.tab3>tbody>tr').eq(y).find('.chartDisplay').prop('checked', 'checked')
					}
					$('.tab3>tbody>tr').eq(y).find('.chartID>option').each(function(z, el) {
						if(data.object[y].chartID == $('.tab3>tbody>tr').eq(y).find('.chartID>option').eq(z).val()) {
							$('.tab3>tbody>tr').eq(y).find('.chartID>option').eq(z).prop('selected', 'selected')
						}
					})
				})
			} else if(data.state == 4) {
				$('.tab3>tbody').empty()
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
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
			parent.removeload()
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
}

function returnCheck(id) {
	var s = false
	if(id == 1) {
		s = true
	}
	return s
}
//专家
function listProfessor(num, s) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	if(s != 0) {
		obj.u_id = s
		obj.start = 0
		obj.u_search = '';
	}
	if(s == 0) {
		obj.start = num
		obj.u_search = $('.mid_three').find('input').val();
	}
	parent.loadShow()
	$.ajax({
		url: http + "listProfessor",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				if(s == 0) {
					var sexArr = ['', '男', '女']
					$('.r_bot2').find('.tab2>tbody').empty()
					$(data.object).each(function(i) {
						$('.r_bot2').find('.tab2>tbody').append(' <tr> <td>' + data.object[i].u_uname + '</td> <td>' + sexArr[data.object[i].sex] + '</td><td>' + data.object[i].u_phone + '</td> <td>' + returnNull(data.object[i].u_email) + '</td> <td>' + returnNull(data.object[i].position) + '</td> <td>' + returnNull(data.object[i].class1name) + "/" + returnNull(data.object[i].class2name) + '</td> <td> <span data-id=' + data.object[i].u_id + ' class="edit">查看</span> </td> </tr>')
						var totalPage = data.totalpage
						var totalSize = data.totalcount
						$("#page").remove();
						$('#parentpage').append(" <div id='page' class='page_div'></div>")
						$("#page").paging({
							pageNo: num,
							totalPage: totalPage,
							totalSize: totalSize,
							callback: function(num) {
								listProfessor(num, 0)
							}
						});

					})
					$('.tab2 .edit').on('click', function() {
						$('#ms5').show()
						listProfessor(num, $(this).attr('data-id'))
					})
				} else {
					$('#ms5').find('li.li1>img').attr('src', data.object[0].headimgurl)
					$('#ms5').find('li.li1>span').text(data.object[0].u_uname)
					$('#ms5').find('li.li2>span').text(data.object[0].u_phone)
					$('#ms5').find('li.li3>span').text(data.object[0].field)
					$('#ms5').find('li.li4>span').text(data.object[0].u_phone2)
					$('#ms5').find('li.li5>span').text(data.object[0].position)
					$('#ms5').find('li.li6>span').text(data.object[0].u_email)
					$('#ms5').find('li.li7>span').text(returnNull(data.object[0].class1name))
					$('#ms5').find('li.li8>span').text(returnNull(data.object[0].class2name))
					$('#ms5').find('li.li9>span').text(data.object[0].detail)
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
			parent.removeload()
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
}

//生产这管理 listUser(1,0,2)  用户103
function listRelationShip(num) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.start = num
	parent.loadShow();
	$.ajax({
		url: http + "listRelationShip",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload();
			$('.r_bot3').find('.tab2>tbody').empty();
					$('#parentpage1').show()
					$(data.object).each(function(i, el) {
						var str="",str1="",str2="";
						str += '<tr> <td>' + data.object[i].tu_name +' </td><td>' + data.object[i].tu_phone +'</td><td class="tp_name">' + data.object[i].tp_name +'</td><td class="deviceId">' + data.object[i].deviceId +' </td><td>'
						if(data.object[i].tu_state == 1) {
							str += "正常"
						} else {
							str += "封停"
						}
						str += '</td> <td> '
						if(data.object[i].tu_state == 1) {
							str += '<span class="change seal huifubtn" data-s="1" data-id=' + data.object[i].tu_id + '>封号</span>'
						} else {
							str += '<span class="change recover huifubtn" data-s="2" data-id=' + data.object[i].tu_id + '>恢复</span>'
						}
						str += '</td> <td> <span class="editPro chankanxiangqing" data-jieid="'+data.object[i].id+'" data-id=' + data.object[i].tu_id + '>'
						str += '查看</span></td> </tr>'
						$('.r_bot3').find('.tab2>tbody').append(str);
					});
					$('.editPro').click(function(){
						$("#msss").show().siblings('.mask').hide();
						$("#msss").find('.li1')
					})
			},
		error: function() {
			parent.removeload()
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
}
$(document).on('click','.huifubtn',function(){
	var text=$(this).text(),id=$(this).attr('data-id');
	if(text=='封号'){
		addupdateUser(id,'2')
	}else if(text=='恢复'){
		addupdateUser(id,'1')
	}
	
})
$(document).on('click','.chankanxiangqing',function(){
	$('#msss').show()
	var id=$(this).attr('data-id'),tp_name=$(this).parent().siblings('.tp_name').text(),tp_id=$(this).parent().siblings('.deviceId').text(),jieid=$(this).attr('data-jieid');
	$('.jiebang').attr('data-jiebang',jieid)
	showPrudectMsg(id,tp_name,tp_id)
})
//产看详情
function showPrudectMsg(id,tp_name,tp_id){
	$.ajax({
		type:"post",
		url:http+"listUser",
		async:true,
		data:JSON.stringify({
			'uid':id,
			'ckuid':sessionStorage.getItem('ckuid'),
			'cksid':sessionStorage.getItem('cksid')
		}),
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		success:function(data){
			if(data.success==true){
				$('#tu_name').val(data.object[0].tu_name)
				$('#tp_name').val(tp_name)
				$('#tp_id').val(tp_id)
			}else{
				alert(data.msg);
				return 
			}
		}
	});
}
//修改状态
function addupdateUser(tu_id,type){
	$.ajax({
		type:"post",
		url:http+"updateUser",
		async:true,
		data:JSON.stringify({
			'ckuid':sessionStorage.getItem('ckuid'),
			'cksid':sessionStorage.getItem('cksid'),
			'uid':tu_id,
			'tu_state':type
		}),
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		success:function(data){
			if(data.success==true){
				listRelationShip(1)
			}else{
				alert(data.msg)
				return
			}
		}
	});
}
//详情点击返回
$(document).on('click','.jiebang1',function(){
	$('#msss').hide()
})
//解绑
$(document).on('click','.jiebang',function(){
	var id=$(this).attr('data-jiebang');
	$.ajax({
		type:"post",
		url:http+"unbind",
		async:true,
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data:JSON.stringify({
			'ckuid':sessionStorage.getItem('ckuid'),
			'cksid':sessionStorage.getItem('cksid'),
			'r_id':id,
			'unbind':'1',
			'deviceId':active().attr('data-deviceid'),
		}),
		success:function(data){
			if(data.success==true){
				alert(data.msg);
				$('#msss').hide()
				listRelationShip(1);
			}else {
				alert(data.msg);
				return
			}
		}
	});
})
//权限管理
function quanxian(){
	$('.r_bot9').find('.tab2>tbody').html("")
	var str="";
	$.ajax({
		type:"post",
		url:http+"listPower",
		async:true,
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data:JSON.stringify({
			'ckuid':sessionStorage.getItem('ckuid'),
			'cksid':sessionStorage.getItem('cksid'),
			'start':1,
		}),
		success:function(data){
			console.log(data)
			if(data.success==true){
				$(data.object).each(function(i, el) {
					str += '<tr> <td>' + data.object[i].tu_name +'</td><td>' + data.object[i].tu_phone +'</td><td>'
					if(data.object[i].tu_type == 1||data.object[i].tu_type == 2) {
						str += "管理员 "
					} else if(data.object[i].tu_type == 3) {
						str += "普通用户"
					}else if(data.object[i].tu_type == 4){
						str+='生产者'
					}else if(data.object[i].tu_type == 5){
						str+='监督者'
					}else if(data.object[i].tu_type == 6){
						str+='生产者,监督者'
					}else if(data.object[i].tu_type == 7){
						str+='专家'
					}
					str += '</td> <td> '
					if(data.object[i].tu_state == 1) {
						str += '<span class="change seal qxfenhao" data-s="1" data-id=' + data.object[i].uid + '>封号</span>'
					} else {
						str += '<span class="change recover qxfenhao" data-s="2" data-id=' + data.object[i].uid + '>恢复</span>'
					}
					str += '</td> <td> <span class="editPro qxshowxq" data-name="'+data.object[i].tu_name+'" data-type="'+data.object[i].tu_type+'" data-id=' + data.object[i].uid + '>'
					str += '编辑</span></td> </tr>'
					
				});
				$('.r_bot9').find('.tab2>tbody').append(str);
			}
		}
	});
}
$(document).on('click','.qxfenhao',function(){
	var text=$(this).text(),id=$(this).attr('data-id');
	var ids=$(this).parents('td').siblings('td').find('.qxshowxq').attr('data-type');
	if(ids==7){
		if(text=='封号'){
		qxUpdataUser(id,'2',ids)
	}else if(text=='恢复'){
		qxUpdataUser(id,'1',ids)
	}
	}else{
		if(text=='封号'){
		qxUpdataUser(id,'2')
	}else if(text=='恢复'){
		qxUpdataUser(id,'1')
		}
	}
})
function qxUpdataUser(tu_id,type,ids){
	var obj=new Object();
	obj.ckuid=sessionStorage.getItem('ckuid');
	obj.cksid=sessionStorage.getItem('cksid');
	obj.uid=tu_id;
	obj.tu_state=type;
	if(ids){
		obj.tu_type=ids;
	}
	$.ajax({
		type:"post",
		url:http+"updateUser",
		async:true,
		data:JSON.stringify(obj),
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		success:function(data){
			if(data.success==true){
				quanxian()
			}else{
				alert(data.msg)
				return
			}
		}
	});
}
$(document).on('click','.qxshowxq',function(){
	$('#ms14').show()
	var type=$(this).attr('data-type'),name=$(this).attr('data-name');
	$('#qxsave').attr('data-id',$(this).attr('data-id'))
	$('#ms14_name').text(name);
	if(type==1||type==2){
		$('#permissions option').each(function(item,val){
			if($(val).val()=='2'){
				$(val).attr('selected',true);
				$('.permissions_list').hide();
			/*}else{
				$('.permissions_list').show();*/
			}
		})
	}
	else if(type==3){
		$('#permissions option').each(function(item,val){
			if($(val).val()=='3'){
				$(val).attr('selected',true);
				$('.permissions_list').hide();
			}
		})
	}
	else if(type==4){
		$('#shengchanzhe').attr('checked',true);
		$('.permissions_list').show();
	}
	else if(type==5){
		$('#jianduzghe').attr('checked',true);
		$('.permissions_list').show();
	}
	else if(type==6){
		$('.permissions_list').show();
		$('#jianduzghe').attr('checked',true)
		$('#shengchanzhe').attr('checked',true)
	}
	else if(type==7){
		$('.permissions_list').show();
		$('#zhuanjia').attr('checked',true)
	}
})
$(document).on('click','#qxsave',function(){
	var type1=$('#permissions').val();
	var tu_id=$(this).attr('data-id');
	$.ajax({
		type:"post",
		url:http+"updateUser",
		async:true,
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify({
			'ckuid':sessionStorage.getItem('ckuid'),
			'cksid':sessionStorage.getItem('cksid'),
			'uid':tu_id,
			'tu_type':type1
		}),
		success:function(data){
			if(data.success==true){
				alert(data.msg)
				$('#ms14').hide();
				quanxian();
			}else{
				alert(data.msg)
				return
			}
		}
	});
})
$('#qxguabbi').on('click',function(){
	$('#ms14').hide();
})
//用户和管理者的--------------编辑与添加
function listUser(num, s, state) {  //state   用户----3管理者----1
	
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.tu_type = state
	if(s != 0) { //编辑
		obj.uid = s
		obj.start = 1
		obj.u_search = '';
	}
	if(s == 0) {// 添加
		obj.start = num
		obj.u_search = $('.mid_three').find('input').val();
	}
	parent.loadShow()
	$.ajax({
		url: http + "listUser",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload();
			if(data.state == 0) {
				if(s == 0) {//添加管理者或者用户
					if(state==1){
						$('.r_bot33').find('.tab2>tbody').html("")
					}else if(state==3){
						$('.r_bot23').find('.tab2>tbody').html("")
					}
					$('#parentpage1').show();
					$(data.object).each(function(i, el) {
						var str;
						str += '<tr> <td>' + data.object[i].tu_name + '</td><td>'+data.object[i].tu_phone+'</td><td>'
						if(data.object[i].tu_state == 1) {
							str += "正常"
						} else {
							str += "封停"
						}
						str += '</td> <td> '
						if(data.object[i].tu_state == 1) {
							str += '<span class="change seal" data-s="1" data-id=' + data.object[i].uid + '>封号</span>'
						} else {
							str += '<span class="change recover" data-s="2" data-id=' + data.object[i].uid + '>恢复</span>'
						}
						str += '</td> <td> <span class="edit" data-id=' + data.object[i].uid + '>'
					/*	if(state == 1) {
							str += '编辑'
						} else {
							str += '查看'
						}*/
						str += '编辑</span></td> </tr>';
						if(state==3){
							$('.r_bot23').find('.tab2>tbody').append(str);
						}else if(state==1){
							$('.r_bot33').find('.tab2>tbody').append(str);
						}
					})
					var totalPage = data.totalpage;
					var totalSize = data.totalcount;
					$("#page2").remove();
					$('#parentpage1').append(" <div id='page2' class='page_div'></div>")
					$("#page2").paging({
						pageNo: num,
						totalPage: totalPage,
						totalSize: totalSize,
						callback: function(num) {
							var v = $('#search').attr('data-s')
							if(v == 1) {
								listUser(num, 0, 3)//用户
							}
							if(v == 2) {
								listUser(num, 0, 2)
							}
							if(v == 3) {
								listUser(num, 0, 1)//管理者
							}
						}
					});
					$('.change').on('click', function() {
						var change_s = $(this).attr('data-s')
						var search_s = $('#search').attr('data-s')
						var updata_id = $(this).attr('data-id')
						if(change_s == 1 && search_s == 1) {
							updateUser(2, updata_id, 3)
						}
						if(change_s == 2 && search_s == 1) {
							updateUser(1, updata_id, 3)
						}
						if(change_s == 1 && search_s == 2) {
							updateUser(2, updata_id, 2)
						}
						if(change_s == 2 && search_s == 2) {
							updateUser(1, updata_id, 2)
						}
						if(change_s == 1 && search_s == 3) {
							updateUser(2, updata_id, 1)
						}
						if(change_s == 2 && search_s == 3) {
							updateUser(1, updata_id, 1)
						}
					});
					
					$('.tab2 .edit').on('click', function(){
						if(state==1){
							$('#ms6').show();
							listUser(num, $(this).attr('data-id'),1);
							$('#ms6').find('.saveedit').attr('data-userId',$(this).attr('data-id'));
						}else{
							$('#ms6user').show();
							listUser(num, $(this).attr('data-id'),3);
							$('#ms6user').find('.saveedit').attr('data-userId',$(this).attr('data-id'));
						}
					});
			/*		$('#ms6').find('.saveedit').click(function(){
						if($('.addguanlizhe').text()=='添加管理者'){
							listUser(num, $(this).attr('data-id'),1);
							return;
						}else{
							listUser(num, $(this).attr('data-id'));
						}
					})
					$('#ms6user').find('.saveedit').click(function(){
						if($('.addguanlizhe').text()=='添加用户'){
							listUser(num, $(this).attr('data-id'),3);
							return;
						}else{
							listUser(num, $(this).attr('data-id'));
						}
					})*/
				} else {//用户或者管理者的-----编辑-----详情 state----3是用户-----1是管理者；
					if(state==1){
						$('#ms6').find('li.li1>input').val(data.object[0].tu_name).show();
						$('#ms6').find('li.li2>input').show();
						$('#ms6').find('li.li5>input').val(data.object[0].tu_username).show();
						/*if(data.object[0].tu_state==1){
							$('#ms6').find('li.li4').find('.seleOne').prop('selected','selected');
						}else{
							$('#ms6').find('li.li4').find('.seleTwo').prop('selected','selected');
						};*/
						$('#ms6').find('.saveedit').click(function(){
							var tu_username = $('#ms6').find('li.li5>input').val();
							var tu_pwd = $('#ms6').find('li.li2>input').val();
							var tu_name = $('#ms6').find('li.li1>input').val();
							/*var tu_state=$('#ms6').find('li.li4>select').val();*/
							var editUid = $(this).attr('data-userId');
							upeditsave(tu_name,tu_username,tu_pwd,editUid,state);
						});
						$('#ms6').find('.deledit').click(function(){
							$("#ms6").hide();
						})
					}else if(state==3){
						$('#ms6user').find('li.li1>input').val(data.object[0].tu_name).show();
						$('#ms6user').find('li.li2>input').show();
						$('#ms6user').find('li.li5>input').val(data.object[0].tu_username).show();
						$('#ms6user').find('li.li4>input').val(data.object[0].tu_phone).show();
						/*if(data.object[0].tu_state==1){
							$('#ms6user').find('li.li4').find('.seleOne').prop('selected','selected');
						}else{
							$('#ms6user').find('li.li4').find('.seleTwo').prop('selected','selected');
						};*/
						$('#ms6user').find('.saveedit').click(function(){
							var tu_username = $('#ms6user').find('li.li5>input').val();
							var tu_pwd = $('#ms6user').find('li.li2>input').val();
							var tu_name = $('#ms6user').find('li.li1>input').val();
							/*var tu_state=$('#ms6user').find('li.li4>select').val();*/
							var editUid = $(this).attr('data-userId');
							var userPhone=$('#ms6user').find('li.li4>input').val();
							upeditsave(tu_name,tu_username, tu_pwd,editUid,state,userPhone);
							
						});
						$('#ms6user').find('.deledit').click(function(){
							$("#ms6user").hide();
						})
					}
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
			} else if(data.state == 3) {
				$('.r_bot3').find('.tab2>tbody').empty();
				$('#parentpage1').hide()
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				return false
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
			parent.removeload()
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
}

function upeditsave(v1, v2, v3, v4,state,phone){//state--1管理者---3用户
	var obj = new Object();
	var s = true;
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.tu_name = v1;
	obj.tu_username = v2;
	obj.tu_pwd = v3;
	obj.uid = v4;
	if(state==1){
		if(!obj.tu_username) {
			swal({
				title: '管理者登录账号不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			s = false
			return;
		}
		if(!obj.tu_name) {
			swal({
				title: '管理者姓名不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			s = false
			return
		}
		
		
	}else if(state==3){
		obj.tu_phone=phone;
		if(!obj.tu_username) {
			swal({
				title: '用户登录账号不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			s = false
			return;
		}
		if(!obj.tu_name) {
			swal({
				title: '用户姓名不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			s = false
			return
		};
		if(!obj.tu_phone) {
			swal({
				title: '手机号不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			s = false
			return
		}
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test(obj.tu_phone)) 
		{ 
			swal({
						title: '请输入有效的手机号码',
						text: "2秒后关闭",
						confirmButtonText: "确定",
						confirmButtonColor: "#30862B",
						timer: 2000
					});
					s = false
					return
		}
	}
	if(s == true){
		
		parent.loadShow();
	$.ajax({
		url: http + "updateUser",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				},function(){
					if(state==3){
						$('#ms6user').hide();
						listUser(1, 0, 3);
					}else{
						$("#ms6").hide();
						listUser(1, 0, 1);
					}
				});
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
			parent.removeload()
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
	}
}

function updateUser(v1, v2, v3) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.tu_state = v1;
	obj.uid = v2;
	obj.tu_type = v3
	parent.loadShow()
	$.ajax({
		url: http + "updateUser",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				active3().click();
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
			parent.removeload()
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
}
//添加管理者---添加用户
function addUser(ids) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	if(ids==1){
		obj.tu_name = $('#ms7').find('li.li1>input').val();
		obj.tu_username = $('#ms7').find('li.li2>input').val();
		obj.tu_pwd = $('#ms7').find('li.li3>input').val();
		if(obj.tu_pwd != $('#ms7').find('li.li4>input').val()) {
		swal({
			title: '两次密码不相同',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	}else if(ids==3){
		obj.tu_name = $('#msuser').find('li.li1>input').val();
		obj.tu_username = $('#msuser').find('li.li2>input').val();
		obj.tu_pwd = $('#msuser').find('li.li3>input').val();
		obj.tu_phone=$('#msuser').find('li.li5>input').val();
		if(obj.tu_pwd != $('#msuser').find('li.li4>input').val()) {
			swal({
				title: '两次密码不相同',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			return
		}
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test(obj.tu_phone)) 
		{ 
			swal({
						title: '请输入有效的手机号码',
						text: "2秒后关闭",
						confirmButtonText: "确定",
						confirmButtonColor: "#30862B",
						timer: 2000
					});
					return
		} 
	}
	var type_num = $('.addguanlizhe').attr('data-type')
	obj.tu_type = type_num;
	if(!obj.tu_name) {
		swal({
			title: '姓名不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	
	if(!obj.tu_username) {
		swal({
			title: '登录账号不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	
	if(!obj.tu_pwd) {
		swal({
			title: '密码不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
		return
	}
	parent.loadShow()
	$.ajax({
		url: http + "addUser",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				if(ids==1){
					$('#ms7').hide();
				}else if(ids==3){
					$('#msuser').hide();
				}
				if(type_num == 1) {
					listUser(1, 0, 1)
				}
				if(type_num == 3) {
					listUser(1, 0, 3)
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
			parent.removeload()
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
}

function getIPCProxy(v1, v2, v3, s) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = {};
	obj.ipc = { 'deviceId': v1, 'type': v2 }
	obj.id = v3
	$.ajax({
		url: http + "getIPCProxy",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				if(s == 1) {
					var arr = ['不在线', '在线']
					$('#ms9').show().find('.pop_save').attr('data-id', data.object.id).attr('data-id_1', data.object.ipcid).attr('data-id_2', data.object.mapingDeviceId)
					$('#ms9').find('li.li2>div').text(arr[parseInt(data.object.s_online)])
					$('#ms9').find('li.li3>div').text(data.object.mapingDeviceId)
					$('#ms9').find('li.li4 input').val(data.object.tp_name)
					$('#ms9').find('li.li5 input').eq(0).val(data.object.s_host)
					$('#ms9').find('li.li5 input').eq(1).val(data.object.s_hostport)
					$('#ms9').find('li.li6 input').val(data.object.username)
					$('#ms9').find('li.li7 input').val(data.object.password)
					$('#ms9').find('li.li8').find('select>option').each(function() {
						if($(this).val() == data.object.s_stream) {
							$(this).prop('selected', 'selected')
						} else {
							$(this).removeAttr('selected')
						}
					})
					$('#ms9').find('li.li9').find('select>option').each(function() {
						if($(this).val() == data.object.s_power) {
							$(this).prop('selected', 'selected')
						} else {
							$(this).removeAttr('selected')
						}
					})
				}
				if(s == 2) {
					$('#ms8').show().find('.pop_save').attr('data-id', data.object.id).attr('data-id_1', data.object.ipcid).attr('data-id_2', data.object.mapingDeviceId)
					$('#ms8').find('li.li1 input').val(data.object.s_host)
					$('#ms8').find('li.li2 input').val(data.object.s_hostport)
					$('#ms8').find('li.li3 input').val(data.object.s_proxy)
				}
				if(s == 3) {
					$('#ms10').show().find('.pop_save').attr('data-id', data.object.id).attr('data-id_1', data.object.ipcid).attr('data-id_2', data.object.mapingDeviceId)
					$('#ms10').find('li.li1 input').val(data.object.s_host)
					$('#ms10').find('li.li2 input').val(data.object.s_hostport)
					$('#ms10').find('li.li3 input').val(data.object.s_proxy)
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
	});
}

function getIPCCodeing(v1, v2, v3) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.mapingDeviceId = v1;
	obj.pointEntity = { 'ip': active().attr('data-ip'), 'port': active().attr('data-port') }
	obj.id = v2
	obj.type = '2'
	parent.loadShow()
	$.ajax({
		url: http + "getIPCCodeing",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				$('#ms11').show().find('.pop_save').attr('data-id', v3).attr('data-mapid', v1).attr('data-id1', v2)
				var data1 = data.object.ablity.mainChannel.videoEncodeTypes
				var data2 = data.object.ablity.mainChannel.solutions
				EncodeType(data1)
				Resolution(data2)
				$('#ms11').find('.li1').find('select').on('change', function() {
					initSelect()
					var v = $('#ms11').find('.li1').find('select>option:selected').val();
					if(v == 0) {
						data1 = data.object.ablity.mainChannel.videoEncodeTypes
						data2 = data.object.ablity.mainChannel.solutions
						EncodeType(data1)
						Resolution(data2)
					}
					if(v == 1) {
						data1 = data.object.ablity.subChannel.videoEncodeTypes
						data2 = data.object.ablity.subChannel.solutions
						EncodeType(data1)
						Resolution(data2)
					}
				})
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
			parent.removeload()
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
}

function EncodeType(data) {
	var arr = ['私有264', '标准h264', '标准mpeg4', '', '', '', '', 'M-JPEG', 'MPEG2', '', 'H.265']
	$('#ms11').find('.li5').find('select').empty().append('<option value="-1" selected>请选择</option>')
	$(data).each(function(a, el) {
		$('#ms11').find('.li5').find('select').append('<option value=' + data[a].videoEncodeType + '>' + arr[parseInt(data[a].videoEncodeType)] + '</option>')
	})
	$('#ms11').find('.li5').find('select').unbind('change')
	$('#ms11').find('.li5').find('select').on('change', function() {
		var v = $('#ms11').find('.li5').find('select>option:selected').val();
		$(data).each(function(b, el) {
			if(data[b].videoEncodeType == v) {
				VideoEncodeEfficiency(data[b].videoEncodeEfficiency)
			}
		})
	})
}

function Resolution(data) {
	$('#ms11').find('.li2').find('select').empty().append('<option value="-1" selected>请选择</option>')
	$(data).each(function(a, el) {
		$('#ms11').find('.li2').find('select').append('<option value=' + data[a].index + '>' + data[a].name + '</option>')
	})
	$('#ms11').find('.li2').find('select').unbind('change')
	$('#ms11').find('.li2').find('select').on('change', function() {
		var v = $('#ms11').find('.li2').find('select>option:selected').val();
		$(data).each(function(b, el) {
			if(data[b].index == v) {
				VideoFrameRate(data[b].videoFrameRate)
			}
		})
	})
}

function VideoEncodeEfficiency(data) {
	var arr = data.split(',')
	var arr1 = ['低', '中', '高']
	$('#ms11').find('.li6').find('select').empty().append('<option value="-1" selected>请选择</option>')
	$(arr).each(function(a, el) {
		$('#ms11').find('.li6').find('select').append('<option value=' + arr[a] + '>' + arr1[parseInt(arr[a])] + '</option>')
	})
}

function VideoFrameRate(data) {
	var arr = data.split(',')
	var arr1 = ['全帧率', '1/16', '1/8', '1/4', '1/2', '1', '2', '4', '6', '8', '10', '12', '16', '20', '15', '18', '22', '25']
	$('#ms11').find('.li3').find('select').empty().append('<option value="-1" selected>请选择</option>')
	$(arr).each(function(a, el) {
		$('#ms11').find('.li3').find('select').append('<option value=' + arr[a] + '>' + arr1[parseInt(arr[a])] + '</option>')
	})
}

function initSelect() {
	$('#ms11').find('.li6').find('select').empty().append('<option value="-1" selected>请选择</option>')
	$('#ms11').find('.li3').find('select').empty().append('<option value="-1" selected>请选择</option>')
}

function updateIPCProxy(v1, v2, v3, v4, v5, v6, type) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = {};
	obj.ipc = { 'deviceId': v1, 's_host': v2, 's_proxy': v3, 's_hostport': v4, 'id': v5, 'type': type }
	obj.id = v6
	parent.loadShow()
	$.ajax({
		url: http + "updateIPCProxy",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				listIPC(active().attr('data-deviceId'))
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				$('.mask').hide()
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
			parent.removeload()
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
}

function updateIPCParam(v1, v2, v4, v5, v6, v7, v8, v9, v10, v11, type) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = { 'tp_name': v11 };
	obj.ipc = { 'deviceId': v1, 's_host': v2, 's_hostport': v4, 'id': v5, 'type': type, 'username': v7, 'password': v8 }
	obj.id = v6;
	obj.s_stream = v9;
	obj.s_power = v10;
	parent.loadShow()
	$.ajax({
		url: http + "updateIPCParam",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				listIPC(active().attr('data-deviceId'))
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				$('.mask').hide()
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
			parent.removeload()
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
}

function setIPCCodeing(v1, v2, v3, v4, v5, v6, v7, v8, v9) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.mapingdeviceId = v1;
	obj.pointEntity = { 'ip': active().attr('data-ip'), 'port': active().attr('data-port') }
	obj.id = v2;
	obj.type = 2;
	obj.cktime = v9;
	if(v3 == 0) {
		obj.videoParamsBean = { 'mainStream': { 'videoEncodeType': v4, 'videoEncodeEfficiency': v5, 'resolution': v6, 'videoFrameRate': v7, 'videoBitrate': v8 } }
	}
	if(v3 == 1) {
		obj.videoParamsBean = { 'sub1Stream': { 'videoEncodeType': v4, 'videoEncodeEfficiency': v5, 'resolution': v6, 'videoFrameRate': v7, 'videoBitrate': v8 } }
	}
	parent.loadShow()
	$.ajax({
		url: http + "setIPCCodeing",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				listIPC(active().attr('data-deviceId'))
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				$('.mask').hide()
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
			parent.removeload()
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
}

function addMainDeviceList() {
	var obj = { 'list': [] }
	var s = true;
	$('#ms12').find('li').each(function(i, el) {
		var obj01 = new Object();
		obj01.deviceId = $('#ms12').find('li').eq(i).find('input').val()
		if(!obj01.deviceId) {
			swal({
				title: '第' + (i + 1) + '条设备号,不能为空',
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#30862B",
				timer: 2000
			});
			s = false
			return
		}
		obj.list.push(obj01)
	})
	if(s == true) {
		parent.loadShow()
		$.ajax({
			url: http + "addMainDeviceList",
			type: "post",
			contentType: "application/json",
			headers: { 'Content-type': 'application/json;charset=UTF-8' },
			data: JSON.stringify(obj),
			cache: false,
			success: function(data) {
				parent.removeload()
				if(data.state == 0) {
					listPoint();
					swal({
						title: data.msg,
						text: "2秒后关闭",
						confirmButtonText: "确定",
						confirmButtonColor: "#30862B",
						timer: 2000
					});
					$('#ms12').hide();
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
				parent.removeload()
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
	}

}
//智能
/*function intelle(id){
	var obj=new Object();
	obj.ckuid=sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
}*/
//预约
function listRule(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.r_type = '1';
	//obj.r_deviceId=active().attr('data-deviceId')
	obj.ctrl_id = active1().attr('data-ctrl_id')
	var aState = 0;
	if(arguments.length == 1) {
		obj.r_id = id
		aState = 1
	}
	parent.loadShow();
	$.ajax({
		url: http + "listRule",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload()
			if(data.state == 0) {
				if(aState == 0) {
					console.log(data);
					$('#ms13').find('table>tbody').empty()
					if(data.object != null) {
						var arr = ['', '启用', '禁用']
						$(data.object).each(function(i, el) {
							$('#ms13').find('table>tbody').append(' <tr> <td> <input data-id=' + data.object[i].r_id + ' type="checkbox"> </td> <td>' + data.object[i].r_name + '</td> <td>' + data.object[i].cycleDay + '</td> <td>' + arr[data.object[i].ruleEnable] + '</td> <td>' + data.object[i].beginTime + '</td> <td>' + data.object[i].endTime + '</td> <td>' + data.object[i].execTime + '</td> <td>' + data.object[i].duration + '</td> <td><span data-id=' + data.object[i].r_id + ' class="edit">编辑</span></td> </tr>')
						})
						$('#ms13').find('.edit').on('click', function() {
							$('#ms13').find('.popupAdd').hide().siblings('.popupList').show()
							listRule($(this).attr('data-id'));
						})
					} else {
						swal({
							title: data.msg,
							text: "2秒后关闭",
							confirmButtonText: "确定",
							confirmButtonColor: "#30862B",
							timer: 2000
						});
						return
					}
				} else {
					$('.saveRoule').attr('data-id', id)
					$('#ms13').find('.popupAdd').show().siblings('.popupList').hide()
					$('#ms13').find('.popupAdd').find('.giveUpRoule').text('放弃修改')
					$('#ms13').find('.popupAdd').find('.pop_tit').html("修改预约规则<span class='close'>X</span>")
					$('.addRoule_pop_ul>li').each(function() {
						if($(this).index() == 0) {
							$(this).find('input').val(data.object[0].r_name)
						}
						if($(this).index() == 1) {
							$(this).find('select>option').each(function() {
								if($(this).val() == data.object[0].ruleEnable) {
									$(this).prop('selected', 'selected')
								}
							})
						}
						if($(this).index() == 2) {
							$(this).find('input').val(data.object[0].cycleDay)
						}
						if($(this).index() == 3) {
							$(this).find('input').val(data.object[0].execTime)
						}
						if($(this).index() == 4) {
							$(this).find('input').val(data.object[0].beginTime)
						}
						if($(this).index() == 5) {
							$(this).find('input').val(data.object[0].endTime)
						}
						if($(this).index() == 6) {
							$(this).find('input').val(data.object[0].duration)
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
			parent.removeload()
			alert('请求失败，请重新尝试')
			return
		}
	});
}

function deleteRuleIds() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	var str_rid = '';
	var s = false;
	$('#ms13').find('table>tbody>tr').each(function() {
		if($(this).find('input').prop('checked')) {
			s = true
			str_rid += $(this).find('input').attr('data-id') + ','
		}
	})
	obj.ids = str_rid.substr(0, str_rid.length - 1)
	if(s == true) {
		parent.loadShow()
		$.ajax({
			url: http + "deleteRuleIds",
			type: "post",
			contentType: "application/json",
			headers: { 'Content-type': 'application/json;charset=UTF-8' },
			data: JSON.stringify(obj),
			cache: false,
			success: function(data) {
				parent.removeload()
				if(data.state == 0) {
					swal({
						title: data.msg,
						text: "2秒后关闭",
						confirmButtonText: "确定",
						confirmButtonColor: "#30862B",
						timer: 2000
					}, function() {
						listRule()
					});

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
				parent.removeload()
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
	} else {
		swal({
			title: '请选择要删除的规则',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#30862B",
			timer: 2000
		});
	}

}

function updateRule(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	if(arguments.length != 0) {
		obj.r_id = id
	}
	var s = true;
	$('.addRoule_pop_ul>li').each(function() {
		if($(this).index() == 0) {
			obj.r_name = $(this).find('input').val();
			if(!obj.r_name) {
				swal({
					title: '预约规则名称不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				s = false
				return
			}
		}
		if($(this).index() == 1) {
			obj.ruleEnable = $(this).find('select>option:selected').val();
		}
		if($(this).index() == 2) {
			obj.cycleDay = $(this).find('input').val();
			if(!obj.cycleDay) {
				swal({
					title: '循环周期不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				s = false
				return
			}
			if(/^[1-9]\d*$/.test(obj.cycleDay) == false) {
				swal({
					title: '循环周期必须为正整数',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				s = false
				return
			}
		}
		if($(this).index() == 3) {
			obj.execTime = $(this).find('input').val();
			if(!obj.execTime) {
				swal({
					title: '执行时间不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				s = false
				return
			}
			if(check(obj.execTime) == false || obj.execTime.length != 8) {
				swal({
					title: '执行时间格式不对',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				s = false
				return
			}
		}
		if($(this).index() == 4) {
			obj.beginTime = $(this).find('input').val();
			if(!obj.beginTime) {
				swal({
					title: '开始时间不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				s = false
				return
			}
			if(/^(?:(?:(?:(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(\/|-)(?:0?2\1(?:29)))|(?:(?:(?:1[6-9]|[2-9]\d)?\d{2})(\/|-)(?:(?:(?:0?[13578]|1[02])\2(?:31))|(?:(?:0?[1,3-9]|1[0-2])\2(29|30))|(?:(?:0?[1-9])|(?:1[0-2]))\2(?:0?[1-9]|1\d|2[0-8])))))\s(?:([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d)$/m.test(obj.beginTime) == false || obj.beginTime.length != 19) {
				swal({
					title: '开始时间格式不对',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				s = false
				return
			}
		}
		if($(this).index() == 5) {
			obj.endTime = $(this).find('input').val();
			if(!obj.endTime) {
				swal({
					title: '结束时间不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				s = false
				return
			}
			if(/^(?:(?:(?:(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(\/|-)(?:0?2\1(?:29)))|(?:(?:(?:1[6-9]|[2-9]\d)?\d{2})(\/|-)(?:(?:(?:0?[13578]|1[02])\2(?:31))|(?:(?:0?[1,3-9]|1[0-2])\2(29|30))|(?:(?:0?[1-9])|(?:1[0-2]))\2(?:0?[1-9]|1\d|2[0-8])))))\s(?:([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d)$/m.test(obj.endTime) == false || obj.endTime.length != 19) {
				swal({
					title: '结束时间格式不对',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				s = false
				return
			}
		}
		if($(this).index() == 6) {
			obj.duration = $(this).find('input').val();
			if(!obj.duration) {
				swal({
					title: '执行时长不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#30862B",
					timer: 2000
				});
				s = false
				return
			}
		}
	})

	if(s == true) {
		obj.r_deviceId = active().attr('data-deviceId')
		obj.ctrl_id = active1().attr('data-ctrl_id')
		parent.loadShow()
		$.ajax({
			url: http + "updateRule",
			type: "post",
			contentType: "application/json",
			headers: { 'Content-type': 'application/json;charset=UTF-8' },
			data: JSON.stringify(obj),
			cache: false,
			success: function(data) {
				parent.removeload()
				if(data.state == 0) {
					swal({
						title: data.msg,
						text: "2秒后关闭",
						confirmButtonText: "确定",
						confirmButtonColor: "#30862B",
						timer: 2000
					}, function() {
						$('#ms13').find('.popupAdd').hide().siblings('.popupList').show()
						listRule()
					});

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
				parent.removeload()
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
	} else {

	}

}

function check(that) {
	var result = /^(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d$/.test(that);
	return result
}

function returnNull(s) {
	var str = ""
	if(s == null || s == '' || s == undefined || !s) {

	} else {
		str = s
	}
	return str
}
window.onload=function() {
	listPointCheck();
	var showVal = getUrlKey('data');
	if(showVal){
		if(showVal=='more'){
				$('.nav_l_morede').parents('.menu_e_list').addClass('choose').siblings().removeClass('choose');
				$('.fir_head_all').text('监测多数据');
				$("#bdequipSelOut").show();
				$(".bdequipSele").hide();
				$('.fieldset').height('1.51rem');
				$('.picHave').css('top','1.85rem');
				var oa=$('.bdequips').height()-$('.fieldset').height();
				if(oa>0){
					$('.picHavedata').show();
				}
				$('.fieldset').scroll(function(){
					if($('.fieldset').scrollTop()-oa>=0){
						$('.picHavedata').hide();
					}else{
						$('.picHavedata').show();
					}
				})
		}else{
				$('.nav_l_de').addClass('choose').parents('.menu_e_list').siblings().find('a').removeClass('choose');
				$('.fir_head_all').text('监测单数据');
				$("#bdequipSelOut").hide();
				$(".bdequipSele").show();
				$('.fieldset').height('2.15rem');
				$('.picHave').css('top','2.6rem');
				var oa=$('.bdequips').height()-$('.fieldset').height();
				if(oa>0){
					$('.picHavedata').show();
				}
				$('.fieldset').scroll(function(){
					if($('.fieldset').scrollTop()-oa>=0){
						$('.picHavedata').hide();
					}else{
						$('.picHavedata').show();
					}
				})
		}
	}
	
	$('.nav_l_morede').click(function() {//多数据分析
		$('#bdequipSelOut').show();
		$('.bdequipSele').hide();
		$('.nav_l_de').removeClass('choose')
		$(this).addClass('choose');
		$('.bdequipselect').attr('datalist', '1');
		$('.bdouterAll').show();
		$('.bdequipshow').hide();
		$('.fir_head_all').text('监测多数据');
		$('.fieldset').height('1.51rem');
		$('.picHave').css('top','1.85rem');
		var oa=$('.bdequips').height()-$('.fieldset').height();
		console.log($('.bdequips').height())
		$('.fieldset').scroll(function(){
			if($('.fieldset').scrollTop()-oa>=0){
				$('.picHavedata').hide();
			}else{
				$('.picHavedata').show();
			}
		})
	})
	$('.nav_l_de').click(function() { //单数据分析
		$('#bdequipSelOut').hide();
		$('.bdequipSele').show();
		$('.nav_l_morede').removeClass('choose')
		$(this).addClass('choose');
		$('.bdequipselect').attr('datalist', '0');
		$('.bdouterAll').show();
		$('.bdequipshow').hide();
		$('.fir_head_all').text('监测单数据');
		$('.fieldset').height('2.15rem');
		$('.picHave').css('top','2.6rem');
		var oa=$('.bdequips').height()-$('.fieldset').height();
		$('.fieldset').scroll(function(){
			if($('.fieldset').scrollTop()-oa>=0){
				$('.picHavedata').hide();
			}else{
				$('.picHavedata').show();
			}
		})
	});
	$('.bdequipselect').click(function() {
		var obj1 = new Object();
		obj1.ckdata='1'
		obj1.deviceList = []; 
		obj1.deviceList = addEqui(); //所选检测器数组
		if(obj1.deviceList.length == 0) {
			swal({
				title: '请选择设备',
				text: "",
				confirmButtonText: "确定",
				confirmButtonColor: "#3366FF",
				timer: 2000
			});
			return
		};
		obj1.channelList=[];
		if($(this).attr('datalist') == 0) { //单选            土壤 co2
			obj1.b  = new Array($('.bdequipSele').find('option:selected').text());
			if(obj1.b.length == 0) {
				swal({
					title: '请选择要分析的指标',
					text: "",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					 closeOnConfirm: false
				});
				return
			};
			
		} else {
			obj1.channelList = channelList(); //多选              土壤 co2
			if(obj1.channelList.length == 0) {
				swal({
					title: '请选择要分析的指标',
					text: "",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					 closeOnConfirm: false
				});
				return
			};
		}
		
		obj1.beginTime = $('.bdequipdataB').val();
		obj1.endTime = $('.bdequipdataE').val();
		if(obj1.beginTime.length != 10) {
			swal({
				title: '日期格式不正确',
				text:'',
				type: "warning",
				confirmButtonText: "确定",
				confirmButtonColor: "#3366FF",
				closeOnConfirm: true
			});
			return
		}
		if(checkTime(obj1.beginTime) == false) {
			swal({
				title: '日期格式不正确',
				text:'',
				confirmButtonText: "确定",
				confirmButtonColor: "#3366FF",
				closeOnConfirm: true
			});
			return
		}
		if(obj1.endTime.length != 10) {
			swal({
				title: '日期格式不正确',
				text: "",
				confirmButtonText: "确定",
				confirmButtonColor: "#3366FF",
				timer: 2000
			});
			return
		}
		if(checkTime(obj1.endTime) == false) {
			swal({
				title: '日期格式不正确',
				text: "",
				confirmButtonText: "确定",
				confirmButtonColor: "#3366FF",
				timer: 2000
			});
			return
		};
		 loadShow();
		$.ajax({
			type: "post",
			url: http + "getStaticData",
			contentType: "application/json",
			headers: { 'Content-type': 'application/json;charset=UTF-8'},
			data: JSON.stringify(obj1),
			cache: false,
			success: function(data) {
				removeload();
				if(!data.success){
					swal({
					title: data.msg,
					text: "",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
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
					window.location.href='./bigDataline.html?m_id=0&targe='+Oids+'&texts='+look+'&value='+title+'&addEqui='+addEqui()+'&beginTime='+$('.bdequipdataB').val()+'&endTime='+$('.bdequipdataE').val()+'&titlesa=单数据分析';
					
				}else{
					$("#mains").hide();
					$('.bdequipshList').show();
					$('.bdequipshList>li').hide();
					var newStr=channelList().join('-');
					window.location.href='./bigDataline.html?m_id=1&selects='+newStr+'&addEqui='+addEqui()+'&channelList='+channelList()+'&beginTime='+$('.bdequipdataB').val()+'&endTime='+$('.bdequipdataE').val()+'&titlesa=多数据分析';
				}
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
				closeOnConfirm: true
			});
			return
		}

	});
});

window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", hengshuping, false);  
  //初始化配置参数      
var calendar = new lCalendar();
	calendar.init({
		'trigger': '#demo1',
		'type': 'date'
	});
var calendar = new lCalendar();
	calendar.init({
		'trigger': '#demo',
		'type': 'date'
	});
	
	
	
	};
 function hengshuping() {  
        if (window.orientation == 90 || window.orientation == -90) {  
           //横屏  
        } else {  
            window.orientation == 90
        }  
    }  
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
	$('.treetwo').each(function() {
		if($(this).find('.checkEquip').prop('checked')) {
			oArr.push($(this).find('.treeleft_stu_m_name').attr('data-deviceId'));
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
				"u_type":sessionStorage.getItem('utype'),
			}),
			success: function(data) {
				removeload();
				if(data.state == 0) {
					var str = "";
					$(data.object).each(function(i, el) {
						str += '<div class="points"><p class="treeleft_head">' + data.object[i].tp_name + '</p>'
						$(data.object[i].rank).each(function(l, el) {
							str += '<ul class="treeleft_second"><li class="treeleft_student"><span class="leftTops"></span><span class="leftLies">' + data.object[i].rank[l].tp_name + '</span><ul class="treeleft_stu_lists">'
							$(data.object[i].rank[l].rank).each(function(y, el) {
								str += '<li class="treeleft_stu_menu"><span class="treeleft_stu_m_lline"></span><div class="treetwo"><img src="./img/treen/online.png" class="treetwoImg"/><input type="checkbox" class="checkEquip"/> <span class="treeleft_stu_m_name" data-tp_id="' +
										data.object[i].rank[l].rank[y].tp_id + '" data-supervisername=" ' + data.object[i].rank[l].rank[y].supervisername +
										'"data-state="' + data.object[i].rank[l].rank[y].state + '" data-producername="' + data.object[i].rank[l].rank[y].producername + '" data-name=" ' + data.object[i].rank[l].rank[y].name + '" data-exportorname=" ' +
										data.object[i].rank[l].rank[y].exportorname + '" data-x="' + data.object[i].rank[l].rank[y].x + '" data-y="' + data.object[i].rank[l].rank[y].x + '" data-deviceId="' +
										data.object[i].rank[l].rank[y].deviceId + '" data-ip="' + data.object[i].rank[l].rank[y].ip + '" data-port="' + data.object[i].rank[l].rank[y].port + '">' +
									data.object[i].rank[l].rank[y].tp_name + '</span></div><ul class="treeleft_stu_cameras">'
							/*	$(data.object[i].rank[l].rank[y].rank).each(function(z, el) {
									str += '<li class="treeleft_stu_camera" class="treeleftHasdate"><span class="fatherLine"></span><span class="treeHasdata" data-ip="'+data.object[i].rank[l].rank[y].rank[z].ip+'" data-deviceId="'
									+data.object[i].rank[l].rank[y].rank[z].deviceId+'" data-tp_id="'+data.object[i].rank[l].rank[y].rank[z].tp_id+'">' + data.object[i].rank[l].rank[y].rank[z].tp_name + '</span></li>'
								})*/
								str += '</ul></li>';
								/*	未显示个数*/
							})
							str += '</ul></li></ul>'
						})
						str += '</div>'
						$(".bdequips").empty().append(str);
						 $('.treetwo').each(function() {
						if($(this).find('.treeleft_stu_m_name').attr('data-state') == 2) { //2不在线
							$(this).find('img').attr('src', './img/shexiang/ico_disconnect.png');
							$(this).find('.treeleft_stu_m_name').addClass('noline').removeClass('online')
						} else {
							$(this).find('img').attr('src', './img/shexiang/ico_device.png');
							$(this).find('.treeleft_stu_m_name').addClass('online').removeClass('noline')
						}
					})
						$('.treeleft_stu_cameras').find('.treeleft_stu_camera:eq(0)').addClass('treeleft_stu_camera_first');
						/*切换摄像头*/
						if($(this).find('.treeleft_stu_m_name').attr('data-state')==2){//2不在线
                    		$(this).find('img').attr('src','./img/shexiang/ico_disconnect.png');
                    	}else{
                    		$(this).find('img').attr('src','./img/shexiang/ico_device.png');
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
						});
						var oa=$('.bdequips').height()-$('.fieldset').height();
						if(oa>0){
							$('.picHavedata').show();
						}
						$('.fieldset').scroll(function(){
							if($('.fieldset').scrollTop()-oa>=0){
								$('.picHavedata').hide();
							}else{
								$('.picHavedata').show();
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
                    closeOnConfirm: true
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
                    closeOnConfirm: true
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
function houtaihaimeiyou(){
	
	
			var len = data.object.length + 1;
					var marRigh = (parseInt($('.menu_eq_list ul li').eq(0).outerWidth()) + parseInt($('.menu_eq_list ul li').eq(0).css('margin-right'))) * len;
	
					$('.menu_eq_list ul').css('width', marRigh + 'px');
					var oTr=marRigh-$(window).innerWidth()
					if(oTr>10){
						$('.toRight').show();
						
					}else{
						$('.toRight').hide();
					}
					$('.menu_eq_list').on('scroll', function() {
						if($('.menu_eq_list').scrollLeft() >= oTr) {
							$('.toRight').hide()
						} else {
							$('.toRight').show()
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

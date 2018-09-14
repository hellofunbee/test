window.onload=function(){
	listPoint();
		$(document).on('click','.treetwo',function(){
			$('.treetwo').css('background-color','#fff');
			$('.treeleft_stu_m_name').removeClass('active');
			$(this).css('background-color','#3366FF');
			$(this).find('.treeleft_stu_m_name').addClass('active');
			$('#fir_pro_menu').hide();
			getMainDeviceInfo();
	});
	//视频
	$(document).on('click','.checkToAndro',function(){
		appAndroid();
	})
/*传感器*/
function active(){
    var state;
    $('.treeleft_stu_m_name').each(function(){
        if($(this).hasClass('active')){
            state=$(this)
        }
    })
    return state
}
//管理信息
function getMainDeviceInfo(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.tp_id=active().attr('data-tp_id');
    obj.deviceId=active().attr('data-deviceId');
  	loadShow();
    $.ajax({
        url: http+"getMainDeviceInfo",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            removeload();
            if(data.state==0){
             $('.mana_lists').find('li.li01 .mana_l_text').text(data.object.groupname)
             $('.mana_lists').find('li.li02 .mana_l_text').text(data.object.sitename)
             $('.mana_lists').find('li.li03 .mana_l_text').text('主设备')
             $('.mana_lists').find('li.li04 .mana_l_text').text(data.object.Softwareversion)
             $('.mana_lists').find('li.li05 .mana_l_text').text(data.object.HardwareVersion)
             $('.mana_lists').find('li.li06 .mana_l_text').text(data.object.supervisername)
             $('.mana_lists').find('li.li07 .mana_l_text').text(data.object.producername)
             $('.mana_lists').find('li.li08 .mana_l_text').text(data.object.exportorname)
             $('.mana_lists').find('li.li09 .mana_l_text').text(data.object.province)
             $('.mana_lists').find('li.li10 .mana_l_text').text(data.object.district)
             $('.mana_lists').find('li.li11 .mana_l_text').text(data.object.city)
            }else if(data.state==2){
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
            } else{
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
        error:function(){
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
					var str = "";
					$(data.object).each(function(i, el) {
						str += '<div class="points"><p class="treeleft_head">' + data.object[i].tp_name + '</p>'
						$(data.object[i].rank).each(function(l, el) {
							str += '<ul class="treeleft_second"><li class="treeleft_student"><span class="leftTops"></span><span class="leftLies">' + data.object[i].rank[l].tp_name + '</span><ul class="treeleft_stu_lists">'
							$(data.object[i].rank[l].rank).each(function(y, el) {
								str += '<li class="treeleft_stu_menu"><span class="treeleft_stu_m_lline"></span><div class="treetwo"><img src="./img/treen/online.png" class="treetwoImg"/><span class="treeleft_stu_m_name" data-tp_id="' +
										data.object[i].rank[l].rank[y].tp_id + '" data-supervisername=" ' + data.object[i].rank[l].rank[y].supervisername +
										'"data-state="' + data.object[i].rank[l].rank[y].state + '" data-producername="' + data.object[i].rank[l].rank[y].producername + '" data-name=" ' + data.object[i].rank[l].rank[y].name + '" data-exportorname=" ' +
										data.object[i].rank[l].rank[y].exportorname + '" data-x="' + data.object[i].rank[l].rank[y].x + '" data-y="' + data.object[i].rank[l].rank[y].x + '" data-deviceId="' +
										data.object[i].rank[l].rank[y].deviceId + '" data-ip="' + data.object[i].rank[l].rank[y].ip + '" data-port="' + data.object[i].rank[l].rank[y].port + '">' +
									data.object[i].rank[l].rank[y].tp_name + '</span></div><ul class="treeleft_stu_cameras">'
								$(data.object[i].rank[l].rank[y].rank).each(function(z, el) {
									str += '<li class="treeleft_stu_camera" style="display:none" class="treeleftHasdate"><span class="fatherLine"></span><span class="treeHasdata" data-ip="'+data.object[i].rank[l].rank[y].rank[z].ip+'" data-deviceId="'
									+data.object[i].rank[l].rank[y].rank[z].deviceId+'" data-tp_id="'+data.object[i].rank[l].rank[y].rank[z].tp_id+'">' + data.object[i].rank[l].rank[y].rank[z].tp_name + '</span></li>'
								})
								str += '</ul></li>';
								/*	未显示个数*/
							})
							str += '</ul></li></ul>'
						})
					/*	str+='<li class="treeleft_stu_last"><span id="">[3号] 王大富 草莓</span></li>'*/
						str += '</div>'
						$(".treeleft").empty().append(str);
						$('.treeleft_stu_cameras').find('.treeleft_stu_camera:eq(0)').addClass('treeleft_stu_camera_first');
						$('.treeleft_second:eq(0)').find('.treeleft_stu_menu:eq(0)').find('.treeleft_stu_m_name').addClass('active');
						$('.treeleft_second:eq(0)').find('.treeleft_stu_menu:eq(0)').find('.treetwo').eq(0).click();
						$('.treeleft_second:eq(0)').find('.treeleft_stu_menu:eq(0)').find('.treeleft_stu_camera_first').find('.treeHasdata').addClass('active').click();
						/*切换摄像头*/
						 $('.treetwo').each(function() {
						if($(this).find('.treeleft_stu_m_name').attr('data-state') == 2) { //2不在线
							$(this).find('img').attr('src', './img/shexiang/ico_disconnect.png');
							$(this).find('.treeleft_stu_m_name').css('color','#bbb')
						} else {
							$(this).find('img').attr('src', './img/shexiang/ico_device.png');
							$(this).find('.treeleft_stu_m_name').css('color','#0066F0')
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
	function appAndroid(){
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
				getDeviceIDiOS(active().parents('.treetwo').siblings('.treeleft_stu_cameras').find('.treeleft_stu_camera').eq(0).attr('data-deviceid'),0,0,0,0);
			}
			if (browser.versions.android) {
				window.AndroidView.showView(active().parents('.treetwo').siblings('.treeleft_stu_cameras').find('.treeleft_stu_camera').eq(0).attr('data-deviceid'),0,0,0,0);
			}
}
}
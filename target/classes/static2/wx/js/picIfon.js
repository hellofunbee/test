//监视点  选择时间写在插件中
var alldata, oindex = 0;
window.onload=function() {
	listPoint();
	$(document).on('click', '.treetwo', function() {
		$('.treetwo').css('background-color','#fff');
		$('.treeHasdata').removeClass('active');
		$('.treeleft_stu_m_name').removeClass('active');
		$('.treeleft_stu_camera').css('background-color','#fff');
		$(this).css('background-color','#3366FF');
		$(this).find('.treeleft_stu_m_name').addClass('active');
		$(this).siblings('.treeleft_stu_cameras').find('.treeleft_stu_camera').eq(0).css('background-color','#3366FF').find('.treeHasdata').addClass('active');
	})
	$(document).on('click', '.treeleft_stu_camera', function() {
		$('.treetwo').css('background-color','#fff');
		$('.treeleft_stu_camera').css('background-color','#fff');
		$(this).parent().siblings('.treetwo').css('background-color','#3366FF');
		$('.treeHasdata').removeClass('active');
		$('.treeleft_stu_m_name').removeClass('active');
		$(this).css('background-color','#3366FF');
		$(this).parent().siblings('.treetwo').find('.treeleft_stu_m_name').addClass('active');
		$(this).find('.treeHasdata').addClass('active');
		$('#fir_pro_menu').hide();
		listIPCPoint01();
	});
	$(document).on('click','.jiafugai',function(){
			$('.pic_co_s_i_eye').removeClass('activeJian');
			var newChan=$(this).parents('.pic_con_list').find('.pic_co_s_i_eye').addClass('activeJian');
			listIPCPointIMG($(this).parents('.pic_con_list').find('.pic_co_s_i_eye').attr('data-monitorid'),1);
		})
//视频
									$(document).on('click','.checkToAndro',function(){
										appAndroid();
									});
/*已经去掉*/
									
									//新添加监视点
									$('#newPoint .pics_picst').click(function(){
										deleteIPId($('#ipcPointId').val());
									});
									$('#newPoint .pics_edit').click(function() {
										addIPCPoint1();
									});
									$(document).on('click', '.pic_co_save', function() {
										var addNum = $('.pic_co_save').attr('data-attr')
										if(addNum == 0) {
												var nums = $('.pic_con_l_out li.pic_con_list').length + 1;
												$('.addpic_coOut').show();
												$('.pic_co_save').attr('data-attr','1');
												$('.pic_con_l_out').css('transform', 'translate3d(0px, 0px, 0px)');
												//保存监视点
												var ip = active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-ip');
												var port = active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-port');
												var obj = new Object();
												obj.ckuid = sessionStorage.getItem('ckuid');
												obj.cksid = sessionStorage.getItem('cksid');
												obj.pointEntity = {};
												obj.pointEntity.ip = active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-ip');
												obj.pointEntity.port = active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-port');
												obj.id=active().parents('.tree2').siblings('p.pinline1').attr('data-tp_id')
												obj.deviceId = active().attr('data-deviceId');
												loadShow();
												$.ajax({
													url: http + "addIPCPoint2",
													type: "post",
													contentType: "application/json",
													headers: { 'Content-type': 'application/json;charset=UTF-8' },
													data: JSON.stringify(obj),
													cache: false,
													success: function(data) {
														removeload();
														var ipcPointId=data.object.ipcPointId;
														var ids=data.object.monitorId;
														var deviceId=active().attr('data-deviceid');
														$('#ipcPointId').val(ipcPointId);
														$('#hideIds').val(ids);
														$('.appSet').click(function(){
															secondClick(deviceId,ids,ip,port);
														})
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
									
												
											} else {
												swal({
													title: '每次只能新建一条',
													text: "2秒后关闭",
													confirmButtonText: "确定",
													confirmButtonColor: "#3366FF",
													timer: 2000
												});
											}
									});
									$(document).on('click', '.pics_cancel', function() {
										$(this).parents('.pic_co_s_info').find('input').hide();
										$(this).parents('.pic_co_s_info').find('.pic_co_s_i_eye').show();
										$(this).parents('.pic_co_s_info').find('.pic_co_s_i_l_l>li').find('span').show();
										$(this).parents('.pic_co_s_info').find('.pics_picst').text('图片集');
										$(this).parents('.pic_co_s_info').find('.pics_visit').show();
										$(this).parents('.pic_co_s_info').find('.pics_edit').text('编辑');
										$(this).hide();
									})
									var calendar = new lCalendar();
										calendar.init({
											'trigger': '#begintime',
											'type': 'time'
										});
									var calendar = new lCalendar();
										calendar.init({
											'trigger': '#endtime',
											'type': 'time'
										});
/*已经去掉*/
	
	//点击当前图片放大   $(".swiper-lazy-preloader")   '.pics_content .swiper-slide-active'
	$(document).on('click','.pics_content .swiper-slide-active',function(){
		var that=$(this);
		if(that.find(".swiper-lazy-preloader").length>0){
			var newSr=that.find(".swiper-lazy-prel_img").attr('src');
			$('.outerDiv').show().find('img').attr('src',newSr)
		}else{
			var newSr=that.find('.swiper-lazy').attr('src');
			$('.outerDiv').show().find('img').attr('src',newSr)
		}
		$('.page_bg').hide()
		$('.outerDiv').show().find('img').attr('src',newSr)
	})
	$("#closeImgOut").click(function(){
		$('.outerDiv').hide();
		$('.page_bg').show();
	});
	
	/*$('.inputAr_s_new').click(function() {
		$('.expertLists').show();
	});*/
};
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
function active3() {
	var state;
	$('.pic_con_l_out .pic_con_list').each(function() {
		if($(this).find('.pics_edit').text() == '保存') {
			state = $(this)
		}
	})
	return state
}
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


/*监视点*/

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
				if(data.object != null) {
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
								'<li class="pic_con_list swiper-slide" data-id=' + (i + 1) + '><div class="pic_co_s_info"><div class="fugai jiafugai"></div><div class="fugai1 jiafugai"></div><p class="clear"><span class="pic_co_s_i_eye fl" data-monitorid="' + data.object[i].monitorId + '">' + data.object[i].monitorName + '</span><input class="pic_co_s_i_e_name monitorName" value="' + data.object[i].monitorName + '" /><span class="pic_co_s_i_num fr">' + x + '/' + num + '</span></p>' +
								'<div class="pic_co_s_i_list clear"><ul class="fl pic_co_s_i_l_l"><li><label>采集日期</label><input class="changesTD" onfocus="this.blur();" id="changesTD'+i+'" value="'+dealdata(data.object[i].createTime)+'" /><label for="changesTD'+i+'" class="changeImg" id="changeImg'+i+'"></label></li><li><label>开始时间</label><span>' + data.object[i].beginTime + '</span><input type="text" name="" class="begintime" id="begintime'+i+'" onfocus="this.blur();" value="' + data.object[i].beginTime + '" /></li>' +
								'<li><label>结束时间</label><span>' + data.object[i].endTime + '</span><input type="text" name="" id="endtime'+i+'" class="endtime" onfocus="this.blur();" value="' + data.object[i].endTime + '" /></li>' +
								'<li><label>时间间隔</label><span>' + data.object[i].rateSecond + '分钟</span><input type="text" name="" class="monitorId" value="' + data.object[i].rateSecond + '"/></li>' +
								'<li><label>采集周期</label><span>' + data.object[i].cycleDay + '天</span><input type="text" name="" class="cycleday" value="' + data.object[i].cycleDay + '"  /></li>' +
								/*'<li><label>状态</label><p style="display:inline">' + texts +'</p></li>'+*/
								'</ul>' +
								/*'<ul class="fr pic_co_s_i_l_r"><li><a href="##" class="pics_picst"  data-monitorId="' + data.object[i].monitorId + '"  data-id="' + data.object[i].id + '" >图片集</a></li><li><a href="##" class="pics_visit" onclick="appAndroid()" data-monitorid="' + data.object[i].monitorId + '">预览</a></li><li><a data-id="' + data.object[i].id + '" href="##" class="pics_edit">编辑</a></li></ul>' +*/
								'</div></div></li>'
							$('.pic_con_l_out').append(str);
							
							var calendar = new lCalendar();
								calendar.init({
									'trigger': '#changesTD'+i,
									'type': 'date',
								});
							
							/*var calendar = new lCalendar();
							calendar.init({
								'trigger': '#begintime'+i,
								'type': 'time'
							});
							var calendar = new lCalendar();
							calendar.init({
								'trigger': '#endtime'+i,
								'type': 'time'
							});*/
							$("#changesTD"+i).change(function(){
								alert()
							})
							
						});
						if(num>1){
								$('.rigntMore').show();
							}
						swiper1(num);
						swiper();
						$('.pic_content').find('.swiper-slide-active').find('.jiafugai').click();
						//已经去掉
												/*$('.pics_edit').click(function() {
													var text = $(this).text();
													if(text == '编辑') {
														$('.pic_co_save').show();
														$(this).parent().siblings('li').find('.pics_visit').hide();
														$(this).parent().siblings('li').find('.pics_picst').text('删除').css({ 'background-color': '#fff', 'border': '1px solid #E2846D', 'color': '#E2846D' });
														$('<a href="##" class="pics_cancel">取消</a>').insertBefore($(this));
														$(this).parents('.pic_con_list').find('.pic_co_s_i_l_l span').hide();
														$(this).parents('.pic_con_list').find('.pic_co_s_i_l_l input').css('display', 'inline-block');
														$(this).text('保存');
														$(this).parents('.pic_con_list').find('.pic_co_s_i_e_name').show();
														$(this).parents('.pic_con_list').find('.pic_co_s_i_eye').text('监视点名称').css({ 'margin-right': '.125rem', 'font-size': '.0875rem', 'width': '.55rem', 'text-align': 'right' });
													} else if(text == '保存') {
														var thisObj = $(this).parents('.pic_con_list');
														updateIPCPoint(thisObj);
													}
												});
												$('.pics_cancel').click(function(){
													$(this).parents('.pic_co_s_info').find('input').hide();
													$(this).parents('.pic_co_s_info').find('.pic_co_s_i_eye').show();
													$(this).parents('.pic_co_s_info').find('.pic_co_s_i_l_l>li').find('span').shoiw();
												})
												if($('.pic_con_list').eq(0).find($('.pics_picst').text == '图片集')) {
													listIPCPointIMG($('.pics_picst').attr('data-monitorid'), 1);
												}*/
												
												
												/*$('.pics_picst').click(function() {
													var text = $(this).text();
													if(text == '删除') {
														var id = $(this).attr('data-id');
														swal({
															title: "您确定要删除该监视点？",
															type: "warning",
															showCancelButton: true,
															closeOnConfirm: false,
															cancelButtonText: '取消',
															confirmButtonText: "确定",
															confirmButtonColor: "#3366FF"
														}, function() {
															deleteIPCPoint(id)
														});
													} else if(text == '图片集') {
														$(this).addClass('active');
														$(this).parents('.pic_con_list').siblings().find('.pics_picst').removeClass('active');
														listIPCPointIMG($(this).attr('data-monitorid'), 1);
													}
												});*/
												
												
												
												/*预览*/
												/*$('.pics_visit').on('click', function() {
													$("#shipin01").contents().find('#preset').val($(this).attr('data-monitorid'));
													$("#shipin01").contents().find('#diaoyong').click();
												})*/
												/*$('.shexiangtoujsdList2>li').eq(0).addClass('active');
												listIPCPointIMG($('.shexiangtoujsdList2>li').eq(0).attr('data-monitorid'), 1);
											
												$('.shexiangtoujsdList2>li').on('click', function() {
													$(this).addClass('active').siblings().removeClass('active')
													listIPCPointIMG($(this).attr('data-monitorid'))
												})*/
						//已经去掉
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
/*每次调用每次新建*/
function swiper1(num){
	var galleryThumbs = new Swiper('.gallery-bottom', {
		//spaceBetween:10,
		slidesPerView:2,
		//slideToClickedSlide: true,
		//centeredSlides: true,
		//freeMode: true，
		initialSlide: 0,
		onSlideNextStart: function(){
			if(num>1){
				if(galleryThumbs.activeIndex < num-1){
					$('.rigntMore').show();
				}else{
					$('.rigntMore').hide();
				}
				if(galleryThumbs.activeIndex > 0){
					$('.picHaveL').show();
				}
			}else{
				$('.rigntMore').hide();
				$('.picHaveL').hide();
			}
		},
		onSlidePrevStart:function(){
			
			if(num>1){
				if(galleryThumbs.activeIndex < num-1){
					$('.rigntMore').show();
				}
				if(galleryThumbs.activeIndex > 0){
					$('.picHaveL').show();
				}else{
					$('.picHaveL').hide();
				}
			}else{
				$('.rigntMore').hide();
				$('.picHaveL').hide();
			};
		},
		//centeredSlides: true,//swiper页面重头开始
		observer: true, //修改swiper自己或子元素时，自动初始化swiper
		observeParents: true, //修改swiper的父元素时，自动初始化swiper*/
//		onTransitionEnd: function(swiper) {
//			
//		
//		}
});
}
function swiper() {
	var galleryTop = new Swiper('.gallery-top', {
		lazyLoading : true,
		lazyLoadingInPrevNext : true,
		lazyLoadingInPrevNextAmount : 2,
		/*autoplay: 3000,*/
		/*loop: true,*/
		 nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        spaceBetween: 10,
	});

 var galleryBoms = new Swiper('.gallery-thumbs', {
        spaceBetween: 10,
        centeredSlides: false,
        slidesPerView: '4',
        onSlideChangeEnd: function(swiper){
            //$('.gallery-thumbs>.swiper-wrapper>div').eq(swiper.activeIndex).css('border','1px solid red')
        },
        touchRatio: 0.2,
        slideToClickedSlide: true
    });
     galleryTop.params.control = galleryBoms;
    galleryBoms.params.control = galleryTop;
 
};
//图片集的图片轮播
function listIPCPointIMG(id,num,houTime) {
	loadShow();
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.app = 1;
	obj.id = active().parents('.treeleft_stu_cameras').siblings('div.treetwo').find('.treeleft_stu_m_name').attr('data-tp_id');
	obj.deviceId = active().attr('data-deviceId');
	obj.monitorId = id;
	obj.start = num;
	if(houTime){
		obj.apptime=houTime
	}
	$.ajax({
		url: http + "listIPCPointIMG",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.state == 0) {
				var str = '<div class="swiper-container gallery-top swiper-container-horizontal"><div class="swiper-wrapper">';
				var strs='<div class="swiper-container gallery-thumbs swiper-container-horizontal"><div class="swiper-wrapper">';
				$(data.object).each(function(i, el) {
					var strImg = "";
					strImg = http + 'getReportImage?file_name=' + data.object[i].fileName;
					var mohuimg=http + 'getReportImage?file_name=' + data.object[i].smallName;
					str += '<div class="swiper-slide swiper-slide-active"><img data-src="' + strImg + '"  class="swiper-lazy" alt=""/><div class="swiper-lazy-preloader"><img class="swiper-lazy-prel_img" src="' + mohuimg + '" alt=""/></div><div ></div></div>'
					strs+='<div class="swiper-slide swiper-slide-active"><img src="' + mohuimg + '" alt=""/></div>'
				});
				str += '</div></div>';
				strs+='</div><div class="swiper-button-next swiper-button-white conpicr"></div><div class="swiper-button-prev swiper-button-white swiper-button-disabled conpicl"></div></div>';
				$('.pics_content').html(str);
				$('.pics_cont_picbot').html(strs);
				swiper();
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
//时间戳
function dealdata(ele) {
	var time = new Date(ele * 1000);
	var n = time.getFullYear();
	var y = time.getMonth() + 1;
	var r = time.getDate();
	var h = time.getHours() < 10 ? "0" + time.getHours() : time.getHours();
	var m = time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes();
	var s = time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds();
	var result = n + '/' + y + '/' + r;
	return result;
}
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
			"u_type":sessionStorage.getItem('utype'),
		}),
		success: function(data) {
			removeload();
			if(data.state == 0) {
				var str = "";var num =0;
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
					})
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
									$(this).parents('.treetwo').siblings('.treeleft_stu_cameras').show().find('.treeleft_stu_camera').eq(0).click();
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
/*摄像头结束*/
/*摄像头*/
	function getUrlKey(name) {
		return decodeURIComponent((
		new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
		};
//新加后台交互
		
		
		
		
		
//已经去掉
function deleteIPId(pot){
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.deviceId = active().attr('data-deviceId');
	obj.pointEntity = { "ip": active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-ip'), "port": active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-port'), "tp_id": active().attr('data-tp_id') };
	obj.id = pot;
	loadShow();
	$.ajax({
		url: http + "deleteIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.success) {
				$('.addpic_coOut').hide();
				$('.pic_co_save').attr('data-attr','0');
				$('#ipcPointId').val('');
				$('#hideIds').val('');
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
//删除监视点
function deleteIPCPoint(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.deviceId = active().attr('data-deviceId');
	obj.pointEntity = { "ip": active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-ip'), "port": active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-port'), "tp_id": active().attr('data-tp_id') };
	obj.id = id;
	loadShow();
	$.ajax({
		url: http + "deleteIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
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
				if($('#hideIds').val()){
					
				}else{
					listIPCPoint01(); /*后台已经删除重新展示*/
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
//添加监视点-验证---不用去掉
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
	loadShow();
	$.ajax({
		url: http + "addIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
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
	obj.id = thisObj.find('.pics_edit').attr('data-id');
	obj.deviceId = active().attr('data-deviceId');
	obj.beginTime = thisObj.find('.begintime').val();
	obj.monitorId = thisObj.attr('data-id');
	obj.monitorName = thisObj.find('.pic_co_s_i_e_name').val();
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
	obj.rateSecond = thisObj.find('.monitorId').val();
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
	loadShow();
	$.ajax({
		url: http + "addIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
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
//保存新建的监视点
function addIPCPoint1(){
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.pointEntity = {};
	obj.pointEntity.ip = active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-ip');
	obj.pointEntity.port = active().parents('.treeleft_stu_cameras').siblings('.treetwo').find('.treeleft_stu_m_name').attr('data-port');
	//obj.id=active().parents('.tree2').siblings('p.pinline1').attr('data-tp_id');
	obj.id =$('#ipcPointId').val();
	obj.monitorId =$('#hideIds').val();
	obj.deviceId = active().attr('data-deviceId');
	obj.beginTime = $('#begintime').val();
	if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test($('#begintime').val()) == false) {
		swal({
			title: '采集开始时间格式不正确',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#3366FF",
			timer: 2000
		});
		return
	}
	obj.endTime = $('#endtime').val();
	if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test($('#endtime').val()) == false) {
		swal({
			title: '采集结束时间格式不正确',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#3366FF",
			timer: 2000
		});
		return
	}
	obj.cycleDay = $('#cycleday').val();
	if(/^[1-9]\d*$/.test($('#cycleday').val()) == false) {
		swal({
			title: '采集周期必须是正整数',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#3366FF",
			timer: 2000
		});
		return
	}
	obj.monitorName = $('#monitorName').val();
	if(!obj.monitorName) {
		swal({
			title: '监视点名称不能为空',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#3366FF",
			timer: 2000
		});
		return
	}
	obj.rateSecond = $('#ratesec').val();
	if(/^[1-9]\d*$/.test($('#ratesec').val()) == false) {
		swal({
			title: '采集时间间隔必须是正整数',
			text: "2秒后关闭",
			confirmButtonText: "确定",
			confirmButtonColor: "#3366FF",
			timer: 2000
		});
		return
	}
	loadShow();
	$.ajax({
		url: http + "addIPCPoint",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.success) {
				swal({
					title: '监视点添加成功',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					timer: 2000
				});
				$('#ipcPointId').val('');
				$('#hideIds').val('');
				$('.pic_co_save').attr('data-attr','0');
				$('.addpic_coOut').find('#monitorName').val('');
				$('.addpic_coOut').find('.changelast_val').find('input').val('');
				$('.addpic_coOut').hide();
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



//与app交互
	function secondClick(deviceId,ids,ip,port){
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
		if(browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
			getDeviceIDiOS(deviceId,ip,port,ids,0);
		}
		if(browser.versions.android) {
			window.AndroidView.showView(deviceId,ip,port,ids,0);
		}
	}
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
				getDeviceIDiOS(active().attr('data-deviceid'),0,0,0,0);
			}
			if (browser.versions.android) {
				window.AndroidView.showView(active().attr('data-deviceid'),0,0,0,0);
			}
}



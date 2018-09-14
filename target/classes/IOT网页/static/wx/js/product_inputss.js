	var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset, generatedCount = 0;
	var configNum = 0;
	var biaozhunarr = ['','有机', '绿色', '无公害'];
	var scrollState = 0;
window.onload=function() {
	var num;
	listPoint();
	listClass1(1,1);
	/* loaded(idas);*/
	$(document).on('click','.inputAr_searNo',function(){
		$('.inputAr_outMore').hide();
	})
	$(document).on('click','.treetwo',function(){
		$('.treetwo').css('background-color','#fff');
		$('.treeleft_stu_m_name').removeClass('active');
		$(this).css('background-color','#3366FF');
		$(this).find('.treeleft_stu_m_name').addClass('active');
		var idas=$(this).find('.treeleft_stu_m_name').attr('data-tp_id');
		loadAction(0,idas,1);
	});
	//正页一级二级分类
	$('.inputAr_se_first').click(function(){
		$('.inputAr_se_fi_list').show();
		$('.inputAr_se_se_list').hide();
	})
	$('.inputAr_se_secon').click(function(){
		$('.inputAr_se_fi_list').hide();
		$('.inputAr_se_se_list').show();
	})
	//搜索
	$('.inputAr_search').on('click', function() {
		$('.inputAr_li_in').empty();
		loadAction(0,active().attr('data-tp_id'),2);
		$('.inputAr_outMore').hide();
	});
	$('.inputAr_s_more').click(function(){
		$('.inputAr_outMore').show();
	});
	$('.inputAr_select').click(function(){
		$(this).parent().next().show();
	});
	var calendar = new lCalendar();
	calendar.init({
		'trigger': '#endTime',
		'type': 'date'
	});
	var calendar = new lCalendar();
	calendar.init({
		'trigger': '#beginTime',
		'type': 'date'
	});
}
//查看投入品记录
/*function loaded(idas) {
	 num=1;
	 pullUpEl = document.getElementById('pullUp');
	pullUpOffset = pullUpEl.offsetHeight;
	myScroll = new iScroll('wrapper', {
		useTransition: true,
		topOffset: pullDownOffset,
		onRefresh: function() {
			if(pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多';
			}
		},
		onScrollMove: function() {
			if(this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '释放刷新';
				this.maxScrollY = this.maxScrollY;
			} else if(this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull up to load more...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function() {
			if(pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中';
				pullUpAction(); 
			}
			num++;
		}
	});
	loadAction(num,idas);
}*/
function active() {
	var state;
	$('.treeleft_stu_m_name').each(function() {
		if($(this).hasClass('active')) {
			state = $(this)
		}
	})
	return state;
}
function loadAction(num,idas,pages){//pages 1 正页    2 辅页
	 var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    if(pages==1){
    	 obj.in_class1=$('.inputAr_se_fi_list').find('li.active').attr('data-id');
    	obj.in_class2=$('.inputAr_se_se_list').find('li.active').attr('data-id');
    }else if(pages==2){
    	 obj.in_class1=$('.inputAr_se_fir').find('li.active').attr('data-id');
    	obj.in_class2=$('.inputAr_se_two').find('li.active').attr('data-id');
    }
    obj.p_begintime=$('.beginTime').val();
    obj.p_endtime=$('.endTime').val();
    obj.start = num;
    obj.app = 1;
    obj.pointEntity=new Object();
    obj.pointEntity.tp_id=idas;
    if(obj.p_begintime){
        if(obj.p_begintime.length!=10){
            swal({
                title: '投放时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#3366FF",
                timer: 2000
            });
            return
        }
        if(checkTime(obj.p_begintime)==false){
            swal({
                title: '投放时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#3366FF",
                timer: 2000
            });
            return
        }
    }else{
        obj.p_begintime='';
    };
    if(obj.p_endtime){
        if(obj.p_endtime.length!=10){
            swal({
                title: '投放时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#3366FF",
                timer: 2000
            });
            return
        }
        if(checkTime(obj.p_endtime)==false){
            swal({
                title: '投放时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#3366FF",
                timer: 2000
            });
            return
        }
    }else{
        obj.p_endtime='';
    }
    loadShow();
    $.ajax({
        url: http+"listInput",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
        	removeload();
            if(data.state==0){
            	$("#fir_pro_menu").hide();
            	$('.inputAr_li_in').empty();
                //if(num1==0){
					$(data.object).each(function(i, el) {//pages 1 正页    2 辅页
						var str='<li><p class="clear"><span class="fl autor">所有者</span><span class="fl list_name"> ' + data.object[i].in_ownername + '</span> <span class="fl"><span style="color:#114D0E"></span> '+ data.object[i].in_parea + '亩</span><span class="times protime fr"><span style="color:#114D0E"></span> ' + data.object[i].in_time + '</span></p>'+
						'<p class="clear"><span class="fl proName">投入物料</span><span class="fl">'+ data.object[i].in_mattername +'</span><span class="fr proVal">'+data.object[i].in_total+'</span></p>'+
						'<p class="clear"><span class="fl proName">排产作物</span><span class="fl">' + data.object[i].in_pname + '</span><span class="fr proVal">'+biaozhunarr[data.object[i].in_pstandrad]+'</span></p>'+
						'</li>'
						$('.inputAr_li_in').append(str);
						
					});
					var a=$(document).height()-$(window).height();
						if(a>0){
							$('.picHave').show();
						}else{
							$('.picHave').hide();
						}
						$(window).on('scroll',function(){
							if(a<=$(document).scrollTop()){
								$('.picHave').hide();
							}else{
								$('.picHave').show()
							}
						})
                   /* myScroll.refresh();*/
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
            }else{
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
        },error:function(){
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
    num++;
}
//上拉加载更多
function pullUpAction() {
	setTimeout(function() {
		alert(12)
		loadAction(num);
		myScroll.refresh();
	}, 400);
}

//查看二级分类
function listClass2Byrid(id, num,page) {//page正页1 副业2
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.c_rid = id;
	obj.c_type = 1;
	loadShow();
	$.ajax({
		url: http + "listClass2Byrid",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.state == 0) {
				/*if(num == 0) {
					$('.mask2').show().siblings('.mask').hide()
					$('.mask2').find('select.s2').empty()
					$(data.object).each(function(i, el) {
						$('.mask2').find('select.s2').append('<option value=' + data.object[i].c_id + '>' + data.object[i].c_name + '</option>')
					})
				}*/
				if(num == 1) {
					if(page==1){//page正页1 副业2
						$('.inputAr_se_se_list').empty().append('<li class="active" data-id="0">二级分类全部</li>');
						$('.inputAr_se_secon').text('二级分类全部');
							$(data.object).each(function(i, el) {
								$('.inputAr_se_se_list').append('<li data-id="' + data.object[i].c_id + '">' + data.object[i].c_name + '</li>')
							});
							$(document).on("click",function(e){  
		       						 var target  = $(e.target);  
								        if(target.closest(".inputAr_se_seconOut").length == 0){  
								        	$('.inputAr_se_se_list').hide();
								        };  
		       					 		e.stopPropagation();  
		   						 });
							$('.inputAr_se_se_list>li').on('click', function() {
									$('.inputAr_se_se_list>li').removeClass('active');
									$(this).addClass('active');
									$(this).parent().siblings('.inputAr_se_secon').text($(this).text());
									loadAction(0,active().attr('data-tp_id'),1)
									$(this).parent().hide();
							});
						
						}else{
							$('.inputAr_se_two').empty().append('<li class="active" data-id="0">全部</li>');
							$('.nomar').text('全部');
							$(data.object).each(function(i, el) {
								$('.inputAr_se_two').append('<li data-id="' + data.object[i].c_id + '">' + data.object[i].c_name + '</li>')
							});
							$(document).on("click",function(e){  
		       						 var target  = $(e.target);  
								        if(target.closest(".inputAr_se_two_bg").length == 0){  
								        	$('.inputAr_se_two').hide();
								        };  
		       					 		e.stopPropagation();  
		   						 });
							$('.inputAr_se_two>li').on('click', function() {
									$('.inputAr_se_two>li').removeClass('active');
									$(this).addClass('active');
									$(this).parent().siblings('.inputAr_selectOut').find('.inputAr_select').text($(this).text());
									$(this).parent().hide();
									//正页联动
									$('.inputAr_se_secon').text($(this).text());
									var that=$(this).text();
									$('.inputAr_se_se_list li').each(function(){
										if($(this).text()==that){
											$(this).addClass('active').siblings().removeClass('active');
										}
									})
								})
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
//一级分类
function listClass1(type, num) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.c_type = type;
	loadShow();
	$.ajax({
		url: http + "listClass1",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.state == 0) {
				if(data.object != null) {
					if(num == 0) {
						/*$('.mask2').show().siblings('.mask').hide()
						$('.mask2').find('select.s1').empty()
						$(data.object).each(function(i, el) {
							$('.mask2').find('select.s1').append('<option value=' + data.object[i].c_id + '>' + data.object[i].c_name + '</option>')
						})*/
						/*setTimeout(function() {
							listClass2Byrid($('.mask2').find('select.s1').find('option:selected').val(), 0)
						}, 50)
						$('.mask2').find('select.s1').on('change', function() {
							listClass2Byrid($('.mask2').find('select.s1').find('option:selected').val(), 0)
						})*/
					}
					if(num == 1) {
						$('.inputAr_se_fir').empty().append('<li class="active" data-id="0">全部</li>')
						$('.inputAr_se_two').empty().append('<li class="active" data-id="0">全部</li>')
						$('.inputAr_se_fi_list').empty().append('<li class="active" data-id="0">一级分类全部</li>')
						$('.inputAr_se_se_list').empty().append('<li class="active" data-id="0">二级分类全部</li>')
						$(data.object).each(function(i, el) {
							$('.inputAr_se_fir').append('<li data-id="'+ data.object[i].c_id +'">' + data.object[i].c_name + '</li>')
							$('.inputAr_se_fi_list').append('<li data-id="'+ data.object[i].c_id +'">' + data.object[i].c_name + '</li>')
							$(data.object[i].list).each(function(j,e){
								$('.inputAr_se_se_list').append('<li data-id="' + data.object[i].list[j].c_id + '">' + data.object[i].list[j].c_name + '</li>');
								$('.inputAr_se_two').append('<li data-id="' + data.object[i].list[j].c_id + '">' + data.object[i].list[j].c_name + '</li>');
							})
						});
   						 //辅页
   						 $(document).on("click",function(e){  
       						 var target  = $(e.target);  
						        if(target.closest(".inputAr_se_fir_bg").length == 0){  
						        	$('.inputAr_se_fir').hide();
						        };  
       					 		e.stopPropagation();  
   						 });
						$('.inputAr_se_fir>li').on('click', function() {//inputAr_se_fir 副页一级   inputAr_se_fi_list 正页一级
							$('.inputAr_se_fir>li').removeClass('active');
							$(this).addClass('active');
							$(this).parent().siblings('.inputAr_selectOut').find('.inputAr_select').text($(this).text());
							//正页联动
							$('.inputAr_se_first').text($(this).text());
							var that=$(this).text();
							$('.inputAr_se_fi_list li').each(function(){
								if($(this).text()==that){
									$(this).click();
								}
							})
							$(this).parent().hide();
							var id = $(this).attr('data-id');
							if(id != 0) {
								listClass2Byrid(id, 1,2);
								listClass2Byrid(id, 1,1);
							} else {
								listClass2Byrid(0, 1,2);
								listClass2Byrid(0, 1,1);
							};
						});
						//副页  二级菜单  inputAr_se_two  inputAr_se_se_list 正页二级
						
						//正页
						//分类-
						$(document).on("click",function(e){  
       						 var target  = $(e.target);  
						        if(target.closest(".inputAr_se_firstOut").length == 0){  
						        	$('.inputAr_se_fi_list').hide();
						        };  
       					 		e.stopPropagation();  
   						 });
   						 //分类二
   						 $(document).on("click",function(e){  
		       						 var target  = $(e.target);  
								        if(target.closest(".inputAr_se_seconOut").length == 0){  
								        	$('.inputAr_se_se_list').hide();
								        };  
		       					 		e.stopPropagation();  
		   						 });
						$('.inputAr_se_fi_list>li').on('click', function() {//inputAr_se_fir 副页一级   inputAr_se_fi_list 正页一级
							$('.inputAr_se_fi_list>li').removeClass('active');
							$(this).addClass('active');
							$(this).parent().siblings('.inputAr_se_first').text($(this).text());
							$(this).parent().hide();
							var id = $(this).attr('data-id');
							loadAction(0,active().attr('data-tp_id'),1)
							if(id != 0) {
								listClass2Byrid(id, 1,1);
							} else {
								listClass2Byrid(0, 1,1);
							};
						});
						//正页  二级菜单
						$('.inputAr_se_se_list li').on('click',function(){
							$('.inputAr_se_se_list li').removeClass('active');
							$(this).addClass('active');
							$(this).parent().siblings('.inputAr_se_secon').text($(this).text());
							$(this).parent().hide();
							loadAction(0,active().attr('data-tp_id'),1)
						})
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
function checkTime(str){
    var state=true;
    var date = str;
    var result = date.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
    if(result===null) {
        state=false;
    }
    return state
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
					var str = "";var num=0
					$(data.object).each(function(i, el) {
						str += '<div class="points"><p class="treeleft_head pinlineHome" data_zoom="'+data.object[i].zoom+'">' + data.object[i].tp_name + '</p>'
						$(data.object[i].rank).each(function(l, el) {
							str += '<ul class="treeleft_second"><li class="treeleft_student"><span class="leftTops"></span><span class="leftLies">' + data.object[i].rank[l].tp_name + '</span><ul class="treeleft_stu_lists">'
							$(data.object[i].rank[l].rank).each(function(y, el) {
								num++;
								str += '<li class="treeleft_stu_menu"><span class="treeleft_stu_m_lline"></span><div class="treetwo"><img src="./img/treen/online.png" class="treetwoImg"/><span class="treeleft_stu_m_name" nums="'+num+'" data-tp_id="' +
										data.object[i].rank[l].rank[y].tp_id + '" data-supervisername=" ' + data.object[i].rank[l].rank[y].supervisername +
										'"data-state="' + data.object[i].rank[l].rank[y].state + '" data-producername="' + data.object[i].rank[l].rank[y].producername + '" data-name=" ' + data.object[i].rank[l].rank[y].name + '" data-exportorname=" ' +
										data.object[i].rank[l].rank[y].exportorname + '" data-x="' + data.object[i].rank[l].rank[y].x + '" data-y="' + data.object[i].rank[l].rank[y].y + '" data-deviceId="' +
										data.object[i].rank[l].rank[y].deviceId + '" data-ip="' + data.object[i].rank[l].rank[y].ip + '" data-port="' + data.object[i].rank[l].rank[y].port + '">' +
									data.object[i].rank[l].rank[y].tp_name + '</span></div><ul class="treeleft_stu_cameras">'
								/*$(data.object[i].rank[l].rank[y].rank).each(function(z, el) {
									str += '<li class="treeleft_stu_camera" class="treeleftHasdate"><span class="fatherLine"></span><span class="treeHasdata" data-ip="'+data.object[i].rank[l].rank[y].rank[z].ip+'" data-deviceId="'
									+data.object[i].rank[l].rank[y].rank[z].deviceId+'" data-tp_id="'+data.object[i].rank[l].rank[y].rank[z].tp_id+'">' + data.object[i].rank[l].rank[y].rank[z].tp_name + '</span></li>'
								})*/
								str += '</ul></li>';
								/*	未显示个数*/
							})
							str += '</ul></li></ul>'
						})
						/*str+='<li class="treeleft_stu_last"><span id="">[3号] 王大富 草莓</span></li>'*/
						str += '</div>'
						$(".treeleft").empty().append(str);
						//$('.treeleft_second').find('.treeleft_stu_menu:eq(0)').find('.treeleft_stu_cameras').find('.treeleft_stu_camera:eq(0)').addClass('treeleft_stu_camera_first');
						/*$('.treeleft_second:eq(0)').find('.treeleft_stu_menu:eq(0)').find('.treeleft_stu_m_name').addClass('active').click();*/
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
						$('.treeleft_second:eq(0)').find('.treeleft_student:eq(0)').find('.treeleft_stu_lists').find('.treeleft_stu_menu:eq(0)').find('.treetwo:eq(0)').click();
						$('.leftLies').on('click', function() {
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
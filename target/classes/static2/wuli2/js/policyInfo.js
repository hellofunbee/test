$(function() {
	//初始化页面
	$('.poli_content').css('min-height',$(window).height());
	listPoint();
		$('.menu_e_list:eq(0)').click(function() {
		listMessage1Bygroup(1);
		listClass1(4);
		$('.fir_head_all').text($(this).find('a').text());
		$('.menu_equip').hide();
		$('.inputAr_s_sear').find('.inputAr_s_search').attr('data-s','0');
	});
	$('.menu_e_list:eq(1)').click(function() {
		listClass1(5);
		listMessage(2);
		$('.fir_head_all').text($(this).find('a').text());
		$('.menu_equip').hide();
		$('.inputAr_s_sear').find('.inputAr_s_search').attr('data-s','1')
		
	});
	$('.menu_e_list:eq(2)').click(function() {
		listClass1(3);
		listMessage(3);
		$('.fir_head_all').text($(this).find('a').text());
		$('.menu_equip').hide();
		$('.inputAr_s_sear').find('.inputAr_s_search').attr('data-s','2')
		
	});
	//gis页面传值
	if(getUrlKey('turnTo')) {
		$('.menu_e_list:eq(1)').click();
			$('.inputAr_s_new').text('即时信息分类');
		/*listClass1(5);
		listMessage(2);
		$('.fir_head_all').text('即时信息');
		$('.menu_equip').hide();
		$('.inputAr_s_sear').find('.inputAr_s_search').attr('data-s', '1');
		$('.menu_e_list').eq(1).addClass('choose').siblings().removeClass('choose');*/
	} else {
		$('.menu_e_list:eq(0)').click();
			$('.inputAr_s_new').text('政策信息分类');
		/*listMessage1Bygroup(1);
		listClass1(4);
		$('.fir_head_all').text('政策信息');
		$('.menu_equip').hide();
		$('.inputAr_s_sear').find('.inputAr_s_search').attr('data-s','0');
		$('.menu_e_list').eq(0).addClass('choose').siblings().removeClass('choose');*/
	};
	//详情页面传值
	if(getUrlKey('home')) {
		var nameTitle=getUrlKey('home');
		if(nameTitle=='政策信息'){
			$('.menu_e_list:eq(0)').click();
			$('.inputAr_s_new').text('政策信息分类');
			/*listMessage1Bygroup(1);
			listClass1(4);
			$('.menu_equip').find('.menu_e_list').eq(0).addClass('choose').siblings('li').removeClass('choose');
			$('.fir_head_all').text('政策信息');
			$('.menu_equip').hide();
			$('.inputAr_s_sear').find('.inputAr_s_search').attr('data-s', '0');*/
		}else if(nameTitle=='即时信息'){
			$('.menu_e_list:eq(1)').click();
			$('.inputAr_s_new').text('即时信息分类');
			/*listClass1(5);
			listMessage(2);
			$('.menu_equip').find('.menu_e_list').eq(1).addClass('choose').siblings('li').removeClass('choose');
			$('.fir_head_all').text('即时信息');
			$('.menu_equip').hide();
			$('.inputAr_s_sear').find('.inputAr_s_search').attr('data-s', '1');*/
		}else if(nameTitle=='预警信息'){
			$('.menu_e_list:eq(2)').click();
			$('.inputAr_s_new').text('预警信息分类');
			/*$('.menu_equip').find('.menu_e_list').eq(2).addClass('choose').siblings('li').removeClass('choose');
			listClass1(3);
			listMessage(3);
			$('.fir_head_all').text('预警信息');
			$('.menu_equip').hide();
			$('.inputAr_s_sear').find('.inputAr_s_search').attr('data-s','2')*/
		}
	}
		$('.menu_equip').hide();
		$('.inputAr_s_new').on('click',function(){
			$('.expertLists').show();
		})
		$(document).on('click',function(e){
			var target=$(e.target);
			if(target.closest(".inputAr_s_new_bg").length == 0){  
				$('.expertLists').hide();
				 };  
		       		e.stopPropagation();
				});
		$('.inputAr_s_search').on('click', function() {
			var str = $(this).siblings('input').val();
			if($(this).attr('data-s')==0){
				if(str) {
					var vals=$('.expertLists').find('.choose').attr('data-val');
					$('.fir_var_listOut').find('.content>li').hide();
					$('.fir_var_listOut').hide();
					$('.fir_var_listOut').each(function(i,el) {
						$('.fir_var_listOut:eq('+i+')').find('.content>li').each(function(j,e){
							if($('.fir_var_listOut:eq('+i+')').find('.content>li:eq('+j+')').find('.fir_var_l_n_t_top').text().indexOf(str) >= 0){
								$('.fir_var_listOut:eq('+i+')').find('.content>li:eq('+j+')').show();
								if(vals!=0){
									if($(this).parents('.fir_var_listOut').attr('dataid')==vals){
										$(this).parents('.fir_var_listOut').show();
									}else{
										$(this).parents('.fir_var_listOut').hide();
									}
								}else{
									$(this).parents('.fir_var_listOut').show();
								}
							}
						})
					})
				} else {
					$('.fir_var_listOut').find('content>li').show();
					$('.fir_var_listOut').show();
				}
			}else if($(this).attr('data-s')==1){
				if(str) {
					$('.poli_content>.poli_co_list').each(function() {
						if($(this).find('.poli_conR_t').text().indexOf(str) >= 0) {
							$(this).show()
						} else {
							$(this).hide()
						}
					})
				} else {
					$('.poli_content>.poli_co_list').show();
				}
			}else if($(this).attr('data-s')==2){
				if(str) {
					$('.poli_content>.poli_co_list').each(function() {
						if($(this).find('.poli_conR_t').text().indexOf(str) >= 0) {
							$(this).show()
						} else {
							$(this).hide()
						}
					})
				} else {
					$('.poli_content>.poli_co_list').show();
				}
			}
		})
})
//时间戳
function dealdata(ele) {
	var time = new Date(ele * 1000);
	var n = time.getFullYear();
	var y = time.getMonth() + 1;
	var r = time.getDate();
	var h = time.getHours() < 10 ? "0" + time.getHours() : time.getHours();
	var m = time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes();
	var s = time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds();
	var result = n + '年' + y + '月' + r + '日';
	return result;
}

////4  政策     5及时 3预警
function listMessage(num) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.m_type = num;
	if(num==2){
        obj.m_class=$('.expertLists').find('.choose').attr('data-val')
    }
    if(num==3){
        obj.m_class=$('.expertLists').find('.choose').attr('data-val')
    }
	$.ajax({
		url: http + "listMessage",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				var str = '';
				$('#nowInfo').empty();
				if(num == 2) {
					for(var i = 0; i < data.object.length; i++) {
						var m_content=data.object[i].m_content;
						if(m_content.length>45){
							m_content=m_content.slice(0,40)+'......'
						}else{
							m_content=m_content
						}
						str += '<div class="poli_co_list"><a class="clear" href="./proDetail.html?m_id='+
							data.object[i].m_id+'&title=即时信息" target="_self"><span class="poli_conL fl">' + dealdata(data.object[i].m_time) +
							'</span><span class="poli_conR  fl"><p class="poli_conR_t">' + data.object[i].m_title +
							'</p><p class="poli_conR_cen">' + m_content + '</p><span class="lookMore">查看详情 &gt;</span></span></a></div>'
					}
					$("#nowInfo").append(str);
					var oa=$(document).height()-$(window).height();
					if(oa>0){
						$('.picHave').show()
					}
					$(document).scroll(function(){
						if($(document).scrollTop()-oa>=0){
							$('.picHave').hide()
						}else{
							$('.picHave').show()
						}
					})
				} else if(num == 3) {
					for(var i = 0; i < data.object.length; i++) {
						var m_content=data.object[i].m_content;
						if(m_content.length>45){
							m_content=m_content.slice(0,40)+'......'
						}else{
							m_content=m_content
						}
						str += '<div class="poli_co_list"><a class="clear" href="./proDetail.html?m_id='+
							data.object[i].m_id+'&title=预警信息"  target="_self" ><span class="poli_conL fl">' + dealdata(data.object[i].m_time) +
							'</span><span class="poli_conR  fl"><p class="poli_conR_t">' + data.object[i].m_title +
							'</p><p class="poli_conR_cen">' + m_content + '</p><span class="lookMore">查看详情 &gt;</span></span></a></div>'
					}
					$("#nowInfo").append(str);
					var oa=$(document).height()-$(window).height();
					if(oa>0){
						$('.picHave').show()
					}
					$(document).scroll(function(){
						if($(document).scrollTop()-oa>=0){
							$('.picHave').hide()
						}else{
							$('.picHave').show()
						}
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
			}
		}
	});
}
//查看政策 
function listMessage1Bygroup(num) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.m_type = num;
	loadShow();
	$.ajax({
		url: http + "listMessage1Bygroup",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload()
			if(data.success) {
				if(num == 1) {
					$("#nowInfo").empty();
					for(var i = 0; i < data.object.length; i++) {
						var str ='<div class="fir_var_listOut" dataid="'+data.object[i].c_id+'"><p class="fir_var_lists" data-type="'+data.object[i].c_id+'">'+ data.object[i].c_name +'</p><ul class="fir_var_l_nums">'
						str += " <ul class='content'>"
						for(var j=0;j<data.object[i].list.length;j++){
							var texts=data.object[i].list[j].m_content;
							var texsts='';
							if(texts.length>45){
								texsts = texts.substr(0,45)+'......'
							}else{
								texsts = texts
							}
                        	str += '<li class="fir_var_l_num"><a class="clear" href="./proDetail.html?m_id='+
							data.object[i].list[j].m_id+'&title=政策信息"  target="_self" ><div class="fir_var_l_n_top clear"><img class="fir_var_l_n_img fl" src="'+data.object[i].list[j].m_cover+'" /><div class="fir_var_l_n_text fl">' +
							'<p class="fir_var_l_n_t_top">' + data.object[i].list[j].m_title + '</p><p class="fir_var_l_n_t_text">' + texsts + '</p>' +
							'</div></div><p class="fir_var_l_user">'+data.object[i].list[j].m_authorname+'</p><div class="clear"><span class="fl fir_var_l_time">' + dealdata(data.object[i].list[j].m_time) + '</span>'+
							'<span class="lookMorezhen fl">了解更多&gt;</span>' +
							'</div></a></li>'
						}
						str += '</ul></div></div>';
						$("#nowInfo").append(str);
					}
					var oa=$(document).height()-$(window).height();
					if(oa>0){
						$('.picHave').show()
					}
					$(document).scroll(function(){
						if($(document).scrollTop()-oa>=0){
							$('.picHave').hide()
						}else{
							$('.picHave').show()
						}
					})
				}
				/*else if(num==4){
				    $("#a4").empty();

				    for(var i=0;i<data.object.length;i++){
				        var str='';
				        str+="<div class='title'>"+data.object[i].c_name+"</div> <div class='top'> <div class='heng fl'></div> <img src='img/lingxing.png' alt=''/> <span >"+data.object[i].c_name+"</span> <img src='img/lingxing.png' alt=''/> <div class='heng fl'></div> </div> <ul class='content con1'>"
				        for(var j=0;j<data.object[i].list.length;j++){
				            str+='<li><div style="background: url('+data.object[i].list[j].m_cover+')no-repeat center;background-size: cover" class="picture"></div><div class="info"><div class="pd fl"><p class="per">'+data.object[i].list[j].m_authorname+'</p><p class="date">'+dealdata(data.object[i].list[j].m_time)+'</p></div><div class="detail fl"><p class="tit">'+data.object[i].list[j].m_title+'</p><p class="read">'+data.object[i].list[j].m_content+'</p></div><div class="more2">了解更多&gt;</div></div></li>'
				        }
				        str+='</ul>';
				        $("#a4").append(str);
				    }
				}*/
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
									str += '<li class="treeleft_stu_camera" class="treeleftHasdate"><span class="fatherLine"></span><span class="treeHasdata" data-ip="'+data.object[i].rank[l].rank[y].rank[z].ip+'" data-deviceId="'
									+data.object[i].rank[l].rank[y].rank[z].deviceId+'" data-tp_id="'+data.object[i].rank[l].rank[y].rank[z].tp_id+'">' + data.object[i].rank[l].rank[y].rank[z].tp_name + '</span></li>'
								})
								str += '</ul></li>';
								/*	未显示个数*/
							})
							str += '</ul></li></ul>'
						})
						str+='<li class="treeleft_stu_last"><span id="">[3号] 王大富 草莓</span></li>'
						str += '</div>'
						$(".treeleft").empty().append(str);
						
						$('.treeleft_stu_cameras').find('.treeleft_stu_camera:eq(0)').addClass('treeleft_stu_camera_first');
						$('.treeleft_second').find('.treeleft_stu_menu:eq(0)').find('.treeleft_stu_m_name').addClass('active');
						$('.treeleft_second').find('.treeleft_stu_menu:eq(0)').find('.treeleft_stu_camera_first').find('.treeHasdata').addClass('active');
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
	 //4  政策     5及时 3预警
function listClass1(num){
    $.ajax({
        url:http + "listClass1",
        type:"post",
        contentType: "application/json",
        cache: false,
        data:JSON.stringify({
            "ckuid": sessionStorage.getItem('ckuid'),
            "cksid": sessionStorage.getItem('cksid'),
            "c_type":num
        }),
        success: function (data) {
            // 渲染页面
            if(data.state==0){
                if(num==4){
                    $('.inputAr_s_new_bg').find('.expertLists').empty();
                    if(data.object==null){
                        $('.inputAr_s_new_bg').find('.expertLists').append("<li>暂无分类</li>")
                    }else {
                    	 $('.inputAr_s_new_bg').find('.expertLists').append("<li data-val='0' class='choose'>政策信息分类</li>")
                        $(data.object).each(function (i, el) {
                            $('.inputAr_s_new_bg').find('.expertLists').append("<li data-val=" + data.object[i].c_id + ">" + data.object[i].c_name + "</li>")
                        });
                    };
                    $('.expertLists li').on('click',function(){
                    		$(this).addClass('choose').siblings('li').removeClass('choose');
                    		$(".inputAr_s_new").text($(this).text());
                    		$(".expertLists").hide();
                    		var vals=$(this).attr('data-val');
                    		$('.inputAr_s_sear').find('input').val('');
                    		$('.fir_var_listOut').find('.content>li').show();
							$('.fir_var_listOut').show();
                    		if(vals==0){
                    			$('.fir_var_listOut').show()
                    		}else{
                    			$('.fir_var_listOut').each(function(){
                    				if($(this).attr('dataid')==vals){
                    					$(this).show().siblings('.fir_var_listOut').hide();
                    				}
                    			})
                    		}
                    });
                    $('.expertLists li:eq('+0+')').click();
                }
                if(num==5){
                    $('.inputAr_s_new_bg').find('.expertLists').empty();
                    if(data.object==null || !data.object){
                        $('.inputAr_s_new_bg').find('.expertLists').append("<li>暂无分类</li>")
                    }else{
                    	 $('.inputAr_s_new_bg').find('.expertLists').append("<li data-val='0' class='choose'>即时信息分类</li>")
                        $(data.object).each(function(i,el){
                            $('.inputAr_s_new_bg').find('.expertLists').append("<li data-val="+data.object[i].c_id+">"+data.object[i].c_name+"</li>")
                        });
                        $('.expertLists li').click(function(){
                        	$(this).addClass('choose').siblings('li').removeClass('choose');
                        	$(".inputAr_s_new").text($(this).text());
                    		$(".expertLists").hide()
                        	$('.inputAr_s_sear').find('input').val('');
                        	listMessage(2)
                        });
                         $('.expertLists li:eq('+0+')').click();
                    }
                }
                if(num==3){
                    $('.inputAr_s_new_bg').find('.expertLists').empty();
                    if(data.object==null){
                        $('.inputAr_s_new_bg').find('.expertLists').append("<li>暂无分类</li>")
                    }else {
                    	 $('.inputAr_s_new_bg').find('.expertLists').append("<li data-val='0' class='choose'>预警信息分类</li>")
                        $(data.object).each(function (i, el) {
                            $('.inputAr_s_new_bg').find('.expertLists').append("<li data-val=" + data.object[i].c_id + ">" + data.object[i].c_name + "</li>")
                        });
                        $('.expertLists li').click(function(){
                        	$(this).addClass('choose').siblings('li').removeClass('choose');
                        	$(".inputAr_s_new").text($(this).text());
                    		$(".expertLists").hide()
                        	$('.inputAr_s_sear').find('input').val('');
                        	listMessage(3)
                        });
                         $('.expertLists li:eq('+0+')').click();
                    };
                }
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
                    window.parent.location.href='./login.html'
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

        },error:function(){
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
    })
}

function getUrlKey(name) {
		return decodeURIComponent((
			new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
		};


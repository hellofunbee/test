
var alldata,confignum=0;
var typeArr=[{img01:'./img/sun.svg',img02:'./img/sun.svg'},{img01:'./img/co2_front.svg',img02:'./img/co2_back.svg'},{img01:'./img/temperature_front.svg',img02:'./img/temperature_back.svg'},{img01:'./img/humidity_pointer.svg',img02:'./img/humidity_dashboard.svg'}]
$(function(){
	listPoint();
	/*初始化页面*/
	$('.allmessage:eq(0)').show();
	/*初始化页面*/
	$(document).on('click', '.treetwo', function() {
		$('.treetwo').css('background-color','#fff');
		$('.treeHasdata').removeClass('active');
		$('.treeleft_stu_m_name').removeClass('active');
		$(this).css('background-color','#3366FF');
		$(this).find('.treeleft_stu_m_name').addClass('active');
		$("#fir_pro_menu").hide();
		if(confignum==0){
            listSensorChartInfo();
            listSensorInfo(0);
		}
	});
	//视频
	$(document).on('click','.checkToAndro',function(){
		appAndroid();
	});
/*	$(document).on('click', '.treeleft_stu_camera', function() {
		$('.treeHasdata').removeClass('active');
		$('.treeleft_stu_m_name').removeClass('active');
		$(this).parent().siblings('.treetwo').find('.treeleft_stu_m_name').addClass('active');
		$(this).find('.treeHasdata').addClass('active');
		$('#fir_pro_menu').hide();
	});*/
		var calendar = new lCalendar();
				calendar.init({
					'trigger': '#selectTime',
					'type': 'date'
				});
/*	$('.btnHistory').click(function(){
			
			
			
		});*/
	$('.btnAlltime').click(function(){
			$('.menu_data_bg').show();
			$('.menu_e_act_out').hide();
			$(".picHave").show();
			$('.rigntMores').hide();
			$(this).hide();
			$('.btnHistory').show()
	})
})
function active(){
    var state;
    $('.treeleft_stu_m_name').each(function(){
        if($(this).hasClass('active')){
            state=$(this)
        }
    })
    return state
}

function active2(){
    var state;
    $('.shexiangtoujsdList2>li').each(function(){
        if($(this).hasClass('active')){
            state=$(this)
        }
    })
    return state
}
/*传感器信息title栏*/
function listSensorChartInfo(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.tp_id=active().attr('data-tp_id');
    obj.deviceId=active().attr('data-deviceId')
    loadShow();
    $.ajax({
        url: http+"listSensorChartInfo",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            removeload()
            if(data.state==0){
                var arrS=['','正常','不正常'];
                $('.menu_e_info_list_l>ul>li').eq(0).find('.menu_e_i_text').text(data.object.info.DeviceId);
                $('.menu_e_info_list_l>ul>li').eq(1).find('.menu_e_i_text').text(data.object.info.name);
                $('.menu_e_info_list_l>ul>li').eq(2).find('.menu_e_i_text').text(data.object.info.supervisername);
                $('.menu_e_info_l_r>ul>li').eq(0).find('.menu_e_i_text').text(arrS[data.object.info.state]);
                $('.menu_e_info_l_r>ul>li').eq(1).find('.menu_e_i_text').text(data.object.info.exportorname);
                $('.menu_data_bg').empty();
                $(data.object.unit).each(function(i,el){
                    var str=''
                    if(data.object.unit[i].chartID==0){
					
                    }
                    //雨量 3  0-100
                    if(data.object.unit[i].chartID==3){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/Rainfall_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/Rainfall_B.svg" alt=""></div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    } 
                    //风向 4  16种
                    if(data.object.unit[i].chartID==4){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/Weathercock_F.svg" alt="" style="z-index: 2;"> <img src="./img/sensor/Weathercock_B.svg" alt=""> </div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    }
                    //风速5 0-32.4m/s
                    if(data.object.unit[i].chartID==5){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/Windspeed_F.svg" alt="" style="z-index: 2;"> <img src="./img/sensor/Windspeed_B.svg" alt=""> </div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    }
                    //na 9 氮肥
                    if(data.object.unit[i].chartID==9){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/N_F.svg" alt="" style="z-index: 2;"> <img src="./img/sensor/N_B.svg" alt=""> </div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    
                    }
                    //ka 10 钾肥
                    if(data.object.unit[i].chartID==10){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/ka_F.svg" alt="" style="z-index: 2;"> <img src="./img/sensor/ka_B.svg" alt=""> </div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    
                    }
                    //co2 11 0-3000
                    if(data.object.unit[i].chartID==11){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/co2_front.svg" alt="" style="z-index: 2;"> <img src="./img/sensor/co2_back.svg" alt=""> </div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    
                    }
                    //o2 12  
                    if(data.object.unit[i].chartID==12){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/o2_B.svg" alt="" style="z-index: 2;"> <img src="./img/sensor/o2_F.svg" alt=""> </div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    
                    }
                    //ph 13
                    if(data.object.unit[i].chartID==13){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/pH_F.svg" alt="" style="z-index: 2;"> <img src="./img/sensor/pH_B.svg" alt=""> </div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    
                    }
                    //空气温度1  土壤温度8  -40~60
                    if(data.object.unit[i].chartID==1 || data.object.unit[i].chartID==8 ){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><div class="bg"></div><img src="./img/sensor/temperature_front.svg" alt="" style="z-index: 2;"> <img src="./img/sensor/temperature_back.svg" alt=""> </div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    
                    }
                    //空气湿度2   土壤水分7  0-100
                    if(data.object.unit[i].chartID==2 || data.object.unit[i].chartID==7){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><img src="./img/sensor/humidity_pointer.svg" alt="" style="z-index: 2;top:.25rem"> <img src="./img/sensor/humidity_dashboard.svg" alt=""></div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    
                    }
                    //光6    0-200000
                    if(data.object.unit[i].chartID==6){
                    	str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+' class="menu_e_listinfo"><p class="menu_e_l_t">'+data.object.unit[i].name+'</p><div class="menu_e_l_pic"><img src="./img/sun.svg" alt="" style="z-index: 2"></div><p class="menu_e_l_b">'+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p></li>'
                    
                    }
                    $('.menu_data_bg').append(str);
                    	a = $(document).height() - $(window).height();
						if(a){
							$(".picHave").show()
						}
						$(window).on('scroll',function(){
							if($(window).scrollTop()>=a){
								$(".picHave").hide()
							}else{
								$(".picHave").show()
							}
						})
                })
                $('.menu_data_bg>li').each(function(i,el){
                	if(i % 2 == 0) { 
						$(this).css('margin-right', "0.05rem");
					}
                	//温度     -40~60
                	 //旋转空气湿度2   土壤水分7 
                    if($(this).attr('data-t')==2 || $(this).attr('data-t')==7){
                        var h=120;
                        var maxh=100;
                        var h1=parseInt($(this).attr('data-num'))
                        var h2=h1*h/maxh;
                        if(h1<=0){
                            h2=0
                        }
                        $(this).find('img').eq(0).rotate({animateTo:h2,duration: 2000});
                    }
                    //雨量
                    if($(this).attr('data-t')==3 || $(this).attr('data-t')==9 || $(this).attr('data-t')==10|| $(this).attr('data-t')==12|| $(this).attr('data-t')==13){
                        var h=120;
                        var maxh=100;
                        var h2;
                        if( parseInt($(this).attr('data-num'))>100){
                            h2=100
                        }else{
                            var h1=Math.abs(parseInt($(this).attr('data-num')))
                            h2=h-h1*h/maxh
                        }
                        $(this).find('div.bg').animate({'height':h2+'px'},2000)
                    };
                      //空气温度1  土壤温度8  -40~60
                    if($(this).attr('data-t')==1|| $(this).attr('data-t')==8){
                    	var h=120;
                        var maxh=100;
                        var h2;
	                    var h1=parseInt($(this).attr('data-num'))+40
	                    h2=h-h1*h/maxh
                        $(this).find('div.bg').animate({'height':h2+'px'},2000)
                    }
                    //co2 11 0-3000
                    if($(this).attr('data-t')==11)	{
                    	var h=120;
                        var maxh=3000;
                        var h2;
	                    var h1=parseInt($(this).attr('data-num'))
	                    h2=h-h1*h/maxh
                        $(this).find('div.bg').animate({'height':h2+'px'},2000)
                    };
                    //风速5 0-32.4m/s
                    if($(this).attr('data-t')==5) {
                    	var h=120;
                        var maxh=32.4;
                        var h2;
	                    var h1=parseInt($(this).attr('data-num'))
	                    h2=h-h1*h/maxh
                        $(this).find('div.bg').animate({'height':h2+'px'},2000)
                    }
                    //风向 4  16种
                    if($(this).attr('data-t')==4){
                    	var h1 = $(this).attr('data-num');
                    	switch(h1)
							{
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
                        $(this).find('img').eq(0).rotate({animateTo:h2,duration: 2000});
                    }
                    
                })
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
            }
        },
        error:function(){
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
//传感器信息表格
function listSensorInfo(num,times){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.tp_id=active().attr('data-tp_id');
    obj.deviceId=active().attr('data-deviceId');
    obj.start=num;
    obj.app=1;
    obj.p_time=times;
    loadShow();
    $.ajax({
        url: http+"listSensorInfo",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
           removeload()
            if(data.state==0){
            	//展现--
            	/*$('.menu_data_bg').hide();
				$(".picHave").hide();
				$('.btnHistory').hide();
				$('.btnAlltime').show();
            	$('.menu_e_act_out').show();
				$('.rigntMores').show();*/
				
               if(data.object!=null){
                	$('.menu_e_actial').empty();
                	var time='<tr>';
                	$(data.object.data).each(function(j,e){
                			var dataList=data.object.data[j];
                            time+='<td class="menu_e_fir_datas">'+getTime(dataList.infoDataTime)+'</td>'
                	});
                	$('.menu_e_actial').append(time+'</tr>')
                	$('.menu_e_act_bg_l').html('<li class="menu_e_fir_td menu_e_fir_times">采集时间</li>');
                	
             	 var arr=[];
             	 var arr1=[];
                	 $(data.object.unit).each(function(a,el){
                        arr.push(data.object.unit[a].fieldName);
                  });
                	$(data.object.unit).each(function(a,el){
                		var litr='';
                		litr+='<li class="menu_e_fir_td">'+data.object.unit[a].name+'</li>';
                		$('.menu_e_act_bg_l').append(litr);
                		var str='<tr>';
                		$(data.object.data).each(function(j,e){
                			var dataList=data.object.data[j];
                            str+='<td>'+dataList[arr[a]]+data.object.unit[a].unit+'</td>'
                		})
                		str+='</tr>'
                		$('.menu_e_actial').append(str);
                	});
                	
                	
                	var leng=$('.menu_e_act_bg').find('.menu_e_fir_td').length;
                	var len=$(data.object.unit).length;
					$('.menu_e_actial>.menu_e_fir_t_fir').css('width',$('.menu_e_fir_td').outWidth+'px')
                    var totalSize=parseInt(data.object.count)
                    var totalPage=parseInt(data.object.totalpage)
                  var top = $('.menu_e_l_top').height()+$('.page_bg').height()+$('.menu_e_act_out').height()/2;
                  $('.rigntMores').css('top',top).show();
                  $('.menu_e_act_bg').scroll(function(){
                  	var lastW=$('.menu_e_actial tr>td').width()*4
                  	if($('.menu_e_act_bg').scrollLeft()+lastW-$('.menu_e_actialout').width()>=0){
                  		$('.rigntMores').hide()
                  	}else{
                  		$('.rigntMores').show()
                  	}
                  });
                  
                  
                  
                }else{
                   swal({
                        title: '暂无信息',
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#3366FF",
                        timer: 2000
                    },function(){
                    	$('.menu_e_act_out').html('暂无信息');
                    });
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


/*传感器信摄像头捕获*/
function  getShopCamera(id){
   loadShow()
    $.ajax({
        url: http+"getShopCamera",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify({"mapingDeviceId":id}),
        cache: false,
        success: function (data) {
            removeload()
            if(data.state==0){
                if(data.object!=null){
                    sessionStorage.setItem('szIP',active().attr('data-ip')),
                      sessionStorage.setItem('szPort',data.object.s_proxy),
                      sessionStorage.setItem('szUsername',data.object.username),
                      sessionStorage.setItem('szPassword',data.object.password);
                    $('#shipin01').attr('src','./demo.html').addClass('fiexleft').removeClass('fiexright').show()
                    setTimeout(function(){
                        $("#shipin01").contents().find('#denglu').click();
                    },300)
                }else{
                    swal({
                        title: '请求失败',
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#3366FF",
                        timer: 2000
                    });
                    return
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
function listIPCPoint02(){
	loadShow()
    $.ajax({
        url: http+"listIPCPoint",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify({ "cksid": sessionStorage.getItem('cksid'),
            "ckuid": sessionStorage.getItem('ckuid'),
            'id':active().attr('data-tp_id'),
            'deviceId':active().attr('data-deviceId')
        }),
        cache: false,
        success: function (data) {
        	 removeload()
            if(data.state==0){
                $('.shexiangtoujsdList1').empty();
                if(data.object.length==0){
                    $('.r_bot6').find('p.ts').show()
                }else{
                    $('.r_bot6').find('p.ts').hide()
                    $(data.object).each(function(i,el){
                        $('.shexiangtoujsdList1').append("<li data-monitorId="+data.object[i].monitorId+">"+data.object[i].monitorName+"</li>")
                    })
                    $('.shexiangtoujsdList1>li').on('click',function(){
                       $(this).addClass('active').siblings().removeClass('active')
                        $("#shipin01").contents().find('#preset').val($(this).attr('data-monitorid'));
                        $("#shipin01").contents().find('#diaoyong').click();
                    })
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
        },error:function(){
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
function picNumber(name,data){
    var num='';
    for(x in data){
        if(x==name){
            num=data[x]
        }
    }
    return num
}
function listPoint() {
	loadShow()
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
					removeload()
					var str = "";var nums=0;
					$(data.object).each(function(i, el) {
						str += '<div class="points"><p class="treeleft_head">' + data.object[i].tp_name + '</p>'
						$(data.object[i].rank).each(function(l, el) {
							str += '<ul class="treeleft_second"><li class="treeleft_student"><span class="leftTops"></span><span class="leftLies">' + data.object[i].rank[l].tp_name + '</span><ul class="treeleft_stu_lists">'
							$(data.object[i].rank[l].rank).each(function(y, el) {
								nums++;
								str += '<li class="treeleft_stu_menu"><span class="treeleft_stu_m_lline"></span><div class="treetwo"><img src="./img/treen/online.png" class="treetwoImg"/><span class="treeleft_stu_m_name" data-tp_id="' +
										data.object[i].rank[l].rank[y].tp_id + '" data-supervisername=" ' + data.object[i].rank[l].rank[y].supervisername +
										'"data-state="' + data.object[i].rank[l].rank[y].state + '" data-producername="' + data.object[i].rank[l].rank[y].producername + '" nums="'+nums+'" data-name=" ' + data.object[i].rank[l].rank[y].name + '" data-exportorname=" ' +
										data.object[i].rank[l].rank[y].exportorname + '" data-x="' + data.object[i].rank[l].rank[y].x + '" data-y="' + data.object[i].rank[l].rank[y].x + '" data-deviceId="' +
										data.object[i].rank[l].rank[y].deviceId + '" data-ip="' + data.object[i].rank[l].rank[y].ip + '" data-port="' + data.object[i].rank[l].rank[y].port + '">' +
									data.object[i].rank[l].rank[y].tp_name + '</span></div><ul class="treeleft_stu_cameras">'
								/*$(data.object[i].rank[l].rank[y].rank).each(function(z, el) {
									str += '<li class="treeleft_stu_camera"  style="display:none" class="treeleftHasdate"><span class="fatherLine"></span><span class="treeHasdata" data-ip="'+data.object[i].rank[l].rank[y].rank[z].ip+'" data-deviceId="'
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
								}
							})
						}else{
							$('.treeleft_second:eq(0)').find('.treeleft_student:eq(0)').find('.treeleft_stu_lists').find('.treeleft_stu_menu:eq(0)').find('.treetwo:eq(0)').find('.treeleft_stu_m_name').click();
						}
						$('.treeleft_stu_cameras').find('.treeleft_stu_camera:eq(0)').addClass('treeleft_stu_camera_first');
						/*切换摄像头*/
						/*active().click();*/
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
	function getTime(Time){
	var dates=new Date(Time);
	var Y=dates.getFullYear();
	var M=dates.getMonth()+1;
		M=M<10?'0'+M:M
	var D=dates.getDate()<10?'0'+dates.getDate():dates.getDate();
	var H=dates.getHours()<10?'0'+dates.getHours():dates.getHours();
	var Mi=dates.getMinutes()<10?'0'+dates.getMinutes():dates.getMinutes();
	var s=dates.getSeconds()<10?'0'+dates.getSeconds():dates.getSeconds();
	var result=Y+'.'+M+'.'+D+'<br/>'+H+':'+Mi+":"+s;
	return result
}
	function getUrlKey(name) {
			return decodeURIComponent((
				new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
		};
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
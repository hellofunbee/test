
var alldata,confignum= 0,addNum=0;
var typeArr=[{img01:'./img/sun.svg',img02:'./img/sun.svg'},{img01:'./img/co2_front.svg',img02:'./img/co2_back.svg'},{img01:'./img/temperature_front.svg',img02:'./img/temperature_back.svg'},{img01:'./img/humidity_pointer.svg',img02:'./img/humidity_dashboard.svg'}]
$(function(){
	listPoint();
	if(getUrlKey('m_id')){
		var ids=getUrlKey('m_id');
		if(ids==1){
			setTimeout(function(){$('.ul_top').find('li').eq(1).click()},60)
		}else if(ids==2){
			setTimeout(function(){$('.ul_top').find('li').eq(2).click()},60)
		}
	}
	$('.datas').each(function(){
		$(this).ionDatePicker({
			lang: 'zh-cn',
			format: 'YYYY-MM-DD'
		});
	});
    $(document).on('click','.pinline3Out',function(){
        $('a.pinline3').removeClass('active');
        $('p.pinline1').removeClass('active');
        $(this).find('.pinline3').addClass('active')
        $(this).parents('.tree2').siblings('.fa2Out').find('.pinline1').addClass('active');
        $("#shipin01").hide().contents().find('#tuichu').click();
        if(confignum==0){
			 $('.mid_info').hide().siblings('.newInfor').show();
        }else if(confignum==1){
            $('.mid_info').show().siblings('.newInfor').hide();
            $('.r_bot2').show().siblings('.r_bot').hide()
            listIPCPoint03()
        }else if(confignum==2){
            $('.mid_info').hide().siblings('.newInfor').hide();
            $('.r_bot6').show().siblings('.r_bot').hide()
            getShopCamera(active().attr('data-deviceId'))
            listIPCPoint02()
        }else if(confignum==3){
        }
    })
    $(document).on('click','.fa2Out',function(){
        $(this).find('.pinline1').addClass('active');
        $(this).parent().parent().siblings('ul').find('p.pinline1').removeClass('active');
        if(confignum==0){
            $('.r_bot1').show().siblings('.r_bot').hide()
            listSensorChartInfo()
            listSensorInfo(1)
        }
        if(confignum==3){
            $('.r_bot5').show().siblings('.r_bot').hide()
            $('.mid_info').hide()
            getMainDeviceInfo()
        }
    })
    $('.ul_top>li').on('click',function(){
        confignum=$(this).index();
        $(this).addClass('choose').siblings().removeClass('choose');
        $("#shipin01").hide().contents().find('#tuichu').click();
        if($(this).index()==0){
            $('.mid_info').hide().siblings('.newInfor').show()
            if(active1()==undefined){

            }else{
                $('.r_bot1').show().siblings('.r_bot').hide()
                listSensorChartInfo()
                listSensorInfo(1)

            }
        }else if($(this).index()==3){
            $('.mid_info').hide().siblings('.newInfor').hide()
            if(active1()==undefined){
            }else{
                $('.r_bot5').show().siblings('.r_bot').hide()
                $('.mid_info').hide().siblings('.newInfor').hide()
                getMainDeviceInfo()
            }
        }else{
            if(active()==undefined){
                $('.r_bot').hide()
            }else{
                if($(this).index()==1){
                    $('.mid_info').show().siblings('.newInfor').hide()
                    $('.r_bot2').show().siblings('.r_bot').hide()
                    listIPCPoint03()
                }else if($(this).index()==2){
                    $('.mid_info').hide()
                    $('.r_bot6').show().siblings('.r_bot').hide()
                    getShopCamera(active().attr('data-deviceId'))
                    listIPCPoint02()
                }else if($(this).index()==3){

                }
            }
        }
    })
  $('.jianshiguanli1').on('click',function(){
      $("#shipin01").show().addClass('fiexright').removeClass('fiexleft');
      $('.r_bot4').show().siblings('.r_bot').hide()
      listIPCPoint01();
      setTimeout(function(){
          $("#shipin01").contents().find('#denglu').click();
      },500)
  })
    $('.jianshiguanli2').on('click',function(){
        $('.ul_top>li').eq(2).click();
        setTimeout(function(){
            $('.jianshiguanli1').click()
        },50)

    })
    setTimeout(function(){
        var right=$(".right").outerHeight();
        var top_h=$(".r_top").outerHeight();
        var nav_h=$(".r_mid").outerHeight();
        $(".r_bot").outerHeight(right-top_h-nav_h)
    },80);
    $(".moni_top").click(function(){
        $(".r_bot4").show().siblings().hide();
    });
    // 点击添加
    $(".save").click(function(){
        if(addNum==0){
            var i=$(".tab2 tbody tr").length;
            var str='<tr id="tr"> <td id="monitorId" style="text-align: center">'+(i+1)+'</td><td>' +
              '<input style="width: 1rem" type="text" id="monitorName"></td>' +
              '<td><input type="text" style="width: 1rem" placeholder="00:00:00" id="begintime" class="begintime"/></td><td><input type="text" style="width: 1rem" placeholder="00:00:00" id="endtime" class="endtime"/></td><td><input type="text" style="width: 1rem" id="ratesec" class="ratesec" placeholder="分钟"/></td><td><input type="text" style="width: 0.68rem" id="cycleday" class="cycleday" placeholder="天（整数）"/></td><td>-</td><td><span class="edit restore">保存</span><span class="deleteDef">删除</span></td></tr>';
            $(".tab2 tbody").append(str);
            	addNum=1;
            $(".tab2 tbody tr").last().find("input").css("border","1px solid #5AA055");
            $('.deleteDef').on('click',function(){
                $(this).parents('tr').remove();
                addNum=0;
            })
            $("#tr .restore").on("click",function(){
                addIPCPoint()
            });
        }else{
            swal({
                title: '每次只能新建一条',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
        }

    })
    $('li.mid_search').on('click',function(){
        listIPCPointIMG(active2().attr('data-monitorid'),1)
    })
});
function swiper(){
    var galleryTop = new Swiper('.gallery-top', {
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        spaceBetween: 10,
    });
    var galleryThumbs = new Swiper('.gallery-thumbs', {
        spaceBetween: 10,
        centeredSlides: true,
        slidesPerView: 'auto',
        onSlideChangeEnd: function(swiper){
            //$('.gallery-thumbs>.swiper-wrapper>div').eq(swiper.activeIndex).css('border','1px solid red')
        },
        touchRatio: 0.2,
        slideToClickedSlide: true
    });
    galleryTop.params.control = galleryThumbs;
    galleryThumbs.params.control = galleryTop;
}
function active(){
    var state;
    $('.pinline3').each(function(){
        if($(this).hasClass('active')){
            state=$(this)
        }
    })
    return state
}
function active1(){
    var state;
    $('.pinline1').each(function(){
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
function listPoint(){
    $.ajax({
        url:http + "listPoint",
        type:"post",
        contentType: "application/json",
        cache: false,
        data:JSON.stringify({
            "ckuid": sessionStorage.getItem('ckuid'),
            "cksid": sessionStorage.getItem('cksid'),
            "tp_pid":0,
            "u_type":sessionStorage.getItem('utype'),
        }),
        success: function (data) {
            if(data.state==0){
                //sessionStorage.setItem('objData',JSON.stringify(data.object))
                var str="";var num=0;
                $(data.object).each(function(i,el){
                    str+="<div class='point'><div class='icon student'> <img src='./img/shipin/ico_home.png' alt=''/> </div> <p class='pinline' style='font-size: 0.12rem'> "+data.object[i].tp_name+"</p>"
                    $(data.object[i].rank).each(function(l,el){
                        str+=" <ul class='navigation' style='font-size: 0.12rem'> <li> <div class='iconOut'> <div class='icon fa1'> <img src='./img/shipin/ico_group.png' alt=''/> </div> <p  class='pinline'>"+data.object[i].rank[l].tp_name+"</p></div>"
                        $(data.object[i].rank[l].rank).each(function(y,el){
                        	num++;
                            str+="<ul class='tree'> <li><div class='fa2Out'> <div class='icon fa2'> <img src='./img/shipin/ico_device.png' alt=''/> </div> <p  class='pinline pinline1' nums="+num+"  data-tp_id="+data.object[i].rank[l].rank[y].tp_id+" data-supervisername="+data.object[i].rank[l].rank[y].supervisername+" data-state="+data.object[i].rank[l].rank[y].state+" data-producername="+data.object[i].rank[l].rank[y].producername+" data-name="+data.object[i].rank[l].rank[y].name+" data-exportorname="+data.object[i].rank[l].rank[y].exportorname+" data-x="+data.object[i].rank[l].rank[y].x+" data-y="+data.object[i].rank[l].rank[y].x+" data-deviceId="+data.object[i].rank[l].rank[y].deviceId+" data-ip="+data.object[i].rank[l].rank[y].ip+" data-port="+data.object[i].rank[l].rank[y].port+">"+data.object[i].rank[l].rank[y].tp_name+"</p> </div><ul class='tree2'> "
                            $(data.object[i].rank[l].rank[y].rank).each(function(z,el){
                                str+="<li class='pinline3Out' id="+data.object[i].rank[l].rank[y].rank[z].tp_id+"><div class='icon fa3'><spna class='border'></spna><img src='./img/camera.svg' alt=''/></div><a href='javascript:;' class='pinline pinline3' data-ip="+data.object[i].rank[l].rank[y].rank[z].ip+" data-deviceId="+data.object[i].rank[l].rank[y].rank[z].deviceId+" data-tp_id="+data.object[i].rank[l].rank[y].rank[z].tp_id+">"+data.object[i].rank[l].rank[y].rank[z].tp_name+"</a></li>"
                            })
                            str+="</ul></li></ul>"
                        })
                        str+=" </li> </ul>"
                    })
                    str+="</div>"
                })
                $(".nav_left").empty().append(str);
                if(getUrlKey('num')!=undefined){
					var nus=getUrlKey('num');
					$('.pinline1').each(function(){
						if($(this).attr('nums')==nus){
							$(this).parents('.fa2Out').click();
							$(this).parents('.fa2Out').siblings('.tree2').show().find('li:eq(0)').click();
						}
					})
				}else{
					$('.navigation:eq(0)').find('li:eq(0)').find('.tree:eq(0)').find('.fa2Out').click();
					$('.navigation:eq(0)').find('li:eq(0)').find('.tree:eq(0)').find('.tree2>li:eq(0)').click();
				}
				
                $('.fa2Out').each(function(){
                    	if($(this).find('.pinline1').attr('data-state')==2){//2不在线
                    		$(this).find('img').attr('src','./img/shipin/ico_disconnect.png');
                    	}else{
                    		$(this).find('img').attr('src','./img/shipin/ico_device.png');
                    	}
                    })
                $('.iconOut').on('click',function(){
                    if($(this).siblings('ul').is(":visible")==false){
                        $(this).siblings('ul').show()
                    }else{
                        $(this).siblings('ul').hide()
                    }
                })
                $('.fa2Out').on('click',function(){
                    if($(this).siblings('ul').is(":visible")==false){
                        $(this).siblings('ul').show()
                    }else{
                        $(this).siblings('ul').hide()
                    }
                })
            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
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
                confirmButtonColor: "#30862B",
                confirmButtonText: "确定",
                closeOnConfirm: false
            });
            return
        }

    })

}
function  getShopCamera(id){
    parent.loadShow()
    $.ajax({
        url: http+"getShopCamera",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify({"mapingDeviceId":id}),
        cache: false,
        success: function (data) {
            parent.removeload()
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
                        confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                });
                return
            }
        },
        error:function(){
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
//传感器信息
function listSensorInfo(num){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.tp_id=active1().attr('data-tp_id')
    obj.deviceId=active1().attr('data-deviceId')
    obj.start=num
    parent.loadShow()
    $.ajax({
        url: http+"listSensorInfo",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.state==0){
                if(data.object.data.length==0){
                    swal({
                        title: '暂无信息',
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#30862B",
                        timer: 2000
                    });
                    return
                }else{
                    var arr=[];
                    $('#sheader').empty().append(" <th>时间</th>")
                    $('#tab01>tbody').empty()
                    $(data.object.unit).each(function(a,el){
                        arr.push(data.object.unit[a].fieldName)
                        $('#sheader').append("<th>"+data.object.unit[a].name+"(单位:"+data.object.unit[a].unit+")</th>")
                    });
                    $(data.object.data).each(function(b,el){
                        var str01="";
                      str01+="<tr><td>"+getTime(data.object.data[b].infoDataTime)+"</td>"
                        var dataList=data.object.data[b]
                        $(arr).each(function(c,el){
                            str01+="<td>"+dataList[arr[c]]+"</td>"
                        })
                        str01+="</tr>"
                        $('#tab01>tbody').append(str01)
                    })
                    var totalSize=parseInt(data.object.count);
                    var totalPage=parseInt(data.object.totalpage);
                    //分页
                    $("#page").remove();
                      $('#parentpage').append(" <div id='page' class='page_div'></div>")

                    $("#page").paging({
                        pageNo:num,
                        totalPage: totalPage,
                        totalSize: totalSize,
                        callback: function(num) {
                            listSensorInfo(num)
                        }
                    });
                }
            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                });
                return
            }
        },
        error:function(){
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
function getTime(Time){
	var dates=new Date(Time);
	var Y=dates.getFullYear();
	var M=dates.getMonth()+1;
	var D=dates.getDate();
	var H=dates.getHours()<10?'0'+dates.getHours():dates.getHours();
	var Mi=dates.getMinutes()<10?'0'+dates.getMinutes():dates.getMinutes();
	var s=dates.getSeconds()<10?'0'+dates.getSeconds():dates.getSeconds();
	var result=Y+'/'+M+'/'+D+'&nbsp;'+H+':'+Mi+":"+s;
	return result
}
function listSensorChartInfo(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.tp_id=active1().attr('data-tp_id')
    obj.deviceId=active1().attr('data-deviceId')
    parent.loadShow()
    $.ajax({
        url: http+"listSensorChartInfo",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.state==0){
                var arrS=['','正常','不正常']
                $('.newInfor>li').eq(0).text("设备ID:"+data.object.info.DeviceId)
                $('.newInfor>li').eq(1).text("所有者姓名:"+data.object.info.name)
                $('.newInfor>li').eq(2).text("监管者姓名:"+data.object.info.supervisername)
                $('.newInfor>li').eq(3).text("指导专家:"+data.object.info.exportorname)
                $('.newInfor>li').eq(4).text("状态:"+arrS[data.object.info.state])
                  $('.sensorAnimation').empty();
                $(data.object.unit).each(function(i,el){
                    var str=''
                    if(data.object.unit[i].chartID==0){

                    }
                    //雨量
                    if(data.object.unit[i].chartID==3){
                        str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+'><div class="bg"></div><img src="./img/sensor/Rainfall_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/Rainfall_B.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    //风向
                    if(data.object.unit[i].chartID==4){
                        str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+'><img src="./img/sensor/Weathercock_F.svg" alt="" style="z-index: 2;width: 50%;height: 50%;left:25%;top:25%;"> <img src="./img/sensor/Weathercock_B.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    //风速
                    if(data.object.unit[i].chartID==5){
                        str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+'><div class="bg"></div><img src="./img/sensor/Windspeed_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/Windspeed_B.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    //na
                    if(data.object.unit[i].chartID==9){
                        str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+'><div class="bg"></div><img src="./img/sensor/N_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/N_B.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    //ka
                    if(data.object.unit[i].chartID==10){
                        str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+'><div class="bg"></div><img src="./img/sensor/ka_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/ka_B.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    //co2
                    if(data.object.unit[i].chartID==11){
                        str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+'><div class="bg"></div><img src="./img/sensor/co2_front.svg" alt="" style="z-index: 2"> <img src="./img/sensor/co2_back.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    //o2
                    if(data.object.unit[i].chartID==12){
                        str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+'><div class="bg"></div><img src="./img/sensor/o2_B.svg" alt="" style="z-index: 2"> <img src="./img/sensor/o2_F.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    //ph
                    if(data.object.unit[i].chartID==13){
                        str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+'><div class="bg"></div><img src="./img/sensor/pH_F.svg" alt="" style="z-index: 2"> <img src="./img/sensor/pH_B.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    //温度
                    if(data.object.unit[i].chartID==1 || data.object.unit[i].chartID==8 ){
                        str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+'> <div class="bg"></div><img src="./img/sensor/temperature_front.svg" alt="" style="z-index: 2"> <img src="./img/sensor/temperature_back.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    //湿度
                    if(data.object.unit[i].chartID==2 || data.object.unit[i].chartID==7){
                        str+='<li data-t='+data.object.unit[i].chartID+' data-num='+picNumber(data.object.unit[i].fieldName,data.object.data[0])+'><img src="./img/sensor/humidity_pointer.svg" alt="" style="z-index: 2;width: 100%;left:0;top:38px;"> <img src="./img/sensor/humidity_dashboard.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    //光
                    if(data.object.unit[i].chartID==6){
                        str+=' <li data-t='+data.object.unit[i].chartID+'><img src="./img/sun.svg" alt=""> <p>'+data.object.unit[i].name+"："+picNumber(data.object.unit[i].fieldName,data.object.data[0])+data.object.unit[i].unit+'</p> </li>'
                    }
                    $('.sensorAnimation').append(str)
                })
                $('.sensorAnimation>li').each(function(){
                    //旋转
                    if($(this).attr('data-t')==2 || $(this).attr('data-t')==7 || $(this).attr('data-t')==4){
                        var h=150;
                        var maxh=100;
                        var h1=parseInt($(this).attr('data-num'))
                        var h2=h1*h/maxh
                        if(h1<=0){
                            h2=0
                        }
                            $(this).find('img').eq(0).rotate({animateTo:h2,duration: 2000});

                    }
                    //雨量
                    if($(this).attr('data-t')==3 || $(this).attr('data-t')==9 || $(this).attr('data-t')==10|| $(this).attr('data-t')==12|| $(this).attr('data-t')==11|| $(this).attr('data-t')==13|| $(this).attr('data-t')==1|| $(this).attr('data-t')==5|| $(this).attr('data-t')==8){
                        var h=150;
                        var maxh=100;
                        var h2;
                        if( parseInt($(this).attr('data-num'))>100){
                            h2=100
                        }else{
                            var h1=Math.abs(parseInt($(this).attr('data-num')))
                            h2=h-h1*h/maxh
                        }
                        $(this).find('div.bg').animate({'height':h2+'px'},2000)
                    }
                    //if($(this).attr('data-t')==1 || $(this).attr('data-t')==8){
                    //    var h=150;
                    //    var maxh=60;
                    //    var h1=parseInt($(this).attr('data-num')-(-30))
                    //    var h2=h-h1*h/maxh
                    //    $(this).find('div.bg').animate({'height':h2+'px'},500)
                    //}


                })
            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                });
                return
            }
        },
        error:function(){
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

//监视点名称
function listIPCPoint01(){
    parent.loadShow()
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
            parent.removeload()
            if(data.state==0){
                $(".tab2 tbody").empty();
                if(data.object.length!=null){
                    for(var i=0;i<data.object.length;i++){
                        alldata=data.object;
                        var success;
                        if(data.object[i].success==1){
                            success="成功"
                        }
                        var str='<tr> <td style="text-align: center">'+(i+1)+'</td><td><input type="text" value='+data.object[i].monitorName+' style="width: 1.5rem" readonly  class="moniname"/></td><td><input type="text" value='+data.object[i].beginTime+' style="width: 1rem"  class="begintime"/></td><td><input type="text" value='+data.object[i].endTime+' style="width: 1rem" readonly class="endtime"/></td><td><input type="text" value="'+data.object[i].rateSecond+'" style="width: 1rem" readonly class="ratesec"/></td><td><input type="text" value="'+data.object[i].cycleDay+'" style="width: 0.68rem" readonly class="cycleday"/></td><td>'+success+'</td><td><span data-id='+data.object[i].id+' data-s="0" class="edit editDetail">编辑</span><span class="preview" data-monitorid='+data.object[i].monitorId+'>预览</span><span data-id='+data.object[i].id+' class="delete">删除</span></td></tr>';
                        $(".tab2 tbody").append(str);
                    }
                    //  删除节点
                    $(".delete").on("click",function(){
                        var id=$(this).attr('data-id')
                        if(confirm("是否删除该监视点")){
                            deleteIPCPoint(id)
                        }
                    });
                    //编辑
                    $('.editDetail').on('click',function(e){
                        e.stopPropagation()
                        if($(this).attr('data-s')==0){
                            $(this).text('保存').attr('data-s',1).parents('tr').find("input").removeAttr("readonly").css('border','1px solid #ccc');
                            return
                        } else{
                            var that=$(this)
                            addIPCPoint1(that)
                        }

                    })
                    $('.preview').on('click',function(){
                        $("#shipin01").contents().find('#preset').val($(this).attr('data-monitorid'));
                        $("#shipin01").contents().find('#diaoyong').click();
                    })

                }else{
                    swal({
                        title: data.msg,
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#30862B",
                        timer: 2000
                    });
                }

            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                });
                return
            }
        },
        error:function(){
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
function listIPCPoint02(){
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
            if(data.state==0){
                $('.shexiangtoujsdList1').empty();
                if(data.object){
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
                }
               
            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
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
                confirmButtonColor: "#30862B",
                confirmButtonText: "确定",
                closeOnConfirm: false
            });
            return
        }
    });
}
function listIPCPoint03(){
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
            if(data.state==0){
                $('.shexiangtoujsdList2').empty();
                if(data.object!=null){
                if(data.object.length==0){
                    $('.r_bot6').find('p.ts').show()
                }else{
                    $('.r_bot6').find('p.ts').hide()
                    $(data.object).each(function(i,el){
                        $('.shexiangtoujsdList2').append("<li data-monitorId="+data.object[i].monitorId+">"+data.object[i].monitorName+"</li>")
                    })
                    $('.shexiangtoujsdList2>li').eq(0).addClass('active');
                    listIPCPointIMG($('.shexiangtoujsdList2>li').eq(0).attr('data-monitorid'),1);
                    $('.shexiangtoujsdList2>li').on('click',function(){
                        $(this).addClass('active').siblings().removeClass('active');
                        listIPCPointIMG($(this).attr('data-monitorid'),1);
                    })
                }
                }
            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                });
                return
            }
        }
    });
}
//图片
function listIPCPointIMG(id,num){
    var beginT=$('#beginIMG').val();
    var endT=$('#endIMG').val();
    if(beginT){
        if(checkTime(beginT)==false || beginT.length!=10){
            swal({
                title: '开始时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }
    }
    if(endT){
        if(checkTime(endT)==false || endT.length!=10){
            swal({
                title: '结束时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }
    }
    parent.loadShow()
    $.ajax({
        url: http+"listIPCPointIMG",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify({"beginTime":beginT,"endTime":endT,"ckuid":sessionStorage.getItem('ckuid'),"cksid":sessionStorage.getItem('cksid'),"id":active().parents('.tree2').siblings('.fa2Out').find('p.pinline1').attr('data-tp_id'), "deviceId":active().attr('data-deviceId'),"monitorId":id,'start':num}),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.state==0){
                $('.gallery-top>.swiper-wrapper').empty();
                $('.gallery-thumbs>.swiper-wrapper').empty();
                if(data.object!=null){
                    $(data.object).each(function(i,el){
                        var strImg="";
                        strImg=http+'getReportImage?file_name='+data.object[i].fileName;
                        smallName= http+'getReportImage?file_name='+data.object[i].smallName;
                        $('.gallery-top>.swiper-wrapper').append(" <div class='swiper-slide swiper-slide-active'> <img src="+strImg+" alt=''> </div>")
                        $('.gallery-thumbs>.swiper-wrapper').append(" <div class='swiper-slide swiper-slide-active'> <img src="+smallName+" alt=''> </div>")
                        swiper();
                        var totalSize=parseInt(data.totalcount)
                        var totalPage=parseInt(data.totalpage)
                        //分页
                        $("#page1").remove();
                        $('#parentpage1').append(" <div id='page1' class='page_div'></div>")

                        $("#page1").paging({
                            pageNo:num,
                            totalPage: totalPage,
                            totalSize: totalSize,
                            callback: function(num) {
                                listIPCPointIMG(active2().attr('data-monitorid'),num)
                            }
                        });

                    })
                }else{
                    swal({
                        title: data.msg,
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#30862B",
                        timer: 2000
                    });
                }

            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                });
                return
            }
        },
        error:function(){
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
//编辑监视点
function addIPCPoint1(that){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.pointEntity={};
    obj.pointEntity.ip=active().parents('.tree2').siblings('.fa2Out').find('p.pinline1').attr('data-ip')
    obj.pointEntity.port=active().parents('.tree2').siblings('.fa2Out').find('p.pinline1').attr('data-port')
    obj.id=that.attr('data-id');
    obj.deviceId=active().attr('data-deviceId')
    obj.beginTime=that.parents('tr').find('td').eq(2).find('input').val();
    if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test(obj.beginTime)==false){
        swal({
            title: '采集开始时间格式不正确',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.endTime=that.parents('tr').find('td').eq(3).find('input').val();
    if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test(obj.endTime)==false){
        swal({
            title: '采集结束时间格式不正确',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.cycleDay=that.parents('tr').find('td').eq(5).find('input').val();
    if(/^[1-9]\d*$/.test(obj.cycleDay)==false){
        swal({
            title: '采集周期必须是正整数',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.monitorName=that.parents('tr').find('td').eq(1).find('input').val();
    if(!obj.monitorName){
        swal({
            title: '监视点名称不能为空',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.monitorId=that.parents('tr').find('td').eq(0).text();
    obj.rateSecond=that.parents('tr').find('td').eq(4).find('input').val();
    if(/^[1-9]\d*$/.test(obj.rateSecond)==false){
        swal({
            title: '采集时间间隔必须是正整数',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    console.log(obj)
    parent.loadShow();
    $.ajax({
        url: http+"addIPCPoint",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.success){
                swal({
                    title: data.msg,
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                listIPCPoint01();
            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                });
                return
            }
        },error:function(){
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
//保存监视点
function addIPCPoint(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.pointEntity={};
    obj.pointEntity.ip=active().parents('.tree2').siblings('.fa2Out').find('p.pinline1').attr('data-ip');
    obj.pointEntity.port=active().parents('.tree2').siblings('.fa2Out').find('p.pinline1').attr('data-port');
    console.log(obj.pointEntity);
    //obj.id=active().parents('.tree2').siblings('p.pinline1').attr('data-tp_id')
    obj.deviceId=active().attr('data-deviceId');
    obj.beginTime=$('#begintime').val();
    if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test($('#begintime').val())==false){
        swal({
            title: '采集开始时间格式不正确',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.endTime=$('#endtime').val();
    if(/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/.test($('#endtime').val())==false){
        swal({
            title: '采集结束时间格式不正确',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.cycleDay=$('#cycleday').val();
    if(/^[1-9]\d*$/.test($('#cycleday').val())==false){
        swal({
            title: '采集周期必须是正整数',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.monitorName=$('#monitorName').val();
    if(!obj.monitorName){
        swal({
            title: '监视点名称不能为空',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.monitorId=$('#monitorId').text();
    obj.rateSecond=$('#ratesec').val();
    if(/^[1-9]\d*$/.test($('#ratesec').val())==false){
        swal({
            title: '采集时间间隔必须是正整数',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    parent.loadShow()
    $.ajax({
        url: http+"addIPCPoint",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload();
            if(data.success){
                swal({
                    title: data.msg,
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                addNum=0;
                listIPCPoint01();
            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                });
                return
            }
        },error:function(){
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
//删除监视点
function deleteIPCPoint(id){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.id=id;
    obj.deviceId=active().attr('data-deviceId');
    obj.pointEntity={"ip":active().parents('.tree2').siblings('.fa2Out').find('p.pinline1').attr('data-ip'),"port":active().parents('.tree2').siblings('.fa2Out').find('p.pinline1').attr('data-port'),"tp_id":active().attr('data-tp_id')};
    $.ajax({
        url: http+"deleteIPCPoint",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.success){
                swal({
                    title: data.msg,
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                listIPCPoint01();
            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
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
                confirmButtonColor: "#30862B",
                confirmButtonText: "确定",
                closeOnConfirm: false
            });
            return
        }
    });
}
//管理信息
function getMainDeviceInfo(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.tp_id=active1().attr('data-tp_id');;
    obj.deviceId=active1().attr('data-deviceId');
    parent.loadShow()
    $.ajax({
        url: http+"getMainDeviceInfo",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.state==0){
             $('.r_bot5').find('li.li01>div').text(data.object.groupname)
             $('.r_bot5').find('li.li02>div').text(data.object.sitename)
             $('.r_bot5').find('li.li03>div').text('主设备')
             $('.r_bot5').find('li.li04>div').text(data.object.Softwareversion)
             $('.r_bot5').find('li.li05>div').text(data.object.HardwareVersion)
             $('.r_bot5').find('li.li06>div').text(data.object.supervisername)
             $('.r_bot5').find('li.li07>div').text(data.object.producername)
             $('.r_bot5').find('li.li08>div').text(data.object.exportorname)
             $('.r_bot5').find('li.li09>div').text(data.object.province)
             $('.r_bot5').find('li.li10>div').text(data.object.district)
             $('.r_bot5').find('li.li11>div').text(data.object.city)
            }else if(data.state==2){
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
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
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                });
                return
            }
        },
        error:function(){
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
function checkTime(str){
    var state=true;
    var date = str;
    var result = date.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
    if(result===null) {
        state=false;
    }
    return state
}
function getUrlKey(name) {
			return decodeURIComponent((
				new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
		};
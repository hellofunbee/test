//电磁阀和电机控制自动控制方式---电机有上升和下降和停止和执行---电磁阀只有开关
window.onload=function(){
var configNum=0;
var biaozhunarr=['','有机','绿色','无公害'];
var oArr=[];
	listPoint();
	$('.radios label').click(function(){
		$(this).addClass('ss').siblings().removeClass('ss');
	})
	var Owidth =Math.ceil($('#moveText').width())
	var oL=parseInt($('.moveInfo').innerWidth());
	var moves=function(){
		if(num < -Owidth){
			num=oL
		}
			num--;
		$('.moveInfo').find('p').css('left',num);
	}
	var num=0;
	var setTime=setInterval(moves,1);
	$('.moveInfo').hover(function(){
		clearInterval(setTime)
	},function(){
		setTime=setInterval(moves,1);
	});
    setTimeout(function(){
        var right=$(".right").outerHeight();
        var top_h=$(".r_top").outerHeight();
        var nav_h=$(".r_mid").outerHeight();
        $(".r_bot").outerHeight(right-top_h-nav_h)
    },80);

	//获取用户的省份 1管理者  2专家  3普通用户
	var users=sessionStorage.getItem('utype');
	
	$('.datas').each(function(){
		$(this).ionDatePicker({
			lang: 'zh-cn',
			format: 'YYYY-MM-DD'
		});
	});
    $('.ul_top>li').on('click',function(){
        $(this).addClass('choose').siblings().removeClass('choose');
        var num=$(this).index();
        configNum=$(this).index();
        if(num==0){
            $('.r_mid').show();
            $('.speack').hide();
            $('.moveInfo').hide();
            $('#iframe1').attr('src','./demo01.html');
            $('.sensorAnimation').show();
            if(active()==undefined){
                $('.r_bot').hide();
            }else{
                $('.r_bot4').show().siblings('.r_bot').hide()
                $('.add_de').hide().siblings('.mid_info').show()
                $("#iframe1").contents().find('#tuichu').click();
                getShopCamera(active1().attr('data-deviceId'));
                listSensorChartInfo();//智能控制中的光照水....
            }
            $('.r_mid>ul.mid_info').show().siblings().hide();
        }else{
            $('.r_mid').show()
            $('#iframe1').attr('src','')
            $('.r_bot1').show().siblings('.r_bot').hide()
            $('.r_mid>ul.mid_info').hide().siblings().show()
            if(num==1){
                $('.search').attr('data-s',1)//投入品管理
                if(users==1){
                
                		$('.addProduct').show();
						$('.addPlain').hide()
                	
				}else{
					$('.addProduct').hide();
					$('.addPlain').hide()
				}
              	 $('.heng').hide().siblings('.finishT').hide().siblings('.beginT').hide().siblings('.tgT').show().siblings('.hengTou').show().siblings('.tgFt').show();
                
                	var str = '';
                	str+='<tr> <th style="text-align: center">生产者姓名</th> <th >投入物料</th> <th>投入品总量</th> <th>排产作物</th> <th>排产标准</th> <th>排产面积(亩)</th> <th>投入时间</th>'
                	if(users==1){
                		str+='<th>操作</th>'
                	}
               	str+='</tr>'
                $('.r_bot1').find('.tab2>thead').empty().append(str);
                //listUser()
                listClass1(1,1)//用在展示以及添加中     一级二级菜单展示
                listInput(1)//投入品记录   默认展示
            }
            if(num==2){
                $('.search').attr('data-s',2)//生产计划管理;
                if(users==1){
					$('.addProduct').hide();
					$('.addPlain').show()
				}else{
					$('.addProduct').hide();
					$('.addPlain').hide()
				}
                $('.heng').show().siblings('.finishT').show().siblings('.beginT').show().siblings('.tgT').hide().siblings('.hengTou').hide().siblings('.tgFt').hide();
                var str='';
                str+='<tr> <th style="text-align: center">所有者姓名</th> <th >排产作物</th> <th>排产品种</th> <th>排产标准</th> <th>排产面积(亩)</th> <th>开始时间</th> <th>结束时间</th>'
                	if(users==1){
                		str+='<th>操作</th>'
                	}
               str+='</tr>'
                $('.r_bot1').find('.tab2>thead').empty().append(str)
                listClass1(1,1)
                listProduce(1,0)//页数--展示默认
            }
            if(num==3){
	            	 $('.r_mid').hide();
	            	 $('.speack').show();
	            	 $('.moveInfo').show();
	            	 $('.sensorAnimation').hide();
	            	 $('#iframe1').attr('src','./demo01.html');
	            	if(active()==undefined){
	               	 	$('.r_bot').hide();
		            }else{
		                $('.r_bot4').show().siblings('.r_bot').hide()
		                $('.add_de').hide().siblings('.mid_info').show()
		                $("#iframe1").contents().find('#tuichu').click();
		                getShopCamera(active1().attr('data-deviceId'));
		            }
            	}
        	}
   		 })
    $('.addProduct').on('click',function(){
        $('.mask1').show().siblings('.mask').hide()
        $('.mask1').find('.addtouruagain').show()
        $('.mask1').find('.pop_tit>p').text('新建投入品使用记录');
        $('.addNews').find('input').val('');
        $('.sureuse').attr('data-id',0);
        listProduce(0,1)//1 是编辑
    })
    $('.addPlain').on('click',function(){
        listClass1(1,0);
        $('.sureproct').attr('dataArr',0);
        $('.mask2').find('.popname').text('新建生产计划');
        $('.mask2').find('input').val('');
    })
    $('.close').on('click',function(){
        $(this).parents('.mask').hide();
    })
    $('.pop_cancel').on('click',function(){
        $(this).parents('.mask').hide()
    });
   // 控温设备
   $('.controlTypeWen>span').click(function(){
   		var test=$(this).text();
   		var oInde=$(this).index();
   		var that=$(this);
   		swal({
            title: '确定更换为'+test+'？',
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#30862B",
            confirmButtonText: "确定",
            closeOnConfirm: true
        },function(){
        	$('.controlTypeWen>span').removeClass('controlActive');
   			that.addClass('controlActive');
        	$('.controlByWen').find('.feiwen').hide();
   			$('.controlByWen').find('.feiwen').eq(oInde).show();
   			//预约
   			if(oInde==0){
   				//预约控制
   			}else if(oInde==1){//智能
   				intellen(1);
   			}
        });
   })
    //非控温设备
	$('.controlTypeNo>span').click(function(){
		var oInde=$(this).index();
		var test=$(this).text();
		var inde=$(this).index();
		var that=$(this);
		swal({
            title: '确定更换为'+test+'？',
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#30862B",
            cancelButtonText: "取消",
            confirmButtonText: "确定",
            closeOnConfirm: false
        },function(){
        	swal("更换成功", "", "success");
        	$('.controlTypeNo>span').removeClass('controlActive');
			that.addClass('controlActive');
        	$('.controlByno').find('.feiwen').hide();
			$('.controlByno').find('.feiwen').eq(inde).show();
			//预约
   			if(oInde==1){
   				setRuleList(1);//预约控制
   			}else if(oInde==2){//智能
   				intellen(1);
   			}
        });
	});
	
//电磁阀控制开关--//电机右侧第一个智能图
	$('.changeState>button:eq(0)').click(function(){
    	controlDev(0);
    });
    $('.changeState>button:eq(1)').click(function(){
    	controlDev(-1);
    	
    });
$(document).on('click','.fa2Out',function(e){
        e.stopPropagation();
        var targetT=$(this).find('.pinline1');
        targetT.addClass('active').attr('data-s',1);
        $(this).parent().parent().siblings('ul').find('.pinline1').removeClass('active').attr('data-s',0)
        $('.r_top').show()
        $('.r_mid').show()
        if(configNum==0){
            $('.r_bot4').show()
            $('.add_de').hide().siblings('.mid_info').show();
            setTimeout(function(){
                listControlSetting();
                listSensorChartInfo()
            },50)
        }else if(configNum==1){
            $('.search').attr('data-s',1);
            if(users==1){
					$('.addProduct').show();
					$('.addPlain').hide()
				}else{
					$('.addProduct').hide();
					$('.addPlain').hide()
				}
            $('.heng').hide().siblings('.finishT').hide().siblings('.beginT').hide().siblings('.tgT').show()
            var str="";
            str+='<tr> <th style="text-align: center">生产者姓名</th> <th >投入物料</th> <th>投入品总量</th> <th>排产作物</th> <th>排产标准</th> <th>排产面积(亩)</th> <th>投入时间</th>'
            $('.r_bot1').find('.tab2>thead').empty().append();
            if(users==1){
                		str+='<th>操作</th>'
                	}
            str+='</tr>';
            listClass1(1,1)
            listInput(1)
        }else if(configNum==2){
            $('.search').attr('data-s',2);
            if(users==1){
					$('.addProduct').hide();
					$('.addPlain').show()
				}else{
					$('.addProduct').hide();
					$('.addPlain').hide()
				}
            $('.heng').show().siblings('.finishT').show().siblings('.beginT').show().siblings('.tgT').hide()
            $('.r_bot1').find('.tab2>thead').empty().append('<tr> <th style="text-align: center">所有者姓名</th> <th >排产作物</th> <th>排产品种</th> <th>排产标准</th> <th>排产面积(亩)</th> <th>开始时间</th> <th>结束时间</th></tr>')
            listClass1(1,1)
            listProduce(1,0)
        }else if(configNum==3){
        	$('.r_bot4').show()
            $('.add_de').hide().siblings('.mid_info').hide();
        	//屏蔽测试按钮
            /*$('.r_mid').hide()
            $('.r_bot5').show().siblings('.r_bot').hide()
            parent.loadShow()
            $.ajax({
                url: http+"controlDevListDefault",
                type: "post",
                contentType: "application/json",
                headers: {'Content-type': 'application/json;charset=UTF-8'},
                data:JSON.stringify({"pointEntity":{"ip":active().attr('data-ip'),"port":active().attr('data-port'),"deviceId":active().attr('data-deviceId')}}),
                cache: false,
                success: function (data) {
                    parent.removeload()
                    if(data.state==0){
                        $('.r_bot5>ul').empty()
                        $(data.object).each(function(i,el){
                            $('.r_bot5>ul').append('<li>'+data.object[i].msg+'</li>')
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
                    }else{
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
            });*/
        }
    })
    $(document).on('click','.pinline3Out',function(e){
        e.stopPropagation()
        if(active()==undefined){
            swal({
                title: '请先选择摄像头对应设备',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }else{
            $('.pinline3').removeClass('active')
            $(this).find('.pinline3').addClass('active')
            $("#iframe1").contents().find('#tuichu').click();
            if(active1()==undefined){

            }else{
                getShopCamera(active1().attr('data-deviceId'))
            }
        }
    })
    $('.addtouruagain').on('click',function(){
        $(this).before("<ul class='pop_ul'>"+$(this).siblings('ul.pop_ul').html()+"</ul>")
    })
    $('.sureuse').on('click',function(){//投入品
        addInput()
    })
    $('.sureproct').on('click',function(){//生产计划
    	var dataArrs=$(this).attr('dataArr');
    	var inID=$(this).attr('dataId');

       addProduce(dataArrs,inID);
    })
    $('.search').on('click',function(){
        if($(this).attr('data-s')==1){
            listInput(1)
        }
        if($(this).attr('data-s')==2){
            listProduce(1,0)
        }
    })
    $('.addcontrol').on('click',function(){
        $('div.list').append(' <ul class="wa_intel fl"> <li>采集周期: <input type="text" placeholder="" value="1" readonly> </li> <li>开始时间: <input class="beginTime" type="text" placeholder="例:2017-01-01"> </li> <li>结束时间: <input class="endTime" type="text" placeholder="例:2017-01-01"> </li> </ul>')
    })

    $('.speack').find('span').on('click',function(){
        if($(this).attr('data-s')==0){
            $("#iframe1").contents().find('#tongdao').click();
            $(this).siblings('img').attr('src','img/intercom_on.gif')
        }else{
            $("#iframe1").contents().find('#stopSpeak').click();
        }
    })
    //执行
    $('.controlWen>span').on('click',function(){
    	$('.controlWen>span').removeClass('nowOn');
    	$(this).addClass('nowOn');
    })
	$('.controlCont>button').on('click',function(){
		var oInde=$(this).parent().siblings('.controlWen').find('.nowOn').index()+1;
		var that=$(this).siblings('input');
			controlDev('',oInde,that);//电机右侧第一个智能图
		})

function active(){
    var state;
    $('.pinline1').each(function(){
        if($(this).hasClass('active')){
            state=$(this)
        }
    })
    return state
}
function active1(){
    var state;
    $('.pinline3').each(function(){
        if($(this).hasClass('active')){
            state=$(this)
        }
    })
    return state
}
function active2(){
    var state;
    $('.mid_info>li').each(function(){
        if($(this).hasClass('choose')){
            state=$(this)
        }
    })
    return state
}
function listControlSetting(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.pointEntity={'deviceId':active().attr('data-deviceId'),'tp_id':active().attr('data-tp_id')}
    $.ajax({
        url: http+"listControlSetting",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.state==0){
                $('.mid_info').empty().append("<li class='choose'>现场监测</li>")
                $(data.object).each(function(i,el){
                    $('.mid_info').append("<li data-ctrl_id="+data.object[i].ctrl_id+" ctrl-type=" + data.object[i].ctrl_type+" data-picType="+data.object[i].ctrl_picturetype+">"+data.object[i].ctrl_name+"</li>")
                })
                $('.con_water').hide()
                $('.mid_info>li').on('click',function(){
                    $(this).addClass('choose').siblings().removeClass('choose');
                    if($(this).index()==0){
                    	$('.controlOut').hide();
                    	$('.sensorAnimation').find('.li1').hide()
                    }else{
                       $('.controlOut').show();
                       $('.sensorAnimation').find('.li1').show();
                        $('.sensorAnimation').show().find('.li1>p').text('智能'+$(this).text()+'状态');
                        getControlDevStatus(active2().attr('data-ctrl_id'),active2().attr('data-pictype'),active2().attr('ctrl-type'));
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
            }else{
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
function getControlDevStatus(id,type,ctrl){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.tp_id=active().attr('data-tp_id')
    obj.ctrl_id=id
    obj.pointEntity={'deviceId':active().attr('data-deviceId'),'ip':active().attr('data-ip'),'port':active().attr('data-port')}
    obj.hbm={}
    parent.loadShow()
    $.ajax({
        url: http+"getControlDevStatus",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.state==0){
            	var oIne;
            	//智能控制
            	$('.wa_intel2').empty();
            	//预约控制
            	listRule();
            	if(ctrl == 1) {//电机       
            		$('.changeState').hide();
            		$('.controlTypeWenOut').show();
            		if(data.object.list[0].state==1){
            			$('.controlCont>input').val(data.object.s_state);
            			$('.controlCont>button').addClass('nowOn');
            			$('.controlWen>span').eq(2).removeClass('nowOn');
            			//开启
            		}else{
            			$('.controlWen>span').eq(2).addClass('nowOn');
            			$('.controlCont>button').removeClass('nowOn');
            		}
            	}else if(ctrl == 2) {//电磁阀
            		$('.changeState').show();
            		$('.controlTypeWenOut').hide();
            	}
            	//默认展示
            	$(data.object.list).each(function(i,el){//state 0自动  1 预约 2 智能
            			if(el.state==1){
            				oIne=i
            				return;
            			}
            		});
            	//控制设备--空光控温控水
            	if(type==1){
                	$('.controlByWen').hide();
                	$('.controlByno').show();
                	$('.controlTypeNo>span').removeClass('controlActive');
                	$('.controlByno').find('.feiwen').hide();
            		$('.controlTypeNo>span').eq(oIne).addClass('controlActive');
            		$('.controlByno').find('.feiwen').eq(oIne).show();
                	if(data.object.s_state!=0){//开
                    	$('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/light_on.svg');
                   }else{
                   	 $('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/light_off.svg');
                   }
                }
            	if(type==2){
                	$('.controlByWen').show();
                	$('.controlByno').hide();
                	$('.controlTypeWen>span').removeClass('controlActive');
                	$('.controlByWen').find('.feiwen').hide();
                	if(oIne==0){
                		$('.controlTypeWen>span').eq(oIne).addClass('controlActive');
            			$('.controlByWen').find('.feiwen').eq(oIne).show();
                	}else if(oIne==2){
                		$('.controlTypeWen>span').eq(oIne-1).addClass('controlActive');
            			$('.controlByWen').find('.feiwen').eq(oIne-1).show();
                	}
                	if(data.object.s_state!=0){ //开
                    		$('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/unfold.svg');
	                   }else{
	                   	 $('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/fold.svg');
	                }
                }else if(type==3){
                	$('.controlByWen').hide();
                	$('.controlByno').show();
                	$('.controlTypeNo>span').removeClass('controlActive');
                	$('.controlByno').find('.feiwen').hide();
            		$('.controlTypeNo>span').eq(oIne).addClass('controlActive');
            		$('.controlByno').find('.feiwen').eq(oIne).show();
                	if(data.object.s_state!=0){//开
                    	$('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/water_on.svg');
                   }else{
                   	 $('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/water_off.svg');
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
                sessionStorage.setItem('szIP',active().attr('data-ip')),
                  sessionStorage.setItem('szPort',data.object.s_proxy),
                  sessionStorage.setItem('szUsername',data.object.username),
                  sessionStorage.setItem('szPassword',data.object.password);
                setTimeout(function(){
                    $("#iframe1").contents().find('#denglu').click();
                },300)

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
//自动控制开和关
function controlDev(num,up,that){
	 var obj = new Object();
	 obj.ruleEntity={};
    obj.hbm={};
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.ctrl_id=active2().attr('data-ctrl_id');
    if(active2().attr('ctrl-type')==1 && up<3){//电机上升下降
    	obj.distanceOrDuration=that.val();		//执行开启度值
	    if(obj.distanceOrDuration==''){
	    	 swal({
	            title: '开启度不能为空',
	            text: "2秒后关闭",
	            confirmButtonText: "确定",
	            confirmButtonColor: "#30862B",
	            timer: 2000
	        });
	        return
	    }
    }else{
    	 obj.distanceOrDuration=num; //-1 开 0 关--电磁阀
    }
    obj.hbm.direction=up;
    obj.tp_id=active().attr('data-tp_id');
    obj.pointEntity={'deviceId':active().attr('data-deviceid'),'ip':active().attr('data-ip'),'port':active().attr('data-port')};
    parent.loadShow();
    $.ajax({
        url: http+"controlDev",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload();
            if(data.state==0){
                swal({
                    title: data.msg,
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000,
                    customClass:'leftss'
                });
                //true
                if(data.object==0){//0 关   -1或大于0开
                	if(active2().attr('data-pictype')==1){
                		 $('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/light_off.svg');
                	}else if(active2().attr('data-pictype')==2){
                		 $('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/fold.svg');
                	}else if(active2().attr('data-pictype')==3){
                		$('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/water_off.svg');
                	}
                }else{
                	if(active2().attr('data-pictype')==1){
                		 $('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/light_on.svg');
                	}else if(active2().attr('data-pictype')==2){
                		 $('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/unfold.svg');
                	}else if(active2().attr('data-pictype')==3){
                		$('.sensorAnimation>li.li1').find('img').attr('src','./img/sensor/water_on.svg');
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
            }else{
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
//预约控制
function setRuleList(num){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.ctrl_id=active2().attr('data-ctrl_id')
    //obj.r_name=''
    //obj.r_deviceId=active().attr('data-deviceId')
    //obj.pointEntity={'ip':active().attr('data-ip'),'port':active().attr('data-port'),'deviceId':active().attr('data-deviceId')}
    obj.type=num;
    parent.loadShow();
    $.ajax({
        url: http+"setRuleList",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.state==0){
                swal({
                    title: data.msg,
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                },function(){
                    active2().click();
                });
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
            }else{
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
//智能
function intellen(num){
	 var obj = new Object();//{"ctrl_id":"5","type":"1"(type为1启动,type为2是停止)}
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.ctrl_id=active2().attr('data-ctrl_id')
    obj.type=num;
    obj.mo_deviceId=active().attr('data-deviceid');
    parent.loadShow();
    $.ajax({
        url: http+"setMonitorList",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.state==0){
                swal({
                    title: data.msg,
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                });
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
            }else{
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
                var str="";
                $(data.object).each(function(i,el){
                    str+="<div class='point'><div class='icon student'> <img src='./img/shipin/ico_home.png' alt=''/> </div> <p class='pinline' style='font-size: 0.12rem'> "+data.object[i].tp_name+"</p>"
                    $(data.object[i].rank).each(function(l,el){
                        str+=" <ul class='navigation' style='font-size: 0.12rem'> <li> <div class='iconOut'><div class='icon fa1'> <img src='./img/shipin/ico_group.png' alt=''/> </div> <p  class='pinline'>"+data.object[i].rank[l].tp_name+"</p></div>"
                        $(data.object[i].rank[l].rank).each(function(y,el){
                            str+="<ul class='tree'> <li> <div class='fa2Out'><div class='icon fa2'> <img src='./img/shipin/ico_device.png' alt=''/> </div> <p  class='pinline pinline1' data-tp_id="+data.object[i].rank[l].rank[y].tp_id+" data-supervisername="+data.object[i].rank[l].rank[y].supervisername+" data-state="+data.object[i].rank[l].rank[y].state+" data-producername="+data.object[i].rank[l].rank[y].producername+" data-name="+data.object[i].rank[l].rank[y].name+" data-exportorname="+data.object[i].rank[l].rank[y].exportorname+" data-x="+data.object[i].rank[l].rank[y].x+" data-y="+data.object[i].rank[l].rank[y].x+" data-deviceId="+data.object[i].rank[l].rank[y].deviceId+" data-ip="+data.object[i].rank[l].rank[y].ip+" data-port="+data.object[i].rank[l].rank[y].port+">"+data.object[i].rank[l].rank[y].tp_name+"</p></div> <ul class='tree2'> "
                            $(data.object[i].rank[l].rank[y].rank).each(function(z,el){
                                str+="<li class='pinline3Out' id="+data.object[i].rank[l].rank[y].rank[z].tp_id+"><div class='icon fa3'><spna class='border'></spna><img src='./img/camera.svg' alt=''/></div><a href='javascript:;' class='pinline pinline3' data-deviceId="+data.object[i].rank[l].rank[y].rank[z].deviceId+">"+data.object[i].rank[l].rank[y].rank[z].tp_name+"</a></li>"
                            })
                            str+="</ul></li></ul>"
                        })
                        str+=" </li> </ul>"
                    })
                    str+="</div>"
                })
                $(".nav_left").empty().append(str);
                $('.navigation:eq(0)').find('li:eq(0)').find('.tree:eq(0)').find('.fa2Out').click();
				$('.navigation:eq(0)').find('li:eq(0)').find('.tree:eq(0)').find('.tree2>li:eq(0)').click();
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
                return false
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
//查看投入品记录
function listInput(num){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.in_class1=$('#classify3').find('option:selected').val()
    obj.in_class2=$('#classify4').find('option:selected').val()
    obj.p_begintime=$('.tgT').val();
    obj.p_endtime=$('.tgFt').val();
    obj.pointEntity=new Object();
    obj.pointEntity.tp_id=active().attr('data-tp_id');
    if(obj.p_endtime){
        if(obj.p_endtime.length!=10){
            swal({
                title: '投放时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }
        if(checkTime(obj.p_endtime)==false){
            swal({
                title: '投放时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }
    }else{
        obj.p_endtime=''
    }
    if(obj.p_begintime){
        if(obj.p_begintime.length!=10){
            swal({
                title: '投放时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }
        if(checkTime(obj.p_begintime)==false){
            swal({
                title: '投放时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }
    }else{
        obj.p_begintime=''
    }
    obj.start=num;
    console.log(obj);
    $.ajax({
        url: http+"listInput",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.state==0){
                //if(num1==0){
                    $('.tab2>tbody').empty();
                     var userType=sessionStorage.getItem('utype');
                    $(data.object).each(function(i,el){
                    	var str='';
                    	str+='<tr><td style="text-align: center">'+data.object[i].in_ownername+'</td> <td>'+data.object[i].in_mattername+'</td> <td>'+data.object[i].in_total+'</td> <td>'+data.object[i].in_pname+'</td> <td>'+biaozhunarr[data.object[i].in_pstandrad]+'</td> <td>'+data.object[i].in_parea+'</td> <td>'+data.object[i].in_time+'</td>'
                    	if(userType==1){
	                    	str+='<td><span class="edit" in_pid='+data.object[i].in_pid+' style="text-align: center;margin: 0 ;">编辑</span> <span class="delete" style="text-align: center;margin: 0 ;" data-in_id='+data.object[i].in_id+'>删除</span> </td>'
	                       }
                    	$('.tab2>tbody').append(str)
                    })
                  
                $('.tab2').find('.delete').on('click',function(){
                    var that=$(this)
                    var name=confirm("是否删除该条数据？","")
                    if(name){
                        var obj = new Object();
                        obj.ckuid=sessionStorage.getItem('ckuid');
                        obj.cksid=sessionStorage.getItem('cksid');
                        obj.in_id=that.attr('data-in_id')
                        $.ajax({
                            url: http+"deleteInput",
                            type: "post",
                            contentType: "application/json",
                            headers: {'Content-type': 'application/json;charset=UTF-8'},
                            data:JSON.stringify(obj),
                            cache: false,
                            success: function (data) {
                                if(data.state==0){
                                    swal({
                                        title: data.msg,
                                        text: "2秒后关闭",
                                        confirmButtonText: "确定",
                                        confirmButtonColor: "#30862B",
                                        timer: 2000
                                    });
                                    listInput(1)
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
                                }else{
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
                })
                $('.tab2').find('.edit').on('click',function(){
                	var ids=$(this).attr('in_pid');
                	var dataid=$(this).siblings('.delete').attr('data-in_id');
                    $('.mask1').show().siblings('.mask').hide();
       				 $('.mask1').find('.addtouruagain').hide();
                    $('.mask1').find('.pop_tit>p').text('编辑投入品使用记录');
                    $('.mask1').find('.s2').val($(this).parents('tr').find('td').eq(6).text())
                    $('.mask1').find('.s3').val($(this).parents('tr').find('td').eq(1).text())
                    $('.mask1').find('.s4').val($(this).parents('tr').find('td').eq(2).text())
                    $('.mask1').find('.sureuse').attr('data-id',dataid);
                    listProduce(0,1,ids)//1 是编辑
                });
                    var totalPage=data.totalpage
                    var totalSize=data.totalcount
                    $("#page1").remove();
                    $('#parentpage').append(" <div id='page' class='page_div'></div>")
                    $("#page").paging({
                        pageNo:num,
                        totalPage: totalPage,
                        totalSize: totalSize,
                        callback: function(num) {
                            listInput(num)
                        }
                    });
                //}
                //else{
                //    $('.mask1').find('.s1').empty()
                //    $(data.object).each(function(i,el){
                //        $('.mask1').find('.s1').append('<option data-t2='+data.object[i].p_endtime+' data-t1='+data.object[i].p_begintime+' value='+data.object[i].p_id+'>'+data.object[i].c2_name+' '+data.object[i].p_begintime+'~'+data.object[i].p_endtime+'</option>')
                //    })
                //}
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
            }else{
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
//查看生产记录
function listProduce(num,num1,ids){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.pointEntity=new Object();
    obj.pointEntity.tp_id=active().attr('data-tp_id');
    if(num1==0){
        obj.p_class1=$('#classify3').find('option:selected').val()
        obj.p_class2=$('#classify4').find('option:selected').val()
        obj.p_begintime=$('.beginT').val()
        if(obj.p_begintime){
            if(checkTime(obj.p_begintime)==false || obj.p_begintime.length!=10){
                swal({
                    title: '开始时间格式不对',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
        }else{
            obj.p_begintime=''
        }
        obj.p_endtime=$('.finishT').val()
        if(obj.p_endtime){
            if(checkTime(obj.p_endtime)==false || obj.p_endtime.length!=10){
                swal({
                    title: '结束时间格式不对',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
        }else{
            obj.p_endtime=''
        }
        obj.start=num
    }else{
        obj.p_class1=''
        obj.p_class2=''
        obj.p_begintime=''
        obj.p_endtime=''
        obj.start=num
    };
    console.log(obj);
    $.ajax({
        url: http+"listProduce",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
        	console.log(data)
            if(data.state==0){
                if(num1==0){
                    $('.tab2>tbody').empty()
                    $(data.object).each(function(i,el){
                    	var str='';
                    	str+='<tr><td style="text-align: center">'+data.object[i].p_ownername+'</td> <td>'+data.object[i].c1_name+'</td> <td>'+data.object[i].c2_name+'</td> <td>'+biaozhunarr[data.object[i].p_standrad]+'</td> <td>'+data.object[i].p_harvestarea+'</td> <td>'+data.object[i].p_begintime+'</td> <td>'+data.object[i].p_endtime+'</td>'
                        	if(sessionStorage.getItem('utype')==1){
                        		 str+='<td><span class="editPro" inId="'+data.object[i].p_class1+'" innerId="'+data.object[i].p_class2+'" in_edits="'+data.object[i].p_harvesttime+'" style="text-align: center;margin: 0 ;">编辑</span> <span class="deletePro" style="text-align: center;margin: 0 ;" data-in_id="'+data.object[i].p_id+'">删除</span> </td>'
                        	}
                       str+='</tr>'
                    	$('.tab2>tbody').append(str)
                    });
                    $('.deletePro').click(function(){
                    	var deleid=$(this).attr('data-in_id');
                    	var deviceid=active().attr('data-tp_id');
                    	var name=confirm("是否删除该条数据？","");
                   		if(name){
                    		deletePromain(deviceid,deleid)
                    	}
                    })
                    $('.editPro').click(function(){
                    	$('.sureproct').attr('dataArr',1);
                    	$('.sureproct').attr('dataId',$(this).siblings('.deletePro').attr('data-in_id'));
                    	$('.mask2').show();
                    	var that=$(this);
                    	var id1=that.attr('inId');
                    	var id2=that.attr('innerId');
                    	listClass1(1,0,id1,id2);
                    	$('.mask2').find('.popname').text('编辑生产计划');
                    	
                    	$('.mask2').find('.checkNewarr>label').each(function(){
                    		if($(this).find('span').text()==that.parents('tr').find('td').eq(3).text()){
                    			$(this).click();
                    		}
                    	});
                    	$('.mask2').find('.s6').val(that.parents('tr').find('td').eq(5).text());
                    	$('.mask2').find('.s7').val(that.parents('tr').find('td').eq(6).text());
                    	$('.mask2').find('.s8').val(that.attr('in_edits'));
                    	$('.mask2').find('.s9').val(that.parents('tr').find('td').eq(4).text());
                    })
                    var totalPage=data.totalpage
                    var totalSize=data.totalcount
                    $("#page1").remove();
                    $('#parentpage').append(" <div id='page' class='page_div'></div>")
                    $("#page").paging({
                        pageNo:num,
                        totalPage: totalPage,
                        totalSize: totalSize,
                        callback: function(num) {
                            listProduce(num,0)
                        }
                    });
                }else{
                    $('.mask1').find('.s1').empty();
                    $(data.object).each(function(i,el){
                        $('.mask1').find('.s1').append('<option  data-t2='+data.object[i].p_endtime+' data-t1='+data.object[i].p_begintime+' value='+data.object[i].p_id+'>'+data.object[i].c2_name+' '+data.object[i].p_begintime+'~'+data.object[i].p_endtime+'</option>');
                    });
                    $('.mask1').find('.s1 option').removeAttr('selected');
                    $('.mask1').find('.s1').find('option').each(function(){
                    	if($(this).val()==ids){
                    		$(this).prop('selected','selected')
                    	}
                    })
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
            }else{
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

function deletePromain(id1,id2){
	 var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.pointEntity=new Object();
    obj.pointEntity.tp_id=id1;
    obj.p_id=id2;
    console.log(obj);
    $.ajax({
        url: http+"deleteProduce",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
        	console.log(data)
        	if(data.state==0){
                    swal({
                        title: data.msg,
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#30862B",
                        timer: 2000
                    });
                    listProduce(1,0)
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
                }else{
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
function check(that){
    var result = /^(?:(?:0?|1)\d|2[0-3]):[0-5]\d$/.test(that);
    return result
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
//投入品生产者姓名
//function listUser(){
//    var obj = new Object();
//    obj.ckuid=sessionStorage.getItem('ckuid');
//    obj.cksid=sessionStorage.getItem('cksid');
//    $.ajax({
//        url: http+"listUser",
//        type: "post",
//        contentType: "application/json",
//        headers: {'Content-type': 'application/json;charset=UTF-8'},
//        data:JSON.stringify(obj),
//        cache: false,
//        success: function (data) {
//            if(data.state==0){
//               $('#classify1').empty().append('<option value="0">全部</option>')
//                $(data.object).each(function(i,el){
//                    $('#classify1').append('<option value='+data.object[i].uid+'>'+data.object[i].tu_username+'</option>')
//                })
//
//            }else{
//                alert(data.msg);
//            }
//        }
//    });
//}
//查看二级分类
function listClass2Byrid(id,num,id2){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.c_rid=id;
    $.ajax({
        url: http+"listClass2Byrid",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.state==0){
                if(num==0){
                    $('.mask2').show().siblings('.mask').hide();
                    $('.mask2').find('select.s2').empty();
                    $(data.object).each(function(i,el){
                        $('.mask2').find('select.s2').append('<option value='+data.object[i].c_id+'>'+data.object[i].c_name+'</option>')
                    })
            		$('.mask2').find('.s2').find('option').each(function(){
                		if($(this).val()==id2){
                			$(this).prop('selected','selected');
                		};
            		});
                }
                if(num==1){
                    $('#classify4').empty();
                    $('#classify4').append('<option value="0" selected>全部</option>');
                    $(data.object).each(function(i,el){
                        $('#classify4').append('<option value='+data.object[i].c_id+'>'+data.object[i].c_name+'</option>')
                    })
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
            }else{
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
function addInput(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.inputEntity=[];
    var state=true
    $('.mask1').find('.pop_ul').each(function(i,el){
        var obj01=new Object();
        if($('.sureuse').attr('data-id')!=0){
            obj01.in_id=$('.sureuse').attr('data-id');
        }
        obj01.in_pid=$('.mask1').find('.pop_ul').eq(i).find('.s1').find('option:selected').val();
        obj01.p_begintime=$('.mask1').find('.pop_ul').eq(i).find('.s1').find('option:selected').attr('data-t1')
        obj01.p_endtime=$('.mask1').find('.pop_ul').eq(i).find('.s1').find('option:selected').attr('data-t2')
        obj01.in_time=$('.mask1').find('.pop_ul').eq(i).find('.s2').val()
        if(!obj01.in_time){
            swal({
                title: '第'+(i+1)+'条的投放时间不能为空',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            state=false
            return
        }
        if(obj01.in_time.length!=10 || checkTime(obj01.in_time)==false){
            swal({
                title: '第'+(i+1)+'条的投放时间格式不对',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            state=false
            return
        }
        obj01.in_mattername=$('.mask1').find('.pop_ul').eq(i).find('.s3').val()
        if(!obj01.in_mattername){
            swal({
                title: '第'+(i+1)+'条的投放明细不能为空',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            state=false
            return
        }
        obj01.in_total=$('.mask1').find('.pop_ul').eq(i).find('.s4').val()
        if(!obj01.in_total){
            swal({
                title: '第'+(i+1)+'条的投放数量不能为空',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            state=false
            return
        }
        obj.inputEntity.push(obj01)
    })
    if(state==true){
        $.ajax({
            url: http+"addInput",
            type: "post",
            contentType: "application/json",
            headers: {'Content-type': 'application/json;charset=UTF-8'},
            data:JSON.stringify(obj),
            cache: false,
            success: function (data) {
                if(data.state==0){
                    swal({
                        title: data.msg,
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#30862B",
                        timer: 2000
                    });
                    listInput(1);
                    $('.mask1').hide()
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
                }else{
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

}
//生产计划作物--
function listClass1(type,num,id1,id2){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.c_type=type;
    $.ajax({
        url: http+"listClass1",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.state==0){
                    if(data.object!=null) {
                        if(num==0){
                            $('.mask2').show().siblings('.mask').hide()
                            $('.mask2').find('select.s1').empty()
                            $(data.object).each(function (i, el) {
                                $('.mask2').find('select.s1').append('<option value=' + data.object[i].c_id + '>' + data.object[i].c_name + '</option>')
                            })
                            $('.mask2').find('.s1').find('option').each(function(){
	                    		if($(this).val()==id1){
	                    			$(this).prop('selected','selected');
	                    		};
	                    	});
                            setTimeout(function () {
                                listClass2Byrid($('.mask2').find('select.s1').find('option:selected').val(),0,id2)
                            }, 50);
                    	
                            $('.mask2').find('select.s1').unbind('change')
                            $('.mask2').find('select.s1').on('change', function () {
                                listClass2Byrid($('.mask2').find('select.s1').find('option:selected').val(),0)
                            })
                        }
                        if(num==1){
                            $('#classify3').empty().append('<option value="0" selected>全部</option>')
                            $('#classify4').empty().append('<option value="0" selected>全部</option>')
                            $(data.object).each(function (i, el) {
                                $('#classify3').append('<option value=' + data.object[i].c_id + '>' + data.object[i].c_name + '</option>')
                            })
                            $('#classify3').unbind('change');
                            $('#classify3').on('change', function () {
                                var id=$('#classify3').find('option:selected').val()
                                if(id!=0){
                                    listClass2Byrid(id,1)
                                }else{
                                    $('#classify4').empty().append('<option value="0" selected>全部</option>')
                                }
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
//保存计划管理
function addProduce(oArrs,ids){
	
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.pointEntity={'tp_id':active().attr('data-tp_id')};
    obj.p_class1=$('.mask2').find('select.s1').find('option:selected').val()
    obj.p_class2=$('.mask2').find('select.s2').find('option:selected').val()
    obj.p_begintime=$('.mask2').find('.s6').val();
    $('.radios label').each(function(i,em){
		if($(this).hasClass('ss')){
			obj.p_standrad=i+1;
		}
	})
    if(oArrs==1){
    	obj.p_id=ids;
    }
    if(checkTime(obj.p_begintime)==false || obj.p_begintime.length!=10){
        swal({
            title: '排产时间时间格式不对',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.p_endtime=$('.mask2').find('.s7').val();
    if(checkTime(obj.p_endtime)==false || obj.p_begintime.length!=10){
        swal({
            title: '结束时间时间格式不对',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.p_harvesttime=$('.mask2').find('.s8').val();
    if(checkTime(obj.p_endtime)==false || obj.p_begintime.length!=10){
        swal({
            title: '采收开始时间时间格式不对',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.p_harvestarea=$('.mask2').find('.s9').val();
    if(!obj.p_harvestarea){
        swal({
            title: '排产面积不能为空',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    console.log(obj);
    parent.loadShow()
    $.ajax({
        url: http+"addProduce",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.state==0){
                swal({
                    title: data.msg,
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                listProduce(1,0);
                $('.mask2').hide()
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
//时间戳
function dealdata(ele) {
    var time = new Date(ele * 1000);
    var n = time.getFullYear();
    var y = time.getMonth() + 1;
    var r = time.getDate();
    var h=time.getHours()<10 ? "0" + time.getHours():time.getHours();
    var m=time.getMinutes()<10 ? "0" + time.getMinutes() :time.getMinutes();
    var s=time.getSeconds()<10? "0"+time.getSeconds():time.getSeconds();
    var result=n+'/'+y+'/'+r+'/';
    return result;
}
function listSensorChartInfo(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.tp_id=active().attr('data-tp_id')
    obj.deviceId=active().attr('data-deviceId')
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
                $('.sensorAnimation').empty();
                var str='<li class="li1"> <p>智能控温状态</p> <div class="bg"><img style="display: block;width: 100%;height: 100%" src="" alt=""></div> </li>'
                $(data.object.unit).each(function(i,el){
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
                })
                $('.sensorAnimation').append(str)
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
//预约控制
function listRule(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.r_type='1';
    //obj.r_deviceId=active().attr('data-deviceId')
    obj.ctrl_id=active2().attr('data-ctrl_id');
    $.ajax({
        url: http+"listRule",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.state==0){
            	$('.wa_intel').empty();
                if(Object.prototype.toString.call(data.object) === '[object Array]'){
                    $(data.object).each(function(i,el){
                        $('.wa_intel').append('<li>周期:1天 / 开始时间:'+data.object[i].beginTime+' / 结束时间:'+data.object[i].endTime+' / 启动时间:'+data.object[i].execTime+' - 执行时长:'+data.object[i].duration+'</li>')
                    })
                }else{
                    swal({
                        title: '暂无相关预约控制规则，请去添加',
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
            }else{
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
function controlMode(s){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.state_type=s;
    obj.ctrl_id=active2().attr('data-ctrl_id')
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
                swal({
                    title: data.msg,
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                },function(){
                    getControlDevStatus(active2().attr('data-ctrl_id'),active2().attr('data-pictype'),active2().attr('ctrl-type'))
                });
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
            }else{
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                },function(){
                    active2().click();
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

//自动控制
function controlSelup(up){
	 var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.ctrl_id=active2().attr('data-ctrl_id');
    obj.distanceOrDuration=$('.controlCont>input').val();
    if(obj.distanceOrDuration==''){
    	 swal({
            title: '开启度不能为空',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
        return
    }
    obj.ruleEntity={};
    obj.hbm={};
    obj.hbm.direction=up;
    obj.pointEntity={};
    obj.pointEntity.ip=active().attr('data-ip');
    obj.pointEntity.port=active().attr('data-port');
    obj.pointEntity.deviceId=active1().attr('data-deviceid');
    parent.loadShow();
	$.ajax({
		url: http+"controlDev",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.state==0){
                swal({
                    title: data.msg,
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                });
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
            }else{
                swal({
                    title: data.msg,
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                },function(){
                    active2().click();
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
function getStyle(obj, attr) 
{ 
if(obj.currentStyle) 
{ 
return obj.currentStyle[attr]; 
} 
else 
{ 
return getComputedStyle(obj,false)[attr]; 
} 
} 
};
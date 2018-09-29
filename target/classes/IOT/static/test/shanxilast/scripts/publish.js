var configNum=0;
$(function(){
	var userType = sessionStorage.getItem('utype');
    	if(userType==1){
    		$('.usreType').show()
    	}else{
    		$('.usreType').hide()
    	}
	if(getUrlKey('m_id')){
		var ids=getUrlKey('m_id');
		if(ids==01){
			setTimeout(function(){$('.ul_top').find('li').eq(1).click()},60)
		}
	}
    setTimeout(function(){
        var right=$(".right").outerHeight();
        var top_h=$(".r_top").outerHeight();
        var nav_h=$(".r_mid").outerHeight();
        $(".r_bot").outerHeight(right-top_h-nav_h)
    },80);
    listMessage1Bygroup(1,0);
    listClass1(4);
    //轮播图hover
    //  导航栏
    var usreTypes=sessionStorage.getItem('utype');
    $('#ul_top>li').on('click',function(){
        configNum=$(this).index();
        $(this).addClass('choose').siblings().removeClass('choose')
        $('.publish').attr('data-num',$(this).index());
        $('.sear').find('input').val('');
        if($(this).index()==0){
            $('#a1').show().siblings('.r_bot ').hide();
            $('.firstpageText').hide();
            if(usreTypes==1){
            	$('.publish').show();
            }else{
            	$('.publish').hide();
            };
            $('.sear').show().find('.search').attr('data-s','0')
            $('.sear').show()
            $('.jss').hide();
            $('.jishiSelect').show();
            $('.yujing').hide()
            $('#a1').find('.cover>img').remove();
            listMessage1Bygroup(1,0);
            listClass1(4);
        }else if($(this).index()==1){
            $('#a2').show().siblings('.r_bot ').hide();
            $('.firstpageText').hide();
             if(usreTypes==1){
            	$('.publish').show();
            }else{
            	$('.publish').hide();
            }
            $('.sear').show().find('.search').attr('data-s','1')
            $('.jss').show()
            $('.yujing').hide();
            $('.jishiSelect').hide();
            listMessage(2)
            listClass1(5)
        }
        else if($(this).index()==2){
            $('#a3').show().siblings('.r_bot ').hide();
            $('.firstpageText').hide();
             if(usreTypes==1){
            	$('.publish').show();
            }else{
            	$('.publish').hide();
            }
            $('.sear').show().find('.search').attr('data-s','2')
            $('.jss').hide()
            $('.yujing').show();
            $('.jishiSelect').hide();
            listMessage(3)
            listClass1(3)
        }
        else if($(this).index()==3){
            $('#a4').show().siblings('.r_bot ').hide()
             if(usreTypes==1){
            	$('.publish').show();
            }else{
            	$('.publish').hide();
            }
            $('.sear').show().find('.search').attr('data-s','3')
            $('.jss').hide()
            $('.yujing').hide();
            $('.jishiSelect').hide();
            $('.firstpageText').show();
            listClass1(7)
            firstZixun(0,0,0);
        }
        else if($(this).index()==4){
            $('#a5').show().siblings('.r_bot ').hide();
             $('.firstpageText').hide();
            if(usreTypes==1){
            	$('.publish').hide();
            }else{
            	$('.publish').hide();
            }
            $('.sear').hide()
            $('.jss').hide()
            $('.yujing').hide()
            $('.jishiSelect').hide();
            listHomePage()
        }
    })
    // 发布信息
    $('.publish').on('click',function () {
        var d = $(this).attr("data-num");
        $(".mask").eq(d).show();
         $(".mask").eq(d).find('ul>li:eq(0)').find('input').val('');
        if(d==0){
            listClass1(4)
        }
        if(d==1){
            listClass1(5)
        }
        if(d==2){
            listClass1(3)
        }
        if(d==3){
            listClass1(7)
        }
    })
//    //分页
//    $("#page").paging({
//        totalPage: 20,
//        totalSize: 300,
//        callback: function(num) {
////				alert(num)
//        }
//    });
    $("#s1").change(function(){
        var ar_id=$("#s1 option:selected").attr("data-id");
        if(ar_id=="710000"||ar_id=="810000"||ar_id=="820000"){
            $("#s2,#s3").parent().parent().hide()
        }else{
            $("#s2,#s3").parent().parent().show();
            listCity(ar_id)
        }
    });
    $("#s2").change(function(){
        var ar_id=$("#s2 option:selected").attr("data-id");
        listDistrict(ar_id)
    });
    $(".close").click(function(){
        $(".mask").hide()
    });
    listProvice();
    //添加新建
    $('.pop_save').on('click',function(){
        var obj={}
        obj.ckuid = sessionStorage.getItem('ckuid');
        obj.cksid = sessionStorage.getItem('cksid');
        obj.m_title=$(this).parents('.mask').find('.pop_kuang>input').val()
        if(!obj.m_title){
            swal({
                title: '标题不能为空',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }
        obj.m_content=$(this).parents('.mask').find('.tx').val()
        if(!obj.m_content){
            swal({
                title: '原文信息不能为空',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }
        obj.m_class=$(this).parents('.mask').find('.fenlei').find('option:selected').val()
        if(obj.m_class==0){
            swal({
                title: '没有分类不能添加，请先添加分类',
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }
        if(configNum==0){
            obj.m_type='1'
            obj.m_cover=$(this).parents('.mask').find('.cover>img').attr('src')
            if(!obj.m_cover){
                swal({
                    title: '您还没有选择文章封面，是否继续？',
                    text: "",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                },function(){
                    obj.m_cover=''
                });
            }
            obj.m_authorname=$(this).parents('.mask').find('.m_authorname').val()
            if(!obj.m_authorname){
                swal({
                    title: '作者名不能为空',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
        }
        if(configNum==1){
            obj.m_type='2'
        }
        if(configNum==2){
            obj.m_type='3'
            obj.m_province = $("#s1 option:selected").attr("data-id");
            obj.m_city = $("#s2 option:selected").attr("data-id");
            obj.m_district = $("#s3 option:selected").attr("data-id");
        }
        if(configNum==3){
            obj.m_type='4'
            obj.m_cover=$(this).parents('.mask').find('.cover>img').attr('src');
            obj.m_class2=$(this).parents('.mask').find('.fenleiTwo').val();
            if(!obj.m_cover){
                swal({
                    title: '您还没有选择文章封面，是否继续？',
                    text: "",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#30862B",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                },function(){
                    obj.m_cover=''
                });
            }
            obj.m_authorname=$(this).parents('.mask').find('.m_authorname').val()
            if(!obj.m_authorname){
                swal({
                    title: '作者名不能为空',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
        }
        $.ajax({
            url:http + "addMessage",
            type:"post",
            contentType: "application/json",
            cache: false,
            data:JSON.stringify(obj),
            success: function (data) {
                if(data.state==0){
                    $('.mask').hide();
                    active().click();
                    swal({
                        title: data.msg,
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#30862B",
                        timer: 2000
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
    })
    //添加政策图片
    $('.abcd').on('change',function(){
        if($(this).val()){
            var obj = new FormData();
            obj.append('ckuid',sessionStorage.getItem('ckuid'))
            obj.append('cksid',sessionStorage.getItem('cksid'))
            obj.append('picture',$('.abcd').get(0).files[0])
            obj.append('oldfile','')
            $.ajax({
                url: http+"addPicture",
                type: "post",
                //contentType: "application/json",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                //headers: {'Content-type': 'application/json;charset=UTF-8'},
                data:obj,
                cache: false,
                success: function (data) {
                    if(data.state==0){
                        $('.mask0').find('.cover').append("<img style='display: block;position: absolute;width: 100%;height: 100%;top:0;left:0;z-index: 100' src="+data.object+" alt=''/>")
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

    })
    $('.abcd1').on('change',function(){
        if($(this).val()){
            var obj = new FormData();
            obj.append('ckuid',sessionStorage.getItem('ckuid'))
            obj.append('cksid',sessionStorage.getItem('cksid'))
            obj.append('picture',$('.abcd1').get(0).files[0])
            obj.append('oldfile','')
            $.ajax({
                url: http+"addPicture",
                type: "post",
                //contentType: "application/json",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                //headers: {'Content-type': 'application/json;charset=UTF-8'},
                data:obj,
                cache: false,
                success: function (data) {
                    if(data.state==0){
                        $('.mask3').find('.cover').append("<img style='display: block;position: absolute;width: 100%;height: 100%;top:0;left:0;z-index: 100' src="+data.object+" alt=''/>")
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

    })
    $('.search').on('click',function(){
        var str=$(this).siblings('input').val();
        parent.loadShow()
        setTimeout(function(){
            parent.removeload()
        },300);
         if($(this).attr('data-s')==0){
         	if(str){
         		var vals=$('.jishiSelect').val();
         		$('.zhengceTopid').find('.content>li').hide();
         		$('.zhengceTopid').hide();
         		$('.zhengceTopid').each(function(i,el){
         			$('.zhengceTopid:eq('+i+')').find('li').each(function(j,e){
	         			if($('.zhengceTopid:eq('+i+')').find('li:eq('+j+')').find('.tit').text().indexOf(str)>=0){
	         				$('.zhengceTopid:eq('+i+')').find('li:eq('+j+')').show();
	         				if(vals!=0){
									if($(this).parents('.zhengceTopid').attr('dataid')==vals){
										$(this).parents('.zhengceTopid').show();
									}
								}else{
									$(this).parents('.zhengceTopid').show();
								}
	         			}
	         		})
         		})
         	}else{
         		$('.zhengceTopid').find('.content>li').show();
         		$('.zhengceTopid').find('.top').show();
         	}
        }
        if($(this).attr('data-s')==1){
        	if(str){
                $('.info1>li').each(function(){
                    if($(this).find('.js_right').find('p').text().indexOf(str)>=0){
                        $(this).show()
                    }else{
                        $(this).hide()
                    }
                })
            }else{
                $('.info1>li').show();
            }
            
        }
        if($(this).attr('data-s')==2){
        	if(str){
                $('.info2>li').each(function(){
                    if($(this).find('.msg_1').text().indexOf(str)>=0){
                        $(this).show()
                    }else{
                        $(this).hide()
                    }
                })


            }else{
                $('.info2>li').show();
            }
          /*  if(str){
                $('.con2>li').each(function(){
                    if($(this).find('.tit').text().indexOf(str)>=0){
                        $(this).show()
                    }else{
                        $(this).hide()
                    }
                })
            }else{
                $('.con2>li').show();
            }*/
        }
        if($(this).attr('data-s')==3){
            if(str){
                $('.con1>li').each(function(){
                    if($(this).find('.tit').text().indexOf(str)>=0){
                        $(this).show()
                    }else{
                        $(this).hide()
                    }
                })
            }else{
                $('.con1>li').show();
            }
        }


    })
    $('.pop_cancel').on('click',function(){
        $(this).parents('.mask').hide()
    })
});
function active(){
    var state;
    $('#ul_top>li').each(function(){
        if($(this).hasClass('choose')){
            state=$(this)
        }
    })
    return state
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
    var result=n+'年'+y+'月'+r+'日';
    return result;
}
function dealdata1(ele) {
    var time = new Date(ele * 1000);
    var n = time.getFullYear();
    var y = time.getMonth() + 1;
    var r = time.getDate();
    var h=time.getHours()<10 ? "0" + time.getHours():time.getHours();
    var m=time.getMinutes()<10 ? "0" + time.getMinutes() :time.getMinutes();
    var s=time.getSeconds()<10? "0"+time.getSeconds():time.getSeconds();
    var result=n+'年';
    return result;
}
function dealdata2(ele) {
    var time = new Date(ele * 1000);
    var n = time.getFullYear();
    var y = time.getMonth() + 1;
    var r = time.getDate();
    var h=time.getHours()<10 ? "0" + time.getHours():time.getHours();
    var m=time.getMinutes()<10 ? "0" + time.getMinutes() :time.getMinutes();
    var s=time.getSeconds()<10? "0"+time.getSeconds():time.getSeconds();
    var result=y+'月'+r+'日';
    return result;
}
//    政策1 及时2，预警3 
function listMessage(num){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.m_type=num;
    if(num==2){
        obj.m_class=$('.jss').find('option:selected').val()
    }
    if(num==3){
        obj.m_class=$('.yujing').find('option:selected').val()
    }
    $.ajax({
        url: http+"listMessage",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.success){
                var str='';
                if(num==1){
                	$('#a1>ul').empty();
                    for(var i=0;i<data.object.length;i++){
                        str='<li> <a href="javascript:;"> <div class="js_left fl"><p>'+dealdata1(data.object[i].m_time)+'</p><p>'+dealdata2(data.object[i].m_time)+'</p></div> <div class="js_right fl"> <p>'+data.object[i].m_title+'</p> <span>'+data.object[i].m_content+'</span> </div> </a> </li>';
                        $("#a1>ul").append(str);
                    }
                }else if(num==2){
                    $('#a2>ul').empty();
                    for(var i=0;i<data.object.length;i++){
                        str='<li> <a href="javascript:;"> <div class="js_left fl"><p>'+dealdata1(data.object[i].m_time)+'</p><p>'+dealdata2(data.object[i].m_time)+'</p></div> <div class="js_right fl"> <p>'+data.object[i].m_title+'</p> <span>'+data.object[i].m_content+'</span> </div> </a> </li>';
                        $("#a2>ul").append(str);
                    }
                }else if(num==3){
                    $('#a3>ul').empty()
                    for(var i=0;i<data.object.length;i++){
                        str='<li><div class="riqi fl"><p>'+dealdata1(data.object[i].m_time)+'</p><p>'+dealdata2(data.object[i].m_time)+'</p></div><div class="msg fl"><p class="msg_1">'+data.object[i].m_title+'</p><p class="msg_2">'+data.object[i].m_content+'</p></div></li>';
                        $("#a3>ul").append(str);
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
//查看政策 资讯消息
function listMessage1Bygroup(num,indexs,dataID){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.m_type=num;
    parent.loadShow()
    $.ajax({
        url: http+"listMessage1Bygroup",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.success){
                    $("#a1").empty();
                    for(var i=0;i<data.object.length;i++){
                        var str='';
                        str+=" <li class='zhengceTop zhengceTopid' dataid='"+data.object[i].c_id+"'><div class='top'> <div class='heng fl'></div> <img src='img/lingxing.png' alt=''/> <span >"+data.object[i].c_name+"</span> <img class='imgRigh' src='img/lingxing.png' alt=''/> <div class='heng fl'><div class='policeknowMore policeknowMoreid'>了解更多&gt;</div></div> </div> <ul class='content con2'>"
                      		for(var j=0;j<data.object[i].list.length;j++){
                      			if(data.object[i].c_id!=dataID){
                      					if(data.object[i].list.length>6){
			                        		data.object[i].list.length=6;
			                        	}
		                      	}
                        		var titlea;
                        		if(data.object[i].list[j].m_title.length>30){
	                        		 titlea= data.object[i].list[j].m_title.substr(0,30)+'......';
	                        		}else{
	                        		 titlea= data.object[i].list[j].m_title
                        		}
                            str+='<li><div class="info"><div class="pd fl"><div style="background: url('+data.object[i].list[j].m_cover+')no-repeat center;background-size: cover" class="picture"></div><p class="per">'+data.object[i].list[j].m_authorname+'</p><p class="date">'+dealdata(data.object[i].list[j].m_time)+'</p></div><div class="detail fl"><p class="tit" data-Tiles="'+data.object[i].c_id+'">'+titlea+'</p><p class="read">'+data.object[i].list[j].m_content+'</p></div><div class="more2"><a style="font-size: 0.14rem" href="./consulting.html?m_id='+data.object[i].list[j].m_id+'&title=政策信息" target="_blank">了解更多&gt;</a></div></div></li>'
                       	 }
                        str+='</ul></li></div>';
                        $("#a1").append(str);
                        $('.zhengceTopid').each(function(){
                        	if($(this).attr('dataid')==dataID){
                        		$(this).find('.policeknowMoreid').hide();
                        	}
                        })
                    }
                    $('.policeknowMore').click(function(){
                    	var dataid=$(this).parents('.zhengceTopid').attr('dataid');
                        	listMessage1Bygroup(1,1,dataid);
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
//资讯二级分类
function classTwo(name,ids){
	var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.c_type='7';
    $.ajax({
        url: http+"listClass1",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
        	if(name==1){
        		$('#firstpageT_twi').html('<option value="0">全部</option>');
	        	$(data.object).each(function(i,el){
	        		if(data.object[i].c_id==ids){
	        			$(data.object[i].list).each(function(j,els){
		        			var oStr='';
		        			oStr="<option value='"+data.object[i].list[j].c_id+"'>"+data.object[i].list[j].c_name+"</option>";
		        			$("#firstpageT_twi").append(oStr);
	        			})
	        		}
	        	})
	        	$("#firstpageT_twi").change(function(){
	        		firstZixun(ids,$(this).val(),0)
	        	})
        	}else{
        		$('.mask3').find('select.fenleiTwo').html('<option value="0">全部</option>');
	        	$(data.object).each(function(i,el){
	        		if(data.object[i].c_id==ids){
	        			$(data.object[i].list).each(function(j,els){
		        			var oStr='';
		        			oStr="<option value='"+data.object[i].list[j].c_id+"'>"+data.object[i].list[j].c_name+"</option>";
		        			$('.mask3').find('select.fenleiTwo').append(oStr);
	        			})
	        		}
	        	})
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
function firstZixun(id1,id2,id3){
	var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.m_type="4";
    obj.m_class=id1;
    obj.m_class2=id2;
   $.ajax({
        url: http+"listHomePageMessage",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
        	 $("#a4").empty();
        	  var str='';
        	  
        	  if(data.object.length!=0){
                    for(var i=0;i<data.object.length;i++){
                        str+="<div class='title' data-id='"+id1+"'>"+data.object[i].c_name+"</div>"
                        if(data.object[i].classList.length!=0){
	                    	for(var j=0;j<data.object[i].classList.length;j++){
	                    		str+="<div class='top'> <div class='heng fl'></div> <img src='img/lingxing.png' alt=''/> <span >"+data.object[i].classList[j].c_name+"</span> <img src='img/lingxing.png' alt=''/> <div class='heng fl'></div><div class='knowMore'>了解更多&gt;</div> </div> <ul class='content con1'>"
	                    		if(id3==0&&data.object[i].classList[j].messageList.length>6){
					        	  		data.object[i].classList[j].messageList.length=6
					        	  }
	                    		for(var k=0;k<data.object[i].classList[j].messageList.length;k++){
	                    			if(data.object[i].classList[j].messageList[k].m_title.length>30){
	                        				 titlea= data.object[i].classList[j].messageList[k].m_title.substr(0,30)+'......';
			                        	}else{
			                        		 titlea= data.object[i].classList[j].messageList[k].m_title
			                        	}
			                            str+='<li><div class="info"><div class="pd fl"><div style="background: url('+data.object[i].classList[j].messageList[k].m_cover+')no-repeat center;background-size: cover" class="picture"></div><p class="per">'+data.object[i].classList[j].messageList[k].m_authorname+'</p><p class="date">'+dealdata(data.object[i].classList[j].messageList[k].m_time)+'</p></div><div class="detail fl"><p class="tit">'+titlea+'</p><p class="read">'+data.object[i].classList[j].messageList[k].m_content+'</p></div><div class="more2"><a style="font-size: 0.14rem" href="./consulting.html?m_id='+data.object[i].classList[j].messageList[k].m_id+'&title=资讯信息" target="_blank">了解更多&gt;</a></div></div></li>'
	                    		}
	                    		str+='</ul>';
	                    	}
                    	}
                    };
                    }
                     $("#a4").append(str);
                     $('.knowMore').click(function(){
                     	var no1=$(this).parents('.top').siblings('.title').attr('data-id');
                     	firstZixun(no1,0,1);
                     })
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
//查找省
function listProvice(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    $.ajax({
        url: http+"listProvice",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.success){
                $("#s1").empty();
                for(var i=0;i<data.object.length;i++){
                    var str='<option data-id='+data.object[i].ar_id+'>'+data.object[i].a_name+'</option>';
                    $("#s1").append(str);
                }
                var ar_id=data.object[0].ar_id;
                listCity(ar_id);
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
//查找市
function listCity(ar_id){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.ar_id=ar_id;
    $.ajax({
        url: http+"listCity",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.success){
                $("#s2").empty();
                for(var i=0;i<data.object.length;i++){
                    var str='<option data-id='+data.object[i].ar_id+'>'+data.object[i].a_name+'</option>';
                    $("#s2").append(str);
                }
                var ar_id=data.object[0].ar_id;
                listDistrict(ar_id);
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
//查找区
function listDistrict(ar_id){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.ar_id=ar_id;
    $.ajax({
        url: http+"listDistrict",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.success){
                $("#s3").empty();
                for(var i=0;i<data.object.length;i++){
                    var str='<option data-id='+data.object[i].ar_id+'>'+data.object[i].a_name+'</option>';
                    $("#s3").append(str);
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
//信息分类           3 预警            5 及时  7咨询             4  政策     5及时 3预警
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
        	$('.m_title').val('');
        	$('.m_authorname').val('');
        	$('.tx').val('');
        	$('.mask0').find('.cover').find('img').remove();
        	$('.mask3').find('.cover').find('img').remove();
        	$('.mask3').find('.cover').find('.abcd1').val('');
        	 $('.mask0').find('.cover').find('.abcd').val('');
            // 渲染页面
            if(data.state==0){
                if(num==4){
                    $('.mask0').find('select.fenlei').empty();
                    if(data.object==null){
                        $('.mask0').find('select.fenlei').append("<option value='0'>暂无分类</option>")
                    }else {
                        $(data.object).each(function (i, el) {
                            $('.mask0').find('select.fenlei').append("<option value=" + data.object[i].c_id + ">" + data.object[i].c_name + "</option>")
                        })
                    };
                    $('.jishiSelect').empty().append('<option value="0" selected>全部</option>');
                    $(data.object).each(function(i,el){
                        $('.jishiSelect').append('<option value='+data.object[i].c_id+'>'+data.object[i].c_name+'</option>')
                    })
                    $('.jishiSelect').unbind('change')
                    $('.jishiSelect').on('change',function(){
                    		var vals=$('.jishiSelect').find('option:selected').val();
                    		$('.sear').find('input').val('');
                    		$('.zhengceTopid').find('.content>li').show();
         					$('.zhengceTopid').find('.top').show();
                    		if(vals==0){
                    			$('.zhengceTopid').show()
                    		}else{
                    			$('.zhengceTopid').each(function(){
                    				if($(this).attr('dataid')==vals){
                    					$(this).show().siblings('.zhengceTopid').hide();
                    				}
                    			})
                    		}
                    })
                }
                if(num==5){
                    $('.mask1').find('select.fenlei').empty();
                    
                    if(data.object==null || !data.object){
                        $('.mask1').find('select.fenlei').append("<option value='0'>暂无分类</option>")
                    }else{
                        $(data.object).each(function(i,el){
                            $('.mask1').find('select.fenlei').append("<option value="+data.object[i].c_id+">"+data.object[i].c_name+"</option>")
                        })
                    }
                    $('.jss').empty().append('<option value="0" selected>全部</option>')
                    $(data.object).each(function(i,el){
                        $('.jss').append('<option value='+data.object[i].c_id+'>'+data.object[i].c_name+'</option>')
                    });
                    $('.sear').find('input').val('');
                    $('.jss').unbind('change')
                    $('.jss').on('change',function(){
                        listMessage(2)
                    })
                }
                if(num==3){
                    $('.mask2').find('select.fenlei').empty()
                    if(data.object==null){
                        $('.mask2').find('select.fenlei').append("<option value='0'>暂无分类</option>")
                    }else {
                        $(data.object).each(function (i, el) {
                            $('.mask2').find('select.fenlei').append("<option value=" + data.object[i].c_id + ">" + data.object[i].c_name + "</option>")
                        })
                    }
                    $('.yujing').empty().append('<option value="0" selected>全部</option>')
                    $(data.object).each(function(i,el){
                        $('.yujing').append('<option value='+data.object[i].c_id+'>'+data.object[i].c_name+'</option>')
                    });
                    $('.yujing').unbind('change')
                    $('.yujing').on('change',function(){
                    	$('.sear').find('input').val('');
                        listMessage(3)
                    })
                }
                if(num==7){
                	$('.mask3').find('select.fenlei').empty();
                	$('.mask3').find('select.fenleiTwo').empty();
                	$("#firstpageT_twi").empty();
                	$("#firstpageT_fir").empty();
                	if(data.object==null){
                		$('.mask3').find('select.fenlei').append("<option value='0'>暂无分类</option>");
                		$("#firstpageT_fir").append("<option value='0'>暂无分类</option>");
                		$('.mask3').find('select.fenleiTwo').hide();
                		$("#firstpageT_twi").hide();
                	}else{
                		$('#firstpageT_fir').html('<option value="0">全部</option>');
                		$("#firstpageT_twi").html('<option value="0">全部</option>');
		        		$(data.object).each(function(i,el){
			        		var str='';
			        		str="<option value='"+data.object[i].c_id+"'>"+data.object[i].c_name+"</option>";
			        		$("#firstpageT_fir").append(str);
			        		$('.mask3').find('select.fenlei').append(str);
			        		$(data.object[i].list).each(function(j,els){
				        		str="<option value='"+data.object[i].list[j].c_id+"'>"+data.object[i].list[j].c_name+"</option>";
			        			$("#firstpageT_twi").append(str);
			        			$('.mask3').find('select.fenleiTwo').append(str)
				        	});
			        	});
			        	$("#firstpageT_fir").change(function(){
			        		classTwo(1,$(this).val());
			        		firstZixun($(this).val(),0)
			        	});
			        	$('.mask3').find('select.fenlei').change(function(){
			        		if($(this).val()==0){
			        			return;
			        		}else{
			        			classTwo(2,$(this).val());
			        		}
			        	});
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
    })
}
//轮播图
function listHomePage(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    parent.loadShow()
    $.ajax({
        url: http+"listHomePage",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.success){
                $("#a5").empty()
                if(data.object!=null){
                    for(var i=0;i<data.object.length;i++){
                        $("#a5").append("<li class='imglist'> <div class='lb_bg'> <img src="+returnLunBoBg(data.object[i].h_cover)+" alt='暂无图片'> <div class='bg'></div> <div class='btn'> <label><span class='replaceimg' id='replaceimg'>"+returnLunBo(data.object[i].h_cover)+"</span><input style='display: none' type='file' class='file'></label><span class='delLB'>删除</span> </div> </div> <p class='lb_tit'>跳转链接：</p> <textarea class='lb_href'>"+data.object[i].h_url+"</textarea> <div class='lb_save' data-id="+data.object[i].h_id+">保存</div> </li>")
                    }
                    $('.file').on('change',function(){
                        //var objUrl = getObjectURL($(this).get(0).files[0]) ;
                        //if (objUrl) {
                        //    $(this).parent().parent().siblings('img').attr("src", objUrl);
                        //}
                        //function getObjectURL(file) {
                        //    var url = null;
                        //    if (window.createObjectURL != undefined) { // basic
                        //        url = window.createObjectURL(file);
                        //    } else if (window.URL != undefined) { // mozilla(firefox)
                        //        url = window.URL.createObjectURL(file);
                        //    } else if (window.webkitURL != undefined) { // webkit or chrome
                        //        url = window.webkitURL.createObjectURL(file);
                        //    }
                        //    return url;
                        //}
                        var index=$(this).parents('li.imglist').index();
                        var obj = new FormData();
                        obj.append('ckuid',sessionStorage.getItem('ckuid'))
                        obj.append('cksid',sessionStorage.getItem('cksid'))
                        obj.append('picture',$(this).get(0).files[0])
                        obj.append('oldfile','');
                        $.ajax({
                            url: http+"addPicture",
                            type: "post",
                            //contentType: "application/json",
                            enctype: 'multipart/form-data',
                            processData: false,
                            contentType: false,
                            //headers: {'Content-type': 'application/json;charset=UTF-8'},
                            data:obj,
                            cache: false,
                            success: function (data) {
                                if(data.state==0){
                                    $('.r_bot5>li').eq(index).find('img').attr('src',data.object).siblings('span').text('替换')
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
                    })
                    $('.delLB').on('click',function(){
                        $(this).siblings('label').find('input.file').val('').siblings('span').text('添加').parent().parent().siblings('img').attr("src", '');
                        swal({
                            title: '删除成功,请务必保存生效',
                            text: "2秒后关闭",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#30862B",
                            timer: 2000
                        });
                        return
                    })
                    $('.lb_save').on('click',function(){
                        var obj = new Object();
                        obj.ckuid=sessionStorage.getItem('ckuid');
                        obj.cksid=sessionStorage.getItem('cksid');
                        obj.h_id=$(this).attr('data-id')
                        obj.h_name='';
                        obj.h_url=$(this).siblings('textarea').val()
                        obj.h_cover=$(this).siblings('.lb_bg').find('img').attr('src')
                        if(obj.h_cover){
                            obj.h_state=1
                        }else{
                            obj.h_state=2
                        }
                        $.ajax({
                            url: http+"updateHomePage",
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
                                    listHomePage()
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
function returnLunBo(s){
    var str='添加'
    if(!s||s==null || s=='' || s==undefined){

    }else{
        str="替换"
    }
    return str
}
function returnLunBoBg(s){
    var str=s
    if(!s||s==null || s=='' || s==undefined){
        str=null
    }
    return str
}
function getUrlKey(name) {
			return decodeURIComponent((
				new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
		};
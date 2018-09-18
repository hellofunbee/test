//var http="/";
/*var http="http://192.168.0.206:8087/";*/
$(function () {
		 setTimeout(function(){
            document.getElementsByTagName('body')[0].style.height = window.innerHeight+'px';
            var top_h=$(".top").outerHeight();
            var top_l=$(".topLine").outerHeight();
            var nav_h=$(".nav").outerHeight();
            $(".homeCon").outerHeight(window.innerHeight-top_h-top_l);
            //423px
        },20);
        
       
        $(".menu>li").click(function(){
        	$(this).siblings('li').removeClass('active');
			$(this).addClass('active');			
	        //var content=$(this).find("div").html();
	        
//          $(".menu>li>p").hide();
//          $(this).find("p").show();
//      if(content=="首页"){
//          $("#main_iframe").attr("src","homepage.html");
//      }
//      if(content=="GIS系统"){
//          $("#main_iframe").attr("src","GIS.html");
//      }
//      if(content=="综合信息"){
//          $("#main_iframe").attr("src","synthesize.html");
//      }
//      if(content=="生产管理"){
//          $("#main_iframe").attr("src","product.html");
//      }
//      if(content=="大数据分析"){
//          $("#main_iframe").attr("src","bigData.html");
//      }
//      if(content=="信息发布"){
//          $("#main_iframe").attr("src","publish.html");
//      }
//      if(content=="专家系统"){
//          $("#main_iframe").attr("src","expert.html");
//      }
//      if(content=="系统管理"){
//          $("#main_iframe").attr("src","system.html");
//      }
//      if(content=="注销"){
//          swal({
//                title: "是否返回登录页面",
//                text: "",
//                type: "warning",
//                showCancelButton: true,
//                confirmButtonColor: "#30862B",
//                cancelButtonText: "取消",
//                confirmButtonText: "确定",
//                closeOnConfirm: false
//            },
//            function(){
//                sessionStorage.removeItem('ckuid')
//                sessionStorage.removeItem('cksid')
//                sessionStorage.removeItem('u_id')
//                window.location.href='./login.html'
//            });
//          return
//      }

    });
	
	
	//nav
	$('.GIS').hover(
	   function(){    
        $(this).find('.giscont').show();
	},
	function(){    
        $(this).find('.giscont').hide();
	});
	$('.giscont>ul>li').click(function(){
		$('.giscont>li').removeClass('active');
		$(this).addClass('active');
	})
	
	
	$('.zonghe').hover(
	   function(){    
        $(this).find('.zongcont').show();
	},
	function(){    
        $(this).find('.zongcont').hide();
	});
	$('.zongcont>ul>li').click(function(){
		$('.zongcont>li').removeClass('active');
		$(this).addClass('active');
	})
	$('.shengchan').hover(
	   function(){    
        $(this).find('.sheng').show();
	},
	function(){    
        $(this).find('.sheng').hide();
	});
	$('.sheng>ul>li').click(function(){
		$('.sheng>li').removeClass('active');
		$(this).addClass('active');
	})
	$('.dashuju').hover(
	   function(){    
        $(this).find('.dashu').show();
	},
	function(){    
        $(this).find('.dashu').hide();
	});
	$('.dashu>ul>li').click(function(){
		$('.dashu>li').removeClass('active');
		$(this).addClass('active');
	})
	$('.xinxifa').hover(
	   function(){    
        $(this).find('.xinxi').show();
	},
	function(){    
        $(this).find('.xinxi').hide();
	});
	$('.xinxi>ul>li').click(function(){
		$('.xinxi>li').removeClass('active');
		$(this).addClass('active');
	})
	$('.xintongguan').hover(
	   function(){    
        $(this).find('.xitong').show();
	},
	function(){    
        $(this).find('.xitong').hide();
	});
	$('.xitong>ul>li').click(function(){
		$('.xitong>li').removeClass('active');
		$(this).addClass('active');
	})
	//nav
	//loading

	//loading
	//实时消息
		setInterval(getNows, 30000)
	function getNows() {
		var obj = new Object();
		obj.ckuid = sessionStorage.getItem('ckuid');
		obj.cksid = sessionStorage.getItem('cksid');
		$.ajax({
			url: http + "getUnReadMessage",
			type: "post",
			contentType: "application/json",
			headers: { 'Content-type': 'application/json;charset=UTF-8' },
			data: JSON.stringify(obj),
			cache: false,
			success: function(data) {
				if(data.object > 0) {
					var str = "您有" + data.object + '条未读的即时消息';
					$('.showKnowsInner').text(str);
					$('.showKnows').show();

				} else {
					$('.showKnows').hide()
				}
			}
		});
	}
	//实时消息

});
	function loadShow() {
		$('body').loading({
			loadingWidth: 240,
			title: '处理中!',
			name: 'test',
			discription: '',
			direction: 'column',
			type: 'origin',
			// originBg:'#71EA71',
			originDivWidth: 40,
			originDivHeight: 40,
			originWidth: 6,
			originHeight: 6,
			smallLoading: false,
			loadingMaskBg: 'rgba(0,0,0,0.2)'
		});
	}

	function removeload() {
		removeLoading('test');
	}
	  function listPoint1(){
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
                    var str="";
                    $(data.object).each(function(i,el){
                        str+="<div class='point'><div class='icon student'> <img src='./img/shipin/ico_home.png' alt=''/> </div> <p class='pinline' style='font-size: 0.12rem'> "+data.object[i].tp_name+"</p>"
                        $(data.object[i].rank).each(function(l,el){
                            str+=" <ul class='navigation' style='font-size: 0.12rem'> <li> <div class='icon fa1'> <img src='./img/shipin/ico_group.png' alt=''/> </div> <p  class='pinline'>"+data.object[i].rank[l].tp_name+"</p>"
                            $(data.object[i].rank[l].rank).each(function(y,el){
                                str+="<ul class='tree'> <li> <div class='fa2Out'><div class='icon fa2'> <img src='./img/shipin/ico_device.png' alt=''/> </div> <p  class='pinline pinline1' data-tp_id="+data.object[i].rank[l].rank[y].tp_id+" data-supervisername="+data.object[i].rank[l].rank[y].supervisername+" data-state="+data.object[i].rank[l].rank[y].state+" data-producername="+data.object[i].rank[l].rank[y].producername+" data-name="+data.object[i].rank[l].rank[y].name+" data-exportorname="+data.object[i].rank[l].rank[y].exportorname+" data-x="+data.object[i].rank[l].rank[y].x+" data-y="+data.object[i].rank[l].rank[y].x+" data-deviceId="+data.object[i].rank[l].rank[y].deviceId+" data-ip="+data.object[i].rank[l].rank[y].ip+" data-port="+data.object[i].rank[l].rank[y].port+">"+data.object[i].rank[l].rank[y].tp_name+"</p> </div><ul class='tree2'> "
                                $(data.object[i].rank[l].rank[y].rank).each(function(z,el){
                                    str+="<li class='pinline3Out' id="+data.object[i].rank[l].rank[y].rank[z].tp_id+"><div class='icon fa3'><spna class='border'></spna><img src='./img/camera.svg' alt=''/></div><a href='javascript:;' class='pinline'>"+data.object[i].rank[l].rank[y].rank[z].tp_name+"</a></li>"
                                })
                                str+="</ul></li></ul>"
                            })
                            str+=" </li> </ul>"
                        })
                        str+="</div>"
                    })
                    $(".nav_left").empty().append(str);
                    $('.fa2Out').each(function(){
                    	if($(this).find('.pinline1').attr('data-state')==2){//2不在线
                    		$(this).find('img').attr('src','./img/shipin/ico_disconnect.png');
                    	}else{
                    		$(this).find('img').attr('src','./img/shipin/ico_device.png');
                    	}                    	
                    });
                     $('.fa2Out').on('click',function(){
                    if($(this).siblings('ul').is(":visible")==false){
                        $(this).siblings('ul').show()
                    }else{
                        $(this).siblings('ul').hide()
                    }
                })
                    $('.fa1>img').on('click',function(){
                        if($(this).parent().siblings('ul').is(":visible")==false){
                            $(this).parent().siblings('ul').show()
                        }else{
                            $(this).parent().siblings('ul').hide()
                        }
                    })
                    $('.fa2>img').on('click',function(){
                        if($(this).parent().siblings('ul').is(":visible")==false){
                            $(this).parent().siblings('ul').show()
                        }else{
                            $(this).parent().siblings('ul').hide()
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
//if(data.state==0){
//
//}else if(data.state==2){
//    alert("登录失效，请重新登录");
//    //window.parent.location.href="login.html";
//    return false
//} else{
//    alert(data.msg);
//}
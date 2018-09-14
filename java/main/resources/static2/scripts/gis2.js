window.onload=function(){
    var rootData;
    var arr=[];
    function addClickHandler(target,window){
        target.addEventListener("click",function(){
            target.openInfoWindow(window);
        });
    }
    var map1;
//设备地址
//  设置文本框
    function addMapOverlay(title,shiyongzhe,zhidaozhuanjia,shengchanzhe,yunxingzhuangtai,guanlizhe,num){
       /* var markers = [
            {content:"设备编号：10.00.21.24",title:"",imageOffset: {width:-46,height:-21},position:{lat:x,lng:y}}
        ];*/
       var yunxingzhuangtai;
       if(yunxingzhuangtai==1){
       		yunxingzhuangtai='在线'
       	}else{
       		yunxingzhuangtai='离线'	
       	}
        var sContent =
          "<p style='margin:0 0 5px 0;padding:0.2em 0;color:#51c626;font-size: 14px'>设置名称："+title+"</p>" +
          "<ul style='overflow: hidden;font-size: 14px'>" +
          "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>使用者:"+shiyongzhe+"</li>" +
          "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>指导专家:"+zhidaozhuanjia+"</li>" +
          "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>生产者:"+shengchanzhe+"</li>" +
          "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>运行状态:"+yunxingzhuangtai+"</li>" +
          "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>管理者:"+guanlizhe+"</li>" +
          "</ul>"+
          "<ul style='overflow: hidden;font-size: 14px'>" +
          "<li class='info_1' onclick='go(1,"+num+")' style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;cursor:pointer'>综合信息</li>" +
          "<li class='info_2' onclick='go(2,"+num+")' style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;cursor:pointer'>视频信息</li>" +
          "<li class='info_3' onclick='go(3,"+num+")' style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;cursor:pointer'>图片信息</li>" +
          "<li class='info_4' onclick='go(4,"+num+")' style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;cursor:pointer'>通知信息</li>" +
          "<li class='info_5' onclick='go(5,"+num+")' style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;cursor:pointer'>生产管理</li>" +
           "<li class='info_5' onclick='go(6,"+num+")' style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;cursor:pointer'>指导专家</li>" +
         "</ul>"+
          "</div>";
          	           // 将标注添加到地图中
       		return sContent;
       
        
    }
//向地图添加控件

//---------------------------------------------------1


// -------------------------------地图-------------------------------------------------------
// 创建地图
/*    function createMap(){
      var map = new BMap.Map("map",{minZoom:4,maxZoom:12});
        map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
        	
    }*/
    function b_createMap(v,index,html){
      var map1 = new BMap.Map("allmap",{minZoom:4,maxZoom:12});
        map1.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
        //设置地图事件
        map1.enableScrollWheelZoom();
        map1.enableKeyboard();
        map1.enableDragging();
        map1.enableDoubleClickZoom()
        //向地图添加控件
        var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
        scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
        map1.addControl(scaleControl);
        var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:0});
        map1.addControl(navControl);
        var overviewControl = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:false});
        map1.addControl(overviewControl);
        var allControl=new BMap.MapTypeControl({
			mapTypes:[
	            BMAP_NORMAL_MAP,
	            BMAP_HYBRID_MAP
	        ]});
		map1.addControl(allControl);
		var bdary = new BMap.Boundary();
        bdary.get(v, function(rs){       //获取行政区域
            var count = rs.boundaries.length; //行政区域的点有多少个
            if (count === 0) {
                swal({
                    title: '未能获取当前输入行政区域',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return ;
            }
            for (var i = 0; i < count; i++) {
                var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000", fillColor:"#fff",strokeOpacity: 1}); //建立多边形覆盖物
                map1.addOverlay(ply);  //添加覆盖物
                var pointArray=[];
                pointArray = pointArray.concat(ply.getPath());
            }
            map1.setViewport(pointArray);    //调整视野
            var lng=pointArray[0].lng
            var lat=pointArray[0].lat
           // windowinfo(lng,lat,index,html)
           if(index!='-1'){
            var str=""
            var str1="<p style='color: #114D0E;text-align: center;font-size:.14rem'>"+html+"</p>"
            $(rootData[index].d_content).each(function(i,el){
                str+="<p style='color: #5AA055;font-size: .14rem;line-height: .2rem'><span style='display: inline-block;width:20px'>"+(i+1)+"</span> <span style='display: inline-block;width: 120px'>"+rootData[index].d_content[i].d_content+"</span><span>"+rootData[index].d_content[i].d_value+"</span></p><br>"
            })
            var sContent =str1+str;
        }else{
            var sContent=html
        }
        var point = new BMap.Point(lng,lat);
        map1.centerAndZoom(point, 16);
        var opts = {
            width : 200,     // 信息窗口宽度
            // 信息窗口高度
            title : "" , // 信息窗口标题
            enableMessage:false,//设置允许信息窗发送短息
            message:""
        }
        var infoWindow = new BMap.InfoWindow(sContent,opts);  // 创建信息窗口对象
        map1.openInfoWindow(infoWindow,point); //开启信息窗口
        document.getElementById("r-result").innerHTML = "信息窗口的内容是：<br />" + infoWindow.getContent();
        });
    }
     function c_createMap(v,index,html){
      var map = new BMap.Map("c_allmap",{minZoom:4,maxZoom:12});
        map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
        map.enableScrollWheelZoom();
        //设置地图事件
        map.enableScrollWheelZoom();
        map.enableKeyboard();
        map.enableDragging();
        map.enableDoubleClickZoom()
        //向地图添加控件
        var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
        scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
        map.addControl(scaleControl);
        var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:0});
        map.addControl(navControl);
        var overviewControl = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:false});
        map.addControl(overviewControl);
        var allControl=new BMap.MapTypeControl({
			mapTypes:[
	            BMAP_NORMAL_MAP,
	            BMAP_HYBRID_MAP
	        ]});
		map.addControl(allControl);
		
				var bdary = new BMap.Boundary();
        bdary.get(v, function(rs){       //获取行政区域
            var count = rs.boundaries.length; //行政区域的点有多少个
            if (count === 0) {
                swal({
                    title: '未能获取当前输入行政区域',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return ;
            }
            for (var i = 0; i < count; i++) {
                var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000", fillColor:"#fff",strokeOpacity: 1}); //建立多边形覆盖物
                map.addOverlay(ply);  //添加覆盖物
                var pointArray=[];
                pointArray = pointArray.concat(ply.getPath());
            }
            map.setViewport(pointArray);    //调整视野
            var lng=pointArray[0].lng
            var lat=pointArray[0].lat
           // windowinfo(lng,lat,index,html)
           if(index!='-1'){
            var str=""
            var str1="<p style='color: #114D0E;text-align: center;font-size: .14rem'>"+html+"</p>"
            $(rootData[index].d_content).each(function(i,el){
                str+="<p style='color: #5AA055;font-size: .14rem;line-height: .2rem'><span style='display: inline-block;width:20px'>"+(i+1)+"</span> <span style='display: inline-block;width: 120px'>"+rootData[index].d_content[i].d_content+"</span><span>"+rootData[index].d_content[i].d_value+"</span></p><br>"
            })
            var sContent =str1+str;
        }else{
            var sContent=html
        }
        var point = new BMap.Point(lng,lat);
        map.centerAndZoom(point, 16);
        var opts = {
            width : 200,     // 信息窗口宽度
            // 信息窗口高度
            title : "" , // 信息窗口标题
            enableMessage:false,//设置允许信息窗发送短息
            message:""
        }
        var infoWindow = new BMap.InfoWindow(sContent,opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow,point); //开启信息窗口
        document.getElementById("r-result").innerHTML = "信息窗口的内容是：<br />" + infoWindow.getContent();
        });
		
    }
    var strinfo = $("#li2").attr("data-str")
    $(document).on('click','.pinline1',function(){
        $(this).addClass('active').parents('ul.tree').siblings().find('.pinline1').removeClass('active');
        var x=parseFloat($(this).attr('data-x'));
        var y=parseFloat($(this).attr('data-y'));
        var zooma=parseInt($(this).parents('.navigation').siblings('.pinlineHome').attr('data_zoom'));
         var num= $(this).attr('nums');
        initMap(x,y,zooma,num);
    });
    function initMap(newX,newY,zooma,num){
       // createMap();//创建地图
      var map = new BMap.Map("map", {mapType:BMAP_HYBRID_MAP});
	   map.enableScrollWheelZoom(true);
	   var point = new BMap.Point(newY,newX);
	   map.centerAndZoom(point,zooma);
        $(document).find('.pinline1').each(function(i,el){
        	var x=parseFloat($(this).attr('data-x'));
            var y=parseFloat($(this).attr('data-y'));
            var point = new BMap.Point(y,x);
            var marker = new BMap.Marker(point);
            map.addOverlay(marker);
            if(i==(num-1)){
            	 marker.setAnimation(BMAP_ANIMATION_BOUNCE);
            }
            var opts = {
	            width : 600,     // 信息窗口宽度
	            height: 200,     // 信息窗口高度
	            title : "" , // 信息窗口标题
	            enableMessage:true,//设置允许信息窗发送短息
	            message:""
        	};
        	var numsa
        	if(active1()!=undefined){
        		numsa=active1().attr('nums');
        	}
        	var ifornewhre=addMapOverlay($(this).attr('data-name'),'',$(this).attr('data-exportorname'),$(this).attr('data-producername'),$(this).attr('data-state'),$(this).attr('data-supervisername'),numsa);
            var infoWindow = new BMap.InfoWindow(ifornewhre, opts);  // 创建信息窗口对象
	       		 marker.addEventListener("click", function(){
	             map.openInfoWindow(infoWindow,point); //开启信息窗口
	        })
		});
		/*alert(num)
		var allOverlay = map.getOverlays();
		map.removeOverlay(allOverlay[1]);*/
    };
    $(document).on('click','.pinlineHome',function(){
    	 $('.pinline1').removeClass('active');
    	 $(this).addClass('active');
    	 var x=$('.pinline1').eq(0).attr('data-x');
    	  var y=$('.pinline1').eq(0).attr('data-y');
    	  var zoom=$('.pinlineHome').eq(0).attr('data_zoom');
    	initMap(x,y,zoom);
    })
//新建
    $("#li1").on("click", function () {
        $('div.map').eq(0).show().siblings('.map').hide();
        $(".getinfo").attr("data-index","0").hide();
        
    });
// 物种
    $("#li2").on("click", function () {
        /*$('div.map').eq(1).show().siblings('.map').hide()*/
        // 文本框模板
        var userType = sessionStorage.getItem('utype');
    	if(userType==1){
    		$(".getinfo").attr("data-index","2").show();
    	}else{
    		$(".getinfo").attr("data-index","2").hide();
    	}
        //  获取信息 接口
        $.ajax({
            url:http + "listDistribution",
            type:"post",
            contentType: "application/json",
            cache: false,
            data:JSON.stringify({
                "ckuid": sessionStorage.getItem('ckuid'),
                "cksid": sessionStorage.getItem('cksid'),
                "d_type":"2"   // 1 生产   2 物种
            }),
            success: function (data) {
                if(data.state==0){
                    rootData=data.object
                    var html = template("city",data);
                    $(".uu_map").html(html);
                    $('.uu_map>li').on('click',function(){
                        var index_name=$(this).text();
                        b_createMap(index_name,$(this).index(),"作物种植面积比例");
                       // getBoundary(index_name,$(this).index(),"作物种植面积比例");
                    });
                    $('.uu_map>li:eq(0)').click();
                }else if(data.state==2){
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
            error: function () {
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
        })


    });

// 点击 获取信息---------------------------------------
// 生产
    $("#li3").on("click", function () {
       /* $('div.map').eq(1).show().siblings('.map').hide()*/
       var userType = sessionStorage.getItem('utype');
    	if(userType==1){
    		$(".getinfo").attr("data-index","1").show();
    	}else{
    		$(".getinfo").attr("data-index","1").hide();
    	}
        //  获取信息 接口
        $.ajax({
            url:http + "listDistribution",
            type:"post",
            contentType: "application/json",
            cache: false,
            data:JSON.stringify({
                "ckuid": sessionStorage.getItem('ckuid'),
                "cksid": sessionStorage.getItem('cksid'),
                "d_type":"1"   // 1 生产   2 物种
            }),
            success: function (data) {
                if(data.state==0){
                    var html = template("city",data);
                    $(".uu_map").html(html);
                    //("#li2").attr("data-str","<p>hahahahha</p><p>hhahahah</p>");
                    rootData=data.object
                    $('.uu_map>li').on('click',function(){
                        var index_name=$(this).text()
                        b_createMap(index_name,$(this).index(),"作物生产进度")
                      /*  getBoundary(index_name,$(this).index(),"作物生产进度")*/
                    });
                    $('.uu_map>li:eq(0)').click();
                }else if(data.state==2){
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
            error: function () {
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
        })

    });
    $('#li4').on('click',function(){
        $('div.map').eq(2).show().siblings('.map').hide()
        $(".getinfo").attr("data-index","3").hide();//屏蔽新建
        $.ajax({
            url:http + "listClass1",
            type:"post",
            contentType: "application/json",
            cache: false,
            data:JSON.stringify({
                "ckuid": sessionStorage.getItem('ckuid'),
                "cksid": sessionStorage.getItem('cksid'),
                "c_type":3
            }),
            success: function (data) {
                // 渲染页面
                if(data.state==0){
                    $('#se').empty().append("<option value='-1'>请选择</option>")
                    $('#fenlei').empty()
                    $(data.object).each(function(i,el){
                        $('#se').append("<option value="+data.object[i].c_id+">"+data.object[i].c_name+"</option>")
                        $('#fenlei').append("<option value="+data.object[i].c_id+">"+data.object[i].c_name+"</option>")
                    })
                    $('#se').unbind('change')
                    $('#se').on('change',function(){
                        if($('#se').find('option:selected').val()!=-1){
                            $.ajax({
                                url:http + "listMessage",
                                type:"post",
                                contentType: "application/json",
                                cache: false,
                                data:JSON.stringify({
                                    "ckuid": sessionStorage.getItem('ckuid'),
                                    "cksid": sessionStorage.getItem('cksid'),
                                    "m_type":3,
                                    "m_class":$('#se').find('option:selected').val()
                                }),
                                success: function (data) {
                                    // 渲染页面
                                    if(data.state==0){
                                        $('#yujing').empty();
                                        $(data.object).each(function(i,el){
                                            $('#yujing').append("<li style='color: #5aa055;margin-top: 10px;font-size: 14px' data-content="+data.object[i].m_content+">"+data.object[i].province+data.object[i].city+data.object[i].district+"</li>")
                                        })
                                        $('#yujing>li').on('click',function(){
                                            c_createMap($(this).text(),'-1',$(this).attr('data-content'));
                                           // getBoundary($(this).text(),'-1',$(this).attr('data-content'))
                                        })
                                    }else if(data.state==2){
                                        alert("登录失效，请重新登录");
                                        //window.parent.location.href="login.html";
                                        return false
                                    } else{
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
                            })
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
        })
    })
    function active1() {
	var state;
	$('.pinline1').each(function() {
		if($(this).hasClass('active')) {
			state = $(this)
		}
	})
	return state
}
}
function go(num,nums){
    parent.clickMenu(num,nums)
}

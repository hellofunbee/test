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
//        "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>指导专家:"+zhidaozhuanjia+"</li>" +
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
   //分布图
    function b_createMap(num,v,index,html){
      var map1 = new BMap.Map("allmap",{minZoom:4,maxZoom:12});
        map1.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
        //设置地图事件
        map1.enableScrollWheelZoom();
        map1.enableKeyboard();
        map1.enableDragging();
        map1.enableDoubleClickZoom();
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
        bdary.get(v,function(rs){       //获取行政区域
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
            var lng=pointArray[0].lng;
            var lat=pointArray[0].lat;   
           $.ajax({
           		url:http+'readExcel',
           		data:JSON.stringify({
           			"ckuid": sessionStorage.getItem('ckuid'),
                	"cksid": sessionStorage.getItem('cksid'),
           			fileName:index,
           		}),
           		cache:false,
           		type:'post',
           		contentType:'application/json',
           		success: function (data) {
           			if(data.success){          
           				if(num==1){
           					//物种覆盖
           					var str=""	           			
				            var str1="<p style='color: #114D0E;text-align: center;font-size:12px;line-height:16px;margin-bottom:5px'>"+html+"</p>"
				            $(data.object).each(function(i,el){
				                str+="<p style='color: #5AA055;font-size: 12px;line-height: 16px'><span style='display: inline-block;width:20px'>"+(i+1)+"</span> <span style='display: inline-block;width: 120px'>"+el.crop+"</span><span>"+el.scale+"</span></p><br>"
				            });
				            var sContent =str1+str;
           				}else{
           					//cavace
           					
           					
           				}
			            var infoWindow = new BMap.InfoWindow(sContent,opts);  // 创建信息窗口对象
				        map1.openInfoWindow(infoWindow,point); //开启信息窗口
				        document.getElementById("r-result").innerHTML =  + infoWindow.getContent();
			       } else {
			       		swal({
	                    title: data.msg,
	                    text: "",
	                    type: "warning",
	                    showCancelButton: false,
	                    confirmButtonColor: "#30862B",
	                    confirmButtonText: "确定",
	                    closeOnConfirm: false
	                });
	                return;			       	
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
           
           
//           农作物
           
//         if(index!='-1'){
//          var str=""
//          var str1="<p style='color: #114D0E;text-align: center;font-size:12px;line-height:16px;margin-bottom:5px'>"+html+"</p>"
//          $(rootData[index].d_content).each(function(i,el){
//              str+="<p style='color: #5AA055;font-size: 12px;line-height: 16px'><span style='display: inline-block;width:20px'>"+(i+1)+"</span> <span style='display: inline-block;width: 120px'>"+rootData[index].d_content[i].d_content+"</span><span>"+rootData[index].d_content[i].d_value+"</span></p><br>"
//          })
//          var sContent =str1+str;
//      }else{
//          var sContent=html
//      }
//       
        var point = new BMap.Point(lng,lat);
        map1.centerAndZoom(point, 16);
        var opts = {
            width : 200,     // 信息窗口宽度
            // 信息窗口高度
            title : "" , // 信息窗口标题
            enableMessage:false,//设置允许信息窗发送短息
            message:""
        }       
        });
    }
    //预警
     function c_createMap(v,index,html){
      var map = new BMap.Map("c_allmaps",{minZoom:4,maxZoom:12});
        map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
        map.enableScrollWheelZoom();
        //设置地图事件
        map.enableScrollWheelZoom();
        map.enableKeyboard();
        map.enableDragging();
        map.enableDoubleClickZoom();
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
            var str1="<p style='color: #114D0E;text-align: center;font-size: 14px'>"+html+"</p>"
            $(rootData[index].d_content).each(function(i,el){
                str+="<p style='color: #5AA055;font-size: .14px;line-height: 20px'><span style='display: inline-block;width:20px'>"+(i+1)+"</span> <span style='display: inline-block;width: 120px'>"+rootData[index].d_content[i].d_content+"</span><span>"+rootData[index].d_content[i].d_value+"</span></p><br>"
            })
            var sContent =str1+str;
        }else{
        	 var str1="<div style='color: #114D0E;text-align: center;font-size: 14px;line-height:20px;height:200px;overflow-y:scroll;overflow-x:hidden;'>"+html+"</div>"
        	
            var sContent=str1
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
    shengchanzhonglei();//生产力种类
    function shengchanzhonglei(){
    	 //  获取信息 接口
         $.ajax({
            url:http + "listClass1",
            type:"post",
            contentType: "application/json",
            cache: false,
            data:JSON.stringify({
                "ckuid": sessionStorage.getItem('ckuid'),
                "cksid": sessionStorage.getItem('cksid'),
                "c_type":8
            }),
            success:function(data){
            	if(data.state===0){
            		 data.object.forEach(function(item,index,arr){
            		var strnew='<option data-id='+item.c_id+'>'+item.c_name+'</option>'
                        	$("#sc4").append(strnew);
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
            
            },
            });
    }
      var strinfo = $("#li2").attr("data-str");
  //初始化页面
      var defaultx=$('.pinline1').eq(0).attr('data-x');
      var defaulty=$('.pinline1').eq(0).attr('data-y');
      var defaultzooma=parseInt($('.pinline1').eq(0).parents('.navigation').siblings('.pinlineHome').attr('data_zoom'));
      var defaultnum=$('.pinline1').eq(0).attr('nums');
      $('.pinline1').eq(0).addClass('active').parents('ul.tree').siblings().find('.pinline1').removeClass('active');
      initMap(defaultx,defaulty,defaultzooma,defaultnum);
      
    //初始化页面
    $(document).on('click','.pinline1',function(){
   
        $(this).addClass('active').parents('ul.tree').siblings().find('.pinline1').removeClass('active');
        var x=parseFloat($(this).attr('data-x'));
        var y=parseFloat($(this).attr('data-y'));
        var zooma=parseInt($(this).parents('.navigation').siblings('.pinlineHome').attr('data_zoom'));
         var num= $(this).attr('nums');
         
        initMap(x,y,zooma,num);
    });
    $(document).on('click','.pinlineHome',function(){
    	 $('.pinline1').removeClass('active');
    	 $(this).addClass('active');
    	 var x=$('.pinline1').eq(0).attr('data-x');
    	  var y=$('.pinline1').eq(0).attr('data-y');
    	  var zoom=$('.pinlineHome').eq(0).attr('data_zoom');
    	initMap(x,y,zoom);
    })
   // $('.pinlineHome').click();
//新建
	//设备分布 0  物种分布2  生产1  报警3
	$("#li1").click(function () {
		$('.shujudao').eq(0).hide();
		$('.shebei').show();
		$('.selectArea').hide();
		$('.shebeifeng').text($("#li1").text()).css('margin-left','20px');
		$('.giscont').hide();
        $('div.map').eq(0).show().siblings('.map').hide();
        $(".getinfo").attr("data-index","0").hide();       
    });
// 物种分布
 	$("#li2").click(function (){
 		$('.selectArea').show();
 		$('.shengchanli').hide().siblings('.yujing').hide()
 		$('.shebei').hide();
 		$('.shujudao').eq(0).show().attr('data-mo','1');
 		$('.shebeifeng').text($("#li2").text()).css('margin-left','40px');
 		$('.giscont').hide();
        $('div.map').eq(1).show().siblings('.map').hide()
        // 文本框模板
        var userType = sessionStorage.getItem('utype');
    	if(userType==1){
    		$(".getinfo").attr("data-index","2").show();
    	}else{
    		$(".getinfo").attr("data-index","2").hide();
    	}
        //  获取信息 接口
        $.ajax({
            url:http + "listDistribute",//(listDistribution 
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
                    rootData=data.object;
                    var obj={};
                    var arr=[];  
                    var arrCity=[];
                    var arrQu=[];
                    $(".allAreasheng").find('ul').empty();
					$('.outerInfoArCConC').empty();
                    data.object.forEach(function(item,index,arr){
//                  	if(!obj[item.d_province]){
//                  		var newObj={};
//                  		newObj.a_name=item.province.toString();
//                  		newObj.ar_id=item.d_province.toString();
//                  		arr.push(newObj)  
//                  	}else{                            
                    		var str='<li data-id='+item.d_province+'>'+item.province+'</li>';
                        	$(".allAreasheng").find('ul').append(str);   
                        	
                        	var strnew='<option data-id='+item.d_province+'>'+item.province+'</option>'
                        	$("#s1").append(strnew);
//                  	}
                    });
                    rootData[0].citys.forEach(function(ite,i){
						var str='<option data-id='+ite.d_city+'>'+ite.city+'</option>';
            			$("#s2").append(str);
					});
					rootData[0].citys[0].districts.forEach(function(ite,k){
						var str='<option data-id='+ite.d_district+' data-con='+ite.files[0].d_originalname+'>'+ite.district+'</option>';
            			$("#s3").append(str);									                        				                        			
					});
					rootData[0].citys[0].districts[0].files.forEach(function(it,j){
						var exel='<li>'+it.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
						$('.outerInfoArCConC').append(exel)
					});		
					
               		$(document).on('change','#s1',function(){
						var thisId=$("#s1 option:selected").attr("data-id");	
						$("#s2").empty();
						$("#s3").empty();
						rootData.forEach(function(item,index){
							if(item.d_province==thisId){ 
								item.citys.forEach(function(ite,i){
									var str='<option data-id='+ite.d_city+'>'+ite.city+'</option>';
                        			$("#s2").append(str);
								});
								
								item.citys[0].districts.forEach(function(ite,k){
									var str='<option data-id='+ite.d_district+' data-con='+ite.files[0].d_originalname+'>'+ite.district+'</option>';
                        			$("#s3").append(str);									                        				                        			
								});
								item.citys[0].districts[0].files.forEach(function(it,j){
									var exel='<li>'+it.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
									$('.outerInfoArCConC').append(exel)
								})		
							}
						})
					});
					$(document).on('change','#s2',function(){			
							var shengval=$('#s1').val();
							var thisId=$("#s2 option:selected").attr("data-id");
							$("#s3").empty();
							rootData.forEach(function(item,index){
								if(item.province==shengval){
									item.citys.forEach(function(ite,i){
										if(ite.d_city==thisId){
											$(".allAreaqu").find('ul').empty();
											$('.outerInfoArCConC').empty();
											ite.districts.forEach(function(it,j){
												var str='<option data-id='+it.d_district+' data-con='+it.files[0]+'>'+it.district+'</option>';
			                        			$("#s3").append(str);
											});			
										
											ite.districts[0].files.forEach(function(it,j){
												var exel='<li>'+it.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
												$('.outerInfoArCConC').append(exel)
											})
											
											
										}
									})
								}								
							});	
						});
					$(document).on('change','#s3',function(){
						var thisId=$("#s3 option:selected").text();
						var shengval=$('#s1').val();
						var shival=$('#s2').val();
						$('.outerInfoArCConC').empty();
						rootData.forEach(function(item,index){
							if(item.province==shengval){
								item.citys.forEach(function(ite,ind){
									if(ite.city==shival){
										ite.districts.forEach(function(it,ins){
											if(it.district==thisId){
												it.files.forEach(function(i,j){
													var exel='<li>'+i.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
													$('.outerInfoArCConC').append(exel);
												})
											}
										})
									}
								})
							}
						})
					});	
					$(document).on('click','.allAreasheng ul li',function(){
						var shengval=$(this).text();
						var shival='';
						var quval='';
						$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
						$(this).parents('ul').hide();
						var thisId=$(this).attr('data-id');
						if(thisId=="710000"||thisId=="810000"||thisId=="820000"){
				            $(".allAreashi ul,.allAreaqu ul").hide()
				        }else{
				             $(".allAreashi ul,.allAreaqu ul").show()
				        }						
						rootData.forEach(function(item,index){
							if(item.d_province==thisId){ 
								//arrCity=item.citys;
								$(".allAreashi").find('ul').empty();
								$(".allAreaqu").find('ul').empty();
								item.citys.forEach(function(ite,i){
									var str='<li data-id='+ite.d_city+'>'+ite.city+'</li>';
                        			$(".allAreashi").find('ul').append(str);
								});		
								$(".allAreashi").find('input').val(item.citys[0].city);
								shival=item.citys[0].city;
								item.citys[0].districts.forEach(function(ite,i){
									var str='<li data-id='+ite.d_district+' data-con='+ite.files[0].d_originalname+'>'+ite.district+'</li>';
                        			$(".allAreaqu").find('ul').append(str);
                        			
								});
								$(".allAreaqu").find('input').val(item.citys[0].districts[0].district);
								quval=item.citys[0].districts[0].district;
								var newAreas=shengval+shival+quval;
								 b_createMap(1,newAreas,$(".allAreaqu").find('li').eq(0).attr('data-con'),"作物种植面积比例");								
							}
						})
					});
					$('.allAreasheng').find('li').eq(0).click();
						$(document).on('click','.allAreashi ul li',function(){
							var shengval=$('.allAreasheng').find('input').val();
							var shival=$(this).val();
							var quval='';
							$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
							$(this).parents('ul').hide();				
						var thisId=$(this).attr('data-id');
						rootData.forEach(function(item,index){
							if(item.province==shengval){
								item.citys.forEach(function(ite,i){
									if(ite.d_city==thisId){
//										arrQu=item.districts;
										$(".allAreaqu").find('ul').empty();
										ite.districts.forEach(function(it,j){
											var str='<li data-id='+it.d_district+' data-con='+it.files[0].d_originalname+'>'+it.district+'</li>';
		                        			$(".allAreaqu").find('ul').append(str);
										});
										var quval=ite.districts[0].district;
										var newAreas=shengval+shival+quval;
										 b_createMap(1,newAreas,$(this).attr('data-con'),"作物种植面积比例");
									}
								})
							}
							
						});						
					})						
                    $(document).on('click','.allAreaqu ul li',function(){	
						$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
						$(this).parents('ul').hide();
						var shengval=$('.allAreasheng').find('input').val();
						var shival=$('.allAreashi').find('input').val();
						var newAreas=shengval+shival+$(this).text();
						 b_createMap(1,newAreas,$(this).attr('data-con'),"作物种植面积比例");
					});
               
                     
                     
//                  var html = template("city",data);
//                  $(".uu_map").html(html);
//                  $('.uu_map>li').on('click',function(){
//                      var index_name=$(this).text();
//                       b_createMap(index_name,$(this).attr('data-con'),"作物种植面积比例");
//
//                  });
//                  $('.uu_map>li:eq(0)').click();
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
   	$("#li3").click(function (){
   		$('.shujudao').eq(0).show().attr('data-mo','2');
   		$('.selectArea').show();
   		$('.shengchanli').show().siblings('.yujing').hide();
   		$('.shebei').hide();
   		$('.shebeifeng').text($("#li3").text()).css('margin-left','40px');
   		$('.giscont').hide();
        $('div.map').eq(1).show().siblings('.map').hide();
       var userType = sessionStorage.getItem('utype');
    	if(userType==1){
    		
    		$(".getinfo").attr("data-index","1").show();
    		
    	}else{
    		$(".getinfo").attr("data-index","1").hide();
    	}
        // 生产力=== 获取信息 接口
        $.ajax({
            url:http + "listDistribute",//(listDistribution 
            type:"post",
            contentType: "application/json",
            cache: false,
            data:JSON.stringify({
                "ckuid": sessionStorage.getItem('ckuid'),
                "cksid": sessionStorage.getItem('cksid'),
                "d_type":"1",   // 1 生产   2 物种
                'd_procedure':$("#sc4 option:selected").attr("data-id")
            }),
            success: function (data) {
                if(data.state==0){
                    rootData=data.object;
                    var obj={};
                    var arr=[];  
                    var arrCity=[];
                    var arrQu=[];
                    $(".allAreasheng").find('ul').empty();
					$('.outerInfoArCConC').empty();
                    data.object.forEach(function(item,index,arr){
//                  	if(!obj[item.d_province]){
//                  		var newObj={};
//                  		newObj.a_name=item.province.toString();
//                  		newObj.ar_id=item.d_province.toString();
//                  		arr.push(newObj)  
//                  	}else{                            
                    		var str='<li data-id='+item.d_province+'>'+item.province+'</li>';
                        	$(".allAreasheng").find('ul').append(str);                           	
//                      	var strnew='<option data-id='+item.d_province+'>'+item.province+'</option>'
//                      	$("#sc1").append(strnew);
//                  	}
                    });
//                  rootData[0].citys.forEach(function(ite,i){
//						var str='<option data-id='+ite.d_city+'>'+ite.city+'</option>';
//          			$("#sc2").append(str);
//					});
//					rootData[0].citys[0].districts.forEach(function(ite,k){
//						var str='<option data-id='+ite.d_district+' data-con='+ite.files[0].d_originalname+'>'+ite.district+'</option>';
//          			$("#sc3").append(str);									                        				                        			
//					});
					rootData[0].citys[0].districts[0].files.forEach(function(it,j){
						var exel='<li>'+it.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
						$('.outerInfoArCConC').append(exel)
					});		
					
//             		$(document).on('change','#s1',function(){
//						var thisId=$("#s1 option:selected").attr("data-id");	
//						$("#sc2").empty();
//						$("#sc3").empty();
//						rootData.forEach(function(item,index){
//							if(item.d_province==thisId){ 
//								item.citys.forEach(function(ite,i){
//									var str='<option data-id='+ite.d_city+'>'+ite.city+'</option>';
//                      			$("#sc2").append(str);
//								});
//								
//								item.citys[0].districts.forEach(function(ite,k){
//									var str='<option data-id='+ite.d_district+' data-con='+ite.files[0].d_originalname+'>'+ite.district+'</option>';
//                      			$("#sc3").append(str);									                        				                        			
//								});
//								item.citys[0].districts[0].files.forEach(function(it,j){
//									var exel='<li>'+it.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
//									$('.outerInfoArCConC').append(exel)
//								})		
//							}
//						})
//					});
//					$(document).on('change','#sc2',function(){			
//							var shengval=$('#sc1').val();
//							var thisId=$("#sc2 option:selected").attr("data-id");
//							$("#sc3").empty();
//							rootData.forEach(function(item,index){
//								if(item.province==shengval){
//									item.citys.forEach(function(ite,i){
//										if(ite.d_city==thisId){
//											$(".allAreaqu").find('ul').empty();
//											$('.outerInfoArCConC').empty();
//											ite.districts.forEach(function(it,j){
//												var str='<option data-id='+it.d_district+' data-con='+it.files[0]+'>'+it.district+'</option>';
//			                        			$("#sc3").append(str);
//											});													
//											ite.districts[0].files.forEach(function(it,j){
//												var exel='<li>'+it.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
//												$('.outerInfoArCConC').append(exel)
//											})	
//										}
//									})
//								}								
//							});	
//						});
//					$(document).on('change','#sc3',function(){
//						var thisId=$("#sc3 option:selected").text();
//						var shengval=$('#s1').val();
//						var shival=$('#sc2').val();
//						$('.outerInfoArCConC').empty();
//						rootData.forEach(function(item,index){
//							if(item.province==shengval){
//								item.citys.forEach(function(ite,ind){
//									if(ite.city==shival){
//										ite.districts.forEach(function(it,ins){
//											if(it.district==thisId){
//												it.files.forEach(function(i,j){
//													var exel='<li>'+i.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
//													$('.outerInfoArCConC').append(exel);
//												})
//											}
//										})
//									}
//								})
//							}
//						})
//					});	
					$(document).on('click','.allAreasheng ul li',function(){
						var shengval=$(this).text();
						var shival='';
						var quval='';
						$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
						$(this).parents('ul').hide();
						var thisId=$(this).attr('data-id');
						if(thisId=="710000"||thisId=="810000"||thisId=="820000"){
				            $(".allAreashi ul,.allAreaqu ul").hide()
				        }else{
				             $(".allAreashi ul,.allAreaqu ul").show()
				        }						
						rootData.forEach(function(item,index){
							if(item.d_province==thisId){ 
								//arrCity=item.citys;
								$(".allAreashi").find('ul').empty();
								$(".allAreaqu").find('ul').empty();
								item.citys.forEach(function(ite,i){
									var str='<li data-id='+ite.d_city+'>'+ite.city+'</li>';
                        			$(".allAreashi").find('ul').append(str);
								});		
								$(".allAreashi").find('input').val(item.citys[0].city);
								shival=item.citys[0].city;
								item.citys[0].districts.forEach(function(ite,i){
									var str='<li data-id='+ite.d_district+' data-con='+ite.files[0].d_originalname+'>'+ite.district+'</li>';
                        			$(".allAreaqu").find('ul').append(str);
                        			
								});
								$(".allAreaqu").find('input').val(item.citys[0].districts[0].district);
								quval=item.citys[0].districts[0].district;
								var newAreas=shengval+shival+quval;
								 b_createMap(2,newAreas,$(".allAreaqu").find('li').eq(0).attr('data-con'),"作物生产进度");								
							}
						})
					});
					$('.allAreasheng').find('li').eq(0).click();
						$(document).on('click','.allAreashi ul li',function(){
							var shengval=$('.allAreasheng').find('input').val();
							var shival=$(this).val();
							var quval='';
							$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
							$(this).parents('ul').hide();				
						var thisId=$(this).attr('data-id');
						rootData.forEach(function(item,index){
							if(item.province==shengval){
								item.citys.forEach(function(ite,i){
									if(ite.d_city==thisId){
//										arrQu=item.districts;
										$(".allAreaqu").find('ul').empty();
										ite.districts.forEach(function(it,j){
											var str='<li data-id='+it.d_district+' data-con='+it.files[0].d_originalname+'>'+it.district+'</li>';
		                        			$(".allAreaqu").find('ul').append(str);
										});
										var quval=ite.districts[0].district;
										var newAreas=shengval+shival+quval;
										 b_createMap(2,newAreas,$(this).attr('data-con'),"作物生产进度");
									}
								})
							}
							
						});						
					})						
                    $(document).on('click','.allAreaqu ul li',function(){	
						$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
						$(this).parents('ul').hide();
						var shengval=$('.allAreasheng').find('input').val();
						var shival=$('.allAreashi').find('input').val();
						var newAreas=shengval+shival+$(this).text();
						 b_createMap(2,newAreas,$(this).attr('data-con'),"作物生产进度");
					});
//                  var html = template("city",data);
//                  $(".uu_map").html(html);
//                  $('.uu_map>li').on('click',function(){
//                      var index_name=$(this).text();
//                       b_createMap(index_name,$(this).attr('data-con'),"作物种植面积比例");
//
//                  });
//                  $('.uu_map>li:eq(0)').click();
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
            error: function (){
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
        
//      $.ajax({
//          url:http + "listDistribution",
//          type:"post",
//          contentType: "application/json",
//          cache: false,
//          data:JSON.stringify({
//              "ckuid": sessionStorage.getItem('ckuid'),
//              "cksid": sessionStorage.getItem('cksid'),
//              "d_type":"1"   // 1 生产   2 物种
//          }),
//          success: function (data) {
//              if(data.state==0){
//                  var html = template("city",data);
//                  $(".uu_map").html(html);
//
//                  rootData=data.object
//                  $('.uu_map>li').on('click',function(){
//                      var index_name=$(this).text()
//                      b_createMap(index_name,$(this).index(),"作物生产进度")
//
//                  });
//                  $('.uu_map>li:eq(0)').click();
//              }else if(data.state==2){
//                  swal({
//                      title: '请求失败，请重新尝试',
//                      text: "",
//                      type: "warning",
//                      showCancelButton: false,
//                      confirmButtonColor: "#30862B",
//                      confirmButtonText: "确定",
//                      closeOnConfirm: false
//                  });
//                  return
//              } else{
//                  swal({
//                      title: data.msg,
//                      text: "",
//                      type: "warning",
//                      showCancelButton: false,
//                      confirmButtonColor: "#30862B",
//                      confirmButtonText: "确定",
//                      closeOnConfirm: false
//                  });
//                  return
//              }
//
//          },
//          error: function () {
//              parent.removeload()
//              swal({
//                  title: '请求失败，请重新尝试',
//                  text: "",
//                  type: "warning",
//                  showCancelButton: false,
//                  confirmButtonColor: "#30862B",
//                  confirmButtonText: "确定",
//                  closeOnConfirm: false
//              });
//              return
//          }
//      })

    });
       	$("#li4").click(function (){
       		$('.shebei').hide();
       		$('.shujudao').eq(0).hide();
	   		$('.selectArea').show();
	   		$('.shengchanli').hide().siblings('.yujing').show();
	   		$('.shebeifeng').text($("#li4").text());
	   		$('.giscont').hide();
	        $('div.map').eq(2).show().siblings('.map').hide();        
	        $(".getinfo").attr("data-index","3").hide();//屏蔽新建
	         // 预警信息=== 获取信息 接口
        $.ajax({
            url:http + "listDistribute",//(listDistribution 
            type:"post",
            contentType: "application/json",
            cache: false,
            data:JSON.stringify({
                "ckuid": sessionStorage.getItem('ckuid'),
                "cksid": sessionStorage.getItem('cksid'),
                "d_type":"1",   // 1 生产   2 物种
                'd_procedure':$("#sc4 option:selected").attr("data-id")
            }),
            success: function (data) {
                if(data.state==0){
                    rootData=data.object;
                    var obj={};
                    var arr=[];  
                    var arrCity=[];
                    var arrQu=[];
                    $(".allAreasheng").find('ul').empty();
					$('.outerInfoArCConC').empty();
                    data.object.forEach(function(item,index,arr){
//                  	if(!obj[item.d_province]){
//                  		var newObj={};
//                  		newObj.a_name=item.province.toString();
//                  		newObj.ar_id=item.d_province.toString();
//                  		arr.push(newObj)  
//                  	}else{                            
                    		var str='<li data-id='+item.m_province+'>'+item.province+'</li>';
                        	$(".allAreasheng").find('ul').append(str);   
                        	
                        	var strnew='<option data-id='+item.m_province+'>'+item.province+'</option>'
                        	$("#sc1").append(strnew);
//                  	}
                    });
                    rootData[0].citys.forEach(function(ite,i){
						var str='<option data-id='+ite.d_city+'>'+ite.city+'</option>';
            			$("#sc2").append(str);
					});
					rootData[0].citys[0].districts.forEach(function(ite,k){
						var str='<option data-id='+ite.d_district+' data-con='+ite.files[0].d_originalname+'>'+ite.district+'</option>';
            			$("#sc3").append(str);									                        				                        			
					});
					rootData[0].citys[0].districts[0].files.forEach(function(it,j){
						var exel='<li>'+it.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
						$('.outerInfoArCConC').append(exel)
					});							
               		$(document).on('change','#s1',function(){
						var thisId=$("#s1 option:selected").attr("data-id");	
						$("#sc2").empty();
						$("#sc3").empty();
						rootData.forEach(function(item,index){
							if(item.m_province==thisId){ 
								item.citys.forEach(function(ite,i){
									var str='<option data-id='+ite.d_city+'>'+ite.city+'</option>';
                        			$("#sc2").append(str);
								});
								
								item.citys[0].districts.forEach(function(ite,k){
									var str='<option data-id='+ite.d_district+' data-con='+ite.files[0].d_originalname+'>'+ite.district+'</option>';
                        			$("#sc3").append(str);									                        				                        			
								});
								item.citys[0].districts[0].files.forEach(function(it,j){
									var exel='<li>'+it.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
									$('.outerInfoArCConC').append(exel)
								})		
							}
						})
					});
					$(document).on('change','#sc2',function(){			
							var shengval=$('#sc1').val();
							var thisId=$("#sc2 option:selected").attr("data-id");
							$("#sc3").empty();
							rootData.forEach(function(item,index){
								if(item.province==shengval){
									item.citys.forEach(function(ite,i){
										if(ite.d_city==thisId){
											$(".allAreaqu").find('ul').empty();
											$('.outerInfoArCConC').empty();
											ite.districts.forEach(function(it,j){
												var str='<option data-id='+it.d_district+' data-con='+it.files[0]+'>'+it.district+'</option>';
			                        			$("#sc3").append(str);
											});			
										
											ite.districts[0].files.forEach(function(it,j){
												var exel='<li>'+it.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
												$('.outerInfoArCConC').append(exel)
											})
	
										}
									})
								}								
							});	
						});
					$(document).on('change','#sc3',function(){
						var thisId=$("#sc3 option:selected").text();
						var shengval=$('#s1').val();
						var shival=$('#sc2').val();
						$('.outerInfoArCConC').empty();
						rootData.forEach(function(item,index){
							if(item.province==shengval){
								item.citys.forEach(function(ite,ind){
									if(ite.city==shival){
										ite.districts.forEach(function(it,ins){
											if(it.district==thisId){
												it.files.forEach(function(i,j){
													var exel='<li>'+i.d_originalname+'<span class="fr delectexsel" data-id='+it.d_id+'>删除</span></li>';									
													$('.outerInfoArCConC').append(exel);
												})
											}
										})
									}
								})
							}
						})
					});	
					$(document).on('click','.allAreasheng ul li',function(){
						var shengval=$(this).text();
						var shival='';
						var quval='';
						$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
						$(this).parents('ul').hide();
						var thisId=$(this).attr('data-id');
						if(thisId=="710000"||thisId=="810000"||thisId=="820000"){
				            $(".allAreashi ul,.allAreaqu ul").hide()
				        }else{
				             $(".allAreashi ul,.allAreaqu ul").show()
				        }						
						rootData.forEach(function(item,index){
							if(item.m_province==thisId){ 
								//arrCity=item.citys;
								$(".allAreashi").find('ul').empty();
								$(".allAreaqu").find('ul').empty();
								item.citys.forEach(function(ite,i){
									var str='<li data-id='+ite.d_city+'>'+ite.city+'</li>';
                        			$(".allAreashi").find('ul').append(str);
								});		
								$(".allAreashi").find('input').val(item.citys[0].city);
								shival=item.citys[0].city;
								item.citys[0].districts.forEach(function(ite,i){
									var str='<li data-id='+ite.d_district+' data-con='+ite.files[0].d_originalname+'>'+ite.district+'</li>';
                        			$(".allAreaqu").find('ul').append(str);
                        			
								});
								$(".allAreaqu").find('input').val(item.citys[0].districts[0].district);
								quval=item.citys[0].districts[0].district;
								var newAreas=shengval+shival+quval;
								 b_createMap(newAreas,$(".allAreaqu").find('li').eq(0).attr('data-con'),"作物生产进度");								
							}
						})
					});
					$('.allAreasheng').find('li').eq(0).click();
						$(document).on('click','.allAreashi ul li',function(){
							var shengval=$('.allAreasheng').find('input').val();
							var shival=$(this).val();
							var quval='';
							$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
							$(this).parents('ul').hide();				
						var thisId=$(this).attr('data-id');
						rootData.forEach(function(item,index){
							if(item.province==shengval){
								item.citys.forEach(function(ite,i){
									if(ite.d_city==thisId){
//										arrQu=item.districts;
										$(".allAreaqu").find('ul').empty();
										ite.districts.forEach(function(it,j){
											var str='<li data-id='+it.d_district+' data-con='+it.files[0].d_originalname+'>'+it.district+'</li>';
		                        			$(".allAreaqu").find('ul').append(str);
										});
										var quval=ite.districts[0].district;
										var newAreas=shengval+shival+quval;
										 b_createMap(newAreas,$(this).attr('data-con'),"作物生产进度");
									}
								})
							}							
						});						
					})						
                    $(document).on('click','.allAreaqu ul li',function(){	
						$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
						$(this).parents('ul').hide();
						var shengval=$('.allAreasheng').find('input').val();
						var shival=$('.allAreashi').find('input').val();
						var newAreas=shengval+shival+$(this).text();
						 b_createMap(newAreas,$(this).attr('data-con'),"作物生产进度");
					});
               
                     
                     
//                  var html = template("city",data);
//                  $(".uu_map").html(html);
//                  $('.uu_map>li').on('click',function(){
//                      var index_name=$(this).text();
//                       b_createMap(index_name,$(this).attr('data-con'),"作物种植面积比例");
//
//                  });
//                  $('.uu_map>li:eq(0)').click();
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
       });
        
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
                    $('.yujing>ul').empty();
                     $(data.object).each(function(i,el){
                        $('.yujing>ul').append("<li data-value="+data.object[i].c_id+">"+data.object[i].c_name+"</li>")
                    })

//                  $('#fenlei').empty();
//                  $(data.object).each(function(i,el){
//                      $('#se').append("<option value="+data.object[i].c_id+">"+data.object[i].c_name+"</option>")
//                      $('#fenlei').append("<option value="+data.object[i].c_id+">"+data.object[i].c_name+"</option>")
//                  })
//                  $('#se').unbind('change')
					$('.yujing li').click(function(){	
						$(this).parent().siblings('.allAreaqucon').find('input').val($(this).text());
                            $.ajax({
                                url:http + "listMessage",
                                type:"post",
                                contentType: "application/json",
                                cache: false,
                                data:JSON.stringify({
                                    "ckuid": sessionStorage.getItem('ckuid'),
                                    "cksid": sessionStorage.getItem('cksid'),
                                    "m_type":3,
                                    "m_class":$(this).attr('data-value')
                                }),
                                success: function (data) {
                                
                                    // 渲染页面
                                    if(data.state==0){
                                    	var rootData=data.object;
                                    	$('.allAreasheng').find('ul').empty();										
                                        $(data.object).each(function(i,el){
                                           $('.allAreasheng').find('ul').append("<li data-id="+data.object[i].m_province+">"+data.object[i].province+"</li>")
                                        })
                                      $('.allAreasheng ul li').click(function(){
										var shengval=$(this).text();
										var shival='';
										var quval='';
										$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
										$(this).parents('ul').hide();
										var thisId=$(this).attr('data-id');
										
										if(thisId=="710000"||thisId=="810000"||thisId=="820000"){
								            $(".allAreashi ul,.allAreaqu ul").hide()
								        }else{
								             $(".allAreashi ul,.allAreaqu ul").show()
								        }	

										rootData.forEach(function(item,index){
											if(item.m_province==thisId){ 
												
												//arrCity=item.citys;
												$(".allAreashi").find('ul').empty();
												$(".allAreaqu").find('ul').empty();
												item.citys.forEach(function(ite,i){
													var str='<li data-id='+ite.d_city+'>'+ite.city+'</li>';
				                        			$(".allAreashi").find('ul').append(str);
												});		
												$(".allAreashi").find('input').val(item.citys[0].city);
												shival=item.citys[0].city;
												item.citys[0].districts.forEach(function(ite,i){
													var str='<li data-id='+ite.m_district+' data-con='+ite.warnings[0].m_content+'>'+ite.district+'</li>';
				                        			$(".allAreaqu").find('ul').append(str);				                        			
												});
												$(".allAreaqu").find('input').val(item.citys[0].districts[0].district);
												quval=item.citys[0].districts[0].district;
												var newAreas=shengval+shival+quval;
												// b_createMap(newAreas,$(".allAreaqu").find('li').eq(0).attr('data-con'),"作物生产进度");				
												 c_createMap(newAreas,'-1',item.citys[0].districts[0].warnings[0].m_content);    
											}
										})
									});
									$('.allAreasheng').find('li').eq(0).click();
										$(document).on('click','.allAreashi ul li',function(){
											var shengval=$('.allAreasheng').find('input').val();
											var shival=$(this).val();
											var quval='';
											$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
											$(this).parents('ul').hide();				
											var thisId=$(this).attr('data-id');
											rootData.forEach(function(item,index){
											if(item.province==shengval){
												item.citys.forEach(function(ite,i){
													if(ite.d_city==thisId){
				//										arrQu=item.districts;
														$(".allAreaqu").find('ul').empty();
														ite.districts.forEach(function(it,j){
															var str='<li data-id='+it.m_district+' data-con='+it.warnings[0].m_content+'>'+it.district+'</li>';
						                        			$(".allAreaqu").find('ul').append(str);
														});
														var quval=ite.districts[0].district;
														var newAreas=shengval+shival+quval;
														 c_createMap(newAreas,'-1',ite.districts[0].warnings[0].m_content);    
														 //b_createMap(newAreas,$(this).attr('data-con'),"作物生产进度");
													}
												})
											}
											
										});						
									});						
				                    $(document).on('click','.allAreaqu ul li',function(){	
										$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
										$(this).parents('ul').hide();
										var shengval=$('.allAreasheng').find('input').val();
										var shival=$('.allAreashi').find('input').val();
										var newAreas=shengval+shival+$(this).text();
										 c_createMap(newAreas,'-1',$(this).attr('data-con'));    
										// b_createMap(newAreas,$(this).attr('data-con'),"作物生产进度");
									});
				               
				                     
				                     
				//                  var html = template("city",data);
				//                  $(".uu_map").html(html);
				//                  $('.uu_map>li').on('click',function(){
				//                      var index_name=$(this).text();
				//                       b_createMap(index_name,$(this).attr('data-con'),"作物种植面积比例");
				//
				//                  });
				//                  $('.uu_map>li:eq(0)').click();


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
                        
                    })
					$('.yujing li').eq(0).click();
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
    });
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

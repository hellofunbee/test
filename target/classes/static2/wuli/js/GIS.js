//选择 省市区县   已经放到到了插件里-----------------

$(function() {
	listPoint();
	$('.fir_head_r_search').show();
	
	/*子页面都加*/
	setTimeout(function() {
		$(".map").outerHeight(window.innerHeight - $('.fir_head').outerHeight());
	}, 30);
	// 创建地图
    $(document).on('click','.pinlineHome',function(){
    	 $('.treeleft_stu_m_name').removeClass('active');
    	 $(this).addClass('active');
    	 $('.treetwo').css('background-color','#fff');
    	 var x=$('.treeleft_stu_m_name').eq(0).attr('data-x');
    	 var y=$('.treeleft_stu_m_name').eq(0).attr('data-y');
    	 var zoom=$('.pinlineHome').eq(0).attr('data_zoom');
			 console.log(zoom);
    	 initMap(x,y,5);
    	 $("#fir_pro_menu").hide();
    });
	/*显示地图*/
	$(document).on('click', '.treetwo', function(){
		$('.treetwo').css('background-color','#fff');
		$('.treeleft_stu_m_name').removeClass('active');
		$(this).css('background-color','#3366FF');
		$(this).find('.treeleft_stu_m_name').addClass('active');
		$('.pinlineHome').removeClass('active');
		var that = $(this).find('.treeleft_stu_m_name');
		var x = parseFloat(that.attr('data-x'));
		var y = parseFloat(that.attr('data-y'));
		var zooma = parseInt($(this).parents('.treeleft_second').siblings('.pinlineHome').attr('data_zoom'));
		var num = that.attr('nums');
		initMap(x, y, zooma, num);
		$("#fir_pro_menu").hide();
	});
	
	// 切换地图
	var map = $(".map");
	var lis = $(".menu_equip ul li");
	for(var i = 0; i < lis.length - 1; i++) {
		$(lis[i]).on("click", function() {
			$(this).siblings("li").attr("class", "menu_e_list");
			$(this).attr("class", "menu_e_list choose");
			var a = $(this).find('a').attr("data-num");
			$(map[a]).siblings("div>.map").css("display", "none");
			if($('.search_l_more')) {
				$('.search_l_more').remove();
			}
			$('.searchList').hide();
			$(map[a]).css("display", "block");
			$(this).parent().parent().hide();
		})
	}
	$.each(lis, function() {
		$(this).click(function() {
			$('.fir_head_all').text($(this).find('a').text());
		})
	});
	//去掉地图阴影、
	
	$("#li1").on("click", function() {
		$('.fir_head_r_search').show();
		$('.fir_head_r_sanji').hide();
		$('div.map').eq(0).show().siblings('.map').hide();
	});

	function initMap(newX,newY,zooma,num){
    var map = new BMap.Map('map',{mapType:BMAP_HYBRID_MAP});
    var point1 = new BMap.Point(newY,newX);
	   map.centerAndZoom(point1,zooma);
	   var top_left_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT});
	    map.enableScrollWheelZoom(true);
	    map.addControl(top_left_navigation);   
	    
	    $(document).find('.treeleft_stu_m_name').each(function(i,el){
	    	var myIcon = new BMap.Icon("img/marker_blue_sprite.png", new BMap.Size(39,25));
			var marker2 = new BMap.Marker(point1,{icon:myIcon});  // 创建标注
        	var x=parseFloat($(this).attr('data-x'));
            var y=parseFloat($(this).attr('data-y'));
            var point = new BMap.Point(y,x);
            var marker = new BMap.Marker(point);
            if(i==(num-1)){
            	map.addOverlay(marker2);
            	 marker2.setAnimation(BMAP_ANIMATION_BOUNCE);
            }else{
            	map.addOverlay(marker);
            }
             
            var opts = {
	            width : 280,     // 信息窗口宽度
	            height: 180,     // 信息窗口高度
	            title : "" , // 信息窗口标题
	            enableMessage:true,//设置允许信息窗发送短息
	            message:""
        	};
        /*	var numsa
        	if(active1()!=undefined){
        		numsa=active1().attr('nums');
        	}*/
        	var deviceIds=$(this).parent('.treetwo').siblings('.treeleft_stu_cameras').find('.treeleft_stu_camera:eq(0)').find('.treeHasdata').attr('data-deviceid');
        	
        	
        	var ifornewhre=addMapOverlay($(this).attr('data-name'),'',$(this).attr('data-exportorname'),$(this).attr('data-producername'),$(this).attr('data-state'),$(this).attr('data-supervisername'),$(this).attr('nums'),deviceIds);
            var infoWindow = new BMap.InfoWindow(ifornewhre, opts);  // 创建信息窗口对象
						
								map.openInfoWindow(infoWindow,point);
						
	       		 marker.addEventListener("click", function(){
	             map.openInfoWindow(infoWindow,point); //开启信息窗口
	        });
	        marker2.addEventListener("click", function(){
	             map.openInfoWindow(infoWindow,point); //开启信息窗口
	        })
		});
}
	function addMapOverlay(title,shiyongzhe,zhidaozhuanjia,shengchanzhe,yunxingzhuangtai,guanlizhe,num,deviceIds){
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
         "<p style='color:#02265A;font-size: .087rem;line-height:.125rem'>设备名称:<span class='equi_newcolo' >"+title+"</span></p>" +
        "<ul style='overflow: hidden;font-size: .087rem;line-height:.125rem'>" +
         "<li style='color:#02265A;font-size: .087rem'>运行状态:<span class='equi_newcolo'>&nbsp; "+yunxingzhuangtai+"</span></li>" +
        "<li style='color:#02265A;font-size: .087rem'>指导专家:<span class='equi_newcolo'>&nbsp;"+zhidaozhuanjia+"</span></li>" +
        "</ul>"+
          "<ul style='overflow: hidden;display:flex;justify-content: space-between;flex-wrap:wrap'>"+
          "<li class='info_1' onclick='go(1,"+num+")' style='margin-top:20px;flex: 0 0 30%;width:.525rem;height:.175rem;text-align:center;color:#3BA9ED;font-size: .0875rem;border: 1px solid #0E64AD;box-sizing: border-box;border-radius: .025rem;cursor:pointer'>综合信息</li>" +
          "<li class='info_2' onclick='secondClick(\""+deviceIds+"\")' style='margin-top:20px;flex: 0 0 30%;width:.525rem;height:.175rem;text-align:center;color:#3BA9ED;font-size: .0875rem;border: 1px solid #0E64AD;box-sizing: border-box;border-radius: .025rem;cursor:pointer'>视频信息</li>"+
          "<li class='info_3' onclick='go(3,"+num+")' style='margin-top:20px;flex: 0 0 30%;width:.525rem;height:.175rem;text-align:center;color:#3BA9ED;font-size: .0875rem;border: 1px solid #0E64AD;box-sizing: border-box;border-radius: .025rem;cursor:pointer'>图片信息</li>" +
          "<li class='info_4' onclick='go(4,"+num+")' style='margin-top:20px;flex: 0 0 30%;width:.525rem;height:.175rem;text-align:center;color:#3BA9ED;font-size: .0875rem;border: 1px solid #0E64AD;box-sizing: border-box;border-radius: .025rem;cursor:pointer'>即时信息</li>" +
          "<li class='info_5' onclick='go(5,"+num+")' style='margin-top:20px;flex: 0 0 30%;width:.525rem;height:.175rem;text-align:center;color:#3BA9ED;font-size: .0875rem;border: 1px solid #0E64AD;box-sizing: border-box;border-radius: .025rem;cursor:pointer'>生产管理</li>" +
           "<li class='info_5' onclick='go(6,"+num+")' style='margin-top:20px;flex: 0 0 30%;width:.525rem;height:.175rem;text-align:center;color:#3BA9ED;font-size: .0875rem;border: 1px solid #0E64AD;box-sizing: border-box;border-radius: .025rem;cursor:pointer'>专家系统</li>" +
         "</ul>"+
          "</div>";
          	           // 将标注添加到地图中
       		return sContent;
    }
	function active1() {
		var state;
		$('.pinline1').each(function() {
			if($(this).hasClass('active')) {
				state = $(this)
			}
		})
		return state
	}
	$("#li2").on("click", function() {
		$('.fir_head_r_search').hide();
		$('.fir_head_r_sanji').show();
		$('#nothmap').hide();
		$('div.map').eq(1).show().siblings('.map').hide();
		$('div.map').eq(1).addClass('species');
		// console.log($('div.map').eq(1)[0].className);
		loadShow();
		$.ajax({
			url: http + "listDistribution",
			type: "post",
			contentType: "application/json",
			cache: false,
			data: JSON.stringify({
				"ckuid": sessionStorage.getItem('ckuid'),
				"cksid": sessionStorage.getItem('cksid'),
				"d_type": "2" // 1 生产   2 物种
			}),
			success: function(data) {
				removeload()
				if(data.state == 0) {
					rootData = data.object;
					//上部横向的地区列表 
					var html = template("city", data);
					$(".speciesMap .uu_map").html(html);
					var len = data.object.length + 2;
					var marRigh = (parseInt($('.uu_map li:eq(0)').outerWidth()) + parseInt($('.uu_map li').eq(0).css('margin-left'))) * len;
					$('.uu_map').css('width',marRigh +'px');
					var oTr= marRigh - $(window).innerWidth();
					if(oTr>0){
						$('.toRight').show();
					}else{
						$('.toRight').hide();
					};
					$('.uu_map_out ').scrollLeft(0);
					$('.uu_map_out').on('scroll', function() {
						if($('.uu_map_out ').scrollLeft() >= oTr) {
							$('.toRight').hide()
						} else {
							$('.toRight').show()
						}
					});
					$('.speciesMap .uu_map>li').on('click', function() {
						var index_name = $(this).text();
						$(this).addClass('actived').siblings().removeClass('actived');
						$('#allmap').show();
						$("#nothmap").hide();
						b_createMap();
						getBoundary(index_name, $(this).index(), "作物种植面积比例",1);
					});
					$('.speciesMap .uu_map>li').eq(0).click();
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
	});
	// 生产
	$("#li3").on("click", function() {
		$('div.map').eq(1).show().siblings('.map').hide();
		$('#nothmap').hide();
		$('.fir_head_r_search').hide();
		$('.fir_head_r_sanji').show();
		$(".getinfo").attr("data-index", "2").show();
		$('div.map').eq(1).removeClass('species');
		// console.log($('div.map').eq(1)[0].className);
		loadShow();
		//  获取信息 接口
		$.ajax({
			url: http + "listDistribution",
			type: "post",
			contentType: "application/json",
			cache: false,
			data: JSON.stringify({
				"ckuid": sessionStorage.getItem('ckuid'),
				"cksid": sessionStorage.getItem('cksid'),
				"d_type": "1" // 1 生产   2 物种
			}),
			success: function(data) {
				removeload()
				if(data.state == 0) {
					var html = template("city", data);
					$(".speciesMap .uu_map").html(html);
					var len = data.object.length + 1;
					var marRigh = (parseInt($('.uu_map li:eq(0)').outerWidth()) + parseInt($('.uu_map li').eq(0).css('margin-left'))) * len;
					$('.uu_map').css('width',marRigh +'px');
					var oTr= marRigh - $(window).innerWidth();
					$('.uu_map_out ').scrollLeft(0);
					if(oTr>0){
						$('.toRight').show();
					}else{
						$('.toRight').hide();
					}
					$('.uu_map_out').on('scroll', function() {
						if($('.uu_map_out ').scrollLeft() >= oTr) {
							$('.toRight').hide()
						} else {
							$('.toRight').show()
						}
					});
					rootData = data.object
					$('.speciesMap .uu_map>li').on('click', function() {
						var index_name = $(this).text();
						$(this).addClass('actived').siblings().removeClass('actived');
						$('#allmap').show();
						$('#nothmap').hide();
						b_createMap();
						getBoundary(index_name, $(this).index(), "作物生产进度",2);
					
					});
					
					$('.speciesMap .uu_map>li').eq(0).click();
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
	});
	//预警
	$('#li4').on('click', function() {
		var hasClick=1;//区分是否默认展示
		var haschege=1;
		$('div.map').eq(2).show().siblings('.map').hide();
		$('.fir_head_r_sanji').show();
		$('.fir_head_r_search').hide();
		$('.earlyWarn .police_map').empty();
		$('.uu_map_out').append("<span class='search_l_more' data-more='1' data-pro='' data-city='' data-qu=''>更多</span>")
		//$(".getinfo").attr("data-index","3").hide();
		loadShow();
		$.ajax({
			url: http + "listClass1",
			type: "post",
			contentType: "application/json",
			cache: false,
			data: JSON.stringify({
				"ckuid": sessionStorage.getItem('ckuid'),
				"cksid": sessionStorage.getItem('cksid'),
				"c_type": 3
			}),
			success: function(data) {
				removeload();
				// 渲染页面
				if(data.state == 0) {
					$('.search_menus').empty().append("<li dataVal='0'>全部预警信息分类</li>")
					$(data.object).each(function(i, el) {//一级分类
						$('.search_menus').append("<li dataVal=" + data.object[i].c_id + ">" + data.object[i].c_name + "</li>")
						//$('.search_list').append("<li dataVal=" + data.object[i].c_id + ">" + data.object[i].c_name + "</li>")
					});
					var opH = $('.searchList p').outerHeight();
					var oulH = $('.search_menus li').outerHeight();
					var leng = $('.search_menus li').length;
					//$('.searchL_inner').height((opH+oulH*leng)+'px');
					$(document).on("click", function(e){
						var target = $(e.target);
						if(target.closest(".searchL_inner").length == 0) {
							$('.search_menus').hide();
						};
						e.stopPropagation();
					});
					
					//显示全部
					$('.search_l_more').click(function() {
						 $('.search_l_more').attr('data-more',1)
							$('.searchList').show().siblings().hide();
							$('.fir_head_l').hide()
							$('#fir_head_r_sanji').hide();	
							$('.search_menus>li').eq(0).click();
					});
					
					
					$('.experDetail_back').click(function(){
						$('.searchList').hide();
						$('.earlyWarn').show();
						
						$('.fir_head_l').show()
						$('#fir_head_r_sanji').show();	
					})
					$('.searchList p').click(function() {
						$('.search_menus').show();
					})
					$('.search_menus>li').on('click', function() {
						$(this).addClass('actived').siblings().removeClass('actived');
						$('.searchList p').text($(this).text());
						$('.search_menus').hide();
						if($(this).attr('dataVal')){
							loadShow();
							var Obj=new Object();
							Obj.ckuid=sessionStorage.getItem('ckuid');
							Obj.cksid=sessionStorage.getItem('cksid');
							Obj.m_type=3;
							Obj.m_class=$(this).attr('dataVal');
							if($('.search_l_more').attr('data-more')==2){//通过区域查询
								Obj.m_province=$('.search_l_more').attr('data-pro');
								Obj.m_city=$('.search_l_more').attr('data-city');
								Obj.m_district=$('.search_l_more').attr('data-qu');
							}
							$.ajax({
								url: http + "listMessage",
								type: "post",
								contentType: "application/json",
								cache: false,
								data: JSON.stringify(Obj),
								success: function(data) {
									 removeload();
									// 渲染页面
									if(data.state == 0) {
										if(data.object!=null){
											$('.searchwar_List').empty();
											//遍历数据 
											$(data.object).each(function(i, el) {
												// 由于市有多个,再次遍历
												data.object[i].citys.forEach(function (item, index) {
													// 由于地区有多个,再次遍历
													item.districts.forEach(function (ite, ind) {
														// 由于警告有多个,再次遍历
														ite.warnings.forEach(function (item1, index1) {
															$('.searchwar_List').append("<li data-address=" + el.province + item.city + ite.district + " data-content=" + item1.m_content + ">" + el.province + item.city + ite.district + item1.m_content + "</li>")
														})
													});
												});
											})
											// console.log(addresses);
											$('.searchwar_List>li').on('click', function() {
												$('.uu_map_out').scrollLeft(0);
												hasClick+=1;//不是默认展示
												/*if($('.search_l_more').attr('data-more')==2){
													haschege+=1;
												}*/
												$('.searchList').hide().siblings('.earlyWarn').show();
												//$('.search_l_more').remove();
												$('.earlyWarn .police_map').html('<li>'+$(this).attr('data-content')+'</li>');
												d_createMap();
												getBoundary($(this).data('address'), '-1', $(this).attr('data-content'),1);
												//滚动
												var marRigh = (parseInt($('.police_map li:eq(0)').outerWidth()) + parseInt($('.police_map').eq(0).css('padding-left')));
												var oTr= marRigh - $(window).innerWidth();
												if(oTr>0){
													$('.toRightPolice').show();
												}else{
													$('.toRightPolice').hide();
												}
												$('.police_map').on('scroll', function() {
													if($('.police_map').scrollLeft() >= oTr) {
														$('.toRightPolice').hide()
													} else {
														$('.toRightPolice').show()
													}
												});
												//滚动
												$('.fir_head_l').show();
												$('#fir_head_r_sanji').show();
											});
											if(hasClick==1){
												$('.searchwar_List>li').eq(0).click();
											}
											if($('.search_l_more').attr('data-more')==2&&haschege==1){
													$('.searchwar_List>li').eq(0).click();
												}
											$('#nowarmap').hide();
											$('#warmap').show();
										}else{
											$('.searchwar_List').empty().append('<li>暂无数据</li>');
											$(".earlyWarn .police_map").html('<li>暂无数据</li>');
											$('.toRightPolice').hide();
											$('#warmap').hide();
											$('#nowarmap').show();
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
							                },function(){
							                    window.location.href='./login.html'
							                });
												return 
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

								},
								error: function() {
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
							})
						}
					});
						$('.search_menus>li').eq(0).click();
					
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
		})
	})

});
//查找省
function listProvice() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	$.ajax({
		url: http + "listProvice",
		type: "post",
		contentType: "application/json",
		headers: {
			'Content-type': 'application/json;charset=UTF-8'
		},
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.success) {
				$("#s1").empty();
				for(var i = 0; i < data.object.length; i++) {
					var str = '<option data-id=' + data.object[i].ar_id + '>' + data.object[i].a_name + '</option>';
					$("#s1").append(str);
				}
				var ar_id = data.object[0].ar_id;
				listCity(ar_id);
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
				return false
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
//查找市
function listCity(ar_id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.ar_id = ar_id;
	$.ajax({
		url: http + "listCity",
		type: "post",
		contentType: "application/json",
		headers: {
			'Content-type': 'application/json;charset=UTF-8'
		},
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.success) {
				$("#s2").empty();
				for(var i = 0; i < data.object.length; i++) {
					var str = '<option data-id=' + data.object[i].ar_id + '>' + data.object[i].a_name + '</option>';
					$("#s2").append(str);
				}
				var ar_id = data.object[0].ar_id;
				listDistrict(ar_id);
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
				return false
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
//查找区
function listDistrict(ar_id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.ar_id = ar_id;
	$.ajax({
		url: http + "listDistrict",
		type: "post",
		contentType: "application/json",
		headers: {
			'Content-type': 'application/json;charset=UTF-8'
		},
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.success) {
				$("#s3").empty();
				for(var i = 0; i < data.object.length; i++) {
					var str = '<option data-id=' + data.object[i].ar_id + '>' + data.object[i].a_name + '</option>';
					$("#s3").append(str);
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
				return false
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
				var num = 0
				$(data.object).each(function(i, el) {
					str += '<div class="points"><p class="treeleft_head pinlineHome" data_zoom="' + data.object[i].zoom + '">' + data.object[i].tp_name + '</p>'
					$(data.object[i].rank).each(function(l, el) {
						str += '<ul class="treeleft_second"><li class="treeleft_student"><span class="leftTops"></span><span class="leftLies">' + data.object[i].rank[l].tp_name + '</span><ul class="treeleft_stu_lists">'
						$(data.object[i].rank[l].rank).each(function(y, el) {
							num++;
							str += '<li class="treeleft_stu_menu"><span class="treeleft_stu_m_lline"></span><div class="treetwo"><img src="./img/treen/online.png" class="treetwoImg"/><span class="treeleft_stu_m_name pinline1" nums="' + num + '" data-tp_id="' +
								data.object[i].rank[l].rank[y].tp_id + '" data-supervisername=" ' + data.object[i].rank[l].rank[y].supervisername +
								'"data-state="' + data.object[i].rank[l].rank[y].state + '" data-producername="' + data.object[i].rank[l].rank[y].producername + '" data-name=" ' + data.object[i].rank[l].rank[y].name + '" data-exportorname=" ' +
								data.object[i].rank[l].rank[y].exportorname + '" data-x="' + data.object[i].rank[l].rank[y].x + '" data-y="' + data.object[i].rank[l].rank[y].y + '" data-deviceId="' +
								data.object[i].rank[l].rank[y].deviceId + '" data-ip="' + data.object[i].rank[l].rank[y].ip + '" data-port="' + data.object[i].rank[l].rank[y].port + '">' +
								data.object[i].rank[l].rank[y].tp_name + '</span></div><ul class="treeleft_stu_cameras" style="display:none">'
							$(data.object[i].rank[l].rank[y].rank).each(function(z, el) {
								str += '<li class="treeleft_stu_camera" class="treeleftHasdate"><span class="fatherLine"></span><span class="treeHasdata" data-ip="'+data.object[i].rank[l].rank[y].rank[z].ip+'" data-deviceId="'
								+data.object[i].rank[l].rank[y].rank[z].deviceId+'" data-tp_id="'+data.object[i].rank[l].rank[y].rank[z].tp_id+'">' + data.object[i].rank[l].rank[y].rank[z].tp_name + '</span></li>'
							})
							str += '</ul></li>';
							/*	未显示个数*/
						})
						str += '</ul></li></ul>'
					})
					/*str += '<li class="treeleft_stu_last"><span id="">[3号] 王大富 草莓</span></li>'*/
					str += '</div>'
					$(".treeleft").empty().append(str);
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
					$('.pinlineHome').click();
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
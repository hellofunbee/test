$(function(){
	var allpro;
    listPoint();
	//创建和初始化地图函数：
	$('.shebei').click(function(){
		$(this).find('.left').toggle();
	})
	$('.allArea').click(function(){
		$(this).find('.allAreaOuter').show()
	});
	$('.allAreaqucon').click(function(){
		$(this).siblings('ul').show()
	});
	$(document).on('click','.delectexsel',function(){
		var newId = $(this).attr('data-id');
		var _this=$(this);
		$.ajax({
			url:http+'/deleteDistribution',
			type:'post',
			contentType:'application/json',
			cache:false,
			data:JSON.stringify({
				ckuid:sessionStorage.getItem('ckuid'),
				cksid:sessionStorage.getItem('cksid'),
				d_id:newId,
			}),
			success:function(data){				
				swal({
                        title: data.msg,
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#30862B",
                        timer: 2000,
                   		
                   },
					function(){
						_this.parent().remove()
					}
				);
			},
			error:function(){
				swal({
                    title: data.msg,
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
              })
			}
	})
	});
	$(document).on('click','.shujudao',function(){
		if($(this).attr('data-mo')=='2'){
			$('.outerInfoSc').show();
			$('.outerInfo').hide();
		}else {
			$('.outerInfo').show();
			$('.outerInfoSc').hide();
		}		
	});
	$(document).on('click','.outerInfoArT_r',function(){
		$('.outerInfo').hide();
		$('.outerInfoSc').hide();
	});
	
	
	
	$(document).on('click','.shengchanli ul li',function(){	
		$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
		$(this).parents('ul').hide();
	});
	$(document).on('click','.yujing ul li',function(){	
		$(this).parents('ul').siblings('.allAreaqucon').find('input').val($(this).text());
		$(this).parents('ul').hide();
	});

	$(document).mouseup(function(e){
	  var _con = $('.allAreaOuter'),shengchanli = $('.shengchanli').find('ul'),yujing=$(".yujing").find('ul');//区域
	  
	  if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
	    _con.hide();
	  };
	  if(!shengchanli.is(e.target) && shengchanli.has(e.target).length === 0){ // Mark 1
	    shengchanli.hide();
	  };
	  if(!yujing.is(e.target) && yujing.has(e.target).length === 0){ // Mark 1
	    yujing.hide();
	  };
	});
	$('#upchuan').change(function(){
		var obj = new FormData(); 
		obj.append('d_content',$('#upchuan').get(0).files[0]);
		obj.append('ckuid',sessionStorage.getItem('ckuid'));
		obj.append('cksid',sessionStorage.getItem('cksid'));
		obj.append('d_type',$(".getinfo").attr("data-index"));////设备分布 0  物种分布2  生产1  报警3
		obj.append('d_state','1');	
		obj.append('d_province',$("#s1 option:selected").attr("data-id"));
		obj.append('d_city',$("#s2 option:selected").attr("data-id"));
		obj.append('d_district',$("#s3 option:selected").attr("data-id")); 
        $.ajax({
			url:http+'addDistribution2',
			type:'post',
			entype:'multipart/form-data',
			processData:false,
			contentType:false,
			data:obj,
			cache:false,
			success:function(data){
				if(data.success){
					 swal({
                            title: data.msg,
                            text: "2秒后关闭",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#30862B",
                            timer: 2000
                        });
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
	});
		$('#upScchuan').change(function(){
		var obj = new FormData(); 
		obj.append('d_content',$('#upScchuan').get(0).files[0]);
		obj.append('ckuid',sessionStorage.getItem('ckuid'));
		obj.append('cksid',sessionStorage.getItem('cksid'));
		obj.append('d_type',$(".getinfo").attr("data-index"));////设备分布 0  物种分布2  生产1  报警3
		obj.append('d_state','1');	
//		obj.append('d_province',$("#sc1 option:selected").attr("data-id"));
//		obj.append('d_city',$("#sc2 option:selected").attr("data-id"));
//		obj.append('d_district',$("#sc3 option:selected").attr("data-id")); 
		obj.append('d_procedure',$("#sc4 option:selected").attr("data-id")); 
        $.ajax({
			url:http+'addProductionProcess',
			type:'post',
			entype:'multipart/form-data',
			processData:false,
			contentType:false,
			data:obj,
			cache:false,
			success:function(data){
				if(data.success){
					
					
					
					 swal({
                            title: data.msg,
                            text: "2秒后关闭",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#30862B",
                            timer: 2000
                        });
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
	});
	$(document).on('click','.outerInfoArT_l',function(){
		if($(this).attr('data-num')=='1'){
			document.location.href = http+"getExcelFile?d_type=1"
		}else {
			document.location.href = http+"getExcelFile?d_type=2"
		}
	})
	
	$(document).on('click','.fa2Out',function(e){
		  e.stopPropagation();
		  $('.pinline1').removeClass('active');
		  $('.pinline').removeClass('active');
		 $(this).find('.pinline1').addClass('active');
	});
    setTimeout(function(){
        var right=$(".right").outerHeight();
        var top_h=$(".r_top").outerHeight();
        var nav_h=$(".r_mid").outerHeight();
        $(".map").outerHeight(right)
    },30);
    // 切换地图
    var map = $(".map");
    var lis = $(".ul_top li");
    for(var i=0;i<lis.length;i++){
        $(lis[i]).on("click", function () {
        	if(i==0){
        		$('.selectArea').hide()
        	}else{
        		$('.selectArea').show()
        	}
            $(this).siblings("li").attr("class","");
            $(this).attr("class","active");
            var a = $(this).attr("data-num");
            $(map[a]).siblings("div>.map").css("display","none");
        });
    }
    // -------------------物种分布图
  $('.canle01').on('click',function(){
      $(".textbox").css("display","none")
  })
//  提交信息接口--------------------------------
    $("#btn_info").on("click", function () {
        if($(".getinfo").attr("data-index")==1||$(".getinfo").attr("data-index")==2){
            var o = {};
            o.ckuid = sessionStorage.getItem('ckuid');
            o.cksid = sessionStorage.getItem('cksid');
            o.d_type = $(".getinfo").attr("data-index");
            o.d_state = '1';
            o.d_province = $("#s1 option:selected").attr("data-id");
            o.d_city = $("#s2 option:selected").attr("data-id");
            o.d_district = $("#s3 option:selected").attr("data-id");
            var lis = $("#data_info>li");
            var arr = [];
            for(var i = 0 ; i<lis.length;i++){
                var obj= {};
                var content =  lis[i].children[0].value;
                var value =  lis[i].children[1].value;
                obj.d_content = content;
                obj.d_value = value;
                arr.push(obj);
            }
            o.content = arr;
            loadShow()				
            $.ajax({
                url:http + "addDistribution",
                type:"post",
                contentType: "application/json",
                cache: false,
                data:JSON.stringify(o),
                success: function (data) {
                	parent.removeload();
                    if(data.state==0){
                        swal({
                            title: data.msg,
                            text: "2秒后关闭",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#30862B",
                            timer: 2000
                        });
                        active().click();
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
                        title: '请求失败,请重新尝试',
                        text: "",
                        type: "warning",
                        showCancelButton: false,
                        confirmButtonColor: "#30862B",
                        confirmButtonText: "确定",
                        closeOnConfirm: false
                    });
                    return;
                }
            });
            // 关闭窗口   在清零
            $(".textbox").css("display","none")
            for(var i = 0 ; i<lis.length;i++){
                lis[i].children[0].value = "";
                lis[i].children[1].value = "";
            }
        }else if($(".getinfo").attr("data-index")==3){
            var obj={}
            obj.ckuid = sessionStorage.getItem('ckuid');
            obj.cksid = sessionStorage.getItem('cksid');
            obj.m_type='3'
            obj.m_title=$('.textbox').find('input.biaoti').val()
            if(!obj.m_title){
                swal({
                    title: '请填写标题',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
            obj.m_content=$('#textarea').val();
            if(!obj.m_content){
                swal({
                    title: '请填写预警信息',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return;
            }
            obj.d_province = $("#s1 option:selected").attr("data-id");
            obj.d_city = $("#s2 option:selected").attr("data-id");
            obj.d_district = $("#s3 option:selected").attr("data-id");
            obj.m_class=$('#fenlei').find('option:selected').val();
				parent.loadShow()
            $.ajax({
                url:http + "addMessage",
                type:"post",
                contentType: "application/json",
                cache: false,
                data:JSON.stringify(obj),
                success: function (data) {
                	parent.removeload();
                    if(data.state==0){
                        alert(data.msg)
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
            });
        }
    })
// 新建框的设置---------------------------------
//  $(".getinfo").on("click", function () {
//      $(".textbox").show();
//      var a = $(this).attr("data-index");
//      if(a==2){
//          $('.textbox').find('.tet_content1').hide()
//          $(".textbox").find('.text_con').show()
//          $(".textbox").find('.text_con1').hide()
//          $(".textbox").find('.tet_title').text('新建物种分布信息')
//          $(".textbox").find('.pop_tit_1').text('物种分布所在省')
//          $(".textbox").find('.pop_tit_2').text('物种分布所在市')
//          $(".textbox").find('.pop_tit_3').text('物种分布所在区')
//      }else if(a==1){
//          $('.textbox').find('.tet_content1').hide()
//          $(".textbox").find('.text_con').show()
//          $(".textbox").find('.text_con1').hide()
//          $(".textbox").find('.tet_title').text('新建生产分布信息')
//          $(".textbox").find('.pop_tit_1').text('生产分布所在省')
//          $(".textbox").find('.pop_tit_2').text('生产分布所在市')
//          $(".textbox").find('.pop_tit_3').text('生产分布所在区')
//      }else if(a==3){
//          $('.textbox').find('.tet_content1').show()
//          $(".textbox").find('.tet_title').text('新建预警信息')
//          $(".textbox").find('.pop_tit_1').text('预警地所在省')
//          $(".textbox").find('.pop_tit_2').text('预警地所在市')
//          $(".textbox").find('.pop_tit_3').text('预警地所在区')
//          $(".textbox").find('.text_con').hide()
//          $(".textbox").find('.text_con1').show()
//      }
//  });
//  $(".xxx").on("click", function () {
//      $(".textbox").css("display","none")
//  })
	//  省 -------- 市 --------- 县-------------------
//  $("#s1").change(function(){
//      var ar_id=$("#s1 option:selected").attr("data-id");
//   
//      if(ar_id=="710000"||ar_id=="810000"||ar_id=="820000"){
//          $("#s2,#s3").parent().parent().hide()
//      }else{
//          $("#s2,#s3").parent().parent().show();
//          listCity(ar_id)
//      }
//  });
//  $("#s2").change(function(){
//      var ar_id=$("#s2 option:selected").attr("data-id");
//      listDistrict(ar_id)
//  });
//查找省
    function listProvice(){
        var obj = new Object();
        obj.ckuid=sessionStorage.getItem('ckuid');
        obj.cksid=sessionStorage.getItem('cksid');
        parent.loadShow()
        $.ajax({
            url: http+"listProvice",
            type: "post",
            contentType: "application/json",
            headers: {'Content-type': 'application/json;charset=UTF-8'},
            data:JSON.stringify(obj),
            cache: false,
            success: function (data) {
            	parent.removeload();
                if(data.success){
                	
                    $(".allAreasheng").find('ul').empty();
                    for(var i=0;i<data.object.length;i++){
                        var str='<li data-id='+data.object[i].ar_id+'>'+data.object[i].a_name+'</li>';
                        $(".allAreasheng").find('ul').append(str);
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
        });
    }
//查找市
    function listCity(ar_id){
        var obj = new Object();
        obj.ckuid=sessionStorage.getItem('ckuid');
        obj.cksid=sessionStorage.getItem('cksid');
        obj.ar_id=ar_id;
        parent.loadShow()
        $.ajax({
            url: http+"listCity",
            type: "post",
            contentType: "application/json",
            headers: {'Content-type': 'application/json;charset=UTF-8'},
            data:JSON.stringify(obj),
            cache: false,
            success: function (data) {
            	parent.removeload();
                if(data.success){
                    $(".allAreashi").find('ul').empty();
                    for(var i=0;i<data.object.length;i++){
                        var str='<li data-id='+data.object[i].ar_id+'>'+data.object[i].a_name+'</li>';
                        $(".allAreashi").find('ul').append(str);
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
        });
    }
//查找区
    function listDistrict(ar_id){
        var obj = new Object();
        obj.ckuid=sessionStorage.getItem('ckuid');
        obj.cksid=sessionStorage.getItem('cksid');
        obj.ar_id=ar_id;
        loadShow()				
        $.ajax({
            url: http+"listDistrict",
            type: "post",
            contentType: "application/json",
            headers: {'Content-type': 'application/json;charset=UTF-8'},
            data:JSON.stringify(obj),
            cache: false,
            success: function (data) {
            	parent.removeload();
                if(data.success){
                    $(".allAreaqu").find('ul').empty();
                    for(var i=0;i<data.object.length;i++){
                        var str='<li data-id='+data.object[i].ar_id+'>'+data.object[i].a_name+'</li>';
                        $(".allAreaqu").find('ul').append(str);
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
                    return false;
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
    //listProvice();
    function chasheng(){
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
            		allpro=data.object;
            		data.object.forEach(function(item,index,arr){
	            		var str='<li data-id='+item.d_province+'>'+item.province+'</li>';
	                		$(".allAreasheng").find('ul').append(str);  	                		
                	});
                	chashi(allpro,data.object[0].d_province)
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
    }
    
    function chashi(obj,id){
    	var oindex=0;
    	var allqu=[];
    	obj.forEach(function(item,index,arr){
    		if(item.d_province==id){
    			oindex=index;
    			item.d_province.citys.forEach(function(ite,i){
					var str='<li data-id='+ite.d_city+'>'+ite.city+'</li>';
        			$(".allAreashi").find('ul').append(str);
				});
				allqu=item.d_province.citys[0]
    		}
    	});
    	chaqu(obj,oindex,allqu)
    }; 
    function chaqu(obj,i){
    	obj[oindex].forEach(function(item,index,arr){
    		if(item.d_province==id){
    			item.d_province.citys.forEach(function(ite,i){
					var str='<li data-id='+ite.d_city+'>'+ite.city+'</li>';
        			$(".allAreashi").find('ul').append(str);
				});
    		}
    	})
    };
    function listPoint(){
    	loadShow()				
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
            	parent.removeload();
                if(data.state==0){
                    //sessionStorage.setItem('objData',JSON.stringify(data.object))
                    var str="";var num=0;
                    $(data.object).each(function(i,el){
                        str+="<div class='point'><div class='icon student'> <img src='./img/shipin/ico_home.png' alt=''/> </div> <p class='pinline pinlineHome active' style='font-size: 0.12rem' data_zoom='"+data.object[i].zoom+"'> "+data.object[i].tp_name+"</p>"
                        $(data.object[i].rank).each(function(l,el){
                            str+=" <ul class='navigation' style='font-size: 0.12rem'> <li> <div class='icon fa1'> <img src='./img/shipin/ico_group.png' alt=''/> </div> <p  class='pinline'>"+data.object[i].rank[l].tp_name+"</p>"
                            $(data.object[i].rank[l].rank).each(function(y,el){
                            	num++;
                                str+="<ul class='tree'> <div class='fa2Out'> <li><div class='icon fa2'> <img src='./img/shipin/ico_device.png' alt=''/> </div> <p  class='pinline pinline1' nums="+num+" data-tp_id="+data.object[i].rank[l].rank[y].tp_id+" data-supervisername="+data.object[i].rank[l].rank[y].supervisername+" data-state="+data.object[i].rank[l].rank[y].state+" data-producername="+data.object[i].rank[l].rank[y].producername+" data-name="+data.object[i].rank[l].rank[y].name+" data-exportorname="+data.object[i].rank[l].rank[y].exportorname+" data-x="+data.object[i].rank[l].rank[y].x+" data-y="+data.object[i].rank[l].rank[y].y+" data-deviceId="+data.object[i].rank[l].rank[y].deviceId+" data-ip="+data.object[i].rank[l].rank[y].ip+" data-port="+data.object[i].rank[l].rank[y].port+">"+data.object[i].rank[l].rank[y].tp_name+"</p></div> <ul class='tree2'> "
                              
                                str+="</ul></li></ul>"
                            })
                            str+=" </li> </ul>"
                        })
                        str+="</div>"
                    })
                    $(".nav_left").empty().append(str);
//                  $('.pinlineHome').click();
                    $('.tree').on('click',function(){
	                    if($(this).siblings('ul').is(":visible")==false){
	                        $(this).siblings('ul').find('.tree2').show()
	                    }else{
	                        $(this).siblings('ul').find('.tree2').hide()
	                    }
	                })
                    $('.fa2Out').each(function(){
                    	if($(this).find('.pinline1').attr('data-state')==2){//2不在线
                    		$(this).find('img').attr('src','./img/shipin/ico_disconnect.png');
                    	}else{
                    		$(this).find('img').attr('src','./img/shipin/ico_device.png');
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
});
function active(){
    var that;
    $('.ul_top>li').each(function(){
        if($(this).hasClass('active')){
            that=$(this)
        }
    }) 
    return that;
}

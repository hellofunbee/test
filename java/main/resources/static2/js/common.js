//var http="/";
/*var http="http://192.168.0.206:8087/";*/
$(function () {
    listPoint()
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
});
//if(data.state==0){
//
//}else if(data.state==2){
//    alert("登录失效，请重新登录");
//    //window.parent.location.href="login.html";
//    return false
//} else{
//    alert(data.msg);
//}
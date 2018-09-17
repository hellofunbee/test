var sexarr=['','男','女']
$(function(){
    if(!sessionStorage.getItem('ckuid')||sessionStorage.getItem('ckuid')==undefined||sessionStorage.getItem('ckuid')==null){
        swal({
            title: '登录失效,请重新登录',
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
    }
    setTimeout(function(){
        var right=$(".right").outerHeight();
        var top_h=$(".r_top").outerHeight();
        var nav_h=$(".r_mid").outerHeight();
        $(".r_bot").outerHeight(right-top_h-nav_h)
    },80);
    $(".xxx").on("click", function () {
        $(this).parents('.textbox').css("display","none");
    });
    $('.textbox').find('button.close').on('click',function(){
        $(this).parents('.textbox').css("display","none");
    })
    $('#btn_info').on('click',function(){
        addAnswerQuestion(1)
    })
    $('.search').on('click',function(){
        var str=$(this).siblings('input').val();
        if(str){
            $('.ul_bot>li').each(function(){
                 if($(this).find('.bot_name').text().indexOf(str)>=0){
                     $(this).show()
                 }else{
                     $(this).hide()
                 }
            })
        }else{
            $('.ul_bot>li').show();
        }

    })
    $('#btn').on('click',function(){
        //普通
        if($(this).attr('data-s')==1){
            $(".textbox1").css("display","block");
        }else{
            $('.textbox4').show()
        }
    })
    $('#btn1').on('click',function(){
         $('.textbox2').show()
    })
    $('#btn2').on('click',function(){
        $('.textbox3').show()
    })
    $('#btn3').on('click',function(){
        $('.textbox5').show()
    })
    $('.addS').on('click',function(){
         $('.finishedS>li:last-child').after('<li> <p>服务过的项目</p> <input type="text" maxlength="32" placeholder="输入项目服务名称，不超过32字"> <span class="removeLi">-</span> </li>')
    })
    $(document).on('click','.removeLi',function(){
        $(this).parent().remove()
    })
    $('#btn_info1').on('click',function(){
        updateUser(1)
    })
    $('#btn_info2').on('click',function(){
        updateUser(2)
    })
    $('#btn_info3').on('click',function(){
        addAnswerQuestion(2)
    })
    $('.textbox5').find('span.sj').on('click',function(){
        var s=$(this).attr('data-s');
        if(s==1){
            $(this).css('transform','rotate(-90deg)').attr('data-s',2).parent().siblings('.class').show()

        }else{
            $(this).css('transform','rotate(0deg)').attr('data-s',1).parent().siblings('.class').hide()
        }
    })
    $('.textbox5').find('div.sex>span').on('click',function(){
       $(this).addClass('active').siblings().removeClass('active')
    })
    $('.class>span.closeClass').on('click',function(){
       $(this).parent().siblings('.bg').find('span.sj').click();
    })
    $(document).on('click','i.checkState',function(){
       setTimeout(function(){
           class2List()
       },50)
    })
    $(document).on('click','.allI',function(){
        var n=$(this)
        return function (n) {
            setTimeout(function(){
                console.log(n.siblings('input').prop('checked'))
                if(n.siblings('input').prop('checked')){
                    n.parents('li').find('.ul_class2_2').find('input').prop('checked','checked')
                }else{
                    n.parents('li').find('.ul_class2_2').find('input').removeAttr('checked')
                }
            },50)
        }(n)
    })
    $('#btn_info4').on('click',function(){
        updateUser()
    })
    $('#changeHeadImg').on('change',function(){
        if($(this).val()){
            var obj = new FormData();
            obj.append('ckuid',sessionStorage.getItem('ckuid'))
            obj.append('cksid',sessionStorage.getItem('cksid'))
            obj.append('picture',$('#changeHeadImg').get(0).files[0])
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
                        $('#changeHeadImg').siblings('img').attr('src',data.object)
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
    listClass1()
});
function active(){
    var state;
    $('.textbox4>div.a_1>ul>li').each(function(){
        if($(this).hasClass('active')){
            state=$(this)
        }
    })
    return state
}
function listProfessor(id,s){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    if(s==1){
        obj.class2id=id
    }else{
        obj.u_id=id
    }
    parent.loadShow()
    $.ajax({
        url: http+"listProfessor",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if(data.state==0){
                if(s==1){
                    $('.r_mid').show()
                    $('.ul_bot').empty()
                    if(data.object==null){
                        swal({
                            title: data.msg,
                            text: "2秒后关闭",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#30862B",
                            timer: 2000
                        });
                        return
                    }else{
                        $(data.object).each(function(i,el){
                            $('.ul_bot').append(" <li data-id="+data.object[i].u_id+"> <a href='javascript:;' class='zj_data' > <div class='head' style='background: url("+data.object[i].headimgurl+")no-repeat center;background-size: cover'></div> <div class='bot_info'> <p class='bot_name'>"+data.object[i].u_uname+"</p> <p class='master'>"+data.object[i].position+"</p> <p>专业领域&nbsp;  <span>"+data.object[i].field+"</span></p> <p>电子邮箱&nbsp;   <span>"+data.object[i].u_email+"</span></p> <p>服务对象&nbsp;   <span>"+(data.object[i].seruser.length)+"个</span></p> </div> </a> </li>")
                        })
                        $('.ul_bot>li').on('click',function(){
                            sessionStorage.setItem('u_id',$(this).attr('data-id'))
                            listProfessor($(this).attr('data-id'),2)
                            listQuestion($(this).attr('data-id'))
                            listAnswerQuestion($(this).attr('data-id'))
                            getProfessorArticle($(this).attr('data-id'))
                            $(".ul_bot").hide();
                            $('.r_mid').hide()
                            $(".info").show();
                        })
                    }
                }else{
                    var user_type=sessionStorage.getItem('ckuid');
                    var user_id=sessionStorage.getItem('u_id');
                    if(user_type!=user_id){
                       $('#btn').text('我要提问').attr('data-s',1)
                        $('#btn1').hide()
                        $('#btn2').hide()
                        $('#btn3').hide()
                    }else{
                        $('#btn').text('回答').attr('data-s',2)
                        $('#btn1').show()
                        $('#btn2').show()
                        $('#btn3').show()
                        $('.textbox2 .finishedS').empty()
                        $('.textbox3 .finishedS').empty()
                        $(data.object[0].serproject).each(function(i,el){
                            $('.textbox2 .finishedS').append(' <li> <p>服务中的项目</p> <input value='+data.object[0].serproject[i].text+' type="text" maxlength="32" placeholder="输入项目服务名称，不超过32字"> <span class="removeLi">-</span> </li>')
                        })
                        $(data.object[0].seredproject).each(function(y,el){
                            $('.textbox3 .finishedS').append(' <li> <p>服务过的项目</p> <input value='+data.object[0].seredproject[y].text+' type="text" maxlength="32" placeholder="输入项目服务名称，不超过32字"> <span class="removeLi">-</span> </li>')
                        })
                        $('#userList>li').each(function(){
                            if($(this).index()==0){
                                $(this).find('img').attr('src',data.object[0].headimgurl)
                            }
                            if($(this).index()==1){
                                $(this).find('.name').val(data.object[0].u_uname)
                                if(data.object[0].sex==1){
                                    $(this).find('.sex>span').eq(0).addClass('active').siblings().removeClass('active')
                                }else{
                                    $(this).find('.sex>span').eq(1).addClass('active').siblings().removeClass('active')
                                }
                            }
                            if($(this).index()==2){
                                $(this).find('input').val(data.object[0].u_phone)
                            }
                            if($(this).index()==3){
                                $(this).find('input').val(data.object[0].field)
                            }
                            if($(this).index()==4){
                                $(this).find('input').val(data.object[0].u_phone2)
                            }
                            if($(this).index()==5){
                                $(this).find('input').val(data.object[0].position)
                            }
                            if($(this).index()==6){
                                $(this).find('input').val(data.object[0].u_nickname)
                            }
                            if($(this).index()==7){
                               $(this).find('.class>ul>li').each(function(){
                                   if(data.object[0].class1id.indexOf($(this).find('i').attr('data-id'))>=0){
                                       $(this).find('i').siblings('input').prop('checked','checked')
                                   }
                               })
                                    class2List(data.object[0].class2id)
                            }
                            if($(this).index()==9){
                                $(this).find('textarea').val(data.object[0].detail)
                            }
                            if($(this).index()==10){
                                if(data.object[0].u_check==1){
                                    $(this).find('span').text('未审核')
                                }else{
                                    $(this).find('span').text('审核通过')
                                }
                            }
                        })
                    }
                    $('.infoLeft>img').attr('src',data.object[0].headimgurl)
                     $('.info_name>span.user_name').text(data.object[0].u_uname)
                     /*$('.info_name>span.user_age').text(data.object[0].u_age)*/
                     $('.info_name>span.user_sex').text(sexarr[data.object[0].sex])
                     $('.info_name>span.user_position').text(data.object[0].position)
                     $('.field').text(data.object[0].field)
                     $('.u_email').text(data.object[0].u_email)
                     $('.detail').text(data.object[0].detail)
                    $('.u_1').empty()
                    $(data.object[0].serproject).each(function(a,el){
                        $('.u_1').append( '<li><span></span>'+data.object[0].serproject[a].text+'</li>')
                    })

                    $('.u_2').empty()
                    $(data.object[0].seredproject).each(function(b,el){
                        $('.u_2').append( '<li><span></span>'+data.object[0].seredproject[b].text+'</li>')
                    })
                    $('.u_3').empty()
                    $(data.object[0].seruser).each(function(c,el){
                        $('.u_3').append( '<li><span></span>'+data.object[0].seruser[c].text+'</li>')
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
                title: "请求失败，请重新尝试",
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
function listClass1(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.c_type='1';
    $.ajax({
        url: http+"listCMSClass1",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if(data.state==0){
                $('#select_1').empty()
                if(data.object==null){
                    swal({
                        title: '没有一级相关信息',
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#30862B",
                        timer: 2000
                    });
                }else{
                    $('#select_1').empty()
                    $('.textbox5').find('.class1>ul').empty();
                    $(data.object).each(function(i,el){
                        $('#select_1').append('<option value='+data.object[i].c_id+'>'+data.object[i].c_name+'</option>')
                        $('.textbox5').find('.class1>ul').append('<li> <label> <input type="checkbox"> <i class="checkState" data-id='+data.object[i].c_id+'></i> </label> <span class="s_l">'+data.object[i].c_name+'</span> </li>')
                    })
                    listClass2($('#select_1').find('option:selected').val())
                    $('#select_1').on('change',function(){
                        listClass2($('#select_1').find('option:selected').val())
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
function listClass2(id,id1){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    if(Object.prototype.toString.call(id)=='[object Array]'){
        $('.textbox5').find('.class2>div').empty()
        $(id).each(function(z,el){
            var str=''
            obj.c_rid=id[z].id;
            $.ajax({
                url: http+"listCMSClass2Byrid",
                type: "post",
                contentType: "application/json",
                headers: {'Content-type': 'application/json;charset=UTF-8'},
                data:JSON.stringify(obj),
                cache: false,
                success: function (data) {
                    if(data.state==0){
                        if(data.object==null){
                        }else{
                            id[z].list=data.object
                           str+='  <ul class="ul_class2_1"> <li style="width: 100%"> <div> <label> <input type="checkbox"> <i class="allI" data-id='+id[z].id+'></i> </label> <span class="s_l">'+id[z].name+'</span> </div> <ul class="ul_class2_2"> '
                            $(id[z].list).each(function(x,el){
                                    str+=' <li> <label> <input type="checkbox" '+returnChecked(id1,id[z].list[x].c_id)+'> <i class="seconeClass" data-id='+id[z].list[x].c_id+'></i> </label> <span class="s_l">'+id[z].list[x].c_name+'</span> </li>'
                            })
                            str+=' </ul> </li> </ul>'
                            $('.textbox5').find('.class2>div').append(str)
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
        })
    }
    else{
        obj.c_rid=id;
        $.ajax({
            url: http+"listCMSClass2Byrid",
            type: "post",
            contentType: "application/json",
            headers: {'Content-type': 'application/json;charset=UTF-8'},
            data:JSON.stringify(obj),
            cache: false,
            success: function (data) {
                if(data.state==0){
                    $('#select_2').empty()
                    if(data.object==null){
                        swal({
                            title: '没有二级相关信息',
                            text: "2秒后关闭",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#30862B",
                            timer: 2000
                        });
                    }else{
                        $(data.object).each(function(i,el){
                            $('#select_2').append('<option value='+data.object[i].c_id+'>'+data.object[i].c_name+'</option>')
                        })
                        listProfessor($('#select_2').find('option:selected').val(),1)
                        $('#select_2').on('change',function(){
                            $('.ul_bot').show().siblings('.info').hide().parent().siblings('.r_mid').show()
                            listProfessor($('#select_2').find('option:selected').val(),1)
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

}
function listQuestion(id){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.aq_pid=id
    $.ajax({
        url: http + "listQuestion",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data: JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if (data.state == 0) {
                $('.u_4').empty()
                $('.textbox4 .a_1>ul').empty()
                $(data.object).each(function (i, el) {
                    $('.u_4').append("<li><span></span>"+data.object[i].aq_content+"</li>")
                })
                $(data.object).each(function(z,el){
                    $('.textbox4 .a_1>ul').append(' <li data-id='+data.object[z].aq_id+'> '+z+': '+data.object[z].aq_content+'</li>')
                })
                $('.textbox4 .a_1>ul>li').eq(0).addClass('active')
                $('.textbox4 .a_2>div.text').text($('.textbox4 .a_1>ul>li').eq(0).text().substr(3))
                $('.textbox4 .a_1>ul>li').on('click',function(){
                    $(this).addClass('active').siblings().removeClass('active')
                    var content=$(this).text()
                    $('.textbox4 .a_2>div.text').text(content.substr(3))
                })
            } else if (data.state == 2) {
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
                return
            }
        },
        error: function () {
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
function listAnswerQuestion(id){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.aq_pid=id
    $.ajax({
        url: http + "listAnswerQuestion",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data: JSON.stringify(obj),
        cache: false,
        success: function (data) {
            if (data.state == 0) {
                $('.u_5').empty()
                $(data.object).each(function (i, el) {
                    $('.u_5').append("<li> <span></span>"+data.object[i].question+"<p>"+data.object[i].answer+"</p> </li>")
                })
            } else if (data.state == 2) {
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
            }
        },
        error: function () {
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
    })
}
function addAnswerQuestion(num){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    if(num==1){
        obj.aq_pid=sessionStorage.getItem('u_id');
        obj.aq_uid=sessionStorage.getItem('ckuid');
        obj.aq_qid=0;
        obj.aq_type=0
        obj.aq_content=$('#tj_txt').val();
        if(!obj.aq_content){
            swal({
                title: "问题内容不能为空!",
                text: "2秒后关闭",
                confirmButtonText: "确定",
                confirmButtonColor: "#30862B",
                timer: 2000
            });
            return
        }
    }else{
        obj.aq_pid=sessionStorage.getItem('u_id');
        obj.aq_uid=sessionStorage.getItem('ckuid');
        obj.aq_qid=active().attr('data-id');
        obj.aq_type=2
        obj.aq_content=$('.textbox4 .ansower').val();
        if(!obj.aq_content){
            swal({
                title: "问题内容不能为空!",
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
        url: http + "addAnswerQuestion",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data: JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if (data.state == 0) {
                swal({
                    title: data.msg,
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                $('.textbox').hide()
                if(num==1){
                    listQuestion(sessionStorage.getItem('u_id'))
                }else{
                    listAnswerQuestion(sessionStorage.getItem('u_id'))
                }

            } else if (data.state == 2) {
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
function getProfessorArticle(id){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.u_id=id;
    parent.loadShow()
    $.ajax({
        url: http + "getProfessorArticle",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data: JSON.stringify(obj),
        cache: false,
        success: function (data) {
            parent.removeload()
            if (data.state == 0) {
                $('.u_6').empty()
                $(data.object).each(function(i,el){
                    var str=''
                    str+='<li> <div class="info_js"> <span class="fr" style="color: #E2846D; ">'+dealdata(data.object[i].a_time)+'</span> <h4>'+data.object[i].class1name+" - "+data.object[i].class2name+'</h4> <p>文章分类 - '+data.object[i].a_typename+'</p>'
                     $(data.object[i].a_content).each(function(y,el){
                         str+='<p style="color: #95989A;">'+data.object[i].a_content[y].text+'</p>'
                     })
                      str+='</div> <div class="info_img"> <img src='+data.object[i].a_cover+' alt="加载失败"/> </div> </li>'
                    $('.u_6').append(str)
                })
            } else if (data.state == 2) {
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
function updateUser (num){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    obj.u_id=sessionStorage.getItem('ckuid');
    if(num==1){
        obj.p_serproject=[];
        $('.textbox2 .finishedS>li').each(function(){
            var obj1={};
            obj1.file='';
            obj1.text=$(this).find('input').val()
            if(!obj1.text){
                s=false
            }else{
                obj.p_serproject.push(obj1)
            }
        })
    }else{
        obj.p_seredproject=[];
        $('.textbox3 .finishedS>li').each(function(){
            var obj1={};
            obj1.file='';
            obj1.text=$(this).find('input').val()
            if(!obj1.text){
                s=false
            }else{
                obj.p_seredproject.push(obj1)
            }
        })
    }
    var s=true;
    if(s==true){
        $.ajax({
            url: http+"updateCMSUser",
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
                    listProfessor(sessionStorage.getItem('u_id'),2)
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
    }else{
        swal({
            title: '所有项目描述不能为空',
            text: "2秒后关闭",
            confirmButtonText: "确定",
            confirmButtonColor: "#30862B",
            timer: 2000
        });
    }

}
function class2List(id){
    var classList=[];
    $('.class1>ul>li').each(function(){
        if($(this).find('input').prop('checked')){
            var id=$(this).find('i').attr('data-id')
            var name=$(this).find('.s_l').text()
            var obj=new Object();
            obj.id=id;
            obj.name=name;
            obj.list='';
            classList.push(obj)
        }else{

        }
    })
        listClass2(classList,id)
}
function updateUser(){
    var obj = new Object();
    obj.ckuid=sessionStorage.getItem('ckuid');
    obj.cksid=sessionStorage.getItem('cksid');
    $('#userList>li').each(function(i,el){
        if(i==0){
            obj.headimgurl=$('#userList>li').eq(i).find('img').attr('src')
        }
        if(i==1){
            obj.u_uname=$('#userList>li').eq(i).find('input').val()
            if(!obj.u_uname){
                swal({
                    title: '姓名不能为空',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
            obj.sex=activeSex()
        }
        if(i==2){
            obj.u_phone=$('#userList>li').eq(i).find('input').val()
            if(!obj.u_phone){
                swal({
                    title: '手机号不能为空',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
        }
        if(i==3){
            obj.field=$('#userList>li').eq(i).find('input').val()
            if(!obj.field){
                swal({
                    title: '研究领域不能为空',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
        }
        if(i==4){
            obj.u_phone2=$('#userList>li').eq(i).find('input').val()
            if(!obj.u_phone2){
                swal({
                    title: '备用联系方式不能为空',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
        }
        if(i==5){
            obj.position=$('#userList>li').eq(i).find('input').val()
            if(!obj.position){
                swal({
                    title: '职务不能为空',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
        }
        if(i==6){
            obj.u_email=$('#userList>li').eq(i).find('input').val()
            if(!obj.u_email){
                swal({
                    title: '电子邮箱不能为空',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
        }
        if(i==9){
            obj.detail=$('#userList>li').eq(i).find('input').val()
            if(!obj.detail){
                swal({
                    title: '个人介绍不能为空',
                    text: "2秒后关闭",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#30862B",
                    timer: 2000
                });
                return
            }
        }
    })
    $('.textbox5').find('.ul_class2_1>li').each(function(a,el){

    })
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
function activeSex(){
    var s='';
   $('.textbox5 .sex>span').each(function(){
       if($(this).hasClass('active')){
           s=$(this).attr('data-s')
       }
   })
    return s
}
function returnChecked(v1,v2){
    var str=''
    if(v1.indexOf(v2+'')>=0){
        str='checked'
    }
    return str
}
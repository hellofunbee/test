$(function(){
  listHomePage()
  listMessage1Bygroup(0,0,0);
  var outerWid=parseInt(($(document.body).width()-1200)/2-$('.classify').outerWidth()-28)+'px';
  var outH=$('.wrap').css('padding-top')+'px';
  $('.classify').css({'left':outerWid,'top':outH,'display':'block'});
  $('.search').on('click',function(){
    var str=$(this).siblings('input').val()
    parent.loadShow()
    setTimeout(function(){
      parent.removeload()
    },300)
    if(str){
      $('.content>li').each(function(){
        if($(this).find('p.tit').text().indexOf(str)>=0){
          $(this).show()
        }else{
          $(this).hide()
        }
          })
    }else{
      $('.content>li').show()
    }
  });
  $(document).on('click',".classify>li",function(){
  	 	$(this).addClass('color').siblings().removeClass('color');
  	 	var i=$(this).index();
      var ids=$(this).attr('data-id');
     	listMessage1Bygroup(ids,0,i);
  })
});
//查看政策 资讯消息
function listMessage1Bygroup(id1,id2,i){
  var obj = new Object();
  obj.ckuid=sessionStorage.getItem('ckuid');
  obj.cksid=sessionStorage.getItem('cksid');
  obj.m_type='4';
  parent.loadShow()
  $.ajax({
    url: http+"listMessage1Bygroup",
    type: "post",
    contentType: "application/json",
    headers: {'Content-type': 'application/json;charset=UTF-8'},
    data:JSON.stringify(obj),
    cache: false,
    success: function (data) {
    		console.log(data);
      parent.removeload()
      if(data.state==0){
          $(".classify").empty().append("<li data-id='0'>全部</li>");
          $(".new_search").empty();
          if(data.object!=null){
		          $(data.object).each(function(i,el){
		            $(".classify").append("<li data-id='"+data.object[i].c_id+"'>"+data.object[i].c_name+"</li>")
		          })
		      $(".classify>li").eq(i).addClass('color');
		        if(id1==0){
		        	 for(var a=0;a<data.object.length;a++){
				          var str=''
				          str+=" <div><div class='s_top'>"+data.object[a].c_name+" </div> <div class='top'> <div class='heng'> <span class='showid' data-id='"+data.object[a].c_id+"'>"+data.object[a].c_name+"</span></div> <div class='knowMore'>了解更多&gt;</div></div> <ul class='content'>"
				          for(var b=0;b<data.object[a].list.length;b++){ 
				          	if(id2==0&&data.object[a].list.length>6){
									        	  		data.object[a].list.length=6
									        	  }
				          	var titles;
				          	if(data.object[a].list[b].m_title.length>30){
				          		titles=data.object[a].list[b].m_title.substr(0,30)+'......'
				          	}else{
				          		titles=data.object[a].list[b].m_title
				          	}
				            str+="<li> <div class='picture_l'> <div class='picture' style='background: url("+data.object[a].list[b].m_cover+")no-repeat center;background-size: cover'></div> <div class='pd'> <p class='per'>"+data.object[a].list[b].m_authorname+"</p> <p class='date'>"+dealdata(data.object[a].list[b].m_time)+"</p> </div> </div> <div class='info'> <div class='detail fl'> <p class='tit'>"+titles+"</p> <p class='read'>"+data.object[a].list[b].m_content+"</p> </div> <div class='more2'><a style='font-size: 0.16rem' href='./consulting.html?m_id="+data.object[a].list[b].m_id+"&title=资讯信息' target='_blank'>了解更多&gt; </a></div> </div> </li>"
				          }
				          str+="</ul></div>"
				          $(".new_search").append(str);
				          $('.knowMore').click(function(){
				          	var oids=$(this).siblings('.heng').find('.showid').attr('data-id');
				          	listMessage1Bygroup(oids,1,i)
				          })
				        }
		        }else{
		        		 for(var a=0;a<data.object.length;a++){
		        		 	if(data.object[a].c_id==id1){
						          var str=''
						          str+=" <div><div class='s_top'>"+data.object[a].c_name+" </div> <div class='top'> <div class='heng'> <span class='showid' data-id='"+data.object[a].c_id+"'>"+data.object[a].c_name+"</span></div> <div class='knowMore'>了解更多&gt;</div></div> <ul class='content'>"
						          for(var b=0;b<data.object[a].list.length;b++){ 
						          	if(id2==0&&data.object[a].list.length>6){
											        	  		data.object[a].list.length=6
											        	  }
						          	var titles;
						          	if(data.object[a].list[b].m_title.length>30){
						          		titles=data.object[a].list[b].m_title.substr(0,30)+'......'
						          	}else{
						          		titles=data.object[a].list[b].m_title
						          	}
						            str+="<li> <div class='picture_l'> <div class='picture' style='background: url("+data.object[a].list[b].m_cover+")no-repeat center;background-size: cover'></div> <div class='pd'> <p class='per'>"+data.object[a].list[b].m_authorname+"</p> <p class='date'>"+dealdata(data.object[a].list[b].m_time)+"</p> </div> </div> <div class='info'> <div class='detail fl'> <p class='tit'>"+titles+"</p> <p class='read'>"+data.object[a].list[b].m_content+"</p> </div> <div class='more2'><a style='font-size: 0.16rem' href='./consulting.html?m_id="+data.object[a].list[b].m_id+"&title=资讯信息' target='_blank'>了解更多&gt; </a></div> </div> </li>"
						          }
						          str+="</ul></div>"
						          $(".new_search").append(str);
					          }
					          $('.knowMore').click(function(){
					          	var oids=$(this).siblings('.heng').find('.showid').attr('data-id');
					          	listMessage1Bygroup(oids,1,i)
					          })
					        }
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
      }
    },
    error:function(){
      parent.removeload()
      swal({
        title: "请求失败,请重新尝试",
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
  var result=n+'年'+y+'月'+r+'日';
  return result;
}
function listHomePage(){
  var obj = new Object();
  obj.ckuid=sessionStorage.getItem('ckuid');
  obj.cksid=sessionStorage.getItem('cksid');
  parent.loadShow()
  $.ajax({
    url: http+"listHomePage",
    type: "post",
    data:JSON.stringify(obj),
    contentType: "application/json",
    headers: {'Content-type': 'application/json;charset=UTF-8'},
    cache: false,
    success: function (data) {
      parent.removeload()
      if(data.state==0){
        $('.swiper-wrapper').empty()
        $(data.object).each(function(i,el){
          var url=data.object[i].h_url;
          if(url){
            $('.swiper-wrapper').append(" <div class='swiper-slide'><div style='background: url("+data.object[i].h_cover+")no-repeat center;background-size: cover' data-url="+url+"></div>")
          }else{
            $('.swiper-wrapper').append(" <div class='swiper-slide'><div style='background: url("+data.object[i].h_cover+")no-repeat center;background-size: cover'></div>")
          }
        })
        $('.swiper-slide').find('div').on('click',function(){
          if($(this).attr('data-url')){
            window.parent.location.href=$(this).attr('data-url')
          }

        })
        var mySwiper = new Swiper('.swiper-container',{
          autoplay: 3000,
          prevButton:'.swiper-button-prev',
          nextButton:'.swiper-button-next',
          speed:500
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

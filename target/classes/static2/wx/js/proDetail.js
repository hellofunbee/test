	$(function(){
		$('.backHome').show();
		if(getUrlKey('title')){
			var titles = getUrlKey('title');
			if(titles=='专家指导'){
				var newTitle=titles+'详情'
				listArtical(titles);//专家文章详情
			}else{
				listMessage(titles);//首页   以及   信息发布--即时 政策  预警
			}
			$('.backHome').attr('data-home',titles)
		};
			$(document).on('click','.backHome',function(){
				var Oattr=$(this).attr('data-home');
				if(Oattr=='文章详情'){
					window.location.href = './firstPage.html';
				}else if(Oattr=='专家指导'){
					window.location.href = './expertServe.html?home='+Oattr+'&experId='+getUrlKey('experId');
				}else{
					window.location.href = './policyInfo.html?home='+Oattr;
				}
			})
		})
    function listMessage(titles){
      var obj = new Object();
      obj.ckuid=sessionStorage.getItem('ckuid');
      obj.cksid=sessionStorage.getItem('cksid');
      obj.m_id=window.location.href.split('?')[1].split('&')[0].split('=')[1];
      loadShow();
      $.ajax({
        url: http+"listMessage",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
        	removeload();
          if(data.success){
          	if(titles=="文章详情"){
          		$('.fir_head_nav').text(titles);
				document.title=titles;
          	}else{
          		$('.fir_head_nav').text(titles+'详情');
				document.title=titles+'详情';
          	};
          	var textContent= data.object[0].m_content;
            $('.details_li_title').text(data.object[0].m_title);
            $('.details_li_t_name').text(data.object[0].m_authorname);
            $('.details_li_t_time').text(dealdata(data.object[0].m_time));
            $('.details_l_text').text(textContent);
            if(titles=='即时信息'||titles=='预警信息'){
            	$('.details_li_t_name').hide()
            	$('.details_l_img').hide();
            }else{
            	$('.details_l_img').attr('src',data.object[0].m_cover).show();
            }
          }else if(data.state==2){
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
				return false
          } else{
            swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
          }
        },
          error: function() {
			removeload()
			swal({
				title: "请求失败,请重新尝试",
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
    function listArtical(titles){
      var obj = new Object();
      obj.ckuid=sessionStorage.getItem('ckuid');
      obj.cksid=sessionStorage.getItem('cksid');
      obj.a_id=getUrlKey('m_id');
      obj.a_type=getUrlKey('m_type');
      loadShow();
      $.ajax({
        url: http+"listArticle",
        type: "post",
        contentType: "application/json",
        headers: {'Content-type': 'application/json;charset=UTF-8'},
        data:JSON.stringify(obj),
        cache: false,
        success: function (data) {
        	removeload();
          if(data.success){
          	$('.fir_head_nav').text(titles+'详情');
			document.title=titles+'详情';
          	$('.details_li_title').text(data.object[0].a_name);
            $('.details_li_t_name').text(data.object[0].a_authorname);
            $('.details_li_t_time').text(dealdata(data.object[0].a_time));
            $(data.object[0].a_content).each(function(i,el){
            	if(i==0){
            		$('.details_l_text').text(data.object[0].a_content[0].text);
            		$('.details_l_img').attr('src',data.object[0].a_content[0].file).show();
            	}else{
            		var text='<img src="'+data.object[0].a_content[i].file+'" class="details_l_img" style="margin-top:.0625rem"/><p class="details_l_text">'+data.object[0].a_content[i].text+'</p>';
            		$('.details_list').append(text)
            	}
            })
          }else if(data.state==2){
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
				return false
          } else{
            swal({
					title: data.msg,
					text: "",
					type: "warning",
					showCancelButton: false,
					confirmButtonColor: "#3366FF",
					confirmButtonText: "确定",
					closeOnConfirm: false
				});
          }
    },
          error: function() {
			removeload()
			swal({
				title: "请求失败,请重新尝试",
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
    };
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

function getUrlKey(name) {
		return decodeURIComponent((
			new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
		};
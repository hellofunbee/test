
var alldata, confignum = 0;
$(function() {
	if(!sessionStorage.getItem('ckuid') || sessionStorage.getItem('ckuid') == undefined || sessionStorage.getItem('ckuid') == null) {
		swal({
                    title: '登录失败,请重新登录',
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#3366FF",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                },function(){
                    window.location.href='./login.html'
                }
            );
		return
	}
/*	setTimeout(function(){
		$('body').css({'height':window.innerHeight+'px','overflow':'scroll'});
	},20)*/
	
	$('.fir_head_l').click(function() {
		$('#fir_var_menu').show();
		$('#fir_pro_menu').hide();
	});
	$('.fir_head_r_search').click(function(){
		$("#fir_pro_menu").show();
		$('#fir_var_menu').hide();
	});
	$(".fir_var_m_list>li").click(function() {
			$(this).parents('#fir_var_menu').hide();
			var content = $(this).text();
			console.log(content)
			if(content == "首页") {
				window.location.href = "firstPage.html"
			}
			if(content == "GIS系统") {
				window.location.href = "GISmenu.html";
			}
			if(content == "综合信息") {
				window.location.href = "generalInfo.html"
			}
			if(content == "生产管理") {
				window.location.href = "productControl.html"
			}
			if(content == "信息发布") {
				window.location.href = "policyInfo.html"
			}
			if(content == "大数据分析") {
				window.location.href = "bigData.html"
			}
			if(content == "专家系统") {
				window.location.href = "expertServe.html"
			}
			if(content == "系统设置") {
				window.location.href = "system.html"
			}
			if(content == "注销") {
				sessionStorage.removeItem('ckuid');
				sessionStorage.removeItem('cksid');
				window.location.href = 'login.html';
			}
		});
	//菜单关闭
	$(document).on('click','.fir_var_menu_close',function(){
		$(this).parent('.fir_var_menu').hide()
	});
	//摄像头关闭
	$(document).on('click','.fir_pro_menu_close',function(){
		$(this).parent('.fir_pro_menu').hide()
	});
	$(document).on('touchend',function(e){
		var target  = $(e.target);
		if(target.closest(".page_bg").length == 0){  
		$('.menu_equip').hide();
		$('.fir_head_all').css('background-image','url(img/1062.svg)');
		 };  
       	e.stopPropagation();
	})
	$('.fir_head_all').click(function() {
		$('.menu_equip').css('display','block');
		$('.fir_head_all').css('background-image','url(img/downed.svg)')
	});
	$('.menu_equip li').click(function(){
		$('.menu_equip li').removeClass('choose');
		$(this).addClass('choose');
		$(this).parents('.menu_equip').hide();
		$('.fir_head_all').css('background-image','url(img/1062.svg)')
	})
})
function loadShow(){
        $('body').loading({
            loadingWidth:120,
            title:'处理中!',
            name:'test',
            discription:'',
            direction:'column',
            type:'origin',
            // originBg:'#71EA71',
            originDivWidth:40,
            originDivHeight:40,
            originWidth:6,
            originHeight:6,
            smallLoading:false,
            loadingMaskBg:'rgba(0,0,0,0.2)'
        });
    }
    function removeload(){
        removeLoading('test');
    }
(function (doc, win) {
        var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
                var clientWidth = docEl.clientWidth;
                if (!clientWidth) return;
                if(clientWidth>=375){
                    docEl.style.fontSize = '160px';
                }else{
                    docEl.style.fontSize = 160 * (clientWidth / 375) + 'px';
                }
            };
        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);

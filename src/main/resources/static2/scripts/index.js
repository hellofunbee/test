window.onload=function(){
	
    if(!sessionStorage.getItem('ckuid')||sessionStorage.getItem('ckuid')==undefined||sessionStorage.getItem('ckuid')==null){
        swal('登录失效，请重新登录')
        swal({
              title: "登录失效，请重新登录",
              text: "",
              type: "warning",
              showCancelButton: false,
              confirmButtonColor: "#30862B",
              confirmButtonText: "返回登录页!",
              closeOnConfirm: false
          },
          function(){
              window.location.href='./login.html'
          });
        return
    }else{
    	var userType = sessionStorage.getItem('utype');
    	if(userType==1){
    		$('.userType').show()
    	}else{
    		$('.userType').hide()
    	}
        setTimeout(function(){
            document.getElementsByTagName('body')[0].style.height = window.innerHeight+'px';
            var top_h=$(".top").outerHeight();
            var nav_h=$(".nav").outerHeight();
            $(".homeCon").outerHeight(window.innerHeight-top_h-nav_h)
            $(".menu>li").eq(0).click();
        },20);
        $(".menu>li").click(function(){
            var content=$(this).find("div").html();
            $(".menu>li>p").hide();
            $(this).find("p").show();
            if(content=="首页"){
                $("#main_iframe").attr("src","homepage.html");
            }
            if(content=="GIS系统"){
                $("#main_iframe").attr("src","GIS.html");
            }
            if(content=="综合信息"){
                $("#main_iframe").attr("src","synthesize.html");
            }
            if(content=="生产管理"){
                $("#main_iframe").attr("src","product.html");
            }
            if(content=="大数据分析"){
                $("#main_iframe").attr("src","bigData.html");
            }
            if(content=="发布信息"){
                $("#main_iframe").attr("src","publish.html");
            }
            if(content=="专家系统"){
                $("#main_iframe").attr("src","expert.html");
            }
            if(content=="系统管理"){
                $("#main_iframe").attr("src","system.html");
            }
            if(content=="注销"){
                swal({
                      title: "是否返回登录页面",
                      text: "",
                      type: "warning",
                      showCancelButton: true,
                      confirmButtonColor: "#30862B",
                      cancelButtonText: "取消",
                      confirmButtonText: "确定",
                      closeOnConfirm: false
                  },
                  function(){
                      sessionStorage.removeItem('ckuid')
                      sessionStorage.removeItem('cksid')
                      sessionStorage.removeItem('u_id')
                      window.location.href='./login.html'
                  });
                return
            }

        });
    }

};
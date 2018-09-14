/**
 * Created by jianghu on 2017/10/23.
 */
$(function(){

/*	(function(){
		$.ajax(function(){
			
		})
	})()*/
  var browser = getBrowserInfo();
  var verinfo = (browser+"").replace(/[^0-9.]/ig,"");
  $('#sub').on('click',function(){
    var tu_username=$('#phone').val();
    if(!tu_username){
    	swal({
          title: '请填写手机号',
          text: "",
          type: "warning",
          showCancelButton: false,
          confirmButtonColor: "#30862B",
          confirmButtonText: "确定",
          closeOnConfirm: false
        });
      return
    }
    var tu_pwd=$('#pass').val();
    if(!tu_pwd){
    	swal({
          title: '请填写密码',
          text: "",
          type: "warning",
          showCancelButton: false,
          confirmButtonColor: "#30862B",
          confirmButtonText: "确定",
          closeOnConfirm: false
        });
      return
    }
    loadShow();
    $.ajax({
      url:http + "Login",
      type:"post",
      contentType: "application/json",
      cache: false,
      data:JSON.stringify({
        "tu_username": tu_username,
        "tu_pwd": tu_pwd
      }),
      success: function (data) {
        setTimeout(function(){
          removeload()
          if(data.success==true){
            sessionStorage.setItem('ckuid',data.object.ckuid);
            sessionStorage.setItem('cksid',data.object.cksid);
            sessionStorage.setItem('utype',data.object.u_type);//3 系统管理  编辑
            window.location.href="./GIS.html"
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
          }
        },1000)
      },error:function(){
        removeload()
        swal({
          title: '用户名或密码错误',
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
});
function loadShow(){
  $('body').loading({
    loadingWidth:240,
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
function getBrowserInfo() {
  var agent = navigator.userAgent.toLowerCase() ;
  var regStr_ie = /msie [\d.]+;/gi ;
  var regStr_ff = /firefox\/[\d.]+/gi
  var regStr_chrome = /chrome\/[\d.]+/gi ;
  var regStr_saf = /safari\/[\d.]+/gi ;
  //IE11以下
  if(agent.indexOf("msie") > 0)
  {
    return agent.match(regStr_ie) ;
  }
  //IE11版本中不包括MSIE字段
  if(agent.indexOf("trident") > 0&&agent.indexOf("rv") > 0){
    return "IE " + agent.match(/rv:(\d+\.\d+)/) [1];
  }
  //firefox
  if(agent.indexOf("firefox") > 0)
  {
    return agent.match(regStr_ff) ;
  }
  //Chrome
  if(agent.indexOf("chrome") > 0)
  {
    return agent.match(regStr_chrome) ;
  }
  //Safari
  if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0)
  {
    return agent.match(regStr_saf) ;
  }
}
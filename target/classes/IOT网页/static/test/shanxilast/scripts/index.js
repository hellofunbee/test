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
    }
};
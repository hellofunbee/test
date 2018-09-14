/**
 * Created by jianghu on 2017/10/23.
 */
$(function() {
	$('#sub').on('click', function() {
		var tu_username = $('#phone').val();
		if(!tu_username) {
			swal({
				title: "请输入用户名",
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#3366FF",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return
		}
		var tu_pwd = $('#pass').val();
		if(!tu_pwd) {
			swal({
				title: "请填写密码",
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#3366FF",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return;
		} 
		$.ajax({
			url: http + "Login",
			type: "post",
			contentType: "application/json",
			cache: false,
			data: JSON.stringify({
				"tu_username": tu_username,
				"tu_pwd": tu_pwd
			}),
			success: function(data) {
				if(data.success == true) {
					sessionStorage.setItem('ckuid', data.object.ckuid)
					sessionStorage.setItem('cksid', data.object.cksid);
					if($("#checkLogin").prop('checked')){
						localStorage.setItem('userSelect',1);
						localStorage.setItem('userId', $('#phone').val());
						localStorage.setItem('userPass',$('#pass').val());
					}
					window.location.href = "./firstPage.html";
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
				}

			}
		})
	});
	$('.checkPass').click(function(){
		swal({
			title: '请联系系统管理员',
			text: "",
			type: "warning",
			showCancelButton: false,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "好",
			closeOnConfirm: false
		});
	})
	var select=localStorage.getItem('userSelect');
	if(select){
		$("#checkLogin").prop('checked','checked');
		$("#phone").val(localStorage.getItem('userId'));
		$("#pass").val(localStorage.getItem('userPass'))
		setTimeout(function(){
			$('#sub').click();
		},3000)
	}else{
		$("#phone").val('')
		$("#pass").val('')
	}
	$("#checkLogin").change(function(){
		if(select){
			localStorage.removeItem('userSelect');
 		}
		
	})
});

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
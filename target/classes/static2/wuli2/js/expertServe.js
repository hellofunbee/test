var sexarr = ['', '男', '女']
$(function() {
	if(!sessionStorage.getItem('ckuid') || sessionStorage.getItem('ckuid') == undefined || sessionStorage.getItem('ckuid') == null) {
		swal({
			title: '登录失效,请重新登录',
			text: "",
			type: "warning",
			showCancelButton: false,
			confirmButtonColor: "#3366FF",
			confirmButtonText: "确定",
			closeOnConfirm: false
		}, function() {
			window.location.href = './login.html'
		});
		return
	};
	listClass1(); //一家专家列表select展示；
	$('.inputAr_s_new').click(function() {
		$('.expertLists').show();
	})
	//我要提问
	$('.expert_de_pro_ask span').on('click', function() {
		$('.expert_de_bg').show().css('height', $('.inputAr_searchList').outerHeight() + 'px');
		if($(this).attr('data-s') == 1) {
			$(".ex_onl_ask").show();
		} else {
			$('.exper_ask').show();
		}

	});
	//专家服务
	$('.expertLink').click(function() {
		$('.experShows').hide();
		$('.experShows:eq(2)').show();
		$('.inputAr_set').hide()
	});
	$('.expertListsBack').click(function(){
		$('.experShows').hide();
		$('.experShows:eq(0)').show();
		$('.inputAr_set').show()
	})
	$('.experDetail_back').click(function() {
		$('.experShows').hide();
		$('.experShows:eq(1)').show();
		$('.inputAr_set').hide();
		
	})
	//遮罩层关闭按钮
	$('.expert_de_bg_clo').click(function() {
		$(this).parents('.dialog').hide();
		$('.expert_de_bg').hide();
	});
	//客户提问
	$('.ques_sub').click(function() {
		addAnswerQuestion(1);
	})
	//专家提问
	$('.expert_sub').click(function() {
		addAnswerQuestion(2);
	})
	// 专家分类搜索
	$('.inputAr_s_search').on('click', function() {
		var str = $(this).siblings('input').val();
		if(str) {
			$('#inputAr_se_out>li').each(function() {
				if($(this).find('.expert_i_name').text().indexOf(str) >= 0) {
					$(this).show()
				} else {
					$(this).hide()
				}
			})
		} else {
			$('.ul_bot>li').show();
		}

	});
	
		$(document).scroll(function(){
			var oa=$(document).height()-$(window).height();
				if(oa>0){
					$('.picHave').show()
				}
			if($(document).scrollTop()-oa>=0){
				$('.picHave').hide()
			}else{
				$('.picHave').show()
			}
		});
		
})
//一级分类列表
function listClass1() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.c_type = '1';
	loadShow();
	$.ajax({
		url: http + "listCMSClass1",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.state == 0) {
				 //一级分类 不同领域
				if(data.object == null) {
					swal({
						title: '没有一级相关信息',
						text: "2秒后关闭",
						confirmButtonText: "确定",
						confirmButtonColor: "#3366FF",
						timer: 2000
					});
				} else {
					$('.expertLists').html('<li expertLData="0">专家分类</li>');
					$(data.object).each(function(i, el) {
						var names;
						if(data.object[i].c_name.length>10){
							names = data.object[i].c_name.substr(0,8)+'...'
						}else{
							names = data.object[i].c_name
						}
						$('.expertLists').append('<li expertLData=' + data.object[i].c_id + '>' + names + '</li>')
					});
					$(document).on('click',function(e){
					var target=$(e.target);
					if(target.closest(".inputAr_s_new_bg").length == 0){  
						$('.expertLists').hide();
						 };  
				       		e.stopPropagation();
						});
					$('.expertLists>li').click(function() {
						$('.expertLists>li').removeClass('choose');
						$(this).addClass('choose');
						$('.experShows').hide();
						$('.experShows:eq(0)').show();
						$(this).parent().hide();
						$(this).parent().siblings('.inputAr_s_new').text($(this).text());
						if($(this).attr('expertLData')){
							listProfessor($(this).attr('expertLData'), 1);
						}
						
					});
					$('.expertLists>li').eq(0).click()
				}

			} else if(data.state == 2) {
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
				return
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
				return
			}
		},
		error: function() {
			swal({
				title: '请求失败，请重新尝试',
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
//专家展示
/*function listClass2(id, id1) { //编辑个人信息--二级分类select与专家详情编辑   id一级专家分类
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.c_rid = id;
	$.ajax({
		url: http1 + "listClass2Byrid",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			if(data.state == 0) {
				$(data.object).each(function(i, el) {
					listProfessor(data.object[i].c_id, 1)
				})
			} else if(data.state == 2) {
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
				return
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
				return
			}
		},
		error: function() {
			swal({
				title: '请求失败，请重新尝试',
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

};*/

function active() {
	var state;
	$('.dialog_lists>li').each(function() {
		if($(this).hasClass('active')) {
			state = $(this)
		}
	})
	return state
}
//获取专家列表   id为选重的领域 s！=1表示编辑
function listProfessor(id, s) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	if(s == 1) {
		obj.class1id = id;
	} else {
		obj.u_id = id
	}
	loadShow();
	$.ajax({
		url: http + 'listProfessor',
		type: 'post',
		contentType: 'application/json',
		headers: { 'content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			removeload();
			if(data.state == 0) {
				if(s == 1) { //多个专家
					/*头部搜索显示*/
					$('.inputAr_set').show();
					if(data.Object != null) {
						swal({
							title: data.msg,
							text: "",
							type: "warning",
							showCancelButton: false,
							confirmButtonColor: "#3366FF",
							confirmButtonText: "确定",
							closeOnConfirm: false
						});
						return;
					} else {
						/*添加专家列表*/
						var str='';
						$(data.object).each(function(i, e) {
							var field='';
							if(data.object[i].position.length>4){
								field=data.object[i].position.substr(0,4)+'......'
							}else{
								field==data.object[i].position
							}
							 str += '<li class="inputAr_se_o_exper" data-id="' + data.object[i].u_id + '"><div class="inputAr_se_i_out" style="background: url(' + data.object[i].headimgurl + ') center/cover no-repeat"></div><div class="expert_info"><p class="clear"><span class="expert_i_name">' + data.object[i].u_uname + '</span><span class="expert_i_work">' + field + '</span>' +
								'</p><p class="area">专业领域</p><p class="ara_work">' + data.object[i].field + '</p><p class="area">电子邮箱</p><p class="areaEmail">' + data.object[i].u_email + '</p><p class="area">服务对象</p><p class="araPro">' + (data.object[i].seruser.length) + '个</p></div></li>'
						});
						$('#inputAr_se_out').empty().append(str);
						var oa=$(document).height()-$(window).height();
							if(oa>0){
								$('.picHave').show();
							}else{
								$('.picHave').hide();
							}
						$('#inputAr_se_out li').on('click', function() {
							$('.inputAr_set').hide();
							$("#inputAr_se_out").hide();
							$('.expert_detail').show();
							sessionStorage.setItem('u_id', $(this).attr('data-id'));
							/*专家列表*/
							listProfessor($(this).attr('data-id'), 2)
							/*专家提问记录*/
							listQuestion($(this).attr('data-id'));
							/*专家提问记录*/
							/*专家答疑问*/
							listAnswerQuestion($(this).attr('data-id'));
							/*专家答疑问*/
							/* 专家服务详情*/
							getProfessorArticle($(this).attr('data-id'));
							/* 专家服务详情*/
							$('.expert_de_pro_tops>li').click(function(){
								$(this).find('.line').css('display','block');
								$(this).siblings('li').find('.line').hide();
								var index=$(this).index();
								$('.expert_de_pro:eq('+index+')').show().siblings('.expert_de_pro').hide();
								
							})
						});
						if(getUrlKey('experId')){
							var index=getUrlKey('experId');
							$('#inputAr_se_out li[data-id="'+index+'"]').click();
							$('.experShows').hide();
							$('.experShows:eq(2)').show();
							$('.inputAr_set').hide()
							
						}
					}
				} else { //单个专家
					var user_type = sessionStorage.getItem('ckuid');
					var user_id = sessionStorage.getItem('u_id');
					if(user_type != user_id) {
						$('.expert_de_pro_ask span').text('我要提问').attr('data-s', 1);
					} else {
						$('.expert_de_pro_ask span').text('回答').attr('data-s', 2)
					}
					//展示专家详细信息
					var age;
					if(data.object[0].u_age!=null){
						age=data.object[0].u_age
					}else{
						age=''
					}
					if(data.object[0].detail.length>32){
						Odetail=data.object[0].detail.slice(0,32)
					}else{
						Odetail=data.object[0].detail
					}
					$('.expertLink').attr('u_id',id);
					$('.expert_de_t .expert_d_img').css('background-image','url('+data.object[0].headimgurl+')')
					$('.expert_name').text(data.object[0].u_uname)
					$('.expert_age').text(age);
					$('.expert_sex').text(sexarr[data.object[0].sex])
					$('.expert_job').text(data.object[0].position);
					$('.exper_serve').text(data.object[0].class2name);
					$('.exper_field').text(data.object[0].field)
					$('.exper_email').text(data.object[0].u_email)
					$('.expert_de_c_text').text(Odetail);
					var oa=false;
					$('.showmore').click(function() {
						oa=!oa;
						if(oa){
							$(this).find('img').attr('src','img/toUp.svg');
							$('.showmore').find('span').text('收起');
							$('.expert_de_c_text').text(data.object[0].detail);
						}else{
							$(this).find('img').attr('src','img/Path/1104.svg');
							$('.showmore').find('span').text('展开更多');
							var Odetail='';
							if(data.object[0].detail.length>32){
									Odetail=data.object[0].detail.slice(0,32)
								}else{
									Odetail=data.object[0].detail
								}
								$('.expert_de_c_text').text(Odetail)
						}
							});
					$('.servingPro').empty();
					$(data.object[0].serproject).each(function(a, el) {
						$('.servingPro').append('<li>' + data.object[0].serproject[a].text + '</li>')
					})
					$('.servedPro').empty();
					$(data.object[0].seredproject).each(function(b, el) {
						$('.servedPro').append('<li>' + data.object[0].seredproject[b].text + '</li>')
					})
					$('.expert_servUser').empty();
					$(data.object[0].seruser).each(function(c, el) {
						$('.expert_servUser').append('<li><span></span>' + data.object[0].seruser[c].text + '</li>')
					})
				}
			} else if(data.state == 2) {
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
		},
		error: function() {
			alert('请求失败，请重试')
		}
	})
}
/*专家答疑                 提问*/
function listAnswerQuestion(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.aq_pid = id;
	loadShow();
	$.ajax({
		url: http + "listAnswerQuestion",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			console.log(data)
			removeload();
			if(data.state == 0) {
				$('.zhuanjaidayi').find('.expert_de_pro_cen').empty();
				$(data.object).each(function(i, el) {
					$('.zhuanjaidayi').find('.expert_de_pro_cen').append('<li class="expert_de_pro_cen_list">' + data.object[i].question + '<br><span>' + data.object[i].answer + '</span></li>');
				});
			} else if(data.state == 2) {
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
				return
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
		},
		error: function() {
			swal({
				title: data.msg,
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#3366FF",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return
		}
	})
};
/*专家答疑                      提问*/
/*专家在线上提问*/
function listQuestion(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.aq_pid = id;
	loadShow();
	$.ajax({
		url: http + "listQuestion",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			console.log(data)
			removeload();
			if(data.state == 0) {
				$('.tiwenjilv').find('.expert_de_pro_a_conten').empty();
				/*$('.textbox4 .a_1>ul').empty()*/
				$(data.object).each(function(i, el) {
					$('.tiwenjilv').find('.expert_de_pro_a_conten').append("<li><span></span>" + data.object[i].aq_content + "</li>")
				});
			
				//问题列表
				$(data.object).each(function(z, el) {
					$('.dialog_lists').append(' <li data-id=' + data.object[z].aq_id + '> ' + z + ': ' + data.object[z].aq_content + '</li>')
				})
				$('.dialog_lists>li').eq(0).addClass('active');
				//提问内容
				$('.asctext').text($('.dialog_lists>li').eq(0).text());

				$('.dialog_lists>li').on('click', function() {
					$(this).addClass('active').siblings().removeClass('active');
					var content = $(this).text();
					$('.asctext').text(content)
				})
			} else if(data.state == 2) {
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
				return
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
				return
			}
		},
		error: function() {
			swal({
				title: '请求失败，请重新尝试',
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#3366FF",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return;
		}
	})
};
/*专家在线上提问*/
/*提问问题*/
function addAnswerQuestion(num) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	if(num == 1) { //客户提问
		obj.aq_pid = sessionStorage.getItem('u_id');
		obj.aq_uid = sessionStorage.getItem('ckuid');
		obj.aq_qid = 0;
		obj.aq_type = 0
		obj.aq_content = $('.userText').val();
		if(!obj.aq_content) {
			swal({
				title: "问题内容不能为空!",
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#3366FF",
				timer: 2000
			});
			return
		}
	} else { //专家提问
		obj.aq_pid = sessionStorage.getItem('u_id');
		obj.aq_uid = sessionStorage.getItem('ckuid');
		obj.aq_qid = active().attr('data-id');
		obj.aq_type = 2
		obj.aq_content = $('.expertTxt').val();
		if(!obj.aq_content) {
			swal({
				title: "问题内容不能为空!",
				text: "2秒后关闭",
				confirmButtonText: "确定",
				confirmButtonColor: "#3366FF",
				timer: 2000
			});
			return
		}
	}
	loadShow()
	$.ajax({
		url: http + "addAnswerQuestion",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			console.log(data)
			removeload()
			if(data.state == 0) {
				swal({
					title: data.msg,
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					timer: 2000
				});
				$('.dialog').hide();
				$('.expert_de_bg').hide();
				if(num == 1) { //客户提问完--专家提问列表
					listQuestion(sessionStorage.getItem('u_id'));
				} else { //专家提问列表完--专家答疑
					listAnswerQuestion(sessionStorage.getItem('u_id'));
				}

			} else if(data.state == 2) {
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
		},
		error: function() {
			removeload()
			swal({
				title: '请求失败，请重新尝试',
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#3366FF",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			alert('请求失败，请重新尝试');
			return
		}
	})
};
/*提问问题*/
/* 专家服务详情*/
function getProfessorArticle(id) {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.u_id = id;
	loadShow()
	$.ajax({
		url: http + "getProfessorArticle",
		type: "post",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			console.log(data)
			removeload()
			if(data.state == 0) {
				$('.expertServedetail').empty();
				$(data.object).each(function(i, el) {
					var str = '';
					var titleName=data.object[i].a_name;
					if(titleName.length>20){
						titleName=titleName.slice(0,20)+'......'
					}else{
						titleName=titleName;
					}
					var titleTexts=data.object[i].a_content[0].text;//以前是100
					if(titleTexts.length>80){
						titleTexts=titleTexts.slice(0,80)+'......'
					}else{
						titleTexts=titleTexts;
					}
					str += '<li data-uid="'+data.object[i].a_uid+'" data-id="'+data.object[i].a_id+'"><a class="clear" href="./proDetail.html?m_id='+
							data.object[i].a_id+'&m_type='+data.object[i].a_type+'&experId='+id+'&title=专家指导" target="_self" ><img class="expertServede_img" src="' + data.object[i].a_cover + '" alt="加载失败" /><div style="width:1.2rem"><p class="expertList_tit">'+titleName+'</p><p class="expertList_tex">' + data.object[i].class1name + " - " + data.object[i].class2name + '</p>' +
						'<p class="expertList_tex">文章分类 - ' + data.object[i].a_typename + '</p><p class="expertList_data">' + dealdata(data.object[i].a_time) + '</p></div>'
						str += '<p class="expertList_text">' + titleTexts + '<span class="showMore">查看详情&gt;</span></p>'
					str += '</a></li>'
					$('.expertServedetail').append(str);
				});
				$('.expertServedetail').find('li').eq(0).css('margin-top','.08rem');
				$('.expertServedetail>li').click(function(){
					
				})
			} else if(data.state == 2) {
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
		},
		error: function() {
			removeload()
			swal({
				title: '请求失败，请重新尝试',
				text: "",
				type: "warning",
				showCancelButton: false,
				confirmButtonColor: "#3366FF",
				confirmButtonText: "确定",
				closeOnConfirm: false
			});
			return
		}
	})
}
//专家文章详情列表单个文章
function getPersonArt(){
	
}
/*专家服务详情*/
//时间戳
function dealdata(ele) {
	var time = new Date(ele * 1000);
	var n = time.getFullYear();
	var y = time.getMonth() + 1;
	var r = time.getDate();
	var h = time.getHours() < 10 ? "0" + time.getHours() : time.getHours();
	var m = time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes();
	var s = time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds();
	var result = n + '年' + y + '月' + r + '日';
	return result;
}

function updateUser() {
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	$('#userList>li').each(function(i, el) {
		if(i == 0) {
			obj.headimgurl = $('#userList>li').eq(i).find('img').attr('src')
		}
		if(i == 1) {
			obj.u_uname = $('#userList>li').eq(i).find('input').val()
			if(!obj.u_uname) {
				swal({
					title: '姓名不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					timer: 2000
				});
				return
			}
			obj.sex = activeSex()
		}
		if(i == 2) {
			obj.u_phone = $('#userList>li').eq(i).find('input').val()
			if(!obj.u_phone) {
				swal({
					title: '手机号不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					timer: 2000
				});
				return
			}
		}
		if(i == 3) {
			obj.field = $('#userList>li').eq(i).find('input').val()
			if(!obj.field) {
				swal({
					title: '研究领域不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					timer: 2000
				});
				return
			}
		}
		if(i == 4) {
			obj.u_phone2 = $('#userList>li').eq(i).find('input').val()
			if(!obj.u_phone2) {
				swal({
					title: '备用联系方式不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					timer: 2000
				});
				return
			}
		}
		if(i == 5) {
			obj.position = $('#userList>li').eq(i).find('input').val()
			if(!obj.position) {
				swal({
					title: '职务不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					timer: 2000
				});
				return
			}
		}
		if(i == 6) {
			obj.u_email = $('#userList>li').eq(i).find('input').val()
			if(!obj.u_email) {
				swal({
					title: '电子邮箱不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					timer: 2000
				});
				return
			}
		}
		if(i == 9) {
			obj.detail = $('#userList>li').eq(i).find('input').val()
			if(!obj.detail) {
				swal({
					title: '个人介绍不能为空',
					text: "2秒后关闭",
					confirmButtonText: "确定",
					confirmButtonColor: "#3366FF",
					timer: 2000
				});
				return
			}
		}
	})
	$('.textbox5').find('.ul_class2_1>li').each(function(a, el) {

	})
};
function getUrlKey(name) {
		return decodeURIComponent((
			new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
		};
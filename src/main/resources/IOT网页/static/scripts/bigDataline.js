$(function() {
	$('.nav_l_morede').click(function() { //多数据分析
		window.location.href = './bigData.html?shows=2';
	})
	$('.nav_l_de').click(function() { //单数据分析
		window.location.href = './bigData.html?shows=1';
	});
	var dom0 = document.getElementById('mains');
	var dom1 = document.getElementById('mainLi1');
	var dom2 = document.getElementById('mainLi2');
	var dom3 = document.getElementById('mainLi3');
	var dom4 = document.getElementById('mainLi4');
	var dom5 = document.getElementById('mainLi5');
	var dom6 = document.getElementById('mainLi6');
	var dom7 = document.getElementById('mainLi7');
	var dom8 = document.getElementById('mainLi8');
	var dom9 = document.getElementById('mainLi9');
	var dom10 = document.getElementById('mainLi10');
	var myChart0 = echarts.init(dom0);
	var myChart1 = echarts.init(dom1);
	var myChart2 = echarts.init(dom2);
	var myChart3 = echarts.init(dom3);
	var myChart4 = echarts.init(dom4);
	var myChart5 = echarts.init(dom5);
	var myChart6 = echarts.init(dom6);
	var myChart7 = echarts.init(dom7);
	var myChart8 = echarts.init(dom8);
	var myChart9 = echarts.init(dom9);
	var myChart10 = echarts.init(dom10);
	option0 = null;
	option1 = null;
	option2 = null;
	option3 = null;
	option4 = null;
	option5 = null;
	option6 = null;
	option7 = null;
	option8 = null;
	option9 = null;
	option10 = null;
	option0 = {
		tooltip: {
			show: true
		},
		title: {
			text: '',
			left: 'center'
		},
		grid: {
	        left: '0%',
	        right: '4%',
	        bottom: '10%',
	        containLabel: true
	    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5]
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value',
			name: ''
		}],
		series: []
	};
	option1 = {
		tooltip: {
			show: true
		},
		title: {
			text: '电池电量分析图表',
			left: 'center'
		},
		grid: {
        left: '0%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5],
			itemHeight:16
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value',
			name: '单位(%)',
		}],
		series: []
	};
	option2 = {
		tooltip: {
			show: true
		},
		title: {
			text: '二氧化碳分析图表',
			left: 'center'
		},
		grid: {
        left: '0%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5],
			itemHeight:16
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value',
			name: '单位(ppm)'

		}],
		series: []
	};

	option3 = {
		tooltip: {
			show: true
		},
		title: {
			text: '风向分析图表',
			left: 'center'
		},
		grid: {
        left: '0%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5],
			itemHeight:16
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value'
		}],
		series: []
	};
	option4 = {
		tooltip: {
			show: true
		},
		title: {
			text: '土壤水分分析图表',
			left: 'center'
		},
		grid: {
        left: '0%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5],
			itemHeight:16
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value',
			name: '单位(%)'

		}],
		series: []
	};
	option5 = {
		tooltip: {
			show: true
		},
		title: {
			text: '系统5V电压分析图表',
			left: 'center'
		},
		grid: {
        left: '0%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5],
			itemHeight:16
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value',
			name: '单位(v)'
		}],
		series: []
	};
	option6 = {
		tooltip: {
			show: true
		},
		title: {
			text: '降水量分析图表',
			left: 'center'
		},
		grid: {
        left: '0%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5],
			itemHeight:16
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value',
			name: '单位(ml)'
		}],
		series: []
	};
	option7 = {
		tooltip: {
			show: true
		},
		title: {
			text: '空气温度分析图表',
			left: 'center'
		},
		grid: {
        left: '0%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5],
			itemHeight:16
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value',
			name: "单位(℃)"
		}],
		series: []
	};
	option8 = {
		tooltip: {
			show: true
		},
		title: {
			text: '光照度分析图表',
			left: 'center'
		},
		grid: {
        left: '0%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5],
			itemHeight:16
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value',
			name: '单位(lux)'
		}],
		series: []
	};
	option9 = {
		tooltip: {
			show: true
		},
		title: {
			text: '土壤温度分析图表',
			left: 'center'
		},
		grid: {
        left: '0%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5],
			itemHeight:16
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value',
			name: '单位(℃)'
		}],
		series: []
	};
	option10 = {
		tooltip: {
			show: true
		},
		title: {
			text: '空气湿度分析图表',
			left: 'center'
		},
		grid: {
        left: '0%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
		legend: {
			data: [],
			bottom: 0,
			padding: [-5, 5],
			itemHeight:16
		},
		xAxis: [{
			type: 'category',
			data: []
		}],
		yAxis: [{
			type: 'value',
			name: '单位(%)'
		}],
		series: []
	};
	var showVal = getUrlKey('m_id');
	var obj = new Object();
		obj.ckuid = sessionStorage.getItem('ckuid');
		obj.cksid = sessionStorage.getItem('cksid');
		if(showVal == '0'){
			obj.deviceList =getUrlKey('addEqui').split(',');
			obj.channelList = new Array(getUrlKey('texts'));
			obj.beginTime = getUrlKey('beginTime');
			obj.endTime = getUrlKey('endTime');
		}else{
			obj.deviceList = getUrlKey('addEqui').split(',');
			obj.channelList = getUrlKey('channelList').split(',');
			obj.beginTime = getUrlKey('beginTime');
			obj.endTime = getUrlKey('endTime');
		};
		 parent.loadShow();
	$.ajax({
		type: "post",
		url: http + "getStaticData",
		contentType: "application/json",
		headers: { 'Content-type': 'application/json;charset=UTF-8' },
		data: JSON.stringify(obj),
		cache: false,
		success: function(data) {
			parent.removeload();
			$('.bdouterAll').hide();
			$('.bdequipshow').show();
			if(showVal == '0') {
				$('.nav_l_morede').removeClass('active');
				$('.nav_l_de').addClass('active')
				var Oids = getUrlKey('targe');
				var look = getUrlKey('texts');
				var title = getUrlKey('value');
				if(data.object != null) {
					var len = data.object[0].info;
					var times = len[len.length - 1];
					var num=0;
					option0.series.length = 0;
					option0.legend.data.length = 0;
					for(var i = 0; i < data.object.length; i++) {
						for(var j = 0; j < data.object[i].info.length - 1; j++) {
							if(data.object[i].info[j].name == look) {
								num++;
								$("#mains").show();
								$('.bdequipshList').hide();
								var objs = {
									name: data.object[i].deviceName,
									type: 'line',
									stack: '总量',
									data: data.object[i].info[j].data,
								};
								option0.yAxis[0].name = title;
								option0.title.text = look + '分析图表';
								option0.legend.data.push(data.object[i].deviceName);
								option0.series.push(objs);
								option0.xAxis[0].data = times.data;
							}
						};
					};
					if(num%2>0){
							option8.grid.bottom = Math.floor((num+1)/4)*6+'%';
						}else{
							option8.grid.bottom = Math.floor(num/4)*6+'%';
						}
					myChart0.setOption(option0);
				}
			} else {
				$('.nav_l_morede').addClass('active')
				$('.nav_l_de').removeClass('active')
				var newStr = decodeURI(window.location.href.split('?')[1].split('&')[1].split('=')[1]);
				$("#mains").hide();
				$('.bdequipshList').show();
				$('.bdequipshList>li').hide();
				var newStr = getUrlKey('channelList');
				if(newStr.indexOf('电池电量') != -1) {
					var look = '电池电量';
					if(data.object != null) {
						var len = data.object[0].info;
						var times = len[len.length - 1];
						var num=0;
						option1.series.length = 0;
						option1.legend.data.length = 0;
						for(var i = 0; i < data.object.length; i++) {
							for(var j = 0; j < data.object[i].info.length - 1; j++) {
								if(data.object[i].info[j].name == look) {
									num++;
									$("#mainLi1").show();
									var objs = {
										name: data.object[i].deviceName,
										type: 'line',
										stack: '总量',
										data: data.object[i].info[j].data,
									};
									option1.legend.data.push(data.object[i].deviceName);
									option1.series.push(objs);
									option1.xAxis[0].data = times.data
								}
							};
						};
						if(num%4>0){
							option1.grid.bottom = Math.floor((num+1)/4)*10+'%';
						}else{
							option1.grid.bottom = Math.floor(num/4)*10+'%';
						}
						myChart1.setOption(option1);
					}
				};
				if(newStr.indexOf('二氧化碳') != -1) {
					var look = '二氧化碳';
					if(data.object != null) {
						$("#mainLi2").show();
						var len = data.object[0].info;
						var times = len[len.length - 1];
						var num=0;
						option2.series.length = 0;
						option2.legend.data.length = 0;
						for(var i = 0; i < data.object.length; i++) {
							for(var j = 0; j < data.object[i].info.length - 1; j++) {
								if(data.object[i].info[j].name == look) {
									num++;
									$("#mainLi2").show();
									var objs = {
										name: data.object[i].deviceName,
										type: 'line',
										stack: '总量',
										data: data.object[i].info[j].data,
									};
									option2.legend.data.push(data.object[i].deviceName);
									option2.series.push(objs);
									option2.xAxis[0].data = times.data
								}
							};
						};
						if(num%4>0){
							option2.grid.bottom = Math.floor((num+1)/4)*10+'%';
						}else{
							option2.grid.bottom = Math.floor(num/4)*10+'%';
						}
						myChart2.setOption(option2);
					}
				}
				if(newStr.indexOf('风向') != -1) {
					if(data.object != null) {
						var look = '风向';
						var len = data.object[0].info;
						var times = len[len.length - 1];
						var num=0;
						option3.series.length = 0;
						option3.legend.data.length = 0;
						for(var i = 0; i < data.object.length; i++) {
							for(var j = 0; j < data.object[i].info.length - 1; j++) {
								if(data.object[i].info[j].name == look) {
									num++;
									$('#mainLi3').show();
									var legent;
									var objs = {
										name: data.object[i].deviceName,
										type: 'line',
										stack: '总量',
										data: data.object[i].info[j].data,
									};
									option3.legend.data.push(data.object[i].deviceName);
									option3.series.push(objs);
									option3.xAxis[0].data = times.data
								}
							};
						};
						if(num%4>0){
							option3.grid.bottom = Math.floor((num+1)/4)*10+'%';
						}else{
							option3.grid.bottom = Math.floor(num/4)*10+'%';
						}
						myChart3.setOption(option3);
					}
				}
				if(newStr.indexOf('土壤水分') != -1) {
					if(data.object != null) {
						var len = data.object[0].info;
						var times = len[len.length - 1];
						var look = '土壤水分';
						var num=0;
						option4.series.length = 0;
						option4.legend.data.length = 0;
						for(var i = 0; i < data.object.length; i++) {
							for(var j = 0; j < data.object[i].info.length - 1; j++) {
								if(data.object[i].info[j].name == look) {
									num++;
									$('#mainLi4').show();
									var objs = {
										name: data.object[i].deviceName,
										type: 'line',
										stack: '总量',
										data: data.object[i].info[j].data,
									};
									option4.legend.data.push(data.object[i].deviceName);
									option4.series.push(objs);
									option4.xAxis[0].data = times.data
								}
							};
						};
						if(num%4>0){
							option4.grid.bottom = Math.floor((num+1)/4)*10+'%';
						}else{
							option4.grid.bottom = Math.floor(num/4)*10+'%';
						}
						myChart4.setOption(option4);
					}
				}
				if(newStr.indexOf('系统5V电压') != -1) {

					if(data.object != null) {
						var look = '系统5V电压';
						var len = data.object[0].info;
						var times = len[len.length - 1];
						var num=0;
						option5.series.length = 0;
						option5.legend.data.length = 0;
						for(var i = 0; i < data.object.length; i++) {
							for(var j = 0; j < data.object[i].info.length - 1; j++) {
								if(data.object[i].info[j].name == look) {
									num++;
									$('#mainLi5').show();
									var objs = {
										name: data.object[i].deviceName,
										type: 'line',
										stack: '总量',
										data: data.object[i].info[j].data,
									};
									option5.legend.data.push(data.object[i].deviceName);
									option5.series.push(objs);
									option5.xAxis[0].data = times.data
								}
							};
						};
						if(num%4>0){
							option5.grid.bottom = Math.floor((num+1)/4)*10+'%';
						}else{
							option5.grid.bottom = Math.floor(num/4)*10+'%';
						}
						myChart5.setOption(option5);
					}
				}
				if(newStr.indexOf('降水量') != -1) {

					if(data.object != null) {
						var look = '降水量';
						var len = data.object[0].info;
						var times = len[len.length - 1];
						var num=0;
						option6.series.length = 0;
						option6.legend.data.length = 0;
						for(var i = 0; i < data.object.length; i++) {
							for(var j = 0; j < data.object[i].info.length - 1; j++) {
								if(data.object[i].info[j].name == look) {
									num++;
									$('#mainLi6').show();
									var objs = {
										name: data.object[i].deviceName,
										type: 'line',
										stack: '总量',
										data: data.object[i].info[j].data,
									};
									option6.legend.data.push(data.object[i].deviceName);
									option6.series.push(objs);
									option6.xAxis[0].data = times.data
								}
							};
						};
						if(num%4>0){
							option6.grid.bottom = Math.floor((num+1)/4)*10+'%';
						}else{
							option6.grid.bottom = Math.floor(num/4)*10+'%';
						}
						myChart6.setOption(option6);
					}
				}
				if(newStr.indexOf('空气温度') != -1) {
					if(data.object != null) {
						var len = data.object[0].info;
						var times = len[len.length - 1];
						var look = '空气温度';
						var num=0;
						option7.series.length = 0;
						option7.legend.data.length = 0;
						for(var i = 0; i < data.object.length; i++) {
							for(var j = 0; j < data.object[i].info.length - 1; j++) {
								if(data.object[i].info[j].name == look) {
									num++;
									$('#mainLi7').show();
									var objs = {
										name: data.object[i].deviceName,
										type: 'line',
										stack: '总量',
										data: data.object[i].info[j].data,
									};
									option7.legend.data.push(data.object[i].deviceName);
									option7.series.push(objs);
									option7.xAxis[0].data = times.data
								}
							};
						};
						if(num%4>0){
							option7.grid.bottom = Math.floor((num+1)/4)*10+'%';
						}else{
							option7.grid.bottom = Math.floor(num/4)*10+'%';
						}
						myChart7.setOption(option7);
					}
				}
				if(newStr.indexOf('光照度') != -1) {
					if(data.object != null) {
						var len = data.object[0].info;
						var times = len[len.length - 1];
						var look = '光照度';
						var num=0;
						option8.series.length = 0;
						option8.legend.data.length = 0;
						for(var i = 0; i < data.object.length; i++) {
							for(var j = 0; j < data.object[i].info.length - 1; j++) {
								if(data.object[i].info[j].name == look) {
									$('#mainLi8').show();
									num++;
									var objs = {
										name: data.object[i].deviceName,
										type: 'line',
										stack: '总量',
										data: data.object[i].info[j].data,
									};
									option8.legend.data.push(data.object[i].deviceName);
									option8.series.push(objs);
									option8.xAxis[0].data = times.data
								}
							};
						};
						if(num%4>0){
							option8.grid.bottom = Math.floor((num+1)/4)*10+'%';
						}else{
							option8.grid.bottom = Math.floor(num/4)*10+'%';
						}
						myChart8.setOption(option8);
					}
				}
				if(newStr.indexOf('土壤温度') != -1) {
					if(data.object != null) {
						var len = data.object[0].info;
						var times = len[len.length - 1];
						var look = '土壤温度';
						var num=0;
						option9.series.length = 0;
						option9.legend.data.length = 0;
						for(var i = 0; i < data.object.length; i++) {
							for(var j = 0; j < data.object[i].info.length - 1; j++) {
								if(data.object[i].info[j].name == look) {
									num++;
									$('#mainLi9').show();
									var objs = {
										name: data.object[i].deviceName,
										type: 'line',
										stack: '总量',
										data: data.object[i].info[j].data,
									};
									option9.legend.data.push(data.object[i].deviceName);
									option9.series.push(objs);
									option9.xAxis[0].data = times.data
								}
							};
						};
						if(num%4>0){
							option9.grid.bottom = Math.floor((num+1)/4)*10+'%';
						}else{
							option9.grid.bottom = Math.floor(num/4)*10+'%';
						}
						myChart9.setOption(option9);
					}

				}
				if(newStr.indexOf('空气湿度') != -1) {
					if(data.object != null) {
						var look = '空气湿度';
						var len = data.object[0].info;
						var times = len[len.length - 1];
						var num=0;
						option10.series.length = 0;
						option10.legend.data.length = 0;
						for(var i = 0; i < data.object.length; i++) {
							for(var j = 0; j < data.object[i].info.length - 1; j++) {
								if(data.object[i].info[j].name == look) {
									num++;
									$("#mainLi10").show();
									var objs = {
										name: data.object[i].deviceName,
										type: 'line',
										stack: '总量',
										data: data.object[i].info[j].data,
									};
									option10.legend.data.push(data.object[i].deviceName);
									option10.series.push(objs);
									option10.xAxis[0].data = times.data
								}
							};
						};
						if(num%4>0){
							option10.grid.bottom = Math.floor((num+1)/4)*10+'%';
						}else{
							option10.grid.bottom = Math.floor(num/4)*10+'%';
						}
						myChart10.setOption(option10);
					}
				}
			}

		},
		error: function() {
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

});

function getUrlKey(name) {
			return decodeURIComponent((
				new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
		};
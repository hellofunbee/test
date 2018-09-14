$(function () {
    $('.backHome').click(function () { //多数据分析
        window.location.href = './bigData.html?data=' + $(this).attr('data-home');
    })
    $('.btn').click(function () {
        $('.bdequipshowNext').css({
            'left': '-1000rem',
            'width': '100%',
            'overflow': 'hidden'
        });
    });
    $("#mains").click(function () {
        $('.bdequipshowNext').css({
            'left': '0',
            'width': '200%'
        });
    });
    $('.bdequipshList>li').click(function () {
        $(window).scrollTop(0)
        var inde = $(this).index();
        $('.bdequipshowNext').css({
            'left': '0',
            'width': '200%'
        });
        $('.bdequipshListn>li:eq(' + inde + ')').css({
            'left': '0',
            'top': '0'
        }).siblings('li').css('left', '-1000rem');
    });
    $('.rigntMores').css('top', '.62rem');
    var oTr = $('.bdequipshowNext').width() - $(window).innerWidth();
    $('.wrap').on('scroll', function () {
        if ($('.wrap').scrollLeft() >= oTr) {
            $('.toRight').hide()
        } else {
            $('.toRight').show()
        }
    })
    $('.fir_head_allaa').text(getUrlKey('titlesa'));
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
    var dom0n = document.getElementById('mainsn');
    var dom1n = document.getElementById('mainLi1n');
    var dom2n = document.getElementById('mainLi2n');
    var dom3n = document.getElementById('mainLi3n');
    var dom4n = document.getElementById('mainLi4n');
    var dom5n = document.getElementById('mainLi5n');
    var dom6n = document.getElementById('mainLi6n');
    var dom7n = document.getElementById('mainLi7n');
    var dom8n = document.getElementById('mainLi8n');
    var dom9n = document.getElementById('mainLi9n');
    var dom10n = document.getElementById('mainLi10n');
    var myChart0n = echarts.init(dom0n);
    var myChart1n = echarts.init(dom1n);
    var myChart2n = echarts.init(dom2n);
    var myChart3n = echarts.init(dom3n);
    var myChart4n = echarts.init(dom4n);
    var myChart5n = echarts.init(dom5n);
    var myChart6n = echarts.init(dom6n);
    var myChart7n = echarts.init(dom7n);
    var myChart8n = echarts.init(dom8n);
    var myChart9n = echarts.init(dom9n);
    var myChart10n = echarts.init(dom10n);

    option0 = {
        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            data: [],
            padding: [-5, 5],
            textStyle: {
                fontSize: 14,
                color: '#666'
            }
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            name: '',
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }
        }],
        series: []
    }; 
    option1 = {

        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            data: [],
            bottom: 0,
            padding: [-5, 5],
            /*itemHeight:16*/
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            name: '单位(%)',
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }
        }],
        series: []
    };
    option2 = {

        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            data: [],
            bottom: 0,
            padding: [-5, 5],
            /*itemHeight:16*/
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            name: '单位(ppm)',
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }

        }],
        series: []
    };

    option3 = {

        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            data: [],
            bottom: 0,
            padding: [-5, 5],
            itemHeight: 16
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }
        }],
        series: []
    };
    option4 = {

        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            data: [],
            bottom: 0,
            padding: [-5, 5],
            itemHeight: 16
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            name: '单位(%)',
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }

        }],
        series: []
    };
    option5 = {

        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            data: [],
            bottom: 0,
            padding: [-5, 5],
            itemHeight: 16
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            name: '单位(v)',
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }
        }],
        series: []
    };
    option6 = {

        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            data: [],
            bottom: 0,
            padding: [-5, 5],
            itemHeight: 16
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            name: '单位(ml)',
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }
        }],
        series: []
    };
    option7 = {

        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            data: [],
            bottom: 0,
            padding: [-5, 5],
            itemHeight: 16
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            name: "单位(℃)",
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }
        }],
        series: []
    };
    option8 = {

        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            data: [],
            bottom: 0,
            padding: [-5, 5],
            itemHeight: 16
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            name: '单位(lux)',
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }
        }],
        series: []
    };
    option9 = {

        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            data: [],
            bottom: 0,
            padding: [-5, 5],
            itemHeight: 16
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            name: '单位(℃)',
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }
        }],
        series: []
    };
    option10 = {

        tooltip: {
            show: true
        },
        title: {
            text: '',
            left: 'center'
        },
        grid: {
            left: '2%',
            right: '4%',
            bottom: '10%',
            containLabel: true,

        },
        legend: {
            data: [],
            bottom: 0,
            padding: [-5, 5],
            itemHeight: 16
        },
        xAxis: [{
            type: 'category',
            data: [],
            axisLabel: {
                formatter: '',
            },
        }],
        yAxis: [{
            type: 'value',
            name: '单位(%)',
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(0,0,0,1)']
                }
            }
        }],
        series: []
    };
	
	// 名字左移，上移
	var optionList = [option0, option2, option3, option4, option5, option6, option7, option8, option9, option10];
	optionList.forEach(function (item) {
		item.legend.bottom = '0px';
		item.legend.left = 0;
		console.log(item.legend);
	})
	
	
    var showVal = getUrlKey('m_id');
    var obj = new Object();
    obj.ckuid = sessionStorage.getItem('ckuid');
    obj.cksid = sessionStorage.getItem('cksid');
    if (showVal == '0') { //单
        $('.backHome').attr('data-home', 'only')
        obj.deviceList = getUrlKey('addEqui').split(',');
        obj.channelList = new Array(getUrlKey('texts'));
        obj.beginTime = getUrlKey('beginTime');
        obj.endTime = getUrlKey('endTime');

    } else {
        $('.backHome').attr('data-home', 'more')
        obj.deviceList = getUrlKey('addEqui').split(',');
        obj.channelList = getUrlKey('channelList').split(',');
        obj.beginTime = getUrlKey('beginTime');
        obj.endTime = getUrlKey('endTime');
    };
    loadShow();
    $.ajax({
        type: "post",
        url: http + "getStaticData",
        contentType: "application/json",
        headers: {
            'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(obj),
        cache: false,
        success: function (data) {
            removeload();
            if (showVal == '0') { //单
                $('.nav_l_morede').removeClass('active');
                $('.nav_l_de').addClass('active')
                var Oids = getUrlKey('targe');
                var look = getUrlKey('texts');
                var title = getUrlKey('value');
                $(".mainsout").find('.title').text(look + '分析图表');
                $(".mainsoutn").find('.title').text(look + '分析图表');
                if (data.object != null) {
                    var len = data.object[0].info;
                    var times = len[len.length - 1];
                    var num = 0;
                    option0.series.length = 0;
                    option0.legend.data.length = 0;
                    for (var i = 0; i < data.object.length; i++) {
												/* data.object[i].info.length==1?data.object[i].info.length=2:data.object[i].info.length=2;
												for (var j = 0; j < data.object[i].info.length - 1; j++) {
														if (true) { */
                        for (var j = 0; j < data.object[i].info.length - 1; j++) {
                            if (data.object[i].info[j].name == look) {
                                num++;
                                $("#mains").show();
                                $("#mainsn").show();
                                $('.bdequipshList').hide();
                                $('.bdequipshListn').hide();
                                var objs = {
                                	name: data.object[i].deviceName,
                                	type: 'line',
                                	stack: '总量' + i + j,
                                	data: data.object[i].info[j].data,
                                };
                                // console.log(objs.data.sort());
                                option0.yAxis[0].name = title;
                                option0.legend.data.push(data.object[i].deviceName);
                                option0.series.push(objs);
                                option0.xAxis[0].data = times.data1;
                                option0.xAxis[0].axisLabel.formatter = function (val) {
                                	var strs = val.split(''); //字符串数组  
                                	var str = ''
                                	for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                		str += s;
                                		if (!(i % 10)) str += '\n';
                                	}
                                	return str
                                }
                            }
                        };
                    };
                    if (num % 2 > 0) {
                        option0.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                    } else {
                        option0.grid.bottom = Math.floor(num / 2) * 10 + '%';
                    }
                    myChart0.setOption(option0);
                    myChart0n.setOption(option0);
                }
            } else {
                var newStr = decodeURI(window.location.href.split('?')[1].split('&')[1].split('=')[
                    1]);
                $(".mainsout").hide(); //单
                $('.bdequipshList').show();
                $('.bdequipshList>li').hide();
                $(".mainsoutn").hide(); //多
                $('.bdequipshListn').show();
                $('.bdequipshListn>li').hide();
                var newStr = getUrlKey('channelList');
                console.log(newStr);
                if (newStr.indexOf('电池电量') != -1) {
                    var look = '电池电量';
                    if (data.object != null) {
                        var len = data.object[0].info;
                        var times = len[len.length - 1];
                        var num = 0;
                        option1.series.length = 0;
                        option1.legend.data.length = 0;
                        for (var i = 0; i < data.object.length; i++) {
                            for (var j = 0; j < data.object[i].info.length - 1; j++) {
                                if (data.object[i].info[j].name == look) {
                                    num++;
                                    $(".mainLi1").show();
                                    $(".mainLi1n").show();
                                    var objs = {
                                        name: data.object[i].deviceName,
                                        type: 'line',
                                        stack: '总量' + j + i,
                                        data: data.object[i].info[j].data,
                                    };
                                    option1.legend.data.push(data.object[i].deviceName);
                                    option1.series.push(objs);
                                    option1.xAxis[0].data = times.data11
                                    option1.xAxis[0].axisLabel.formatter = function (val) {
                                        var strs = val.split(''); //字符串数组  
                                        var str = ''
                                        for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                            str += s;
                                            if (!(i % 10)) str += '\n';
                                        }
                                        return str
                                    }
                                }
                            };
                        };
                        if (num % 2 > 0) {
                            option1.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                        } else {
                            option1.grid.bottom = Math.floor(num / 2) * 10 + '%';
                        }
                        myChart1.setOption(option1);
                        myChart1n.setOption(option1);
                    }
                };
                if (newStr.indexOf('二氧化碳') != -1) {
                    var look = '二氧化碳';
                    if (data.object != null) {
                        var len = data.object[0].info;
                        var times = len[len.length - 1];
                        var num = 0;
                        option2.series.length = 0;
                        option2.legend.data.length = 0;
                        for (var i = 0; i < data.object.length; i++) {
                            for (var j = 0; j < data.object[i].info.length - 1; j++) {
                                if (data.object[i].info[j].name == look) {
                                    num++;
                                    $(".mainLi2").show();
                                    $(".mainLi2n").show();
                                    var objs = {
                                        name: data.object[i].deviceName,
                                        type: 'line',
                                        stack: '总量' + j + i,
                                        data: data.object[i].info[j].data,
                                    };

                                    option2.legend.data.push(data.object[i].deviceName);
                                    option2.series.push(objs);
                                    option2.xAxis[0].data = times.data1;
                                    option2.xAxis[0].axisLabel.formatter = function (val) {
                                        var strs = val.split(''); //字符串数组  
                                        var str = ''
                                        for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                            str += s;
                                            if (!(i % 10)) str += '\n';
                                        }
                                        return str
                                    }
                                }
                            };
                        };
                        if (num % 2 > 0) {
                            option2.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                        } else {
                            option2.grid.bottom = Math.floor(num / 2) * 10 + '%';
                        }
                        console.log(option2);
                        myChart2.setOption(option2);
                        myChart2n.setOption(option2);
                    }
                }
                if (newStr.indexOf('风向') != -1) {
                    if (data.object != null) {
                        var look = '风向';
                        var len = data.object[0].info;
                        var times = len[len.length - 1];
                        var num = 0;
                        option3.series.length = 0;
                        option3.legend.data.length = 0;
                        for (var i = 0; i < data.object.length; i++) {
                            for (var j = 0; j < data.object[i].info.length - 1; j++) {
                                if (data.object[i].info[j].name == look) {
                                    num++;
                                    $('.mainLi3').show();
                                    $('.mainLi3n').show();
                                    var legent;
                                    var objs = {
                                        name: data.object[i].deviceName,
                                        type: 'line',
                                        stack: '总量' + j + i,
                                        data: data.object[i].info[j].data,
                                    };
                                    option3.legend.data.push(data.object[i].deviceName);
                                    option3.series.push(objs);
                                    option3.xAxis[0].data = times.data1;
                                    option3.xAxis[0].axisLabel.formatter = function (val) {
                                        var strs = val.split(''); //字符串数组  
                                        var str = ''
                                        for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                            str += s;
                                            if (!(i % 10)) str += '\n';
                                        }
                                        return str
                                    }
                                }
                            };
                        };
                        if (num % 2 > 0) {
                            option3.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                        } else {
                            option3.grid.bottom = Math.floor(num / 2) * 10 + '%';
                        }
                        myChart3.setOption(option3);
                        myChart3n.setOption(option3);
                    }
                }
                if (newStr.indexOf('土壤水分') != -1) {
                    if (data.object != null) {
                        var len = data.object[0].info;
                        var times = len[len.length - 1];
                        var look = '土壤水分';
                        var num = 0;
                        option4.series.length = 0;
                        option4.legend.data.length = 0;
                        for (var i = 0; i < data.object.length; i++) {
                            for (var j = 0; j < data.object[i].info.length - 1; j++) {
                                if (data.object[i].info[j].name == look) {
                                    num++;
                                    $('.mainLi4').show();
                                    $('.mainLi4n').show();
                                    var objs = {
                                        name: data.object[i].deviceName,
                                        type: 'line',
                                        stack: '总量' + j + i,
                                        data: data.object[i].info[j].data,
                                    };
                                    option4.legend.data.push(data.object[i].deviceName);
                                    option4.series.push(objs);
                                    option4.xAxis[0].data = times.data1;
                                    option4.xAxis[0].axisLabel.formatter = function (val) {
                                        var strs = val.split(''); //字符串数组  
                                        var str = ''
                                        for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                            str += s;
                                            if (!(i % 10)) str += '\n';
                                        }
                                        return str
                                    }
                                }
                            };
                        };
                        if (num % 2 > 0) {
                            option4.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                        } else {
                            option4.grid.bottom = Math.floor(num / 2) * 10 + '%';
                        }
                        myChart4.setOption(option4);
                        myChart4n.setOption(option4);
                    }
                }
                if (newStr.indexOf('系统5V电压') != -1) {

                    if (data.object != null) {
                        var look = '系统5V电压';
                        var len = data.object[0].info;
                        var times = len[len.length - 1];
                        var num = 0;
                        option5.series.length = 0;
                        option5.legend.data.length = 0;
                        for (var i = 0; i < data.object.length; i++) {
                            for (var j = 0; j < data.object[i].info.length - 1; j++) {
                                if (data.object[i].info[j].name == look) {
                                    num++;
                                    $('.mainLi5').show();
                                    $('.mainLi5n').show();
                                    var objs = {
                                        name: data.object[i].deviceName,
                                        type: 'line',
                                        stack: '总量' + j + i,
                                        data: data.object[i].info[j].data,
                                    };
                                    option5.legend.data.push(data.object[i].deviceName);
                                    option5.series.push(objs);
                                    option5.xAxis[0].data = times.data1;
                                    option5.xAxis[0].axisLabel.formatter = function (val) {
                                        var strs = val.split(''); //字符串数组  
                                        var str = ''
                                        for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                            str += s;
                                            if (!(i % 10)) str += '\n';
                                        }
                                        return str
                                    }
                                }
                            };
                        };
                        if (num % 2 > 0) {
                            option5.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                        } else {
                            option5.grid.bottom = Math.floor(num / 2) * 10 + '%';
                        }
                        myChart5.setOption(option5);
                        myChart5n.setOption(option5);
                    }
                }
                if (newStr.indexOf('降水量') != -1) {

                    if (data.object != null) {
                        var look = '降水量';
                        var len = data.object[0].info;
                        var times = len[len.length - 1];
                        var num = 0;
                        option6.series.length = 0;
                        option6.legend.data.length = 0;
                        for (var i = 0; i < data.object.length; i++) {
                            for (var j = 0; j < data.object[i].info.length - 1; j++) {
                                if (data.object[i].info[j].name == look) {
                                    num++;
                                    $('.mainLi6').show();
                                    $('.mainLi6n').show();
                                    var objs = {
                                        name: data.object[i].deviceName,
                                        type: 'line',
                                        stack: '总量' + j + i,
                                        data: data.object[i].info[j].data,
                                    };
                                    option6.legend.data.push(data.object[i].deviceName);
                                    option6.series.push(objs);
                                    option6.xAxis[0].data = times.data1;
                                    option6.xAxis[0].axisLabel.formatter = function (val) {
                                        var strs = val.split(''); //字符串数组  
                                        var str = ''
                                        for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                            str += s;
                                            if (!(i % 10)) str += '\n';
                                        }
                                        return str
                                    }
                                }
                            };
                        };
                        if (num % 2 > 0) {
                            option6.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                        } else {
                            option6.grid.bottom = Math.floor(num / 2) * 10 + '%';
                        }
                        myChart6.setOption(option6);
                        myChart6n.setOption(option6);
                    }
                }
                if (newStr.indexOf('空气温度') != -1) {
                    if (data.object != null) {
                        var len = data.object[0].info;
                        var times = len[len.length - 1];
                        var look = '空气温度';
                        var num = 0;
                        option7.series.length = 0;
                        option7.legend.data.length = 0;
                        for (var i = 0; i < data.object.length; i++) {
                            for (var j = 0; j < data.object[i].info.length - 1; j++) {
                                if (data.object[i].info[j].name == look) {
                                    num++;
                                    $('.mainLi7').show();
                                    $('.mainLi7n').show();
                                    var objs = {
                                        name: data.object[i].deviceName,
                                        type: 'line',
                                        stack: '总量' + j + i,
                                        data: data.object[i].info[j].data,
                                    };
									console.log(objs);
                                    option7.legend.data.push(data.object[i].deviceName);
                                    option7.series.push(objs);
                                    option7.xAxis[0].data = times.data1;
                                    option7.xAxis[0].axisLabel.formatter = function (val) {
                                        var strs = val.split(''); //字符串数组  
                                        var str = ''
                                        for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                            str += s;
                                            if (!(i % 10)) str += '\n';
                                        }
                                        return str
                                    }
                                }
                            };
                        };
                        if (num % 2 > 0) {
                            option7.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                        } else {
                            option7.grid.bottom = Math.floor(num / 2) * 10 + '%';
                        }
                        myChart7.setOption(option7);
                        myChart7n.setOption(option7);
                    }
                }
                if (newStr.indexOf('光照度') != -1) {
                    if (data.object != null) {
                        var len = data.object[0].info;
                        var times = len[len.length - 1];
                        var look = '光照度';
                        var num = 0;
                        option8.series.length = 0;
                        option8.legend.data.length = 0;
                        for (var i = 0; i < data.object.length; i++) {
                            for (var j = 0; j < data.object[i].info.length - 1; j++) {
                                if (data.object[i].info[j].name == look) {
                                    $('.mainLi8').show();
                                    $('.mainLi8n').show();
                                    num++;
                                    var objs = {
                                        name: data.object[i].deviceName,
                                        type: 'line',
                                        stack: '总量' + j + i,
                                        data: data.object[i].info[j].data,
                                    };
                                    option8.legend.data.push(data.object[i].deviceName);
                                    option8.series.push(objs);
                                    option8.xAxis[0].data = times.data1;
                                    option8.xAxis[0].axisLabel.formatter = function (val) {
                                        var strs = val.split(''); //字符串数组  
                                        var str = ''
                                        for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                            str += s;
                                            if (!(i % 10)) str += '\n';
                                        }
                                        return str
                                    }
                                }
                            };
                        };
                        if (num % 2 > 0) {
                            option8.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                        } else {
                            option8.grid.bottom = Math.floor(num / 2) * 10 + '%';
                        }
                        myChart8.setOption(option8);
                        myChart8n.setOption(option8);
                    }
                }
                if (newStr.indexOf('土壤温度') != -1) {
                    if (data.object != null) {
                        var len = data.object[0].info;
                        var times = len[len.length - 1];
                        var look = '土壤温度';
                        var num = 0;
                        option9.series.length = 0;
                        option9.legend.data.length = 0;
                        for (var i = 0; i < data.object.length; i++) {
                            for (var j = 0; j < data.object[i].info.length - 1; j++) {
                                if (data.object[i].info[j].name == look) {
                                    num++;
                                    $('.mainLi9').show();
                                    $('.mainLi9n').show();
                                    var objs = {
                                        name: data.object[i].deviceName,
                                        type: 'line',
                                        stack: '总量' + j + i,
                                        data: data.object[i].info[j].data,
                                    };
                                    option9.legend.data.push(data.object[i].deviceName);
                                    option9.series.push(objs);
                                    option9.xAxis[0].data = times.data1;
                                    option9.xAxis[0].axisLabel.formatter = function (val) {
                                        var strs = val.split(''); //字符串数组  
                                        var str = ''
                                        for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                            str += s;
                                            if (!(i % 10)) str += '\n';
                                        }
                                        return str
                                    }
                                }
                            };
                        };
                        if (num % 2 > 0) {
                            option9.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                        } else {
                            option9.grid.bottom = Math.floor(num / 2) * 10 + '%';
                        }
                        myChart9.setOption(option9);
                        myChart9n.setOption(option9);
                    }

                }
                if (newStr.indexOf('空气湿度') != -1) {
                    if (data.object != null) {
                        var look = '空气湿度';
                        var len = data.object[0].info;
                        var times = len[len.length - 1];
                        var num = 0;
                        option10.series.length = 0;
                        option10.legend.data.length = 0;
                        for (var i = 0; i < data.object.length; i++) {
                            for (var j = 0; j < data.object[i].info.length - 1; j++) {
                                if (data.object[i].info[j].name == look) {
                                    num++;
                                    $(".mainLi10").show();
                                    $(".mainLi10n").show();
                                    var objs = {
                                        name: data.object[i].deviceName,
                                        type: 'line',
                                        stack: '总量' + j + i,
                                        data: data.object[i].info[j].data,
                                    };
                                    option10.legend.data.push(data.object[i].deviceName);
                                    option10.series.push(objs);
                                    option10.xAxis[0].data = times.data1;
                                    option10.xAxis[0].axisLabel.formatter = function (val) {
                                        var strs = val.split(''); //字符串数组  
                                        var str = ''
                                        for (var i = 0, s; s = strs[i++];) { //遍历字符串数组  
                                            str += s;
                                            if (!(i % 10)) str += '\n';
                                        }
                                        return str
                                    }
                                }
                            };
                        };
                        if (num % 2 > 0) {
                            option10.grid.bottom = Math.floor((num + 1) / 2) * 10 + '%';
                        } else {
                            option10.grid.bottom = Math.floor(num / 2) * 10 + '%';
                        }
                        myChart10.setOption(option10);
                        myChart10n.setOption(option10);
                    }
                }
            }
        },
        error: function () {
            removeload();
            swal({
                title: '请求失败，请重新尝试',
                text: "",
                type: "warning",
                showCancelButton: false,
                confirmButtonColor: "#3366FF",
                confirmButtonText: "确定",
                closeOnConfirm: true
            });
            return
        }
    });

});

function getUrlKey(name) {
    /* var u = (new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20');
	console.log(name);
	console.log(decodeURIComponent(u));  */
    // 根据正则匹配name对应的URL地址中的信息，进行解码返回
    return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1]
        .replace(/\+/g, '%20')) || null;
};


$(function () {
	$('#tips').hide()
	window.setTimeout(function () {
		var nima = $(document).height() - $(window).height();
		console.log($(document).height());
		console.log(nima);
		if (nima > 0) {
			$('#tips').show()
		} else {
			$('#tips').hide()
		}
		$(document).scroll(function () {
			if ($(document).scrollTop() - nima >= 0) {
				$('#tips').hide()
			} else {
				$('#tips').show()
			}
		})
	}, 500);
	
})
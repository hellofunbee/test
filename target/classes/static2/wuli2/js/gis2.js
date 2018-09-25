var rootData;
//创建和初始化地图函数：
function setMapEvent(){
    map.enableScrollWheelZoom();
    map.enableKeyboard();
    map.enableDragging();
    map.enableDoubleClickZoom();
}
function addClickHandler(target,window){
    target.addEventListener("click",function(){
        target.openInfoWindow(window);
    });
}
//  设置文本框



//向地图添加控件
function addMapControl(){
    var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
    scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
    map.addControl(scaleControl);
    var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:0});
    map.addControl(navControl);
    var overviewControl = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:false});
    map.addControl(overviewControl);
}
var map;

//---------------------------------------------------1



// -------------------------------地图-------------------------------------------------------
function shebei_createMap(){
    map = new BMap.Map("map",{minZoom:4,maxZoom:12});
    map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
    map.enableScrollWheelZoom();
}
//b_createMap()
function c_createMap(){
    map = new BMap.Map("c_allmap",{minZoom:4,maxZoom:12});
    map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
    map.enableScrollWheelZoom();
};
function b_createMap(){//生产--物种
    map = new BMap.Map("allmap",{minZoom:4,maxZoom:12});
    map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
    map.enableScrollWheelZoom();
}
function d_createMap(){//预警
    map = new BMap.Map("warmap",{minZoom:4,maxZoom:12});
    map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
    map.enableScrollWheelZoom();
}
function e_createMap(){//分布图--地区没有
	map = new BMap.Map("nothmap",{minZoom:4,maxZoom:12});
    map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
    map.enableScrollWheelZoom();
}
function f_createMap(){//预警--地区没有
	map = new BMap.Map("nowarmap",{minZoom:4,maxZoom:12});
    map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
    map.enableScrollWheelZoom();
}
e_createMap();
/*b_createMap();*/
f_createMap();
/*d_createMap()*/
//c_createMap()
//var lis = document.querySelectorAll(".uu_map>li");
//for(var i = 0;i<lis.length;i++){
//    lis[i].onclick =  function () {
//        var index_name = this.innerText;
// 设置区块
function getBoundary(v,index,html,type){
    var bdary = new BMap.Boundary();
    bdary.get(v, function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        if (count === 0) {
            swal({
                        title: '未能获取当前输入行政区域',
                        text: "2秒后关闭",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#3366FF",
                        timer: 2000
                    });
            return ;
        }
        var pointArray = [];
        for (var i = 0; i < count; i++) {
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#79C2F7", fillColor:"#79C2F7",strokeOpacity: 1}); //建立多边形覆盖物
            map.addOverlay(ply);  //添加覆盖物
            pointArray = pointArray.concat(ply.getPath());
        }
        map.setViewport(pointArray);    //调整视野
        var lng=pointArray[0].lng
        var lat=pointArray[0].lat
        if(type==1){
        	windowinfo(lng,lat,index,html)
        }else{
        	windowinfoBin(lng,lat,index,html)
        }
            
    });
}
// 信息窗口
function windowinfo(lng,lat,index,html){
    if(index!='-1'){
        var str=""
        var str1="<p style='color: #0E64AD;text-align: center;font-size: .075rem'>"+html+"</p>"
        $(rootData[index].d_content).each(function(i,el){
        	if(rootData[index].d_content[i].d_content==''&&rootData[index].d_content[i].d_value==''){
        		return;
        	}
            str+="<p style='color: #02265A;font-size: .075rem;line-height: .15rem'><span style='display: inline-block;width:.1rem;line-height: .15rem'>"+(i+1)+"</span> <span style='display: inline-block;width: .5rem;line-height: .15rem'>"+rootData[index].d_content[i].d_content+"</span><span style='color:#0E64AD'>"+rootData[index].d_content[i].d_value+"</span></p>"
        })
        var sContent =str1+str;
    }else{
        var sContent=html
    }
    var point = new BMap.Point(lng,lat);
    map.centerAndZoom(point, 16);
    var opts = {
        width : 200,     // 信息窗口宽度
        // 信息窗口高度
        title : "" , // 信息窗口标题
        enableMessage:false,//设置允许信息窗发送短息
        message:""
    }
    var infoWindow = new BMap.InfoWindow(sContent,opts);  // 创建信息窗口对象
    map.openInfoWindow(infoWindow,point); //开启信息窗口
     $('.BMap_pop>img').hide();
    document.getElementById("r-result").innerHTML = "信息窗口的内容是：<br />" + infoWindow.getContent();
}

//生产分布--饼图
function windowinfoBin(lng,lat,index,html){
    if(index!='-1'){
        var str=""
       /* var str1="<p style='color: #0E64AD;text-align: center;font-size: .075rem'>"+html+"</p>"*/
        	if(rootData[index].d_content[0].d_content==''&&rootData[index].d_content[0].d_value==''){
        		return;
        	}else{
        		str+="<div style='display:flex;font-size:.075rem;text-align:center'><div style='flex:1'><div style='color:#02265A;margin-bottom:.05rem'>种植阶段</div><div style='color:#0887D6'>100</div></div>"+
        	"<div style='flex:1'><div style='color:#02265A;margin-bottom:.05rem'>总面积(亩)</div><div style='color:#0887D6'>200亩</div></div>"+
        	"<div style='flex:1.5'><div style='color:#02265A;margin-bottom:.05rem'>已完成面积</div><div style='color:#0887D6'>300亩</div></div></div>";
        	str+="<div id='Binlie' style='width:200px;height:200px'>123</div>"
        
        	}
        var sContent =str;
    }else{
        var sContent=html
    }
    var point = new BMap.Point(lng,lat);
    map.centerAndZoom(point, 16);
    var opts = {
        width : 200,     // 信息窗口宽度
        // 信息窗口高度
        title : "" , // 信息窗口标题
        enableMessage:false,//设置允许信息窗发送短息
        message:""
    }
    var infoWindow = new BMap.InfoWindow(sContent,opts);  // 创建信息窗口对象
    map.openInfoWindow(infoWindow,point); //开启信息窗口
    $('.BMap_pop>img').hide();
    var oa='<span style="color:#0887D6;">  50%<span>'
     var ob='<span style="color:#0887D6">  50%<span>'
  	 $('#Binlie').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
        },
         colors:[
            '#0CACF4',//第二个颜色
            '#FF5252',//第一个颜色，
          ],
        title: {
            text: ''
        },
         credits: {  
          enabled:false  
		},
		 plotOptions: {
	        pie: {
		            allowPointSelect: false,
		            cursor: 'pointer',
		            dataLabels: {
		                enabled: false
		            },
		            showInLegend: true
		        }
	    },
        legend : {
		    itemStyle : {
		        'fontWeight' : 'normal',
		        'color':'',
		        'font-size':'.075rem'
		    }
		},
	
        series: [{
            type: 'pie',
            name: '生产分布-',
            data: [
	            {name:'已完成比例'+oa,y:45,text:'已完成面积:',option:'亩'},
	            {name:'未完成比例'+ob,y:55,text:'未完成面积:',option:'亩'}
            ]
        }],
 		tooltip: { 
			formatter: function() { 
				console.log(this);
				return this.key+'<br/>'+this.point.options.text+
				'<span style="color:#0887D6">'+this.point.options.y+this.point.options.option+'</span>';
			} 
		}
    });
    document.getElementById("r-result").innerHTML = "信息窗口的内容是：<br />" + infoWindow.getContent();
}


var strinfo = $("#li2").attr("data-str");
/*父页面点击跳转至地图页面*/
function go(type,nums,deviceIds){
	if(type==1){
		window.location.href='./generalInfo.html?num='+nums;
	}else if(type==2){//视频信息
		//secondClick(deviceIds);
	}else if(type==3){//图片
		window.location.href='./picInfo.html?num='+nums;
	}else if(type==4){//即时
		window.location.href='./policyInfo.html?turnTo=2';
	}else if(type==5){
		window.location.href='./productControl.html?num='+nums;
	}else if(type==6){
		window.location.href='./expertServe.html';
	}
}

function secondClick(deviceIds){
		var browser = {
			  versions: function () {
			  var u = navigator.userAgent, app = navigator.appVersion;
			  return {//移动终端浏览器版本信息
			   trident: u.indexOf('Trident') > -1, //IE内核
			   presto: u.indexOf('Presto') > -1, //opera内核
			   webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
			   gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
			   mobile: !!u.match(/AppleWebKit.*Mobile/i) || !!u.match(/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/), //是否为移动终端
			   ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
			   android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
			   iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
			   iPad: u.indexOf('iPad') > -1, //是否iPad
			   webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
			  };
			  }(),
			  language: (navigator.browserLanguage || navigator.language).toLowerCase()
			}
			if (browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
				getDeviceIDiOS(deviceIds,0,0,0,0);
			}
			if (browser.versions.android) {
				window.AndroidView.showView(deviceIds,0,0,0,0);
			}
}
function active() {
	var state;
	$('.treeleft_stu_m_name').each(function() {
		if($(this).hasClass('active')) {
			state = $(this)
		}
	})
	return state
}

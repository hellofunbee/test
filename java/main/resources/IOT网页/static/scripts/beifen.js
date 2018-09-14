
//�����ͳ�ʼ����ͼ������

function initMap(){
    createMap();//������ͼ
    setMapEvent();//���õ�ͼ�¼�
    addMapControl();//���ͼ��ӿؼ�
    addMapOverlay();//���ͼ��Ӹ�����
}
// ������ͼ
function createMap(){
    map = new BMap.Map("map");
    map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
}

function setMapEvent(){
    map.enableScrollWheelZoom();
    map.enableKeyboard();
    map.enableDragging();
    map.enableDoubleClickZoom()
}
function addClickHandler(target,window){
    target.addEventListener("click",function(){
        target.openInfoWindow(window);
    });
}
//  �����ı���
function addMapOverlay(){
    var markers = [
        {content:"�豸��ţ�10.00.21.24",title:"��ƽ���齭Ħ������",imageOffset: {width:-46,height:-21},position:{lat:40.105369,lng:116.296562}},
        {content:"�豸��ţ�10.00.21.24",title:"��ƽ���齭Ħ������",imageOffset: {width:-46,height:-21},position:{lat:40.10569,lng:116.29562}},
        {content:"�豸��ţ�10.00.21.24",title:"��ƽ���齭Ħ������",imageOffset: {width:-46,height:-21},position:{lat:40.10569,lng:116.29662}}
    ];
    var sContent =
        "<p style='margin:0 0 5px 0;padding:0.2em 0;color:#51c626 '>�������ƣ����Ƽ�ʱ���Ƽ�ʱ���Ƽ�ʱ������</p>" +
        "<ul style='overflow: hidden'>" +
        "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>ʹ����</li>" +
        "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>ʹ����</li>" +
        "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>ʹ����</li>" +
        "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>ʹ����</li>" +
        "<li style='float: left;margin:2px 10px 2px 0px ;width: 28%;color:#51c626'>ʹ����</li>" +
        "</ul>"+
        "<ul style='overflow: hidden'>" +
        "<li style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;'>ʹ����</li>" +
        "<li style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;'>ʹ����</li>" +
        "<li style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;'>ʹ����</li>" +
        "<li style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;'>ʹ����</li>" +
        "<li style='float: left;width: 25%;color:#51c626;text-align: center;padding:2px;border: 1px solid #51c626;margin:2px 10px 2px 0;box-sizing: border-box;border-radius: 4px;'>ʹ����</li>" +
        "</ul>"+

        "</div>";


    for(var index = 0; index < markers.length; index++ ){
        var point = new BMap.Point(markers[index].position.lng,markers[index].position.lat);
        var marker = new BMap.Marker(point,{icon:new BMap.Icon("http://api.map.baidu.com/lbsapi/createmap/images/icon.png",new BMap.Size(20,25),{
            imageOffset: new BMap.Size(markers[index].imageOffset.width,markers[index].imageOffset.height)
        })});
        var label = new BMap.Label(markers[index].title,{offset: new BMap.Size(25,5)});
        var opts = {
            width: 200,
            title: markers[index].title,
            enableMessage: false
        };
        var infoWindow = new BMap.InfoWindow(sContent);  // ������Ϣ���ڶ���
//            var infoWindow = new BMap.InfoWindow(markers[index].content,opts);
        marker.setLabel(label);
        addClickHandler(marker,infoWindow);
        map.addOverlay(marker);
    }
}
//���ͼ��ӿؼ�
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
initMap();
//---------------------------------------------------1


function b_createMap(){
    map = new BMap.Map("allmap");
    map.centerAndZoom(new BMap.Point(116.297461,40.106777),16);
    map.enableScrollWheelZoom();
}



var lis = document.querySelectorAll(".uu_map>li");
for(var i = 0;i<lis.length;i++){
    lis[i].onclick =  function () {
        var index_name = this.innerText;


        // ��������
        function getBoundary(){
            var bdary = new BMap.Boundary();

            bdary.get(index_name, function(rs){       //��ȡ��������

                var count = rs.boundaries.length; //��������ĵ��ж��ٸ�
                if (count === 0) {
                    alert('δ�ܻ�ȡ��ǰ������������');
                    return ;
                }
                var pointArray = [];
                for (var i = 0; i < count; i++) {
                    var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#79C2F7"}); //��������θ�����
                    map.addOverlay(ply);  //��Ӹ�����
                    pointArray = pointArray.concat(ply.getPath());
                }
                map.setViewport(pointArray);    //������Ұ
                addlabel();
            });
        }




        $("#lis2").on("click", function () {

        })

        setTimeout(function(){
            getBoundary();
            windowinfo();
        }, 1000);


        function addlabel() {
            var pointArray = [
                new BMap.Point(121.716076,23.703799),
                new BMap.Point(112.121885,14.570616),
                new BMap.Point(123.776573,25.695422)];
            var optsArray = [{},{},{}];
            var labelArray = [];
            var contentArray = [
                "̨�����й��ģ�",
                "�Ϻ����й��ģ�",
                "���㵺���й��ģ�"];
            for(var i = 0;i < pointArray.length; i++) {
                optsArray[i].position = pointArray[i];
                labelArray[i] = new BMap.Label(contentArray[i],optsArray[i]);
                labelArray[i].setStyle({
                    color : "red",
                    fontSize : "12px",
                    height : "20px",
                    lineHeight : "20px",
                    fontFamily:"΢���ź�"
                });
                map.addOverlay(labelArray[i]);
            }
        }
        // ��Ϣ����
        function windowinfo(){
            var sContent ="<p>lalal</p><p>lalal</p>";
            var point = new BMap.Point(116.297461,40.106777);
            map.centerAndZoom(point, 16);
            var infoWindow = new BMap.InfoWindow(sContent);  // ������Ϣ���ڶ���
            map.openInfoWindow(infoWindow,point); //������Ϣ����
            document.getElementById("r-result").innerHTML = "��Ϣ���ڵ������ǣ�<br />" + infoWindow.getContent();
        }

        // ��� ��ȡ��Ϣ---------------------------------------



        var strinfo = $("#li2").attr("data-str")
        function windowinfo(){
            var sContent = strinfo;
            var point = new BMap.Point(116.297461,40.106777);
            map.centerAndZoom(point, 16);
            var infoWindow = new BMap.InfoWindow(sContent);  // ������Ϣ���ڶ���
            map.openInfoWindow(infoWindow,point); //������Ϣ����
            document.getElementById("r-result").innerHTML = "��Ϣ���ڵ������ǣ�<br />" + infoWindow.getContent();
        }

        b_createMap();

    }
};
// -------------------------------------------------2

// ����
$("#li2").on("click", function () {
    // �ı���ģ��
    console.log(123);
    $(".button").attr("data-index","2");
    //  ��ȡ��Ϣ �ӿ�
    $.ajax({
        url:http + "listDistribution",
        type:"post",
        contentType: "application/json",
        cache: false,
        data:JSON.stringify({
            "ckuid": sessionStorage.getItem('ckuid'),
            "cksid": sessionStorage.getItem('cksid'),
            "d_type":"2"   // 1 ����   2 ����
        }),
        success: function (data) {

            console.log(data);
            var html = template("city",data);
            $(".uu_map").html(html);

            $("#li2").attr("data-str","<p>lalal</p><p>lalal</p>");
            if(data.state==0){

            }else if(data.state==2){
                alert("��¼ʧЧ�������µ�¼");
                //window.parent.location.href="login.html";
                return false
            } else{
                alert(data.msg);
            }

        }
    })


});
// ����
$("#li3").on("click", function () {
    $("#li2").attr("data-str","<p>hahahahha</p><p>hhahahah</p>");
    $(".button").attr("data-index","1");
    //  ��ȡ��Ϣ �ӿ�
    $.ajax({
        url:http + "listDistribution",
        type:"post",
        contentType: "application/json",
        cache: false,
        data:JSON.stringify({
            "ckuid": sessionStorage.getItem('ckuid'),
            "cksid": sessionStorage.getItem('cksid'),
            "d_type":"1"   // 1 ����   2 ����
        }),
        success: function (data) {

            console.log(data);

            if(data.state==0){

            }else if(data.state==2){
                alert("��¼ʧЧ�������µ�¼");
                //window.parent.location.href="login.html";
                return false
            } else{
                alert(data.msg);
            }

        }
    })

});/**
 * Created by Administrator on 2017-10-20.
 */
//swal({
//    title: '请求失败，请重新尝试',
//    text: "",
//    type: "warning",
//    showCancelButton: false,
//    confirmButtonColor: "#30862B",
//    confirmButtonText: "确定",
//    closeOnConfirm: false
//});
//return

    //swal({
    //    title: data.msg,
    //    text: "",
    //    type: "warning",
    //    showCancelButton: false,
    //    confirmButtonColor: "#30862B",
    //    confirmButtonText: "确定",
    //    closeOnConfirm: false
    //});
    //return
//swal({
//    title: data.msg,
//    text: "",
//    type: "warning",
//    showCancelButton: false,
//    confirmButtonColor: "#30862B",
//    confirmButtonText: "确定",
//    closeOnConfirm: false
//},function(){
//    window.parent.location.href='./login.html'
//});
//return
swal({
    title: data.msg,
    text: "2秒后关闭",
    confirmButtonText: "确定",
    confirmButtonColor: "#30862B",
    timer: 2000
});

$(function () {
    var distData = [];
    var renderMapData = function (d) {
        var index = 0;
        var v = d.province + d.city + d.district;
        var title = "";
        var html = "作物种植面积比例<br/>" + v;
        var mapEl = $("map2");
        var map1 = mapEl.data("map");
        if (!map1) {
            map1 = new BMap.Map("map2");
            mapEl.data("map", map1)
        }
        map1.enableScrollWheelZoom();
        map1.enableKeyboard();
        map1.enableDragging();
        map1.enableDoubleClickZoom();
        var scaleControl = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});
        scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
        map1.addControl(scaleControl);
        var navControl = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT, type: 0});
        map1.addControl(navControl);
        var overviewControl = new BMap.OverviewMapControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, isOpen: false});
        map1.addControl(overviewControl);
        var allControl = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP]});
        map1.addControl(allControl);
        var bdary = new BMap.Boundary;
        bdary.get(v, function (rs) {
            var count = rs.boundaries.length;
            if (count === 0) {
                layer.msg("未能获取当前输入行政区域");
                return
            }
            for (var i = 0; i < count; i++) {
                var ply = new BMap.Polygon(rs.boundaries[i], {
                    strokeWeight: 2,
                    strokeColor: "#ff0000",
                    fillColor: "#fff",
                    strokeOpacity: 1
                });
                map1.addOverlay(ply);
                var pointArray = [];
                pointArray = pointArray.concat(ply.getPath())
            }
            map1.setViewport(pointArray);
            var lng = pointArray[0].lng;
            var lat = pointArray[0].lat;
            var str = "";
            var str1 = "<p style='color: #115FAD;text-align: center;font-size:.14rem'>" + html + "</p>";
            var idx = 0;
            $(d.d_content).each(function (i, el) {
                var item = this;
                if (item.d_value && item.d_content) {
                    str += "<p style='color: #115FAD;font-size: .14rem;'><span style='display: inline-block;width:20px'>" + ++idx + "</span> <span style='display: inline-block;width: 120px'>" + item.d_content + "</span><span>" + item.d_value + "</span></p>"
                }
            });
            var sContent = str1 + str;
            var point = new BMap.Point(lng, lat);
            map1.centerAndZoom(point, 13);
            var opts = {width: 200, title: title, enableMessage: false, message: ""};
            var infoWindow = new BMap.InfoWindow(sContent, opts);
            map1.openInfoWindow(infoWindow, point)
        })
    };
    API.service("/listDistribution", {d_type: "2"}, function (data) {
        distData = data.object;
        if (distData.length) {
            renderMapData(distData[0])
        }
    });
    $(".sblb>h3").click(function () {
        $(this).next().toggle()
    });
    $(".mx-top-select > h3").click(function () {
        var me = $(this);
        if (me.hasClass("on")) {
            me.removeClass("on");
            me.next().hide()
        } else {
            me.addClass("on");
            me.next().show();
            if (!me.data("init_select")) {
                me.data("init_select", true);
                var render = function (d) {
                    return '<dd style="cursor: pointer;"' + " onmouseover=\"this.style.backgroundColor='#ccc';\"" + " onmouseout=\"this.style.backgroundColor='#fff'\"" + ' value="' + d.value + '"' + " onclick=\"$(this).parents('dl')" + ".attr('value', $(this).attr('value'))" + ".trigger('change')" + ".prev('h3').html($(this).text()+'<em><i></i></em>').click()" + '">' + d.name + "</dd>"
                };
                UI.renderProvince(".topProvinceSel", function () {
                    if ($(this).attr("value")) {
                        $(".topCitySel").prev("h3").removeClass("on").addClass("on").next().show();
                        UI.renderCity(".topCitySel", $(this).attr("value"), function () {
                            if ($(this).attr("value")) {
                                $(".topDistrictSel").prev("h3").removeClass("on").addClass("on").next().show();
                                UI.renderDistrict(".topDistrictSel", $(this).attr("value"), function () {
                                    var val = $(this).attr("value");
                                    if (val) {
                                        val = parseInt(val);
                                        for (var i = 0; i < distData.length; i++) {
                                            if (parseInt(distData[i]["d_district"]) === val) {
                                                renderMapData(distData[i]);
                                                break
                                            }
                                        }
                                    }
                                }, render)
                            }
                        }, render)
                    }
                }, render);
                $(".topProvinceSel, .topCitySel, .topDistrictSel").css("max-height", "300px").css("overflow", "auto")
            }
        }
    });
    $("#sjdl").click(function () {
        layer.open({
            type: 1,
            area: ["1000px", "670px"],
            title: "物种分布信息管理",
            content: $(".xxx-layer"),
            skin: "mlayer",
            success: function (page) {

                var dtype = 1;
                UI.renderProvince("#gis2_provice", function () {
                    UI.renderCity("#gis2_city", $(this).val(), function () {
                        UI.renderDistrict("#gis2_district", $(this).val())
                    })
                });
                var uploadWrapEL = page.find("a.upload-xls");

                var uploadFileEl = $('<input id="publish-file" name="d_content" style="float:left;left:0;top:0;opacity:0;cursor:pointer" type="file"/>');
                uploadFileEl.css({
                    width: uploadWrapEL.width() + "px",
                    height: uploadWrapEL.height() + "px",
                    "margin-top": "-" + uploadWrapEL.height() + "px"
                });
                uploadWrapEL.append(uploadFileEl);
                uploadWrapEL.on("click", function (e) {
                    if (uploadFileEl.val()) {
                        uploadFileEl.val("").change()
                    }
                });

                page.find(".btn-upload").click(function () {

                    if (!$('#publish-file').val()) {
                        layer.msg('请选择文件')
                        return;
                    }
                    var obj = {};
                    obj.ckuid = sessionStorage.getItem("ckuid");
                    obj.cksid = sessionStorage.getItem("cksid");
                    obj.d_type = dtype;
                    obj.d_state = 1;
                    obj.d_province = page.find("#gis2_provice").val();
                    obj.d_city = page.find("#gis2_city").val();
                    obj.d_district = page.find("#gis2_district").val();

                    startLoading();
                    uploadImg(obj,
                        '/addDistribution2',
                        'publish-file', '',
                        function (data) {
                            if (data.state === 2) {
                                layer.confirm(data.msg, function (idx) {
                                    top.location.href = "./login.html"
                                })
                            } else {
                                layer.msg(data.msg)
                            }
                        }, function (data) {
                            uploadFileEl.val("").change();
                            layer.msg(data.msg)
                        });
                    stopLoading();

                });
                uploadFileEl.change(function () {
                    page.find(".file-name").text($(this).val())
                });
                API.service("/listDistribute", {d_type: dtype}, function (rsp) {
                    console.log(rsp)


                    var tpl = page.find('#file-tpl').clone();
                    var toEl = page.find('.mx-table5 tbody').empty();
                    $(rsp.object).each(function (i, e) {
                        UI.appendFieldTo(tpl, e, toEl).data('data', e);
                    });

                }, function (m) {
                    layer.msg(m.msg);
                })
            }
        })
    });
    var mainIframe = $("#main_iframe");
    var mapEl = $("#map2");
    mapEl.height(Math.max(500, mainIframe.height() - 150));
    mainIframe.on("body-height", function () {
        var mf = $("#main_iframe");
        $("#map").height(Math.max(500, mf.height() - 65))
    })
});
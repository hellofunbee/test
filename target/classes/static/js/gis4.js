$(function () {
    var page = $("[page=gis4]");
    var selEl = page.find(".mx-top-search");
    var map = new BMap.Map("map4");
    var overlays = [], controls = [];
    var cleanMapAreas = function () {
        map.reset();
        $(overlays).each(function () {
            map.removeOverlay(this)
        });
        map.clearOverlays();
        $(controls).each(function () {
            map.removeControl(this)
        });
        controls = [];
        overlays = []
    };
    var drawMapArea = function (v, messages, keep) {
        map.reset();
        if (!keep) {
            cleanMapAreas()
        }
        var index = 0;
        var title = "";
        var html = "";
        html += "<ul>";
        $(messages).each(function () {
            html += "<li>" + this["m_content"] + "</li>"
        });
        html += "</ul>";
        var map1 = map;
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
        controls.push(allControl);
        controls.push(scaleControl);
        controls.push(navControl);
        controls.push(overviewControl);
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
                overlays.push(ply);
                var pointArray = [];
                pointArray = pointArray.concat(ply.getPath())
            }
            map1.setViewport(pointArray);
            var lng = pointArray[0].lng;
            var lat = pointArray[0].lat;
            var str = "";
            var str1 = "<p style='color: #115FAD;text-align: center;font-size:.14rem'>" + html + "</p>";
            var idx = 0;
            var sContent = str1 + str;
            var point = new BMap.Point(lng, lat);
            map1.centerAndZoom(point, 10);
            var opts = {width: 200, title: title, enableMessage: true, message: ""};
            var infoWindow = new BMap.InfoWindow(sContent, opts);
            map1.openInfoWindow(infoWindow, point)
        })
    };
    var changeAreaSelect = function (rsp) {
        var pRender = function (d, srcData) {
            return '<dd style="cursor: pointer;"' + " onmouseover=\"this.style.backgroundColor='#ccc';\"" + " onmouseout=\"this.style.backgroundColor='#fff'\"" + ' value="' + srcData.m_province + '"' + " onclick=\"$(this).parents('dl')" + ".attr('value', $(this).attr('value'))" + ".trigger('change')" + ".prev('h3').html($(this).text()+'<em><i></i></em>').click()" + '">' + srcData.province + "</dd>"
        };
        var cRender = function (d, srcData) {
            return '<dd style="cursor: pointer;"' + " onmouseover=\"this.style.backgroundColor='#ccc';\"" + " onmouseout=\"this.style.backgroundColor='#fff'\"" + ' value="' + srcData.m_city + '"' + " onclick=\"$(this).parents('dl')" + ".attr('value', $(this).attr('value'))" + ".trigger('change')" + ".prev('h3').html($(this).text()+'<em><i></i></em>').click()" + '">' + srcData.city + "</dd>"
        };
        var dRender = function (d, srcData) {
            return '<dd style="cursor: pointer;"' + " onmouseover=\"this.style.backgroundColor='#ccc';\"" + " onmouseout=\"this.style.backgroundColor='#fff'\"" + ' value="' + srcData.m_district + '"' + " onclick=\"$(this).parents('dl')" + ".attr('value', $(this).attr('value'))" + ".trigger('change')" + ".prev('h3').html($(this).text()+'<em><i></i></em>').click()" + '">' + srcData.district + "</dd>"
        };
        UI.renderSelectByData(".topProvinceSel", function () {
            if ($(this).attr("value")) {
                var province;
                for (var i = 0; i < rsp.object.length; i++) {
                    if (rsp.object[i]["m_province"] === $(this).attr("value")) {
                        province = rsp.object[i];
                        break
                    }
                }
                if (province) {
                    UI.renderSelectByData(".topCitySel", function () {
                        if ($(this).attr("value")) {
                            var city;
                            for (var i = 0; i < province.citys.length; i++) {
                                if (province.citys[i]["m_city"] === $(this).attr("value")) {
                                    city = province.citys[i];
                                    break
                                }
                            }
                            if (city) {
                                UI.renderSelectByData(".topDistrictSel", function () {
                                    var district;
                                    for (var i = 0; i < city.districts.length; i++) {
                                        if (city.districts[i]["m_district"] === $(this).attr("value")) {
                                            district = city.districts[i];
                                            break
                                        }
                                    }
                                    if (district && district.warnings) {
                                        drawMapArea(province.province + city.city + district.district, district.warnings)
                                    }
                                }, dRender, city.districts)
                            }
                        }
                    }, cRender, province.citys)
                }
            }
        }, pRender, rsp.object);
        $(".topProvinceSel").find("dd:eq(0)").click()
    };
    var showMap = function () {
        var waringType = page.find(".selWaringType");
        var type = waringType.attr("value");
        if (!type) {
            alert("select type");
            waringType.toggle();
            return
        }
        var pSel = $(".topProvinceSel");
        if (!pSel.attr("value")) {
            $(".more-cnt-other").show();
            pSel.prev().find("i").click();
            layer.msg("请先选择所在省份");
            return
        }
        var cSel = $(".topCitySel");
        var dSel = $(".topDistrictSel");
        API.service("/listMessage", {
            m_class: type,
            m_type: 3,
            m_content: selEl.find("input").val(),
            m_province: pSel.attr("value"),
            m_city: cSel.attr("value"),
            m_district: dSel.attr("value")
        }, function (d) {
            if (d.object.citys) {
                changeAreaSelect(d)
            } else {
                $.each(d.object, function (i) {
                    var item = this;
                    drawMapArea(item.province + item.city + item.district, [item], i > 0)
                })
            }
        }, function (d) {
            layer.msg(d.msg || "暂无数据");
            cleanMapAreas()
        })
    };
    $(".selWaringType").on("change", function () {
        showMap()
    });
    API.listClassType("3", function (d) {
        $(".selWaringType").selectX(d)
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
                                    showMap()
                                }, render)
                            }
                        }, render)
                    }
                }, render);
                $(".topProvinceSel, .topCitySel, .topDistrictSel").css("max-height", "300px").css("overflow", "auto")
            }
        }
    });
    selEl.find("a").click(function () {
        showMap()
    });
    var mainIframe = $("#main_iframe");
    var mapEl = $("#map4");
    mapEl.height(Math.max(500, mainIframe.height() - 150));
    mainIframe.on("body-height", function () {
        var mf = $("#main_iframe");
        $("#map").height(Math.max(500, mf.height() - 65))
    })
});
$(function () {
    var index = 0;
    var isFocused = false;
    var d_procedure = 0;
    var all = [];//所有区域的中心点数组用于聚焦所有的点
    var polygon_item = [];//区域，以及数据item

    var marks = [];

    var mapEl = $("map3");
    var map1 = mapEl.data("map");
    if (!map1) {
        map1 = new BMap.Map("map3");
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

    map1.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
    map1.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放


    map1.addEventListener("zoomend", function (evt) {
        var point = map1.getCenter();
        var zoom = map1.getZoom();
        var offsetPoint = new BMap.Pixel(evt.offsetX, evt.offsetY);   //记录鼠标当前点坐标


        if (zoom <= 7) {

            if (polygon_item.length > 0) {
                $(polygon_item).each(function (i, e) {
                    var point = e.polygon.getBounds().getCenter()
                    var marker = new BMap.Marker(point);
                    marks.push(marker);
                    map1.addOverlay(marker);
                    marker.addEventListener("click", function () {
                        showInfo(e.polygon, e.item, false);

                    });

                });

            }

        } else {
            $(marks).each(function (i, e) {
                map1.removeOverlay(e)

            });
            marks = [];

        }

    });


    var showInfo = function (polygon, d, zoom) {
        var point = polygon.getBounds().getCenter();
        var v = d.province + d.city + d.district;
        var title = "";
        var html = "生产分布比例<br/>" + v;


        var lng = point.lng;
        var lat = point.lat;

        // map1.addOverlay(new BMap.Marker(point));
        if (zoom != false) {
            var pointArray = [];
            pointArray = pointArray.concat(polygon.getPath())
            map1.setViewport(pointArray);
        }


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
        // map1.centerAndZoom(point, 13);
        var opts = {width: 200, title: title, enableMessage: false, message: ""};
        var infoWindow = new BMap.InfoWindow(sContent, opts);
        map1.openInfoWindow(infoWindow, point)


    }


    var distData = [];
    var renderMapData = function (d, t) {
        var v = d.province + d.city + d.district;
        var bdary = new BMap.Boundary;
        bdary.get(v, function (rs) {
            var count = rs.boundaries.length;
            if (count === 0) {
                console.log(v)
                // layer.msg("未能获取当前输入行政区域");
                return
            }
            // var pointArray = [];
            for (var i = 0; i < count; i++) {
                var ply = new BMap.Polygon(rs.boundaries[i], {
                    strokeWeight: 2,
                    strokeColor: "#ff0000",
                    fillColor: "#fff",
                    strokeOpacity: 1
                });
                map1.addOverlay(ply);

                all.push(ply.getBounds().getCenter());
                polygon_item.push({item: d, polygon: ply});

                //TODO 后台获取默认点
                if (isFocused == false && ply) {
                    showInfo(ply, d)
                    isFocused = true;
                }

                ply.addEventListener("click", function () {
                    // console.log(this.Ou.Ol)
                    showInfo(this, d);


                });

                // pointArray = pointArray.concat(ply.getPath())
            }


            // map1.setViewport(pointArray);

        })
    };
    $('#showall').on('click', function () {
        if (all.length == 0) {
            layer.msg('未发现任何区域');
        } else {
            map1.setViewport(all);
        }
    });


    //农事
    API.service("/listClass1", {c_type: 8, c_lev: 1}, function (data) {
        $('.d_procedure').empty();
        $(data.object).each(function (i, e) {

            var el = $('<dd value="' + e.c_id + '">' + e.c_name + '</dd>');
            UI.appendFieldTo(el, {}, $('.d_procedure')).data('data', e)
                .on('click', function () {

                    var item = $(this).data('data');
                    d_procedure = item.d_procedure;
                    $('.d_procedure').hide();
                    $('.d_procedure').prev().removeClass("on");
                    $('.d_procedure').prev().attr('value', item.c_id).html(item.c_name + '<em><i></i></em>').change();
                    console.log($(this).data('data'))
                    reload();
                });
        });


    });


    var reload = function () {
        API.service("/listDistribution",
            {d_type: "1", d_procedure: d_procedure},
            function (data) {
                distData = data.object;
                if (distData.length) {
                    all = [];
                    polygon_item = [];
                    map1.clearOverlays();
                    isFocused = false;
                    $(data.object).each(function (i, e) {

                        renderMapData(e, i)
                    });

                    // renderMapData(distData[0])
                }
            });
    }
    reload();
    $(".sblb>h3").click(function () {
        $(this).next().toggle()
    });

    //省市县
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
                    return '<dd style="cursor: pointer;"' +
                        " onmouseover=\"this.style.backgroundColor='#ccc';\"" +
                        " onmouseout=\"this.style.backgroundColor='#fff'\"" +
                        ' value="' + d.value + '"' +
                        " onclick=\"$(this).parents('dl')" +
                        ".attr('value', $(this).attr('value'))" +
                        ".trigger('change')" +
                        ".prev('h3').html($(this).text()+" +
                        "'<em><i></i></em>').click()" +
                        '">' + d.name + "</dd>"
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
            title: "生产分布图信息管理",
            content: $(".xxx-layer"),
            skin: "mlayer",
            success: function (page) {
                var d_procedure = 0;
                var d_province = page.find('#gis2_provice').val();
                var d_city = page.find('#gis2_city').val();
                var d_district = page.find('#gis2_district').val();

                var dtype = 2;
                UI.renderProvince("#gis2_provice", function () {
                    UI.renderCity("#gis2_city", $(this).val(), function () {
                        UI.renderDistrict("#gis2_district", $(this).val(), function () {
                            // reload();
                            setParam();

                        });
                    })
                });


                //农事
                API.service("/listClass1", {c_type: 8, c_lev: 1}, function (data) {
                    page.find('#gis3_type').empty();
                    $(data.object).each(function (i, e) {
                        var el = $('<option value="' + e.c_id + '">' + e.c_name + '</option>');
                        UI.appendFieldTo(el, {}, page.find('#gis3_type')).data('data', e);
                    });
                    page.find('#gis3_type').on('change', function () {
                        var item = $(this).data('data');
                        d_procedure = item.d_procedure;
                        reload();
                    });


                });

                function setParam() {
                    d_province = page.find('#gis2_provice').val();
                    d_city = page.find('#gis2_city').val();
                    d_district = page.find('#gis2_district').val();
                }

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
                uploadFileEl.change(function () {
                    page.find(".file-name").text($(this).val())
                });

                page.find('.download-model').click(function () {
                    var url = '/downLoadFile';
                    var cksid = sessionStorage.getItem("cksid");
                    var ckuid = sessionStorage.getItem("ckuid");
                    var data = {
                        ckuid: ckuid,
                        cksid: cksid,
                        file_name: 'model-1.xls'
                    };
                    API.formDownlad({url: url, method: 'GET', data: data});

                });
                /*page.find('.btn-search').click(function () {
                 setParam();
                 reload();
                 });
                 page.find('.btn-search-all').click(function () {
                 d_province = null;
                 d_city = null;
                 d_district = null;

                 reload();
                 });*/
                page.find('.do-upload').on('click', function () {

                    if (!$('#publish-file').val()) {
                        layer.msg('请选择文件')
                        return;
                    }
                    layer.alert('确定上传？',
                        function () {


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
                        }
                    )
                    ;

                });

                /*listDistribute*/

                // reload();
                function reload() {
                    API.service("/listDistribution", {
                        d_type: dtype,
                        d_province: d_province,
                        d_city: d_city,
                        d_district: d_district,
                        d_procedure:d_procedure
                    }, function (rsp) {
                        console.log(rsp)
                        layer.msg(rsp.msg)

                        var tpl =
                            '<tr class="file-tpl">' +
                            '<td field="d_originalname"></td>' +
                            '<td field="is_special" render="dict" dict="yes_no"></td>' +
                            '<td><a href="#" class="btn-sm btn-fh delete">删除</a></td>' +
                            '</tr>';

                        /*var tpl = page.find('.file-tpl').clone();*/
                        var toEl = page.find('.mx-table5 tbody');
                        toEl.empty();
                        $(rsp.object).each(function (i, e) {

                            var el = UI.appendFieldTo(tpl, e, toEl).data('data', e);
                            if (e.is_special == 1)
                                el.find('[field="is_special"]').text('是');
                            else
                                el.find('[field="is_special"]').text('否');


                            el.find('.delete').on('click', function () {

                                var data = $(this).closest('tr').data('data');
                                console.log($(this).closest('tr').data('data'));

                                var info = "确认删除"
                                if (data.d_originalname)
                                    info += data.d_originalname;

                                UI.renderProvince("#gis2_provice", function () {
                                    UI.renderCity("#gis2_city", $(this).val(), function () {
                                        $(function () {
                                            var index = 0;
                                            var all = [];//所有区域的中心点数组用于聚焦所有的点
                                            var polygon_item = [];//区域，以及数据item

                                            var marks = [];

                                            var mapEl = $("map3");
                                            var map1 = mapEl.data("map");
                                            if (!map1) {
                                                map1 = new BMap.Map("map3");
                                                mapEl.data("map", map1)
                                            }
                                            map1.enableScrollWheelZoom();
                                            map1.enableKeyboard();
                                            map1.enableDragging();
                                            map1.enableDoubleClickZoom();
                                            var scaleControl = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});
                                            scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
                                            map1.addControl(scaleControl);
                                            var navControl = new BMap.NavigationControl({
                                                anchor: BMAP_ANCHOR_TOP_LEFT,
                                                type: 0
                                            });
                                            map1.addControl(navControl);
                                            var overviewControl = new BMap.OverviewMapControl({
                                                anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
                                                isOpen: false
                                            });
                                            map1.addControl(overviewControl);
                                            var allControl = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP]});
                                            map1.addControl(allControl);

                                            map1.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
                                            map1.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放


                                            map1.addEventListener("zoomend", function (evt) {
                                                var point = map1.getCenter();
                                                var zoom = map1.getZoom();
                                                var offsetPoint = new BMap.Pixel(evt.offsetX, evt.offsetY);   //记录鼠标当前点坐标


                                                if (zoom <= 7) {

                                                    if (polygon_item.length > 0) {
                                                        $(polygon_item).each(function (i, e) {
                                                            var point = e.polygon.getBounds().getCenter()
                                                            var marker = new BMap.Marker(point);
                                                            marks.push(marker);
                                                            map1.addOverlay(marker);
                                                            marker.addEventListener("click", function () {
                                                                showInfo(e.polygon, e.item, false);

                                                            });

                                                        });

                                                    }

                                                } else {
                                                    $(marks).each(function (i, e) {
                                                        map1.removeOverlay(e)

                                                    });
                                                    marks = [];

                                                }

                                            });


                                            var showInfo = function (polygon, d, zoom) {
                                                var point = polygon.getBounds().getCenter();
                                                var v = d.province + d.city + d.district;
                                                var title = "";
                                                var html = "生产分布比例<br/>" + v;


                                                var lng = point.lng;
                                                var lat = point.lat;

                                                // map1.addOverlay(new BMap.Marker(point));
                                                if (zoom != false) {
                                                    var pointArray = [];
                                                    pointArray = pointArray.concat(polygon.getPath())
                                                    map1.setViewport(pointArray);
                                                }


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
                                                // map1.centerAndZoom(point, 13);
                                                var opts = {
                                                    width: 200,
                                                    title: title,
                                                    enableMessage: false,
                                                    message: ""
                                                };
                                                var infoWindow = new BMap.InfoWindow(sContent, opts);
                                                map1.openInfoWindow(infoWindow, point)


                                            }


                                            var distData = [];
                                            var renderMapData = function (d, t) {
                                                var v = d.province + d.city + d.district;
                                                var bdary = new BMap.Boundary;
                                                bdary.get(v, function (rs) {
                                                    var count = rs.boundaries.length;
                                                    if (count === 0) {
                                                        console.log(v)
                                                        // layer.msg("未能获取当前输入行政区域");
                                                        return
                                                    }
                                                    // var pointArray = [];
                                                    for (var i = 0; i < count; i++) {
                                                        var ply = new BMap.Polygon(rs.boundaries[i], {
                                                            strokeWeight: 2,
                                                            strokeColor: "#ff0000",
                                                            fillColor: "#fff",
                                                            strokeOpacity: 1
                                                        });
                                                        map1.addOverlay(ply);

                                                        all.push(ply.getBounds().getCenter());
                                                        polygon_item.push({item: d, polygon: ply});

                                                        ply.addEventListener("click", function () {
                                                            // console.log(this.Ou.Ol)
                                                            showInfo(this, d);


                                                        });

                                                        // pointArray = pointArray.concat(ply.getPath())
                                                    }
                                                    //TODO 后台获取默认点
                                                    if (polygon_item.length > 0 && t == 2) {

                                                        showInfo(polygon_item[0].polygon, polygon_item[0].item)
                                                    }

                                                    // map1.setViewport(pointArray);

                                                })
                                            };
                                            $('#showall').on('click', function () {
                                                if (all.length == 0) {
                                                    layer.msg('未发现任何区域');
                                                } else {
                                                    map1.setViewport(all);
                                                }
                                            });
                                            API.service("/listDistribution", {d_type: "1"}, function (data) {
                                                distData = data.object;
                                                if (distData.length) {
                                                    all = [];
                                                    polygon_item = [];

                                                    $(data.object).each(function (i, e) {
                                                        renderMapData(e, i)
                                                    });

                                                    // renderMapData(distData[0])
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
                                                    title: "生产分布图信息管理",
                                                    content: $(".xxx-layer"),
                                                    skin: "mlayer",
                                                    success: function (page) {
                                                        var d_province = page.find('#gis2_provice').val();
                                                        var d_city = page.find('#gis2_city').val();
                                                        var d_district = page.find('#gis2_district').val();

                                                        var dtype = 2;
                                                        UI.renderProvince("#gis2_provice", function () {
                                                            UI.renderCity("#gis2_city", $(this).val(), function () {
                                                                UI.renderDistrict("#gis2_district", $(this).val(), function () {
                                                                    // reload();
                                                                    setParam();

                                                                });
                                                            })
                                                        });


                                                        //农事
                                                        API.service("/listClass1", {
                                                            c_type: 8,
                                                            c_lev: 1
                                                        }, function (data) {
                                                            return UI.renderSelectByData_artical(page.find('.d_procedure'), function () {
                                                            }, null, data.object);
                                                        })


                                                        function setParam() {
                                                            d_province = page.find('#gis2_provice').val();
                                                            d_city = page.find('#gis2_city').val();
                                                            d_district = page.find('#gis2_district').val();
                                                        }

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
                                                        uploadFileEl.change(function () {
                                                            page.find(".file-name").text($(this).val())
                                                        });

                                                        page.find('.download-model').click(function () {
                                                            var url = '/downLoadFile';
                                                            var cksid = sessionStorage.getItem("cksid");
                                                            var ckuid = sessionStorage.getItem("ckuid");
                                                            var data = {
                                                                ckuid: ckuid,
                                                                cksid: cksid,
                                                                file_name: 'model-1.xls'
                                                            };
                                                            API.formDownlad({url: url, method: 'GET', data: data});

                                                        });
                                                        /*page.find('.btn-search').click(function () {
                                                         setParam();
                                                         reload();
                                                         });
                                                         page.find('.btn-search-all').click(function () {
                                                         d_province = null;
                                                         d_city = null;
                                                         d_district = null;

                                                         reload();
                                                         });*/
                                                        page.find('.do-upload').on('click', function () {

                                                            if (!$('#publish-file').val()) {
                                                                layer.msg('请选择文件')
                                                                return;
                                                            }
                                                            layer.alert('确定上传？',
                                                                function () {


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
                                                                }
                                                            )
                                                            ;

                                                        });


                                                        function reload() {
                                                            API.service("/listDistribution", {
                                                                d_type: dtype,
                                                                d_province: d_province,
                                                                d_city: d_city,
                                                                d_district: d_district
                                                            }, function (rsp) {
                                                                console.log(rsp)
                                                                layer.msg(rsp.msg)

                                                                var tpl =
                                                                    '<tr class="file-tpl">' +
                                                                    '<td field="d_originalname"></td>' +
                                                                    '<td field="is_special" render="dict" dict="yes_no"></td>' +
                                                                    '<td><a href="#" class="btn-sm btn-fh delete">删除</a></td>' +
                                                                    '</tr>';

                                                                /*var tpl = page.find('.file-tpl').clone();*/
                                                                var toEl = page.find('.mx-table5 tbody');
                                                                toEl.empty();
                                                                $(rsp.object).each(function (i, e) {

                                                                    var el = UI.appendFieldTo(tpl, e, toEl).data('data', e);
                                                                    if (e.is_special == 1)
                                                                        el.find('[field="is_special"]').text('是');
                                                                    else
                                                                        el.find('[field="is_special"]').text('否');


                                                                    el.find('.delete').on('click', function () {

                                                                        var data = $(this).closest('tr').data('data');
                                                                        console.log($(this).closest('tr').data('data'));

                                                                        var info = "确认删除"
                                                                        if (data.d_originalname)
                                                                            info += data.d_originalname;

                                                                        UI.renderProvince("#gis2_provice", function () {
                                                                            UI.renderCity("#gis2_city", $(this).val(), function () {
                                                                                UI.renderDistrict("#gis2_district", $(this).val())
                                                                            })
                                                                        });


                                                                        layer.alert(info, function (l) {

                                                                            API.service("/deleteDistribution", {
                                                                                d_id: data.d_id,
                                                                            }, function (result) {
                                                                                layer.msg(result.msg)
                                                                                if (result.success) {
                                                                                    reload();
                                                                                }
                                                                            });

                                                                            layer.close(l);

                                                                        });


                                                                    });
                                                                });

                                                            }, function (m) {
                                                                layer.msg(m.msg);
                                                            })
                                                        };
                                                        reload();

                                                    }
                                                })
                                            });
                                            var mainIframe = $("#main_iframe");
                                            var mapEl = $("#map3");
                                            mapEl.height(Math.max(500, mainIframe.height() - 150));
                                            mainIframe.on("body-height", function () {
                                                var mf = $("#main_iframe");
                                                $("#map").height(Math.max(500, mf.height() - 65))
                                            })
                                        });
                                        UI.renderDistrict("#gis2_district", $(this).val())
                                    })
                                });


                                layer.alert(info, function (l) {

                                    API.service("/deleteDistribution", {
                                        d_id: data.d_id,
                                    }, function (result) {
                                        layer.msg(result.msg)
                                        if (result.success) {
                                            reload();
                                        }
                                    });

                                    layer.close(l);

                                });


                            });
                        });

                    }, function (m) {
                        layer.msg(m.msg);
                    })
                };
                reload();

            }
        })
    });
    var mainIframe = $("#main_iframe");
    var mapEl = $("#map3");
    mapEl.height(Math.max(500, mainIframe.height() - 150));
    mainIframe.on("body-height", function () {
        var mf = $("#main_iframe");
        $("#map").height(Math.max(500, mf.height() - 65))
    })

});
$(function () {

    var item;
    var lastSelectNode;
    var lct = $("div#main_iframe").attr("src") || location.href,
        e = purl(lct);
    var tp_id_from_url = e.param("tp_id");


    var page = $("[page=product]");
    var treeEl = page.find("#product_device_tree");
    var otherCtrlBox = page.find(".other-control-status");
    var ctrlBox = page.find(".default-control-status");
    var ctrl_auto = page.find(".fitbit-handle-result");

    otherCtrlBox.find(".fitbit-handle>span").on('click', function () {
        var data = item;
        var obj = $(this);
        var index = $(this).index();
        if (index == 1) {//预约控制
            if (!data.rules || data.rules.length == 0) {
                layer.msg('请先配置预约控制规则');
                return !1;
            }
        }
        if (index == 2) {//智能控制
            if (!data.mos || data.mos.length == 0) {
                layer.msg('请先配置智能控制规则');
                return !1;
            }
        }
        if (index == 0) {
            if (data.ctrl_type == 1) {
                ctrl_auto.find(".btn-offon").hide();
                ctrl_auto.find("table").show()
            } else {
                ctrl_auto.find(".btn-offon").show();
                ctrl_auto.find("table").hide()
            }
        }


        var c_data_type = index + 1;

        if (data.state_type != c_data_type) {

            var ctrl = {};
            ctrl.ctrl_id = data.ctrl_id;
            ctrl.state_type = c_data_type;
            ctrl.pointEntity = {deviceId: lastSelectNode.oriData.deviceId};
            ctrl.ctrl_deviceId = lastSelectNode.oriData.deviceId;

            layer.confirm('确认修改控制方式?', function (index) {
                layer.close(index);
                console.log(data)
                API.service('/updateControlSetting', ctrl, function (e) {
                    data.state_type = c_data_type;
                    layer.msg(e.msg)
                    obj.addClass("on").siblings().removeClass("on");
                    otherCtrlBox.find(".fitbit-handle-result>div").hide().eq(obj.index()).show()

                });
            });


        } else {
            obj.addClass("on").siblings().removeClass("on");
            otherCtrlBox.find(".fitbit-handle-result>div").hide().eq(obj.index()).show()

        }

    });

    ctrl_auto.find(".btn-off,.btn-on").click(function () {
        //卷帘
        if (item.ctrl_type == 1) {
            if ($(this).is(".btn-off")) {
                $(this).parents(".ctrl-box").find("table").hide()
                console.log('拉帘关闭')
            } else {
                $(this).parents(".ctrl-box").find("table").show()
                console.log('拉帘开启')
            }
            return false
        } else {
            if ($(this).is(".btn-off")) {
                console.log('关闭')
                exeOff_on(2);
            } else {
                // 开启
                console.log('开启')
                exeOff_on(1);
            }
            return false
        }
    });

    ctrl_auto.find(".ctrl").on('click', function () {
        ctrl_auto.find(".ctrl").removeClass('kz-btn-on ');
        $(this).addClass('kz-btn-on');

        if ($(this).hasClass('up')) {
            console.log('up')
            exe(0, 0, 1);
        }
        if ($(this).hasClass('down')) {
            console.log('down')
            exe(0, 0, 2);
        }


    });


    ctrl_auto.find(".ctrl-close").on('click', function () {
        ctrl_auto.find(".ctrl").removeClass('kz-btn-on ');
        exe(0, 0, 4);

    });
    ctrl_auto.find(".exe").on('click', function () {
        if (ctrl_auto.find('.kqd').val()) {
            ctrl_auto.find(".exe").addClass('kz-btn-on ');
            console.log('exe');
            exe(ctrl_auto.find('.kqd').val(), 0, 3);
        }
        else {
            layer.msg('请输入开启度');
            return false;
        }


    });

    //卷帘：direction 开启度0x0，控制运动方向，0x1：上升，0x2：下降，0x3:停止。
    //卷帘：distanceOrDuration 0 ~ 100：表示整个行程的0% ~ 100%。

    //开关 distanceOrduration  -1一直开，0立即关


    function exe(distanceOrduration, direction, auto_type) {
        item.distanceOrduration = distanceOrduration;
        item.direction = direction;
        item.auto_type = auto_type;//1：上升 2：下降 3：开启度 4；关闭


        API.service('/autoControlDev', item, function (e) {
            if (e.success) {
                layer.msg(e.msg);
            }

        });

    }

    function exeOff_on(auto_type) {

        item.auto_type = auto_type;//1：开启 2：关闭

        API.service('/autoControlDev_off_on', item, function (e) {
            if (e.success) {
                layer.msg(e.msg);
                item.s_state = auto_type;
                appendControlImage(item)
            }

        });

    }


    var appendControlImage = function (data) {
        item = data;

        var l = page.find(".chart-list");
        var tmp = $("<ul></ul>").hide().appendTo("body:eq(0)");
        l.find("li.device-info-li").remove();
        var lis = l.find("li").appendTo(tmp);
        l.find("ul.fitbit-box").remove();
        var newUl = $('<ul class="fitbit-box new-fitbit"></ul>').appendTo(l);
        if (data) {
            l.find("li.device-info-li").remove();
            /*状态图名称 1: "控光", 2: "控温", 3: "控水" */
            var imgSrc = {1: "images/znbg.png", 3: "images/znks.png", 2: "images/znwk.png"};
            var statusInfo = {
                0: {1: "开启", 2: "关闭"},
                1: {1: "开启", 2: "关闭"},
                2: {1: "开启", 2: "关闭"},
                3: {1: "开启", 2: "关闭"}
            };
            var deviceInfoLi = $(
                '<li class="device-info-li">' +
                "  <h3>设备信息</h3>" +
                '  <div class="info">' +
                '    <div class="sensorAnimation">\n' +
                '      <img src="images/znks.png" class="img">' +
                "    </div>\n" +
                '    <p class="close">智能控水：关闭</p>' +
                "  </div>" + "</li>"
            ).prependTo(newUl);

            console.log(data);
            var statusText = (statusInfo[data.state_type] || {})[data.s_state] || "未知";
            deviceInfoLi.find("h3").text(data.ctrl_picturetitle).end().find("img").attr("src", imgSrc[data.ctrl_picturetype]).end().find("p").text(data.ctrl_name + " : " + statusText).end();
            if (data["s_state"] === 1) {
                deviceInfoLi.find("p").removeClass("close").text()
            }
            ctrlBox.hide();


            //开启度
            if (data.open_lev && data.open_lev > 0) {
                $('#open_lev').attr('placeholder', '当前' + data.open_lev);
            } else {
                $('#open_lev').attr('placeholder', '');
            }

            console.log(data.state_type)
            if (data.state_type == 0 || data.state_type == 1) {
                otherCtrlBox.show().find(".fitbit-handle>span:eq(0)").click()
            } else if (data.state_type == 3) {
                if (data.mos && data.mos.length > 0)
                    otherCtrlBox.show().find(".fitbit-handle>span:eq(" + (data.state_type - 1) + ")").click()
                else
                    otherCtrlBox.show().find(".fitbit-handle>span:eq(0)").click()

            } else if (data.state_type == 2) {
                console.log('------------2', data.rules, (data.state_type - 1))
                if (data.rules && data.rules.length > 0)
                    otherCtrlBox.show().find(".fitbit-handle>span:eq(" + (data.state_type - 1) + ")").click()
                else
                    otherCtrlBox.show().find(".fitbit-handle>span:eq(0)").click()

            }


        } else {
            tmp.find("li.device-info-li").remove();
            otherCtrlBox.hide();
            ctrlBox.show()
        }
        while (tmp.find("li").size()) {
            if (newUl.find("li").size() === 2) {
                newUl = $('<ul class="fitbit-box new-fitbit"></ul>').appendTo(l)
            }
            tmp.find("li:first").appendTo(newUl)
        }
        var oneWidth = newUl.width() + 11;
        l.width(oneWidth * l.find("ul.fitbit-box").size() + "px");
        tmp.remove()
    };
    page.on("click", ".ctrl-item-nav", function () {
        var me = $(this);
        me.parents("ul").find("li").removeClass("on");
        me.addClass("on");
        appendControlImage(me.data("data"))
    });
    var initControls = function (node) {
        var ul = page.find(".little-menu").find(">li").each(function () {
            if (!$(this).is(".lock")) {
                $(this).remove()
            }
        }).end();
        API.service("/listControlSetting", {
            pointEntity: {
                deviceId: node.oriData.deviceId,
                tp_id: node.oriData.tp_id
            }
        }, function (rsp) {

            console.log('*************控制数据***********')
            console.log(rsp)
            console.log('**************控制数据*******************')
            $(rsp.object).each(function () {
                var li = $('<li class="ctrl-item-nav"></li>').appendTo(ul).data("data", this);
                $('<a href="javascript:;" ></a>').text(this.ctrl_name).appendTo(li)
            });
            ul.find("li:last").addClass("last")
        })
    };
    var initCharts = function () {
        page.find(".fitbit-result").slide({
            mainCell: ".fitbit-result-cnt",
            autoPage: true,
            effect: "left",
            vis: 2,
            pnLoop: false,
            nextCell: ".fitbit-prev",
            prevCell: ".fitbit-next"
        })
    };
    var tryShowCamera = function (node) {
        if (node && node.oriData.tp_type === 3) {
            if (node.children) {
                for (var i = 0; i < node.children.length; i++) {
                    if (tryShowCamera(node.children[i])) {
                        return true
                    }
                }
            }
        } else if (node && node.oriData.tp_type === 4) {
            UI.showCamera(node.oriData.deviceId, page.find(".fitbit-video"));
            return true
        }
        return false
    };
    var onNodeSelect = function (node) {
        if (node && lastSelectNode && lastSelectNode === node)return;
        if (!node)node = lastSelectNode;
        if (!node)return;

        //当点击父节点时，自动选择第一个设备
        if (node.oriData["tp_type"] < 3) {
            while (node && node.oriData["tp_type"] < 3) {
                var c = node.children;
                if (c && c.length > 0) {
                    node = c[0];
                    if (node && node.oriData["tp_type"] === 3) {
                        treeEl.data("z-tree").selectNode(node);
                        onNodeSelect(node);
                        return
                    }
                } else {
                    layer.msg('请选择摄像头');
                    return;
                }
            }
        }


        if (node && node.oriData && node.oriData["tp_type"] === 3) {
            lastSelectNode = node;
            initControls(node);
            tryShowCamera(node);


            API.listSensorChartInfo(node.oriData.deviceId, node.oriData.tp_id, function (rsp) {
                var data = rsp.object.data;
                if (data.length !== 1) {
                    return
                }
                data = data[0];
                var info = rsp.object.info;
                UI.renderField(page, info);
                var unit = rsp.object.unit;
                var chartData = [];
                $.each(unit, function (i) {
                    var item = this;
                    if (item.chartDisplay) {
                        chartData.push({
                            fieldName: item.fieldName,
                            data: data[item.fieldName],
                            name: item.name,
                            label: data[item.fieldName] + item.unit,
                            unit: item.unit
                        })
                    }
                });
                var el = page.find(".chart-list").empty();
                var colTpl = [];
                $('<ul class="fitbit-box"></ul>').appendTo(el).append($('<li><h3>设备信息</h3><div class="info">' + '  <div class="sensorAnimation">' + '    <img src="images/znks.png" class="img">' + "  </div>" + '  <p class="close">智能控水：关闭</p></div></li>'));
                var renderHtml = function (html, option) {
                    var ul;
                    if (colTpl.length > 0) {
                        ul = colTpl[colTpl.length - 1];
                        if (ul.find(">li").size() === 2) {
                            ul = false
                        }
                    }
                    if (!ul) {
                        ul = $('<ul class="fitbit-box"></ul>');
                        ul.appendTo(el);
                        colTpl.push(ul)
                    }
                    $("<li><h3>光照</h3></li>").find("h3").text(option.label).end().append(html.addClass("info")).appendTo(ul)
                };
                UI.renderSynChart(el, chartData, renderHtml);
                initCharts()
            })
        }
    };
    treeEl.on("z-tree-load", function () {

        if (tp_id_from_url > 0) {
            var treeObj = $.fn.zTree.getZTreeObj('product_device_tree');
            var nodes = treeObj.getNodes();//父节点
            nodes = treeObj.transformToArray(nodes);//获取树所有节点
            if (nodes && nodes.length > 0) {
                $(nodes).each(function (i, e) {
                    if (e.oriData.tp_id == tp_id_from_url) {
                        return onNodeSelect(e);
                    }

                });
            }

        } else {
            var nodes = $(this).data("z-tree").getNodes();
            nodes && nodes.length > 0 && onNodeSelect(nodes[0]);
        }
        // UI.findFirstDeviceOnTree($(this).data("z-tree"), 3, onNodeSelect)
    });
    UI.renderPointTree("#product_device_tree", onNodeSelect);

    page.find(".sblb >h3").click(function () {
        $(this).next().toggle()
    });
    page.find(".znkz-module>div:not(:first)").hide();
    page.find(".little-menu li").click(function () {
        if ($(this).is(".lock")) {
            appendControlImage()
        }
        $(this).addClass("on").siblings().removeClass("on");
        var id = $(this).children("a").attr("data-href");
        page.find(id).show().siblings().hide()
    })
});
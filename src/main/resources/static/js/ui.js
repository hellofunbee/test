$("#main_iframe").on("click", ".mx-main", function (e) {
    if ($(e.target).is("table,tr,td,.mx-table2,div,th")) {
        $(".more-cnt,.tree-cnt").hide()
    }
});
var UI = {
    renderPointTree: function (targetEl, onClick, setting, showTypes) {
        var el = $(targetEl).empty();
        setting = $.extend({}, {
            check: {chkboxType: {Y: "ps", N: "ps"}},
            data: {simpleData: {enable: true}},
            callback: {
                onClick: function (e, target, selNode) {
                    if (onClick) {
                        onClick(selNode)
                    }
                }
            }
        }, setting);
        API.listPoint({tp_pid: 0, u_type: "1"}, function (data) {
            var zNodes = [];
            var buildNode = function (data, parents, deep) {
                $.each(data, function (idx) {
                    var node = this;
                    var currenNode = {name: node["tp_name"], id: node["tp_id"], oriData: node};
                    if (node["tp_type"] === 1) {
                        currenNode.icon = "css/zTreeStyle/img/diy/11.png"
                    } else if (node["tp_type"] === 2) {
                        currenNode.icon = "css/zTreeStyle/img/diy/22.png"
                    } else if (node["tp_type"] === 3) {
                        if (node["state"] === 1) {
                            currenNode.icon = "css/zTreeStyle/img/diy/44.png"
                        } else {
                            currenNode.icon = "css/zTreeStyle/img/diy/33.png"
                        }
                    } else if (node["tp_type"] === 4) {
                        currenNode.icon = "css/zTreeStyle/img/diy/55.png"
                    }
                    if (!showTypes || showTypes[node["tp_type"]] === true) {
                        parents.push(currenNode);
                        if (node["rank"] && node["rank"].length) {
                            currenNode.children = [];
                            buildNode(node["rank"], currenNode.children, deep + 1);
                            if (currenNode.children.length === 0) {
                                delete currenNode.children
                            }
                            currenNode.open = node["state"] === 1
                        }
                    }
                })
            };
            buildNode(data.object, zNodes, 0);
            var treeObj = $.fn.zTree.init(el, setting, zNodes);
            el.data("z-tree", treeObj).trigger("z-tree-load");
            var ctEl = el.parents(".tree-cnt");
            if ($.fn.bgiframe) {
                ctEl.bgiframe({conditional: true, width: "291px", height: "250px"})
            }
        })
    }, getPointTreeChecked: function (tree, checked) {
        var nodes = tree.getCheckedNodes(checked);
        var deviceIds = [];
        if (nodes.length === 0) {
            nodes = tree.getSelectedNodes()
        }
        $(nodes).each(function (idx) {
            var node = this;
            if (node.oriData["tp_type"] === 4) {
                deviceIds.push(node.oriData.deviceId)
            }
        });
        return deviceIds
    }, findFirstDeviceOnTreeActive: function (tree, type, callback) {
        return this.findFirstDeviceOnTree(tree, type, callback, true)
    }, findFirstDeviceOnTree: function (tree, type, callback, active) {
        active = active !== false;
        var nodes = tree.getNodes();
        var tpId = null;
        if (window.purl) {
            var url = purl($("#main_iframe").attr("src") || location.href);
            tpId = url.param("tp_id");
            if (tpId !== null && tpId !== undefined) {
                tpId = parseInt(tpId);
                if (tpId < 1) {
                    tpId = null
                }
            } else {
                tpId = null
            }
        }
        var foundDeviceNode = function (nodes) {
            for (var i = 0; i < nodes.length; i++) {
                var node = nodes[i];
                if (node.oriData && node.oriData["tp_type"] === type) {
                    if (tpId || !active || node.oriData["state"] === 1) {
                        if (tpId !== null) {
                            if (node.oriData["tp_id"] === tpId) {
                                return node
                            }
                        } else return node
                    }
                }
                if (node.children && node.children.length) {
                    for (var j = 0; j < node.children.length; j++) {
                        var nodeC = node.children[j];
                        var foundNode;
                        if (foundNode = foundDeviceNode([nodeC])) {
                            return foundNode
                        }
                    }
                }
            }
        };
        var firstDeviceNode = foundDeviceNode(nodes);
        if (!foundDeviceNode) {
            active = false;
            tpId = null;
            firstDeviceNode = foundDeviceNode(nodes)
        }
        if (firstDeviceNode) {
            tree.selectNode(firstDeviceNode);
            callback(firstDeviceNode)
        } else {
            layer.msg("请选择设备");
            callback(false)
        }
    }, renderSelectByData: function (targetEl, onChange, render, datas) {
        var el = $(targetEl);
        var oldValue = el.val();
        el.empty();
        if (onChange) {
            if (!el.attr("_oldchange")) {
                el.attr("_oldchange", onChange);
                el.on("change", onChange)
            }
        }
        if (datas) {
            $.each(datas, function (idx) {
                var d = this;
                if (render) {
                    $(render({value: d["ar_id"], name: d["a_name"]}, d)).appendTo(el)
                } else {
                    $("<option></option>").attr("value", d["ar_id"]).text(d["a_name"]).appendTo(el)
                }
            })
        }
        if (el.trigger("init") !== false) {
            el.change()
        }
    }, renderProvince: function (targetEl, onChange, render) {
        API.listProvince(function (data) {
            return UI.renderSelectByData(targetEl, onChange, render, data.object)
        })
    }, renderCity: function (targetEl, provinceId, onChange, render) {
        if (provinceId !== null) {
            API.listCity(provinceId, function (data) {
                return UI.renderSelectByData(targetEl, onChange, render, data.object)
            })
        }
    }, renderDistrict: function (targetEl, cityId, onChange, render) {
        if (cityId !== null) {
            API.listDistrict(cityId, function (data) {
                return UI.renderSelectByData(targetEl, onChange, render, data.object)
            })
        }
        //首页资讯
    }, renderSelectByData_artical: function (targetEl, onChange, render, datas) {
        var el = $(targetEl);
        var oldValue = el.val();
        el.empty();
        if (onChange) {
            if (!el.attr("_oldchange")) {
                el.attr("_oldchange", onChange);
                el.on("change", onChange)
            }
        }
        if (datas) {
            $.each(datas, function (idx) {
                var d = this;
                if (render) {
                    $(render({value: d["c_id"], name: d["c_name"]}, d)).appendTo(el)
                } else {
                    $("<option></option>").attr("value", d["c_id"]).text(d["c_name"]).appendTo(el)
                }
            })
        }
        if (el.trigger("init") !== false) {
            el.change()
        }
    }, renderHArtical_class1: function (targetEl, onChange, render) {

        API.service("/listClass1", {c_type: 7, c_lev: 1}, function (data) {
            return UI.renderSelectByData_artical(targetEl, onChange, render, data.object);
        })

    }, renderHArtical_class2: function (targetEl, c_rid, onChange, render) {

        if (c_rid != null) {
            API.service("/listClass2Byrid", {c_type: 7, c_lev: 2, c_rid: c_rid}, function (data) {

                return UI.renderSelectByData_artical(targetEl, onChange, render, data.object);
            }, function (e) {
                if (e.status === 404) {
                    layer.msg("接口开发中 " + this.url)
                } else {
                    console.log(e);
                    layer.alert('二级分类' + (e.statusText || e.msg || e.state || e.status || e.message))
                }
                return UI.renderSelectByData_artical(targetEl, onChange, render, null);

            });
        }

    }, renderInputType: function (targetEl, onClick, setting, showTypes) {
        var el = $(targetEl).empty();
        setting = $.extend({}, {
            check: {chkboxType: {Y: "ps", N: "ps"}},
            data: {simpleData: {enable: true}},
            callback: {
                onClick: function (e, target, selNode) {
                    if (onClick) {
                        onClick(selNode)
                    }
                }
            }
        }, setting);
        API.listInputType({c_type: 2, c_state: 1, u_type: "1"}, function (data) {
            console.log(data)
            var zNodes = [];
            var buildNode = function (data, parents, deep) {
                $.each(data, function (idx) {
                    var node = this;
                    var currenNode = {name: node["c_name"], id: node["c_id"], oriData: node};
                    if (node["c_lev"] === 1) {
                        currenNode.icon = "css/zTreeStyle/img/diy/3.png"
                    } else if (node["c_lev"] === 2) {
                        currenNode.icon = "css/zTreeStyle/img/diy/3.png"
                    } else if (node["c_lev"] === 3) {
                        if (node["state"] === 1) {
                            currenNode.icon = "css/zTreeStyle/img/diy/44.png"
                        } else {
                            currenNode.icon = "css/zTreeStyle/img/diy/33.png"
                        }
                    } else if (node["c_lev"] === 4) {
                        currenNode.icon = "css/zTreeStyle/img/diy/55.png"
                    }
                    if (!showTypes || showTypes[node["c_lev"]] === true) {
                        parents.push(currenNode);
                        if (node["rank"] && node["rank"].length) {
                            currenNode.children = [];
                            buildNode(node["rank"], currenNode.children, deep + 1);
                            if (currenNode.children.length === 0) {
                                delete currenNode.children
                            }
                            currenNode.open = node["state"] === 1
                        }
                    }
                })
            };
            buildNode(data.object, zNodes, 0);
            var treeObj = $.fn.zTree.init(el, setting, zNodes);
            el.data("z-tree", treeObj).trigger("z-tree-load");
            var ctEl = el.parents(".tree-cnt");
            if ($.fn.bgiframe) {
                ctEl.bgiframe({conditional: true, width: "291px", height: "250px"})
            }
        })
    }, renderMenuType: function (targetEl, onClick, setting, showTypes) {
        var el = $(targetEl).empty();
        setting = $.extend({}, {
            check: {chkboxType: {Y: "ps", N: "ps"}},
            data: {simpleData: {enable: true}},
            callback: {
                onClick: function (e, target, selNode) {
                    if (onClick) {
                        onClick(selNode)
                    }
                }
            }
        }, setting);
        API.listMenus({}, function (data) {

            var zNodes = [];
            var buildNode = function (data, parents, deep) {
                $.each(data, function (idx) {
                    var node = this;
                    var currenNode = {name: node["name"], id: node["id"], oriData: node};

                    parents.push(currenNode);
                    if (node["rank"] && node["rank"].length) {
                        currenNode.children = [];
                        buildNode(node["rank"], currenNode.children, deep + 1);
                        if (currenNode.children.length === 0) {
                            delete currenNode.children
                        }
                        currenNode.open = node["state"] === 1
                    }

                })
            };
            buildNode(data.object, zNodes, 0);
            var treeObj = $.fn.zTree.init(el, setting, zNodes);
            el.data("z-tree", treeObj).trigger("z-tree-load");
            var ctEl = el.parents(".tree-cnt");
            if ($.fn.bgiframe) {
                ctEl.bgiframe({conditional: true, width: "291px", height: "250px"})
            }
        })
    }
};


$.fn.selectX = function (data) {
    var render = function (d) {
        return '<dd style="cursor: pointer;"' + " onmouseover=\"this.style.backgroundColor='#ccc';\"" + " onmouseout=\"this.style.backgroundColor='#fff'\"" + ' value="' + d.value + '"' + " onclick=\"$(this).parents('dl')" + ".attr('value', $(this).attr('value'))" + ".trigger('change')" + ".prev('h3').html($(this).text()+'<em><i></i></em>').click()" + '">' + d.name + "</dd>"
    };
    return $(this).each(function (e) {
        var me = $(this).empty();
        var notReq = me.attr("require") === "false" && !me.attr("required");
        if (notReq) {
            me.append(render({value: "", name: "== 全部 =="}))
        }
        $.each(data.object, function (idx) {
            var item = this;
            me.append(render({value: item["c_id"], name: item["c_name"]}))
        });
        if (me.attr("value")) {
            me.find('dd[value="' + me.attr("value") + '"]').click()
        } else {
            me.find("dd:eq(0)").click();
            me.prev("h3").click()
        }
    })
};
UI.renderPageBar = function (options) {
    options = $.extend({}, {
        paperEl: "",
        count: 0,
        totalPages: 0,
        pageNo: 1,
        pageItems: 8,
        onPageItemClick: false
    }, options);
    var pageEl = $(options.paperEl).empty();
    if (!options.totalPages) {
        options.totalPages = 1
    }
    {
        $('<a href="#" class="btn-o">首页</a>').appendTo(pageEl).click(function () {
            if (options.pageNo - 1 < 1) {
                return false
            }
            options.onPageItemClick && options.onPageItemClick(1);
            return false
        });
        $('<a href="#" class="btn-o">上一页</a>').appendTo(pageEl).click(function () {
            if (options.pageNo - 1 < 1) {
                return false
            }
            options.onPageItemClick && options.onPageItemClick(options.pageNo - 1);
            return false
        });
        var pageItems = options.pageItems;
        var itemClick = function () {
            options.onPageItemClick && options.onPageItemClick(parseInt($(this).text()));
            return false
        };
        var offsetPageNo = Math.max(1, options.pageNo - pageItems / 2);
        if (offsetPageNo > 2) {
            var el = $('<a href="#" >1</a>').text(idx).appendTo(pageEl).click(itemClick);
            if (1 === options.pageNo) {
                el.addClass("cur")
            }
            $("<span>…</span>").appendTo(pageEl);
            pageItems--
        }
        for (var i = 0; i < pageItems; i++) {
            var idx = offsetPageNo + i;
            if (i === pageItems - 1 && options.totalPages > idx + 2) {
                $("<span>…</span>").appendTo(pageEl);
                idx = options.totalPages;
                i = options.totalPages + 1
            }
            if (idx > options.totalPages)break;
            var el = $('<a href="#" ></a>').text(idx).appendTo(pageEl).click(itemClick);
            if (idx === options.pageNo) {
                el.addClass("cur")
            }
        }
        $('<a href="#" class="btn-o">下一页</a>').appendTo(pageEl).click(function () {
            if (options.pageNo + 1 > options.totalPages) {
                return false
            }
            options.onPageItemClick && options.onPageItemClick(options.pageNo + 1);
            return false
        });
        $('<a href="#" class="btn-o">末页</a>').appendTo(pageEl).click(function () {
            if (options.pageNo + 1 > options.totalPages) {
                return false
            }
            options.onPageItemClick && options.onPageItemClick(options.totalPages);
            return false
        })
    }
};
UI.renderSimpleTable = function (options) {
    options = $.extend({}, {
        tableEl: "",
        fields: [],
        paperEl: "",
        ctrlHtml: "",
        dataObjectArray: [],
        count: 0,
        totalPages: 0,
        pageNo: 1,
        pageItems: 8,
        onPageItemClick: false
    }, options);
    var tbody = $(options.tableEl).find("tbody").empty();
    $.each(options.dataObjectArray, function (idx) {
        var item = this;
        var tr = $("<tr></tr>");
        $(options.fields).each(function (idx) {
            var n = this;
            var value;
            if (n && n.render && n.field) {
                value = n.render(item[n.field], n, item, options.dataObjectArray)
            } else {
                value = item[n]
            }
            if (value === "" || value === null) {
                $("<td></td>").appendTo(tr).html("&#160;")
            } else {
                $("<td></td>").appendTo(tr).text(value)
            }
        });
        if (options.ctrlHtml) {
            $("<td></td>").html(options.ctrlHtml).appendTo(tr);
            tr.data("oriData", item)
        }
        tr.appendTo(tbody)
    });
    UI.renderPageBar(options)
};
UI.syncChart = [{
    name: "光照度",
    tpl: '<div class="sensorAnimation" title="{label}">' + '<img src="images/gz.png" alt="" >' + "</div>" + "<p>光照度： {label}</p>",
    renderTo: function (el, options) {
        $(el).html(this.tpl.replace(/{label}/g, options.label).replace(/{height}/g, options.data)).find("img").css("opacity", "0.3").animate({opacity: "1"}, 1e3)
    }
}, {
    name: "二氧化碳",
    tpl: '<div class="sensorAnimation"  title="{label}">\n' + '    <div class="eyht-box">\n' + '        <img src="images/eyht.png" class="first">\n' + '        <div class="middle" style="height:{height}px"></div>\n' + '        <img src="images/eyht-bg.png" class="last">\n' + "    </div>\n" + "</div>\n" + "<p>二氧化碳：{label}</p>",
    renderTo: function (el, options) {
        var height = 160 - 160 / 1200 * options.data;
        $(el).html(this.tpl.replace(/{label}/g, options.label).replace(/{height}/g, height)).find(".middle").css({height: "160px"}).animate({height: height + "px"}, 300)
    }
}, {
    name: "空气温度",
    tpl: '<div class="sensorAnimation" title="{label}">' + '<div class="wdj-box" >' + '<img src="images/wdj.png" class="first">' + '<div class="middle" style="height:{height}px"></div>' + '<img src="images/wdj-bg.png" class="last">' + "</div>" + "</div>" + "<p>空气温度：{label}</p>",
    renderTo: function (el, options) {
        var height = 160 - 160 / 70 * options.data;
        $(el).html(this.tpl.replace(/{label}/g, options.label).replace(/{height}/g, height)).find(".middle").css({height: "160px"}).animate({height: height + "px"}, 300)
    }
}, {
    name: "土壤温度",
    tpl: '<div class="sensorAnimation" title="{label}">' + '<div class="wdj-box">' + '<img src="images/wdj.png" class="first">' + '<div class="middle" style="height:{height}px"></div>' + '<img src="images/wdj-bg.png" class="last">' + "</div>" + "</div>" + "<p>土壤温度：{label}</p>",
    renderTo: function (el, options) {
        var height = 160 - 160 / 70 * options.data;
        $(el).html(this.tpl.replace(/{label}/g, options.label).replace(/{height}/g, height)).find(".middle").css({height: "160px"}).animate({height: height + "px"}, 300)
    }
}, {
    name: "空气湿度",
    tpl: '<div class="sensorAnimation" title="{label}">' + '<div class="sdj-box">\n' + '    <img src="images/sdj.png" class="first">\n' + '    <img src="images/sdj-zz.png" style="transform: rotate({height}deg);" class="last">\n' + "</div>" + " </div>" + " <p> 空气湿度：{label}</p>",
    renderTo: function (el, options) {
        var height = 160 / 100 * options.data;
        $(el).html(this.tpl.replace(/{label}/g, options.label).replace(/{height}/g, height)).find(".last").css({transform: "rotate(0)"}).animate({aa: height}, {
            step: function (now, fx) {
                $(this).css({transform: "rotate(" + now + "deg)"})
            }, duration: 300
        })
    }
}, {
    name: "土壤水分",
    tpl: '<div class="sensorAnimation" title="{label}">' + '<div class="sdj-box">\n' + '    <img src="images/sdj.png" class="first">\n' + '    <img src="images/sdj-zz.png" style="transform: rotate({height}deg);" class="last">\n' + "</div>" + " </div>" + " <p> 土壤水分：{label}</p>",
    renderTo: function (el, options) {
        var height = 160 / 100 * options.data;
        $(el).html(this.tpl.replace(/{label}/g, options.label).replace(/{height}/g, height)).find(".last").css({transform: "rotate(0)"}).animate({aa: height}, {
            step: function (now, fx) {
                $(this).css({transform: "rotate(" + now + "deg)"})
            }, duration: 300
        })
    }
}, {
    name: "土壤湿度",
    tpl: '<div class="sensorAnimation" title="{label}">' + '<div class="sdj-box">\n' + '    <img src="images/sdj.png" class="first">\n' + '    <img src="images/sdj-zz.png" style="transform: rotate({height}deg);" class="last">\n' + "</div>" + " </div>" + " <p> 土壤湿度：{label}</p>",
    renderTo: function (el, options) {
        var height = 160 / 100 * options.data;
        $(el).html(this.tpl.replace(/{label}/g, options.label).replace(/{height}/g, height)).find(".last").css({transform: "rotate(0)"}).animate({aa: height}, {
            step: function (now, fx) {
                $(this).css({transform: "rotate(" + now + "deg)"})
            }, duration: 300
        })
    }
}];
UI.renderSynChart = function (ulEL, chartData, callbackHtml) {
    var el = $(ulEL).empty();
    $.each(chartData, function () {
        var chartOption = this;
        console.log(chartOption);
        var chart = null;
        $(UI.syncChart).each(function (o) {
            var c = this;
            if (c.name === chartOption.name) {
                chart = c;
                return true
            }
        });
        if (!chart) {
            chart = {
                renderTo: function (el, chartOption) {
                    return $("<div>TODO:" + chartOption.label + "|" + chartOption.name + "</div>").appendTo(el)
                }
            }
        }
        if (callbackHtml) {
            el = $("<div></div>");
            chart.renderTo(el, chartOption);
            callbackHtml(el, chartOption)
        } else {
            chart.renderTo($("<li></li>").appendTo(el), chartOption)
        }
    })
};
UI.cleanHtml = function (html) {
    return html.replace(/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi, "").replace(/<style\b[^<]*(?:(?!<\/style>)<[^<]*)*<\/style>/gi, "").replace(/<link\b[^<]*(?:(?!<\/>)<[^<]*)*<\/>/gi, "").replace(/onmouse/gi, "_onmouse").replace(/onclick/gi, "_onclick")
};
var showMessageDetail = function (id, type, title, type2) {
    var url = "detail.html";
    if (type === 4) {
        url = "news.html"
    }

    if (type2 == 1)
        url = "detail-exp.html";
    if (type2 == 2)
        url = "device-gover.html";

    var options = {url: url + "?id=" + id + "&type=" + (type || 2)};


    window.openPageContent("信息发布", "首页资讯", options);
    return false
};
UI.preZero = function (oriStr, len, maxValue) {
    if (maxValue > 0) {
        if (oriStr > maxValue)return maxValue
    }
    if (("" + oriStr).length > len) {
        return oriStr
    } else {
        var zeroLen = len - ("" + oriStr).length;
        while (zeroLen-- > 0) {
            oriStr = "0" + oriStr
        }
    }
    return oriStr
};
UI.cutSize = function (str, n) {
    if (!str)return "";
    var r = /[^\x00-\xff]/g;
    if (str.replace(r, "mm").length <= n) {
        return str
    }
    var m = Math.floor(n / 2);
    for (var i = m; i < str.length; i++) {
        if (str.substr(0, i).replace(r, "mm").length >= n) {
            return str.substr(0, i) + "..."
        }
    }
    return str
};
UI.validRules = {
    ip: function (ip, el) {
        var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
        var objExp = new RegExp(exp);
        var flag = objExp.test(ip);

        /*  if (flag !== undefined && flag !== "") {
         return true
         }*/

        if (flag) {
            return true
        }
        /*if (ip.match(/:/g).length <= 7 && /::/.test(ipvalue) ? /^([\da-f]{1,4}(:|::)){1,6}[\da-f]{1,4}$/i.test(ipvalue) : /^([\da-f]{1,4}:){7}[\da-f]{1,4}$/i.test(ipvalue)) {
         return true
         }*/
        if (el) {
            el.focus();
            layer.tips("不正确的IP地址", el, {tips: [2, "#3595CC"], time: 1500})
        }
        return false
    }, port: function (port, el) {
        port = parseInt(port);
        if (port > 1 && port < 65536)return true;
        if (el) {
            el.focus();
            layer.tips("不正确的端口", el, {tips: [2, "#3595CC"], time: 1500})
        }
        return false
    }
};
UI.getFieldValue = function (targetEl) {
    var obj = {};
    var valid = true;
    $(targetEl).find("input[field],select[field],div[field][dict],textarea[field]").each(function () {
        var me = $(this);
        var val = me.val();
        if (me.is("div[dict]")) {
            val = me.attr("value")
        }
        obj[me.attr("field")] = val;
        if (me.attr("required") && !val) {
            me.focus();
            layer.tips("不能为空", me, {tips: [2, "#3595CC"], time: 1500});
            valid = false;
            return false
        } else if (me.attr("valid-rule") && val) {
            var validRule = UI.validRules[me.attr("valid-rule")];
            if (validRule && !validRule(val, me)) {
                valid = false;
                return false
            }
        } else {
            obj[me.attr("field")] = val
        }
    });
    if (!valid) {
        return false
    }
    return obj
};
UI.renderField = function (el, data) {
    data = data || {};
    el = $(el);
    var renderLaydate = function () {
        var type = "datetime";
        var me = $(this);
        if (me.is(".laydate-time")) {
            type = "time"
        } else if (me.is(".laydate")) {
            type = "date"
        }
        var newVar = {elem: this, type: type};
        if (me.attr("min")) {
            newVar.min = me.attr("min")
        }
        if (me.attr("max")) {
            newVar.min = me.attr("max")
        }
        laydate.render(newVar)
    };
    el.find(".laydate-time,.laydate,.laydate-datetime").each(renderLaydate).on("ui-change", renderLaydate);
    el.find("[field]").each(function () {
        var me = $(this);
        var f = me.attr("field");
        var unit = me.attr("unit");
        {
            var text = data[f];
            var rendered = false;
            if (!text) {
                text = ""
            }
            if (me.attr("date-format")) {
                text = new Date(text * 1e3).format(me.attr("date-format"))
            } else if (me.attr("max-length")) {
                text = UI.cutSize(text, me.attr("max-length"))
            }
            var tagName = this.tagName.toUpperCase();
            if (me.attr("render") === "dict") {
                if (me.attr("dict")) {
                    var isMxCheckbox = me.attr("render-type") === "mx-checkbox";
                    if (tagName === "SELECT" || isMxCheckbox) {
                        var dictVal = API.dict[me.attr("dict")];
                        me.empty();
                        if (dictVal)for (var n in dictVal) {
                            if (isMxCheckbox) {
                                var itemEl = $('<div class="mx-checkbox mr20" style="display:inline-block"><em></em><label></label></div>').appendTo(me).attr("value", n);
                                itemEl.find("label").text(dictVal[n]);
                                itemEl.find(".mx-checkbox:last").removeClass("mr20")
                            } else {
                                $("<option></option>").attr("value", n).html(dictVal[n]).appendTo(me)
                            }
                        }
                        if (isMxCheckbox) {
                            me.find(".mx-checkbox").click(function () {
                                $(this).addClass("on").siblings().removeClass("on");
                                $(this).parents('[render-type="mx-checkbox"]').attr("value", $(this).attr("value")).trigger("change")
                            });
                            me.find('.mx-checkbox[value="' + text + '"]').click();
                            rendered = true
                        }
                    } else if (tagName === "DIV" || tagName === "SPAN" || tagName === "TD" || tagName === "TH" || tagName === "DD" || tagName === "DT") {
                        var val = API.dict[me.attr("dict")][text];
                        val = text ? val + (unit || "") : "";
                        me.attr("value", text).text(val).change();
                        rendered = true
                    }
                }
            }
            if (tagName === "INPUT" || tagName === "SELECT" || tagName === "TEXTAREA") {
                me.val(text)
            } else if (tagName === "IMG") {
                me.attr("src", text)
            } else if (tagName === "A" || tagName === "SCRIPT") {
                me.attr("href", text)
            } else if (me.hasClass("mx-checkbox")) {
                me.removeClass("on");
                if (text) {
                    me.addClass("on")
                }
            } else {
                if (!rendered) {
                    me.text(text + (unit || ""))
                }
            }
        }
    }).end();
    return el
};
UI.appendFieldTo = function (tpl, data, toEl) {
    if (!data) {
        data = {}
    }
    if (tpl && typeof tpl === "string") {
        for (var n in data) {
            tpl = tpl.replace(new RegExp("{" + n + "}", "gi"), data[n])
        }
    }
    return UI.renderField($(tpl), data).appendTo(toEl)
};

UI.renderField2 = function (tpl, data) {
    if (!data) {
        data = {}
    }
    if (tpl && typeof tpl === "string") {
        for (var n in data) {
            tpl = tpl.replace(new RegExp("{" + n + "}", "gi"), data[n] ? data[n] : "")
        }
    }
    return tpl;
};
UI.showCamera = function (deviceId, elSelector) {
    var renderCameraTo = function (el, deviceIp, devicePort, deviceUser, devicePwd, protocolType) {
        if (location.href.indexOf("127.0.0.1") > 0) {
            return el.text("debug not show (product remove this in ui.js)")
        }
        var iframe = el.find("iframe");
        sessionStorage.setItem("szIP", deviceIp);
        sessionStorage.setItem("szPort", devicePort);
        sessionStorage.setItem("szUsername", deviceUser);
        sessionStorage.setItem("szPassword", devicePwd);
        if (!iframe.size()) {
            iframe = $('<iframe frameborder="no"></iframe>').appendTo(el.empty()).height(el.height()).width(el.width()).on("load", function () {
                $(this).contents().find("html,body").css({
                    margin: 0,
                    "padding:": 0,
                    overflow: "hidden",
                    height: el.height() + "px",
                    width: el.width() + "px"
                })
            })
        }
        sessionStorage.setItem("player_height", el.height());
        sessionStorage.setItem("player_width", el.width());
        el.css({overflow: "hidden"});
        iframe.attr("src", "./js/player/player.html?t=" + (new Date).getTime())
    };
    var showCamera = function (deviceId) {
        var el = $(elSelector).html("获取设备信息中 [" + deviceId + "]     ... ");
        API.getShopCamera(deviceId, function (d) {
            renderCameraTo(el, d.object.ip, d.object.port || d.object.s_proxy, d.object.username, d.object.password, d.object.type)
        }, function (d) {
            el.html(d.msg + '[<a class="retry">重试</a>&#160;|&#160;<a class="test">测试</a>]').find("a.retry").click(function () {
                showCamera(deviceId);
                return false
            }).end();
            if (location.href.indexOf("127.0.0.1") !== "-1") {
                el.find("a.test").click(function () {
                    renderCameraTo(el, "192.168.2.8", 80, "admin", "a8888888", 1);
                    return false
                })
            }
        })
    };
    return showCamera(deviceId)
};
UI.closeIframeDialog = function () {
    if (parent.layer) {
        var index = parent.layer.getFrameIndex(window.name);
        if (index !== null) {
            parent.layer.close(index);
            return true
        }
    }
    return false
};


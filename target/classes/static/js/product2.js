$(function () {
    var page = $("[page=product2]");
    var selEl = page.find(".mx-top-search");
    var treeEl = page.find("#product2_device_tree");
    var lastPageNo = 1;
    var lastSelectNode;
    var inClass1 = page.find(".class1");
    var inClass2 = page.find(".class2");
    var tbody = page.find(".mx-table2").find("tbody");
    var rowTpl = tbody.find("tr.tpl:eq(0)").clone().removeClass("tpl");
    var endTimeEl = page.find(".more-cnt input[field=p_endtime]");
    var beginTimeEl = page.find(".more-cnt input[field=p_begintime]");
    UI.renderField(page.find(".more-cnt"), {});
    API.service("/listClass1", {c_type: 1}, function (rsp) {
        inClass1.selectX(rsp)
    });
    inClass1.change(function () {
        var cRid = inClass1.attr("value");
        if (!cRid) {
            inClass2.selectX({object: []})
        } else {
            API.service("/listClass2Byrid", {c_rid: cRid}, function (rsp) {
                inClass2.selectX(rsp)
            })
        }
    });
    page.find(" a.btn-item-add").click(function () {
        page.find(".layer-edit-item").remove();
        var content = page.find(".add-layer").clone().appendTo(page).addClass("layer-edit-item");
        layer.open({
            type: 1,
            area: ["750px", "600px"],
            title: "新建投入品使用记录",
            content: content,
            success: function (layero, index) {
                var pEl = layero.find("[field=in_pid]").empty();
                pEl.on("change", function () {
                    var item = $(this).find("option:selected").data("data");
                    var inTime = layero.find("[field=in_time]");
                    inTime.attr("min", item.p_begintime);
                    inTime.attr("max", item.p_endtime);
                    inTime.trigger("ui-change")
                });


                layero.find("a.btn-cancel").click(function () {
                    layer.close(index)
                });
                layero.find("a.btn-save").click(function () {
                    var entities = [];
                    var err = false;
                    layero.find(".row-tpl").each(function () {
                        var o = UI.getFieldValue($(this));
                        var item = $(this).find("[field=in_pid] option:selected");
                        if (!o) {
                            err = true;
                            return true
                        }
                        o.p_begintime = item.attr("p_begintime");
                        o.p_endtime = item.attr("p_endtime");
                        entities.push(o)
                    });
                    if (err || !entities.length) {
                        return
                    }
                    console.log(entities)
                    API.service("/addInput", {inputEntity: entities}, function (rsp) {
                        layer.msg(rsp.msg);
                        layer.close(index);
                        queryIt()
                    });
                    return false
                });
                layero.on("click", ".btn-remove", function () {
                    var row = $(this).parents(".row-tpl");
                    row.fadeOut(row.remove);
                    return false
                });
                layero.on("click", ".btn-plus", function () {
                    var rowTpl = $(this).parents("div.row-tpl");
                    rowTpl.after(rowTpl.clone().find(".btn-remove").show().end());
                    return false
                });
                UI.renderField(layero, {});
                API.service("/listProduce", {
                    pointEntity: {tp_id: lastSelectNode.oriData.tp_id},
                    start: 0
                }, function (rsp) {
                    if (rsp.object.length === 0) {
                        layer.msg("没有生产计划");
                        layer.close(index);
                        return
                    }
                    $.each(rsp.object, function (i) {
                        var item = this;
                        $("<option></option>").text(item["c2_name"] + " - " + item["p_begintime"] + "~" + item["p_endtime"]).attr("value", item["p_id"]).appendTo(pEl).attr("p_begintime", item["p_begintime"]).attr("p_endtime", item["p_endtime"]).data("data", item)
                    });
                    pEl.change()
                }, function (rsp) {
                    layer.alert(rsp.msg);
                    layer.close(index)
                })
            },
            skin: "mlayer"
        });
        return false
    });
    tbody.on("click", "a.btn-item-delete", function () {
        var tr = $(this).parents("tr");
        var item = tr.data("data");
        layer.confirm("确定要删除吗？", function (idx) {
            layer.close(idx);
            API.service("/deleteInput", {in_id: item.in_id}, function (rsp) {
                layer.msg(rsp.msg);
                tr.fadeOut(function () {
                    tr.remove()
                })
            })
        });
        return false
    });
    tbody.on("click", "a.btn-item-edit", function () {
        var tr = $(this).parents("tr");
        var item = tr.data("data");
        page.find(".layer-edit-item").remove();
        var content = page.find(".edit-layer").clone().appendTo(page).addClass("layer-edit-item");
        layer.open({
            type: 1,
            area: ["720px", "300px"],
            title: "编辑投入品使用记录",
            content: content,
            success: function (layero, index) {
                var pEl = layero.find("[field=in_pid]").empty();
                layero.find("a.btn-cancel").click(function () {
                    layer.close(index)
                });
                layero.find("a.btn-save").click(function () {
                    var o = UI.getFieldValue(layero);
                    if (!o) {
                        return true
                    }
                    var param = {};
                    for (var n in item) {
                        if (n.indexOf("in_") === 0) {
                            param[n] = item[n]
                        }
                    }
                    param = $.extend({}, param, o);

                    var pro = pEl.find('option:selected').data('data');
                    param.in_class1 = pro.p_class1;
                    param.in_class2 = pro.p_class2;
                    param.in_pname = pro.p_name
                    console.log(pro)

                    API.service("/updateInput", param, function (rsp) {
                        layer.msg(rsp.msg);
                        layer.close(index);
                        queryIt()
                    });
                    return false
                });
                API.service("/listProduce", {
                    pointEntity: {tp_id: lastSelectNode.oriData.tp_id},
                    /* p_id: item.in_pid,*/
                    start: 0,
                    pagesize:10
                }, function (rsp) {
                    if (rsp.object.length === 0) {
                        layer.msg("没有生产计划");
                        layer.close(index);
                        return
                    }

                    $.each(rsp.object, function (i) {
                        var item = this;

                        $("<option></option>").text(item["c2_name"] + " - " + item["p_begintime"] + "~" + item["p_endtime"]).attr("value", item["p_id"]).appendTo(pEl).attr("p_begintime", item["p_begintime"]).attr("p_endtime", item["p_endtime"]).data("data", item)
                    });
                    pEl.change();

                    UI.renderField(layero, item)
                }, function (rsp) {
                    layer.alert(rsp.msg);
                    layer.close(index)
                })
            },
            cancel: function () {
            },
            skin: "mlayer"
        });
        return false
    });
    var queryIt = function () {
        var node = lastSelectNode;
        if (node && node.oriData && node.oriData["tp_type"] === 3) {

            console.log( $('.mx-top-search input').val())
            API.service("/listInput", {
                in_class1: inClass1.attr("value"),
                in_class2: inClass2.attr("value"),
                p_begintime: beginTimeEl.val(),
                p_endtime: endTimeEl.val(),
                pointEntity: {tp_id: node.oriData["tp_id"]},
                start: lastPageNo,
                pagesize:10,
                in_mattername: $('.mx-top-search input').val()
            }, function (data) {
                tbody.empty();
                $(data.object).each(function (i,e) {

                    var tpl = rowTpl.clone();
                    tpl.find('[field="in_total"]').attr('unit',API.dict.in_unit[e.in_unit]);

                    UI.appendFieldTo(tpl, this, tbody).data("data", this);


                });
                UI.renderPageBar({
                    paperEl: page.find(".mx-page"),
                    count: data.object.count,
                    pageNo: lastPageNo,
                    totalPages: data.totalpage,
                    onPageItemClick: function (pageNo) {
                        lastPageNo = pageNo;
                        queryIt();
                    }
                })
            }, function (rsp) {
                layer.msg(rsp.msg);
                tbody.empty();
                page.find(".mx-page").empty()
            })
        }
    };
    page.find(".more-cnt a.btn-lg").click(function () {
        queryIt();
        return false
    });
    selEl.find("a").click(function () {
        queryIt();
        return false
    });
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
                        retuproduct2rn
                    }
                } else {
                    layer.msg('请选择设备');
                    return;
                }
            }
        }

        lastSelectNode = node;
        if (node && node.oriData && node.oriData["tp_type"] === 3) {
            queryIt()
        }
    };
    UI.renderPointTree("#product2_device_tree", onNodeSelect);
    treeEl.on("z-tree-load", function () {

        var nodes = $(this).data("z-tree").getNodes();
        nodes && nodes.length > 0 && onNodeSelect(nodes[0]);

        // UI.findFirstDeviceOnTree($(this).data("z-tree"), 3, onNodeSelect)
    });
    page.find(".mx-top-select > h3").click(function () {
        if ($(this).hasClass("on")) {
            $(this).removeClass("on");
            $(this).next().hide()
        } else {
            $(this).addClass("on");
            $(this).next().show()
        }
    });
    page.find(".sblb >h3").click(function () {
        $(this).next().toggle()
    })
});
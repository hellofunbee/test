$(function () {
    var page = $("[page=product3]");
    var selEl = page.find(".mx-top-search");
    var treeEl = page.find("#product3_device_tree");
    var lastPageNo = 1;
    var lastSelectNode;
    var inClass1 = page.find(".class1");
    var inClass2 = page.find(".class2");
    var tbody = page.find(".mx-table2").find("tbody");
    var rowTpl = tbody.find("tr.tpl:eq(0)").clone().removeClass("tpl");
    var endTimeEl = page.find(".more-cnt input[field=p_endtime]");
    var beginTimeEl = page.find(".more-cnt input[field=p_begintime]");
    var queryIt;
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
            area: ["720px", "530px"],
            title: "新建投入品使用记录",
            content: content,
            success: function (layero, index) {
                var pEl = layero.find("[field=in_pid]").empty();
                var pClass1 = layero.find("[field=p_class1]").empty();
                var pClass2 = layero.find("[field=p_class2]").empty();
                var renderFun = function (v, item) {
                    return $("<option></option>").text(item.c_name).attr("value", item.c_id)
                };
                UI.renderField(layero, {});
                API.service("/listClass1", {c_type: 1}, function (rsp) {
                    UI.renderSelectByData(pClass1, function () {
                        var cRid = $(this).val();
                        pClass2.empty();
                        if (cRid) {
                            API.service("/listClass2Byrid", {c_rid: cRid}, function (rsp1) {
                                UI.renderSelectByData(pClass2, null, renderFun, rsp1.object)
                            }, function (rsp) {
                                pClass2.empty()
                            })
                        }
                    }, renderFun, rsp.object)
                }, function (rsp) {
                    pClass1.empty()
                });
                layero.find("a.btn-cancel").click(function () {
                    layer.close(index)
                });
                layero.find("a.btn-save").click(function () {
                    var o = UI.getFieldValue(layero);
                    if (!o) {
                        return true
                    }
                    o.pointEntity = {tp_id: lastSelectNode.oriData["tp_id"]};
                    API.service("/addProduce", o, function (rsp) {
                        layer.msg(rsp.msg);
                        layer.close(index);
                        queryIt()
                    });
                    return false
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
            API.service("/deleteProduce", {p_id: item.p_id}, function (rsp) {
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
            area: ["720px", "530px"],
            title: "编辑投入品使用记录",
            content: content,
            success: function (layero, index) {
                var pEl = layero.find("[field=in_pid]").empty();
                var pClass1 = layero.find("[field=p_class1]").empty();
                var pClass2 = layero.find("[field=p_class2]").empty();
                var renderFun = function (v, item) {
                    return $("<option></option>").text(item.c_name).attr("value", item.c_id)
                };
                var first = true;
                UI.renderField(layero, item);
                API.service("/listClass1", {c_type: 1}, function (rsp) {
                    UI.renderSelectByData(pClass1, function () {
                        var cRid = $(this).val();
                        pClass2.empty();
                        if (cRid && !first) {
                            API.service("/listClass2Byrid", {c_rid: cRid}, function (rsp1) {
                                UI.renderSelectByData(pClass2, null, renderFun, rsp1.object)
                            }, function (rsp) {
                                pClass2.empty()
                            })
                        }
                    }, renderFun, rsp.object);
                    if (first) {
                        pClass1.val(item.p_class1).change();
                        API.service("/listClass2Byrid", {c_rid: item.p_class1}, function (rsp1) {
                            UI.renderSelectByData(pClass2, null, renderFun, rsp1.object);
                            pClass2.val(item.p_class2);
                            first = false
                        }, function (rsp) {
                            pClass2.empty()
                        })
                    }
                }, function (rsp) {
                    pClass1.empty()
                });
                layero.find("a.btn-cancel").click(function () {
                    layer.close(index)
                });
                layero.find("a.btn-save").click(function () {
                    var o = UI.getFieldValue(layero);
                    if (!o) {
                        return true
                    }
                    var param = {pointEntity: {tp_id: lastSelectNode.oriData["tp_id"]}};
                    for (var n in item) {
                        if (n.indexOf("p_") === 0) {
                            param[n] = item[n]
                        }
                    }
                    param = $.extend({}, param, o);
                    console.log(param)
                    API.service("/updateProduce", param, function (rsp) {
                        layer.msg(rsp.msg);
                        layer.close(index);
                        queryIt()
                    });
                    return false
                })
            },
            skin: "mlayer"
        });
        return false
    });
    queryIt = function () {
        var data = {
            p_class1: inClass1.attr("value"),
            p_class2: inClass2.attr("value"),
            p_begintime: beginTimeEl.val(),
            p_endtime: endTimeEl.val(),
        }
        console.log(data)

        var node = lastSelectNode;
        if (node && node.oriData && node.oriData["tp_type"] === 3) {
            API.service("/listProduce", {
                p_class1: inClass1.attr("value"),
                p_class2: inClass2.attr("value"),
                p_begintime: beginTimeEl.val(),
                p_endtime: endTimeEl.val(),
                pointEntity: {tp_id: node.oriData["tp_id"]},
                start: lastPageNo,
                pagesize: 10,
                p_name: $('.mx-top-search input').val()
            }, function (data) {
                console.log(data)
                tbody.empty();
                $(data.object).each(function (i) {
                    UI.appendFieldTo(rowTpl.clone(), this, tbody).data("data", this)
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
                        return
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
    UI.renderPointTree("#product3_device_tree", onNodeSelect);
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
})
;
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

    //导出EXCEl
    page.find(".btn-excel-export").click(function () {
        var node = lastSelectNode;
        if (node && node.oriData && node.oriData["tp_type"] === 3) {

            API.exportExcel("/inputExcelOut", {
                in_class1: inClass1.attr("value"),
                in_class2: inClass2.attr("value"),
                p_begintime: beginTimeEl.val(),
                p_endtime: endTimeEl.val(),
                pointEntity: {tp_id: node.oriData["tp_id"]},
                start: lastPageNo,
                pagesize: 10,
                in_mattername: $('.mx-top-search input').val()
            });
        }
    });


    page.find(" a.btn-item-add").click(function () {
        var node = lastSelectNode;

        var str = '';
        $.ajaxSetup({
            async: false
        });

        API.service("/listProduce", {
            pointEntity: {tp_id: node.oriData.tp_id},
            start: 0
        }, function (rsp) {
            if (rsp.object.length === 0) {
                layer.msg("没有生产计划");
                layer.close(index);
                return
            }
            $(rsp.object).each(function (i) {
                var item = this;
                str += '<option p_endtime="' + item.p_endtime + '" p_begintime="' + item.p_begintime + '" value="' + item.p_id + '">' + item.c2_name + " - " + item.p_begintime + "~" + item.p_endtime + '</option>';
            });

        }, function (rsp) {
            layer.msg(rsp.msg);
            layer.close(index)
        })

        var tpl =
            '<div class="row-tpl" id="start-id"> ' +
            '   <a href="#" class="mt20 btn-remove fr" style="display:none;margin-top:-10px"><img src="images/close.png" alt="" /></a> ' +
            '   <div class="clearfix"> ' +
            '    <div class="fl" style="width:290px">' +
            '     <h4>种植作物</h4> ' +
            '     <p><select field="in_pid" required="required"> ' +
            str +
            '</select></p> ' +
            '    </div> ' +
            '    <div class="fr" style="width:290px">' +
            '     <h4>投放时间</h4> ' +
            '     <p><input  id="start-time-id" field="in_time" required="required" class="laydate" placeholder="yyyy-MM-dd" type="text" /> </p>' +
            '    </div> ' +
            '   </div> ' +
            '   <div class="cleardix input-items">' +
            '    <h4>投放明细</h4> ' +
            '    <input type="text" id="name-show" field="in_mattername" readonly="" required="required" placeholder="投放明细" style="width:228px;margin-right:20px" /> ' +
            '    <input type="hidden" field="in_c_id" required="required" placeholder="投放明细" /> ' +
            '    <div id="info" style="z-index: 1;position:absolute; width:228px; height:300px;  background:#efefef; border:1px solid #eee;overflow-y:auto; display:none;"> ' +
            '     <ul id="ztree" class="ztree"> ' +
            '     </ul> ' +
            '    </div> ' +
            '    <input required="required" field="in_total" placeholder="投放数量" type="text" style="width:180px;margin-right:20px" /> ' +
            '    <select field="in_unit" required="" style="width:140px"> <option value="1">kg</option> <option value="2">吨</option> </select> ' +
            '   </div> ' +
            '   <a href="#" class="mt20 btn-plus" style="display:inline-block"><img src="images/plus.png" alt="" /></a>' +
            '  </div> ';

        console.log(str)

        page.find(".layer-edit-item").remove();
        var content = page.find(".add-layer").clone().appendTo(page).addClass("layer-edit-item");
        layer.open({
            type: 1,
            area: ["750px", "600px"],
            title: "新建投入品使用记录",
            content: content,
            success: function (layero, index) {


                var items = layero.find('#add-content');
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

                    addRow();
                });


                function addRow() {
                    var rowTpl = $(tpl);
                    var row = rowTpl.find(".btn-remove").show().end();

                    var id = Math.random().toString(36).substr(2);

                    initTree(changeID(row, id), id)
                    items.append(row);


                    return false
                }

                function changeID(el, id) {
                    el.find("#start-time-id").attr('id', 'time-' + id);
                    el.find("#ztree").attr('id', 'ztree' + id)
                    el.find("#name-show").attr('id', 'name-show' + id)
                    el.find("#info").attr('id', 'info' + id)


                    el.attr('id', id)


                    return el;
                }


                addRow();
                function initTree(layero, i) {
                    var ztree = '#ztree';
                    var info = '#info';
                    var name_show = '#name-show';
                    var id = "#start-id"
                    var id_time = '#time-' + i
                    if (i) {
                        ztree = ztree + i;
                        info = info + i;
                        name_show = name_show + i;

                        id = "#" + i;
                    }


                    var pEl = layero.find("[field=in_pid]");


                    pEl.on("change", function () {
                        var item = $(this).find("option:selected");
                        var inTime = layero.find(id_time);
                        inTime.attr("min", item.attr('p_begintime'));
                        inTime.attr("max", item.attr('p_endtime'));

                        laydate.render({
                            elem: id_time
                            , min: item.attr('p_begintime')
                            , max: item.attr('p_endtime')
                        });
                        inTime.trigger("ui-change")

                    });
                    UI.renderField(layero, {});

                    layero.find('[field=in_unit]').val('1');


                    var treeEl = layero.find(ztree);
                    var stopFunc = function (e) {
                        e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
                    }
                    var changeName = function (node) {
                        layero.find(name_show).val(node.oriData.c_name);
                        layero.find(name_show).next().val(node.oriData.c_id);
                        treeEl.parents(info).hide();
                    }

                    /* layero.onclick = function (e) {
                     layero.find("#info").hide(300);
                     }*/
                    layero.find(info).click(function (e) {
                        e = e || event;
                        stopFunc(e);
                    });
                    layero.find(name_show).click(function (e) {
                        e = e || event;
                        // stopFunc(e);
                        layero.find(info).slideToggle(300)
                    });

                    var lastSelectNode;
                    /*ztree*/
                    var onNodeSelect = function (node) {
                        if (node && lastSelectNode && lastSelectNode === node)return;

                        if (!node)node = lastSelectNode;

                        if (!node)return;

                        //当点击父节点时，自动选择第一个设备
                        if (node.oriData["c_lev"] < 2) {
                            while (node && node.oriData["c_lev"] < 2) {

                                var c = node.children;
                                if (c && c.length > 0) {
                                    node = c[0];
                                    if (node && node.oriData["c_lev"] === 2) {
                                        treeEl.data("z-tree").selectNode(node);
                                        onNodeSelect(node);
                                        return
                                    } else {

                                    }
                                } else {
                                    return;
                                }
                            }
                        }

                        lastSelectNode = node;
                        changeName(node);
                    };
                    treeEl.on("z-tree-load", function () {
                        var nodes = $(this).data("z-tree").getNodes();
                        nodes && nodes.length > 0 && onNodeSelect(nodes[0]);
                    });
                    UI.renderInputType(treeEl, onNodeSelect);
                }

            }

            ,
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
            area: ["720px", "600px"],
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


                });
                API.service("/listProduce", {
                    pointEntity: {tp_id: lastSelectNode.oriData.tp_id},
                    /* p_id: item.in_pid,*/
                    start: 0,
                    pagesize: 10
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
                    layer.msg(rsp.msg);
                    layer.close(index)
                });


                initTree(item.in_c_id);
                console.log(item)
                function initTree(c_id) {
                    var treeEl = layero.find("#ztree");
                    var stopFunc = function (e) {
                        e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
                    }
                    var changeName = function (node) {
                        if (!c_id) {

                            layero.find('[field=in_mattername]').val(node.oriData.c_name);
                            layero.find('[field=in_c_id]').val(node.oriData.c_id);
                            treeEl.parents("#info").hide();
                        }
                        c_id = null;

                    }

                    /* layero.onclick = function (e) {
                     layero.find("#info").hide(300);
                     }*/
                    layero.find('#info').click(function (e) {
                        e = e || event;
                        stopFunc(e);
                    });
                    layero.find('#name-show').click(function (e) {
                        e = e || event;
                        // stopFunc(e);
                        layero.find("#info").slideToggle(300)
                    });

                    var lastSelectNode;
                    /*ztree*/
                    var onNodeSelect = function (node) {
                        if (node && lastSelectNode && lastSelectNode === node)return;

                        if (!node)node = lastSelectNode;

                        if (!node)return;

                        //当点击父节点时，自动选择第一个设备
                        if (node.oriData["c_lev"] < 2) {
                            while (node && node.oriData["c_lev"] < 2) {

                                var c = node.children;
                                if (c && c.length > 0) {
                                    node = c[0];
                                    if (node && node.oriData["c_lev"] === 2) {
                                        treeEl.data("z-tree").selectNode(node);
                                        onNodeSelect(node);
                                        return
                                    } else {

                                    }
                                } else {
                                    return;
                                }
                            }
                        }

                        lastSelectNode = node;
                        changeName(node);
                    };
                    treeEl.on("z-tree-load", function () {
                        var nodes = $(this).data("z-tree").getNodes();
                        nodes && nodes.length > 0 && onNodeSelect(nodes[0]);

                    });
                    UI.renderInputType(treeEl, onNodeSelect);
                }


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

            console.log($('.mx-top-search input').val())
            API.service("/listInput", {
                in_class1: inClass1.attr("value"),
                in_class2: inClass2.attr("value"),
                p_begintime: beginTimeEl.val(),
                p_endtime: endTimeEl.val(),
                pointEntity: {tp_id: node.oriData["tp_id"]},
                start: lastPageNo,
                pagesize: 10,
                in_mattername: $('.mx-top-search input').val()
            }, function (data) {
                console.log(data)
                tbody.empty();
                $(data.object).each(function (i, e) {

                    var tpl = rowTpl.clone();
                    tpl.find('[field="in_total"]').attr('unit', API.dict.in_unit[e.in_unit]);

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
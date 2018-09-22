$(function () {
    var mapEl = $("#map");

    function addMapOverlay(title, shiyongzhe, zhidaozhuanjia, shengchanzhe, yunxingzhuangtai, guanlizhe, num) {
        if (yunxingzhuangtai === 1) {
            yunxingzhuangtai = "在线"
        } else {
            yunxingzhuangtai = "离线"
        }
        var sContent = "<p class='gis-info' style='width:100%; float:none;'><span>设置名称：</span>" + title + "</p>" + "<ul style='overflow: hidden;font-size: 14px'>" + "<li class='gis-info'><span>使用者:</span>" + shiyongzhe + "</li>" + "<li class='gis-info'><span>指导专家:</span>" + zhidaozhuanjia + "</li>" + "<li class='gis-info'><span>生产者:</span>" + shengchanzhe + "</li>" + "<li class='gis-info'><span>运行状态:</span>" + yunxingzhuangtai + "</li>" + "<li class='gis-info'><span>管理者:</span>" + guanlizhe + "</li>" + "</ul>" + "<ul style='overflow: hidden;font-size: 14px; margin-top:10px'>" + "<li class='info_1 gis-btn' onclick=\"window.openPageContent('综合信息','传感信息',{url:'/synthesize.html?tp_id=" + num + "'})\" >综合信息</li>" + "<li class='info_2 gis-btn' onclick=\"window.openPageContent('综合信息','视频信息',{url:'/synthesize3.html?tp_id=" + num + "'})\" >视频信息</li>" + "<li class='info_3 gis-btn' onclick=\"window.openPageContent('综合信息','图片信息',{url:'/synthesize2.html?tp_id=" + num + "'})\">图片信息</li>" + "<li class='info_4 gis-btn' onclick=\"window.openPageContent('信息发布','政策信息')\">通知信息</li>" + "<li class='info_5 gis-btn' onclick=\"window.openPageContent('生产管理','智能控制',{url:'/product.html?tp_id=" + num + "'})\">生产管理</li>" + "<li class='info_5 gis-btn'onclick=\"window.openPageContent('专家系统')\">指导专家</li>" + "</ul>" + "</div>";
        return sContent
    }

    var nodePoints = {};
    var addNodeOverlay = function (map, data) {
        var point = new BMap.Point(data.y, data.x);
        var marker = new BMap.Marker(point);
        nodePoints[data["tp_id"]] = marker;
        map.addOverlay(marker);
        map.centerAndZoom(point, 18);
        var opts = {width: 430, height: 200, title: "", enableMessage: true, message: ""};
        var ifornewhre = addMapOverlay(data.name, "", data.exportorname, data.producername, data.state, data.supervisername, data.tp_id);
        var infoWindow = new BMap.InfoWindow(ifornewhre, opts);
        marker.addEventListener("click", function () {
            map.openInfoWindow(infoWindow, point)
        })
    };
    var handlerNode = function (map, node) {
        if (node.oriData.x !== node.oriData.y && node.oriData.x !== 0) {
            addNodeOverlay(map, node.oriData)
        }
        if (node.children) {
            $(node.children).each(function () {
                handlerNode(map, this)
            })
        }
    };
    var renderMap = function (parentNode) {
        var map = mapEl.data("map");
        if (!map) {
            map = new BMap.Map("map", {minZoom: 4, maxZoom: 18});
            map.enableScrollWheelZoom(true);
            mapEl.data("map", map)
        }
        map.reset();
        for (var n in nodePoints) {
            map.removeOverlay(nodePoints[n])
        }
        nodePoints = {};
        if (parentNode && parentNode.children)$(parentNode.children).each(function (i) {
            handlerNode(map, this)
        })
    };
    var activeMapNode = function (actNode) {
        var oriDatum = actNode.oriData["tp_id"];
        var marker = nodePoints[oriDatum];
        if (marker) {
            var map = mapEl.data("map");
            if (map) {
                map.centerAndZoom(marker.point, 18)
            }
            for (var n in nodePoints) {
                nodePoints[n].setAnimation(false)
            }
            marker.setAnimation(BMAP_ANIMATION_BOUNCE);
            setTimeout(function () {
                marker.setAnimation(false)
            }, 3e3)
        }
    };
    var clickAreaNode = function (tree, node) {
        if (node.oriData && node.oriData["tp_type"] === 2) {
            tree.selectNode(node);
            renderMap(node);
            return true
        }
        if (node.children && node.children.length > 0) {
            for (var i = 0; i < node.children.length; i++) {
                var cNode = node.children[i];
                if (cNode.oriData["tp_type"] === 2) {
                    tree.selectNode(cNode);
                    renderMap(node);
                    return true
                }
                if (clickAreaNode(tree, cNode))return
            }
        }
    };
    $("#treeDemo").on("z-tree-load", function () {
        var tree = $(this).data("z-tree");
        var nodes = tree.getNodes();
        for (var i = 0; i < nodes.length; i++) {
            if (clickAreaNode(tree, nodes[i])) {
                break
            }
        }
    });
    var notifyId;
    var nodeClick = function (clickNode) {
        if (clickNode.oriData["tp_type"] === 2) {
            if (notifyId) {
                layer.close(notifyId);
                notifyId = 0
            }
            renderMap(clickNode);
            if (Object.keys && Object.keys(nodePoints).length === 0) {
                notifyId = layer.msg("无设备")
            }
        } else if (clickNode.oriData["tp_type"] === 3) {
            activeMapNode(clickNode)
        }
    };
    UI.renderPointTree("#treeDemo", nodeClick, null, {1: true, 2: true, 3: true});
    $(".sblb>h3").click(function () {
        $(this).next().toggle()
    });
    var mainIframe = $("#main_iframe");
    mapEl.height(Math.max(500, mainIframe.height() - 150));
    mainIframe.on("body-height", function () {
        var mf = $("#main_iframe");
        $("#map").height(Math.max(500, mf.height() - 65))
    })
});
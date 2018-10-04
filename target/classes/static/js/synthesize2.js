$(function () {
    var lct = $("div#main_iframe").attr("src") || location.href,
        e = purl(lct);
    var tp_id_from_url = e.param("tp_id");


    var destroyImageSlide = function () {
        var s = ".view .swiper-container";
        var bigSlide = $(s);
        if (bigSlide.data("slide")) {
            bigSlide.data("slide").destroy()
        }
        var s2 = ".preview .swiper-container";
        var smallSwiperEl = $(s2);
        if (smallSwiperEl.data("slide")) {
            smallSwiperEl.data("slide").destroy()
        }
    };
    var showDateTimeInfo = function () {
        var mn = $(".monitor-name");
        mn.find("span.date-time").remove();
        mn.append('<span class="date-time " style="padding-left:10px;">' + ($(".view .swiper-slide-active").attr("infodatatime") || "") + "</span>")
    };
    var initImageSlide = function () {
        var s = ".view .swiper-container";
        var bigSlide = $(s);
        if (bigSlide.data("slide")) {
            bigSlide.data("slide").destroy()
        }
        var viewSwiper = new Swiper(s, {
            on: {
                slideChangeTransitionStart: function () {
                    $(".preview .active-nav").removeClass("active-nav");
                    var activeNav = $(".preview .swiper-slide").eq(viewSwiper.activeIndex).addClass("active-nav");
                    showDateTimeInfo();
                    if (!activeNav.hasClass("swiper-slide-visible")) {
                        if (activeNav.index() > previewSwiper.activeIndex) {
                            var thumbsPerNav = Math.floor(previewSwiper.width / activeNav.width()) - 1;
                            previewSwiper.slideTo(activeNav.index() - thumbsPerNav)
                        } else {
                            previewSwiper.slideTo(activeNav.index())
                        }
                    }
                }
            }
        });
        bigSlide.data("slide", viewSwiper);
        $(".preview .arrow-left").off().on("click", function (e) {
            e.preventDefault();
            if (viewSwiper.activeIndex === 0) {
                viewSwiper.slideTo(viewSwiper.slides.length - 1, 1e3);
                return
            }
            viewSwiper.slidePrev()
        });
        $(".preview .arrow-right").off().on("click", function (e) {
            e.preventDefault();
            if (viewSwiper.activeIndex === viewSwiper.slides.length - 1) {
                viewSwiper.slideTo(0, 1e3);
                return
            }
            viewSwiper.slideNext()
        });
        var s2 = ".preview .swiper-container";
        var smallSwiperEl = $(s2);
        if (smallSwiperEl.data("slide")) {
            smallSwiperEl.data("slide").destroy()
        }
        var previewSwiper = new Swiper(s2, {
            slidesPerView: "auto", on: {
                tap: function () {
                    viewSwiper.slideTo(previewSwiper.clickedIndex)
                }
            }
        });
        smallSwiperEl.data("slide", previewSwiper)
    };
    laydate.render({elem: "#synthesize2_from"});
    laydate.render({elem: "#synthesize2_to"});

    var lastSelectNode = null, lastPageNo = 1;
    var listImage = function () {
        var treeEl = $("#synthesize2_tree");
        var nodes = treeEl.data("z-tree").getSelectedNodes();
        var node = nodes.length > 0 ? nodes[0] : null;
        if (null) {
            layer.msg("请选择设备");
            return
        }
        var monitorId = $(".jsd-list").find("ul").attr("value");
        if (!monitorId) {
            return
        }
        treeEl.parents(".tree-cnt").hide();
        var id = node.oriData["tp_id"];
        var deviceId = node.oriData["deviceId"];
        API.service("/listIPCPointIMG", {
            deviceId: deviceId,
            id: id,
            monitorId: monitorId,
            start: lastPageNo,
            beginTime: $("#synthesize2_from").val(),
            endTime: $("#synthesize2_to").val()
        }, function (d) {
            destroyImageSlide();
            var showPicEl = $(".pc-slide");
            var bigImgEl = showPicEl.find(".view .swiper-wrapper").empty();
            var smallImgEl = showPicEl.find(".preview .swiper-wrapper").empty();
            $(d.object).each(function () {
                var item = this;
                var bigImgSrc = window.staticPre + "/getReportImage?file_name=" + encodeURIComponent(item["smallName"]);
                var bigImgSrcB = window.staticPre + "/getReportImage?file_name=" + encodeURIComponent(item["fileName"]);
                $('<div class="swiper-slide"><a href="javascript:;" target="_blank"> <img style="width:100%;" src="images/video2.png" /></a></dev>').appendTo(bigImgEl).attr("infoDataTime", new Date(item["infoDataTime"]).format("yyyy-MM-dd hh:mm:ss")).find("img").attr("src", bigImgSrc).end().find("a").attr("href", bigImgSrcB).click(function () {
                    var bd = $("body");
                    var index = layer.open({
                        type: 1,
                        content: '<img src="' + $(this).attr("href") + '" />',
                        area: [bd.width() - 100 + "px", bd.height() - 100 + "px"],
                        shade: false,
                        title: false
                    });
                    return false
                });
                $('<div class="swiper-slide active-nav"><img src="images/video2.png" /></dev>').appendTo(smallImgEl).find("img").attr("src", window.staticPre + "/getReportImage?file_name=" + encodeURIComponent(item["smallName"]))
            });
            initImageSlide();
            UI.renderPageBar({
                paperEl: ".mx-page",
                pageNo: lastPageNo,
                count: d.totalcount,
                totalPages: d.totalpage,
                onPageItemClick: function (pageNo) {
                    lastPageNo = pageNo;
                    listImage()
                }
            });
            showDateTimeInfo()
        }, function (d) {
            destroyImageSlide();
            var showPicEl = $(".pc-slide");
            var bigImgEl = showPicEl.find(".view .swiper-wrapper").empty();
            var smallImgEl = showPicEl.find(".preview .swiper-wrapper").empty();
            initImageSlide();
            UI.renderPageBar({
                paperEl: ".mx-page",
                pageNo: lastPageNo,
                count: d.totalcount,
                totalPages: d.totalpage,
                onPageItemClick: function (pageNo) {
                    lastPageNo = pageNo;
                    listImage()
                }
            });
            showDateTimeInfo();
            layer.msg(d.msg)
        })
    };
    var queryIt = function () {
        return listImage()
    };
    $(".btn-search").click(queryIt);

    $(".jsd-list").on("click", "li", function () {
        $(this).addClass("on").siblings().removeClass("on");
        $(this).parents("ul").attr("value", $(this).attr("value")).trigger("change")
    });
    $(".jsd-list").find("ul").on("change", function () {
        var name = $(this).find("li.on").text();
        $(".monitor-name").text(name);
        lastPageNo = 1;
        listImage()
    });

    $('.btn-jsdgl').click(function (el) {
        if (!lastSelectNode) {
            layer.msg('请选择摄像头');
            return false;
        }
        var id = lastSelectNode.oriData["tp_id"];
        var deviceId = lastSelectNode.oriData["deviceId"];
        window.showMainContent('jsdgl.html?deviceId=' + deviceId + "&tp_id=" + id);
    });


    var onNodeSelect = function (node) {
        if (lastSelectNode === node)return;
        //当点击父节点时，自动选择第一个设备
        if (node.oriData["tp_type"] < 3) {
            while (node && node.oriData["tp_type"] < 3) {
                var c = node.children;
                if (c && c.length > 0) {
                    node = c[0];
                    if (node && node.oriData["tp_type"] === 3) {
                        $("#synthesize2_tree").data("z-tree").selectNode(node);
                        onNodeSelect(node);
                        return
                    }
                } else {
                    return;
                }
            }
        }


        if (node && node.oriData && node.oriData["tp_type"] === 3) {
            if (node.children && node.children.length) {
                $("#synthesize2_tree").data("z-tree").selectNode(node.children[0]);
                onNodeSelect(node.children[0]);
                return
            }
        } else if (node && node.oriData && node.oriData["tp_type"] === 4) {
            lastSelectNode = node;
            var name = node.oriData["tp_name"];
            var id = node.oriData["tp_id"];
            var deviceId = node.oriData["deviceId"];
            var parentNode = node.getParentNode();
            if (parentNode && parentNode.oriData) {
                name = parentNode.oriData["tp_name"] + " " + name
            }
            $(".sm-userInfo").text(name);
            API.service("/listIPCPoint", {deviceId: deviceId, id: id}, function (d) {
                var list = $(".sbjkd-list").empty();
                $(d.object).each(function (i) {
                    var item = this;
                    if (item.monitorName) {
                        $("<li></li>").attr("value", item.monitorId).text(item.monitorName).appendTo(list)
                    }
                });
                list.change();
                list.find("li:eq(0)").click()
            }, function (d) {
                layer.msg("err:5" + d.msg)
            });
            queryIt()
        }
    };


    $("#synthesize2_tree").on("z-tree-load", function () {
        if (tp_id_from_url > 0) {
            var treeObj = $.fn.zTree.getZTreeObj('synthesize2_tree');
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

    UI.renderPointTree("#synthesize2_tree", onNodeSelect);

    $(".sblb>h3").click(function () {
        $(this).next().toggle()
    });

});
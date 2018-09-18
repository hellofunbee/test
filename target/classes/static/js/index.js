$(function () {
    if (!sessionStorage.getItem("ckuid")) {
        layer.alert("登录失效，请重新登录", function () {
            window.location.href = "./login.html"
        })
    } else {
        var userType = sessionStorage.getItem("utype");
        if (userType == 1) {
            $(".userType").show()
        } else {
            $(".userType").hide()
        }
        var topMenus = {
            "首页": {url: "homepage.html", type: "iframe"},
            "GIS系统": "",
            "综合信息": "",
            "生产管理": "",
            "大数据分析": "",
            "发布信息": "",
            "专家系统": "expert.html",
            "系统管理": "",
            "注销": function () {
                layer.confirm("是否返回登录页面", function (b) {
                    if (b) {
                        sessionStorage.removeItem("ckuid");
                        sessionStorage.removeItem("cksid");
                        sessionStorage.removeItem("utype");
                        window.location.href = "./login.html"
                    }
                });
                return "logout"
            }
        };
        var subMenus = {
            "传感信息": "synthesize.html",
            "图片信息": "synthesize2.html",
            "视频信息": "synthesize3.html",
            "管理信息": "synthesize4.html",
            "设备分布": "GIS.html",
            "物种分布": "GIS2.html",
            "生产分布": "GIS3.html",
            "信息预警": "GIS4.html",
            "智能控制": "product.html",
            "投入品": "product2.html",
            "生产计划": "product3.html",
            "专家远程指导": "product4.html",
            "监测单数据": "bigData.html",
            "监测多数据": "bigData2.html",
            "生产数据": "bigData3.html",
            "市场数据": "bigData4.html",
            "政策信息": "publish.html",
            "即时信息": "publish2.html",
            "预警信息": "publish3.html",
            "首页资讯": "publish4.html",
            "首页轮播图": "publish5.html",
            "设备管理": "system.html",
            "分类管理": "system2.html",
            "用户管理": "system3.html",
            "权限管理": "system4.html"
        };
        var pageNav = $(".mx-nav>li").click(function () {
            var content = $(this).children("a").text();
            $(this).addClass("active").siblings().removeClass("active");
            if (topMenus[content] && content) {
                showMainContent(topMenus[content])
            }
        });
        if (window.purl && location.hash && location.hash.indexOf("page=") > 0) {
            var s = "http://s.com/?" + location.hash.substring(1);
            var url = purl(s);
            var define = url.param("page");
            var type = url.param("type");
            var nav = url.param("nav");
            if (!define) {
                pageNav.eq(0).click()
            } else {
                top.$(".mx-nav>li").removeClass("active").eq(nav).addClass("active");
                showMainContent(define, type)
            }
        } else {
            pageNav.eq(0).click()
        }
        $(".nav-drop dd").click(function () {
            $(this).addClass("on").siblings().removeClass("on").parents("li").siblings().children("dl").children("dd").removeClass("on");
            var content = $(this).text();
            if (subMenus[content]) {
                showMainContent(subMenus[content])
            } else {
                layer.msg("功能[" + content + "] 开发中...")
            }
        })
    }
    var handleUnReadMessageTimer = 0;
    var handleUnReadMessage = function () {
        API.getUnReadMessage({}, function (data) {
            console.log("有新消息：TODO：", data)
        }, function (data) {
            if (data.msg === "登录失效" || data.msg === "登陆失效") {
                layer.alert(data.msg, function () {
                    top.location.href = "login.html"
                })
            }
        })
    };
    if (false) handleUnReadMessageTimer = setInterval(handleUnReadMessage, 3e3);
    $(".mx-topSearch").on("click", "a", function () {
        window.openPageContent("信息发布", "首页资讯", {url: "publish4.html?keyword=" + encodeURIComponent($(".mx-topSearch").find("input").val())})
    })
});
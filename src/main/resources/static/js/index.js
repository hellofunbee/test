$(function () {

    var menu = $('.mx-nav').empty();
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
        var tu_id = sessionStorage.getItem("ckuid");
        var tu_type = sessionStorage.getItem("tu_type");

        $.ajaxSetup({async: false});


        API.service("/listRole", {r_value: tu_type}, function (data) {


            if (!data.object || data.object.length <= 0) {
                layer.msg('未发现任何权限');
                return false;
            }
            //设置菜单
            if (data.success && data.object.length > 0) {
                var menus = data.object[0].menu_ids
                if (!menus || menus.length == 0) {
                    layer.alert('未发现任何菜单，亲先配置角色菜单');
                    return false;
                }

                $(menus).each(function (i, e) {
                    var li = '<li class=""><a href="javascript:;"><i class="' + e.icon + '"></i>' + e.name + '</a></li>'
                    var el = UI.appendFieldTo($(li), {}, menu)

                    if (e.rank && e.rank.length > 0) {
                        var w = e.rank.length*105;
                        var left ;
                        if(i == 1){
                            left = -80;
                        }else {
                            left = -w/2
                        }

                        var sub_el = UI.appendFieldTo($('<dl class="nav-drop" style="width:'+w+'px;left:'+left+'px"></dl>'), {}, el)

                        $(e.rank).each(function (i, e) {
                            UI.appendFieldTo($('<dd class=""><a href="javascript:;">' + e.name + '</a></dd>'), {}, sub_el);
                        })
                    }

                });
                menu.change();

                var topMenus = {};
                var subMenus = {};
                $(menus).each(function (i, e) {
                    if (e.name == '首页') {
                        topMenus[e.name] = {url: e.href, type: 'iframe'}
                    } else if (e.name == '注销') {
                        topMenus[e.name] = function () {
                            layer.confirm("是否返回登录页面", function (b) {
                                if (b) {
                                    sessionStorage.removeItem("ckuid");
                                    sessionStorage.removeItem("cksid");
                                    sessionStorage.removeItem("utype");
                                    window.location.href = "./login.html"
                                }
                            });
                            return "logout"
                        };
                    } else {
                        topMenus[e.name] = e.href;
                    }

                    if (e.rank && e.rank.length > 0) {
                        $(e.rank).each(function (i, e) {
                            subMenus[e.name] = e.href;
                        })
                    }

                    


                });
                
                console.log(topMenus)
                console.log(subMenus)

                var pageNav = $(".mx-nav>li").click(function () {

                    var content = $(this).children("a").text().trim();

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
                });

                if (!subMenus['首页资讯']) {
                    $(".mx-topSearch").hide();
                }

                $(".mx-topSearch").on("click", "a", function () {
                    window.openPageContent("信息发布", "首页资讯", {url: "publish4.html?keyword=" + encodeURIComponent($(".mx-topSearch").find("input").val())})
                })


            }
        });


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
    if (false)handleUnReadMessageTimer = setInterval(handleUnReadMessage, 3e3);


});
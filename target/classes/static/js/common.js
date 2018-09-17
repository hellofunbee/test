if (!window.js_common_loaded) {
    window.console = window.console || {
            log: function () {
            }, error: function () {
            }
        };
    window.js_common_loaded = true;
    window.apiPre = window.apiPre || "";
    window.staticPre = window.staticPre || "http://120.132.53.44:8087";
    var _mock = false;
    var loadingLayers = [];
    window.startLoading = function () {
        if (loadingLayers.length) {
            return
        }
        loadingLayers.push(layer.load())
    };
    window.stopLoading = function () {
        if (loadingLayers && loadingLayers.length) {
            if (loadingLayers.length === 1) {
                layer.close(loadingLayers[0]);
                loadingLayers = []
            } else {
                loadingLayers = loadingLayers.slice(1, loadingLayers.length)
            }
        }
    };
    $.ajaxSetup({
        type: "post", beforeSend: function (xhr) {
            startLoading();
            xhr.setRequestHeader("Content-Type", "application/json")
        }, complete: stopLoading, dataType: "json"
    });
    window.API = {
        service: function (uri, data, successFunc, errorFunc) {
            if (window._hmt) {
                var s;
                try {
                    s = JSON.stringify(data || {})
                } catch (e) {
                }
                _hmt.push(["_trackEvent", "api", uri, "param", s])
            }
            if (_mock) {
                if (uri.indexOf("?") !== -1) {
                    uri = uri.substring(0, uri.indexOf("?"))
                }
                console.log("mock ", uri);
                return $.get("./data" + uri + ".json", successFunc, "json").error(function (e) {
                    if (e.status === 404) {
                        layer.msg("[mock]接口开发中 " + this.url)
                    } else {
                        layer.alert("[mock]系统异常，请重试\n" + (e.message || e.status || e.msg || e.state))
                    }
                })
            }
            var cksid = sessionStorage.getItem("cksid");
            if (cksid) {
                var ckuid = sessionStorage.getItem("ckuid");
                data = $.extend({}, {ckuid: ckuid, cksid: cksid}, data)
            }
            startLoading();
            $.ajax({
                url: apiPre + uri,
                type: "post",
                dataType: "json",
                data: JSON.stringify(data),
                success: function (data) {
                    stopLoading();
                    if (data.success) {
                        if (successFunc) {
                            successFunc(data)
                        } else {
                            layer.alert(data.msg || "成功！")
                        }
                    } else {
                        if (errorFunc) {
                            return errorFunc(data)
                        }
                        if (data.msg === "登录失效" || data.msg === "登陆失效" || data.state === 1 && data.msg === "信息不规范") {
                            layer.alert(data.msg, function () {
                                top.location.href = "login.html"
                            })
                        } else {
                            layer.alert(data.msg)
                        }
                    }
                },
                contentType: "application/json",
                error: function (e) {
                    stopLoading();
                    if (errorFunc) {
                        return errorFunc(e)
                    }
                    if (e.status === 404) {
                        layer.msg("接口开发中 " + this.url)
                    } else {
                        console.log(e);
                        layer.alert("系统异常，请重试\n" + (e.statusText || e.msg || e.state || e.status || e.message))
                    }
                }
            });
            return false
        }
    };
    API.bind = function (path) {
        return function (data, successFunc, errorFunc) {
            var callback = successFunc;
            if (typeof data === "function") {
                callback = data;
                data = {}
            }
            API.service(path, data, callback, errorFunc)
        }
    };
    function autoBody() {
        var bd = $("body");
        var body_h = bd.height();
        var height = Math.max(300, body_h - 125);
        var mbody = $("#main_iframe").height(height);
        bd.css("overflow", "auto");
        if (mbody.find(">iframe").size()) {
            mbody.height("auto").css("overflow", "hidden");
            var frameEl = mbody.find(">iframe");
            var loadFun = function () {
                var frameEl = mbody.find(">iframe");
                if (!frameEl.size() || !frameEl.contents()) {
                    return
                }
                var bh = frameEl.contents().find("body").get(0).scrollHeight;
                bh = Math.max(height, bh);
                mbody.trigger("body-height", bh);
                frameEl.height(bh).attr("scrolling", "no")
            };
            frameEl.height(height);
            setTimeout(loadFun, 100);
            setTimeout(loadFun, 1e3)
        } else {
            mbody.trigger("body-height", height);
            mbody.css("overflow", "auto").css("height", "auto").css("min-height", "600px")
        }
    }

    $(window).resize(function () {
        autoBody()
    });
    window.showMainContent = function (define, type) {
        if (typeof define === "function") {
            try {
                var ret = define()
            } catch (e) {
            }
            if (window._hmt) {
                _hmt.push(["_trackEvent", "showMainContent", "function", ret || ""])
            }
            return ret
        }
        var url = define;
        if (typeof define === "object") {
            url = define.url;
            type = define.type
        }
        try {
            if (url.indexOf("homepage.html") !== 0) {
                top.$(".mx-topSearch").hide()
            } else {
                top.$(".mx-topSearch").show()
            }
        } catch (e) {
        }
        if (window._hmt) {
            _hmt.push(["_trackPageview", url]);
            var pageURL = url;
            if (url.indexOf("/") !== 0) {
                pageURL = (location.pathname || "") + "/" + url
            }
            _hmt.push(["_trackPageview", pageURL])
        }
        if (type === "iframe") {
            $('<iframe src="' + url + '" width="100%" height="1000px" frameborder="0" scrolling="no" id="main_iframe_s"></iframe>').appendTo($("#main_iframe").empty().css("overflow", "hidden")).on("load", autoBody)
        } else {
            $.get(url, function (rsp) {
                var reg = /<head>([\s\S]+)<\/head>/gi;
                var body = rsp.replace(reg, "");
                if (body) {
                    $("#main_iframe").empty().attr("src", url).html(body);
                    autoBody()
                } else {
                    layer.msg("页面异常，无法访问")
                }
            }, "html").error(function (e) {
                if (e.status === 404) {
                    layer.alert("开发中...")
                } else {
                    layer.alert("页面异常..." + e.status)
                }
            });
            var navIndex = 0;
            top.$(".mx-nav>li").each(function (i) {
                if ($(this).is(".active")) {
                    navIndex = i;
                    return true
                }
            });
            location.hash = "#" + $.param({page: define, type: type, nav: navIndex});
            if (false)$("#main_iframe").load(url, function () {
                $("#main_iframe").attr("src", url);
                autoBody()
            }).error(function (e) {
                if (e.status === 404) {
                    layer.alert("开发中...")
                } else {
                    layer.alert("页面异常..." + e.status)
                }
            });
            autoBody()
        }
    };
    window.openPageContent = function (bigClass, childClass, options) {
        top.jQuery(".mx-nav").find("li").each(function () {
            var me = $(this);
            if (me.find(">a").text() === bigClass) {
                me.click();
                if (childClass) {
                    me.find("dd").each(function () {
                        if ($(this).text() === childClass) {
                            if (!options || !options.url) {
                                $(this).click()
                            }
                            return true
                        }
                    })
                }
                return true
            }
        });
        if (options && options.url) {
            top.showMainContent(options.url)
        }
    };
    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            S: this.getMilliseconds()
        };
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length))
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length === 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length))
            }
        }
        return format
    }

    /*auto xwf*/
    /**
     *
     * @param obj input
     * @returns {boolean}
     */
    function check(obj) {

        var fileSize = 0;
        var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
        if (isIE && !obj.files) {
            var filePath = obj.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            var file = fileSystem.GetFile(filePath);
            fileSize = file.Size;
        } else {
            fileSize = obj.get(0).files[0].size;
        }
        fileSize = Math.round(fileSize / 1024 * 100) / 100; //单位为KB
        if (fileSize >= 1024) {
            layer.alert("照片最大尺寸为1M，请重新上传!");
            return false;
        }

        return true;
    }

    /**
     *
     * @param data {key:value}
     * @param url 请求接口
     * @param input_id input id
     * @param img_id img id
     * @param onSuccess 回调
     * @param onFail 回调
     */
    function uploadImg(data, url, input_id, img_id, onSuccess, onFail) {
        $.ajaxFileUpload(
            {
                url: apiPre + url,
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: input_id, //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                data: data,
                success: function (e) {

                    if (onSuccess) {
                        onSuccess(data)
                    }
                    ;

                    if (e.state == 0) {
                        $("#" + img_id).attr("src", e.object);
                        $("#" + img_id).attr("value", e.object)

                    } else if (e.state == 2) {
                        layer.alert(e.msg, function () {
                            window.parent.location.href = "./login.html"
                        })

                    }
                    layer.alert(e.msg)
                    layer.close(t);

                },
                error: function () {
                    if (onFail) {
                        onFail(data)
                    }
                    ;
                    layer.alert("请求失败，请重新尝试")
                }
            }
        );

    }
}
(function (e) {
    if (!e.WebVideoCtrl) {
        var t = function () {
            function t() {
                this.id = this.createUUID()
            }

            var a = "100%", o = "100%", r = "", s = "", i = {
                szContainerID: "",
                szColorProperty: "",
                szOcxClassId: "clsid:E7EF736D-B4E6-4A5A-BA94-732D71107808",
                szMimeTypes: "application/hwp-webvideo-plugin",
                iWndowType: 1,
                iPlayMode: 2,
                bDebugMode: !1,
                cbSelWnd: null,
                cbEvent: null
            }, l = null, c = 0, u = [], d = [], p = null, m = null, h = null, P = null, f = this, g = null, I = 1, y = 2, v = 200, C = 0, S = 1, _ = 2, b = 3, D = 4, T = 5, x = 6, z = 0, L = 2, w = 3, A = 21, M = 0, R = "IPCamera", W = "IPDome", H = "IPZoom", q = "<?xml version='1.0' encoding='utf-8'?><FileVersion><Platform name='win32'><npWebVideoPlugin.dll>3,0,5,56</npWebVideoPlugin.dll><WebVideoActiveX.ocx>3,0,5,56</WebVideoActiveX.ocx><PlayCtrl.dll>7,3,0,40</PlayCtrl.dll><StreamTransClient.dll>1,1,2,11</StreamTransClient.dll><SystemTransform.dll>2,3,0,10</SystemTransform.dll><NetStream.dll>1,0,5,58</NetStream.dll><HCNetSDK.dll>4,2,6,7</HCNetSDK.dll><ShowRemConfig.dll>3,1,2,14</ShowRemConfig.dll></Platform></FileVersion>";
            e.GetSelectWndInfo = function (e) {
                var t = P.loadXML(e);
                c = parseInt(n.$XML(t).find("SelectWnd").eq(0).text(), 10);
                var a = [];
                a.push("<RealPlayInfo>"), a.push("<SelectWnd>" + c + "</SelectWnd>"), a.push("</RealPlayInfo>"), i.cbSelWnd && i.cbSelWnd(P.loadXML(a.join("")))
            }, e.ZoomInfoCallback = function (e) {
                var t = f.findWndIndexByIndex(c);
                if (-1 != t) {
                    var n = d[t];
                    if (t = f.findDeviceIndexByIP(n.szIP), -1 != t) {
                        var a = u[t];
                        a.oProtocolInc.set3DZoom(a, n, e, {
                            success: function () {
                            }, error: function () {
                            }
                        })
                    }
                }
            }, e.PluginEventHandler = function (e, t, n) {
                k("插件事件：PluginEventHandler iEventType：%s iParam1: %s, iParam2: %s", e, t, n), z == e || L == e ? f.I_Stop(t) : A == e ? f.I_StopRecord(t) : w == e && f.I_StopVoiceTalk(), i.cbEvent && i.cbEvent(e, t, n)
            }, e.GetHttpInfo = function (e, t) {
                k("http响应返回：http状态：%s, http数据：%s", e, t), et.prototype.processCallback(e, t)
            };
            var k = function () {
                if (i.bDebugMode) {
                    var e = N(arguments);
                    p._alert(e)
                }
            }, N = function () {
                for (var e = arguments[0], t = 1; arguments.length > t; t++)e = e.replace("%s", arguments[t]);
                return e
            }, G = function (e) {
                return e === void 0
            }, F = function () {
                var e = "";
                if (P.browser().msie)e = "<object classid='" + i.szOcxClassId + "' codebase='' standby='Waiting...' " + "id='" + r + "' width='" + a + "' height='" + o + "' align='center' >" + "<param name='wndtype' value='" + i.iWndowType + "'>" + "<param name='playmode' value='" + i.iPlayMode + "'>" + "<param name='colors' value='" + i.szColorProperty + "'></object>"; else for (var t = navigator.mimeTypes.length, n = 0; t > n; n++)navigator.mimeTypes[n].type.toLowerCase() == i.szMimeTypes && (e = "<embed align='center' type='" + i.szMimeTypes + "' width='" + a + "' height='" + o + "' name='" + s + "' wndtype='" + i.iWndowType + "' playmode='" + i.iPlayMode + "' colors='" + i.szColorProperty + "'>");
                return e
            }, E = function () {
                var e = l.HWP_GetLocalConfig();
                g = P.loadXML(e)
            }, X = function (e) {
                f.I_GetDeviceInfo(e.szIP, {
                    success: function (t) {
                        e.szDeviceType = n.$XML(t).find("deviceType").eq(0).text()
                    }
                }), f.I_GetAnalogChannelInfo(e.szIP, {
                    success: function (t) {
                        e.iAnalogChannelNum = n.$XML(t).find("VideoInputChannel", !0).length
                    }
                }), f.I_GetAudioInfo(e.szIP, {
                    success: function (t) {
                        var a = n.$XML(t).find("audioCompressionType", !0);
                        if (a.length > 0) {
                            var o = n.$XML(a).eq(0).text(), r = 0;
                            "G.711ulaw" == o ? r = 1 : "G.711alaw" == o ? r = 2 : "G.726" == o && (r = 3), e.iAudioType = r
                        }
                    }
                })
            }, V = function (e) {
                var t = -1, n = -1, a = -1, o = null;
                if (O(e))o = Z(e), t = o.iRtspPort, a = o.iDevicePort; else {
                    for (var r = U(e), s = !1, i = 0; r.length > i; i++)if (r[i].ipv4 == e.szIP || r[i].ipv6 == e.szIP) {
                        s = !0;
                        break
                    }
                    s ? o = Z(e) : (o = B(e), -1 == o.iRtspPort && -1 == o.iDevicePort && (o = Z(e))), t = o.iRtspPort, n = o.iHttpPort, a = o.iDevicePort
                }
                return o
            }, Z = function (e) {
                var t = -1, a = -1, o = -1;
                return e.oProtocolInc.getPortInfo(e, {
                    async: !1, success: function (e) {
                        for (var r = n.$XML(e).find("AdminAccessProtocol", !0), s = 0, i = r.length; i > s; s++)"rtsp" === n.$XML(r).eq(s).find("protocol").eq(0).text().toLowerCase() && (t = parseInt(n.$XML(r).eq(s).find("portNo").eq(0).text(), 10)), "http" === n.$XML(r).eq(s).find("protocol").eq(0).text().toLowerCase() && (a = parseInt(n.$XML(r).eq(s).find("portNo").eq(0).text(), 10)), "dev_manage" === n.$XML(r).eq(s).find("protocol").eq(0).text().toLowerCase() && (o = parseInt(n.$XML(r).eq(s).find("portNo").eq(0).text(), 10))
                    }, error: function () {
                        t = -1, a = -1, o = -1
                    }
                }), {iRtspPort: t, iHttpPort: a, iDevicePort: o}
            }, B = function (e) {
                var t = -1, a = -1, o = -1;
                return e.oProtocolInc.getUPnPPortStatus(e, {
                    async: !1, success: function (e) {
                        for (var r = n.$XML(e).find("portStatus", !0), s = 0, i = r.length; i > s; s++)"rtsp" == n.$XML(r).eq(s).find("internalPort").eq(0).text().toLowerCase() && (t = parseInt(n.$XML(r).eq(s).find("externalPort").eq(0).text(), 10)), "http" == n.$XML(r).eq(s).find("internalPort").eq(0).text().toLowerCase() && (a = parseInt(n.$XML(r).eq(s).find("externalPort").eq(0).text(), 10)), "admin" == n.$XML(r).eq(s).find("internalPort").eq(0).text().toLowerCase() && (o = parseInt(n.$XML(r).eq(s).find("externalPort").eq(0).text(), 10))
                    }, error: function () {
                        t = -1, a = -1, o = -1
                    }
                }), {iRtspPort: t, iHttpPort: a, iDevicePort: o}
            }, U = function (e) {
                var t = [];
                return e.oProtocolInc.getNetworkBond(e, {
                    async: !1, success: function (a) {
                        "true" == n.$XML(a).find("enabled").eq(0).text() ? t.push({
                            ipv4: n.$XML(a).find("ipAddress").eq(0).text(),
                            ipv6: n.$XML(a).find("ipv6Address").eq(0).text()
                        }) : e.oProtocolInc.getNetworkInterface(e, {
                            async: !1, success: function (e) {
                                for (var a = n.$XML(e).find("NetworkInterface", !0), o = 0, r = a.length; r > o; o++) {
                                    t.push({
                                        ipv4: n.$XML(e).find("ipAddress").eq(0).text(),
                                        ipv6: n.$XML(e).find("ipv6Address").eq(0).text()
                                    });
                                    break
                                }
                            }, error: function () {
                            }
                        })
                    }, error: function () {
                        e.oProtocolInc.getNetworkInterface(e, {
                            async: !1, success: function (e) {
                                for (var a = n.$XML(e).find("NetworkInterface", !0), o = 0, r = a.length; r > o; o++) {
                                    t.push({
                                        ipv4: n.$XML(e).find("ipAddress").eq(0).text(),
                                        ipv6: n.$XML(e).find("ipv6Address").eq(0).text()
                                    });
                                    break
                                }
                            }, error: function () {
                            }
                        })
                    }
                }), t
            }, O = function (e) {
                var t = !1;
                return e.oProtocolInc.getPPPoEStatus(e, {
                    async: !1, success: function (e) {
                        t = n.$XML(e).find("ipAddress", !0).length > 0 ? !0 : n.$XML(e).find("ipv6Address", !0).length > 0 ? !0 : !1
                    }, error: function () {
                        t = !1
                    }
                }), t
            }, K = function (e) {
                e.oStreamCapa.bObtained || e.oProtocolInc instanceof tt && (R == e.szDeviceType || W == e.szDeviceType || H == e.szDeviceType ? e.oProtocolInc.getStreamChannels(e, {
                    async: !1,
                    success: function (t) {
                        e.oStreamCapa.bObtained = !0;
                        for (var n = $(t).find("streamingTransport", !0).length, a = 0; n > a; a++)if ("shttp" == $(t).find("streamingTransport").eq(a).text().toLowerCase()) {
                            e.oStreamCapa.bObtained = !0, e.oStreamCapa.bSupportShttpPlay = !0, e.oStreamCapa.bSupportShttpPlayback = !0, e.oStreamCapa.bSupportShttpsPlay = !0, e.oStreamCapa.bSupportShttpsPlayback = !0, e.oStreamCapa.iIpChanBase = 1;
                            break
                        }
                    },
                    error: function () {
                    }
                }) : e.oProtocolInc.getSDKCapa(e, {
                    async: !1, success: function (t) {
                        e.oStreamCapa.bObtained = !0, e.oStreamCapa.bSupportShttpPlay = "true" === n.$XML(t).find("isSupportHttpPlay").eq(0).text(), e.oStreamCapa.bSupportShttpPlayback = "true" === n.$XML(t).find("isSupportHttpPlayback").eq(0).text(), e.oStreamCapa.bSupportShttpsPlay = "true" === n.$XML(t).find("isSupportHttpsPlay").eq(0).text(), e.oStreamCapa.bSupportShttpsPlayback = "true" === n.$XML(t).find("isSupportHttpsPlayback").eq(0).text(), e.oStreamCapa.bSupportShttpPlaybackTransCode = "true" === n.$XML(t).find("isSupportHttpTransCodePlayback").eq(0).text(), e.oStreamCapa.bSupportShttpsPlaybackTransCode = "true" === n.$XML(t).find("isSupportHttpsTransCodePlayback").eq(0).text(), n.$XML(t).find("ipChanBase", !0).length > 0 && (e.oStreamCapa.iIpChanBase = parseInt(n.$XML(t).find("ipChanBase").eq(0).text(), 10))
                    }, error: function () {
                        e.oStreamCapa.bObtained = !0
                    }
                }))
            }, j = function (e) {
                var t = {TransFrameRate: "", TransResolution: "", TransBitrate: ""};
                if (P.extend(t, e), "" == t.TransFrameRate || "" == t.TransResolution || "" == t.TransBitrate)return "";
                var n = [];
                return n.push("<?xml version='1.0' encoding='UTF-8'?>"), n.push("<CompressionInfo>"), n.push("<TransFrameRate>" + t.TransFrameRate + "</TransFrameRate>"), n.push("<TransResolution>" + t.TransResolution + "</TransResolution>"), n.push("<TransBitrate>" + t.TransBitrate + "</TransBitrate>"), n.push("</CompressionInfo>"), n.join("")
            };
            this.I_InitPlugin = function (e, t, n) {
                a = e, o = t, P.extend(i, n)
            }, this.I_InsertOBJECTPlugin = function (t) {
                return G(t) || (i.szContainerID = t), null == document.getElementById(i.szContainerID) ? -1 : null != document.getElementById(r) || 0 != document.getElementsByName(r).length ? -1 : (document.getElementById(i.szContainerID).innerHTML = F(), l = P.browser().msie ? document.getElementById(r) : document.getElementsByName(s)[0], null == l && null == l.object ? -1 : ("object" == typeof e.attachEvent && P.browser().msie && (l.attachEvent("GetSelectWndInfo", GetSelectWndInfo), l.attachEvent("ZoomInfoCallback", ZoomInfoCallback), l.attachEvent("GetHttpInfo", GetHttpInfo), l.attachEvent("PluginEventHandler", PluginEventHandler)), E(), 0))
            }, this.I_WriteOBJECT_XHTML = function () {
                return document.writeln(F()), l = P.browser().msie ? document.getElementById(r) : document.getElementsByName(s)[0], null == l && null == l.object ? -1 : (P.browser().msie && (l.attachEvent("GetSelectWndInfo", GetSelectWndInfo), l.attachEvent("ZoomInfoCallback", ZoomInfoCallback), l.attachEvent("GetHttpInfo", GetHttpInfo), l.attachEvent("PluginEventHandler", PluginEventHandler)), E(), 0)
            }, this.I_OpenFileDlg = function (e) {
                var t = l.HWP_OpenFileBrowser(e, "");
                if (null == t)return "";
                if (1 == e) {
                    if (t.length > 100)return -1
                } else if (t.length > 130)return -1;
                return t
            }, this.I_GetLocalCfg = function () {
                var e = l.HWP_GetLocalConfig(), t = [];
                return g = P.loadXML(e), t.push("<LocalConfigInfo>"), t.push("<ProtocolType>" + n.$XML(g).find("ProtocolType").eq(0).text() + "</ProtocolType>"), t.push("<PackgeSize>" + n.$XML(g).find("PackgeSize").eq(0).text() + "</PackgeSize>"), t.push("<PlayWndType>" + n.$XML(g).find("PlayWndType").eq(0).text() + "</PlayWndType>"), t.push("<BuffNumberType>" + n.$XML(g).find("BuffNumberType").eq(0).text() + "</BuffNumberType>"), t.push("<RecordPath>" + n.$XML(g).find("RecordPath").eq(0).text() + "</RecordPath>"), t.push("<CapturePath>" + n.$XML(g).find("CapturePath").eq(0).text() + "</CapturePath>"), t.push("<PlaybackFilePath>" + n.$XML(g).find("PlaybackFilePath").eq(0).text() + "</PlaybackFilePath>"), t.push("<PlaybackPicPath>" + n.$XML(g).find("PlaybackPicPath").eq(0).text() + "</PlaybackPicPath>"), t.push("<DownloadPath>" + n.$XML(g).find("DownloadPath").eq(0).text() + "</DownloadPath>"), t.push("<IVSMode>" + n.$XML(g).find("IVSMode").eq(0).text() + "</IVSMode>"), t.push("<CaptureFileFormat>" + n.$XML(g).find("CaptureFileFormat").eq(0).text() + "</CaptureFileFormat>"), t.push("</LocalConfigInfo>"), P.loadXML(t.join(""))
            }, this.I_SetLocalCfg = function (e) {
                var t = P.loadXML(e), a = -1;
                return n.$XML(g).find("ProtocolType").eq(0).text(n.$XML(t).find("ProtocolType").eq(0).text()), n.$XML(g).find("PackgeSize").eq(0).text(n.$XML(t).find("PackgeSize").eq(0).text()), n.$XML(g).find("PlayWndType").eq(0).text(n.$XML(t).find("PlayWndType").eq(0).text()), n.$XML(g).find("BuffNumberType").eq(0).text(n.$XML(t).find("BuffNumberType").eq(0).text()), n.$XML(g).find("RecordPath").eq(0).text(n.$XML(t).find("RecordPath").eq(0).text()), n.$XML(g).find("CapturePath").eq(0).text(n.$XML(t).find("CapturePath").eq(0).text()), n.$XML(g).find("PlaybackFilePath").eq(0).text(n.$XML(t).find("PlaybackFilePath").eq(0).text()), n.$XML(g).find("PlaybackPicPath").eq(0).text(n.$XML(t).find("PlaybackPicPath").eq(0).text()), n.$XML(g).find("DownloadPath").eq(0).text(n.$XML(t).find("DownloadPath").eq(0).text()), n.$XML(g).find("IVSMode").eq(0).text(n.$XML(t).find("IVSMode").eq(0).text()), n.$XML(g).find("CaptureFileFormat").eq(0).text(n.$XML(t).find("CaptureFileFormat").eq(0).text()), a = l.HWP_SetLocalConfig(P.toXMLStr(g)), a ? 0 : -1
            };
            var Y = function (e, t, n, a, o, r, s) {
                var i = {protocol: t, success: null, error: null};
                P.extend(i, s), P.extend(i, {
                    success: function (i) {
                        var l = new J;
                        l.szIP = e, 2 == t ? (l.szHttpProtocol = "https://", l.iHttpsPort = n) : (l.szHttpProtocol = "http://", l.iHttpPort = n), l.iCGIPort = n, l.szAuth = a, l.iDeviceProtocol = o, l.oProtocolInc = r, u.push(l), k("使用%s协议登录成功", o), X(l), s.success && s.success(i)
                    }, error: function (e, t) {
                        s.error && s.error(e, t)
                    }
                }), r.login(e, n, a, i)
            };
            this.I_Login = function (e, t, n, a, o, r) {
                var s = this.findDeviceIndexByIP(e);
                if (-1 != s)return k("设备已经登录过"), -1;
                var i = m, l = I;
                G(r.cgi) || (I == r.cgi ? (i = m, l = I) : (i = h, l = y));
                var c = "";
                if (I == l) {
                    c = P.Base64.encode(":" + a + ":" + o);
                    var u = {success: null, error: null};
                    P.extend(u, r), P.extend(u, {
                        error: function (s, u) {
                            c = P.Base64.encode(a + ":" + o), l = I, i = m;
                            var d = {success: null, error: null};
                            P.extend(d, r), P.extend(d, {
                                error: function () {
                                    if (!G(r.cgi))return r.error && r.error(s, u), void 0;
                                    c = P.Base64.encode(a + ":" + o), l = y, i = h;
                                    var d = {success: null, error: null};
                                    P.extend(d, r), P.extend(d, {
                                        error: function (e, t) {
                                            r.error && r.error(e, t)
                                        }
                                    }), Y(e, t, n, c, l, i, d)
                                }
                            }), Y(e, t, n, c, l, i, d)
                        }
                    }), Y(e, t, n, c, l, i, u)
                } else {
                    c = P.Base64.encode(a + ":" + o), l = y, i = h;
                    var u = {success: null, error: null};
                    P.extend(u, r), P.extend(u, {
                        error: function (e, t) {
                            r.error && r.error(e, t)
                        }
                    }), Y(e, t, n, c, l, i, u)
                }
            }, this.I_Logout = function (e) {
                var t = this.findDeviceIndexByIP(e);
                return -1 != t ? (u.splice(t, 1), 0) : -1
            }, this.I_GetAudioInfo = function (e, t) {
                var n = this.findDeviceIndexByIP(e);
                if (-1 != n) {
                    var a = u[n], o = {success: null, error: null};
                    P.extend(o, t), a.oProtocolInc.getAudioInfo(a, o)
                }
            }, this.I_GetDeviceInfo = function (e, t) {
                var n = this.findDeviceIndexByIP(e);
                if (-1 != n) {
                    var a = u[n], o = {success: null, error: null};
                    P.extend(o, t), a.oProtocolInc.getDeviceInfo(a, o)
                }
            }, this.I_GetAnalogChannelInfo = function (e, t) {
                var n = this.findDeviceIndexByIP(e);
                if (-1 != n) {
                    var a = u[n], o = {success: null, error: null};
                    P.extend(o, t), a.oProtocolInc.getAnalogChannelInfo(a, o)
                }
            }, this.I_GetDigitalChannelInfo = function (e, t) {
                var n = this.findDeviceIndexByIP(e);
                if (-1 != n) {
                    var a = u[n], o = {success: null, error: null};
                    P.extend(o, t), a.oProtocolInc.getDigitalChannelInfo(a, o)
                }
            }, this.I_GetZeroChannelInfo = function (e, t) {
                var n = this.findDeviceIndexByIP(e);
                if (-1 != n) {
                    var a = u[n], o = {success: null, error: null};
                    P.extend(o, t), a.oProtocolInc.getZeroChannelInfo(a, o)
                }
            }, this.I_StartRealPlay = function (e, t) {
                var a = this.findDeviceIndexByIP(e), o = -1, r = "", s = "", i = -1, l = 0, p = 0, m = !1, h = {
                    iWndIndex: c,
                    iStreamType: 1,
                    iChannelID: 1,
                    bZeroChannel: !1
                };
                if (P.extend(h, t), -1 != a) {
                    K(u[a]);
                    var f = u[a], I = parseInt(n.$XML(g).find("ProtocolType").eq(0).text(), 10);
                    if (I == M && f.oStreamCapa.bSupportShttpPlay ? (k("SHTTP RealPlay"), r = f.oProtocolInc.CGI.startShttpRealPlay, s = "http://", p = h.iStreamType - 1, l = h.iChannelID <= f.iAnalogChannelNum ? h.iChannelID : f.oStreamCapa.iIpChanBase + parseInt(h.iChannelID, 10) - f.iAnalogChannelNum - 1, m = !0, G(h.iPort) ? "https://" == f.szHttpProtocol ? (-1 == f.iHttpPort && (f.iHttpPort = V(f).iHttpPort), i = f.iHttpPort) : i = f.iCGIPort : (f.iHttpPort = h.iPort, i = h.iPort)) : (k("RTSP RealPlay"), r = f.oProtocolInc.CGI.startRealPlay, s = "rtsp://", p = h.iStreamType, l = h.iChannelID, G(h.iPort) || (f.iRtspPort = h.iPort), -1 == f.iRtspPort && (f.iRtspPort = V(f).iRtspPort), i = f.iRtspPort), -1 == i)return k("获取端口号失败"), o;
                    if (P.extend(h, {
                            urlProtocol: s,
                            cgi: r,
                            iPort: i,
                            iStreamType: p,
                            iChannelID: l
                        }), a = this.findWndIndexByIndex(h.iWndIndex), -1 == a && (o = f.oProtocolInc.startRealPlay(f, h)), -1 == o)f.iRtspPort = -1; else {
                        a = this.findWndIndexByIndex(h.iWndIndex);
                        var y = d[a];
                        y.bShttpIPChannel = m
                    }
                }
                return o
            }, this.I_Stop = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t];
                    a.bRecord && l.HWP_StopSave(a.iIndex), a.bSound && l.HWP_CloseSound(), a.bEZoom && l.HWP_DisableZoom(a.iIndex), n = l.HWP_Stop(e), 0 == n && d.splice(t, 1)
                }
                return n
            }, this.I_OpenSound = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t];
                    a.bSound || (n = l.HWP_OpenSound(e), 0 == n && (a.bSound = !0))
                }
                return n
            }, this.I_CloseSound = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t];
                    a.bSound && (n = l.HWP_CloseSound(), 0 == n && (a.bSound = !1))
                }
                return n
            }, this.I_SetVolume = function (e, t) {
                t = G(t) ? c : t;
                var n = this.findWndIndexByIndex(t), a = -1;
                return -1 != n && (a = l.HWP_SetVolume(t, e)), a
            }, this.I_CapturePic = function (e, t) {
                t = G(t) ? c : t;
                var n = this.findWndIndexByIndex(t), a = -1;
                return -1 != n && (a = l.HWP_CapturePicture(t, e)), a
            }, this.I_StartRecord = function (e, t) {
                t = G(t) ? c : t;
                var n = this.findWndIndexByIndex(t), a = -1;
                if (-1 != n) {
                    var o = d[n];
                    o.bRecord || (a = l.HWP_StartSave(t, e), 0 == a && (o.bRecord = !0))
                }
                return a
            }, this.I_StopRecord = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t];
                    a.bRecord && (n = l.HWP_StopSave(e), 0 == n && (a.bRecord = !1))
                }
                return n
            }, this.I_StartVoiceTalk = function (e, t) {
                if (isNaN(parseInt(t, 10)))return -1;
                var n = this.findDeviceIndexByIP(e), a = -1;
                if (-1 != n) {
                    var o = u[n];
                    o.bVoiceTalk || (a = o.oProtocolInc.startVoiceTalk(o, t), 0 == a && (u[n].bVoiceTalk = !0))
                }
                return a
            }, this.I_StopVoiceTalk = function () {
                for (var e = l.HWP_StopVoiceTalk(), t = 0, n = u.length; n > t; t++)if (u[t].bVoiceTalk) {
                    u[t].bVoiceTalk = !1;
                    break
                }
                return e
            }, this.I_PTZControl = function (e, t, n) {
                var a = {iWndIndex: c, iPTZIndex: e, iPTZSpeed: 4};
                P.extend(a, n), P.extend(a, {async: !1});
                var o = this.findWndIndexByIndex(a.iWndIndex);
                if (-1 != o) {
                    var r = d[o];
                    if (o = this.findDeviceIndexByIP(r.szIP), -1 != o) {
                        var s = u[o];
                        9 == e ? s.oProtocolInc.ptzAutoControl(s, t, r, a) : s.oProtocolInc.ptzControl(s, t, r, a)
                    }
                }
            }, this.I_EnableEZoom = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t];
                    a.bEZoom || (n = l.HWP_EnableZoom(e, 0), 0 == n && (a.bEZoom = !0))
                }
                return n
            }, this.I_DisableEZoom = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t];
                    if (a.bEZoom)return l.HWP_DisableZoom(e), a.bEZoom = !1, 0
                }
                return n
            }, this.I_Enable3DZoom = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t];
                    a.b3DZoom || (n = l.HWP_EnableZoom(e, 1), 0 == n && (a.b3DZoom = !0))
                }
                return n
            }, this.I_Disable3DZoom = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t];
                    if (a.b3DZoom)return l.HWP_DisableZoom(e), a.b3DZoom = !1, 0
                }
                return n
            }, this.I_FullScreen = function (e) {
                l.HWP_FullScreenDisplay(e)
            }, this.I_SetPreset = function (e, t) {
                var n = {iWndIndex: c, iPresetID: e};
                P.extend(n, t);
                var a = this.findWndIndexByIndex(n.iWndIndex);
                if (-1 != a) {
                    var o = d[a];
                    if (a = this.findDeviceIndexByIP(o.szIP), -1 != a) {
                        var r = u[a];
                        r.oProtocolInc.setPreset(r, o, n)
                    }
                }
            }, this.I_GoPreset = function (e, t) {
                var n = {iWndIndex: c, iPresetID: e};
                P.extend(n, t);
                var a = this.findWndIndexByIndex(n.iWndIndex);
                if (-1 != a) {
                    var o = d[a];
                    if (a = this.findDeviceIndexByIP(o.szIP), -1 != a) {
                        var r = u[a];
                        r.oProtocolInc.goPreset(r, o, n)
                    }
                }
            }, this.I_RecordSearch = function (e, t, n, a, o) {
                var r = this.findDeviceIndexByIP(e);
                if (-1 != r) {
                    var s = u[r], i = {
                        iChannelID: t,
                        szStartTime: n,
                        szEndTime: a,
                        iSearchPos: 0,
                        success: null,
                        error: null
                    };
                    P.extend(i, o), s.oProtocolInc.recordSearch(s, i)
                }
            }, this.I_StartPlayback = function (e, t) {
                var a = this.findDeviceIndexByIP(e), o = -1, r = "", s = "", i = -1, l = 1, d = P.dateFormat(new Date, "yyyy-MM-dd"), p = {
                    iWndIndex: c,
                    iChannelID: 1,
                    szStartTime: d + " 00:00:00",
                    szEndTime: d + " 23:59:59"
                };
                if (P.extend(p, t), -1 != a) {
                    K(u[a]);
                    var m = u[a], h = parseInt(n.$XML(g).find("ProtocolType").eq(0).text(), 10);
                    if (h == M && m.oStreamCapa.bSupportShttpPlay ? (r = G(p.oTransCodeParam) ? m.oProtocolInc.CGI.startShttpPlayback : m.oProtocolInc.CGI.startTransCodePlayback, s = "http://", l = p.iChannelID <= m.iAnalogChannelNum ? p.iChannelID : m.oStreamCapa.iIpChanBase + parseInt(p.iChannelID, 10) - m.iAnalogChannelNum - 1, G(p.iPort) ? "https://" == m.szHttpProtocol ? (-1 == m.iHttpPort && (m.iHttpPort = V(m).iHttpPort), i = m.iHttpPort) : i = m.iCGIPort : (m.iHttpPort = p.iPort, i = p.iPort)) : (r = m.oProtocolInc.CGI.startPlayback, s = "rtsp://", l = 100 * p.iChannelID + 1, G(p.iPort) || (m.iRtspPort = p.iPort), -1 == m.iRtspPort && (m.iRtspPort = V(m).iRtspPort), i = m.iRtspPort), -1 == i)return k("获取端口号失败"), o;
                    P.extend(p, {
                        urlProtocol: s,
                        cgi: r,
                        iPort: i,
                        iChannelID: l
                    }), a = this.findWndIndexByIndex(p.iWndIndex), -1 == a && (p.szStartTime = p.szStartTime.replace(/[-:]/g, "").replace(" ", "T") + "Z", p.szEndTime = p.szEndTime.replace(/[-:]/g, "").replace(" ", "T") + "Z", o = m.oProtocolInc.startPlayback(m, p)), -1 == o && (m.iRtspPort = -1)
                }
                return o
            }, this.I_ReversePlayback = function (e, t) {
                var a = this.findDeviceIndexByIP(e), o = -1, r = "", s = "", i = -1, l = -1, d = P.dateFormat(new Date, "yyyy-MM-dd"), p = {
                    iWndIndex: c,
                    iChannelID: 1,
                    bZeroChannel: !1,
                    szStartTime: d + " 00:00:00",
                    szEndTime: d + " 23:59:59"
                };
                if (P.extend(p, t), -1 != a) {
                    K(u[a]);
                    var m = u[a], h = parseInt(n.$XML(g).find("ProtocolType").eq(0).text(), 10);
                    if (h == M && m.oStreamCapa.bSupportShttpPlay ? (r = m.oProtocolInc.CGI.startShttpReversePlayback, s = "http://", l = p.iChannelID <= m.iAnalogChannelNum ? p.iChannelID : m.oStreamCapa.iIpChanBase + parseInt(p.iChannelID, 10) - m.iAnalogChannelNum - 1, G(p.iPort) ? "https://" == m.szHttpProtocol ? (-1 == m.iHttpPort && (m.iHttpPort = V(m).iHttpPort), i = m.iHttpPort) : i = m.iCGIPort : (m.iHttpPort = p.iPort, i = p.iPort)) : (r = m.oProtocolInc.CGI.startPlayback, s = "rtsp://", l = 100 * p.iChannelID + 1, G(p.iPort) || (m.iRtspPort = p.iPort), -1 == m.iRtspPort && (m.iRtspPort = V(m).iRtspPort), i = m.iRtspPort), -1 == i)return k("获取端口号失败"), o;
                    P.extend(p, {
                        urlProtocol: s,
                        cgi: r,
                        iPort: i,
                        iChannelID: l
                    }), a = this.findWndIndexByIndex(p.iWndIndex), -1 == a && (p.szStartTime = p.szStartTime.replace(/[-:]/g, "").replace(" ", "T") + "Z", p.szEndTime = p.szEndTime.replace(/[-:]/g, "").replace(" ", "T") + "Z", o = m.oProtocolInc.reversePlayback(m, p))
                }
                return -1 == o && (m.iRtspPort = -1), o
            }, this.I_Frame = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t], o = a.iPlayStatus;
                    (o == _ || o == D) && (n = l.HWP_FrameForward(e), 0 == n && (a.iPlayStatus = D))
                }
                return n
            }, this.I_Pause = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t], o = a.iPlayStatus;
                    o == _ ? (n = l.HWP_Pause(e), 0 == n && (a.iPlayStatus = b)) : o == T && (n = l.HWP_Pause(e), 0 == n && (a.iPlayStatus = x))
                }
                return n
            }, this.I_Resume = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t], o = a.iPlayStatus;
                    o == b || o == D ? (n = l.HWP_Resume(e), 0 == n && (a.iPlayStatus = _)) : o == x && (n = l.HWP_Resume(e), 0 == n && (a.iPlayStatus = T))
                }
                return n
            }, this.I_PlaySlow = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t];
                    a.iPlayStatus == _ && (n = l.HWP_Slow(e))
                }
                return n
            }, this.I_PlayFast = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = d[t];
                    a.iPlayStatus == _ && (n = l.HWP_Fast(e))
                }
                return n
            }, this.I_GetOSDTime = function (e) {
                e = G(e) ? c : e;
                var t = this.findWndIndexByIndex(e), n = -1;
                if (-1 != t) {
                    var a = l.HWP_GetOSDTime(e);
                    return P.dateFormat(new Date(1e3 * a), "yyyy-MM-dd hh:mm:ss")
                }
                return n
            }, this.I_StartDownloadRecord = function (e, t, n) {
                var a = this.findDeviceIndexByIP(e), o = -1;
                if (-1 != a) {
                    var r = u[a], s = {szPlaybackURI: t, szFileName: n};
                    o = r.oProtocolInc.startDownloadRecord(r, s)
                }
                return o
            }, this.I_GetDownloadStatus = function (e) {
                return l.HWP_GetDownloadStatus(e)
            }, this.I_GetDownloadProgress = function (e) {
                return l.HWP_GetDownloadProgress(e)
            }, this.I_StopDownloadRecord = function (e) {
                return l.HWP_StopDownload(e)
            }, this.I_ExportDeviceConfig = function (e) {
                var t = this.findDeviceIndexByIP(e);
                if (-1 != t) {
                    var n = u[t];
                    return n.oProtocolInc.exportDeviceConfig(n)
                }
            }, this.I_ImportDeviceConfig = function (e, t) {
                var n = this.findDeviceIndexByIP(e), a = -1;
                if (-1 != n) {
                    var o = u[n], r = {szFileName: t};
                    a = o.oProtocolInc.importDeviceConfig(o, r)
                }
                return a
            }, this.I_RestoreDefault = function (e, t, n) {
                var a = {success: null, error: null};
                P.extend(a, n);
                var o = this.findDeviceIndexByIP(e);
                if (-1 != o) {
                    var r = u[o];
                    r.oProtocolInc.restore(r, t, a)
                }
            }, this.I_Restart = function (e, t) {
                var n = this.findDeviceIndexByIP(e), a = {success: null, error: null};
                if (P.extend(a, t), -1 != n) {
                    var o = u[n];
                    o.oProtocolInc.restart(o, a)
                }
            }, this.I_Reconnect = function (e, t) {
                var n = this.findDeviceIndexByIP(e), a = {success: null, error: null};
                if (P.extend(a, t), -1 != n) {
                    var o = u[n];
                    o.oProtocolInc.login(o.szIP, o.iCGIPort, o.szAuth, a)
                }
            }, this.I_StartUpgrade = function (e, t) {
                var n = this.findDeviceIndexByIP(e), a = -1;
                if (-1 != n) {
                    var o = u[n], r = {szFileName: t};
                    a = o.oProtocolInc.startUpgrade(o, r)
                }
                return a
            }, this.I_UpgradeStatus = function () {
                return l.HWP_UpgradeStatus()
            }, this.I_UpgradeProgress = function () {
                return l.HWP_UpgradeProgress()
            }, this.I_StopUpgrade = function () {
                return l.HWP_StopUpgrade()
            }, this.I_CheckPluginInstall = function () {
                var e = !1;
                if (P.browser().msie)try {
                    new ActiveXObject("WebVideoActiveX.WebVideoActiveXCtrl.1"), e = !0
                } catch (t) {
                } else for (var n = 0, a = navigator.mimeTypes.length; a > n; n++)if ("application/hwp-webvideo-plugin" == navigator.mimeTypes[n].type.toLowerCase()) {
                    e = !0;
                    break
                }
                return e ? 0 : -1
            }, this.I_CheckPluginVersion = function () {
                return l.HWP_CheckPluginUpdate(q) ? -1 : 0
            }, this.I_RemoteConfig = function (e, t) {
                var n = this.findDeviceIndexByIP(e), a = -1, o = -1, r = {iLan: 0, iDevicePort: -1, iType: 0};
                if (P.extend(r, t), -1 != n) {
                    var s = u[n];
                    if (-1 == r.iDevicePort)if (-1 == s.iDevicePort) {
                        if (s.iDevicePort = V(s).iDevicePort, o = s.iDevicePort, -1 == o)return a
                    } else o = s.iDevicePort; else o = r.iDevicePort;
                    if (":" == P.Base64.decode(s.szAuth)[0])var i = P.Base64.decode(s.szAuth).split(":")[1], c = P.Base64.decode(s.szAuth).split(":")[2]; else var i = P.Base64.decode(s.szAuth).split(":")[0], c = P.Base64.decode(s.szAuth).split(":")[1];
                    var d = "<RemoteInfo><DeviceInfo><DeviceType>" + r.iType + "</DeviceType>" + "<LanType>" + r.iLan + "</LanType>" + "<IP>" + e + "</IP>" + "<Port>" + o + "</Port>" + "<UName>" + i + "</UName>" + "<PWD>" + P.Base64.encode(c) + "</PWD></DeviceInfo></RemoteInfo>";
                    return l.HWP_ShowRemConfig(d)
                }
                return a
            }, this.I_ChangeWndNum = function (e) {
                return isNaN(parseInt(e, 10)) ? -1 : (l.HWP_ArrangeWindow(e), void 0)
            }, this.I_GetLastError = function () {
                return l.HWP_GetLastError()
            }, this.I_GetWindowStatus = function (e) {
                if (G(e)) {
                    var t = [];
                    return P.extend(t, d), t
                }
                var n = this.findWndIndexByIndex(e);
                return -1 != n ? d[n] : null
            }, this.I_GetIPInfoByMode = function (e, t, n, a) {
                return l.HWP_GetIpInfoByMode(e, t, n, a)
            }, this.findDeviceIndexByIP = function (e) {
                for (var t = 0; u.length > t; t++)if (u[t].szIP == e)return t;
                return -1
            }, this.findWndIndexByIndex = function (e) {
                for (var t = 0; d.length > t; t++)if (d[t].iIndex == e)return t;
                return -1
            };
            var J = function () {
                this.szIP = "", this.szHostName = "", this.szAuth = "", this.szHttpProtocol = "http://", this.iCGIPort = 80, this.iDevicePort = -1, this.iHttpPort = -1, this.iHttpsPort = -1, this.iRtspPort = -1, this.iAudioType = 1, this.iDeviceProtocol = I, this.oProtocolInc = null, this.iAnalogChannelNum = 0, this.szDeviceType = "", this.bVoiceTalk = !1, this.oStreamCapa = {
                    bObtained: !1,
                    bSupportShttpPlay: !1,
                    bSupportShttpPlayback: !1,
                    bSupportShttpsPlay: !1,
                    bSupportShttpsPlayback: !1,
                    bSupportShttpPlaybackTransCode: !1,
                    bSupportShttpsPlaybackTransCode: !1,
                    iIpChanBase: 1
                }
            }, Q = function () {
                this.iIndex = 0, this.szIP = "", this.iChannelID = "", this.iPlayStatus = C, this.bSound = !1, this.bRecord = !1, this.bPTZAuto = !1, this.bEZoom = !1, this.b3DZoom = !1
            }, et = function () {
                this.options = {
                    type: "GET",
                    url: "",
                    auth: "",
                    timeout: 1e4,
                    data: "",
                    async: !0,
                    success: null,
                    error: null
                }, this.m_szHttpHead = "", this.m_szHttpContent = "", this.m_szHttpData = ""
            };
            et.prototype.m_httpRequestSet = [], et.prototype.setRequestParam = function (e) {
                P.extend(this.options, e)
            }, et.prototype.submitRequest = function () {
                var e = this.getHttpMethod(this.options.type), t = null;
                if (this.options.async) {
                    var n = l.HWP_SubmitHttpRequest(e, this.options.url, this.options.auth, this.options.data, this.options.timeout);
                    -1 != n && (t = {
                        iRequestID: n,
                        funSuccessCallback: this.options.success,
                        funErrorCallback: this.options.error
                    }, this.m_httpRequestSet.push(t))
                } else {
                    var a = l.HWP_SendHttpSynRequest(e, this.options.url, this.options.auth, this.options.data, this.options.timeout);
                    t = {
                        funSuccessCallback: this.options.success,
                        funErrorCallback: this.options.error
                    }, this.httpDataAnalyse(t, a)
                }
            }, et.prototype.getHttpMethod = function (e) {
                var t = {GET: 1, POST: 2, PUT: 5, DELETE: 6}, n = t[e];
                return n ? n : -1
            }, et.prototype.processCallback = function (e, t) {
                for (var n = null, a = 0; this.m_httpRequestSet.length > a; a++)if (e == this.m_httpRequestSet[a].iRequestID) {
                    n = this.m_httpRequestSet[a], this.m_httpRequestSet.splice(a, 1);
                    break
                }
                null != n && (this.httpDataAnalyse(n, t), delete n)
            }, et.prototype.httpDataAnalyse = function (e, t) {
                var n = "", a = 0;
                "" == t || G(t) ? e.funErrorCallback() : (a = parseInt(t.substring(0, 3)), n = t.substring(3, t.length), isNaN(a) ? e.funErrorCallback() : v == a ? e.funSuccessCallback(P.loadXML(n)) : e.funErrorCallback(a, P.loadXML(n)))
            };
            var tt = function () {
            };
            tt.prototype.CGI = {
                login: "%s%s:%s/ISAPI/Security/userCheck",
                getAudioInfo: "%s%s:%s/ISAPI/System/TwoWayAudio/channels",
                getDeviceInfo: "%s%s:%s/ISAPI/System/deviceInfo",
                getAnalogChannelInfo: "%s%s:%s/ISAPI/System/Video/inputs/channels",
                getDigitalChannel: "%s%s:%s/ISAPI/ContentMgmt/InputProxy/channels",
                getDigitalChannelInfo: "%s%s:%s/ISAPI/ContentMgmt/InputProxy/channels/status",
                getZeroChannelInfo: "%s%s:%s/ISAPI/ContentMgmt/ZeroVideo/channels",
                getStreamChannels: {
                    analog: "%s%s:%s/ISAPI/Streaming/channels",
                    digital: "%s%s:%s/ISAPI/ContentMgmt/StreamingProxy/channels"
                },
                getStreamDynChannels: "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/DynStreaming/channels",
                startRealPlay: {
                    channels: "%s%s:%s/PSIA/streaming/channels/%s",
                    zeroChannels: "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/ZeroStreaming/channels/%s"
                },
                startShttpRealPlay: {
                    channels: "%s%s:%s/SDK/play/%s/004",
                    zeroChannels: "%s%s:%s/SDK/play/100/004/ZeroStreaming"
                },
                startVoiceTalk: {
                    open: "%s%s:%s/ISAPI/System/TwoWayAudio/channels/%s/open",
                    close: "%s%s:%s/ISAPI/System/TwoWayAudio/channels/%s/close",
                    audioData: "%s%s:%s/ISAPI/System/TwoWayAudio/channels/%s/audioData"
                },
                ptzControl: {
                    analog: "%s%s:%s/ISAPI/PTZCtrl/channels/%s/continuous",
                    digital: "%s%s:%s/ISAPI/ContentMgmt/PTZCtrlProxy/channels/%s/continuous"
                },
                ptzAutoControl: {
                    ipdome: "%s%s:%s/ISAPI/PTZCtrl/channels/%s/presets/%s/goto",
                    analog: "%s%s:%s/ISAPI/PTZCtrl/channels/%s/autoPan",
                    digital: "%s%s:%s/ISAPI/ContentMgmt/PTZCtrlProxy/channels/%s/autoPan"
                },
                setPreset: {
                    analog: "%s%s:%s/ISAPI/PTZCtrl/channels/%s/presets/%s",
                    digital: "%s%s:%s/ISAPI/ContentMgmt/PTZCtrlProxy/channels/%s/presets/%s"
                },
                goPreset: {
                    analog: "%s%s:%s/ISAPI/PTZCtrl/channels/%s/presets/%s/goto",
                    digital: "%s%s:%s/ISAPI/ContentMgmt/PTZCtrlProxy/channels/%s/presets/%s/goto"
                },
                ptzFocus: {
                    analog: "%s%s:%s/ISAPI/Image/channels/%s/focus",
                    digital: "%s%s:%s/ISAPI/ContentMgmt/ImageProxy/channels/%s/focus",
                    ipc: "%s%s:%s/ISAPI/System/Video/inputs/channels/%s/focus"
                },
                ptzIris: {
                    analog: "%s%s:%s/ISAPI/Image/channels/%s/iris",
                    digital: "%s%s:%s/ISAPI/ContentMgmt/ImageProxy/channels/%s/iris",
                    ipc: "%s%s:%s/ISAPI/System/Video/inputs/channels/%s/iris"
                },
                getNetworkBond: "%s%s:%s/ISAPI/System/Network/Bond",
                getNetworkInterface: "%s%s:%s/ISAPI/System/Network/interfaces",
                getUPnPPortStatus: "%s%s:%s/ISAPI/System/Network/UPnP/ports/status",
                getPPPoEStatus: "%s%s:%s/ISAPI/System/Network/PPPoE/1/status",
                getPortInfo: "%s%s:%s/ISAPI/Security/adminAccesses",
                recordSearch: "%s%s:%s/ISAPI/ContentMgmt/search",
                startPlayback: "%s%s:%s/PSIA/streaming/tracks/%s?starttime=%s&endtime=%s",
                startShttpPlayback: "%s%s:%s/SDK/playback/%s",
                startShttpReversePlayback: "%s%s:%s/SDK/playback/%s/reversePlay",
                startTransCodePlayback: "%s%s:%s/SDK/playback/%s/transcoding",
                startDownloadRecord: "%s%s:%s/ISAPI/ContentMgmt/download",
                deviceConfig: "%s%s:%s/ISAPI/System/configurationData",
                restart: "%s%s:%s/ISAPI/System/reboot",
                restore: "%s%s:%s/ISAPI/System/factoryReset?mode=%s",
                startUpgrade: {
                    upgrade: "%s%s:%s/ISAPI/System/updateFirmware",
                    status: "%s%s:%s/ISAPI/System/upgradeStatus"
                },
                set3DZoom: "%s%s:%s/ISAPI/PTZCtrl/channels/%s/position3D",
                SDKCapabilities: "%s%s:%s/SDK/capabilities"
            }, tt.prototype.login = function (e, t, a, o) {
                var r = 2 == o.protocol ? "https://" : "http://", s = N(this.CGI.login, r, e, t), i = new et, l = {
                    type: "GET",
                    url: s,
                    auth: a,
                    success: null,
                    error: null
                };
                P.extend(l, o), P.extend(l, {
                    success: function (e) {
                        "200" == n.$XML(e).find("statusValue").eq(0).text() || "OK" == n.$XML(e).find("statusString").eq(0).text() ? o.success && o.success(e) : o.error && o.error(401, e)
                    }, error: function (e, t) {
                        o.error && o.error(e, t)
                    }
                }), i.setRequestParam(l), i.submitRequest()
            }, tt.prototype.getAudioInfo = function (e, t) {
                var n = N(this.CGI.getAudioInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, tt.prototype.getDeviceInfo = function (e, t) {
                var a = N(this.CGI.getDeviceInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), o = new et, r = {
                    type: "GET",
                    url: a,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(r, t), P.extend(r, {
                    success: function (e) {
                        var a = [];
                        a.push("<DeviceInfo>"), a.push("<deviceName>" + P.escape(n.$XML(e).find("deviceName").eq(0).text()) + "</deviceName>"), a.push("<deviceID>" + n.$XML(e).find("deviceID").eq(0).text() + "</deviceID>"), a.push("<deviceType>" + n.$XML(e).find("deviceType").eq(0).text() + "</deviceType>"), a.push("<model>" + n.$XML(e).find("model").eq(0).text() + "</model>"), a.push("<serialNumber>" + n.$XML(e).find("serialNumber").eq(0).text() + "</serialNumber>"), a.push("<macAddress>" + n.$XML(e).find("macAddress").eq(0).text() + "</macAddress>"), a.push("<firmwareVersion>" + n.$XML(e).find("firmwareVersion").eq(0).text() + "</firmwareVersion>"), a.push("<firmwareReleasedDate>" + n.$XML(e).find("firmwareReleasedDate").eq(0).text() + "</firmwareReleasedDate>"), a.push("<encoderVersion>" + n.$XML(e).find("encoderVersion").eq(0).text() + "</encoderVersion>"), a.push("<encoderReleasedDate>" + n.$XML(e).find("encoderReleasedDate").eq(0).text() + "</encoderReleasedDate>"), a.push("</DeviceInfo>"), e = P.loadXML(a.join("")), t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), o.setRequestParam(r), o.submitRequest()
            }, tt.prototype.getAnalogChannelInfo = function (e, t) {
                var a = N(this.CGI.getAnalogChannelInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), o = new et, r = {
                    type: "GET",
                    url: a,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(r, t), P.extend(r, {
                    success: function (e) {
                        var a = [];
                        a.push("<VideoInputChannelList>");
                        for (var o = n.$XML(e).find("VideoInputChannel", !0), r = 0, s = o.length; s > r; r++) {
                            var i = o[r];
                            a.push("<VideoInputChannel>"), a.push("<id>" + n.$XML(i).find("id").eq(0).text() + "</id>"), a.push("<inputPort>" + n.$XML(i).find("inputPort").eq(0).text() + "</inputPort>"), a.push("<name>" + P.escape(n.$XML(i).find("name").eq(0).text()) + "</name>"), a.push("<videoFormat>" + n.$XML(i).find("videoFormat").eq(0).text() + "</videoFormat>"), a.push("</VideoInputChannel>")
                        }
                        a.push("</VideoInputChannelList>"), e = P.loadXML(a.join("")), t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), o.setRequestParam(r), o.submitRequest()
            }, tt.prototype.getDigitalChannel = function (e, t) {
                var a = N(this.CGI.getDigitalChannel, e.szHttpProtocol, e.szIP, e.iCGIPort), o = new et, r = {
                    type: "GET",
                    url: a,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(r, t), P.extend(r, {
                    success: function (e) {
                        var a = [];
                        a.push("<InputProxyChannelList>");
                        for (var o = n.$XML(e).find("InputProxyChannel", !0), r = 0, s = o.length; s > r; r++) {
                            var i = o[r];
                            a.push("<InputProxyChannel>"), a.push("<id>" + n.$XML(i).find("id").eq(0).text() + "</id>"), a.push("<name>" + P.escape(n.$XML(i).find("name").eq(0).text()) + "</name>"), a.push("</InputProxyChannel>")
                        }
                        a.push("</InputProxyChannelList>"), e = P.loadXML(a.join("")), t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), o.setRequestParam(r), o.submitRequest()
            }, tt.prototype.getDigitalChannelInfo = function (e, t) {
                var a = null, o = {};
                if (this.getDigitalChannel(e, {
                        async: !1, success: function (e) {
                            a = e;
                            for (var t = n.$XML(a).find("InputProxyChannel", !0), r = 0, s = t.length; s > r; r++) {
                                var i = t[r], l = n.$XML(i).find("id").eq(0).text(), c = n.$XML(i).find("name").eq(0).text();
                                o[l] = c
                            }
                        }, error: function (e, n) {
                            t.error && t.error(e, n)
                        }
                    }), null !== a) {
                    var r = N(this.CGI.getDigitalChannelInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), s = new et, i = {
                        type: "GET",
                        url: r,
                        auth: e.szAuth,
                        success: null,
                        error: null
                    };
                    P.extend(i, t), P.extend(i, {
                        success: function (e) {
                            var a = [];
                            a.push("<InputProxyChannelStatusList>");
                            for (var r = n.$XML(e).find("InputProxyChannelStatus", !0), s = 0, i = r.length; i > s; s++) {
                                var l = r[s], c = n.$XML(l).find("id").eq(0).text();
                                a.push("<InputProxyChannelStatus>"), a.push("<id>" + c + "</id>"), a.push("<sourceInputPortDescriptor>"), a.push("<proxyProtocol>" + n.$XML(l).find("proxyProtocol").eq(0).text() + "</proxyProtocol>"), a.push("<addressingFormatType>" + n.$XML(l).find("addressingFormatType").eq(0).text() + "</addressingFormatType>"), a.push("<ipAddress>" + n.$XML(l).find("ipAddress").eq(0).text() + "</ipAddress>"), a.push("<managePortNo>" + n.$XML(l).find("managePortNo").eq(0).text() + "</managePortNo>"), a.push("<srcInputPort>" + n.$XML(l).find("srcInputPort").eq(0).text() + "</srcInputPort>"), a.push("<userName>" + P.escape(n.$XML(l).find("userName").eq(0).text()) + "</userName>"), a.push("<streamType>" + n.$XML(l).find("streamType").eq(0).text() + "</streamType>"), a.push("<online>" + n.$XML(l).find("online").eq(0).text() + "</online>"), a.push("<name>" + P.escape(o[c]) + "</name>"), a.push("</sourceInputPortDescriptor>"), a.push("</InputProxyChannelStatus>")
                            }
                            a.push("</InputProxyChannelStatusList>"), e = P.loadXML(a.join("")), t.success && t.success(e)
                        }, error: function (e, n) {
                            t.error && t.error(e, n)
                        }
                    }), s.setRequestParam(i), s.submitRequest()
                }
            }, tt.prototype.getZeroChannelInfo = function (e, t) {
                var n = N(this.CGI.getZeroChannelInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, tt.prototype.getStreamChannels = function (e, t) {
                if (0 != e.iAnalogChannelNum)var n = N(this.CGI.getStreamChannels.analog, e.szHttpProtocol, e.szIP, e.iCGIPort); else var n = N(this.CGI.getStreamChannels.digital, e.szHttpProtocol, e.szIP, e.iCGIPort);
                var a = new et, o = {type: "GET", url: n, auth: e.szAuth, success: null, error: null};
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, tt.prototype.getPPPoEStatus = function (e, t) {
                var n = N(this.CGI.getPPPoEStatus, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, tt.prototype.getUPnPPortStatus = function (e, t) {
                var n = N(this.CGI.getUPnPPortStatus, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, tt.prototype.getNetworkBond = function (e, t) {
                var n = N(this.CGI.getNetworkBond, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, tt.prototype.getNetworkInterface = function (e, t) {
                var n = N(this.CGI.getNetworkInterface, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, tt.prototype.getPortInfo = function (e, t) {
                var n = N(this.CGI.getPortInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, tt.prototype.startRealPlay = function (e, t) {
                var n = 100 * t.iChannelID + t.iStreamType, a = "";
                a = t.bZeroChannel ? N(t.cgi.zeroChannels, t.urlProtocol, e.szIP, t.iPort, n) : N(t.cgi.channels, t.urlProtocol, e.szIP, t.iPort, n);
                var o = l.HWP_Play(a, e.szAuth, t.iWndIndex, "", "");
                if (0 == o) {
                    var r = new Q;
                    r.iIndex = t.iWndIndex, r.szIP = e.szIP, r.iChannelID = t.iChannelID, r.iPlayStatus = S, d.push(r)
                }
                return o
            }, tt.prototype.startVoiceTalk = function (e, t) {
                var n = N(this.CGI.startVoiceTalk.open, e.szHttpProtocol, e.szIP, e.iCGIPort, t), a = N(this.CGI.startVoiceTalk.close, e.szHttpProtocol, e.szIP, e.iCGIPort, t), o = N(this.CGI.startVoiceTalk.audioData, e.szHttpProtocol, e.szIP, e.iCGIPort, t), r = l.HWP_StartVoiceTalk(n, a, o, e.szAuth, e.iAudioType);
                return r
            }, tt.prototype.ptzAutoControl = function (e, t, n, a) {
                var o = n.iChannelID, r = "", s = "";
                if (a.iPTZSpeed = 7 > a.iPTZSpeed ? 15 * a.iPTZSpeed : 100, t && (a.iPTZSpeed = 0), e.szDeviceType != W)r = e.iAnalogChannelNum >= o ? N(this.CGI.ptzAutoControl.analog, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID) : n.bShttpIPChannel ? N(this.CGI.ptzAutoControl.digital, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID - e.oStreamCapa.iIpChanBase + 1 + e.iAnalogChannelNum) : N(this.CGI.ptzAutoControl.digital, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID), s = "<?xml version='1.0' encoding='UTF-8'?><autoPanData><autoPan>" + a.iPTZSpeed + "</autoPan>" + "</autoPanData>"; else {
                    var i = 99;
                    t && (i = 96), r = N(this.CGI.ptzAutoControl.ipdome, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID, i)
                }
                var l = new et, c = {
                    type: "PUT",
                    url: r,
                    async: !1,
                    auth: e.szAuth,
                    data: s,
                    success: null,
                    error: null
                }, u = this;
                P.extend(c, a), P.extend(c, {
                    success: function (e) {
                        n.bPTZAuto = !n.bPTZAuto, a.success && a.success(e)
                    }, error: function (t, o) {
                        if (R == e.szDeviceType || H == e.szDeviceType) {
                            r = n.bShttpIPChannel ? N(u.CGI.ptzControl.analog, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID - e.oStreamCapa.iIpChanBase + 1 + e.iAnalogChannelNum) : N(u.CGI.ptzControl.analog, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID), s = "<?xml version='1.0' encoding='UTF-8'?><PTZData><pan>" + a.iPTZSpeed + "</pan>" + "<tilt>" + 0 + "</tilt>" + "</PTZData>";
                            var i = new et, l = {
                                type: "PUT",
                                url: r,
                                async: !1,
                                auth: e.szAuth,
                                data: s,
                                success: null,
                                error: null
                            };
                            P.extend(l, a), i.setRequestParam(l), i.submitRequest()
                        } else a.error && a.error(t, o)
                    }
                }), l.setRequestParam(c), l.submitRequest()
            }, tt.prototype.ptzControl = function (e, t, n, a) {
                var o = n.iChannelID, r = "";
                n.bPTZAuto && this.ptzAutoControl(e, !0, n, {iPTZSpeed: 0}), a.iPTZSpeed = t ? 0 : 7 > a.iPTZSpeed ? 15 * a.iPTZSpeed : 100;
                var s = [{}, {pan: 0, tilt: a.iPTZSpeed}, {pan: 0, tilt: -a.iPTZSpeed}, {
                    pan: -a.iPTZSpeed,
                    tilt: 0
                }, {pan: a.iPTZSpeed, tilt: 0}, {pan: -a.iPTZSpeed, tilt: a.iPTZSpeed}, {
                    pan: -a.iPTZSpeed,
                    tilt: -a.iPTZSpeed
                }, {pan: a.iPTZSpeed, tilt: a.iPTZSpeed}, {
                    pan: a.iPTZSpeed,
                    tilt: -a.iPTZSpeed
                }, {}, {speed: a.iPTZSpeed}, {speed: -a.iPTZSpeed}, {speed: a.iPTZSpeed}, {speed: -a.iPTZSpeed}, {speed: a.iPTZSpeed}, {speed: -a.iPTZSpeed}], i = "", l = {};
                switch (a.iPTZIndex) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        l = this.CGI.ptzControl, i = "<?xml version='1.0' encoding='UTF-8'?><PTZData><pan>" + s[a.iPTZIndex].pan + "</pan>" + "<tilt>" + s[a.iPTZIndex].tilt + "</tilt>" + "</PTZData>";
                        break;
                    case 10:
                    case 11:
                        l = this.CGI.ptzControl, i = "<?xml version='1.0' encoding='UTF-8'?><PTZData><zoom>" + s[a.iPTZIndex].speed + "</zoom>" + "</PTZData>";
                        break;
                    case 12:
                    case 13:
                        l = this.CGI.ptzFocus, i = "<?xml version='1.0' encoding='UTF-8'?><FocusData><focus>" + s[a.iPTZIndex].speed + "</focus>" + "</FocusData>";
                        break;
                    case 14:
                    case 15:
                        l = this.CGI.ptzIris, i = "<?xml version='1.0' encoding='UTF-8'?><IrisData><iris>" + s[a.iPTZIndex].speed + "</iris>" + "</IrisData>";
                        break;
                    default:
                        return G(a.error) && a.error(), void 0
                }
                r = l != this.CGI.ptzFocus && l != this.CGI.ptzIris || e.szDeviceType != R && e.szDeviceType != W && e.szDeviceType != H ? e.iAnalogChannelNum >= o ? N(l.analog, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID) : n.bShttpIPChannel ? N(l.digital, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID - e.oStreamCapa.iIpChanBase + 1 + e.iAnalogChannelNum) : N(l.digital, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID) : N(l.ipc, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID);
                var c = new et, u = {
                    type: "PUT",
                    url: r,
                    async: !1,
                    auth: e.szAuth,
                    data: i,
                    success: null,
                    error: null
                };
                P.extend(u, a), P.extend(u, {
                    success: function (e) {
                        a.success && a.success(e)
                    }, error: function (e, t) {
                        a.error && a.error(e, t)
                    }
                }), c.setRequestParam(u), c.submitRequest()
            }, tt.prototype.setPreset = function (e, t, n) {
                var a = t.iChannelID, o = "", r = "";
                o = e.iAnalogChannelNum >= a ? N(this.CGI.setPreset.analog, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID, n.iPresetID) : t.bShttpIPChannel ? N(this.CGI.setPreset.digital, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID - e.oStreamCapa.iIpChanBase + 1 + e.iAnalogChannelNum, n.iPresetID) : N(this.CGI.setPreset.digital, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID, n.iPresetID), r = "<?xml version='1.0' encoding='UTF-8'?>", r += "<PTZPreset>", r += "<id>" + n.iPresetID + "</id>", e.szDeviceType != W && (r += "<presetName>Preset" + n.iPresetID + "</presetName>"), r += "</PTZPreset>";
                var s = new et, i = {type: "PUT", url: o, auth: e.szAuth, data: r, success: null, error: null};
                P.extend(i, n), P.extend(i, {
                    success: function (e) {
                        n.success && n.success(e)
                    }, error: function (e, t) {
                        n.error && n.error(e, t)
                    }
                }), s.setRequestParam(i), s.submitRequest()
            }, tt.prototype.goPreset = function (e, t, n) {
                var a = t.iChannelID, o = "";
                o = e.iAnalogChannelNum >= a ? N(this.CGI.goPreset.analog, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID, n.iPresetID) : t.bShttpIPChannel ? N(this.CGI.goPreset.digital, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID - e.oStreamCapa.iIpChanBase + 1 + e.iAnalogChannelNum, n.iPresetID) : N(this.CGI.goPreset.digital, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID, n.iPresetID);
                var r = new et, s = {type: "PUT", url: o, auth: e.szAuth, success: null, error: null};
                P.extend(s, n), P.extend(s, {
                    success: function (e) {
                        n.success && n.success(e)
                    }, error: function (e, t) {
                        n.error && n.error(e, t)
                    }
                }), r.setRequestParam(s), r.submitRequest()
            }, tt.prototype.recordSearch = function (e, a) {
                var o = "", r = "", s = a.iChannelID, i = a.szStartTime.replace(" ", "T") + "Z", l = a.szEndTime.replace(" ", "T") + "Z";
                o = N(this.CGI.recordSearch, e.szHttpProtocol, e.szIP, e.iCGIPort), r = "<?xml version='1.0' encoding='UTF-8'?><CMSearchDescription><searchID>" + new t + "</searchID>" + "<trackList><trackID>" + (100 * s + 1) + "</trackID></trackList>" + "<timeSpanList>" + "<timeSpan>" + "<startTime>" + i + "</startTime>" + "<endTime>" + l + "</endTime>" + "</timeSpan>" + "</timeSpanList>" + "<maxResults>40</maxResults>" + "<searchResultPostion>" + a.iSearchPos + "</searchResultPostion>" + "<metadataList>" + "<metadataDescriptor>//metadata.ISAPI.org/VideoMotion</metadataDescriptor>" + "</metadataList>" + "</CMSearchDescription>";
                var c = new et, u = {type: "POST", url: o, auth: e.szAuth, data: r, success: null, error: null};
                P.extend(u, a), P.extend(u, {
                    success: function (e) {
                        var t = [];
                        t.push("<CMSearchResult>"), t.push("<responseStatus>" + n.$XML(e).find("responseStatus").eq(0).text() + "</responseStatus>"), t.push("<responseStatusStrg>" + n.$XML(e).find("responseStatusStrg").eq(0).text() + "</responseStatusStrg>"), t.push("<numOfMatches>" + n.$XML(e).find("numOfMatches").eq(0).text() + "</numOfMatches>"), t.push("<matchList>");
                        for (var o = n.$XML(e).find("searchMatchItem", !0), r = 0, s = o.length; s > r; r++) {
                            var i = o[r];
                            t.push("<searchMatchItem>"), t.push("<trackID>" + n.$XML(i).find("trackID").eq(0).text() + "</trackID>"), t.push("<startTime>" + n.$XML(i).find("startTime").eq(0).text() + "</startTime>"), t.push("<endTime>" + n.$XML(i).find("endTime").eq(0).text() + "</endTime>"), t.push("<playbackURI>" + P.escape(n.$XML(i).find("playbackURI").eq(0).text()) + "</playbackURI>"), t.push("<metadataDescriptor>" + n.$XML(i).find("metadataDescriptor").eq(0).text().split("/")[1] + "</metadataDescriptor>"), t.push("</searchMatchItem>")
                        }
                        t.push("</matchList>"), t.push("</CMSearchResult>"), e = P.loadXML(t.join("")), a.success && a.success(e)
                    }, error: function (e, t) {
                        a.error && a.error(e, t)
                    }
                }), c.setRequestParam(u), c.submitRequest()
            }, tt.prototype.startPlayback = function (e, t) {
                var n = t.iWndIndex, a = t.szStartTime, o = t.szEndTime, r = N(t.cgi, t.urlProtocol, e.szIP, t.iPort, t.iChannelID, a, o);
                if (!G(t.oTransCodeParam)) {
                    var s = j(t.oTransCodeParam);
                    if ("" == s)return -1;
                    l.HWP_SetTrsPlayBackParam(n, s)
                }
                var i = l.HWP_Play(r, e.szAuth, n, a, o);
                if (0 == i) {
                    var c = new Q;
                    c.iIndex = n, c.szIP = e.szIP, c.iChannelID = t.iChannelID, c.iPlayStatus = _, d.push(c)
                }
                return i
            }, tt.prototype.reversePlayback = function (e, t) {
                var n = t.iWndIndex, a = t.szStartTime, o = t.szEndTime, r = N(t.cgi, t.urlProtocol, e.szIP, t.iPort, t.iChannelID, a, o), s = l.HWP_ReversePlay(r, e.szAuth, n, a, o);
                if (0 == s) {
                    var i = new Q;
                    i.iIndex = n, i.szIP = e.szIP, i.iChannelID = t.iChannelID, i.iPlayStatus = T, d.push(i)
                }
                return s
            }, tt.prototype.startDownloadRecord = function (e, t) {
                var n = N(this.CGI.startDownloadRecord, e.szHttpProtocol, e.szIP, e.iCGIPort), a = "<?xml version='1.0' encoding='UTF-8'?><downloadRequest><playbackURI> " + P.escape(t.szPlaybackURI) + "</playbackURI>" + "</downloadRequest>";
                return l.HWP_StartDownload(n, e.szAuth, t.szFileName, a)
            }, tt.prototype.exportDeviceConfig = function (e) {
                var t = N(this.CGI.deviceConfig, e.szHttpProtocol, e.szIP, e.iCGIPort);
                return l.HWP_ExportDeviceConfig(t, e.szAuth, "", 0)
            }, tt.prototype.importDeviceConfig = function (e, t) {
                var n = N(this.CGI.deviceConfig, e.szHttpProtocol, e.szIP, e.iCGIPort);
                return l.HWP_ImportDeviceConfig(n, e.szAuth, t.szFileName, 0)
            }, tt.prototype.restart = function (e, t) {
                var n = N(this.CGI.restart, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "PUT",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, tt.prototype.restore = function (e, t, n) {
                var a = N(this.CGI.restore, e.szHttpProtocol, e.szIP, e.iCGIPort, t), o = new et, r = {
                    type: "PUT",
                    url: a,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(r, n), P.extend(r, {
                    success: function (e) {
                        n.success && n.success(e)
                    }, error: function (e, t) {
                        n.error && n.error(e, t)
                    }
                }), o.setRequestParam(r), o.submitRequest()
            }, tt.prototype.startUpgrade = function (e, t) {
                var n = N(this.CGI.startUpgrade.upgrade, e.szHttpProtocol, e.szIP, e.iCGIPort), a = N(this.CGI.startUpgrade.status, e.szHttpProtocol, e.szIP, e.iCGIPort);
                return l.HWP_StartUpgrade(n, a, e.szAuth, t.szFileName)
            }, tt.prototype.set3DZoom = function (e, t, a, o) {
                var r = t.iChannelID, s = "";
                s = e.iAnalogChannelNum >= r ? N(this.CGI.set3DZoom, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID) : t.bShttpIPChannel ? N(this.CGI.set3DZoom, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID - e.oStreamCapa.iIpChanBase + 1 + e.iAnalogChannelNum) : N(this.CGI.set3DZoom, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID);
                var i = P.loadXML(a), l = parseInt(n.$XML(i).find("StartPoint").eq(0).find("positionX").eq(0).text(), 10), c = parseInt(n.$XML(i).find("StartPoint").eq(0).find("positionY").eq(0).text(), 10), u = parseInt(n.$XML(i).find("EndPoint").eq(0).find("positionX").eq(0).text(), 10), d = parseInt(n.$XML(i).find("EndPoint").eq(0).find("positionY").eq(0).text(), 10), p = "<?xml version='1.0' encoding='UTF-8'?><position3D><StartPoint><positionX>" + l + "</positionX>" + "<positionY>" + (255 - c) + "</positionY>" + "</StartPoint>" + "<EndPoint>" + "<positionX>" + u + "</positionX>" + "<positionY>" + (255 - d) + "</positionY>" + "</EndPoint>" + "</position3D>", m = new et, h = {
                    type: "PUT",
                    url: s,
                    data: p,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(h, o), P.extend(h, {
                    success: function (e) {
                        o.success && o.success(e)
                    }, error: function (e, t) {
                        o.error && o.error(e, t)
                    }
                }), m.setRequestParam(h), m.submitRequest()
            }, tt.prototype.getSDKCapa = function (e, t) {
                var n = N(this.CGI.SDKCapabilities, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            };
            var nt = function () {
            };
            nt.prototype.CGI = {
                login: "%s%s:%s/PSIA/Custom/SelfExt/userCheck",
                getAudioInfo: "%s%s:%s/PSIA/Custom/SelfExt/TwoWayAudio/channels",
                getDeviceInfo: "%s%s:%s/PSIA/System/deviceInfo",
                getAnalogChannelInfo: "%s%s:%s/PSIA/System/Video/inputs/channels",
                getDigitalChannel: "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/DynVideo/inputs/channels",
                getDigitalChannelInfo: "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/DynVideo/inputs/channels/status",
                getZeroChannelInfo: "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/ZeroVideo/channels",
                getStreamChannels: {
                    analog: "%s%s:%s/PSIA/Streaming/channels",
                    digital: "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/DynStreaming/channels"
                },
                getStreamDynChannels: "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/DynStreaming/channels",
                startRealPlay: {
                    channels: "%s%s:%s/PSIA/streaming/channels/%s",
                    zeroChannels: "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/ZeroStreaming/channels/%s"
                },
                startVoiceTalk: {
                    open: "%s%s:%s/PSIA/Custom/SelfExt/TwoWayAudio/channels/%s/open",
                    close: "%s%s:%s/PSIA/Custom/SelfExt/TwoWayAudio/channels/%s/close",
                    audioData: "%s%s:%s/PSIA/Custom/SelfExt/TwoWayAudio/channels/%s/audioData"
                },
                ptzControl: "%s%s:%s/PSIA/PTZ/channels/%s/continuous",
                ptzAutoControl: "%s%s:%s/PSIA/Custom/SelfExt/PTZ/channels/%s/autoptz",
                setPreset: "%s%s:%s/PSIA/PTZ/channels/%s/presets/%s",
                goPreset: "%s%s:%s/PSIA/PTZ/channels/%s/presets/%s/goto",
                ptzFocus: "%s%s:%s/PSIA/System/Video/inputs/channels/%s/focus",
                ptzIris: "%s%s:%s/PSIA/System/Video/inputs/channels/%s/iris",
                getNetworkBond: "%s%s:%s/PSIA/Custom/SelfExt/Bond",
                getNetworkInterface: "%s%s:%s/PSIA/System/Network/interfaces",
                getUPnPPortStatus: "%s%s:%s/PSIA/Custom/SelfExt/UPnP/ports/status",
                getPPPoEStatus: "%s%s:%s/PSIA/Custom/SelfExt/PPPoE/1/status",
                getPortInfo: "%s%s:%s/PSIA/Security/AAA/adminAccesses",
                recordSearch: "%s%s:%s/PSIA/ContentMgmt/search",
                startPlayback: "%s%s:%s/PSIA/streaming/tracks/%s?starttime=%s&endtime=%s",
                startDownloadRecord: "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/download",
                deviceConfig: "%s%s:%s/PSIA/System/configurationData",
                restart: "%s%s:%s/PSIA/System/reboot",
                restore: "%s%s:%s/PSIA/System/factoryReset?mode=%s",
                startUpgrade: {
                    upgrade: "%s%s:%s/PSIA/System/updateFirmware",
                    status: "%s%s:%s/PSIA/Custom/SelfExt/upgradeStatus"
                },
                set3DZoom: "%s%s:%s/PSIA/Custom/SelfExt/PTZ/channels/%s/Set3DZoom"
            }, nt.prototype.login = function (e, t, a, o) {
                var r = 2 == o.protocol ? "https://" : "http://", s = N(this.CGI.login, r, e, t), i = new et, l = {
                    type: "GET",
                    url: s,
                    auth: a,
                    success: null,
                    error: null
                };
                P.extend(l, o), P.extend(l, {
                    success: function (e) {
                        "200" == n.$XML(e).find("statusValue").eq(0).text() ? o.success && o.success(e) : o.error && o.error(401, e)
                    }, error: function (e, t) {
                        o.error && o.error(e, t)
                    }
                }), i.setRequestParam(l), i.submitRequest()
            }, nt.prototype.getAudioInfo = function (e, t) {
                var n = N(this.CGI.getAudioInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, nt.prototype.getDeviceInfo = function (e, t) {
                var a = N(this.CGI.getDeviceInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), o = new et, r = {
                    type: "GET",
                    url: a,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(r, t), P.extend(r, {
                    success: function (e) {
                        var a = [];
                        a.push("<DeviceInfo>"), a.push("<deviceName>" + P.escape(n.$XML(e).find("deviceName").eq(0).text()) + "</deviceName>"), a.push("<deviceID>" + n.$XML(e).find("deviceID").eq(0).text() + "</deviceID>"), a.push("<deviceType>" + n.$XML(e).find("deviceDescription").eq(0).text() + "</deviceType>"), a.push("<model>" + n.$XML(e).find("model").eq(0).text() + "</model>"), a.push("<serialNumber>" + n.$XML(e).find("serialNumber").eq(0).text() + "</serialNumber>"), a.push("<macAddress>" + n.$XML(e).find("macAddress").eq(0).text() + "</macAddress>"), a.push("<firmwareVersion>" + n.$XML(e).find("firmwareVersion").eq(0).text() + "</firmwareVersion>"), a.push("<firmwareReleasedDate>" + n.$XML(e).find("firmwareReleasedDate").eq(0).text() + "</firmwareReleasedDate>"), a.push("<encoderVersion>" + n.$XML(e).find("logicVersion").eq(0).text() + "</encoderVersion>"), a.push("<encoderReleasedDate>" + n.$XML(e).find("logicReleasedDate").eq(0).text() + "</encoderReleasedDate>"), a.push("</DeviceInfo>"), e = P.loadXML(a.join("")), t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), o.setRequestParam(r), o.submitRequest()
            }, nt.prototype.getAnalogChannelInfo = function (e, t) {
                var a = N(this.CGI.getAnalogChannelInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), o = new et, r = {
                    type: "GET",
                    url: a,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(r, t), P.extend(r, {
                    success: function (e) {
                        var a = [];
                        a.push("<VideoInputChannelList>");
                        for (var o = n.$XML(e).find("VideoInputChannel", !0), r = 0, s = o.length; s > r; r++) {
                            var i = o[r];
                            a.push("<VideoInputChannel>"), a.push("<id>" + n.$XML(i).find("id").eq(0).text() + "</id>"), a.push("<inputPort>" + n.$XML(i).find("inputPort").eq(0).text() + "</inputPort>"), a.push("<name>" + P.escape(n.$XML(i).find("name").eq(0).text()) + "</name>"), a.push("<videoFormat>" + n.$XML(i).find("videoFormat").eq(0).text() + "</videoFormat>"), a.push("</VideoInputChannel>")
                        }
                        a.push("</VideoInputChannelList>"), e = P.loadXML(a.join("")), t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), o.setRequestParam(r), o.submitRequest()
            }, nt.prototype.getDigitalChannel = function (e, t) {
                var a = N(this.CGI.getDigitalChannel, e.szHttpProtocol, e.szIP, e.iCGIPort), o = new et, r = {
                    type: "GET",
                    url: a,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(r, t), P.extend(r, {
                    success: function (e) {
                        var a = [];
                        a.push("<InputProxyChannelList>");
                        for (var o = n.$XML(e).find("DynVideoInputChannel", !0), r = 0, s = o.length; s > r; r++) {
                            var i = o[r];
                            a.push("<InputProxyChannel>"), a.push("<id>" + n.$XML(i).find("id").eq(0).text() + "</id>"), a.push("<name>" + P.escape(n.$XML(i).find("name").eq(0).text()) + "</name>"), a.push("</InputProxyChannel>")
                        }
                        a.push("</InputProxyChannelList>"), e = P.loadXML(a.join("")), t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), o.setRequestParam(r), o.submitRequest()
            }, nt.prototype.getDigitalChannelInfo = function (e, t) {
                var a = null, o = {};
                if (this.getDigitalChannel(e, {
                        async: !1, success: function (e) {
                            a = e;
                            for (var t = n.$XML(a).find("InputProxyChannel", !0), r = 0, s = t.length; s > r; r++) {
                                var i = t[r], l = n.$XML(i).find("id").eq(0).text(), c = n.$XML(i).find("name").eq(0).text();
                                o[l] = c
                            }
                        }, error: function (e, n) {
                            t.error && t.error(e, n)
                        }
                    }), null !== a) {
                    var r = N(this.CGI.getDigitalChannelInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), s = new et, i = {
                        type: "GET",
                        url: r,
                        auth: e.szAuth,
                        success: null,
                        error: null
                    };
                    P.extend(i, t), P.extend(i, {
                        success: function (e) {
                            var a = [];
                            a.push("<InputProxyChannelStatusList>");
                            for (var r = n.$XML(e).find("DynVideoInputChannelStatus", !0), s = 0, i = r.length; i > s; s++) {
                                var l = r[s], c = n.$XML(l).find("id").eq(0).text();
                                a.push("<InputProxyChannelStatus>"), a.push("<id>" + c + "</id>"), a.push("<sourceInputPortDescriptor>"), a.push("<proxyProtocol>" + n.$XML(l).find("adminProtocol").eq(0).text() + "</proxyProtocol>"), a.push("<addressingFormatType>" + n.$XML(l).find("addressingFormatType").eq(0).text() + "</addressingFormatType>"), a.push("<ipAddress>" + n.$XML(l).find("ipAddress").eq(0).text() + "</ipAddress>"), a.push("<managePortNo>" + n.$XML(l).find("adminPortNo").eq(0).text() + "</managePortNo>"), a.push("<srcInputPort>" + n.$XML(l).find("srcInputPort").eq(0).text() + "</srcInputPort>"), a.push("<userName>" + P.escape(n.$XML(l).find("userName").eq(0).text()) + "</userName>"), a.push("<streamType>" + n.$XML(l).find("streamType").eq(0).text() + "</streamType>"), a.push("<online>" + n.$XML(l).find("online").eq(0).text() + "</online>"), a.push("<name>" + P.escape(o[c]) + "</name>"), a.push("</sourceInputPortDescriptor>"), a.push("</InputProxyChannelStatus>")
                            }
                            a.push("</InputProxyChannelStatusList>"), e = P.loadXML(a.join("")), t.success && t.success(e)
                        }, error: function (e, n) {
                            t.error && t.error(e, n)
                        }
                    }), s.setRequestParam(i), s.submitRequest()
                }
            }, nt.prototype.getZeroChannelInfo = function (e, t) {
                var n = N(this.CGI.getZeroChannelInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, nt.prototype.getPPPoEStatus = function (e, t) {
                var n = N(this.CGI.getPPPoEStatus, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, nt.prototype.getUPnPPortStatus = function (e, t) {
                var n = N(this.CGI.getUPnPPortStatus, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, nt.prototype.getNetworkBond = function (e, t) {
                var n = N(this.CGI.getNetworkBond, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, nt.prototype.getNetworkInterface = function (e, t) {
                var n = N(this.CGI.getNetworkInterface, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, nt.prototype.getPortInfo = function (e, t) {
                var a = N(this.CGI.getPortInfo, e.szHttpProtocol, e.szIP, e.iCGIPort), o = new et, r = {
                    type: "GET",
                    url: a,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(r, t), P.extend(r, {
                    success: function (a) {
                        var o = [];
                        o.push("<AdminAccessProtocolList>");
                        for (var r = n.$XML(a).find("AdminAccessProtocol", !0), s = 0, i = r.length; i > s; s++)r[s], o.push("<AdminAccessProtocol>"), o.push("<id>" + n.$XML(a).find("id").eq(0).text() + "</id>"), o.push("<enabled>" + n.$XML(a).find("enabled").eq(0).text() + "</enabled>"), o.push("<protocol>" + n.$XML(a).find("protocol").eq(0).text().toUpperCase() + "</protocol>"), o.push("<portNo>" + n.$XML(a).find("portNo").eq(0).text() + "</portNo>"), o.push("</AdminAccessProtocol>");
                        h.getStreamChannels(e, {
                            async: !1, success: function (a) {
                                if (n.$XML(a).find("rtspPortNo", !0).length > 0) {
                                    var r = parseInt(n.$XML(a).find("rtspPortNo").eq(0).text(), 10);
                                    o.push("<AdminAccessProtocol>"), o.push("<id>4</id>"), o.push("<enabled>true</enabled>"), o.push("<protocol>RTSP</protocol>"), o.push("<portNo>" + r + "</portNo>"), o.push("</AdminAccessProtocol>"), o.push("</AdminAccessProtocolList>");
                                    var s = P.loadXML(o.join(""));
                                    t.success && t.success(s)
                                } else h.getStreamDynChannels(e, {
                                    async: !1, success: function (e) {
                                        if (n.$XML(e).find("rtspPortNo", !0).length > 0) {
                                            var a = parseInt(n.$XML(e).find("rtspPortNo").eq(0).text(), 10);
                                            o.push("<AdminAccessProtocol>"), o.push("<id>4</id>"), o.push("<enabled>true</enabled>"), o.push("<protocol>RTSP</protocol>"), o.push("<portNo>" + a + "</portNo>"), o.push("</AdminAccessProtocol>"), o.push("</AdminAccessProtocolList>");
                                            var r = P.loadXML(o.join(""));
                                            t.success && t.success(r)
                                        }
                                    }, error: function () {
                                        t.error && t.error()
                                    }
                                })
                            }, error: function () {
                                t.error && t.error()
                            }
                        })
                    }, error: function () {
                        var a = [];
                        a.push("<AdminAccessProtocolList>"), h.getStreamChannels(e, {
                            async: !1, success: function (o) {
                                if (n.$XML(o).find("rtspPortNo", !0).length > 0) {
                                    var r = parseInt(n.$XML(o).find("rtspPortNo").eq(0).text(), 10);
                                    a.push("<AdminAccessProtocol>"), a.push("<id>4</id>"), a.push("<enabled>true</enabled>"), a.push("<protocol>RTSP</protocol>"), a.push("<portNo>" + r + "</portNo>"), a.push("</AdminAccessProtocol>"), a.push("</AdminAccessProtocolList>");
                                    var s = P.loadXML(a.join(""));
                                    t.success && t.success(s)
                                } else h.getStreamDynChannels(e, {
                                    async: !1, success: function (e) {
                                        if (n.$XML(e).find("rtspPortNo", !0).length > 0) {
                                            var o = parseInt(n.$XML(e).find("rtspPortNo").eq(0).text(), 10);
                                            a.push("<AdminAccessProtocol>"), a.push("<id>4</id>"), a.push("<enabled>true</enabled>"), a.push("<protocol>RTSP</protocol>"), a.push("<portNo>" + o + "</portNo>"), a.push("</AdminAccessProtocol>"), a.push("</AdminAccessProtocolList>");
                                            var r = P.loadXML(a.join(""));
                                            t.success && t.success(r)
                                        }
                                    }, error: function () {
                                        t.error && t.error()
                                    }
                                })
                            }, error: function () {
                                t.error && t.error()
                            }
                        })
                    }
                }), o.setRequestParam(r), o.submitRequest()
            }, nt.prototype.getStreamChannels = function (e, t) {
                if (0 != e.iAnalogChannelNum)var n = N(this.CGI.getStreamChannels.analog, e.szHttpProtocol, e.szIP, e.iCGIPort); else var n = N(this.CGI.getStreamChannels.digital, e.szHttpProtocol, e.szIP, e.iCGIPort);
                var a = new et, o = {type: "GET", url: n, auth: e.szAuth, success: null, error: null};
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, nt.prototype.getStreamDynChannels = function (e, t) {
                var n = N(this.CGI.getStreamDynChannels, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "GET",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, nt.prototype.startRealPlay = function (e, t) {
                var n = 100 * t.iChannelID + t.iStreamType, a = "";
                a = t.bZeroChannel ? N(t.cgi.zeroChannels, t.urlProtocol, e.szIP, t.iPort, n) : N(t.cgi.channels, t.urlProtocol, e.szIP, t.iPort, n);
                var o = l.HWP_Play(a, e.szAuth, t.iWndIndex, "", "");
                if (0 == o) {
                    var r = new Q;
                    r.iIndex = t.iWndIndex, r.szIP = e.szIP, r.iChannelID = t.iChannelID, r.iPlayStatus = S, d.push(r)
                }
                return o
            }, nt.prototype.startVoiceTalk = function (e, t) {
                var n = N(this.CGI.startVoiceTalk.open, e.szHttpProtocol, e.szIP, e.iCGIPort, t), a = N(this.CGI.startVoiceTalk.close, e.szHttpProtocol, e.szIP, e.iCGIPort, t), o = N(this.CGI.startVoiceTalk.audioData, e.szHttpProtocol, e.szIP, e.iCGIPort, t), r = l.HWP_StartVoiceTalk(n, a, o, e.szAuth, e.iAudioType);
                return r
            }, nt.prototype.ptzAutoControl = function (e, t, n, a) {
                var o = n.iChannelID, r = "", s = "";
                if (a.iPTZSpeed = 7 > a.iPTZSpeed ? 15 * a.iPTZSpeed : 100, t && (a.iPTZSpeed = 0), e.szDeviceType != W)r = N(this.CGI.ptzAutoControl, e.szHttpProtocol, e.szIP, e.iCGIPort, o), s = "<?xml version='1.0' encoding='UTF-8'?><PTZData><pan>" + a.iPTZSpeed + "</pan>" + "<tilt>0</tilt>" + "</PTZData>"; else {
                    var i = 99;
                    t && (i = 96), r = N(this.CGI.goPreset, e.szHttpProtocol, e.szIP, e.iCGIPort, o, i)
                }
                var l = new et, c = {
                    type: "PUT",
                    url: r,
                    async: !1,
                    auth: e.szAuth,
                    data: s,
                    success: null,
                    error: null
                }, u = this;
                P.extend(c, a), P.extend(c, {
                    success: function (e) {
                        n.bPTZAuto = !n.bPTZAuto, a.success && a.success(e)
                    }, error: function (t, o) {
                        if (e.szDeviceType != W) {
                            r = N(u.CGI.ptzControl, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID);
                            var i = new et, l = {
                                type: "PUT",
                                url: r,
                                async: !1,
                                auth: e.szAuth,
                                data: s,
                                success: null,
                                error: null
                            };
                            P.extend(l, a), i.setRequestParam(l), i.submitRequest()
                        } else a.error && a.error(t, o)
                    }
                }), l.setRequestParam(c), l.submitRequest()
            }, nt.prototype.ptzControl = function (e, t, n, a) {
                var o = (n.iChannelID, "");
                n.bPTZAuto && this.ptzAutoControl(e, !0, n, {iPTZSpeed: 0}), a.iPTZSpeed = t ? 0 : 7 > a.iPTZSpeed ? 15 * a.iPTZSpeed : 100;
                var r = [{}, {pan: 0, tilt: a.iPTZSpeed}, {pan: 0, tilt: -a.iPTZSpeed}, {
                    pan: -a.iPTZSpeed,
                    tilt: 0
                }, {pan: a.iPTZSpeed, tilt: 0}, {pan: -a.iPTZSpeed, tilt: a.iPTZSpeed}, {
                    pan: -a.iPTZSpeed,
                    tilt: -a.iPTZSpeed
                }, {pan: a.iPTZSpeed, tilt: a.iPTZSpeed}, {
                    pan: a.iPTZSpeed,
                    tilt: -a.iPTZSpeed
                }, {}, {speed: a.iPTZSpeed}, {speed: -a.iPTZSpeed}, {speed: a.iPTZSpeed}, {speed: -a.iPTZSpeed}, {speed: a.iPTZSpeed}, {speed: -a.iPTZSpeed}], s = "", i = {};
                switch (a.iPTZIndex) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        i = this.CGI.ptzControl, s = "<?xml version='1.0' encoding='UTF-8'?><PTZData><pan>" + r[a.iPTZIndex].pan + "</pan>" + "<tilt>" + r[a.iPTZIndex].tilt + "</tilt>" + "</PTZData>";
                        break;
                    case 10:
                    case 11:
                        i = this.CGI.ptzControl, s = "<?xml version='1.0' encoding='UTF-8'?><PTZData><zoom>" + r[a.iPTZIndex].speed + "</zoom>" + "</PTZData>";
                        break;
                    case 12:
                    case 13:
                        i = this.CGI.ptzFocus, s = "<?xml version='1.0' encoding='UTF-8'?><FocusData><focus>" + r[a.iPTZIndex].speed + "</focus>" + "</FocusData>";
                        break;
                    case 14:
                    case 15:
                        i = this.CGI.ptzIris, s = "<?xml version='1.0' encoding='UTF-8'?><IrisData><iris>" + r[a.iPTZIndex].speed + "</iris>" + "</IrisData>";
                        break;
                    default:
                        return G(a.error) && a.error(), void 0
                }
                o = N(i, e.szHttpProtocol, e.szIP, e.iCGIPort, n.iChannelID);
                var l = new et, c = {
                    type: "PUT",
                    url: o,
                    async: !1,
                    auth: e.szAuth,
                    data: s,
                    success: null,
                    error: null
                };
                P.extend(c, a), P.extend(c, {
                    success: function (e) {
                        a.success && a.success(e)
                    }, error: function (e, t) {
                        a.error && a.error(e, t)
                    }
                }), l.setRequestParam(c), l.submitRequest()
            }, nt.prototype.setPreset = function (e, t, n) {
                var a = (t.iChannelID, ""), o = "";
                a = N(this.CGI.setPreset, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID, n.iPresetID), o = "<?xml version='1.0' encoding='UTF-8'?>", o += "<PTZPreset>", o += "<id>" + n.iPresetID + "</id>", e.szDeviceType != W && (o += "<presetName>Preset" + n.iPresetID + "</presetName>"), o += "</PTZPreset>";
                var r = new et, s = {type: "PUT", url: a, auth: e.szAuth, data: o, success: null, error: null};
                P.extend(s, n), P.extend(s, {
                    success: function (e) {
                        n.success && n.success(e)
                    }, error: function (e, t) {
                        n.error && n.error(e, t)
                    }
                }), r.setRequestParam(s), r.submitRequest()
            }, nt.prototype.goPreset = function (e, t, n) {
                var a = (t.iChannelID, "");
                a = N(this.CGI.goPreset, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID, n.iPresetID);
                var o = new et, r = {type: "PUT", url: a, auth: e.szAuth, success: null, error: null};
                P.extend(r, n), P.extend(r, {
                    success: function (e) {
                        n.success && n.success(e)
                    }, error: function (e, t) {
                        n.error && n.error(e, t)
                    }
                }), o.setRequestParam(r), o.submitRequest()
            }, nt.prototype.recordSearch = function (e, a) {
                var o = "", r = "", s = a.iChannelID, i = a.szStartTime.replace(" ", "T") + "Z", l = a.szEndTime.replace(" ", "T") + "Z";
                o = N(this.CGI.recordSearch, e.szHttpProtocol, e.szIP, e.iCGIPort), r = "<?xml version='1.0' encoding='UTF-8'?><CMSearchDescription><searchID>" + new t + "</searchID>" + "<trackList><trackID>" + (100 * s + 1) + "</trackID></trackList>" + "<timeSpanList>" + "<timeSpan>" + "<startTime>" + i + "</startTime>" + "<endTime>" + l + "</endTime>" + "</timeSpan>" + "</timeSpanList>" + "<maxResults>40</maxResults>" + "<searchResultPostion>" + a.iSearchPos + "</searchResultPostion>" + "<metadataList>" + "<metadataDescriptor>//metadata.psia.org/VideoMotion</metadataDescriptor>" + "</metadataList>" + "</CMSearchDescription>";
                var c = new et, u = {type: "POST", url: o, auth: e.szAuth, data: r, success: null, error: null};
                P.extend(u, a), P.extend(u, {
                    success: function (e) {
                        var t = [];
                        t.push("<CMSearchResult>"), t.push("<responseStatus>" + n.$XML(e).find("responseStatus").eq(0).text() + "</responseStatus>"), t.push("<responseStatusStrg>" + n.$XML(e).find("responseStatusStrg").eq(0).text() + "</responseStatusStrg>"), t.push("<numOfMatches>" + n.$XML(e).find("numOfMatches").eq(0).text() + "</numOfMatches>"), t.push("<matchList>");
                        for (var o = n.$XML(e).find("searchMatchItem", !0), r = 0, s = o.length; s > r; r++) {
                            var i = o[r];
                            t.push("<searchMatchItem>"), t.push("<trackID>" + n.$XML(i).find("trackID").eq(0).text() + "</trackID>"), t.push("<startTime>" + n.$XML(i).find("startTime").eq(0).text() + "</startTime>"), t.push("<endTime>" + n.$XML(i).find("endTime").eq(0).text() + "</endTime>"), t.push("<playbackURI>" + P.escape(n.$XML(i).find("playbackURI").eq(0).text()) + "</playbackURI>"), t.push("<metadataDescriptor>" + n.$XML(i).find("metadataDescriptor").eq(0).text().split("/")[1] + "</metadataDescriptor>"), t.push("</searchMatchItem>")
                        }
                        t.push("</matchList>"), t.push("</CMSearchResult>"), e = P.loadXML(t.join("")), a.success && a.success(e)
                    }, error: function (e, t) {
                        a.error && a.error(e, t)
                    }
                }), c.setRequestParam(u), c.submitRequest()
            }, nt.prototype.startPlayback = function (e, t) {
                var n = t.iWndIndex, a = t.szStartTime, o = t.szEndTime, r = N(t.cgi, t.urlProtocol, e.szIP, t.iPort, t.iChannelID, a, o), s = l.HWP_Play(r, e.szAuth, n, a, o);
                if (0 == s) {
                    var i = new Q;
                    i.iIndex = n, i.szIP = e.szIP, i.iChannelID = t.iChannelID, i.iPlayStatus = _, d.push(i)
                }
                return s
            }, nt.prototype.reversePlayback = function (e, t) {
                var n = t.iWndIndex, a = t.szStartTime, o = t.szEndTime, r = N(t.cgi, t.urlProtocol, e.szIP, t.iPort, t.iChannelID, a, o), s = l.HWP_ReversePlay(r, e.szAuth, n, a, o);
                if (0 == s) {
                    var i = new Q;
                    i.iIndex = n, i.szIP = e.szIP, i.iChannelID = t.iChannelID, i.iPlayStatus = T, d.push(i)
                }
                return s
            }, nt.prototype.startDownloadRecord = function (e, t) {
                var n = N(this.CGI.startDownloadRecord, e.szHttpProtocol, e.szIP, e.iCGIPort), a = "<?xml version='1.0' encoding='UTF-8'?><downloadRequest><playbackURI> " + P.escape(t.szPlaybackURI) + "</playbackURI>" + "</downloadRequest>";
                return l.HWP_StartDownload(n, e.szAuth, t.szFileName, a)
            }, nt.prototype.exportDeviceConfig = function (e) {
                var t = N(this.CGI.deviceConfig, e.szHttpProtocol, e.szIP, e.iCGIPort);
                return l.HWP_ExportDeviceConfig(t, e.szAuth, "", 0)
            }, nt.prototype.importDeviceConfig = function (e, t) {
                var n = N(this.CGI.deviceConfig, e.szHttpProtocol, e.szIP, e.iCGIPort);
                return l.HWP_ImportDeviceConfig(n, e.szAuth, t.szFileName, 0)
            }, nt.prototype.restart = function (e, t) {
                var n = N(this.CGI.restart, e.szHttpProtocol, e.szIP, e.iCGIPort), a = new et, o = {
                    type: "PUT",
                    url: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(o, t), P.extend(o, {
                    success: function (e) {
                        t.success && t.success(e)
                    }, error: function (e, n) {
                        t.error && t.error(e, n)
                    }
                }), a.setRequestParam(o), a.submitRequest()
            }, nt.prototype.restore = function (e, t, n) {
                var a = N(this.CGI.restore, e.szHttpProtocol, e.szIP, e.iCGIPort, t), o = new et, r = {
                    type: "PUT",
                    url: a,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(r, n), P.extend(r, {
                    success: function (e) {
                        n.success && n.success(e)
                    }, error: function (e, t) {
                        n.error && n.error(e, t)
                    }
                }), o.setRequestParam(r), o.submitRequest()
            }, nt.prototype.startUpgrade = function (e, t) {
                var n = N(this.CGI.startUpgrade.upgrade, e.szHttpProtocol, e.szIP, e.iCGIPort), a = N(this.CGI.startUpgrade.status, e.szHttpProtocol, e.szIP, e.iCGIPort);
                return l.HWP_StartUpgrade(n, a, e.szAuth, t.szFileName)
            }, nt.prototype.set3DZoom = function (e, t, n, a) {
                var o = N(this.CGI.set3DZoom, e.szHttpProtocol, e.szIP, e.iCGIPort, t.iChannelID), r = new et, s = {
                    type: "PUT",
                    url: o,
                    data: n,
                    auth: e.szAuth,
                    success: null,
                    error: null
                };
                P.extend(s, a), P.extend(s, {
                    success: function (e) {
                        a.success && a.success(e)
                    }, error: function (e, t) {
                        a.error && a.error(e, t)
                    }
                }), r.setRequestParam(s), r.submitRequest()
            };
            var at = function () {
            };
            at.prototype._alert = function (e) {
                i.bDebugMode && console.log(e)
            }, function (t) {
                var n = function (e) {
                    this.elems = [], this.length = 0, this.length = this.elems.push(e)
                };
                n.prototype.find = function (e, t) {
                    var n = this.elems[this.length - 1].getElementsByTagName(e);
                    return this.length = this.elems.push(n), t ? n : this
                }, n.prototype.eq = function (e, t) {
                    var n = this.elems[this.length - 1].length, a = null;
                    return n > 0 && n > e && (a = this.elems[this.length - 1][e]), this.length = this.elems.push(a), t ? a : this
                }, n.prototype.text = function (t) {
                    return this.elems[this.length - 1] ? t ? (e.DOMParser ? this.elems[this.length - 1].textContent = t : this.elems[this.length - 1].text = t, void 0) : e.DOMParser ? this.elems[this.length - 1].textContent : this.elems[this.length - 1].text : ""
                }, n.prototype.attr = function (e) {
                    if (this.elems[this.length - 1]) {
                        var t = this.elems[this.length - 1].attributes.getNamedItem(e);
                        return t ? t.value : ""
                    }
                }, t.$XML = function (e) {
                    return new n(e)
                }
            }(this);
            var ot = function () {
            };
            ot.prototype.extend = function () {
                for (var e, t = arguments[0] || {}, n = 1, a = arguments.length; a > n; n++)if (null != (e = arguments[n]))for (var o in e) {
                    var r = (t[o], e[o]);
                    t !== r && ("object" == typeof r ? t[o] = this.extend({}, r) : void 0 !== r && (t[o] = r))
                }
                return t
            }, ot.prototype.browser = function () {
                var e = /(webkit)[ \/]([\w.]+)/, t = /(opera)(?:.*version)?[ \/]([\w.]+)/, n = /(msie) ([\w.]+)/, a = /(trident.*rv:)([\w.]+)/, o = /(mozilla)(?:.*? rv:([\w.]+))?/, r = navigator.userAgent.toLowerCase(), s = e.exec(r) || t.exec(r) || n.exec(r) || a.exec(r) || 0 > r.indexOf("compatible") && o.exec(r) || ["unknow", "0"];
                s.length > 0 && s[1].indexOf("trident") > -1 && (s[1] = "msie"), "webkit" == s[1] && (s[1] = r.indexOf("chrome") > -1 ? "chrome" : "safari");
                var i = {};
                return i[s[1]] = !0, i.version = s[2], i
            }, ot.prototype.loadXML = function (t) {
                if (null == t || "" == t)return null;
                var n = null;
                if (e.DOMParser) {
                    var a = new DOMParser;
                    n = a.parseFromString(t, "text/xml")
                } else n = new ActiveXObject("Microsoft.XMLDOM"), n.async = !1, n.loadXML(t);
                return n
            }, ot.prototype.toXMLStr = function (e) {
                var t = "";
                try {
                    var n = new XMLSerializer;
                    t = n.serializeToString(e)
                } catch (a) {
                    try {
                        t = e.xml
                    } catch (a) {
                        return ""
                    }
                }
                return -1 == t.indexOf("<?xml") && (t = "<?xml version='1.0' encoding='utf-8'?>" + t), t
            }, ot.prototype.escape = function (e) {
                return e.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
            }, ot.prototype.dateFormat = function (e, t) {
                var n = {
                    "M+": e.getMonth() + 1,
                    "d+": e.getDate(),
                    "h+": e.getHours(),
                    "m+": e.getMinutes(),
                    "s+": e.getSeconds(),
                    "q+": Math.floor((e.getMonth() + 3) / 3),
                    S: e.getMilliseconds()
                };
                /(y+)/.test(t) && (t = t.replace(RegExp.$1, (e.getFullYear() + "").substr(4 - RegExp.$1.length)));
                for (var a in n)RegExp("(" + a + ")").test(t) && (t = t.replace(RegExp.$1, 1 == RegExp.$1.length ? n[a] : ("00" + n[a]).substr(("" + n[a]).length)));
                return t
            }, ot.prototype.Base64 = {
                _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
                encode: function (e) {
                    var t, n, a, o, r, s, i, l = "", c = 0;
                    for (e = ot.prototype.Base64._utf8_encode(e); e.length > c;)t = e.charCodeAt(c++), n = e.charCodeAt(c++), a = e.charCodeAt(c++), o = t >> 2, r = (3 & t) << 4 | n >> 4, s = (15 & n) << 2 | a >> 6, i = 63 & a, isNaN(n) ? s = i = 64 : isNaN(a) && (i = 64), l = l + this._keyStr.charAt(o) + this._keyStr.charAt(r) + this._keyStr.charAt(s) + this._keyStr.charAt(i);
                    return l
                },
                decode: function (e) {
                    var t, n, a, o, r, s, i, l = "", c = 0;
                    for (e = e.replace(/[^A-Za-z0-9\+\/\=]/g, ""); e.length > c;)o = this._keyStr.indexOf(e.charAt(c++)), r = this._keyStr.indexOf(e.charAt(c++)), s = this._keyStr.indexOf(e.charAt(c++)), i = this._keyStr.indexOf(e.charAt(c++)), t = o << 2 | r >> 4, n = (15 & r) << 4 | s >> 2, a = (3 & s) << 6 | i, l += String.fromCharCode(t), 64 != s && (l += String.fromCharCode(n)), 64 != i && (l += String.fromCharCode(a));
                    return l = ot.prototype.Base64._utf8_decode(l)
                },
                _utf8_encode: function (e) {
                    e = e.replace(/\r\n/g, "\n");
                    for (var t = "", n = 0; e.length > n; n++) {
                        var a = e.charCodeAt(n);
                        128 > a ? t += String.fromCharCode(a) : a > 127 && 2048 > a ? (t += String.fromCharCode(192 | a >> 6), t += String.fromCharCode(128 | 63 & a)) : (t += String.fromCharCode(224 | a >> 12), t += String.fromCharCode(128 | 63 & a >> 6), t += String.fromCharCode(128 | 63 & a))
                    }
                    return t
                },
                _utf8_decode: function (e) {
                    for (var t = "", n = 0, a = c1 = c2 = 0; e.length > n;)a = e.charCodeAt(n), 128 > a ? (t += String.fromCharCode(a), n++) : a > 191 && 224 > a ? (c2 = e.charCodeAt(n + 1), t += String.fromCharCode((31 & a) << 6 | 63 & c2), n += 2) : (c2 = e.charCodeAt(n + 1), c3 = e.charCodeAt(n + 2), t += String.fromCharCode((15 & a) << 12 | (63 & c2) << 6 | 63 & c3), n += 3);
                    return t
                }
            }, t.prototype.valueOf = function () {
                return this.id
            }, t.prototype.toString = function () {
                return this.id
            }, t.prototype.createUUID = function () {
                var e = new Date(1582, 10, 15, 0, 0, 0, 0), n = new Date, a = n.getTime() - e.getTime(), o = "-", r = t.getIntegerBits(a, 0, 31), s = t.getIntegerBits(a, 32, 47), i = t.getIntegerBits(a, 48, 59) + "1", l = t.getIntegerBits(t.rand(4095), 0, 7), c = t.getIntegerBits(t.rand(4095), 0, 7), u = t.getIntegerBits(t.rand(8191), 0, 7) + t.getIntegerBits(t.rand(8191), 8, 15) + t.getIntegerBits(t.rand(8191), 0, 7) + t.getIntegerBits(t.rand(8191), 8, 15) + t.getIntegerBits(t.rand(8191), 0, 15);
                return r + o + s + o + i + o + l + c + o + u
            }, t.getIntegerBits = function (e, n, a) {
                var o = t.returnBase(e, 16), r = [], s = "", i = 0;
                for (i = 0; o.length > i; i++)r.push(o.substring(i, i + 1));
                for (i = Math.floor(n / 4); Math.floor(a / 4) >= i; i++)s += r[i] && "" != r[i] ? r[i] : "0";
                return s
            }, t.returnBase = function (e, t) {
                var n = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
                if (t > e)var a = n[e]; else {
                    var o = "" + Math.floor(e / t), r = e - o * t;
                    if (o >= t)var a = this.returnBase(o, t) + n[r]; else var a = n[o] + n[r]
                }
                return a
            }, t.rand = function (e) {
                return Math.floor(Math.random() * e)
            }, m = new tt, h = new nt, p = new at, P = new ot;
            var rt = P.dateFormat(new Date, "yyyyMMddhhmmss");
            if (r = "webVideoCtrl" + rt, s = "webVideoCtrl" + rt, "object" != typeof e.attachEvent && P.browser().msie) {
                var st = "<script for=" + r + " event='GetSelectWndInfo(SelectWndInfo)'>GetSelectWndInfo(SelectWndInfo);</script>";
                st += "<script for=" + r + " event='ZoomInfoCallback(szZoomInfo)'>ZoomInfoCallback(szZoomInfo);</script>", st += "<script for=" + r + "  event='GetHttpInfo(lID, lpInfo, lReverse)'>GetHttpInfo(lID, lpInfo, lReverse);</script>", st += "<script for=" + r + "  event='PluginEventHandler(iEventType, iParam1, iParam2)'>PluginEventHandler(iEventType, iParam1, iParam2);</script>", document.write(st)
            }
            return this
        }(), n = e.WebVideoCtrl = t;
        n.version = "1.0.51"
    }
})(this);
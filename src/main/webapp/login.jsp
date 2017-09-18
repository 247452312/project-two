<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>百莲达管理系统</title>
    <link href="css/base.css" rel="stylesheet">
    <link href="css/login/login.css" rel="stylesheet">
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <script type="text/javascript" src="js/basic.js"></script>
    <link href="css/base.css" rel="stylesheet">
    <script type="text/javascript" src="../custom/jquery.min.js"></script>
</head>
<body>
<div class="login-hd">
    <div class="hd-inner">
        <img src="images/bailianda.png" alt="logo"
             style="vertical-align:top;margin-top: 20px;margin-left: 20px;">
    </div>
</div>
<div class="login-bd">
    <div class="bd-inner">
        <div class="inner-wrap">
            <div class="lg-zone">
                <div class="lg-box">
                    <div class="lg-label"><h4>用户登录</h4></div>
                    <div class="alert alert-error" style="display: none">
                        <i class="iconfont">&#xe62e;</i>
                        <span></span>
                    </div>
                    <form action="User/login" method="post" class="form">
                        <div class="lg-username input-item clearfix">
                            <i class="iconfont">&#xe60d;</i>
                            <input type="text" name="name" placeholder="请输入账号" value="${cookie.username.value}">
                        </div>
                        <div class="lg-password input-item clearfix">
                            <i class="iconfont">&#xe634;</i>
                            <input type="password" name="pass" placeholder="请输入密码" value="${cookie.userpass.value}">
                        </div>
                        <div class="lg-check clearfix">
                            <div class="input-item">
                                <i class="iconfont">&#xe633;</i>
                                <input type="text" name="code" placeholder="请输入验证码">
                            </div>
                            <span class="check-code">
									<img src="/code" alt="点击刷新" onclick="codeReload($(this));"
                                         style="width: 100%;height: 100%;">
								</span>
                        </div>
                        <input type="hidden" name="box" value="1">
                        <div class="tips clearfix">
                            <label><input type="checkbox" checked="checked" onclick="rebox($(this));">记住用户名</label>
                        </div>
                        <div class="enter">
                            <a href="javascript:;" class="purchaser"
                               onclick="login($('.form'));" style="width: 296px;">登录</a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="lg-poster"></div>
        </div>
    </div>
</div>
<div class="login-ft">
    <div class="ft-inner">
        <div class="about-us">
            <a href="javascript:;">关于我们</a>
            <a href="javascript:;">法律声明</a>
            <a href="javascript:;">服务条款</a>
            <a href="javascript:;">联系方式</a>
        </div>
        <div class="address">地址：xxxxxxxxxxxxxxxxxxx&nbsp;邮编：xxxxxx&nbsp;&nbsp;Copyright&nbsp;©&nbsp;xxxx&nbsp;-&nbsp;xxxx&nbsp;xxxxxxxxxxxxx&nbsp;版权所有</div>
        <div class="other-info">建议使用IE8及以上版本浏览器&nbsp;苏ICP备&nbsp;xxxxxxxx号&nbsp;E-mail：xxxxx@xxx.com</div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    var a = 0;

    function codeReload(img) {
        var src = img.attr("src");
        img.attr("src", src + "?a=" + (a++));
    }

    function rebox(box) {
        inbox = $("[name=box]");
        if (box.prop("checked")) inbox.val(1);
        else inbox.val(0);
    }

    function login(form) {
        if ($("[name=name]").val().length == 0) showErr("请填入用户名");
        else if ($("[name=pass]").val().length == 0) showErr("请填入密码");
        else if ($("[name=code]").val().length == 0) showErr("请填入验证码");
        else {
            var action = form.attr("action");
            var method = form.attr("method");
            var data = form.serializeArray();
            $.ajax({
                url: action, type: method, dataType: "json", data: data,
                success: function (json) {
                    if (json.status != 1) {
                        showErr(json.info);
                    }
                    else {
                        location.href = "/index.jsp";
                    }
                }
            });
        }
    }

    function showErr(text) {
        var err = $(".alert-error");
        err.removeAttr("style");
        err.find("span").html(text);
    }
</script>

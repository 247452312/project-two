<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改密码</title>
    <script type="text/javascript" src="/custom/jquery.min.js"></script>
    <script type="text/javascript" src="/js/layer/layer.js"></script>
    <script type="text/javascript" src="/js/basic.js"></script>
    <script type="text/javascript" src="/js/Calendar3.js"></script>
    <script type="text/javascript" src="/js/selfFunction.js"></script>
    <link rel="stylesheet" type="text/css" href="/js/layer/layui.css">
    <link rel="stylesheet" type="text/css" href="/css/lib/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/addpage/addpage.css"/>
    <link rel="stylesheet" type="text/css" href="/css/addpage/font.css"/>
</head>
<body>
<form class="form" action="/User/updatePass" method="post">
    <%--<input type="hidden" name="id" value="${sessionScope.user.id}">--%>
    <div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">原始密码</label>
            <input type="password" name="oldPass"
                   lay-verify="title" autocomplete="off" placeholder="原始密码" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">新设密码</label>
            <input type="password" name="newPass"
                   lay-verify="title" autocomplete="off" placeholder="新设密码" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">确认密码</label>
            <input type="password" name="newPassAgain"
                   lay-verify="title" autocomplete="off" placeholder="确认密码" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
    </div>
    <div class="float-clear"></div>
    <div class="div-part div-buttons">
        <button type="button" class="layui-btn submit-btn float-left" onclick="save($('.form'));">提交</button>
        <button type="reset" class="layui-btn reset-btn float-right">重置</button>
        <div class="float-clear"></div>
    </div>
</form>
<%--函数部分--%>
<script type="text/javascript">
    //提交表单
    function save(form) {
        if ($("[name=oldPass]").val().length == 0)
            showMsg("请填入原始密码");
        else if ($("[name=newPass]").val().length == 0)
            showMsg("请填入新设密码");
        else if ($("[name=newPassAgain]").val().length == 0)
            showMsg("请填入确认密码");
        else if($("[name=newPass]").val()!=$("[name=newPassAgain]").val())
            showMsg("新设密码和确认密码不同");
        else {
            var action = form.attr("action");
            var method = form.attr("method");
            var data = form.serializeArray();
            $.ajax({
                url: action, type: method, dataType: "json", data: data,
                success: function (json) {
                    if (json.status == 1) closePage();
                    else showMsg(json.info);
                }
            });
        }
    }
</script>
</body>
</html>

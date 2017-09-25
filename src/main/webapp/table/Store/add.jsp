<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员类型信息修改</title>
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
<form class="form" action="/Store/ajax" method="post">
    <input type="hidden" name="cmd" value="${requestScope.cmd}">
    <input type="hidden" name="id" value="${requestScope.object.id}">
    <input type="hidden" name="productid" value="${requestScope.product.id}">
    <input type="hidden" name="shopid" value="${requestScope.shopid}">
    <input type="hidden" name="defprice" value="${requestScope.object.defprice}">
    <div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">商品名称</label>
            <input type="text" value="${requestScope.product.name}" disabled="disabled"
                   lay-verify="title" autocomplete="off" placeholder="商品名称" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">商品数量</label>
            <input type="number" name="count" value="${requestScope.object.count}" min="0"
                   lay-verify="title" autocomplete="off" placeholder="商品数量" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">商品成本</label>
            <input type="number" name="cbprice" value="${requestScope.object.cbprice}" min="0"
                   lay-verify="title" autocomplete="off" placeholder="商品成本" class="input form-name">
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
        if ($("[name=count]").val().length == 0)
            showMsg("请填入商品数量");
        else if ($("[name=cbprice]").val().length == 0)
            showMsg("请填入商品成本");
        else {
            $("[name=productid]").attr("name","productid.id");
            $("[name=shopid]").attr("name","shopid.id");
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

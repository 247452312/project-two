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
<form class="form" action="add.jsp" method="post">
    <div class="float-left" style="width: 30%;position: relative;top: 40px;">
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">商品名称</label>
            <select class="select" name="productid">
                <c:forEach items="${paramValues.proid}" var="ids" varStatus="nu">
                    <option value="${ids}">${paramValues.proname[nu.index]}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="float-left" style="width: 70%;position: relative;top: 40px;">
        <div class="div-part">
            <label class="label">商品数量</label>
            <input type="number" name="count"
                   lay-verify="title" autocomplete="off"
                   placeholder="请输入商品数" class="input form-name"
                   min="1">
        </div>
    </div>
    <div class="float-clear"></div>
    <div class="div-part div-buttons" style="position: relative;top: 100px;">
        <button type="button" class="layui-btn submit-btn float-left" onclick="save($('.form'));">提交</button>
        <button type="reset" class="layui-btn reset-btn float-right">重置</button>
        <div class="float-clear"></div>
    </div>
</form>
<%--函数部分--%>
<script type="text/javascript">
    //提交表单
    function save(form) {
        var proid = $("[name=productid]").val();
        var count = $("[name=count]").val();
        $.getJSON("/Product/selectById", {"id": proid}, function (pro) {
            var action = form.attr("action");
            var method = form.attr("method");
            var data = form.serializeObject();
            data.productid = pro;
            data.count = count ? count : 1;
            data.price = pro.defaultprice;
            data.amount = data.count * data.price;
            data.point = data.count * pro.defaultpoint;
            window.parent.details.push(data);
            closePage();
        });

    }
</script>
</body>
</html>

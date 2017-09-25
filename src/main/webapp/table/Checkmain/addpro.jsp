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
    <div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">商品名称</label>
            <select class="select" name="productid">
                <c:forEach items="${paramValues.proid}" var="ids" varStatus="nu">
                    <option value="${ids}">${paramValues.proname[nu.index]}</option>
                </c:forEach>
            </select>
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">商品数量</label>
            <input type="number" name="count" value="0" min="0"
                   lay-verify="title" autocomplete="off" placeholder="商品数量" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">盘点备注</label>
            <input type="text" name="fexp" min="0"
                   lay-verify="title" autocomplete="off" placeholder="盘点备注" class="input form-name">
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
        else {
            var proid=$("[name=productid]").val();
            $.getJSON("/Product/selectById?id="+proid,function (pro) {
                var action = form.attr("action");
                var method = form.attr("method");
                var data = form.serializeObject();
                data.productid=pro;
                window.parent.details.push(data);
                closePage();
            });
        }
    }
</script>
</body>
</html>

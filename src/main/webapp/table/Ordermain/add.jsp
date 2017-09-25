<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>供应商信息修改</title>
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
<form class="form" action="/Client/ajax" method="post">
    <input type="hidden" name="ordertype"
    value="${param.ordertype==null?(requestScope.object.ordertype==null?9:requestScope.object.ordertype):param.ordertype}">
    <input type="hidden" name="cmd" value="${requestScope.cmd}">
    <input type="hidden" name="id" value="${requestScope.object.id}">
    <div class="float-left" style="width: 50%;">
        <%--<!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">供应商编号</label>
            <input type="number" name="code" value="${requestScope.object.code}"
                   lay-verify="title" autocomplete="off" placeholder="请输入供应商编号" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">供应商姓名</label>
            <input type="text" name="name" value="${requestScope.object.name}"
                   lay-verify="title" autocomplete="off" placeholder="请输入供应商姓名" class="input form-name">
        </div>
        <!--------------------------------------------------------------->--%>
    </div>
    <div class="float-right" style="width: 50%;">
        <%--<!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">银行账号</label>
            <input type="text" name="bank" value="${requestScope.object.bank}"
                   lay-verify="title" autocomplete="off" placeholder="" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label float-left">信息备注</label>
            <textarea name="fexp" lay-verify="content" id="LAY_demo_editor"
                      class="textarea float-left form-info">${requestScope.object.fexp}</textarea>
            <div class="float-clear"></div>
        </div>
        <!--------------------------------------------------------------->--%>
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
    //获得下拉列表
    function addSel(input, attr) {
        var opts = input.input;
        var sel = $("[name=" + attr + "]").empty();
        for (var name in opts) {
            var opt = $("<option></option>");
            opt.val(name);
            opt.html(opts[name]);
            if (sel.siblings(".inpval").val() == name)
                opt.attr("selected", "selected");
            sel.append(opt);
        }
    }

    //提交表单
    function save(form) {
        if ($("[name=code]").val().length == 0)
            showMsg("请填入供应商编号");
        else if ($("[name=name]").val().length == 0)
            showMsg("请填入供应商姓名");
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

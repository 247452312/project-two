<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="../../">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员类型信息修改</title>
    <script type="text/javascript" src="custom/jquery.min.js"></script>
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <script type="text/javascript" src="js/basic.js"></script>
    <script type="text/javascript" src="js/Calendar3.js"></script>
    <script type="text/javascript" src="js/selfFunction.js"></script>
    <link rel="stylesheet" type="text/css" href="js/layer/layui.css">
    <link rel="stylesheet" type="text/css" href="css/lib/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="css/addpage/addpage.css" />
    <link rel="stylesheet" type="text/css" href="css/addpage/font.css" />
</head>
<body>
    <form class="form" action="Viptype/ajax" method="post">
        <input type="hidden" name="cmd" value="${requestScope.cmd}">
        <input type="hidden" name="id" value="${requestScope.object.id}">
        <div class="float-left" style="width: 50%;">
            <!--------------------------------------------------------------->
            <div class="div-part">
                <label class="label">会员类型名称</label>
                <input type="text" name="name" value="${requestScope.object.name}"
                       lay-verify="title" autocomplete="off" placeholder="请输入会员类型名称" class="input form-name">
            </div>
            <!--------------------------------------------------------------->
            <div class="div-part">
                <label class="label float-left">会员信息备注</label>
                <textarea name="fexp" lay-verify="content" id="LAY_demo_editor"
                          class="textarea float-left form-info">${requestScope.object.fexp}</textarea>
                <div class="float-clear"></div>
            </div>
            <!--------------------------------------------------------------->
        </div>
        <div class="float-clear"></div>
        <div class="div-part div-buttons">
            <button type="button" class="layui-btn submit-btn float-left" onclick="save($('.form'));">提交</button>
            <button type="reset"  class="layui-btn reset-btn float-right">重置</button>
            <div class="float-clear"></div>
        </div>
    </form>
    <%--函数部分--%>
    <script type="text/javascript">
        //提交表单
        function save(form) {
            if($("[name=name]").val().length==0)
                showMsg("请填入会员类型名称");
            else{
                var action=form.attr("action");
                var method=form.attr("method");
                var data=form.serializeArray();
                $.ajax({url:action,type:method,dataType:"json",data:data,
                    success:function (json) {
                        if(json.status==-1) showMsg(json.info);
                        else closePage();
                    }
                });
            }
        }
    </script>
</body>
</html>

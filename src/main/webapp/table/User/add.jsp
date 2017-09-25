<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>操作员信息修改</title>
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
<form class="form" action="/User/ajax" method="post">
    <input type="hidden" name="cmd" value="${requestScope.cmd}">
    <input type="hidden" name="id" value="${requestScope.object.id}">
    <input type="hidden" name="fright" value="${requestScope.object.fright}">
    <div class="float-left" style="width: 33%;">
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">人员姓名</label>
            <input type="text" name="name" value="${requestScope.object.name}"
                   lay-verify="title" autocomplete="off" placeholder="请输入操作员姓名" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label font-gray float-left">人员性别</label>
            <input type="hidden" class="inpval" value="${requestScope.object.sex}">
            <select class="select" name="sex"></select>
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">人员编号</label>
            <input type="text" name="code" value="${requestScope.object.code}"
                   lay-verify="title" autocomplete="off" placeholder="请输入操作员编号" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label font-gray float-left">负责分店</label>
            <input type="hidden" class="inpval" value="${requestScope.object.shopid.id}">
            <select class="select" name="shopid"></select>
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label font-gray float-left">人员类型</label>
            <input type="hidden" class="inpval" value="${requestScope.object.status}">
            <select class="select" name="status"></select>
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">身份证号</label>
            <input type="text" name="ccode" value="${requestScope.object.ccode}"
                   lay-verify="title" autocomplete="off" placeholder="请输入身份证号" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">固定电话</label>
            <input type="text" name="telmov" value="${requestScope.object.telmov}"
                   lay-verify="title" autocomplete="off" placeholder="请输入8位固定电话" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">移动电话</label>
            <input type="text" name="tel" value="${requestScope.object.tel}"
                   lay-verify="title" autocomplete="off" placeholder="请输入11位手机号" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
    </div>
    <div class="float-left" style="width: 33%;">
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">出生日期</label>
            <input type="" name="birthdate" value="${requestScope.object.birthdate}"
                   lay-verify="title" autocomplete="off" placeholder="" class="input form-name"
                   size="10" maxlength="10"
                   onclick="new Calendar().show(this);" readonly="readonly">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">就职时间</label>
            <input type="" name="enterdate" value="${requestScope.object.enterdate}"
                   lay-verify="title" autocomplete="off" placeholder="" class="input form-name"
                   size="10" maxlength="10"
                   onclick="new Calendar().show(this);" readonly="readonly">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">离职时间</label>
            <input type="" name="exitdate" value="${requestScope.object.exitdate}"
                   lay-verify="title" autocomplete="off" placeholder="" class="input form-name"
                   size="10" maxlength="10"
                   onclick="new Calendar().show(this);" readonly="readonly">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label">所在地址</label>
            <input type="text" name="addr" value="${requestScope.object.addr}"
                   lay-verify="title" autocomplete="off" placeholder="请输入地址" class="input form-name">
        </div>
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label float-left">信息备注</label>
            <textarea name="fexp" lay-verify="content" id="LAY_demo_editor"
                      class="textarea float-left form-info">${requestScope.object.fexp}</textarea>
            <div class="float-clear"></div>
        </div>
        <!--------------------------------------------------------------->
    </div>
    <div class="float-left" style="width: 34%;">
        <!--------------------------------------------------------------->
        <div class="div-part">
            <label class="label float-left">权限选择</label>
            <div class="boxs float-left"
                 style="width: 75%;border: 1px solid rgb(180, 180, 180);
                 margin-left:4px;max-height: 300px;
                 overflow: auto;padding-left: 10px;">
            </div>
            <div class="float-clear"></div>
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
    $(function () {
        $.getJSON("/User/getStatus", function (json) {
            var userSels = json.userInput;
            addSel(userSels[2], "sex");
            addSel(userSels[13], "shopid");
            addSel(userSels[8],"status");
            $("[name=shopid]").attr("name", "shopid.id");
            var boxs=$(".boxs");
            var boxarray=json.powerArray;
            var fright=$("[name=fright]").val();
            for (var i = 0; i < boxarray.length; i++) {
                var box = boxarray[i];
                var label=$("<label></label>").attr("for","boxid"+i);
                var input=$("<input/>").attr("type","checkbox").attr("id","boxid"+i).attr("name","powerlist");
                if(fright.substring(i,i+1)==1) input.prop("checked","checked");
                label.append(input);
                label.append(box);
                label.append($("<br>"));
                boxs.append(label);
            }
        });
    });

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
        //将权限序列化
        var boxs=$(".boxs").find("input");
        var fright="";
        $.each(boxs,function (i,box) {
            if($(box).prop("checked")) fright+="1";
            else fright+="0";
        });
        $("[name=fright]").val(fright);

        if ($("[name=code]").val().length == 0)
            showMsg("请填入操作员编号");
        else if ($("[name=name]").val().length == 0)
            showMsg("请填入操作员姓名");
        else if ($("[name=ccode]").val().length == 0)
            showMsg("请填入操作员身份证号");
        else {
            var action = form.attr("action");
            var method = form.attr("method");
            var data = form.serializeArray();
            $.ajax({
                url: action, type: method, dataType: "json", data: data,
                success: function (json) {
                    if (json.status == -1) showMsg(json.info);
                    else closePage();
                }
            });
        }
    }
</script>
</body>
</html>

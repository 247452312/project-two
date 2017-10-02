<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>操作员统计列表</title>

    <script type="text/javascript" src="/js/Calendar3.js"></script>
    <script type="text/javascript" src="/custom/jquery.min.js"></script>
    <script type="text/javascript" src="/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/custom/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/selfFunction.js"></script>
    <script type="text/javascript" src="/js/layer/layer.js"></script>
    <script type="text/javascript" src="/js/basic.js"></script>
    <script type="text/javascript" src="/js/jquery.jqprint-0.3.js"></script>
    <script type="text/javascript" src="/js/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="/js/easyuiTable.js"></script>

    <link href="/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="/custom/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="/custom/uimaker/icon.css">
    <link rel="stylesheet" href="/css/providers1.css">
    <style type="text/css">
        .none {
            display: none;
        }

        .block {
            display: block;
        }

        .in-line {
            display: inline;
        }
    </style>
</head>
<body>
<div id="test"></div>
<div class="container">
    <table id="dg" style="width:100%;height:529px" title="操作员统计列表" data-options="
            rownumbers:true,
            singleSelect:false,
            autoRowHeight:true,
            pagination:false,
            fitColumns:true,
            striped:true,
            checkOnSelect:false,
            selectOnCheck:false,
            collapsible:true,
            toolbar:'#tb',
            pageSize:50
            ">
        <thead>
        <tr>
            <th field="username" width="10%">操作员名称</th>
            <th field="je1" width="9%">充值金额</th>
            <th field="je2" width="9%">退款金额</th>
            <th field="je10" width="9%">项目充值金额</th>
            <th field="je11" width="9%">项目退款金额</th>
            <th field="je3" width="9%">采购金额</th>
            <th field="je4" width="9%">采购退货金额</th>
            <th field="je5" width="9%">销售金额</th>
            <th field="xj5" width="9%">销售现金</th>
            <th field="je6" width="9%">销售退货金额</th>
            <th field="xj6" width="9%">退货现金</th>
        </tr>
        </thead>
    </table>

    <div id="tb" style="padding:0 30px;">
        <form action="" method="post" class="form" style="overflow-y: auto;overflow-x:hidden;max-height: 140px;">
            <div>
                <input class="trem-input in-line" type="text" name="sdate" placeholder="开始日期"
                       onclick="new Calendar().show(this);" readonly="readonly"
                       style="width:166px;height:35px;line-height:35px;"/>
                <input class="trem-input in-line" type="text" name="ddate" placeholder="结束日期"
                       onclick="new Calendar().show(this);" readonly="readonly"
                       style="width:166px;height:35px;line-height:35px;"/>
            </div>
            <div class="conditions search-trem first-trem">
                <select style="height:35px;width:10%;" name="trem" onchange="changeInput($(this));"></select>
                <select style="height:35px;width:12%;" name="compare"></select>
                <input class="trem-input in-line" type="text" name="text"
                       style="width:166px;height:35px;line-height:35px;"/>
                <select class="trem-select none" style="height:35px;width:166px;"></select>
                <select style="height:35px;width:6%;" name="join">
                    <option value="0">并且</option>
                    <option value="1">或者</option>
                </select>
                <a onclick="addTrem($('.form'));"
                   class="easyui-linkbutton more" iconCls="icon-add">添加条件</a>
                <a onclick="removeTrem($(this));" style="display: none"
                   class="easyui-linkbutton more" iconCls="icon-cancel">去除条件</a>
                <a onclick="tableData($('.form'));" class="easyui-linkbutton a-select" iconCls="icon-search"
                   data-options="selected:true">查询</a>
                <a onclick="$('[name=where]').val('');tableData();" class="easyui-linkbutton a-select"
                   iconCls="icon-search" data-options="selected:true">全查</a>
                <a onclick="tablePrint();" class="easyui-linkbutton a-select"
                   iconCls="icon-print" data-options="selected:true"><%--打印--%></a>
            </div>
        </form>
    </div>
</div>


<script type="text/javascript">
    //后台获得资料，方便以后调用
    var rows = [];

    //传送数据获取表格信息
    function tableData(form) {
        rows = [];
        var data = [];
        if (form) {//查询时
            var selable = false;
            if ($("[name=compare]")[0].hasAttribute("disabled")) {
                selable = true;
                $("[name=compare]").removeAttr("disabled");
            }
            data = form.serializeArray();
            if (selable) $("[name=compare]").attr("disabled", "disabled");
        } else {//全查时
            data = {
                "sdata": "1900-01-01",
                "ddata": "3000-01-01",
                "where": "1=1"
            };
        }
        $.ajax({
            type: "POST", url: "/Tj/selectUser", dataType: "json", data: data, success: function (json) {
                for (var i = 0; i < json.length; i++) {
                    var Tj_User = json[i];
                    rows.push({
                        userid: Tj_User.id,
                        username: Tj_User.username,
                        vipname: Tj_User.vipname,
                        je1: Tj_User.je1,
                        je2: Tj_User.je2,
                        je3: Tj_User.je3,
                        je4: Tj_User.je4,
                        je5: Tj_User.je5,
                        xj5: Tj_User.xj5,
                        je6: Tj_User.je6,
                        xj6: Tj_User.xj6,
                    });
                }
                //页数相关赋值
                var jsea = json.sea;
                resetDg();
            }
        });
    }

    //重新添加表格数据
    function resetDg() {
        $(".datagrid-body").find("tbody").remove();
        $('#dg').datagrid({data: rows}).datagrid('clientPaging');
        $("#dg").datagrid({
            onDblClickRow: function (rowIndex, rowData) {
                //alert(JSON.stringify(rowData));
                //编辑本行资料
                showPage("会员订单明细", "/table/Ordermain/index.jsp?tempname=userid&tempinfo=" + rowData.userid, 1000, 400, function () {
                    tableData();
                }, true, false);
            }
        });
        $('#dg').datagrid('reload');
    }


    $(function () {
        tableData();
        getSelects();
    });

    //获得查询条件下拉列表
    function getSelects() {
        var divTrem = $(".first-trem");
        var trem = divTrem.find("[name=trem]");
        var compare = divTrem.find("[name=compare]");
        $.getJSON("/User/getStatus", function (json) {

            var tremOpt = json.userInput;
            for (var name in tremOpt) {
                var opt = $("<option></option>");
                opt.val(name);
                opt.html(tremOpt[name].option);
                trem.append(opt);
            }
            //获得比较方法
            var compareOpt = json.compareInput;
            for (var name in compareOpt) {
                var opt = $("<option></option>");
                opt.val(name);
                opt.html(compareOpt[name].option);
                compare.append(opt);
            }
        });
    }

    //变化填写条件
    function changeInput(select) {
        var inp = select.siblings(".trem-input");
        inp.html("");
        var sel = select.siblings(".trem-select");
        sel.empty();
        var selval = select.val();
        $.getJSON("/User/getStatus", function (json) {
            var textOpt = json.userInput[selval].input;//下拉框集合
            //如果是空的，则是输入框
            if (isEmptyObject(textOpt)) {
                sel.removeAttr("name").addClass("none").removeClass("in-line");
                inp.attr("name", "text").addClass("in-line").removeClass("none");
                select.siblings("[name=compare]").removeAttr("disabled");
            }
            //否则是下拉列表
            else {
                inp.removeAttr("name").addClass("none").removeClass("in-line");
                sel.attr("name", "text").addClass("in-line").removeClass("none");
                select.siblings("[name=compare]").attr("disabled", "disabled").val(2);
                for (var name in textOpt) {
                    var opt = $("<option></option>");
                    opt.val(name);
                    opt.html(textOpt[name]);
                    sel.append(opt);
                }
            }
        });
    }
</script>
</body>
</html>

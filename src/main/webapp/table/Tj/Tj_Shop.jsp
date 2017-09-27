<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>分店统计列表</title>

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
    <table id="dg" style="width:100%;height:529px" title="分店统计列表" data-options="
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
            <th field="shopname" width="8%">分店名称</th>
            <th field="storeamount" width="7%">库存金额</th>
            <th field="inamount" width="7%">采购金额</th>
            <th field="movinamount" width="7%">移入金额</th>
            <th field="movoutamount" width="7%">移出金额</th>
            <th field="saleamount" width="8%">销售金额</th>
            <th field="xj1" width="8%">销售现金</th>
            <th field="saletamount" width="8%">销售退货金额</th>
            <th field="xj2" width="8%">退货现金</th>
            <th field="samount" width="8%">库损金额</th>
            <th field="yamount" width="8%">库溢金额</th>
            <th field="saleallamount" width="8%">销售利润</th>
            <th field="allamount" width="8%">总利润</th>
        </tr>
        </thead>
    </table>

    <div id="tb" style="padding:0 30px;">
        <form action="" method="post" class="form" style="overflow-y: auto;overflow-x:hidden;max-height: 140px;">
            <div>
                <input class="trem-input in-line" type="text" name="sdate" placeholder="开始日期" onclick="new Calendar().show(this);" readonly="readonly"
                       style="width:166px;height:35px;line-height:35px;"/>
                <input class="trem-input in-line" type="text" name="ddate" placeholder="结束日期" onclick="new Calendar().show(this);" readonly="readonly"
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
            type: "POST", url: "/Tj/selectShop", dataType: "json", data: data, success: function (json) {
                for (var i = 0; i < json.length; i++) {
                    var Tj_Shop = json[i];
                    rows.push({
                        shopid: Tj_Shop.shopid,
                        shopname: Tj_Shop.shopname,
                        storeamount: Tj_Shop.storeamount,
                        inamount: Tj_Shop.inamount,
                        movinamount: Tj_Shop.movinamount,
                        movoutamount: Tj_Shop.movoutamount,
                        saleamount: Tj_Shop.saleamount,
                        xj1: Tj_Shop.xj1,
                        saletamount: Tj_Shop.saletamount,
                        xj2: Tj_Shop.xj2,
                        samount: Tj_Shop.samount,
                        yamount: Tj_Shop.yamount,
                        saleallamount: Tj_Shop.saleallamount,
                        allamount: Tj_Shop.allamount,
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
                showPage("会员订单明细", "/Ordermain/select?VipId=" + rowData.id, 1000, 400, function () {
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
        $.getJSON("/Shop/getStatus", function (json) {

            var tremOpt = json.shopInput;
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
        $.getJSON("/shop/getStatus", function (json) {
            var textOpt = json.shopInput[selval].input;//下拉框集合
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

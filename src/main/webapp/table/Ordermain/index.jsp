<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>单据列表</title>

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
<input type="hidden" value="${param.tempname}" id="tempname">
<input type="hidden" value="${param.tempinfo}" id="tempinfo">
<div class="container">
    <table id="dg" style="width:100%;height:529px" title="所有单据列表" data-options="
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
            <th field="code" width="8%">单据编号</th>
            <th field="type" width="8%">单据类型</th>
            <th field="date" width="8%">单据日期</th>
            <th field="amount" width="8%">总金额</th>
            <th field="vipamount" width="8%">会员卡支付</th>
            <th field="point" width="8%">总积分</th>
            <th field="client" width="8%">供应商名称</th>
            <th field="vip" width="8%">会员名称</th>
            <th field="shop" width="8%">分店名字</th>
            <th field="destshop" width="8%">目的分店名字</th>
            <th field="status" width="8%">单据状态</th>
            <th field="fexp" width="12%">单据备注</th>
        </tr>
        </thead>
    </table>

    <div id="tb" style="padding:0 30px;">
        <input type="hidden" name="where">
        <form action="" method="post" class="form" id="select-form"
              style="overflow-y: auto;overflow-x:hidden;max-height: 140px;">
            <input type="hidden" name="pageno">
            <input type="hidden" name="maxrow">
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
                <a onclick="tableData();" class="easyui-linkbutton a-select"
                   iconCls="icon-reload" data-options="selected:true"><%--刷新--%></a>
                <a onclick="tablePrint();" class="easyui-linkbutton a-select"
                   iconCls="icon-print" data-options="selected:true"><%--打印--%></a>
                <%--<a onclick="addPage();" class="easyui-linkbutton addRow" style="background: #4f9fcf;color: #ffffff"
                   iconCls="icon-add">添加单据</a>--%>
            </div>
        </form>
    </div>
    <%--换页盒子--%>
    <div class="datagrid-pager pagination">
        <table cellspacing="0" cellpadding="0" border="0">
            <tbody>
            <tr>
                <%--每页最大行数--%>
                <input type="hidden" name="pagination-page-list" value="10">
                <td><select class="pagination-page-list">
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="30">30</option>
                    <option value="40">40</option>
                    <option value="50">50</option>
                </select></td>
                <%--?--%>
                <td>
                    <div class="pagination-btn-separator"></div>
                </td>
                <%--第一页--%>
                <td><a href="javascript:void(0)"
                       class="l-btn l-btn-small l-btn-plain" group="" id="page-first"><span
                        class="l-btn-left l-btn-icon-left"><span class="l-btn-text l-btn-empty">&nbsp;</span><span
                        class="l-btn-icon pagination-first">&nbsp;</span></span></a></td>
                <%--上一页--%>
                <td><a href="javascript:void(0)"
                       class="l-btn l-btn-small l-btn-plain" group="" id="page-prev"><span
                        class="l-btn-left l-btn-icon-left"><span class="l-btn-text l-btn-empty">&nbsp;</span><span
                        class="l-btn-icon pagination-prev">&nbsp;</span></span></a></td>
                <td>
                    <div class="pagination-btn-separator"></div>
                </td>
                <%--当前页--%>
                <td><span style="padding-left:6px;">第</span></td>
                <td><input class="pagination-num" type="text" value="1" size="2"></td>
                <td><span style="padding-right:6px;">
                    共<span class="pagecount"></span>页
                </span></td>
                <td>
                    <div class="pagination-btn-separator"></div>
                </td>
                <%--下一页--%>
                <td><a href="javascript:void(0)"
                       class="l-btn l-btn-small l-btn-plain" group="" id="page-next"><span
                        class="l-btn-left l-btn-icon-left"><span class="l-btn-text l-btn-empty">&nbsp;</span><span
                        class="l-btn-icon pagination-next">&nbsp;</span></span></a></td>
                <%--最后一页--%>
                <td><a href="javascript:void(0)"
                       class="l-btn l-btn-small l-btn-plain" group="" id="page-last"><span
                        class="l-btn-left l-btn-icon-left"><span class="l-btn-text l-btn-empty">&nbsp;</span><span
                        class="l-btn-icon pagination-last">&nbsp;</span></span></a></td>
                <td>
                    <div class="pagination-btn-separator"></div>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="pagination-info">共<span class="rowcount"></span>记录</div>
        <div style="clear:both;"></div>
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
            form.find("[name=pageno]").val(1);
            form.find("[name=maxrow]").val($(".pagination-page-list").val());
            var selable = false;
            if ($("[name=compare]")[0].hasAttribute("disabled")) {
                selable = true;
                $("[name=compare]").removeAttr("disabled");
            }
            data = form.serializeArray();
            if (selable) $("[name=compare]").attr("disabled", "disabled");
        } else {//换页时
            data = {
                "where": $("[name=where]").val(),
                "pageno": $(".pagination-num").val(),
                "maxrow": $(".pagination-page-list").val()
            };
        }
        $.ajax({
            type: "POST", url: "/Ordermain/selectByAll", dataType: "json", data: data, success: function (json) {
                for (var i = 0; i < json.list.length; i++) {
                    var ordermain = json.list[i];
                    if (!ordermain.clientid) ordermain.clientid = {"name": "无"}
                    if (!ordermain.vipid) ordermain.vipid = {"name": "无"}
                    if (!ordermain.shopid) ordermain.shopid = {"name": "无"}
                    if (!ordermain.destshopid) ordermain.destshopid = {"name": "无"}
                    rows.push({
                        id: ordermain.id,
                        code: ordermain.ordercode,
                        type: ordermain.typeString,
                        date: ordermain.orderdate,
                        amount: ordermain.amount,
                        vipamount: ordermain.vipamount,
                        point: ordermain.point,
                        client: ordermain.clientid.name,
                        vip: ordermain.vipid.name,
                        shop: ordermain.shopid.name,
                        destshop: ordermain.destshopid.name,
                        status: ordermain.statusString,
                        fexp: ordermain.fexp,
                        orderType: ordermain.ordertype,
                    });
                }
                //页数相关赋值
                var jsea = json.sea;
                if (form) {
                    $("[name=where]").val(jsea.where);
                }
                ;
                $(".rowcount").html(jsea.rowcount);
                $(".pagecount").html(jsea.pagecount);
                $(".pagination-num").val(jsea.pageno);
                $(".pagination-page-list").val(jsea.maxrow);
                //$("#dg").attr("data-options","pageSize:"+jsea.maxrow);
                //点击事件
                $("#page-first").attr("onclick", "{$('.pagination-num').val(" + 1 + ");tableData();}");
                $("#page-prev").attr("onclick", "{$('.pagination-num').val(" + json.sea.prepage + ");tableData();}");
                $("#page-next").attr("onclick", "{ $('.pagination-num').val(" + json.sea.nextpage + ");tableData();}");
                $("#page-last").attr("onclick", "{ $('.pagination-num').val(" + json.sea.pagecount + ");tableData();}");
                //换条数事件
                $(".pagination-page-list").attr("onchange", "{$('.pagination-num').val(" + 1 + ");tableData();}");
                //换页数事件
                $(".pagination-num").attr("onchange", "{tableData();}");
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
                showPage("单据详情", "/table/Ordermain/add.jsp?ordertype=" + rowData.orderType + "&orderid=" + rowData.id,
                    1250, 550, function () {
                    tableData();
                }, false, false);
            }
        });
        $('#dg').datagrid('reload');
    }

    //打开新增页面
    function addPage() {
        showPage("新增单据", "/Ordermain/ad", 1250, 550, function () {
            tableData();
        }, false, false);
    }

    $(function () {
        getSelects();
        if (tempname.value != "") {
            $("#select-form").remove();
            rows = [];
            var data = {
                "where": "where " + $("#tempname").val() + "=" + $("#tempinfo").val(),
            };
            $.ajax({
                type: "POST", url: "/Ordermain/selectByAll", dataType: "json", data: data, success: function (json) {
                    for (var i = 0; i < json.list.length; i++) {
                        var ordermain = json.list[i];
                        if (!ordermain.clientid) ordermain.clientid = {"name": "无"}
                        if (!ordermain.vipid) ordermain.vipid = {"name": "无"}
                        if (!ordermain.shopid) ordermain.shopid = {"name": "无"}
                        if (!ordermain.destshopid) ordermain.destshopid = {"name": "无"}
                        rows.push({
                            id: ordermain.id,
                            code: ordermain.ordercode,
                            type: ordermain.typeString,
                            date: ordermain.orderdate,
                            amount: ordermain.amount,
                            vipamount: ordermain.vipamount,
                            point: ordermain.point,
                            client: ordermain.clientid.name,
                            vip: ordermain.vipid.name,
                            shop: ordermain.shopid.name,
                            destshop: ordermain.destshopid.name,
                            status: ordermain.statusString,
                            fexp: ordermain.fexp,
                            orderType: ordermain.ordertype,
                        });
                    }
                    //页数相关赋值
                    var jsea = json.sea;
                    $("[name=where]").val(jsea.where);
                    $(".rowcount").html(jsea.rowcount);
                    $(".pagecount").html(jsea.pagecount);
                    $(".pagination-num").val(jsea.pageno);
                    $(".pagination-page-list").val(jsea.maxrow);
                    //$("#dg").attr("data-options","pageSize:"+jsea.maxrow);
                    //点击事件
                    $("#page-first").attr("onclick", "{$('.pagination-num').val(" + 1 + ");tableData();}");
                    $("#page-prev").attr("onclick", "{$('.pagination-num').val(" + json.sea.prepage + ");tableData();}");
                    $("#page-next").attr("onclick", "{ $('.pagination-num').val(" + json.sea.nextpage + ");tableData();}");
                    $("#page-last").attr("onclick", "{ $('.pagination-num').val(" + json.sea.pagecount + ");tableData();}");
                    //换条数事件
                    $(".pagination-page-list").attr("onchange", "{$('.pagination-num').val(" + 1 + ");tableData();}");
                    //换页数事件
                    $(".pagination-num").attr("onchange", "{tableData();}");
                    resetDg();
                }
            });

        } else {
            tableData();
        }
    });

    //获得查询条件下拉列表
    function getSelects() {
        var divTrem = $(".first-trem");
        var trem = divTrem.find("[name=trem]");
        var compare = divTrem.find("[name=compare]");
        $.getJSON("/Ordermain/getStatus", function (json) {
            //获得client查询变量
            var tremOpt = json.ordermainInput;
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
        $.getJSON("/Ordermain/getStatus", function (json) {
            //获得client查询变量
            var textOpt = json.ordermainInput[selval].input;//下拉框集合
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
<%--value="${param.ordertype==null?(requestScope.object.ordertype==null?9:requestScope.object.ordertype):param.ordertype}"--%>
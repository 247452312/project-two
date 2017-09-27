<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员明细余额统计列表</title>

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
    <table id="dg" style="width:100%;height:529px" title="会员明细余额统计列表" data-options="
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
            <th field="ordercode" width="9%">单据号</th>
            <th field="ordertype" width="9%">单据类型</th>
            <th field="orderdate" width="9%">日期</th>
            <th field="shopname" width="8%">分店</th>
            <th field="amount" width="9%">单据金额</th>
            <th field="inje" width="8%">增加</th>
            <th field="outje" width="8%">减少</th>
            <th field="je" width="8%">余额</th>
            <th field="inpoint" width="8%">增加</th>
            <th field="outpoint" width="8%">减少</th>
            <th field="point" width="8%">积分</th>
            <th field="username" width="8%">创建人</th>
        </tr>
        </thead>
    </table>

    <div id="tb" style="padding:0 30px;">
        <div action="" method="post" class="form" style="overflow-y: auto;overflow-x:hidden;max-height: 140px;">

            <div class="conditions search-trem first-trem">
                <input type="hidden" name="markid" value="">
                <input name="name" style="height:35px;width:7%;position:absolute;left:90px;top: 9px;" onkeypress="tj(event)">
                选择会员：<select style="height:35px;width:10%;" class="vip-select" name="trem" onchange="$('[name=markid]').val($(this).val())"></select>
                <a onclick="tableData()" class="easyui-linkbutton a-select"
                   data-options="selected:true">统计</a>
                <a onclick="tablePrint();" class="easyui-linkbutton a-select"
                   iconCls="icon-print" data-options="selected:true"><%--打印--%></a>

            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    //后台获得资料，方便以后调用
    var rows = [];

    //传送数据获取表格信息
    function tableData() {
        rows = [];
        $.ajax({
            type: "POST", url: "/Tj/selectVipMx", dataType: "json", data: {"id":$('[name=markid]').val()}, success: function (json) {
                for (var i = 0; i < json.length; i++) {
                    var TjVipMx = json[i];
                    rows.push({
                        ordercode:TjVipMx.ordercode,
                        ordertype: TjVipMx.ordertype,
                        orderdate: TjVipMx.orderdate,
                        shopname: TjVipMx.shopname,
                        amount: TjVipMx.amount,
                        inje: TjVipMx.inje,
                        outje: TjVipMx.outje,
                        je: TjVipMx.je,
                        inpoint: TjVipMx.inpoint,
                        outpoint: TjVipMx.outpoint,
                        point: TjVipMx.point,
                        username: TjVipMx.username
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
        //getSelects();

    });


    function tj(event) {
        if(event.keyCode!=13)return;
       var data=$("[name=name]").val();
        $.ajax({
            type: "POST", url: "/Vip/getByName", dataType: "json", data: {"name":data}, success: function (json) {
                if (json.length==0){
                    showMsg("无此人");
                    rerurn;
                }
                var select = $(".vip-select");
                select.empty();
                for (var i = 0; i < json.length; i++) {
                    var Vip = json[i];
                    var op = $("<option value='"+Vip.id+"'>"+Vip.name+"<option>");
                    select.append(op);
                   // alert(1);
                }
                $("[name=markid]").val(json[0].id);
                //页数相关赋值

                resetDg();
            }
        });
    }

    //获得查询条件下拉列表
   /* function getSelects() {
        var divTrem = $(".first-trem");
        var trem = divTrem.find("[name=trem]");
        var compare = divTrem.find("[name=compare]");
        $.getJSON("/Vip/getStatus", function (json) {

            var tremOpt = json.vipInput;
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
*/
   /* //变化填写条件
    function changeInput(select) {
        var inp = select.siblings(".trem-input");
        inp.html("");
        var sel = select.siblings(".trem-select");
        sel.empty();
        var selval = select.val();
        $.getJSON("/Ordermain/getStatus", function (json) {
            var textOpt = json.vipInput[selval].input;//下拉框集合
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
    }*/
</script>
</body>
</html>

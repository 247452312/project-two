<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加单据</title>

    <script type="text/javascript" src="/custom/jquery.min.js"></script>
    <script type="text/javascript" src="/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/custom/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/selfFunction.js"></script>
    <script type="text/javascript" src="/js/layer/layer.js"></script>
    <script type="text/javascript" src="/js/basic.js"></script>
    <script type="text/javascript" src="/js/jquery.jqprint-0.3.js"></script>
    <script type="text/javascript" src="/js/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="/js/easyuiTable.js"></script>
    <script type="text/javascript" src="/js/Calendar3.js"></script>

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

        select, input {
            height: 35px;
            width: 120px;
            padding-left: 5px;
            padding-right: 5px;
        }

        textarea {
            vertical-align: top;
            resize: none;
            width: 531px;
            height: 100px;
            box-sizing: content-box;
            margin-top: 7px;
        }

        .conditions .con-span {
            text-align: center;
            width: 80px;
        }

        .textbox {
            margin-right: 0px;
        }

        td .textbox {
            border: 0px;
        }

        .datagrid-view .datagrid-editable-input,
        .textbox .textbox-text {
            border: 0px solid #fff;
        }
    </style>
</head>
<body>
<div id="test"></div>
<input type="hidden" class="ordertype" name="ordertype"
       value="${param.ordertype==null?
        (requestScope.object.ordertype==null?9:requestScope.object.ordertype)
        :param.ordertype}">
<input type="hidden" class=" orderid" value="${param.orderid}">
<div class="container">
    <table id="dg" style="width:100%;height:529px" title="" data-options="
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
            pageSize:50,
            onClickCell:onClickCell
            ">
        <thead>
        <tr>
            <%--充值单--%><%--退款单--%>
            <c:if test="${param.ordertype==1||requestScope.object.ordertype==1||
                            param.ordertype==2||requestScope.object.ordertype==2}">
                <script type="text/javascript">
                    function save() {
                        $("[name=shopid]").attr("name", "shopid.id");
                        $("[name=destshopid]").attr("name", "destshopid.id");
                        $("[name=userid]").attr("name", "userid.id");
                        $("[name=vipid]").attr("name", "vipid.id");
                        var main = $(".form").serializeArray();
                        main.push({"name": "type", "value": $(".ordertype").val()});
                        $.ajax({
                            type: "POST", url: "/Ordermain/insert1", dataType: "json",
                            data: main,
                            success: function () {
                                showMsg("保存成功");
                            }
                        });
                        showMsg("保存完成");
                        closePage();
                    }

                    $(function () {
                        var orderid = $(" .orderid").val();
                        if (orderid != "") {
                            var data = {
                                "id": orderid
                            }
                            //填充主表
                            $.ajax({
                                url: "/Ordermain/selectById",
                                type: "POST",
                                dataType: "json",
                                data: data,
                                success: function (ordermain) {
                                    $("#order-code").find("input").val(ordermain.ordercode);
                                    $("#order-date").find("input").val(ordermain.orderdate);
                                    $("#order-shopid").find("select").append("<option selected='selected' value='" + ordermain.shopid.id + "'>" + ordermain.shopid.name + "</option>")
                                    $("#order-vipname").find("input").val(ordermain.vipid.name);
                                    $("#order-vipname").find(".inp-val").val(ordermain.vipid.id);
                                    $("#order-viptel").find("input").val(ordermain.vipid.tel);
                                    $("#order-vipamount").find("input").val(ordermain.vipid.amount);
                                    $("#order-vippoint").find("input").val(ordermain.vipid.point);
                                    $("#order-vipaddr").find("input").val(ordermain.vipid.addr);
                                    $("#order-amount").find("input").val(ordermain.amount);
                                    $("#order-point").find("input").val(ordermain.point);
                                    $("#order-fexp").find("textarea").text(ordermain.fexp);
                                }
                            });

                        }
                    })
                </script>
            </c:if>
            <%--采购单--%><%--采购退货单--%>
            <c:if test="${param.ordertype==3||requestScope.object.ordertype==3||
                            param.ordertype==4||requestScope.object.ordertype==4}">
                <script type="text/javascript">
                    function save() {
                        $("[name=shopid]").attr("name", "shopid.id");
                        $("[name=destshopid]").attr("name", "destshopid.id");
                        $("[name=userid]").attr("name", "userid.id");
                        $("[name=vipid]").attr("name", "vipid.id");
                        $("[name=clientid]").attr("name", "clientid.id");
                        var formDate = $('.form').serializeObject();//主表数据
                        var action = "";
                        if ($(".orderid").val()) action = "/Ordermain/update1";
                        else action = "/Ordermain/insert1";
                        //传主表
                        var mainId;
                        $.ajax({
                            url: action,
                            type: "POST",
                            async: false,
                            dataType: "JSON",
                            data: formDate,
                            success: function (json) {
                                if (json.status == 1) mainId = json.info;
                                else showMsg(json.info);
                            }
                        });
                        $(".orderid").val(mainId);
                        var rows = $('#dg').datagrid("getRows");//获得表格内所有数据
                        for (var i = 0; i < rows.length; i++) {
                            var rowData = rows[i];//获取单行数据
                            //alert(rowData.proid);
                            var data = {
                                "orderid.id": mainId,
                                "productid.id": rowData.proid,
                                "count": rowData.count,
                                "price": rowData.price,
                                "amount": rowData.amount,
                                "fexp": rowData.fexp
                            };
                            var action2 = "/Ordermain/insert2";
                            if (rowData.id) action2 = "/Ordermain/update2"
                            $.ajax({
                                url: action2,
                                async: false,
                                type: "POST",
                                dataType: "JSON",
                                data: data,
                                success: function (jsondls) {
                                    if (jsondls.status == 1) {
                                        if (jsondls.info) window.rows[i].id = jsondls.info;//将最新id给相应行
                                    }
                                    else {
                                        showMsg(jsondls.info);
                                        return;
                                    }
                                }
                            });
                        }
                        resetDg();//重写表格内容
                        showMsg("保存完成");
                        closePage();

                    }

                    function add() {
                        var name = $(".tremName").val();
                        $.ajax({
                            url: "/Product/getProduct",
                            type: "POST",
                            dataType: "json",
                            data: {"name": name},
                            success: function (pros) {
                                if (pros.length == 0) showMsg("没找到相关商品");
                                else {
                                    var pro = pros[0];
                                    var urlpart = "";
                                    for (var i = 0; i < pros.length; i++) {
                                        var obj = pros[i];
                                        urlpart += "proid=" + obj.id + "&proname=" + obj.name + "&";
                                    }
                                    showPage("增加盘点商品", "/table/Ordermain/addpro.jsp?" + urlpart.substring(0, urlpart.length - 1),
                                        800, 300,
                                        function () {
                                            for (var i = 0; i < details.length; i++) {
                                                var detail = details[i];
                                                rows.push({
                                                    proid: detail.productid.id,
                                                    procode: detail.productid.code,
                                                    proname: detail.productid.name,
                                                    count: "",
                                                    price: "",
                                                    amount: "",
                                                    fexp: "",
                                                });
                                            }
                                            resetDg();
                                            details = [];
                                        }, false, true);
                                }
                            }
                        });
                    }

                    $(function () {
                        var orderid = $(" .orderid").val();
                        if (orderid != "") {
                            var data = {
                                "id": orderid
                            }
                            //填充上部信息
                            $.ajax({
                                url: "/Ordermain/selectById",
                                type: "POST",
                                dataType: "json",
                                data: data,
                                success: function (ordermain) {
                                    $("#order-code").find("input").val(ordermain.ordercode);
                                    $("#order-date").find("input").val(ordermain.orderdate);
                                    $("#order-destshopid").find("select").append("<option selected='selected' value='" + ordermain.destshopid.id + "'>" + ordermain.destshopid.name + "</option>")
                                    $("#order-clientid").find("select").append("<option selected='selected' value='" + ordermain.clientid.id + "'>" + ordermain.clientid.name + "</option>")
                                    $("#order-viptel").find("input").val(ordermain.clientid.tel);
                                    $("#order-clientlxr").find("input").val(ordermain.clientid.lxr);
                                    $("#order-vipaddr").find("input").val(ordermain.clientid.point);
                                    $("#order-amount").find("input").val(ordermain.amount);
                                    $("#order-point").find("input").val(ordermain.point);
                                    $("#order-fexp").find("textarea").text(ordermain.fexp);
                                }
                            });
                            //填充表格
                            $.ajax({
                                type: "POST", url: "/Orderdetail/selectByAttr", dataType: "json", data: {
                                    "attrName": "orderid",
                                    "object": $(".orderid").val()
                                }, success: function (json) {
                                    for (var i = 0; i < json.length; i++) {
                                        var orderdetail = json[i];
                                        //details.push(checkdetail);
                                        rows.push({
                                            id: orderdetail.id,
                                            proid: orderdetail.productid.id,
                                            procode: orderdetail.productid.code,
                                            proname: orderdetail.productid.name,
                                            count: orderdetail.count,
                                            price: orderdetail.price,
                                            amount: orderdetail.amount,
                                            fexp: orderdetail.fexp,
                                        });
                                    }
                                    resetDg();
                                }
                            });
                        }
                    });


                </script>
                <th field="procode" width="12%" editor="text">商品编号</th>
                <th field="proname" width="12%" editor="text">商品名称</th>
                <th field="count" width="12%" editor="numberbox">数量</th>
                <th field="price" width="12%" editor="numberbox">售价</th>
                <th field="amount" width="12%" editor="numberbox">金额小计</th>
                <th field="fexp" width="12%" editor="text">明细备注</th>
            </c:if>
            <%--销售单--%><%--销售退货单--%>
            <c:if test="${param.ordertype==5||requestScope.object.ordertype==5||
                            param.ordertype==6||requestScope.object.ordertype==6}">
                <script type="text/javascript">
                    function save() {
                        $("[name=shopid]").attr("name", "shopid.id");
                        $("[name=destshopid]").attr("name", "destshopid.id");
                        $("[name=userid]").attr("name", "userid.id");
                        $("[name=vipid]").attr("name", "vipid.id");

                        var main = $(".form").serializeObject();
                        $.ajax({
                            type: "POST", url: "/Ordermain/insert", dataType: "json",
                            data: {
                                type: $(".ordertype").val(),
                                main: main
                            },
                            success: function () {
                                showMsg("保存成功");
                            }
                        });
                        resetDg();//重写表格内容
                        showMsg("保存完成");
                        closePage();

                    }

                    function add() {
                        var name = $(".tremName").val();
                        $.ajax({
                            url: "/Product/getProduct",
                            type: "POST",
                            dataType: "json",
                            data: {"name": name},
                            success: function (pros) {
                                if (pros.length == 0) showMsg("没找到相关商品");
                                else {
                                    var pro = pros[0];
                                    var urlpart = "";
                                    for (var i = 0; i < pros.length; i++) {
                                        var obj = pros[i];
                                        urlpart += "proid=" + obj.id + "&proname=" + obj.name + "&";
                                    }
                                    showPage("增加盘点商品", "/table/Ordermain/addpro.jsp?" + urlpart.substring(0, urlpart.length - 1),
                                        800, 300,
                                        function () {
                                            for (var i = 0; i < details.length; i++) {
                                                var detail = details[i];
                                                rows.push({
                                                    proid: detail.productid.id,
                                                    procode: detail.productid.code,
                                                    proname: detail.productid.name,
                                                    count: "",
                                                    price: "",
                                                    amount: "",
                                                    point: "",
                                                    fexp: ""
                                                });
                                            }
                                            resetDg();
                                            details = [];
                                        }, false, true);
                                }
                            }
                        });
                    }

                    $(function () {
                        var orderId = $(".orderid").val();
                        if (orderId != "") {
                            var data = {
                                "id": orderId
                            }
                            $.ajax({
                                url: "/Ordermain/selectById",
                                type: "POST",
                                dataType: "json",
                                data: data,
                                success: function (ordermain) {
                                    $("#order-code").find("input").val(ordermain.ordercode);
                                    $("#order-date").find("input").val(ordermain.orderdate);
                                    $("#order-shopid").find("select").append("<option selected='selected' value='" + ordermain.shopid.id + "'>" + ordermain.shopid.name + "</option>")
                                    $("#order-vipname").find("input").val(ordermain.vipid.name);
                                    $("#order-vipname").find(".inp-val").val(ordermain.vipid.id);
                                    $("#order-viptel").find("input").val(ordermain.vipid.tel);
                                    $("#order-vipamount").find("input").val(ordermain.vipid.amount);
                                    $("#order-vippoint").find("input").val(ordermain.vipid.point);
                                    $("#order-vipaddr").find("input").val(ordermain.vipid.addr);
                                    $("#order-amount").find("input").val(ordermain.amount);
                                    $("#order-point").find("input").val(ordermain.point);
                                    $("#order-fexp").find("textarea").text(ordermain.fexp);
                                    $("#order-money").find("input").val(ordermain.amount - ordermain.vipamount);
                                }
                            });
                            //填充表格
                            $.ajax({
                                type: "POST", url: "/Orderdetail/selectByAttr", dataType: "json", data: {
                                    "attrName": "orderid",
                                    "object": $(".orderid").val()
                                }, success: function (json) {
                                    for (var i = 0; i < json.list.length; i++) {
                                        var orderdetail = json.list[i];
                                        //details.push(checkdetail);
                                        rows.push({
                                            id: orderdetail.id,
                                            proid: orderdetail.productid.id,
                                            procode: orderdetail.productid.code,
                                            proname: orderdetail.productid.name,
                                            count: orderdetail.count,
                                            price: orderdetail.price,
                                            amount: orderdetail.amount,
                                            point: orderdetail.point,
                                            fexp: orderdetail.fexp
                                        });
                                    }
                                    resetDg();
                                }
                            });
                        }
                    })
                </script>
                <th field="procode" width="12%" editor="text">商品编号</th>
                <th field="proname" width="12%" editor="text">商品名称</th>
                <th field="count" width="12%" editor="numberbox">数量</th>
                <th field="price" width="12%" editor="numberbox">售价</th>
                <th field="amount" width="12%" editor="numberbox">金额小计</th>
                <th field="point" width="12%" editor="numberbox">积分小计</th>
                <th field="fexp" width="12%" editor="text">明细备注</th>
            </c:if>
            <%--移库单--%><%--库损单--%><%--库溢单--%>
            <c:if test="${param.ordertype==7||requestScope.object.ordertype==7||
                            param.ordertype==8||requestScope.object.ordertype==8||
                            param.ordertype==9||requestScope.object.ordertype==9}">
                <script type="text/javascript">
                    function save() {
                        $("[name=shopid]").attr("name", "shopid.id");
                        $("[name=destshopid]").attr("name", "destshopid.id");
                        $("[name=userid]").attr("name", "userid.id");
                        $("[name=vipid]").attr("name", "vipid.id");
                        var main = $(".form").serializeObject();
                        $.ajax({
                            type: "POST", url: "/Ordermain/insert", dataType: "json",
                            data: {
                                type: $(".ordertype").val(),
                                main: main
                            },
                            success: function () {
                                showMsg("保存成功");
                            }
                        });
                        resetDg();//重写表格内容
                        showMsg("保存完成");
                        closePage();
                    }

                    function add() {
                        var name = $(".tremName").val();
                        $.ajax({
                            url: "/Product/getProduct",
                            type: "POST",
                            dataType: "json",
                            data: {"name": name},
                            success: function (pros) {
                                if (pros.length == 0) showMsg("没找到相关商品");
                                else {
                                    var pro = pros[0];
                                    var urlpart = "";
                                    for (var i = 0; i < pros.length; i++) {
                                        var obj = pros[i];
                                        urlpart += "proid=" + obj.id + "&proname=" + obj.name + "&";
                                    }
                                    showPage("增加盘点商品", "/table/Ordermain/addpro.jsp?" + urlpart.substring(0, urlpart.length - 1),
                                        800, 300,
                                        function () {
                                            for (var i = 0; i < details.length; i++) {
                                                var detail = details[i];
                                                rows.push({
                                                    proid: detail.productid.id,
                                                    procode: detail.productid.code,
                                                    proname: detail.productid.name,
                                                    count: "",
                                                    fexp: ""
                                                });
                                            }
                                            resetDg();
                                            details = [];
                                        }, false, true);
                                }
                            }
                        });
                    }


                    $(function () {
                        var orderid = $(" .orderid").val();
                        if (orderid != "") {
                            var data = {
                                "id": orderid
                            }
                            $.ajax({
                                url: "/Ordermain/selectById",
                                type: "POST",
                                dataType: "json",
                                data: data,
                                success: function (ordermain) {
                                    $("#order-code").find("input").val(ordermain.ordercode);
                                    $("#order-date").find("input").val(ordermain.orderdate);
                                    $("#order-shopid").find("select").append("<option selected='selected' value='" + ordermain.shopid.id + "'>" + ordermain.shopid.name + "</option>")
                                    $("#order-destshopid").find("select").append("<option selected='selected' value='" + ordermain.destshopid.id + "'>" + ordermain.destshopid.name + "</option>")
                                    $("#order-amount").find("input").val(ordermain.amount);
                                    $("#order-point").find("input").val(ordermain.point);
                                    $("#order-fexp").find("textarea").text(ordermain.fexp);
                                }
                            });

                            //填充表格
                            $.ajax({
                                type: "POST", url: "/Orderdetail/selectByAttr", dataType: "json", data: {
                                    "attrName": "orderid",
                                    "object": $(".orderid").val()
                                }, success: function (json) {
                                    for (var i = 0; i < json.length; i++) {
                                        var orderdetail = json[i];
                                        //details.push(checkdetail);
                                        rows.push({
                                            id: orderdetail.id,
                                            proid: orderdetail.productid.id,
                                            procode: orderdetail.productid.code,
                                            proname: orderdetail.productid.name,
                                            count: orderdetail.count,
                                            fexp: orderdetail.fexp
                                        });
                                    }
                                    resetDg();
                                }
                            });
                        }
                    })
                </script>
                <th field="procode" width="12%" editor="text">商品编号</th>
                <th field="proname" width="12%" editor="text">商品名称</th>
                <th field="count" width="12%" editor="numberbox">数量</th>
                <th field="fexp" width="12%" editor="text">明细备注</th>
            </c:if>
            <%--项目充值单--%><%--项目退款单--%>
            <c:if test="${param.ordertype==10||requestScope.object.ordertype==10||
                        param.ordertype==11||requestScope.object.ordertype==11}">
                <script type="text/javascript">
                    function save() {
                        $("[name=shopid]").attr("name", "shopid.id");
                        $("[name=destshopid]").attr("name", "destshopid.id");
                        $("[name=userid]").attr("name", "userid.id");
                        $("[name=vipid]").attr("name", "vipid.id");
                        var main = $(".form").serializeObject();
                        $.ajax({
                            type: "POST", url: "/Ordermain/insert", dataType: "json",
                            data: {
                                type: $(".ordertype").val(),
                                main: main
                            },
                            success: function () {
                                showMsg("保存成功");
                            }
                        });
                        resetDg();//重写表格内容
                        showMsg("保存完成");
                        closePage();
                    }

                    function add() {
                        var name = $(".tremName").val();
                        $.ajax({
                            url: "/Product/getProduct",
                            type: "POST",
                            dataType: "json",
                            data: {"name": name},
                            success: function (pros) {
                                if (pros.length == 0) showMsg("没找到相关商品");
                                else {
                                    var pro = pros[0];
                                    var urlpart = "";
                                    for (var i = 0; i < pros.length; i++) {
                                        var obj = pros[i];
                                        urlpart += "proid=" + obj.id + "&proname=" + obj.name + "&";
                                    }
                                    showPage("增加盘点商品", "/table/Ordermain/addpro.jsp?" + urlpart.substring(0, urlpart.length - 1),
                                        800, 300,
                                        function () {
                                            for (var i = 0; i < details.length; i++) {
                                                var detail = details[i];
                                                rows.push({
                                                    proid: detail.productid.id,
                                                    procode: detail.productid.code,
                                                    proname: detail.productid.name,
                                                    count: "",
                                                    amount: "",
                                                    point: "",
                                                    fexp: ""
                                                });
                                            }
                                            resetDg();
                                            details = [];
                                        }, false, true);
                                }
                            }
                        });
                    }

                    $(function () {
                        var orderid = $(" .orderid").val();
                        if (orderid != "") {
                            var data = {
                                "id": orderid
                            }
                            $.ajax({
                                url: "/Ordermain/selectById",
                                type: "POST",
                                dataType: "json",
                                data: data,
                                success: function (ordermain) {
                                    $("#order-code").find("input").val(ordermain.ordercode);
                                    $("#order-date").find("input").val(ordermain.orderdate);
                                    $("#order-shopid").find("select").append("<option selected='selected' value='" + ordermain.shopid.id + "'>" + ordermain.shopid.name + "</option>")
                                    $("#order-vipname").find("input").val(ordermain.vipid.name);
                                    $("#order-vipname").find(".inp-val").val(ordermain.vipid.id);
                                    $("#order-viptel").find("input").val(ordermain.vipid.tel);
                                    $("#order-vipamount").find("input").val(ordermain.vipid.amount);
                                    $("#order-vippoint").find("input").val(ordermain.vipid.point);
                                    $("#order-vipaddr").find("input").val(ordermain.vipid.addr);
                                    $("#order-amount").find("input").val(ordermain.amount);
                                    $("#order-point").find("input").val(ordermain.point);
                                    $("#order-fexp").find("textarea").text(ordermain.fexp);
                                }
                            });
                            //填充表格
                            $.ajax({
                                type: "POST", url: "/Orderdetail/selectByAttr", dataType: "json", data: {
                                    "attrName": "orderid",
                                    "object": $(".ordertype").val()
                                }, success: function (json) {
                                    for (var i = 0; i < json.length; i++) {
                                        var orderdetail = json[i];
                                        //details.push(checkdetail);
                                        rows.push({
                                            id: orderdetail.id,
                                            proid: orderdetail.productid.id,
                                            procode: orderdetail.productid.code,
                                            proname: orderdetail.productid.name,
                                            count: orderdetail.count,
                                            amount: orderdetail.amount,
                                            point: orderdetail.point,
                                            fexp: orderdetail.fexp
                                        });
                                    }
                                    resetDg();
                                }
                            });
                        }
                    })
                </script>
                <th field="procode" width="12%" editor="text">商品编号</th>
                <th field="proname" width="12%" editor="text">商品名称</th>
                <th field="count" width="12%" editor="numberbox">数量</th>
                <th field="amount" width="12%" editor="numberbox">金额小计</th>
                <th field="point" width="12%" editor="numberbox">积分小计</th>
                <th field="fexp" width="12%" editor="text">明细备注</th>
            </c:if>
        </tr>
        </thead>
    </table>
    <div id="tb" style="padding:30px;position: relative">
        <div class="ordername" style="width: 100%;text-align: center;font-size: 30px;"></div>
        <div class="buttons" style="position: absolute;right: 46px;top: 30px;">
            <a onclick="add();" class="easyui-linkbutton a-program" iconCls="icon-list"
               data-options="selected:true">项目</a>
            <a onclick="save();" class="easyui-linkbutton" iconCls="icon-save"
               data-options="selected:true">保存</a>
            <input class="trem-input in-line tremName" type="text">
            <a onclick="add();" class="easyui-linkbutton" iconCls="icon-save"
               data-options="selected:true">添加一行</a>
        </div>
        <form action="" method="post" class="form">

            <input type="hidden" class="ordertype" name="ordertype"
                   value="${param.ordertype==null?
        (requestScope.object.ordertype==null?9:requestScope.object.ordertype)
        :param.ordertype}">
            <div class="conditions search-trem first-trem">
                <div>
                    <span id="order-code">
                        <span class="con-span">单据号</span>
                        <input class="trem-input in-line" type="text" name="ordercode">
                    </span>

                    <span id="order-date">
                        <span class="con-span">单据日期</span>
                        <input type="" name="orderdate" value="${requestScope.object.birthdate}"
                               lay-verify="title" autocomplete="off" placeholder=""
                               class="input form-name trem-input in-line"
                               id="control_date" size="10" maxlength="10"
                               onclick="new Calendar().show(this);" readonly="readonly">
                    </span>

                    <span id="order-shopid">
                        <span class="con-span">分店</span>
                        <select name="shopid"></select>
                    </span>

                    <span id="order-destshopid">
                        <span class="con-span">目的分店</span>
                        <select name="destshopid"></select>
                    </span>

                    <span id="order-clientid">
                        <span class="con-span">供应商</span>
                        <select name="clientid">
                            <option></option>
                        </select>
                    </span>

                    <span id="order-vipname" onclick="setSelinputEvt($(this));">
                        <span class="con-span">会员名称</span>
                        <input type="hidden" name="vipid" class="inp-val">
                        <select class="easyui-combobox" style="height:35px;"></select>
                    </span>

                    <span id="order-viptel">
                        <span class="con-span">电话</span>
                        <input class="trem-input in-line" type="text" disabled="disabled">
                    </span>

                    <span id="order-clientlxr">
                        <span class="con-span">联系人</span>
                        <input class="trem-input in-line" type="text" disabled="disabled">
                    </span>

                    <span id="order-vipamount">
                        <span class="con-span">余额</span>
                        <input class="trem-input in-line" type="text" disabled="disabled">
                    </span>

                    <span id="order-vippoint">
                        <span class="con-span">积分</span>
                        <input class="trem-input in-line" type="text" disabled="disabled">
                    </span>

                    <span id="order-vipaddr">
                        <span class="con-span">地址</span>
                        <input class="trem-input in-line" type="text" disabled="disabled">
                    </span>

                    <span id="order-amount">
                        <span class="con-span">总金额</span>
                        <input class="trem-input in-line" type="text" disabled="disabled"
                               name="amount" value="0">
                    </span>

                    <span id="order-point">
                        <span class="con-span">总积分</span>
                        <input class="trem-input in-line" type="text" disabled="disabled"
                               name="point" value="0">
                    </span>

                    <span id="order-fexp">
                        <span class="con-span">备注</span>
                        <textarea class="trem-textarea in-line" name="fexp"></textarea>
                    </span>
                </div>
                <%-----------------------------------------------------------------------------%>
            </div>
        </form>
    </div>
</div>


<script type="text/javascript">
    //后台获得资料，方便以后调用
    var rows = [];
    var details = [];

    //传送数据获取表格信息
    function tableData(form) {
        rows = [];
        var data = [];
        $.ajax({
            type: "POST", url: "/Ordermain/selectByAll", dataType: "json", data: data, success: function (json) {
                for (var i = 0; i < json.list.length; i++) {
                    var ordermain = json.list[i];
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
                    });
                }
            }
        });
    }

    //重新添加表格数据
    function resetDg() {
        $(".datagrid-body").find("tbody").remove();
        $('#dg').datagrid({data: rows}).datagrid('clientPaging');
        $('#dg').datagrid('reload');
    }


    $(function () {
        aboutHidden();
        getSelects();
        resetDg();
//        tableData();


    });

    //获得查询条件下拉列表
    function getSelects() {
        $.getJSON("/Ordermain/getStatus", function (json) {
            var tremOpt = json.ordermainInput;
            var opts;
            //获得client查询变量
            opts = tremOpt["7"].input;
            for (var name in opts) {
                var opt = $("<option></option>");
                opt.val(name);
                opt.html(opts[name]);

                $("[name=clientid]").append(opt);
            }
            $("[name=clientid]").change(function () {
                $.ajax({
                    url: "/Client/selectById",
                    data: {id: $(this).val()},
                    success: function (json) {
                        $("#order-viptel").find("input").val(json.tel);
                        $("#order-clientlxr").find("input").val(json.lxr);
                        $("#order-vipaddr").find("input").val(json.addr);
                    }
                });

            });

            //获得shop查询变量
            opts = tremOpt["9"].input;
            for (var name in opts) {
                var opt = $("<option></option>");
                opt.val(name);
                opt.html(opts[name]);
                $("[name=shopid]").append(opt);
                $("[name=destshopid]").append(opt.clone());
            }
            //获得标题
            $(".ordername").html(json.orderTypeArray[$(".ordertype").val() - 1]);
        });
    }

    //文本下拉框
    function setSelinputEvt(span) {
        //alert(1);
        if (!span[0].hasAttribute("change")) {
            span.attr("change", "");
            span.find(".textbox-text").change(function () {
                var value = span.find(".textbox-value").val();
                if (!value || value.length == 0) return;
                $.getJSON("/Vip/getByName", {"name": value}, function (result) {
                    var opts = $(".combo-panel");
                    opts.empty();
                    for (var i = 0; i < result.length; i++) {
                        var obj = result[i];
                        //alert(JSON.stringify(obj));
                        var opt = $("<div id='_easyui_combobox_i1_0' " +
                            "class='combobox-item combobox-item-selected' " +
                            "val='" + obj.id + "' " +
                            "style='display: block;'>" + obj.name + "</div>");
                        opts.append(opt);
                        opt.click(function () {
                            span.find(".inp-val").val(obj.id);
                            span.find(".textbox-text").val(obj.name);
                            span.find(".textbox-value").val(obj.name);
                            opt.attr("selected", "selected");

                            $("#order-viptel").find("input").val(obj.tel);
                            $("#order-vipamount").find("input").val(obj.amount);
                            $("#order-vippoint").find("input").val(obj.point);
                            $("#order-vipaddr").find("input").val(obj.addr);

                        });
                    }
                });
            });
        }
    }

    //各个表单隐藏与显示情况
    function aboutHidden() {
        var orderType = $(".orderType").val();
        if (orderType == 1 || orderType == 2) {
            $("#order-amount").find("input").removeAttr("disabled");
            $("#order-point").find("input").removeAttr("disabled");
            $("#order-clientid").css("display", "none");
            $("#order-clientid").find("select").removeAttr("name");
            $("#order-destshopid").css("display", "none");
            $("#order-destshopid").find("select").removeAttr("name");
            $("#order-clientlxr").css("display", "none");

        }
        else if (orderType == 3 || orderType == 4) {
            $("#order-shopid").css("display", "none");
            $("#order-shopid").find("select").removeAttr("name");
            $("#order-vipname").css("display", "none");
            $("#order-vipname").find("input").removeAttr("name");
            $("#order-vipamount").css("display", "none");
            $("#order-vippoint").css("display", "none");
        }
        else if (orderType == 5 || orderType == 6) {
            $("#order-destshopid").css("display", "none");
            $("#order-destshopid").removeAttr("name");
            $("#order-clientid").css("display", "none");
            $("#order-clientid").removeAttr("name");
            $("#order-clientlxr").css("display", "none");
            var temp = $("<span id='order-money' style='position: relative;left:150px;top: 60px;'><span class='con-span'>现金支付</span><input class='trem-input in-line' type='text' name='money'></span>");
            $(".first-trem>div").append(temp);
        }
        else if (orderType == 7 || orderType == 8 || orderType == 9) {

            $("#order-vipname").css("display", "none");
            $("#order-vipname").removeAttr("name");
            $("#order-clientlxr").css("display", "none");
            $("#order-viptel").css("display", "none");
            $("#order-vipamount").css("display", "none");
            $("#order-vippoint").css("display", "none");
            $("#order-vipaddr").css("display", "none");
            $("#order-clientid").css("display", "none");
            $("#order-clientid").removeAttr("name");
            if (orderType == 8 || orderType == 9) {
                $("#order-destshopid").css("display", "none");
                $("#order-destshopid").removeAttr("name");
            }
        }
        else if (orderType == 10 || orderType == 11) {
            $("#order-destshopid").css("display", "none");
            $("#order-destshopid").removeAttr("name");
            $("#order-clientid").css("display", "none");
            $("#order-clientid").removeAttr("name");
            $("#order-clientlxr").css("display", "none");
        }
    }

</script>
</body>
</html>
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
            width: 12%;
            padding-left: 5px;
            padding-right: 5px;
        }

        textarea {
            vertical-align: top;
            resize: none;
            width: 89%;
            height: 80px;
            box-sizing: content-box;
            margin-top: 7px;
        }

        .conditions .con-span {
            text-align: center;
            width: 7%;
        }

        .textBox {
            width: 12%;
            margin-right: 0px;
        }

        .tb-text {
            width: 90%;
            border: 0px;
            padding-left: 5px;
            padding-right: 0px;
            outline: none;
        }

        .tb-arrow {
            position: absolute;
            right: 0%;
            top: 0;
            bottom: 0;
            height: 100%;
            width: 10%;
        }

        .tb-opts {
            position: absolute;
            left: 0%;
            background-color: rgba(255, 255, 255, 1);
            border: 1px solid rgba(150, 150, 150, 1);
            overflow-y: auto;
            overflow-x: hidden;
            max-height: 120px;
        }

        .tb-opt {
            width: 100%;
            height: 15px;
            padding-top: 5px;
            padding-bottom: 5px;
        }

        .tb-opt:hover {
            background-color: blue;
            color: #fff;
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
<input type="hidden" class="orderid" value="${param.orderid}">
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
            <c:if test="${param.ordertype==1||param.ordertype==2||
                            requestScope.object.ordertype==1||
                            requestScope.object.ordertype==2}">
            </c:if>
            <%--采购单--%><%--采购退货单--%>
            <c:if test="${param.ordertype==3||param.ordertype==4||
                            requestScope.object.ordertype==3||
                            requestScope.object.ordertype==4}">
                <th field="procode" width="12%">商品编号</th>
                <th field="proname" width="12%">商品名称</th>
                <th field="count" width="12%">数量</th>
                <th field="price" width="12%">售价</th>
                <th field="amount" width="12%">金额小计</th>
                <th field="fexp" width="12%" editor="text">明细备注</th>
            </c:if>
            <%--销售单--%><%--销售退货单--%>
            <c:if test="${param.ordertype==5||param.ordertype==6||
                            requestScope.object.ordertype==5||
                            requestScope.object.ordertype==6}">
                <th field="procode" width="12%">商品编号</th>
                <th field="proname" width="12%">商品名称</th>
                <th field="count" width="12%">数量</th>
                <th field="price" width="12%">售价</th>
                <th field="amount" width="12%">金额小计</th>
                <th field="point" width="12%">积分小计</th>
                <th field="fexp" width="12%" editor="text">明细备注</th>
            </c:if>
            <%--移库单--%><%--库损单--%><%--库溢单--%>
            <c:if test="${param.ordertype==7||param.ordertype==8||param.ordertype==9||
                            requestScope.object.ordertype==7||
                            requestScope.object.ordertype==8||
                            requestScope.object.ordertype==9}">
                <th field="procode" width="12%">商品编号</th>
                <th field="proname" width="12%">商品名称</th>
                <th field="count" width="12%" editor="numberbox">数量</th>
                <th field="fexp" width="12%" editor="text">明细备注</th>
            </c:if>
            <%--项目充值单--%><%--项目退款单--%>
            <c:if test="${param.ordertype==10||param.ordertype==11||
                        requestScope.object.ordertype==10||
                        requestScope.object.ordertype==11}">
                <th field="procode" width="12%">商品编号</th>
                <th field="proname" width="12%">商品名称</th>
                <th field="count" width="12%">数量</th>
                <th field="amount" width="12%">金额小计</th>
                <th field="point" width="12%">积分小计</th>
                <th field="fexp" width="12%" editor="text">明细备注</th>
            </c:if>
        </tr>
        </thead>
    </table>
    <div id="tb" style="padding:30px;position: relative">

        <div class="ordername" style="width: 100%;text-align: center;font-size: 30px;"></div>
        <div class="buttons" style="position: absolute;right: 75px;top: 30px;">
            <a onclick="add();" class="easyui-linkbutton float-right"
               id="add-detail" iconCls="icon-save"
               data-options="selected:true">添加一行</a>
            <input class="trem-input in-line tremName float-right"
                   type="text" id="name-detail" placeholder="商品名称"
                   style="margin-right: 5px;width: 20%">
            <a onclick="save();" class="easyui-linkbutton float-right"
               id="save"
               iconCls="icon-save"
               data-options="selected:true">保存</a>
            <a onclick="prog();" class="easyui-linkbutton a-program float-right"
               iconCls="icon-list" id="program-detail"
               data-options="selected:true">项目</a>
            <div class="float-clear"></div>
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
                        <input type="hidden" class="inpval" value="${requestScope.object.shopid}">
                        <select name="shopid"></select>
                    </span>

                    <span id="order-destshopid">
                        <span class="con-span">目的分店</span>
                        <input type="hidden" class="inpval" value="${requestScope.object.destshopid}">
                        <select name="destshopid"></select>
                    </span>

                    <span id="order-clientid">
                        <span class="con-span">供应商</span>
                        <input type="hidden" class="inpval" value="${requestScope.object.clientid}">
                        <select name="clientid"></select>
                    </span>

                    <span id="order-vipname" onclick="setSelinputEvt($(this));">
                        <span class="con-span">会员名称</span>
                        <input type="hidden" name="vipid" class="inp-val">
                        <span class="textBox" id="tb-vipname">
                            <input type="text" class="tb-text">
                            <a class="textbox-icon combo-arrow tb-arrow"
                               href="javascript:;" onclick="disOpts($(this))"></a>
                        </span>
                    </span>
                    <%--绝对定位下拉列表--%>
                    <div class="tb-opts" tbid="tb-vipname">
                        <%--<div class="tb-opt"></div>--%>
                    </div>

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

                    <span id='order-money' class="none">
                        <span class='con-span'>现金支付</span>
                        <input class='trem-input in-line' type='number' name2='money'>
                    </span>

                    <div id="order-fexp">
                        <span class="con-span">备注</span>
                        <textarea class="trem-textarea in-line" name="fexp"></textarea>
                    </div>
                </div>
                <%-----------------------------------------------------------------------------%>
            </div>
        </form>
    </div>
</div>


<script type="text/javascript">
    var hastable = true;

    //后台获得资料，方便以后调用
    var rows = [];//表格总数据
    var List = [];//总集合
    var ajaxJson = [];//数据库
    var details = [];//销售物品
    var progs = [];//项目赠品

    //传送数据获取表格信息
    function tableData(form) {
        if ($(".orderid").val()) {
            var data = {
                "attrName": "orderid",
                "o": $(".orderid").val()
            };
            $.ajax({
                type: "POST",
                url: "/Orderdetail/selectByAttrLS",
                dataType: "json",
                data: data,
                async: false,
                success: function (json) {
                    ajaxJson = json.list;
                }
            });
        }
        resetDg();
    }

    //重新添加表格数据
    function resetDg() {
        //整合三方数据
        rows = [];
        List = ajaxJson.concat(details).concat(progs);
        var proglen = ajaxJson.length + details.length;
        //showMsg(JSON.stringify(List))
        for (var i = 0; i < List.length; i++) {
            var orderdetail = List[i];
            rows.push({
                id: orderdetail.id,
                proid: orderdetail.productid.id,
                procode: orderdetail.productid.code,
                proname: orderdetail.productid.name,
                count: orderdetail.count,
                price: orderdetail.price,
                amount: (i >= proglen) ? 0 : orderdetail.amount,
                point: (i >= proglen) ? 0 : orderdetail.point,
                fexp: orderdetail.fexp
            });
        }
        //有项目，不能改人
        if (progs.length > 0) {
            $("#tb-vipname").find(".tb-text").attr("disabled", "disabled");
            $("select[name=shopid]").attr("disabled", "disabled");
        }
        $(".datagrid-body").find("tbody").remove();
        $('#dg').datagrid({data: rows}).datagrid('clientPaging');
        $('#dg').datagrid('reload');
    }

    $(function () {
        getSelects();
        addHidden();
        var orderid = $(" .orderid").val();
        if (orderid != "") {
            var data = {"id": orderid}
            //填充上部信息
            $.ajax({
                url: "/Ordermain/selectById",
                type: "POST",
                dataType: "json",
                async: false,
                data: data,
                success: function (ordermain) {
                    if ($("#order-code")[0]) $("#order-code").find("input").val(ordermain.ordercode);
                    if ($("#order-date")[0]) $("#order-date").find("input").val(ordermain.orderdate);
                    if ($("#order-shopid")[0]) $("#order-shopid").find("select").find("[value=" + ordermain.shopid.id + "]").attr("selected", "selected");
                    if ($("#order-destshopid")[0]) $("#order-destshopid").find("select").find("[value=" + ordermain.destshopid.id + "]").attr("selected", "selected");
                    if ($("#order-clientid")[0]) $("#order-clientid").find("select").find("[value=" + ordermain.clientid.id + "]").attr("selected", "selected");
                    if ($("#order-vipname")[0]) $("#order-vipname").find(".inp-val").val(ordermain.vipid.id);
                    if ($("#order-vipname")[0]) $("#order-vipname").find(".tb-text").val(ordermain.vipid.name);
                    if ($("#order-viptel")[0]) $("#order-viptel").find("input").val(ordermain.vipid.tel);
                    if ($("#order-clientlxr")[0]) $("#order-clientlxr").find("input").val(ordermain.clientid.lxr);
                    if ($("#order-vipamount")[0]) $("#order-vipamount").find("input").val(ordermain.vipid.amount);
                    if ($("#order-vippoint")[0]) $("#order-vippoint").find("input").val(ordermain.vipid.point);
                    if ($("#order-vipaddr")[0]) $("#order-vipaddr").find("input").val(ordermain.vipid.addr);
                    if ($("#order-amount")[0]) $("#order-amount").find("input").val(ordermain.amount);
                    if ($("#order-point")[0]) $("#order-point").find("input").val(ordermain.point);
                    /*if($("#order-money")[0])        $("#order-money").find("input").val(ordermain.point);*/
                    if ($("#order-fexp")[0]) $("#order-fexp").find("textarea").text(ordermain.fexp);
                }
            });
            if (hastable) {
                //填充表格
                $.ajax({
                    type: "POST", url: "/Orderdetail/selectByAttrLS", async: false, dataType: "json", data: {
                        "attrName": "orderid",
                        "o": $(".orderid").val()
                    }, success: function (json) {
                        ajaxJson = json.list;
                    }
                });
            }
        }
        resetDg();
        addTextSelect();
    });


    //获得查询条件下拉列表
    function getSelects() {
        $.ajax({
            url: "/Ordermain/getStatus", async: false, success: function (json) {
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
                        async: false,
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
            }
        });
    }

    //文本下拉框
    function setSelinputEvt(span) {
        //alert(1);
        if (!span[0].hasAttribute("change")) {
            span.attr("change", "");
            span.find(".tb-text").change(function () {
                var value = span.find(".tb-text").val();
                var opts = $(".tb-opts");
                opts.empty();
                if (!value) return;
                $.getJSON("/Vip/getByName", {"name": value}, function (result) {
                    for (var i = 0; i < result.length; i++) {
                        var obj = result[i];
                        var opt = $("<div " +
                            "class='tb-opt' " +
                            "obj-id='" + obj.id + "' " +
                            "obj-tel='" + obj.tel + "' " +
                            "obj-amount='" + obj.amount + "' " +
                            "obj-point='" + obj.point + "' " +
                            "obj-addr='" + obj.addr + "' " +
                            ">" + obj.name + "</div>");
                        opt.click(function () {
                            span.find(".inp-val").val($(this).attr("obj-id"));
                            span.find(".tb-text").val($(this).html());

                            $("#order-viptel").find("input").val($(this).attr("obj-tel"));
                            $("#order-vipamount").find("input").val($(this).attr("obj-amount"));
                            $("#order-vippoint").find("input").val($(this).attr("obj-point"));
                            $("#order-vipaddr").find("input").val($(this).attr("obj-addr"));
                        });
                        opts.append(opt);
                    }
                });
            });
        }
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
                    //details = [];
                    var pro = pros[0];
                    var urlpart = "";
                    for (var i = 0; i < pros.length; i++) {
                        var obj = pros[i];
                        urlpart += "proid=" + obj.id + "&proname=" + obj.name + "&";
                    }
                    showPage("增加盘点商品", "/table/Ordermain/addpro.jsp?" + urlpart.substring(0, urlpart.length - 1),
                        700, 300,
                        function () {
                            resetDg();
                        }, false, true);
                }
            }
        });
    }

    function save() {
        if(!$("[name=ordercode]").val()
        || !$("[name=orderdate]").val()
        ||($("#order-vipname")&&!$("#tb-vipname").find(".tb-text").val())){
            showMsg("信息不全！");
            return;
        }
        //对会员进行确认
        var hasVip=true;
        var viptext=$("#tb-vipname").find(".tb-text").val();
        var vipid=$("#order-vipname").find("[name=vipid]").val();
        if($("#order-vipname")){
            $.ajax({
                url:"/Vip/selectByAttr",
                data:{"attrName":"name","object":viptext},
                dataType:"JSON",
                type:"POST",
                async:false,
                success:function (vip) {
                    if(vip.length<=0){
                        showMsg("此会员不存在");
                        hasVip=false;
                        return;
                    }else{
                        $("#order-vipname").find("[name=vipid]").val(vip[0].id);
                        $("#order-viptel").find("input").val(vip[0].tel);
                        $("#order-vipamount").find("input").val(vip[0].amount);
                        $("#order-vippoint").find("input").val(vip[0].point);
                        $("#order-vipaddr").find("input").val(vip[0].addr);
                    }
                }
            });
        }
        if(!hasVip) return;

        $("[name=shopid]").attr("disabled", false);
        $("[name=shopid]").attr("name", "shopid.id");
        $("[name=destshopid]").attr("name", "destshopid.id");
        $("[name=userid]").attr("name", "userid.id");
        $("[name=vipid]").attr("name", "vipid.id");
        $("[name=clientid]").attr("name", "clientid.id");
        $("[name2=money]").attr("name", "vipamount");
        $("[name2=money]").val(-$("[name2=money]").val());
        var formDate = $('.form').serializeObject();//主表数据
        var action = "";
        var mainId;
        if ($(".orderid").val()) {
            action = "/Ordermain/update1";
            mainId = $(".orderid").val();
        }
        else action = "/Ordermain/insert1";
        //传主表
        $.ajax({
            url: action,
            type: "POST",
            async: false,
            dataType: "JSON",
            data: formDate,
            success: function (json) {
                if (json.status == 1 && !$(".orderid").val())
                    mainId = json.info;
                else showMsg(json.info);
            }
        });
        if (hastable) {
            //alert(mainId)
            $(".orderid").val(mainId);
            var rows = $('#dg').datagrid("getRows");//获得表格内所有数据
            for (var i = 0; i < rows.length; i++) {
                var rowData = rows[i];//获取单行数据
                //alert(rowData.proid);
                var data = {
                    id: rowData.id,
                    "orderid.id": mainId,
                    "productid.id": rowData.proid,
                    "shopid.id": rowData.shopid,
                    count: rowData.count,
                    price: rowData.price,
                    amount: rowData.amount,
                    fexp: rowData.fexp
                };
                var action2 = "/Ordermain/insert2";
                if (rowData.id) {
                    action2 = "/Ordermain/update2"
                    //if(rowData.amount==0) action2 = "/Ordermain/update3"
                }
                $.ajax({
                    url: action2,
                    async: false,
                    type: "POST",
                    dataType: "JSON",
                    data: data,
                    success: function (jsondls) {
                        if (jsondls.status == 1) {
                            if (jsondls.info)
                                window.rows[i].id = jsondls.info;//将最新id给相应行
                        }
                        else {
                            showMsg(jsondls.info);
                            return;
                        }
                    }
                });
            }
            resetDg();//重写表格内容
        }
        showMsg("保存完成");
        closePage();
    }

    function addHidden() {
        var type = $(".ordertype").val();
        if (type == 1 || type == 2) {
            hastable = false;
            $("#name-detail").remove();
            $("#add-detail").remove();
            $("#program-detail").remove();

            $("#order-amount").find("input").removeAttr("disabled");
            $("#order-point").find("input").removeAttr("disabled");
            $("#order-clientid").remove();
            $("#order-destshopid").remove();
            $("#order-clientlxr").remove();
        } else if (type == 3 || type == 4) {
            $("#program-detail").remove();

            $("#order-shopid").remove();
            $("#order-vipname").remove();
            $("#order-vipamount").remove();
            $("#order-vippoint").remove();
        } else if (type == 5 || type == 6) {
            $("#order-destshopid").remove();
            $("#order-clientid").remove();
            $("#order-clientlxr").remove();
            var subinp = $("#order-money").removeClass("none").find(".trem-input");
            subinp.attr("name", subinp.attr("name2"));
        } else if (type == 7 || type == 8 || type == 9) {
            $("#program-detail").remove();

            $("#order-vipname").remove();
            $("#order-clientlxr").remove();
            $("#order-viptel").remove();
            $("#order-vipamount").remove();
            $("#order-vippoint").remove();
            $("#order-vipaddr").remove();
            $("#order-clientid").remove();
            if (type == 8 || type == 9) $("#order-destshopid").remove();
        } else if (type == 10 || type == 11) {
            $("#program-detail").remove();

            $("#order-destshopid").remove();
            $("#order-clientid").remove();
            $("#order-clientlxr").remove();
            /*$("#order-amount").find("input").removeAttr("disabled");
            $("#order-point").find("input").removeAttr("disabled");*/
        }
        //对所有选项进行分组
        var $spanDiv = $(".search-trem>div");
        var $spans = $spanDiv.children("span");
        var divpart;
        for (var i = 0; i < $spans.length; i++) {
            var span = $spans.eq(i);
            //每行五个
            if (i % 5 == 0) {
                divpart = $("<div></div>");
                $spanDiv.append(divpart)
            }
            divpart.append(span)
        }
        //单独添加注释
        $spanDiv.append($("#order-fexp"));
    }

    function addTextSelect() {
        var optsArray = $(".tb-opts");
        for (var i = 0; i < optsArray.length; i++) {
            var opts = optsArray.eq(i);
            var tb = $("[id=" + opts.attr("tbid") + "]");
            opts.css({
                "width": tb.innerWidth(),
                "left": getBodyLeft(tb[0]),
                "top": getBodyTop(tb[0]) + tb.innerHeight()/*+tb.siblings(".con-span").height()*/
            });
            opts.addClass("none");
            tb.find(".tb-text").blur(function () {
                $(document).on('click', function (e) {
                    if ($(e.target).parent(".textBox").length <= 0
                        || $(e.target).parent(".textBox").attr("id") != opts.attr("tbid"))
                        opts.addClass("none");
                });
            });
            $("body").append(opts);
        }
    }

    function disOpts(a) {
        if (a.siblings(".tb-text").attr("disabled") == "disabled") return;
        a.siblings(".tb-text")[0].focus();
        var tbid = a.parent(".textBox").attr("id");
        var opts = $("[tbid=" + tbid + "]");
        if (opts.hasClass("none")) opts.removeClass("none");
        else opts.addClass("none");
        var tb = $("[id=" + opts.attr("tbid") + "]");
        opts.css({
            "width": tb.innerWidth(),
            "left": getBodyLeft(tb[0]),
            "top": getBodyTop(tb[0]) + tb.innerHeight()
        });
    }

    //打开项目
    function prog() {
        var url = "/table/Ordermain/prog.jsp" +
            "?ordertype=" + $(".ordertype").val() +
            "&shop=" + $("[name=shopid]").val();
        var vip = $("[name=vipid]").val();
        if (vip) url += "&vip=" + vip;
        else {
            showMsg("请选择会员");
            return;
        }
        for (var i = 0; i < rows.length; i++) {
            var rid = rows[i].id;
            url += "&id=" + rid;
        }
        showPage("选择项目",
            url,
            800, 400, function () {
                tableData();
            }, false, false)
    }
</script>
</body>
</html>
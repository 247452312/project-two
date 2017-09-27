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
        .none{
            display: none;
        }
        .block{
            display: block;
        }
        .in-line{
            display: inline;
        }
        select,input{
            height:35px;
            width:150px;
            padding-left: 5px;
            padding-right: 5px;
        }
        textarea{
            vertical-align: top;
            resize: none;
            width: 624px;
            height: 100px;
            box-sizing:content-box;
            margin-top: 7px;
        }
        .conditions .con-span{
            text-align: center;
            width: 80px;
        }
        .textbox{
            margin-right: 0px;
        }
        td .textbox{
            border: 0px;
        }
        .datagrid-view .datagrid-editable-input,
        .textbox .textbox-text{
            border: 0px solid #fff;
        }
    </style>
</head>
<body>
<div id="test"></div>
<div class="container">
    <input type="hidden" class="ordertype" name="ordertype"
        value="${param.ordertype==null?
        (requestScope.object.ordertype==null?9:requestScope.object.ordertype)
        :param.ordertype}">
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
            pageSize:50
            ">
        <thead>
        <tr>
            <%--充值单--%><%--退款单--%>
            <c:if test="${param.ordertype==1||requestScope.object.ordertype==1||
                            param.ordertype==2||requestScope.object.ordertype==2}">
                <script type="text/javascript">
                </script>
            </c:if>
            <%--采购单--%><%--采购退货单--%>
            <c:if test="${param.ordertype==3||requestScope.object.ordertype==3||
                            param.ordertype==4||requestScope.object.ordertype==4}">
                <script type="text/javascript">
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
            <a onclick="save();" class="easyui-linkbutton a-program" iconCls="icon-list"
               data-options="selected:true">项目</a>
            <a onclick="save();" class="easyui-linkbutton" iconCls="icon-save"
               data-options="selected:true">保存</a>
        </div>
        <form action="" method="post" class="form">
            <input type="hidden" name="pageno">
            <input type="hidden" name="maxrow">
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
                        <select name="clientid"></select>
                    </span>

                    <span id="order-vipname" onclick="setSelinputEvt($(this));">
                        <span class="con-span">会员名称</span>
                        <input type="hidden" name="vipid" class="inp-val">
                        <select class="easyui-combobox" style="height:35px;"></select>
                    </span>

                    <span id="order-viptel">
                        <span class="con-span">会员电话</span>
                        <input class="trem-input in-line" type="text" disabled="disabled">
                    </span>

                    <span id="order-vipamount">
                        <span class="con-span">会员余额</span>
                        <input class="trem-input in-line" type="text" disabled="disabled">
                    </span>

                    <span id="order-vippoint">
                        <span class="con-span">会员积分</span>
                        <input class="trem-input in-line" type="text" disabled="disabled">
                    </span>

                    <span id="order-vipaddr">
                        <span class="con-span">会员地址</span>
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
                        <textarea class="trem-textarea in-line"  name="fexp"></textarea>
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
        $("#dg").datagrid({
            onDblClickRow:function(rowIndex, rowData){
                //alert(JSON.stringify(rowData));
                //编辑本行资料
                showPage("修改单据","/Ordermain/updat?id="+rowData.id,1000,400,function(){
                    tableData();
                },true,false);
            }
        });
        $('#dg').datagrid('reload');
    }

    //打开新增页面
    function addPage() {
        showPage("新增单据","/Ordermain/ad",1000,400,function(){
            tableData();
        },true,false);
    }

    $(function () {
        getSelects();
        resetDg();
        //tableData();
    });
    //获得查询条件下拉列表
    function getSelects() {
        $.getJSON("/Ordermain/getStatus", function (json) {
            var tremOpt = json.ordermainInput;
            var opts;
            //获得client查询变量
            opts=tremOpt["7"].input;
            for(var name in opts){
                var opt = $("<option></option>");
                opt.val(name);
                opt.html(opts[name]);
                $("[name=clientid]").append(opt);
            }
            //获得shop查询变量
            opts=tremOpt["9"].input;
            for(var name in opts){
                var opt = $("<option></option>");
                opt.val(name);
                opt.html(opts[name]);
                $("[name=shopid]").append(opt);
                $("[name=destshopid]").append(opt.clone());
            }
            //获得标题
            $(".ordername").html(json.orderTypeArray[$(".ordertype").val()-1]);
        });
    }
    //文本下拉框
    function setSelinputEvt(span) {
        //alert(1);
        if(!span[0].hasAttribute("change")){
            span.attr("change","");
            span.find(".textbox-text").change(function () {
                var value=span.find(".textbox-value").val();
                if(!value || value.length==0) return;
                $.getJSON("/Vip/getByName",{"name":value},function (result) {
                    var opts=$(".combo-panel");
                    for (var i = 0; i < result.length; i++) {
                        var obj = result[i];
                        //alert(JSON.stringify(obj));
                        var opt=$("<div id='_easyui_combobox_i1_0' " +
                            "class='combobox-item combobox-item-selected' " +
                            "val='"+obj.id+"' " +
                            "style='display: block;'>"+obj.name+"</div>");
                        opt.click(function () {
                            span.find(".inp-val").val(opt.attr("val"));
                        });
                        opts.append(opt);
                    }
                });
            });
        }
    }
</script>
</body>
</html>
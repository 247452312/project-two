<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>库存初始</title>

    <script type="text/javascript" src="/custom/jquery.min.js"></script>
    <script type="text/javascript" src="/custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/custom/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/selfFunction.js"></script>
    <script type="text/javascript" src="/js/layer/layer.js"></script>
    <script type="text/javascript" src="/js/basic.js"></script>
    <script type="text/javascript" src="/js/jquery.jqprint-0.3.js"></script>
    <script type="text/javascript" src="/js/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="/js/easyuiTable.js"></script>

    <link rel="stylesheet" href="/css/base.css">
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
        .datagrid-view .datagrid-editable-input,
        .textbox .textbox-text{
            border: 0px solid #fff;
        }
        .textbox{
            border: 0px;
        }
    </style>
</head>
<body>
<div id="test"></div>
<div class="container">
    <table id="dg" style="width:100%;height:529px" title="库存初始" data-options="
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
            onClickCell:onClickCell<%--,
            onAfterEdit:onAfterEdit--%>">
        <thead>
        <tr>
            <th field="code"
                width="20%">商品编码</th>
            <th field="name"
                width="20%">商品姓名</th>
            <th field="status"
                width="20%">商品状态</th>
            <th field="count" editor="numberbox"
                width="20%">库存数量</th>
            <th field="cbprice" editor="numberbox"
                width="20%">库存成本价格</th>
        </tr>
        </thead>
    </table>

    <div id="tb" style="padding:0 30px;">
        <input type="hidden" name="where">
        <form action="" method="post" class="form" style="overflow-y: auto;overflow-x:hidden;max-height: 140px;">
            <div class="conditions search-trem first-trem">
                <select style="height:35px;width:160px;" name="shopid" class="select"
                        onchange="tableData();"></select>
                <a onclick="save();" class="easyui-linkbutton a-select" iconCls="icon-save"
                   data-options="selected:true">保存</a>
                <a onclick="tableData();" class="easyui-linkbutton a-select" iconCls="icon-reload"
                   data-options="selected:true">刷新</a>
                <a onclick="tablePrint();" class="easyui-linkbutton a-select"iconCls="icon-print"
                   data-options="selected:true">打印</a>
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
    function tableData() {
        rows = [];
        var data = $(".form").serializeArray();
        if(!$(".select").val()) data={"shopid":0};
        //获取所有商品（换页相关）
        $.getJSON("/Product/selectByAll", {"pageno":$(".pagination-num").val(),"maxrow":$(".pagination-page-list").val()}, function (pros) {
            //获取库存数据（查询form）相关
            $.ajax({type: "POST", url: "/Store/ready", dataType: "json", data: data, success: function (stores) {
                for (var i = 0; i < pros.list.length; i++) {
                    //alert(1);
                    var pro = pros.list[i];//商品行
                    var ste={};
                    //获得库存数据
                    for (var j = 0; j < stores.list.length; j++) {
                        var store=stores.list[j];//库存数据
                        if(store.productid.id==pro.id) ste=store;
                    }
                    rows.push({
                        id: ste.id?ste.id:0,
                        proid: pro.id,
                        shopid: $(".select").val(),
                        code: pro.code,
                        name: pro.name,
                        status: pro.statusString,
                        count: ste.count?ste.count:0,
                        cbprice:ste.cbprice?ste.cbprice:0,
                        defprice:ste.defprice
                    });
                }
                //页数相关赋值
                var jsea=pros.sea;
                $("[name=where]").val(jsea.where);
                $(".rowcount").html(jsea.rowcount);
                $(".pagecount").html(jsea.pagecount);
                $(".pagination-num").val(jsea.pageno);
                $(".pagination-page-list").val(jsea.maxrow);
                //点击事件
                $("#page-first").attr("onclick","{$('.pagination-num').val("+1+");tableData();}");
                $("#page-prev").attr("onclick","{$('.pagination-num').val("+pros.sea.prepage+");tableData();}");
                $("#page-next").attr("onclick","{ $('.pagination-num').val("+pros.sea.nextpage+");tableData();}");
                $("#page-last").attr("onclick","{ $('.pagination-num').val("+pros.sea.pagecount+");tableData();}");
                //换条数事件
                $(".pagination-page-list").attr("onchange","{$('.pagination-num').val("+1+");tableData();}");
                //换页数事件
                $(".pagination-num").attr("onchange","{tableData();}");
                resetDg();
            }});
        });
    }
    //重新添加表格数据
    function resetDg() {
        $(".datagrid-body").find("tbody").remove();
        $('#dg').datagrid({data: rows}).datagrid('clientPaging');
        $('#dg').datagrid('reload');
    }
    $(function () {
        getSelects();
    });
    //获得查询条件下拉列表
    function getSelects() {
        var select=$(".select");
        $.getJSON("/Store/getStatus",function (pros) {
            var textOpt = pros.vipInput["11"].input;//下拉框集合
            for (var name in textOpt) {
                var opt = $("<option></option>");
                opt.val(name);
                opt.html(textOpt[name]);
                select.append(opt);
            }
            tableData();
        });
        select.val(0);
    }
    //保存表格内容
    function save() {
        var rows=$('#dg').datagrid("getRows");//获得表格内所有数据
        for (var i = 0; i < rows.length; i++) {
            var rowData = rows[i];//获取单行数据
            var data={
                "cmd":"update",
                "id":rowData.id,
                "productid.id":rowData.proid,
                "shopid.id":rowData.shopid,
                "count":rowData.count,
                "cbprice":rowData.cbprice,
                "defprice":rowData.defprice
            };
            $.ajax({url: "/Store/ajax",async:false,type: "POST", dataType: "json", data:data,
                success: function (json) {if (json.status != 1) showMsg(json.info);return;}
            });
            showMsg("保存完成");
        }
    }
</script>
</body>
</html>

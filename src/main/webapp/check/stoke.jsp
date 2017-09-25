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
            pageSize:50
            ">
        <thead>
        <tr>
            <th field="code" width="15%">商品编码</th>
            <th field="name" width="25%">商品姓名</th>
            <th field="status" width="20%">商品状态</th>
            <th field="count" width="15%">库存数量</th>
            <th field="cbprice" width="15%">库存成本价格</th>
        </tr>
        </thead>
    </table>

    <div id="tb" style="padding:0 30px;">
        <input type="hidden" name="where">
        <form action="" method="post" class="form" style="overflow-y: auto;overflow-x:hidden;max-height: 140px;">
            <input type="hidden" name="pageno">
            <input type="hidden" name="maxrow">
            <div class="conditions search-trem first-trem">
                <select style="height:35px;width:160px;" name="shopid" class="select"
                        onchange="tableData($('.form'));"></select>
                <a onclick="tableData();" class="easyui-linkbutton a-select" iconCls="icon-reload"
                   style="padding-left: 5px;padding-right: 5px;" data-options="selected:true">刷新</a>
                <%--<a onclick="tablePrint();" class="easyui-linkbutton a-select"iconCls="icon-print"
                   style="padding-left: 5px;padding-right: 5px;" data-options="selected:true">打印</a>--%>
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
            data = form.serializeArray();
        }else{//换页时
            data={
                "where":$("[name=where]").val(),
                "pageno":$(".pagination-num").val(),
                "maxrow":$(".pagination-page-list").val()
            };
        }
        $.ajax({
            type: "POST", url: "/Store/ready", dataType: "json", data: data, success: function (json) {
                for (var i = 0; i < json.list.length; i++) {
                    var store = json.list[i];
                    //alert(JSON.stringify(store));
                    rows.push({
                        id: store.id,
                        code: store.productid.code,
                        name: store.productid.name,
                        status: store.productid.statusString,
                        count: store.count,
                        cbprice:store.cbprice
                    });
                }
                //页数相关赋值
                var jsea=json.sea;
                //alert(JSON.stringify(jsea));
                if(form){
                    $("[name=where]").val(jsea.where);
                };
                $(".rowcount").html(jsea.rowcount);
                $(".pagecount").html(jsea.pagecount);
                $(".pagination-num").val(jsea.pageno);
                $(".pagination-page-list").val(jsea.maxrow);
                //$("#dg").attr("data-options","pageSize:"+jsea.maxrow);
                //点击事件
                $("#page-first").attr("onclick","{$('.pagination-num').val("+1+");tableData();}");
                $("#page-prev").attr("onclick","{$('.pagination-num').val("+json.sea.prepage+");tableData();}");
                $("#page-next").attr("onclick","{ $('.pagination-num').val("+json.sea.nextpage+");tableData();}");
                $("#page-last").attr("onclick","{ $('.pagination-num').val("+json.sea.pagecount+");tableData();}");
                //换条数事件
                $(".pagination-page-list").attr("onchange","{$('.pagination-num').val("+1+");tableData();}");
                //换页数事件
                $(".pagination-num").attr("onchange","{tableData();}");
                resetDg();
            }
        });
    }
    //重新添加表格数据
    function resetDg() {
        $(".datagrid-body").find("tbody").remove();
        $('#dg').datagrid({data: rows}).datagrid('clientPaging');
        $("#dg").datagrid({
            onDblClickRow:function(rowIndex, rowData){
                //编辑本行资料
                /*showPage("修改库存信息","Vip/updat?id="+rowData.id,1000,400,function(){
                    tableData();
                },true,false);*/
            }
        });
        $('#dg').datagrid('reload');
    }

    $(function () {
        resetDg();
        getSelects();
    });
    //获得查询条件下拉列表
    function getSelects() {
        var select=$(".select");
        $.getJSON("/Store/getStatus",function (json) {
            //获得vip查询变量
            var textOpt = json.vipInput["0"].input;//下拉框集合
            for (var name in textOpt) {
                var opt = $("<option></option>");
                opt.val(name);
                opt.html(textOpt[name]);
                $(".select").append(opt);
            }
        });
    }
</script>
</body>
</html>

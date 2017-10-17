<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加项目</title>

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
    </style>
</head>
<body>
<div id="test">
    <input type="hidden" class="ordertype" value="${param.ordertype}">
    <input type="hidden" class="vip" value="${param.vip}">
    <input type="hidden" class="shop" value="${param.shop}">
    <c:forEach items="${paramValues.id}" var="ids">
        <input type="hidden" class="id" value="${ids}">
    </c:forEach>
</div>
<div class="container">
    <table id="dg" style="width:100%;height:529px" title="可添加项目列表" data-options="
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
            pageSize:50">
        <thead>
        <tr>
            <th field="ck" checkbox="true" width="4%">选中</th>
            <th field="code" width="24%">商品编号</th>
            <th field="name" width="24%">商品名称</th>
            <th field="count" width="24%">商品数量</th>
            <th field="fexp" width="24%">备注</th>
        </tr>
        </thead>
    </table>

    <div id="tb" style="padding:0 30px;">
        <a onclick="save();" class="easyui-linkbutton a-select" iconCls="icon-save"
           data-options="selected:true">保存</a>
    </div>
</div>


<script type="text/javascript">
    //后台获得资料，方便以后调用
    var rows = [];
    var ajaxJson = [];
    var hasids = [];

    //传送数据获取表格信息
    function tableData() {
        for (var i = 0; i < $(".id").length; i++) {
            var id = parseInt($(".id").eq(i).val());
            hasids.push(id);
        }
        //alert(JSON.stringify(hasids));
        rows = [];
        var data = {
            "vipid": $(".vip").val(),
            "shopid": $(".shop").val()
        };
        $.ajax({
            type: "POST",
            url: "/Ordermain/selectProject",
            dataType: "json",
            async: false,
            data: data,
            success: function (json) {
                ajaxJson = json.list;
                for (var i = 0; i < json.list.length; i++) {
                    var detail = json.list[i];
                    //不包含的数据才能加入
                    if ($.inArray(detail.id, hasids) < 0) {
                        rows.push({
                            id: detail.id,
                            code: detail.productid.code,
                            name: detail.productid.name,
                            count: detail.count,
                            fexp: detail.fexp
                        });
                    }
                }
                resetDg();
            }
        });
    }

    //重新添加表格数据
    function resetDg() {
        $(".datagrid-body").find("tbody").remove();
        $('#dg').datagrid({data: rows}).datagrid('clientPaging');
        $('#dg').datagrid('reload');
        /*$('#dg').datagrid({
            onDblClickRow:function(rowIndex, rowData){
                alert(JSON.stringify(rowData));
            }
        });*/
    }

    $(function () {
        tableData();
    });

    //保存主表和子表
    function save(trans) {
        var rowDatas = $("#dg").datagrid("getChecked");
        for (var i = 0; i < rowDatas.length; i++) {
            //alert(JSON.stringify(ajaxJson[i]))
            window.parent.progs.push(ajaxJson[i]);
            $("#tb-vipname", window.parent.document).find(".tb-text").attr("disabled", "disabled");
            $("select[name=shopid]", window.parent.document).attr("disabled", "disabled");
        }
        closePage();
    }
</script>
</body>
</html>

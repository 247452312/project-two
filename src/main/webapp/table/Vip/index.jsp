<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="../../">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>供应商列表</title>

    <link href="css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="custom/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="custom/uimaker/icon.css">
    <link rel="stylesheet" href="css/providers1.css">
</head>
<body>
<div class="container">
    <table id="dg" style="width:100%;height:529px" title="全体供应商列表" data-options="
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
            <th field="code" width="4%">编码</th>
            <th field="name" width="4%">姓名</th>
            <th field="viptypeid" width="5%">类型</th>
            <th field="sex" width="4%">性别</th>
            <th field="addr" width="8%">家庭住址</th>
            <th field="tel" width="6%">固定电话</th>
            <th field="telmov" width="8%">移动电话</th>
            <th field="birthdate" width="6%">生日</th>
            <th field="ccode" width="10%">身份证号</th>
            <th field="shopid" width="5%">办理分店</th>
            <th field="amount" width="4%">总金额</th>
            <th field="point" width="4%">积分</th>
            <th field="status" width="4%">状态</th>
            <th field="createdate" width="10%">创建日期</th>
            <th field="userid" width="4%">创建人</th>
            <th field="jsr" width="4%">介绍人</th>
            <th field="fexp" width="9%">备注</th>
        </tr>
        </thead>
    </table>

    <div id="tb" style="padding:0 30px;">
        <input type="hidden" name="where">
        <form action="" method="post" class="form" style="overflow-y: auto;overflow-x:hidden;max-height: 140px;">
            <input type="hidden" name="pageno">
            <input type="hidden" name="maxrow">
            <div class="conditions search-trem first-trem">
                <select style="height:35px;width:166px;" name="trem"></select>
                <select style="height:35px;width:166px;" name="compare"></select>
                <input type="text" name="text" style="width:166px;height:35px;line-height:35px;"></input>
                <select style="height:35px;width:166px;" name="join">
                    <option value="0">并且</option>
                    <option value="1">或者</option>
                </select>
                <a onclick="removeTrem($(this));" class="easyui-linkbutton more" iconCls="icon-cancel">去除条件</a>
                <a onclick="addTrem($('.form'));" class="easyui-linkbutton more" iconCls="icon-add">添加条件</a>
                <a onclick="tableData($('.form'));" class="easyui-linkbutton a-select" iconCls="icon-search"
                   data-options="selected:true">查询</a>
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
<script type="text/javascript" src="../custom/jquery.min.js"></script>
<script type="text/javascript" src="../custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../custom/easyui-lang-zh_CN.js"></script>


<script type="text/javascript">
    (function ($) {
        function pagerFilter(data) {
            if ($.isArray(data)) {   // is array
                data = {
                    total: data.length,
                    rows: data
                }
            }
            var target = this;
            var dg = $(target);
            var state = dg.data('datagrid');
            var opts = dg.datagrid('options');
            if (!state.allRows) {
                state.allRows = (data.rows);
            }
            if (!opts.remoteSort && opts.sortName) {
                var names = opts.sortName.split(',');
                var orders = opts.sortOrder.split(',');
                state.allRows.sort(function (r1, r2) {
                    var r = 0;
                    for (var i = 0; i < names.length; i++) {
                        var sn = names[i];
                        var so = orders[i];
                        var col = $(target).datagrid('getColumnOption', sn);
                        var sortFunc = col.sorter || function (a, b) {
                            return a == b ? 0 : (a > b ? 1 : -1);
                        };
                        r = sortFunc(r1[sn], r2[sn]) * (so == 'asc' ? 1 : -1);
                        if (r != 0) {
                            return r;
                        }
                    }
                    return r;
                });
            }
            var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = state.allRows.slice(start, end);
            return data;
        }

        var loadDataMethod = $.fn.datagrid.methods.loadData;
        var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
        $.extend($.fn.datagrid.methods, {
            clientPaging: function (jq) {
                return jq.each(function () {
                    var dg = $(this);
                    var state = dg.data('datagrid');
                    var opts = state.options;
                    opts.loadFilter = pagerFilter;
                    var onBeforeLoad = opts.onBeforeLoad;
                    opts.onBeforeLoad = function (param) {
                        state.allRows = null;
                        return onBeforeLoad.call(this, param);
                    }
                    var pager = dg.datagrid('getPager');
                    pager.pagination({
                        onSelectPage: function (pageNum, pageSize) {
                            opts.pageNumber = pageNum;
                            opts.pageSize = pageSize;
                            pager.pagination('refresh', {
                                pageNumber: pageNum,
                                pageSize: pageSize
                            });
                            dg.datagrid('loadData', state.allRows);
                        }
                    });
                    $(this).datagrid('loadData', state.data);
                    if (opts.url) {
                        $(this).datagrid('reload');
                    }
                });
            },
            loadData: function (jq, data) {
                jq.each(function () {
                    $(this).data('datagrid').allRows = null;
                });
                return loadDataMethod.call($.fn.datagrid.methods, jq, data);
            },
            deleteRow: function (jq, index) {
                return jq.each(function () {
                    var row = $(this).datagrid('getRows')[index];
                    deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
                    var state = $(this).data('datagrid');
                    if (state.options.loadFilter == pagerFilter) {
                        for (var i = 0; i < state.allRows.length; i++) {
                            if (state.allRows[i] == row) {
                                state.allRows.splice(i, 1);
                                break;
                            }
                        }
                        $(this).datagrid('loadData', state.allRows);
                    }
                });
            },
            getAllRows: function (jq) {
                return jq.data('datagrid').allRows;
            }
        })
    })(jQuery);

    //添加查询条件
    function addTrem(divTrems) {
        var newdiv = $(".first-trem").clone();
        newdiv.find("input[type=text]").val("");
        newdiv.find("select").val(0);
        newdiv.removeClass("first-trem");
        newdiv.find(".a-select").remove();
        divTrems.append(newdiv);
        resetDg();
    }

    //删除查询条件
    function removeTrem(delTrem) {
        var pardiv = delTrem.parents(".search-trem");
        if (!pardiv.hasClass("first-trem")){
            pardiv.remove();
            resetDg();
        }
    }
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
            type: "POST", url: "Vip/selectByAll", dataType: "json", data: data, success: function (json) {
                for (var i = 0; i < json.list.length; i++) {
                    var vip = json.list[i];
                    rows.push({
                        code: vip.code,
                        name: vip.name,
                        shopid: vip.shopid.name,
                        viptypeid: vip.viptypeid.name,
                        sex: vip.sexString,
                        addr: vip.addr,
                        tel: vip.tel,
                        telmov: vip.telmov,
                        birthdate: vip.birthdate,
                        ccode: vip.ccode,
                        amount: vip.amount,
                        point: vip.point,
                        status: vip.statusString,
                        createdate: vip.createdate,
                        userid: vip.userid.name,
                        jsr: vip.jsr,
                        fexp: vip.fexp
                    });
                }
                //页数相关赋值
                var jsea=json.sea;
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
        $.getJSON("Vip/getStatus", function (json) {
            //获得vip查询变量
            var tremOpt = json.vipSelect;
            for (var i = 0; i < tremOpt.length; i++) {
                var opt = $("<option></option>");
                opt.val(i);
                opt.html(tremOpt[i]);
                trem.append(opt);
            }
            //获得比较方法
            var compareOpt = json.compare;
            for (var i = 0; i < compareOpt.length; i++) {
                var opt = $("<option></option>");
                opt.val(i);
                opt.html(compareOpt[i]);
                compare.append(opt);
            }
        });
    }
</script>
</body>
</html>

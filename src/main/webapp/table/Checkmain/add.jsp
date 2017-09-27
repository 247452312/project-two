<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>盘点详细</title>

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
        .none{  display: none;  }
        .block{  display: block;  }
        .in-line{  display: inline;  }
    </style>
</head>
<body>
<div id="test"></div>
<div class="container">
    <table id="dg" style="width:100%;height:529px" title="盘点记录查询列表" data-options="
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
            <th field="code" width="20%">商品编号</th>
            <th field="name" width="20%">商品名称</th>
            <th field="status" width="20%">商品状态</th>
            <th field="count" width="20%">盘点数量</th>
            <th field="fexp" width="20%">备注</th>
        </tr>
        </thead>
    </table>

    <div id="tb" style="padding:0 30px;">
        <input type="hidden" name="pageno">
        <input type="hidden" name="maxrow">
        <div style="overflow-y: auto;overflow-x:hidden;max-height: 140px;">
            <%--主表修改数据--%>
            <form action="" method="post" class="form in-line">
                <input type="hidden" name="id" class="checkid" value="${requestScope.object.id}">
                <input type="hidden" name="status" class="checkstatus" value="${requestScope.object.status}">
                <select style="height:35px;width:10%;" name="shopid"></select>
                <input class="trem-input in-line" type="text" name="checkcode" placeholder="盘点编号"
                       value="${requestScope.object.checkcode}" style="width:10%;height:35px;line-height:35px;"/>
                <input class="trem-input in-line" type="text" name="checkname" placeholder="盘点名称"
                       value="${requestScope.object.checkname}" style="width:10%;height:35px;line-height:35px;"/>
                <input class="trem-input in-line" type="text" name="checkdate" placeholder="盘点日期"
                       value="${requestScope.object.checkdate}" style="width:10%;height:35px;line-height:35px;"
                       id="control_date" size="10" maxlength="10"
                       onclick="new Calendar().show(this);" readonly="readonly"/>
                <input class="trem-input in-line" type="text" name="fexp" placeholder="盘点备注"
                       value="${requestScope.object.fexp}" style="width:10%;height:35px;line-height:35px;"/>
            </form>
            <%--商品查询表--%>
            <form action="" method="post" class="formPro in-line">
                <%--<input type="hidden" name="canpage" value="false">
                <input type="hidden" name="trem" value="1">
                <input type="hidden" name="compare" value="0">--%>
                <input type="text" name="text"
                       class="trem-input in-line pro-find" placeholder="商品名称" style="width:10%;height:35px;line-height:35px;"/>
                <%--<input type="hidden" name="join" value="1">--%>
                <a onclick="find();" class="easyui-linkbutton a-select" iconCls="icon-search"
                   data-options="selected:true">查找商品</a>
                <a onclick="save();" class="easyui-linkbutton a-select" iconCls="icon-save"
                   data-options="selected:true">保存</a>
                <a onclick="trans();" class="easyui-linkbutton a-select" iconCls="icon-print"
                   data-options="selected:true">生成损益单</a>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">
    //后台获得资料，方便以后调用
    var details=[];
    var rows = [];
    //传送数据获取表格信息
    function tableData() {
        rows = [];
        var data={
            "canpage":false,
            "trem":0,
            "compare":2,
            "text":$(".checkid").val()?$(".checkid").val():0,
            "join":0
        };
        $.ajax({
            type: "POST", url: "/Checkdetail/selectByAll", dataType: "json", data: data, success: function (json) {
                for (var i = 0; i < json.list.length; i++) {
                    var checkdetail = json.list[i];
                    //details.push(checkdetail);
                    rows.push({
                        id: checkdetail.id,
                        proid: checkdetail.productid.id,
                        code: checkdetail.productid.code,
                        name: checkdetail.productid.name,
                        status: checkdetail.productid.statusString,
                        count: checkdetail.count,
                        fexp: checkdetail.fexp
                    });
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
        getSelects();
        lock();
    });
    //获得查询条件下拉列表
    function getSelects() {
        var trem = $(".form").find("[name=shopid]");
        $.getJSON("/Shop/select",{"canpage":false}, function (json) {
            var shopOpt = json.list;
            for (var i = 0; i < shopOpt.length; i++) {
                var shop = shopOpt[i];
                var opt = $("<option></option>");
                opt.val(shop.id);
                opt.html(shop.name);
                trem.append(opt);
            }
        });
    }
    //保存主表和子表
    function save(trans) {
        formTrans();
        $("[name=shopid]").attr("name","shopid.id").addClass("point-id");
        var formDate=$('.form').serializeObject();//主表数据
        $(".point-id").attr("name","shopid").removeClass("point-id");
        var action="";
        if($(".checkid").val()) action="/Checkmain/update1";
        else action="/Checkmain/insertMain";
        //传主表
        var mainId;
        $.ajax({url:action,type:"POST",async:false,dataType:"JSON",data:formDate,success:function (json) {
            if(json.status==1) mainId=json.info;
            else showMsg(json.info);
        }});
        $(".checkid").val(mainId);
        var rows=$('#dg').datagrid("getRows");//获得表格内所有数据
        for (var i = 0; i < rows.length; i++) {
            var rowData = rows[i];//获取单行数据
            //alert(rowData.proid);
            var data={
                "checkid.id":mainId,
                "productid.id":rowData.proid,
                "count":rowData.count,
                "fexp":rowData.fexp
            };
            var action2="/Checkdetail/ajax?cmd=add";
            if(rowData.id) action2="/Checkmain/update2"
            $.ajax({url:action2,async:false,type:"POST",dataType:"JSON",data:data,success:function (jsondls){
                if(jsondls.status==1){
                    if(jsondls.info) window.rows[i].id=jsondls.info;//将最新id给相应行
                }
                else{showMsg(jsondls.info);return;}
            }});
        }
        resetDg();//重写表格内容
        if(!trans){
            showMsg("保存完成");
            closePage();
        }
    }
    //查找商品
    function find() {
        /*var proForm=$('.formPro');
        var data=proForm.serialize();*/
        $.ajax({url:"/Product/getProduct",type:"POST",dataType:"json",data:{"name":$('.pro-find').val()},success:function (pros) {
            if(pros.length==0) showMsg("没找到相关商品");
            else{
                var pro=pros[0];
                var urlpart="";
                for (var i = 0; i < pros.length; i++) {
                    var obj = pros[i];
                    urlpart+="proid="+obj.id+"&proname="+obj.name+"&";
                }
                showPage("增加盘点商品","/table/Checkmain/addpro.jsp?" + urlpart.substring(0,urlpart.length-1),
                    800,300,
                    function () {
                        for (var i = 0; i < details.length; i++) {
                            var detail = details[i];
                            rows.push({
                                id: detail.id,
                                proid: detail.productid.id,
                                code: detail.productid.code,
                                name: detail.productid.name,
                                status: detail.productid.statusString,
                                count: detail.count,
                                fexp: detail.fexp
                            });
                        }
                        resetDg();
                        details=[];
                    },false,true);
            }
        }});
    }
    //生成损益单
    function trans() {
        save();
        $.getJSON("/Checkmain/autoOrder",{"id":$(".checkid").val()},function (result) {
            if(result.status==1) {
                showMsg("转换完成");
                $(".checkstatus").val(result.info);
                lock();
            }
        });
    }
    //表格数据转换
    function formTrans() {
        if(!$(".checkstatus").val())
            $(".checkstatus").val(0);
    }
    //表单根据状态封锁功能
    function lock() {
        if($(".checkstatus").val()==1){
            $("input").attr("disabled","disabled");
            $("select").attr("disabled","disabled");
            $(".formPro").remove();
        }
    }
</script>
</body>
</html>

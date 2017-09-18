<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>百莲达管理系统</title>
    <link href="css/base.css" rel="stylesheet">
    <link href="css/platform.css" rel="stylesheet">
    <link rel="stylesheet" href="../custom/uimaker/easyui.css">
</head>
<body>
<div class="container">
    <div id="pf-hd" style="background-image: none">
        <div class="pf-logo" style="width: 190px;">
            <img src="images/bailianda.png" alt="logo"
                 style="vertical-align:top;margin-top: 10px;margin-left: 20px;">
        </div>

        <div class="pf-nav-wrap">
            <div class="pf-nav-ww">
                <ul class="pf-nav">
                    <li class="pf-nav-item home current" data-menu="sys-manage">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe63f;</span>
                            <span class="pf-nav-title">供货商管理</span>
                        </a>
                    </li>
                    <li class="pf-nav-item project" data-menu="org-manage" src="table/Vip/index.jsp">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe60d;</span>
                            <span class="pf-nav-title">会员管理</span>
                        </a>
                    </li>
                    <li class="pf-nav-item static" data-menu="main-data">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe61e;</span>
                            <span class="pf-nav-title">分店管理</span>
                        </a>
                    </li>
                    <li class="pf-nav-item manger" data-menu="supplier-mange">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe620;</span>
                            <span class="pf-nav-title">商品管理</span>
                        </a>
                    </li>

                    <li class="pf-nav-item manger" data-menu="supplier-dev">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe625;</span>
                            <span class="pf-nav-title">会员类型</span>
                        </a>
                    </li>

                    <li class="pf-nav-item manger" data-menu="pur-source">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe64b;</span>
                            <span class="pf-nav-title">商品类型</span>
                        </a>
                    </li>

                    <%--<li class="pf-nav-item manger" data-menu="contract-mange">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe64c;</span>
                            <span class="pf-nav-title">合同管理</span>
                        </a>
                    </li>


                    <li class="pf-nav-item manger" data-menu="pur-source">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe623;</span>
                            <span class="pf-nav-title">示例示例</span>
                        </a>
                    </li>

                    <li class="pf-nav-item manger" data-menu="contract-mange">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe646;</span>
                            <span class="pf-nav-title">示例示例示例示例示例</span>
                        </a>
                    </li>
                    <li class="pf-nav-item manger" data-menu="pur-source">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe623;</span>
                            <span class="pf-nav-title">采购寻源</span>
                        </a>
                    </li>

                    <li class="pf-nav-item manger" data-menu="contract-mange">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe646;</span>
                            <span class="pf-nav-title">合同管理</span>
                        </a>
                    </li>


                    <li class="pf-nav-item manger" data-menu="pur-source">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe623;</span>
                            <span class="pf-nav-title">示例示例</span>
                        </a>
                    </li>

                    <li class="pf-nav-item manger" data-menu="contract-mange">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe646;</span>
                            <span class="pf-nav-title">示例示例示例示例示例示例</span>
                        </a>
                    </li>

                    <li class="pf-nav-item manger" data-menu="pur-source">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe623;</span>
                            <span class="pf-nav-title">采购寻源</span>
                        </a>
                    </li>

                    <li class="pf-nav-item manger" data-menu="contract-mange">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe646;</span>
                            <span class="pf-nav-title">合同管理</span>
                        </a>
                    </li>--%>
                </ul>
            </div>


            <a href="javascript:;" class="pf-nav-prev disabled iconfont">&#xe606;</a>
            <a href="javascript:;" class="pf-nav-next iconfont">&#xe607;</a>
        </div>



        <div class="pf-user">
            <div class="pf-user-photo">
                <img src="images/main/user.png" alt="">
            </div>
            <h4 class="pf-user-name ellipsis">${sessionScope.user.name}</h4>
            <i class="iconfont xiala">&#xe607;</i>

            <div class="pf-user-panel">
                <ul class="pf-user-opt">
                    <li>
                        <a href="javascript:;">
                            <i class="iconfont">&#xe60d;</i>
                            <span class="pf-opt-name">用户信息</span>
                        </a>
                    </li>
                    <li class="pf-modify-pwd">
                        <a href="#">
                            <i class="iconfont">&#xe634;</i>
                            <span class="pf-opt-name">修改密码</span>
                        </a>
                    </li>
                    <li class="pf-logout">
                        <a href="/User/cancel">
                            <i class="iconfont">&#xe60e;</i>
                            <span class="pf-opt-name">退出</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </div>

    <div id="pf-bd">
        <div id="pf-sider">
            <h2 class="pf-model-name">
                <span class="iconfont">&#xe64a;</span>
                <span class="pf-name">组织管理</span>
                <span class="toggle-icon"></span>
            </h2>

            <ul class="sider-nav">
                <li class="current">
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe620;</span>
                        <span class="sider-nav-title">基本信息</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                    <ul class="sider-nav-s">
                        <li src="hello.jsp?name=user" class="active"><a href="javascript:;">操作员信息</a></li>
                        <li src="hello.jsp?name=client" ><a href="javascript:;">供货商信息</a></li>
                        <li src="table/Vip/index.jsp" ><a href="javascript:;">会员信息</a></li>
                        <li src="hello.jsp?name=shop" ><a href="javascript:;">分店信息</a></li>
                        <li src="hello.jsp?name=product" ><a href="javascript:;">商品信息</a></li>
                        <li src="table/Viptype/index.jsp" ><a href="javascript:;">会员类型信息</a></li>
                        <li src="hello.jsp?name=protype" ><a href="javascript:;">商品类型信息</a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe625;</span>
                        <span class="sider-nav-title">设置信息</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                    <ul class="sider-nav-s">
                        <li src="#" class="active"><a href="javascript:;">修改密码</a></li>
                        <li src="#" ><a href="javascript:;">库存初始</a></li>
                        <li src="#" ><a href="javascript:;">库存盘点</a></li>
                        <li src="#" ><a href="javascript:;">盘点记录</a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe62f;</span>
                        <span class="sider-nav-title">单据录入</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                    <ul class="sider-nav-s">
                        <li src="#" class="active"><a href="javascript:;">充值单</a></li>
                        <li src="#" ><a href="javascript:;">退款单</a></li>
                        <li src="#" ><a href="javascript:;">项目充值单</a></li>
                        <li src="#" ><a href="javascript:;">项目退款单</a></li>
                        <li src="#" ><a href="javascript:;">采购单</a></li>
                        <li src="#" ><a href="javascript:;">采购退货单</a></li>
                        <li src="#" ><a href="javascript:;">销售单</a></li>
                        <li src="#" ><a href="javascript:;">销售退货单</a></li>
                        <li src="#" ><a href="javascript:;">移库单</a></li>
                        <li src="#" ><a href="javascript:;">库损单</a></li>
                        <li src="#" ><a href="javascript:;">库溢单</a></li>
                        <li src="#" ><a href="javascript:;">单据列表</a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe647;</span>
                        <span class="sider-nav-title">统计报表</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                    <ul class="sider-nav-s">
                        <li src="#" class="active"><a href="javascript:;">会员明细余额统计表</a></li>
                        <li src="#" ><a href="javascript:;">会员统计表</a></li>
                        <li src="#" ><a href="javascript:;">操作员统计表</a></li>
                        <li src="#" ><a href="javascript:;">分店统计表</a></li>
                        <li src="#" ><a href="javascript:;">商品统计表</a></li>
                        <li src="#" ><a href="javascript:;">综合统计表</a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe611;</span>
                        <span class="sider-nav-title">关于</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                    <ul class="sider-nav-s">
                        <li src="#" class="active"><a href="javascript:;">数据导入</a></li>
                    </ul>
                </li>
            </ul>
        </div>

        <div id="pf-page">
            <div class="easyui-tabs1" style="width:100%;height:100%;">
                <%--<div title="首页" style="padding:10px 5px 5px 10px;">
                  <iframe class="page-iframe" src="workbench.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                </div>--%>
            </div>
        </div>
    </div>

    <div id="pf-ft">
        <div class="system-name">
            <i class="iconfont">&#xe6fe;</i>
            <span>信息管理系统&nbsp;v1.0</span>
        </div>
        <div class="copyright-name">
            <span>CopyRight&nbsp;2016&nbsp;&nbsp;uimaker.com&nbsp;版权所有</span>
            <i class="iconfont" >&#xe6ff;</i>
        </div>
    </div>
</div>

<%--选项卡范例--%>
<div style="display: none" id="exTab">
    <li class="tabs-selected tabs-last" src="EXSRC">
        <a href="javascript:void(0)" class="tabs-inner"
           style="height: 43px; line-height: 42px;">
            <span class="tabs-title tabs-closable">EXTLE</span>
            <span class="tabs-icon"></span>
        </a>
    </li>
</div>
<%--范例子页--%>
<div style="display: none" id="exPanel">
    <div class="panel">
        <div title="" style="padding: 10px 5px 5px 10px; width: 100%; height: 163px;"
             class="panel-body panel-body-noheader panel-body-noborder">
            <iframe class="page-iframe" src="" frameborder="no" border="no" height="100%" width="99%"
                    scrolling="auto"></iframe>
        </div>
    </div>
</div>
<%--范例li--%>
<div style="display: none" id="exLi">
    <li src="hello.jsp?name=main" close="false" class="active"><a href="javascript:;">首页</a></li>
</div>


<script type="text/javascript" src="../custom/jquery.min.js"></script>
<script type="text/javascript" src="../custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<!--[if IE 7]>
<script type="text/javascript">
    $(window).resize(function(){
        $('#pf-bd').height($(window).height()-76);
    }).resize();

</script>
<![endif]-->


<script type="text/javascript">
    $('.easyui-tabs1').tabs({
        tabHeight: 44,
        onSelect:function(title,index){
            var currentTab = $('.easyui-tabs1').tabs("getSelected");
            if(currentTab.find("iframe") && currentTab.find("iframe").size()){
                currentTab.find("iframe").attr("src",currentTab.find("iframe").attr("src"));
            }
        }
    })
    $(window).resize(function(){
        $('.tabs-panels').height($("#pf-page").height()-46);
        $('.panel-body').height($("#pf-page").height()-76)
    }).resize();

    var page = 0,
        pages = ($('.pf-nav').height() / 70) - 1;

    if(pages === 0){
        $('.pf-nav-prev,.pf-nav-next').hide();
    }
    $(document).on('click', '.pf-nav-prev,.pf-nav-next', function(){


        if($(this).hasClass('disabled')) return;
        if($(this).hasClass('pf-nav-next')){
            page++;
            $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
            if(page == pages){
                $(this).addClass('disabled');
                $('.pf-nav-prev').removeClass('disabled');
            }else{
                $('.pf-nav-prev').removeClass('disabled');
            }

        }else{
            page--;
            $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
            if(page == 0){
                $(this).addClass('disabled');
                $('.pf-nav-next').removeClass('disabled');
            }else{
                $('.pf-nav-next').removeClass('disabled');
            }

        }
    })

    $(function () {
        addTab($("#exLi li"));//加入首页
    });
    // setTimeout(function(){
    //    $('.tabs-panels').height($("#pf-page").height()-46);
    //    $('.panel-body').height($("#pf-page").height()-76)
    // }, 200)
</script>
</body>
</html>

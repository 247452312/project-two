var mainPlatform = {

    init: function () {

        this.bindEvent();
        // this.render(menu['home']);
    },

    bindEvent: function () {
        var self = this;
        // 顶部大菜单单击事件
        $(document).on('click', '.pf-nav-item', function () {
            $('.pf-nav-item').removeClass('current');
            $(this).addClass('current');

            // 渲染对应侧边菜单
            //var m = $(this).data('menu');
            //self.render(menu[m]);
            addTab($(this));
        });
        //一级菜单设置
        $(document).on('click', '.sider-nav>li>a', function () {
            if ($(this).parents(".sider-nav>li").hasClass("current")) {
                $(this).parents(".sider-nav>li").removeClass("current");
            } else {
                $('.sider-nav>li').removeClass("current");
                $(this).parents(".sider-nav>li").addClass("current");
            }
        });
        //二级菜单设置
        $(document).on('click', '.sider-nav-s>li', function () {
            $('.sider-nav-s>li').removeClass("active");
            $(this).addClass("active");
            addTab($(this));
        });

        $(document).on('click', '.pf-logout', function () {
            layer.confirm('您确定要退出吗？', {
                icon: 4,
                title: '确定退出' //按钮
            }, function () {
                location.href = 'login.html';
            });
        });
        //左侧菜单收起
        $(document).on('click', '.toggle-icon', function () {
            $(this).closest("#pf-bd").toggleClass("toggle");
            setTimeout(function () {
                $(window).resize();
            }, 300)
        });

        $(document).on('click', '.pf-modify-pwd', function () {
            $('#pf-page').find('iframe').eq(0).attr('src', 'backend/modify_pwd.html')
        });

        $(document).on('click', '.pf-notice-item', function () {
            $('#pf-page').find('iframe').eq(0).attr('src', 'backend/notice.html')
        });
    },

    render: function (menu) {
        var current,
            html = ['<h2 class="pf-model-name"><span class="pf-sider-icon"></span><span class="pf-name">' + menu.title + '</span></h2>'];

        html.push('<ul class="sider-nav">');
        for (var i = 0, len = menu.menu.length; i < len; i++) {
            if (menu.menu[i].isCurrent) {
                current = menu.menu[i];
                html.push('<li class="current" title="' + menu.menu[i].title + '" data-src="' + menu.menu[i].href + '"><a href="javascript:;"><img src="' + menu.menu[i].icon + '"><span class="sider-nav-title">' + menu.menu[i].title + '</span><i class="iconfont"></i></a></li>');
            } else {
                html.push('<li data-src="' + menu.menu[i].href + '" title="' + menu.menu[i].title + '"><a href="javascript:;"><img src="' + menu.menu[i].icon + '"><span class="sider-nav-title">' + menu.menu[i].title + '</span><i class="iconfont"></i></a></li>');
            }
        }
        html.push('</ul>');

        $('iframe').attr('src', current.href);
        $('#pf-sider').html(html.join(''));
    }

};

mainPlatform.init();

function addTab(li) {
    if(!li[0].hasAttribute("num")) li.attr("num",$(".tabs").children("li").length);//给选项卡做标记
    var src=li.attr('src');
    var title=li.children("a").html();
    //检查是否已经被打开
    var tabs=$(".tabs").children("li");
    for (var i = 0; i < tabs.length; i++) {
        if(tabs.eq(i).attr("src")==src){
            changeIframe($(".panel[num="+li.attr("num")+"]"));
            tabs.removeClass("tabs-selected");
            tabs.eq(i).addClass("tabs-selected");
            return;
        }
    }
    //新加选项卡
    var tab=$("#exTab").children("li").clone();
    tab.attr("src",src).attr("num",li.attr("num"));//给选项卡做标记
    tab.find(".tabs-title").html(title);
    //增加iframe
    var panle=$("#exPanel").find(".panel").clone();
    panle.attr("num",li.attr("num"));//给panel做标记
    panle.find("iframe").attr('src', src);//重设iframe
    changeIframe(panle);
    $(".tabs-panels").append(panle);//添加iframe
    //增加关闭按钮
    if(!li[0].hasAttribute("close")){
        var a=$("<a></a>");
        a.attr("href","javascript:void(0)");
        a.addClass("tabs-close");
        tab.append(a);
    }
    //点击事件
    tab.click(function () {
        $(".tabs").children("li").removeClass("tabs-selected");
        tab.addClass("tabs-selected");
        changeIframe($(".panel[num="+li.attr("num")+"]"));
    });
    //关闭事件
    tab.find(".tabs-close").click(function () {
        var selected=tab.hasClass("tabs-selected");
        $(".panel[num="+tab.attr("num")+"]").remove();
        tab.remove();
        //如果关闭的选项卡是被选中的，就重新定义子类和最后一张选项卡
        if(selected){
            var tablast=$(".tabs>li").last();
            tablast.addClass("tabs-selected").addClass("tabs-last");
            changeIframe($(".panel[num="+tablast.attr("num")+"]"));
        }
    });
    $(".tabs").children("li").removeClass("tabs-selected");
    $(".tabs").children("li").removeClass("tabs-last");
    $(".tabs").append(tab);
}
function changeIframe(disPanel) {
    $('.panel').attr("style","display: none;");//其他Panel不可见
    disPanel.attr("style","display: block; width: 100%;");//显示选中的Panel
}
//判断js对象是否拥有属性
function isEmptyObject(e) {
    var t;
    for (t in e)
        return !1;
    return !0
}
//将表单的键值对转化为普通json
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
//添加按键事件
/*$(document).keypress(function(e) {
    var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
    if(eCode=='13'){
        //触发自定义keyEnter方法
        alert($(this).html());
        try{this.trigger('keyEnter');}
        catch (e){}
    }
});*/
//获取元素的纵坐标
function getTop(e){
    var offset=e.offsetTop;
    if(e.offsetParent!=null) offset+=getTop(e.offsetParent);
    return offset;
}
//获取元素的横坐标
function getLeft(e){
    var offset=e.offsetLeft;
    if(e.offsetParent!=null) offset+=getLeft(e.offsetParent);
    return offset;
}
//获取元素的body纵坐标
function getBodyTop(e){
    try{
        var offset=e.offsetTop;
        if(e.offsetParent!=$('body')[0])
            offset+=getBodyTop(e.offsetParent);
        return offset;
    }
    catch (ex){}
}
//获取元素的body横坐标
function getBodyLeft(e){
    try{
        var offset=e.offsetLeft;
        if(e.offsetParent!=$('body')[0])
            offset+=getBodyLeft(e.offsetParent);
        return offset;
    }
    catch (ex){}
}



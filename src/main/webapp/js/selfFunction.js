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
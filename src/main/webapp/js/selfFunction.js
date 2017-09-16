//判断js对象是否拥有属性
function isEmptyObject(e) {
    var t;
    for (t in e)
        return !1;
    return !0
}
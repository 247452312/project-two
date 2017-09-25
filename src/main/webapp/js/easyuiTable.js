(function($){
    $.fn.numberbox.defaults.filter = function(e){
        var opts = $(this).numberbox('options');
        var s = $(this).numberbox('getText');
        if (e.which == 45){    //-
            return (s.indexOf('-') == -1 ? true : false);
        }
        var c = String.fromCharCode(e.which);
        if (c == opts.decimalSeparator){
            return (s.indexOf(c) == -1 ? true : false);
        } else if (c == opts.groupSeparator){
            return true;
        } else if ((e.which >= 48 && e.which <= 57 && e.ctrlKey == false && e.shiftKey == false) || e.which == 0 || e.which == 8) {
            return true;
        } else if (e.ctrlKey == true && (e.which == 99 || e.which == 118)) {
            return true;
        } else {
            return false;
        }
    }
})(jQuery);
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
//打印预览
function tablePrint() {
    var thead=$("<thead></thead>").append($(".datagrid-header-row").clone());
    thead.find("td").css("min-width:80px;");
    var tbody=$(".datagrid-view2").find(".datagrid-btable").find("tbody").clone();
    var table=$("<table></table>").attr("border",1).attr("cellspacing",0).append(thead).append(tbody);
    table.jqprint();
    table.remove();
}
//添加查询条件
function addTrem(divTrems) {
    var newdiv = $(".first-trem").clone();
    newdiv.find("input[type=text]").val("");
    newdiv.find("select").val(0);
    newdiv.removeClass("first-trem");
    newdiv.find(".a-select").remove();
    newdiv.find(".addRow").remove();
    newdiv.find(".trem-select").removeAttr("name").addClass("none").removeClass("in-line");
    newdiv.find(".trem-input").attr("name", "text").addClass("in-line").removeClass("none");
    newdiv.find("[name=compare]").removeAttr("disabled");
    newdiv.find(".more").removeAttr("style");
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
//行内编辑
$.extend($.fn.datagrid.methods, {
    editCell: function (jq, param) {
        return jq.each(function () {
            var opts = $(this).datagrid('options');
            var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
            for (var i = 0; i < fields.length; i++) {
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor1 = col.editor;
                if (fields[i] != param.field) {
                    col.editor = null;
                }
            }
            $(this).datagrid('beginEdit', param.index);
            for (var i = 0; i < fields.length; i++) {
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor = col.editor1;
            }
        });
    }
});

var editIndex = undefined;
function endEditing() {
    if (editIndex == undefined) { return true }
    if ($('#dg').datagrid('validateRow', editIndex)) {
        $('#dg').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickCell(index, field) {
    if (endEditing()) {
        $('#dg').datagrid('selectRow', index)
            .datagrid('editCell', { index: index, field: field });
        editIndex = index;
        $(".datagrid-editable-input").style
    }
}
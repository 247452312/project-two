//显示信息
function showMsg(txt){
	layer.alert(txt);
}
//打开页面
function showPage(title,url,width,height,closeCallBack,full,maxmin) {
	var win;
	if(maxmin) maxmin=true;
	else maxmin=false;
	layer.ready(function() {
        win=layer.open({
			type:2,//类型
			title:title,//窗口标题
			maxmin:maxmin,//是否最大化
			area:[width+'px',height+'px'],//尺寸
			content:url,//地址
			end:function(){
				if(closeCallBack)
					closeCallBack();
			}
		});
        if(full) layer.full(win);
	});
	return win;
}
function showTip(selector,txt) {
	layer.tips(txt,selector,{tips:1});
}
function closePage() {
	var index=parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
function sendData(url,data) {
	$.ajax({
		type:"post",
		url:url,
		dataType:"json",
		data:data,
		success:function(json){
			if(json.status>0){
				closePage();
			}
		}
	});
}
function sendForm(form) {
	var m=form.find(".must");
	for(var i=0;i<m.length;i++){
		if(m.get(i).value.length==0){
			showMsg("信息不全");
			return ;
		}
	}
	var data=form.serializearray();
	var action=form.attr("action");
	sendData(action,data);
}
//确认对话框
function showConfirm(txt,fun1,fun2,button1,button2){
	var bt1="确定";
	var bt2="取消";
	if(button1)bt1=button1;
	if(button2)bt2=button2;
	layer.confirm(txt,{
		btn:[bt1,bt2]//按钮
	},function(){
		fun1();
	},function(){
		if(fun2)
		fun2();
	});
}
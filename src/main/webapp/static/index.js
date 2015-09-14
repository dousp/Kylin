/**
 * Created by Douspeng on 2015/4/24.
 */


setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
//添加一个选项卡面板

function setHeight(vId){
    var c = $(vId);
    var p = c.layout('panel','center');	// get the center panel
    var oldHeight = p.panel('panel').outerHeight();
    p.panel('resize', {height:'auto'});
    var newHeight = p.panel('panel').outerHeight();
    c.layout('resize',{
        height: (c.height() + newHeight - oldHeight)
    });
}
//系统时间显示

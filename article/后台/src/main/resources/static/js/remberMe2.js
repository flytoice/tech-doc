if($.cookies.get('wx_trxn_userName')!=undefined){
    $("#rememberMe").attr("checked", true);
	$('#username').val($.cookies.get('wx_trxn_userName'));
    $('#pwd').val($.cookies.get('wx_trxn_password'));
}else{
    $("#rememberMe").attr("checked", false);  
};  
  
//读取cookie  
/*if($('#rememberMe:checked').length>0){  
	$('#username').val($.cookies.get('wx_trxn_userName'));  
    $('#pwd').val($.cookies.get('wx_trxn_password'));   
};*/

function setCookies(){
	if($('#rememberMe:checked').length>0){//设置cookie 
        $.cookies.set('wx_trxn_userName', $('#username').val(),{hoursToLive: 168});  
        $.cookies.set('wx_trxn_password', $('#pwd').val(),{hoursToLive: 168});  
    }else{//清除cookie 
    	$.cookies.del('wx_trxn_userName');  
    	$.cookies.del('wx_trxn_password');  
    }
};
  
//监听【记住我】事件  
$("#rememberMe").click(function(){  
	setCookies();
});
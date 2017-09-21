if($.cookie('wx_trxn_userName')!=undefined){
    $("#rememberMe").attr("checked", true);
	$('#telphone').val($.cookie('wx_trxn_userName'));
    $('#pwd').val($.cookie('wx_trxn_password'));
}else{
	alert("set unchecked")
    $("#rememberMe").attr("checked", false);  
};  


function setCookies(){
	if($('#rememberMe:checked').length>0){//设置cookie 
		$.cookie('wx_trxn_userName', $('#telphone').val(),{expires: 7});  
		$.cookie('wx_trxn_password', $('#pwd').val(),{expires: 7});  
    }else{//清除cookie 
    	alert('clear');
    	$.cookie('wx_trxn_userName');  
    	$.cookie('wx_trxn_password');  
    }
};
  
//监听【记住我】事件  
$("#rememberMe").click(function(){  
	setCookies();
});
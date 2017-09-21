/**
 * @param base64Codes
 *            图片的base64编码
 */
function sumitImageFile(base64Codes){
    var form=document.forms[0];
    /*
    //var formData = new FormData(form);   //这里连带form里的其他参数也一起提交了,如果不需要提交其他参数可以直接FormData无参数的构造函数
    //convertBase64UrlToBlob函数是将base64编码转换为Blob
    //formData.append("data",convertBase64UrlToBlob(base64Codes));  //append函数的第一个参数是后台获取数据的参数名,和html标签的input的name属性功能相同
    
    //sumitImageFile(data);
	// 重点来了 ajax在传输过程中 加号会变成空格 base64里是有加号的我不幸掉进了这个坑……  
    // 把+替换成编码 %2B是加号的编码  
	//data = data.replace(/\+/g, "%2B"); 
	//alert(data);
    *
    */
    
    
    
    var formData = new FormData();
    var builder = new BolbBuilder();  
    builder.append(base64Codes);  
    var blob = builder.getBlob("image/png");
    formData.append("data",blob);
    formData.append("articleId","1")
    formData.append("typeId","2")
    
    //ajax 提交form
    $.ajax({
        url : "title2",
        type : "POST",
        data : formData,
        dataType:"text",
        processData : false,         // 告诉jQuery不要去处理发送的数据
        contentType : false,        // 告诉jQuery不要去设置Content-Type请求头
        
        success:function(data){
            //window.location.href="${ctx}"+data;
        },
        xhr:function(){            //在jquery函数中直接使用ajax的XMLHttpRequest对象
            var xhr = new XMLHttpRequest();
            
            xhr.upload.addEventListener("progress", function(evt){
//                if (evt.lengthComputable) {
//                    var percentComplete = Math.round(evt.loaded * 100 / evt.total);  
//                    console.log("正在提交."+percentComplete.toString() + '%');        //在控制台打印上传进度
//                }
            }, false);
            
            return xhr;
        }
        
    });
}

/**
 * 将以base64的图片url数据转换为Blob
 * @param urlData 用url方式表示的base64图片数据
 */
function convertBase64UrlToBlob(urlData){
    
    var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte
    
    //处理异常,将ascii码小于0的转换为大于0
    var ab = new ArrayBuffer(bytes.length);
    var ia = new Uint8Array(ab);
    for (var i = 0; i < bytes.length; i++) {
        ia[i] = bytes.charCodeAt(i);
    }

    return new Blob( [ab] , {type : 'image/png'});
}
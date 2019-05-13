$(function(){
	/*
	 * 1.遍历错误信息，没有就不显示
	 */
	$(".errorClass").each(function(){
		showError($(this));
	});
	
	/*
	 * 2.切换 立即注册 的图片
	 */
	replaceRegistImg();   
	
	/*
	 * 3.当光标进入某一个input时 希望它对应 的错误提示消失
	 */
	$(".inputClass").focus(function() {
		var errorId = "#"+$(this).attr("id") + "Error";
		$(errorId).text("");  //置控错误内容
		showError($(errorId));   //让label不显示
	});
	
	/*
	 * 4.失去焦点时进行校验
	 */
	$(".inputClass").blur(function() {
		var funName = $(this).attr("id");
		funName = "validate" + funName.substring(0,1).toUpperCase() + funName.substring(1) + "()";
		eval(funName);  //会把字符串当会JavaScript代码执行
	});
	
	/*
	 * 5.当表单提交的时候再次进行校验
	 */
	$("#loginForm").submit(function() {
		if(!validateLoginname()) {
			return false;
		}
		if(!validateLoginpass()) {
			return false;
		}
		if(!validateVerifyCode()) {
			return false;
		}
		return true;
	});
});

function validateLoginname() {
	var id = "loginname";
	var value = $("#" + id).val();
	/*
	 * 1.校验是否为空
	 */
	if(!value) {
		$("#" + id + "Error").text("用户名不能为空！");
		showError($("#" + id + "Error"));
		return false;
	}
	/*
	 * 2.校验长度
	 */
	if(value.length<3 || value.length>20) {
		$("#" + id + "Error").text("用户名长度在3~20之间！");
		showError($("#" + id + "Error"));
		return false;
	}
     return true;
}

function validateLoginpass() {
	var id = "loginpass";
	var value = $("#" + id).val();
	/*
	 * 1.校验是否为空
	 */
	if(!value) {
		$("#" + id + "Error").text("密码不能为空！");
		showError($("#" + id + "Error"));
		return false;
	}
	/*
	 * 2.校验长度
	 */
	if(value.length<3 || value.length>20) {
		$("#" + id + "Error").text("用户名长度在3~20之间！");
		showError($("#" + id + "Error"));
		return false;
	}
	return true;
}


function validateVerifyCode() {
	var id = "verifyCode";
	var value = $("#" + id).val();
	/*
	 * 1.校验是否为空
	 */
	if(!value) {
		$("#" + id + "Error").text("验证码不能为空！");
		showError($("#" + id + "Error"));
		return false;
	}
	/*
	 * 2.校验长度
	 */
	if(value.length != 4) {
		$("#" + id + "Error").text("验证码错误！");
		showError($("#" + id + "Error"));
		return false;
	}
	
	/*
	 * 3.后台校验验证码是否正确
	 */
	$.ajax({
		url:"/AgricultureSale0.1/verify/AjaxValidateVerifyCode.action",
		data:{verifycode:value},
		dataType:"json",
		type:"post",
		async:false,
		cache:false,
		success:function(result) {
			if(result) {
				$("#" + id + "Error").text(result.msg);
				showError($("#" + id + "Error"));
			}
		}
	});
	return true;
}
/*
 * 隐藏没有错误内容的label
 */
function showError(ele){
	var text = ele.text();
	if(!text) { //如果没有错误内容
		ele.css("display","none"); //不显示 
	} else{
		ele.css("display",""); //默认 display
	}
}

/*
 * 实现点击换一张验证码
 */
function _hyz() {
	$("#imgVerifyCode").attr("src","/AgricultureSale0.1/verify/VerifyCode.action?a=" + new Date().getTime());//不加参数的话，验证码不会改变，跟游览器的缓存有关
}
/*
 * 实现光标进入注册图标的时候换一张图片
 */
function replaceRegistImg() {
	$("#submitBtn").hover(
      function(){
		$("#submitBtn").attr("src","/AgricultureSale0.1/zw/image/login1.jpg");
	  },
	  function(){
		$("#submitBtn").attr("src","/AgricultureSale0.1/zw/image/login2.jpg");  
	  }
	);
}




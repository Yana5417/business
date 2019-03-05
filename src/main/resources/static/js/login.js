$(function() {

	/**
	 *公共正则表达式验证表单
	 */

	// 刷新图片
	$(".changeImg").on('click', function() {
		var imgSrc = $("#imgObj");
		var src = imgSrc.attr("src");
		imgSrc.attr("src", changeUrl(src));
	});
	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
	function changeUrl(url) {
		var timestamp = (new Date()).valueOf();
		var index = url.indexOf("?",url);
		if (index > 0) {
			url = url.substring(index, url.indexOf(url, "?"));
		}
		if ((url.indexOf("&") >= 0)) {
			url = url + "×tamp=" + timestamp;
		} else {
			url = url + "?timestamp=" + timestamp;
		}
		return url;
	}

	//手机号验证
	$("#phone").on('blur', function() {
		var reg = /^1[34578]\d{9}$/,
			val = $("#phone").val();
		if(reg.test(val) == false) {
			$("#phoneMsg").show();
			$("#phoneMsg").html("手机号码格式错误，请重新输入！");
		} else {
			$("#phoneMsg").hide();
		}
	});

	//密码是否可见
	$(".pass-eyes").on('click', function() {
		if($("#password").attr('type') == 'password') {
			$("#password").attr('type', 'text');
			$(".pass-eyes").css('background', 'url(img/icon4@2x.png) no-repeat');
			$(".pass-eyes").css('background-size', 'cover');
		}else if($("#password").attr('type') == 'text'){
			$("#password").attr('type', 'password');
			$(".pass-eyes").css('background', 'url(img/icon3@2x.png) no-repeat');
			$(".pass-eyes").css('background-size', 'cover');
		}
	});

	/**
	 *登录模块js实现
	 */

	//登录实现
	$("#login").on('click', function() {
        debugger;
		var phone = $("#phone").val(),
				password = $("#password").val(),
				code = $("#code").val();

		if (!phone) {
			alert('请输入手机号');
			return;
		};
		if (!password) {
			alert('请输入密码');
			return;
		};
		if (!code) {
			alert('请输入验证码');
			return;
		};

		if (phone && password) {
			$.ajax({
				type: "post",
				url: urlBaseQ + "business/login",
				async: false,
				data: {
					'phone': phone,
					'password': password,
					'code': code
				},
				success: function(response) {
					console.log(response);
					if(response.code == 200) {
						window.location.href = "code";
					}else{
						layer.msg(response.msg);
					}
				},
				error: function(response) {
					console.log(response);
				    console.log("error");
				}
			});
		}
	});
	//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf49c6b60fade7ed2&redirect_uri=http://www.baiwei120.com/baiwei-wechat/fastReading/home.html&response_type=code&scope=snsapi_base&state=baiwei#wechat_redirect
});
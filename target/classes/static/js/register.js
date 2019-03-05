/**
 * 注册
 */
$(function(){

	// 获取省
	function fetchPositions(type, parent) {
		$.ajax({
			type:"get",
			url:  urlBaseQ + "area/list",
			async: false,
			data:{
				parentAreaId: parent
			},
			dataType:'json',
			success:function(response){
				if(response.code === 200) {
					var html = "";

					if (type === 'province') {
						// 省市区三级联动
						$("#businessAddressCity").append(html);
						$("#businessAddressCounty").append(html);
						$.each(response.data, function(idx,item){
							html += "<option value="+ item.areaId+" >"+ item.areaName +"</option> ";
						});
						$("#businessAddressProvince").append(html);
					} else if (type === 'city') {
						var html = "<option value=''>--请选择--</option>";
						$("#businessAddressCounty").append(html);
						$.each(response.data,function(idx, item){
							html +="<option value="+item.areaId+" >"+ item.areaName +"</option> ";
						});
						$("#businessAddressCity ").append(html);
					} else if (type === 'county') {
						var html = "<option value=''>--请选择--</option>";
						$.each(response.data,function(idx,item){
							html +="<option value="+item.areaId+" >"+ item.areaName +"</option> ";
						});
						$("#businessAddressCounty ").append(html);
					}
				}else{
					layer.msg(response.message);
				}
			},
			error:function(err){
				console.log('err:', err);
			}
		});
	}

	fetchPositions('province', 0);

	$("#businessAddressProvince").change(function(){
		if ($(this).val() == "") return;
		$("#businessAddressCity option").remove();
		$("#businessAddressCounty option").remove();
		var areaId = $(this).find("option:selected").val();

		fetchPositions('city', areaId);
	});

	$("#businessAddressCity").change(function(){
		if ($(this).val() == "") return;
		$("#businessAddressCounty option").remove();
		var areaId = $(this).find("option:selected").val();

		fetchPositions('county', areaId);
	});

	//正则表达式验证
	var regPhone = /^1(2|3|4|5|6|7|8|9)\d{9}$/,
		regPassword = /^[a-zA-Z0-9]\w{5,19}$/;
	var phoneVal,//手机号
		passwordVal;//密码

	$("#phoneMsg").hide();
	$("#passwordMsg").hide();

	// 刷新图片
	$('.changeImg').on('click',function(){
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

	//获取验证码
	$("#getCode").on('click',function(){
		phoneVal = $('#phone').val();
		var code = $('#code').val();

		if($('#getCode').hasClass('pick')){
			return;
    }
		if (regPhone.test(phoneVal) == false) {
			$("#phoneMsg").show();
		} else {
			$("#phoneMsg").hide();
			if (!code) return;

			$.ajax({
				type:"get",
				url: urlBaseQ + "business/sms/code",
				async:false,
				data:{
					phone: phoneVal,
					code: code
				},
				dataType:'json',
				success:function(response){
					if(response.code === 200){
						$("#getCode").addClass("pick");
						$("#getCode").html("短信已发送");
						var _time = 60;

						var count = setInterval(function(){
							$("#getCode").html(_time--+'s后重试');
							if(_time == 0){
								$("#getCode").html("获取验证码");
								clearInterval(count);
								_time = 60;
								$("#getCode").removeClass("pick");
							}
						},1000);
					} else {
						layer.msg(response.msg);
					}
				},
				error:function(response){
					alert('error');
				}
			});
		}
	});
	$("#password").on('blur',function(){
		passwordVal = $("#password").val();
		if(regPassword.test(passwordVal) == false){
			$("#passwordMsg").show();
		}else{
			$("#passwordMsg").hide();
		}
	});

	//注册
	function handleRegister(params){
		//注册
		$.ajax({
			type:"post",
			url: urlBaseQ + "business/register",
			async:false,
			data: params,
			dataType:'json',
			success:function(response){
				if(response.code === 200) {
					window.location.href = "login.html";
				} else {
					layer.msg(response.msg);
				}
			},
			error:function(response){

			}
		});
	}

	// 注册
	var paramsText = {
		businessName: '请填写商家名',
		password: '请输入密码',
		rePassword: '请重新输入密码',
		phone: '请输入手机号',
		phoneCode: '请输入验证码',
		businessAddressDetail: '请输入详细地址',
		businessAddressProvince: '请选择省',
		businessAddressCity: '请选择市',
		businessAddressCounty: '请选择区',
		wechat: '请输入商家号'
	};

	// 注册
	$("#registerBtn").on("click",function(){
		var params = {};
		params.phone = $("#phone").val();
		params.phoneCode = $("#phoneCode").val();
		params.businessName = $("#businessName").val();
		params.wechat = $("#wechat").val();
		params.businessAddressProvince = $("#businessAddressProvince").val();
		params.businessAddressCity = $("#businessAddressCity").val();
		params.businessAddressCounty = $("#businessAddressCounty").val();
		params.businessAddressDetail = $("#businessAddressDetail").val();
		params.password = $("#password").val();
		params.rePassword = $("#rePassword").val();

		var bool = false;
		for (var i in params) {
			if (!params[i]) {
				layer.msg(paramsText[i]);
				bool = true;
				return;
			}
		}

		if (bool) return;
		params.email = '';
		handleRegister(params);
	});
});
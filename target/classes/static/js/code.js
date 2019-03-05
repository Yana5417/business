/**
 * 兑换
 */
$(function(){

	//兑换
	$("#getCode").on("click",function(){
		$.ajax({
			type:"post",
			url:urlBaseQ + "business/coupon/exchange",
			async:false,
			data:{
				couponCode: $("#code").val()
			},
			dataType:'json',
			success:function(response){
				console.log(response);
				if (response.code === 200) {
					layer.msg('兑换成功，兑换金额将在1-2各工作日内发送到您的微信账户，请注意查收～');
				} else {
					layer.msg(response.msg);
				}
			},
			error:function(response){

			}
		});
	});
});
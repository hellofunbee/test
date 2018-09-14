$(function(){
	$('.productLists>li').on('click',function(){
		var index=$(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$('.productDesign:eq('+index+')').show().siblings('.productDesign').hide()
	});
	$('.productLists>li').eq(0).click()
})

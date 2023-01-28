$(document).ready(function(){
});

//상품 수량 조절 버튼
$("button[name=plus_btn]").on("click", function(){
	let quantity = $(this).parent("div").find("input[name=count]").val();
	let price = $(this).parent().parent("div").find("input[name=product_price]").val();
	$(this).parent("div").find("input[name=count]").val(++quantity);
	$(this).parent().parent("div").find("span[name=product_sumprice]").text(quantity*price);
});

$("button[name=minus_btn]").on("click", function(){
	let quantity = $(this).parent("div").find("input[name=count]").val();
	let price = $(this).parent().parent("div").find("input[name=product_price]").val();
	if(quantity > 1){
		$(this).parent("div").find("input[name=count]").val(--quantity);
		$(this).parent().parent("div").find("span[name=product_sumprice]").text(quantity*price);	
	}
});

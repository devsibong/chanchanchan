
$(document).ready(function() {
	if($("span[name=cartCount]").text()=="0"){
		$("span[name=cartCount]").prop("hidden", true);
	}
});


$("a[name=add_cart]").on("click", function() {
	
	if(mem != null) {
		let id = $(this).parent().parent().parent("div").find("input[name=product_id]").val();
		var product = {
			product_id: id
		};
		$.ajax({
			url: "/addtocart",
			type: "POST",
			data: JSON.stringify(product),
			contentType: 'application/json'
		})
			.done(function(data) {
				if(data=="success"){
					alert("장바구니에 상품을 담았습니다.");
					cartIconCountRefresh();
				} else{
					alert("동일한 상품이 이미 장바구니에 있습니다.");
				}
				
			})
			.fail(function(request, error) {
				alert(request.status+request.responseText+error);
			});
	} else{
		alert("로그인이 필요합니다.")
		location.replace("/login");
	}
});

		
//장바구니 아이콘 수량
function cartIconCountRefresh() {
	let temp = parseInt($("span[name=cartCount]").text());
	$("span[name=cartCount]").prop("hidden", false);
	$("span[name=cartCount]").text(++temp);
	

}
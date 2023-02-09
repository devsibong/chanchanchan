
$(document).ready(function() {

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
			.done(function() {
				alert("장바구니에 추가되었습니다.");
				cartCountRefresh()
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
function cartCountRefresh() {
	let temp = parseInt($("span[name=cartCount]").text());
	$("span[name=cartCount]").text(
		++temp);
}
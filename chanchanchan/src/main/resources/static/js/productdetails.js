
$(document).ready(function() {
	let price = $("input[name=detail_product_price]").val();
	let count = $("input[name=count]").val();
	$("span[name=total_price]").text(priceViewFormatter(String(count * price)));
});

//금액 formatter
function priceViewFormatter(value) {
	value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	return value;
}

function priceNumFormatter(value) {
	value = value.replace(/[^\d]+/g, "");
	return value;
}


$("button[name=add_cart]").on("click", function() {
	if(mem != null) {
		let id = $("input[name=detail_product_id]").val();
		let count = $("input[name=count]").val();
		var newCart = {
			product_id: id,
			product_count: count
		};
		$.ajax({
			url: "/addtocartdetail",
			type: "POST",
			data: JSON.stringify(newCart),
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

		
//상품 수량 조절 버튼
$("button[name=detail_plus_btn]").on("click", function() {
	let price = $("input[name=detail_product_price]").val();
	let count = $("input[name=count]").val();
	$("input[name=count]").val(++count);
	$("span[name=total_price]").text(priceViewFormatter(String(count * price)));
});

$("button[name=detail_minus_btn]").on("click", function() {
	let price = $("input[name=detail_product_price]").val();
	let count = $("input[name=count]").val();
	if(count > 1) {
		$("input[name=count]").val(--count);
		$("span[name=total_price]").text(priceViewFormatter(String(count * price)));
	};
});
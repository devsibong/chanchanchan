$(document).ready(function() {
	totalPrice();
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

//장바구니 아이콘 수량
function cartCountRefresh() {
	let temp = $("span[name=product_sumprice]").length;
	$("span[name=cartCount]").text(temp);
}

//총 상품금액 합계
function totalPrice() {
	let totalPrice = 0;
	$("span[name=product_sumprice]").each(function() {
		var value = priceNumFormatter($(this).text());
		totalPrice += parseInt(value);
	});
	if(totalPrice == 0){
		$("#empty_cart").prop("hidden", false);
		$("#see_more").prop("hidden", true);
		$("#order").prop("hidden", true);
		$("#go_main").prop("hidden", false);
		$("span[name=totalPrice]").text(priceViewFormatter(String(totalPrice)));
	} else {
	$("span[name=totalPrice]").text(priceViewFormatter(String(totalPrice)));
	}
};

//상품 수량 조절 버튼
$("button[name=plus_btn]").on("click", function() {
	let id = $(this).parent().parent("div").find("input[name=cart_id]").val();
	let price = $(this).parent().parent("div").find("input[name=product_price]").val();
	let count = $(this).parent().parent("div").find("input[name=count]").val();
	$(this).parent().parent("div").find("input[name=count]").val(++count);
	$(this).parent().parent("div").find("span[name=product_sumprice]").text(priceViewFormatter(String(count * price)));
	totalPrice();
	var cart = {
		cart_id: id,
		product_count: count
	};
	$.ajax({
		url: "/updatecart",
		type: "POST",
		data: JSON.stringify(cart),
		contentType: 'application/json'
	})
		.done(function() {
		})
		.fail(function() {
			alert("fail");
		});
});

$("button[name=minus_btn]").on("click", function() {
	let id = $(this).parent().parent("div").find("input[name=cart_id]").val();
	let price = $(this).parent().parent("div").find("input[name=product_price]").val();
	let count = $(this).parent().parent("div").find("input[name=count]").val();
	if (count > 1) {
		$(this).parent().parent("div").find("input[name=count]").val(--count);
		$(this).parent().parent().parent("div").find("span[name=product_sumprice]").text(priceViewFormatter(String(count * price)));
		totalPrice();
		var cart = {
			cart_id: id,
			product_count: count
		};
		$.ajax({
			url: "/updatecart",
			type: "POST",
			data: JSON.stringify(cart),
			contentType: 'application/json'
		})
			.done(function() {
			})
			.fail(function() {
				alert(request.status+request.responseText+error);
			});
	}

});

//상품 삭제 버튼
$("a[name=close]").on("click", function() {
	let id = $(this).parent().parent("div").find("input[name=cart_id]").val();
	var cart = {
		cart_id: id
	};
	$.ajax({
		url: "/deletecart",
		type: "POST",
		data: JSON.stringify(cart),
		contentType: 'application/json'
	})
		.done(function() {
		})
		.fail(function(request, error) {
			alert(request.status+request.responseText+error);
		});
	$(this).parent().parent().parent().parent("div").remove();
	totalPrice();
	cartCountRefresh();
	
});


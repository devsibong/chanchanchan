
$(document).ready(function() {
	
	normalTotalPrice();
	regularTotalPrice();
	checkCart();
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
function normalTotalPrice() {
	let totalPrice = 0;
	$("span[name=normal_sumprice]").each(function() {
		var value = priceNumFormatter($(this).text());
		totalPrice += parseInt(value);
	});
	$("span[name=normalTotalPrice]").text(priceViewFormatter(String(totalPrice)));
};

//총 상품금액 합계
function regularTotalPrice() {
	let totalPrice = 0;
	$("span[name=regular_sumprice]").each(function() {
		var value = priceNumFormatter($(this).text());
		totalPrice += parseInt(value);
	});
	$("span[name=regularTotalPrice]").text(priceViewFormatter(String(totalPrice)));
};

//일반배송
function selectNormalProduct() {
	$("#normal_product").css("background-color","#0e4478").css("color","white");
	$("#regular_product").css("background-color","lightgray").css("color","gray");
	$("#regular_cart_list").prop("hidden", true);
	$("#cart_list").prop("hidden", false);
}

//정기배송
function selectRegularProduct() {
	$("#regular_product").css("background-color","#0e4478").css("color","white");
	$("#normal_product").css("background-color","lightgray").css("color","gray");
	$("#cart_list").prop("hidden", true);
	$("#regular_cart_list").prop("hidden", false);
}

function checkCart() {
		let cartCount = $("div[name=product_name]").size();
		let normalCartCount = $("div[name=normal_product_img]").size();
		let regularCartCount = $("div[name=regular_product_img]").size();
	if(cartCount == 0) {
		$("#normal_empty_cart").prop("hidden", false);
		$("#see_more").prop("hidden", true);
		$("#order").prop("hidden", true);
		$("#go_main").prop("hidden", false);
		$("#regular_product").attr("disabled", true);
		selectNormalProduct();		
	} else{
		//정기배송 상품만 있을 때
		if(normalCartCount == 0) {
			selectRegularProduct();
			$("#normal_product").attr("disabled", true);
			$("#regular_empty_cart").prop("hidden", true);
			$("#normal_empty_cart").prop("hidden", true);
			}
		//일반상품만 있을 때
		else if(regularCartCount == 0) {
			$("#regular_product").attr("disabled", true);
			$("#regular_empty_cart").prop("hidden", true);
			$("#normal_empty_cart").prop("hidden", true);
			selectNormalProduct();
		}
		else {
			selectNormalProduct();
			};
		}
}


//상품 수량 조절 버튼
$("button[name=plus_btn]").on("click", function() {
	let id = $(this).parent().parent("div").find("input[name=cart_id]").val();
	let price = $(this).parent().parent("div").find("input[name=product_price]").val();
	let count = $(this).parent().parent("div").find("input[name=count]").val();
	$(this).parent().parent("div").find("input[name=count]").val(++count);
	$(this).parent().parent("div").find("span[name=normal_sumprice]").text(priceViewFormatter(String(count * price)));
	$(this).parent().parent("div").find("span[name=regular_sumprice]").text(priceViewFormatter(String(count * price)));
	normalTotalPrice();
	regularTotalPrice();
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
		$(this).parent().parent().parent("div").find("span[name=normal_sumprice]").text(priceViewFormatter(String(count * price)));
		$(this).parent().parent().parent("div").find("span[name=regular_sumprice]").text(priceViewFormatter(String(count * price)));
		normalTotalPrice();
		regularTotalPrice();
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
	let normal = $("#normal_cart").text();
	let regular = $("#regular_cart").text();
	
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
	if ($(this).attr("id") == "normal_close") {
		$("#normal_cart").text(--normal);
	} else {
		$("#regular_cart").text(--regular);
	}
	normalTotalPrice();
	regularTotalPrice();
	cartCountRefresh();
	checkCart();
	let regularCartCount = $("div[name=regular_product_img]").size();
	if(regularCartCount > 0) {
		selectRegularProduct();
	}
	
});

//일반배송 선택
$("#normal_product").on("click", function() {
	selectNormalProduct();
});

//정기배송 선택
$("#regular_product").on("click", function() {
	selectRegularProduct();
});

//주문하기
$("#order").on("click", function() {
	if($("#regular_cart_list").is(":hidden")) {
		location.href="/normalorder"
	} else{
		location.href="/regularorder"
	}
});



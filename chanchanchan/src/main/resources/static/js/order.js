$(document).ready(function() {
	totalPrice();
	totalPayment();
});

//총 상품금액 합계
function totalPrice() {
	let totalPrice = 0;
	$("span[name=product_sumprice]").each(function() {
		var value = priceNumFormatter($(this).text());
		totalPrice += parseInt(value);
	});
	$("#total_price").text(priceViewFormatter(String(totalPrice)));
};

//총 결제금액
function totalPayment() {
	var total_price = parseInt(priceNumFormatter($("#total_price").text()));
	var shipping_fee = parseInt(priceNumFormatter($("#shipping_fee").text()));
	var total_payment = total_price + shipping_fee;
	$("#total_payment").text(priceViewFormatter(String(total_payment)));
}

//금액 formatter
function priceViewFormatter(value) {
	value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	return value;
}

function priceNumFormatter(value) {
	value = value.replace(/[^\d]+/g, "");
	return value;
}


//주문자와 동일
$("input#reciever_equals_member").on("click", function() {
	let member_index = $("#member_index").val();
	var member = {
		member_index: member_index
	};
	$.ajax({
		url: "/getmemberinfo",
		type: "POST",
		data: JSON.stringify(member),
		contentType: 'application/json'
	})
		.done(function(data) {
			$("#receiver").val(data.member_name);
			$("#receiver_tel").val(data.member_tel);
			
		})
		.fail(function() {
			alert(request.status+request.responseText+error);
		});
});

//요청사항
$("select#delivery_info_select").on("change", function() {
	switch($(this).val()) {
		case "0":
			$("#delivery_info").removeAttr("disabled");
			$("#delivery_info").val("");
			break;
		case "3":
			$("#delivery_info").removeAttr("disabled");
			$("#delivery_info").val("");
			break;
		default:
			$("#delivery_info").val($("#delivery_info_select option:selected").text());
			$("#delivery_info").attr("disabled",true); 
	};
});

//카카오페이
$("#payment").on("click", function(){
	$.ajax({
		url: "/payment/ready",
		type: "POST",
		data: 
		{
			"member_index": "100"
		}
	})
		.done(function(data) {
			window.name="temp";
			var popup = window.open(data.next_redirect_pc_url,"카카오페이", "width=400, height=700, scrollbars=yes, resizable=no");
			popup.focus();
			order();
		})
		.fail(function() {
		});
});


function order() {
	let member_index = $("#member_index").val();
	let product_totalprice = priceNumFormatter($("#product_sumprice").text());
	let shippingfee= priceNumFormatter($("#shipping_fee").text());
	let order_totalpayment= priceNumFormatter($("#total_payment").text());
	let payment_method= $("input[name=payment_check]:checked").next().text();
	let order_state= "배송준비";
	let receiver= $("#receiver").val();
	let receiver_tel= $("#receiver_tel").val();
	let delivery_info= $("#delivery_info").val();
	let shipping_title = "배송지";
	let shipping_address = $("#shipping_address").val();
	let shipping_address_detail = $("#shipping_address_detail").val();
	let shipping_zipcode = $("#shipping_zipcode").val();
	
	$.ajax({
		url: "/payment",
		type: "POST",
		data: 
		{
			"member_index": member_index,	
			"product_totalprice": product_totalprice,	
			"shippingfee": shippingfee,	
			"order_totalpayment": order_totalpayment,	
			"payment_method": payment_method,	
			"order_state": order_state,	
			"receiver": receiver,	
			"receiver_tel": receiver_tel,	
			"delivery_info": delivery_info,	
			"shipping_title": shipping_title,	
			"shipping_address": shipping_address,	
			"shipping_address_detail": shipping_address_detail,	
			"shipping_zipcode": shipping_zipcode
		}
	})
		.done(function() {
//			location.replace("/ordercomplete")
		})
		.fail(function() {
			//alert(request.status+request.responseText+error);
		});
};

//daum 주소검색서비스
var element_layer = document.getElementById('layer');
function closeDaumPostcode() {
	// iframe을 넣은 element를 안보이게 한다.
	element_layer.style.display = 'none';
}
function sample2_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
				}
				// 조합된 참고항목을 해당 필드에 넣는다.
				document.getElementById("shipping_address").value = extraAddr;

			} else {
				document.getElementById("shipping_address").value = '';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('shipping_zipcode').value = data.zonecode;
			document.getElementById("shipping_address").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("shipping_address_detail").focus();

			// iframe을 넣은 element를 안보이게 한다.
			// (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
			element_layer.style.display = 'none';
		},
		width: '100%',
		height: '100%',
		maxSuggestItems: 5
	}).embed(element_layer);

	// iframe을 넣은 element를 보이게 한다.
	element_layer.style.display = 'block';

	// iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
	initLayerPosition();
};

// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
// 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
function initLayerPosition() {
	var width = 400; //우편번호서비스가 들어갈 element의 width
	var height = 600; //우편번호서비스가 들어갈 element의 height
	var borderWidth = 5; //샘플에서 사용하는 border의 두께

	// 위에서 선언한 값들을 실제 element에 넣는다.
	element_layer.style.width = width + 'px';
	element_layer.style.height = height + 'px';
	element_layer.style.border = borderWidth + 'px solid';
	// 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
	element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth) + 'px';
	element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth) + 'px';
}





















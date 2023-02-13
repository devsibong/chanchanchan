$(document).ready(function() {
	$("div[name=product_info]").each(function() {
		let review_avg = parseFloat($(this).find("span[name=product_review]").text());
		if(4.75 < review_avg && review_avg <= 5){
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			console.log("test");
		}
		if(4.25 < review_avg && review_avg <= 4.75){
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-half'></i>");
		}
		if(3.75 < review_avg && review_avg <= 4.25){
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
		}
		if(3.25 < review_avg && review_avg <= 3.75){
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-half'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
		}
		if(2.75 < review_avg && review_avg <= 3.25){
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
		}
		if(2.25 < review_avg && review_avg <= 2.75){
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-half'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
		}
		if(1.75 < review_avg && review_avg <= 2.25){
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
		}
		if(1.25 < review_avg && review_avg <= 1.75){
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star-half'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
		}
		if(0.75 < review_avg && review_avg <= 1.25){
			$(this).find("li[name=star]").append("<i class='bi bi-star-fill'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
		}
		if(0.25 < review_avg && review_avg <= 0.75){
			$(this).find("li[name=star]").append("<i class='bi bi-star-half'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
		}
		if(review_avg <= 0.25){
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
			$(this).find("li[name=star]").append("<i class='bi bi-star'></i>");
		}
		


	});
});

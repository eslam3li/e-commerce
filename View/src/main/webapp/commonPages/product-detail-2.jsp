<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Tab01 -->
<script src="js/scripts.js"></script>
<div class="tab01">
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item p-b-10">
			<a class="nav-link active" data-toggle="tab" href="#description" role="tab">Description</a>
		</li>

		<li class="nav-item p-b-10">
			<a class="nav-link" data-toggle="tab" href="#information" role="tab">Information</a>
		</li>

		<li class="nav-item p-b-10">
			<a class="nav-link" data-toggle="tab" href="#reviews" role="tab" id="Reviews">Rate</a>
		</li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content p-t-43">
		<!-- - -->
		<div class="tab-pane fade show active" id="description" role="tabpanel">
			<div class="how-pos2 p-lr-15-md">
				<p class="stext-102 cl6" >
					${product.description}
				</p>
			</div>
		</div>

		<!-- - -->
		<div class="tab-pane fade" id="information" role="tabpanel">
			<div class="row">
				<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
					<ul class="p-lr-28 p-lr-15-sm">
						<li class="flex-w flex-t p-b-7">
							<span class="stext-102 cl3 size-205">
								Name
							</span>

							<span class="stext-102 cl6 size-206">
								${product.name}
							</span>
						</li>

						<li class="flex-w flex-t p-b-7">
							<span class="stext-102 cl3 size-205">
								Selling price
							</span>

							<span class="stext-102 cl6 size-206">
								$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.sellingPrice}" />
							</span>
						</li>

						<li class="flex-w flex-t p-b-7">
							<span class="stext-102 cl3 size-205">
								Sale
							</span>

							<span class="stext-102 cl6 size-206">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.sale}" /> %
							</span>
						</li>

						<li class="flex-w flex-t p-b-7">
								<span class="stext-102 cl3 size-205">
									Price after sale
								</span>
	
								<span class="stext-102 cl6 size-206">
									$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.sellingPrice * (1 - product.sale/100)}" />
								</span>
							</li>

						<li class="flex-w flex-t p-b-7">
							<span class="stext-102 cl3 size-205">
								Category
							</span>

							<span class="stext-102 cl6 size-206">
								${product.category.name}
							</span>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<!-- - -->
		<div class="tab-pane fade" id="reviews" role="tabpanel">
			<div class="row">
				<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
					<div class="p-b-30 m-lr-15-sm" >
						<!-- Review -->
						<div class="flex-w flex-t p-b-68" id="rev">
							<div class="wrap-pic-s size-109 bor0 of-hidden m-r-18 m-t-6">
								<img src="images/avatar-01.jpg" alt="AVATAR">
							</div>

							<div class="size-207">
								<div class="flex-w flex-sb-m p-b-17">
									<span class="mtext-107 cl2 p-r-20" id="userName">
										Ariana Grande
									</span>

									<span class="fs-18 cl11">
										<i  class="zmdi zmdi-star"></i>
										<i class="zmdi zmdi-star"></i>
										<i class="zmdi zmdi-star"></i>
										<i class="zmdi zmdi-star"></i>
										<i class="zmdi zmdi-star-half"></i>
									</span>
								</div>

								<p class="stext-102 cl6" id="userreview">
									Quod autem *******
								</p>
							</div>
						</div>

						<!-- Add review -->
						<form class="w-full">
							<h5 class="mtext-108 cl2 p-b-7">
								Add a review
							</h5>

							<p class="stext-102 cl6">
								Your email address will not be published. Required fields are marked *
							</p>

							<div class="flex-w flex-m p-t-50 p-b-23">
								<span class="stext-102 cl3 m-r-16">
									Your Rating
								</span>

								<span class="wrap-rating fs-18 cl11 pointer">
									<i class="item-rating pointer zmdi zmdi-star-outline"></i>
									<i class="item-rating pointer zmdi zmdi-star-outline"></i>
									<i class="item-rating pointer zmdi zmdi-star-outline"></i>
									<i class="item-rating pointer zmdi zmdi-star-outline"></i>
									<i class="item-rating pointer zmdi zmdi-star-outline"></i>
									<input class="dis-none" type="number" name="rating" id="rating">
								</span>
							</div>

							<div class="row p-b-25">
								<div class="col-12 p-b-5">
									<label class="stext-102 cl3" for="review">Your review</label>
									<textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review"
										name="review"></textarea>
								</div>

								<div class="col-sm-6 p-b-5">
									<label class="stext-102 cl3" for="name">Name</label>
									<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="name" type="text"
										name="name">
								</div>

							</div>

							<button id="send"  class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10">Send</button>
								
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
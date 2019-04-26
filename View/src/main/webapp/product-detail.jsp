<!DOCTYPE html>
<html lang="en">

<head>
	<title>Product Detail</title>
	<jsp:include page="commonPages/head.html"></jsp:include>
	<script src="js/scripts.js"></script>
</head>

<body onload="start();">

	<!-- Header -->
	<header class="header-v4">
		<!-- Header desktop -->
		<div class="container-menu-desktop">
			<jsp:include page="commonPages/topbar.html"></jsp:include>
		</div>

		<jsp:include page="commonPages/header.html"></jsp:include>
	</header>

	<jsp:include page="commonPages/cart.html"></jsp:include>


	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="home" class="stext-109 cl8 hov-cl1 trans-04">
				Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>

			<a href="home?category=${product.category.id}" class="stext-109 cl8 hov-cl1 trans-04">
				${product.category.name}
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>

			<span class="stext-109 cl4">
				${product.name}
			</span>
		</div>
	</div>


	<!-- Product Detail -->
	<section class="sec-product-detail bg0 p-t-65 p-b-60">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-lg-7 p-b-30">
					<div class="p-l-25 p-r-30 p-lr-0-lg">
						<div class="wrap-slick3 flex-sb flex-w">
							<div class="wrap-slick3-dots"></div>
							<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

							<div class="slick3 gallery-lb">
								<div class="item-slick3" data-thumb="${product.displayPicture}">
								   <div class="wrap-pic-w pos-relative">
								       <img src="${product.displayPicture}" alt="IMG-PRODUCT">
								       <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="${product.displayPicture}">
								           <i class="fa fa-expand"></i>
								       </a>
								   </div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-6 col-lg-5 p-b-30">
					<div class="p-r-50 p-t-5 p-lr-0-lg">
						<jsp:include page="commonPages/product-detail-1.jsp"></jsp:include>
					</div>
				</div>
			</div>

			<div class="bor10 m-t-50 p-t-43 p-b-40">

				<jsp:include page="commonPages/product-rate.jsp"></jsp:include>

                                
			</div>
		</div>

		<div class="bg6 flex-c-m flex-w size-302 m-t-73 p-tb-15">
			<span class="stext-107 cl6 p-lr-25">
				Category: ${product.category.name}
			</span>

			<span class="stext-107 cl6 p-lr-25">
				Categories: Jacket, Men
			</span>
		</div>
	</section>


	<jsp:include page="commonPages/footer.html"></jsp:include>


	<!-- Back to top -->
	<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
		</span>
	</div>

	<jsp:include page="commonPages/modal.html"></jsp:include>
	

	<jsp:include page="commonPages/scripts.jsp"></jsp:include>
	<script>
		$("#addToCart").click(function() {
		    addToCart(${product.id}, parseInt($("#productColors").val()), parseFloat($("#numProductItems").val()));
		});
	</script>
	<script src="js/product.js"></script>
</body>

</html>
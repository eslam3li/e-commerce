<!DOCTYPE html>
<html lang="en">

<head>
	<title>Home</title>
	<jsp:include page="commonPages/head.html"></jsp:include>
</head>

<body>

	<!-- Header -->
	<header class="header-v3">
		<!-- Header desktop -->
		<div class="container-menu-desktop trans-03">
			<jsp:include page="commonPages/topbar-home.html"></jsp:include>
		</div>

		<jsp:include page="commonPages/header-home.html"></jsp:include>
	</header>


	<jsp:include page="commonPages/sidebar.html"></jsp:include>


	<jsp:include page="commonPages/cart.html"></jsp:include>



	<jsp:include page="commonPages/slider.html"></jsp:include>


	<!-- Banner -->
	<div class="sec-banner bg0 p-t-95 p-b-55">
		<div class="container">
			<div class="row" id="categories">
			</div>
		</div>
	</div>


	<!-- Product -->
	<section class="bg0 p-t-23 p-b-130">
		<div class="container">
			<div class="p-b-10" id="productOverview">
				<h3 class="ltext-103 cl5">
					Product Overview
				</h3>
			</div>

			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
					<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" data-filter="*" onclick="getCategory(null)">
						All Products
					</button>

					<div id="parentCategories">
					</div>
					
					<div id="categoriesBar">
					</div>
				</div>

				<div class="flex-w flex-c-m m-tb-10">
					<div
						class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4 js-show-filter">
						<i class="icon-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-filter-list"></i>
						<i class="icon-close-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
						Filter
					</div>

					<div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search">
						<i class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"></i>
						<i class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
						Search
					</div>
				</div>

				<!-- Search product -->
				<div class="dis-none panel-search w-full p-t-10 p-b-15">
					<div class="bor8 dis-flex p-l-15">
						<button class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04">
							<i class="zmdi zmdi-search"></i>
						</button>

						<input class="mtext-107 cl2 size-114 plh2 p-r-15" type="text" name="search-product"
							placeholder="Search" id="search">
					</div>
				</div>

				<jsp:include page="commonPages/filter-bar.html"></jsp:include>
			</div>

			<div class="row isotope-grid products" id="products">
			</div>

			<!------ pagination ---------->
			<br>
			<div>
				<ul class="pagination-custom text-center" id="pages">
				</ul>
			</div>
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
	<script src="js/product.js"></script>
	<script>
		load(${products}, ${pages}, ${categories});
	</script>

</body>

</html>
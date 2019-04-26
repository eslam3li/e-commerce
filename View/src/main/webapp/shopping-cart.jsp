<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Shopping Cart</title>
	<jsp:include page="commonPages/head.html"></jsp:include>
</head>

<body class="animsition">

	<!-- Header -->
	<header class="header-v4">
		<!-- Header desktop -->
		<div class="container-menu-desktop">
			<jsp:include page="commonPages/topbar.html"></jsp:include>
		</div>

		<jsp:include page="commonPages/header.html"></jsp:include>
	</header>


	<!-- bread crumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="home" class="stext-109 cl8 hov-cl1 trans-04">
				Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>

			<span class="stext-109 cl4">
				Shopping Cart
			</span>
		</div>
	</div>


	<!-- Shopping Cart -->
	<form class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<thead>
									<tr class="table_head">
										<th class="column-1">Product</th>
										<th class="column-2"></th>
										<th class="column-3">Price</th>
										<th class="column-4">Quantity</th>
										<th class="column-5">Total</th>
									</tr>
								</thead>
								<tbody id="shoppingCart">
								</tbody>
							</table>
						</div>

						<div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm" id="coupon">
							<div class="flex-w flex-m m-r-20 m-tb-5">
								<input class="stext-104 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5" type="text"
									name="coupon" id="couponCode" placeholder="Coupon Code">

								<div
									class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5" id="applyCoupon">
									Apply coupon
								</div>
							</div>
							<div class="flex-w flex-m m-r-20 m-tb-5">
								<b style="color : red" id="couponError">Please enter a valid coupon</b>
								<b style="color : red" id="couponUsedError">This coupon is already used</b>
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
					<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-30">
							Cart Totals
						</h4>

						<div class="flex-w flex-t bor12 p-b-13">
							<div class="size-208">
								<span class="stext-110 cl2">
									Subtotal:
								</span>
							</div>

							<div class="size-209">
								<span class="mtext-110 cl2" id="cartTotalBeforeCoupon">
									$ 0.00
								</span>
							</div>

							<div class="size-208">
									<span class="stext-110 cl2">
										Coupon:
									</span>
								</div>
	
								<div class="size-209">
									<span class="mtext-110 cl2" id="cartCoupon">
										- $ 0.00
									</span>
								</div>
						</div>

						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2">
									Total:
								</span>
							</div>

							<div class="size-209 p-t-1">
								<span class="mtext-110 cl2" id="cartTotal">
									$ 0.00
								</span>
							</div>
						</div>

						<a class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer" id="payOrder" href="cart?operation=pay">
							Pay now
						</a>
						<br/>
						<a class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer" href="cart?operation=cancel">
							Cancel order
						</a>
					</div>
				</div>
			</div>
		</div>
	</form>




	<jsp:include page="commonPages/footer.html"></jsp:include>


	<!-- Back to top -->
	<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
		</span>
	</div>

	<jsp:include page="commonPages/scripts.jsp"></jsp:include>
	<script src="js/products.js"></script>
	<script src="js/cart.js"></script>
	<script src="js/add_to_cart.js"></script>
	<script src="js/shopping_cart.js"></script>
	<script>
		addShoppingCartItems(displayedCart.getCartItems());
	</script>

</body>

</html>
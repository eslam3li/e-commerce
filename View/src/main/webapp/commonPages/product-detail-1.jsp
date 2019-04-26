<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Product Detail -->
<h4 class="mtext-105 cl2 js-name-detail p-b-14">
	${product.name}
</h4>

<span class="mtext-106 cl2">
	<c:if test="${product.sale > 0}">
		<strike>
			$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.sellingPrice}" />
		</strike>
		<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.sale}" /> %
		<br/>
		$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.sellingPrice * (1 - product.sale/100)}" />
	</c:if>
	<c:if test="${product.sale == 0}">
		$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.sellingPrice * (1 - product.sale/100)}" />
	</c:if>
</span>


<!--  -->
<div class="p-t-33">

	<c:if test="${not empty product.productItems}">

		<div class="flex-w flex-r-m p-b-10">
			<div class="size-203 flex-c-m respon6">
				Color
			</div>

			<div class="size-204 respon6-next">
				<div class="rs1-select2 bor8 bg0">
					<select class="js-select2" id="productColors" name="time">
						<c:forEach items="${product.productItems}" var="productItem">
							<option value="${productItem.id}">${productItem.color}</option>
						</c:forEach>
					</select>
					<div class="dropDownSelect2"></div>
				</div>
			</div>
		</div>

	</c:if>

	<div class="flex-w flex-r-m p-b-10">
		<div class="size-204 flex-w flex-m respon6-next">
			<c:if test="${not empty product.productItems}">
				<div class="wrap-num-product flex-w m-r-20 m-tb-10">
					<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
						<i class="fs-16 zmdi zmdi-minus"></i>
					</div>

					<input class="mtext-104 cl3 txt-center num-product" type="number" id="numProductItems" name="num-product" value="1">

					<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
						<i class="fs-16 zmdi zmdi-plus"></i>
					</div>
				</div>
			</c:if>

			<ul class="rating">
				<li class="fa fa-star"></li>
				<li class="fa fa-star"></li>
				<li class="fa fa-star"></li>
				<li class="fa fa-star"></li>
				<li class="fa fa-star disable"></li>
			</ul>

			<c:if test="${not empty product.productItems}">
				<button class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04" id="addToCart">
					Add to cart
				</button>
			</c:if>
			<c:if test="${empty product.productItems}">
				<b style="color : red">Out of stock</b>
			</c:if>
		</div>
	</div>
</div>

<!--  -->
<div class="flex-w flex-m p-l-100 p-t-40 respon7">
	<div class="flex-m bor9 p-r-10 m-r-11">
		<a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100"
			data-tooltip="Add to Wishlist">
			<i class="zmdi zmdi-favorite"></i>
		</a>
	</div>
</div>
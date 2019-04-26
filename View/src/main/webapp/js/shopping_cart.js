var displayedCart = new Cart();
var promoCode = null;

function addOrderTotal(amount) {
    addToTotal(amount);
    var total = displayedCart.getCartTotal();
    $("#cartTotalBeforeCoupon").html("$ " + formatNumber(total));
    if(promoCode != null) {
        $("#cartCoupon").html("- $ "+formatNumber(promoCode.discount));
        total -= promoCode.discount;
    }
    $("#cartTotal").html("$ " + formatNumber(total));
}

function addShoppingCartItem(cartItem) {
    var id = cartItem.productItemBean.id;
    $("#shoppingCart").append(
        '<tr class="table_row" id="cartItem'+id+'">'+
        '	<td class="column-1">'+
        '		<div class="how-itemcart1" onclick="removeShoppingCartItem('+id+')">'+
        '			<img src="'+cartItem.product.displayPicture+'" alt="IMG">'+
        '		</div>'+
        '	</td>'+
        '	<td class="column-2">'+cartItem.product.name+" "+cartItem.productItemBean.color+'</td>'+
        '	<td class="column-3">$ '+formatNumber(cartItem.product.sellingPrice)+'</td>'+
        '	<td class="column-4">'+
        '		<div class="wrap-num-product flex-w m-l-auto m-r-0">'+
        '			<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m" onclick="addQuantity('+id+', -1)">'+
        '				<i class="fs-16 zmdi zmdi-minus"></i>'+
        '			</div>'+
        '			<input class="mtext-104 cl3 txt-center num-product" type="number" name="cartItemQuantity'+id+'" id="cartItemQuantity'+id+'" value="'+cartItem.quantity+'">'+
        '			<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m" onclick="addQuantity('+id+', 1)">'+
        '				<i class="fs-16 zmdi zmdi-plus"></i>'+
        '			</div>'+
        '		</div>'+
        '	</td>'+
        '	<td class="column-5" id="cartItemTotal'+id+'">$ '+formatNumber(cartItem.quantity * cartItem.product.sellingPrice)+'</td>'+
        '</tr>'
    );
    $("#cartItemQuantity"+id).on("keyup", function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            addQuantity(id, parseFloat($("#cartItemQuantity"+id).val()));
        }
    });
}

function addShoppingCartItems(cartItems) {
    for(var i = 0 ; i < cartItems.length ; i++) {
        addShoppingCartItem(cartItems[i]);
    }
    var total = displayedCart.getCartTotal();
    $("#cartTotalBeforeCoupon").html("$ " + formatNumber(total));
    $("#cartTotal").html("$ " + formatNumber(total));
}

function removeShoppingCartItem(cartItemId) {
    var cartItem = displayedCart.getCartItem(cartItemId);
    $.ajax({
		type : "POST",
		url : "cart",
		data : {productItemId : cartItemId, operation : "remove"},
        dataType : "text",
        contentType:"application/x-www-form-urlencoded",
		success : function(result) {
			$("#cartItem"+cartItemId).remove();
            displayedCart.removeCartItem(cartItemId);
            addOrderTotal(-(cartItem.product.sellingPrice * cartItem.quantity));
        },
        error : function(result) {
            if(result.responseText == undefined || result.responseText == "") {
                swal("Error", "An unknown error occurred. Please try again.", "error");
            }
            else {
                swal("Error", result.responseText, "error");
            }
        }
    });
}

function addPromoCode(promoCode) {
    $("#coupon").html(
        '<tr class="table_row" id="cartItem'+id+'">'+
        '	<td class="column-2">PromoCode '+promoCode.code+'</td>'+
        '	<td class="column-3">- $ '+formatNumber(promoCode.discount)+'</td>'+
        '</tr>'
    );
}

function addQuantity(cartItemId, value) {
    var quantity = $("#cartItemQuantity"+cartItemId).val();
    var cartItem = displayedCart.getCartItem(cartItemId);
    var cartItemQuantity = parseFloat(quantity) + value;
    if(cartItemQuantity > 0 && Number.isInteger(cartItemQuantity)) {
        cartItem.quantity = parseInt(quantity) + value;
        $.ajax({
            type : "POST",
            url : "cart",
            data : {cartItem : JSON.stringify(cartItem), operation : "add"},
            dataType : "text",
            contentType:"application/x-www-form-urlencoded",
            success : function(result) {
                $("#cartItemTotal"+cartItemId).html("$ " + formatNumber(cartItem.quantity * cartItem.product.sellingPrice));
                displayedCart.addCartItem(cartItem);
                addOrderTotal(cartItem.product.sellingPrice * value);
            },
            error : function(result) {
                if(result.responseText == undefined || result.responseText == "") {
                    swal("Error", "An unknown error occurred. Please try again.", "error");
                }
                else {
                    swal("Error", result.responseText, "error");
                }
            }
        });
        $("#cartItemTotal"+cartItemId).html("$ " + formatNumber(cartItem.quantity * cartItem.product.sellingPrice));
        displayedCart.addCartItem(cartItem);
        addOrderTotal(cartItem.product.sellingPrice * value);
    }
    else {
        $("#cartItemTotal"+cartItemId).html("$ " + formatNumber(cartItem.product.sellingPrice));
        swal(cartItem.product.name + " " + cartItem.productItemBean.color, "Please enter a correct quantity", "error");
    }
}

$("#couponError").hide();
$("#couponUsedError").hide();

$("#applyCoupon").click(function() {
    $.ajax({
		type : "POST",
		url : "cart",
		data : {coupon : $("#couponCode").val(), operation : "add"},
        dataType : "json",
        contentType:"application/x-www-form-urlencoded",
		success : function(result) {
            $("#couponError").hide();
            $("#couponUsedError").hide();
            promoCode = result;
            addOrderTotal(0);
        },
        error : function(result) {
            if(result.status == 401) {
                swal("Error", result.responseText, "error");
            }
            else {
                if(result.responseText != "") {
                    $("#couponUsedError").show();
                    $("#couponError").hide();
                }
                else {
                    $("#couponError").show();
                    $("#couponUsedError").hide();
                }
            }
        }
    });
});
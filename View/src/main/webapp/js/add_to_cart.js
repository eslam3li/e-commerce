var displayedCart = new Cart();
var displayedProducts = new Products();

function addToTotal(amount) {
    var total = displayedCart.getCartTotal() + amount;
    displayedCart.setCartTotal(total);
    $("#cartTotal").html("Total: $ " + formatNumber(total));
}

function addNotification(number) {
    var cartItems = parseInt($("#cartItemsNum").attr("data-notify"));
    var cartItemsTotal = cartItems + number;
    $("#cartItemsNum").attr("data-notify", cartItemsTotal);
}

function removeCartItem(id) {
    $.ajax({
		type : "POST",
		url : "cart",
		data : {productItemId : id, operation : "remove"},
        dataType : "text",
        contentType:"application/x-www-form-urlencoded",
		success : function(result) {
			$("#cartItem"+id).remove();
            var cartItem = displayedCart.getCartItem(id);
            addToTotal(-(cartItem.product.sellingPrice * cartItem.quantity));
            displayedCart.removeCartItem(id);
            addNotification(-1);
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

function emptyCart() {
    $("#cart").empty();
    displayedCart.removeCart();
    $("#cartItemsNum").attr("data-notify", "0");
}

function addCartItem(cartItem) {
    var id = cartItem.productItemBean.id;
    if($("#cartItem"+id).length > 0) {
        cartItem.quantity += displayedCart.getCartItem(id).quantity;
        removeCartItem(id);
    }
    $("#cart").append(
        '<li class="header-cart-item flex-w flex-t m-b-12" id="cartItem'+id+'">'+
        '   <div class="header-cart-item-img" onclick="removeCartItem('+id+')">'+
        '      <img src="'+cartItem.product.displayPicture+'" alt="IMG">'+
        '   </div>'+
        '   <div class="header-cart-item-txt p-t-8">'+
        '       <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">'+
        '           '+cartItem.product.name+" "+cartItem.productItemBean.color+
        '       </a>'+
        '       <span class="header-cart-item-info">'+
        '           '+cartItem.quantity+' x $ '+formatNumber(cartItem.product.sellingPrice)+
        '       </span>'+
        '   </div>'+
        '</li>'
    );
    addToTotal(cartItem.product.sellingPrice * cartItem.quantity);
    addNotification(1);
}

function addCartItems(cartItems) {
    for(var i = 0 ; i < cartItems.length ; i++) {
        addCartItem(cartItems[i]);
        displayedCart.addCartItem(cartItems[i]);
    }
}

function addCartItemsFromLocalStorage() {
    displayedCart.setCartTotal(0);
    var cartItems = displayedCart.getCartItems();
    for(var i = 0 ; i < cartItems.length ; i++) {
        addCartItem(cartItems[i]);
    }
}

function cartInit() {
    emptyCart();
    displayedCart.setCartTotal(0);
    $("#cartTotal").html("Total: $ 0.00");
}

function addToCart(productId, productItemId, cartItemQuantity) {
    var cartItemProduct = displayedProducts.getProduct(productId);
    var productItem = cartItemProduct.productItems.find(function(item) {
        return item.id == productItemId;
    });
    if(cartItemQuantity > 0 && Number.isInteger(cartItemQuantity)) {
        cartItemProduct.productItems = null;
        var cartItem = {productItemBean : productItem,
            quantity : cartItemQuantity,
            product : cartItemProduct};
        $.ajax({
            type : "POST",
            url : "cart",
            data : {cartItem : JSON.stringify(cartItem), operation : "add"},
            dataType : "text",
            contentType:"application/x-www-form-urlencoded",
            success : function(result) {
                addCartItem(cartItem, cartItemProduct.id);
                displayedCart.addCartItem(cartItem);
                swal(cartItemProduct.name + " " + productItem.color, "is added to cart !", "success");
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
    else {
        swal(cartItemProduct.name + " " + productItem.color, "Please enter a correct quantity", "error");
    }
}
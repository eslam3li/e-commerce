function Cart() {
}

Cart.prototype.setCartTotal = function(total) {
    localStorage.setItem("cartTotal", total);
}

Cart.prototype.getCartTotal = function() {
    var cartTotalString = localStorage.getItem("cartTotal");
    var cartTotal;
    if(cartTotalString == null) {
        cartTotal = 0;
    }
    else {
        cartTotal = parseFloat(cartTotalString);
    }
    return cartTotal;
}

Cart.prototype.setCart = function(cart) {
    cart = cart.filter(function(value, index, arr) {
        return arr.indexOf(value) === index;
    });
    localStorage.setItem("cart", JSON.stringify(cart));
}

Cart.prototype.getCart = function() {
    var cart = JSON.parse(localStorage.getItem("cart"));
    if(cart == null) {
        cart = [];
    }
    return cart;
}

Cart.prototype.removeCart = function() {
    var cart = this.getCart();
    for(var i = 0 ; i < cart.length ; i++) {
        localStorage.removeItem("cartItem" + i);
    }
	localStorage.removeItem("cart");
}

Cart.prototype.addCartItem = function(cartItem) {
    localStorage.setItem("cartItem" + cartItem.productItemBean.id, JSON.stringify(cartItem));
    var cart = this.getCart();
    cart.push(cartItem.productItemBean.id);
    this.setCart(cart);
}

Cart.prototype.getCartItem = function(cartItemId) {
    return JSON.parse(localStorage.getItem("cartItem" + cartItemId));
}

Cart.prototype.getCartItems = function() {
    var cart = this.getCart();
    var cartItems = [];
    for(var i = 0 ; i < cart.length ; i++) {
        cartItems.push(this.getCartItem(cart[i]));
    }
    return cartItems;
}

Cart.prototype.removeCartItem = function(cartItemId) {
    localStorage.removeItem("cartItem" + cartItemId);
    var cart = this.getCart();
    var cartItemIndex = cart.find(function(id) {
        return cartItemId == id;
    });
    cart = cart.splice(cartItemIndex, 1);
    displayedCart.setCart(cart);
}
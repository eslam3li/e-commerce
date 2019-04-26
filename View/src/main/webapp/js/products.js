function Products() {
}

Products.prototype.setProducts = function(products) {
	products = products.filter(function(value, index, arr) {
        return arr.indexOf(value) === index;
    });
	localStorage.setItem("products", JSON.stringify(products));
}

Products.prototype.getProducts = function() {
	var products = JSON.parse(localStorage.getItem("products"));
	if(products == null) {
		products = [];
	}
	return products;
}

Products.prototype.removeProducts = function() {
	var products = this.getProducts();
	for(var i = 0 ; i < products.length ; i++) {
		localStorage.removeItem("product" + i);
	}
	localStorage.removeItem("products");
}

Products.prototype.addProduct = function(product) {
	localStorage.setItem("product" + product.id, JSON.stringify(product));
	var products = this.getProducts();
    products.push(product.id);
    this.setProducts(products);
}

Products.prototype.getProduct = function(productId) {
	return JSON.parse(localStorage.getItem("product" + productId));
}

Products.prototype.removeProduct = function(productId) {
	localStorage.removeItem("product" + productId);
	var products = this.getProducts();
    var productIndex = products.find(function(id) {
        return productId == id;
    });
    products = products.splice(productIndex, 1);
    this.setProducts(products);
}
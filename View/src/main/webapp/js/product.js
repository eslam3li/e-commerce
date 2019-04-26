var displayedProducts = new Products();
var currentPage = 1;
var currentCategory = null;
var currentFilter = {};

/* Dynamic html */

function addProduct(product) {
	var picture = product.displayPicture;
	var price;
	if(product.sale > 0) {
		price = "<strike> $ " + formatNumber(product.sellingPrice) + "</strike> " + formatNumber(product.sale) + " % <br/> $ " + formatNumber(product.sellingPrice * (1 - product.sale/100));
	}
	else {
		price = "$ " + formatNumber(product.sellingPrice);
	}
    $("#products").append(
        '<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item '+product.category.name+'" id="product'+product.id+'">'+
        '	<div class="block2">'+
        '		<div class="block2-pic hov-img0 center-cropped">'+
        '			<img src="'+picture+'" alt="IMG-PRODUCT">'+
        '			<a href="#" onclick="updateModal('+product.id+')" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1">'+
        '				Quick View'+
        '			</a>'+
        '		</div>'+
        '		<div class="block2-txt flex-w flex-t p-t-14">'+
        '			<div class="block2-txt-child1 flex-col-l ">'+
        '				<a href="product-detail?productId='+product.id+'" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">'+product.name+
        '				</a>'+
        '				<span class="stext-105 cl3">'+price+
        '				</span>'+
        '			</div>'+
        '			<div class="block2-txt-child2 flex-r p-t-3">'+
        '				<a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">'+
        '					<img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">'+
        '					<img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">'+
        '				</a>'+
        '			</div>'+
        '		</div>'+
        '	</div>'+
        '</div>'
	);
	displayedProducts.addProduct(product);
	showModal();
}

function updateModal(productId) {
	var product = displayedProducts.getProduct(productId);
	var picture = product.displayPicture;
	$("#productDisplayImage").html(
		'<div class="item-slick3" data-thumb="'+picture+'">'+
		'	<div class="wrap-pic-w pos-relative">'+
		'		<img src="'+picture+'" alt="IMG-PRODUCT"/>'+
		'		<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"'+
		'			href="'+picture+'">'+
		'			<i class="fa fa-expand"></i>'+
		'		</a>'+
		'	</div>'+
		'</div>'
	);
	$("#productImages").empty();
	for(var i = 0 ; i < product.pictures.length ; i++) {
		var picture = product.pictures[i];
		$("#productImages").append();
	}
	$("#productName").text(product.name);
	$("#productPrice").text("$"+formatNumber(product.sellingPrice));
	$("#productDescription").text(product.description);
	$("#numProductItems").val(1);
	$("#productColors").empty();
	for(var i = 0 ; i < product.productItems.length ; i++) {
		var productItem = product.productItems[i];
		$("#productColors").append('<option value="'+productItem.id+'">'+productItem.color+'</option>');
	}
	$("#addToCart").off();
	$("#addToCart").click(function() {
		addToCart(product.id, parseInt($("#productColors").val()), parseFloat($("#numProductItems").val()));
	});
}

function addProducts(products) {
	$("#products").fadeOut(function() {
		emptyProducts();
		if(products.length > 0) {
			for(var i = 0 ; i < products.length ; i++) {
				addProduct(products[i]);
			}
		}
		else {
			$("#products").append("<h2><i>No data found</i></h2>");
			$("#pages").empty();
		}
		$("#products").removeAttr("style").fadeIn(function() {
			$("html").animate({
				scrollTop: $("#productOverview").offset().top
			}, 500);
		});
	});
}

function removeProduct(productId) {
	$("#product"+productId).remove();
	displayedProducts.removeProduct(productId);
}

function emptyProducts() {
	$("#products").empty();
	displayedProducts.removeProducts();
}

function addCategory(category) {
	var classes = "col-md-6 p-b-30 m-lr-auto";
	if(category.big != true) {
		classes = classes + " col-lg-4";
	}
	var picture = category.picture;
	$("#categories").append(
		'<div class="'+classes+'" id="category'+category.id+'">'+
		'	<!-- Block1 -->'+
		'	<div class="block1-txt-child1 flex-col-l">'+
		'		<span class="block1-name ltext-102 trans-04 p-b-8">'+category.name+
		'		</span>'+
		'	</div>'+
		'	<div class="block1 wrap-pic-w">'+
		'		<img src="'+picture+'" alt="IMG-BANNER" class="center-cropped-big"/>'+
		'		<a onclick="getCategory('+category.id+')"'+
		'			class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">'+
		'			<div class="block1-txt-child2 p-b-4 trans-05">'+
		'				<div class="block1-link stext-101 cl0 trans-09">'+
		'					Shop Now'+
		'				</div>'+
		'			</div>'+
		'		</a>'+
		'	</div>'+
		'</div>'
	);
	$("#categoriesBar").append(
		'<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".'+category.name+
		'" id="categoryButton'+category.id+'" onclick="getCategory('+category.id+')">'+category.name+
		'</button>'
	);
}

function addParentCategory(parentCategory) {
	if(parentCategory != null) {
		$("#parentCategory"+parentCategory.id).remove();
		$("#parentCategories").append(
			'<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5"'+
			' id="parentCategory'+parentCategory.id+'" onclick="getParentCategory('+parentCategory.id+')">'+parentCategory.name+
			'</button>'
		);
	}
}

function addCategories(categories) {
	$("#categoriesBar").fadeOut();
	$("#categories").fadeOut(function() {
		emptyCategories();
		for(var i = 0 ; i < categories.length ; i++) {
			if(i < 2) {
				categories[i].big = true;
			}
			addCategory(categories[i]);
		}
		$("#categoriesBar").fadeIn();
		$("#categories").fadeIn();
	});
}

function removeCategory(categoryId) {
	$("#category"+categoryId).remove();
	$("#categoryButton"+categoryId).remove();
}

function emptyCategories() {
	$("#categories").empty();
	$("#categoriesBar").empty();
}

function addPages(pages) {
	if(pages > 0) {
		$("#pages").fadeTo("slow", 0.75, function() {
			$("#pages").empty();
			$("#pages").append('<li class="pag-link"><a onclick="getProducts(1)"><i class="fa fa-angle-double-left"></i></a></li>');
			var start
			var end;
			if(pages <= 5) {
				start = 1;
				end = pages;
			}
			else if(currentPage < 3) {
				start = 1;
				end = 5;
			}
			else if(currentPage > pages-2) {
				start = pages - 4;
				end = pages;
			}
			else {
				start = currentPage - 2;
				end = currentPage + 2;
			}
			for(var i = start ; i <= end ; i++) {
				if(i != currentPage) {
					$("#pages").append('<li class="pag-link"><a onclick="getProducts('+i+')">'+i+'</a></li>');
				}
				else {
					$("#pages").append('<li class="pag-link current"><span>'+i+'</span></li>');
				}
			}
			$("#pages").append('<li class="pag-link"><a onclick="getProducts('+pages+')"><i class="fa fa-angle-double-right"></i></a></li>');
			$("#pages").fadeTo("slow", 1.0);
		});
	}
}

function load(products, pages, categories) {
	setTimeout(function() {
		addProducts(products);
		getCurrentPage();
		addPages(parseInt(pages));
		addCategories(categories);
	}, 500);
}

/* Ajax */

function getProducts(pageNumber) {
	var query = Object.assign({}, currentFilter);
	query.page = pageNumber;
	getCurrentCategory();
	if(currentCategory != null) {
		query.category = currentCategory;
	}
	$.ajax({
		type : "GET",
		url : "products",
		data : query,
		dataType : "json",
		success : function(result) {
			currentPage = pageNumber;
			addProducts(JSON.parse(result.products));
			addPages(parseInt(result.pages));
		}
	});
}

function getCategory(categoryId) {
	var query = Object.assign({}, currentFilter);
	query.page = 1;
	if(categoryId != null) {
		query.category = categoryId;
	}
	$.ajax({
		type : "GET",
		url : "products",
		data : query,
		dataType : "json",
		success : function(result) {
			currentPage = 1;
			currentCategory = categoryId;
			var categories = JSON.parse(result.categories);
			var category = JSON.parse(result.category);
			if(category == null) {
				$("#parentCategories").empty();
			}
			else {
				if(categories.length > 0) {
					category.name = category.name + " >";
				}
			}
			addParentCategory(category);
			addCategories(categories);
			addProducts(JSON.parse(result.products));
			addPages(parseInt(result.pages));
		}
	});
}

function getParentCategory(categoryId) {
	$("#parentCategory"+categoryId).nextAll().remove();
	getCategory(categoryId);
}

/* Filters */

function setPrice(min, max) {
	currentFilter.priceMin = min;
	currentFilter.priceMax = max;
}

function setSort(by, order) {
	if(by == "default") {
		currentFilter.sortBy = "id";
		currentFilter.sortOrder = "asc";
	}
	else if(by == "price") {
		currentFilter.sortBy = by;
		currentFilter.sortOrder = order;
	}
}

function setColor(col) {
	currentFilter.color = col;
}

function getCurrentPage() {
	var pageQuery = getQueryVariable("page", 1);
	currentPage = parseInt(pageQuery);
	return currentPage;
}

function getCurrentCategory() {
	if(currentCategory == null) {
		var categoryId = getQueryVariable("category", null);
		if(categoryId != null) {
			currentCategory = parseInt(categoryId);
		}
	}
	return currentCategory;
}

function getCurrentFilter() {
	var min = getQueryVariable("priceMin", null);
	if(min != null) {
		currentFilter.priceMin = parseInt(min);
	}
	var max = getQueryVariable("priceMax", null);
	if(max != null) {
		currentFilter.priceMax = parseInt(max);
	}
	var col = getQueryVariable("color", null);
	if(col != null) {
		currentFilter.color = col;
	}
	setSort(getQueryVariable("sortBy", null), getQueryVariable("sortOrder", null));
	return currentFilter;
}

function filterSort(sortBy, sortOrder) {
	setSort(sortBy, sortOrder);
	getProducts(1);
}

function filterPrice(min, max) {
	setPrice(min, max);
	getProducts(1);
}

function filterColor(color) {
	setColor(color);
	getProducts(1);
}

/* Set up */

window.onresize = function() {
	setTimeout('$("#products").removeAttr("style")', 200);
};

$("#search").on("keyup", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		currentFilter.search = searchText.value;
		getProducts(currentPage);
	}
});
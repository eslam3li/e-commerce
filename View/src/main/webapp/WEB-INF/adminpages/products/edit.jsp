<%-- 
    Document   : Add product    Created on : Apr 12, 2019, 12:51:53 PM
    Author     : Saror Mohamed
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="../partial/head.jsp" flush="true">
        <jsp:param name="pageTitle" value="Edit product"/>
    </jsp:include>
  </head>
  <body class="animsition">
    <jsp:include page="../partial/headermobile.jsp"/>
    <jsp:include page="../partial/sidebar.jsp"/>

    <!-- PAGE CONTAINER-->
    <div class="page-container">
      <jsp:include page="../partial/headerdesktop.jsp"/>
      <!-- MAIN CONTENT-->
      <div class="main-content">
        <div class="section__content section__content--p30">
          <div class="container-fluid">
            <div class="row">
              <div class="col-lg-12">
                <div class="card card-outline">
                  <div class="card-header">
                    <strong>Edit Product</strong>
                  </div>

                  <div class="card-body card-block">
                    <form name="productForm" action="" method="post" enctype="multipart/form-data" class="form-horizontal">                                                

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Category</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="text" value="${product.category.name}" readonly class="form-control">
                        </div>
                      </div>   

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Product Name</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="text" id="text-input" value="${product.name}" name="productName" required="true" class="form-control">
                        </div>
                      </div>   


                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label" >Description</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <textarea type="text" rows="6" id="text-input" name="description" required="true" class="form-control">${product.description}</textarea>
                        </div>
                      </div>                                                            
                      <br>

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="file-input" class=" form-control-label">Default picture</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="file" class="form-control-file"  name="defaultPicture" accept="image/*" required="true" multiple="false">
                        </div>
                      </div>

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="file-multiple-input" class=" form-control-label">More pictures</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="file" class="form-control-file"  multiple="true" name="pictures" accept="image/*" required="true">
                        </div>
                      </div>

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Base price<Br>(without profit)</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="number" name="basePrice" required="true" class="form-control" value="${product.basePrice}">
                        </div>
                      </div>

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Selling price</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="number" name="sellingPrice" required="true" class="form-control"  value="${product.sellingPrice}">
                        </div>
                      </div>

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label" value="${product.sale}">Sale price<br>If you are offering sales</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="number" name="sale" class="form-control" value="0">
                        </div>
                      </div>

                      <input type="hidden" name="productItemsJson">

                      <div style="text-align: right">
                        <button type="submit" class="btn btn-primary" onclick="prepareProductItems()">
                          Save updates
                        </button>
                      </div>
                    </form>

                    <hr>

                    <div class="table-responsive table--no-card m-b-30">
                      <table id="product-items-table" class="table table-borderless table-striped table-earning">
                        <thead>
                          <tr>                                              
                            <th>Color</th>
                            <th>Quantity in Stock</th>
                            <th>Delete</th>
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
                    </div>
                    <form class="form-horizontal" name="productItemsMapForm" onsubmit="addProductItems(event)">

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Color</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input  type="text" name="color" class="form-control">
                        </div>
                      </div>

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Quantity in stock</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="number" name="quantity" class="form-control">
                        </div>
                      </div>
                      <div style="text-align: right">
                        <button type="reset" class="btn btn-danger">
                          Reset
                        </button>
                        <button type="submit" class="btn btn-primary">
                          Add Product Item
                        </button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>                         
            </div>
          </div>
        </div>
      </div>
      <!-- END MAIN CONTENT-->
    </div>
    <!-- END PAGE CONTAINER-->

    <script>
        var productItemsMap = {};
        function addProductItems(ev) {
            ev.preventDefault();
            var colorField = window.productItemsMapForm.color;
            var quantityField = window.productItemsMapForm.quantity;
            productItemsMap[colorField.value] = quantityField.value;
            updateProductItemsTable();
            colorField.value = "";
            quantityField.value = "";
        }

        function updateProductItemsTable() {
            var html = "";
            for (var color in productItemsMap) {
                html += "<tr><td>" + color + "</td><td>" +
                        productItemsMap[color] + "</td><td>" +
                        "<button onclick=\"deleteProductItemsTable('" + color + "')\">Delete</button>" + "</td></tr>";
            }
            var table = document.getElementById("product-items-table");
            var tbody = table.getElementsByTagName("tbody")[0];
            tbody.innerHTML = html;
        }

        function deleteProductItemsTable(color) {
            delete productItemsMap[color];
            updateProductItemsTable();
        }

        function prepareProductItems() {
            var productItemsMapField = window.productForm.productItemsJson;
            var productItems = [];
            for (var color in productItemsMap) {
                productItems.push({color: color, quantity: productItemsMap[color]});
            }
            productItemsMapField.value = JSON.stringify(productItems);
        }
        var json = ${productItemBeansJson};
        for (var i = 0; i < json.length; i++) {
            productItemsMap[json[i].color] = json[i].quantity;
        }
        updateProductItemsTable();
    </script>
    <jsp:include page="../partial/scripts.jsp"/>
  </body>
</html>
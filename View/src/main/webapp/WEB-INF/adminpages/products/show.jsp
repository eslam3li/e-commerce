<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="../partial/head.jsp" flush="true">
        <jsp:param name="pageTitle" value="Products"/>
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
              <div class="col-md-12">
                <div class="overview-wrap">
                  <h2 class="title-1">Products</h2>
                  <a href="addproduct" class="au-btn au-btn-icon au-btn--blue" style="color: white;">
                    <i class="zmdi zmdi-plus"></i>Add Product</a>
                </div>
              </div>
            </div>
            <br>
            <div class="row">
              <div class="col-lg-12">
                <div class="card card-outline">
                  <div class="card-header">
                    <strong>Filter</strong>
                  </div>
                  <div class="card-body card-block">
                    <form method="get" onsubmit="false" class="form-horizontal">  
                      <div class="row form-group">
                        <div class="col col-md-4">
                          <input type="text" name="name" placeholder="Name" class="form-control" value="${param.name}"/>
                        </div>
                        <div class="col col-md-2">
                          <input type="number" name="priceMin" placeholder="Min price" class="form-control" value="${param.priceMin}"/>
                        </div>
                        <div class="col col-md-2">
                          <input type="number" name="priceMax" placeholder="Max price" class="form-control" value="${param.priceMax}"/>
                        </div>
                        <div class="col col-md-4">
                          <select name="categoryId" class="form-control">
                            <option value="">-- All Categories</option>
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}" <c:if test="${category.id==param.categoryId}">selected</c:if>>${category.name}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                      <div style="text-align: right">
                        <button type="submit" class="btn btn-primary">
                          Search
                        </button>
                      </div>
                    </form>
                    <h3>
                      Products found
                      <span class="badge badge-primary">${productsCount}</span>
                    </h3>
                    <br>
                    <div class="table-responsive table--no-card m-b-30">
                      <table class="table table-borderless table-striped table-earning">
                        <thead>
                          <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Base Price</th>
                            <th>Offered Price</th>
                            <th>Category</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${products}" var="product">
                              <tr class='clickable-row' data-href="editproduct?productId=${product.id}">
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>$${product.basePrice}</td>
                                <td>$${product.sellingPrice-product.sale}</td>
                                <td>${product.category.name}</td>
                              </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                    <div class="flex-c-m flex-w w-full">
                      <c:forEach var="i" begin="1" end="${pageCount}" step="1" varStatus="status">
                          <c:url value="" var="url">
                              <c:param name="page" value="${i}" />
                              <c:param name="name" value="${param.name}" />
                              <c:param name="priceMin" value="${param.priceMin}" />
                              <c:param name="priceMax" value="${param.priceMax}" />
                              <c:param name="categoryId" value="${param.categoryId}" />
                          </c:url>
                          <a class="flex-c-m how-pagination1 trans-04 m-all-7 <c:if test="${param.page==i || (param.page==null && i==1)}">active-pagination1</c:if>"
                             href='${url}'>${i}</a>
                      </c:forEach>
                    </div>
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
    <jsp:include page="../partial/scripts.jsp"/>
  </body>
</html>
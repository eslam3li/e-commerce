<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="partial/head.jsp" flush="true">
        <jsp:param name="pageTitle" value="Promocodes"/>
    </jsp:include>
  </head>
  <body class="animsition">
    <jsp:include page="partial/headermobile.jsp"/>
    <jsp:include page="partial/sidebar.jsp"/>

    <!-- PAGE CONTAINER -->
    <div class="page-container">
      <jsp:include page="partial/headerdesktop.jsp"/>
      <!-- MAIN CONTENT -->
      <div class="main-content">
        <div class="section__content section__content--p30">
          <div class="container-fluid"><div class="row">
              <div class="col-md-12">
                <div class="overview-wrap">
                  <h2 class="title-1">Categories</h2>
                </div>
              </div>
            </div>
            <br>
            <div class="row">
              <div class="col-lg-12">
                <div class="card card-outline">
                  <div class="card-body card-block">

                    <form name="categoryForm" method="post" class="form-horizontal"  enctype="multipart/form-data">  

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Category</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <select name="categoryId" id="select" class="form-control">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}"<c:if test="${selectedCategory.id==category.id}"> selected </c:if>>
                                  ${category.name}
                                </option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Category Name</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="text" id="text-input"  name="categoryName" required class="form-control">
                        </div>
                      </div>   

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="file-input" class=" form-control-label">Default picture</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="file" class="form-control-file"  name="defaultPicture" accept="image/*" required multiple="false">
                        </div>
                      </div>

                      <input type="hidden" name="productItemsJson">

                      <div style="text-align: right">
                        <button type="submit" class="btn btn-primary" onclick="prepareProductItems()">
                          Add Category
                        </button>
                      </div>

                    </form>

                    <br>

                    <div class="table-responsive table--no-card m-b-30">
                      <table class="table table-borderless table-striped table-earning">
                        <thead>
                          <tr>
                            <th>Category Name</th>
                            <th>Parent Category</th>
                            <th>Actions</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:if test="${not(fn:length(categories)>0)}">
                              <tr>
                                <td colspan="3" style="text-align: center">No Categories</td>
                              </tr>
                          </c:if>
                          <c:if test="${fn:length(categories)>0}">
                              <c:forEach items="${categories}" var="category">
                                  <tr class='clickable-row' data-href="showproducts?categoryId=${category.id}">
                                    <td><c:out value="${category.name}"/></td>
                                    <td><c:out value="${category.parentCategory.name}"/></td>                 
                                    <td><a href="?delete=${category.id}">Delete</a></td>
                                  </tr>
                              </c:forEach>
                          </c:if>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>                         
            </div>
          </div>
        </div>
      </div>
      <!-- END MAIN CONTENT -->
    </div>
    <!-- END PAGE CONTAINER -->

    <jsp:include page="partial/scripts.jsp"/>
  </body>
</html>
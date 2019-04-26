<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="partial/head.jsp" flush="true">
        <jsp:param name="pageTitle" value="Order details"/>
    </jsp:include>
  </head>
  <body class="animsition">
    <jsp:include page="partial/headermobile.jsp"/>
    <jsp:include page="partial/sidebar.jsp"/>

    <!-- PAGE CONTAINER-->
    <div class="page-container">
      <jsp:include page="partial/headerdesktop.jsp"/>
      <!-- MAIN CONTENT-->
      <div class="main-content">
        <div class="section__content section__content--p30">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-12">
                <div class="overview-wrap">
                  <h2 class="title-1">Order #${order.id}</h2>
                </div>
              </div>
            </div>
            <br>
            <div class="row">
              <div class="col-lg-12">
                <div class="card card-outline">
                  <div class="card-header">
                    <strong>Details</strong>
                  </div>
                  <div class="card-body card-block">
                    <div class="row form-group">
                      <div class="col col-md-3">
                        <label for="text-input" class=" form-control-label">Order purchaseDate</label>
                      </div>
                      <div class="col-12 col-md-9">
                        <input type="text" value="${order.purchaseDate}" readonly class="form-control">
                      </div>
                    </div>

                    <div class="form-horizontal">
                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">User name</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="text" value="${order.user.name}" readonly class="form-control">
                        </div>
                      </div>

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">User email</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="text" value="${order.user.email}" readonly class="form-control">
                        </div>
                      </div>

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">User phone</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="text" value="${order.user.phone}" readonly class="form-control">
                        </div>
                      </div>

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">User address</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="text" value="${order.user.address}" readonly class="form-control">
                        </div>
                      </div>
                    </div>
                    <form method="post" class="form-horizontal">
                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">State</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <select name="orderState" id="select" class="form-control">
                            <c:forEach items="${states}" var="state">
                                <option value="${state}"<c:if test="${state==order.orderState}"> selected </c:if>>
                                  ${state}
                                </option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                      <div style="text-align: right">
                        <button type="submit" class="btn btn-primary">
                          Change State
                        </button>
                      </div>
                    </form>
                    <h3>
                      Order items
                    </h3>
                    <br>
                    <div class="table-responsive table--no-card m-b-30">
                      <table class="table table-borderless table-striped table-earning card-outline">
                        <thead>
                          <tr>
                            <th>Product</th>
                            <th>Color</th>
                            <th>Quantity</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:if test="${not(fn:length(orderItems)>0)}">
                              <tr>
                                <td colspan="3" style="text-align: center">No order items! What the hell!</td>
                              </tr>
                          </c:if>
                          <c:if test="${fn:length(orderItems)>0}">
                              <c:forEach items="${orderItems}" var="orderItem">
                                  <tr>
                                    <td>${orderItem.productItem.product.name}</td>
                                    <td>${orderItem.productItem.color}</td>
                                    <td>${orderItem.quantity}</td>
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
      <!-- END MAIN CONTENT-->
    </div>
    <!-- END PAGE CONTAINER-->

    <jsp:include page="partial/scripts.jsp"/>
  </body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="partial/head.jsp" flush="true">
        <jsp:param name="pageTitle" value="Orders"/>
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
                  <h2 class="title-1">Orders</h2>
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
                    <form method="get" class="form-horizontal">  
                      <div class="row form-group">
                        <div class="col col-md-4">
                          <input type="text" placeholder="User name pattern" name="username" value="${param.username}" class="form-control"/>
                        </div>
                        <div class="col col-md-4">
                          <input type="text" placeholder="Email pattern" name="email" value="${param.email}" class="form-control"/>
                        </div>
                        <div class="col col-md-4">
                          <select name="orderState" id="select" class="form-control">
                            <option value="">
                              -- All
                            </option>
                            <c:forEach items="${states}" var="state">
                                <option value="${state}"<c:if test="${state==param.orderState}"> selected </c:if>>
                                  ${state}
                                </option>
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
                      Matched orders
                      <span class="badge badge-primary">${ordersCount}</span>
                    </h3>
                    <br>
                    <div class="table-responsive table--no-card m-b-30">
                      <table class="table table-borderless table-striped table-earning card-outline">
                        <thead>
                          <tr>
                            <th>ID</th>
                            <th>User name</th>
                            <th>User email</th>
                            <th>Order state</th>
                            <th>Purchase date</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:if test="${not(fn:length(orders)>0)}">
                              <tr>
                                <td colspan="5" style="text-align: center">No orders yet!</td>
                              </tr>
                          </c:if>
                          <c:if test="${fn:length(orders)>0}">
                              <c:forEach items="${orders}" var="order">
                                  <tr class='clickable-row' data-href="orderdetails?orderId=${order.id}">
                                    <td>${order.id}</td>
                                    <td>${order.user.name}</td>
                                    <td>${order.user.email}</td>
                                    <td>${order.orderState}</td>
                                    <td>${order.purchaseDate}</td>
                                  </tr>
                              </c:forEach>
                          </c:if>
                        </tbody>
                      </table>
                    </div>
                    <div class="flex-c-m flex-w w-full">
                      <c:forEach var="i" begin="1" end="${pageCount}" step="1" varStatus="status">
                          <c:url value="" var="url">
                              <c:param name="page" value="${i}" />
                              <c:param name="username" value="${param.username}" />
                              <c:param name="email" value="${param.email}" />
                              <c:param name="orderState" value="${param.orderState}" />
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

    <jsp:include page="partial/scripts.jsp"/>
  </body>
</html>
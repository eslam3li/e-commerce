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
                  <h2 class="title-1">Promocodes</h2>
                </div>
              </div>
            </div>
            <br>
            <div class="row">
              <div class="col-lg-12">
                <div class="card card-outline">
                  <div class="card-body card-block">
                    <form method="post" class="form-horizontal">  
                      <div class="row form-group">
                        <div class="col col-md-5">
                          <input type="text" name="code" required placeholder="Promocode" class="form-control"/>
                        </div>
                        <div class="col col-md-4">
                          <input type="number" name="discount" required placeholder="Discount" class="form-control"/>
                        </div>
                        <div class="col col-md-3">
                          <button type="submit" class="btn btn-primary form-control">
                            Add
                          </button>
                        </div>
                      </div>
                    </form>

                    <br>

                    <div class="table-responsive table--no-card m-b-30">
                      <table class="table table-borderless table-striped table-earning">
                        <thead>
                          <tr>
                            <th>Promocode</th>
                            <th>Dicount</th>
                            <th>Actions</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:if test="${not(fn:length(promocodes)>0)}">
                              <tr>
                                <td colspan="3" style="text-align: center">No promocodes</td>
                              </tr>
                          </c:if>
                          <c:if test="${fn:length(promocodes)>0}">
                              <c:forEach items="${promocodes}" var="promocode">
                                  <tr>
                                    <td>${promocode.code}</td>
                                    <td>$${promocode.discount}</td>                 
                                    <td><a href="?delete=${promocode.id}">Delete</a></td>
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
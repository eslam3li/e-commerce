<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="partial/head.jsp" flush="true">
        <jsp:param name="pageTitle" value="Admins"/>
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
                  <h2 class="title-1">Admins</h2>
                  <a href="addadmin" class="au-btn au-btn-icon au-btn--blue" style="color: white;">
                    <i class="zmdi zmdi-plus"></i>Add Admin</a>
                </div>
              </div>
            </div>
            <br>
            <div class="row">
              <div class="col-lg-12">
                <table class="table table-borderless table-striped table-earning card-outline">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Name</th>
                      <th>Email</th>
                      <th>Phone</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:if test="${not(fn:length(admins)>0)}">
                        <tr>
                          <td colspan="4" style="text-align: center">No admins found! How on earth did you get here?</td>
                        </tr>
                    </c:if>
                    <c:if test="${fn:length(admins)>0}">
                        <c:forEach items="${admins}" var="admin">
                            <tr>
                              <td>${admin.id}</td>
                              <td>${admin.name}</td>
                              <td>${admin.email}</td>
                              <td>${admin.phone}</td>
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
      <!-- END MAIN CONTENT-->
    </div>
    <!-- END PAGE CONTAINER-->

    <jsp:include page="partial/scripts.jsp"/>
  </body>
</html>
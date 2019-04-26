<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="partial/head.jsp" flush="true">
        <jsp:param name="pageTitle" value="Users"/>
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
                  <h2 class="title-1">Users</h2>
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
                          <input type="text" placeholder="Name pattern" name="name" value="${param.name}" class="form-control"/>
                        </div>
                        <div class="col col-md-4">
                          <input type="text" placeholder="Email pattern" name="email" value="${param.email}" class="form-control"/>
                        </div>
                        <div class="col col-md-4">
                          <input type="text" placeholder="Phone pattern" name="phone" value="${param.phone}" class="form-control"/>
                        </div>
                      </div>
                      <div style="text-align: right">
                        <button type="submit" class="btn btn-primary">
                          Search
                        </button>
                      </div>
                    </form>
                    <h3>
                      Users found
                      <span class="badge badge-primary">${usersCount}</span>
                    </h3>
                    <br>
                    <div class="table-responsive table--no-card m-b-30">
                      <table class="table table-borderless table-striped table-earning">
                        <thead>
                          <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Address</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:if test="${not(fn:length(users)>0)}">
                              <tr>
                                <td colspan="3" style="text-align: center">No users found</td>
                              </tr>
                          </c:if>
                          <c:if test="${fn:length(users)>0}">
                              <c:forEach items="${users}" var="user">
                                  <tr>
                                    <td>${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>${user.email}</td>
                                    <td>${user.phone}</td>
                                    <td>${user.address}</td> 
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
                              <c:param name="name" value="${param.name}" />
                              <c:param name="email" value="${param.email}" />
                              <c:param name="phone" value="${param.phone}" />
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
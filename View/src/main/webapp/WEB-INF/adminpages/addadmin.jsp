<%-- 
    Document   : addadmin2
    Created on : Apr 13, 2019, 8:17:51 PM
    Author     : Saror Mohamed
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="partial/head.jsp" flush="true">
        <jsp:param name="pageTitle" value="AddAdmin"/>
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
              <div class="col-lg-12">
                <div class="card card-outline">
                  <div class="card-header">
                    <strong>Add Admin</strong>
                  </div>

                  <div class="card-body card-block">
                    <form method="post" class="form-horizontal">                                                

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Name</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="text" placeholder="Enter name" name="name" required class="form-control">
                        </div>
                      </div>   

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Email</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="email" placeholder="Enter emial" name="email" required class="form-control">
                        </div>
                      </div> 

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Password</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="password" placeholder="Enter password" name="password" required class="form-control">
                        </div>
                      </div> 

                      <div class="row form-group">
                        <div class="col col-md-3">
                          <label for="text-input" class=" form-control-label">Phone</label>
                        </div>
                        <div class="col-12 col-md-9">
                          <input type="tel" placeholder="Enter phone" name="phone" required class="form-control">
                        </div>
                      </div>

                      <div style="text-align: right">
                        <button type="reset" class="btn btn-danger">
                          Reset
                        </button>
                        <button type="submit" class="btn btn-primary">
                          Add Admin
                        </button>
                      </div>
                      
                    </form>
                  </div>
                </div>
              </div>                         
            </div>
          </div>
        </div>
        <!-- END MAIN CONTENT-->
      </div>
      <!-- END PAGE CONTAINER-->
    </div>
    <jsp:include page="partial/scripts.jsp"/>
  </body>
</html>
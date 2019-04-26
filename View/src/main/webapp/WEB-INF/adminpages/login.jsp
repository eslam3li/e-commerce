<%-- 
    Document   : Login
    Created on : Apr 12, 2019, 12:47:53 PM
    Author     : Saror Mohamed
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="partial/head.jsp" flush="true">
        <jsp:param name="pageTitle" value="Login"/>
    </jsp:include>
  </head>
  <body class="animsition">
    <div class="page-wrapper">
      <div class="page-content--bge5">
        <div class="container">
          <div class="login-wrap">
            <div class="login-content">
              <div class="login-logo">
                <a href="#">
                  <img src="images/icon/logo.png" alt="CoolAdmin">
                </a>
              </div>
              <div class="login-form">
                <form action="" method="post">
                  <div class="form-group">
                    <label>Email Address</label>
                    <input class="au-input au-input--full<c:if test="${param.error}"> is-invalid form-control</c:if>" type="email" name="email" placeholder="Email">
                  </div>
                  <div class="form-group">
                    <label>Password</label>
                    <input class="au-input au-input--full<c:if test="${param.error}"> is-invalid form-control</c:if>" type="password" name="password" placeholder="Password">
                  </div>
                  <button class="au-btn au-btn--block au-btn--green m-b-20" type="submit">sign in</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <jsp:include page="partial/scripts.jsp"/>
  </body>
</html>
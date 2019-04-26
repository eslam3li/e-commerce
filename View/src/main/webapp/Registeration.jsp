<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Contact</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->	
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"  crossorigin="anonymous">

    <link rel="icon" type="image/png" href="images/icons/favicon.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->	
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">

    <!--===============================================================================================-->

    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->	




    <!--=============================================================================================-->
  </head>
  <body class="animsition">
    <jsp:include page="commonPages/header.html"/>
    <jsp:include page="commonPages/cart.html"/>
    <section class="container" style="background-image: url('images/slide-03.jpg');
             background-size: cover;background-repeat: no-repeat; width: 100%;
             margin-top: 2%;height: 80%" >
      <section class="col-md-5"  >
        <br>
        <section class="card-header">
          <h3>Sign Up</h3>
        </section>
        <section class="card-body">
          <form id="registerForm" action="/user/register" method="post">
            <section class="input-group form-group">
              <input type="text" class="form-control" placeholder="Your Name"  name="fullName" required>
            </section>
            <section class="input-group form-group">
              <input type="email" class="form-control" placeholder="Email"  name="email" required>
            </section>
            <section class="input-group form-group">
              <input type="text" class="form-control" placeholder="username" name="userName" required>
              <c:if test="${pageContext.request.method=='POST'}">
                <jsp:setProperty name="userBean" property="*"/>
                <fmt:parseDate pattern="dd/MM/yyyy" value="${param.birthdate}" var="userBean.birthday" />
                <m:register userBean="${userBean}"/>
                <c:if test="${not empty error}">
                  <label style="color:red">User name already exists</label>
                </c:if>
                <c:if test="${empty error}">
                  <c:redirect url="login.jsp"/>
                </c:if>
              </c:if>
            </section>
            <section class="input-group form-group">
              <input type="password" class="form-control" id="password" placeholder="password" name="password" required>
            </section>
            <section class="input-group form-group">
              <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm password" required>
            </section>
            <section class="input-group form-group">
              <input type="text" class="form-control" placeholder="Address"  name="address" required>
            </section>
            <section class="input-group form-group">
              <input type="date" class="form-control" placeholder="BirthDay"  name="birthdate" required>
            </section>
            <section class="input-group form-group">
              <input type="text" class="form-control" placeholder="phone"  name="phone" required>
            </section>
            <section class="form-group">
              <input type="submit" value="Submit" class="btn float-right" >
            </section>
          </form>
        </section>
      </section>
    </section>
  </div>
  <!--===============================================================================================-->	
  <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
  <!--===============================================================================================-->
  <script src="vendor/animsition/js/animsition.min.js"></script>
  <!--===============================================================================================-->
  <script src="vendor/bootstrap/js/popper.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
  <!--===============================================================================================-->
  <script src="vendor/select2/select2.min.js"></script>
  <script>
      $(".js-select2").each(function () {
          $(this).select2({
              minimumResultsForSearch: 20,
              dropdownParent: $(this).next('.dropDownSelect2')
          });
      })
  </script>
  <!--===============================================================================================-->
  <script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
  <!--===============================================================================================-->
  <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
  <script>
      $('.js-pscroll').each(function () {
          $(this).css('position', 'relative');
          $(this).css('overflow', 'hidden');
          var ps = new PerfectScrollbar(this, {
              wheelSpeed: 1,
              scrollingThreshold: 1000,
              wheelPropagation: false,
          });

          $(window).on('resize', function () {
              ps.update();
          })
      });
  </script>
  <!--===============================================================================================-->
  <script src="js/main.js"></script>
  <script src="js/validate_register.js"></script>
</body>
</html>
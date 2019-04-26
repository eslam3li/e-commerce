<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Login</title>	
    <jsp:include page="commonPages/head.html"></jsp:include>
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="css/util_login.css">
        <link rel="stylesheet" type="text/css" href="css/main_login.css">
    </head>
    <body style="background-color: #666666;">

      <!-- Header -->
      <header class="header-v4">
        <!-- Header desktop -->
        <div class="container-menu-desktop">
        <jsp:include page="commonPages/topbar.html"></jsp:include>
        </div>

      <jsp:include page="commonPages/header.html"></jsp:include>
      </header>

      <div class="limiter">
        <div class="container-login100">
          <div class="wrap-login100">
            <form class="login100-form validate-form" action="login" method="post">
              <span class="login100-form-title p-b-43">
                Login to continue
              </span>


              <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                <input class="input100" type="text" name="email">
                <span class="focus-input100"></span>
                <span class="label-input100">Email</span>
              </div>


              <div class="wrap-input100 validate-input" data-validate="Password is required">
                <input class="input100" type="password" name="pass">
                <span class="focus-input100"></span>
                <span class="label-input100">Password</span>
              </div>

              <div class="flex-sb-m w-full p-t-3 p-b-32">
                <div class="contact100-form-checkbox">
                  <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                  <label class="label-checkbox100" for="ckb1">
                    Remember me
                  </label>
                </div>

                <div>
                  <a href="#" class="txt1">
                    Forgot Password?
                  </a>
                </div>
              </div>


              <div class="container-login100-form-btn">
                <button class="login100-form-btn">
                  Login
                </button>
              </div>

              <div class="text-center p-t-46 p-b-20">
                <span class="txt2">
                  Don`t Have Account yet? 
                </span>
              </div>
                  
				<div class="text-center">
					<a href="registration.jsp" class="txt2 hov1">
						Sign Up
					</a>
				</div>

            
            </form>

            <div class="login100-more" style="background-image: url('images/bg-01.jpg');">
            </div>
          </div>
        </div>
      </div>


    


        <!-- Back to top -->
        <div class="btn-back-to-top" id="myBtn">
          <span class="symbol-btn-back-to-top">
            <i class="zmdi zmdi-chevron-up"></i>
          </span>
        </div>



    <jsp:include page="commonPages/scripts.jsp"></jsp:include>
    <script src="js/login.js"></script>
    <script>
        $(function () {
            var input = $(".input100");
            for (var i = 0; i < input.length; i++) {
                if ($(input[i]).val().trim() !== "") {
                    $(input[i]).addClass('has-val');
                } else {
                    $(input[i]).removeClass('has-val');
                }
            }
        });
    </script>
  </body>
</html>
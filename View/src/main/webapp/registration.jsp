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

                <div class="wrap-login100">
                    <form class="login100-form validate-form" action="register" method="post">
                        <span class="login100-form-title p-b-43">
                            Register Now
                        </span>

                        <div class="wrap-input100 validate-input" data-validate = "Valid full Name is required: xxx xxxx">
                            <input class="input100" type="text" name="fullName">
                            <span class="focus-input100"></span>
                            <span class="label-input100">Full Name</span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Valid user Name is required">
                            <input class="input100" type="text" name="userName">
                            <span class="focus-input100"></span>
                            <span class="label-input100">User Name</span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                            <input class="input100" type="text" name="email">
                            <span class="focus-input100"></span>
                            <span class="label-input100">Email</span>
                        </div>


                        <div class="wrap-input100 validate-input" data-validate="Password is required">
                            <input class="input100" type="password" name="password" id="confirmPassword" onblur="checkpass()">
                            <span class="focus-input100"></span>
                            <span class="label-input100">Password</span>
                            <span id='passtext'></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Must Match Password">
                            <input class="input100" type="password" name="confirmPassword" id="confirmPassword">
                            <span class="focus-input100"></span>
                            <span class="label-input100"> Confirm Password</span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Valid phone is required: 555-555-5555-5">
                            <input class="input100" type="number" name="phone">
                            <span class="focus-input100"></span>
                            <span class="label-input100">phone</span>
                        </div>


                        <br>

                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn">
                                Submit
                            </button>
                        </div>


                    </form>

                    <div class="login100-more" style="background-image: url('images/bg-01.jpg');">
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
         <script src="js/validate_register.js"></script>
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
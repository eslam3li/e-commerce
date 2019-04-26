<!DOCTYPE html>
<html lang="en">

<head>
    <title>Profile</title>
    <jsp:include page="commonPages/head.html"></jsp:include>
</head>

<body class="animsition">

    <!-- Header -->
    <header>
        <!-- Header desktop -->
        <div class="container-menu-desktop">
            <jsp:include page="commonPages/topbar.html"></jsp:include>
        </div>

        <jsp:include page="commonPages/header.html"></jsp:include>
    </header>

    <jsp:include page="commonPages/cart.html"></jsp:include>


    <section class="container" style="background-image: url('images/slide-04.jpg');background-size: cover;
    background-repeat: no-repeat; width: 80%;margin-top: 6%;height: 65%">
        <div class="row">
            <div class="col-3">
                <br>
                <img src="images/product-01.jpg" class="rounded-circle" alt="User Photo" width="210" height="260">
            </div>
            <center>
                <div>
                    <br>
                    <h3 class="typo-main-heading mb-lg-4 mb-3 pr-3 pb-1" id="userName">Mai Mostafa</h3>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Age</label>
                        <div class="col-sm-10 px-4">
                            <input type="number" value="24" name="age" class="form-control" id="age" width="100%"
                                readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Address</label>
                        <div class="col-sm-10 px-4">
                            <input type="text" value="October" name="address" class="form-control" id="address"
                                width="100%" readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">phone</label>
                        <div class="col-sm-10 px-4">
                            <input type="text" value="01146759130" name="phone" class="form-control" id="job"
                                width="100%" readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10 px-4">
                            <input type="text" value="mai@gmail.com" name="email" class="form-control" id="email"
                                width="100%" readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Credit</label>
                        <div class="col-sm-10 px-4">
                            <input type="number" value="5000" name="credit" class="form-control" id="credit"
                                width="100%" readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button id="edit" class="btn btn-primary">Recharge Credit</button>
                        </div>
                    </div>
                </div>
            </center>
        </div>
    </section>


    <jsp:include page="commonPages/footer.html"></jsp:include>


    <!-- Back to top -->
    <div class="btn-back-to-top" id="myBtn">
        <span class="symbol-btn-back-to-top">
            <i class="zmdi zmdi-chevron-up"></i>
        </span>
    </div>

    <jsp:include page="commonPages/scripts.jsp"></jsp:include>

</body>

</html>
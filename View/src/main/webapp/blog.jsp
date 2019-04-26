<%-- 
    Document   : blog.jsp
    Created on : Apr 21, 2019, 1:29:10 AM
    Author     : maimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Blog</title>
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
                 <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/bg-02.jpg');">
                  <h2 class="ltext-105 cl0 txt-center">
                    Blog
                  </h2>
            </section>	 <!-- Content page -->
            <section class="bg0 p-t-62 p-b-60">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8 col-lg-9 p-b-80">
                            <div class="p-r-45 p-r-0-lg">
                                <!-- item blog -->
                                <div class="p-b-63">
                                    <a href="blog-detail.html" class="hov-img0 how-pos5-parent">
                                        <img src="images/blog-04.jpg" alt="IMG-BLOG"/>

                                        <div class="flex-col-c-m size-123 bg9 how-pos5">
                                            <span class="ltext-107 cl2 txt-center">
                                                22
                                            </span>

                                            <span class="stext-109 cl3 txt-center">
                                                Jan 2018
                                            </span>
                                        </div>
                                    </a>

                                    <div class="p-t-32">
                                        <h4 class="p-b-15">
                                            <a href="blog-detail.html" class="ltext-108 cl2 hov-cl1 trans-04">
                                                8 Inspiring Ways to Wear Dresses in the Winter
                                            </a>
                                        </h4>

                                        <p class="stext-117 cl6">
                                            Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce eget dictum tortor. Donec dictum vitae sapien eu varius
                                        </p>

                                    </div>
                                </div>

                                <!-- item blog -->
                                <div class="p-b-63">
                                    <a href="blog-detail.html" class="hov-img0 how-pos5-parent">
                                        <img src="images/blog-05.jpg" alt="IMG-BLOG"/>

                                        <div class="flex-col-c-m size-123 bg9 how-pos5">
                                            <span class="ltext-107 cl2 txt-center">
                                                18
                                            </span>

                                            <span class="stext-109 cl3 txt-center">
                                                Jan 2018
                                            </span>
                                        </div>
                                    </a>
                                    <div class="p-t-32">
                                        <h4 class="p-b-15">
                                            <a href="blog-detail.html" class="ltext-108 cl2 hov-cl1 trans-04">
                                                The Great Big List of Men’s Gifts for the Holidays 
                                            </a>
                                        </h4>

                                        <p class="stext-117 cl6">
                                            Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce eget dictum tortor. Donec dictum vitae sapien eu varius
                                        </p>
                                    </div>
                                </div>

                                <!-- item blog -->
                                <div class="p-b-63">
                                    <a href="blog-detail.html" class="hov-img0 how-pos5-parent">
                                        <img src="images/blog-06.jpg" alt="IMG-BLOG"/>

                                        <div class="flex-col-c-m size-123 bg9 how-pos5">
                                            <span class="ltext-107 cl2 txt-center">
                                                16
                                            </span>

                                            <span class="stext-109 cl3 txt-center">
                                                Jan 2018
                                            </span>
                                        </div>
                                    </a>

                                    <div class="p-t-32">
                                        <h4 class="p-b-15">
                                            <a href="blog-detail.html" class="ltext-108 cl2 hov-cl1 trans-04">
                                                5 Winter-to-Spring Fashion Trends to Try Now
                                            </a>
                                        </h4>

                                        <p class="stext-117 cl6">
                                            Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce eget dictum tortor. Donec dictum vitae sapien eu varius
                                        </p>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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

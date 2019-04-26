<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/slick-custom.js"></script>
<script src="js/main.js"></script>
<c:if test="${not empty loggedIn}">
    <script>
        cartInit();
        var cartItems = ${cartItems};
        for(var i = 0 ; i < cartItems.length ; i++) {
            cartItems[i].product = cartItems[i].productItemBean.product;
        }
        addCartItems(cartItems);
    </script>
    <c:remove var="loggedIn"/>
    <c:remove var="cartItems"/>
</c:if>
<c:if test="${not empty loggedOut}">
    <script>
        cartInit();
    </script>
    <c:remove var="loggedOut"/>
</c:if>
<c:if test="${not empty thankYou}">
    <script>
        swal("Thanks", "Thank you for shopping with us", "success");
        cartInit();
    </script>
    <c:remove var="thankYou"/>
</c:if>
<c:if test="${not empty checkoutError}">
    <script>
        swal("Checkout error", "${checkoutError}", "error");
    </script>
    <c:remove var="checkoutError"/>
</c:if>
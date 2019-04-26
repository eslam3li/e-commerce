<!-- HEADER DESKTOP-->
<header class="header-desktop">
  <div class="section__content section__content--p30">
    <div class="container-fluid">
      <div class="header-wrap">
        <div></div>
        <div class="header-button">
          <div></div>
          <div class="account-wrap">
            <div class="account-item clearfix js-item-menu">
              <div class="image">
                <div></div>
              </div>
              <div class="content">
                <a class="js-acc-btn" href="#">${sessionScope.adminBean.name}</a>
              </div>
              <div class="account-dropdown js-dropdown">
                <div class="info clearfix">
                  <div class="content" style="margin-left:0px;">
                    <h5 class="name">
                      <a href="#">${sessionScope.adminBean.name}</a>
                    </h5>
                    <span class="email">${sessionScope.adminBean.email}</span>
                  </div>
                </div>
                <div class="account-dropdown__footer">
                  <a href="login?logout">
                    <i class="zmdi zmdi-power"></i>Logout</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</header>
<!-- HEADER DESKTOP-->
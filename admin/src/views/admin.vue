<template>
  <div>
    <body id="page-top">
      <!-- Page Wrapper -->
      <div id="wrapper">
        <!-- Sidebar -->
        <ul
          class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
          id="accordionSidebar"
        >
          <!-- Sidebar - Brand -->
          <a
            class="sidebar-brand d-flex align-items-center justify-content-center"
            href="index.html"
          >
            <div class="sidebar-brand-icon rotate-n-15">
              <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">造梦家纺</div>
          </a>

          <!-- Divider -->
          <hr class="sidebar-divider my-0" />

          <!-- Nav Item - Dashboard -->
          <li class="nav-item active">
            <router-link to="/admin/welcome" class="nav-link">
              <i class="fas fa-fw fa-tachometer-alt"></i>
              <span>欢迎</span>
            </router-link>
          </li>

          <!-- Divider -->
          <hr class="sidebar-divider" />

          <!-- Nav Item - Pages Collapse Menu -->
          <li class="nav-item">
            <a
              class="nav-link collapsed"
              href="#"
              data-toggle="collapse"
              data-target="#collapseTwo"
              aria-expanded="true"
              aria-controls="collapseTwo"
            >
              <i class="fas fa-fw fa-cog"></i>
              <span>系统管理</span>
            </a>
            <div
              id="collapseTwo"
              class="collapse"
              aria-labelledby="headingTwo"
              data-parent="#accordionSidebar"
            >
              <div class="bg-white py-2 collapse-inner rounded">
                <router-link
                  to="/admin/user"
                  class="collapse-item"
                  active-class="collapse-item active"
                  >用户管理</router-link
                >
                <router-link
                  to="/admin/category"
                  class="collapse-item"
                  active-class="collapse-item active"
                  >分类管理</router-link
                >
                <router-link
                  to="/admin/goods"
                  class="collapse-item"
                  active-class="collapse-item active"
                  >商品管理</router-link
                >
              </div>
            </div>
          </li>

          <!-- Divider -->
          <hr class="sidebar-divider" />

          <!-- Heading -->
          <div class="sidebar-heading">
            菜单
          </div>

          <!-- Nav Item - Pages Collapse Menu -->
          <li class="nav-item">
            <a
              class="nav-link collapsed"
              href="#"
              data-toggle="collapse"
              data-target="#collapsePages"
              aria-expanded="true"
              aria-controls="collapsePages"
            >
              <i class="fas fa-fw fa-folder"></i>
              <span>业务管理</span>
            </a>
            <div
              id="collapsePages"
              class="collapse"
              aria-labelledby="headingPages"
              data-parent="#accordionSidebar"
            >
              <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">会员:</h6>
                <router-link
                  to="/admin/customer"
                  class="collapse-item"
                  active-class="collapse-item active"
                  >会员管理</router-link
                >
                <h6 class="collapse-header">订单:</h6>
                <router-link
                  to="/admin/addOrder"
                  class="collapse-item"
                  active-class="collapse-item active"
                  >订单录入</router-link
                >
                <router-link
                  to="/admin/order"
                  class="collapse-item"
                  active-class="collapse-item active"
                  >订单管理</router-link
                >
              </div>
            </div>
          </li>
          <li class="nav-item">
            <a
              class="nav-link collapsed"
              href="#"
              data-toggle="collapse"
              data-target="#collapsePages"
              aria-expanded="true"
              aria-controls="collapsePages"
            >
              <i class="fas fa-fw fa-folder"></i>
              <span>账单管理</span>
            </a>
            <div
              id="collapsePages"
              class="collapse"
              aria-labelledby="headingPages"
              data-parent="#accordionSidebar"
            >
              <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">药品管理:</h6>
                <router-link
                  to="/admin/drugsBill"
                  class="collapse-item"
                  active-class="collapse-item active"
                  >账单录入</router-link
                >
              </div>
            </div>
          </li>
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
          <!-- Main Content -->
          <div id="content">
            <!-- Topbar -->
            <nav
              class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow"
            >
              <!-- Sidebar Toggle (Topbar) -->
              <button
                id="sidebarToggleTop"
                class="btn btn-link d-md-none rounded-circle mr-3"
              >
                <i class="fa fa-bars"></i>
              </button>

              <!-- Topbar Navbar -->
              <ul class="navbar-nav ml-auto">
                <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                <li class="nav-item dropdown no-arrow d-sm-none">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="searchDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    <i class="fas fa-search fa-fw"></i>
                  </a>
                  <!-- Dropdown - Messages -->
                  <div
                    class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                    aria-labelledby="searchDropdown"
                  >
                    <form class="form-inline mr-auto w-100 navbar-search">
                      <div class="input-group">
                        <input
                          type="text"
                          class="form-control bg-light border-0 small"
                          placeholder="Search for..."
                          aria-label="Search"
                          aria-describedby="basic-addon2"
                        />
                        <div class="input-group-append">
                          <button class="btn btn-primary" type="button">
                            <i class="fas fa-search fa-sm"></i>
                          </button>
                        </div>
                      </div>
                    </form>
                  </div>
                </li>

                <!-- Nav Item - Alerts -->
                <li class="nav-item dropdown no-arrow mx-1">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="alertsDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    <i class="fas fa-bell fa-fw"></i>
                    <!-- Counter - Alerts -->
                    <span class="badge badge-danger badge-counter">3+</span>
                  </a>
                  <!-- Dropdown - Alerts -->
                  <div
                    class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                    aria-labelledby="alertsDropdown"
                  >
                    <h6 class="dropdown-header">
                      Alerts Center
                    </h6>
                    <a class="dropdown-item d-flex align-items-center" href="#">
                      <div class="mr-3">
                        <div class="icon-circle bg-primary">
                          <i class="fas fa-file-alt text-white"></i>
                        </div>
                      </div>
                      <div>
                        <div class="small text-gray-500">December 12, 2019</div>
                        <span class="font-weight-bold"
                          >A new monthly report is ready to download!</span
                        >
                      </div>
                    </a>
                    <a class="dropdown-item d-flex align-items-center" href="#">
                      <div class="mr-3">
                        <div class="icon-circle bg-success">
                          <i class="fas fa-donate text-white"></i>
                        </div>
                      </div>
                      <div>
                        <div class="small text-gray-500">December 7, 2019</div>
                        $290.29 has been deposited into your account!
                      </div>
                    </a>
                    <a class="dropdown-item d-flex align-items-center" href="#">
                      <div class="mr-3">
                        <div class="icon-circle bg-warning">
                          <i class="fas fa-exclamation-triangle text-white"></i>
                        </div>
                      </div>
                      <div>
                        <div class="small text-gray-500">December 2, 2019</div>
                        Spending Alert: We've noticed unusually high spending
                        for your account.
                      </div>
                    </a>
                    <a
                      class="dropdown-item text-center small text-gray-500"
                      href="#"
                      >Show All Alerts</a
                    >
                  </div>
                </li>

                <!-- Nav Item - Messages -->
                <li class="nav-item dropdown no-arrow mx-1">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="messagesDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    <i class="fas fa-envelope fa-fw"></i>
                    <!-- Counter - Messages -->
                    <span class="badge badge-danger badge-counter">7</span>
                  </a>
                  <!-- Dropdown - Messages -->
                  <div
                    class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                    aria-labelledby="messagesDropdown"
                  >
                    <h6 class="dropdown-header">
                      Message Center
                    </h6>
                    <a class="dropdown-item d-flex align-items-center" href="#">
                      <div class="dropdown-list-image mr-3">
                        <img
                          class="rounded-circle"
                          src="../../public/static/img/undraw_profile_1.svg"
                          alt="..."
                        />
                        <div class="status-indicator bg-success"></div>
                      </div>
                      <div class="font-weight-bold">
                        <div class="text-truncate">
                          Hi there! I am wondering if you can help me with a
                          problem I've been having.
                        </div>
                        <div class="small text-gray-500">
                          Emily Fowler · 58m
                        </div>
                      </div>
                    </a>
                    <a class="dropdown-item d-flex align-items-center" href="#">
                      <div class="dropdown-list-image mr-3">
                        <img
                          class="rounded-circle"
                          src="../../public/static/img/undraw_profile_2.svg"
                          alt="..."
                        />
                        <div class="status-indicator"></div>
                      </div>
                      <div>
                        <div class="text-truncate">
                          I have the photos that you ordered last month, how
                          would you like them sent to you?
                        </div>
                        <div class="small text-gray-500">Jae Chun · 1d</div>
                      </div>
                    </a>
                    <a class="dropdown-item d-flex align-items-center" href="#">
                      <div class="dropdown-list-image mr-3">
                        <img
                          class="rounded-circle"
                          src="../../public/static/img/undraw_profile_3.svg"
                          alt="..."
                        />
                        <div class="status-indicator bg-warning"></div>
                      </div>
                      <div>
                        <div class="text-truncate">
                          Last month's report looks great, I am very happy with
                          the progress so far, keep up the good work!
                        </div>
                        <div class="small text-gray-500">
                          Morgan Alvarez · 2d
                        </div>
                      </div>
                    </a>
                    <a class="dropdown-item d-flex align-items-center" href="#">
                      <div class="dropdown-list-image mr-3">
                        <img
                          class="rounded-circle"
                          src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                          alt="..."
                        />
                        <div class="status-indicator bg-success"></div>
                      </div>
                      <div>
                        <div class="text-truncate">
                          Am I a good boy? The reason I ask is because someone
                          told me that people say this to all dogs, even if they
                          aren't good...
                        </div>
                        <div class="small text-gray-500">
                          Chicken the Dog · 2w
                        </div>
                      </div>
                    </a>
                    <a
                      class="dropdown-item text-center small text-gray-500"
                      href="#"
                      >Read More Messages</a
                    >
                  </div>
                </li>

                <div class="topbar-divider d-none d-sm-block"></div>

                <!-- Nav Item - User Information -->
                <li class="nav-item dropdown no-arrow">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="userDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">{{
                      loginUser.name
                    }}</span>
                    <img
                      class="img-profile rounded-circle"
                      src="../../public/static/img/undraw_profile.svg"
                    />
                  </a>
                  <!-- Dropdown - User Information -->
                  <div
                    class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                    aria-labelledby="userDropdown"
                  >
                    <a class="dropdown-item" href="#">
                      <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                      Profile
                    </a>
                    <a class="dropdown-item" href="#">
                      <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                      Settings
                    </a>
                    <a class="dropdown-item" href="#">
                      <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                      Activity Log
                    </a>
                    <div class="dropdown-divider"></div>
                    <a
                      class="dropdown-item"
                      href="#"
                      @click="deleteDialogVisible = true"
                    >
                      <i
                        class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"
                      ></i>
                      登出账号
                    </a>
                  </div>
                </li>
              </ul>
            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">
              <router-view />
            </div>
            <!-- /.container-fluid -->
          </div>
          <!-- End of Main Content -->

          <!-- Footer -->
          <footer class="sticky-footer bg-white">
            <div class="container my-auto">
              <div class="copyright text-center my-auto">
                <span>Copyright &copy; Your Website 2021</span>
              </div>
            </div>
          </footer>
          <!-- End of Footer -->
        </div>
        <!-- End of Content Wrapper -->
      </div>
      <!-- End of Page Wrapper -->

      <!-- Scroll to Top Button-->
      <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
      </a>

      <!-- Logout Modal-->
      <el-dialog
        title="退出登录"
        :visible.sync="deleteDialogVisible"
        width="15%"
        center
      >
        <span slot="footer" class="dialog-footer">
          <el-button @click="deleteDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="logout()">确 定</el-button>
        </span>
      </el-dialog>
    </body>
  </div>
</template>

<script>
export default {
  name: 'admin',
  data: function() {
    return {
      loginUser: {},
      deleteDialogVisible: false
    }
  },
  mounted: function() {
    let _this = this
    _this.loginUser = Tool.getLoginUser()
  },

  methods: {
    logout() {
      let _this = this
      _this.$ajax
        .get(
          process.env.VUE_APP_SERVER +
            '/system/user/logout/' +
            _this.loginUser.token
        )
        .then(response => {
          let resp = response.data
          if (resp.success) {
            Tool.setLoginUser(null)
            _this.$router.push('/login')
          } else {
            Toast.warning(resp.message, _this)
          }
        })
    }
  }
}
</script>

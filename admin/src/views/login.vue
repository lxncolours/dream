<template>
  <body class="bg-lxn-gradient-primary">
    <div class="container" >
      <!-- Outer Row -->
      <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
          <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
              <!-- Nested Row within Card Body -->
              <div class="row">
                <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                <div class="col-lg-6">
                  <div class="p-5">
                    <div class="text-center">
                      <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                    </div>
                    <form class="user">
                      <div class="form-group">
                        <input
                          v-model="user.loginName"
                          type="text"
                          class="form-control form-control-user"
                          placeholder="请输入用户名"
                        />
                      </div>
                      <div class="form-group">
                        <input
                          v-model="user.password"
                          type="password"
                          class="form-control form-control-user"
                          id="exampleInputPassword"
                          placeholder="请输入密码"
                        />
                      </div>
                      <el-checkbox v-model="remember" size="mini"
                        >记住我</el-checkbox
                      >
                      <el-button
                        type="primary"
                        class="btn btn-primary btn-user btn-block"
                        @click="login()"
                        >登录</el-button
                      >
                      <hr />
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
export default {
  name: 'login',
  data: function() {
    return {
      user: {},
      remember: false // 默认不勾选记住我
    }
  },
  mounted: function() {
    let _this = this
    // 判断缓存是否还生效,如果生效直接跳转admin
    let loginUser = Tool.getLoginUser()
    if (Tool.isNotEmpty(loginUser)) {
      _this.$router.push('/admin/welcome')
    }
    // 从缓存中获取记住的用户名密码，如果获取不到，说明上一次没有勾选“记住我”
    let rememberUser = LocalStorage.get(LOCAL_KEY_REMEMBER_USER)
    if (rememberUser) {
      _this.user = rememberUser
      // 如果上次勾选了记住密码,那么本次应该给记住密码打勾
      _this.remember = true
    }
  },
  methods: {
    login() {
      let _this = this
      // 如果密码是从缓存带出来的，则不需要重新加密
      let md5 = hex_md5(_this.user.password)
      console.log('md5:' + md5)
      let rememberUser = LocalStorage.get(LOCAL_KEY_REMEMBER_USER) || {}
      console.log('rememberUser:' + rememberUser.md5)
      if (md5 !== rememberUser.md5) {
        _this.user.password = hex_md5(_this.user.password + KEY)
      }
      console.log(_this.user.password)

      _this.$ajax
        .post(process.env.VUE_APP_SERVER + '/system/user/login', _this.user)
        .then(response => {
          console.log(response)
          let resp = response.data
          if (resp.success) {
            console.log('登录成功：', resp.content)
            let loginUser = resp.content
            Tool.setLoginUser(loginUser)
            console.log('loginName' + loginUser.loginName)
            // 判断“记住我”
            if (_this.remember) {
              // 如果勾选记住我，则将用户名密码保存到本地缓存
              // 原：这里需要保存密码明文，否则登录时又会再加一层密
              // 新：这里保存密码密文，并保存密文md5，用于检测密码是否被重新输入过
              let md5 = hex_md5(_this.user.password)
              LocalStorage.set(LOCAL_KEY_REMEMBER_USER, {
                loginName: loginUser.loginName,
                // password: _this.user.passwordShow,
                password: _this.user.password,
                md5: md5
              })
            } else {
              // 没有勾选“记住我”时，要把本地缓存清空，否则按照mounted的逻辑，下次打开时会自动显示用户名密码
              LocalStorage.set(LOCAL_KEY_REMEMBER_USER, null)
            }
            _this.$router.push('/admin/welcome')
          } else {
            Toast.warning(resp.message, _this)
            _this.user.password = ''
          }
        })
    }
  }
}
</script>
<style>
.bg-lxn-gradient-primary {
  background: url('https://dream-home.oss-cn-beijing.aliyuncs.com/course/login.jpg')
    no-repeat center center;
  background-size: cover;
  background-attachment: fixed;
  /* 设置背景颜色，背景图加载过程中会显示背景色 我试了下可加可不加 */
  background-color: #cccccc;
}
html,
body,
#app {
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>

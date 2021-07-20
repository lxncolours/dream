<template>
  <div>
    <el-page-header @back="goBack" content="用户管理"> </el-page-header>
    <el-divider></el-divider>
    <el-row :gutter="20">
      <el-col :span="2">
        <el-input placeholder="请输入用户账号" v-model="queryUser.loginName">
        </el-input>
      </el-col>
      <el-col :span="2">
        <el-input placeholder="请输入用户名称" v-model="queryUser.name">
        </el-input>
      </el-col>
      <el-col :span="1" :offset="16"
        ><el-button type="info" icon="el-icon-search" @click.native="list()"
          >搜索</el-button
        ></el-col
      >
      <el-col :span="1"
        ><el-button
          type="primary"
          icon="el-icon-circle-plus"
          @click="handleAdd()"
          >新增</el-button
        ></el-col
      >
    </el-row>
    <el-table
      :data="users"
      height="580"
      border
      style="width: 100%"
      v-loading="loading"
    >
      <el-table-column prop="loginName" label="用户账号" width="450">
      </el-table-column>
      <el-table-column prop="name" label="用户名称" width="450">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="450">
      </el-table-column>
      <el-table-column prop="updateTime" label="修改时间" width="450">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-popconfirm
            title="确定删除该用户吗？"
            @confirm="handleDelete(scope.$index, scope.row)"
          >
            <el-button type="danger" size="mini" slot="reference"
              >删除</el-button
            >
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div class="block" style="float:right">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageform.page"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageform.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageform.total"
      />
    </div>
    <el-drawer
      :title="dialogTitle"
      :before-close="handleClose"
      :visible.sync="dialog"
      direction="rtl"
      :destroy-on-close="true"
      :wrapperClosable="false"
      :show-close="false"
      custom-class="demo-drawer"
      ref="drawer"
    >
      <div class="demo-drawer__content">
        <el-form
          :model="userForm"
          :rules="rules"
          ref="userForm"
          :hide-required-asterisk="true"
        >
          <el-form-item
            label="用户账号"
            :label-width="formLabelWidth"
            prop="loginName"
          >
            <el-input
              :disabled="showLoginName"
              v-model="userForm.loginName"
              autocomplete="off"
            ></el-input>
          </el-form-item>

          <el-form-item
            label="用户密码"
            :label-width="formLabelWidth"
            prop="password"
          >
            <el-input
              type="password"
              v-model="userForm.password"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="用户名称"
            prop="name"
            :label-width="formLabelWidth"
          >
            <el-input v-model="userForm.name" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button @click="cancelForm">取 消</el-button>
          <el-button
            type="primary"
            @click="submitForm('userForm')"
            :loading="loading"
            >{{ loading ? '提交中 ...' : '确 定' }}</el-button
          >
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
export default {
  name: 'system-user',
  data() {
    return {
      // 默认第一页,10条
      pageform: {},
      users: [],
      user: {},
      queryUser: {},
      loading: false,
      dialog: false,
      showLoginName: false,
      rules: {
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        loginName: [
          { required: true, message: '请输入用户账号', trigger: 'blur' }
        ],
        name: [{ required: true, message: '请输入用户名称', trigger: 'blur' }]
      },
      // 新增和编辑弹框标题
      dialogTitle: '',
      userForm: {
        userId: '',
        name: '',
        loginName: '',
        password: ''
      },
      formLabelWidth: '80px',
      timer: null
    }
  },
  mounted: function() {
    let _this = this
    // 需要将导航修改为激活状态collapse-item active
    _this.pageform.size = 10
    _this.list()
  },
  methods: {
    handleEdit(index, row) {
      let _this = this
      console.log(index, row)
      _this.dialog = true
      _this.dialogTitle = '编辑用户'
      _this.showLoginName = true
      _this.userForm.userId = row.userId
      _this.userForm.password = row.password
      _this.userForm.loginName = row.loginName
      _this.userForm.name = row.name
    },
    handleAdd() {
      let _this = this
      _this.dialog = true
      _this.dialogTitle = '新增用户'
      _this.showLoginName = false
    },
    handleDelete(index, row) {
      let _this = this
      console.log(index, row)
      _this.delete(row.userId)
    },
    handleSizeChange(val) {
      let _this = this
      console.log(`每页 ${val} 条`)
      _this.pageform.size = val
      _this.list()
    },
    handleCurrentChange(val) {
      let _this = this
      console.log(`当前页: ${val}`)
      _this.pageform.page = val
      _this.list()
    },
    render(page, total) {
      let _this = this
      _this.pageform.page = page
      _this.pageform.total = total
    },
    /**
     * 列表查询
     */
    list() {
      let _this = this
      _this.loading = true
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + '/system/user/list', {
          page: _this.pageform.page,
          size: _this.pageform.size,
          loginName: _this.queryUser.loginName,
          name: _this.queryUser.name
        })
        .then(response => {
          _this.loading = false
          let resp = response.data
          if (resp.success) {
            _this.users = resp.content.list
            _this.render(resp.content.page, resp.content.total)
          }
        })
    },
    goBack() {
      let _this = this
      console.log('go back')
      _this.$router.push('welcome')
    },
    delete(id) {
      let _this = this
      _this.loading = true
      _this.$ajax
        .delete(process.env.VUE_APP_SERVER + '/system/user/delete/' + id)
        .then(response => {
          _this.loading = false
          let resp = response.data
          if (resp.success) {
            Toast.success('删除用户成功!', _this)
            this.list(_this.pageform.page)
          } else {
            Toast.warning(resp.message, _this)
          }
          _this.list(_this.pageform.page)
        })
    },
    handleClose(done) {
      let _this = this
      if (_this.loading) {
        return
      }
      _this
        .$confirm('确定要提交表单吗？')
        .then(_ => {
          console.log('点击了确认按钮')
          _this.userForm.password = hex_md5(_this.userForm.password + KEY)
          _this.$ajax
            .post(
              process.env.VUE_APP_SERVER + '/system/user/save',
              _this.userForm
            )
            .then(response => {
              let resp = response.data
              if (resp.success) {
                _this.timer = setTimeout(() => {
                  done()
                  // 动画关闭需要一定的时间
                  setTimeout(() => {
                    _this.loading = false
                  }, 400)
                }, 2000)
                if (_this.userForm.userId) {
                  Toast.success('更新用户成功!', _this)
                } else {
                  Toast.success('新增用户成功!', _this)
                }
                _this.cancelForm()
                _this.list(_this.pageform.page)
              } else {
                _this.timer = setTimeout(() => {
                  done()
                  // 动画关闭需要一定的时间
                  setTimeout(() => {
                    _this.loading = false
                  }, 400)
                }, 2000)
                Toast.warning(resp.message, _this)
                _this.cancelForm()
              }
            })
        })
        .catch(_ => {})
    },
    cancelForm() {
      let _this = this
      _this.loading = false
      _this.dialog = false
      clearTimeout(this.timer)
      _this.userForm = {
        userId: '',
        name: '',
        loginName: '',
        password: ''
      }
    },
    submitForm(formName) {
      let _this = this
      _this.$refs[formName].validate(valid => {
        if (valid) {
          _this.$refs.drawer.closeDrawer()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>

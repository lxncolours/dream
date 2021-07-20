<template>
  <div>
    <el-page-header @back="goBack" content="会员管理"> </el-page-header>
    <el-divider></el-divider>
    <el-row :gutter="20">
      <el-col :span="2">
        <el-input
          placeholder="请输入会员编号"
          v-model="queryCustomer.customerId"
        >
        </el-input>
      </el-col>
      <el-col :span="2">
        <el-input
          placeholder="请输入会员名称"
          v-model="queryCustomer.customerName"
        >
        </el-input>
      </el-col>
      <el-col :span="2">
        <el-input placeholder="请输入会员电话" v-model="queryCustomer.mobile">
        </el-input>
      </el-col>
      <el-col :span="1" :offset="16"
        ><el-button type="primary" icon="el-icon-search" @click.native="list()"
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
      :data="customers"
      height="580"
      border
      style="width: 100%"
      v-loading="loading"
    >
      <el-table-column prop="customerId" label="会员编号" width="280">
      </el-table-column>
      <el-table-column prop="customerName" label="会员名称" width="280">
      </el-table-column>
      <el-table-column prop="mobile" label="会员电话" width="280">
      </el-table-column>
      <el-table-column prop="address" label="会员地址" width="480">
      </el-table-column>
      <el-table-column prop="sex" label="会员性别" width="280">
        <template slot-scope="scope">
          {{ scope.row.sex | optionKVArray(sexArry) }}
        </template>
      </el-table-column>
      <el-table-column prop="consumptionAmount" label="消费金额" width="200">
      </el-table-column>
      <el-table-column prop="balance" label="会员余额" width="200">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click="rechargeDialog(scope.$index, scope.row)"
            >充值</el-button
          >
          <el-button
            type="primary"
            size="mini"
            @click="cashOutDialog(scope.$index, scope.row)"
            >提现</el-button
          >
          <el-button
            size="mini"
            type="primary"
            icon="el-icon-edit"
            @click="editCustomer(scope.$index, scope.row)"
          ></el-button>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            slot="reference"
            @click="deleteDialog(scope.$index, scope.row)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block" style="float:right">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageForm.page"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageForm.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageForm.total"
      />
    </div>
    <el-dialog
      :title="deleteTitle"
      :visible.sync="deleteDialogVisible"
      width="15%"
      center
    >
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelForm()">取 消</el-button>
        <el-button type="primary" @click="deleteCustomer()">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="会员提现" :visible.sync="cashOutVisible" width="15%">
      <el-form :model="cashOutForm">
        <el-form-item label="会员名称:" :label-width="formLabelWidth">
          <el-input
            type="text"
            :readonly="true"
            v-model="cashOutForm.customerName"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item label="可用余额:" :label-width="formLabelWidth">
          <el-input
            type="text"
            :readonly="true"
            v-model="cashOutForm.balance"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelForm()">取 消</el-button>
        <el-button type="primary" @click="cashOut()">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="会员充值" :visible.sync="rechargeFormVisible" width="15%">
      <el-form
        :model="rechargeForm"
        :rules="rules"
        :hide-required-asterisk="true"
      >
        <el-form-item label="会员名称:" :label-width="formLabelWidth">
          <el-input
            type="text"
            :readonly="true"
            v-model="rechargeForm.customerName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="充值金额:"
          :label-width="formLabelWidth"
          prop="balance"
        >
          <el-input
            type="number"
            v-model="rechargeForm.balance"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelForm()">取 消</el-button>
        <el-button type="primary" @click="recharge()">确 定</el-button>
      </div>
    </el-dialog>
    <el-drawer
      :title="dialogTitle"
      :before-close="handleClose"
      :visible.sync="dialog"
      direction="rtl"
      :wrapperClosable="false"
      :show-close="false"
      :destroy-on-close="true"
      custom-class="demo-drawer"
      ref="drawer"
    >
      <div class="demo-drawer__content">
        <el-form
          :model="customerForm"
          :rules="rules"
          ref="customerForm"
          :hide-required-asterisk="true"
        >
          <el-form-item
            label="会员名称"
            :label-width="formLabelWidth"
            prop="customerName"
          >
            <el-input
              v-model="customerForm.customerName"
              autocomplete="off"
            ></el-input>
          </el-form-item>

          <el-form-item
            label="会员电话"
            :label-width="formLabelWidth"
            prop="mobile"
          >
            <el-input
              v-model="customerForm.mobile"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="会员地址"
            prop="address"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="customerForm.address"
              autocomplete="off"
            ></el-input>
          </el-form-item>

          <el-form-item
            label="会员性别"
            prop="sex"
            :label-width="formLabelWidth"
          >
            <el-select v-model="customerForm.sex" placeholder="请选择会员性别">
              <el-option
                v-for="item in sexArry"
                :key="item.key"
                :label="item.value"
                :value="item.key"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button @click="cancelForm()">取 消</el-button>
          <el-button
            type="primary"
            @click="submitForm('customerForm')"
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
  name: 'business-customer',
  data() {
    return {
      // 默认第一页,10条
      pageForm: {},
      customers: [],
      customer: {},
      queryCustomer: {},
      loading: false,
      dialog: false,
      sexArry: SEX_ARRAY,
      rechargeFormVisible: false,
      deleteDialogVisible: false,
      cashOutVisible: false,
      rules: {
        customerName: [
          { required: true, message: '请输入会员名称', trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: '请输入会员电话', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入会员地址', trigger: 'blur' }
        ],
        sex: [{ required: true, message: '请选择会员性别', trigger: 'blur' }],
        balance: [
          { required: true, message: '请输入充值金额', trigger: 'blur' }
        ]
      },
      // 新增和编辑弹框标题
      dialogTitle: '',
      customerForm: {
        customerName: '',
        mobile: '',
        address: '',
        sex: ''
      },
      rechargeForm: {
        balance: 0.0,
        customerId: '',
        customerName: ''
      },
      formLabelWidth: '80px',
      timer: null,
      deleteTitle: '',
      deleteId: '',
      cashOutForm: {
        customerId: '',
        customerName: '',
        balance: 0.0
      }
    }
  },
  mounted: function() {
    let _this = this
    _this.pageForm.size = 10
    _this.list()
  },
  methods: {
    editCustomer(index, row) {
      let _this = this
      console.log(index, row)
      _this.dialog = true
      _this.dialogTitle = '编辑会员'
      _this.customerForm.customerId = row.customerId
      _this.customerForm.customerName = row.customerName
      _this.customerForm.sex = row.sex
      _this.customerForm.address = row.address
      _this.customerForm.mobile = row.mobile
    },
    handleAdd() {
      let _this = this
      _this.dialog = true
      _this.dialogTitle = '新增会员'
    },
    deleteDialog(index, row) {
      let _this = this
      console.log(index, row)
      _this.deleteTitle = '确定删除会员' + row.customerName + '吗?'
      _this.deleteId = row.customerId
      _this.deleteDialogVisible = true
    },
    cashOutDialog(index, row) {
      let _this = this
      console.log(index, row)
      _this.cashOutForm.balance = row.balance
      _this.cashOutForm.customerId = row.customerId
      _this.cashOutForm.customerName = row.customerName
      _this.cashOutVisible = true
    },
    rechargeDialog(index, row) {
      let _this = this
      console.log(index, row)
      _this.rechargeForm.customerId = row.customerId
      _this.rechargeForm.customerName = row.customerName
      _this.rechargeFormVisible = true
    },
    handleSizeChange(val) {
      let _this = this
      console.log(`每页 ${val} 条`)
      _this.pageForm.size = val
      _this.list()
    },
    handleCurrentChange(val) {
      let _this = this
      console.log(`当前页: ${val}`)
      _this.pageForm.page = val
      _this.list()
    },
    render(page, total) {
      let _this = this
      _this.pageForm.page = page
      _this.pageForm.total = total
    },
    /**
     * 列表查询
     */
    list() {
      let _this = this
      _this.loading = true
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + '/business/customer/list', {
          page: _this.pageForm.page,
          size: _this.pageForm.size,
          customerId: _this.queryCustomer.customerId,
          customerName: _this.queryCustomer.customerName,
          mobile: _this.queryCustomer.mobile
        })
        .then(response => {
          _this.loading = false
          let resp = response.data
          if (resp.success) {
            _this.customers = resp.content.list
            _this.render(resp.content.page, resp.content.total)
          }
        })
    },
    goBack() {
      let _this = this
      console.log('go back')
      _this.$router.push('welcome')
    },
    deleteCustomer() {
      let _this = this
      _this.loading = true
      _this.$ajax
        .delete(
          process.env.VUE_APP_SERVER +
            '/business/customer/delete/' +
            _this.deleteId
        )
        .then(response => {
          _this.loading = false
          let resp = response.data
          if (resp.success) {
            Toast.success('删除会员成功!', _this)
          } else {
            Toast.warning(resp.message, _this)
          }
          _this.deleteDialogVisible = false
          _this.list(_this.pageForm.page)
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
          _this.$ajax
            .post(
              process.env.VUE_APP_SERVER + '/business/customer/save',
              _this.customerForm
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
                if (_this.customerForm.customerId) {
                  Toast.success('更新会员成功!', _this)
                } else {
                  Toast.success('新增会员成功!', _this)
                }
                _this.cancelForm()
                _this.list(_this.pageForm.page)
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
      _this.rechargeFormVisible = false
      _this.cashOutVisible = false
      clearTimeout(this.timer)
      _this.customerForm = {
        sex: '',
        customerName: '',
        mobile: '',
        address: ''
      }
      _this.rechargeForm = {
        balance: 0.0,
        customerId: '',
        customerName: ''
      }
      _this.cashOutForm = {
        balance: 0.0,
        customerName: '',
        customerId: ''
      }
      _this.deleteTitle = ''
      _this.deleteId = ''
    },
    submitForm(formName) {
      let _this = this
      _this.$refs[formName].validate(valid => {
        if (valid) {
          _this.$refs.drawer.closeDrawer()
        } else {
          return false
        }
      })
    },
    recharge() {
      let _this = this
      if (_this.rechargeForm.balance <= 0) {
        Toast.warning('请输入大于0的充值金额', _this)
      } else {
        _this.$ajax
          .post(
            process.env.VUE_APP_SERVER + '/business/customer/recharge/',
            _this.rechargeForm
          )
          .then(response => {
            let resp = response.data
            if (resp.success) {
              Toast.success('充值成功!', _this)
            } else {
              Toast.warning(resp.message, _this)
            }
            _this.cancelForm()
            _this.list(_this.pageForm.page)
          })
      }
    },
    cashOut() {
      let _this = this
      if (_this.cashOutForm.balance <= 0) {
        Toast.warning('无可提现余额', _this)
        _this.cancelForm()
      } else {
        _this.$ajax
          .post(
            process.env.VUE_APP_SERVER + '/business/customer/cashOut/',
            _this.cashOutForm
          )
          .then(response => {
            let resp = response.data
            if (resp.success) {
              Toast.success('提现成功!', _this)
            } else {
              Toast.warning(resp.message, _this)
            }
            _this.cancelForm()
            _this.list(_this.pageForm.page)
          })
      }
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

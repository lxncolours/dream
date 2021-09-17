<template>
  <div>
    <el-page-header @back="goBack" content="订单录入"> </el-page-header>
    <el-divider></el-divider>
    <el-row :gutter="20">
      <el-col :span="3">
        <el-tag>{{
          showForm.customerId === '' ? '会员编号' : showForm.customerId
        }}</el-tag>
        <el-tag>{{
          showForm.customerName === '' ? '会员名称' : showForm.customerName
        }}</el-tag>

        <el-tag>{{
          showForm.mobile === '' ? '会员电话' : showForm.mobile
        }}</el-tag>
      </el-col>
      <el-col :span="1" :offset="18"
        ><el-button
          type="primary"
          size="mini"
          icon="el-icon-search"
          @click.native="openDialog()"
          >选择会员</el-button
        ></el-col
      >
      <el-col :span="1"
        ><el-button
          type="primary"
          size="mini"
          icon="el-icon-plus"
          @click.native="openStockDialog()"
          >新增订单</el-button
        ></el-col
      >
    </el-row>
    <!-- table -->
    <el-table
      :data="orderForm.orderDet"
      height="580"
      border
      style="width: 100%"
      v-loading="loading"
    >
      <el-table-column prop="storeName" label="商家名称" width="320">
      </el-table-column>
      <el-table-column prop="goodsName" label="商品名称" width="320">
      </el-table-column>
      <el-table-column prop="stockId" label="入库id" width="320">
      </el-table-column>
      <el-table-column prop="inPrice" label="入库单价" width="320">
      </el-table-column>
      <el-table-column prop="outPrice" label="出库单价" width="320">
      </el-table-column>
      <el-table-column prop="num" label="商品数量" width="200">
      </el-table-column>
      <el-table-column prop="inMoney" label="入库金额" width="200">
      </el-table-column>
      <el-table-column prop="outMoney" label="出库金额" width="200">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-popconfirm
            title="确定删除该订单吗？"
            @confirm="deleteOrder(scope.$index, scope.row)"
          >
            <el-button type="danger" size="mini" slot="reference"
              >删除</el-button
            >
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-row>
      <el-col :span="1" :offset="22"
        ><el-button
          type="primary"
          size="mini"
          icon="el-icon-s-promotion"
          @click.native="createOrder()"
          >发送订单</el-button
        ></el-col
      >
    </el-row>

    <!-- customerDialog -->
    <el-dialog
      title="选择会员"
      :visible.sync="dialog"
      :before-close="closeDialog"
    >
      <el-row :gutter="20">
        <el-col :span="3">
          <el-input placeholder="会员编号" v-model="queryCustomer.customerId">
          </el-input>
        </el-col>
        <el-col :span="3">
          <el-input placeholder="会员名称" v-model="queryCustomer.customerName">
          </el-input>
        </el-col>
        <el-col :span="3">
          <el-input placeholder="会员电话" v-model="queryCustomer.mobile">
          </el-input>
        </el-col>
        <el-col :span="1" :offset="12"
          ><el-button
            type="primary"
            icon="el-icon-search"
            @click.native="listCustomer()"
            >搜索</el-button
          ></el-col
        >
      </el-row>
      <el-table
        :data="customers"
        height="580"
        @row-click="selectCustomer"
        highlight-current-row
        style="width: 100%"
        v-loading="customerLoading"
      >
        <el-table-column prop="customerId" label="会员编号" width="180">
        </el-table-column>
        <el-table-column prop="customerName" label="会员名称" width="180">
        </el-table-column>
        <el-table-column prop="mobile" label="会员电话" width="180">
        </el-table-column>
        <el-table-column prop="address" label="会员地址" width="180">
        </el-table-column>
        <el-table-column prop="sex" label="会员性别" width="170">
          <template slot-scope="scope">
            {{ scope.row.sex | optionKVArray(sexArry) }}
          </template>
        </el-table-column>
        <el-table-column prop="consumptionAmount" label="消费金额" width="170">
        </el-table-column>
        <el-table-column prop="balance" label="会员余额" width="170">
        </el-table-column>
      </el-table>
      <el-row>
        <el-col :span="1" :offset="22"
          ><el-button
            type="primary"
            icon="el-icon-check"
            @click.native="dialog = false"
            >确定</el-button
          ></el-col
        >
      </el-row>
    </el-dialog>
    <!-- stockDialog -->
    <el-dialog
      title="选择库存"
      :visible.sync="stockDialog"
      :before-close="closeStockDialog"
    >
      <el-row :gutter="20">
        <el-col :span="3">
          <el-input placeholder="商品名称" v-model="queryStock.goodsName">
          </el-input>
        </el-col>
        <el-col :span="5">
          <el-date-picker
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="开始日期"
            v-model="queryStock.beginDate"
            :editable="false"
          ></el-date-picker>
        </el-col>
        <el-col :span="5">
          <el-date-picker
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="截止日期"
            v-model="queryStock.endDate"
            :editable="false"
          ></el-date-picker>
        </el-col>
        <el-col :span="1" :offset="8"
          ><el-button
            type="primary"
            icon="el-icon-search"
            @click.native="listStocks()"
            >搜索</el-button
          ></el-col
        >
      </el-row>
      <el-table
        :data="stocks"
        height="580"
        @row-click="selectStock"
        highlight-current-row
        style="width: 100%"
        v-loading="stockLoading"
      >
        <el-table-column prop="storeName" label="商家名称" width="200">
        </el-table-column>
        <el-table-column prop="goodsName" label="商品名称" width="200">
        </el-table-column>
        <el-table-column prop="stockId" label="入库id" width="200">
        </el-table-column>
        <el-table-column prop="stockDate" label="入库时间" width="200">
        </el-table-column>
        <el-table-column prop="inPrice" label="入库单价" width="200">
        </el-table-column>
        <el-table-column prop="stockNum" label="剩余库存" width="200">
        </el-table-column>
      </el-table>
      <el-row>
        <el-col :span="3" :offset="14">
          <el-input placeholder="出库单价" v-model="orderDet.outPrice">
          </el-input>
        </el-col>
        <el-col :span="3" :offset="1">
          <el-input placeholder="出库数量" v-model="orderDet.num"> </el-input>
        </el-col>
        <el-col :span="1" :offset="1"
          ><el-button
            type="primary"
            icon="el-icon-check"
            @click.native="submitStock"
            >确定</el-button
          ></el-col
        >
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'business-addOrder',
  data() {
    return {
      // 默认第一页,10条
      pageForm: {},
      customers: [],
      stocks: [],
      orderDet: {},
      queryCustomer: {
        customerId: '',
        customerName: '',
        mobile: ''
      },
      loading: false,
      exists: false,
      customerLoading: false,
      stockLoading: false,
      dialog: false,
      stockDialog: false,
      sexArry: SEX_ARRAY,
      loginName: '',
      index: 0,
      state: '',
      timeout: null,
      orderDetArray: [],
      showForm: { customerId: '', customerName: '', mobile: '' },
      queryStock: { goodsName: '', beginDate: '', endDate: '' },
      orderForm: {
        customerId: '',
        status: 'U',
        operator: '',
        orderDet: []
      },
      rules: {
        num: [{ required: true, message: '请输入出库数量', trigger: 'blur' }],
        outPrice: [
          { required: true, message: '请输入出库单价', trigger: 'blur' }
        ]
      },
      formLabelWidth: '80px'
    }
  },
  mounted: function() {
    let _this = this
    console.log(Tool.getLoginUser())
    _this.loginName = Tool.getLoginUser().loginName
    // _this.list()
  },
  methods: {
    openDialog() {
      let _this = this
      _this.orderForm.operator = _this.loginName
      _this.dialog = true
    },
    closeDialog() {
      let _this = this
      _this.orderForm.customerId = ''
      _this.showForm.customerId = ''
      _this.showForm.customerName = ''
      _this.showForm.mobile = ''
      _this.dialog = false
    },
    openStockDialog() {
      let _this = this
      // 打开之前判断orderForm里customerId有没有值
      let customerId = _this.orderForm.customerId
      if (Tool.isEmpty(customerId)) {
        Toast.warning('请先选择会员!', _this)
        return
      }
      _this.stockDialog = true
    },
    closeStockDialog() {
      let _this = this
      _this.stockDialog = false
      _this.cancelForm()
      _this.orderDet = {}
    },
    submitStock() {
      let _this = this
      let orderArray = _this.orderForm.orderDet
      // 判断该库存是否已在订单列表中
      let stockId = _this.orderDet.stockId

      orderArray.forEach(item => {
        if (item.stockId === stockId) {
          _this.exists = true
          return false
        }
      })
      if (_this.exists) {
        Toast.warning(
          '该库存已存在订单列表中,如果需要修改数量请先在订单列表中删除再进行添加!',
          _this
        )
        _this.stockDialog = false
        _this.cancelForm()
        return
      }
      // 判断出库数量是否大于库存数量
      let stockNum = _this.orderDet.stockNum
      let num = _this.orderDet.num
      if (Tool.isEmpty(stockNum) || stockNum < 0) {
        Toast.warning('请输入大于0的出库数量!', _this)
        return ''
      }
      if (
        Tool.isEmpty(_this.orderDet.outPrice) ||
        _this.orderDet.outPrice < 0
      ) {
        Toast.warning('请输入大于0的出库单价!', _this)
        return ''
      }
      if (num > stockNum) {
        Toast.warning('出库数量大于库存数量,请重新输入出库数量!', _this)
        return ''
      }
      // 计算入库金额和出库金额
      _this.orderDet.outMoney = _this.orderDet.outPrice * num
      _this.orderDet.inMoney = _this.orderDet.inPrice * num
      _this.orderForm.orderDet.push(_this.orderDet)
      _this.stockDialog = false
      _this.cancelForm()
    },
    deleteOrder(index, row) {
      let _this = this
      console.log(index, row)
      Tool.removeObj(_this.orderForm.orderDet, row)
    },
    selectCustomer(row) {
      let _this = this
      // 选择行之后将id赋值给orderForm,将name和mobile赋值给标签
      console.log(row)
      _this.orderForm.customerId = row.customerId
      _this.showForm.customerId = row.customerId
      _this.showForm.customerName = row.customerName
      _this.showForm.mobile = row.mobile
      // _this.dialog = false
    },
    selectStock(row) {
      let _this = this
      // 将选择的所有属性放到orderDte
      _this.orderDet = row
      console.log(_this.orderDet)
      // _this.dialog = false
    },
    /**
     * 列表查询
     */
    listCustomer() {
      let _this = this
      _this.customerLoading = true
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + '/business/customer/list', {
          customerId: _this.queryCustomer.customerId,
          customerName: _this.queryCustomer.customerName,
          mobile: _this.queryCustomer.mobile
        })
        .then(response => {
          _this.customerLoading = false
          let resp = response.data
          if (resp.success) {
            _this.customers = resp.content.list
          }
        })
    },
    listStocks() {
      let _this = this
      _this.stockLoading = true
      _this.$ajax
        .post(
          process.env.VUE_APP_SERVER + '/business/stock/list',
          _this.queryStock
        )
        .then(response => {
          _this.stockLoading = false
          let resp = response.data
          if (resp.success) {
            _this.stocks = resp.content.list
          }
        })
    },
    goBack() {
      let _this = this
      console.log('go back')
      _this.$router.push('welcome')
    },
    cancelForm() {
      let _this = this
      _this.queryCustomer = {
        customerId: '',
        customerName: '',
        mobile: ''
      }
      _this.queryStock = {
        goodsName: '',
        beginDate: '',
        endDate: ''
      }
      _this.customers = []
      _this.stocks = []
    },
    createOrder() {
      let _this = this
      if (_this.orderForm.orderDet.length === 0) {
        Toast.warning('订单列表为空,无法发送!', _this)
      } else {
        _this.$ajax
          .post(
            process.env.VUE_APP_SERVER + '/business/order/save/',
            _this.orderForm
          )
          .then(response => {
            let resp = response.data
            if (resp.success) {
              Toast.success('发送成功!', _this)
              _this.orderForm = {
                customerId: '',
                status: 'U',
                operator: '',
                orderDet: []
              }
              _this.showForm = { customerId: '', customerName: '', mobile: '' }
            } else {
              Toast.warning(resp.message, _this)
            }
            _this.cancelForm()
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

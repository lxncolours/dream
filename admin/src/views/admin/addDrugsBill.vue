<template>
  <div>
    <el-page-header @back="goBack" content="账单录入"> </el-page-header>
    <el-divider></el-divider>
    <el-row>
      <el-col :span="1">
        <span>客户名称:</span>
      </el-col>

      <el-col :span="2">
        <el-input v-model="billForm.customerName" size="mini" />
      </el-col>
      <el-col :offset="19" :span="1"
        ><el-button
          type="primary"
          size="mini"
          icon="el-icon-plus"
          @click.native="dialogFormVisible = true"
          >新建订单项</el-button
        ></el-col
      >
    </el-row>
    <!-- table -->
    <el-table :data="billForm.billDet" height="580" border style="width: 100%">
      <el-table-column prop="goodsName" label="商品名称"> </el-table-column>
      <el-table-column prop="goodsNum" label="商品数量"> </el-table-column>
      <el-table-column prop="salePrice" label="商品售价"> </el-table-column>
      <el-table-column prop="goodsAmount" label="商品金额"> </el-table-column>
      <el-table-column prop="costPrice" label="商品进价"> </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-popconfirm
            title="确定删除该订单吗？"
            @confirm="deleteBillDet(scope.$index, scope.row)"
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
          @click.native="createBill()"
          >发送帐单</el-button
        ></el-col
      >
    </el-row>

    <!-- 新增订单项弹窗 -->
    <el-dialog title="新建订单项" :visible.sync="dialogFormVisible" width="20%">
      <el-form :model="billDet" :rules="rules">
        <el-form-item
          label="商品名称"
          :label-width="formLabelWidth"
          prop="goodsName"
        >
          <el-input v-model="billDet.goodsName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="商品数量"
          :label-width="formLabelWidth"
          prop="goodsNum"
        >
          <el-input v-model="billDet.goodsNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="商品售价"
          :label-width="formLabelWidth"
          prop="salePrice"
        >
          <el-input v-model="billDet.salePrice" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品进价" :label-width="formLabelWidth">
          <el-input v-model="billDet.costPrice" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addBillDet()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'business-drugsBill',
  data() {
    return {
      loginName: '',
      index: 0,
      state: '',
      billDet: {},
      timeout: null,
      dialogFormVisible: false,
      billForm: {
        customerName: '',
        billDet: []
      },
      rules: {
        goodsName: [
          { required: true, message: '请输入商品名称', trigger: 'blur' }
        ],
        goodsNum: [
          {
            required: true,
            message: '请输入商品数量',
            trigger: 'blur'
          }
        ],
        salePrice: [
          {
            required: true,
            message: '请输入大于0的商品单价',
            trigger: 'blur'
          }
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
    deleteBillDet(index, row) {
      let _this = this
      console.log(index, row)
      Tool.removeObj(_this.billForm.billDet, row)
    },
    goBack() {
      let _this = this
      _this.$router.push('welcome')
    },
    addBillDet() {
      let _this = this
      _this.billDet.goodsAmount =
        _this.billDet.salePrice * _this.billDet.goodsNum
      _this.billForm.billDet.push(_this.billDet)
      _this.billDet = {}
      _this.dialogFormVisible = false
    },
    createBill() {
      let _this = this
      if (_this.billForm.billDet.length === 0) {
        Toast.warning('订单列表为空,无法发送!', _this)
      } else if (Tool.isEmpty(_this.billForm.customerName)) {
        Toast.warning('客户姓名为空,无法发送!', _this)
      } else {
        _this.$ajax
          .post(
            process.env.VUE_APP_SERVER + '/business/drugsBill/save/',
            _this.billForm
          )
          .then(response => {
            let resp = response.data
            if (resp.success) {
              Toast.success('发送成功!', _this)
              _this.billForm = {
                customerName: '',
                billDet: []
              }
            } else {
              Toast.warning(resp.message, _this)
            }
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

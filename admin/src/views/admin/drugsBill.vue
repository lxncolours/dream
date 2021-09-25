<template>
  <div>
    <el-page-header @back="goBack" content="账单查询"> </el-page-header>
    <el-divider></el-divider>
    <el-row>
      <el-col :span="1">
        <span>客户名称:</span>
      </el-col>

      <el-col :span="2">
        <el-input v-model="customerName" size="mini" />
      </el-col>
      <el-col :offset="19" :span="1"
        ><el-button
          type="primary"
          size="mini"
          icon="el-icon-search"
          @click.native="list()"
          >查询</el-button
        ></el-col
      >
    </el-row>
    <el-divider></el-divider>
    <!-- table -->
    <span>订单列表</span>
    <el-table :data="billTable" height="200" border style="width: 100%">
      <el-table-column prop="billId" label="订单Id"> </el-table-column>
      <el-table-column prop="customerName" label="客户名称"> </el-table-column>
      <el-table-column prop="billDate" label="订单时间"> </el-table-column>
      <el-table-column prop="billAmount" label="订单金额"> </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-popconfirm
            title="确定删除该订单吗？"
            @confirm="deleteBill(scope.$index, scope.row)"
          >
            <el-button type="danger" size="mini" slot="reference"
              >删除</el-button
            >
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-divider></el-divider>
    <span>订单明细列表</span>
    <!-- detTable -->
    <el-table :data="billDetTable" height="400" border style="width: 100%">
      <el-table-column prop="goodsName" label="商品名称"> </el-table-column>
      <el-table-column prop="goodsNum" label="商品数量"> </el-table-column>
      <el-table-column prop="salePrice" label="商品售价"> </el-table-column>
      <el-table-column prop="goodsAmount" label="商品金额"> </el-table-column>
      <el-table-column prop="costPrice" label="商品进价"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'business-drugsBill',
  data() {
    return {
      loginName: '',
      customerName: '',
      billTable: [],
      billDetTable: [],
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
    deleteBill(index, row) {
      let _this = this
      console.log(index, row)
      Tool.removeObj(_this.billForm.billDet, row)
    },
    goBack() {
      let _this = this
      _this.$router.push('welcome')
    },
    /**
     * 列表查询
     */
    list() {
      let _this = this
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + '/business/drugsBill/list', {
          customerName: _this.customerName
        })
        .then(response => {
          let resp = response.data
          if (resp.success) {
            _this.billTable = resp.content.list
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

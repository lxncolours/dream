<template>
  <div>
    <el-page-header @back="goBack" content="订单管理"> </el-page-header>
    <el-divider></el-divider>
    <el-row :gutter="20">
      <el-col :span="2">
        <el-select
          v-model="queryGoods.category1Id"
          clearable
          @change="listCategoryInit(2, queryGoods.category1Id)"
          placeholder="请选择一级分类"
        >
          <el-option
            v-for="item in categoryArray1"
            :key="item.categoryId"
            :label="item.categoryName"
            :value="item.categoryId"
          >
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="2">
        <el-select
          v-model="queryGoods.category2Id"
          clearable
          @change="listCategoryInit(3, queryGoods.category2Id)"
          placeholder="请选择二级分类"
        >
          <el-option
            v-for="item in categoryArray2"
            :key="item.categoryId"
            :label="item.categoryName"
            :value="item.categoryId"
          >
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="2">
        <el-select
          v-model="queryGoods.category3Id"
          clearable
          placeholder="请选择三级分类"
        >
          <el-option
            v-for="item in categoryArray3"
            :key="item.categoryId"
            :label="item.categoryName"
            :value="item.categoryId"
          >
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="2">
        <el-input placeholder="请输入商品名称" v-model="queryGoods.goodsName">
        </el-input>
      </el-col>
      <el-col :span="2">
        <el-input placeholder="请输入商家名称" v-model="queryGoods.storeName">
        </el-input>
      </el-col>
      <el-col :span="1" :offset="10"
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
      :data="goodss"
      height="580"
      border
      style="width: 100%"
      v-loading="loading"
    >
      <el-table-column prop="storeName" label="商家名称" width="280">
      </el-table-column>
      <el-table-column prop="goodsName" label="商品名称" width="280">
      </el-table-column>
      <el-table-column prop="category1Name" label="一级分类" width="280">
      </el-table-column>
      <el-table-column prop="category2Name" label="二级分类" width="280">
      </el-table-column>
      <el-table-column prop="category3Name" label="三级分类" width="280">
      </el-table-column>
      <el-table-column prop="price" label="上次入库单价" width="280">
      </el-table-column>
      <el-table-column prop="num" label="商品库存" width="280">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click="stockDialog(scope.$index, scope.row)"
            >入库</el-button
          >
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-popconfirm
            title="确定删除该商品吗？"
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
          :model="goodsForm"
          :rules="rules"
          ref="goodsForm"
          :hide-required-asterisk="true"
        >
          <el-form-item
            label="商家名称"
            :label-width="formLabelWidth"
            prop="storeName"
          >
            <el-input
              v-model="goodsForm.storeName"
              autocomplete="off"
            ></el-input>
          </el-form-item>

          <el-form-item
            label="商品名称"
            :label-width="formLabelWidth"
            prop="goodsName"
          >
            <el-input
              v-model="goodsForm.goodsName"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="一级分类"
            prop="category1Id"
            :label-width="formLabelWidth"
          >
            <el-select
              v-model="goodsForm.category1Id"
              @change="listCategory2(2, goodsForm.category1Id)"
              placeholder="请选择一级分类"
            >
              <el-option
                v-for="item in categoryOne"
                :key="item.categoryId"
                :label="item.categoryName"
                :value="item.categoryId"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="二级分类"
            prop="category2Id"
            :label-width="formLabelWidth"
          >
            <el-select
              v-model="goodsForm.category2Id"
              @change="listCategory2(3, goodsForm.category2Id)"
              placeholder="请选择二级分类"
            >
              <el-option
                v-for="item in categoryTwo"
                :key="item.categoryId"
                :label="item.categoryName"
                :value="item.categoryId"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="三级分类"
            prop="category3Id"
            :label-width="formLabelWidth"
          >
            <el-select
              v-model="goodsForm.category3Id"
              placeholder="请选择三级分类"
            >
              <el-option
                v-for="item in categoryThree"
                :key="item.categoryId"
                :label="item.categoryName"
                :value="item.categoryId"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button @click="cancelForm">取 消</el-button>
          <el-button
            type="primary"
            @click="submitForm('goodsForm')"
            :loading="loading"
            >{{ loading ? '提交中 ...' : '确 定' }}</el-button
          >
        </div>
      </div>
    </el-drawer>
    <el-dialog title="商品入库" :visible.sync="stockFormVisible" width="15%">
      <el-form :model="stockForm" :rules="rules" :hide-required-asterisk="true">
        <el-form-item label="商品名称:" :label-width="formLabelWidth">
          <el-input
            type="text"
            :readonly="true"
            v-model="stockForm.goodsName"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item
          label="入库单价:"
          :label-width="formLabelWidth"
          prop="inPrice"
        >
          <el-input
            type="number"
            v-model="stockForm.inPrice"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item
          label="入库数量:"
          :label-width="formLabelWidth"
          prop="inNum"
        >
          <el-input
            type="number"
            v-model="stockForm.inNum"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelForm()">取 消</el-button>
        <el-button type="primary" @click="stock()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'business-order',
  data() {
    return {
      // 默认第一页,10条
      pageform: {},
      goodss: [],
      goods: {},
      queryGoods: {},
      loading: false,
      dialog: false,
      stockFormVisible: false,
      categoryOne: [],
      categoryTwo: [],
      categoryThree: [],
      categoryArray1: [],
      categoryArray2: [],
      categoryArray3: [],
      rules: {
        storeName: [
          { required: true, message: '请输入商家名称', trigger: 'blur' }
        ],
        goodsName: [
          { required: true, message: '请输入商品名称', trigger: 'blur' }
        ],
        category1Id: [
          { required: true, message: '请选择一级分类', trigger: 'blur' }
        ],
        category2Id: [
          { required: true, message: '请选择二级分类', trigger: 'blur' }
        ],
        category3Id: [
          { required: true, message: '请选择三级分类', trigger: 'blur' }
        ],
        inPrice: [
          { required: true, message: '请输入入库单价', trigger: 'blur' }
        ],
        inNum: [{ required: true, message: '请输入入库数量', trigger: 'blur' }]
      },
      // 新增和编辑弹框标题
      dialogTitle: '',
      goodsForm: {
        storeName: '',
        goodsName: '',
        category1Id: '',
        category2Id: '',
        category3Id: ''
      },
      stockForm: {
        goodsId: '',
        goodsName: '',
        inPrice: '',
        inNum: ''
      },
      formLabelWidth: '80px',
      timer: null
    }
  },
  mounted: function() {
    let _this = this
    _this.pageform.size = 10
    _this.list()
    _this.listCategoryInit(1, '')
  },
  methods: {
    handleEdit(index, row) {
      let _this = this
      console.log(index, row)
      _this.dialog = true
      _this.dialogTitle = '编辑商品'
      _this.listCategory(1, '')
      _this.listCategory(2, row.category1Id)
      _this.listCategory(3, row.category2Id)
      _this.goodsForm.goodsId = row.goodsId
      _this.goodsForm.goodsName = row.goodsName
      _this.goodsForm.category1Id = row.category1Id
      _this.goodsForm.category2Id = row.category2Id
      _this.goodsForm.category3Id = row.category3Id
      _this.goodsForm.storeName = row.storeName
    },
    handleAdd() {
      let _this = this
      _this.dialog = true
      _this.dialogTitle = '新增商品'
      _this.listCategory(1, '')
    },
    handleDelete(index, row) {
      let _this = this
      console.log(index, row)
      _this.delete(row.goodsId)
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
        .post(process.env.VUE_APP_SERVER + '/business/goods/list', {
          page: _this.pageform.page,
          size: _this.pageform.size,
          category1Id: _this.queryGoods.category1Id,
          category2Id: _this.queryGoods.category2Id,
          category3Id: _this.queryGoods.category3Id,
          goodsName: _this.queryGoods.goodsName,
          storeName: _this.queryGoods.storeName
        })
        .then(response => {
          _this.loading = false
          let resp = response.data
          if (resp.success) {
            _this.goodss = resp.content.list
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
        .delete(process.env.VUE_APP_SERVER + '/business/goods/delete/' + id)
        .then(response => {
          _this.loading = false
          let resp = response.data
          if (resp.success) {
            Toast.success('删除商品成功!', _this)
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
          _this.goodsForm.password = hex_md5(_this.goodsForm.password + KEY)
          _this.$ajax
            .post(
              process.env.VUE_APP_SERVER + '/business/goods/save',
              _this.goodsForm
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
                if (_this.goodsForm.goodsId) {
                  Toast.success('更新商品成功!', _this)
                } else {
                  Toast.success('新增商品成功!', _this)
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
      _this.stockFormVisible = false
      _this.categoryOne = []
      _this.categoryTwo = []
      _this.categoryThree = []
      clearTimeout(this.timer)
      _this.goodsForm = {
        storeName: '',
        goodsName: '',
        category1Id: '',
        category2Id: '',
        category3Id: ''
      }
      _this.stockForm = {
        goodsId: '',
        goodsName: '',
        inPrice: '',
        inNum: ''
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
    },
    listCategoryInit(level, categoryId) {
      let _this = this
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + '/business/category/listCategory', {
          level,
          categoryId
        })
        .then(response => {
          let resp = response.data
          if (resp.success) {
            if (level === 1) {
              _this.categoryArray1 = resp.content.list
              _this.categoryArray2 = []
            } else if (level === 2) {
              _this.categoryArray2 = resp.content.list
              _this.categoryArray3 = []
            } else if (level === 3) {
              _this.categoryArray3 = resp.content.list
            }
          }
        })
    },
    listCategory(level, categoryId) {
      let _this = this
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + '/business/category/listCategory', {
          level,
          categoryId
        })
        .then(response => {
          let resp = response.data
          if (resp.success) {
            if (level === 1) {
              _this.categoryOne = resp.content.list
            } else if (level === 2) {
              _this.categoryTwo = resp.content.list
              _this.categoryThree = []
            } else if (level === 3) {
              _this.categoryThree = resp.content.list
            }
          }
        })
    },
    listCategory2(level, categoryId) {
      let _this = this
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + '/business/category/listCategory', {
          level,
          categoryId
        })
        .then(response => {
          let resp = response.data
          if (resp.success) {
            if (level === 1) {
              _this.categoryOne = resp.content.list
            } else if (level === 2) {
              _this.categoryTwo = resp.content.list
              _this.categoryThree = []
              _this.goodsForm.category2Id = ''
              _this.goodsForm.category3Id = ''
            } else if (level === 3) {
              _this.goodsForm.category3Id = ''
              _this.categoryThree = resp.content.list
            }
          }
        })
    },
    stockDialog(index, row) {
      let _this = this
      console.log(index, row)
      _this.stockForm.goodsId = row.goodsId
      _this.stockForm.goodsName = row.goodsName
      _this.stockFormVisible = true
    },
    stock() {
      let _this = this
      if (_this.stockForm.inPrice <= 0) {
        Toast.warning('请输入大于0的入库单价', _this)
      } else if (_this.stockForm.inNum <= 0) {
        Toast.warning('请输入大于0的入库数量', _this)
      } else {
        _this.$ajax
          .post(
            process.env.VUE_APP_SERVER + '/business/stock/stock/',
            _this.stockForm
          )
          .then(response => {
            let resp = response.data
            if (resp.success) {
              Toast.success('入库成功!', _this)
            } else {
              Toast.warning(resp.message, _this)
            }
            _this.list(_this.pageform.page)
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

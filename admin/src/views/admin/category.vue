<template>
  <div>
    <el-page-header @back="goBack" content="分类管理"> </el-page-header>
    <el-divider></el-divider>
    <el-row :gutter="50">
      <el-col :span="2">
        <el-select v-model="queryForm.level" placeholder="请选择分类级别">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="2">
        <el-input placeholder="请输入分类名称" v-model="queryForm.categoryName">
        </el-input>
      </el-col>
      <el-col :span="1"
        ><el-button type="primary" icon="el-icon-search" @click.native="list()"
          >搜索</el-button
        ></el-col
      >
    </el-row>
    <el-row>
      <el-col :span="2">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>一级分类</span>
            <el-button
              :loading="loadingOne"
              style="float: right; padding: 3px 0"
              type="text"
              @click="addDialog(1)"
              >新增分类</el-button
            >
          </div>
          <div>
            <el-table
              :data="categoryOne"
              height="400"
              border
              highlight-current-row
              @current-change="handleCurrentChange"
              v-loading="loadingOne"
              row-key="id"
            >
              <el-table-column
                prop="categoryName"
                label="分类名称"
                width="280"
                @click="list(scope.row)"
              >
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                    type="primary"
                    size="mini"
                    @click="deleteDialog(scope.row)"
                    >删除</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-card>
      </el-col>
      <el-col :span="2" :offset="4">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>二级分类</span>
            <el-button
              :loading="loadingTwo"
              style="float: right; padding: 3px 0"
              @click="addDialog(2)"
              type="text"
              >新增分类</el-button
            >
          </div>
          <div>
            <el-table
              :data="categoryTwo"
              height="400"
              border
              highlight-current-row
              @current-change="handleCurrentChange"
              v-loading="loadingTwo"
              row-key="id"
            >
              <el-table-column prop="categoryName" label="分类名称" width="280">
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                    type="primary"
                    size="mini"
                    @click="deleteDialog(scope.row)"
                    >删除</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-card>
      </el-col>
      <el-col :span="2" :offset="4">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>三级分类</span>
            <el-button
              :loading="loadingThree"
              style="float: right; padding: 3px 0"
              type="text"
              @click="addDialog(3)"
              >新增分类</el-button
            >
          </div>
          <div>
            <el-table
              :data="categoryThree"
              height="400"
              border
              row-key="id"
              v-loading="loadingThree"
            >
              <el-table-column prop="categoryName" label="分类名称" width="280">
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                    type="primary"
                    size="mini"
                    @click="deleteDialog(scope.row)"
                    >删除</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog title="新增分类" :visible.sync="categoryVisible" width="15%">
      <el-form :model="categoryForm">
        <el-form-item label="分类名称:">
          <el-input
            type="text"
            v-model="categoryForm.categoryName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelForm()">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="确定删除该分类吗?"
      :visible.sync="deleteDialogVisible"
      width="15%"
      center
    >
      <span slot="footer" class="dialog-footer">
        <el-button @click="deleteDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="deleteCategory()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'system-category',
  data() {
    return {
      categorys: [],
      options: [
        {
          value: 1,
          label: '一级分类'
        },
        {
          value: 2,
          label: '二级分类'
        },
        {
          value: 3,
          label: '三级分类'
        }
      ],
      loadingOne: false,
      loadingTwo: false,
      loadingThree: false,
      categoryVisible: false,
      deleteDialogVisible: false,
      deleteId: '',
      deleteLevel: 0,
      rules: {
        categoryName: [
          { required: true, message: '请输入分类名称', trigger: 'blur' }
        ]
      },
      // 新增和编辑弹框标题
      categoryForm: {
        level: 1,
        categoryName: '',
        parentId: ''
      },
      queryForm: {
        level: 1,
        categoryName: '',
        parentId: ''
      },
      categoryOne: [],
      categoryTwo: [],
      categoryThree: [],
      parentIdOne: '',
      parentIdTwo: ''
    }
  },
  mounted: function() {
    let _this = this
    _this.list()
  },
  methods: {
    handleCurrentChange(row) {
      let _this = this
      let level = row.level
      console.log('' + row.categoryId)
      console.log('' + row.parentId)
      console.log('' + row.categoryName)
      console.log('row-level' + row.level)
      _this.parentId = row.categoryId
      _this.queryForm.parentId = row.categoryId
      _this.queryForm.level = level + 1
      if (level === 1) {
        // 如果是选择的一级分类,先清空二级分类
        _this.categoryTwo = []
        _this.categoryThree = []
        _this.parentIdOne = row.categoryId
        _this.parentIdTwo = ''
      } else if (level === 2) {
        _this.categoryThree = []
        _this.parentIdTwo = row.categoryId
      } else if (level === 3) {
        return _this
      }
      _this.list()
    },
    addDialog(level) {
      let _this = this
      let parentIdOne = _this.parentIdOne
      let parentIdTwo = _this.parentIdTwo
      console.log('parentIdOne:' + parentIdOne)
      console.log('parentIdTwo:' + parentIdTwo)

      _this.categoryForm.level = level
      if (level === 2 && Tool.isEmpty(parentIdOne)) {
        Toast.warning('请选择一级分类后再新增!', _this)
        return ''
      }
      if (level === 3 && Tool.isEmpty(parentIdTwo)) {
        Toast.warning('请选择二级分类后再新增!', _this)
        return ''
      }
      _this.categoryVisible = true
    },
    list() {
      let _this = this
      let level = _this.queryForm.level
      // 根据selete选择条件查询不同的card
      if (level === 1) {
        _this.queryForm.parentId = ''
        _this.listOne()
      } else if (level === 2) {
        _this.listTwo()
      } else {
        _this.listThree()
      }
    },
    save() {
      let _this = this
      let level = _this.categoryForm.level
      console.log(
        'level:' +
          _this.categoryForm.level +
          'name:' +
          _this.categoryForm.categoryName
      )
      if (level === 2) {
        _this.categoryForm.parentId = _this.parentIdOne
      }
      if (level === 3) {
        _this.categoryForm.parentId = _this.parentIdTwo
      }
      _this.isLoading(level, _this)
      _this.$ajax
        .post(
          process.env.VUE_APP_SERVER + '/system/category/save',
          _this.categoryForm
        )
        .then(response => {
          _this.closeLoading(level, _this)
          let resp = response.data
          if (resp.success) {
            _this.queryForm = {
              level: level,
              categoryName: '',
              parentId: ''
            }
            if (level === 1) {
              Toast.success('新增一级分类成功', _this)
              _this.queryForm.parentId = ''
              _this.list()
            } else if (level === 2) {
              Toast.success('新增二级分类成功', _this)
              _this.queryForm.parentId = _this.parentId
              _this.list()
            } else if (level === 3) {
              Toast.success('新增三级分类成功', _this)
              _this.queryForm.parentId = _this.parentId
              _this.list()
            }
            _this.cancelForm()
          } else {
            Toast.warning(resp.message, _this)
          }
        })
    },
    cancelForm() {
      let _this = this
      _this.categoryVisible = false
      _this.deleteDialogVisible = false
      _this.categoryForm = {
        level: '',
        categoryName: '',
        categoryId: ''
      }
      _this.deleteId = ''
      _this.deleteLevel = 0
    },
    /**
     * 列表查询
     */
    listOne() {
      let _this = this
      _this.loadingOne = true
      _this.$ajax
        .post(
          process.env.VUE_APP_SERVER + '/system/category/list',
          _this.queryForm
        )
        .then(response => {
          _this.loadingOne = false
          let resp = response.data
          if (resp.success) {
            _this.categoryOne = resp.content.list
          }
        })
    },
    listTwo() {
      let _this = this
      _this.loadingTwo = true
      _this.$ajax
        .post(
          process.env.VUE_APP_SERVER + '/system/category/list',
          _this.queryForm
        )
        .then(response => {
          _this.loadingTwo = false
          let resp = response.data
          if (resp.success) {
            _this.categoryTwo = resp.content.list
            _this.parentIdOne = _this.queryForm.parentId
          }
        })
    },
    listThree() {
      let _this = this
      _this.loadingThree = true
      _this.$ajax
        .post(
          process.env.VUE_APP_SERVER + '/system/category/list',
          _this.queryForm
        )
        .then(response => {
          _this.loadingThree = false
          let resp = response.data
          if (resp.success) {
            _this.categoryThree = resp.content.list
            _this.parentIdTwo = _this.queryForm.parentId
          }
        })
    },
    goBack() {
      let _this = this
      _this.$router.push('welcome')
    },
    deleteCategory() {
      let _this = this
      let level = _this.deleteLevel
      _this.isLoading(level, _this)
      _this.$ajax
        .delete(
          process.env.VUE_APP_SERVER +
            '/system/category/delete/' +
            _this.deleteId
        )
        .then(response => {
          _this.closeLoading(level, _this)
          let resp = response.data
          if (resp.success) {
            Toast.success('删除分类成功!', _this)
            _this.queryForm.level = level
          } else {
            Toast.warning(resp.message, _this)
          }
          _this.deleteDialogVisible = false
          _this.cancelForm()
          _this.list()
        })
    },
    isLoading(level, _this) {
      if (level === 1) {
        _this.loadingOne = true
      }
      if (level === 2) {
        _this.loadingTwo = true
      }
      if (level === 2) {
        _this.loadingThree = true
      }
    },
    closeLoading(level, _this) {
      if (level === 1) {
        _this.loadingOne = false
      }
      if (level === 2) {
        _this.loadingTwo = false
      }
      if (level === 2) {
        _this.loadingThree = false
      }
    },
    deleteDialog(row) {
      let _this = this
      _this.deleteId = row.categoryId
      _this.deleteLevel = row.level
      _this.deleteDialogVisible = true
    }
  }
}
</script>
<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}
</style>

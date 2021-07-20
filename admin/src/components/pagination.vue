<template>
  <pagination
    v-show="total > 0"
    :total="total"
    :page.sync="page"
    :limit.sync="limit"
    @pagination="getList"
  />
</template>

<script>
import Pagination from '@/components/Pagination'
export default {
  components: { Pagination },
  data() {
    return {
      list: null, // 请求回来填充表格的数据
      total: 0, // 数据总条数
      page: 1, // 默认显示第1页
      limit: 10 // 默认一次显示10条数据
    }
  },
  methods: {
    getlist() {
      var start = (this.page - 1) * this.limit
      var end = this.page * this.limit
      this.$axios
        .get(url + '?start=' + start + '&end=' + end)
        .then(response => {
          this.list = response.data.items
          this.total = response.data.total
          resolve()
        })
        .catch(err => {
          reject(err)
        })
    }
  }
}
</script>

Toast = {
  success: function(message, _this) {
    _this.$notify({
      title: '成功',
      message: message,
      type: 'success'
    })
  },

  error: function(message, _this) {
    _this.$notify.error({ title: '错误', message })
  },

  warning: function(message, _this) {
    _this.$notify({
      title: '警告',
      message: message,
      type: 'warning'
    })
  }
}

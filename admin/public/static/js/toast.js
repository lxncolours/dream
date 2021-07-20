Toast = {
  success: function(message, _this) {
    _this.$message({
      message: message,
      type: 'success'
    })
  },

  error: function(message, _this) {
    _this.$message.error(message)
  },

  warning: function(message, _this) {
    _this.$message({
      message: message,
      type: 'warning'
    })
  }
}

Validator = {
  require: function(value, text) {
    if (Tool.isEmpty(value)) {
      Toast.warning(text + '不能为空')
      return false
    } else {
      return true
    }
  },

  length: function(value, text, min, max) {
    if (Tool.isEmpty(value)) {
      return true
    }
    if (!Tool.isLength(value, min, max)) {
      // Toast.warning(text + '长度' + min + '~' + max + '位')
      return false
    } else {
      return true
    }
  },

  checkLoginName: function(rule, value, callback) {
    if (!value) {
      return callback(new Error('用户账号不能为空'))
    }
    setTimeout(() => {
      if (!Tool.checkStr(value)) {
        callback(new Error('请输入16位数字和字母'))
      } else {
        if (!length(value, '', 0, 16)) {
          callback(new Error('账号长度为0-16位'))
        } else {
          callback()
        }
      }
    }, 1000)
  },
  validatePass: function(rule, value, callback) {
    if (value === '') {
      callback(new Error('请输入密码'))
    } else {
      if (!Tool.checkStr(value)) {
        callback(new Error('请输入16位数字和字母'))
      } else {
        if (!Tool.isLength(value, 0, 16)) {
          callback(new Error('密码长度为0-16位'))
        } else {
          if (this.user.checkPass !== '') {
            this.$refs.user.validateField('checkPass')
          }
          callback()
        }
      }
    }
  },
  validatePass2: function(rule, value, callback) {
    if (value === '') {
      callback(new Error('请再次输入密码'))
    } else if (value !== this.user.pass) {
      callback(new Error('两次输入密码不一致!'))
    } else {
      callback()
    }
  }
}

const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 7000,
    client: {
      overlay: false,
    },
    // proxy: {
    //   '/api': {
    //     target: 'http://113.44.84.103:8080',  //你要跨域访问的网址
    //     changeOrigin: true,   // 允许跨域
    //     rewrite: (path) => path.replace(/^\/api/, '') // 重写路径把路径变成空字符
    //   }
    // }
  },
})

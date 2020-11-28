<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <!-- or name="fade" -->
      <router-view :key="key"></router-view>
      <!-- vue-router导航切换 时，如果两个路由都渲染同个组件，
      组件的生命周期方法（created或者mounted）不会再被调用, 组件会被重用，显示上一个路由渲染出来的自建 -->
      <!-- <router-view/> -->
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  // 计算属性
  computed: {
    key() {
      return this.$route.name !== undefined ? this.$route.name + +new Date() : this.$route + +new Date()
    }
  }
}
</script>

<style scoped>
.app-main {
  /*50 = navbar  */
  min-height: calc(100vh - 50px);
  position: relative;
  overflow: hidden;
}
</style>

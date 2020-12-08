<template>
  <div class="app-container">
    <!-- 章节列表 -->
    <div style="text-align: center">
      <el-button type="primary" @click="prev()">上一步</el-button>
      <el-button type="primary" @click="next()">下一步</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    prev: function () {
      this.$parent.active = 0;
    },
    next: function () {
      this.$parent.active = 3;
    },
  },
};
</script>
<template>
  <div class="app-container">
    <div style="text-align: center">
      <el-button type="primary" @click="prev()">上一步</el-button>
      <el-button type="primary" :disabled="publishBtd" @click="next()"
        >发布</el-button
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      publishBtd: false,
    };
  },
  methods: {
    prev: function () {
      this.$parent.active = 1;
    },
    next: function () {
      this.$parent.active = 3;
    },
  },
};
</script>
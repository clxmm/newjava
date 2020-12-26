<template>
  <div class="app-container">
    <!-- 步骤导航 -->
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="active"
      finish-status="success"
      simple
      style="margin-botton: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="发布课程" />
    </el-steps>

    <!-- 步骤内容 -->
    <!-- 填写课程基本信息 info -->
    <info v-if="active === 0" />

    <!-- 创建课程大纲  chapter index-->
    <chapter v-if="active === 1" />

    <!-- 发布课程 publish-->
    <Publish v-if="active === 2 || active === 3" />
  </div>
</template>

<script>
// 引入子组件
import Info from "@/views/course/components/Info";
import Chapter from "@/views/course/components/Chapter"; // 找Chapter.vue or 找Chapter/Index.vue
import Publish from "@/views/course/components/Publish";

export default {
  components: { Info, Chapter, Publish }, //  注册子组件
  data() {
    return {
      active: 0,
      courseId: "",
    };
  },
  created() {
    if (this.$route.name === "CourseInfoEdit") {
      this.courseId = this.$route.params.id;
      this.active = 0;
    }
    if (this.$route.name === "CourseChapterEdit") {
      this.courseId = this.$route.params.id;
      this.active = 1;
    }
  },
};
</script>

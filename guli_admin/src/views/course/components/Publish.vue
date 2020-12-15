<template>
  <div class="app-container">
  <!--课程预览-->
    <div class="ccInfo">
      <img :src="coursePublish.cover">
      <div class="main">
        <h2>{{ coursePublish.title }}</h2>
        <p class="gray"><span>共{{ coursePublish.lessonNum }}课时</span></p>
        <p><span>所属分类：{{ coursePublish.subjectParentTitle }} — {{ coursePublish.subjectTitle }}</span></p>
        <p>课程讲师：{{ coursePublish.teacherName }}</p>
        <h3 class="red">￥{{ coursePublish.price }}</h3>
      </div>

    </div>
    <div style="text-align: center">
      <el-button type="primary" @click="prev()">上一步</el-button>
      <el-button type="primary" :disabled="publishBtd" @click="publish()"
        >发布</el-button
      >
    </div>
  </div>
</template>

<script>

import courseApi from '@/api/course'

export default {


  data() {
    return {
      publishBtd: false,
      coursePublish: {},
    };
  },
  methods: {
    prev: function () {
      this.$parent.active = 1;
    },
    next: function () {
      this.$parent.active = 3;
    },
    // / 获取课程发布信息
    fetchCoursePublishById: function(id) {
      courseApi.getCoursePublishById(id)
      .then(response => {
        this.coursePublish = response.data.item;
      })
    },
    // 发布课程
    publish() {
      this.publishBtd = true
      courseApi.publishCourseById(this.$parent.courseId).then(response => {
        this.$parent.active = 3
        this.$message.success(response.message)
      })
    }
  },
  created () {
    // 初始化课程基本信息
    if (this.$parent.courseId) {
      this.fetchCoursePublishById(this.$parent.courseId)
    }
  }
};
</script>
<style>

.ccInfo {
  background: #f5f5f5;
  padding: 20px;
  overflow: hidden;
  border: 1px dashed #DDD;
  margin-bottom: 40px;
  position: relative;
}
.ccInfo img {
  background: #d6d6d6;
  width: 500px;
  height: 278px;
  display: block;
  float: left;
  border: none;

}
.ccInfo .main {
  margin-left: 520px;
}

.ccInfo .main h2 {
  font-size: 28px;
  margin-bottom: 30px;
  line-height: 1;
  font-weight: normal;
}

.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}

.ccInfo .main h3 {
  left: 540px;
  bottom: 20px;
  line-height: 1;
  font-size: 28px;
  color: #d32f24;
  font-weight: normal;
  position: absolute;

}

</style>
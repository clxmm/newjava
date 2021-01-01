<template>
  <div class="app-container">
    <!-- 添加章节按钮 -->
    <div>
      <el-button type="primary" @click="addChapter()">添加章节</el-button>
    </div>

    <!-- 章节列表 -->
    <ul class="chapterList">
      <li v-for="chapter in chapterList" :key="chapter.id">
        <p>
          {{ chapter.title }}
          <span class="acts">
            <el-button type="text" @click="addVideo(chapter.id)"
              >添加课时</el-button
            >
            <el-button type="text" @click="editChapter(chapter.id)"
              >编辑</el-button
            >
            <el-button type="text" @click="removeChapterById(chapter.id)"
              >删除</el-button
            >
          </span>
        </p>
        <ul class="chapterList videoList">
          <li v-for="video in chapter.children" :key="video.id">
            <p>
              {{ video.title }}
              <el-tag type="danger" v-if="!video.videoSourceId" size="mini">{{
                "未上传视频"
              }}</el-tag>
              <span class="acts">
                <el-tag type="success" v-if="video.free" size="mini">{{
                  "免费观看"
                }}</el-tag>
                <el-button type="text" @click="editVideo(chapter.id, video.id)"
                  >编辑</el-button
                >
                <el-button type="text" @click="removeVideoById(video.id)"
                  >删除</el-button
                >
              </span>
            </p>
          </li>
        </ul>
      </li>
      <li></li>
    </ul>

    <!-- 章节列表 -->
    <div style="text-align: center">
      <el-button type="primary" @click="prev()">上一步</el-button>
      <el-button type="primary" @click="next()">下一步</el-button>
    </div>
    <!-- 章节表单对话框 -->
    <chapter-form ref="chapterForm" />

    <video-form ref="videoForm" />
  </div>
</template>

<script>
import chapterApi from "@/api/chapter";
// 引入组件
import ChapterForm from "@/views/course/components/Chapter/Form";
import VideoForm from "@/views/course/components/Vedio/Form";

export default {
  // 注册组件
  components: { ChapterForm, VideoForm },
  data() {
    return {
      chapterList: [], // 章节嵌套列表
    };
  },
  created() {
    this.fetchNodeList();
  },
  methods: {
    //
    removeVideoById: function (videoid) {
      this.$refs.videoForm.deleteVideoById(videoid);
    },
    //
    editVideo: function (chapterId, videoId) {
      this.$refs.videoForm.open(chapterId, videoId);
    },
    // 添加课时
    addVideo: function (chapterId) {
      this.$refs.videoForm.open(chapterId);
    },
    //  编辑章节
    editChapter: function (id) {
      this.$refs.chapterForm.open(id);
    },
    //
    addChapter: function () {
      this.$refs.chapterForm.open();
    },

    // 删除章节
    removeChapterById: function (chapterId) {
      this.$confirm("此操作将永久删除该章节，是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return chapterApi.removeById(chapterId);
        })
        .then((response) => {
          this.fetchNodeList();
          this.$message.success(response.message);
        })
        .catch((response) => {
          if (response === "cancel") {
            this.$message.info("取消删除");
          }
        });
    },
    // 获取后端章节列表数据
    fetchNodeList: function () {
      chapterApi.getNestedTreeList(this.$parent.courseId).then((response) => {
        this.chapterList = response.data.item;
      });
    },

    prev: function () {
      this.$parent.active = 0;
    },
    next: function () {
      this.$parent.active = 3;
    },
  },
};
</script>

<style>
.chapterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}

.chapterList li {
  position: relative;
}

.chapterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #ddd;
}

.chapterList .acts {
  float: right;
  font-size: 14px;
}

.videoList {
  padding-left: 50px;
}

.videoList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dashed #ddd;
}
</style>
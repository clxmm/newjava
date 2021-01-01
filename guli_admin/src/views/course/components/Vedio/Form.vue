<template>
  <!-- 添加和修改课时表单 -->
  <el-dialog :visible="dialogVisible" title="添加课时" @close="close()">
    <el-form :model="video" label-width="120px">
      <el-form-item label="课时标题">
        <el-input v-model="video.title" />
      </el-form-item>

      <el-form-item label="课时排序">
        <el-input-number v-model="video.sort" :min="0" />
      </el-form-item>
      <el-form-item label="是否免费">
        <el-radio-group v-model="video.free">
          <el-radio :label="true">免费</el-radio>
          <el-radio :label="false">默认</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="上传视频">
        <el-upload
          ref="upload"
          action="http://127.0.0.1:8130/admin/vod/media/upload"
          :on-success="handleUploadSuccess"
          :on-error="handleUploadError"
          :file-list="fileList"
          :on-exceed="handleUploadExceed"
          :on-remove="handleOnRemove"
          :before-remove="handleBeforeRemove"
          :limit="1"
          :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="primary"
            >选取视频</el-button
          >
          <el-button
            :disabled="uploadBtnDisabled"
            style="margin-left: 10px"
            size="small"
            type="success"
            @click="submitUpload()"
            >上传到服务器</el-button
          >
        </el-upload>
      </el-form-item>
      <!-- 上传视频 -->
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close()">取 消</el-button>
      <el-button type="primary" @click="saveOrUpdate()">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import videoApi from "@/api/video";

export default {
  data() {
    return {
      dialogVisible: false,
      video: {
        sort: 0,
        free: false,
      },
      fileList: [], //上传文件列表
      uploadBtnDisabled: false,
    };
  },
  methods: {
    handleBeforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },

    // 执行视频文件的删除
    handleOnRemove(file, fileList) {
      if (!this.video.videoSourceId) {
        return
      }
      videoApi.removeByVodId(this.video.videoSourceId).then(response => {
        this.video.videoSourceId = ''
        this.video.videoOriginalName = ''
        videoApi.update(this.video)
        this.$message.success(response.message)

      })
    },
    handleUploadSuccess(response, file, fileList) {
      this.uploadBtnDisabled = false;
      if (response.success) {
        this.video.videoSourceId = response.data.videoId;
        this.video.videoOriginalName = file.name;
      } else {
        this.$message.error("上传失败1");
      }
    },
    handleUploadError() {
      this.uploadBtnDisabled = false;
      this.$message.error("上传失败2");
    },
    handleUploadExceed() {
      this.$message.warning("想要重新上传视频，请先删除已上传的视频");
    },
    submitUpload() {
      this.uploadBtnDisabled = true;
      this.$refs.upload.submit(); // 提交上传请求
    },
    open(chapterId, videoId) {
      this.dialogVisible = true;
      this.video.chapterId = chapterId;
      if (videoId) {
        videoApi.getVideoById(videoId).then((response) => {
          this.video = response.data.item;
          // 回显
          if (this.video.videoOriginalName) {
            this.fileList = [{ name: this.video.videoOriginalName }];
          }
        });
      }
    },
    close() {
      this.dialogVisible = false;
      this.resetForm();
    },
    saveOrUpdate() {
      if (!this.video.id) {
        this.save();
      } else {
        this.update();
      }
    },
    save() {
      this.video.courseId = this.$parent.$parent.courseId;
      videoApi.save(this.video).then((response) => {
        this.$message.success(response.message);
        this.close();
        this.$parent.fetchNodeList();
      });
    },
    update() {
      videoApi.update(this.video).then((response) => {
        this.$message.success(response.message);
        this.close();
        this.$parent.fetchNodeList();
      });
    },
    resetForm() {
      this.video = {
        free: false,
        sort: 0,
      };
      this.fileList = []; //重置视频上传列表
    },
    deleteVideoById(id) {
      this.$confirm("此操作将永久删除该课时，是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return videoApi.deleteVideoById(id);
        })
        .then((response) => {
          this.$parent.fetchNodeList();
          this.$message.success(response.message);
        })
        .catch((response) => {
          if (response === "cancel") {
            this.$message.info("取消删除");
          }
        });
    },
  },
};
</script>
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
    };
  },
  methods: {
    open(chapterId, videoId) {
      this.dialogVisible = true;
      this.video.chapterId = chapterId;
      if (videoId) {
        videoApi.getVideoById(videoId).then((response) => {
          this.video = response.data.item;
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
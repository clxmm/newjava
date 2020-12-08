<template>
  <div class="app-container">
    <!-- 表单输入 -->
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name" />
      </el-form-item>
      <el-form-item label="入驻时间">
        <el-date-picker v-model="teacher.joinDate" value-format="yyyy-MM-dd" />
      </el-form-item>

      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" :min="0" />
      </el-form-item>

      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level">
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>

      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" />
      </el-form-item>

      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career" :rows="10" type="textarea" />
      </el-form-item>

      <!-- 讲师头像：TODO -->
      <el-form-item label="讲师头像">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8120/admin/oss/file/upload?module=clxmm"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :on-error="errHandler"
        >
          <img v-if="teacher.avatar" :src="teacher.avatar" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate()"
          >保存</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacherApi from "@/api/teacher";

export default {
  data() {
    return {
      // 讲师对象,初始化默认值
      teacher: {
        sort: 0,
        level: 1,
      },
      // 默认按钮可用
      saveBtnDisabled: false,
    };
  },
  created() {
    if (this.$route.params.id) {
      this.getTeacherById(this.$route.params.id);
    }
  },
  methods: {
    saveOrUpdate: function () {
      this.saveBtnDisabled = true;
      if (!this.teacher.id) {
        this.saveData();
      } else {
        this.updateById();
      }
    },

    // 保存数据
    saveData: function () {
      teacherApi.save(this.teacher).then((response) => {
        // 成功提示
        this.$message({
          message: response.message,
          type: "success",
        });

        // 跳转
        this.$router.push({ path: "/teacher" });
      });
    },
    // 更新数据
    updateById: function () {
      teacherApi.updateTeacher(this.teacher).then((response) => {
        // 成功提示
        this.$message({
          message: response.message,
          type: "success",
        });

        // 跳转
        this.$router.push({ path: "/teacher" });
      });
    },
    // 根据id查询记录
    getTeacherById: function (id) {
      teacherApi.getTeacherById(id).then((response) => {
        // console.log(response)
        this.teacher = response.data.teacher;
      });
    },
    // 图片上传成功
    handleAvatarSuccess(res, file) {
      if (res.success) {
        console.log(res);
        this.teacher.avatar = res.data.url;
        // 强制渲染
        this.$forceUpdate();
      } else {
        this.$message.error('文件上传失败，请联系管理员')
      }
    },
    // 图片上传之前的校验
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    // htpp 的失败
    errHandler() {

        this.$message.error('上传失败，http失败')
    }

  },
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
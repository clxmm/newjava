<template>
  <div class="app-container">
    <el-form label-width="120px">
      <!-- 课程信息表单 -->
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfo.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"
        />
      </el-form-item>
      <!-- 课程讲师 TODO -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option
            v-for="item in teacherList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <!-- 所属分类 TODO -->
      <el-form-item label="课程分类">
        <el-select
          v-model="courseInfo.subjectParentId"
          @change="subjectChange"
          placeholder="请选择"
        >
          <el-option
            v-for="item in subjectList"
            :key="item.id"
            :label="item.title"
            :value="item.id"
          >
          </el-option>
        </el-select>
        <el-select v-model="courseInfo.subjectId" placeholder="请选择">
          <el-option
            v-for="item in subjectLevelTwoList"
            :key="item.id"
            :label="item.title"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number
          :min="0"
          v-model="courseInfo.lessonNum"
          controls-position="right"
          placeholder="请填写课程的总课时数"
        />
      </el-form-item>

      <!-- 课程简介 TODO -->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description" />
      </el-form-item>

      <!-- 课程封面 TODO -->
      <el-form-item label="课程封面">
        <el-upload
          class="upload-uploader"
          action="http://localhost:8120/admin/oss/file/upload?module=cover"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :on-error="errHandler"
        >
          <img v-if="courseInfo.cover" :src="courseInfo.cover" class="upload" />
          <i v-else class="el-icon-plus upload-uploader-icon"></i>
        </el-upload>
      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfo.price"
          controls-position="right"
          placeholder="免费课程请设置为0元"
        />
        元
      </el-form-item>
    </el-form>

    <div style="text-align: center">
      <el-button
        :disabled="saveBtnDisabled"
        type="primary"
        @click="saveAndNext()"
        >保存并下一步</el-button
      >
    </div>
  </div>
</template>

<script>
import courseApi from "@/api/course";
import teacherApi from "@/api/teacher";
import subjectApi from "@/api/subject";
import Tinymce from "@/components/Tinymce";

export default {
  components: { Tinymce },
  data() {
    return {
      saveBtnDisabled: false, // 按钮是否禁用
      courseInfo: {
        // 表单数据
        price: 0,
        lessonNum: 1,
        // 以下解决表单数据不全时insert语句非空校验
        teacherId: "",
        subjectId: "",
        subjectParentId: "",
        cover: "",
        description: "",
      },
      teacherList: [], // 讲师列表
      subjectList: [], // 课程列表
      subjectLevelTwoList: [], //二级类别
    };
  },
  created() {
    // courseID 来源于路由 
    // if(this.$route.name === 'CourseInfoEdit') {
    //   this.$parent.courseId = this.$route.params.id
    // }

    // courseid 的来源，来源于父组件
    if (this.$parent.courseId) {
      // 回显
      this.getCourseInfoById(this.$parent.courseId);
    } else {
      // 新增
      this.initSubjectList();
    }
    // 获取讲师列表
    this.initTeacherList();
  },
  methods: {
    // 根据课程id获取课程信息
    getCourseInfoById: function (id) {
      // console.log(id)
      courseApi.getCourseInfoByid(id).then((response) => {
        this.courseInfo = response.data.courseInfo;

        // 初始化分类列表
        subjectApi.getNestedTreeList().then((response) => {
          this.subjectList = response.data.items;

          // 填充二级列表
          this.subjectList.forEach((subject) => {
            // 找到和courseInfo.subjectParentId一致的父类别记录
            if (subject.id === this.courseInfo.subjectParentId) {
              // 拿到当前类别下的子类别列表，将子类别列表填入二级下拉菜单列表
              this.subjectLevelTwoList = subject.children;
            }
          });
        });
      });
    },

    // 切换一级类别下来列表
    subjectChange: function (value) {
      // console.log( "11" + value)
      this.subjectList.forEach((subject) => {
        if (subject.id === value) {
          this.courseInfo.subjectId = "";
          this.subjectLevelTwoList = subject.children;
        }
      });
    },
    initSubjectList: function () {
      subjectApi.getNestedTreeList().then((response) => {
        this.subjectList = response.data.items;
      });
    },
    // 获取讲师列表
    initTeacherList: function () {
      teacherApi.list().then((response) => {
        this.teacherList = response.data.items;
      });
      //   console.log(this.teacherList)
    },
    
    // 保存 下一步
    saveAndNext: function () {
      this.saveBtnDisabled = true;

      if(!this.$parent.courseId) {
          this.saveData();
      } else{
          this.updateDate();
      }
      
    },

    // 保存课程信息
    saveData: function () {
      courseApi.saveCourseInfo(this.courseInfo).then((response) => {
        this.$message.success(response.message);
        this.$parent.courseId = response.data.courseId;
        this.$parent.active = 1; // 下一步
      });
    },

    // 更新课程信息
    updateDate: function () {
      courseApi.updateCourseInfo(this.courseInfo).then((response) => {
        this.$message.success(response.message);
        this.$parent.courseId = response.data.courseId;
        this.$parent.active = 1; // 下一步
      });
    },

    // 图片上传成功
    handleAvatarSuccess(res, file) {
      if (res.success) {
        // console.log(res);
        this.courseInfo.cover = res.data.url;
        // 强制渲染
        this.$forceUpdate();
      } else {
        this.$message.error("文件上传失败，请联系管理员");
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
      this.$message.error("上传失败，http失败");
    },
  },
};
</script>


<style>
.tinymce-container {
  position: relative;
  line-height: normal;
}
.upload-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.upload-uploader .el-upload:hover {
  border-color: #409eff;
}
.upload-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.upload {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
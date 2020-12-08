<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-form inline>
      <el-form-item>
        <!-- <el-input v-model="searchObj.name" placeholder="讲师" /> -->
        <el-autocomplete
          class="inline-input"
          value-key = "name"
          v-model="searchObj.name"
          :fetch-suggestions="querySearch"
          placeholder="请输入教师"
          :trigger-on-focus="false"

        ></el-autocomplete>
      </el-form-item>

      <el-form-item>
        <el-select v-model="searchObj.level" clearable placeholder="头衔">
          <el-option value="1" label="高级讲师" />
          <el-option value="2" label="首席讲师" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-date-picker
          v-model="searchObj.joinDateBegin"
          placeholder="开始时间"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="-">
        <el-date-picker
          v-model="searchObj.joinDateEnd"
          placeholder="结束时间"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="fetchData()"
          >查询</el-button
        >
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form-item>
    </el-form>

    <div style="margin-bottom: 10px">
      <!-- 工具条 -->
      <el-button type="danger" size="mini" @click="deleteByIds">
        批量删除
      </el-button>
    </div>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection"></el-table-column>

      <el-table-column label="#" width="50">
        <template slot-scope="scope">
          {{ (pageSize - 1) * pageNumber + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="名称" width="80" />
      <el-table-column prop="level" label="头衔" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.level === 1" type="success">
            高级讲师
          </el-tag>
          <el-tag v-if="scope.row.level === 2"> 首席讲师 </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="资历" />
      <el-table-column prop="sort" label="排序" width="60" />
      <el-table-column prop="joinDate" label="入驻时间" width="100" />
      <!-- 操作 -->
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button size="mini" type="primary">修改</el-button>
          </router-link>
          <el-button size="mini" type="danger" @click="removeByid(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!--  分页 -->
    <el-pagination
      layout="prev, pager, next,sizes, ->,total"
      :total="total"
      :page-sizes="[5, 10, 20]"
      :page-size="pageNumber"
      @size-change="changePageSize"
      @current-change="changeCurrentPage"
      :current-page="pageSize"
      style="padding: 30px; text-align: center"
    >
    </el-pagination>
  </div>
</template>

<script>
import teacherApi from "@/api/teacher";

export default {
  data() {
    return {
      list: [], //讲师列表
      pageSize: 1, // 当前页
      pageNumber: 5, // 每页记录数
      total: 0, // 总记录数
      searchObj: {},
      rmoveIds: [], //批量删除时选中的ids
    };
  },

  created() {
    // 页面成功渲染后获取数据
    this.fetchData();
  },

  methods: {
    fetchData() {
      // 调用api
      teacherApi
        .pageList(this.pageSize, this.pageNumber, this.searchObj)
        .then((response) => {
          this.list = response.data.rows;
          this.total = response.data.total;
        });
    },
    // 改变页码
    changeCurrentPage: function (page) {
      // console.log(page)
      this.pageSize = page;
      this.fetchData();
    },
    // 改变每页记录数
    changePageSize: function (page) {
      this.pageNumber = page;
      this.fetchData();
    },
    //  清空按钮
    resetData: function () {
      this.searchObj = {};
      this.fetchData();
    },
    // 删除
    removeByid: function (id) {
      this.$confirm("此操作将永久删除该讲师, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          teacherApi.removeById(id).then((response) => {
            this.fetchData();

            // 成功提示
            this.$message({
              message: response.message,
              type: "success",
            });
          });
        })
        .catch((err) => {
          console.log(err);
          if (err === "cancel") {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          }
        });
    },

    // 表格中多选项发生变化的时候触发
    handleSelectionChange: function (selection) {
      this.rmoveIds = selection;
      //   console.log(this.rmoveIds);
    },
    // 批量删除
    deleteByIds: function () {
      // 判断是否有数据
      if (this.rmoveIds.length === 0) {
        this.$message({
          message: "请先选择数据",
          type: "warning",
        });
        return;
      }

      this.$confirm("此操作将永久删除这些数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 拿到选中的数据
          let idList = [];
          this.rmoveIds.forEach((i) => {
            console.log(i);
            idList.push(i.id);
          });
          //   console.log(idList);
          return teacherApi.removeByIds(idList);
        })
        .then((response) => {
          // 刷新数据
          this.fetchData();
          this.rmoveIds = [];
          this.$message({
            message: response.message,
            type: "success",
          });
        })
        .catch((err) => {
          console.log(err);
          if (err === "cancel") {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          }
        });
    },
    // 输入建议
    querySearch: function(quertString,callback) {
        console.log(quertString)
        teacherApi.getTeacherNamesByKey(quertString).then(response => {
            // console.log(response.data.nameList)
            callback(response.data.nameList)
        })
    }


  },
};
</script>
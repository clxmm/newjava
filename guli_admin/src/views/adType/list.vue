<template>
  <div class="app-container">
    <!-- 工具按钮 -->
    <div style="margin-bottom: 10px">
      <router-link to="/ad/type-create">
        <el-button type="primary" size="mini" icon="el-icon-edit"
          >添加推荐位</el-button
        >
      </router-link>
    </div>

    <!-- 表格 -->
    <el-table :data="list" border stripe>
      <el-table-column label="#" width="50">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="title" label="推荐位名称" />
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/ad/type-edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >修改</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeById(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="changePageSize"
      @current-change="changeCurrentPage"
    />
  </div>
</template>
<script>
import adTypeApi from "@/api/adType";
export default {
  data() {
    return {
      list: [],
      total: 0,
      page: 1,
      limit: 10,
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      // 调用api
      adTypeApi.pageList(this.page, this.limit).then((response) => {
        this.list = response.data.records;
        this.total = response.data.total;
      });
    },
    // 每页记录数改变，size：回调参数，表示当前选中的“每页条数”
    changePageSize() {
      this.limit = size;
      this.fetchData();
    },
    // 改变页码，page：回调参数，表示当前选中的“页码”
    changeCurrentPage(page) {
      this.page = page
      this.fetchData()
    },
    // 根据id删除数据
    removeById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return adTypeApi.removeById(id)
      }).then((response) => {
        this.fetchData()
        this.$message.success(response.message)
      }).catch(error => {
        // 当取消时会进入catch语句:error = 'cancel'
        // 当后端服务抛出异常时：error = 'error'
        if (error === 'cancel') {
          this.$message.info('取消删除')
        }
      })
    }
  },
};
</script>
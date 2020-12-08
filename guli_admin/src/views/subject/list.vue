<template>
  <div class="app-container">
    <el-input placeholder="输入关键字进行过滤" v-model="filterText"> </el-input>
    <el-tree :data="subjectList" ref="tree" :props="defaultProps" :v-model="filterText" :filter-node-method="filterNode">
    </el-tree>
  </div>
</template>

<script>
import subjectApi from "@/api/subject";

export default {
  data() {
    return {
      defaultProps: {
        children: "children",
        label: "title",
      },
      subjectList: [], // 数据列表
      filterText: "", // 过滤文本
    };
  },
  created() {
    this.fetchNodeList();
  },
  watch: {
      filterText(val) {
          console.log(val)
          this.$refs.tree.filter(val);
      }
  },
  methods: {
    fetchNodeList() {
      subjectApi.getNestedTreeList().then((response) => {
        console.log(response.data);
        this.subjectList = response.data.items;
      });

      console.log(this.subjectList);
    },

    filterNode(value,data) {
        if (!value) return true;
        return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1;
    }
  },
};
</script>
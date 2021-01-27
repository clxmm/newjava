<template>
    <div class="app-container">
        <!-- 工具按钮 -->
        <div style="margin-bottom: 10px">
            <router-link to="/ad/type-create">
                <el-button type="primary" size="mini" icon="el-icon-edit">添加推荐位</el-button>
            </router-link>
        </div>
        
    </div>
</template>
<script>
import adTypeApi from '@/api/adType'
export default {
    data () {
        return {
            list:[],
            total: 0,
            page: 1,
            limit: 10
        }
    },
    created () {
        
    },
    methods: {
        fetchData() {
            
        }
        
    }
}
</script>
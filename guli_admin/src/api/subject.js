import request from '@/utils/request'

export default {
    // 获取课程列表的树形结构
    getNestedTreeList() {
        return request({
            url: "/admin/edu/subject/nested-list",
            method: "post"
        })
    }

}



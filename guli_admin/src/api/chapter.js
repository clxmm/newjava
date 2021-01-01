import request from '@/utils/request'

export default {

    getNestedTreeList(courseId) {
        return request({
            url: `/admin/edu/chapter/nested-list/${courseId}`,
            method: 'get'
        })
    },

    // 根据章节id删除
    removeById(id) {
        return request({
            url: `/admin/edu/chapter/deleteChapterById/${id}`,
            method: 'delete'
        })
    },

    //保存章节
    save(chapter) {
        console.log(chapter)
        return request({
            url: `/admin/edu/chapter/save`,
            method: 'post',
            data: chapter
        })

    },

    // 获取章节
    getById(id) {
        return request({
            url: `/admin/edu/chapter/gatChapterById/${id}`,
            method: 'get'
        })
    },
    // 更新
    updateById(chapter) {
        return request({
            url: `/admin/edu/chapter/updateChapterById`,
            method: 'put',
            data: chapter
        })
    }
}

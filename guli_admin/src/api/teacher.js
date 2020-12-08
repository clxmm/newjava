
// @ 符号在build/webpack.base.conf.js 中配置 表示 'src' 路径

import request from '@/utils/request'

export default {

    list() {
        return request({
            url: '/admin/edu/teacher/list',
            method: 'get'
        })
    },
    pageList(pageNumber, pageSize, searchObj) {
        return request({
            url: `/admin/edu/teacher/page/${pageNumber}/${pageSize}`,
            method: 'get',
            params: searchObj
        })
    },
    // 移除讲师
    removeById(id) {
        return request({
            url: `/admin/edu/teacher/remove/${id}`,
            method: 'delete'
        })
    },
    // 保存讲师
    save(teacher) {
        return request({
            url: `/admin/edu/teacher/save`,
            method: 'post',
            data: teacher
        })
    },
    //  根据id获取讲师
    getTeacherById(id) {
        return request({
            url: `/admin/edu/teacher/getTeacheById/${id}`,
            method: 'get'
        })
    },
    //更新讲师
    updateTeacher(teacher) {
        return request({
            url: `/admin/edu/teacher/update`,
            method: 'put',
            data: teacher
        })
    },
    // 批量删除
    removeByIds(ids) {
        return request({
            url: '/admin/edu/teacher/removeS',
            data: ids,
            method: 'delete'
        })
    },
    // 根据关键字查询老师的姓名
    getTeacherNamesByKey(key) {
        return request({
            url: `/admin/edu/teacher/list/name/${key}`,
            method: 'get'
        })
    }


}
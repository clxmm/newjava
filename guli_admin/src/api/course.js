import request from '@/utils/request'

export default {
    // 保存课程表单
    saveCourseInfo(course) {
        return request({
            url: '/admin/edu/course/save-course-info',
            method: 'post',
            data: course
        })
    },
    // 根据课程id获取课程信息
    getCourseInfoByid(id) {
        return request({
            url: `/admin/edu/course/course-info/${id}`,
            method: 'get'
        })
    },
    updateCourseInfo(course) {
        return request({
            url: '/admin/edu/course/update-course-info',
            method: 'put',
            data: course
        })
    },
    pageList(pageNumber, pageSize, searchObj) {
        return request({
            url: `/admin/edu/course/page/${pageNumber}/${pageSize}`,
            method: 'get',
            params: searchObj
        })
    },
    // 删除课程信息
    removeById(id) {
        return request({
            url: `/admin/edu/course/remove/${id}`,
            method:'delete'
        })
    },

    //根据课程id获取课程发布基本信息
    getCoursePublishById(id) {
        return request({
            url: `admin/edu/course/course-publish/${id}`,
            method: 'get'
        })
    },
    // 根据课程id发布课程
    publishCourseById(id) {
        return request({
            url: `/admin/edu/course/publish-course/${id}`,
            method: 'put'
        })
    }





}